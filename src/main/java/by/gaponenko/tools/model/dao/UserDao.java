package by.gaponenko.tools.model.dao;

import by.gaponenko.tools.entity.User;
import by.gaponenko.tools.exception.DaoException;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;

/**
 * The interface User dao.
 * <p>
 * Extends the interface of the {@code Dao}, supplementing it with specific
 * methods for the interaction of the application with User entities in the database.
 *
 * @version 1.0
 * @see Dao * @author Ihar Haponenka
 */
public interface UserDao extends Dao<User> {

    /**
     * Find all clients in database.
     *
     * @return the list
     * @throws DaoException the dao exception
     */
    List<User> findClients() throws DaoException;

    /**
     * Find all clients by status in database.
     *
     * @return the list
     * @throws DaoException the dao exception
     */
    List<User> findClientsByStatus(User.Status status) throws DaoException;

    /**
     * Find a user in the database by login.
     *
     * @param login the login
     * @return the optional
     * @throws DaoException the dao exception
     */
    Optional<User> findByLogin(String login) throws DaoException;

    /**
     * Find a user in the database by email address.
     *
     * @param email the email
     * @return the optional
     * @throws DaoException the dao exception
     */
    Optional<User> findByEmail(String email) throws DaoException;

    /**
     * Find a user in the database by phone.
     *
     * @param phone the phone
     * @return the optional
     * @throws DaoException the dao exception
     */
    Optional<User> findByPhone(String phone) throws DaoException;

    /**
     * Find password by login.
     *
     * @param login the login
     * @return the optional
     * @throws DaoException the dao exception
     */
    String findPasswordByLogin(String login) throws DaoException;

    /**
     * Find user status by login.
     *
     * @param login the login
     * @return the optional
     * @throws DaoException the dao exception
     */
    User.Status findStatus(String login) throws DaoException;

    /**
     * Adding a new user to the database.
     *
     * @param user     the user
     * @param password the password
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean add(User user, String password) throws DaoException;

    /**
     * Update user status.
     *
     * @param login  the login
     * @param status the status
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean updateStatus(String login, User.Status status) throws DaoException;

    /**
     * Update user password.
     *
     * @param id       the id
     * @param password the password
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean updatePassword(long id, String password) throws DaoException;

    /**
     * Update user info.
     *
     * @param user the user
     * @return the optional
     * @throws DaoException the dao exception
     */
    Optional<User> updateUserInfo(User user) throws DaoException;

    /**
     * Update user avatar.
     *
     * @param id     the id
     * @param avatar the avatar
     * @return the optional
     * @throws DaoException the dao exception
     */
    boolean updateAvatar(long id, InputStream avatar) throws DaoException;
}
