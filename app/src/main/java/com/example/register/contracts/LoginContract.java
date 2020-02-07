package com.example.register.contracts;

        import com.example.register.models.loginData.LoginDto;

public interface LoginContract {
    interface Model{

    }
    interface View{
        void startMain();
        void showError(String message);
        void setSpinnerVisible(int visible);



    }
    interface Presenter{
        void loginClick(LoginDto loginDto);
    }
}