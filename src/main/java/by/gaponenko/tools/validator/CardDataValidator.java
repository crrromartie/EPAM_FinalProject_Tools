package by.gaponenko.tools.validator;

import by.gaponenko.tools.controller.command.ParameterName;

import java.util.Map;

/**
 * Card data validator.
 *
 * @author Haponenka Ihar
 * @version 1.0
 */
public class CardDataValidator {
    private static final String CARD_NUMBER_REGEX = "^[\\d]{4}\\s[\\d]{4}\\s[\\d]{4}\\s[\\d]{4}$";
    private static final String MONTH_CARD_REGEX = "\\d{1,2}";
    private static final String YEAR_CARD_REGEX = "\\d{2}";
    private static final String CVV_CARD_CODE_REGEX = "^[\\d]{3}$";

    private CardDataValidator() {
    }

    /**
     * Validates payment card parameters.
     *
     * @param paymentCardParameters the paymentCardParameters
     * @return the boolean
     */
    public static boolean isValidPaymentCardParameters(Map<String, String> paymentCardParameters) {
        boolean isValid = true;
        if (!isValidCardNumber(paymentCardParameters.get(ParameterName.CARD_NUMBER))) {
            isValid = false;
        }
        if (!isValidMonthCard(paymentCardParameters.get(ParameterName.CARD_MONTH))) {
            isValid = false;
        }
        if (!isValidYearCard(paymentCardParameters.get(ParameterName.CARD_YEAR))) {
            isValid = false;
        }
        if (!isValidCardCvv(paymentCardParameters.get(ParameterName.CARD_CVV))) {
            isValid = false;
        }
        return isValid;
    }

    /**
     * Validates card number.
     *
     * @param number the number
     * @return the boolean
     */
    public static boolean isValidCardNumber(String number) {
        return (number != null && !number.isEmpty() && number.matches(CARD_NUMBER_REGEX));
    }

    /**
     * Validates month card.
     *
     * @param month
     * @return
     */
    public static boolean isValidMonthCard(String month) {
        if (month == null || month.isEmpty() || !month.matches(MONTH_CARD_REGEX)) {
            return false;
        } else {
            int intMonth = Integer.parseInt(month);
            return (intMonth >= 1 && intMonth <= 12);
        }
    }

    /**
     * Validates year card.
     *
     * @param year the year
     * @return the boolean
     */
    public static boolean isValidYearCard(String year) {
        if (year == null || year.isEmpty() || !year.matches(YEAR_CARD_REGEX)) {
            return false;
        } else {
            int intYear = Integer.parseInt(year);
            return (intYear >= 21 && intYear <= 26);
        }
    }

    /**
     * Validates card CVV.
     *
     * @param cvv the cvv
     * @return the boolean
     */
    public static boolean isValidCardCvv(String cvv) {
        return (cvv != null && !cvv.isEmpty() && cvv.matches(CVV_CARD_CODE_REGEX));
    }
}
