package com.example.register.models;

import com.example.register.contracts.MainContract;
import com.example.register.models.getCompanyData.Payment;

import java.util.ArrayList;

public class MainModel implements MainContract.Model {
    @Override
    public ArrayList<Payment> getCompanyData() {
        return null;
    }
}
