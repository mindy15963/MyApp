package org.techtown.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;
/**
 * DB 헬퍼
 * -DB를 관리하는 기능을 합니다.
 * @author 유세빈, 김은석, 이하나, 김동권
 */
public class DBHelper extends SQLiteOpenHelper
{
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "user_information.db";

    /**
     * DB 헬퍼
     * @param context
     */
    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    /**
     * DB 생성
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        // 데이터 베이스가 생성이 될 때 호출
        // 데이터베이스 -> 테이블 -> 컬럼 -> 값
        db.execSQL("CREATE TABLE IF NOT EXISTS User(id INTEGER PRIMARY KEY AUTOINCREMENT, nickname TEXT NOT NULL, password TEXT NOT NULL, name TEXT NOT NULL, age TEXT NOT NULL, sex TEXT NOT NULL, health TEXT)");

    }

    /**
     * DB 갱신
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }

    /**
     * 사용자 삽입
     * @param _nickname
     * @param _password
     * @param _name
     * @param _age
     * @param _sex
     * @param _health
     */
    public void InsertUser(String _nickname, String _password, String _name, String _age, String _sex, String _health){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO User (nickname, password, name, age, sex, health) VALUES('" + _nickname + "','" + _password + "','" + _name + "','" + _age + "','" + _sex + "','" + _health + "');");
    }

    /**
     * 사용자 갱신
     * @param _nickname
     * @param _password
     * @param _name
     * @param _age
     * @param _sex
     * @param _health
     */
    public void UpdateUser(String _nickname, String _password, String _name, String _age, String _sex, String _health){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE User SET nickname = '" + _nickname + "', password = '" + _password + "', name = '" + _name + "', age = '" + _age + "', sex = '" + _sex + "', health = '" + _health + "';");
    }

    /**
     * 사용자 삭제
     * @param id
     */
    public void DeleteUser(int id){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM User WHERE id = " + id + ";");
    }

    /**
     * 사용자 선택
     * @param id
     */
    public void SelectUser(int id){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("SELECT * FROM User WHERE id = " + id + ";");
    }
}

