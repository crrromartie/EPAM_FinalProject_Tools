package by.gaponenko.tools.model.dao.impl;

import by.gaponenko.tools.builder.UserBuilder;
import by.gaponenko.tools.entity.User;
import by.gaponenko.tools.exception.DaoException;
import by.gaponenko.tools.model.dao.AbstractDao;
import by.gaponenko.tools.model.dao.UserDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

/**
 * The User dao.
 * {@code UserDao} interface implementation
 *
 * @author Haponenka Ihar
 * @version 1.0
 * @see UserDao
 */
public class UserDaoImpl extends AbstractDao implements UserDao {
    static Logger logger = LogManager.getLogger();

    private static final String DEFAULT_USER_AVATAR_PATH = "C:\\Users\\IGOR\\IdeaProjects" +
            "\\EPAM_FinalProject_Tools\\photo\\default_avatar.jpg";

    private static final String EMPTY_STRING = "";

    private static final String FIND_USER_BY_ID = "SELECT user_id, login, name, surname, email, phone, " +
            "user_role_id, user_status_id, avatar FROM users WHERE user_id = ?";
    private static final String FIND_CLIENTS = "SELECT user_id, login, name, surname, email, phone, user_role_id, " +
            "user_status_id, avatar FROM users WHERE user_role_id = 2";
    private static final String FIND_CLIENTS_BY_STATUS = "SELECT user_id, login, name, surname, email, phone, " +
            "user_role_id, user_status_id, avatar FROM users WHERE user_role_id = 2 AND user_status_id = ?";
    private static final String FIND_USER_BY_LOGIN = "SELECT user_id, login, name, surname, email, phone, " +
            "user_role_id, user_status_id, avatar FROM users WHERE login = ?";
    private static final String FIND_USER_BY_EMAIL = "SELECT user_id, login, name, surname, email, phone, " +
            "user_role_id, user_status_id, avatar FROM users WHERE email = ?";
    private static final String FIND_USER_BY_PHONE = "SELECT user_id, login, name, surname, email, phone, " +
            "user_role_id, user_status_id, avatar FROM users WHERE phone = ?";
    private static final String FIND_PASSWORD_BY_LOGIN = "SELECT password FROM USERS WHERE login = ? ";
    private static final String FIND_STATUS = "SELECT user_status_id FROM USERS WHERE login = ?";
    private static final String ADD_USER = "INSERT INTO users (login, password, name, surname, email, phone, avatar) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_STATUS = "UPDATE users SET user_status_id = ? WHERE login = ?";
    private static final String UPDATE_PASSWORD = "UPDATE users SET password = ? WHERE user_id = ?";
    private static final String UPDATE_USER_INFO = "UPDATE users SET name = ?, surname = ?, email = ?, " +
            "phone = ? WHERE user_id = ?";
    private static final String UPDATE_AVATAR = "UPDATE users SET avatar = ? WHERE user_id = ?";

    @Override
    public Optional<User> findById(long id) throws DaoException {
        Optional<User> optionalUser = Optional.empty();
        ResultSet resultSet = null;
        try (PreparedStatement statement = connection.prepareStatement(FIND_USER_BY_ID)) {
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                User user = createFromResultSet(resultSet);
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
        throw new UnsupportedOperationException("Method is not supported");
    }

    @Override
    public List<User> findClients() throws DaoException {
        List<User> users = new ArrayList<>();
        ResultSet resultSet = null;
        try (PreparedStatement statement = connection.prepareStatement(FIND_CLIENTS)) {
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
    public List<User> findClientsByStatus(User.Status status) throws DaoException {
        List<User> users = new ArrayList<>();
        ResultSet resultSet = null;
        try (PreparedStatement statement = connection.prepareStatement(FIND_CLIENTS_BY_STATUS)) {
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
    public Optional<User> findByLogin(String login) throws DaoException {
        Optional<User> optionalUser = Optional.empty();
        ResultSet resultSet = null;
        try (PreparedStatement statement = connection.prepareStatement(FIND_USER_BY_LOGIN)) {
            statement.setString(1, login);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                User user = createFromResultSet(resultSet);
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
        ResultSet resultSet = null;
        try (PreparedStatement statement = connection.prepareStatement(FIND_USER_BY_EMAIL)) {
            statement.setString(1, email);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                User user = createFromResultSet(resultSet);
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
        ResultSet resultSet = null;
        try (PreparedStatement statement = connection.prepareStatement(FIND_USER_BY_PHONE)) {
            statement.setString(1, phone);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                User user = createFromResultSet(resultSet);
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
        try (PreparedStatement statement = connection.prepareStatement(FIND_PASSWORD_BY_LOGIN)) {
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
        try (PreparedStatement statement = connection.prepareStatement(FIND_STATUS)) {
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
    public boolean add(User user, String password) throws DaoException {
        File file = new File(DEFAULT_USER_AVATAR_PATH);
        try (InputStream fileInputStream = new FileInputStream(file);
             PreparedStatement statement = connection.prepareStatement(ADD_USER)) {
            Statement statement1 = connection.createStatement();
            statement.setString(1, user.getLogin());
            statement.setString(2, password);
            statement.setString(3, user.getName());
            statement.setString(4, user.getSurname());
            statement.setString(5, user.getEmail());
            statement.setString(6, user.getPhone());
            statement.setBlob(7, fileInputStream);
            return (statement.executeUpdate() == 1);
        } catch (SQLException e) {
            throw new DaoException(e);
        } catch (IOException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean updateStatus(String login, User.Status status) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_STATUS)) {
            statement.setInt(1, status.getStatusId());
            statement.setString(2, login);
            return (statement.executeUpdate() == 1);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean updatePassword(long id, String password) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_PASSWORD)) {
            statement.setString(1, password);
            statement.setLong(2, id);
            return (statement.executeUpdate() == 1);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<User> updateUserInfo(User user) throws DaoException {
        Optional<User> optionalUser = Optional.empty();
        ResultSet resultSet = null;
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_USER_INFO);
             PreparedStatement updateStatment = connection.prepareStatement(FIND_USER_BY_ID)) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getSurname());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPhone());
            statement.setLong(5, user.getUserId());
            statement.executeUpdate();

            updateStatment.setLong(1, user.getUserId());
            resultSet = updateStatment.executeQuery();
            if (resultSet.next()) {
                User updatedUser = createFromResultSet(resultSet);
                optionalUser = Optional.of(updatedUser);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeResultSet(resultSet);
        }
        return optionalUser;
    }

    @Override
    public boolean updateAvatar(long id, InputStream avatar) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_AVATAR)) {
            statement.setBlob(1, avatar);
            statement.setLong(2, id);
            return (statement.executeUpdate() == 1);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private User createFromResultSet(ResultSet resultSet) throws DaoException {
        try {
            byte[] avatarBytes = resultSet.getBytes(ColumnName.USER_AVATAR);
            String avatarBase64 = Base64.getEncoder().encodeToString(avatarBytes);
            UserBuilder userBuilder = new UserBuilder()
                    .setUserId(resultSet.getLong(ColumnName.USER_ID))
                    .setLogin(resultSet.getString(ColumnName.USER_LOGIN))
                    .setName(resultSet.getString(ColumnName.USER_NAME))
                    .setSurname(resultSet.getString(ColumnName.USER_SURNAME))
                    .setEmail(resultSet.getString(ColumnName.USER_EMAIL))
                    .setPhone(resultSet.getString(ColumnName.USER_PHONE))
                    .setRole(User.Role.getRoleById(resultSet.getInt(ColumnName.USER_ROLE_ID)))
                    .setStatus(User.Status.getStatusById(resultSet.getInt(ColumnName.USER_STATUS_ID)))
                    .setAvatar(avatarBase64);
            return new User(userBuilder);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
