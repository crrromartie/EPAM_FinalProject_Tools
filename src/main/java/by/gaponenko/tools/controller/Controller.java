package by.gaponenko.tools.controller;

import by.gaponenko.tools.controller.command.AttributeName;
import by.gaponenko.tools.controller.command.Command;
import by.gaponenko.tools.controller.command.CommandProvider;
import by.gaponenko.tools.controller.command.ParameterName;
import by.gaponenko.tools.model.pool.ConnectionPool;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * The Controller.
 * <p>
 * Controller for processing requests coming from the client side.
 *
 * @author Haponenka Ihar
 * @version 1.0
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 10, maxRequestSize = 1024 * 1024 * 5 * 5)
public class Controller extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Process request.
     * Receives a request from the client, retrieves the name of the requested command,
     * searches for this command from the list of existing ones, and redirects the
     * request to the command for processing. Based on the processing results, it
     * generates a response and redirects or forwards to the required page.
     *
     * @param request  the request
     * @param response the response
     * @throws ServletException the servlet exception
     * @throws IOException      the io exception
     */
    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Command command = CommandProvider.takeCommand(request.getParameter(ParameterName.COMMAND));
        Router router = command.execute(request);
        String currentPage = router.getPage();
        HttpSession session = request.getSession();
        session.setAttribute(AttributeName.CURRENT_PAGE, currentPage);
        if (router.getType() == Router.Type.FORWARD) {
            request.getRequestDispatcher(currentPage).forward(request, response);
        } else {
            response.sendRedirect(currentPage);
        }
    }

    @Override
    public void destroy() {
        super.destroy();
        ConnectionPool.INSTANCE.destroyPool();
    }
}
