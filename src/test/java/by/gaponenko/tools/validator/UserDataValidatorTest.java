package by.gaponenko.tools.validator;

import by.gaponenko.tools.controller.command.ParameterName;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class UserDataValidatorTest {

    @Test
    public void isValidRegistrationParametersPositiveTest() {
        Map<String, String> registrationParameters = new HashMap<>();
        registrationParameters.put(ParameterName.USER_LOGIN, "big");
        registrationParameters.put(ParameterName.USER_PASSWORD, "qwerty");
        registrationParameters.put(ParameterName.USER_NAME, "Сергей");
        registrationParameters.put(ParameterName.USER_SURNAME, "Минаков");
        registrationParameters.put(ParameterName.USER_EMAIL, "email@tut.by");
        registrationParameters.put(ParameterName.USER_PHONE, "+375295556677");
        Assert.assertTrue(UserDataValidator.isValidRegistrationParameters(registrationParameters));
    }

    @Test
    public void isValidRegistrationParametersNegativeTest() {
        Map<String, String> registrationParameters = new HashMap<>();
        registrationParameters.put(ParameterName.USER_LOGIN, "big");
        registrationParameters.put(ParameterName.USER_PASSWORD, "qwerty");
        registrationParameters.put(ParameterName.USER_NAME, "Сергей");
        registrationParameters.put(ParameterName.USER_SURNAME, "Минаков");
        registrationParameters.put(ParameterName.USER_EMAIL, "email@tut.by");
        registrationParameters.put(ParameterName.USER_PHONE, "+375(29)5556677");
        Assert.assertFalse(UserDataValidator.isValidRegistrationParameters(registrationParameters));
    }

    @Test
    public void isValidEditUserParametersPositiveTest() {
        Map<String, String> editUserParameters = new HashMap<>();
        editUserParameters.put(ParameterName.USER_LOGIN, "big");
        editUserParameters.put(ParameterName.USER_NAME, "Сергей");
        editUserParameters.put(ParameterName.USER_SURNAME, "Минаков");
        editUserParameters.put(ParameterName.USER_EMAIL, "email@tut.by");
        editUserParameters.put(ParameterName.USER_PHONE, "+375295556677");
        Assert.assertTrue(UserDataValidator.isValidEditUserParameters(editUserParameters));
    }

    @Test
    public void isValidEditUserParametersNegativeTest() {
        Map<String, String> editUserParameters = new HashMap<>();
        editUserParameters.put(ParameterName.USER_LOGIN, "big");
        editUserParameters.put(ParameterName.USER_NAME, "Сергей");
        editUserParameters.put(ParameterName.USER_SURNAME, "Минаков");
        editUserParameters.put(ParameterName.USER_EMAIL, "email@tut.by");
        editUserParameters.put(ParameterName.USER_PHONE, "+375(29)5556677");
        Assert.assertFalse(UserDataValidator.isValidEditUserParameters(editUserParameters));
    }

    @DataProvider(name = "loginData")
    public Object[][] createLoginData() {
        return new Object[][]{
                {"big", true},
                {"gorilla_1989", true},
                {"sf", false},
                {"gorilla 1990", false},
                {null, false},
                {"", false},
        };
    }

    @Test(dataProvider = "loginData")
    public void isValidLoginTest(String login, boolean expected) {
        boolean actual = UserDataValidator.isValidLogin(login);
        Assert.assertEquals(actual, expected);
    }

    @DataProvider(name = "passwordData")
    public Object[][] createPasswordData() {
        return new Object[][]{
                {"qwerty", true},
                {"dfgg48ynjgb", true},
                {"sdf", false},
                {"jdfefbuebvuvbeuvbwbrwvburvbur", false},
                {null, false},
                {"", false},
        };
    }

    @Test(dataProvider = "passwordData")
    public void isValidPasswordTest(String password, boolean expected) {
        boolean actual = UserDataValidator.isValidPassword(password);
        Assert.assertEquals(actual, expected);
    }

    @DataProvider(name = "nameData")
    public Object[][] createNameData() {
        return new Object[][]{
                {"Сергей", true},
                {"Joe", true},
                {"Сергеййййййййййййййййййййййййййй", false},
                {"Минаков Сергей", false},
                {null, false},
                {"", false},
        };
    }

    @Test(dataProvider = "nameData")
    public void isValidNameTest(String name, boolean expected) {
        boolean actual = UserDataValidator.isValidName(name);
        Assert.assertEquals(actual, expected);
    }

    @DataProvider(name = "surnameData")
    public Object[][] createSurnameData() {
        return new Object[][]{
                {"Минаков", true},
                {"Bean", true},
                {"Минаковвввввввввввввввв", false},
                {"Минаков Сергей", false},
                {null, false},
                {"", false},
        };
    }

    @Test(dataProvider = "surnameData")
    public void isValidSurnameTest(String surname, boolean expected) {
        boolean actual = UserDataValidator.isValidSurname(surname);
        Assert.assertEquals(actual, expected);
    }

    @DataProvider(name = "emailData")
    public Object[][] createEmailData() {
        return new Object[][]{
                {"email@mail.ru", true},
                {"5568@97.by", true},
                {"user@bestcompany.byebye", false},
                {"<script>@tut.by", false},
                {null, false},
                {"", false},
        };
    }

    @Test(dataProvider = "emailData")
    public void isValidEmailTest(String email, boolean expected) {
        boolean actual = UserDataValidator.isValidEmail(email);
        Assert.assertEquals(actual, expected);
    }

    @DataProvider(name = "phoneData")
    public Object[][] createPhoneData() {
        return new Object[][]{
                {"+375295556688", true},
                {"+375443511406", true},
                {"+375(29)1115566", false},
                {"+375(29)111-55-66", false},
                {null, false},
                {"", false},
        };
    }

    @Test(dataProvider = "phoneData")
    public void isValidPhoneTest(String phone, boolean expected) {
        boolean actual = UserDataValidator.isValidPhone(phone);
        Assert.assertEquals(actual, expected);
    }

    @DataProvider(name = "userStatusData")
    public Object[][] createUserStatusData() {
        return new Object[][]{
                {"blocked", true},
                {"UNCONFIRMED", true},
                {"Active", true},
                {"unknown", false},
                {null, false},
                {"", false},
        };
    }

    @Test(dataProvider = "userStatusData")
    public void isValidUserStatusTest(String userStatus, boolean expected) {
        boolean actual = UserDataValidator.isValidUserStatus(userStatus);
        Assert.assertEquals(actual, expected);
    }
}
