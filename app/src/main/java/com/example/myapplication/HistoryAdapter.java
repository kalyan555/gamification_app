package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.MyViewHolder> {
    List<String> albumList;
    private Context mContext;
    private int position;

    public HistoryAdapter(List<String> albumList, Context mContext) {
        this.albumList = albumList;
        this.mContext = mContext;
    }

    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.all_gaols, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int j) {
        this.position = holder.getAdapterPosition();


    }

    @Override
    public int getItemCount() {
        return  7;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {


        public MyViewHolder(final View view) {
            super(view);

        }
    }
}
