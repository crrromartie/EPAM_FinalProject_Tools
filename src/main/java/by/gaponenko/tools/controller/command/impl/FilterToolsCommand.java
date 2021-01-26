package by.gaponenko.tools.controller.command.impl;

import by.gaponenko.tools.controller.Router;
import by.gaponenko.tools.controller.command.AttributeName;
import by.gaponenko.tools.controller.command.Command;
import by.gaponenko.tools.controller.command.PagePath;
import by.gaponenko.tools.entity.Tool;
import by.gaponenko.tools.exception.ServiceException;
import by.gaponenko.tools.model.service.ServiceFactory;
import by.gaponenko.tools.model.service.ToolService;
import by.gaponenko.tools.util.ParameterName;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class FilterToolsCommand implements Command {
    static Logger logger = LogManager.getLogger();

    private static final int FIRST_PAGE = 1;

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router(PagePath.TOOLS_PAGE);
        HttpSession session = request.getSession();
        ServiceFactory factory = ServiceFactory.getINSTANCE();
        ToolService toolService = factory.getToolService();
        String toolType = request.getParameter(ParameterName.TOOL_TYPE);
        List<Tool> tools;
        try {
            tools = toolService.findByType(Tool.Type.valueOf(toolType));
            session.setAttribute(AttributeName.TOOLS, tools);
            session.setAttribute(AttributeName.TOOLS_PAGE_NUMBER, FIRST_PAGE);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e.getMessage());
            router.setPage(PagePath.ERROR_500);
        }
        return router;
    }
}
