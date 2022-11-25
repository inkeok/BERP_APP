package com.example.berp_and.notice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.berp_and.R;

import java.util.ArrayList;

public class NoticeAdapter  extends RecyclerView.Adapter<NoticeAdapter.ViewHolder> {

    LayoutInflater inflater;
    ArrayList<NoticeVO> list;
    Context context;

    public NoticeAdapter(LayoutInflater inflater, ArrayList<NoticeVO> list, Context context) {
        this.inflater = inflater;
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = inflater.inflate(R.layout.notice_item,parent,false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder h, int i) {
        h.tv_notice_dual_num.setText(i+1+"");
        h.tv_notice_name.setText(list.get(i).getNotice_writer());
        h.tv_notice_title.setText(list.get(i).getNotice_title());
        h.tv_notice_date.setText(list.get(i).getNotice_date()+"");


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_notice_dual_num, tv_notice_name, tv_notice_title,tv_notice_date;


        public ViewHolder(@NonNull View v) {
            super(v);

            tv_notice_dual_num = v.findViewById(R.id.tv_notice_dual_num);
            tv_notice_name = v.findViewById(R.id.tv_notice_name);
            tv_notice_title = v.findViewById(R.id.tv_notice_title);
            tv_notice_date = v.findViewById(R.id.tv_notice_date);


        }
    }
}
