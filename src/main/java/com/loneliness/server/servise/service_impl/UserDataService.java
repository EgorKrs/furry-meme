package com.loneliness.server.servise.service_impl;

import com.loneliness.entity.UserData;
import com.loneliness.server.dao.DAOFactory;
import com.loneliness.server.dao.sql_dao.SQLUserDAO;
import com.loneliness.server.servise.DataService;

import java.util.Map;

public class UserDataService implements DataService<UserData,String, Map<Integer,UserData>> {
    private final SQLUserDAO userDAO =DAOFactory.getInstance().getUserDAO();
    @Override
    public String add(UserData note) {
        return userDAO.add(note);
    }

    @Override
    public String update(UserData note) {
        return userDAO.update(note);
    }

    @Override
    public UserData receive(UserData note) {
        return userDAO.receive(note);
    }

    @Override
    public String delete(UserData note) {
        return userDAO.delete(note);
    }

    @Override
    public Map<Integer, UserData> receiveAll() {
        return userDAO.receiveAll();
    }

    @Override
    public Map<Integer, UserData> receiveAllInLimit(int left, int right) {
        return userDAO.receiveAllInLimit(left,right);
    }
    public UserData authorise(UserData data){
        if(data.getPassword().equals(receive(data).getPassword()))
        return receive(data);
        else {
            data.setType(UserData.Type.NO_TYPE);
            return data;
        }
    }
    public Map<Integer, UserData> receiveAllManager(){
        return userDAO.receiveAllManager();
    }
}
