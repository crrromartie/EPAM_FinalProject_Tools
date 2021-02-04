package by.gaponenko.tools.controller.command.impl.navigation;

import by.gaponenko.tools.controller.Router;
import by.gaponenko.tools.controller.command.Command;
import by.gaponenko.tools.controller.command.PagePath;

import javax.servlet.http.HttpServletRequest;

/**
 * The Tool add pass command.
 * <p>
 * Forwarding admin to the new tool page.
 *
 * @author Haponenka Ihar
 * @version 1.0
 */
public class ToolAddPassCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        return new Router(PagePath.NEW_TOOL_PAGE);
    }
}
