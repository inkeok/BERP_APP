package com.example.berp_and.apply;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.berp_and.R;

public class ApplyListAdapter extends RecyclerView.Adapter<ApplyListAdapter.ApplyListHolder> {
    LayoutInflater inflater;

    public ApplyListAdapter(LayoutInflater inflater) {
        this.inflater = inflater;
    }

    @NonNull
    @Override
    public ApplyListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ApplyListHolder(inflater.inflate(R.layout.item_recruit_board, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ApplyListHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 7;
    }

    public class ApplyListHolder extends RecyclerView.ViewHolder{

        public ApplyListHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
