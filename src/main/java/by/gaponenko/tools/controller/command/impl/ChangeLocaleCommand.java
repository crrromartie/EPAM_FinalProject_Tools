package by.gaponenko.tools.controller.command.impl;

import by.gaponenko.tools.controller.Router;
import by.gaponenko.tools.controller.command.Command;
import by.gaponenko.tools.controller.command.AttributeName;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * The Change locale command.
 * <p>
 * Processes a request to change the site locale. After changing the locale,
 * a forwarding to the current page occurs.
 *
 * @author Haponenka Ihar
 * @version 1.0
 */
public class ChangeLocaleCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String requestLang = request.getParameter(AttributeName.LANG);
        session.setAttribute(AttributeName.LANG, requestLang);
        String currentPage = (String) session.getAttribute(AttributeName.CURRENT_PAGE);
        return new Router(currentPage);
    }
}
