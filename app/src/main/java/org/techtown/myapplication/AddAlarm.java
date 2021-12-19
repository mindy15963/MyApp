package org.techtown.myapplication;

import static java.util.Calendar.YEAR;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/*
    import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TimePicker;

import java.util.Calendar;*/


public class AddAlarm extends AppCompatActivity {

    private TimePicker timePicker;
    private AlarmManager alarmManager;
    private int hour, minute;
    private TextView startdate, enddate;
    private Button startdatebtn, enddatebtn, savebtn, cancelbtn;
    private EditText medicineName;
    CheckBox cbSun, cbMon, cbTue, cbWed, cbThu, cbFri, cbSat;
    alarmDBHelper dbHelper;
    int start_year, start_month, start_date, end_year, end_month, end_date;

    @SuppressLint({"SetTextI18n", "CutPasteId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_alarm);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        initialize();

        Calendar calendar = Calendar.getInstance();
        startdate.setText(calendar.get(YEAR) + "-" + (calendar.get(Calendar.MONTH)+1) + "-" + calendar.get(Calendar.DATE));
        enddate.setText(calendar.get(YEAR) + "-" + (calendar.get(Calendar.MONTH)+1) + "-" + calendar.get(Calendar.DATE));

        DatePickerDialog startDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) { //DB는 여기서? 처리하면? 될것같아요..........
                startdate.setText(year + "-" + (month + 1) + "-" + day);
                start_year = year;
                start_month = month;
                start_date = day;
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE));

        startdatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (startdatebtn.isClickable()) {
                    startDialog.show();
                }
            }
        });

        DatePickerDialog endDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                enddate.setText(year + "-" + (month + 1) + "-" + day);
                end_year = year;
                end_month = month;
                end_date = day;
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE));

        enddatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (enddatebtn.isClickable()) {
                    endDialog.show();
                }
            }
        });

        /*Intent intent = new Intent(this, AddAlarm.class);
        PendingIntent pIntent = PendingIntent.getBroadcast(this, 0, intent, 0);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            hour = timePicker.getHour();
            minute = timePicker.getMinute();
        }*/

        savebtn = findViewById(R.id.addsaveBtn);
        cancelbtn = findViewById(R.id.addcancelBtn);

        savebtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                regist(view);
            }
        });

        cancelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        dbHelper = new alarmDBHelper(this);
        dbHelper.open();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void regist(View view) {

        boolean[] week = { false, cbMon.isChecked(), cbTue.isChecked(), cbWed.isChecked(), cbThu.isChecked(), cbFri.isChecked(), cbSat.isChecked(), cbSun.isChecked() };

        if(!cbSun.isChecked() &&  !cbMon.isChecked() &&  !cbTue.isChecked() && !cbWed.isChecked() &&  !cbThu.isChecked() && !cbFri.isChecked() && !cbSat.isChecked()){
            Toast.makeText(this, "요일을 선택해 주세요.", Toast.LENGTH_SHORT).show();
            return;
        }

        int day_set = 0;
        int weight = 1;
        for(int i=1; i<=7; i++){
            if(week[i]) {
                day_set= day_set + i*weight;
                weight*=10;
            }
        }

        hour = timePicker.getHour();
        minute = timePicker.getMinute();

        Intent intent = new Intent(this, AddAlarm.class);
        intent.putExtra("weekday", week);
        PendingIntent pIntent = PendingIntent.getBroadcast(this, 0,intent, 0); //PendingIntent.FLAG_UPDATE_CURRENT
        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        Date today = new Date();
        long intervalDay = 24 * 60 * 60 * 1000;// 24시간

        long selectTime=calendar.getTimeInMillis();
        long currenTime=System.currentTimeMillis();

        //만약 설정한 시간이, 현재 시간보다 작다면 알람이 부정확하게 울리기 때문에 다음날 울리게 설정
        if(currenTime>selectTime){
            selectTime += intervalDay;
        }

        //Log.e(TAG,"등록 버튼을 누른 시간 : "+today+"  설정한 시간 : "+calendar.getTime());

        //Log.d(TAG,"calendar.getTimeInMillis()  : "+calendar.getTimeInMillis());

        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, selectTime,  intervalDay, pIntent);

        String med = medicineName.getText().toString();

        dbHelper.insertColumn(hour,minute,med,this.start_year, this.start_month, this.start_date, this.end_year, this.end_month, this.end_date, day_set);
        finish();
    }

    public void unregist(View view) {
        Intent intent = new Intent(this, AddAlarm.class);
        PendingIntent pIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
        alarmManager.cancel(pIntent);
    }// unregist()..

    @SuppressLint("CutPasteId")
    private void initialize() {
        cbSun = findViewById(R.id.cb_sun);
        cbMon = findViewById(R.id.cb_mon);
        cbTue = findViewById(R.id.cb_thu);
        cbWed = findViewById(R.id.cb_wed);
        cbThu = findViewById(R.id.cb_thu);
        cbFri = findViewById(R.id.cb_fri);
        cbSat = findViewById(R.id.cb_sat);

        medicineName = findViewById(R.id.addAlarmName);
        startdate = findViewById(R.id.addStartdate);
        enddate = findViewById(R.id.addEnddate);
        startdatebtn = findViewById(R.id.addsetStartdate);
        enddatebtn = findViewById(R.id.addsetEnddate);
        timePicker = findViewById(R.id.setTime);
    }
}