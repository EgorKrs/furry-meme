package com.loneliness.entity;

public enum  Report {
    ROE,INITIAL_DATA;
    private int companyId;
    private int year;

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
