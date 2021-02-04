package by.gaponenko.tools.controller.command.impl.navigation;

import by.gaponenko.tools.controller.Router;
import by.gaponenko.tools.controller.command.Command;
import by.gaponenko.tools.controller.command.PagePath;

import javax.servlet.http.HttpServletRequest;

/**
 * The Clients pass only command.
 * <p>
 * Forwarding admin to the clients page
 * without adding clients.
 * Using for defence pagination command against f5.
 *
 * @author Haponenka Ihar
 * @version 1.0
 */
public class ClientsPassOnlyCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        return new Router(PagePath.CLIENTS_PAGE);
    }
}
