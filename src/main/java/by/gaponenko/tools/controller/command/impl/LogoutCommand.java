package by.gaponenko.tools.controller.command.impl;

import by.gaponenko.tools.controller.Router;
import by.gaponenko.tools.controller.command.Command;
import by.gaponenko.tools.controller.command.PagePath;

import javax.servlet.http.HttpServletRequest;

/**
 * The Logout command.
 * <p>
 * Processes a request to terminate a session with a user. The session is
 * no longer valid, the user is forwarding to the home page.
 *
 * @author Haponenka Ihar
 * @version 1.0
 */
public class LogoutCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router(PagePath.INDEX_PAGE);
        router.setRedirect();
        request.getSession().invalidate();
        return router;
    }
}
