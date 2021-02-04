package by.gaponenko.tools.model.service;

import by.gaponenko.tools.model.dao.Dao;
import by.gaponenko.tools.model.pool.ConnectionPool;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Transaction management.
 *
 * @author Haponenka Ihar
 * @version 1.0
 */
public class EntityTransaction {
    static Logger logger = LogManager.getLogger();

    private Connection connection;

    /**
     * Initialize transaction one or more Dao with one connection.
     *
     * @param dao  the dao
     * @param daos the daos
     */
    public void initTransaction(Dao dao, Dao... daos) {
        if (connection == null) {
            connection = ConnectionPool.INSTANCE.getConnection();
            try {
                connection.setAutoCommit(false);
            } catch (SQLException e) {
                logger.log(Level.ERROR, e.getMessage());
            }
            dao.setConnection(connection);
            for (Dao daoItem : daos) {
                daoItem.setConnection(connection);
            }
        }
    }

    /**
     * Commit transaction.
     */
    public void commitTransaction() {
        if (connection != null) {
            try {
                connection.commit();
            } catch (SQLException e) {
                logger.log(Level.ERROR, e.getMessage());
            }
        }
    }

    /**
     * Rollback transaction.
     */
    public void rollbackTransaction() {
        if (connection != null) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                logger.log(Level.ERROR, e.getMessage());
            }
        }
    }

    /**
     * End transaction.
     */
    public void endTransaction() {
        if (connection != null) {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                logger.log(Level.ERROR, e.getMessage());
            }
            ConnectionPool.INSTANCE.releaseConnection(connection);
            connection = null;
        }
    }

    /**
     * Initialize one dao with one connection.
     *
     * @param dao the dao
     */
    public void initSingleQuery(Dao dao) {
        if (connection == null) {
            connection = ConnectionPool.INSTANCE.getConnection();
            dao.setConnection(connection);
        }
    }

    /**
     * End single query.
     */
    public void endSingleQuery() {
        if (connection != null) {
            ConnectionPool.INSTANCE.releaseConnection(connection);
            connection = null;
        }
    }
}
