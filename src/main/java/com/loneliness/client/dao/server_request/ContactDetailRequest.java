package com.loneliness.client.dao.server_request;

import com.loneliness.client.dao.DAOException;
import com.loneliness.client.dao.IDAO;
import com.loneliness.client.launcher.Client;
import com.loneliness.entity.ContactDetail;
import com.loneliness.entity.Transmission;

import java.io.IOException;
import java.util.Map;

public class ContactDetailRequest implements IDAO<ContactDetail,String, Map<Integer,ContactDetail>> {
    private static final ContactDetailRequest instance=new ContactDetailRequest();
    private ContactDetailRequest(){}
    public static ContactDetailRequest getInstance() {
        return instance;
    }
    private Transmission transmission;

    @Override
    public String add(ContactDetail note) throws DAOException {
        transmission = new Transmission();
        transmission.setCommand("CREATE_CONTACT_DETAIL");
        transmission.setContactDetail(note);
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
    public String update(ContactDetail note) throws DAOException {
        transmission = new Transmission();
        transmission.setCommand("UPDATE_CONTACT_DETAIL");
        transmission.setContactDetail(note);
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
    public ContactDetail receive(ContactDetail note) throws DAOException {
        transmission = new Transmission();
        transmission.setCommand("RECEIVE_CONTACT_DETAIL");
        transmission.setContactDetail(note);
        try {
            Client.getOutObject().writeObject(transmission);
            return (ContactDetail) Client.getInObject().readObject();
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
    public String delete(ContactDetail note) throws DAOException {
        transmission = new Transmission();
        transmission.setCommand("DELETE_CONTACT_DETAIL");
        transmission.setContactDetail(note);
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
    public Map<Integer, ContactDetail> receiveAll() throws DAOException {
        transmission = new Transmission();
        transmission.setCommand("RECEIVE_ALL_CONTACT_DETAIL");
        try {
            Client.getOutObject().writeObject(transmission);
            return (Map<Integer, ContactDetail>) Client.getInObject().readObject();
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
    public Map<Integer, ContactDetail> receiveAllInLimit(int left, int right) throws DAOException {
        transmission = new Transmission();
        transmission.setCommand("RECEIVE_ALL_CONTACT_DETAIL");
        transmission.setBounds(new int[]{left,right});
        try {
            Client.getOutObject().writeObject(transmission);
            return (Map<Integer, ContactDetail>) Client.getInObject().readObject();
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
