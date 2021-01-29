package by.gaponenko.tools.controller.command.impl;

import by.gaponenko.tools.controller.Router;
import by.gaponenko.tools.controller.command.AttributeName;
import by.gaponenko.tools.controller.command.Command;
import by.gaponenko.tools.controller.command.PagePath;
import by.gaponenko.tools.entity.Order;
import by.gaponenko.tools.exception.ServiceException;
import by.gaponenko.tools.model.service.OrderService;
import by.gaponenko.tools.model.service.ServiceFactory;
import by.gaponenko.tools.util.ParameterName;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ApproveOrderCommand implements Command {
    static Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router(PagePath.ORDERS_PAGE);
        HttpSession session = request.getSession();
        long orderId = Long.parseLong(request.getParameter(ParameterName.ORDER_ID));
        String ordersFilterStatus = (String) session.getAttribute(AttributeName.ORDERS_FILTER_STATUS);
        OrderService orderService = ServiceFactory.getINSTANCE().getOrderService();
        try {
            if (orderService.updateStatus(orderId, Order.Status.APPROVED)) {
                List<Order> orders;
                if (ordersFilterStatus != null) {
                    orders = orderService.findByStatus(Order.Status.valueOf(ordersFilterStatus));
                } else {
                    orders = orderService.findAll();
                }
                session.setAttribute(AttributeName.ORDERS, orders);
            } else {
                logger.log(Level.WARN, "Order is not approved");
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e.getMessage());
            router.setPage(PagePath.ERROR_500);
        }
        return router;
    }
}
