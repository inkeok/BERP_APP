package com.example.berp_and.apply;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.berp_and.R;

import java.util.ArrayList;

public class ApplyListAdapter extends RecyclerView.Adapter<ApplyListAdapter.ApplyListHolder> {
    LayoutInflater inflater;
    ArrayList<RecruitVO> rec_list;

    public ApplyListAdapter(LayoutInflater inflater, ArrayList<RecruitVO> rec_list) {
        this.inflater = inflater;
        this.rec_list = rec_list;
    }

    @NonNull
    @Override
    public ApplyListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ApplyListHolder(inflater.inflate(R.layout.item_recruit_board, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ApplyListHolder h, int i) {
        h.tv_rec_company.setText(rec_list.get(i).getCompany_name());
        h.tv_rec_title.setText(rec_list.get(i).getRecruit_title());
        h.tv_rec_career.setText(rec_list.get(i).getCareer_name());
        h.tv_rec_employee_pattern.setText(rec_list.get(i).getEmployee_pattern_name());
        h.tv_rec_salary.setText("[연봉]" + rec_list.get(i).getSalary() +"만원");

    }

    @Override
    public int getItemCount() {
        return rec_list.size();
    }

    public class ApplyListHolder extends RecyclerView.ViewHolder{
        TextView tv_rec_company, tv_rec_title, tv_rec_career, tv_rec_employee_pattern, tv_rec_salary;
        public ApplyListHolder(@NonNull View v) {
            super(v);
            tv_rec_company = v.findViewById(R.id.tv_rec_company);
            tv_rec_title = v.findViewById(R.id.tv_rec_title);
            tv_rec_career = v.findViewById(R.id.tv_rec_career);
            tv_rec_employee_pattern = v.findViewById(R.id.tv_rec_employee_pattern);
            tv_rec_salary = v.findViewById(R.id.tv_rec_salary);
        }
    }
}
