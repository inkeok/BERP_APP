package com.example.berp_and.home;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import android.widget.ImageView;
import android.widget.TextView;


import com.example.berp_and.MainActivity;
import com.example.berp_and.R;
import com.example.berp_and.login.LoginActivity;
import com.example.berp_and.mypage.MyPageActivity;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;


public class HomeLoginFragment extends Fragment {

    TextView tv_main_login_name;
    ImageView img_main_login_setting;


        Button start_work_btn, end_work_btn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home_login, container, false);


        tv_main_login_name = v.findViewById(R.id.tv_main_login_name);
        img_main_login_setting = v.findViewById(R.id.img_main_login_setting);
    

        tv_main_login_name.setText(LoginActivity.loginInfoList.get(0).getPosition_name() + " "+ LoginActivity.loginInfoList.get(0).getName()+"님 / " + LoginActivity.loginInfoList.get(0).getDepartment_name());

        start_work_btn = v.findViewById(R.id.start_work_btn);
        end_work_btn = v.findViewById(R.id.end_work_btn);


        start_work_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TimeZone tz;                                        // 객체 생성
                DateFormat dateFormat = new SimpleDateFormat("HH:mm:SS", Locale.KOREAN);
                tz = TimeZone.getTimeZone("Asia/Seoul");        // TimeZone에 표준시 설정
                dateFormat.setTimeZone(tz);                    //DateFormat에 TimeZone 설정

                Date date = new Date();                        // 현재 날짜가 담긴 Date 객체 생성
                Log.d("DATE", dateFormat.format(date).toString());
                
                
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
}