package by.gaponenko.tools.controller.command.impl;

import by.gaponenko.tools.controller.Router;
import by.gaponenko.tools.controller.command.AttributeName;
import by.gaponenko.tools.controller.command.Command;
import by.gaponenko.tools.controller.command.PagePath;
import by.gaponenko.tools.entity.Order;
import by.gaponenko.tools.entity.User;
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

public class FilterOrdersCommand implements Command {
    static Logger logger = LogManager.getLogger();

    private static final int FIRST_PAGE = 1;

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router(PagePath.ORDERS_PAGE);
        HttpSession session = request.getSession();
        String orderStatus = request.getParameter(ParameterName.ORDER_STATUS);
        User user = (User) session.getAttribute(AttributeName.USER);
        OrderService orderService = ServiceFactory.getINSTANCE().getOrderService();
        try {
            List<Order> orders;
            if (user.getRole().equals(User.Role.ADMIN)) {
                orders = orderService.findByStatus(Order.Status.valueOf(orderStatus));
                session.setAttribute(AttributeName.ORDERS, orders);
                session.setAttribute(AttributeName.ORDERS_PAGE_NUMBER, FIRST_PAGE);
                session.setAttribute(AttributeName.ORDERS_FILTER_STATUS, orderStatus);
            }
            if (user.getRole().equals(User.Role.CLIENT)) {
                orders = orderService.findAllByUserIdAndOrderStatus(user.getUserId(),
                        Order.Status.valueOf(orderStatus));
                session.setAttribute(AttributeName.ORDERS, orders);
                session.setAttribute(AttributeName.ORDERS_PAGE_NUMBER, FIRST_PAGE);
                session.setAttribute(AttributeName.ORDERS_FILTER_STATUS, orderStatus);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e.getMessage());
            router.setPage(PagePath.ERROR_500);
        }
        return router;
    }
}
