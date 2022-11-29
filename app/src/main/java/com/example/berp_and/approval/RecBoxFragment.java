package com.example.berp_and.approval;

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


public class RecBoxFragment extends Fragment {

    RecyclerView recv_recbox;
    ArrayList<And_Ing_tableVO> list = new ArrayList<>();

                @Override
                public View onCreateView(LayoutInflater inflater, ViewGroup container,
                        Bundle savedInstanceState) {
                    View v =  inflater.inflate(R.layout.fragment_rec_box, container, false);

                    recv_recbox = v.findViewById(R.id.recv_recbox);

                    CommonAskTask askTask = new CommonAskTask("andRec.ap", getContext());
                    askTask.executeAsk(new CommonAskTask.AsynkTaskCallback() {
                        @Override
                        public void onResult(String data, boolean isResult) {
                            list = new Gson().fromJson(data, new TypeToken<ArrayList<And_Ing_tableVO>>() {
                            }.getType());

                recv_recbox.setAdapter(new RecAdapter(getLayoutInflater(),list));
                recv_recbox.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL, false));
            }
        });


        return v;
    }
}