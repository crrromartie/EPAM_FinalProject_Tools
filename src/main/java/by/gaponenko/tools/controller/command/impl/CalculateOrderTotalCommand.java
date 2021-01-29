package by.gaponenko.tools.controller.command.impl;

import by.gaponenko.tools.controller.Router;
import by.gaponenko.tools.controller.command.AttributeName;
import by.gaponenko.tools.controller.command.Command;
import by.gaponenko.tools.entity.Tool;
import by.gaponenko.tools.model.service.OrderService;
import by.gaponenko.tools.model.service.ServiceFactory;
import by.gaponenko.tools.util.ParameterName;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;

public class CalculateOrderTotalCommand implements Command {
    private static final String TOOL_PASS_ONLY_COMMAND = "/ToolRental?command=tool_pass_only";

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
        router.setPage(request.getContextPath() + TOOL_PASS_ONLY_COMMAND);
        router.setRedirect();
        return router;
    }
}
