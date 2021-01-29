package by.gaponenko.tools.validator;

import by.gaponenko.tools.entity.User;
import by.gaponenko.tools.util.ParameterName;

import java.util.Map;

public class UserDataValidator {
    private static final String LOGIN_REGEX = "^\\S{3,15}$";
    private static final String PASSWORD_REGEX = "^\\S{6,15}$";
    private static final String NAME_REGEX = "^[a-zA-Zа-яА-я]{1,20}$";
    private static final String SURNAME_REGEX = "^[a-zA-Zа-яА-я]{1,20}$";
    private static final String EMAIL_REGEX = "\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*\\.\\w{2,4}";
    private static final String PHONE_REGEX = "\\+375\\d{9}";

    private UserDataValidator() {
    }

    public static boolean isValidRegistrationParameters(Map<String, String> registrationParameters) {
        boolean isValid = true;
        if (!isValidLogin(registrationParameters.get(ParameterName.USER_LOGIN))) {
            isValid = false;
        }
        if (!isValidPassword(registrationParameters.get(ParameterName.USER_PASSWORD))) {
            isValid = false;
        }
        if (!isValidName(registrationParameters.get(ParameterName.USER_NAME))) {
            isValid = false;
        }
        if (!isValidSurname(registrationParameters.get(ParameterName.USER_SURNAME))) {
            isValid = false;
        }
        if (!isValidEmail(registrationParameters.get(ParameterName.USER_EMAIL))) {
            isValid = false;
        }
        if (!isValidPhone(registrationParameters.get(ParameterName.USER_PHONE))) {
            isValid = false;
        }
        return isValid;
    }

    public static boolean isValidEditUserParameters(Map<String, String> editUserParameters) {
        boolean isValid = true;
        if (!isValidName(editUserParameters.get(ParameterName.USER_NAME))) {
            isValid = false;
        }
        if (!isValidSurname(editUserParameters.get(ParameterName.USER_SURNAME))) {
            isValid = false;
        }
        if (!isValidEmail(editUserParameters.get(ParameterName.USER_EMAIL))) {
            isValid = false;
        }
        if (!isValidPhone(editUserParameters.get(ParameterName.USER_PHONE))) {
            isValid = false;
        }
        return isValid;
    }

    public static boolean isValidLogin(String login) {
        return (login != null && !login.isEmpty() && login.matches(LOGIN_REGEX));
    }

    public static boolean isValidPassword(String password) {
        return (password != null && !password.isEmpty() && password.matches(PASSWORD_REGEX));
    }

    public static boolean isValidName(String name) {
        return (name != null && !name.isEmpty() && name.matches(NAME_REGEX));
    }

    public static boolean isValidSurname(String surname) {
        return (surname != null && !surname.isEmpty() && surname.matches(SURNAME_REGEX));
    }

    public static boolean isValidEmail(String email) {
        return (email != null && !email.isEmpty() && email.matches(EMAIL_REGEX));
    }

    public static boolean isValidPhone(String phone) {
        return (phone != null && !phone.isEmpty() && phone.matches(PHONE_REGEX));
    }

    public static boolean isValidUserStatus(User.Status status) {
        int counter = 0;
        User.Status[] statuses = User.Status.values();
        for (User.Status element : statuses) {
            if (status == element) {
                counter++;
            }
        }
        return (counter == 1);
    }
}
