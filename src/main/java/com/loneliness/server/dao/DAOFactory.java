package com.loneliness.server.dao;

public class DAOFactory {
    private static final  DAOFactory instance=new DAOFactory();
    private DAOFactory(){}
    private final SQLUserDAO userDAO=SQLUserDAO.getInstance();
    private final SQLDifferentialIndicatorsDAO differentialIndicatorsDAO=SQLDifferentialIndicatorsDAO.getInstance();

    public static DAOFactory getInstance() {
        return instance;
    }

    public SQLUserDAO getUserDAO() {
        return userDAO;
    }

    public SQLDifferentialIndicatorsDAO getDifferentialIndicatorsDAO() {
        return differentialIndicatorsDAO;
    }
}
