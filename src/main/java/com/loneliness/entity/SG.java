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

public class SG implements Serializable ,Entity{
    private int SGId;
    @Positive(message = "Id исходных данных должно быть положительным")
    private int initialDataId;
    @NotNull(message = "Задайте ROE")
    private int roeId;
    @Positive(message = "Id компании должно быть положительным")
    private int companyId;
    @Positive(message = "Id кредита должно быть положительным")
    private int creditId;
    @Positive(message = "Id дивиданда должно быть положительным")
    private int dividendID;
    @NotNull(message = "Задайте реинвестиционную прибыль")
    private BigDecimal reinvestmentProfit;
    @NotNull(message = "Задайте Коэффициент реинвестирования")
    private BigDecimal reinvestmentRatio;
    //@NotNull(message = "SG")
    private BigDecimal SG;

    @Override
    public String beautyToString() {
        return
                "Id исходных= " + initialDataId +
                ", Id рентабельность собственного капитала = " + roeId +
                ", Id компании= " + companyId +
                ", Id кредита= " + creditId +
                ", Id дивиданда= " + dividendID +
                ", реинвестиционная прибыль= " + reinvestmentProfit +
                ", коэффициент реинвестирования= " + reinvestmentRatio +
                ", показатели экономического роста = " + SG +
                '}';
    }

    @Override
    public IntegerProperty getIntegerId() {
        return new SimpleIntegerProperty(companyId);
    }

    @Override
    public String getPrimaryStringId() {
        return String.valueOf(SGId);
    }

    @Override
    public StringProperty getStringValue() {
        return new SimpleStringProperty(String.valueOf(initialDataId));
    }

    public int getSGId() {
        return SGId;
    }

    public void setSGId(int SGId) {
        this.SGId = SGId;
    }

    public int getInitialDataId() {
        return initialDataId;
    }

    public void setInitialDataId(int initialDataId) {
        this.initialDataId = initialDataId;
    }

    public int getRoeId() {
        return roeId;
    }

    public void setRoeId(int roeId) {
        this.roeId = roeId;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
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

    public BigDecimal getReinvestmentProfit() {
        return reinvestmentProfit;
    }

    public void setReinvestmentProfit(BigDecimal reinvestmentProfit) {
        this.reinvestmentProfit = reinvestmentProfit;
    }

    public BigDecimal getReinvestmentRatio() {
        return reinvestmentRatio;
    }

    public void setReinvestmentRatio(BigDecimal reinvestmentRatio) {
        this.reinvestmentRatio = reinvestmentRatio;
    }

    public BigDecimal getSG() {
        return SG;
    }

    public void setSG(BigDecimal SG) {
        this.SG = SG;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SG sg = (SG) o;
        return SGId == sg.SGId &&
                initialDataId == sg.initialDataId &&
                companyId == sg.companyId &&
                creditId == sg.creditId &&
                dividendID == sg.dividendID &&
                roeId==sg.roeId&&
                reinvestmentProfit.equals(sg.reinvestmentProfit) &&
                reinvestmentRatio.equals(sg.reinvestmentRatio) &&
                SG.equals(sg.SG);
    }

    @Override
    public int hashCode() {
        return Objects.hash(SGId, initialDataId, roeId, companyId, creditId, dividendID, reinvestmentProfit, reinvestmentRatio, SG);
    }

    @Override
    public String toString() {
        return "SG{" +
                "SGId=" + SGId +
                ", sourceDataId=" + initialDataId +
                ", ROE=" + roeId +
                ", companyId=" + companyId +
                ", creditId=" + creditId +
                ", dividendID=" + dividendID +
                ", reinvestmentProfit=" + reinvestmentProfit +
                ", reinvestmentRatio=" + reinvestmentRatio +
                ", SG=" + SG +
                '}';
    }
}
