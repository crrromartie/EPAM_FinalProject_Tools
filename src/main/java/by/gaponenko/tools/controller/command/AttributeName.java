package by.gaponenko.tools.controller.command;

public class AttributeName {
    //general
    public static final String CURRENT_PAGE = "currentPage";
    public static final String LANG = "lang";
    //user
    public static final String USER = "user";
    public static final String USERS = "users";
    public static final String LOGIN = "login";
    public static final String PASSWORD = "password";
    public static final String ROLE = "role";
    public static final String REGISTRATION_PARAMETERS = "registrationParameters";
    public static final String USERS_FILTER_STATUS = "usersStatus";
    //tool
    public static final String TOOL = "tool";
    public static final String TOOLS = "tools";
    public static final String TOOLS_DISPLAY = "toolsDisplay";
    public static final String TOOL_PARAMETERS = "toolParameters";
    //order
    public static final String ORDER = "order";
    public static final String ORDERS = "orders";
    public static final String ORDER_DATE = "order_date";
    public static final String ORDER_RETURN_DATE = "return_date";
    public static final String ORDER_TOTAL_COST = "totalCost";
    public static final String ORDERS_FILTER_STATUS = "ordersFilterStatus";
    public static final String PAYMENT_PARAMETERS = "paymentParameters";
    //pagination
    public static final String PAGE_ENTRIES = "pageEntries";
    public static final String USERS_PAGE_NUMBER = "usersPageNumber";
    public static final String TOOLS_PAGE_NUMBER = "toolsPageNumber";
    public static final String ORDERS_PAGE_NUMBER = "ordersPageNumber";
    public static final String PAGINATION_USERS = "pagination_users";
    public static final String PAGINATION_TOOLS = "pagination_tools";
    public static final String PAGINATION_ORDERS = "pagination_orders";
    //notification
    public static final String REGISTRATION_SUCCESS = "registrationSuccess";
    public static final String CONFIRMATION_SUCCESS = "confirmationSuccess";
    public static final String MAKE_ORDER_SUCCESS = "makeOrderSuccess";
    public static final String PAYED_ORDER_SUCCESS = "payedOrderSuccess";
    public static final String ADD_TOOL_SUCCESS = "addToolSuccess";
    public static final String UPDATE_TOOL_SUCCESS = "updateToolSuccess";
    public static final String UPDATE_USER_INFO_SUCCESS = "updateUserInfoSuccess";
    public static final String UPDATE_PASSWORD_SUCCESS = "updatePasswordSuccess";
    public static final String CONFIRM_EMAIL = "confirmEmail";
    public static final String ACCESS_DENIED = "accessDenied";
    public static final String ACCOUNT_BLOCKED = "accountBlocked";
    //message-error
    public static final String LOGIN_INCORRECT_DATA = "loginIncorrectData";
    public static final String REGISTRATION_INCORRECT_DATA = "registrationIncorrectData";
    public static final String TOOL_INCORRECT_DATA = "toolIncorrectData";
    public static final String PAYMENT_INCORRECT_DATA = "paymentIncorrectData";
    public static final String TOOL_EDIT_INCORRECT_DATA = "toolEditIncorrectData";
    public static final String USER_EDIT_INCORRECT_DATA = "userEditIncorrectData";
    public static final String PASSWORD_INCORRECT_DATA = "passwordIncorrectData";
    public static final String UNIQUE_LOGIN_ERROR = "uniqueLoginError";
    public static final String UNIQUE_EMAIL_ERROR = "uniqueEmailError";
    public static final String UNIQUE_PHONE_ERROR = "uniquePhoneError";
    public static final String UNIQUE_MODEL_ERROR = "uniqueModelError";

    private AttributeName() {
    }
}
