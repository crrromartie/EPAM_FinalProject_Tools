package by.gaponenko.tools.controller.command.impl;

import by.gaponenko.tools.controller.Router;
import by.gaponenko.tools.controller.command.*;
import by.gaponenko.tools.entity.Order;
import by.gaponenko.tools.entity.User;
import by.gaponenko.tools.exception.ServiceException;
import by.gaponenko.tools.model.service.OrderService;
import by.gaponenko.tools.model.service.ServiceFactory;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.List;

/**
 * The Filter orders command.
 * <p>
 * This command allows a client to filter his orders by status.
 *
 * @author Haponenka Ihar
 * @version 1.0
 */
public class FilterOrdersCommand implements Command {
    static Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router(PagePath.ORDERS_PAGE);
        HttpSession session = request.getSession();
        String orderStatus = request.getParameter(ParameterName.ORDER_STATUS);
        User user = (User) session.getAttribute(AttributeName.USER);
        OrderService orderService = ServiceFactory.getINSTANCE().getOrderService();
        List<Order> orders = Collections.EMPTY_LIST;
        try {
            if (user.getRole().equals(User.Role.ADMIN)) {
                if (orderStatus.equals(CommandConstant.ALL)) {
                    orders = orderService.findAll();
                } else {
                    orders = orderService.findByStatus(Order.Status.valueOf(orderStatus));
                }
            }
            if (user.getRole().equals(User.Role.CLIENT)) {
                if (orderStatus.equals(CommandConstant.ALL)) {
                    orders = orderService.findByUserId(user.getUserId());
                } else {
                    orders = orderService.findByUserIdAndOrderStatus(user.getUserId(),
                            Order.Status.valueOf(orderStatus));
                }
            }
            session.setAttribute(AttributeName.ORDERS, orders);
            session.setAttribute(AttributeName.ORDERS_PAGE_NUMBER, CommandConstant.FIRST_PAGE);
            session.setAttribute(AttributeName.ORDERS_FILTER_STATUS, orderStatus);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e.getMessage());
            router.setPage(PagePath.ERROR_500);
        }
        return router;
    }
}
