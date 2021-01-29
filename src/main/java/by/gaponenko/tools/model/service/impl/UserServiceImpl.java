package by.gaponenko.tools.model.service.impl;

import by.gaponenko.tools.creator.UserCreator;
import by.gaponenko.tools.entity.User;
import by.gaponenko.tools.exception.DaoException;
import by.gaponenko.tools.exception.ServiceException;
import by.gaponenko.tools.model.dao.UserDao;
import by.gaponenko.tools.model.dao.impl.UserDaoImpl;
import by.gaponenko.tools.model.service.EntityTransaction;
import by.gaponenko.tools.model.service.UserService;
import by.gaponenko.tools.util.ImageCompressor;
import by.gaponenko.tools.util.ParameterName;
import by.gaponenko.tools.util.PasswordEncrypt;
import by.gaponenko.tools.validator.UserDataValidator;

import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    @Override
    public Optional<User> findById(long id) throws ServiceException {
        UserDao userDao = new UserDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.initSingleQuery(userDao);
        try {
            return userDao.findById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.endSingleQuery();
        }
    }

    @Override
    public List<User> findAll() throws ServiceException {
        UserDao userDao = new UserDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.initSingleQuery(userDao);
        try {
            return userDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.endSingleQuery();
        }
    }

    @Override
    public Optional<User> login(String login, String password) throws ServiceException {
        if (!UserDataValidator.isValidLogin(login) || !UserDataValidator.isValidPassword(password)) {
            return Optional.empty();
        }
        UserDao userDao = new UserDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.initSingleQuery(userDao);
        try {
            Optional<User> optionalUser = userDao.findByLogin(login);
            String userPassword = userDao.findPasswordByLogin(login);
            if (optionalUser.isEmpty()
                    || !PasswordEncrypt.encrypt(password).equals(userPassword)) {
                optionalUser = Optional.empty();
            }
            return optionalUser;
        } catch (DaoException e) {
            throw new ServiceException("Login failed", e);
        } finally {
            transaction.endSingleQuery();
        }
    }

    @Override
    public boolean registration(Map<String, String> registrationParameters) throws ServiceException {
        if (!UserDataValidator.isValidRegistrationParameters(registrationParameters)) {
            return false;
        }
        UserDao userDao = new UserDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.initSingleQuery(userDao);
        User user = UserCreator.createUser(registrationParameters);
        String encryptedPassword = PasswordEncrypt.encrypt(registrationParameters.get(ParameterName.USER_PASSWORD));
        try {
            return userDao.add(user, encryptedPassword);
        } catch (DaoException e) {
            throw new ServiceException("Registration failed", e);
        } finally {
            transaction.endSingleQuery();
        }
    }

    @Override
    public boolean activate(String login) throws ServiceException {
        if (!UserDataValidator.isValidLogin(login)) {
            return false;
        }
        boolean isActivate = false;
        UserDao userDao = new UserDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.initSingleQuery(userDao);
        try {
            if (userDao.findStatus(login) == User.Status.UNCONFIRMED) {
                isActivate = userDao.updateStatus(login, User.Status.ACTIVE);
            }
        } catch (DaoException e) {
            throw new ServiceException("User is not activated", e);
        } finally {
            transaction.endSingleQuery();
        }
        return isActivate;
    }

    @Override
    public List<User> findByStatus(User.Status status) throws ServiceException {
        if (!UserDataValidator.isValidUserStatus(status)) {
            return Collections.EMPTY_LIST;
        }
        UserDao userDao = new UserDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.initSingleQuery(userDao);
        try {
            return userDao.findByStatus(status);
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.endSingleQuery();
        }
    }

    @Override
    public boolean updateStatus(String login, User.Status status) throws ServiceException {
        if (!UserDataValidator.isValidLogin(login) || !UserDataValidator.isValidUserStatus(status)) {
            return false;
        }
        UserDao userDao = new UserDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.initSingleQuery(userDao);
        try {
            return userDao.updateStatus(login, status);
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.endSingleQuery();
        }
    }

    @Override
    public Optional<User> updateUserInfo(Map<String, String> editUserParameters, long id) throws ServiceException {
        if (!UserDataValidator.isValidEditUserParameters(editUserParameters)) {
            return Optional.empty();
        }
        UserDao userDao = new UserDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.initSingleQuery(userDao);
        User user = UserCreator.createUser(editUserParameters, id);
        try {
            return userDao.updateUserInfo(user);
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.endSingleQuery();
        }
    }

    @Override
    public boolean updatePassword(long id, String password) throws ServiceException {
        if (!UserDataValidator.isValidPassword(password)) {
            return false;
        }
        UserDao userDao = new UserDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.initSingleQuery(userDao);
        String encryptedPassword = PasswordEncrypt.encrypt(password);
        try {
            return userDao.updatePassword(id, encryptedPassword);
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.endSingleQuery();
        }
    }

    @Override
    public boolean updateAvatar(long id, InputStream avatar) throws ServiceException {
        if (avatar == null) {
            return false;
        }
        UserDao userDao = new UserDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.initSingleQuery(userDao);
        InputStream compressedAvatar = ImageCompressor.compressAvatar(avatar);
        try {
            return userDao.updateAvatar(id, compressedAvatar);
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.endSingleQuery();
        }
    }

    @Override
    public boolean isLoginUnique(String login) throws ServiceException {
        if (!UserDataValidator.isValidLogin(login)) {
            return false;
        }
        UserDao userDao = new UserDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.initSingleQuery(userDao);
        try {
            return userDao.findByLogin(login).isEmpty();
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.endSingleQuery();
        }
    }

    @Override
    public boolean isEmailUnique(String email) throws ServiceException {
        if (!UserDataValidator.isValidEmail(email)) {
            return false;
        }
        UserDao userDao = new UserDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.initSingleQuery(userDao);
        try {
            return userDao.findByEmail(email).isEmpty();
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.endSingleQuery();
        }
    }

    @Override
    public boolean isPhoneUnique(String phone) throws ServiceException {
        if (!UserDataValidator.isValidPhone(phone)) {
            return false;
        }
        UserDao userDao = new UserDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.initSingleQuery(userDao);
        try {
            return userDao.findByPhone(phone).isEmpty();
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.endSingleQuery();
        }
    }
}
