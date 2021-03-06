package by.gaponenko.tools.controller.command;

/**
 * The Parameter name.
 * <p>
 * The class is used for work with the {@code HttpServletRequest} when processing the request.
 *
 * @author Haponenka Ihar
 * @version 1.0
 */
public class ParameterName {
    //general
    public static final String COMMAND = "command";
    //user
    public static final String USER_LOGIN = "login";
    public static final String USER_PASSWORD = "password";
    public static final String USER_EMAIL = "email";
    public static final String USER_NAME = "name";
    public static final String USER_SURNAME = "surname";
    public static final String USER_PHONE = "phone";
    public static final String CLIENT_STATUS = "clientStatus";
    public static final String AVATAR = "avatar";
    //tool
    public static final String TOOL_ID = "toolId";
    public static final String TOOL_TYPE = "toolType";
    public static final String TOOL_MODEL = "model";
    public static final String TOOL_DESCRIPTION_ENG = "descriptionEng";
    public static final String TOOL_DESCRIPTION_RUS = "descriptionRus";
    public static final String TOOL_RENT_PRICE = "rentPrice";
    public static final String TOOL_AVAILABILITY = "availability";
    public static final String TOOL_PHOTO = "toolPhoto";
    public static final String TOOLS_SORT_CRITERIA = "toolsSortCriteria";
    public static final String PRICE_UP = "price_up";
    public static final String PRICE_DOWN = "price_down";
    //order
    public static final String ORDER_ID = "orderId";
    public static final String ORDER_DATE = "order_date";
    public static final String ORDER_RETURN_DATE = "return_date";
    public static final String ORDER_TOTAL_COST = "totalCost";
    public static final String ORDER_STATUS = "orderStatus";
    //pagination
    public static final String PAGINATION_DIRECTION = "paginationDirection";
    public static final String PAGINATION_SUBJECT = "paginationSubject";
    public static final String NEXT_PAGE = "nextPage";
    public static final String PREVIOUS_PAGE = "previousPage";
    //payment_card
    public static final String CARD_NUMBER = "cardNumber";
    public static final String CARD_MONTH = "cardMonth";
    public static final String CARD_YEAR = "cardYear";
    public static final String CARD_CVV = "cardCvv";
    //notification
    public static final String REGISTERED = "registered";
    public static final String CONFIRMED = "confirmed";
    public static final String ORDER_CREATED = "orderCreated";
    public static final String ORDER_PAYED = "orderPayed";
    public static final String TOOL_ADDED = "toolAdded";
    public static final String TOOL_UPDATED = "toolUpdated";
    public static final String USER_INFO_UPDATED = "userInfoUpdated";
    public static final String PASSWORD_UPDATED = "passwordUpdated";

    private ParameterName() {
    }
}
