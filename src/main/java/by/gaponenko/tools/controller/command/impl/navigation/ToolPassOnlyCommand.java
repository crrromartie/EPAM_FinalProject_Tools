package by.gaponenko.tools.controller.command.impl.navigation;

import by.gaponenko.tools.controller.Router;
import by.gaponenko.tools.controller.command.Command;
import by.gaponenko.tools.controller.command.PagePath;

import javax.servlet.http.HttpServletRequest;

/**
 * The Tool pass only command.
 * <p>
 * Forwarding a user to the tool page after calculate total cost.
 * Using for defence against f5 after calculating total cost.
 *
 * @author Haponenka Ihar
 * @version 1.0
 */
public class ToolPassOnlyCommand implements Command {


    @Override
    public Router execute(HttpServletRequest request) {
        return new Router(PagePath.TOOL_PAGE);
    }
}
