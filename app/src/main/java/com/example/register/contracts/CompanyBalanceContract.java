package com.example.register.contracts;

import com.example.register.models.CompanyBalances.CompanyBalanceDetails;
import com.example.register.models.getCompanyData.CompanyResponseDto;
import com.example.register.models.getCompanyData.Payment;

import java.util.ArrayList;

public interface CompanyBalanceContract {
    interface Model {
        ArrayList<CompanyBalanceDetails> getCompanyData();

    }
    interface View {

        void showData(CompanyBalanceDetails data);

    }



    interface Presenter {

        void clickTab(int position);

    }


}
