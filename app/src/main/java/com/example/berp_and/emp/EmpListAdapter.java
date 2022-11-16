package com.example.berp_and.emp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.berp_and.R;

import java.util.ArrayList;

public class EmpListAdapter extends RecyclerView.Adapter<EmpListAdapter.EmpListHolder> {

    LayoutInflater inflater;
    ArrayList<EmpVO> list;
    Context context;

    public EmpListAdapter(LayoutInflater inflater, ArrayList<EmpVO> list, Context context) {
        this.inflater = inflater;
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public EmpListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new EmpListHolder(inflater.inflate(R.layout.item_emplist, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull EmpListHolder h, int i) {

        h.tv_employee_id.setText(list.get(i).getEmployee_id()+"");
        h.tv_employee_name.setText(list.get(i).getName());
        h.tv_employee_department.setText(list.get(i).getDepartment_name());
        h.tv_hire_date.setText(list.get(i).getHire_date()+"");
        h.tv_employee_email.setText(list.get(i).getEmail());

        h.img_empSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class EmpListHolder extends RecyclerView.ViewHolder{

        TextView tv_employee_id, tv_employee_name, tv_employee_department, tv_hire_date, tv_employee_email;
        ImageView img_empSearch;
        public EmpListHolder(@NonNull View v) {
            super(v);

            tv_employee_id = v.findViewById(R.id.tv_employee_id);
            tv_employee_name = v.findViewById(R.id.tv_employee_name);
            tv_employee_department = v.findViewById(R.id.tv_employee_department);
            tv_hire_date = v.findViewById(R.id.tv_hire_date);
            tv_employee_email = v.findViewById(R.id.tv_employee_email);
            img_empSearch = v.findViewById(R.id.img_empSearch);
        }
    }
}
