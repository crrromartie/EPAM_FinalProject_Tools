package by.gaponenko.tools.model.service;

import by.gaponenko.tools.entity.Tool;
import by.gaponenko.tools.exception.ServiceException;

import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * The interface Tool service.
 * <p>
 * Indicates methods for processing information related to tool.
 *
 * @author Haponenka Ihar
 * @version 1.0
 */
public interface ToolService {

    /**
     * Find tool by id.
     *
     * @param id the id
     * @return the optional
     * @throws ServiceException the Service exception
     */
    Optional<Tool> findById(long id) throws ServiceException;

    /**
     * Find all tools.
     *
     * @return the list
     * @throws ServiceException the Service exception
     */
    List<Tool> findAll() throws ServiceException;

    /**
     * Find all tools by type.
     *
     * @param toolType the toolType
     * @return the list
     * @throws ServiceException the Service exception
     */
    List<Tool> findByType(String toolType) throws ServiceException;

    /**
     * Add new  tool.
     *
     * @param toolParameters the toolParameters
     * @return the boolean
     * @throws ServiceException the Service exception
     */
    boolean add(Map<String, String> toolParameters) throws ServiceException;

    /**
     * Update tool info.
     *
     * @param toolEditParameters the toolEditParameters
     * @param isAvailable        the isAvailable
     * @param id                 the id
     * @return the boolean
     * @throws ServiceException the Service exception
     */
    Optional<Tool> updateTool(Map<String, String> toolEditParameters, boolean isAvailable, long id)
            throws ServiceException;

    /**
     * Update tool photo.
     *
     * @param id        the id
     * @param toolPhoto the toolPhoto
     * @return the boolean
     * @throws ServiceException the Service exception
     */
    boolean updateToolPhoto(long id, InputStream toolPhoto) throws ServiceException;

    /**
     * Model uniqueness check.
     *
     * @param model the model
     * @return the boolean
     * @throws ServiceException the Service exception
     */
    boolean isModelUnique(String model) throws ServiceException;

    /**
     * Sort by criteria.
     *
     * @param tools    the tool
     * @param criteria the criteria
     * @return the list
     * @throws ServiceException the Service exception
     */
    List<Tool> sortByCriteria(List<Tool> tools, String criteria) throws ServiceException;
}
