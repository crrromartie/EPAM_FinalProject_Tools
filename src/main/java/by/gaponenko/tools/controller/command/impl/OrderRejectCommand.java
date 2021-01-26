package by.gaponenko.tools.controller.command.impl;

import by.gaponenko.tools.controller.Router;
import by.gaponenko.tools.controller.command.AttributeName;
import by.gaponenko.tools.controller.command.Command;
import by.gaponenko.tools.controller.command.PagePath;
import by.gaponenko.tools.entity.Order;
import by.gaponenko.tools.entity.Tool;
import by.gaponenko.tools.exception.ServiceException;
import by.gaponenko.tools.model.service.OrderService;
import by.gaponenko.tools.model.service.ServiceFactory;
import by.gaponenko.tools.model.service.ToolService;
import by.gaponenko.tools.util.ParameterName;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

public class OrderRejectCommand implements Command {
    static Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router(PagePath.ORDERS_PAGE);
        HttpSession session = request.getSession();
        ServiceFactory factory = ServiceFactory.getINSTANCE();
        OrderService orderService = factory.getOrderService();
        ToolService toolService = factory.getToolService();
        long orderId = Long.parseLong(request.getParameter(ParameterName.ORDER_ID));
        Optional<Order> optionalOrder;
        Tool tool;
        List<Order> orders;
        try {
            optionalOrder = orderService.findById(orderId);
            if (optionalOrder.isPresent()) {
                tool = optionalOrder.get().getTool();
                long toolId = tool.getToolId();
                if (toolService.activateTool(toolId)
                        && orderService.updateStatus(orderId, Order.Status.REJECTED)) {
                    orders = orderService.findAll();
                    session.setAttribute(AttributeName.ORDERS, orders);
                } else {
                    logger.log(Level.WARN, "Order was not rejected");
                }
            } else {
                logger.log(Level.WARN, "Tool was not found");
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e.getMessage());
            router.setPage(PagePath.ERROR_500);
        }
        return router;
    }
}
