package by.gaponenko.tools.util;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.LocalDate;

public class DateConverterTest {

    @Test
    public void convertToLongPositiveTest() {
        LocalDate localDate = LocalDate.parse("2021-02-25");
        long expected = 1614200400000L;
        long actual = DateConverter.convertToLong(localDate);
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void convertToLongNegativeTest() {
        LocalDate localDate = LocalDate.parse("2021-02-25");
        long unexpected = 1614200400200L;
        long actual = DateConverter.convertToLong(localDate);
        Assert.assertNotEquals(actual, unexpected);
    }

    @Test
    public void convertToDatePositiveTest() {
        long dateMillis = 1614200400000L;
        LocalDate expected = LocalDate.parse("2021-02-25");
        LocalDate actual = DateConverter.convertToDate(dateMillis);
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void convertToDateNegativeTest() {
        long dateMillis = 1614200400000L;
        LocalDate unexpected = LocalDate.parse("2021-02-26");
        LocalDate actual = DateConverter.convertToDate(dateMillis);
        Assert.assertNotEquals(actual, unexpected);
    }
}
