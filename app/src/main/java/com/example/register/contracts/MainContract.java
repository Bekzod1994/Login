package com.example.register.contracts;

import com.example.register.models.getCompanyData.Payment;
import com.example.register.models.getCompanyData.CompanyResponseDto;

import java.util.ArrayList;

public interface MainContract {
    interface Model {
        ArrayList<Payment> getCompanyData();

    }

    interface View {
        void showData(CompanyResponseDto data, int fragmentPosition);
        void showError(String message);
        void spinnerVisible(int visible);

    }

    interface Presenter {
        void incomeTabPress();
        void outcomeTabPress();
        void onClickBalance();
    }


}
