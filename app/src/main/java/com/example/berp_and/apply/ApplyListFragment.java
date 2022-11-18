package com.example.berp_and.apply;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.berp_and.R;

public class ApplyListFragment extends Fragment {

    RecyclerView recv_apply_board;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_apply_list, container, false);

        recv_apply_board = v.findViewById(R.id.recv_apply_board);



        recv_apply_board.setAdapter(new ApplyListAdapter(inflater));
        recv_apply_board.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));


        return v;
    }
}