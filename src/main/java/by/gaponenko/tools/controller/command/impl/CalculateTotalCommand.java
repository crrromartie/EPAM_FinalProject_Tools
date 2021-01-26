package by.gaponenko.tools.controller.command.impl;

import by.gaponenko.tools.controller.Router;
import by.gaponenko.tools.controller.command.AttributeName;
import by.gaponenko.tools.controller.command.Command;
import by.gaponenko.tools.controller.command.PagePath;
import by.gaponenko.tools.entity.Tool;
import by.gaponenko.tools.exception.ServiceException;
import by.gaponenko.tools.model.service.OrderService;
import by.gaponenko.tools.model.service.ServiceFactory;
import by.gaponenko.tools.util.ParameterName;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;

public class CalculateTotalCommand implements Command {
    static Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router(PagePath.TOOL_PAGE);
        HttpSession session = request.getSession();
        String orderDate = request.getParameter(ParameterName.ORDER_DATE);
        String returnDate = request.getParameter(ParameterName.ORDER_RETURN_DATE);
        Tool tool = (Tool) session.getAttribute(AttributeName.TOOL);
        BigDecimal rentPrice = tool.getRentPrice();
        ServiceFactory factory = ServiceFactory.getINSTANCE();
        OrderService orderService = factory.getOrderService();
        BigDecimal totalCost;
        try {
            totalCost = orderService.calculateTotalCost(rentPrice, orderDate, returnDate);
            session.setAttribute(AttributeName.ORDER_DATE, orderDate);
            session.setAttribute(AttributeName.ORDER_RETURN_DATE, returnDate);
            request.setAttribute(AttributeName.ORDER_TOTAL_COST, totalCost);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e.getMessage());
            router.setPage(PagePath.ERROR_500);
        }
        return router;
    }
}
