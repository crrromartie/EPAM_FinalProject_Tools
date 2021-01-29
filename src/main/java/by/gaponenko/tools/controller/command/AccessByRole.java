package by.gaponenko.tools.controller.command;

import java.util.EnumSet;
import java.util.Set;

import static by.gaponenko.tools.controller.command.CommandType.*;

public enum AccessByRole {
    GUEST(EnumSet.of(CHANGE_LOCALE,
            PAGINATION,
            LOGIN,
            REGISTRATION,
            CONFIRM_REGISTRATION,
            FILTER_TOOLS,
            SORT_TOOLS,
            CHANGE_TOOLS_DISPLAY,
            HOME_PASS,
            LOGIN_PASS,
            REGISTRATION_PASS,
            TOOLS_PASS,
            TOOLS_PASS_ONLY,
            NOTIFICATION_PASS
    )),

    CLIENT(EnumSet.of(CHANGE_LOCALE,
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
            PROFILE_PASS,
            AVATAR_PASS,
            PASSWORD_PASS,
            PAYMENT_PASS,
            NOTIFICATION_PASS
    )),

    ADMIN(EnumSet.of(CHANGE_LOCALE,
            PAGINATION,
            LOGOUT,
            FILTER_TOOLS,
            SORT_TOOLS,
            CHANGE_TOOLS_DISPLAY,
            ADD_TOOL,
            EDIT_TOOL,
            UPDATE_TOOL_PHOTO,
            EDIT_PROFILE,
            UPDATE_AVATAR,
            UPDATE_PASSWORD,
            FILTER_ORDERS,
            APPROVE_ORDER,
            REJECT_ORDER,
            FILTER_CLIENTS,
            BLOCK_CLIENT,
            UNBLOCK_CLIENT,
            HOME_PASS,
            TOOLS_PASS,
            TOOLS_PASS_ONLY,
            TOOL_PASS,
            TOOL_EDIT_PASS,
            TOOL_ADD_PASS,
            TOOL_PHOTO_PASS,
            ORDERS_ADMIN_PASS,
            ORDERS_PASS_ONLY,
            PROFILE_PASS,
            AVATAR_PASS,
            PASSWORD_PASS,
            CLIENTS_PASS,
            CLIENTS_PASS_ONLY,
            NOTIFICATION_PASS
    ));

    private final Set<CommandType> allowedCommands;

    AccessByRole(Set<CommandType> allowedCommands) {
        this.allowedCommands = allowedCommands;
    }

    public Set<CommandType> getAllowedCommands() {
        return allowedCommands;
    }
}
