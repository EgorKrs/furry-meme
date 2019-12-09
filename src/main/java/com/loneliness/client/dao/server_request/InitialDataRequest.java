package com.loneliness.client.dao.server_request;

import com.loneliness.client.dao.DAOException;
import com.loneliness.client.dao.IDAO;
import com.loneliness.client.launcher.Client;
import com.loneliness.entity.InitialData;
import com.loneliness.entity.Transmission;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;

public class InitialDataRequest implements IDAO<InitialData,String, Map<Integer,InitialData>> {
    private static final InitialDataRequest instance=new InitialDataRequest();
    private InitialDataRequest(){}
    public static InitialDataRequest getInstance() {
        return instance;
    }
    private Transmission transmission;


    @Override
    public String add(InitialData note) throws DAOException {
        transmission = new Transmission();
        transmission.setCommand("CREATE_INITIAL_DATA");
        transmission.setInitialData(note);
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
    public String update(InitialData note) throws DAOException {
        transmission = new Transmission();
        transmission.setCommand("UPDATE_INITIAL_DATA");
        transmission.setInitialData(note);
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
    public InitialData receive(InitialData note) throws DAOException {
        transmission = new Transmission();
        transmission.setCommand("RECEIVE_INITIAL_DATA");
        transmission.setInitialData(note);
        try {
            Client.getOutObject().writeObject(transmission);
            return (InitialData) Client.getInObject().readObject();
        } catch (IOException e) {
            throw new DAOException(e.getMessage(), e.getCause(), "Ошибка соединения с сервером");
        } catch (ClassNotFoundException e) {
            throw new DAOException(e.getMessage(), e.getCause(), "Не верный ответ с сервера");
        }catch (ClassCastException e){
            throw new DAOException(e.getMessage(), e.getCause(), "Не валидные данные отправлены были");
        }
    }

    @Override
    public String delete(InitialData note) throws DAOException {
        transmission = new Transmission();
        transmission.setCommand("DELETE_INITIAL_DATA");
        transmission.setInitialData(note);
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
    public Map<Integer, InitialData> receiveAll() throws DAOException {
        transmission = new Transmission();
        transmission.setCommand("RECEIVE_ALL_INITIAL_DATA");
        try {
            Client.getOutObject().writeObject(transmission);
            return (Map<Integer, InitialData>) Client.getInObject().readObject();
        } catch (IOException e) {
            throw new DAOException(e.getMessage(), e.getCause(), "Ошибка соединения с сервером");
        } catch (ClassNotFoundException e) {
            throw new DAOException(e.getMessage(), e.getCause(), "Не верный ответ с сервера");
        }catch (ClassCastException e){
            throw new DAOException(e.getMessage(), e.getCause(), "Не валидные данные отправлены были");
        }
    }

    @Override
    public Map<Integer, InitialData> receiveAllInLimit(int left, int right) throws DAOException {
        transmission = new Transmission();
        transmission.setCommand("RECEIVE_ALL_INITIAL_DATA_IN_LIMIT");
        transmission.setBounds(new int[]{left,right});
        try {
            Client.getOutObject().writeObject(transmission);
            return (Map<Integer, InitialData>) Client.getInObject().readObject();
        } catch (IOException e) {
            throw new DAOException(e.getMessage(), e.getCause(), "Ошибка соединения с сервером");
        } catch (ClassNotFoundException e) {
            throw new DAOException(e.getMessage(), e.getCause(), "Не верный ответ с сервера");
        }catch (ClassCastException e){
            throw new DAOException(e.getMessage(), e.getCause(), "Не валидные данные отправлены были");
        }
    }
    public BigDecimal findFutureEquity(InitialData note) throws DAOException {
        transmission = new Transmission();
        transmission.setCommand("FIND_FUTURE_EQUITY");
        transmission.setInitialData(note);
        try {
            Client.getOutObject().writeObject(transmission);
            return (BigDecimal) Client.getInObject().readObject();
        } catch (IOException e) {
            throw new DAOException(e.getMessage(), e.getCause(), "Ошибка соединения с сервером");
        } catch (ClassNotFoundException e) {
            throw new DAOException(e.getMessage(), e.getCause(), "Не верный ответ с сервера");
        }catch (ClassCastException e){
            throw new DAOException(e.getMessage(), e.getCause(), "Не валидные данные отправлены были");
        }
    }
    public BigDecimal findPreviousEquity(InitialData note) throws DAOException {
        transmission = new Transmission();
        transmission.setCommand("FIND_PREVIOUS_EQUITY");
        transmission.setInitialData(note);
        try {
            Client.getOutObject().writeObject(transmission);
            return (BigDecimal) Client.getInObject().readObject();
        } catch (IOException e) {
            throw new DAOException(e.getMessage(), e.getCause(), "Ошибка соединения с сервером");
        } catch (ClassNotFoundException e) {
            throw new DAOException(e.getMessage(), e.getCause(), "Не верный ответ с сервера");
        }catch (ClassCastException e){
            throw new DAOException(e.getMessage(), e.getCause(), "Не валидные данные отправлены были");
        }
    }
    public BigDecimal findCreditInPeriod(int note) throws DAOException {
        transmission = new Transmission();
        transmission.setCommand("FIND_PREVIOUS_EQUITY");
        transmission.setId(note);
        try {
            Client.getOutObject().writeObject(transmission);
            return (BigDecimal) Client.getInObject().readObject();
        } catch (IOException e) {
            throw new DAOException(e.getMessage(), e.getCause(), "Ошибка соединения с сервером");
        } catch (ClassNotFoundException e) {
            throw new DAOException(e.getMessage(), e.getCause(), "Не верный ответ с сервера");
        }catch (ClassCastException e){
            throw new DAOException(e.getMessage(), e.getCause(), "Не валидные данные отправлены были");
        }
    }

}
