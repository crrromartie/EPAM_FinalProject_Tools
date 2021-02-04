package by.gaponenko.tools.controller.command.impl;

import by.gaponenko.tools.controller.Router;
import by.gaponenko.tools.controller.command.Command;
import by.gaponenko.tools.controller.command.PagePath;

import javax.servlet.http.HttpServletRequest;

/**
 * The No such command.
 * <p>
 * It is called if it was not possible to determine the command
 * received in the request. User is forwarding to the error404 page.
 *
 * @author Haponenka Ihar
 * @version 1.0
 */
public class NoSuchCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        return new Router(PagePath.ERROR_404);
    }
}
