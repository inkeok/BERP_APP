package com.example.berp_and.work;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.berp_and.R;

import java.util.ArrayList;

public class WorkIndiAdapter extends RecyclerView.Adapter<WorkIndiAdapter.ViewHolder> {

    LayoutInflater inflater;
    ArrayList<WorkResultVO>list;
    Context context;


    public WorkIndiAdapter(LayoutInflater inflater, ArrayList<WorkResultVO> list, Context context) {
        this.inflater = inflater;
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = inflater.inflate(R.layout.holi_indi_item,parent,false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder h, int i) {
        h.tv_employee_id.setText(list.get(i).getEmployee_id());
        h.tv_employee_name.setText(list.get(i).getName());
        h.work_date.setText(list.get(i).getWork_date());
        h.start_work.setText(list.get(i).getStart_work());
        h.end_work.setText(list.get(i).getEnd_work());
    }

    @Override
    public int getItemCount() {
        return list.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv_employee_id, tv_employee_name, work_date,start_work,end_work;
        public ViewHolder(@NonNull View v) {
            super(v);

            tv_employee_id = v.findViewById(R.id.tv_employee_id);
            tv_employee_name = v.findViewById(R.id.tv_employee_name);
            work_date = v.findViewById(R.id.work_date);
            start_work = v.findViewById(R.id.start_work);
            end_work = v.findViewById(R.id.end_work);


        }
    }


}
