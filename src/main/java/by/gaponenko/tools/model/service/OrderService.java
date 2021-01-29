package by.gaponenko.tools.model.service;

import by.gaponenko.tools.entity.Order;
import by.gaponenko.tools.entity.Tool;
import by.gaponenko.tools.entity.User;
import by.gaponenko.tools.exception.ServiceException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface OrderService {

    Optional<Order> findById(long id) throws ServiceException;

    List<Order> findAll() throws ServiceException;

    boolean add(Map<String, String> orderParameters, User user, Tool tool) throws ServiceException;

    boolean updateStatus(long id, Order.Status status) throws ServiceException;

    List<Order> findByStatus(Order.Status status) throws ServiceException;

    List<Order> findAllByUserId(long id) throws ServiceException;

    List<Order> findAllByUserIdAndOrderStatus(long id, Order.Status status) throws ServiceException;

    boolean paymentOrder(Order order, Map<String, String> paymentCardParameters) throws ServiceException;

    boolean cancelOrder(long orderId, long toolId) throws ServiceException;

    boolean rejectOrder(long orderId, long toolId) throws ServiceException;

    void updateOrdersStatuses() throws ServiceException;

    BigDecimal calculateTotalCost(BigDecimal rentPrice, String orderDate, String returnDay);
}
