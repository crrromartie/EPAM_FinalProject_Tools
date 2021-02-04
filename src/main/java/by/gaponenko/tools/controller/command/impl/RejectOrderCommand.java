package by.gaponenko.tools.controller.command.impl;

import by.gaponenko.tools.controller.Router;
import by.gaponenko.tools.controller.command.*;
import by.gaponenko.tools.entity.Order;
import by.gaponenko.tools.entity.Tool;
import by.gaponenko.tools.exception.ServiceException;
import by.gaponenko.tools.model.service.OrderService;
import by.gaponenko.tools.model.service.ServiceFactory;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

/**
 * The Reject order command.
 * <p>
 * This command allows admin to reject a client order.
 *
 * @author Haponenka Ihar
 * @version 1.0
 */
public class RejectOrderCommand implements Command {
    static Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router(PagePath.ORDERS_PAGE);
        HttpSession session = request.getSession();
        long orderId = Long.parseLong(request.getParameter(ParameterName.ORDER_ID));
        String ordersFilterStatus = (String) session.getAttribute(AttributeName.ORDERS_FILTER_STATUS);
        OrderService orderService = ServiceFactory.getINSTANCE().getOrderService();
        try {
            Optional<Order> optionalOrder = orderService.findById(orderId);
            if (optionalOrder.isPresent()) {
                Tool tool = optionalOrder.get().getTool();
                if (orderService.rejectOrder(orderId, tool.getToolId())) {
                    List<Order> orders;
                    if (ordersFilterStatus != null
                            && !ordersFilterStatus.equals(CommandConstant.ALL)) {
                        orders = orderService.findByStatus(Order.Status.valueOf(ordersFilterStatus));
                    } else {
                        orders = orderService.findAll();
                    }
                    session.setAttribute(AttributeName.ORDERS, orders);
                } else {
                    logger.log(Level.WARN, "Order is not rejected");
                }
            } else {
                logger.log(Level.WARN, "Tool is not exist");
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e.getMessage());
            router.setPage(PagePath.ERROR_500);
        }
        return router;
    }
}
