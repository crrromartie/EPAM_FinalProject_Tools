package by.gaponenko.tools.model.service.impl;

import by.gaponenko.tools.creator.UserCreator;
import by.gaponenko.tools.entity.User;
import by.gaponenko.tools.exception.DaoException;
import by.gaponenko.tools.exception.ServiceException;
import by.gaponenko.tools.model.dao.DaoFactory;
import by.gaponenko.tools.model.dao.UserDao;
import by.gaponenko.tools.model.service.UserService;
import by.gaponenko.tools.util.ParameterName;
import by.gaponenko.tools.util.PasswordEncrypt;
import by.gaponenko.tools.validator.UserDataValidator;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class UserServiceImpl implements UserService {
    @Override
    public Optional<User> findById(long id) throws ServiceException {
        Optional<User> optionalUser;
        DaoFactory factory = DaoFactory.getINSTANCE();
        UserDao userDao = factory.getUserDao();
        try {
            optionalUser = userDao.findById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return optionalUser;
    }

    @Override
    public List<User> findAll() throws ServiceException {
        List<User> users;
        DaoFactory factory = DaoFactory.getINSTANCE();
        UserDao userDao = factory.getUserDao();
        try {
            users = userDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return users;
    }

    @Override
    public Optional<User> login(String login, String password) throws ServiceException {
        Optional<User> optionalUser = Optional.empty();
        DaoFactory factory = DaoFactory.getINSTANCE();
        UserDao userDao = factory.getUserDao();
        if (UserDataValidator.isValidLogin(login) && UserDataValidator.isValidPassword(password)) {
            try {
                optionalUser = userDao.findByLogin(login);
                String userPassword = userDao.findPasswordByLogin(login);
                if (optionalUser.isEmpty()
                        || !PasswordEncrypt.encrypt(password).equals(userPassword)) {
                    optionalUser = Optional.empty();
                }
            } catch (DaoException e) {
                throw new ServiceException("Error login!", e);
            }
        }
        return optionalUser;
    }

    @Override
    public boolean registration(Map<String, String> registrationParameters) throws ServiceException {
        boolean isRegister = false;
        DaoFactory factory = DaoFactory.getINSTANCE();
        UserDao userDao = factory.getUserDao();
        if (UserDataValidator.isValidRegistrationParameters(registrationParameters)) {
            User user = UserCreator.createUser(registrationParameters);
            String encryptedPassword = PasswordEncrypt.encrypt(registrationParameters.get(ParameterName.USER_PASSWORD));
            try {
                isRegister = userDao.add(user, encryptedPassword);
            } catch (DaoException e) {
                throw new ServiceException("Registration error!", e);
            }
        }
        return isRegister;
    }

    @Override
    public boolean activate(String login) throws ServiceException {
        boolean isActivate = false;
        DaoFactory factory = DaoFactory.getINSTANCE();
        UserDao userDao = factory.getUserDao();
        if (UserDataValidator.isValidLogin(login)) {
            try {
                if (userDao.findStatus(login) == User.Status.UNCONFIRMED) {
                    isActivate = userDao.updateStatus(login, User.Status.ACTIVE);
                }
            } catch (DaoException e) {
                throw new ServiceException("User not activate!", e);
            }
        }
        return isActivate;
    }

    @Override
    public List<User> findByStatus(User.Status status) throws ServiceException {
        List<User> users = Collections.EMPTY_LIST;
        DaoFactory factory = DaoFactory.getINSTANCE();
        UserDao userDao = factory.getUserDao();
        if (UserDataValidator.isValidUserStatus(status)) {
            try {
                users = userDao.findByStatus(status);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return users;
    }

    @Override
    public boolean updateStatus(String login, User.Status status) throws ServiceException {
        boolean isUpdate = false;
        DaoFactory factory = DaoFactory.getINSTANCE();
        UserDao userDao = factory.getUserDao();
        if (UserDataValidator.isValidLogin(login) && UserDataValidator.isValidUserStatus(status)) {
            try {
                isUpdate = userDao.updateStatus(login, status);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return isUpdate;
    }

    @Override
    public boolean updateUser(Map<String, String> userParameters) throws ServiceException {
        return false;
    }

    @Override
    public boolean isLoginUnique(String login) throws ServiceException {
        boolean isUnique = false;
        DaoFactory factory = DaoFactory.getINSTANCE();
        UserDao userDao = factory.getUserDao();
        if (UserDataValidator.isValidLogin(login)) {
            try {
                if (userDao.findByLogin(login).isEmpty()) {
                    isUnique = true;
                }
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return isUnique;
    }

    @Override
    public boolean isEmailUnique(String email) throws ServiceException {
        boolean isUnique = false;
        DaoFactory factory = DaoFactory.getINSTANCE();
        UserDao userDao = factory.getUserDao();
        if (UserDataValidator.isValidEmail(email)) {
            try {
                if (userDao.findByEmail(email).isEmpty()) {
                    isUnique = true;
                }
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return isUnique;
    }

    @Override
    public boolean isPhoneUnique(String phone) throws ServiceException {
        boolean isUnique = false;
        DaoFactory factory = DaoFactory.getINSTANCE();
        UserDao userDao = factory.getUserDao();
        if (UserDataValidator.isValidPhone(phone)) {
            try {
                if (userDao.findByPhone(phone).isEmpty()) {
                    isUnique = true;
                }
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return isUnique;
    }
}
