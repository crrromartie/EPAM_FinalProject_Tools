package by.gaponenko.tools.validator;

import by.gaponenko.tools.controller.command.ParameterName;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class CardDataValidatorTest {

    @Test
    public void isValidPaymentCardParametersPositiveTest() {
        Map<String, String> paymentCardParameters = new HashMap<>();
        paymentCardParameters.put(ParameterName.CARD_NUMBER, "5555 6666 8888 9999");
        paymentCardParameters.put(ParameterName.CARD_YEAR, "22");
        paymentCardParameters.put(ParameterName.CARD_MONTH, "12");
        paymentCardParameters.put(ParameterName.CARD_CVV, "511");
        Assert.assertTrue(CardDataValidator.isValidPaymentCardParameters(paymentCardParameters));
    }

    @Test
    public void isValidPaymentCardParametersNegativeTest() {
        Map<String, String> paymentCardParameters = new HashMap<>();
        paymentCardParameters.put(ParameterName.CARD_NUMBER, "5555-6666-8888-9999");
        paymentCardParameters.put(ParameterName.CARD_YEAR, "28");
        paymentCardParameters.put(ParameterName.CARD_MONTH, "13");
        paymentCardParameters.put(ParameterName.CARD_CVV, "51");
        Assert.assertFalse(CardDataValidator.isValidPaymentCardParameters(paymentCardParameters));
    }

    @DataProvider(name = "cardNumberData")
    public Object[][] createCardNumberData() {
        return new Object[][]{
                {"4444 5555 6666 7777", true},
                {"2222 1111 4589 6589", true},
                {"2222444455556666", false},
                {"1111-8888-9999-8566", false},
                {null, false},
                {"", false},
        };
    }

    @Test(dataProvider = "cardNumberData")
    public void isValidCardNumberDataTest(String number, boolean expected) {
        boolean actual = CardDataValidator.isValidCardNumber(number);
        Assert.assertEquals(actual, expected);
    }

    @DataProvider(name = "monthCardData")
    public Object[][] createMonthCardData() {
        return new Object[][]{
                {"12", true},
                {"05", true},
                {"13", false},
                {"-1", false},
                {null, false},
                {"", false},
        };
    }

    @Test(dataProvider = "monthCardData")
    public void isValidMonthCardDataTest(String month, boolean expected) {
        boolean actual = CardDataValidator.isValidMonthCard(month);
        Assert.assertEquals(actual, expected);
    }

    @DataProvider(name = "yearCardData")
    public Object[][] createYearCardData() {
        return new Object[][]{
                {"21", true},
                {"25", true},
                {"20", false},
                {"27", false},
                {"20278", false},
                {null, false},
                {"", false},
        };
    }

    @Test(dataProvider = "yearCardData")
    public void isValidYearCardDataTest(String year, boolean expected) {
        boolean actual = CardDataValidator.isValidYearCard(year);
        Assert.assertEquals(actual, expected);
    }

    @DataProvider(name = "cvvCardData")
    public Object[][] createCvvCardData() {
        return new Object[][]{
                {"656", true},
                {"258", true},
                {"1", false},
                {"55", false},
                {"-10", false},
                {null, false},
                {"", false},
        };
    }

    @Test(dataProvider = "cvvCardData")
    public void isValidCardCvvTest(String cvv, boolean expected) {
        boolean actual = CardDataValidator.isValidCardCvv(cvv);
        Assert.assertEquals(actual, expected);
    }
}
