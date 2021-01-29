package by.gaponenko.tools.controller.command.impl;

import by.gaponenko.tools.controller.Router;
import by.gaponenko.tools.controller.command.AttributeName;
import by.gaponenko.tools.controller.command.Command;
import by.gaponenko.tools.controller.command.PagePath;
import by.gaponenko.tools.util.ParameterName;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ChangeToolsDisplayCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String toolsDisplay = request.getParameter(ParameterName.TOOLS_DISPLAY);
        if (toolsDisplay.equals(ParameterName.TOOLS_LIST)) {
            session.setAttribute(AttributeName.TOOLS_DISPLAY, ParameterName.TOOLS_LIST);
        }
        if (toolsDisplay.equals(ParameterName.TOOLS_CARDS)) {
            session.setAttribute(AttributeName.TOOLS_DISPLAY, ParameterName.TOOLS_CARDS);
        }
        return new Router(PagePath.TOOLS_PAGE);
    }
}
