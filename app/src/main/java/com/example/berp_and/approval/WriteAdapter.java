package com.example.berp_and.approval;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.berp_and.R;

import java.util.ArrayList;


public class WriteAdapter extends RecyclerView.Adapter<WriteAdapter.WriteHolder> {
    LayoutInflater inflater;
    ArrayList<And_Ing_tableVO> list;

    public WriteAdapter(LayoutInflater inflater, ArrayList<And_Ing_tableVO> list) {
        this.inflater = inflater;
        this.list = list;
    }

    @NonNull
    @Override
    public WriteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new WriteHolder(inflater.inflate(R.layout.item_rec_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull WriteHolder h, int i) {
        h.tv_rec_num.setText(i+1+"");
        h.tv_rec_title.setText(list.get(i).getDocument_title());
        h.tv_rec_date.setText(list.get(i).getDocument_date()+"");
        h.tv_rec_name.setText(list.get(i).getApp_position_name()+" "+list.get(i).getApp_name());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class WriteHolder extends RecyclerView.ViewHolder {

        TextView tv_rec_num, tv_rec_title, tv_rec_date, tv_rec_name;
        public WriteHolder(@NonNull View v) {
            super(v);
            tv_rec_num = v.findViewById(R.id.tv_rec_num);
            tv_rec_title = v.findViewById(R.id.tv_rec_title);
            tv_rec_date = v.findViewById(R.id.tv_rec_date);
            tv_rec_name = v.findViewById(R.id.tv_rec_name);

        }
    }
}
