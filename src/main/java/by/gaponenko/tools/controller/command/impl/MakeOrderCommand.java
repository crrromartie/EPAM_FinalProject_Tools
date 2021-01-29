package by.gaponenko.tools.controller.command.impl;

import by.gaponenko.tools.controller.Router;
import by.gaponenko.tools.controller.command.AttributeName;
import by.gaponenko.tools.controller.command.Command;
import by.gaponenko.tools.controller.command.PagePath;
import by.gaponenko.tools.entity.Tool;
import by.gaponenko.tools.entity.User;
import by.gaponenko.tools.exception.ServiceException;
import by.gaponenko.tools.model.service.OrderService;
import by.gaponenko.tools.model.service.ServiceFactory;
import by.gaponenko.tools.util.ParameterName;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

public class MakeOrderCommand implements Command {
    static Logger logger = LogManager.getLogger();

    private static final String NOTIFICATION_PASS_SUCCESS = "/ToolRental?command=" +
            "notification_pass&orderCreated=true";
    private static final String NOTIFICATION_PASS_FAIL = "/ToolRental?command=" +
            "notification_pass&orderCreated=false";

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(AttributeName.USER);
        Tool tool = (Tool) session.getAttribute(AttributeName.TOOL);
        Map<String, String> orderParameters = fillOrderParameters(request);
        OrderService orderService = ServiceFactory.getINSTANCE().getOrderService();
        try {
            if (orderService.add(orderParameters, user, tool)) {
                router.setPage(request.getContextPath() + NOTIFICATION_PASS_SUCCESS);
            } else {
                router.setPage(request.getContextPath() + NOTIFICATION_PASS_FAIL);
            }
            router.setRedirect();
            session.removeAttribute(AttributeName.ORDER_DATE);
            session.removeAttribute(AttributeName.ORDER_RETURN_DATE);
            session.removeAttribute(AttributeName.ORDER_TOTAL_COST);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e.getMessage());
            router.setPage(PagePath.ERROR_500);
        }
        return router;
    }

    private Map<String, String> fillOrderParameters(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Map<String, String> orderParameters = new HashMap<>();
        String totalCost = request.getParameter(ParameterName.ORDER_TOTAL_COST);
        String orderDate = String.valueOf(session.getAttribute(AttributeName.ORDER_DATE));
        String returnDate = String.valueOf(session.getAttribute(AttributeName.ORDER_RETURN_DATE));
        orderParameters.put(ParameterName.ORDER_DATE, orderDate);
        orderParameters.put(ParameterName.ORDER_RETURN_DATE, returnDate);
        orderParameters.put(ParameterName.ORDER_TOTAL_COST, totalCost);
        return orderParameters;
    }
}
