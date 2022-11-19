package com.example.berp_and.home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.berp_and.R;
import com.example.berp_and.login.LoginActivity;

import org.w3c.dom.Text;


public class HomeLoginFragment extends Fragment {
    TextView tv_main_login_name;
    ImageView img_main_login_setting;
    Button btn_start_work, btn_finish_work;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home_login, container, false);

        tv_main_login_name = v.findViewById(R.id.tv_main_login_name);
        img_main_login_setting = v.findViewById(R.id.img_main_login_setting);
        btn_start_work = v.findViewById(R.id.btn_start_work);
        btn_finish_work = v.findViewById(R.id.btn_finish_work);

        tv_main_login_name.setText(LoginActivity.loginInfoList.get(0).getPosition_name() + " "+ LoginActivity.loginInfoList.get(0).getName()+"님 / " + LoginActivity.loginInfoList.get(0).getDepartment_name());

        return v;
    }
}