package by.gaponenko.tools.controller.command.impl.navigation;

import by.gaponenko.tools.controller.Router;
import by.gaponenko.tools.controller.command.Command;
import by.gaponenko.tools.controller.command.PagePath;

import javax.servlet.http.HttpServletRequest;

/**
 * The Orders pass only command.
 * <p>
 * Forwarding a user to the orders page
 * without adding orders.
 * Using for defence pagination command against f5.
 *
 * @author Haponenka Ihar
 * @version 1.0
 */
public class OrdersPassOnlyCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        return new Router(PagePath.ORDERS_PAGE);
    }
}
