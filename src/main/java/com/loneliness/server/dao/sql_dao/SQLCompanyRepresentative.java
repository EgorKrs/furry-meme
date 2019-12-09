package com.loneliness.server.dao.sql_dao;

import com.loneliness.entity.CompanyRepresentatives;
import com.loneliness.server.dao.DataBaseConnection;
import com.loneliness.server.dao.IDAO;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SQLCompanyRepresentative implements IDAO<CompanyRepresentatives,String, Map<Integer,CompanyRepresentatives>> {
    private String sql;
    private String tableName="представители_компаний";
    private static final SQLCompanyRepresentative instance=new SQLCompanyRepresentative();
    private SQLCompanyRepresentative(){}
    public static SQLCompanyRepresentative getInstance() {
        return instance;
    }
    @Override
    public String add(CompanyRepresentatives note) {
        try (Connection connection= DataBaseConnection.getInstance().getConnection()){

            sql="INSERT "+tableName+" (компания , менеджер) " +
                    "VALUES ( "+
                    note.getCompanyId()+", "+
                    note.getManagerId()+
                    ");";
            if(connection.prepareStatement(sql).executeUpdate()>=1){
                return "Данные успешно добавлены";
            }
            else {
                return "ERROR Ошибка добавления";
            }
        } catch (SQLException e) {
            logger.catching(e);
            return "ERROR невозможно добавить такую запись";
        } catch (PropertyVetoException e) {
            logger.catching(e);
            return "ERROR база данных пока не доступна";
        }
    }

    @Override
    public String update(CompanyRepresentatives note) {
        sql = "UPDATE "+tableName+" SET " +
                "компания= " + note.getCompanyId() + " , " +
                "менеджер= " + note.getManagerId()+  "  " +
                "WHERE id_представители_компаний=" + note.getId() + ";";
        try (Connection connection= DataBaseConnection.getInstance().getConnection()){
            if(connection.createStatement().executeUpdate(sql)==1){
                return "Данные обновлены";
            }
            else {
                return "ERROR данные не могут быть обновлены";
            }
        } catch (SQLException e) {
            logger.catching(e);
            return "ERROR невозможно добавить такую запись";
        } catch (PropertyVetoException e) {
            logger.catching(e);
            return "ERROR база данных пока не доступна";
        }
    }

    @Override
    public CompanyRepresentatives receive(CompanyRepresentatives note) {
        if (note.getId()!=0)
        sql= "SELECT * FROM "+tableName+" WHERE id_представители_компаний = " + note.getId() + ";";
        else {
            sql= "SELECT * FROM "+tableName+" WHERE менеджер = " + note.getManagerId() + ";";
        }
        try (Connection connection= DataBaseConnection.getInstance().getConnection()){
            ResultSet resultSet = connection.createStatement().executeQuery(sql);
            if( resultSet.next()){
                return getDataFromResultSet(resultSet);
            }
        } catch (SQLException | PropertyVetoException e) {
            logger.catching(e);
        }
        return note;
    }

    @Override
    public String delete(CompanyRepresentatives note) {
        sql="DELETE FROM "+tableName+" WHERE id_представители_компаний = " + note.getId() + ";";
        try (Connection connection= DataBaseConnection.getInstance().getConnection()){
            if(connection.createStatement().executeUpdate(sql) == 1) {
                return "Данные удалены";
            }
            else {
                return "ERROR Нет данных для удаления";
            }

        } catch (SQLException | PropertyVetoException e) {
            logger.catching(e);
            return "ERROR Ошибка удаления";
        }
    }

    @Override
    public Map<Integer, CompanyRepresentatives> receiveAll() {
        sql = "SELECT * FROM "+tableName+" ;";
        return receiveData(sql);
    }

    @Override
    public Map<Integer, CompanyRepresentatives> receiveAllInLimit(int left, int right) {
        sql="SELECT * FROM "+tableName+" LIMIT "+left+" , "+right+" ;";
        return receiveData(sql);
    }
    private CompanyRepresentatives getDataFromResultSet(ResultSet resultSet) throws SQLException {
        CompanyRepresentatives companyRepresentatives  = new CompanyRepresentatives ();
        companyRepresentatives.setId(resultSet.getInt("id_представители_компаний"));
        companyRepresentatives.setCompanyId(resultSet.getInt("компания"));
        companyRepresentatives.setManagerId(resultSet.getInt("менеджер"));
        return companyRepresentatives;
    }
    private Map<Integer, CompanyRepresentatives> receiveData(String sql){
        ResultSet resultSet;
        ConcurrentHashMap<Integer, CompanyRepresentatives> data=new ConcurrentHashMap<>();
        CompanyRepresentatives companyRepresentatives;
        try (Connection connection= DataBaseConnection.getInstance().getConnection()){

            resultSet=connection.createStatement().executeQuery(sql);
            while (resultSet.next()){
                companyRepresentatives=getDataFromResultSet(resultSet);
                data.put(companyRepresentatives.getId(),companyRepresentatives);
            }
        } catch (SQLException | PropertyVetoException e) {
            logger.catching(e);
        }
        return data;
    }
}
