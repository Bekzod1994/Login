package com.example.register.models.CompanyBalances;

public class CompanyBalanceDetails {
    private String bankName;
    private String account;
    private String amount;

    public String getBankName() {
        return bankName;
    }

    public String getAccount() {
        return account;
    }

    public String getAmount() {
        return amount;
    }

    public CompanyBalanceDetails(String bankName, String account, String amount) {
        this.bankName = bankName;
        this.account = account;
        this.amount = amount;
    }
}
