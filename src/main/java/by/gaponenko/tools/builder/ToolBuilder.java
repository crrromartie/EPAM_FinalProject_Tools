package by.gaponenko.tools.builder;

import by.gaponenko.tools.entity.Tool;

import java.math.BigDecimal;

/**
 * The class represents tool builder.
 *
 * @author Haponenka Ihar
 * @version 1.0
 */
public class ToolBuilder {
    private long toolId;
    private Tool.Type type;
    private String model;
    private String descriptionEng;
    private String descriptionRus;
    private boolean isAvailable;
    private BigDecimal rentPrice;
    private String photo;

    public long getToolId() {
        return toolId;
    }

    public ToolBuilder setToolId(long toolId) {
        this.toolId = toolId;
        return this;
    }

    public Tool.Type getType() {
        return type;
    }

    public ToolBuilder setType(Tool.Type type) {
        this.type = type;
        return this;
    }

    public String getModel() {
        return model;
    }

    public ToolBuilder setModel(String model) {
        this.model = model;
        return this;
    }

    public String getDescriptionEng() {
        return descriptionEng;
    }

    public ToolBuilder setDescriptionEng(String descriptionEng) {
        this.descriptionEng = descriptionEng;
        return this;
    }

    public String getDescriptionRus() {
        return descriptionRus;
    }

    public ToolBuilder setDescriptionRus(String descriptionRus) {
        this.descriptionRus = descriptionRus;
        return this;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public ToolBuilder setAvailable(boolean available) {
        isAvailable = available;
        return this;
    }

    public BigDecimal getRentPrice() {
        return rentPrice;
    }

    public ToolBuilder setRentPrice(BigDecimal rentPrice) {
        this.rentPrice = rentPrice;
        return this;
    }

    public String getPhoto() {
        return photo;
    }

    public ToolBuilder setPhoto(String photo) {
        this.photo = photo;
        return this;
    }
}
