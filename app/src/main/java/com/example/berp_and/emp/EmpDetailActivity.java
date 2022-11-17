package com.example.berp_and.emp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.berp_and.CommonAskTask;
import com.example.berp_and.R;
import com.example.berp_and.login.LoginMemberVO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class EmpDetailActivity extends AppCompatActivity {
    TextView tv_employee_id_detail, tv_employee_name_detail;
    Spinner spinner_emp_department, spinner_emp_company, spinner_emp_position;
    RadioGroup rdg_emp_admin, rdg_emp_pattern;
    Button btn_emp_close, btn_emp_delete, btn_modify;


    ArrayList<EmpVO> department_list = new ArrayList<>();
    ArrayList<EmpVO> company_list = new ArrayList<>();
    ArrayList<EmpVO> position_list = new ArrayList<>();
    ArrayList<String> department_list_real = new ArrayList<>();
    ArrayList<String> company_list_real = new ArrayList<>();
    ArrayList<String> position_list_real = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emp_detail);

        tv_employee_id_detail = findViewById(R.id.tv_employee_id_detail);
        tv_employee_name_detail = findViewById(R.id.tv_employee_name_detail);
        spinner_emp_department = findViewById(R.id.spinner_emp_department);
        spinner_emp_company = findViewById(R.id.spinner_emp_company);
        spinner_emp_position = findViewById(R.id.spinner_emp_position);
        rdg_emp_admin = findViewById(R.id.rdg_emp_admin);
        rdg_emp_pattern = findViewById(R.id.rdg_emp_pattern);
        btn_modify = findViewById(R.id.btn_modify);
        btn_emp_delete = findViewById(R.id.btn_emp_delete);
        btn_emp_close = findViewById(R.id.btn_emp_close);


        Intent intent = getIntent();
        tv_employee_id_detail.setText(intent.getStringExtra("employee_id"));
        tv_employee_name_detail.setText(intent.getStringExtra("name"));

        CommonAskTask askTask_department = new CommonAskTask("andEmpListDepartment.hr", this);
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
        CommonAskTask askTask_company = new CommonAskTask("andEmpListCompany.hr", this);
        askTask_company.executeAsk(new CommonAskTask.AsynkTaskCallback() {
            @Override
            public void onResult(String data, boolean isResult) {
                company_list = new Gson().fromJson(data, new TypeToken<ArrayList<EmpVO>>() {
                }.getType());
                for (int i = 0; i < company_list.size(); i++) {
                    company_list_real.add(company_list.get(i).getCompany_name());
                }


            }
        });
        CommonAskTask askTask_position = new CommonAskTask("andEmpListPosition.hr", this);
        askTask_position.executeAsk(new CommonAskTask.AsynkTaskCallback() {
            @Override
            public void onResult(String data, boolean isResult) {
                position_list = new Gson().fromJson(data, new TypeToken<ArrayList<EmpVO>>() {
                }.getType());
                for (int i = 0; i < position_list.size(); i++) {
                    position_list_real.add(position_list.get(i).getPosition_name());
                }
            }
        });


        spinner_emp_department.setAdapter(new ArrayAdapter<>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, department_list_real ));
        spinner_emp_company.setAdapter(new ArrayAdapter<>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, company_list_real ));
        spinner_emp_position.setAdapter(new ArrayAdapter<>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, position_list_real ));

        spinner_emp_department.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btn_emp_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



        if(intent.getStringExtra("admin").equals("Y")){
            rdg_emp_admin.check(R.id.emp_admin_Y);
        }else {
            rdg_emp_admin.check(R.id.emp_admin_N);
        }
        if(intent.getStringExtra("pattern").equals("H101")){
            rdg_emp_pattern.check(R.id.emp_pattern_H101);
        }else{
            rdg_emp_pattern.check(R.id.emp_pattern_H102);
        }



    }
}