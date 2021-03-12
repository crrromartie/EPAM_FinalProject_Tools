package by.gaponenko.tools.model.service.impl;

import by.gaponenko.tools.builder.OrderBuilder;
import by.gaponenko.tools.controller.command.ParameterName;
import by.gaponenko.tools.entity.Order;
import by.gaponenko.tools.entity.Tool;
import by.gaponenko.tools.entity.User;
import by.gaponenko.tools.exception.DaoException;
import by.gaponenko.tools.exception.ServiceException;
import by.gaponenko.tools.model.dao.OrderDao;
import by.gaponenko.tools.model.dao.ToolDao;
import by.gaponenko.tools.model.dao.impl.OrderDaoImpl;
import by.gaponenko.tools.model.dao.impl.ToolDaoImpl;
import by.gaponenko.tools.model.service.EntityTransaction;
import by.gaponenko.tools.model.service.OrderService;
import by.gaponenko.tools.util.DateConverter;
import by.gaponenko.tools.validator.CardDataValidator;
import by.gaponenko.tools.validator.OrderDataValidator;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * The Order service.
 * <p>
 * Implements an interface OrderService for processing order-related data.
 *
 * @author Haponenka Ihar
 * @version 1.0
 * @see OrderService
 */
public class OrderServiceImpl implements OrderService {
    private static final int CURRENT_DAY = 1;

    @Override
    public Optional<Order> findById(long id) throws ServiceException {
        OrderDao orderDao = new OrderDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.initSingleQuery(orderDao);
        try {
            return orderDao.findById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.endSingleQuery();
        }
    }

    @Override
    public List<Order> findAll() throws ServiceException {
        OrderDao orderDao = new OrderDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.initSingleQuery(orderDao);
        try {
            return orderDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.endSingleQuery();
        }
    }

    @Override
    public List<Order> findByStatus(String orderStatus) throws ServiceException {
        if (!OrderDataValidator.isValidOrderStatus(orderStatus)) {
            return Collections.EMPTY_LIST;
        }
        OrderDao orderDao = new OrderDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.initSingleQuery(orderDao);
        try {
            return orderDao.findByStatus(Order.Status.valueOf(orderStatus.toUpperCase()));
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.endSingleQuery();
        }
    }

    @Override
    public List<Order> findByUserId(long id) throws ServiceException {
        OrderDao orderDao = new OrderDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.initSingleQuery(orderDao);
        try {
            return orderDao.findByUserId(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.endSingleQuery();
        }
    }

    @Override
    public List<Order> findByUserIdAndOrderStatus(long id, String orderStatus) throws ServiceException {
        if (!OrderDataValidator.isValidOrderStatus(orderStatus)) {
            return Collections.EMPTY_LIST;
        }
        OrderDao orderDao = new OrderDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.initSingleQuery(orderDao);
        try {
            return orderDao.findByUserIdAndOrderStatus(id, Order.Status.valueOf(orderStatus.toUpperCase()));
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.endSingleQuery();
        }
    }

    @Override
    public boolean add(Map<String, String> orderParameters, User user, Tool tool) throws ServiceException {
        if (!OrderDataValidator.isValidOrderParameters(orderParameters)) {
            return false;
        }
        OrderDao orderDao = new OrderDaoImpl();
        ToolDao toolDao = new ToolDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.initTransaction(orderDao, toolDao);
        OrderBuilder orderBuilder = new OrderBuilder()
                .setUser(user)
                .setTool(tool)
                .setStatus(Order.Status.NEW)
                .setOrderDate(LocalDate.parse(orderParameters.get(ParameterName.ORDER_DATE)))
                .setReturnDate(LocalDate.parse(orderParameters.get(ParameterName.ORDER_RETURN_DATE)))
                .setTotalCost(BigDecimal.valueOf(Double.parseDouble(orderParameters.get(ParameterName.ORDER_TOTAL_COST))));
        Order order = new Order(orderBuilder);
        boolean isAdd;
        try {
            isAdd = orderDao.add(order) && toolDao.inactivateTool(tool.getToolId());
            transaction.commitTransaction();
        } catch (DaoException e) {
            transaction.rollbackTransaction();
            throw new ServiceException(e);
        } finally {
            transaction.endTransaction();
        }
        return isAdd;
    }

    @Override
    public boolean updateStatus(long id, String orderStatus) throws ServiceException {
        if (!OrderDataValidator.isValidOrderStatus(orderStatus)) {
            return false;
        }
        OrderDao orderDao = new OrderDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.initSingleQuery(orderDao);
        try {
            return orderDao.updateStatus(id, Order.Status.valueOf(orderStatus.toUpperCase()));
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.endSingleQuery();
        }
    }

    @Override
    public boolean paymentOrder(Order order, Map<String, String> paymentCardParameters) throws ServiceException {
        if (!CardDataValidator.isValidPaymentCardParameters(paymentCardParameters)) {
            return false;
        }
        OrderDao orderDao = new OrderDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.initSingleQuery(orderDao);
        try {
            return orderDao.updateStatus(order.getOrderId(), Order.Status.ACTIVE);
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.endSingleQuery();
        }
    }

    @Override
    public boolean cancelOrder(long orderId, long toolId) throws ServiceException {
        OrderDao orderDao = new OrderDaoImpl();
        ToolDao toolDao = new ToolDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.initTransaction(orderDao, toolDao);
        boolean isCanceled;
        try {
            isCanceled = orderDao.updateStatus(orderId, Order.Status.CANCELED)
                    && toolDao.activateTool(toolId);
            transaction.commitTransaction();
        } catch (DaoException e) {
            transaction.rollbackTransaction();
            throw new ServiceException(e);
        } finally {
            transaction.endTransaction();
        }
        return isCanceled;
    }

    @Override
    public boolean rejectOrder(long orderId, long toolId) throws ServiceException {
        OrderDao orderDao = new OrderDaoImpl();
        ToolDao toolDao = new ToolDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.initTransaction(orderDao, toolDao);
        boolean isRejected;
        try {
            isRejected = orderDao.updateStatus(orderId, Order.Status.REJECTED)
                    && toolDao.activateTool(toolId);
            transaction.commitTransaction();
        } catch (DaoException e) {
            transaction.rollbackTransaction();
            throw new ServiceException(e);
        } finally {
            transaction.endTransaction();
        }
        return isRejected;
    }

    @Override
    public void updateOrdersStatuses() throws ServiceException {
        OrderDao orderDao = new OrderDaoImpl();
        ToolDao toolDao = new ToolDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.initTransaction(orderDao, toolDao);
        long currentDay = DateConverter.convertToLong(LocalDate.now());
        try {
            List<Order> orders = orderDao.findAll();
            for (Order order : orders) {
                if (order.getStatus().equals(Order.Status.ACTIVE)
                        && currentDay > DateConverter.convertToLong(order.getReturnDate())) {
                    toolDao.activateTool(order.getTool().getToolId());
                    orderDao.updateStatus(order.getOrderId(), Order.Status.COMPLETE);
                }
                if (order.getStatus().equals(Order.Status.NEW)
                        && currentDay > DateConverter.convertToLong(order.getOrderDate())) {
                    toolDao.activateTool(order.getTool().getToolId());
                    orderDao.delete(order.getOrderId());
                }
                if (order.getStatus().equals(Order.Status.APPROVED)
                        && currentDay > DateConverter.convertToLong(order.getOrderDate())) {
                    toolDao.activateTool(order.getTool().getToolId());
                    orderDao.delete(order.getOrderId());
                }
            }
            transaction.commitTransaction();
        } catch (DaoException e) {
            transaction.rollbackTransaction();
            throw new ServiceException(e);
        } finally {
            transaction.endTransaction();
        }
    }

    public BigDecimal calculateTotalCost(BigDecimal rentPrice, String orderDate, String returnDay) {
        BigDecimal totalCost = new BigDecimal(-1);
        if (OrderDataValidator.isValidDate(orderDate) && OrderDataValidator.isValidDate(returnDay)) {
            LocalDate oDate = LocalDate.parse(orderDate);
            LocalDate rDate = LocalDate.parse(returnDay);
            Period period = Period.between(oDate, rDate);
            int rentDays = period.getDays() + CURRENT_DAY;
            totalCost = (rentDays > 0) ? rentPrice.multiply(BigDecimal.valueOf(rentDays)) : new BigDecimal(-1);
        }
        return totalCost;
    }
}
