package com.loneliness.entity;


import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;

public class ReportingPeriod implements Serializable,Entity {
    private int reportingPeriodId;
    @Positive(message = "Год дожен быть положитнльным")
    private int year;
    @NotNull(message = "Должен быть задан квартал")
    private Quarter quarter;
    @Positive(message = "id компании дожен быть положитнльным")
    private int companyId;

    @Override
    public String beautyToString() {

        return "год= " + year +
                ", квартал= " + quarter ;
    }

    @Override
    public String getPrimaryStringId() {
        return String.valueOf(reportingPeriodId);
    }

    @Override
    public IntegerProperty getIntegerId() {
        return new SimpleIntegerProperty(companyId);
    }

    @Override
    public StringProperty getStringValue() {
        return new SimpleStringProperty(String.valueOf(year));
    }

    public int getReportingPeriodId() {
        return reportingPeriodId;
    }

    public void setReportingPeriodId(int reportingPeriodId) {
        this.reportingPeriodId = reportingPeriodId;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public Quarter getQuarter() {
        return quarter;
    }

    public void setQuarter(Quarter quarter) {
        this.quarter = quarter;
    }

    @Override
    public String toString() {
        return "ReportingPeriod{" +
                "reportingPeriodId=" + reportingPeriodId +
                ", year=" + year +
                ", quarter=" + quarter +
                ", companyId=" + companyId +
                '}';
    }
}
