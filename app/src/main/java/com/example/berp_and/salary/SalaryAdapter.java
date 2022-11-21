package com.example.berp_and.salary;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.berp_and.R;

import java.util.ArrayList;
import java.util.List;


public class SalaryAdapter extends RecyclerView.Adapter<SalaryAdapter.SalaryHolder> {
    LayoutInflater layoutInflater;
    Context context;
    ArrayList<SalaryVO> salaryList;

    public SalaryAdapter(LayoutInflater layoutInflater, Context context, ArrayList<SalaryVO> salaryList) {
        this.layoutInflater = layoutInflater;
        this.context = context;
        this.salaryList = salaryList;
    }

    public LayoutInflater getLayoutInflater() {
        return layoutInflater;
    }

    public void setLayoutInflater(LayoutInflater layoutInflater) {
        this.layoutInflater = layoutInflater;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public ArrayList<SalaryVO> getSalaryList() {
        return salaryList;
    }

    public void setSalaryList(ArrayList<SalaryVO> salaryList) {
        this.salaryList = salaryList;
    }

    @NonNull
    @Override
    public SalaryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = layoutInflater.inflate(R.layout.item_salarylist_recv, parent, false);
        return new SalaryHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SalaryHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return salaryList.size();
    }

    class SalaryHolder extends RecyclerView.ViewHolder {

        public SalaryHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
