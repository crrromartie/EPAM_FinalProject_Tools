package by.gaponenko.tools.controller.command.impl.navigation;

import by.gaponenko.tools.controller.Router;
import by.gaponenko.tools.controller.command.Command;
import by.gaponenko.tools.controller.command.PagePath;

import javax.servlet.http.HttpServletRequest;
/**
 * The Login pass command.
 * <p>
 * Forwarding a user to the login page.
 *
 * @author Haponenka Ihar
 * @version 1.0
 */
public class LoginPassCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        return new Router(PagePath.LOGIN_PAGE);
    }
}
