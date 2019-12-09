package com.loneliness.client.dao.server_request;

import com.loneliness.client.dao.DAOException;
import com.loneliness.client.dao.IDAO;
import com.loneliness.client.launcher.Client;
import com.loneliness.entity.CompanyRepresentatives;
import com.loneliness.entity.Transmission;

import java.io.IOException;
import java.util.Map;

public class CompanyRepresentativeRequest implements IDAO<CompanyRepresentatives,String, Map<Integer,CompanyRepresentatives>> {
    private static final CompanyRepresentativeRequest instance = new CompanyRepresentativeRequest();

    private CompanyRepresentativeRequest() {
    }

    public static CompanyRepresentativeRequest getInstance() {
        return instance;
    }

    private Transmission transmission;

    @Override
    public String add(CompanyRepresentatives note) throws DAOException {
        transmission = new Transmission();
        transmission.setCommand("CREATE_COMPANY_REPRESENTATIVES");
        transmission.setCompanyRepresentatives(note);
        try {
            Client.getOutObject().writeObject(transmission);
            return (String) Client.getInObject().readObject();
        } catch (IOException e) {
            throw new DAOException(e.getMessage(), e.getCause(), "Ошибка соединения с сервером");
        } catch (ClassNotFoundException  e) {
            throw new DAOException(e.getMessage(), e.getCause(), "Не верный ответ с сервера");

        }
        catch (ClassCastException e){
            throw new DAOException(e.getMessage(), e.getCause(), "Не валидные данные отправлены были");
        }

    }

    @Override
    public String update(CompanyRepresentatives note) throws DAOException {
        transmission = new Transmission();
        transmission.setCommand("UPDATE_COMPANY_REPRESENTATIVES");
        transmission.setCompanyRepresentatives(note);
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
    public CompanyRepresentatives receive(CompanyRepresentatives note) throws DAOException {
        transmission = new Transmission();
        transmission.setCommand("RECEIVE_COMPANY_REPRESENTATIVES");
        transmission.setCompanyRepresentatives(note);
        try {
            Client.getOutObject().writeObject(transmission);
            return (CompanyRepresentatives) Client.getInObject().readObject();
        } catch (IOException e) {
            throw new DAOException(e.getMessage(), e.getCause(), "Ошибка соединения с сервером");
        } catch (ClassNotFoundException  e) {
            throw new DAOException(e.getMessage(), e.getCause(), "Не верный ответ с сервера");
        }
        catch (ClassCastException e){
            throw new DAOException(e.getMessage(), e.getCause(), "Не валидные данные отправлены были");
        }
    }

    @Override
    public String delete(CompanyRepresentatives note) throws DAOException {
        transmission = new Transmission();
        transmission.setCommand("DELETE_COMPANY_REPRESENTATIVES");
        transmission.setCompanyRepresentatives(note);
        try {
            Client.getOutObject().writeObject(transmission);
            return (String) Client.getInObject().readObject();
        } catch (IOException e) {
            throw new DAOException(e.getMessage(), e.getCause(), "Ошибка соединения с сервером");
        } catch (ClassNotFoundException  e) {
            throw new DAOException(e.getMessage(), e.getCause(), "Не верный ответ с сервера");
        }
        catch (ClassCastException e){
            throw new DAOException(e.getMessage(), e.getCause(), "Не валидные данные отправлены были");
        }
    }

    @Override
    public Map<Integer, CompanyRepresentatives> receiveAll() throws DAOException {
        transmission = new Transmission();
        transmission.setCommand("RECEIVE_ALL_COMPANY_REPRESENTATIVES");

        try {
            Client.getOutObject().writeObject(transmission);
            return (Map<Integer, CompanyRepresentatives>) Client.getInObject().readObject();
        } catch (IOException e) {
            throw new DAOException(e.getMessage(), e.getCause(), "Ошибка соединения с сервером");
        } catch (ClassNotFoundException  e) {
            throw new DAOException(e.getMessage(), e.getCause(), "Не верный ответ с сервера");
        }
        catch (ClassCastException e){
            throw new DAOException(e.getMessage(), e.getCause(), "Не валидные данные отправлены были");
        }
    }

    @Override
    public Map<Integer, CompanyRepresentatives> receiveAllInLimit(int left, int right) throws DAOException {
        transmission = new Transmission();
        transmission.setCommand("RECEIVE_ALL_COMPANY_REPRESENTATIVES_IN_LIMIT");
        transmission.setBounds(new int[]{left, right});
        try {
            Client.getOutObject().writeObject(transmission);
            return (Map<Integer, CompanyRepresentatives>) Client.getInObject().readObject();
        } catch (IOException e) {
            throw new DAOException(e.getMessage(), e.getCause(), "Ошибка соединения с сервером");
        } catch (ClassNotFoundException e) {
            throw new DAOException(e.getMessage(), e.getCause(), "Не верный ответ с сервера");
        } catch (ClassCastException e){
            throw new DAOException(e.getMessage(), e.getCause(), "Не валидные данные отправлены были");
        }
    }
}
