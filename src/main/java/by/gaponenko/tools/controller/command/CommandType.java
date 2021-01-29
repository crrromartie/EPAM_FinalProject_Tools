package by.gaponenko.tools.controller.command;

import by.gaponenko.tools.controller.command.impl.*;
import by.gaponenko.tools.controller.command.impl.navigation.*;

public enum CommandType {
    NO_SUCH_COMMAND(new NoSuchCommand()),
    CHANGE_LOCALE(new ChangeLocaleCommand()),
    PAGINATION(new PaginationCommand()),
    LOGIN(new LoginCommand()),
    LOGOUT(new LogoutCommand()),
    REGISTRATION(new RegistrationCommand()),
    CONFIRM_REGISTRATION(new ConfirmRegistrationCommand()),
    FILTER_TOOLS(new FilterToolsCommand()),
    SORT_TOOLS(new SortToolsCommand()),
    CHANGE_TOOLS_DISPLAY(new ChangeToolsDisplayCommand()),
    ADD_TOOL(new AddToolCommand()),
    EDIT_TOOL(new EditToolCommand()),
    UPDATE_TOOL_PHOTO(new UpdateToolPhotoCommand()),
    FILTER_ORDERS(new FilterOrdersCommand()),
    APPROVE_ORDER(new ApproveOrderCommand()),
    REJECT_ORDER(new RejectOrderCommand()),
    CANCEL_ORDER(new CancelOrderCommand()),
    CALCULATE_ORDER_TOTAL(new CalculateOrderTotalCommand()),
    MAKE_ORDER(new MakeOrderCommand()),
    MAKE_PAYMENT(new MakePaymentCommand()),
    FILTER_CLIENTS(new FilterClientsCommand()),
    BLOCK_CLIENT(new BlockClientCommand()),
    UNBLOCK_CLIENT(new UnblockClientCommand()),
    EDIT_PROFILE(new EditProfileCommand()),
    UPDATE_AVATAR(new UpdateAvatarCommand()),
    UPDATE_PASSWORD(new UpdatePasswordCommand()),
    HOME_PASS(new HomePassCommand()),
    LOGIN_PASS(new LoginPassCommand()),
    REGISTRATION_PASS(new RegistrationPassCommand()),
    TOOLS_PASS(new ToolsPassCommand()),
    TOOLS_PASS_ONLY(new ToolsPassOnlyCommand()),
    TOOL_PASS(new ToolPassCommand()),
    TOOL_PASS_ONLY(new ToolPassOnlyCommand()),
    TOOL_ADD_PASS(new ToolAddPassCommand()),
    TOOL_EDIT_PASS(new ToolEditPassCommand()),
    TOOL_PHOTO_PASS(new ToolPhotoPassCommand()),
    ORDERS_ADMIN_PASS(new OrdersAdminPassCommand()),
    ORDERS_CLIENT_PASS(new OrdersClientPassCommand()),
    ORDERS_PASS_ONLY(new OrdersPassOnlyCommand()),
    PAYMENT_PASS(new PaymentPassCommand()),
    PROFILE_PASS(new ProfilePassCommand()),
    AVATAR_PASS(new AvatarPassCommand()),
    PASSWORD_PASS(new PasswordPassCommand()),
    CLIENTS_PASS(new ClientsPassCommand()),
    CLIENTS_PASS_ONLY(new ClientsPassOnlyCommand()),
    NOTIFICATION_PASS(new NotificationPassCommand());

    private final Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
