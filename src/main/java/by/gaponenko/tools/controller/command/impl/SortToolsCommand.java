package by.gaponenko.tools.controller.command.impl;

import by.gaponenko.tools.controller.Router;
import by.gaponenko.tools.controller.command.*;
import by.gaponenko.tools.entity.Tool;
import by.gaponenko.tools.exception.ServiceException;
import by.gaponenko.tools.model.service.ServiceFactory;
import by.gaponenko.tools.model.service.ToolService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The Sort tools command.
 * <p>
 * This command allows to sort tools by price.
 *
 * @author Haponenka Ihar
 * @version 1.0
 */
public class SortToolsCommand implements Command {
    static Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router(PagePath.TOOLS_PAGE);
        HttpSession session = request.getSession();
        List<Tool> tools = (ArrayList<Tool>) session.getAttribute(AttributeName.TOOLS);
        String sortDirection = request.getParameter(ParameterName.TOOLS_SORT_CRITERIA);
        ToolService toolService = ServiceFactory.getINSTANCE().getToolService();
        List<Tool> sortedTools = Collections.EMPTY_LIST;
        try {
            if (ParameterName.PRICE_UP.equals(sortDirection)) {
                sortedTools = toolService.sortByCriteria(tools, ParameterName.PRICE_UP);
            }
            if (ParameterName.PRICE_DOWN.equals(sortDirection)) {
                sortedTools = toolService.sortByCriteria(tools, ParameterName.PRICE_DOWN);
            }
            session.setAttribute(AttributeName.TOOLS, sortedTools);
            session.setAttribute(AttributeName.TOOLS_PAGE_NUMBER, CommandConstant.FIRST_PAGE);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e.getMessage());
            router.setPage(PagePath.ERROR_500);
        }
        return router;
    }
}
