package com.example.berp_and.salary;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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

    @NonNull
    @Override
    public SalaryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = layoutInflater.inflate(R.layout.item_salarylist_recv, parent, false);
        return new SalaryHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SalaryHolder h, int i) {
        h.tv_name.setText(salaryList.get(i).getName());
        h.tv_commission.setText(salaryList.get(i).getCommission_pct());
        h.tv_c_employee_parttern.setText(salaryList.get(i).getC_employee_pattern());
        h.tv_salary.setText(salaryList.get(i).getSalary());
        h.tv_c_position.setText(salaryList.get(i).getC_position());
        h.tv_hire_date.setText(salaryList.get(i).getHire_date());
        h.tv_department_name.setText(salaryList.get(i).getDepartment_id());
        h.tv_employee_id.setText(salaryList.get(i).getEmployee_id());
    }

    @Override
    public int getItemCount() {
        return salaryList.size();
    }

    class SalaryHolder extends RecyclerView.ViewHolder {
        TextView tv_employee_id, tv_name,tv_c_employee_parttern, tv_c_position,
        tv_department_name, tv_commission, tv_hire_date, tv_salary;
        public SalaryHolder(@NonNull View v) {
            super(v);
            tv_employee_id = v.findViewById(R.id.tv_employee_id);
            tv_name = v.findViewById(R.id.tv_name);
            tv_c_employee_parttern = v.findViewById(R.id.tv_c_employee_parttern);
            tv_c_position = v.findViewById(R.id.tv_c_position);
            tv_department_name = v.findViewById(R.id.tv_department_name);
            tv_commission = v.findViewById(R.id.tv_commission);
            tv_hire_date = v.findViewById(R.id.tv_hire_date);
            tv_salary = v.findViewById(R.id.tv_salary);
        }
    }
}
