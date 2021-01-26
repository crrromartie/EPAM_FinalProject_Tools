package by.gaponenko.tools.model.dao;

import by.gaponenko.tools.model.dao.impl.OrderDaoImpl;
import by.gaponenko.tools.model.dao.impl.ToolDaoImpl;
import by.gaponenko.tools.model.dao.impl.UserDaoImpl;

public class DaoFactory {
    private static final DaoFactory INSTANCE = new DaoFactory();

    private final UserDao userDao = new UserDaoImpl();
    private final ToolDao toolDao = new ToolDaoImpl();
    private final OrderDao orderDao = new OrderDaoImpl();

    private DaoFactory() {
    }

    public static DaoFactory getINSTANCE() {
        return INSTANCE;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public ToolDao getToolDao() {
        return toolDao;
    }

    public OrderDao getOrderDao() {
        return orderDao;
    }
}
