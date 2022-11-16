package com.example.berp_and;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.berp_and.login.LoginActivity;
import com.google.android.material.navigation.NavigationView;

import com.example.berp_and.login.JoinActivity;
import com.example.berp_and.login.LoginDTO;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    HashMap<String, ArrayList<String>> menu_list = new HashMap<>();
    HashMap<String, ArrayList<String>> menu_list_none = new HashMap<>();
    ArrayList<String> parent_menu = new ArrayList<>();
    ArrayList<String> parent_menu_none = new ArrayList<>();
    ExpandableListView exp_menu;

    TextView tv_logintop, tv_loginbot;
    Button btn_logout;
    public static int LoginInfo = 0;
    NavigationView nav_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        exp_menu = findViewById(R.id.exp_menu);
        nav_view = findViewById(R.id.nav_view);
        tv_logintop=findViewById(R.id.tv_logintop);
        tv_loginbot=findViewById(R.id.tv_loginbot);
        btn_logout = findViewById(R.id.btn_logout);

        menu_hash();

        menu();



        exp_menu.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                for (int i = 0; i < parent.getChildCount(); i++) {

                    if (groupPosition == i) {
                        parent.expandGroup(i);
                    } else {
                        parent.collapseGroup(i);
                    }

                }

                return true;
            }
        });





        onRestart();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        menu();
    }

    public void menu(){

        if(LoginInfo == 0) {
            btn_logout.setVisibility(View.INVISIBLE);
            exp_menu.setAdapter(new MainMenuAdapter(getLayoutInflater(), menu_list_none, parent_menu_none));
            tv_logintop.setText("로그인");
            tv_loginbot.setText("회원가입");

            tv_logintop.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            });
            tv_loginbot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, JoinActivity.class);
                    startActivity(intent);
                }
            });


        }else if (LoginInfo == 1){
            btn_logout.setVisibility(View.VISIBLE);

            if (LoginActivity.loginInfoList.get(0).getJoin_check().equals("Y")){
                tv_logintop.setText(LoginActivity.loginInfoList.get(0).getName()+"님 반갑습니다.");
                tv_loginbot.setText(LoginActivity.loginInfoList.get(0).getEmail()+"");

                exp_menu.setAdapter(new MainMenuAdapter(getLayoutInflater(), menu_list, parent_menu));
            }else {
                tv_logintop.setText(LoginActivity.loginInfoList.get(0).getName()+"님 반갑습니다.");
                tv_loginbot.setText(LoginActivity.loginInfoList.get(0).getEmail()+"");
                exp_menu.setAdapter(new MainMenuAdapter(getLayoutInflater(), menu_list_none, parent_menu_none));
            }


        }
    }

    public void menu_hash(){
        parent_menu.add("마이페이지");
        parent_menu.add("채용관리");
        parent_menu.add("인사관리");
        parent_menu.add("코드관리");
        parent_menu.add("근태관리");
        parent_menu.add("급여관리");
        parent_menu.add("업무관리");
        ArrayList<String> child_menu1= new ArrayList<>();

        child_menu1.add(0, "출퇴근 관리");
        child_menu1.add(1, "개인정보 수정");

        menu_list.put("마이페이지", child_menu1);


        ArrayList<String> child_menu2= new ArrayList<>();
        child_menu2.add(0, "지원자 목록");
        child_menu2.add(1, "합격자 목록");

        menu_list.put("채용관리", child_menu2);

        ArrayList<String> child_menu3= new ArrayList<>();

        child_menu3.add(0, "부서관리");
        child_menu3.add(1, "직원관리");

        menu_list.put("인사관리", child_menu3);

        ArrayList<String> child_menu4= new ArrayList<>();

        child_menu4.add(0, "인사 코드조회");
        child_menu4.add(1, "문서 코드조회");
        child_menu4.add(2, "고용형태 코드조회");
        child_menu4.add(3, "근무구분 코드조회");

        menu_list.put("코드관리", child_menu4);

        ArrayList<String> child_menu5= new ArrayList<>();

        child_menu5.add(0, "휴일 관리");
        child_menu5.add(1, "근무시간관리");

        menu_list.put("근태관리", child_menu5);


        ArrayList<String> child_menu6= new ArrayList<>();
        child_menu6.add(0, "급여기본정보");
        child_menu6.add(1, "급상여 입력");
        child_menu6.add(2, "월별급여상여지급현황");

        menu_list.put("급여관리", child_menu6);


        ArrayList<String> child_menu7= new ArrayList<>();
        child_menu7.add(0, "전자결재");
        child_menu7.add(1, "임시 보관함");
        child_menu7.add(2, "상신함");
        child_menu7.add(3, "결재처리함");
        child_menu7.add(4, "업무공유");
        child_menu7.add(5, "공지사항");

        menu_list.put("업무관리", child_menu7);



        parent_menu_none.add("공지사항");
        parent_menu_none.add("회사정보");
        parent_menu_none.add("채용정보");


        ArrayList<String> child_menu1_1= new ArrayList<>();

        menu_list_none.put("공지사항", child_menu1_1);


        ArrayList<String> child_menu2_1= new ArrayList<>();

        menu_list_none.put("회사정보", child_menu2_1);

        ArrayList<String> child_menu3_1= new ArrayList<>();
        child_menu3_1.add(0, "채용공고보기");
        child_menu3_1.add(1, "지원여부확인");

        menu_list_none.put("채용정보", child_menu3_1);

    }

}

