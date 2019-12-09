package com.loneliness.entity;

import com.loneliness.client.controller.CommandName;
import com.loneliness.client.controller.CommandProvider;
import com.loneliness.client.controller.ControllerException;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javax.validation.constraints.Positive;
import java.io.Serializable;

public class CompanyRepresentatives implements Entity, Serializable {
    private int id;
    @Positive(message = "id компании должно быть > 0")
    private int companyId;
    @Positive(message = "id менеджера должно быть > 0")
    private int managerId;

    @Override
    public String beautyToString() throws ControllerException {
        UserData userData=new UserData();
        userData.setId(id);
        CommandProvider.getCommandProvider().getCommand(CommandName.RECEIVE_USER).execute(userData);
        UserData manager=new UserData();
        manager.setId(managerId);
        CommandProvider.getCommandProvider().getCommand(CommandName.RECEIVE_USER).execute(manager);
        return "id=" + id +
                ", " +userData.beautyToString()+
                ", "+ manager.beautyToString()+
                '}';
    }

    @Override
    public String getPrimaryStringId() {
        return String.valueOf(id);
    }

    @Override
    public IntegerProperty getIntegerId() {
        return new SimpleIntegerProperty(companyId);
    }

    @Override
    public StringProperty getStringValue() {
        return new SimpleStringProperty(String.valueOf(managerId));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public int getManagerId() {
        return managerId;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }

    @Override
    public String toString() {
        return "CompanyRepresentatives{" +
                "id=" + id +
                ", companyId=" + companyId +
                ", managerId=" + managerId +
                '}';
    }
}
