package com.example.berp_and.salary;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.berp_and.CommonAskTask;
import com.example.berp_and.R;
import com.example.berp_and.login.LoginActivity;
import com.example.berp_and.work.WorkAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


public class MySalaryAdapter extends RecyclerView.Adapter<MySalaryAdapter.MySalaryHolder> {

    LayoutInflater inflater;
    Context context;
    List<BonusVO> myBonusList;

    public MySalaryAdapter(LayoutInflater inflater, Context context, List<BonusVO> myBonusList) {
        this.inflater = inflater;
        this.context = context;
        this.myBonusList = myBonusList;
    }

    @NonNull
    @Override
    public MySalaryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_mybonuslist, parent, false);
        return new MySalaryHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MySalaryHolder h, @SuppressLint("RecyclerView") int i) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일");
        h.tv_myDate.setText(sdf.format(myBonusList.get(i).getBonus_date()));
        h.tv_myBonus.setText(myBonusList.get(i).getBonus()+"");
        h.tv_myComment.setText(myBonusList.get(i).getBonus_comment());



    }

    @Override
    public int getItemCount() {
        return myBonusList.size();
    }

    class MySalaryHolder extends RecyclerView.ViewHolder {

        TextView tv_myDate, tv_myBonus, tv_myComment;
        int employee_id;

        public MySalaryHolder(@NonNull View v) {
            super(v);
            tv_myDate = v.findViewById(R.id.tv_myDate);
            tv_myBonus = v.findViewById(R.id.tv_myBonus);
            tv_myComment = v.findViewById(R.id.tv_myComment);
        }
    }
}
