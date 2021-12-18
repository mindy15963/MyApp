package org.techtown.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class EditAlarm extends AppCompatActivity {
    int _id;
    alarmData helper;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_alarm);
        EditText time=findViewById(R.id.time);
        String strtime=time.getText().toString();
        EditText medicine=findViewById(R.id.medicine);
        String strmedicine=medicine.getText().toString();
        EditText startdate=findViewById(R.id.startdate);
        String strstartdate=startdate.getText().toString();
        EditText enddate=findViewById(R.id.enddate);
        String strenddate=enddate.getText().toString();
        Intent intent=getIntent();
        _id=intent.getIntExtra("_id",0);
        helper=new alarmData(strtime,strmedicine,strstartdate,strenddate);
        /*
        Cursor cursor=db.rawQuery("select * from alarm where _id="+_id, null);
        if(cursor.moveToNext()){
            TextView edittime=findViewById(R.id.time);
            edittime.setText(cursor.getString(1));
            TextView editalarmname=findViewById(R.id.medicine);
            editalarmname.setText(cursor.getString(2));
            TextView editstartdate=findViewById(R.id.startdate);
            editstartdate.setText(cursor.getString(3));
            TextView editenddate=findViewById(R.id.enddate);
            editenddate.setText(cursor.getString(4));
        }
        */

        Button setR=findViewById(R.id.setRepetition);
        setR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        Button delete=findViewById(R.id.delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                String sql="delete from alarm where _id="+_id;
                db.execSQL(sql);
                onRestart();
                */
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
                /*
                String sql="update alarm set time='" + strtime + "'," + "medicine='" + strmedicine+ "'," + "startdate='" + strstartdate+ "'," + "enddate='" + strenddate + "'";
                sql += " where _id=" +_id;
                db.execSQL(sql);
                Toast.makeText(EditAlarm.this, "수정 완료", Toast.LENGTH_SHORT).show();
                */
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