package com.example.berp_and.emp;

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
    ArrayList<EmpVO> company_list = new ArrayList<>();
    ArrayList<EmpVO> position_list = new ArrayList<>();
    ArrayList<EmpVO> pattern_list = new ArrayList<>();
    ArrayList<String> department_list_real = new ArrayList<>();
    ArrayList<String> company_list_real = new ArrayList<>();
    ArrayList<String> position_list_real = new ArrayList<>();
    ArrayList<String> pattern_list_real = new ArrayList<>();
    AutoCompleteTextView emp_item_filled_exposed, emp_item_filled_exposed2;

    int first_num = 0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_emp, container, false);
        MainActivity.LoginInfo = 1;

        recv_empList = v.findViewById(R.id.recv_empList);

        origin_list();




        value_add();

        String[] first_list = {"부서", "회사", "포지션", "패턴"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                getActivity().getApplicationContext(), R.layout.emp_drop_down_item,
                first_list
        );

        emp_item_filled_exposed = v.findViewById(R.id.emp_item_filled_exposed);
        emp_item_filled_exposed2 = v.findViewById(R.id.emp_item_filled_exposed2);

        emp_item_filled_exposed.setAdapter(adapter);


        emp_item_filled_exposed.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                first_item_selected(i);
                first_num = i;
            }
        });

        emp_item_filled_exposed2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                second_item_selected(i);
            }
        });








        return v;

    }

    @Override
    public void onResume() {
        super.onResume();
        origin_list();
    }

    public void origin_list(){
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
    }

    public void first_item_selected(int i){
        if(i == 0) {
            department_spinner();
        }else if(i == 1){
            company_spinner();
        }else if(i == 2){
            position_spinner();
        }else if(i == 3){
            pattern_spinner();
        }

    }
    public void second_item_selected(int i){
        if(first_num == 0) {
            CommonAskTask askTask_selectList = new CommonAskTask("andEmpDepartmentSelect.hr", getContext());
            askTask_selectList.addParam("department_id", department_list.get(i).getDepartment_id());
            askTask_selectList.executeAsk(new CommonAskTask.AsynkTaskCallback() {
                @Override
                public void onResult(String data, boolean isResult) {
                    ArrayList<EmpVO> list = new Gson().fromJson(data, new TypeToken<ArrayList<EmpVO>>() {
                    }.getType());

                    recv_empList.setAdapter(new EmpListAdapter(getLayoutInflater(),list, getContext()));
                    recv_empList.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));
                }
            });
        }else if(first_num == 1){
            CommonAskTask askTask_selectList = new CommonAskTask("andEmpCompanySelect.hr", getContext());
            askTask_selectList.addParam("company", company_list.get(i).getCompany_cd());
            askTask_selectList.executeAsk(new CommonAskTask.AsynkTaskCallback() {
                @Override
                public void onResult(String data, boolean isResult) {
                    ArrayList<EmpVO> list = new Gson().fromJson(data, new TypeToken<ArrayList<EmpVO>>() {
                    }.getType());

                    recv_empList.setAdapter(new EmpListAdapter(getLayoutInflater(),list, getContext()));
                    recv_empList.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));
                }
            });
        }else if(first_num == 2){
            CommonAskTask askTask_selectList = new CommonAskTask("andEmpPositionSelect.hr", getContext());
            askTask_selectList.addParam("position", position_list.get(i).getPosition());
            askTask_selectList.executeAsk(new CommonAskTask.AsynkTaskCallback() {
                @Override
                public void onResult(String data, boolean isResult) {
                    ArrayList<EmpVO> list = new Gson().fromJson(data, new TypeToken<ArrayList<EmpVO>>() {
                    }.getType());

                    recv_empList.setAdapter(new EmpListAdapter(getLayoutInflater(),list, getContext()));
                    recv_empList.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));
                }
            });
        }else if(first_num == 3){
            CommonAskTask askTask_selectList = new CommonAskTask("andEmpPatternSelect.hr", getContext());
            askTask_selectList.addParam("pattern", pattern_list.get(i).getEmployee_pattern());
            askTask_selectList.executeAsk(new CommonAskTask.AsynkTaskCallback() {
                @Override
                public void onResult(String data, boolean isResult) {
                    ArrayList<EmpVO> list = new Gson().fromJson(data, new TypeToken<ArrayList<EmpVO>>() {
                    }.getType());

                    recv_empList.setAdapter(new EmpListAdapter(getLayoutInflater(),list, getContext()));
                    recv_empList.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));
                }
            });
        }
    }

    public void value_add(){
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
        CommonAskTask askTask_company = new CommonAskTask("andEmpListCompany.hr", getContext());
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
        CommonAskTask askTask_position = new CommonAskTask("andEmpListPosition.hr", getContext());
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
        CommonAskTask askTask_pattern = new CommonAskTask("andEmpListPattern.hr", getContext());
        askTask_pattern.executeAsk(new CommonAskTask.AsynkTaskCallback() {
            @Override
            public void onResult(String data, boolean isResult) {
                pattern_list = new Gson().fromJson(data, new TypeToken<ArrayList<EmpVO>>() {
                }.getType());
                for (int i = 0; i < pattern_list.size(); i++) {
                    pattern_list_real.add(pattern_list.get(i).getEmployee_pattern_name());
                }
            }
        });

    }


    public void department_spinner(){
        emp_item_filled_exposed2.setText("세부선택");

        emp_item_filled_exposed2.setAdapter(new ArrayAdapter<>(
                getActivity().getApplicationContext(), R.layout.emp_drop_down_item,
                department_list_real
        ));

    }

    public void company_spinner(){
        emp_item_filled_exposed2.setText("세부선택");

        emp_item_filled_exposed2.setAdapter(new ArrayAdapter<>(
                getActivity().getApplicationContext(), R.layout.emp_drop_down_item,
                company_list_real
        ));
    }
    public void position_spinner(){
        emp_item_filled_exposed2.setText("세부선택");

        emp_item_filled_exposed2.setAdapter(new ArrayAdapter<>(
                getActivity().getApplicationContext(), R.layout.emp_drop_down_item,
                position_list_real
        ));
    }
    public void pattern_spinner(){
        emp_item_filled_exposed2.setText("세부선택");

        emp_item_filled_exposed2.setAdapter(new ArrayAdapter<>(
                getActivity().getApplicationContext(), R.layout.emp_drop_down_item,
                pattern_list_real
        ));
    }
}