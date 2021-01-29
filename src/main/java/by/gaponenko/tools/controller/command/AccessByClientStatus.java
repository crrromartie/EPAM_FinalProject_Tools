package by.gaponenko.tools.controller.command;

import java.util.EnumSet;
import java.util.Set;

import static by.gaponenko.tools.controller.command.CommandType.*;

public enum AccessByClientStatus {
    ACTIVE(EnumSet.of(CHANGE_LOCALE,
            PAGINATION,
            LOGOUT,
            FILTER_TOOLS,
            SORT_TOOLS,
            CHANGE_TOOLS_DISPLAY,
            FILTER_ORDERS,
            CALCULATE_ORDER_TOTAL,
            MAKE_ORDER,
            CANCEL_ORDER,
            MAKE_PAYMENT,
            EDIT_PROFILE,
            UPDATE_AVATAR,
            UPDATE_PASSWORD,
            HOME_PASS,
            TOOLS_PASS,
            TOOLS_PASS_ONLY,
            TOOL_PASS,
            TOOL_PASS_ONLY,
            ORDERS_CLIENT_PASS,
            ORDERS_PASS_ONLY,
            PAYMENT_PASS,
            PROFILE_PASS,
            AVATAR_PASS,
            PASSWORD_PASS,
            NOTIFICATION_PASS
    )),

    UNCONFIRMED(EnumSet.of(CHANGE_LOCALE,
            PAGINATION,
            LOGOUT,
            CONFIRM_REGISTRATION,
            FILTER_TOOLS,
            SORT_TOOLS,
            CHANGE_TOOLS_DISPLAY,
            HOME_PASS,
            TOOLS_PASS,
            TOOLS_PASS_ONLY,
            NOTIFICATION_PASS
    )),

    BLOCKED(EnumSet.of(CHANGE_LOCALE,
            PAGINATION,
            LOGOUT,
            FILTER_TOOLS,
            SORT_TOOLS,
            CHANGE_TOOLS_DISPLAY,
            HOME_PASS,
            TOOLS_PASS,
            TOOLS_PASS_ONLY,
            NOTIFICATION_PASS
    ));

    private final Set<CommandType> allowedCommands;

    AccessByClientStatus(Set<CommandType> allowedCommands) {
        this.allowedCommands = allowedCommands;
    }

    public Set<CommandType> getAllowedCommands() {
        return allowedCommands;
    }
}
