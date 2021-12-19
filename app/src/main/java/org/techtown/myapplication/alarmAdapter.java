package org.techtown.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class alarmAdapter extends RecyclerView.Adapter<alarmAdapter.alarmViewHolder> {

    private ArrayList<alarmData> alarmList;

    public alarmAdapter(ArrayList<alarmData> alarmList) {
        this.alarmList = alarmList;
    }

    @NonNull
    @Override
    public alarmViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_alarm,parent,false);
        alarmViewHolder holder = new alarmViewHolder(view);
        return holder;
    }

    public interface OnItemClickListener {
        void onItemClick(View v, int pos);
    }

    private OnItemClickListener mListener = null;

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    @Override
    public void onBindViewHolder(@NonNull alarmViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.timeText.setText(alarmList.get(position).gettimeText());
        holder.medText.setText(alarmList.get(position).getmedText());
        holder.dayText.setText(alarmList.get(position).getdayText());
        holder.ampmText.setText(alarmList.get(position).getampmText());

        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = position;
                if (pos != RecyclerView.NO_POSITION)
                {
                    mListener.onItemClick(view, pos);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return (null != alarmList ? alarmList.size() : 0);
    }

    public void remove_alarm(int position) { //알람 항목 삭제
        try{
            alarmList.remove(position);
            notifyItemRemoved(position);
        } catch(IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }

    public class alarmViewHolder extends RecyclerView.ViewHolder {

        protected TextView ampmText;
        protected TextView timeText;
        protected TextView medText;
        protected TextView dayText;

        public alarmViewHolder(@NonNull View itemView) {
            super(itemView);
            this.timeText = (TextView) itemView.findViewById(R.id.TimeText);
            this.medText = (TextView) itemView.findViewById(R.id.MedText);
            this.dayText = (TextView) itemView.findViewById(R.id.DayText);
            this.ampmText = (TextView) itemView.findViewById(R.id.AMPMText);
        }
    }
}
