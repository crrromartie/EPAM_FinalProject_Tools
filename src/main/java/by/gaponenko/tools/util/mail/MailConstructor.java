package by.gaponenko.tools.util.mail;

/**
 * The Mail constructor.
 *
 * @author Ihar Haponenka
 * @version 1.0
 */
public class MailConstructor {
    private static final String NEW_USER_TEXT =
            "Thanks for registration! Please confirm your email (Спасибо за регистрацию! " +
                    "Пожалуйста, подтвердите свой email):";
    private static final String NEW_USER_LINK =
            "http://localhost:9090/ToolRental?command=confirm_registration&login=";
    private static final String SPACE = " ";

    private MailConstructor() {
    }

    /**
     * Make mail message.
     *
     * @param login the login.
     * @return the string
     */
    public static String newUserMail(String login) {
        StringBuilder builder = new StringBuilder()
                .append(NEW_USER_TEXT)
                .append(SPACE)
                .append(NEW_USER_LINK)
                .append(login);
        return builder.toString();
    }
}
