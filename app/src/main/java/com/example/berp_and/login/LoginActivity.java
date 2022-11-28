package com.example.berp_and.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.berp_and.CommonAskTask;
import com.example.berp_and.MainActivity;
import com.example.berp_and.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {
    EditText edt_id, edt_pw;
    Button btn_login;

    public static ArrayList<LoginMemberVO> loginInfoList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        edt_id = findViewById(R.id.edt_id);
        edt_pw = findViewById(R.id.edt_pw);
        btn_login = findViewById(R.id.btn_login);


        btn_login.setOnClickListener(v -> {
            CommonAskTask askTask = new CommonAskTask("andLogin.mem", this);
            askTask.addParam("id", edt_id.getText());
            askTask.addParam("pw", edt_pw.getText());
            askTask.executeAsk(new CommonAskTask.AsynkTaskCallback() {
                @Override
                public void onResult(String data, boolean isResult) {

                    if(data == "zzz") {
                        Toast.makeText(LoginActivity.this, "아이디/비밀번호가 잘못되었습니다", Toast.LENGTH_SHORT).show();
                    }else {
                        loginInfoList = new Gson().fromJson(data, new TypeToken<ArrayList<LoginMemberVO>>() {
                        }.getType());
                        Toast.makeText(LoginActivity.this, "로그인함", Toast.LENGTH_SHORT).show();
                        MainActivity.LoginInfo = 1;
                        finish();
                    }
                }
            });
        });
    }
}