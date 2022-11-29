package com.example.berp_and.salary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.example.berp_and.CommonAskTask;
import com.example.berp_and.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;

public class TestActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    Spinner spinner;
    ArrayList<DeptVO> deptList;
    HashMap<Integer, String> map;
    String farmSeq;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        map = new HashMap<>();

        spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);

        CommonAskTask askTask = new CommonAskTask("andDepartments.sa", TestActivity.this);
        //askTask.addParam("commission_pct", tv_updateCommission.getText());
        //askTask.addParam("employee_id", LoginActivity.loginInfoList.get(0).getEmployee_id());
        //askTask.addParam("employee_id", employee_id);
        askTask.executeAsk(new CommonAskTask.AsynkTaskCallback() {
            @Override
            public void onResult(String data, boolean isResult) {
                if(isResult){
                    deptList = new Gson().fromJson(data, new TypeToken<ArrayList<DeptVO>>() {
                    }.getType());

                    for(int i = 1; i < deptList.size(); i++){
                        map.put(i, deptList.get(i).getDepartment_name());
                    }

                }else{
                    Log.d("로그", "onResult: 실패");
                }
            }
        });//askTask
    }//onCreate

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        farmSeq = map.get(spinner.getSelectedItem());
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}