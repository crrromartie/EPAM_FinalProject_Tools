package by.gaponenko.tools.validator;

import by.gaponenko.tools.controller.command.ParameterName;
import by.gaponenko.tools.entity.User;

import java.util.Map;

/**
 * The User data validator.
 *
 * @author Haponenka Ihar
 * @version 1.0
 */
public class UserDataValidator {
    private static final String LOGIN_REGEX = "^\\S{3,15}$";
    private static final String PASSWORD_REGEX = "^\\S{6,15}$";
    private static final String NAME_REGEX = "^[a-zA-Zа-яА-Я]{1,20}$";
    private static final String SURNAME_REGEX = "^[a-zA-Zа-яА-Я]{1,20}$";
    private static final String EMAIL_REGEX = "^\\p{Alnum}+[._-]?\\p{Alnum}+@\\p{Alnum}+\\.\\p{Alpha}{2,4}$";
    private static final String PHONE_REGEX = "\\+375\\d{9}";

    private UserDataValidator() {
    }

    /**
     * Validates registration parameters.
     *
     * @param registrationParameters the registrationParameters
     * @return the boolean
     */
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

    /**
     * Validates edit user parameters.
     *
     * @param editUserParameters the editUserParameters
     * @return the boolean
     */
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

    /**
     * Validates user login.
     *
     * @param login the login
     * @return the boolean
     */
    public static boolean isValidLogin(String login) {
        return (login != null && !login.isEmpty() && login.matches(LOGIN_REGEX));
    }

    /**
     * Validates user password.
     *
     * @param password the password
     * @return the boolean
     */
    public static boolean isValidPassword(String password) {
        return (password != null && !password.isEmpty() && password.matches(PASSWORD_REGEX));
    }

    /**
     * Validates user name.
     *
     * @param name the name
     * @return the boolean
     */
    public static boolean isValidName(String name) {
        return (name != null && !name.isEmpty() && name.matches(NAME_REGEX));
    }

    /**
     * Validates user surname.
     *
     * @param surname the surname
     * @return the boolean
     */
    public static boolean isValidSurname(String surname) {
        return (surname != null && !surname.isEmpty() && surname.matches(SURNAME_REGEX));
    }

    /**
     * Validates user email.
     *
     * @param email the email
     * @return the boolean
     */
    public static boolean isValidEmail(String email) {
        return (email != null && !email.isEmpty() && email.matches(EMAIL_REGEX));
    }

    /**
     * Validates user phone.
     *
     * @param phone the phone
     * @return the boolean
     */
    public static boolean isValidPhone(String phone) {
        return (phone != null && !phone.isEmpty() && phone.matches(PHONE_REGEX));
    }

    /**
     * Validates user status.
     *
     * @param userStatus the userStatus
     * @return the boolean
     */
    public static boolean isValidUserStatus(String userStatus) {
        if (userStatus == null || userStatus.isEmpty()) {
            return false;
        }
        boolean isStatusValid = false;
        User.Status[] statuses = User.Status.values();
        for (User.Status status : statuses) {
            if (userStatus.toUpperCase().equals(status.name())) {
                isStatusValid = true;
                break;
            }
        }
        return isStatusValid;
    }
}
