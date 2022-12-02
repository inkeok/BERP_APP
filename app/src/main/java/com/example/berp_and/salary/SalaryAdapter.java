package com.example.berp_and.salary;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.berp_and.ApiClient;
import com.example.berp_and.ApiInterface;
import com.example.berp_and.CommonAskTask;
import com.example.berp_and.CommonVal;
import com.example.berp_and.MainActivity;
import com.example.berp_and.R;
import com.example.berp_and.emp.EmpVO;
import com.example.berp_and.login.LoginActivity;
import com.example.berp_and.login.LoginMemberVO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SalaryAdapter extends RecyclerView.Adapter<SalaryAdapter.SalaryHolder> {
    LayoutInflater layoutInflater;
    Context context;
    ArrayList<SalaryVO> salaryList;
    SalaryListFragment fragment ;

    public SalaryAdapter(LayoutInflater layoutInflater, Context context, ArrayList<SalaryVO> salaryList , SalaryListFragment fragment) {
        this.layoutInflater = layoutInflater;
        this.context = context;
        this.salaryList = salaryList;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public SalaryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = layoutInflater.inflate(R.layout.item_salarylist_recv, parent, false);
        return new SalaryHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull SalaryHolder h, @SuppressLint("RecyclerView") int i) {
        h.tv_name.setText(salaryList.get(i).getName());
        h.tv_commission.setText(salaryList.get(i).getCommission_pct()+"");
        h.tv_c_employee_parttern.setText(salaryList.get(i).getC_employee_pattern());
        h.tv_salary.setText(salaryList.get(i).getSalary()+"");
        h.tv_c_position.setText(salaryList.get(i).getC_position());

        h.tv_hire_date.setText(salaryList.get(i).getHire_date());

        h.tv_department_name.setText(salaryList.get(i).getDepartment_name());
        h.employee_id = salaryList.get(i).getEmployee_id();

        if (salaryList.get(i).getDepartment_id() == 10){
            h.view_color.setBackgroundColor(Color.parseColor("#000000"));
        }else if(salaryList.get(i).getDepartment_id() == 20){
            h.view_color.setBackgroundColor(Color.parseColor("#EA3737"));
        }else if(salaryList.get(i).getDepartment_id() == 30){
            h.view_color.setBackgroundColor(Color.parseColor("#679333"));
        }else if(salaryList.get(i).getDepartment_id() == 40){
            h.view_color.setBackgroundColor(Color.parseColor("#C6B203"));
        }else if(salaryList.get(i).getDepartment_id() == 50){
            h.view_color.setBackgroundColor(Color.parseColor("#008EFF"));
        }else if(salaryList.get(i).getDepartment_id() == 60){
            h.view_color.setBackgroundColor(Color.parseColor("#ED00FF"));
        }



        h.img_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, BonusActivity.class);
                //Intent intent = new Intent(context, TestActivity.class);
                intent.putExtra("vo", salaryList.get(i)); // ==> vo

                context.startActivity(intent);

            }//onClick
        }); //h.img_insert.setOnClickListener(new View.OnClickListener()



        h.tv_salary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(h.tv_salary.getContext());
                dialog.setContentView(R.layout.activity_salary);

                h.tv_name_salary = dialog.findViewById(R.id.tv_name_salary);
                h.tv_name_salary.setText("("+salaryList.get(i).getDepartment_name()+" "+salaryList.get(i).getC_position()+" "+salaryList.get(i).getName()+")");

                h.tv_updateSalary = dialog.findViewById(R.id.tv_updateSalary);
                h.salaryBtn_cancel = dialog.findViewById(R.id.salaryBtn_cancel);
                h.salaryBtn_save = dialog.findViewById(R.id.salaryBtn_save);

                dialog.show();

                dialog.findViewById(R.id.salaryBtn_save).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CommonAskTask askTask = new CommonAskTask("andSalarySave.sa", context);
                        askTask.addParam("salary", h.tv_updateSalary.getText());
                        askTask.addParam("employee_id", h.employee_id);
                        askTask.executeAsk(new CommonAskTask.AsynkTaskCallback() {
                            @Override
                            public void onResult(String data, boolean isResult) {
                                if(isResult){
                                    fragment.salaryList();
                                }else{
                                    Log.d("로그", "onResult: 통신 실패");
                                }
                                dialog.dismiss();

                            }
                        });
                    }
                }); //dialog.findViewById(R.id.salaryBtn_save).setOnClickListener(new View.OnClickListener()


                dialog.findViewById(R.id.salaryBtn_cancel).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
            }
        }); //h.tv_salary.setOnClickListener(new View.OnClickListener()

        h.tv_commission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(h.tv_commission.getContext());
                dialog.setContentView(R.layout.activity_commission);

//                h.tv_name_commission = dialog.findViewById(R.id.tv_name_commission);
               // h.tv_name_commission.setText("("+salaryList.get(i).getDepartment_name()+" "+salaryList.get(i).getC_position()+" "+salaryList.get(i).getName()+")");

                h.tv_updateCommission = dialog.findViewById(R.id.tv_updateCommission);
                h.commissionBtn_cancel = dialog.findViewById(R.id.commissionBtn_cancel);
                h.commissionBtn_save = dialog.findViewById(R.id.commissionBtn_save);


                dialog.show();

                dialog.findViewById(R.id.commissionBtn_save).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        CommonAskTask askTask = new CommonAskTask("andCommissionSave.sa", context);
                        askTask.addParam("commission_pct", h.tv_updateCommission.getText());
                        //askTask.addParam("employee_id", LoginActivity.loginInfoList.get(0).getEmployee_id());
                        askTask.addParam("employee_id", h.employee_id);
                        askTask.executeAsk(new CommonAskTask.AsynkTaskCallback() {
                            @Override
                            public void onResult(String data, boolean isResult) {
                                if(isResult){
                                    fragment.salaryList();
                                }else{
                                    Log.d("로그", "onResult: 통신 실패");
                                }
                                dialog.dismiss();
                            }
                        });
                    }
                });

                dialog.findViewById(R.id.commissionBtn_cancel).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
            }
        });
    }//onBindViewHolder






    @Override
    public int getItemCount() {
        return salaryList.size();
    }




    class SalaryHolder extends RecyclerView.ViewHolder {
        TextView tv_name,tv_c_employee_parttern, tv_c_position,
        tv_department_name, tv_commission, tv_hire_date, tv_salary, tv_name_bonus, tv_name_salary,
        tv_name_commission;

        int employee_id;

        EditText tv_updateCommission, tv_updateSalary;

        Button commissionBtn_cancel, commissionBtn_save, salaryBtn_cancel, salaryBtn_save;

        ImageView img_insert;

        View view_color;

        public SalaryHolder(@NonNull View v) {
            super(v);
            //tv_employee_id = v.findViewById(R.id.tv_employee_id);
            tv_name = v.findViewById(R.id.tv_name);
            tv_c_employee_parttern = v.findViewById(R.id.tv_c_employee_parttern);
            tv_c_position = v.findViewById(R.id.tv_c_position);
            tv_department_name = v.findViewById(R.id.tv_department_name);
            tv_commission = v.findViewById(R.id.tv_commission);
            tv_hire_date = v.findViewById(R.id.tv_hire_date);
            tv_salary = v.findViewById(R.id.tv_salary);
            img_insert = v.findViewById(R.id.img_insert);
            tv_name_bonus = v.findViewById(R.id.tv_name_bonus);
            tv_c_position = v.findViewById(R.id.tv_c_position);
            view_color = v.findViewById(R.id.view_color);





        }//public SalaryHolder(@NonNull View v)
    }//class SalaryHolder extends RecyclerView.ViewHolder
}//public class SalaryAdapter extends RecyclerView.Adapter<SalaryAdapter.SalaryHolder>
