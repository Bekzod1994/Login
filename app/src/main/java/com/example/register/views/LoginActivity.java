package com.example.register.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.register.R;
import com.example.register.contracts.LoginContract;
import com.example.register.models.LoginDto;
import com.example.register.models.Reposirory;
import com.example.register.presenters.LoginPresenter;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {
    private LoginContract.Model model;
    private LoginContract.Presenter presenter;
    private TextInputLayout textInputLogin;
    private TextInputLayout textInputPassword;
    private LoginDto loginDto;
    private MaterialButton loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        textInputLogin = findViewById(R.id.textInputLogin);
        textInputPassword = findViewById(R.id.textInputPassword);
        load();

    }

    private void load(){
        loginButton = findViewById(R.id.loginButton);
        presenter = new LoginPresenter(this, new Reposirory());
        loginButton.setOnClickListener(view -> presenter.loginClick(getLoginDto()));

    }


    private LoginDto getLoginDto() {
        return new LoginDto(textInputLogin.getTransitionName(),textInputPassword.getTransitionName());
    }

    @Override
    public void showLoginError() {

    }

    @Override
    public void showPasswordError() {

    }
}
