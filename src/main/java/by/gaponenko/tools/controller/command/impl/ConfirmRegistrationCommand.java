package by.gaponenko.tools.controller.command.impl;

import by.gaponenko.tools.controller.Router;
import by.gaponenko.tools.controller.command.Command;
import by.gaponenko.tools.controller.command.PagePath;
import by.gaponenko.tools.model.service.ServiceFactory;
import by.gaponenko.tools.util.ParameterName;
import by.gaponenko.tools.exception.ServiceException;
import by.gaponenko.tools.model.service.UserService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class ConfirmRegistrationCommand implements Command {
    static Logger logger = LogManager.getLogger();

    private static final String NOTIFICATION_PASS_SUCCESS = "/ToolRental?command=notification_pass&confirmed=true";
    private static final String NOTIFICATION_PASS_FAIL = "/ToolRental?command=notification_pass&confirmed=false";

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        ServiceFactory factory = ServiceFactory.getINSTANCE();
        UserService userService = factory.getUserService();
        String login = request.getParameter(ParameterName.USER_LOGIN);
        try {
            if (userService.activate(login)) {
                router.setPage(request.getContextPath() + NOTIFICATION_PASS_SUCCESS);
            } else {
                router.setPage(request.getContextPath() + NOTIFICATION_PASS_FAIL);
            }
            router.setRedirect();
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e.getMessage());
            router.setPage(PagePath.ERROR_500);
        }
        return router;
    }
}
