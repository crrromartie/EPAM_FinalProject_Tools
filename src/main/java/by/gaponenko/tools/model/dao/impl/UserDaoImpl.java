package by.gaponenko.tools.model.dao.impl;

import by.gaponenko.tools.entity.User;
import by.gaponenko.tools.exception.DaoException;
import by.gaponenko.tools.model.dao.UserDao;
import by.gaponenko.tools.model.pool.ConnectionPool;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao {
    static Logger logger = LogManager.getLogger();

    private static final String DEFAULT_USER_AVATAR_PATH = "C:\\Users\\IGOR\\IdeaProjects" +
            "\\EPAM_FinalProject_Tools\\photo\\default_avatar.jpg";

    private static final String EMPTY_STRING = "";

    private static final String FIND_USER_BY_ID = "SELECT user_id, login, name, surname, email, phone, user_role_id, " +
            "user_status_id, avatar FROM users WHERE user_id = ?";
    private static final String FIND_ALL = "SELECT user_id, login, name, surname, email, phone, user_role_id, user_status_id, " +
            "avatar FROM users";
    private static final String FIND_USER_BY_LOGIN = "SELECT user_id, login, name, surname, email, phone, user_role_id, " +
            "user_status_id, avatar FROM users WHERE login = ?";
    private static final String FIND_USER_BY_EMAIL = "SELECT user_id, login, name, surname, email, phone, user_role_id, " +
            "user_status_id, avatar FROM users WHERE email = ?";
    private static final String FIND_USER_BY_PHONE = "SELECT user_id, login, name, surname, email, phone, user_role_id, " +
            "user_status_id, avatar FROM users WHERE phone = ?";
    private static final String FIND_PASSWORD_BY_LOGIN = "SELECT password FROM USERS WHERE login = ? ";
    private static final String FIND_STATUS = "SELECT user_status_id FROM USERS WHERE login = ?";
    private static final String FIND_USERS_BY_STATUS = "SELECT user_id, login, name, surname, email, phone, user_role_id, " +
            "user_status_id, avatar FROM users WHERE user_status_id = ?";
    private static final String ADD_USER = "INSERT INTO users (login, password, name, surname, email, phone, avatar) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_STATUS = "UPDATE users SET user_status_id = ? WHERE login = ?";
    private static final String UPDATE_PASSWORD = "UPDATE users SET password = ? WHERE login = ?";
    private static final String UPDATE_PHONE = "UPDATE users SET phone = ? WHERE login = ?";
    private static final String UPDATE_AVATAR = "UPDATE users SET avatar = ? WHERE login = ?";

    @Override
    public Optional<User> findById(long id) throws DaoException {
        Optional<User> optionalUser = Optional.empty();
        User user = null;
        ResultSet resultSet = null;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_USER_BY_ID)) {
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = createFromResultSet(resultSet);
                optionalUser = Optional.of(user);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeResultSet(resultSet);
        }
        return optionalUser;
    }

    @Override
    public List<User> findAll() throws DaoException {
        List<User> users = new ArrayList<>();
        ResultSet resultSet = null;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL)) {
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = createFromResultSet(resultSet);
                users.add(user);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeResultSet(resultSet);
        }
        return users;
    }

    @Override
    public Optional<User> findByLogin(String login) throws DaoException {
        Optional<User> optionalUser = Optional.empty();
        User user = null;
        ResultSet resultSet = null;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_USER_BY_LOGIN)) {
            statement.setString(1, login);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = createFromResultSet(resultSet);
                optionalUser = Optional.of(user);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeResultSet(resultSet);
        }
        return optionalUser;
    }

    @Override
    public Optional<User> findByEmail(String email) throws DaoException {
        Optional<User> optionalUser = Optional.empty();
        User user = null;
        ResultSet resultSet = null;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_USER_BY_EMAIL)) {
            statement.setString(1, email);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = createFromResultSet(resultSet);
                optionalUser = Optional.of(user);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeResultSet(resultSet);
        }
        return optionalUser;
    }

    @Override
    public Optional<User> findByPhone(String phone) throws DaoException {
        Optional<User> optionalUser = Optional.empty();
        User user = null;
        ResultSet resultSet = null;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_USER_BY_PHONE)) {
            statement.setString(1, phone);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = createFromResultSet(resultSet);
                optionalUser = Optional.of(user);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeResultSet(resultSet);
        }
        return optionalUser;
    }

    @Override
    public String findPasswordByLogin(String login) throws DaoException {
        String password = EMPTY_STRING;
        ResultSet resultSet = null;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_PASSWORD_BY_LOGIN)) {
            statement.setString(1, login);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                password = resultSet.getString(ColumnName.USER_PASSWORD);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeResultSet(resultSet);
        }
        return password;
    }

    @Override
    public User.Status findStatus(String login) throws DaoException {
        User.Status status = null;
        ResultSet resultSet = null;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_STATUS)) {
            statement.setString(1, login);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                status = User.Status.getStatusById(resultSet.getInt(ColumnName.USER_STATUS_ID));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeResultSet(resultSet);
        }
        return status;
    }

    @Override
    public List<User> findByStatus(User.Status status) throws DaoException {
        List<User> users = new ArrayList<>();
        ResultSet resultSet = null;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_USERS_BY_STATUS)) {
            statement.setInt(1, status.getStatusId());
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = createFromResultSet(resultSet);
                users.add(user);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeResultSet(resultSet);
        }
        return users;
    }

    @Override
    public boolean add(User user, String password) throws DaoException {
        InputStream fileInputStream = null;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(ADD_USER)) {
            Statement statement1 = connection.createStatement();
            statement.setString(1, user.getLogin());
            statement.setString(2, password);
            statement.setString(3, user.getName());
            statement.setString(4, user.getSurname());
            statement.setString(5, user.getEmail());
            statement.setString(6, user.getPhone());
            File file = new File(DEFAULT_USER_AVATAR_PATH);
            fileInputStream = new FileInputStream(file);
            statement.setBlob(7, fileInputStream);
            return (statement.executeUpdate() > 0);
        } catch (SQLException e) {
            throw new DaoException(e);
        } catch (FileNotFoundException e) {
            throw new DaoException("File not found!", e);
        } finally {
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
            } catch (IOException e) {
                logger.log(Level.WARN, e.getMessage());
            }
        }
    }

    @Override
    public boolean updateStatus(String login, User.Status status) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_STATUS)) {
            statement.setInt(1, status.getStatusId());
            statement.setString(2, login);
            return (statement.executeUpdate() > 0);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean updatePassword(String login, String password) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_PASSWORD)) {
            statement.setString(1, password);
            statement.setString(2, login);
            return (statement.executeUpdate() > 0);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean updatePhone(String login, String phone) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_PHONE)) {
            statement.setString(1, phone);
            statement.setString(2, login);
            return (statement.executeUpdate() > 0);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean updateAvatar(String login, InputStream avatar) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_AVATAR)) {
            statement.setBlob(1, avatar);
            statement.setString(2, login);
            return (statement.executeUpdate() > 0);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private User createFromResultSet(ResultSet resultSet) throws DaoException {
        User user = new User();
        try {
            user.setUserId(resultSet.getLong(ColumnName.USER_ID));
            user.setLogin(resultSet.getString(ColumnName.USER_LOGIN));
            user.setName(resultSet.getString(ColumnName.USER_NAME));
            user.setSurname(resultSet.getString(ColumnName.USER_SURNAME));
            user.setEmail(resultSet.getString(ColumnName.USER_EMAIL));
            user.setPhone(resultSet.getString(ColumnName.USER_PHONE));
            user.setRole(User.Role.getRoleById(resultSet.getInt(ColumnName.USER_ROLE_ID)));
            user.setStatus(User.Status.getStatusById(resultSet.getInt(ColumnName.USER_STATUS_ID)));
            byte[] avatarBytes = resultSet.getBytes(ColumnName.USER_AVATAR);
            String avatarBase64 = Base64.getEncoder().encodeToString(avatarBytes);
            user.setAvatar(avatarBase64);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return user;
    }
}
