package by.gaponenko.tools.model.dao;

import by.gaponenko.tools.entity.Entity;
import by.gaponenko.tools.exception.DaoException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * The interface Dao.
 * <p>
 * The base interface denoting the main methods of interaction between the
 * application and the database (CRUD). Has default implementation method
 * for closing resultSet.
 *
 * @param <T> the type parameter
 * @author Haponenka Ihar
 * @version 1.0
 */
public interface Dao<T extends Entity> {
    Logger logger = LogManager.getLogger();

    /**
     * Establishing a connection.
     *
     * @param connection the connection
     */
    void setConnection(Connection connection);

    /**
     * Find entity by Id in database.
     *
     * @param id the id
     * @return the optional
     * @throws DaoException the dao exception
     */
    Optional<T> findById(long id) throws DaoException;

    /**
     * Find all entities in database.
     *
     * @return the list
     * @throws DaoException the dao exception
     */
    List<T> findAll() throws DaoException;

    /**
     * For closing a resultSet.
     *
     * @param resultSet the resultSet
     */
    default void closeResultSet(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                logger.log(Level.WARN, e.getMessage());
            }
        }
    }
}
