package org.techtown.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UserDBHelper {

    private static final String DATABASE_NAME = "InnerDatabase(SQLite).db";
    private static final int DATABASE_VERSION = 1;
    public static SQLiteDatabase uDB;
    private DatabaseHelper uDBHelper;
    private Context context;

    private class DatabaseHelper extends SQLiteOpenHelper {

        public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db){
            db.execSQL(UserDB.CreateDB._CREATEUSERDB);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
            db.execSQL("DROP TABLE IF EXISTS "+UserDB.CreateDB.USERTABLE);
            onCreate(db);
        }
    }

    public UserDBHelper(Context context){
        this.context = context;
    }

    public UserDBHelper open() throws SQLException {
        uDBHelper = new DatabaseHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
        uDB = uDBHelper.getWritableDatabase();
        return this;
    }

    public void create(){
        uDBHelper.onCreate(uDB);
    }

    public void close(){
        uDB.close();
    }

    public Boolean checkusername(String userid){
        Cursor c = uDB.rawQuery("SELECT * FROM usertable WHERE USERID = ?", new String[] {userid});
        if (c.getCount() > 0)
            return false;
        else
            return true;
    }



    public Boolean memberCheck(String userid, String pw){
        Cursor c = uDB.rawQuery("SELECT * FROM usertable WHERE USERID = ? AND PW = ?", new String[] {userid, pw});
        if (c.getCount() > 0)
            return true;
        else
            return false;
    }

    public long insertColumn(String userid, String pw, String name, int age, int sex, boolean health1
            , boolean health2, boolean health3, boolean health4, boolean health5, boolean health6, boolean health7){                          //데이터베이스 삽입
        ContentValues values = new ContentValues();
        values.put(UserDB.CreateDB.USERID, userid);
        values.put(UserDB.CreateDB.PW, pw);
        values.put(UserDB.CreateDB.NAME, name);
        values.put(UserDB.CreateDB.AGE, age);
        values.put(UserDB.CreateDB.SEX, sex);
        values.put(UserDB.CreateDB.HEALTH1, health1);
        values.put(UserDB.CreateDB.HEALTH2, health2);
        values.put(UserDB.CreateDB.HEALTH3, health3);
        values.put(UserDB.CreateDB.HEALTH4, health4);
        values.put(UserDB.CreateDB.HEALTH5, health5);
        values.put(UserDB.CreateDB.HEALTH6, health6);
        values.put(UserDB.CreateDB.HEALTH7, health7);
        return uDB.insert(UserDB.CreateDB.USERTABLE, null, values);
    }

    public Cursor selectColumns(){                                                                          //데이터베이스 선택
        return uDB.query(UserDB.CreateDB.USERTABLE, null, null, null, null, null, null);
    }

    public Cursor sortColumn(){                                                                             //데이터베이스 정렬
        Cursor c = uDB.rawQuery( "SELECT * FROM usertable ORDER BY " + "userid" + ";", null);
        return c;
    }

    public boolean updateColumn(long ukey, String userid, String pw, String name, int age, int sex, boolean health1
            , boolean health2, boolean health3, boolean health4, boolean health5, boolean health6, boolean health7){                                                     //데이터베이스 수정
        ContentValues values = new ContentValues();
        values.put(UserDB.CreateDB.USERID, userid);
        values.put(UserDB.CreateDB.PW, pw);
        values.put(UserDB.CreateDB.NAME, name);
        values.put(UserDB.CreateDB.AGE, age);
        values.put(UserDB.CreateDB.SEX, sex);
        values.put(UserDB.CreateDB.HEALTH1, health1);
        values.put(UserDB.CreateDB.HEALTH2, health2);
        values.put(UserDB.CreateDB.HEALTH3, health3);
        values.put(UserDB.CreateDB.HEALTH4, health4);
        values.put(UserDB.CreateDB.HEALTH5, health5);
        values.put(UserDB.CreateDB.HEALTH6, health6);
        values.put(UserDB.CreateDB.HEALTH7, health7);
        return uDB.update(UserDB.CreateDB.USERTABLE, values, "ukey=" + ukey, null) > 0;
    }

    public boolean deleteColumn(long ukey){                                                                       //데이터베이스 삭제
        return uDB.delete(UserDB.CreateDB.USERTABLE, "ukey=" + ukey, null) > 0;
    }
}