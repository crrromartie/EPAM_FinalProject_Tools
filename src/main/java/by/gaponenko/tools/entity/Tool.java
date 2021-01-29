package by.gaponenko.tools.entity;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Tool extends Entity {

    public enum Type {
        CHAINSAW(1),
        CONCRETE_SAW(2),
        JIGSAW(3),
        PETROL_CUTTER(4),
        TILE_CUTTER(5),
        BREAKER_HAMMER(6),
        SCREW(7),
        DRILL(8),
        PERFORATOR(9),
        WELDER(10);

        private final int toolTypeId;

        Type(int toolTypeId) {
            this.toolTypeId = toolTypeId;
        }

        public int getToolTypeId() {
            return toolTypeId;
        }

        private static final Map<Integer, Tool.Type> TOOL_TYPE_MAP = new HashMap<>();

        static {
            for (Tool.Type type : values()) {
                TOOL_TYPE_MAP.put(type.getToolTypeId(), type);
            }
        }

        public static Tool.Type getToolTypeById(int toolTypeId) {
            return TOOL_TYPE_MAP.get(toolTypeId);
        }
    }

    private long toolId;
    private Type type;
    private String model;
    private String descriptionEng;
    private String descriptionRus;
    private boolean isAvailable;
    private BigDecimal rentPrice;
    private String photo;

    public long getToolId() {
        return toolId;
    }

    public void setToolId(long toolId) {
        this.toolId = toolId;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getDescriptionEng() {
        return descriptionEng;
    }

    public void setDescriptionEng(String descriptionEng) {
        this.descriptionEng = descriptionEng;
    }

    public String getDescriptionRus() {
        return descriptionRus;
    }

    public void setDescriptionRus(String descriptionRus) {
        this.descriptionRus = descriptionRus;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public BigDecimal getRentPrice() {
        return rentPrice;
    }

    public void setRentPrice(BigDecimal rentPrice) {
        this.rentPrice = rentPrice;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Tool tool = (Tool) o;
        if (toolId != tool.toolId) {
            return false;
        }
        if (isAvailable != tool.isAvailable) {
            return false;
        }
        if (type != tool.type) {
            return false;
        }
        if (model != null ? !model.equals(tool.model) : tool.model != null) {
            return false;
        }
        if (descriptionEng != null ? !descriptionEng.equals(tool.descriptionEng) : tool.descriptionEng != null) {
            return false;
        }
        if (descriptionRus != null ? !descriptionRus.equals(tool.descriptionRus) : tool.descriptionRus != null) {
            return false;
        }
        if (rentPrice != null ? !rentPrice.equals(tool.rentPrice) : tool.rentPrice != null) {
            return false;
        }
        return photo != null ? photo.equals(tool.photo) : tool.photo == null;
    }

    @Override
    public int hashCode() {
        int result = Long.hashCode(toolId);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (model != null ? model.hashCode() : 0);
        result = 31 * result + (descriptionEng != null ? descriptionEng.hashCode() : 0);
        result = 31 * result + (descriptionRus != null ? descriptionRus.hashCode() : 0);
        result = 31 * result + (isAvailable ? 1 : 0);
        result = 31 * result + (rentPrice != null ? rentPrice.hashCode() : 0);
        result = 31 * result + (photo != null ? photo.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Tool.class.getSimpleName() + "[", "]")
                .add("toolId=" + toolId)
                .add("type=" + type)
                .add("model=" + model)
                .add("descriptionEng=" + descriptionEng)
                .add("descriptionRus=" + descriptionRus)
                .add("isAvailable=" + isAvailable)
                .add("rentPrice=" + rentPrice)
                .toString();
    }
}
