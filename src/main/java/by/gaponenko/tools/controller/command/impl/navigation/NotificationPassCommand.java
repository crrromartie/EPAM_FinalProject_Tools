package by.gaponenko.tools.controller.command.impl.navigation;

import by.gaponenko.tools.controller.Router;
import by.gaponenko.tools.controller.command.AttributeName;
import by.gaponenko.tools.controller.command.Command;
import by.gaponenko.tools.controller.command.PagePath;
import by.gaponenko.tools.util.ParameterName;

import javax.servlet.http.HttpServletRequest;

public class NotificationPassCommand implements Command {
    private static final String TRUE = "true";

    @Override
    public Router execute(HttpServletRequest request) {
        if (request.getParameter(ParameterName.REGISTERED) != null) {
            request.setAttribute(AttributeName.REGISTRATION_SUCCESS, true);
        }
        if (request.getParameter(ParameterName.CONFIRMED) != null) {
            request.setAttribute(AttributeName.CONFIRMATION_SUCCESS,
                    request.getParameter(ParameterName.CONFIRMED).equals(TRUE));
        }
        if (request.getParameter(ParameterName.ORDER_CREATED) != null) {
            request.setAttribute(AttributeName.MAKE_ORDER_SUCCESS,
                    request.getParameter(ParameterName.ORDER_CREATED).equals(TRUE));
        }
        if (request.getParameter(ParameterName.ORDER_PAYED) != null) {
            request.setAttribute(AttributeName.PAYED_ORDER_SUCCESS, true);
        }
        if (request.getParameter(ParameterName.TOOL_ADD) != null) {
            request.setAttribute(AttributeName.ADD_TOOL_SUCCESS, true);
        }
        if (request.getParameter(ParameterName.TOOL_UPDATE) != null) {
            request.setAttribute(AttributeName.UPDATE_TOOL_SUCCESS, true);
        }
        return new Router(PagePath.NOTIFICATION_PAGE);
    }
}
