package by.gaponenko.tools.model.service.impl;

import by.gaponenko.tools.creator.OrderCreator;
import by.gaponenko.tools.entity.Order;
import by.gaponenko.tools.entity.Tool;
import by.gaponenko.tools.entity.User;
import by.gaponenko.tools.exception.DaoException;
import by.gaponenko.tools.exception.ServiceException;
import by.gaponenko.tools.model.dao.DaoFactory;
import by.gaponenko.tools.model.dao.OrderDao;
import by.gaponenko.tools.model.dao.ToolDao;
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

public class OrderServiceImpl implements OrderService {
    private static final int CURRENT_DAY = 1;

    @Override
    public Optional<Order> findById(long id) throws ServiceException {
        Optional<Order> optionalOrder;
        DaoFactory factory = DaoFactory.getINSTANCE();
        OrderDao orderDao = factory.getOrderDao();
        try {
            optionalOrder = orderDao.findById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return optionalOrder;
    }

    @Override
    public List<Order> findAll() throws ServiceException {
        List<Order> orders;
        DaoFactory factory = DaoFactory.getINSTANCE();
        OrderDao orderDao = factory.getOrderDao();
        try {
            orders = orderDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return orders;
    }

    @Override
    public boolean add(Map<String, String> orderParameters, User user, Tool tool) throws ServiceException {
        boolean isAdd = false;
        DaoFactory factory = DaoFactory.getINSTANCE();
        OrderDao orderDao = factory.getOrderDao();
        if (OrderDataValidator.isValidOrderParameters(orderParameters)) {
            Order order = OrderCreator.createOrder(orderParameters, user, tool);
            try {
                isAdd = orderDao.add(order);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return isAdd;
    }

    @Override
    public boolean updateStatus(long id, Order.Status status) throws ServiceException {
        boolean isUpdate = false;
        DaoFactory factory = DaoFactory.getINSTANCE();
        OrderDao orderDao = factory.getOrderDao();
        if (OrderDataValidator.isValidOrderStatus(status)) {
            try {
                isUpdate = orderDao.updateStatus(id, status);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return isUpdate;
    }

    @Override
    public List<Order> findByStatus(Order.Status status) throws ServiceException {
        List<Order> orders = Collections.EMPTY_LIST;
        DaoFactory factory = DaoFactory.getINSTANCE();
        OrderDao orderDao = factory.getOrderDao();
        if (OrderDataValidator.isValidOrderStatus(status)) {
            try {
                orders = orderDao.findByStatus(status);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return orders;
    }

    @Override
    public List<Order> findAllByUserId(long id) throws ServiceException {
        List<Order> orders;
        DaoFactory factory = DaoFactory.getINSTANCE();
        OrderDao orderDao = factory.getOrderDao();
        try {
            orders = orderDao.findAllByUserId(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return orders;
    }

    @Override
    public List<Order> findAllByUserIdAndOrderStatus(long id, Order.Status status) throws ServiceException {
        List<Order> orders = Collections.EMPTY_LIST;
        DaoFactory factory = DaoFactory.getINSTANCE();
        OrderDao orderDao = factory.getOrderDao();
        if (OrderDataValidator.isValidOrderStatus(status)) {
            try {
                orders = orderDao.findAllByUserIdAndOrderStatus(id, status);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return orders;
    }

    @Override
    public BigDecimal calculateTotalCost(BigDecimal rentPrice, String orderDate, String returnDay) throws ServiceException {
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

    @Override
    public boolean paymentOrder(Order order, Map<String, String> paymentCardParameters) throws ServiceException {
        boolean isPaymentComplete = false;
        DaoFactory factory = DaoFactory.getINSTANCE();
        OrderDao orderDao = factory.getOrderDao();
        if (CardDataValidator.isValidPaymentCardParameters(paymentCardParameters)) {
            try {
                isPaymentComplete = orderDao.updateStatus(order.getOrderId(), Order.Status.ACTIVE);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return isPaymentComplete;
    }

    @Override
    public void updateOrdersStatuses() throws ServiceException {
        DaoFactory factory = DaoFactory.getINSTANCE();
        ToolDao toolDao = factory.getToolDao();
        List<Order> orders = findAll();
        long currentDay = DateConverter.convertToLong(LocalDate.now());
        try {
            for (Order order : orders) {
                if (order.getStatus().equals(Order.Status.ACTIVE)
                        && currentDay > DateConverter.convertToLong(order.getReturnDate())) {
                    updateStatus(order.getOrderId(), Order.Status.COMPLETE);
                    toolDao.activateTool(order.getTool().getToolId());
                }
                if ((order.getStatus().equals(Order.Status.NEW)
                        || order.getStatus().equals(Order.Status.APPROVED))
                        && currentDay > DateConverter.convertToLong(order.getOrderDate())) {
                    updateStatus(order.getOrderId(), Order.Status.CANCELED);
                    toolDao.activateTool(order.getTool().getToolId());
                }
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
