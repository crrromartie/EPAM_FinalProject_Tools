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

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

public class UpdateToolPhoto implements Command {
    static Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router(PagePath.TOOL_PHOTO_PAGE);
        HttpSession session = request.getSession();
        long toolId = Long.parseLong(request.getParameter(ParameterName.TOOL_ID));
        ServiceFactory factory = ServiceFactory.getINSTANCE();
        ToolService toolService = factory.getToolService();
        InputStream toolPhoto;
        Optional<Tool> optionalTool;
        Tool tool;
        try {
            toolPhoto = request.getPart(ParameterName.TOOL_PHOTO).getInputStream();
            if (toolService.updateToolPhoto(toolId, toolPhoto)) {
                optionalTool = toolService.findById(toolId);
                if (optionalTool.isPresent()) {
                    tool = optionalTool.get();
                    session.setAttribute(AttributeName.TOOL, tool);
                } else {
                    logger.log(Level.WARN, "Tool has not found");
                }
            } else {
                logger.log(Level.WARN, "Tool photo has not update");
            }
        } catch (IOException | ServletException | ServiceException e) {
            logger.log(Level.ERROR, e.getMessage());
            router.setPage(PagePath.ERROR_500);
        }
        return router;
    }
}
