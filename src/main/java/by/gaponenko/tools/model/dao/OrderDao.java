package by.gaponenko.tools.model.dao;

import by.gaponenko.tools.entity.Order;
import by.gaponenko.tools.exception.DaoException;

import java.util.List;

/**
 * The interface Order dao.
 * <p>
 * Extends the interface of the {@code Dao}, supplementing it with specific
 * methods for the interaction of the application with Order entities in the database.
 *
 * @author Haponenka Ihar
 * @version 1.0
 * @see Dao
 */
public interface OrderDao extends Dao<Order> {
    /**
     * Find all order in the database by status.
     *
     * @param status the status
     * @return the list
     * @throws DaoException the dao exception
     */
    List<Order> findByStatus(Order.Status status) throws DaoException;

    /**
     * Find all order in the database by user id.
     *
     * @param id the id
     * @return the list
     * @throws DaoException the dao exception
     */
    List<Order> findByUserId(long id) throws DaoException;

    /**
     * Find all order in the database by user id and order status.
     *
     * @param id     the id
     * @param status the status
     * @return the list
     * @throws DaoException the dao exception
     */
    List<Order> findByUserIdAndOrderStatus(long id, Order.Status status) throws DaoException;

    /**
     * Adding a new order to the database.
     *
     * @param order the order
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean add(Order order) throws DaoException;

    /**
     * Update order status.
     *
     * @param id     the id
     * @param status the status
     * @return the boolean
     * @throws DaoException the dao exception
     */

    boolean updateStatus(long id, Order.Status status) throws DaoException;

    /**
     * Delete an order from the database.
     *
     * @param id the id
     * @throws DaoException the dao exception
     */
    void delete(long id) throws DaoException;
}
