package com.example.register.models;

import com.example.register.models.loginData.LoginResponseDto;

public class Repository {
    private LoginResponseDto loginResponseDto;
    private static Repository instance;

    private Repository() {
    }

    public static Repository getInstance() {
        if (instance == null) {
            instance = new Repository();
        }
        return instance;
    }

    public LoginResponseDto getLoginResponseDto() {
        return loginResponseDto;
    }

    public void setLoginResponseDto(LoginResponseDto loginResponseDto) {
        this.loginResponseDto = loginResponseDto;
    }
}
