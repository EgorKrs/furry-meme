package com.loneliness.entity;

import com.loneliness.client.controller.CommandName;
import com.loneliness.client.controller.CommandProvider;
import com.loneliness.client.controller.ControllerException;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Credit implements Serializable ,Entity{
    private int creditId;
    @Positive(message = "Задайте положительный id компании ")
    private int companyId;
    @NotNull(message = "Задайте процент кредита")
    private BigDecimal loanPercentage;
    @NotNull(message = "Задайте сумма по кредиту")
    private BigDecimal loanTotal;
    @PastOrPresent(message = "дата взятия должна быть в прошлом ")
    @NotNull(message = "Задайте дата взятия")
    private LocalDate dateOfCollection;
    @NotNull(message = "Задайте дата выплаты")
    private LocalDate payDate;
    @NotNull(message = "Задайте кредитную ставка банка")
    private BigDecimal R;

    @Override
    public String getPrimaryStringId() {
        return String.valueOf(creditId);
    }

    @Override
    public String beautyToString() throws ControllerException {
        Company company=new Company();
        company.setCompanyId(companyId);
        company=(Company) CommandProvider.getCommandProvider().getCommand(CommandName.RECEIVE_COMPANY).execute(company);
        return  ""+company.beautyToString() +
                ", процент кредита= " + loanPercentage +
                ", сумма по кредиту= " + loanTotal +
                ", дата взятия кредита= " + dateOfCollection +
                ", кредитную ставка банка =" + payDate ;
    }

    @Override
    public IntegerProperty getIntegerId() {
        return new SimpleIntegerProperty(companyId);

    }

    @Override
    public StringProperty getStringValue() {
        return new SimpleStringProperty(loanTotal.toString());
    }

    public int getCreditId() {
        return creditId;
    }

    public BigDecimal getR() {
        return R;
    }

    public void setR(BigDecimal r) {
        R = r;
    }

    public void setCreditId(int creditId) {
        this.creditId = creditId;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public BigDecimal getLoanPercentage() {
        return loanPercentage;
    }

    public void setLoanPercentage(BigDecimal loanPercentage) {
        this.loanPercentage = loanPercentage;
    }

    public BigDecimal getLoanTotal() {
        return loanTotal;
    }

    public void setLoanTotal(BigDecimal loanTotal) {
        this.loanTotal = loanTotal;
    }

    public LocalDate getDateOfCollection() {
        return dateOfCollection;
    }

    public void setDateOfCollection(LocalDate dateOfCollection) {
        this.dateOfCollection = dateOfCollection;
    }

    public LocalDate getPayDate() {
        return payDate;
    }

    public void setPayDate(LocalDate payDate) {
        this.payDate = payDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Credit credit = (Credit) o;
        return creditId == credit.creditId &&
                companyId == credit.companyId &&
                loanPercentage.equals(credit.loanPercentage) &&
                loanTotal.equals(credit.loanTotal) &&
                dateOfCollection.equals(credit.dateOfCollection) &&
                payDate.equals(credit.payDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(creditId, companyId, loanPercentage, loanTotal, dateOfCollection, payDate);
    }

    @Override
    public String toString() {
        return "Credit{" +
                "creditId=" + creditId +
                ", companyId=" + companyId +
                ", loanPercentage=" + loanPercentage +
                ", loanTotal=" + loanTotal +
                ", dateOfCollection=" + dateOfCollection +
                ", payDate=" + payDate +
                '}';
    }
}
