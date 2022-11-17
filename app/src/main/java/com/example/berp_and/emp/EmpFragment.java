package com.example.berp_and.emp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.example.berp_and.CommonAskTask;
import com.example.berp_and.MainActivity;
import com.example.berp_and.R;
import com.example.berp_and.login.LoginMemberVO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;


public class EmpFragment extends Fragment {

    RecyclerView recv_empList;
    ArrayList<EmpVO> department_list = new ArrayList<>();
    ArrayList<String> department_list_real = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_emp, container, false);

        recv_empList = v.findViewById(R.id.recv_empList);

        CommonAskTask askTask = new CommonAskTask("andEmpList.hr", getActivity());
        askTask.executeAsk(new CommonAskTask.AsynkTaskCallback() {
            @Override
            public void onResult(String data, boolean isResult) {
                ArrayList<EmpVO> list = new Gson().fromJson(data, new TypeToken<ArrayList<EmpVO>>() {
                }.getType());

                recv_empList.setAdapter(new EmpListAdapter(getLayoutInflater(),list, getContext()));
                recv_empList.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));
            }
        });



        CommonAskTask askTask_department = new CommonAskTask("andEmpListDepartment.hr", getContext());
        askTask_department.executeAsk(new CommonAskTask.AsynkTaskCallback() {
            @Override
            public void onResult(String data, boolean isResult) {
                department_list = new Gson().fromJson(data, new TypeToken<ArrayList<EmpVO>>() {
                }.getType());
                for (int i = 0; i < department_list.size(); i++) {
                    department_list_real.add(department_list.get(i).getDepartment_name());
                }

            }
        });


        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                getActivity().getApplicationContext(), R.layout.emp_drop_down_item,
                department_list_real
        );

        AutoCompleteTextView autoCompleteTextView = v.findViewById(R.id.emp_item_filled_exposed);
        autoCompleteTextView.setAdapter(adapter);

        autoCompleteTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return v;



    }
}