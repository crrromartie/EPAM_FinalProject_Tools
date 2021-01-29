package by.gaponenko.tools.validator;

import by.gaponenko.tools.entity.Tool;
import by.gaponenko.tools.util.ParameterName;

import java.util.Map;

public class ToolDataValidator {
    private static final String MODEL_REGEX = ".{1,50}";
    private static final String DESCRIPTION_REGEX = ".{1,250}";
    private static final double MIN_RENT_PRICE = 1;
    private static final double MAX_RENT_PRICE = 99;

    private ToolDataValidator() {
    }

    public static boolean isValidToolParameters(Map<String, String> toolParameters) {
        boolean isValid = true;
        if (!isValidToolType(Tool.Type.valueOf(toolParameters.get(ParameterName.TOOL_TYPE)))) {
            isValid = false;
        }
        if (!isValidModel(toolParameters.get(ParameterName.TOOL_MODEL))) {
            isValid = false;
        }
        if (!isValidDescriptionEng(toolParameters.get(ParameterName.TOOL_DESCRIPTION_ENG))) {
            isValid = false;
        }
        if (!isValidDescriptionRus(toolParameters.get(ParameterName.TOOL_DESCRIPTION_RUS))) {
            isValid = false;
        }
        if (!isValidRentPrice(toolParameters.get(ParameterName.TOOL_RENT_PRICE))) {
            isValid = false;
        }
        return isValid;
    }

    public static boolean isValidEditToolParameters(Map<String, String> toolParameters) {
        boolean isValid = true;
        if (!isValidDescriptionEng(toolParameters.get(ParameterName.TOOL_DESCRIPTION_ENG))) {
            isValid = false;
        }
        if (!isValidDescriptionRus(toolParameters.get(ParameterName.TOOL_DESCRIPTION_RUS))) {
            isValid = false;
        }
        if (!isValidRentPrice(toolParameters.get(ParameterName.TOOL_RENT_PRICE))) {
            isValid = false;
        }
        return isValid;
    }

    public static boolean isValidToolType(Tool.Type type) {
        int counter = 0;
        Tool.Type[] types = Tool.Type.values();
        for (Tool.Type element : types) {
            if (element == type) {
                counter++;
            }
        }
        return (counter == 1);
    }

    public static boolean isValidModel(String model) {
        return (model != null && !model.isEmpty() && model.matches(MODEL_REGEX));
    }

    public static boolean isValidDescriptionEng(String description) {
        return (description != null && !description.isEmpty() && description.matches(DESCRIPTION_REGEX));
    }

    public static boolean isValidDescriptionRus(String description) {
        return (description != null && !description.isEmpty() && description.matches(DESCRIPTION_REGEX));
    }

    public static boolean isValidRentPrice(String price) {
        boolean isValid = false;
        if (price != null && !price.isEmpty()) {
            double doublePrice = Double.parseDouble(price);
            isValid = (doublePrice >= MIN_RENT_PRICE && doublePrice <= MAX_RENT_PRICE);
        }
        return isValid;
    }
}
