package com.example.register.views;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.register.R;
import com.example.register.contracts.MainContract;
import com.example.register.models.getCompanyData.CompanyResponseDto;
import com.example.register.models.MainModel;
import com.example.register.presenters.MainPresenter;
import com.example.register.views.adapters.CostAdapter;
import com.example.register.views.adapters.CostPagerAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements MainContract.View {
    private MainContract.Presenter presenter;
    private MainContract.Model model;
    private FragmentTransaction fTrans;
    private CostPagerAdapter adapter;
    private TabLayout tabLayout;
    private List<CompanyFragment> fragments;
    private List<String> titles;
    private ViewPager viewPager;
    private ProgressBar progressBar;
    private TextView companyName;
    private TextView companyBalance;
    private CompanyFragment companyFragmentInput;
    private CompanyFragment companyFragmentOutput;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadViews();
        loadData();
        model = new MainModel();
        presenter = new MainPresenter(model, this);
    }

    private void loadViews() {
        progressBar = findViewById(R.id.progressBar);
        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);
        companyName = findViewById(R.id.companyName);
        companyName.setOnClickListener(view -> onClickBalance());
        companyBalance = findViewById(R.id.companyBalance);
        companyBalance.setOnClickListener(view -> onClickBalance());
    }

    private void loadData() {
        fragments = new ArrayList<>();
        titles = new ArrayList<>();
        companyFragmentInput = new CompanyFragment(this);
        companyFragmentOutput = new CompanyFragment(this);
        fragments.add(companyFragmentInput);
        fragments.add(companyFragmentOutput);
        titles.add(getString(R.string.income));
        titles.add(getString(R.string.outcome));
        adapter = new CostPagerAdapter(getSupportFragmentManager(), fragments, titles);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getSelectedTabPosition();
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0) {
                    presenter.incomeTabPress();
                } else if (tab.getPosition() == 1) {
                    presenter.outcomeTabPress();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }

        });
    }


    @Override
    public void showData(CompanyResponseDto data, int fragmentPosition) {
        fragments.get(fragmentPosition - 1).setDataAdapter(data.getPayments());
        companyBalance.setText(getString(R.string.currency, data.getCompanyBalance()));
        titles.set(0, String.valueOf(data.getIncomeSum()));
        titles.set(1, String.valueOf(data.getOutcomeSum()));
    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void spinnerVisible(int visible) {
        progressBar.setVisibility(visible);

    }

    private void onClickBalance() {
        startActivity(new Intent(this,ComBalanceActivity.class));
    }


}
