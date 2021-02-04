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
import java.util.Optional;

/**
 * The Login command.
 * <p>
 * This command allows to pass the authorisation.
 *
 * @author Haponenka Ihar
 * @version 1.0
 */
public class LoginCommand implements Command {
    static Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        HttpSession session = request.getSession();
        String login = request.getParameter(ParameterName.USER_LOGIN);
        String password = request.getParameter(ParameterName.USER_PASSWORD);
        UserService userService = ServiceFactory.getINSTANCE().getUserService();
        try {
            Optional<User> optionalUser = userService.login(login, password);
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                session.setAttribute(AttributeName.USER, user);
                session.setAttribute(AttributeName.ROLE, user.getRole());
                router.setPage(request.getContextPath() + CommandPath.HOME_PASS);
                router.setRedirect();
            } else {
                request.setAttribute(AttributeName.LOGIN, login);
                request.setAttribute(AttributeName.PASSWORD, password);
                request.setAttribute(AttributeName.LOGIN_INCORRECT_DATA, true);
                router.setPage(PagePath.LOGIN_PAGE);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e.getMessage());
            router.setPage(PagePath.ERROR_500);
        }
        return router;
    }
}
