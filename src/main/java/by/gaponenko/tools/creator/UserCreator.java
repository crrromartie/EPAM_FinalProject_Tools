package by.gaponenko.tools.creator;

import by.gaponenko.tools.util.ParameterName;
import by.gaponenko.tools.entity.User;

import java.util.Map;

public class UserCreator {
    public static User createUser(Map<String, String> registrationParameters) {
        User user = new User();
        user.setLogin(registrationParameters.get(ParameterName.USER_LOGIN));
        user.setName(registrationParameters.get(ParameterName.USER_NAME));
        user.setSurname(registrationParameters.get(ParameterName.USER_SURNAME));
        user.setPhone(registrationParameters.get(ParameterName.USER_PHONE));
        user.setEmail(registrationParameters.get(ParameterName.USER_EMAIL));
        return user;
    }

    private UserCreator() {
    }
}
