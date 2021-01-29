package by.gaponenko.tools.controller.command.impl.navigation;

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
import java.util.Optional;

public class ToolPassCommand implements Command {
    static Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        HttpSession session = request.getSession();
        long toolId = Long.parseLong(request.getParameter(ParameterName.TOOL_ID));
        ToolService toolService = ServiceFactory.getINSTANCE().getToolService();
        try {
            Optional<Tool> optionalTool = toolService.findById(toolId);
            if (optionalTool.isPresent()) {
                session.setAttribute(AttributeName.TOOL, optionalTool.get());
                session.removeAttribute(AttributeName.ORDER_DATE);
                session.removeAttribute(AttributeName.ORDER_RETURN_DATE);
                session.removeAttribute(AttributeName.ORDER_TOTAL_COST);
                router.setPage(PagePath.TOOL_PAGE);
            } else {
                logger.log(Level.ERROR, "Tool is not exist!");
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e.getMessage());
            router.setPage(PagePath.ERROR_500);
        }
        return router;
    }
}
