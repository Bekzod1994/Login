package com.example.register.contracts;

import com.example.register.models.LoginDto;

public interface LoginContract {
    interface Model{

    }
    interface View{
        void showLoginError();
        void showPasswordError();


    }
    interface Presenter{
        void loginClick(LoginDto loginDto);


    }
}
