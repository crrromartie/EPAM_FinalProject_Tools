package by.gaponenko.tools.controller.command.impl;

import by.gaponenko.tools.controller.Router;
import by.gaponenko.tools.controller.command.Command;
import by.gaponenko.tools.controller.command.PagePath;
import by.gaponenko.tools.exception.ServiceException;
import by.gaponenko.tools.model.service.ServiceFactory;
import by.gaponenko.tools.model.service.ToolService;
import by.gaponenko.tools.util.ParameterName;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;

public class UpdateToolPhotoCommand implements Command {
    static Logger logger = LogManager.getLogger();

    private static final String TOOL_PHOTO_PASS_COMMAND = "/ToolRental?command=tool_photo_pass&toolId=";

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        long toolId = Long.parseLong(request.getParameter(ParameterName.TOOL_ID));
        ToolService toolService = ServiceFactory.getINSTANCE().getToolService();
        try {
            InputStream toolPhoto = request.getPart(ParameterName.TOOL_PHOTO).getInputStream();
            if (toolService.updateToolPhoto(toolId, toolPhoto)) {
                router.setPage(request.getContextPath() + TOOL_PHOTO_PASS_COMMAND + toolId);
                router.setRedirect();
            } else {
                logger.log(Level.WARN, "Tool is not exist");
            }
        } catch (IOException | ServletException | ServiceException e) {
            logger.log(Level.ERROR, e.getMessage());
            router.setPage(PagePath.ERROR_500);
        }
        return router;
    }
}
