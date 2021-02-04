package by.gaponenko.tools.controller.command;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The Command provider.
 * <p>
 * The main task of this class is to provide command objects that implement
 * the {@code Command} interface, expected by the controller to perform
 * operations with the requests of the application clients.
 *
 * @author Haponenka Ihar
 * @version 1.0
 */
public class CommandProvider {
    static Logger logger = LogManager.getLogger();

    private CommandProvider() {
    }

    /**
     * Checks the name of the command passed as a parameter for null and for a empty value
     * and tries to return an object that implements the {@code Command} interface
     * from the enum {@code CommandType}. If the command was not found, the method returns
     * an no such command
     *
     * @param commandName the command name
     * @return the command
     */
    public static Command takeCommand(String commandName) {
        Command currentCommand = CommandType.NO_SUCH_COMMAND.getCommand();
        if (commandName != null && !commandName.isBlank()) {
            try {
                currentCommand = CommandType.valueOf(commandName.toUpperCase()).getCommand();
            } catch (IllegalArgumentException e) {
                logger.log(Level.ERROR, "Command is not defined " + commandName);
            }
        }
        return currentCommand;
    }
}
