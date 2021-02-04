package by.gaponenko.tools.model.service;

import by.gaponenko.tools.entity.Order;
import by.gaponenko.tools.entity.Tool;
import by.gaponenko.tools.entity.User;
import by.gaponenko.tools.exception.ServiceException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * The interface Order service.
 * <p>
 * Indicates methods for processing information related to orders.
 *
 * @author Haponenka Ihar
 * @version 1.0
 */
public interface OrderService {

    /**
     * Find order by id.
     *
     * @param id the id
     * @return the optional
     * @throws ServiceException the service exception
     */
    Optional<Order> findById(long id) throws ServiceException;

    /**
     * Find all orders.
     *
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Order> findAll() throws ServiceException;

    /**
     * Find all orders by status.
     *
     * @param status the status
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Order> findByStatus(Order.Status status) throws ServiceException;

    /**
     * Find all orders by user id.
     *
     * @param id the id
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Order> findByUserId(long id) throws ServiceException;

    /**
     * Find all orders by user id and order status.
     *
     * @param id     the id
     * @param status the status
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Order> findByUserIdAndOrderStatus(long id, Order.Status status) throws ServiceException;

    /**
     * Add new order.
     *
     * @param orderParameters the orderParameters
     * @param user            the user
     * @param tool            the tool
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean add(Map<String, String> orderParameters, User user, Tool tool) throws ServiceException;

    /**
     * Update order status.
     *
     * @param id     the id
     * @param status the status
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean updateStatus(long id, Order.Status status) throws ServiceException;

    /**
     * Order payment.
     *
     * @param order                 the order
     * @param paymentCardParameters the paymentCardParameters
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean paymentOrder(Order order, Map<String, String> paymentCardParameters) throws ServiceException;

    /**
     * Cancel order.
     *
     * @param orderId the orderId
     * @param toolId  the toolId
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean cancelOrder(long orderId, long toolId) throws ServiceException;

    /**
     * Reject order.
     *
     * @param orderId the orderId
     * @param toolId  the toolId
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean rejectOrder(long orderId, long toolId) throws ServiceException;

    /**
     * Update orders statuses.
     *
     * @throws ServiceException the service exception
     */
    void updateOrdersStatuses() throws ServiceException;

    /**
     * Calculate total order cost.
     *
     * @param rentPrice the rentPrice
     * @param orderDate the orderDate
     * @param returnDay the returnDate
     * @return the BigDecimal
     */
    BigDecimal calculateTotalCost(BigDecimal rentPrice, String orderDate, String returnDay);
}
