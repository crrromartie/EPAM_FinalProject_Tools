package by.gaponenko.tools.controller.command.impl;

import by.gaponenko.tools.controller.Router;
import by.gaponenko.tools.controller.command.AttributeName;
import by.gaponenko.tools.controller.command.Command;
import by.gaponenko.tools.controller.command.PagePath;
import by.gaponenko.tools.exception.ServiceException;
import by.gaponenko.tools.model.service.ServiceFactory;
import by.gaponenko.tools.model.service.UserService;
import by.gaponenko.tools.util.ParameterName;
import by.gaponenko.tools.util.mail.MailConstructor;
import by.gaponenko.tools.util.mail.MailSender;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class RegistrationCommand implements Command {
    static Logger logger = LogManager.getLogger();

    private static final String EMAIL_SUBJECT = "Email address confirmation";
    private static final String NOTIFICATION_PASS_COMMAND = "/ToolRental?command=notification_pass&registered=true";

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        Map<String, String> registrationParameters = fillRegistrationParameters(request);
        UserService userService = ServiceFactory.getINSTANCE().getUserService();
        try {
            if (userService.isLoginUnique(registrationParameters.get(ParameterName.USER_LOGIN))
                    && userService.isEmailUnique(registrationParameters.get(ParameterName.USER_EMAIL))
                    && userService.isPhoneUnique(registrationParameters.get(ParameterName.USER_PHONE))) {
                if (userService.registration(registrationParameters)) {
//                    String mailText = MailConstructor.newUserMail(request.getParameter(ParameterName.USER_LOGIN));
//                    MailSender.sendMessage(EMAIL_SUBJECT, mailText, request.getParameter(ParameterName.USER_EMAIL));
                    router.setPage(request.getContextPath() + NOTIFICATION_PASS_COMMAND);
                    router.setRedirect();
                } else {
                    request.setAttribute(AttributeName.REGISTRATION_PARAMETERS, registrationParameters);
                    request.setAttribute(AttributeName.REGISTRATION_INCORRECT_DATA, true);
                    router.setPage(PagePath.REGISTRATION_PAGE);
                }
            } else {
                if (!userService.isLoginUnique(registrationParameters.get(ParameterName.USER_LOGIN))) {
                    request.setAttribute(AttributeName.UNIQUE_LOGIN_ERROR, true);
                }
                if (!userService.isEmailUnique(registrationParameters.get(ParameterName.USER_EMAIL))) {
                    request.setAttribute(AttributeName.UNIQUE_EMAIL_ERROR, true);
                }
                if (!userService.isPhoneUnique(registrationParameters.get(ParameterName.USER_PHONE))) {
                    request.setAttribute(AttributeName.UNIQUE_PHONE_ERROR, true);
                }
                request.setAttribute(AttributeName.REGISTRATION_PARAMETERS, registrationParameters);
                router.setPage(PagePath.REGISTRATION_PAGE);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e.getMessage());
            router.setPage(PagePath.ERROR_500);
        }
        return router;
    }

    private Map<String, String> fillRegistrationParameters(HttpServletRequest request) {
        Map<String, String> registrationParameters = new HashMap<>();
        String login = request.getParameter(ParameterName.USER_LOGIN);
        String password = request.getParameter(ParameterName.USER_PASSWORD);
        String name = request.getParameter(ParameterName.USER_NAME);
        String surname = request.getParameter(ParameterName.USER_SURNAME);
        String email = request.getParameter(ParameterName.USER_EMAIL);
        String phone = request.getParameter(ParameterName.USER_PHONE);
        registrationParameters.put(ParameterName.USER_LOGIN, login);
        registrationParameters.put(ParameterName.USER_PASSWORD, password);
        registrationParameters.put(ParameterName.USER_NAME, name);
        registrationParameters.put(ParameterName.USER_SURNAME, surname);
        registrationParameters.put(ParameterName.USER_EMAIL, email);
        registrationParameters.put(ParameterName.USER_PHONE, phone);
        return registrationParameters;
    }
}
