package by.gaponenko.tools.controller.command.impl;

import by.gaponenko.tools.controller.Router;
import by.gaponenko.tools.controller.command.*;
import by.gaponenko.tools.entity.User;
import by.gaponenko.tools.exception.ServiceException;
import by.gaponenko.tools.model.service.ServiceFactory;
import by.gaponenko.tools.model.service.UserService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

/**
 * The Update avatar command.
 * <p>
 * This command allows admin or client to update his photo.
 *
 * @author Haponenka Ihar
 * @version 1.0
 */
public class UpdateAvatarCommand implements Command {
    static Logger logger = LogManager.getLogger();

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
                    router.setPage(request.getContextPath() + CommandPath.USER_AVATAR_PASS);
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
