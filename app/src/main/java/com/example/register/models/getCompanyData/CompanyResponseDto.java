package com.example.register.models.getCompanyData;

import java.util.ArrayList;

public class CompanyResponseDto {
    private ArrayList<Payment> payments;
    private Double companyBalance;
    private Double incomeSum;
    private Double outcomeSum;

    public ArrayList<Payment> getPayments() {
        return payments;
    }


    public Double getCompanyBalance() {
        return companyBalance;
    }


    public Double getIncomeSum() {
        return incomeSum;
    }


    public Double getOutcomeSum() {
        return outcomeSum;
    }

}
