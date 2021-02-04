package by.gaponenko.tools.controller.command.impl.navigation;

import by.gaponenko.tools.controller.Router;
import by.gaponenko.tools.controller.command.AttributeName;
import by.gaponenko.tools.controller.command.Command;
import by.gaponenko.tools.controller.command.CommandConstant;
import by.gaponenko.tools.controller.command.PagePath;
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
import java.util.List;

/**
 * The Orders client pass command.
 * <p>
 * Forwarding a client to the orders page.
 *
 * @author Haponenka Ihar
 * @version 1.0
 */
public class OrdersClientPassCommand implements Command {
    static Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router(PagePath.ORDERS_PAGE);
        HttpSession session = request.getSession();
        OrderService orderService = ServiceFactory.getINSTANCE().getOrderService();
        User user = (User) session.getAttribute(AttributeName.USER);
        try {
            List<Order> orders = orderService.findByUserId(user.getUserId());
            session.setAttribute(AttributeName.ORDERS, orders);
            session.setAttribute(AttributeName.ORDERS_PAGE_NUMBER, CommandConstant.FIRST_PAGE);
            session.removeAttribute(AttributeName.USERS_FILTER_STATUS);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e.getMessage());
            router.setPage(PagePath.ERROR_500);
        }
        return router;
    }
}
