package by.gaponenko.tools.controller.command.impl;

import by.gaponenko.tools.controller.Router;
import by.gaponenko.tools.controller.command.AttributeName;
import by.gaponenko.tools.controller.command.Command;
import by.gaponenko.tools.controller.command.CommandPath;
import by.gaponenko.tools.controller.command.ParameterName;
import by.gaponenko.tools.entity.Tool;
import by.gaponenko.tools.model.service.OrderService;
import by.gaponenko.tools.model.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;

/**
 * The Calculate order total command.
 * <p>
 * This command allows a client calculate total order cost.
 *
 * @author Haponenka Ihar
 * @version 1.0
 */
public class CalculateOrderTotalCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        HttpSession session = request.getSession();
        String orderDate = request.getParameter(ParameterName.ORDER_DATE);
        String returnDate = request.getParameter(ParameterName.ORDER_RETURN_DATE);
        Tool tool = (Tool) session.getAttribute(AttributeName.TOOL);
        BigDecimal rentPrice = tool.getRentPrice();
        OrderService orderService = ServiceFactory.getINSTANCE().getOrderService();
        BigDecimal totalCost = orderService.calculateTotalCost(rentPrice, orderDate, returnDate);
        session.setAttribute(AttributeName.ORDER_DATE, orderDate);
        session.setAttribute(AttributeName.ORDER_RETURN_DATE, returnDate);
        session.setAttribute(AttributeName.ORDER_TOTAL_COST, totalCost);
        router.setPage(request.getContextPath() + CommandPath.TOOL_PASS_ONLY);
        router.setRedirect();
        return router;
    }
}
