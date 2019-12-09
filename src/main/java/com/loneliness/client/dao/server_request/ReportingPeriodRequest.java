package com.loneliness.client.dao.server_request;

import com.loneliness.client.dao.DAOException;
import com.loneliness.client.dao.IDAO;
import com.loneliness.client.launcher.Client;
import com.loneliness.entity.ReportingPeriod;
import com.loneliness.entity.Transmission;

import java.io.IOException;
import java.util.Map;

public class ReportingPeriodRequest implements IDAO<ReportingPeriod,String, Map<Integer,ReportingPeriod>> {

    private static final ReportingPeriodRequest instance=new ReportingPeriodRequest();
    private ReportingPeriodRequest(){}
    public static ReportingPeriodRequest getInstance() {
        return instance;
    }
    private Transmission transmission;

    @Override
    public String add(ReportingPeriod note) throws DAOException {
        transmission = new Transmission();
        transmission.setCommand("CREATE_REPORTING_PERIOD");
        transmission.setReportingPeriod(note);
        try {
            Client.getOutObject().writeObject(transmission);
            return (String) Client.getInObject().readObject();
        } catch (IOException e) {
            throw new DAOException(e.getMessage(), e.getCause(), "Ошибка соединения с сервером");
        } catch (ClassNotFoundException e) {
            throw new DAOException(e.getMessage(), e.getCause(), "Не верный ответ с сервера");
        }catch (ClassCastException e){
            throw new DAOException(e.getMessage(), e.getCause(), "Не валидные данные отправлены были");
        }
    }

    @Override
    public String update(ReportingPeriod note) throws DAOException {
        transmission = new Transmission();
        transmission.setCommand("UPDATE_REPORTING_PERIOD");
        transmission.setReportingPeriod(note);
        try {
            Client.getOutObject().writeObject(transmission);
            return (String) Client.getInObject().readObject();
        } catch (IOException e) {
            throw new DAOException(e.getMessage(), e.getCause(), "Ошибка соединения с сервером");
        } catch (ClassNotFoundException e) {
            throw new DAOException(e.getMessage(), e.getCause(), "Не верный ответ с сервера");
        }catch (ClassCastException e){
            throw new DAOException(e.getMessage(), e.getCause(), "Не валидные данные отправлены были");
        }
    }

    @Override
    public ReportingPeriod receive(ReportingPeriod note) throws DAOException {
        transmission = new Transmission();
        transmission.setCommand("RECEIVE_REPORTING_PERIOD");
        transmission.setReportingPeriod(note);
        try {
            Client.getOutObject().writeObject(transmission);
            return (ReportingPeriod) Client.getInObject().readObject();
        } catch (IOException e) {
            throw new DAOException(e.getMessage(), e.getCause(), "Ошибка соединения с сервером");
        } catch (ClassNotFoundException e) {
            throw new DAOException(e.getMessage(), e.getCause(), "Не верный ответ с сервера");
        }catch (ClassCastException e){
            throw new DAOException(e.getMessage(), e.getCause(), "Не валидные данные отправлены были");
        }
    }

    @Override
    public String delete(ReportingPeriod note) throws DAOException {
        transmission = new Transmission();
        transmission.setCommand("DELETE_REPORTING_PERIOD");
        transmission.setReportingPeriod(note);
        try {
            Client.getOutObject().writeObject(transmission);
            return (String) Client.getInObject().readObject();
        } catch (IOException e) {
            throw new DAOException(e.getMessage(), e.getCause(), "Ошибка соединения с сервером");
        } catch (ClassNotFoundException e) {
            throw new DAOException(e.getMessage(), e.getCause(), "Не верный ответ с сервера");
        }catch (ClassCastException e){
            throw new DAOException(e.getMessage(), e.getCause(), "Не валидные данные отправлены были");
        }
    }

    @Override
    public Map<Integer, ReportingPeriod> receiveAll() throws DAOException {
        transmission = new Transmission();
        transmission.setCommand("RECEIVE_ALL_REPORTING_PERIOD");
        try {
            Client.getOutObject().writeObject(transmission);
            return (Map<Integer, ReportingPeriod>) Client.getInObject().readObject();
        } catch (IOException e) {
            throw new DAOException(e.getMessage(), e.getCause(), "Ошибка соединения с сервером");
        } catch (ClassNotFoundException e) {
            throw new DAOException(e.getMessage(), e.getCause(), "Не верный ответ с сервера");
        }catch (ClassCastException e){
            throw new DAOException(e.getMessage(), e.getCause(), "Не валидные данные отправлены были");
        }
    }

    @Override
    public Map<Integer, ReportingPeriod> receiveAllInLimit(int left, int right) throws DAOException {
        transmission = new Transmission();
        transmission.setCommand("RECEIVE_ALL_REPORTING_PERIOD_IN_LIMIT");
        transmission.setBounds(new int[]{left,right});
        try {
            Client.getOutObject().writeObject(transmission);
            return (Map<Integer, ReportingPeriod>) Client.getInObject().readObject();
        } catch (IOException e) {
            throw new DAOException(e.getMessage(), e.getCause(), "Ошибка соединения с сервером");
        } catch (ClassNotFoundException e) {
            throw new DAOException(e.getMessage(), e.getCause(), "Не верный ответ с сервера");
        }catch (ClassCastException e){
            throw new DAOException(e.getMessage(), e.getCause(), "Не валидные данные отправлены были");
        }
    }


}
