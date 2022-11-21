package com.example.berp_and.apply;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.berp_and.CommonAskTask;
import com.example.berp_and.MainActivity;
import com.example.berp_and.R;
import com.example.berp_and.emp.EmpVO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class ApplyListFragment extends Fragment {

    RecyclerView recv_apply_board;
    ArrayList<RecruitVO> rec_list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_apply_list, container, false);
        MainActivity.container_state = 5;

        recv_apply_board = v.findViewById(R.id.recv_apply_board);

        rec_list_select();






        return v;
    }

    public void rec_list_select(){
        CommonAskTask askTask = new CommonAskTask("andRecList.rec", getActivity());
        askTask.executeAsk(new CommonAskTask.AsynkTaskCallback() {
            @Override
            public void onResult(String data, boolean isResult) {
                rec_list = new Gson().fromJson(data, new TypeToken<ArrayList<RecruitVO>>() {
                }.getType());
                recv_apply_board.setAdapter(new ApplyListAdapter(getLayoutInflater(), rec_list));
                recv_apply_board.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

            }
        });
    }
}