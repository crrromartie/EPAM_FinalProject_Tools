package by.gaponenko.tools.controller.command;

import static by.gaponenko.tools.controller.command.CommandProvider.CommandName.*;

import java.util.EnumSet;
import java.util.Set;

public enum AccessByRole {
    GUEST(EnumSet.of(NO_SUCH_COMMAND,
            CHANGE_LOCALE,
            PAGINATION,
            LOGIN,
            REGISTRATION,
            CONFIRM_REGISTRATION,
            FILTER_TOOLS,
            SORT_TOOLS,
            NOTIFICATION_PASS,
            HOME_PASS,
            LOGIN_PASS,
            REGISTRATION_PASS,
            TOOLS_PASS,
            TOOLS_PASS_ONLY,
            CHANGE_TOOLS_DISPLAY,
            TOOL_PASS
    )),

    CLIENT(EnumSet.of(NO_SUCH_COMMAND,
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

    ADMIN(EnumSet.of(NO_SUCH_COMMAND,
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
            ORDERS_ADMIN_PASS,
            ORDERS_PASS_ONLY,
            PROFILE_PASS,
            CHANGE_TOOLS_DISPLAY,
            CLIENTS_PASS,
            ORDER_APPROVE,
            ORDER_REJECT,
            CLIENTS_PASS,
            CLIENTS_PASS_ONLY,
            CLIENT_BLOCK,
            CLIENT_UNBLOCK,
            ADD_TOOL_PASS,
            ADD_TOOL,
            FILTER_ORDERS,
            FILTER_CLIENTS,
            TOOL_EDIT_PASS,
            TOOL_PHOTO_PASS,
            EDIT_TOOL,
            UPDATE_TOOL_PHOTO
    ));

    private Set<CommandProvider.CommandName> allowedCommands;

    AccessByRole(Set<CommandProvider.CommandName> allowedCommands) {
        this.allowedCommands = allowedCommands;
    }

    public Set<CommandProvider.CommandName> getAllowedCommands() {
        return allowedCommands;
    }
}
