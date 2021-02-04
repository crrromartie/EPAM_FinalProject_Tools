package by.gaponenko.tools.controller.command;

/**
 * The Command path.
 * <p>
 * The class is used to implement the pattern POST-REDIRECT-GET,
 * using to protect against f5.
 *
 * @author Haponenka Ihar
 * @version 1.0
 */
public class CommandPath {
    //CalculateOrderTotalCommand
    public static final String TOOL_PASS_ONLY = "/ToolRental?command=tool_pass_only";
    //PaginationCommand
    public static final String CLIENTS_PASS_ONLY = "/ToolRental?command=clients_pass_only";
    public static final String TOOLS_PASS_ONLY = "/ToolRental?command=tools_pass_only";
    public static final String ORDERS_PASS_ONLY = "/ToolRental?command=orders_pass_only";
    //UpdateAvatarCommand
    public static final String USER_AVATAR_PASS = "/ToolRental?command=avatar_pass";
    //UpdateToolPhotoCommand
    public static final String TOOL_PHOTO_PASS_WITH_ID = "/ToolRental?command=tool_photo_pass&toolId=";
    //LoginCommand
    public static final String HOME_PASS = "/ToolRental?command=home_pass";
    //ConfirmRegistrationCommand
    public static final String NOTIFICATION_CONFIRMED_SUCCESS = "/ToolRental?command=notification_pass&confirmed=true";
    public static final String NOTIFICATION_CONFIRMED_FAIL = "/ToolRental?command=notification_pass&confirmed=false";
    //MakeOrderCommand
    public static final String NOTIFICATION_ORDER_CREATED_SUCCESS = "/ToolRental?command=notification_pass&orderCreated=true";
    public static final String NOTIFICATION_ORDER_CREATED_FAIL = "/ToolRental?command=notification_pass&orderCreated=false";
    //UpdatePasswordCommand
    public static final String NOTIFICATION_PASSWORD_UPDATED = "/ToolRental?command=notification_pass&passwordUpdated=true";
    //RegistrationCommand
    public static final String NOTIFICATION_REGISTERED = "/ToolRental?command=notification_pass&registered=true";
    //MakePaymentCommand
    public static final String NOTIFICATION_ORDER_PAYED = "/ToolRental?command=notification_pass&orderPayed=true";
    //EditProfileCommand
    public static final String NOTIFICATION_USER_UPDATED = "/ToolRental?command=notification_pass&userInfoUpdated=true";
    //EditToolCommand
    public static final String NOTIFICATION_TOOL_UPDATED = "/ToolRental?command=notification_pass&toolUpdated=true";
    //AddToolCommand
    public static final String NOTIFICATION_TOOL_ADDED = "/ToolRental?command=notification_pass&toolAdded=true";

    private CommandPath() {
    }
}
