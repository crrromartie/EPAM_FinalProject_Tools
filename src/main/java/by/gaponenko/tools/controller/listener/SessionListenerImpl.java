package by.gaponenko.tools.controller.listener;

import by.gaponenko.tools.controller.command.AttributeName;
import by.gaponenko.tools.controller.command.CommandConstant;
import by.gaponenko.tools.entity.User;
import by.gaponenko.tools.exception.ServiceException;
import by.gaponenko.tools.model.service.OrderService;
import by.gaponenko.tools.model.service.ServiceFactory;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Session listener(create).
 * Used to set the locale, set the Guest role, update order statuses.
 *
 * @author Haponenka Ihar
 * @version 1.0
 */
public class SessionListenerImpl implements HttpSessionListener {
    static Logger logger = LogManager.getLogger();

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        ServiceFactory factory = ServiceFactory.getINSTANCE();
        OrderService orderService = factory.getOrderService();
        try {
            orderService.updateOrdersStatuses();
        } catch (ServiceException e) {
            logger.log(Level.WARN, e.getMessage());
        }
        session.setAttribute(AttributeName.LANG, CommandConstant.EN_LANG);
        session.setAttribute(AttributeName.ROLE, User.Role.GUEST);
    }
}
