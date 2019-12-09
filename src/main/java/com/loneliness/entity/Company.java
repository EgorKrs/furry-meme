package com.loneliness.entity;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

public class Company implements Serializable ,Entity{
    private int companyId;
    @NotNull(message = "Задайте имя компании")
    private String companyName;

    @Override
    public String getPrimaryStringId() {
        return String.valueOf(companyId);
    }

    transient SimpleIntegerProperty companyIDProperty;

    @Override
    public IntegerProperty getIntegerId() {
        return new SimpleIntegerProperty(companyId);
    }

    @Override
    public StringProperty getStringValue() {
        return new SimpleStringProperty(companyName);
    }

    @Override
    public String beautyToString() {
        return "Имя компании= '" + companyName + "'";
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public boolean isNull(){
        if(companyId<0){
            return true;
        }
        else return companyName == null || companyName.length() == 0;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return companyId == company.companyId &&
                companyName.equals(company.companyName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(companyId, companyName);
    }

    @Override
    public String toString() {
        return "Company{" +
                "companyId=" + companyId +
                ", companyName='" + companyName + '\'' +
                '}';
    }
}
