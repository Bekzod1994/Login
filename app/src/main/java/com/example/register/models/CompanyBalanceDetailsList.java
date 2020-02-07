package com.example.register.models;

import com.example.register.models.CompanyBalances.CompanyBalanceDetails;

import java.util.ArrayList;

public class CompanyBalanceDetailsList {
    private String code;
    private boolean accept;
    private String status;
    private ArrayList<CompanyBalanceDetails> data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isAccept() {
        return accept;
    }

    public void setAccept(boolean accept) {
        this.accept = accept;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<CompanyBalanceDetails> getData() {
        return data;
    }

    public void setData(ArrayList<CompanyBalanceDetails> data) {
        this.data = data;
    }
}
