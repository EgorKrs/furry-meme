package com.loneliness.client.dao.server_request;

import com.loneliness.client.dao.DAOException;
import com.loneliness.client.dao.IDAO;
import com.loneliness.client.launcher.Client;
import com.loneliness.entity.Quarter;
import com.loneliness.entity.ROE;
import com.loneliness.entity.ReportingPeriod;
import com.loneliness.entity.Transmission;

import java.io.IOException;
import java.util.Map;

public class ROERequest implements IDAO<ROE,String, Map<Integer,ROE>> {
    private static final ROERequest instance=new ROERequest();
    private ROERequest(){}
    public static ROERequest getInstance() {
        return instance;
    }
    private Transmission transmission;

    @Override
    public String add(ROE note) throws DAOException {
        transmission = new Transmission();
        transmission.setCommand("CREATE_ROE");
        transmission.setRoe(note);
        try {
            Client.getOutObject().writeObject(transmission);
            return (String) Client.getInObject().readObject();
        } catch (IOException e) {
            throw new DAOException(e.getMessage(), e.getCause(), "Ошибка соединения с сервером");
        } catch (ClassNotFoundException e) {
            throw new DAOException(e.getMessage(), e.getCause(), "Не верный ответ с сервера");
        }
        catch (ClassCastException e){
            throw new DAOException(e.getMessage(), e.getCause(), "Не валидные данные отправлены были");
        }
    }

    @Override
    public String update(ROE note) throws DAOException {
        transmission = new Transmission();
        transmission.setCommand("UPDATE_ROE");
        transmission.setRoe(note);
        try {
            Client.getOutObject().writeObject(transmission);
            return (String) Client.getInObject().readObject();
        } catch (IOException e) {
            throw new DAOException(e.getMessage(), e.getCause(), "Ошибка соединения с сервером");
        } catch (ClassNotFoundException e) {
            throw new DAOException(e.getMessage(), e.getCause(), "Не верный ответ с сервера");
        }
        catch (ClassCastException e){
            throw new DAOException(e.getMessage(), e.getCause(), "Не валидные данные отправлены были");
        }
    }

    @Override
    public ROE receive(ROE note) throws DAOException {
        transmission = new Transmission();
        transmission.setCommand("RECEIVE_ROE");
        transmission.setRoe(note);
        try {
            Client.getOutObject().writeObject(transmission);
            return (ROE) Client.getInObject().readObject();
        } catch (IOException e) {
            throw new DAOException(e.getMessage(), e.getCause(), "Ошибка соединения с сервером");
        } catch (ClassNotFoundException e) {
            throw new DAOException(e.getMessage(), e.getCause(), "Не верный ответ с сервера");
        }
        catch (ClassCastException e){
            throw new DAOException(e.getMessage(), e.getCause(), "Не валидные данные отправлены были");
        }
    }

    @Override
    public String delete(ROE note) throws DAOException {
        transmission = new Transmission();
        transmission.setCommand("DELETE_ROE");
        transmission.setRoe(note);
        try {
            Client.getOutObject().writeObject(transmission);
            return (String) Client.getInObject().readObject();
        } catch (IOException e) {
            throw new DAOException(e.getMessage(), e.getCause(), "Ошибка соединения с сервером");
        } catch (ClassNotFoundException e) {
            throw new DAOException(e.getMessage(), e.getCause(), "Не верный ответ с сервера");
        }
        catch (ClassCastException e){
            throw new DAOException(e.getMessage(), e.getCause(), "Не валидные данные отправлены были");
        }
    }

    @Override
    public Map<Integer, ROE> receiveAll() throws DAOException {
        transmission = new Transmission();
        transmission.setCommand("RECEIVE_ALL_ROE");

        try {
            Client.getOutObject().writeObject(transmission);
            return (Map<Integer, ROE> ) Client.getInObject().readObject();
        } catch (IOException e) {
            throw new DAOException(e.getMessage(), e.getCause(), "Ошибка соединения с сервером");
        } catch (ClassNotFoundException e) {
            throw new DAOException(e.getMessage(), e.getCause(), "Не верный ответ с сервера");
        }
        catch (ClassCastException e){
            throw new DAOException(e.getMessage(), e.getCause(), "Не валидные данные отправлены были");
        }
    }

    @Override
    public Map<Integer, ROE> receiveAllInLimit(int left, int right) throws DAOException {
        transmission = new Transmission();
        transmission.setCommand("RECEIVE_ALL_ROE_IN_LIMIT");
        transmission.setBounds(new int[]{left,right});
        try {
            Client.getOutObject().writeObject(transmission);
            return (Map<Integer, ROE> ) Client.getInObject().readObject();
        } catch (IOException e) {
            throw new DAOException(e.getMessage(), e.getCause(), "Ошибка соединения с сервером");
        } catch (ClassNotFoundException e) {
            throw new DAOException(e.getMessage(), e.getCause(), "Не верный ответ с сервера");
        }
        catch (ClassCastException e){
            throw new DAOException(e.getMessage(), e.getCause(), "Не валидные данные отправлены были");
        }
    }
    public ROE findRoeByReportingPeriodId(int id) throws DAOException {
        transmission = new Transmission();
        transmission.setCommand("FIND_ROE_BY_REPORTING_PERIOD_ID");
        transmission.setId(id);
        try {
            Client.getOutObject().writeObject(transmission);
            return (ROE) Client.getInObject().readObject();
        } catch (IOException e) {
            throw new DAOException(e.getMessage(), e.getCause(), "Ошибка соединения с сервером");
        } catch (ClassNotFoundException e) {
            throw new DAOException(e.getMessage(), e.getCause(), "Не верный ответ с сервера");
        }
        catch (ClassCastException e){
            throw new DAOException(e.getMessage(), e.getCause(), "Не валидные данные отправлены были");
        }
    }
    public Map<Quarter,ROE> findRoeByReportingPeriodYear(ReportingPeriod note) throws DAOException {
        transmission = new Transmission();
        transmission.setCommand("FIND_ROE_BY_REPORTING_PERIOD_YEAR");
        transmission.setReportingPeriod(note);
        try {
            Client.getOutObject().writeObject(transmission);
            return (Map<Quarter,ROE>) Client.getInObject().readObject();
        } catch (IOException e) {
            throw new DAOException(e.getMessage(), e.getCause(), "Ошибка соединения с сервером");
        } catch (ClassNotFoundException e) {
            throw new DAOException(e.getMessage(), e.getCause(), "Не верный ответ с сервера");
        }
        catch (ClassCastException e){
            throw new DAOException(e.getMessage(), e.getCause(), "Не валидные данные отправлены были");
        }
    }

    public String getState(ROE note) throws DAOException {
        transmission = new Transmission();
        transmission.setCommand("GET_STATE");
        transmission.setRoe(note);
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

}
