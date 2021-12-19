package org.techtown.myapplication;

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
    /**
     * 알람 어댑터
     * -알람 기능을 보조합니다.
     * @author 유세빈, 김은석, 이하나, 김동권
     */
    private ArrayList<alarmData> alarmList;

    /**
     * 알람 리스트 할당
     * @param alarmList
     */
    public alarmAdapter(ArrayList<alarmData> alarmList) {
        this.alarmList = alarmList;
    }

    /**
     * 알람 뷰 홀더
     * @param parent
     * @param viewType
     * @return holder
     */
    @NonNull
    @Override
    public alarmViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_alarm,parent,false);
        alarmViewHolder holder = new alarmViewHolder(view);

        return holder;
    }

    /**
     * 입력된 값 반환
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull alarmViewHolder holder, int position) {
        holder.timeText.setText(alarmList.get(position).gettimeText());
        holder.medText.setText(alarmList.get(position).getmedText());
        holder.dayText.setText(alarmList.get(position).getdayText());
        holder.ampmText.setText(alarmList.get(position).getampmText());

        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            /**
             * 알람 항목 클릭 시
             * @param view
             */
            @Override
            public void onClick(View view) {                                                                    //클릭 시 수정 화면으로 넘어감
                Context context = view.getContext();
                Intent editAlarm = new Intent(context,EditAlarm.class);
                context.startActivity(editAlarm);
            }
        });
    }

    /**
     * 알람 항목 갯수
     * @return (null != alarmList ? alarmList.size() : 0)
     */
    @Override
    public int getItemCount() {
        return (null != alarmList ? alarmList.size() : 0);
    }

    /**
     * 알람 항목 삭제
     * @param position
     */
    public void remove_alarm(int position) {
        try{
            alarmList.remove(position);
            notifyItemRemoved(position);
        } catch(IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }

    /**
     * 알람 항목 표시하기
     */
    public class alarmViewHolder extends RecyclerView.ViewHolder {

        protected TextView ampmText;
        protected TextView timeText;
        protected TextView medText;
        protected TextView dayText;

        /**
         * 알람 값 할당
         * @param itemView
         */
        public alarmViewHolder(@NonNull View itemView) {
            super(itemView);
            this.timeText = (TextView) itemView.findViewById(R.id.TimeText);
            this.medText = (TextView) itemView.findViewById(R.id.MedText);
            this.dayText = (TextView) itemView.findViewById(R.id.DayText);
            this.ampmText = (TextView) itemView.findViewById(R.id.AMPMText);
        }
    }
}