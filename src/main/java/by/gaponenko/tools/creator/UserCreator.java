package by.gaponenko.tools.creator;

import by.gaponenko.tools.entity.User;
import by.gaponenko.tools.util.ParameterName;

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

    public static User createUser(Map<String, String> editUserParameters, long id) {
        User user = new User();
        user.setName(editUserParameters.get(ParameterName.USER_NAME));
        user.setSurname(editUserParameters.get(ParameterName.USER_SURNAME));
        user.setPhone(editUserParameters.get(ParameterName.USER_PHONE));
        user.setEmail(editUserParameters.get(ParameterName.USER_EMAIL));
        user.setUserId(id);
        return user;
    }

    private UserCreator() {
    }
}
