package by.gaponenko.tools.model.dao.impl;

import by.gaponenko.tools.entity.Tool;
import by.gaponenko.tools.exception.DaoException;
import by.gaponenko.tools.model.dao.ToolDao;
import by.gaponenko.tools.model.pool.ConnectionPool;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

public class ToolDaoImpl implements ToolDao {
    static Logger logger = LogManager.getLogger();

    private static final String DEFAULT_TOOL_PHOTO_PATH = "C:\\Users\\IGOR\\IdeaProjects" +
            "\\EPAM_FinalProject_Tools\\photo\\default_tool.jpg";

    private static final String FIND_BY_ID = "SELECT tool_id, tool_type_id, model, description_eng, description_rus," +
            "is_available, rent_price, photo FROM tools WHERE tool_id = ?";
    private static final String FIND_ALL = "SELECT tool_id, tool_type_id, model, description_eng, description_rus," +
            "is_available, rent_price, photo FROM tools";
    private static final String FIND_BY_MODEL = "SELECT tool_id, tool_type_id, model, description_eng, description_rus," +
            "is_available, rent_price, photo FROM tools WHERE model = ?";
    private static final String FIND_TOOLS_BY_TYPE = "SELECT tool_id, tool_type_id, model, description_eng, description_rus," +
            "is_available, rent_price, photo FROM tools WHERE tool_type_id = ?";
    private static final String ADD_TOOL = "INSERT INTO tools (tool_type_id, model, description_eng, description_rus," +
            "rent_price, photo) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String ACTIVATE_TOOL = "UPDATE tools SET is_available = true WHERE tool_id = ?";
    private static final String INACTIVATE_TOOL = "UPDATE tools SET is_available = false WHERE tool_id = ?";
    private static final String UPDATE_TOOL = "UPDATE tools SET description_rus = ?, description_eng = ?, " +
            "rent_price = ?, is_available = ?  WHERE tool_id = ?";
    private static final String UPDATE_TOOL_PHOTO = "UPDATE tools SET photo = ? WHERE tool_id = ?";

    @Override
    public Optional<Tool> findById(long id) throws DaoException {
        Optional<Tool> optionalTool = Optional.empty();
        Tool tool = null;
        ResultSet resultSet = null;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_ID)) {
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                tool = createFromResultSet(resultSet);
                optionalTool = Optional.of(tool);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeResultSet(resultSet);
        }
        return optionalTool;
    }

    @Override
    public List<Tool> findAll() throws DaoException {
        List<Tool> tools = new ArrayList<>();
        ResultSet resultSet = null;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL)) {
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Tool tool = createFromResultSet(resultSet);
                tools.add(tool);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeResultSet(resultSet);
        }
        return tools;
    }

    @Override
    public Optional<Tool> findByModel(String model) throws DaoException {
        Optional<Tool> optionalTool = Optional.empty();
        Tool tool = null;
        ResultSet resultSet = null;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_MODEL)) {
            statement.setString(1, model);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                tool = createFromResultSet(resultSet);
                optionalTool = Optional.of(tool);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeResultSet(resultSet);
        }
        return optionalTool;
    }

    @Override
    public List<Tool> findByType(Tool.Type type) throws DaoException {
        List<Tool> tools = new ArrayList<>();
        ResultSet resultSet = null;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_TOOLS_BY_TYPE)) {
            statement.setInt(1, type.getToolTypeId());
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Tool tool = createFromResultSet(resultSet);
                tools.add(tool);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeResultSet(resultSet);
        }
        return tools;
    }

    @Override
    public boolean add(Tool tool) throws DaoException {
        InputStream fileInputStream = null;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(ADD_TOOL)) {
            statement.setInt(1, tool.getType().getToolTypeId());
            statement.setString(2, tool.getModel());
            statement.setString(3, tool.getDescriptionEng());
            statement.setString(4, tool.getDescriptionRus());
            statement.setBigDecimal(5, tool.getRentPrice());
            File file = new File(DEFAULT_TOOL_PHOTO_PATH);
            fileInputStream = new FileInputStream(file);
            statement.setBlob(6, fileInputStream);
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
    public boolean activateTool(long id) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(ACTIVATE_TOOL)) {
            statement.setLong(1, id);
            return (statement.executeUpdate() > 0);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean inactivateTool(long id) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(INACTIVATE_TOOL)) {
            statement.setLong(1, id);
            return (statement.executeUpdate() > 0);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<Tool> updateTool(Tool tool) throws DaoException {
        Optional<Tool> optionalTool = Optional.empty();
        Tool updatedTool = null;
        ResultSet resultSet = null;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_TOOL);
             PreparedStatement updateStatment = connection.prepareStatement(FIND_BY_ID)) {
            statement.setString(1, tool.getDescriptionRus());
            statement.setString(2, tool.getDescriptionEng());
            statement.setBigDecimal(3, tool.getRentPrice());
            statement.setBoolean(4, tool.isAvailable());
            statement.setLong(5, tool.getToolId());
            statement.executeUpdate();

            updateStatment.setLong(1, tool.getToolId());
            resultSet = updateStatment.executeQuery();
            if (resultSet.next()) {
                updatedTool = createFromResultSet(resultSet);
                optionalTool = Optional.of(updatedTool);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeResultSet(resultSet);
        }
        return optionalTool;
    }

    @Override
    public boolean updateToolPhoto(long id, InputStream toolPhoto) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_TOOL_PHOTO)) {
            statement.setBlob(1, toolPhoto);
            statement.setLong(2, id);
            return (statement.executeUpdate() > 0);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private Tool createFromResultSet(ResultSet resultSet) throws DaoException {
        Tool tool = new Tool();
        try {
            tool.setToolId(resultSet.getLong(ColumnName.TOOL_ID));
            tool.setType(Tool.Type.getToolTypeById(resultSet.getInt(ColumnName.TOOL_TYPE_ID)));
            tool.setModel(resultSet.getString(ColumnName.TOOL_MODEL));
            tool.setDescriptionEng(resultSet.getString(ColumnName.TOOL_DESCRIPTION_ENG));
            tool.setDescriptionRus(resultSet.getString(ColumnName.TOOL_DESCRIPTION_RUS));
            tool.setAvailable(resultSet.getBoolean(ColumnName.TOOL_IS_AVAILABLE));
            tool.setRentPrice(resultSet.getBigDecimal(ColumnName.TOOL_RENT_PRICE));
            byte[] photoBytes = resultSet.getBytes(ColumnName.TOOL_PHOTO);
            String photoBase64 = Base64.getEncoder().encodeToString(photoBytes);
            tool.setPhoto(photoBase64);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return tool;
    }
}
