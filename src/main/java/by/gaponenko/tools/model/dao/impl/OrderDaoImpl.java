package by.gaponenko.tools.model.dao.impl;

import by.gaponenko.tools.builder.OrderBuilder;
import by.gaponenko.tools.builder.ToolBuilder;
import by.gaponenko.tools.builder.UserBuilder;
import by.gaponenko.tools.entity.Order;
import by.gaponenko.tools.entity.Tool;
import by.gaponenko.tools.entity.User;
import by.gaponenko.tools.exception.DaoException;
import by.gaponenko.tools.model.dao.AbstractDao;
import by.gaponenko.tools.model.dao.OrderDao;
import by.gaponenko.tools.util.DateConverter;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

/**
 * The Order dao.
 * {@code OrderDao} interface implementation
 *
 * @author Haponenka Ihar
 * @version 1.0
 * @see OrderDao
 */
public class OrderDaoImpl extends AbstractDao implements OrderDao {
    private static final String FIND_ORDER_BY_ID = "SELECT users.user_id, users.login, users.name, " +
            "users.surname, users.email, users.phone, users.user_role_id, users.user_status_id, users.avatar, " +
            "tools.tool_id, tools.tool_type_id, tools.model, tools.description_eng, " +
            "tools.description_rus, tools.is_available, tools.rent_price, tools.photo, orders.order_id, " +
            "orders.order_date, orders.return_date, orders.total_cost, orders.order_status_id " +
            "FROM orders JOIN users ON users.user_id = orders.user_id JOIN tools ON tools.tool_id = " +
            "orders.tool_id WHERE orders.order_id = ?";
    private static final String FIND_ALL = "SELECT users.user_id, users.login, users.name, users.surname, " +
            "users.email, users.phone, users.user_role_id, users.user_status_id, users.avatar, " +
            "tools.tool_id, tools.tool_type_id, tools.model, tools.description_eng, tools.description_rus, " +
            "tools.is_available, tools.rent_price, tools.photo, orders.order_id, orders.order_date, " +
            "orders.return_date, orders.total_cost, orders.order_status_id FROM orders JOIN users ON " +
            "users.user_id = orders.user_id JOIN tools ON tools.tool_id = orders.tool_id";
    private static final String FIND_ORDERS_BY_STATUS = "SELECT users.user_id, users.login, users.name, " +
            "users.surname, users.email, users.phone, users.user_role_id, users.user_status_id, users.avatar, " +
            "tools.tool_id, tools.tool_type_id, tools.model, tools.description_eng, tools.description_rus, " +
            "tools.is_available, tools.rent_price, tools.photo, orders.order_id, orders.order_date, " +
            "orders.return_date, orders.total_cost, orders.order_status_id FROM orders JOIN users ON " +
            "users.user_id = orders.user_id JOIN tools ON tools.tool_id = orders.tool_id " +
            "WHERE orders.order_status_id = ?";
    private static final String FIND_ORDERS_BY_USER_ID = "SELECT users.user_id, users.login, users.name, " +
            "users.surname, users.email, users.phone, users.user_role_id, users.user_status_id, users.avatar, " +
            "tools.tool_id, tools.tool_type_id, tools.model, tools.description_eng, tools.description_rus, " +
            "tools.is_available, tools.rent_price, tools.photo, orders.order_id, orders.order_date, " +
            "orders.return_date, orders.total_cost, orders.order_status_id FROM orders JOIN users ON " +
            "users.user_id = orders.user_id JOIN tools ON tools.tool_id = orders.tool_id WHERE users.user_id = ?";
    private static final String FIND_ORDERS_BY_USER_ID_AND_ORDER_STATUS = "SELECT users.user_id, users.login, " +
            "users.name, users.surname, users.email, users.phone, users.user_role_id, users.user_status_id, " +
            "users.avatar, tools.tool_id, tools.tool_type_id, tools.model, tools.description_eng, " +
            "tools.description_rus, tools.is_available, tools.rent_price, tools.photo, orders.order_id, " +
            "orders.order_date, orders.return_date, orders.total_cost, orders.order_status_id FROM orders JOIN " +
            "users ON users.user_id = orders.user_id JOIN tools ON tools.tool_id = orders.tool_id " +
            "WHERE users.user_id = ? AND orders.order_status_id = ?";
    private static final String ADD_ORDER = "INSERT INTO orders (order_id, order_date, return_date, total_cost, " +
            "order_status_id, user_id, tool_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_ORDER_STATUS = "UPDATE orders SET order_status_id = ? WHERE order_id = ?";
    private static final String DELETE_ORDER = "DELETE FROM orders WHERE tool_id = ?";

    @Override
    public Optional<Order> findById(long id) throws DaoException {
        Optional<Order> optionalOrder = Optional.empty();
        ResultSet resultSet = null;
        try (PreparedStatement statement = connection.prepareStatement(FIND_ORDER_BY_ID)) {
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Order order = createFromResultSet(resultSet);
                optionalOrder = Optional.of(order);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeResultSet(resultSet);
        }
        return optionalOrder;
    }

    @Override
    public List<Order> findAll() throws DaoException {
        List<Order> orders = new ArrayList<>();
        ResultSet resultSet = null;
        try (PreparedStatement statement = connection.prepareStatement(FIND_ALL)) {
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Order order = createFromResultSet(resultSet);
                orders.add(order);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeResultSet(resultSet);
        }
        return orders;
    }

    @Override
    public List<Order> findByStatus(Order.Status status) throws DaoException {
        List<Order> orders = new ArrayList<>();
        ResultSet resultSet = null;
        try (PreparedStatement statement = connection.prepareStatement(FIND_ORDERS_BY_STATUS)) {
            statement.setInt(1, status.getOrderStatusId());
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Order order = createFromResultSet(resultSet);
                orders.add(order);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeResultSet(resultSet);
        }
        return orders;
    }

    @Override
    public List<Order> findByUserId(long id) throws DaoException {
        List<Order> orders = new ArrayList<>();
        ResultSet resultSet = null;
        try (PreparedStatement statement = connection.prepareStatement(FIND_ORDERS_BY_USER_ID)) {
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Order order = createFromResultSet(resultSet);
                orders.add(order);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeResultSet(resultSet);
        }
        return orders;
    }

    @Override
    public List<Order> findByUserIdAndOrderStatus(long id, Order.Status status) throws DaoException {
        List<Order> orders = new ArrayList<>();
        ResultSet resultSet = null;
        try (PreparedStatement statement = connection.prepareStatement(FIND_ORDERS_BY_USER_ID_AND_ORDER_STATUS)) {
            statement.setLong(1, id);
            statement.setInt(2, status.getOrderStatusId());
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Order order = createFromResultSet(resultSet);
                orders.add(order);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeResultSet(resultSet);
        }
        return orders;
    }

    @Override
    public boolean add(Order order) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(ADD_ORDER)) {
            statement.setLong(1, order.getOrderId());
            statement.setLong(2, DateConverter.convertToLong(order.getOrderDate()));
            statement.setLong(3, DateConverter.convertToLong(order.getReturnDate()));
            statement.setBigDecimal(4, order.getTotalCost());
            statement.setInt(5, order.getStatus().getOrderStatusId());
            statement.setLong(6, order.getUser().getUserId());
            statement.setLong(7, order.getTool().getToolId());
            return (statement.executeUpdate() == 1);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean updateStatus(long id, Order.Status status) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_ORDER_STATUS)) {
            statement.setInt(1, status.getOrderStatusId());
            statement.setLong(2, id);
            return (statement.executeUpdate() == 1);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void delete(long id) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_ORDER)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private Order createFromResultSet(ResultSet resultSet) throws DaoException {
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
            User user = new User(userBuilder);

            byte[] photoBytes = resultSet.getBytes(ColumnName.TOOL_PHOTO);
            String photoBase64 = Base64.getEncoder().encodeToString(photoBytes);
            ToolBuilder toolBuilder = new ToolBuilder()
                    .setToolId(resultSet.getLong(ColumnName.TOOL_ID))
                    .setType(Tool.Type.getToolTypeById(resultSet.getInt(ColumnName.TOOL_TYPE_ID)))
                    .setModel(resultSet.getString(ColumnName.TOOL_MODEL))
                    .setDescriptionEng(resultSet.getString(ColumnName.TOOL_DESCRIPTION_ENG))
                    .setDescriptionRus(resultSet.getString(ColumnName.TOOL_DESCRIPTION_RUS))
                    .setAvailable(resultSet.getBoolean(ColumnName.TOOL_IS_AVAILABLE))
                    .setRentPrice(resultSet.getBigDecimal(ColumnName.TOOL_RENT_PRICE))
                    .setPhoto(photoBase64);
            Tool tool = new Tool(toolBuilder);

            OrderBuilder orderBuilder = new OrderBuilder()
                    .setUser(user)
                    .setTool(tool)
                    .setOrderId(resultSet.getLong(ColumnName.ORDER_ID))
                    .setOrderDate(DateConverter.convertToDate(resultSet.getLong(ColumnName.ORDER_DATE)))
                    .setReturnDate(DateConverter.convertToDate(resultSet.getLong(ColumnName.ORDER_RETURN_DATE)))
                    .setStatus(Order.Status.getOrderStatusById(resultSet.getInt(ColumnName.ORDER_STATUS_ID)))
                    .setTotalCost(resultSet.getBigDecimal(ColumnName.ORDER_TOTAL_COST));
            return new Order(orderBuilder);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
