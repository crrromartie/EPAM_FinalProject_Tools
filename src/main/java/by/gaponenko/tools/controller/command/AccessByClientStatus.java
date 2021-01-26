package by.gaponenko.tools.controller.command;

import static by.gaponenko.tools.controller.command.CommandProvider.CommandName.*;

import java.util.EnumSet;
import java.util.Set;

public enum AccessByClientStatus {
    ACTIVE(EnumSet.of(NO_SUCH_COMMAND,
            CHANGE_LOCALE,
            PAGINATION,
            LOGOUT,
            FILTER_TOOLS,
            SORT_TOOLS,
            NOTIFICATION_PASS,
            HOME_PASS,
            TOOLS_PASS,
            TOOLS_PASS_ONLY,
            TOOL_PASS,
            ORDERS_CLIENT_PASS,
            ORDERS_PASS_ONLY,
            PROFILE_PASS,
            CHANGE_TOOLS_DISPLAY,
            CALCULATE_TOTAL,
            MAKE_ORDER,
            ORDER_CANCEL,
            PAYMENT_PASS,
            MAKE_PAYMENT,
            FILTER_ORDERS
    )),

    UNCONFIRMED(EnumSet.of(
            NO_SUCH_COMMAND,
            CHANGE_LOCALE,
            PAGINATION,
            LOGOUT,
            CONFIRM_REGISTRATION,
            FILTER_TOOLS,
            SORT_TOOLS,
            NOTIFICATION_PASS,
            HOME_PASS,
            TOOLS_PASS,
            TOOLS_PASS_ONLY,
            CHANGE_TOOLS_DISPLAY
    )),

    BLOCKED(EnumSet.of(NO_SUCH_COMMAND,
            CHANGE_LOCALE,
            PAGINATION,
            LOGOUT,
            FILTER_TOOLS,
            SORT_TOOLS,
            NOTIFICATION_PASS,
            HOME_PASS,
            TOOLS_PASS,
            TOOLS_PASS_ONLY,
            CHANGE_TOOLS_DISPLAY
    ));

    private Set<CommandProvider.CommandName> allowedCommands;

    AccessByClientStatus(Set<CommandProvider.CommandName> allowedCommands) {
        this.allowedCommands = allowedCommands;
    }

    public Set<CommandProvider.CommandName> getAllowedCommands() {
        return allowedCommands;
    }
}
