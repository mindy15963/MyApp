package org.techtown.myapplication;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class AddAlarm extends AppCompatActivity {
    alarmData helper;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_alarm);
        EditText time=findViewById(R.id.time);
        String strtime=time.getText().toString();
        EditText medicine=findViewById(R.id.medicine);
        String strmedicine=medicine.getText().toString();
        EditText startdate=findViewById(R.id.startdate);
        String strstartdate=startdate.getText().toString();
        EditText enddate=findViewById(R.id.enddate);
        String strenddate=enddate.getText().toString();
        helper=new alarmData(this);
        db=helper.getWritableDatabase();
        Button setR1=findViewById(R.id.setRepetition1);
        setR1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        Button setR2=findViewById(R.id.setRepetition2);
        setR2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        Button setR3=findViewById(R.id.setRepetition3);
        setR3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        Button setR4=findViewById(R.id.setRepetition4);
        setR4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        Button setR5=findViewById(R.id.setRepetition5);
        setR5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        Button setR6=findViewById(R.id.setRepetition6);
        setR6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        Button setR7=findViewById(R.id.setRepetition7);
        setR7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        Button cancel=findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Button save=findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sql="insert into alarm values(";
                sql += "'" + strtime + "',";
                sql += "'" + strmedicine + "',";
                sql += "'" + strstartdate + "',";
                sql += "'" + strenddate + "')";
                db.execSQL(sql);
                Toast.makeText(AddAlarm.this, "저장 완료", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}