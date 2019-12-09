package com.loneliness.client.service.service_impl;

import com.loneliness.client.dao.DAOException;
import com.loneliness.client.dao.FactoryDAO;
import com.loneliness.client.dao.server_request.UserRequest;
import com.loneliness.client.service.CRUDService;
import com.loneliness.client.service.ServiceException;
import com.loneliness.entity.UserData;

import java.util.Map;

public class UserService implements CRUDService<UserData,String, Map<Integer,UserData>> {
    private UserRequest request= FactoryDAO.getInstance().getUserRequest();
    @Override
    public String create(UserData obj) throws ServiceException {
        try {
            return request.add(obj);
        } catch (DAOException e) {
            throw new ServiceException(e.getCause(),e.getMessage());
        }
    }

    @Override
    public UserData receive(UserData obj) throws ServiceException {
        try {
            return request.receive(obj);
        } catch (DAOException e) {
            throw new ServiceException(e.getCause(),e.getMessage());
        }
    }

    @Override
    public String update(UserData obj) throws ServiceException {
        try {
            return request.update(obj);
        } catch (DAOException e) {
            throw new ServiceException(e.getCause(),e.getMessage());
        }
    }

    @Override
    public String delete(UserData obj) throws ServiceException {
        try {
            return request.delete(obj);
        } catch (DAOException e) {
            throw new ServiceException(e.getCause(),e.getMessage());
        }
    }

    @Override
    public Map<Integer, UserData> receiveAllElem() throws ServiceException {
        try {
            return request.receiveAll();
        } catch (DAOException e) {
            throw new ServiceException(e.getCause(),e.getMessage());
        }
    }

    @Override
    public Map<Integer, UserData> receiveAllElemInLimit(int left, int right) throws ServiceException {
        try {
            return request.receiveAllInLimit(left, right);
        } catch (DAOException e) {
            throw new ServiceException(e.getCause(),e.getMessage());
        }
    }
    public UserData authorise(UserData obj) throws ServiceException {
        try {
            return request.authorise(obj);
        } catch (DAOException e) {
            throw new ServiceException(e.getCause(),e.getMessage());
        }
    }
    public Map<Integer, UserData> receiveAllManager() throws ServiceException {
        try {
            return request.receiveAllManager();
        } catch (DAOException e) {
            throw new ServiceException(e.getCause(),e.getMessage());
        }
    }
}
