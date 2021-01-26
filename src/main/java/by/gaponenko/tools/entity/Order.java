package by.gaponenko.tools.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Order extends Entity {
    public enum Status {
        NEW(1), APPROVED(2),
        CANCELED(3), REJECTED(4),
        ACTIVE(5), COMPLETE(6);

        private final int orderStatusId;

        Status(int orderStatusId) {
            this.orderStatusId = orderStatusId;
        }

        public int getOrderStatusId() {
            return orderStatusId;
        }

        private static final Map<Integer, Order.Status> ORDER_STATUS_MAP = new HashMap<>();

        static {
            for (Order.Status status : values()) {
                ORDER_STATUS_MAP.put(status.getOrderStatusId(), status);
            }
        }

        public static Order.Status getOrderStatusById(int orderStatusId) {
            return ORDER_STATUS_MAP.get(orderStatusId);
        }
    }

    private long orderId;
    private LocalDate orderDate;
    private LocalDate returnDate;
    private BigDecimal totalCost;
    private Status status;
    private User user;
    private Tool tool;

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Tool getTool() {
        return tool;
    }

    public void setTool(Tool tool) {
        this.tool = tool;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Order order = (Order) o;
        if (orderId != order.orderId) {
            return false;
        }
        if (orderDate != null ? !orderDate.equals(order.orderDate) : order.orderDate != null) {
            return false;
        }
        if (returnDate != null ? !returnDate.equals(order.returnDate) : order.returnDate != null) {
            return false;
        }
        if (totalCost != null ? !totalCost.equals(order.totalCost) : order.totalCost != null) {
            return false;
        }
        if (status != order.status) {
            return false;
        }
        if (user != null ? !user.equals(order.user) : order.user != null) {
            return false;
        }
        return tool != null ? tool.equals(order.tool) : order.tool == null;
    }

    @Override
    public int hashCode() {
        int result = Long.hashCode(orderId);
        result = 31 * result + (orderDate != null ? orderDate.hashCode() : 0);
        result = 31 * result + (returnDate != null ? returnDate.hashCode() : 0);
        result = 31 * result + (totalCost != null ? totalCost.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (tool != null ? tool.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Order.class.getSimpleName() + "[", "]")
                .add("orderId=" + orderId)
                .add("orderDate=" + orderDate)
                .add("returnDate=" + returnDate)
                .add("totalCost=" + totalCost)
                .add("status=" + status)
                .add("user=" + user)
                .add("tool=" + tool)
                .toString();
    }
}
