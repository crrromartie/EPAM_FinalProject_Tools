package by.gaponenko.tools.controller.command.impl.navigation;

import by.gaponenko.tools.controller.Router;
import by.gaponenko.tools.controller.command.Command;
import by.gaponenko.tools.controller.command.PagePath;

import javax.servlet.http.HttpServletRequest;

/**
 * The Tools pass only command.
 * <p>
 * Forwarding a user to the tools page,
 * without adding tools.
 * Using for defence pagination command against f5.
 *
 * @author Haponenka Ihar
 * @version 1.0
 */
public class ToolsPassOnlyCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        return new Router(PagePath.TOOLS_PAGE);
    }
}
