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
import com.example.berp_and.R;
import com.example.berp_and.emp.EmpListAdapter;
import com.example.berp_and.emp.EmpVO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class SalaryListFragment extends Fragment {

    RecyclerView recv_salaryList;

//    AutoCompleteTextView salary_item_filled_exposed;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_salary_list, container, false);

        recv_salaryList = v.findViewById(R.id.recv_salaryList);
//        salary_item_filled_exposed = v.findViewById(R.id.salary_item_filled_exposed);

//        dept_list();
            salaryList();



        return v;
    }//onCreateView

//    public void dept_list(){
//        CommonAskTask askTask = new CommonAskTask("andDeptList.sa", getActivity());
//        askTask.executeAsk(new CommonAskTask.AsynkTaskCallback() {
//            @Override
//            public void onResult(String data, boolean isResult) {
//                ArrayList<DeptVO> deptList = new Gson().fromJson(data, new TypeToken<ArrayList<DeptVO>>() {
//                }.getType());
//
//                SalaryAdapter adapter = new SalaryAdapter(getLayoutInflater(), getContext(), deptList);
//                RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
//
//                recv_salaryList.setAdapter(adapter);
//                recv_salaryList.setLayoutManager(manager);
//
//                if(isResult){
//                    Log.d("안드", "onResult: "+"성공");
//                    Log.d("안드", "onResult: "+deptList);
//                }else{
//                    Log.d("안드", "onResult: "+"실패");
//                }
//
////                recv_salaryList.setAdapter(new EmpListAdapter(getLayoutInflater(),list, getContext()));
////                recv_salaryList.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));
//            }
//        });
//    }//dept_list

    public void salaryList(){
        CommonAskTask askTask = new CommonAskTask("andSalaryList.sa", getActivity());
        askTask.executeAsk(new CommonAskTask.AsynkTaskCallback() {
            @Override
            public void onResult(String data, boolean isResult) {
                ArrayList<SalaryVO> salaryList = new Gson().fromJson(data, new TypeToken<ArrayList<SalaryVO>>() {
                }.getType());

                SalaryAdapter adapter = new SalaryAdapter(getLayoutInflater(), getContext(), salaryList);
                RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);

                recv_salaryList.setAdapter(adapter);
                recv_salaryList.setLayoutManager(manager);

                if(isResult){
                    Log.d("안드", "onResult: "+"성공");
                    Log.d("안드", "onResult: "+salaryList);
                }else{
                    Log.d("안드", "onResult: "+"실패");
                }

//                recv_salaryList.setAdapter(new EmpListAdapter(getLayoutInflater(),list, getContext()));
//                recv_salaryList.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));
            }
        });
    }//salaryList

}