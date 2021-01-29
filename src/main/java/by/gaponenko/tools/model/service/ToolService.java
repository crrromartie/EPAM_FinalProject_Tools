package by.gaponenko.tools.model.service;

import by.gaponenko.tools.entity.Tool;
import by.gaponenko.tools.exception.ServiceException;

import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ToolService {

    Optional<Tool> findById(long id) throws ServiceException;

    List<Tool> findAll() throws ServiceException;

    List<Tool> findByType(Tool.Type type) throws ServiceException;

    boolean add(Map<String, String> toolParameters) throws ServiceException;

    Optional<Tool> updateTool(Map<String, String> toolEditParameters, boolean isAvailable, long id) throws ServiceException;

    boolean updateToolPhoto(long id, InputStream toolPhoto) throws ServiceException;

    boolean isModelUnique(String model) throws ServiceException;

    List<Tool> sortByCriteria(List<Tool> tools, String criteria) throws ServiceException;
}
