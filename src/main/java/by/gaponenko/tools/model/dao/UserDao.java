package by.gaponenko.tools.model.dao;

import by.gaponenko.tools.entity.User;
import by.gaponenko.tools.exception.DaoException;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;

public interface UserDao extends Dao<User> {

    Optional<User> findByLogin(String login) throws DaoException;

    Optional<User> findByEmail(String email) throws DaoException;

    Optional<User> findByPhone(String phone) throws DaoException;

    String findPasswordByLogin(String login) throws DaoException;

    User.Status findStatus(String login) throws DaoException;

    List<User> findByStatus(User.Status status) throws DaoException;

    boolean add(User user, String password) throws DaoException;

    boolean updateStatus(String login, User.Status status) throws DaoException;

    boolean updatePassword(String login, String password) throws DaoException;

    boolean updatePhone(String login, String phone) throws DaoException;

    boolean updateAvatar(String login, InputStream avatar) throws DaoException;
}
