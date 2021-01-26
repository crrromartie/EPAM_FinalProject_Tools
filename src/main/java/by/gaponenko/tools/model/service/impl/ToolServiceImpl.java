package by.gaponenko.tools.model.service.impl;

import by.gaponenko.tools.creator.ToolCreator;
import by.gaponenko.tools.entity.Tool;
import by.gaponenko.tools.exception.DaoException;
import by.gaponenko.tools.exception.ServiceException;
import by.gaponenko.tools.model.dao.DaoFactory;
import by.gaponenko.tools.model.dao.ToolDao;
import by.gaponenko.tools.model.service.ToolService;
import by.gaponenko.tools.util.ImageCompressor;
import by.gaponenko.tools.util.comparator.ToolComparator;
import by.gaponenko.tools.validator.ToolDataValidator;

import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

public class ToolServiceImpl implements ToolService {
    @Override
    public Optional<Tool> findById(long id) throws ServiceException {
        Optional<Tool> optionalTool = Optional.empty();
        DaoFactory factory = DaoFactory.getINSTANCE();
        ToolDao toolDao = factory.getToolDao();
        try {
            optionalTool = toolDao.findById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return optionalTool;
    }

    @Override
    public List<Tool> findAll() throws ServiceException {
        List<Tool> tools;
        DaoFactory factory = DaoFactory.getINSTANCE();
        ToolDao toolDao = factory.getToolDao();
        try {
            tools = toolDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return tools;
    }

    @Override
    public List<Tool> findByType(Tool.Type type) throws ServiceException {
        List<Tool> tools = Collections.EMPTY_LIST;
        DaoFactory factory = DaoFactory.getINSTANCE();
        ToolDao toolDao = factory.getToolDao();
        if (ToolDataValidator.isValidToolType(type)) {
            try {
                tools = toolDao.findByType(type);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return tools;
    }

    @Override
    public boolean add(Map<String, String> toolParameters) throws ServiceException {
        boolean isAdd = false;
        DaoFactory factory = DaoFactory.getINSTANCE();
        ToolDao toolDao = factory.getToolDao();
        if (ToolDataValidator.isValidToolParameters(toolParameters)) {
            Tool tool = ToolCreator.createTool(toolParameters);
            try {
                isAdd = toolDao.add(tool);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return isAdd;
    }

    @Override
    public boolean activateTool(long id) throws ServiceException {
        DaoFactory factory = DaoFactory.getINSTANCE();
        ToolDao toolDao = factory.getToolDao();
        try {
            return toolDao.activateTool(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean inactivateTool(long id) throws ServiceException {
        DaoFactory factory = DaoFactory.getINSTANCE();
        ToolDao toolDao = factory.getToolDao();
        try {
            return toolDao.inactivateTool(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<Tool> updateTool(Map<String, String> toolEditParameters, boolean isAvailable, long id)
            throws ServiceException {
        DaoFactory factory = DaoFactory.getINSTANCE();
        ToolDao toolDao = factory.getToolDao();
        Optional<Tool> updateTool = Optional.empty();
        if (ToolDataValidator.isValidEditToolParameters(toolEditParameters)) {
            Tool editTool = ToolCreator.createTool(toolEditParameters, isAvailable, id);
            try {
                updateTool = toolDao.updateTool(editTool);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return updateTool;
    }

    @Override
    public boolean updateToolPhoto(long id, InputStream toolPhoto) throws ServiceException {
        DaoFactory factory = DaoFactory.getINSTANCE();
        ToolDao toolDao = factory.getToolDao();
        InputStream compressedPhoto = ImageCompressor.compressPhoto(toolPhoto);
        try {
            return toolDao.updateToolPhoto(id, compressedPhoto);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Tool> sortByCriteria(List<Tool> tools, String criteria) throws ServiceException {
        try {
            Comparator<Tool> toolComparator = ToolComparator.valueOf(criteria.toUpperCase()).getComparator();
            return (tools.stream().sorted(toolComparator).collect(Collectors.toList()));
        } catch (IllegalArgumentException e) {
            throw new ServiceException("Criteria not exist!", e);
        }
    }

    @Override
    public boolean isModelUnique(String model) throws ServiceException {
        boolean isUnique = false;
        DaoFactory factory = DaoFactory.getINSTANCE();
        ToolDao toolDao = factory.getToolDao();
        if (ToolDataValidator.isValidModel(model)) {
            try {
                if (toolDao.findByModel(model).isEmpty()) {
                    isUnique = true;
                }
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return isUnique;
    }
}
