package by.gaponenko.tools.controller.command.impl;

import by.gaponenko.tools.controller.Router;
import by.gaponenko.tools.controller.command.Command;
import by.gaponenko.tools.controller.command.CommandPath;
import by.gaponenko.tools.controller.command.PagePath;
import by.gaponenko.tools.controller.command.ParameterName;
import by.gaponenko.tools.exception.ServiceException;
import by.gaponenko.tools.model.service.ServiceFactory;
import by.gaponenko.tools.model.service.UserService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * The Confirm registration command.
 * <p>
 * This command allows to confirm registration.
 *
 * @author Haponenka Ihar
 * @version 1.0
 */
public class ConfirmRegistrationCommand implements Command {
    static Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        String login = request.getParameter(ParameterName.USER_LOGIN);
        UserService userService = ServiceFactory.getINSTANCE().getUserService();
        try {
            if (userService.activate(login)) {
                router.setPage(request.getContextPath() + CommandPath.NOTIFICATION_CONFIRMED_SUCCESS);
            } else {
                router.setPage(request.getContextPath() + CommandPath.NOTIFICATION_CONFIRMED_FAIL);
            }
            router.setRedirect();
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e.getMessage());
            router.setPage(PagePath.ERROR_500);
        }
        return router;
    }
}
