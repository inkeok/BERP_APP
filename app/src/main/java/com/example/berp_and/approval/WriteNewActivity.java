package com.example.berp_and.approval;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import com.example.berp_and.R;

public class WriteNewActivity extends AppCompatActivity {

    EditText edt_write_title, edt_write_content;
    AutoCompleteTextView spinner_write1, spinner_write2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_new);


        edt_write_title = findViewById(R.id.edt_write_title);
        edt_write_content = findViewById(R.id.edt_write_content);
        spinner_write1 = findViewById(R.id.spinner_write1);
        spinner_write2 = findViewById(R.id.spinner_write2);








    }
}