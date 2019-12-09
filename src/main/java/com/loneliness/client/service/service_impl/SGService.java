package com.loneliness.client.service.service_impl;

import com.loneliness.client.dao.DAOException;
import com.loneliness.client.dao.FactoryDAO;
import com.loneliness.client.dao.server_request.SGRequest;
import com.loneliness.client.service.CRUDService;
import com.loneliness.client.service.ServiceException;
import com.loneliness.entity.Quarter;
import com.loneliness.entity.ReportingPeriod;
import com.loneliness.entity.SG;

import java.util.Map;

public class SGService implements CRUDService<SG,String, Map<Integer,SG>> {
    private SGService(){}
    private SGRequest dao= FactoryDAO.getInstance().getSgRequest();
    private static final SGService instance=new SGService();


    public static SGService getInstance() {
        return instance;
    }


    @Override
    public String update(SG note) throws ServiceException {

        try {
            return dao.update(note);
        } catch (DAOException e) {
            throw new ServiceException(e.getCause(),e.getMessage());
        }
    }

    @Override
    public SG receive(SG note)throws ServiceException {
        try {
            return dao.receive(note);
        } catch (DAOException e) {
            throw new ServiceException(e.getCause(),e.getMessage());
        }
    }

    @Override
    public String delete(SG note)throws ServiceException {
        try {
            return dao.delete(note);
        } catch (DAOException e) {
            throw new ServiceException(e.getCause(),e.getMessage());
        }
    }

    @Override
    public String create(SG obj) throws ServiceException {
        try {
            return dao.add(obj);
        } catch (DAOException e) {
            throw new ServiceException(e.getCause(),e.getMessage());
        }
    }

    @Override
    public Map<Integer, SG> receiveAllElem() throws ServiceException {
        try {
            return dao.receiveAll();
        } catch (DAOException e) {
            throw new ServiceException(e.getCause(),e.getMessage());
        }
    }

    @Override
    public Map<Integer, SG> receiveAllElemInLimit(int left, int right) throws ServiceException {
        try {
            return dao.receiveAllInLimit(left, right);
        } catch (DAOException e) {
            throw new ServiceException(e.getCause(),e.getMessage());
        }
    }

    public SG findSGByReportingPeriodID(int id) throws ServiceException {
        try {
            return dao.findSGByReportingPeriodID(id);
        } catch (DAOException e) {
            throw new ServiceException(e.getCause(), e.getMessage());
        }
    }
    public  Map<Quarter, SG> findSGByReportingPeriodYear(ReportingPeriod note) throws ServiceException {
        try {
            return dao.findSGByReportingPeriodYear(note);
        } catch (DAOException e) {
            throw new ServiceException(e.getCause(), e.getMessage());
        }
    }

}
