package by.gaponenko.tools.controller.command.impl.navigation;

import by.gaponenko.tools.controller.Router;
import by.gaponenko.tools.controller.command.Command;
import by.gaponenko.tools.controller.command.PagePath;

import javax.servlet.http.HttpServletRequest;

public class ToolsPassOnlyCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        return new Router(PagePath.TOOLS_PAGE);
    }
}
