package com.example.berp_and;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private View drawerView;
    ImageButton menu_button;
    HashMap<String, ArrayList<String>> menu_list = new HashMap<>();

    ArrayList<String> parent_menu = new ArrayList<>();
    int lastPosition = -1;
    ExpandableListView exp_menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        drawerView = (View) findViewById(R.id.drawerView);
        drawerLayout.setDrawerListener(listener);

        menu_button = findViewById(R.id.menu_button);



        exp_menu = findViewById(R.id.exp_menu);

        menu_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout. openDrawer(drawerView);

            }
        });


        menu_hash();

        exp_menu.setAdapter(new MainMenuAdapter(getLayoutInflater(),menu_list, parent_menu));




    }
    DrawerLayout.DrawerListener listener = new DrawerLayout.DrawerListener() {
        @Override
        public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {
        }

        @Override
        public void onDrawerOpened(@NonNull View drawerView) {
            drawerLayout.openDrawer(drawerView);
        }

        @Override
        public void onDrawerClosed(@NonNull View drawerView) {
        }

        @Override
        public void onDrawerStateChanged(int newState) {
        }
    };




    public void menu_hash(){
        parent_menu.add("회사정보");
        parent_menu.add("채용관리");
        parent_menu.add("인사관리");
        parent_menu.add("코드관리");
        parent_menu.add("근태관리");
        parent_menu.add("급여관리");
        parent_menu.add("업무관리");
        ArrayList<String> child_menu1= new ArrayList<>();

        child_menu1.add(0, "출퇴근 관리");
        child_menu1.add(1, "개인정보 수정");

        menu_list.put("회사정보", child_menu1);


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





    }

}

