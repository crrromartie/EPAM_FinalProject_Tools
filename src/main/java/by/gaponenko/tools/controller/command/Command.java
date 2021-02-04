package by.gaponenko.tools.controller.command;

import by.gaponenko.tools.controller.Router;

import javax.servlet.http.HttpServletRequest;

/**
 * The interface Command.
 * <p>
 * This interface should be implemented by those classes that are designed
 * to process requests from the client to the server.
 * Using the implementation of the methods of this interface, dependency
 * inversion is implemented, which allows the controller that redirects
 * the request to dynamically determine the command to be executed.
 *
 * @author Haponenka Ihar
 * @version 1.0
 */
public interface Command {
    /**
     * The method is designed to process a {@code HttpServletRequest} from a controller.
     * The result of processing is an object of the {@code Router} class.
     * During processing, modification of the request object is possible.
     *
     * @param request the request
     * @return the router
     */
    Router execute(HttpServletRequest request);
}
