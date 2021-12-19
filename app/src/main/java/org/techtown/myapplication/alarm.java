package org.techtown.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;

public class alarm extends Fragment {

    private ArrayList<alarmData> alarmList = new ArrayList<>();
    static ArrayList<String> arrayIndex  =  new ArrayList<>();
    private alarmAdapter alarmadapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    private int left_h = 0;//남은 시간
    private int add_h = -1; //시
    private int add_m; //분
    private int add_med; //약품
    private int add_day; //요일 {1-월, 2-화, 3-수, 4-목, 5-금, 6-토, 7-일} ex) 76321 = 월, 화, 수, 토, 일
    private String add_string_AMPM;
    private String add_string_h;
    private String add_string_m;
    private String add_string_med;
    private String add_string_day;
    private String text;
    alarmDBHelper dbHelper;
    TextView t;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public int left_time_calc(int hour, int day){
        boolean end = false;
        int resh = hour-LocalTime.now(ZoneId.of("Asia/Seoul")).getHour();
        int now_day = LocalDate.now(ZoneId.of("Asia/Seoul")).getDayOfWeek().getValue();
        int add_day = day;
        int count = 0;
        if(resh<0){
            if(now_day<7)
                now_day++;
            else
                now_day=1;
            count++;
        }
        for(int i=0; i<7; i++){
            add_day = day;
            while(add_day>0){
                if(add_day%10==now_day) {
                    end = true;
                    break;
                }
                else
                    add_day/=10;
            }
            if(end)break;
            if(now_day<7)
                now_day++;
            else
                now_day = 1;
            count++;
        }
        //t.setText(String.format(text,count*24+resh));
        return count*24+resh;
    }

    //요일 출력값 설정
    public void day_calc(int day){
        String result = "";
        if(day == 7654321) add_string_day = ",매일";
        else if(day == 76) add_string_day = ",주말";
        else if(day == 54321) add_string_day = ",평일";
        else{
            while(day>0){
                if(day%10 == 1) result = result + ",월";
                else if(day%10 == 2) result = result + ",화";
                else if(day%10 == 3) result = result + ",수";
                else if(day%10 == 4) result = result + ",목";
                else if(day%10 == 5) result = result + ",금";
                else if(day%10 == 6) result = result + ",토";
                else if(day%10 == 7) result = result + ",일";
                day /= 10;
            }
            add_string_day = result;
        }
    }

    public String day_calc_string(int day){
        String result = "";
        if(day == 7654321) return ",매일";
        else if(day == 76) return ",주말";
        else if(day == 54321) return ",평일";
        else{
            while(day>0){
                if(day%10 == 1) result = result + ",월";
                else if(day%10 == 2) result = result + ",화";
                else if(day%10 == 3) result = result + ",수";
                else if(day%10 == 4) result = result + ",목";
                else if(day%10 == 5) result = result + ",금";
                else if(day%10 == 6) result = result + ",토";
                else if(day%10 == 7) result = result + ",일";
                day /= 10;
            }
            return result;
        }
    }


    //알람 추가
    public void set_add_parameter(int h, int m, int med, int day){
        add_h = h;
        add_m = m;
        add_med = med;
        add_day = day;
        add_string_AMPM = h/12==0 ? "오전" : "오후";
        add_string_h = h-(h/12)*12<10 ? "0"+(h-(h/12)*12) : Integer.toString(h-(h/12)*12);
        add_string_m = m<10 ? "0"+m : Integer.toString(h);
        add_string_med = "약품";
        day_calc(day);
        /*
        alarmData alarmdata = new alarmData(add_string_AMPM,add_string_h+":"+add_string_m,add_string_med,add_string_day);
        alarmList.add(alarmdata);
         */
        alarmadapter.notifyDataSetChanged();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.alarm, container, false);
//액션바 숨기기
        ActionBar actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        actionBar.hide();

        recyclerView = rootView.findViewById(R.id.rv);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);

//추가버튼 이벤트 설정
        ImageButton addButton = (ImageButton)rootView.findViewById(R.id.AddButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent addAlarm = new Intent(context,AddAlarm.class);
                startActivity(addAlarm);
            }
        });

        alarmadapter = new alarmAdapter(alarmList);
        recyclerView.setAdapter(alarmadapter);
        t = rootView.findViewById(R.id.TimeLeftText);
        text = getString(R.string.time_left);

        alarmadapter.setOnItemClickListener(new alarmAdapter.OnItemClickListener()
        {
            @Override
            public void onItemClick(View v, int pos)
            {
                Intent intent = new Intent(getContext(),EditAlarm.class);
                intent.putExtra("pos",Long.parseLong(arrayIndex.get(pos)));
                startActivity(intent);
            }
        });

        dbHelper = new alarmDBHelper(this.getContext());
        dbHelper.open();
        dbHelper.create();

        showDatabase();

        return rootView;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @SuppressLint("Range")
    public void showDatabase(){                                                                 //데이터베이스 내용들을 리사이클러뷰에 표시
        Cursor iCursor = dbHelper.sortColumn();
        Log.d("showDatabase", "DB Size: " + iCursor.getCount());
        alarmList.clear();
        arrayIndex.clear();
        int min=-1;
        while(iCursor.moveToNext()){
            @SuppressLint("Range") String tempIndex = iCursor.getString(iCursor.getColumnIndex("_id"));
            String ampmText;
            String timeText;
            String medText;
            String dayText;
            if(iCursor.getInt(iCursor.getColumnIndex("hour"))%24>=12)
                ampmText = "오후";
            else
                ampmText = "오전";

            timeText = iCursor.getInt(iCursor.getColumnIndex("hour"))+":"
                    +iCursor.getInt(iCursor.getColumnIndex("minute"));
            medText = iCursor.getString(iCursor.getColumnIndex("medicine"));
            dayText = day_calc_string(iCursor.getInt(iCursor.getColumnIndex("day")));
            alarmData alarmdata = new alarmData(ampmText,timeText,medText,dayText);
            alarmList.add(alarmdata);
            arrayIndex.add(tempIndex);
            int ltc = left_time_calc(iCursor.getInt(iCursor.getColumnIndex("hour")),iCursor.getInt(iCursor.getColumnIndex("day")));
            if(min<0) min=ltc;
            else if(min>ltc) min=ltc;
        }
        if(min<0) t.setText("\n저장된 알람이 없습니다.");
        else t.setText(String.format(text,min));
    }

}