package com.example.berp_and.code;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.berp_and.CommonAskTask;
import com.example.berp_and.R;
import com.example.berp_and.approval.Ing_tableVO;
import com.example.berp_and.approval.TempDetailActivity;
import com.example.berp_and.approval.TempModifyActivity;
import com.example.berp_and.work.CommonCodeVO;
import com.google.gson.Gson;

import java.io.Serializable;

public class CodeDetailActivity extends AppCompatActivity {

    EditText code_detail_personal, code_detail_document, code_detail_used, code_detail_work, code_detail_date, code_detail_name;
    Button btn_code_modify, btn_code_cancel;
    Ing_tableVO vo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code_detail);

        Intent intent = getIntent();
        CommonCodeVO vo = (CommonCodeVO) intent.getSerializableExtra("vo");

        code_detail_personal = findViewById(R.id.code_detail_personal);
        code_detail_document = findViewById(R.id.code_detail_document);
        code_detail_used = findViewById(R.id.code_detail_used);
        code_detail_work = findViewById(R.id.code_detail_work);
        code_detail_date = findViewById(R.id.code_detail_date);
        code_detail_name = findViewById(R.id.code_detail_name);

        btn_code_modify = findViewById(R.id.btn_code_modify);
        btn_code_cancel = findViewById(R.id.btn_code_cancel);

        code_detail_personal.setText(vo.getCode_title());
        code_detail_document.setText(vo.getCode_value());
        code_detail_used.setText(vo.getCode_used());
        code_detail_name.setText(vo.getCode_name());
        code_detail_date.setText(vo.getCode_birth()+"");
        code_detail_name.setText(vo.getCode_name());

        boolean isEnable = intent.getBooleanExtra("isEnable", false);

        code_detail_personal.setEnabled(isEnable);
        code_detail_document.setEnabled(isEnable);
        code_detail_used.setEnabled(isEnable);
        code_detail_work.setEnabled(isEnable);
        code_detail_date.setEnabled(isEnable);
        code_detail_name.setEnabled(isEnable);

        /*코드 상세보기 및 수정하기*/
        btn_code_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        /*코드저장*/
        btn_code_modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(CodeDetailActivity.this, CodeModifyActivity.class);

                intent.putExtra("vo", (Serializable) vo);
                startActivityForResult(intent , 1000);
            }
        });
    }

 /*   @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1000){
            origin_list();
        }
    }

    public void origin_list() {
        CommonAskTask askTask = new CommonAskTask("andCodeListOne", CodeDetailActivity.this);
        askTask.addParam("ing_no", vo.getIng_no());
        askTask.executeAsk(new CommonAskTask.AsynkTaskCallback() {
            @Override
            public void onResult(String data, boolean isResult) {
                Ing_tableVO dto = new Gson().fromJson(data, Ing_tableVO.class);

                code_detail_personal.setText(vo.getCode_title());
                code_detail_document.setText(vo.getCode_value());
                code_detail_used.setText(vo.getCode_used());
                code_detail_name.setText(vo.getCode_name());
                code_detail_date.setText(vo.getCode_birth()+"");
                code_detail_name.setText(vo.getCode_name());



            }
        });
    }

        btn_code_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                vo.setCode_title(code_detail_personal.getText()+"");
                vo.setCode_value(code_detail_document.getText()+"");
                vo.setCode_used(code_detail_used.getText()+"");
                vo.setCode_name(code_detail_work.getText()+"");
                *//*vo.setCode_birth(code_detail_date.getText());*//*
                vo.setCode_maker(code_detail_name.getText()+"");

                CommonAskTask task = new CommonAskTask("andUpdate.code" , CodeDetailActivity.this);
                task.addParam("code_title", code_detail_personal.getText()+"");
                task.addParam("code_value", code_detail_document.getText()+"");
                task.addParam("code_used", code_detail_used.getText()+"");
                task.addParam("code_name", code_detail_work.getText()+"");
                *//*task.addParam("code_birth", code_detail_date.getText()+"");*//*
                task.addParam("code_maker", code_detail_name.getText()+"");
                task.executeAsk(new CommonAskTask.AsynkTaskCallback() {
                    @Override
                    public void onResult(String data, boolean isResult) {
                        if(isResult && data.equals("1")) {
                            finish();
                        }else{
                            Toast.makeText(CodeDetailActivity.this, "실패", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });*/

    }
