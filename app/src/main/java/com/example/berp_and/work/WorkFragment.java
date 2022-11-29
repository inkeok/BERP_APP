package com.example.berp_and.work;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.example.berp_and.CommonAskTask;
import com.example.berp_and.MainActivity;
import com.example.berp_and.R;
import com.example.berp_and.emp.EmpListAdapter;
import com.example.berp_and.emp.EmpVO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;


public class WorkFragment extends Fragment {
    RecyclerView recv_workList;
    ArrayList<EmpVO> department_list = new ArrayList<>();
    ArrayList<String> department_list_real = new ArrayList<>();
    AutoCompleteTextView work_item_filled_exposed;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_work, container, false);
        MainActivity.toolbar.setTitle("근태관리");

        recv_workList = v.findViewById(R.id.recv_workList);
        origin_list();

        value_add();
        work_item_filled_exposed = v.findViewById(R.id.work_item_filled_exposed);

        work_item_filled_exposed.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                work_item_filled_exposed.setAdapter(new ArrayAdapter<>(getActivity().getApplicationContext(),
                        R.layout.emp_drop_down_item, department_list_real));


            }
        });

        work_item_filled_exposed.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                department_list_real.get(i);


                CommonAskTask askTask = new CommonAskTask("andWorkDeptList", getContext());
                askTask.addParam("department_id", department_list.get(i).getDepartment_id());
                askTask.executeAsk(new CommonAskTask.AsynkTaskCallback() {
                    @Override
                    public void onResult(String data, boolean isResult) {
                        Log.d("TAG", "onResult: " + data);
                        ArrayList<WorkResultVO> list = new Gson().fromJson(data, new TypeToken<ArrayList<WorkResultVO>>() {
                        }.getType());

                        recv_workList.setAdapter(new WorkAdapter(getLayoutInflater(), list, getContext()));
                        recv_workList.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
                    }
                });

            }
        });



        return v;
    }


    public void origin_list() {
        CommonAskTask askTask = new CommonAskTask("andWorkList", getActivity());
        askTask.executeAsk(new CommonAskTask.AsynkTaskCallback() {
            @Override
            public void onResult(String data, boolean isResult) {
                ArrayList<WorkResultVO> list = new Gson().fromJson(data, new TypeToken<ArrayList<WorkResultVO>>() {
                }.getType());

                recv_workList.setAdapter(new WorkAdapter(getLayoutInflater(), list, getContext()));
                recv_workList.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
            }
        });
    }
    public void value_add() {
        CommonAskTask askTask_department = new CommonAskTask("andWorkDept", getContext());
        askTask_department.executeAsk(new CommonAskTask.AsynkTaskCallback() {
            @Override
            public void onResult(String data, boolean isResult) {
                department_list_real.clear();
                department_list = new Gson().fromJson(data, new TypeToken<ArrayList<EmpVO>>() {
                }.getType());
                for (int i = 0; i < department_list.size(); i++) {
                    department_list_real.add(department_list.get(i).getDepartment_name());
                }
                work_item_filled_exposed.setAdapter(new ArrayAdapter<>(
                        getActivity().getApplicationContext(), R.layout.emp_drop_down_item,
                        department_list_real));
            }
        });
    }



}