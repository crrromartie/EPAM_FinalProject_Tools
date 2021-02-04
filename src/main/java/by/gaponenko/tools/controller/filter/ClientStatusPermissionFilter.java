package by.gaponenko.tools.controller.filter;

import by.gaponenko.tools.controller.command.*;
import by.gaponenko.tools.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Set;

/**
 * The Client status permission filter.
 * <p>
 * Filter of the client's access level to the command sent to the
 * controller based on the current status. The filter intercepts
 * the request sent to {@code Controller}.
 * The user's role and command name are retrieved from the {@code HttpServletRequest}.
 * If the user's role is defined and the user is a client, then the client object is retrieved
 * from the {@code HttpSession}. The command that the request wants to call is determined.
 * The status is retrieved from the client, and based on it, a set of commands available
 * for processing by the client with this status is obtained. The command received from
 * the request is searched for in the received set, if such a command is found, control
 * is passed to the next filter, if not, it is redirected to the notification page.
 *
 * @author Haponenka Ihar
 * @version 1.0
 */
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
