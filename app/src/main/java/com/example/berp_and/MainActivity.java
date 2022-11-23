package com.example.berp_and;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.example.berp_and.adminApply.ApplyCheckFragment;
import com.example.berp_and.adminApply.ApplyPassFragment;
import com.example.berp_and.apply.ApplyListFragment;
import com.example.berp_and.apply.MyApplyListFragment;
import com.example.berp_and.emp.EmpFragment;
import com.example.berp_and.emp.EmpInsertFragment;
import com.example.berp_and.home.HomeFragment;
import com.example.berp_and.home.HomeLoginFragment;
import com.example.berp_and.login.LoginActivity;
import com.example.berp_and.salary.SalaryListFragment;
import com.example.berp_and.work.HolidayFragment;
import com.example.berp_and.work.HolidayInsertFragment;
import com.example.berp_and.work.WorkFragment;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;

    HashMap<String, ArrayList<MenuDTO>> menu_list = new HashMap<>();
    HashMap<String, ArrayList<MenuDTO>> menu_list_none = new HashMap<>();
    ArrayList<String> parent_menu = new ArrayList<>();
    ArrayList<String> parent_menu_none = new ArrayList<>();
    ExpandableListView exp_menu;
    DrawerLayout drawer;
    int containerInt;
    public static int container_state = 0;


    TextView tv_logintop, tv_loginbot;
    Button btn_logout;
    public static int LoginInfo = 0;
    NavigationView nav_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        toolbar = findViewById(R.id.toolbar);
        exp_menu = findViewById(R.id.exp_menu);
        nav_view = findViewById(R.id.nav_view);
        tv_logintop=findViewById(R.id.tv_logintop);
        tv_loginbot=findViewById(R.id.tv_loginbot);
        btn_logout = findViewById(R.id.btn_logout);
        containerInt = R.id.container;

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("YM NetWork");

        drawer = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,drawer,toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close


        );
        drawer.addDrawerListener(toggle);
        toggle.syncState();



        menu_hash();
     //   exp_menu.setAdapter(new MainMenuAdapter(getLayoutInflater(), menu_list, parent_menu, containerInt, getSupportFragmentManager()));
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
        });//setOnGroupClickListener

        onRestart();
    }//onCreate

    @Override
    protected void onResume() {
        super.onResume();
        menu();
    }

    public void menu(){

        if(LoginInfo == 0) {
            tv_logintop.setClickable(true);
            tv_loginbot.setClickable(true);
            btn_logout.setVisibility(View.INVISIBLE);
            exp_menu.setAdapter(new MainMenuAdapter(getLayoutInflater(), menu_list_none, parent_menu_none, containerInt, getSupportFragmentManager(), drawer));
            tv_logintop.setText("로그인");
            tv_loginbot.setText("회원가입");
            getSupportFragmentManager().beginTransaction().replace(R.id.container, new HomeFragment()).commit();

            tv_logintop.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                    if(drawer.isDrawerOpen(Gravity.LEFT)){
                        drawer.closeDrawers();
                    }
                }
            });



        }else if (LoginInfo == 1){
            btn_logout.setVisibility(View.VISIBLE);
            tv_logintop.setClickable(false);
            tv_loginbot.setClickable(false);


                tv_logintop.setText(LoginActivity.loginInfoList.get(0).getName()+"님 반갑습니다.");
                tv_loginbot.setText(LoginActivity.loginInfoList.get(0).getEmployee_id()+" / "+LoginActivity.loginInfoList.get(0).getPosition_name());
                exp_menu.setAdapter(new MainMenuAdapter(getLayoutInflater(), menu_list, parent_menu, containerInt, getSupportFragmentManager(), drawer));

                getSupportFragmentManager().beginTransaction().replace(R.id.container, new HomeLoginFragment()).commit();

            btn_logout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LoginActivity.loginInfoList.clear();
                    LoginInfo = 0 ;
                    onResume();
                    if(drawer.isDrawerOpen(Gravity.LEFT)){
                        drawer.closeDrawers();

                    }
                }
            });


        }//if
    }//menu


    public void menu_hash(){
        parent_menu.add("마이페이지");
        parent_menu.add("채용관리");
        parent_menu.add("인사관리");
        parent_menu.add("코드관리");
        parent_menu.add("근태관리");
        parent_menu.add("급여관리");
        parent_menu.add("업무관리");
        ArrayList<MenuDTO> child_menu1= new ArrayList<>();

        child_menu1.add( new MenuDTO("출퇴근 관리", new HomeLoginFragment()));
        child_menu1.add(  new MenuDTO("개인정보 수정" ));

        menu_list.put("마이페이지", child_menu1);


        ArrayList<MenuDTO> child_menu2= new ArrayList<>();
        child_menu2.add(new MenuDTO("지원자 목록", new ApplyCheckFragment()));
        child_menu2.add(new MenuDTO("합격자 목록", new ApplyPassFragment()));

        menu_list.put("채용관리", child_menu2);

        ArrayList<MenuDTO> child_menu3= new ArrayList<>();

        child_menu3.add(new MenuDTO("직원추가", new EmpInsertFragment()));
        child_menu3.add(new MenuDTO("직원관리", new EmpFragment()));

        menu_list.put("인사관리", child_menu3);

        ArrayList<MenuDTO> child_menu4= new ArrayList<>();

        child_menu4.add(new MenuDTO("인사 코드조회"));
        child_menu4.add(new MenuDTO("문서 코드조회"));
        child_menu4.add(new MenuDTO("고용형태 코드조회"));
        child_menu4.add(new MenuDTO("근무구분 코드조회"));

        menu_list.put("코드관리", child_menu4);

        ArrayList<MenuDTO> child_menu5= new ArrayList<>();

        child_menu5.add(new MenuDTO("휴일 관리", new HolidayFragment()));
        child_menu5.add(new MenuDTO("근무시간관리", new WorkFragment()));

        menu_list.put("근태관리", child_menu5);


        ArrayList<MenuDTO> child_menu6= new ArrayList<>();
        child_menu6.add(new MenuDTO("급여 기본 정보", new SalaryListFragment()));


       /* if(LoginActivity.loginInfoList.get(0).getAdmin().equals("Y")) {
            child_menu6.add(new MenuDTO("급상여 관리"));
        }*/

        child_menu6.add(new MenuDTO("월별 지급 현황"));

        menu_list.put("급여관리", child_menu6);


        ArrayList<MenuDTO> child_menu7= new ArrayList<>();
        child_menu7.add(new MenuDTO("전자결재"));
        child_menu7.add(new MenuDTO("임시 보관함"));
        child_menu7.add(new MenuDTO("상신함"));
        child_menu7.add(new MenuDTO("결재처리함"));
        child_menu7.add(new MenuDTO("업무공유"));
        child_menu7.add(new MenuDTO("공지사항"));

        menu_list.put("업무관리", child_menu7);



        parent_menu_none.add("공지사항");
        parent_menu_none.add("회사정보");
        parent_menu_none.add("채용정보");


        ArrayList<MenuDTO> child_menu1_1= new ArrayList<>();

        menu_list_none.put("공지사항", child_menu1_1);


        ArrayList<MenuDTO> child_menu2_1= new ArrayList<>();

        menu_list_none.put("회사정보", child_menu2_1);

        ArrayList<MenuDTO> child_menu3_1= new ArrayList<>();
        child_menu3_1.add(new MenuDTO("채용공고보기", new ApplyListFragment()));
        child_menu3_1.add(new MenuDTO("지원여부확인", new MyApplyListFragment()));

        menu_list_none.put("채용정보", child_menu3_1);

    }

    public class MenuDTO{
            String menu_name ;
            Fragment fragment;

        public MenuDTO(String menu_name, Fragment fragment) {
            this.menu_name = menu_name;
            this.fragment = fragment;
        }

        public MenuDTO(String menu_name) {
            this.menu_name = menu_name;
        }

        public String getMenu_name() {
            return menu_name;
        }

        public void setMenu_name(String menu_name) {
            this.menu_name = menu_name;
        }

        public Fragment getFragment() {
            return fragment;
        }

        public void setFragment(Fragment fragment) {
            this.fragment = fragment;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK :
                if (container_state == 1) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, new HomeLoginFragment()).commit();
                    container_state = 0;
                    return true;
                }else if(container_state == 5){
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, new HomeFragment()).commit();
                    container_state = 4;
                    return true;
                }
                break;

        }
        return super.onKeyDown(keyCode, event);
    }
}

