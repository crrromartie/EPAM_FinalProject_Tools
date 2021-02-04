package by.gaponenko.tools.controller.command.impl;

import by.gaponenko.tools.controller.Router;
import by.gaponenko.tools.controller.command.*;
import by.gaponenko.tools.exception.ServiceException;
import by.gaponenko.tools.model.service.ServiceFactory;
import by.gaponenko.tools.model.service.ToolService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * The Add tool command.
 * <p>
 * This command allows admin to add new tool on the site.
 *
 * @author Haponenka Ihar
 * @version 1.0
 */
public class AddToolCommand implements Command {
    static Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        Map<String, String> toolParameters = fillToolParameters(request);
        ToolService toolService = ServiceFactory.getINSTANCE().getToolService();
        try {
            if (toolService.isModelUnique(toolParameters.get(ParameterName.TOOL_MODEL))) {
                if (toolService.add(toolParameters)) {
                    router.setPage(request.getContextPath() + CommandPath.NOTIFICATION_TOOL_ADDED);
                    router.setRedirect();
                } else {
                    request.setAttribute(AttributeName.TOOL_INCORRECT_DATA, true);
                    request.setAttribute(AttributeName.TOOL_PARAMETERS, toolParameters);
                    router.setPage(PagePath.NEW_TOOL_PAGE);
                }
            } else {
                request.setAttribute(AttributeName.UNIQUE_MODEL_ERROR, true);
                request.setAttribute(AttributeName.TOOL_PARAMETERS, toolParameters);
                router.setPage(PagePath.NEW_TOOL_PAGE);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e.getMessage());
            router.setPage(PagePath.ERROR_500);
        }
        return router;
    }

    private Map<String, String> fillToolParameters(HttpServletRequest request) {
        Map<String, String> toolParameters = new HashMap<>();
        String toolType = request.getParameter(ParameterName.TOOL_TYPE);
        String model = request.getParameter(ParameterName.TOOL_MODEL);
        String descriptionRus = request.getParameter(ParameterName.TOOL_DESCRIPTION_RUS);
        String descriptionEng = request.getParameter(ParameterName.TOOL_DESCRIPTION_ENG);
        String rentPrice = request.getParameter(ParameterName.TOOL_RENT_PRICE);
        toolParameters.put(ParameterName.TOOL_TYPE, toolType);
        toolParameters.put(ParameterName.TOOL_MODEL, model);
        toolParameters.put(ParameterName.TOOL_DESCRIPTION_RUS, descriptionRus);
        toolParameters.put(ParameterName.TOOL_DESCRIPTION_ENG, descriptionEng);
        toolParameters.put(ParameterName.TOOL_RENT_PRICE, rentPrice);
        return toolParameters;
    }
}
