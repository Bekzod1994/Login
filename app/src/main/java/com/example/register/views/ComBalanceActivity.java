package com.example.register.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.widget.ProgressBar;

import com.example.register.R;
import com.example.register.api.ApiService;
import com.example.register.models.CompanyBalanceDetailsList;
import com.example.register.models.CompanyBalances.CompanyBalanceDetails;
import com.example.register.models.Repository;
import com.example.register.models.getCompanyData.CompanyDataDto;
import com.example.register.views.adapters.CompanyBalanceDetailsAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ComBalanceActivity extends AppCompatActivity {
    private RecyclerView recyclerViewBalance;
    private ArrayList<CompanyBalanceDetails> list = new ArrayList<>();
    private CompanyBalanceDetailsAdapter adapter;
    private ApiService apiService;
    private List<CompanyBalanceDetails> companyBalanceDetailsData;
    private Handler handler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_com_balance);
        recyclerViewBalance = findViewById(R.id.recyclerViewBalance);
        setTitle("Balance");
        list.add(new CompanyBalanceDetails("Xalq Banki","11114343434" ,"32345670"));

        adapter = new CompanyBalanceDetailsAdapter(this);
        recyclerViewBalance.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewBalance.setAdapter(adapter);
        net();
        loadCompanyData();
        adapter.setData(list);

    }
    private void net(){
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
    }
    void loadCompanyData() {

        handler = new Handler() {
            public void handleMessage(android.os.Message msg) {
                if (msg.what == 1) {
                   list.addAll(companyBalanceDetailsData);
                }
            }
        };
        Thread thread = new Thread(() -> {
            try {
                Response<CompanyBalanceDetailsList> response = (apiService.getCompanyBalanceDetails().execute());
                if (response.code() == 200) {
                    companyBalanceDetailsData = response.body().getData();
                    handler.sendEmptyMessage(1);
                } else if (response.errorBody() != null) {
                    JSONObject jObjError = new JSONObject(response.errorBody().string());
                    handler.sendEmptyMessage(1);
                }
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
        });
        thread.start();
    }

}
