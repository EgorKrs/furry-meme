package com.loneliness.entity;

import com.loneliness.client.controller.CommandName;
import com.loneliness.client.controller.CommandProvider;
import com.loneliness.client.controller.ControllerException;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.util.Objects;

public class ContactDetail implements Serializable,Entity {
    private int contactDetailId;
    @NotNull(message = "Задайте email ")
    @Email(message = "Задайте валидный email")
    private String email;
    @NotNull(message = "Задайте номер телефона")
    private String phoneNumber;
    private String info;
    @Positive(message = "Задайте положительный id компании ")
    private int companyId;


    @Override
    public String beautyToString() throws ControllerException {
        Company company=new Company();
        company.setCompanyId(companyId);
        company=(Company)CommandProvider.getCommandProvider().getCommand(CommandName.RECEIVE_COMPANY).execute(company);
        return  "email='" + email + "'" +
                ", номер телефона= '" + phoneNumber + "'" +
                ", дополнительная информация= '" + info + '\'' +
                ", id компании= " + company.beautyToString() ;
    }

    @Override
    public String getPrimaryStringId() {
        return String.valueOf(contactDetailId);
    }

    @Override
    public IntegerProperty getIntegerId() {
        return new SimpleIntegerProperty(companyId);
    }

    @Override
    public StringProperty getStringValue() {
        return new SimpleStringProperty(email);
    }

    public int getContactDetailId() {
        return contactDetailId;
    }

    public void setContactDetailId(int contactDetailId) {
        this.contactDetailId = contactDetailId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactDetail that = (ContactDetail) o;
        return contactDetailId == that.contactDetailId &&
                companyId == that.companyId &&
                email.equals(that.email) &&
                phoneNumber.equals(that.phoneNumber) &&
                info.equals(that.info);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contactDetailId, email, phoneNumber, info, companyId);
    }

    @Override
    public String toString() {
        return "ContactDetail{" +
                "contactDetailId=" + contactDetailId +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", info='" + info + '\'' +
                ", companyId=" + companyId +
                '}';
    }
}
