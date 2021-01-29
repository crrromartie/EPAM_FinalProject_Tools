package by.gaponenko.tools.controller.filter;

import by.gaponenko.tools.controller.command.AccessByClientStatus;
import by.gaponenko.tools.controller.command.AttributeName;
import by.gaponenko.tools.controller.command.CommandType;
import by.gaponenko.tools.controller.command.PagePath;
import by.gaponenko.tools.entity.User;
import by.gaponenko.tools.util.ParameterName;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Set;

@WebFilter(urlPatterns = "/ToolRental")
public class ClientStatusPermissionFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession();
        String commandName = httpRequest.getParameter(ParameterName.COMMAND);
        User.Role role = (User.Role) session.getAttribute(AttributeName.ROLE);
        User user = (User) session.getAttribute(AttributeName.USER);
        if (user != null && role == User.Role.CLIENT) {
            User.Status status = user.getStatus();
            Set<CommandType> commandTypeSet = switch (status) {
                case ACTIVE -> AccessByClientStatus.ACTIVE.getAllowedCommands();
                case UNCONFIRMED -> AccessByClientStatus.UNCONFIRMED.getAllowedCommands();
                case BLOCKED -> AccessByClientStatus.BLOCKED.getAllowedCommands();
            };
            if (!commandTypeSet.contains(CommandType.valueOf(commandName.toUpperCase()))) {
                if (status == User.Status.UNCONFIRMED) {
                    httpRequest.setAttribute(AttributeName.CONFIRM_EMAIL, true);
                }
                if (status == User.Status.BLOCKED) {
                    httpRequest.setAttribute(AttributeName.ACCOUNT_BLOCKED, true);
                }
                request.getRequestDispatcher(PagePath.NOTIFICATION_PAGE).forward(httpRequest, httpResponse);
                return;
            }
        }
        chain.doFilter(request, response);
    }
}
