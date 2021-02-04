package by.gaponenko.tools.util.mail;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Mail properties manager.
 *
 * @author Haponenka Ihar.
 * @version 1.0
 */
class MailPropertiesManager {
    static Logger logger = LogManager.getLogger();

    private static final Properties properties;

    private static final String PROPERTIES_FILE = "properties/mail.properties";
    private static final String USER_NAME = "mail.user.name";
    private static final String USER_PASSWORD = "mail.user.password";

    private MailPropertiesManager() {
    }

    static {
        properties = new Properties();
        try {
            InputStream inputStream = MailPropertiesManager.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE);
            if (inputStream == null) {
                logger.log(Level.ERROR, "Properties file is not found!");
            }
            properties.load(inputStream);
        } catch (IOException e) {
            logger.log(Level.ERROR, e.getMessage());
        }
    }

    public static Properties getProperties() {
        return properties;
    }

    public static String getUserName() {
        return properties.getProperty(USER_NAME);
    }

    public static String getUserPassword() {
        return properties.getProperty(USER_PASSWORD);
    }

}
