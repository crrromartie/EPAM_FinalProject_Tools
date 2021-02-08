package by.gaponenko.tools.controller.filter;

import by.gaponenko.tools.controller.command.PagePath;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * The Timeout session filter.
 *
 * @author Haponenka Ihar
 * @version 1.0
 */
@WebFilter(urlPatterns = {("/ToolRental")})
public class TimeOutSessionFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);
        if (session == null) {
            httpResponse.sendRedirect(httpRequest.getContextPath() + PagePath.INDEX_PAGE);
        } else {
            chain.doFilter(request, response);
        }
    }
}
