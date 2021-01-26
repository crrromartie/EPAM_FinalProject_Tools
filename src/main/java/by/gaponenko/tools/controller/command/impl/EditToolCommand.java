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
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class EditToolCommand implements Command {
    static Logger logger = LogManager.getLogger();

    private static final String NOTIFICATION_PASS_COMMAND = "/ToolRental?command=notification_pass&updateTool=true";

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        HttpSession session = request.getSession();
        Tool tool = (Tool) session.getAttribute(AttributeName.TOOL);
        Map<String, String> toolEditParameters = fillEditToolParameters(request);
        boolean isAvailable = Boolean.parseBoolean(request.getParameter(ParameterName.TOOL_AVAILABILITY));
        ServiceFactory factory = ServiceFactory.getINSTANCE();
        ToolService toolService = factory.getToolService();
        Optional<Tool> optionalTool;
        try {
            optionalTool = toolService.updateTool(toolEditParameters, isAvailable, tool.getToolId());
            if (optionalTool.isPresent()) {
                session.setAttribute(AttributeName.TOOL, optionalTool.get());
                router.setPage(request.getContextPath() + NOTIFICATION_PASS_COMMAND);
                router.setRedirect();
            } else {
                router.setPage(PagePath.TOOL_EDIT_PAGE);
                request.setAttribute(AttributeName.TOOL_EDIT_INCORRECT_DATA, true);
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
