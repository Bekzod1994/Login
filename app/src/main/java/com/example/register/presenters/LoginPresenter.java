package com.example.register.presenters;

import android.os.Handler;
import android.util.Log;
import android.widget.ProgressBar;

import com.example.register.api.ApiService;
import com.example.register.contracts.LoginContract;
import com.example.register.models.Repository;
import com.example.register.models.loginData.LoginDto;
import com.example.register.models.loginData.LoginResponseDto;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginPresenter implements LoginContract.Presenter {
    private LoginContract.View view;
    private LoginContract.Model model;
    private ApiService apiService;
    LoginResponseDto loginResponseDto;
    private Handler handler;


    public LoginPresenter( LoginContract.View view,LoginContract.Model model) {
        this.model = model;
        this.view = view;
       networkConnection();
    }

    @Override
    public void loginClick(LoginDto loginDto) {
        view.setSpinnerVisible(1);
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

        Call<LoginResponseDto> request = apiService.getToken(loginDto);
        handler = new Handler() {
            public void handleMessage(android.os.Message msg) {
                if (msg.what == 1) {
                    view.startMain();
                    view.setSpinnerVisible(0);
                }else if(msg.what == 2) {
                    view.setSpinnerVisible(0);
                }
            }
        };
        Thread thread = new Thread(() -> {
            try {
                Response<LoginResponseDto> response = request.execute();
                if(response.code() == 200){
                    loginResponseDto = response.body();
                    Repository.getInstance().setLoginResponseDto(loginResponseDto);
                    loginResponseDto = Repository.getInstance().getLoginResponseDto();
                    handler.sendEmptyMessage(1);
                }else {
                    JSONObject jObjError = new JSONObject(response.errorBody().string());
                    view.showError(jObjError.get("message").toString());
                    handler.sendEmptyMessage(2);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        });
        thread.start();
//        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(chain -> {
//            Request newRequest  = chain.request().newBuilder()
//                    .addHeader("Authorization", "Bearer " + loginResponseDto.getAccessToken())
//                    .build();
//            return chain.proceed(newRequest);
//        }).build();
//
//        Retrofit retrofit  = new Retrofit.Builder()
//                .client(client)
//                .baseUrl("http://api-mobile.24m.uz/api/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();

return null;

    }
}
