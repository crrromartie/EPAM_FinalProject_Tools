package by.gaponenko.tools.creator;

import by.gaponenko.tools.entity.Tool;
import by.gaponenko.tools.util.ParameterName;

import java.math.BigDecimal;
import java.util.Map;

public class ToolCreator {
    public static Tool createTool(Map<String, String> toolParameters) {
        Tool tool = new Tool();
        tool.setType(Tool.Type.valueOf(toolParameters.get(ParameterName.TOOL_TYPE).toUpperCase()));
        tool.setModel(toolParameters.get(ParameterName.TOOL_MODEL));
        tool.setDescriptionEng(toolParameters.get(ParameterName.TOOL_DESCRIPTION_ENG));
        tool.setDescriptionRus(toolParameters.get(ParameterName.TOOL_DESCRIPTION_RUS));
        tool.setRentPrice(BigDecimal.valueOf(Double.parseDouble(toolParameters.get(ParameterName.TOOL_RENT_PRICE))));
        return tool;
    }

    public static Tool createTool(Map<String, String> toolEditParameters, boolean isAvailable, long id) {
        Tool tool = new Tool();
        tool.setDescriptionEng(toolEditParameters.get(ParameterName.TOOL_DESCRIPTION_ENG));
        tool.setDescriptionRus(toolEditParameters.get(ParameterName.TOOL_DESCRIPTION_RUS));
        tool.setRentPrice(BigDecimal.valueOf(Double.parseDouble(toolEditParameters.get(ParameterName.TOOL_RENT_PRICE))));
        tool.setToolId(id);
        tool.setAvailable(isAvailable);
        return tool;
    }

    private ToolCreator() {
    }
}
