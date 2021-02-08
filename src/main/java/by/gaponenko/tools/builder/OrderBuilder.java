package by.gaponenko.tools.builder;

import by.gaponenko.tools.entity.Order;
import by.gaponenko.tools.entity.Tool;
import by.gaponenko.tools.entity.User;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * The Order builder.
 * <p>
 * The class represents order builder.
 *
 * @author Haponenka Ihar
 * @version 1.0
 */
public class OrderBuilder {
    private long orderId;
    private LocalDate orderDate;
    private LocalDate returnDate;
    private BigDecimal totalCost;
    private Order.Status status;
    private User user;
    private Tool tool;

    public long getOrderId() {
        return orderId;
    }

    public OrderBuilder setOrderId(long orderId) {
        this.orderId = orderId;
        return this;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public OrderBuilder setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
        return this;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public OrderBuilder setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
        return this;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public OrderBuilder setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
        return this;
    }

    public Order.Status getStatus() {
        return status;
    }

    public OrderBuilder setStatus(Order.Status status) {
        this.status = status;
        return this;
    }

    public User getUser() {
        return user;
    }

    public OrderBuilder setUser(User user) {
        this.user = user;
        return this;
    }

    public Tool getTool() {
        return tool;
    }

    public OrderBuilder setTool(Tool tool) {
        this.tool = tool;
        return this;
    }
}
