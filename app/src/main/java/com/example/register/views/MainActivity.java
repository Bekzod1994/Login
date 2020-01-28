package com.example.register.views;

import android.app.FragmentTransaction;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.register.R;
import com.google.android.material.tabs.TabLayout;


public class MainActivity extends AppCompatActivity {
    private FragmentTransaction fTrans;

    private TabAdapter adapter;
    private TabLayout tabLayout;
  //  private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         loadData();

    }
    private void loadData(){
        adapter = new TabAdapter(getSupportFragmentManager());
        adapter.addFragment(new InputFragment(), "Tab 1");
        adapter.addFragment(new OutputFragment(), "Tab 2");
//        tabLayout.setupWithViewPager(viewPager);



    }

}
