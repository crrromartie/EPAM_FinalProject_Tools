package by.gaponenko.tools.controller.command;

import by.gaponenko.tools.controller.command.impl.*;
import by.gaponenko.tools.controller.command.impl.navigation.*;

import java.util.EnumMap;

public enum CommandProvider {
    PROVIDER;

    public enum CommandName {
        NO_SUCH_COMMAND, CHANGE_LOCALE, PAGINATION, LOGIN, LOGOUT, REGISTRATION, CONFIRM_REGISTRATION,
        FILTER_TOOLS, SORT_TOOLS, CHANGE_TOOLS_DISPLAY, CALCULATE_TOTAL, MAKE_ORDER, ORDER_APPROVE,
        ORDER_REJECT, ORDER_CANCEL, MAKE_PAYMENT, CLIENT_BLOCK, CLIENT_UNBLOCK, ADD_TOOL, FILTER_ORDERS,
        FILTER_CLIENTS, EDIT_TOOL, UPDATE_TOOL_PHOTO,
        NOTIFICATION_PASS, HOME_PASS, LOGIN_PASS, REGISTRATION_PASS, TOOLS_PASS, TOOLS_PASS_ONLY, TOOL_PASS,
        ORDERS_ADMIN_PASS, ORDERS_CLIENT_PASS, ORDERS_PASS_ONLY, PROFILE_PASS, CLIENTS_PASS, CLIENTS_PASS_ONLY,
        PAYMENT_PASS, ADD_TOOL_PASS, TOOL_EDIT_PASS, TOOL_PHOTO_PASS
    }

    private final EnumMap<CommandName, Command> commands = new EnumMap<>(CommandName.class);

    CommandProvider() {
        commands.put(CommandName.NO_SUCH_COMMAND, new NoSuchCommand());
        commands.put(CommandName.CHANGE_LOCALE, new ChangeLocaleCommand());
        commands.put(CommandName.PAGINATION, new PaginationCommand());
        commands.put(CommandName.LOGIN, new LoginCommand());
        commands.put(CommandName.LOGOUT, new LogoutCommand());
        commands.put(CommandName.REGISTRATION, new RegistrationCommand());
        commands.put(CommandName.CONFIRM_REGISTRATION, new ConfirmRegistrationCommand());
        commands.put(CommandName.FILTER_TOOLS, new FilterToolsCommand());
        commands.put(CommandName.SORT_TOOLS, new SortToolsCommand());
        commands.put(CommandName.CHANGE_TOOLS_DISPLAY, new ChangeToolsDisplay());
        commands.put(CommandName.CALCULATE_TOTAL, new CalculateTotalCommand());
        commands.put(CommandName.MAKE_ORDER, new MakeOrderCommand());
        commands.put(CommandName.ORDER_APPROVE, new OrderApproveCommand());
        commands.put(CommandName.ORDER_REJECT, new OrderRejectCommand());
        commands.put(CommandName.ORDER_CANCEL, new OrderCancelCommand());
        commands.put(CommandName.MAKE_PAYMENT, new PaymentCommand());
        commands.put(CommandName.CLIENT_BLOCK, new ClientBlockCommand());
        commands.put(CommandName.CLIENT_UNBLOCK, new ClientUnblockCommand());
        commands.put(CommandName.ADD_TOOL, new AddToolCommand());
        commands.put(CommandName.FILTER_ORDERS, new FilterOrdersCommand());
        commands.put(CommandName.FILTER_CLIENTS, new FilterClientsCommand());
        commands.put(CommandName.EDIT_TOOL, new EditToolCommand());
        commands.put(CommandName.UPDATE_TOOL_PHOTO, new UpdateToolPhoto());
        commands.put(CommandName.NOTIFICATION_PASS, new NotificationPassCommand());
        commands.put(CommandName.HOME_PASS, new HomePassCommand());
        commands.put(CommandName.LOGIN_PASS, new LoginPassCommand());
        commands.put(CommandName.REGISTRATION_PASS, new RegistrationPassCommand());
        commands.put(CommandName.TOOLS_PASS, new ToolsPassCommand());
        commands.put(CommandName.TOOLS_PASS_ONLY, new ToolsPassOnlyCommand());
        commands.put(CommandName.TOOL_PASS, new ToolPassCommand());
        commands.put(CommandName.ORDERS_ADMIN_PASS, new OrdersPassAdminCommand());
        commands.put(CommandName.ORDERS_CLIENT_PASS, new OrdersPassClientCommand());
        commands.put(CommandName.ORDERS_PASS_ONLY, new OrdersPassOnlyCommand());
        commands.put(CommandName.PROFILE_PASS, new ProfilePassCommand());
        commands.put(CommandName.CLIENTS_PASS, new ClientsPassCommand());
        commands.put(CommandName.CLIENTS_PASS_ONLY, new ClientsPassOnlyCommand());
        commands.put(CommandName.PAYMENT_PASS, new PaymentPassCommand());
        commands.put(CommandName.ADD_TOOL_PASS, new AddToolPassCommand());
        commands.put(CommandName.TOOL_EDIT_PASS, new ToolEditPassCommand());
        commands.put(CommandName.TOOL_PHOTO_PASS, new ToolPhotoPassCommand());
    }

    public Command takeCommand(String command) {
        if (command == null || command.isBlank()) {
            return commands.get(CommandName.NO_SUCH_COMMAND);
        }
        Command value;
        try {
            CommandName commandName = CommandName.valueOf(command.toUpperCase());
            value = commands.get(commandName);
        } catch (IllegalArgumentException e) {
            value = commands.get(CommandName.NO_SUCH_COMMAND);
        }
        return value;
    }
}
