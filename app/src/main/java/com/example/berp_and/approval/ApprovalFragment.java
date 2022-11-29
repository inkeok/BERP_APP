package com.example.berp_and.approval;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.berp_and.CommonAskTask;
import com.example.berp_and.R;
import com.example.berp_and.notice.NoticeListAdapter;
import com.example.berp_and.notice.NoticeVO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;


public class ApprovalFragment extends Fragment {

    RecyclerView recv_approval_box;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v =inflater.inflate(R.layout.fragment_approval, container, false);
        origin_list();


        recv_approval_box  = v.findViewById(R.id.recv_approval_box);

        return v;
    }





    public void origin_list(){
        CommonAskTask askTask = new CommonAskTask("andApproval_list", getContext());
        askTask.executeAsk(new CommonAskTask.AsynkTaskCallback() {
            @Override
            public void onResult(String data, boolean isResult) {
                Log.d("TAG", "onResult: "+data);
                ArrayList<Result_tableVO> list = new Gson().fromJson(data, new TypeToken<ArrayList<Result_tableVO>>() {
                }.getType());

                Log.d("TAG", "onResult: 키키킥");
                recv_approval_box.setAdapter(new ApprovalAdapter(getLayoutInflater(),list,getContext()));
                recv_approval_box.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL,false));


            }
        });


    }






}