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
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class EditProfileCommand implements Command {
    static Logger logger = LogManager.getLogger();

    private static final String NOTIFICATION_PASS_COMMAND = "/ToolRental?command=notification_pass&userInfoUpdated=true";

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(AttributeName.USER);
        Map<String, String> editUserParameters = fillEditUserParameters(request);
        UserService userService = ServiceFactory.getINSTANCE().getUserService();
        try {
            if ((userService.isEmailUnique(editUserParameters.get(ParameterName.USER_EMAIL))
                    || user.getEmail().equals(editUserParameters.get(ParameterName.USER_EMAIL)))
                    && (userService.isPhoneUnique(editUserParameters.get(ParameterName.USER_PHONE))
                    || user.getPhone().equals(editUserParameters.get(ParameterName.USER_PHONE)))) {
                Optional<User> optionalUser = userService.updateUserInfo(editUserParameters, user.getUserId());
                if (optionalUser.isPresent()) {
                    session.setAttribute(AttributeName.USER, optionalUser.get());
                    router.setPage(request.getContextPath() + NOTIFICATION_PASS_COMMAND);
                    router.setRedirect();
                } else {
                    request.setAttribute(AttributeName.USER_EDIT_INCORRECT_DATA, true);
                    router.setPage(PagePath.PROFILE_PAGE);
                }
            } else {
                if (!userService.isEmailUnique(editUserParameters.get(ParameterName.USER_EMAIL))
                        || !user.getEmail().equals(editUserParameters.get(ParameterName.USER_EMAIL))) {
                    request.setAttribute(AttributeName.UNIQUE_EMAIL_ERROR, true);
                }
                if (!userService.isPhoneUnique(editUserParameters.get(ParameterName.USER_PHONE))
                        || !user.getPhone().equals(editUserParameters.get(ParameterName.USER_PHONE))) {
                    request.setAttribute(AttributeName.UNIQUE_PHONE_ERROR, true);
                }
                router.setPage(PagePath.PROFILE_PAGE);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e.getMessage());
            router.setPage(PagePath.ERROR_500);
        }
        return router;
    }

    private Map<String, String> fillEditUserParameters(HttpServletRequest request) {
        Map<String, String> editUserParameters = new HashMap<>();
        String name = request.getParameter(ParameterName.USER_NAME);
        String surname = request.getParameter(ParameterName.USER_SURNAME);
        String email = request.getParameter(ParameterName.USER_EMAIL);
        String phone = request.getParameter(ParameterName.USER_PHONE);
        editUserParameters.put(ParameterName.USER_NAME, name);
        editUserParameters.put(ParameterName.USER_SURNAME, surname);
        editUserParameters.put(ParameterName.USER_EMAIL, email);
        editUserParameters.put(ParameterName.USER_PHONE, phone);
        return editUserParameters;
    }
}
