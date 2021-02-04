package by.gaponenko.tools.main;

import by.gaponenko.tools.entity.User;
import by.gaponenko.tools.exception.ServiceException;
import by.gaponenko.tools.model.service.ServiceFactory;
import by.gaponenko.tools.model.service.UserService;

public class Runner {
    public static void main(String[] args) throws ServiceException {
        UserService service = ServiceFactory.getINSTANCE().getUserService();
        User user = service.findById(3).get();
        System.out.println(user);
    }
}
