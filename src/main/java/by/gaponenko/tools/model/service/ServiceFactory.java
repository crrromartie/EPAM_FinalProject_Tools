package by.gaponenko.tools.model.service;

import by.gaponenko.tools.model.service.impl.OrderServiceImpl;
import by.gaponenko.tools.model.service.impl.ToolServiceImpl;
import by.gaponenko.tools.model.service.impl.UserServiceImpl;

public class ServiceFactory {
    private static final ServiceFactory INSTANCE = new ServiceFactory();

    private final UserService userService = new UserServiceImpl();
    private final ToolService toolService = new ToolServiceImpl();
    private final OrderService orderService = new OrderServiceImpl();

    private ServiceFactory() {
    }

    public static ServiceFactory getINSTANCE() {
        return INSTANCE;
    }

    public UserService getUserService() {
        return userService;
    }

    public ToolService getToolService() {
        return toolService;
    }

    public OrderService getOrderService() {
        return orderService;
    }
}
