package by.gaponenko.tools.model.service;

import by.gaponenko.tools.entity.User;
import by.gaponenko.tools.exception.ServiceException;

import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserService {

    Optional<User> findById(long id) throws ServiceException;

    List<User> findAll() throws ServiceException;

    Optional<User> login(String login, String password) throws ServiceException;

    boolean registration(Map<String, String> registrationParameters) throws ServiceException;

    boolean activate(String login) throws ServiceException;

    List<User> findByStatus(User.Status status) throws ServiceException;

    boolean updateStatus(String login, User.Status status) throws ServiceException;

    boolean updatePassword(long id, String password) throws ServiceException;

    Optional<User> updateUserInfo(Map<String, String> editUserParameters, long id) throws ServiceException;

    boolean updateAvatar(long id, InputStream avatar) throws ServiceException;

    boolean isLoginUnique(String login) throws ServiceException;

    boolean isEmailUnique(String email) throws ServiceException;

    boolean isPhoneUnique(String phone) throws ServiceException;
}


