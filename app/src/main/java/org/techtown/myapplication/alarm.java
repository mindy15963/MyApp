package org.techtown.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
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

    private ArrayList<alarmData> alarmList;
    private alarmAdapter alarmadapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    private int left_h = 0;//남은 시간
    private int cur_d; //현재 요일
    private int cur_h; //현재 시
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
    TextView t;
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void set_cur_date(){                                                                        // 현재 시간 설정
        LocalDate date = LocalDate.now(ZoneId.of("Asia/Seoul"));
        LocalTime time = LocalTime.now(ZoneId.of("Asia/Seoul"));
        DayOfWeek dow = date.getDayOfWeek();
        cur_d = dow.getValue();
        cur_h = time.getHour();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void left_time_calc(int hour, int day){
        boolean end = false;
        int resh = hour-cur_h;
        int now_day = cur_d;
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
        t.setText(String.format(text,count*24+resh));
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

        ActionBar actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        actionBar.hide();

        recyclerView = rootView.findViewById(R.id.rv);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);

        alarmList = new ArrayList<>();
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
        set_cur_date();
        t = rootView.findViewById(R.id.TimeLeftText);
        text = getString(R.string.time_left);
        return rootView;
    }

}