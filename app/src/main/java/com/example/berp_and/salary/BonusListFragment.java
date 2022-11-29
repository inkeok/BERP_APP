package com.example.berp_and.salary;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.berp_and.CommonAskTask;
import com.example.berp_and.MainActivity;
import com.example.berp_and.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class BonusListFragment extends Fragment {

    RecyclerView recv_bonusList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_bonus_list, container, false);

        recv_bonusList = v.findViewById(R.id.recv_bonusList);
        MainActivity.toolbar.setTitle("상여금 지급내역 ");
        bonusList();

        return v;
    }//onCreateView

    public void bonusList(){
        CommonAskTask askTask = new CommonAskTask("andBonusList.sa", getActivity());
        askTask.executeAsk(new CommonAskTask.AsynkTaskCallback() {
            @Override
            public void onResult(String data, boolean isResult) {
                Log.d("로그", "onResult: "+data);
                ArrayList<BonusVO> bonusList = new Gson().fromJson(data, new TypeToken<ArrayList<BonusVO>>() {
                }.getType());

                BonusAdapter adapter = new BonusAdapter(getLayoutInflater(), getContext(), bonusList);
                RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);

                recv_bonusList.setAdapter(adapter);
                recv_bonusList.setLayoutManager(manager);

            }
        });
    }//salaryList
}//class