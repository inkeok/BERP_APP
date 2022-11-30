package com.example.berp_and.salary;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.berp_and.CommonAskTask;
import com.example.berp_and.MainActivity;
import com.example.berp_and.R;
import com.example.berp_and.login.LoginActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MySalaryFragment extends Fragment {

    RecyclerView recv_myBonusList;
    Context context;

    TextView tv_mySalary, tv_myDepartment, tv_myHireDate, tv_myPosition, tv_myEmployeePattern, tv_myCommission;

    List<BonusVO> myBonusList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_my_salary, container, false);
        MainActivity.toolbar.setTitle("나의 급여 조회");
        recv_myBonusList = v.findViewById(R.id.recv_myBonusList);

        tv_mySalary = v.findViewById(R.id.tv_mySalary);
        tv_myDepartment = v.findViewById(R.id.tv_myDepartment);
        tv_myHireDate = v.findViewById(R.id.tv_myHireDate);
        tv_myPosition = v.findViewById(R.id.tv_myPosition);
        tv_myEmployeePattern = v.findViewById(R.id.tv_myEmployeePattern);
        tv_myCommission = v.findViewById(R.id.tv_myCommission);

        //급여는 개체 하나만 가져와서 바로 화면에 보여주고,
        CommonAskTask askTask = new CommonAskTask("andMySalaryVo.sa", getActivity());
        askTask.addParam("employee_id", LoginActivity.loginInfoList.get(0).getEmployee_id());
        askTask.executeAsk(new CommonAskTask.AsynkTaskCallback() {
            @Override
            public void onResult(String data, boolean isResult) {
                SalaryVO mySalaryVo = new Gson().fromJson(data, new TypeToken<SalaryVO>() {
                }.getType());

                tv_mySalary.setText(mySalaryVo.getSalary()+"");
                tv_myDepartment.setText(mySalaryVo.getDepartment_name());
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일", Locale.KOREA);
                tv_myHireDate.setText(mySalaryVo.getHire_date());
                tv_myPosition.setText(mySalaryVo.getC_position());
                tv_myEmployeePattern.setText(mySalaryVo.getC_employee_pattern());
                tv_myCommission.setText(mySalaryVo.getCommission_pct()+"");


            }
        });

        CommonAskTask askTask2 = new CommonAskTask("andMyBonusList.sa", getActivity());
        askTask2.addParam("employee_id", LoginActivity.loginInfoList.get(0).getEmployee_id());
        askTask2.executeAsk(new CommonAskTask.AsynkTaskCallback() {
            @Override
            public void onResult(String data, boolean isResult) {
                myBonusList = new Gson().fromJson(data, new TypeToken<List<BonusVO>>() {
                }.getType());

                MySalaryAdapter adapter = new MySalaryAdapter(getLayoutInflater(), context, myBonusList);
                RecyclerView.LayoutManager manager = new LinearLayoutManager(context, RecyclerView.VERTICAL, false);

                recv_myBonusList.setAdapter(adapter);
                recv_myBonusList.setLayoutManager(manager);

            }
        });





        return v;
    }
}