package by.gaponenko.tools.controller.command.impl.navigation;

import by.gaponenko.tools.controller.Router;
import by.gaponenko.tools.controller.command.AttributeName;
import by.gaponenko.tools.controller.command.Command;
import by.gaponenko.tools.controller.command.PagePath;
import by.gaponenko.tools.controller.command.ParameterName;
import by.gaponenko.tools.entity.Order;
import by.gaponenko.tools.exception.ServiceException;
import by.gaponenko.tools.model.service.OrderService;
import by.gaponenko.tools.model.service.ServiceFactory;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

/**
 * The Payment pass command.
 * <p>
 * Forwarding a client to the payment page.
 *
 * @author Haponenka Ihar
 * @version 1.0
 */
public class PaymentPassCommand implements Command {
    static Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router(PagePath.PAYMENT_PAGE);
        HttpSession session = request.getSession();
        long orderId = Long.parseLong(request.getParameter(ParameterName.ORDER_ID));
        OrderService service = ServiceFactory.getINSTANCE().getOrderService();
        try {
            Optional<Order> optionalOrder = service.findById(orderId);
            if (optionalOrder.isPresent()) {
                session.setAttribute(AttributeName.ORDER, optionalOrder.get());
            } else {
                logger.log(Level.WARN, "Order is not exist");
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e.getMessage());
            router.setPage(PagePath.ERROR_500);
        }
        return router;
    }
}
