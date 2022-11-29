package com.example.berp_and.code;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.berp_and.R;
import com.example.berp_and.work.CommonCodeVO;

import java.util.ArrayList;

public class CodeAdapter extends RecyclerView.Adapter<CodeAdapter.CodeHolder> {

    LayoutInflater inflater;
    ArrayList<CommonCodeVO> list;

    public CodeAdapter(LayoutInflater inflater, ArrayList<CommonCodeVO> list) {
        this.inflater = inflater;
        this.list = list;
    }

    @NonNull
    @Override
    public CodeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_codelist, parent, false);
        return new CodeHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CodeHolder h, int i) {
        h.code_personal.setText(list.get(i).getCode_title());
        h.code_document.setText(list.get(i).getCode_value());
        h.code_approve.setText(list.get(i).getCode_used());
        h.code_work.setText(list.get(i).getCode_name());
        h.code_date.setText(list.get(i).getCode_birth()+"");
        h.code_name.setText(list.get(i).getCode_maker_name());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class CodeHolder extends RecyclerView.ViewHolder {
        TextView code_personal, code_document, code_approve, code_work, code_date, code_name;

        public CodeHolder(@NonNull View v) {
            super(v);
            code_personal = v.findViewById(R.id.code_personal);
            code_document = v.findViewById(R.id.code_document);
            code_approve = v.findViewById(R.id.code_approve);
            code_work = v.findViewById(R.id.code_work);
            code_date = v.findViewById(R.id.code_date);
            code_name = v.findViewById(R.id.code_name);
        }
    }
}
