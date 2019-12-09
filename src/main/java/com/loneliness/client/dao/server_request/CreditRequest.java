package com.loneliness.client.dao.server_request;

import com.loneliness.client.dao.DAOException;
import com.loneliness.client.dao.IDAO;
import com.loneliness.client.launcher.Client;
import com.loneliness.entity.Credit;
import com.loneliness.entity.Transmission;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;

public class CreditRequest implements IDAO<Credit,String, Map<Integer,Credit>> {
    private static final CreditRequest instance=new CreditRequest();
    private CreditRequest(){}
    public static CreditRequest getInstance() {
        return instance;
    }
    private Transmission transmission;

    @Override
    public String add(Credit note) throws DAOException {
        transmission = new Transmission();
        transmission.setCommand("CREATE_CREDIT");
        transmission.setCredit(note);
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
    public String update(Credit note) throws DAOException {
        transmission = new Transmission();
        transmission.setCommand("UPDATE_CREDIT");
        transmission.setCredit(note);
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
    public Credit receive(Credit note) throws DAOException {
        transmission = new Transmission();
        transmission.setCommand("RECEIVE_CREDIT");
        transmission.setCredit(note);
        try {
            Client.getOutObject().writeObject(transmission);
            return (Credit) Client.getInObject().readObject();
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
    public String delete(Credit note) throws DAOException {
        transmission = new Transmission();
        transmission.setCommand("DELETE_CREDIT");
        transmission.setCredit(note);
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
    public Map<Integer, Credit> receiveAll() throws DAOException {
        transmission = new Transmission();
        transmission.setCommand("RECEIVE_ALL_CREDIT");

        try {
            Client.getOutObject().writeObject(transmission);
            return (Map<Integer, Credit>) Client.getInObject().readObject();
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
    public Map<Integer, Credit> receiveAllInLimit(int left, int right) throws DAOException {
        transmission = new Transmission();
        transmission.setCommand("RECEIVE_ALL_CREDIT_IN_LIMIT");
        transmission.setBounds(new int[]{left,right});
        try {
            Client.getOutObject().writeObject(transmission);
            return ( Map<Integer, Credit>) Client.getInObject().readObject();
        } catch (IOException e) {
            throw new DAOException(e.getMessage(), e.getCause(), "Ошибка соединения с сервером");
        } catch (ClassNotFoundException e) {
            throw new DAOException(e.getMessage(), e.getCause(), "Не верный ответ с сервера");
        }
        catch (ClassCastException e){
            throw new DAOException(e.getMessage(), e.getCause(), "Не валидные данные отправлены были");
        }
    }
    public BigDecimal findCreditInPeriod(Integer id) throws DAOException {
        transmission = new Transmission();
        transmission.setCommand("FIND_CREDIT_IN_PERIOD");
        transmission.setId(id);
        try {
            Client.getOutObject().writeObject(transmission);
            return (BigDecimal) Client.getInObject().readObject();
        } catch (IOException e) {
            throw new DAOException(e.getMessage(), e.getCause(), "Ошибка соединения с сервером");
        } catch (ClassNotFoundException e) {
            throw new DAOException(e.getMessage(), e.getCause(), "Не верный ответ с сервера");
        }
        catch (ClassCastException e){
            throw new DAOException(e.getMessage(), e.getCause(), "Не валидные данные отправлены были");
        }
    }

}
