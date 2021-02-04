package by.gaponenko.tools.controller.command.impl;

import by.gaponenko.tools.controller.Router;
import by.gaponenko.tools.controller.command.*;
import by.gaponenko.tools.entity.Tool;
import by.gaponenko.tools.exception.ServiceException;
import by.gaponenko.tools.model.service.ServiceFactory;
import by.gaponenko.tools.model.service.ToolService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * The Filter tools command.
 * <p>
 * This command allows a user to filter tools by type.
 *
 * @author Haponenka Ihar
 * @version 1.0
 */
public class FilterToolsCommand implements Command {
    static Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router(PagePath.TOOLS_PAGE);
        HttpSession session = request.getSession();
        String toolType = request.getParameter(ParameterName.TOOL_TYPE);
        ToolService toolService = ServiceFactory.getINSTANCE().getToolService();
        try {
            List<Tool> tools;
            if (toolType.equals(CommandConstant.ALL)) {
                tools = toolService.findAll();
            } else {
                tools = toolService.findByType(Tool.Type.valueOf(toolType));
            }
            session.setAttribute(AttributeName.TOOLS, tools);
            session.setAttribute(AttributeName.TOOLS_PAGE_NUMBER, CommandConstant.FIRST_PAGE);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e.getMessage());
            router.setPage(PagePath.ERROR_500);
        }
        return router;
    }
}
