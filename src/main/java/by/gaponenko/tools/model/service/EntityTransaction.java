package by.gaponenko.tools.model.service;

import by.gaponenko.tools.model.dao.Dao;
import by.gaponenko.tools.model.pool.ConnectionPool;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

public class EntityTransaction {
    static Logger logger = LogManager.getLogger();

    private Connection connection;

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

    public void commitTransaction() {
        if (connection != null) {
            try {
                connection.commit();
            } catch (SQLException e) {
                logger.log(Level.ERROR, e.getMessage());
            }
        }
    }

    public void rollbackTransaction() {
        if (connection != null) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                logger.log(Level.ERROR, e.getMessage());
            }
        }
    }

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
     * For single query
     */

    public void initSingleQuery(Dao dao) {
        if (connection == null) {
            connection = ConnectionPool.INSTANCE.getConnection();
            dao.setConnection(connection);
        }
    }

    public void endSingleQuery() {
        if (connection != null) {
            ConnectionPool.INSTANCE.releaseConnection(connection);
            connection = null;
        }
    }
}
