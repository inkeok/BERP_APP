package com.example.berp_and.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.berp_and.ApiClient;
import com.example.berp_and.ApiInterface;
import com.example.berp_and.CommonAskTask;
import com.example.berp_and.R;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;

public class JoinActivity extends AppCompatActivity {

    Button btn_join, btn_id_check;
    EditText join_name, join_id, join_password, join_email, join_phone, join_addr;
    RadioGroup checkbox;
    String gender;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        join_name = findViewById(R.id.name);
        join_id = findViewById(R.id.id);
        join_password = findViewById(R.id.password);
        join_email = findViewById(R.id.email);
        join_phone = findViewById(R.id.phone);
        join_addr = findViewById(R.id.addr);
        checkbox = findViewById(R.id.checkbox);
        btn_join = findViewById(R.id.btn_join);
        btn_id_check = findViewById(R.id.btn_id_check);

        if(checkbox.getCheckedRadioButtonId() == R.id.rdb_man){
            gender = "남";
        }else {
            gender = "여";
        }

        btn_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CommonAskTask askTask = new CommonAskTask("andJoin.mem", JoinActivity.this);
                JoinDTO dto = new JoinDTO(join_email.getText()+""
                                        , join_id.getText()+""
                                        , join_password.getText()+""
                                        , join_name.getText()+""
                                        , gender+""
                                        , join_phone.getText()+""
                                        , join_addr.getText()+"");
                askTask.addParam("dto" , new Gson().toJson(dto));

                askTask.executeAsk(new CommonAskTask.AsynkTaskCallback() {
                    @Override
                    public void onResult(String data, boolean inResult) {

                        if (inResult){
                            Toast.makeText(JoinActivity.this, "잘됨", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(JoinActivity.this, "안됨", Toast.LENGTH_SHORT).show();
                        }
                            
                    }
                });


            }
        });


    }
}