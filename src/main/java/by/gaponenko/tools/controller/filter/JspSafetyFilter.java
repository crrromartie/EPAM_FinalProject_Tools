package by.gaponenko.tools.controller.filter;

import by.gaponenko.tools.controller.command.PagePath;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The Jsp safety filter.
 * <p>
 * The filter's task is to redirect direct calls to jsp pages located in the package
 * 'jsp' of webapp to the index page.
 *
 * @author Haponenka Ihar
 * @version 1.0
 */
@WebFilter(urlPatterns = "/jsp/*")
public class JspSafetyFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.sendRedirect(httpRequest.getContextPath() + PagePath.INDEX_PAGE);
    }
}