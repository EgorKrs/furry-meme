package com.loneliness.server.servise.service_impl;

import com.loneliness.entity.Quarter;
import com.loneliness.entity.ReportingPeriod;
import com.loneliness.entity.SG;
import com.loneliness.server.dao.DAOFactory;
import com.loneliness.server.dao.sql_dao.SQLSGDAO;
import com.loneliness.server.servise.DataService;
import com.loneliness.server.servise.ServiceException;
import com.loneliness.server.servise.ServiceFactory;

import java.util.Map;

public class SGService implements DataService<SG,String, Map<Integer,SG>> {
    private SGService(){}
    private SQLSGDAO dao= DAOFactory.getInstance().getSgDAO();
    private static final SGService instance=new SGService();
    private BusinessServiceImpl calculator= ServiceFactory.getInstance().getBusinessService();

    public static SGService getInstance() {
        return instance;
    }

    @Override
    public String add(SG note) throws ServiceException {
        note.setSG(calculator.calculateSG(note));
        return dao.add(note);
    }

    @Override
    public String update(SG note) throws ServiceException {
        note.setSG(calculator.calculateSG(note));
        return dao.update(note);
    }

    @Override
    public SG receive(SG note) {
        return dao.receive(note);
    }

    @Override
    public String delete(SG note) {
        return dao.delete(note);
    }

    @Override
    public Map<Integer, SG> receiveAll() {
        return dao.receiveAll();
    }

    @Override
    public Map<Integer, SG> receiveAllInLimit(int left, int right) {
        return dao.receiveAllInLimit(left, right);
    }

    public SG findSGByReportingPeriodID(int id){
        return dao.findSGByReportingPeriodID(id);
    }
    public Map<Quarter, SG>  findSGByReportingPeriodYear(ReportingPeriod reportingPeriod){
        return dao.findSGByReportingPeriodYear(reportingPeriod);
    }
}
