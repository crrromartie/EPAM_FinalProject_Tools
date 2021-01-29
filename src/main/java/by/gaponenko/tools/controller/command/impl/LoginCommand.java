package by.gaponenko.tools.controller.command.impl;

import by.gaponenko.tools.controller.Router;
import by.gaponenko.tools.controller.command.AttributeName;
import by.gaponenko.tools.controller.command.Command;
import by.gaponenko.tools.controller.command.PagePath;
import by.gaponenko.tools.entity.User;
import by.gaponenko.tools.exception.ServiceException;
import by.gaponenko.tools.model.service.ServiceFactory;
import by.gaponenko.tools.model.service.UserService;
import by.gaponenko.tools.util.ParameterName;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class LoginCommand implements Command {
    static Logger logger = LogManager.getLogger();

    private static final String HOME_PASS_COMMAND = "/ToolRental?command=home_pass";

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
                router.setPage(request.getContextPath() + HOME_PASS_COMMAND);
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
