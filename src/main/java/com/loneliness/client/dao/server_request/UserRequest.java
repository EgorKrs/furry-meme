package com.loneliness.client.dao.server_request;

import com.loneliness.client.dao.DAOException;
import com.loneliness.client.dao.IDAO;
import com.loneliness.client.launcher.Client;
import com.loneliness.entity.Transmission;
import com.loneliness.entity.UserData;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class UserRequest implements IDAO<UserData,String, Map<Integer,UserData>> {
    private Transmission transmission;

    @Override
    public String add(UserData note) throws DAOException {
        transmission = new Transmission();
        transmission.setCommand("CREATE_USER");
        transmission.setUserData(note);
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
    public String update(UserData note) throws DAOException {
        transmission = new Transmission();
        transmission.setCommand("UPDATE_USER");
        transmission.setUserData(note);
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
    public UserData receive(UserData note) throws DAOException {
        transmission = new Transmission();
        transmission.setCommand("RECEIVE_USER");
        transmission.setUserData(note);
        try {
            Client.getOutObject().writeObject(transmission);
            return (UserData) Client.getInObject().readObject();
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
    public String delete(UserData note) throws DAOException {
        transmission = new Transmission();
        transmission.setCommand("DELETE_USER");
        transmission.setUserData(note);
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
    public Map<Integer, UserData> receiveAll() throws DAOException {
        transmission = new Transmission();
        transmission.setCommand("RECEIVE_ALL_USERS");
        try {
            Client.getOutObject().writeObject(transmission);
            return (ConcurrentHashMap<Integer, UserData>) Client.getInObject().readObject();
        } catch (IOException e) {
            throw new DAOException(e.getMessage(), e.getCause(), "Ошибка соединения с сервером");
        } catch (ClassNotFoundException e) {
            throw new DAOException(e.getMessage(), e.getCause(), "Не верный ответ с сервера");
        }catch (ClassCastException e){
            throw new DAOException(e.getMessage(), e.getCause(), "Не валидные данные отправлены были");
        }
    }

    public Map<Integer, UserData> receiveAllManager() throws DAOException {
        transmission = new Transmission();
        transmission.setCommand("RECEIVE_ALL_MANAGER");
        try {
            Client.getOutObject().writeObject(transmission);
            return (ConcurrentHashMap<Integer, UserData>) Client.getInObject().readObject();
        } catch (IOException e) {
            throw new DAOException(e.getMessage(), e.getCause(), "Ошибка соединения с сервером");
        } catch (ClassNotFoundException e) {
            throw new DAOException(e.getMessage(), e.getCause(), "Не верный ответ с сервера");
        }catch (ClassCastException e){
            throw new DAOException(e.getMessage(), e.getCause(), "Не валидные данные отправлены были");
        }
    }

    @Override
    public Map<Integer, UserData> receiveAllInLimit(int left, int right) throws DAOException {
        try {
            transmission = new Transmission();
            transmission.setBounds(new int[]{left, right});
            transmission.setCommand("RECEIVE_ALL_USERS_IN_LIMIT");
            Client.getOutObject().writeObject(transmission);
            return (Map<Integer, UserData>) Client.getInObject().readObject();
        } catch (IOException e) {
            throw new DAOException(e.getMessage(), e.getCause(), "Ошибка соединения с сервером");
        } catch (ClassNotFoundException e) {
            throw new DAOException(e.getMessage(), e.getCause(), "Не верный ответ с сервера");
        }catch (ClassCastException e){
            throw new DAOException(e.getMessage(), e.getCause(), "Не валидные данные отправлены были");
        }
    }

    public UserData authorise(UserData note) throws DAOException {
        transmission = new Transmission();
        transmission.setCommand("AUTHORISE_USER");
        transmission.setUserData(note);
        try {
            Client.getOutObject().writeObject(transmission);
            return (UserData) Client.getInObject().readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new DAOException("Ошибка получения данных", "ServerUserRequest " + e.getMessage());
        }catch (ClassCastException e){
            throw new DAOException(e.getMessage(), e.getCause(), "Не валидные данные отправлены были");
        }
    }
}
