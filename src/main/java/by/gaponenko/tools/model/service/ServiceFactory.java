package by.gaponenko.tools.model.service;

import by.gaponenko.tools.model.service.impl.OrderServiceImpl;
import by.gaponenko.tools.model.service.impl.ToolServiceImpl;
import by.gaponenko.tools.model.service.impl.UserServiceImpl;

/**
 * The Service factory.
 * <p>
 * Is used for storing  objects of services and provides an access to them.
 *
 * @author Haponenka Ihar
 * @version 1.0
 */
public class ServiceFactory {
    private static final ServiceFactory INSTANCE = new ServiceFactory();

    private final UserService userService = new UserServiceImpl();
    private final ToolService toolService = new ToolServiceImpl();
    private final OrderService orderService = new OrderServiceImpl();

    private ServiceFactory() {
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static ServiceFactory getINSTANCE() {
        return INSTANCE;
    }

    /**
     * Gets user service.
     *
     * @return the user service
     */
    public UserService getUserService() {
        return userService;
    }

    /**
     * Gets tool service.
     *
     * @return the tool service
     */
    public ToolService getToolService() {
        return toolService;
    }

    /**
     * Gets order service.
     *
     * @return the order service
     */
    public OrderService getOrderService() {
        return orderService;
    }
}
