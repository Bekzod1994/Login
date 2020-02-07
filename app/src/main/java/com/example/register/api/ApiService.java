package com.example.register.api;



import com.example.register.models.CompanyBalanceDetailsList;
import com.example.register.models.CompanyBalances.CompanyBalanceDetails;
import com.example.register.models.getCompanyData.CompanyDataDto;
import com.example.register.models.loginData.LoginDto;
import com.example.register.models.loginData.LoginResponseDto;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {

    @POST("auth/login")
    Call<LoginResponseDto> getToken(@Body LoginDto body);
    @GET("company/get/data")
    Call<CompanyDataDto> getCompanyData(@Query("type") int type);
    @GET("company/get/balance/details")
    Call<CompanyBalanceDetailsList> getCompanyBalanceDetails();
}
