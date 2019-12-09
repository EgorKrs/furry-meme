package com.loneliness.client.dao.server_request;

import com.loneliness.client.dao.DAOException;
import com.loneliness.client.dao.IDAO;
import com.loneliness.client.launcher.Client;
import com.loneliness.entity.Quarter;
import com.loneliness.entity.ReportingPeriod;
import com.loneliness.entity.SG;
import com.loneliness.entity.Transmission;

import java.io.IOException;
import java.util.Map;

public class SGRequest implements IDAO<SG,String, Map<Integer,SG>> {
    private static final SGRequest instance=new SGRequest();
    private SGRequest(){}
    public static SGRequest getInstance() {
        return instance;
    }
    private Transmission transmission;
    @Override
    public String add(SG note) throws DAOException {
        transmission = new Transmission();
        transmission.setCommand("CREATE_SG");
        transmission.setSg(note);
        try {
            Client.getOutObject().writeObject(transmission);
            return (String) Client.getInObject().readObject();
        } catch (IOException e) {
            throw new DAOException(e.getMessage(), e.getCause(), "Ошибка соединения с сервером");
        } catch (ClassNotFoundException  |ClassCastException e) {
            throw new DAOException(e.getMessage(), e.getCause(), "Не верный ответ с сервера");
        }
    }

    @Override
    public String update(SG note) throws DAOException {
        transmission = new Transmission();
        transmission.setCommand("UPDATE_SG");
        transmission.setSg(note);
        try {
            Client.getOutObject().writeObject(transmission);
            return (String) Client.getInObject().readObject();
        } catch (IOException e) {
            throw new DAOException(e.getMessage(), e.getCause(), "Ошибка соединения с сервером");
        } catch (ClassNotFoundException  |ClassCastException e) {
            throw new DAOException(e.getMessage(), e.getCause(), "Не верный ответ с сервера");
        }
    }

    @Override
    public SG receive(SG note) throws DAOException {
        transmission = new Transmission();
        transmission.setCommand("RECEIVE_SG");
        transmission.setSg(note);
        try {
            Client.getOutObject().writeObject(transmission);
            return (SG) Client.getInObject().readObject();
        } catch (IOException e) {
            throw new DAOException(e.getMessage(), e.getCause(), "Ошибка соединения с сервером");
        } catch (ClassNotFoundException  |ClassCastException e) {
            throw new DAOException(e.getMessage(), e.getCause(), "Не верный ответ с сервера");
        }
    }

    @Override
    public String delete(SG note) throws DAOException {
        transmission = new Transmission();
        transmission.setCommand("DELETE_SG");
        transmission.setSg(note);
        try {
            Client.getOutObject().writeObject(transmission);
            return (String) Client.getInObject().readObject();
        } catch (IOException e) {
            throw new DAOException(e.getMessage(), e.getCause(), "Ошибка соединения с сервером");
        } catch (ClassNotFoundException  |ClassCastException e) {
            throw new DAOException(e.getMessage(), e.getCause(), "Не верный ответ с сервера");
        }
    }

    @Override
    public Map<Integer, SG> receiveAll() throws DAOException {
        transmission = new Transmission();
        transmission.setCommand("RECEIVE_ALL_SG");

        try {
            Client.getOutObject().writeObject(transmission);
            return (Map<Integer, SG>) Client.getInObject().readObject();
        } catch (IOException e) {
            throw new DAOException(e.getMessage(), e.getCause(), "Ошибка соединения с сервером");
        } catch (ClassNotFoundException  |ClassCastException e) {
            throw new DAOException(e.getMessage(), e.getCause(), "Не верный ответ с сервера");
        }
    }

    @Override
    public Map<Integer, SG> receiveAllInLimit(int left, int right) throws DAOException {
        transmission = new Transmission();
        transmission.setCommand("RECEIVE_ALL_SG_IN_LIMIT");
        transmission.setBounds(new int[]{left,right});
        try {
            Client.getOutObject().writeObject(transmission);
            return (Map<Integer, SG>) Client.getInObject().readObject();
        } catch (IOException e) {
            throw new DAOException(e.getMessage(), e.getCause(), "Ошибка соединения с сервером");
        } catch (ClassNotFoundException |ClassCastException e) {
            throw new DAOException(e.getMessage(), e.getCause(), "Не верный ответ с сервера");
        }
    }

    public SG findSGByReportingPeriodID(int note) throws DAOException {
        transmission = new Transmission();
        transmission.setCommand("FIND_SG_BY_REPORTING_PERIOD_ID");
        transmission.setId(note);
        try {
            Client.getOutObject().writeObject(transmission);
            return (SG) Client.getInObject().readObject();
        } catch (IOException e) {
            throw new DAOException(e.getMessage(), e.getCause(), "Ошибка соединения с сервером");
        } catch (ClassNotFoundException  |ClassCastException e) {
            throw new DAOException(e.getMessage(), e.getCause(), "Не верный ответ с сервера");
        }
    }
    public  Map<Quarter, SG> findSGByReportingPeriodYear(ReportingPeriod note)throws DAOException {
        transmission = new Transmission();
        transmission.setCommand("FIND_SG_BY_REPORTING_PERIOD_YEAR");
        transmission.setReportingPeriod(note);
        try {
            Client.getOutObject().writeObject(transmission);
            return (Map<Quarter, SG>) Client.getInObject().readObject();
        } catch (IOException e) {
            throw new DAOException(e.getMessage(), e.getCause(), "Ошибка соединения с сервером");
        } catch (ClassNotFoundException | ClassCastException e) {
            throw new DAOException(e.getMessage(), e.getCause(), "Не верный ответ с сервера");
        }
    }
}
