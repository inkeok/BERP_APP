package com.example.berp_and.approval;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.berp_and.CommonAskTask;
import com.example.berp_and.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class RecDetailActivity extends AppCompatActivity {
    Button rec_btn_confirm, rec_btn_cancel;
    TextView contents, rec_title, rec_writer, rec_date;
    AutoCompleteTextView rec_item_filled_exposed;
    ArrayList<And_Ing_tableVO> list = new ArrayList<>();
    ArrayList<String> str_list = new ArrayList<>();
    String sign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rec_detail);

        rec_btn_confirm = findViewById(R.id.rec_btn_confirm);
        rec_btn_cancel = findViewById(R.id.rec_btn_cancel);
        contents = findViewById(R.id.contents);
        rec_title = findViewById(R.id.rec_title);
        rec_writer = findViewById(R.id.rec_writer);
        rec_date = findViewById(R.id.rec_date);
        rec_item_filled_exposed = findViewById(R.id.rec_item_filled_exposed);

        CommonAskTask askTask = new CommonAskTask("andRecsign.ap", this);
        askTask.executeAsk(new CommonAskTask.AsynkTaskCallback() {

            @Override
            public void onResult(String data, boolean isResult) {
                list = new Gson().fromJson(data, new TypeToken<ArrayList<And_Ing_tableVO>>() {
                }.getType());
                for (int i = 0; i < list.size(); i++) {
                    str_list.add(list.get(i).getCheck_name());
                }
                rec_item_filled_exposed.setAdapter(new ArrayAdapter<String>(
                        RecDetailActivity.this.getApplicationContext(),R.layout.emp_drop_down_item, str_list));
            }
        });
        rec_item_filled_exposed.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                sign = list.get(i).getDocument_check();
            }
        });

        rec_btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                And_Ing_tableVO vo = new And_Ing_tableVO();
                vo.setIng_no();
                CommonAskTask askTask1 = new CommonAskTask("andRecConfirm.ap", RecDetailActivity.this);
                askTask1.addParam();

            }
        });
    }
}