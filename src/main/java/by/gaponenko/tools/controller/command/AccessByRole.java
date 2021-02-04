package by.gaponenko.tools.controller.command;

import java.util.EnumSet;
import java.util.Set;

import static by.gaponenko.tools.controller.command.CommandType.*;

/**
 * The enum Access by role.
 * <p>
 * This enum is described to differentiate the user's access levels to the elements
 * of the application, depending on its roles. Today the user has one of the
 * roles: GUEST, ADMIN, CLIENT ({@code User.Role}). Depending on the role,
 * the web filter {@code RolePermissionFilter} can pass requests to execute a particular
 * command {@code CommandType} or block access.
 *
 * @author Haponenka Ihar
 * @version 1.0
 */
public enum AccessByRole {
    /**
     * Available commands for user role GUEST.
     */
    GUEST(EnumSet.of(CHANGE_LOCALE,
            PAGINATION,
            LOGIN,
            REGISTRATION,
            CONFIRM_REGISTRATION,
            FILTER_TOOLS,
            SORT_TOOLS,
            HOME_PASS,
            LOGIN_PASS,
            REGISTRATION_PASS,
            TOOLS_PASS,
            TOOLS_PASS_ONLY,
            NOTIFICATION_PASS
    )),

    /**
     * Available commands for user role CLIENT.
     */
    CLIENT(EnumSet.of(CHANGE_LOCALE,
            PAGINATION,
            LOGOUT,
            FILTER_TOOLS,
            SORT_TOOLS,
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

    /**
     * Available commands for user role ADMIN.
     */
    ADMIN(EnumSet.of(CHANGE_LOCALE,
            PAGINATION,
            LOGOUT,
            FILTER_TOOLS,
            SORT_TOOLS,
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

    /**
     * Returns the set of available commands according to the corresponding role value.
     *
     * @return the set
     */
    public Set<CommandType> getAllowedCommands() {
        return allowedCommands;
    }
}
