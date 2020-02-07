package com.example.register.presenters;

import android.os.Handler;
import android.widget.ProgressBar;

import com.example.register.api.ApiService;
import com.example.register.contracts.MainContract;
import com.example.register.models.Repository;
import com.example.register.models.getCompanyData.CompanyDataDto;
import com.example.register.models.getCompanyData.CompanyResponseDto;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainPresenter implements MainContract.Presenter {
    private CompanyResponseDto companyResponseDto;
    private MainContract.Model model;
    private MainContract.View view;
    private ApiService apiService;
    private Handler handler;

    public MainPresenter(MainContract.Model model, MainContract.View view) {
        this.model = model;
        this.view = view;
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(chain -> {
            Request newRequest = chain.request().newBuilder()
                    .addHeader("Authorization", "Bearer " + Repository.getInstance().getLoginResponseDto().getAccessToken())
                    .build();
            return chain.proceed(newRequest);
        }).build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl("http://api-dev.24m.uz/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(ApiService.class);
        incomeTabPress();
    }


    @Override
    public void incomeTabPress() {
        loadCompanyData(1);
    }

    @Override
    public void outcomeTabPress() {
        loadCompanyData(2);
    }

    @Override
    public void onClickBalance() {


    }

    void loadCompanyData(int type) {

        handler = new Handler() {
            public void handleMessage(android.os.Message msg) {
                if (msg.what == 1) {
                    view.showData(companyResponseDto,type);
                    view.spinnerVisible(ProgressBar.INVISIBLE);
                }
            }
        };
        view.spinnerVisible(ProgressBar.VISIBLE);
        Thread thread = new Thread(() -> {
            try {
                Response<CompanyDataDto> response = (apiService.getCompanyData(type).execute());
                if (response.code() == 200) {
                     companyResponseDto = response.body().getData();
                    handler.sendEmptyMessage(1);
                } else if (response.errorBody() != null) {
                    JSONObject jObjError = new JSONObject(response.errorBody().string());
                    view.showError(jObjError.get("message").toString());
                    handler.sendEmptyMessage(1);
                }
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
        });
        thread.start();
    }
}
