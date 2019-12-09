package com.loneliness.entity;

import com.loneliness.client.controller.CommandName;
import com.loneliness.client.controller.CommandProvider;
import com.loneliness.client.controller.ControllerException;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public class InitialData implements Serializable ,Entity{
    private int initialDataId;
    @Positive(message = "ID компании должен быть положительным")
    private int companyId;
    @NotNull(message = "Задайте выручка от реализации ")
    private BigDecimal sales;
    @NotNull(message = "Задайте активы ")
    private BigDecimal assets;
    @NotNull(message = "Задайте собственный капитал ")
    private BigDecimal equity;
    @NotNull(message = "Задайте чистую прибыль ")
    private BigDecimal PBIT;
    @NotNull(message = "Задайте привлеченный капитал ")
    private BigDecimal credit;
    @Positive(message = "ID отчетного периода должен быть положительным")
    private int reportingDateId;

    @Override
    public String beautyToString() throws ControllerException {
        Company company=new Company();
        company.setCompanyId(companyId);
        company=(Company) CommandProvider.getCommandProvider().getCommand(CommandName.RECEIVE_COMPANY).execute(company);
        ReportingPeriod reportingPeriod=new ReportingPeriod();
        reportingPeriod.setReportingPeriodId(reportingDateId);
        reportingPeriod=(ReportingPeriod) CommandProvider.getCommandProvider().getCommand(CommandName.RECEIVE_REPORTING_PERIOD).execute(reportingPeriod);
        return
                company.beautyToString()+
                ", выручка от реализации= " + sales +
                ", активы= " + assets +
                ", собственный капитал= " + equity +
                ", чистую прибыль= " + PBIT +
                ", привлеченный капитал= " + credit +
                ", "+reportingPeriod.beautyToString();
    }

    @Override
    public String getPrimaryStringId() {
        return String.valueOf(initialDataId);
    }

    @Override
    public IntegerProperty getIntegerId() {
        return new SimpleIntegerProperty(companyId);
    }

    @Override
    public StringProperty getStringValue() {
        return new SimpleStringProperty(String.valueOf(reportingDateId));
    }

    public int getInitialDataId() {
        return initialDataId;
    }

    public void setInitialDataId(int initialDataId) {
        this.initialDataId = initialDataId;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public BigDecimal getSales() {
        return sales;
    }

    public void setSales(BigDecimal sales) {
        this.sales = sales;
    }

    public BigDecimal getAssets() {
        return assets;
    }

    public void setAssets(BigDecimal assets) {
        this.assets = assets;
    }

    public BigDecimal getEquity() {
        return equity;
    }

    public void setEquity(BigDecimal equity) {
        this.equity = equity;
    }

    public BigDecimal getPBIT() {
        return PBIT;
    }

    public void setPBIT(BigDecimal PBIT) {
        this.PBIT = PBIT;
    }

    public BigDecimal getCredit() {
        return credit;
    }

    public void setCredit(BigDecimal credit) {
        this.credit = credit;
    }

    public int getReportingDateId() {
        return reportingDateId;
    }

    public void setReportingDateId(int reportingDateId) {
        this.reportingDateId = reportingDateId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InitialData that = (InitialData) o;
        return initialDataId == that.initialDataId &&
                companyId == that.companyId &&
                reportingDateId == that.reportingDateId &&
                sales.equals(that.sales) &&
                assets.equals(that.assets) &&
                equity.equals(that.equity) &&
                PBIT.equals(that.PBIT) &&
                credit.equals(that.credit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(initialDataId, companyId, sales, assets, equity, PBIT, credit, reportingDateId);
    }

    @Override
    public String toString() {
        return "InitialData{" +
                "initialDataId=" + initialDataId +
                ", companyId=" + companyId +
                ", sales=" + sales +
                ", assets=" + assets +
                ", equity=" + equity +
                ", PBIT=" + PBIT +
                ", credit=" + credit +
                ", reportingDateId=" + reportingDateId +
                '}';
    }



}
