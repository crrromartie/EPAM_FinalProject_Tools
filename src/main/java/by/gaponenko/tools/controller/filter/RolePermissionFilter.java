package by.gaponenko.tools.controller.filter;

import by.gaponenko.tools.controller.command.*;
import by.gaponenko.tools.controller.command.impl.NoSuchCommand;
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
