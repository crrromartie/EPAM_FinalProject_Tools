package by.gaponenko.tools.controller.command.impl;

import by.gaponenko.tools.controller.Router;
import by.gaponenko.tools.controller.command.*;
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
 * The Filter clients command.
 * <p>
 * This command allows admin to filter clients by status.
 *
 * @author Haponenka Ihar
 * @version 1.0
 */
public class FilterClientsCommand implements Command {
    static Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router(PagePath.CLIENTS_PAGE);
        HttpSession session = request.getSession();
        String clientStatus = request.getParameter(ParameterName.CLIENT_STATUS);
        UserService userService = ServiceFactory.getINSTANCE().getUserService();
        try {
            List<User> users;
            if (clientStatus.equals(CommandConstant.ALL)) {
                users = userService.findClients();
            } else {
                users = userService.findClientsByStatus(User.Status.valueOf(clientStatus));
            }
            session.setAttribute(AttributeName.USERS, users);
            session.setAttribute(AttributeName.USERS_PAGE_NUMBER, CommandConstant.FIRST_PAGE);
            session.setAttribute(AttributeName.USERS_FILTER_STATUS, clientStatus);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e.getMessage());
            router.setPage(PagePath.ERROR_500);
        }
        return router;
    }
}
