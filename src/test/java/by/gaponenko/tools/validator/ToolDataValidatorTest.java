package by.gaponenko.tools.validator;

import by.gaponenko.tools.controller.command.ParameterName;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class ToolDataValidatorTest {

    @Test
    public void isValidToolParametersPositiveTest() {
        Map<String, String> toolParameters = new HashMap<>();
        toolParameters.put(ParameterName.TOOL_TYPE, "WELDER");
        toolParameters.put(ParameterName.TOOL_MODEL, "BOSH");
        toolParameters.put(ParameterName.TOOL_DESCRIPTION_ENG, "Description");
        toolParameters.put(ParameterName.TOOL_DESCRIPTION_RUS, "Описание");
        toolParameters.put(ParameterName.TOOL_RENT_PRICE, "20");
        Assert.assertTrue(ToolDataValidator.isValidToolParameters(toolParameters));
    }

    @Test
    public void isValidToolParametersNegativeTest() {
        Map<String, String> toolParameters = new HashMap<>();
        toolParameters.put(ParameterName.TOOL_TYPE, "WELDER");
        toolParameters.put(ParameterName.TOOL_MODEL, "BOSH");
        toolParameters.put(ParameterName.TOOL_DESCRIPTION_ENG, "Description");
        toolParameters.put(ParameterName.TOOL_DESCRIPTION_RUS, "Описание");
        toolParameters.put(ParameterName.TOOL_RENT_PRICE, "-5");
        Assert.assertFalse(ToolDataValidator.isValidToolParameters(toolParameters));
    }

    @Test
    public void isValidEditToolParametersPositiveTest() {
        Map<String, String> editToolParameters = new HashMap<>();
        editToolParameters.put(ParameterName.TOOL_DESCRIPTION_ENG, "Description");
        editToolParameters.put(ParameterName.TOOL_DESCRIPTION_RUS, "Описание");
        editToolParameters.put(ParameterName.TOOL_RENT_PRICE, "20");
        Assert.assertTrue(ToolDataValidator.isValidEditToolParameters(editToolParameters));
    }

    @Test
    public void isValidEditToolParametersNegativeTest() {
        Map<String, String> editToolParameters = new HashMap<>();
        editToolParameters.put(ParameterName.TOOL_DESCRIPTION_ENG, "Description");
        editToolParameters.put(ParameterName.TOOL_DESCRIPTION_RUS, "Описание");
        editToolParameters.put(ParameterName.TOOL_RENT_PRICE, "-5");
        Assert.assertFalse(ToolDataValidator.isValidEditToolParameters(editToolParameters));
    }

    @DataProvider(name = "toolTypeData")
    public Object[][] createToolTypeData() {
        return new Object[][]{
                {"welder", true},
                {"Drill", true},
                {"PERFORATOR", true},
                {"hummer", false},
                {null, false},
                {"", false},
        };
    }

    @Test(dataProvider = "toolTypeData")
    public void isValidToolTypeTest(String toolType, boolean expected) {
        boolean actual = ToolDataValidator.isValidToolType(toolType);
        Assert.assertEquals(actual, expected);
    }

    @DataProvider(name = "modelData")
    public Object[][] createModelData() {
        return new Object[][]{
                {"Bosh", true},
                {"Stihl vf-50", true},
                {"Фиолент 500", true},
                {"Фиоленттттттттттттттттттттттттттттттттттттттттттттттттттттттттттттттттттттттттттттттттттттт", false},
                {null, false},
                {"", false},
        };
    }

    @Test(dataProvider = "modelData")
    public void isValidModelTest(String model, boolean expected) {
        boolean actual = ToolDataValidator.isValidModel(model);
        Assert.assertEquals(actual, expected);
    }

    @DataProvider(name = "descriptionEngData")
    public Object[][] createDescriptionEngData() {
        return new Object[][]{
                {"Description for tool", true},
                {"Description", true},
                {"Descriptionnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn" +
                        "nnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn" +
                        "nnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn" +
                        "nnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn" +
                        "nnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn" +
                        "nnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn", false},
                {null, false},
                {"", false},
        };
    }

    @Test(dataProvider = "descriptionEngData")
    public void isValidDescriptionEngTest(String descriptionEng, boolean expected) {
        boolean actual = ToolDataValidator.isValidDescriptionEng(descriptionEng);
        Assert.assertEquals(actual, expected);
    }

    @DataProvider(name = "descriptionRusData")
    public Object[][] createDescriptionRusData() {
        return new Object[][]{
                {"Описание для инструмента", true},
                {"Описание", true},
                {"Описаниеееееееееееееееееееееееееееееееееееееееееееееееееееееееееееееееееееееееееееееееееееее" +
                        "еееееееееееееееееееееееееееееееееееееееееееееееееееееееееееееееееееееееееееееееееееее" +
                        "еееееееееееееееееееееееееееееееееееееееееееееееееееееееееееееееееееееееееееееееееееее" +
                        "еееееееееееееееееееееееееееееееееееееееееееееееееееееееееееееееееееееееееееееееееееее" +
                        "еееееееееееееееееееееееееееееееееееееееееееееееееееееееееееееееееееееееееееееееееееее" +
                        "еееееееееееееееееееееееееееееееееееееееееееееееееееееееееееееееееееееееееееееееееееее", false},
                {null, false},
                {"", false},
        };
    }

    @Test(dataProvider = "descriptionRusData")
    public void isValidDescriptionRusTest(String descriptionRus, boolean expected) {
        boolean actual = ToolDataValidator.isValidDescriptionRus(descriptionRus);
        Assert.assertEquals(actual, expected);
    }

    @DataProvider(name = "rentPriceData")
    public Object[][] createRentPriceData() {
        return new Object[][]{
                {"15", true},
                {"55", true},
                {"-1", false},
                {"1050", false},
                {null, false},
                {"", false},
        };
    }

    @Test(dataProvider = "rentPriceData")
    public void isValidRentPriceTest(String rentPrice, boolean expected) {
        boolean actual = ToolDataValidator.isValidRentPrice(rentPrice);
        Assert.assertEquals(actual, expected);
    }
}
