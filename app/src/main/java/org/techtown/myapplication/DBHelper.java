package org.techtown.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper
{
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "user_information.db";

    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // 데이터 베이스가 생성이 될 때 호출
        // 데이터베이스 -> 테이블 -> 컬럼 -> 값
        db.execSQL("CREATE TABLE IF NOT EXISTS User(id INTEGER PRIMARY KEY AUTOINCREMENT, nickname TEXT NOT NULL, password TEXT NOT NULL, name TEXT NOT NULL, age TEXT NOT NULL, sex TEXT NOT NULL, health TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }

    // INSERT
    public void InsertUser(String _nickname, String _password, String _name, String _age, String _sex, String _health){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO User (nickname, password, name, age, sex, health) VALUES('" + _nickname + "','" + _password + "','" + _name + "','" + _age + "','" + _sex + "','" + _health + "');");
    }

    // UPDATE
    public void UpdateUser(String _nickname, String _password, String _name, String _age, String _sex, String _health){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE User SET nickname = '" + _nickname + "', password = '" + _password + "', name = '" + _name + "', age = '" + _age + "', sex = '" + _sex + "', health = '" + _health + "';");
    }
    // DELETE
    public void DeleteUser(int id){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM User WHERE id = " + id + ";");
    }
    // SELECT
    public void SelectUser(int id){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("SELECT * FROM User WHERE id = " + id + ";");
    }
}

