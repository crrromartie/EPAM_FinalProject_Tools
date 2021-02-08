package by.gaponenko.tools.model.dao.impl;

/**
 * The Column name.
 * <p>
 * The class is used to store the names of the tables columns in the database.
 *
 * @author Haponenka Ihar
 * @version 1.0
 */
class ColumnName {
    // table 'users'
    public static final String USER_ID = "user_id";
    public static final String USER_LOGIN = "login";
    public static final String USER_PASSWORD = "password";
    public static final String USER_NAME = "name";
    public static final String USER_SURNAME = "surname";
    public static final String USER_EMAIL = "email";
    public static final String USER_PHONE = "phone";
    public static final String USER_ROLE_ID = "user_role_id";
    public static final String USER_STATUS_ID = "user_status_id";
    public static final String USER_AVATAR = "avatar";
    //table 'tools'
    public static final String TOOL_ID = "tool_id";
    public static final String TOOL_TYPE_ID = "tool_type_id";
    public static final String TOOL_MODEL = "model";
    public static final String TOOL_DESCRIPTION_ENG = "description_eng";
    public static final String TOOL_DESCRIPTION_RUS = "description_rus";
    public static final String TOOL_IS_AVAILABLE = "is_available";
    public static final String TOOL_RENT_PRICE = "rent_price";
    public static final String TOOL_PHOTO = "photo";
    //table 'orders'
    public static final String ORDER_ID = "order_id";
    public static final String ORDER_DATE = "order_date";
    public static final String ORDER_RETURN_DATE = "return_date";
    public static final String ORDER_TOTAL_COST = "total_cost";
    public static final String ORDER_STATUS_ID = "order_status_id";

    private ColumnName() {
    }
}
