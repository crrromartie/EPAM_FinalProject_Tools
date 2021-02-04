package by.gaponenko.tools.model.service.impl;

import by.gaponenko.tools.builder.ToolBuilder;
import by.gaponenko.tools.controller.command.ParameterName;
import by.gaponenko.tools.entity.Tool;
import by.gaponenko.tools.exception.DaoException;
import by.gaponenko.tools.exception.ServiceException;
import by.gaponenko.tools.model.dao.ToolDao;
import by.gaponenko.tools.model.dao.impl.ToolDaoImpl;
import by.gaponenko.tools.model.service.EntityTransaction;
import by.gaponenko.tools.model.service.ToolService;
import by.gaponenko.tools.util.ImageCompressor;
import by.gaponenko.tools.util.comparator.ToolComparator;
import by.gaponenko.tools.validator.ToolDataValidator;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * The Tool service.
 * <p>
 * Implements an interface ToolService for processing tool-related data.
 *
 * @author Haponenka Ihar
 * @version 1.0
 * @see ToolService
 */
public class ToolServiceImpl implements ToolService {

    @Override
    public Optional<Tool> findById(long id) throws ServiceException {
        ToolDao toolDao = new ToolDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.initSingleQuery(toolDao);
        try {
            return toolDao.findById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.endSingleQuery();
        }
    }

    @Override
    public List<Tool> findAll() throws ServiceException {
        ToolDao toolDao = new ToolDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.initSingleQuery(toolDao);
        try {
            return toolDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.endSingleQuery();
        }
    }

    @Override
    public List<Tool> findByType(Tool.Type type) throws ServiceException {
        if (!ToolDataValidator.isValidToolType(type)) {
            return Collections.EMPTY_LIST;
        }
        ToolDao toolDao = new ToolDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.initSingleQuery(toolDao);
        try {
            return toolDao.findByType(type);
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.endSingleQuery();
        }
    }

    @Override
    public boolean add(Map<String, String> toolParameters) throws ServiceException {
        if (!ToolDataValidator.isValidToolParameters(toolParameters)) {
            return false;
        }
        ToolDao toolDao = new ToolDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.initSingleQuery(toolDao);
        ToolBuilder toolBuilder = new ToolBuilder()
                .setType(Tool.Type.valueOf(toolParameters.get(ParameterName.TOOL_TYPE).toUpperCase()))
                .setModel(toolParameters.get(ParameterName.TOOL_MODEL))
                .setDescriptionEng(toolParameters.get(ParameterName.TOOL_DESCRIPTION_ENG))
                .setDescriptionRus(toolParameters.get(ParameterName.TOOL_DESCRIPTION_RUS))
                .setRentPrice(BigDecimal.valueOf(Double.parseDouble(toolParameters.get(ParameterName.TOOL_RENT_PRICE))));
        Tool tool = new Tool(toolBuilder);
        try {
            return toolDao.add(tool);
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.endSingleQuery();
        }
    }

    @Override
    public Optional<Tool> updateTool(Map<String, String> toolEditParameters,
                                     boolean isAvailable,
                                     long id) throws ServiceException {
        if (!ToolDataValidator.isValidEditToolParameters(toolEditParameters)) {
            return Optional.empty();
        }
        ToolDao toolDao = new ToolDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.initSingleQuery(toolDao);
        ToolBuilder toolBuilder = new ToolBuilder()
                .setToolId(id)
                .setDescriptionEng(toolEditParameters.get(ParameterName.TOOL_DESCRIPTION_ENG))
                .setDescriptionRus(toolEditParameters.get(ParameterName.TOOL_DESCRIPTION_RUS))
                .setRentPrice(BigDecimal.valueOf(Double.parseDouble(toolEditParameters
                        .get(ParameterName.TOOL_RENT_PRICE))))
                .setAvailable(isAvailable);
        Tool tool = new Tool(toolBuilder);
        try {
            return toolDao.updateTool(tool);
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.endSingleQuery();
        }
    }

    @Override
    public boolean updateToolPhoto(long id, InputStream toolPhoto) throws ServiceException {
        if (toolPhoto == null) {
            return false;
        }
        ToolDao toolDao = new ToolDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.initSingleQuery(toolDao);
        InputStream compressedPhoto = ImageCompressor.compressPhoto(toolPhoto);
        try {
            return toolDao.updateToolPhoto(id, compressedPhoto);
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.endSingleQuery();
        }
    }

    @Override
    public boolean isModelUnique(String model) throws ServiceException {
        if (!ToolDataValidator.isValidModel(model)) {
            return false;
        }
        ToolDao toolDao = new ToolDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.initSingleQuery(toolDao);
        try {
            return toolDao.findByModel(model).isEmpty();
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.endSingleQuery();
        }
    }

    @Override
    public List<Tool> sortByCriteria(List<Tool> tools, String criteria) throws ServiceException {
        try {
            Comparator<Tool> toolComparator = ToolComparator.valueOf(criteria.toUpperCase()).getComparator();
            return (tools.stream().sorted(toolComparator).collect(Collectors.toList()));
        } catch (IllegalArgumentException e) {
            throw new ServiceException("Criteria is not exist", e);
        }
    }
}
