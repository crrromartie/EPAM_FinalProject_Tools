package by.gaponenko.tools.model.dao;

import by.gaponenko.tools.entity.Order;
import by.gaponenko.tools.exception.DaoException;

import java.util.List;

public interface OrderDao extends Dao<Order> {
    boolean add(Order order) throws DaoException;

    boolean updateStatus(long id, Order.Status status) throws DaoException;

    List<Order> findByStatus(Order.Status status) throws DaoException;

    List<Order> findAllByUserId(long id) throws DaoException;

    List<Order> findAllByUserIdAndOrderStatus(long id, Order.Status status) throws DaoException;
}
