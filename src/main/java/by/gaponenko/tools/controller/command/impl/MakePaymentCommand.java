package by.gaponenko.tools.controller.command.impl;

import by.gaponenko.tools.controller.Router;
import by.gaponenko.tools.controller.command.*;
import by.gaponenko.tools.entity.Order;
import by.gaponenko.tools.exception.ServiceException;
import by.gaponenko.tools.model.service.OrderService;
import by.gaponenko.tools.model.service.ServiceFactory;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * The Make payment command.
 * <p>
 * This command allows a client to pay for his order.
 *
 * @author Haponenka Ihar
 * @version 1.0
 */
public class MakePaymentCommand implements Command {
    static Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        HttpSession session = request.getSession();
        Order order = (Order) session.getAttribute(AttributeName.ORDER);
        Map<String, String> paymentCardParameters = fillPaymentCardParameters(request);
        OrderService orderService = ServiceFactory.getINSTANCE().getOrderService();
        try {
            if (orderService.paymentOrder(order, paymentCardParameters)) {
                router.setPage(request.getContextPath() + CommandPath.NOTIFICATION_ORDER_PAYED);
                session.removeAttribute(AttributeName.ORDER);
            } else {
                request.setAttribute(AttributeName.PAYMENT_INCORRECT_DATA, true);
                request.setAttribute(AttributeName.PAYMENT_PARAMETERS, paymentCardParameters);
                router.setPage(PagePath.PAYMENT_PAGE);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e.getMessage());
            router.setPage(PagePath.ERROR_500);
        }
        return router;
    }

    private Map<String, String> fillPaymentCardParameters(HttpServletRequest request) {
        Map<String, String> paymentCardParameters = new HashMap<>();
        String cardNumber = request.getParameter(ParameterName.CARD_NUMBER);
        String cardMonth = request.getParameter(ParameterName.CARD_MONTH);
        String cardYear = request.getParameter(ParameterName.CARD_YEAR);
        String cardCvv = request.getParameter(ParameterName.CARD_CVV);
        paymentCardParameters.put(ParameterName.CARD_NUMBER, cardNumber);
        paymentCardParameters.put(ParameterName.CARD_MONTH, cardMonth);
        paymentCardParameters.put(ParameterName.CARD_YEAR, cardYear);
        paymentCardParameters.put(ParameterName.CARD_CVV, cardCvv);
        return paymentCardParameters;
    }
}
