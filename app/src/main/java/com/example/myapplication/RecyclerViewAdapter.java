package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;


/**
 * Created by User on 1/1/2018.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    private static final String TAG = "RecyclerViewAdapter";

    private ArrayList<Integer> mgoal_id = new ArrayList<>();
    private ArrayList<String> mgoal_name = new ArrayList<>();
    private ArrayList<Integer> mgoal_amount = new ArrayList<>();
    private ArrayList<Integer> mgoal_status = new ArrayList<>();
    private ArrayList<Integer> msaved_amount = new ArrayList<>();

    private Context mContext;

    public RecyclerViewAdapter(Context context, ArrayList<Integer> goal_id,ArrayList<String> goal_name,ArrayList<Integer> goal_amount,ArrayList<Integer> goal_status,ArrayList<Integer> saved_amount ) {
        mgoal_id=goal_id;
        mgoal_name=goal_name;
        mgoal_amount=goal_amount;
        mgoal_status=goal_status;
        msaved_amount=saved_amount;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.all_gaols, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
//        Log.d(TAG, "onBindViewHolder: called.");
//        holder.goalName.setText(String.valueOf(mgoal_name.get(position)));
//        holder.goalAmount.setText(String.valueOf(mgoal_amount.get(position)));
//        holder.savedAmount.setText(String.valueOf(msaved_amount.get(position)));
//
//        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                Log.d(TAG, "onClick: clicked on: " + mImageNames.get(position));
//
////                Toast.makeText(mContext, mImageNames.get(position), Toast.LENGTH_SHORT).show();
//
//                Intent intent = new Intent(mContext, addMoneyActivity.class);
//                intent.putExtra("id", mgoal_id.get(position));
////                intent.putExtra("image_name", mImageNames.get(position));
//                mContext.startActivity(intent);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return 7;
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView goalName,goalAmount,savedAmount,status;
        RelativeLayout parentLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            goalName = itemView.findViewById(R.id.goalName);
//            goalAmount= itemView.findViewById(R.id.goalAmount);
//            savedAmount= itemView.findViewById(R.id.savedAmount);
//            status= itemView.findViewById(R.id.status);
//            parentLayout = itemView.findViewById(R.id.goal_layout);
        }
    }
}
