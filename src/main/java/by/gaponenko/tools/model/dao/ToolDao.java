package by.gaponenko.tools.model.dao;

import by.gaponenko.tools.entity.Tool;
import by.gaponenko.tools.exception.DaoException;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;

public interface ToolDao extends Dao<Tool> {

    Optional<Tool> findByModel(String model) throws DaoException;

    List<Tool> findByType(Tool.Type type) throws DaoException;

    boolean add(Tool tool) throws DaoException;

    boolean activateTool(long id) throws DaoException;

    boolean inactivateTool(long id) throws DaoException;

    Optional<Tool> updateTool(Tool tool) throws DaoException;

    boolean updateToolPhoto(long id, InputStream toolPhoto) throws DaoException;
}
