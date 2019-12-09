package com.loneliness.client.dao.server_request;

import com.loneliness.client.dao.DAOException;
import com.loneliness.client.dao.IDAO;
import com.loneliness.client.launcher.Client;
import com.loneliness.entity.Company;
import com.loneliness.entity.Transmission;

import java.io.IOException;
import java.util.Map;

public class CompanyRequest implements IDAO<Company,String, Map<Integer,Company>> {
    private static final CompanyRequest instance=new CompanyRequest();
    private CompanyRequest(){}
    public static CompanyRequest getInstance() {
        return instance;
    }
    private Transmission transmission;


    @Override
    public String add(Company note) throws DAOException {
        transmission = new Transmission();
        transmission.setCommand("CREATE_COMPANY");
        transmission.setCompany(note);
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
    public String update(Company note) throws DAOException {
        transmission = new Transmission();
        transmission.setCommand("UPDATE_COMPANY");
        transmission.setCompany(note);
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
    public Company receive(Company note) throws DAOException {
        transmission = new Transmission();
        transmission.setCommand("RECEIVE_COMPANY");
        transmission.setCompany(note);
        try {
            Client.getOutObject().writeObject(transmission);
            return (Company) Client.getInObject().readObject();
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
    public String delete(Company note) throws DAOException {
        transmission = new Transmission();
        transmission.setCommand("DELETE_COMPANY");
        transmission.setCompany(note);
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
    public Map<Integer, Company> receiveAll() throws DAOException {
        transmission = new Transmission();
        transmission.setCommand("RECEIVE_ALL_COMPANY");
        try {
            Client.getOutObject().writeObject(transmission);
            return (Map<Integer, Company>) Client.getInObject().readObject();
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
    public Map<Integer, Company> receiveAllInLimit(int left, int right) throws DAOException {
        transmission = new Transmission();
        transmission.setCommand("RECEIVE_ALL_COMPANY_IN_LIMIT");
        transmission.setBounds(new int[]{left,right});
        try {
            Client.getOutObject().writeObject(transmission);
            return (Map<Integer, Company>) Client.getInObject().readObject();
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
