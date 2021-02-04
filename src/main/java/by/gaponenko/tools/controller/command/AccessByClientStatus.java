package by.gaponenko.tools.controller.command;

import java.util.EnumSet;
import java.util.Set;

import static by.gaponenko.tools.controller.command.CommandType.*;

/**
 * The enum Access by client status.
 * <p>
 * This enum is described to differentiate the client's access levels to the elements
 * of the application, depending on its status. Today the client has one of the
 * statuses: UNCONFIRMED, ACTIVE, BLOCKED ({@code User.Status}). Depending on the status,
 * the web filter {@code ClientStatusPermissionFilter} can pass requests to execute a particular
 * command {@code CommandType} or block access.
 *
 * @author Haponenka Ihar
 * @version 1.0
 */
public enum AccessByClientStatus {
    /**
     * Available commands for client status ACTIVE.
     */
    ACTIVE(EnumSet.of(CHANGE_LOCALE,
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
            PAYMENT_PASS,
            PROFILE_PASS,
            AVATAR_PASS,
            PASSWORD_PASS,
            NOTIFICATION_PASS
    )),

    /**
     * Available commands for client status UNCONFIRMED.
     */
    UNCONFIRMED(EnumSet.of(CHANGE_LOCALE,
            PAGINATION,
            LOGOUT,
            CONFIRM_REGISTRATION,
            FILTER_TOOLS,
            SORT_TOOLS,
            HOME_PASS,
            TOOLS_PASS,
            TOOLS_PASS_ONLY,
            NOTIFICATION_PASS
    )),

    /**
     * Available commands for client status BLOCKED.
     */
    BLOCKED(EnumSet.of(CHANGE_LOCALE,
            PAGINATION,
            LOGOUT,
            FILTER_TOOLS,
            SORT_TOOLS,
            HOME_PASS,
            TOOLS_PASS,
            TOOLS_PASS_ONLY,
            NOTIFICATION_PASS
    ));

    private final Set<CommandType> allowedCommands;

    AccessByClientStatus(Set<CommandType> allowedCommands) {
        this.allowedCommands = allowedCommands;
    }

    /**
     * Returns the set of available commands according to the corresponding status value.
     *
     * @return the set
     */
    public Set<CommandType> getAllowedCommands() {
        return allowedCommands;
    }
}
