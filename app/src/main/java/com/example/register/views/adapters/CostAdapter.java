package com.example.register.views.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.register.R;
import com.example.register.models.getCompanyData.Payment;

import java.util.ArrayList;

public class CostAdapter extends RecyclerView.Adapter<CostAdapter.ViewHolder> {
    private ArrayList<Payment> data = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cost_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.companyNameTV.setText(data.get(position).getCompanyName());
        holder.dateTV.setText(data.get(position).getDate());
        holder.costTV.setText(getSum(data.get(position).getCost()));

    }
    public String getSum(String str) {
        if (str != null && str.length() > 5 ) {
            str = str.substring(0, str.length() - 4);
        }
        return str;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(ArrayList<Payment> list) {
        data.clear();
        data.addAll(list);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public TextView companyNameTV;
        public TextView dateTV;
        public TextView costTV;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.companyNameTV = itemView.findViewById(R.id.companyName);
            this.dateTV = itemView.findViewById(R.id.date);
            this.costTV = itemView.findViewById(R.id.valueCost);
        }
    }

}
