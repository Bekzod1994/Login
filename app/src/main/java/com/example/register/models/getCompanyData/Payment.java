package com.example.register.models.getCompanyData;

import java.util.SplittableRandom;

public class Payment {
    private String companyName;
    private String amount;
    private String date;

    public Payment(String companyName, String date, String amount) {
        this.companyName = companyName;
        this.date = date;
        this.amount = amount;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCost() {
        return amount;
    }

    public void setCost(String amount) {
        this.amount = amount;
    }
}
