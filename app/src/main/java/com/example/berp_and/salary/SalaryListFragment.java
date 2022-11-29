package com.example.berp_and.salary;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;

import com.example.berp_and.CommonAskTask;
import com.example.berp_and.MainActivity;
import com.example.berp_and.R;
import com.example.berp_and.emp.EmpListAdapter;
import com.example.berp_and.emp.EmpVO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class SalaryListFragment extends Fragment {

    RecyclerView recv_salaryList;
    LayoutInflater inflater;

    public void setInflater(LayoutInflater inflater) {
        this.inflater = inflater;
    }

    //    AutoCompleteTextView salary_item_filled_exposed;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_salary_list, container, false);
        setInflater(inflater);
        recv_salaryList = v.findViewById(R.id.recv_salaryList);
        MainActivity.toolbar.setTitle("급여관리");
        return v;
    }//onCreateView

    @Override
    public void onResume() {
        super.onResume();
        salaryList();
    }

    public void salaryList(){
        CommonAskTask askTask = new CommonAskTask("andSalaryList.sa", getActivity());
        askTask.executeAsk(new CommonAskTask.AsynkTaskCallback() {
            @Override
            public void onResult(String data, boolean isResult) {
                ArrayList<SalaryVO> salaryList = new Gson().fromJson(data, new TypeToken<ArrayList<SalaryVO>>() {
                }.getType());

                SalaryAdapter adapter = new SalaryAdapter(inflater, getContext(), salaryList , SalaryListFragment.this);
                RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);

                recv_salaryList.setLayoutManager(manager);
                recv_salaryList.setAdapter(adapter);

            }
        });
    }//salaryList



}//SalaryListFragment