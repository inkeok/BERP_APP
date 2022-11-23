package com.example.berp_and.work;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.berp_and.CommonAskTask;
import com.example.berp_and.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;


public class WorkIndiFragment extends Fragment {

    RecyclerView recv_holIndiList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
     View v = inflater.inflate(R.layout.fragment_work_indi, container, false);

        recv_holIndiList = v.findViewById(R.id.recv_holIndiList);


        origin_list();




     return v;
    }

    public void origin_list() {
        CommonAskTask askTask = new CommonAskTask("andHolidayIndi_List", getActivity());
        askTask.executeAsk(new CommonAskTask.AsynkTaskCallback() {
            @Override
            public void onResult(String data, boolean isResult) {
                ArrayList<WorkResultVO> list = new Gson().fromJson(data, new TypeToken<ArrayList<WorkResultVO>>() {
                }.getType());

                recv_holIndiList.setAdapter(new WorkAdapter(getLayoutInflater(), list, getContext()));
                recv_holIndiList.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
            }
        });
    }

}