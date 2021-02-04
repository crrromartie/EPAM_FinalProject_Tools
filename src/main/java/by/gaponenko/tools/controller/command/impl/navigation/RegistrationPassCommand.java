package by.gaponenko.tools.controller.command.impl.navigation;

import by.gaponenko.tools.controller.Router;
import by.gaponenko.tools.controller.command.Command;
import by.gaponenko.tools.controller.command.PagePath;

import javax.servlet.http.HttpServletRequest;

/**
 * The Registration pass command.
 * <p>
 * Forwarding a user to the registration page.
 *
 * @author Haponenka Ihar
 * @version 1.0
 */
public class RegistrationPassCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        return new Router(PagePath.REGISTRATION_PAGE);
    }
}
