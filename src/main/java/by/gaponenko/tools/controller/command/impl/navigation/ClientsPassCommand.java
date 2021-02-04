package by.gaponenko.tools.controller.command.impl.navigation;

import by.gaponenko.tools.controller.Router;
import by.gaponenko.tools.controller.command.AttributeName;
import by.gaponenko.tools.controller.command.Command;
import by.gaponenko.tools.controller.command.CommandConstant;
import by.gaponenko.tools.controller.command.PagePath;
import by.gaponenko.tools.entity.User;
import by.gaponenko.tools.exception.ServiceException;
import by.gaponenko.tools.model.service.ServiceFactory;
import by.gaponenko.tools.model.service.UserService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * The Clients pass command.
 * <p>
 * Forwarding admin to the clients page.
 *
 * @author Haponenka Ihar
 * @version 1.0
 */
public class ClientsPassCommand implements Command {
    static Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router(PagePath.CLIENTS_PAGE);
        HttpSession session = request.getSession();
        UserService userService = ServiceFactory.getINSTANCE().getUserService();
        try {
            List<User> users = userService.findClients();
            session.setAttribute(AttributeName.USERS, users);
            session.setAttribute(AttributeName.USERS_PAGE_NUMBER, CommandConstant.FIRST_PAGE);
            session.removeAttribute(AttributeName.USERS_FILTER_STATUS);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e.getMessage());
            router.setPage(PagePath.ERROR_500);
        }
        return router;
    }
}
