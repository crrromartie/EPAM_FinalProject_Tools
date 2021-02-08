package by.gaponenko.tools.validator;

import by.gaponenko.tools.controller.command.ParameterName;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class OrderDataValidatorTest {

    @Test
    public void isValidOrderParametersPositiveTest() {
        Map<String, String> orderParameters = new HashMap<>();
        orderParameters.put(ParameterName.ORDER_DATE, "2021-01-15");
        orderParameters.put(ParameterName.ORDER_RETURN_DATE, "2021-01-16");
        orderParameters.put(ParameterName.ORDER_TOTAL_COST, "50");
        Assert.assertTrue(OrderDataValidator.isValidOrderParameters(orderParameters));
    }

    @Test
    public void isValidOrderParametersNegativeTest() {
        Map<String, String> orderParameters = new HashMap<>();
        orderParameters.put(ParameterName.ORDER_DATE, "2021-01-15");
        orderParameters.put(ParameterName.ORDER_RETURN_DATE, "2021-01-16");
        orderParameters.put(ParameterName.ORDER_TOTAL_COST, "1000");
        Assert.assertFalse(OrderDataValidator.isValidOrderParameters(orderParameters));
    }

    @DataProvider(name = "dateData")
    public Object[][] createDateData() {
        return new Object[][]{
                {"2021-01-15", true},
                {"2022-12-01", true},
                {"2023-01-01", false},
                {"2022-13-05", false},
                {null, false},
                {"", false},
        };
    }

    @Test(dataProvider = "dateData")
    public void isValidDateTest(String date, boolean expected) {
        boolean actual = OrderDataValidator.isValidDate(date);
        Assert.assertEquals(actual, expected);
    }

    @DataProvider(name = "yearData")
    public Object[][] createYearData() {
        return new Object[][]{
                {2021, true},
                {2022, true},
                {2023, false},
                {202, false},
                {0, false},
        };
    }

    @Test(dataProvider = "yearData")
    public void isValidYearTest(int year, boolean expected) {
        boolean actual = OrderDataValidator.isValidYear(year);
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void iaValidMonthAndDayPositiveTest() {
        Assert.assertTrue(OrderDataValidator.isValidMonthAndDay(2, 12));
    }

    @Test
    public void iaValidMonthAndDayNegativeTest() {
        Assert.assertFalse(OrderDataValidator.isValidMonthAndDay(30, 2));
    }

    @DataProvider(name = "orderStatusData")
    public Object[][] createOrderStatusData() {
        return new Object[][]{
                {"approved", true},
                {"Rejected", true},
                {"active", true},
                {"any", false},
                {"status-new", false},
                {null, false},
                {"", false},
        };
    }

    @Test(dataProvider = "orderStatusData")
    public void isValidOrderStatusDataTest(String orderStatus, boolean expected) {
        boolean actual = OrderDataValidator.isValidOrderStatus(orderStatus);
        Assert.assertEquals(actual, expected);
    }

    @DataProvider(name = "totalCostData")
    public Object[][] createTotalCostData() {
        return new Object[][]{
                {"15", true},
                {"99", true},
                {"-1", false},
                {"1000", false},
                {null, false},
                {"", false},
        };
    }

    @Test(dataProvider = "totalCostData")
    public void isValidTotalCostTest(String totalCost, boolean expected) {
        boolean actual = OrderDataValidator.isValidTotalCost(totalCost);
        Assert.assertEquals(actual, expected);
    }
}
