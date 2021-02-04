package by.gaponenko.tools.controller.command.impl;

import by.gaponenko.tools.controller.Router;
import by.gaponenko.tools.controller.command.AttributeName;
import by.gaponenko.tools.controller.command.Command;
import by.gaponenko.tools.controller.command.CommandPath;
import by.gaponenko.tools.controller.command.ParameterName;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * The Pagination command.
 * <p>
 * Use for pagination.
 *
 * @author Haponenko Ihar
 * @version 1.0
 */
public class PaginationCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        HttpSession session = request.getSession();
        String paginationSubject = request.getParameter(ParameterName.PAGINATION_SUBJECT);
        String paginationDirection = request.getParameter(ParameterName.PAGINATION_DIRECTION);

        if (paginationSubject.equals(AttributeName.PAGINATION_USERS)) {
            int usersPageNumber = (int) session.getAttribute(AttributeName.USERS_PAGE_NUMBER);
            if (paginationDirection.equals(ParameterName.NEXT_PAGE)) {
                session.setAttribute(AttributeName.USERS_PAGE_NUMBER, ++usersPageNumber);
            }
            if (paginationDirection.equals(ParameterName.PREVIOUS_PAGE)) {
                session.setAttribute(AttributeName.USERS_PAGE_NUMBER, --usersPageNumber);
            }
            router.setPage(request.getContextPath() + CommandPath.CLIENTS_PASS_ONLY);
        }

        if (paginationSubject.equals(AttributeName.PAGINATION_TOOLS)) {
            int toolsPageNumber = (int) session.getAttribute(AttributeName.TOOLS_PAGE_NUMBER);
            if (paginationDirection.equals(ParameterName.NEXT_PAGE)) {
                session.setAttribute(AttributeName.TOOLS_PAGE_NUMBER, ++toolsPageNumber);
            }
            if (paginationDirection.equals(ParameterName.PREVIOUS_PAGE)) {
                session.setAttribute(AttributeName.TOOLS_PAGE_NUMBER, --toolsPageNumber);
            }
            router.setPage(request.getContextPath() + CommandPath.TOOLS_PASS_ONLY);
        }

        if (paginationSubject.equals(AttributeName.PAGINATION_ORDERS)) {
            int ordersPageNumber = (int) session.getAttribute(AttributeName.ORDERS_PAGE_NUMBER);
            if (paginationDirection.equals(ParameterName.NEXT_PAGE)) {
                session.setAttribute(AttributeName.ORDERS_PAGE_NUMBER, ++ordersPageNumber);
            }
            if (paginationDirection.equals(ParameterName.PREVIOUS_PAGE)) {
                session.setAttribute(AttributeName.ORDERS_PAGE_NUMBER, --ordersPageNumber);
            }
            router.setPage(request.getContextPath() + CommandPath.ORDERS_PASS_ONLY);
        }
        router.setRedirect();
        return router;
    }
}
