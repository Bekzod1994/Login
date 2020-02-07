package com.example.register.views;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.register.R;
import com.example.register.models.getCompanyData.Payment;
import com.example.register.views.adapters.CostAdapter;

import java.util.ArrayList;

public class CompanyFragment extends Fragment {
    private CostAdapter adapter;
    private Context context;
    private RecyclerView.LayoutManager layoutManager;

    public CompanyFragment(Context context) {
        this.context = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_company, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new CostAdapter();
        recyclerView.setAdapter(adapter);
        Log.i("TTT", "recyc");

    }

    public void setDataAdapter(ArrayList<Payment> companyResponseDtoList) {
        adapter.setData(companyResponseDtoList);
    }
}
