package com.example.berp_and.approval;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.berp_and.R;


public class RecBoxFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View v =  inflater.inflate(R.layout.fragment_rec_box, container, false);
        return v;
    }
}