package org.techtown.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 알람 DB 헬퍼
 * -알람 DB를 관리하는 기능을 합니다.
 * @author 유세빈, 김은석, 이하나, 김동권
 */

public class alarmDBHelper {

    private static final String DATABASE_NAME = "InnerDatabase(SQLite).db";
    private static final int DATABASE_VERSION = 1;
    public static SQLiteDatabase alDB;
    private DatabaseHelper alDBHelper;
    private Context context;

    private class DatabaseHelper extends SQLiteOpenHelper {

        /**
         * DB 헬퍼
         * @param context
         * @param name
         * @param factory
         * @param version
         */
        public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        /**
         * DB 생성
         * @param db
         */
        @Override
        public void onCreate(SQLiteDatabase db){
            db.execSQL(alarmDB.CreateDB._CREATE0);
        }

        /**
         * DB 갱신
         * @param db
         * @param oldVersion
         * @param newVersion
         */
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
            db.execSQL("DROP TABLE IF EXISTS "+alarmDB.CreateDB._TABLENAME0);
            onCreate(db);
        }
    }

    /**
     * 내용 할당
     * @param context
     */
    public alarmDBHelper(Context context){
        this.context = context;
    }

    /**
     * DB 열기
     * @return this
     * @throws SQLException
     */
    public alarmDBHelper open() throws SQLException {
        alDBHelper = new DatabaseHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
        alDB = alDBHelper.getWritableDatabase();
        return this;
    }

    /**
     * DB 만들기
     */
    public void create(){
        alDBHelper.onCreate(alDB);
    }

    /**
     * DB 닫기
     */
    public void close(){
        alDB.close();
    }

    /**
     * 열 삽입하기
     * @param hour
     * @param minuate
     * @param medicine
     * @param start_year
     * @param start_month
     * @param start_date
     * @param end_year
     * @param end_month
     * @param end_date
     * @param day
     * @return alDB.insert(alarmDB.CreateDB._TABLENAME0, null, values)
     */
    public long insertColumn(int hour, int minuate, String medicine, int start_year, int start_month
            , int start_date, int end_year, int end_month, int end_date, int day){                          //데이터베이스 삽입
        ContentValues values = new ContentValues();
        values.put(alarmDB.CreateDB.HOUR, hour);
        values.put(alarmDB.CreateDB.MINUTE, minuate);
        values.put(alarmDB.CreateDB.MEDICINE, medicine);
        values.put(alarmDB.CreateDB.START_YEAR, start_year);
        values.put(alarmDB.CreateDB.START_MONTH, start_month);
        values.put(alarmDB.CreateDB.START_DATE, start_date);
        values.put(alarmDB.CreateDB.END_YEAR, end_year);
        values.put(alarmDB.CreateDB.END_MONTH, end_month);
        values.put(alarmDB.CreateDB.END_DATE, end_date);
        values.put(alarmDB.CreateDB.DAY, day);
        return alDB.insert(alarmDB.CreateDB._TABLENAME0, null, values);
    }

    /**
     * 열 선택하기
     * @return alDB.query(alarmDB.CreateDB._TABLENAME0, null, null, null, null, null, null)
     */
    public Cursor selectColumns(){                                                                          //데이터베이스 선택
        return alDB.query(alarmDB.CreateDB._TABLENAME0, null, null, null, null, null, null);
    }

    /**
     * 열 정렬하기
     * @return c
     */
    public Cursor sortColumn(){                                                                             //데이터베이스 정렬
        Cursor c = alDB.rawQuery( "SELECT * FROM alarmtable ORDER BY " + "hour" + ";", null);
        return c;
    }

    /**
     * 열 갱신하기
     * @param id
     * @param hour
     * @param minuate
     * @param medicine
     * @param start_year
     * @param start_month
     * @param start_date
     * @param end_year
     * @param end_month
     * @param end_date
     * @param day
     * @return alDB.update(alarmDB.CreateDB._TABLENAME0, values, "_id=" + id, null) > 0
     */
    public boolean updateColumn(long id, int hour, int minuate, String medicine, int start_year,
                                int start_month, int start_date, int end_year, int end_month,
                                int end_date, int day){                                                     //데이터베이스 수정
        ContentValues values = new ContentValues();
        values.put(alarmDB.CreateDB.HOUR, hour);
        values.put(alarmDB.CreateDB.MINUTE, minuate);
        values.put(alarmDB.CreateDB.MEDICINE, medicine);
        values.put(alarmDB.CreateDB.START_YEAR, start_year);
        values.put(alarmDB.CreateDB.START_MONTH, start_month);
        values.put(alarmDB.CreateDB.START_DATE, start_date);
        values.put(alarmDB.CreateDB.END_YEAR, end_year);
        values.put(alarmDB.CreateDB.END_MONTH, end_month);
        values.put(alarmDB.CreateDB.END_DATE, end_date);
        values.put(alarmDB.CreateDB.DAY, day);
        return alDB.update(alarmDB.CreateDB._TABLENAME0, values, "_id=" + id, null) > 0;
    }

    /**
     * 열 삭제하기
     * @param id
     * @return alDB.delete(alarmDB.CreateDB._TABLENAME0, "_id="+id, null) > 0
     */
    public boolean deleteColumn(long id){                                                                       //데이터베이스 삭제
        return alDB.delete(alarmDB.CreateDB._TABLENAME0, "_id="+id, null) > 0;
    }
}
