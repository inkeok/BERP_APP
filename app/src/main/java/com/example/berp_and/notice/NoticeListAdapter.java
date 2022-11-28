package com.example.berp_and.notice;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.berp_and.CommonAskTask;
import com.example.berp_and.R;

import java.io.Serializable;
import java.util.ArrayList;

public class NoticeListAdapter  extends RecyclerView.Adapter<NoticeListAdapter.ViewHolder> {

    LayoutInflater inflater;
    ArrayList<NoticeVO> list;
    Context context;


    public NoticeListAdapter(LayoutInflater inflater, ArrayList<NoticeVO> list, Context context) {
        this.inflater = inflater;
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public NoticeListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.notice_list_item,parent,false);


        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull NoticeListAdapter.ViewHolder h, @SuppressLint("RecyclerView") int i) {
    h.notice_title.setText(list.get(i).getNotice_title());
    h.notice_writer.setText(list.get(i).getNotice_writer());
    h.notice_readCnt.setText(list.get(i).getNotice_readcnt()+"");
    h.notice_date.setText(list.get(i).getNotice_date()+"");
    h.lin_notice_list.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, NoticeDetailActivity.class);
            intent.putExtra("vo", (Serializable) list.get(i));
            context.startActivity(intent);
            h.notice_readCnt.setText(list.get(i).getNotice_readcnt()+"");
            CommonAskTask askTask = new CommonAskTask("detail_notice_list", context);
            askTask.addParam("notice_num",list.get(i).getNotice_num()+"");
            askTask.executeAsk(new CommonAskTask.AsynkTaskCallback() {
                @Override
                public void onResult(String data, boolean isResult) {
                    Log.d("TAG", "onResult: "+data);
                    h.notice_readCnt.setText(list.get(i).getNotice_readcnt()+"");

                }
            });
        }
    });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView notice_title,notice_writer, notice_readCnt , notice_date;
        LinearLayout lin_notice_list;


        public ViewHolder(@NonNull View v) {
            super(v);

            notice_title = v.findViewById(R.id.notice_title);
            notice_writer = v.findViewById(R.id.notice_writer);
            notice_readCnt = v.findViewById(R.id.notice_readCnt);
            notice_date = v.findViewById(R.id.notice_date);
            lin_notice_list = v.findViewById(R.id.lin_notice_list);


        }
    }


}
