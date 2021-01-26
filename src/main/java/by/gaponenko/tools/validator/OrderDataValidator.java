package by.gaponenko.tools.validator;

import by.gaponenko.tools.entity.Order;
import by.gaponenko.tools.util.ParameterName;

import java.util.Map;

public class OrderDataValidator {
    private static final String DATE_PATTERN = "(\\d{4})-(\\d{2})-(\\d{2})";
    private static final String DATE_DELIMITER = "-";
    private static final double MIN_TOTAL_COST = 1;
    private static final double MAX_TOTAL_COST = 999;

    private OrderDataValidator() {
    }

    public static boolean isValidOrderParameters(Map<String, String> orderParameters) {
        boolean isValid = true;
        if (!isValidDate(orderParameters.get(ParameterName.ORDER_DATE))) {
            isValid = false;
        }
        if (!isValidDate(orderParameters.get(ParameterName.ORDER_RETURN_DATE))) {
            isValid = false;
        }
        if (!isValidTotalCost(orderParameters.get(ParameterName.ORDER_TOTAL_COST))) {
            isValid = false;
        }
        return isValid;
    }

    public static boolean isValidDate(String date) {
        if (date == null || date.isEmpty() || !date.matches(DATE_PATTERN)) {
            return false;
        } else {
            String[] dateParts = date.split(DATE_DELIMITER);
            int year = Integer.parseInt(dateParts[0]);
            int month = Integer.parseInt(dateParts[1]);
            int day = Integer.parseInt(dateParts[2]);
            if (!isValidYear(year)) {
                return false;
            }
            return isValidMonthAndDay(day, month);
        }
    }

    public static boolean isValidYear(int year) {
        boolean isValid = true;
        if (year < 2020 || year > 2022) {
            isValid = false;
        }
        return isValid;
    }

    public static boolean isValidMonthAndDay(int day, int month) {
        boolean isValid = false;
        if (month > 0 && month <= 12) {
            isValid = switch (month) {
                case 1, 3, 5, 7, 8, 10, 12 -> day > 0 && day <= 31;
                case 4, 6, 9, 11 -> day > 0 && day <= 30;
                case 2 -> day > 0 && day <= 29;
                default -> false;
            };
        }
        return isValid;
    }

    public static boolean isValidOrderStatus(Order.Status status) {
        int counter = 0;
        Order.Status[] statuses = Order.Status.values();
        for (Order.Status element : statuses) {
            if (status == element) {
                counter++;
            }
        }
        return (counter > 0);
    }

    public static boolean isValidTotalCost(String cost) {
        boolean isValid = false;
        if (cost != null && !cost.isEmpty()) {
            double doubleCost = Double.parseDouble(cost);
            isValid = (doubleCost >= MIN_TOTAL_COST && doubleCost <= MAX_TOTAL_COST);
        }
        return isValid;
    }
}
