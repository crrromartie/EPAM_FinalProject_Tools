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
 * The Unblock client command.
 * <p>
 * This command allows admin to unblock client.
 *
 * @author Haponenka Ihar
 * @version 1.0
 */
public class UnblockClientCommand implements Command {
    static Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router(PagePath.CLIENTS_PAGE);
        HttpSession session = request.getSession();
        String login = request.getParameter(ParameterName.USER_LOGIN);
        String clientFilterStatus = (String) session.getAttribute(AttributeName.USERS_FILTER_STATUS);
        UserService userService = ServiceFactory.getINSTANCE().getUserService();
        try {
            if (userService.updateStatus(login, CommandConstant.ACTIVE)) {
                List<User> users;
                if (clientFilterStatus != null
                        && !clientFilterStatus.equalsIgnoreCase(CommandConstant.ALL)) {
                    users = userService.findClientsByStatus(clientFilterStatus);
                } else {
                    users = userService.findClients();
                }
                session.setAttribute(AttributeName.USERS, users);
            } else {
                logger.log(Level.WARN, "Client is not unblocked");
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e.getMessage());
            router.setPage(PagePath.ERROR_500);
        }
        return router;
    }
}
