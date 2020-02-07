package com.example.register.views.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.register.R;
import com.example.register.models.CompanyBalances.CompanyBalanceDetails;

import java.util.ArrayList;

public class CompanyBalanceDetailsAdapter extends RecyclerView.Adapter<CompanyBalanceDetailsAdapter.ViewHolder>  {
    private ArrayList<CompanyBalanceDetails> data = new ArrayList<>();
    private Context context;

    public CompanyBalanceDetailsAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cost_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bankName.setText(data.get(position).getBankName());
        holder.account.setText(data.get(position).getAccount());
        holder.amount.setText(data.get(position).getAmount());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(ArrayList<CompanyBalanceDetails> list) {
        data.clear();
        data.addAll(list);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public TextView bankName;
        public TextView account;
            public TextView amount;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.bankName = itemView.findViewById(R.id.companyName);
            this.account = itemView.findViewById(R.id.date);
            this.amount = itemView.findViewById(R.id.valueCost);
        }
    }
}
