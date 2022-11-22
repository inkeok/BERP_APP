package com.example.berp_and.home;


import android.content.Intent;
import android.annotation.SuppressLint;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.berp_and.MainActivity;
import com.example.berp_and.R;
import com.example.berp_and.mypage.MyPageActivity;
import com.example.berp_and.CommonAskTask;
import com.example.berp_and.R;
import com.example.berp_and.login.LoginActivity;

import java.util.Date;


public class HomeLoginFragment extends Fragment {

    TextView tv_main_login_name;
    ImageView img_main_login_setting;
    TextView start_work_text, end_work_text;
        Button start_work_btn, end_work_btn;
    public static int i = 0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home_login, container, false);


        tv_main_login_name = v.findViewById(R.id.tv_main_login_name);
        img_main_login_setting = v.findViewById(R.id.img_main_login_setting);
    

        tv_main_login_name.setText(LoginActivity.loginInfoList.get(0).getPosition_name() + " "+ LoginActivity.loginInfoList.get(0).getName()+"님 / " + LoginActivity.loginInfoList.get(0).getDepartment_name());

        start_work_btn = v.findViewById(R.id.start_work_btn);
        end_work_btn = v.findViewById(R.id.end_work_btn);
        start_work_text = v.findViewById(R.id.start_work_text);
        end_work_text = v.findViewById(R.id.end_work_text);






        start_work_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            if( Search() != false){
                start_work_btn.isEnabled();
            }else{
            work_start_input();
            }

                
            }
        });
        end_work_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            work_end_input();

            }
        });
        img_main_login_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //MyPage-> Activity 연결 새창띄우는거 < 저장하고 창끄기
                Intent intent = new Intent(getActivity(), MyPageActivity.class);
                startActivity(intent);

            }
        });






        return v;
    }

    public boolean Search(){
        CommonAskTask askTask = new CommonAskTask("andSearch",getActivity());

        askTask.executeAsk(new CommonAskTask.AsynkTaskCallback() {
            @Override
            public void onResult(String data, boolean isResult) {

            }
        });
        return false;
    }


    public void work_start_input(){
        CommonAskTask askTask = new CommonAskTask("andWork_start_input", getActivity());

        String date = new Date().getHours() +":" +new Date().getMinutes() +":"+ new Date().getSeconds();

        askTask.addParam("start_work", date);
              askTask.executeAsk(new CommonAskTask.AsynkTaskCallback() {

            @Override
            public void onResult(String data, boolean isResult) {
                if(i == 0){
                start_work_text.setText(date);
                i++;
                }else {
                    Toast.makeText(getContext(),"출근은 한 번만 돼요",1000*2).show();
                }

            }
        });
    } public void work_end_input(){
        CommonAskTask askTask = new CommonAskTask("andWork_end_input", getActivity());

        String date = new Date().getHours() +":" +new Date().getMinutes() +":"+ new Date().getSeconds();

        askTask.addParam("end_work", date);
              askTask.executeAsk(new CommonAskTask.AsynkTaskCallback() {
            @Override
            public void onResult(String data, boolean isResult) {
                end_work_text.setText(date);


            }
        });
    }
}