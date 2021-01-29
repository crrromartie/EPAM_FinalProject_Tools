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

public interface Dao<T extends Entity> {
    Logger logger = LogManager.getLogger();

    void setConnection(Connection connection);

    Optional<T> findById(long id) throws DaoException;

    List<T> findAll() throws DaoException;

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
