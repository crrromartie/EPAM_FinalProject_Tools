package by.gaponenko.tools.controller.command.impl;

import by.gaponenko.tools.controller.Router;
import by.gaponenko.tools.controller.command.AttributeName;
import by.gaponenko.tools.controller.command.Command;
import by.gaponenko.tools.controller.command.PagePath;
import by.gaponenko.tools.entity.User;
import by.gaponenko.tools.exception.ServiceException;
import by.gaponenko.tools.model.service.ServiceFactory;
import by.gaponenko.tools.model.service.UserService;
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

public class UpdateAvatarCommand implements Command {
    static Logger logger = LogManager.getLogger();

    private static final String USER_AVATAR_PASS_COMMAND = "/ToolRental?command=avatar_pass";

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(AttributeName.USER);
        UserService userService = ServiceFactory.getINSTANCE().getUserService();
        try {
            InputStream toolPhoto = request.getPart(ParameterName.AVATAR).getInputStream();
            if (userService.updateAvatar(user.getUserId(), toolPhoto)) {
                Optional<User> optionalUser = userService.findById(user.getUserId());
                if (optionalUser.isPresent()) {
                    session.setAttribute(AttributeName.USER, optionalUser.get());
                    router.setPage(request.getContextPath() + USER_AVATAR_PASS_COMMAND);
                    router.setRedirect();
                } else {
                    logger.log(Level.WARN, "User is not exist");
                }
            } else {
                logger.log(Level.WARN, "Avatar is not update");
            }
        } catch (IOException | ServletException | ServiceException e) {
            logger.log(Level.ERROR, e.getMessage());
            router.setPage(PagePath.ERROR_500);
        }
        return router;
    }
}
