package by.gaponenko.tools.model.service;

import by.gaponenko.tools.entity.User;
import by.gaponenko.tools.exception.ServiceException;

import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * The interface User service.
 * <p>
 * Indicates methods for processing information related to users.
 *
 * @author Haponenka Ihar
 * @version 1.0
 */
public interface UserService {

    /**
     * Find user by id.
     *
     * @param id the id
     * @return the optional
     * @throws ServiceException the service exception
     */
    Optional<User> findById(long id) throws ServiceException;

    /**
     * Find all clients.
     *
     * @return the list
     * @throws ServiceException the service exception
     */
    List<User> findClients() throws ServiceException;

    /**
     * Find all clients by status.
     *
     * @param status the status
     * @return the list
     * @throws ServiceException the service exception
     */
    List<User> findClientsByStatus(User.Status status) throws ServiceException;

    /**
     * The method is used to log into the system.
     *
     * @param login    the login
     * @param password the password
     * @return the optional
     * @throws ServiceException the service exception
     */
    Optional<User> login(String login, String password) throws ServiceException;

    /**
     * The method is used to registration into the system.
     *
     * @param registrationParameters the registrationParameters
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean registration(Map<String, String> registrationParameters) throws ServiceException;

    /**
     * Activate client.
     *
     * @param login the login
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean activate(String login) throws ServiceException;

    /**
     * Update client status.
     *
     * @param login  the login.
     * @param status the status
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean updateStatus(String login, User.Status status) throws ServiceException;

    /**
     * Update user status.
     *
     * @param id       the id
     * @param password the password
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean updatePassword(long id, String password) throws ServiceException;

    /**
     * Update user info.
     *
     * @param editUserParameters the editUserParameters
     * @param id                 the id
     * @return the optional
     * @throws ServiceException the service exception
     */
    Optional<User> updateUserInfo(Map<String, String> editUserParameters, long id) throws ServiceException;

    /**
     * Update user avatar.
     *
     * @param id     the id
     * @param avatar the avatar
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean updateAvatar(long id, InputStream avatar) throws ServiceException;

    /**
     * Login uniqueness check.
     *
     * @param login the login
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean isLoginUnique(String login) throws ServiceException;

    /**
     * Email uniqueness check.
     *
     * @param email the email
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean isEmailUnique(String email) throws ServiceException;

    /**
     * Phone uniqueness check.
     *
     * @param phone the phoe
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean isPhoneUnique(String phone) throws ServiceException;
}
