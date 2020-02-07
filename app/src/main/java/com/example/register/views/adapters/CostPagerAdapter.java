package com.example.register.views.adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.register.views.CompanyFragment;

import java.util.List;

public class CostPagerAdapter extends FragmentPagerAdapter {
    private List<CompanyFragment> fragments;
    private List<String> pagerTitles;

    public CostPagerAdapter(@NonNull FragmentManager fm, List<CompanyFragment> fragments, List<String> pagerTitles) {
        super(fm);
        this.fragments = fragments;
        this.pagerTitles = pagerTitles;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return pagerTitles.get(position);
    }

}
