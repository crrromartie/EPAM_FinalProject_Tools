package by.gaponenko.tools.controller.filter;

import by.gaponenko.tools.controller.command.*;
import by.gaponenko.tools.controller.command.impl.NoSuchCommand;
import by.gaponenko.tools.entity.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Set;

/**
 * The Role permission filter.
 * <p>
 * Filter of the user's access level to the command sent to the
 * controller based on the current role. The filter intercepts
 * the request sent to {@code Controller}.
 * The user's role and command name are retrieved from the {@code HttpServletRequest}.
 * The role is retrieved from the {@code HttpSession}, and based on it, a set of commands available
 * for processing by the user with this role is obtained. The command, received from
 * the request is searched for in the received set, if such a command is found, control
 * is passed to the next filter, if not, it is redirected to the notification page.
 *
 * @author Haponenka Ihar
 * @version 1.0
 */
public class RolePermissionFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession();
        User.Role role = (User.Role) session.getAttribute(AttributeName.ROLE);
        String commandName = httpRequest.getParameter(ParameterName.COMMAND);
        Command command = CommandProvider.takeCommand(commandName);
        if (command.getClass() != NoSuchCommand.class) {
            Set<CommandType> commandTypeSet = switch (role) {
                case GUEST -> AccessByRole.GUEST.getAllowedCommands();
                case CLIENT -> AccessByRole.CLIENT.getAllowedCommands();
                case ADMIN -> AccessByRole.ADMIN.getAllowedCommands();
            };
            if (!commandTypeSet.contains(CommandType.valueOf(commandName.toUpperCase()))) {
                httpRequest.setAttribute(AttributeName.ACCESS_DENIED, true);
                httpRequest.getRequestDispatcher(PagePath.NOTIFICATION_PAGE).forward(httpRequest, httpResponse);
                return;
            }
        }
        chain.doFilter(request, response);
    }
}
