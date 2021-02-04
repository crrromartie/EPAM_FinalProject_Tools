package by.gaponenko.tools.util.comparator;

import by.gaponenko.tools.entity.Tool;

import java.util.Comparator;

/**
 * Tool comparator.
 *
 * @author Haponenka Ihar
 * @version 1.0
 */
public enum ToolComparator {
    PRICE_UP((o1, o2) -> o1.getRentPrice().compareTo(o2.getRentPrice())),
    PRICE_DOWN((o1, o2) -> o2.getRentPrice().compareTo(o1.getRentPrice()));

    private Comparator<Tool> comparator;

    ToolComparator(Comparator<Tool> comparator) {
        this.comparator = comparator;
    }

    public Comparator<Tool> getComparator() {
        return comparator;
    }
}
