package by.gaponenko.tools.model.dao;

import java.sql.Connection;

/**
 * The interface abstract Dao.
 *
 * @author Haponenka Ihar
 * @version 1.0
 */
public abstract class AbstractDao {
    protected Connection connection;

    /**
     * Establishing a connection.
     *
     * @param connection the connection
     */
    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
