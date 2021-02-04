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
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * The Edit tool command.
 * <p>
 * This command allows admin to edit any tool.
 *
 * @author Haponenka Ihar
 * @version 1.0
 */
public class EditToolCommand implements Command {
    static Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        HttpSession session = request.getSession();
        Tool tool = (Tool) session.getAttribute(AttributeName.TOOL);
        Map<String, String> toolEditParameters = fillEditToolParameters(request);
        boolean isAvailable = Boolean.parseBoolean(request.getParameter(ParameterName.TOOL_AVAILABILITY));
        ToolService toolService = ServiceFactory.getINSTANCE().getToolService();
        try {
            Optional<Tool> optionalTool = toolService.updateTool(toolEditParameters, isAvailable, tool.getToolId());
            if (optionalTool.isPresent()) {
                session.setAttribute(AttributeName.TOOL, optionalTool.get());
                router.setPage(request.getContextPath() + CommandPath.NOTIFICATION_TOOL_UPDATED);
                router.setRedirect();
            } else {
                request.setAttribute(AttributeName.TOOL_EDIT_INCORRECT_DATA, true);
                router.setPage(PagePath.TOOL_EDIT_PAGE);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e.getMessage());
            router.setPage(PagePath.ERROR_500);
        }
        return router;
    }

    private Map<String, String> fillEditToolParameters(HttpServletRequest request) {
        Map<String, String> toolEditParameters = new HashMap<>();
        String descriptionRus = request.getParameter(ParameterName.TOOL_DESCRIPTION_RUS);
        String descriptionEng = request.getParameter(ParameterName.TOOL_DESCRIPTION_ENG);
        String rentPrice = request.getParameter(ParameterName.TOOL_RENT_PRICE);
        toolEditParameters.put(ParameterName.TOOL_DESCRIPTION_RUS, descriptionRus);
        toolEditParameters.put(ParameterName.TOOL_DESCRIPTION_ENG, descriptionEng);
        toolEditParameters.put(ParameterName.TOOL_RENT_PRICE, rentPrice);
        return toolEditParameters;
    }
}
