package com.example.berp_and.mypage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.berp_and.CommonAskTask;
import com.example.berp_and.MainActivity;
import com.example.berp_and.R;
import com.example.berp_and.login.LoginActivity;
import com.example.berp_and.login.LoginMemberVO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class MyPageActivity extends AppCompatActivity {

    TextView my_page_name;
    EditText my_page_pw, my_page_pw_ck, my_page_phone, my_page_email;
    Button my_page_submit, my_page_cancel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_page);

        my_page_name = findViewById(R.id.my_page_name);
        my_page_pw = findViewById(R.id.my_page_pw);
        my_page_pw_ck = findViewById(R.id.my_page_pw_ck);
        my_page_phone = findViewById(R.id.my_page_phone);
        my_page_email = findViewById(R.id.my_page_email);
        my_page_submit = findViewById(R.id.my_page_submit);
        my_page_cancel = findViewById(R.id.my_page_cancel);

        LoginMemberVO vo = new LoginMemberVO();
        my_page_name.setText(LoginActivity.loginInfoList.get(0).getName());
        my_page_pw.setText(LoginActivity.loginInfoList.get(0).getPw());
        my_page_phone.setText(LoginActivity.loginInfoList.get(0).getPhone());
        my_page_email.setText(LoginActivity.loginInfoList.get(0).getEmail());






    }
    public void my_page() {
        CommonAskTask askTask = new CommonAskTask("andModify.mypage", this);

        askTask.executeAsk(new CommonAskTask.AsynkTaskCallback() {
            @Override
            public void onResult(String data, boolean isResult) {

                if(data == "zzz") {
                    Log.d("mypage", "onResult: 성공");;
                }else {
                    Log.d("mypage", "onResult: 실패");
                }

            }

        });

    }
}