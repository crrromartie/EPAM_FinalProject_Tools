package by.gaponenko.tools.model.dao;

import by.gaponenko.tools.entity.Tool;
import by.gaponenko.tools.exception.DaoException;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;

/**
 * The interface Tool dao.
 * <p>
 * Extends the interface of the {@code Dao}, supplementing it with specific
 * methods for the interaction of the application with Tool entities in the database.
 *
 * @version 1.0
 * @see Dao * @author Ihar Haponenka
 */
public interface ToolDao extends Dao<Tool> {

    /**
     * Find a tool by model.
     *
     * @param model the model
     * @return the optional
     * @throws DaoException the dao exception
     */
    Optional<Tool> findByModel(String model) throws DaoException;

    /**
     * Find all tool by type in the database.
     *
     * @param type the type
     * @return the list
     * @throws DaoException the dao exception
     */
    List<Tool> findByType(Tool.Type type) throws DaoException;

    /**
     * Adding a new tool to the database.
     *
     * @param tool the tool
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean add(Tool tool) throws DaoException;

    /**
     * Activate tool.
     *
     * @param id the id
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean activateTool(long id) throws DaoException;

    /**
     * Inactivate tool.
     *
     * @param id the id
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean inactivateTool(long id) throws DaoException;

    /**
     * Update tool info.
     *
     * @param tool the tool
     * @return the optional
     * @throws DaoException the dao exception
     */
    Optional<Tool> updateTool(Tool tool) throws DaoException;

    /**
     * Update tool photo.
     *
     * @param id        the id
     * @param toolPhoto the toolPhoto
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean updateToolPhoto(long id, InputStream toolPhoto) throws DaoException;
}
