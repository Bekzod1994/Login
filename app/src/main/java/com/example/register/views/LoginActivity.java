package com.example.register.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.register.R;
import com.example.register.contracts.LoginContract;
import com.example.register.models.loginData.LoginDto;
import com.example.register.models.loginData.LoginModel;
import com.example.register.presenters.LoginPresenter;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {
    private LoginContract.Model model;
    private LoginContract.Presenter presenter;
    private TextInputLayout textInputLogin;
    private TextInputLayout textInputPassword;
    private LoginDto loginDto;
    private MaterialButton loginButton;
    private EditText login, password;
    private ProgressBar spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    @Override
    protected void onPause() {
        super.onPause();
        spinner.setVisibility(ProgressBar.INVISIBLE);
    }

    private void init() {
        spinner = findViewById(R.id.progressBar1);
        login = findViewById(R.id.login);
        password = findViewById(R.id.password);
        textInputLogin = findViewById(R.id.textInputLogin);
        textInputPassword = findViewById(R.id.textInputPassword);
        loginButton = findViewById(R.id.loginButton);
        presenter = new LoginPresenter(this, new LoginModel());
        loginButton.setOnClickListener(view -> presenter.loginClick( getLoginDto()));

    }


    private LoginDto getLoginDto() {
        return new LoginDto(login.getText().toString(), password.getText().toString());
    }

    @Override
    public void startMain() {
        startActivity(new Intent(this, MainActivity.class));

    }

    @Override
    public void showError(String message) {
        Snackbar.make(textInputPassword, message, BaseTransientBottomBar.LENGTH_LONG)
                .setBackgroundTint(ContextCompat.getColor(this, R.color.design_default_color_error))
                .show();
    }

    @Override
    public void setSpinnerVisible(int visible) {
        if (visible == 1) spinner.setVisibility(View.VISIBLE);
        if (visible == 0) spinner.setVisibility(View.INVISIBLE);
    }




}
