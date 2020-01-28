package com.example.register.presenters;
import android.util.Log;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import com.example.register.api.ApiService;
import com.example.register.contracts.LoginContract;
import com.example.register.models.LoginDto;
import com.example.register.models.LoginResponseDto;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.annotation.Annotation;

import javax.net.ssl.SSLEngineResult;

public class LoginPresenter implements LoginContract.Presenter {
    private LoginContract.View view;
    private LoginContract.Model model;
    private   ApiService apiService;
    LoginResponseDto loginResponseDto;

    public LoginPresenter( LoginContract.View view,LoginContract.Model model) {
        this.model = model;
        this.view = view;
       networkConnection();
    }



    @Override
    public void loginClick(LoginDto loginDto) {
        loadData(loginDto);


        }


    private void networkConnection(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api-mobile.24m.uz/api/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(ApiService.class);
    }
    private LoginResponseDto  loadData(LoginDto loginDto) {

        Call<LoginResponseDto> request = apiService.getToken(new LoginDto("566584589AAA","12345"));
        Thread thread = new Thread(() -> {
            try {
                Response<LoginResponseDto> response = request.execute();
                if(response.code() == 200){
                    loginResponseDto = response.body();
                    loginResponseDto.getAccessToken();
                    loginResponseDto.getTokenType();
                    Log.i("TAG", loginResponseDto.getAccessToken());
                }else {
                    JSONObject jObjError = new JSONObject(response.errorBody().string());
                    String message =  jObjError.get("message").toString();
                    Log.i("TAG", jObjError.get("message").toString());
                }


                 response.code();
                Log.i("TAG", loginResponseDto.toString());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        });
        thread.start();
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(chain -> {
            Request newRequest  = chain.request().newBuilder()
                    .addHeader("Authorization", "Bearer " + loginResponseDto.getAccessToken())
                    .build();
            return chain.proceed(newRequest);
        }).build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl("http://api-mobile.24m.uz/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

return null;

    }
}
