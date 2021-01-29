package by.gaponenko.tools.creator;

import by.gaponenko.tools.entity.Order;
import by.gaponenko.tools.entity.Tool;
import by.gaponenko.tools.entity.User;
import by.gaponenko.tools.util.ParameterName;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

public class OrderCreator {

    public static Order createOrder(Map<String, String> orderParameters, User user, Tool tool) {
        Order order = new Order();
        order.setUser(user);
        order.setTool(tool);
        order.setStatus(Order.Status.NEW);
        order.setOrderDate(LocalDate.parse(orderParameters.get(ParameterName.ORDER_DATE)));
        order.setReturnDate(LocalDate.parse(orderParameters.get(ParameterName.ORDER_RETURN_DATE)));
        order.setTotalCost(BigDecimal.valueOf(Double.parseDouble(orderParameters.get(ParameterName.ORDER_TOTAL_COST))));
        return order;
    }

    private OrderCreator() {
    }
}
