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

public class Dividend implements Serializable,Entity {
    private int dividendId;
    @Positive(message = "id компании должно быть положительным ")
    private int companyId;
    @NotNull(message = "Должны быть заданы проценты дивидента ")
    private BigDecimal dividendPercentage;
    @NotNull(message = "Должен быть задан получатель ")
    private String recipient;
    @Positive(message = "id отчётного периода должно быть положительным ")
    private int reportingPeriodId;

    @Override
    public String beautyToString() throws ControllerException {
        Company company=new Company();
        company.setCompanyId(companyId);
        company=(Company) CommandProvider.getCommandProvider().getCommand(CommandName.RECEIVE_COMPANY).execute(company);
        ReportingPeriod reportingPeriod=new ReportingPeriod();
        reportingPeriod.setReportingPeriodId(reportingPeriodId);
        reportingPeriod=(ReportingPeriod) CommandProvider.getCommandProvider().getCommand(CommandName.RECEIVE_REPORTING_PERIOD).execute(reportingPeriod);
        return
                ""+company.beautyToString() +
                ", проценты дивидента= " + dividendPercentage +
                ", получатель= '" + recipient + '\'' +
                "," +reportingPeriod.beautyToString();
    }

    @Override
    public String getPrimaryStringId() {
        return String.valueOf(dividendId);
    }

    @Override
    public IntegerProperty getIntegerId() {
        return new SimpleIntegerProperty(companyId);
    }

    @Override
    public StringProperty getStringValue() {
        return new SimpleStringProperty(recipient);
    }

    public int getDividendId() {
        return dividendId;
    }

    public void setDividendId(int dividendId) {
        this.dividendId = dividendId;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public BigDecimal getDividendPercentage() {
        return dividendPercentage;
    }

    public void setDividendPercentage(BigDecimal dividendPercentage) {
        this.dividendPercentage = dividendPercentage;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public int getReportingPeriodId() {
        return reportingPeriodId;
    }

    public void setReportingPeriodId(int reportingPeriodId) {
        this.reportingPeriodId = reportingPeriodId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dividend dividend = (Dividend) o;
        return dividendId == dividend.dividendId &&
                companyId == dividend.companyId &&
                reportingPeriodId == dividend.reportingPeriodId &&
                dividendPercentage.equals(dividend.dividendPercentage) &&
                recipient.equals(dividend.recipient);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dividendId, companyId, dividendPercentage, recipient, reportingPeriodId);
    }

    @Override
    public String toString() {
        return "Dividend{" +
                "dividendId=" + dividendId +
                ", companyId=" + companyId +
                ", dividendPercentage=" + dividendPercentage +
                ", recipient='" + recipient + '\'' +
                ", reportingPeriodId=" + reportingPeriodId +
                '}';
    }
}
