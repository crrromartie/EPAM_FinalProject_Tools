package by.gaponenko.tools.model.pool;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * The enum Connection pool.
 * Creates, gives and monitors connections with database.
 *
 * @author Haponenka Ihar
 * @version 1.0
 */
public enum ConnectionPool {
    /**
     * Instance connection pool.
     */
    INSTANCE;

    static Logger logger = LogManager.getLogger();

    private final BlockingQueue<ProxyConnection> freeConnections;
    private final BlockingQueue<ProxyConnection> activeConnections;

    private final int DEFAULT_POOL_SIZE = Integer.parseInt(DBPropertiesManager.getPoolSize());
    private final String DATABASE_DRIVER = DBPropertiesManager.getDriver();

    ConnectionPool() {
        freeConnections = new LinkedBlockingQueue<>(DEFAULT_POOL_SIZE);
        activeConnections = new LinkedBlockingQueue<>(DEFAULT_POOL_SIZE);
        registerDriver();
        for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
            freeConnections.offer(ConnectionCreator.createConnection());
        }
    }

    /**
     * Gets connection.
     *
     * @return the connection
     */
    public Connection getConnection() {
        ProxyConnection connection = null;
        try {
            connection = freeConnections.take();
            activeConnections.put(connection);
        } catch (InterruptedException e) {
            logger.log(Level.ERROR, e.getMessage());
            Thread.currentThread().interrupt();
        }
        return connection;
    }

    /**
     * Release connection.
     *
     * @param connection the connection
     */
    public void releaseConnection(Connection connection) {
        if (connection instanceof ProxyConnection
                && activeConnections.remove(connection)) {
            try {
                if (!connection.getAutoCommit()) {
                    connection.setAutoCommit(true);
                }
            } catch (SQLException e) {
                logger.log(Level.ERROR, e.getMessage());
            }
            freeConnections.offer((ProxyConnection) connection);
        } else {
            logger.log(Level.ERROR, "Received connection is not proxy connection");
        }
    }

    /**
     * Destroy pool.
     */
    public void destroyPool() {
        for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
            try {
                freeConnections.take().reallyClose();
            } catch (SQLException e) {
                logger.log(Level.ERROR, e.getMessage());
            } catch (InterruptedException e) {
                logger.log(Level.ERROR, e.getMessage());
                Thread.currentThread().interrupt();
            }
        }
        deregisterDrivers();
    }

    /**
     * Register driver.
     */
    private void registerDriver() {
        try {
            Class.forName(DATABASE_DRIVER);
        } catch (ClassNotFoundException e) {
            logger.log(Level.FATAL, "Driver is not registered");
            throw new RuntimeException("Driver is not registered", e);
        }
    }

    /**
     * Deregister drivers.
     */
    private void deregisterDrivers() {
        DriverManager.getDrivers().asIterator().forEachRemaining(driver -> {
            try {
                DriverManager.deregisterDriver(driver);
            } catch (SQLException e) {
                logger.log(Level.ERROR, e.getMessage());
            }
        });
    }
}

