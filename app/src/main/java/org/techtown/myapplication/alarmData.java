package org.techtown.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class alarmData extends SQLiteOpenHelper {
    private String timeText;
    private String medicineText;
    private String starttimeText;
    private String endtimeText;

    public alarmData(@Nullable Context context) {
        super(context, "alarm.db", null, 1);
    }
    @Override
    //글 작성
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table alarm(_id integer primary key autoincrement,_time time,medicine text,starttime date,endtime date)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public String getTimeText() {
        return timeText;
    }

    public void setTimeText(String timeText) {
        this.timeText = timeText;
    }

    public String getMedicineText() {
        return medicineText;
    }

    public void setMedicineText(String medicineText) {
        this.medicineText = medicineText;
    }

    public String getStarttimeText() {
        return starttimeText;
    }

    public void setStarttimeText(String starttimeText) {
        this.starttimeText = starttimeText;
    }

    public String getEndtimeText() {
        return endtimeText;
    }

    public void setEndtimeText(String endtimeText) {
        this.endtimeText = endtimeText;
    }
}
