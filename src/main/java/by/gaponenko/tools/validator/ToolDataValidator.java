package by.gaponenko.tools.validator;

import by.gaponenko.tools.controller.command.ParameterName;
import by.gaponenko.tools.entity.Tool;

import java.util.Map;

/**
 * The Tool data validator.
 *
 * @author Haponenka Ihar
 * @version 1.0
 */
public class ToolDataValidator {
    private static final String MODEL_REGEX = ".{1,50}";
    private static final String DESCRIPTION_REGEX = ".{1,250}";
    private static final double MIN_RENT_PRICE = 1;
    private static final double MAX_RENT_PRICE = 99;

    private ToolDataValidator() {
    }

    /**
     * Validates tool parameters.
     *
     * @param toolParameters the toolParameters
     * @return the boolean
     */
    public static boolean isValidToolParameters(Map<String, String> toolParameters) {
        boolean isValid = true;
        if (!isValidToolType(toolParameters.get(ParameterName.TOOL_TYPE))) {
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

    /**
     * Validates edit tool parameters.
     *
     * @param editToolParameters the editToolParameters
     * @return the boolean
     */
    public static boolean isValidEditToolParameters(Map<String, String> editToolParameters) {
        boolean isValid = true;
        if (!isValidDescriptionEng(editToolParameters.get(ParameterName.TOOL_DESCRIPTION_ENG))) {
            isValid = false;
        }
        if (!isValidDescriptionRus(editToolParameters.get(ParameterName.TOOL_DESCRIPTION_RUS))) {
            isValid = false;
        }
        if (!isValidRentPrice(editToolParameters.get(ParameterName.TOOL_RENT_PRICE))) {
            isValid = false;
        }
        return isValid;
    }

    /**
     * Validates tool type.
     *
     * @param toolType the toolType
     * @return the boolean
     */
    public static boolean isValidToolType(String toolType) {
        if (toolType == null || toolType.isEmpty()) {
            return false;
        }
        boolean isToolTypeValid = false;
        Tool.Type[] types = Tool.Type.values();
        for (Tool.Type type : types) {
            if (toolType.toUpperCase().equals(type.name())) {
                isToolTypeValid = true;
                break;
            }
        }
        return isToolTypeValid;
    }

    /**
     * Validates tool model.
     *
     * @param model the model
     * @return the boolean
     */
    public static boolean isValidModel(String model) {
        return (model != null && !model.isEmpty() && model.matches(MODEL_REGEX));
    }

    /**
     * Validates description on english.
     *
     * @param description the description
     * @return the boolean
     */
    public static boolean isValidDescriptionEng(String description) {
        return (description != null && !description.isEmpty() && description.matches(DESCRIPTION_REGEX));
    }

    /**
     * Validates description on russian.
     *
     * @param description the description
     * @return the boolean
     */
    public static boolean isValidDescriptionRus(String description) {
        return (description != null && !description.isEmpty() && description.matches(DESCRIPTION_REGEX));
    }

    /**
     * Validates tool rent price.
     *
     * @param price the price
     * @return the boolean
     */
    public static boolean isValidRentPrice(String price) {
        boolean isValid = false;
        if (price != null && !price.isEmpty()) {
            double doublePrice = Double.parseDouble(price);
            isValid = (doublePrice >= MIN_RENT_PRICE && doublePrice <= MAX_RENT_PRICE);
        }
        return isValid;
    }
}
