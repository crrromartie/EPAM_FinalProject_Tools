package by.gaponenko.tools.controller.listener;

import by.gaponenko.tools.controller.command.AttributeName;
import by.gaponenko.tools.entity.User;
import by.gaponenko.tools.exception.ServiceException;
import by.gaponenko.tools.model.service.OrderService;
import by.gaponenko.tools.model.service.ServiceFactory;
import by.gaponenko.tools.util.ParameterName;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListenerImpl implements HttpSessionListener {
    static Logger logger = LogManager.getLogger();

    private static final String EN_LANG = "en";
    private static final int PAGE_ENTRIES = 8;

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
        session.setAttribute(AttributeName.LANG, EN_LANG);
        session.setAttribute(AttributeName.ROLE, User.Role.GUEST);
        session.setAttribute(AttributeName.PAGE_ENTRIES, PAGE_ENTRIES);
        session.setAttribute(AttributeName.TOOLS_DISPLAY, ParameterName.TOOLS_LIST);
    }
}
