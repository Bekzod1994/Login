package com.example.register.api;



import com.example.register.models.LoginDto;
import com.example.register.models.LoginResponseDto;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {

    @POST("auth/login")
    Call<LoginResponseDto> getToken(@Body LoginDto body);
}
