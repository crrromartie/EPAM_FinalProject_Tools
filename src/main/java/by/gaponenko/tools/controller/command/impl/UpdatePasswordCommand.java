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

/**
 * The Update password command.
 * <p>
 * This command allows admin or client to update his password.
 *
 * @author Haponenka Ihar
 * @version 1.0
 */
public class UpdatePasswordCommand implements Command {
    static Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        HttpSession session = request.getSession();
        String password = request.getParameter(ParameterName.USER_PASSWORD);
        User user = (User) session.getAttribute(AttributeName.USER);
        UserService userService = ServiceFactory.getINSTANCE().getUserService();
        try {
            if (userService.updatePassword(user.getUserId(), password)) {
                router.setPage(request.getContextPath() + CommandPath.NOTIFICATION_PASSWORD_UPDATED);
                router.setRedirect();
            } else {
                router.setPage(PagePath.PASSWORD_PAGE);
                request.setAttribute(AttributeName.PASSWORD_INCORRECT_DATA, true);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e.getMessage());
            router.setPage(PagePath.ERROR_500);
        }
        return router;
    }
}
