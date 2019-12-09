package com.loneliness.entity;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public class ROE implements Serializable,Entity {
    private int ROEId;
    @Positive(message = "Id компании должно быть положительным ")
    private int companyId;
    @Positive(message = "Id исходных данных должно быть положительным ")
    private int initialDataId;
    @Positive(message = "Id кредита должно быть положительным ")
    private int creditId;
    @Positive(message = "Id дивиданда должно быть положительным ")
    private int dividendID;
    //@NotNull(message = "Задайте ROE")
    private BigDecimal ROE;
    @NotNull(message = "Задайте оперативную прибыль ")
    private BigDecimal EBIT;
    //@NotNull(message = "Задайте рентабельность продаж")
    private BigDecimal profR;
    //@NotNull(message = "Задайте RONA")
    private BigDecimal RONA;
    //@NotNull(message = "Задайте Fl")
    private BigDecimal FL;

    @Override
    public String beautyToString() {
        return
                "Id компании=" + companyId +
                ", Id исходных данных= " + initialDataId +
                ", Id кредита= " + creditId +
                ", Id дивиданда= " + dividendID +
                ", рентабельность собственного капитала = " + ROE +
                ", Оперативная прибыль= " + EBIT +
                ", рентабельность продаж= " + profR +
                ", рентабельность чистых активов = " + RONA +
                ", Финансовый рычаг = " + FL ;
    }

    @Override
    public String getPrimaryStringId() {
        return String.valueOf(ROEId);
    }

    @Override
    public IntegerProperty getIntegerId() {
        return new SimpleIntegerProperty(companyId);
    }

    @Override
    public StringProperty getStringValue() {
        return new SimpleStringProperty(String.valueOf(initialDataId));
    }

    public int getROEId() {
        return ROEId;
    }

    public void setROEId(int ROEId) {
        this.ROEId = ROEId;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public int getInitialDataId() {
        return initialDataId;
    }

    public void setInitialDataId(int initialDataId) {
        this.initialDataId = initialDataId;
    }

    public int getCreditId() {
        return creditId;
    }

    public void setCreditId(int creditId) {
        this.creditId = creditId;
    }

    public int getDividendID() {
        return dividendID;
    }

    public void setDividendID(int dividendID) {
        this.dividendID = dividendID;
    }

    public BigDecimal getROE() {
        return ROE;
    }

    public void setROE(BigDecimal ROE) {
        this.ROE = ROE;
    }

    public BigDecimal getEBIT() {
        return EBIT;
    }

    public void setEBIT(BigDecimal EBIT) {
        this.EBIT = EBIT;
    }

    public BigDecimal getProfR() {
        return profR;
    }

    public void setProfR(BigDecimal profR) {
        this.profR = profR;
    }

    public BigDecimal getRONA() {
        return RONA;
    }

    public void setRONA(BigDecimal RONA) {
        this.RONA = RONA;
    }

    public BigDecimal getFL() {
        return FL;
    }

    public void setFL(BigDecimal FL) {
        this.FL = FL;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ROE roe = (ROE) o;
        return ROEId == roe.ROEId &&
                companyId == roe.companyId &&
                initialDataId == roe.initialDataId &&
                creditId == roe.creditId &&
                dividendID == roe.dividendID &&
                ROE.equals(roe.ROE) &&
                EBIT.equals(roe.EBIT) &&
                profR.equals(roe.profR) &&
                RONA.equals(roe.RONA) &&
                FL.equals(roe.FL);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ROEId, companyId, initialDataId, creditId, dividendID, ROE, EBIT, profR, RONA, FL);
    }

    @Override
    public String toString() {
        return "ROE{" +
                "ROEId=" + ROEId +
                ", companyId=" + companyId +
                ", sourceDataId=" + initialDataId +
                ", creditId=" + creditId +
                ", dividendID=" + dividendID +
                ", ROE=" + ROE +
                ", EBIT=" + EBIT +
                ", profR=" + profR +
                ", RONA=" + RONA +
                ", FL=" + FL +
                '}';
    }
}
