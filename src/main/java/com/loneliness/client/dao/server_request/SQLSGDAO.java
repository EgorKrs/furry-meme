package com.loneliness.client.dao.server_request;

import com.loneliness.entity.SG;
import com.loneliness.server.dao.DataBaseConnection;
import com.loneliness.server.dao.IDAO;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SQLSGDAO implements IDAO<SG,String, Map<Integer,SG>> {
    private String sql;
    private String tableName="sg";
    private static final SQLSGDAO instance=new SQLSGDAO();
    private SQLSGDAO(){}
    public static SQLSGDAO getInstance() {
        return instance;
    }
    @Override
    public String add(SG note) {
        try {
            Connection connection= DataBaseConnection.getInstance().getConnection();
            sql="INSERT roe (id_компании , id_исходных_данных , id_кредита,id_дивиденда," +
                    "ROE,EBIT,рентабельность_продаж,RONA,FL) " +
                    "VALUES ( '"+
                    note.getCompanyId()+"',' "+
                    note.getInitialDataId()+"', '"+
                    note.getCreditId()+"', '"+
                    note.getDividendID()+"', '"+
                    note.getROE()+"', '"+
                    note.getReinvestmentProfit().toString()+"', '"+
                    note.getReinvestmentRatio().toString()+"', '"+
                    note.getSG().toString()+
                    "');";
            if(connection.prepareStatement(sql).executeUpdate()>=1){
                return "Данные успешно добавлены";
            }
            else {
                return "ERROR Ошибка добавления";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "ERROR невозможно добавить такую запись";
        } catch (PropertyVetoException e) {
            e.printStackTrace();
            return "ERROR база данных пока не доступна";
        }
    }

    @Override
    public String update(SG note) {
        sql = "UPDATE "+tableName+" SET " +
                "id_компании='" + note.getCompanyId() + "'," +
                "id_исходных_данных='" + note.getInitialDataId()+ "'," +
                "id_кредита='" + note.getCreditId()+ "'," +
                "id_дивиденда='" + note.getDividendID()+ "'," +
                "id_ROE='" + note.getROE()+ "'," +
                "реинвестиционная_прибыль='" + note.getReinvestmentProfit().toString()+ "'," +
                "Коэффициент_реинвестирования='" + note.getReinvestmentRatio().toString()+ "'," +
                "SG='" + note.getSG().toString()+  "' " +
                "WHERE id_SG=" + note.getSGId() + ";";
        try {
            Connection connection=DataBaseConnection.getInstance().getConnection();
            if(connection.createStatement().executeUpdate(sql)==1){
                return "Данные обновлены";
            }
            else {
                return "ERROR данные не могут быть обновлены";
            }
        } catch (SQLException e) {
            System.out.println(e.getErrorCode()+"\n"+e.getSQLState());
            return "ERROR невозможно добавить такую запись";
        } catch (PropertyVetoException e) {
            e.printStackTrace();
            return "ERROR база данных пока не доступна";
        }
    }

    @Override
    public SG receive(SG note) {
        sql= "SELECT * FROM "+tableName+" WHERE id_ROE = " + note.getSGId() + ";";
        try {
            Connection connection= DataBaseConnection.getInstance().getConnection();
            ResultSet resultSet = connection.createStatement().executeQuery(sql);
            if( resultSet.next()){
                return getDataFromResultSet(resultSet);
            }
        } catch (SQLException | PropertyVetoException e) {
            e.printStackTrace();
        }
        return note;
    }

    @Override
    public String delete(SG note) {
        sql="DELETE FROM "+tableName+" WHERE id_ROE = " + note.getSGId() + ";";
        try {
            Connection connection= DataBaseConnection.getInstance().getConnection();
            if(connection.createStatement().executeUpdate(sql) == 1) {
                return "Данные удалены";
            }
            else {
                return "ERROR Нет данных для удаления";
            }

        } catch (SQLException | PropertyVetoException e) {
            e.printStackTrace();
            return "ERROR Ошибка удаления";
        }
    }

    @Override
    public Map<Integer, SG> receiveAll() {
        sql = "SELECT * FROM "+tableName+" ;";
        return receiveData(sql);
    }

    @Override
    public Map<Integer, SG> receiveAllInLimit(int left, int right) {
        sql="SELECT * FROM "+tableName+" LIMIT "+left+" , "+right+" ;";
        return receiveData(sql);
    }
    private SG getDataFromResultSet(ResultSet resultSet) throws SQLException {
        SG sg  = new SG ();
        sg.setSGId(resultSet.getInt("id_SG"));
        sg.setCompanyId(resultSet.getInt("id_компании"));
        sg.setInitialDataId(resultSet.getInt("id_исходных_данных"));
        sg.setCreditId(resultSet.getInt("id_кредита"));
        sg.setDividendID(resultSet.getInt("id_дивиденда"));
        sg.setROE(resultSet.getBigDecimal("ROE"));
        sg.setReinvestmentProfit(resultSet.getBigDecimal("реинвестиционная_прибыль"));
        sg.setReinvestmentRatio(resultSet.getBigDecimal("Коэффициент_реинвестирования"));
        sg.setSG(resultSet.getBigDecimal("SG"));
        return sg;
    }
    private Map<Integer, SG> receiveData(String sql){
        ResultSet resultSet;
        ConcurrentHashMap<Integer, SG> data=new ConcurrentHashMap<>();
        SG sg;
        try {
            Connection connection= DataBaseConnection.getInstance().getConnection();
            resultSet=connection.createStatement().executeQuery(sql);
            while (resultSet.next()){
                sg=getDataFromResultSet(resultSet);
                data.put(sg.getSGId(),sg);
            }
        } catch (SQLException | PropertyVetoException e) {
            e.printStackTrace();
        }
        return data;
    }
}