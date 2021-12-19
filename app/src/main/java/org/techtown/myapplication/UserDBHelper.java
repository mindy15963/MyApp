package org.techtown.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
/**
 * 사용자 DB 헬퍼
 * -사용자 DB를 관리하는 기능을 합니다.
 * @author 유세빈, 김은석, 이하나, 김동권
 */
public class UserDBHelper {

    private static final String DATABASE_NAME = "InnerDatabase(SQLite).db";
    private static final int DATABASE_VERSION = 1;
    public static SQLiteDatabase uDB;
    private DatabaseHelper uDBHelper;
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
            db.execSQL(UserDB.CreateDB._CREATEUSERDB);
        }

        /**
         * DB 갱신
         * @param db
         * @param oldVersion
         * @param newVersion
         */
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
            db.execSQL("DROP TABLE IF EXISTS "+UserDB.CreateDB.USERTABLE);
            onCreate(db);
        }
    }

    /**
     * 내용 할당
     * @param context
     */
    public UserDBHelper(Context context){
        this.context = context;
    }

    /**
     * DB 열기
     * @return this
     * @throws SQLException
     */
    public UserDBHelper open() throws SQLException {
        uDBHelper = new DatabaseHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
        uDB = uDBHelper.getWritableDatabase();
        return this;
    }

    /**
     * DB 만들기
     */
    public void create(){
        uDBHelper.onCreate(uDB);
    }

    /**
     * DB 닫기
     */
    public void close(){
        uDB.close();
    }

    /**
     * 사용자 이름 확인
     * @param userid
     * @return false(불일치 시)
     * @return true(일치 시)
     */
    public Boolean checkusername(String userid){
        Cursor c = uDB.rawQuery("SELECT * FROM usertable WHERE USERID = ?", new String[] {userid});
        if (c.getCount() > 0)
            return false;
        else
            return true;
    }

    /**
     * 회원 확인
     * @param userid
     * @param pw
     * @return false(불일치 시)
     * @return true(일치 시)
     */
    public Boolean memberCheck(String userid, String pw){
        Cursor c = uDB.rawQuery("SELECT * FROM usertable WHERE USERID = ? AND PW = ?", new String[] {userid, pw});
        if (c.getCount() > 0)
            return true;
        else
            return false;
    }

    /**
     * 열 삽입
     * @param userid
     * @param pw
     * @param name
     * @param age
     * @param sex
     * @param health1
     * @param health2
     * @param health3
     * @param health4
     * @param health5
     * @param health6
     * @param health7
     * @return uDB.insert(UserDB.CreateDB.USERTABLE, null, values)
     */
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

    /**
     * 열 선택
     * @return uDB.query(UserDB.CreateDB.USERTABLE, null, null, null, null, null, null)
     */
    public Cursor selectColumns(){                                                                          //데이터베이스 선택
        return uDB.query(UserDB.CreateDB.USERTABLE, null, null, null, null, null, null);
    }

    /**
     * 열 정렬
     * @return c
     */
    public Cursor sortColumn(){                                                                             //데이터베이스 정렬
        Cursor c = uDB.rawQuery( "SELECT * FROM usertable ORDER BY " + "userid" + ";", null);
        return c;
    }

    /**
     * 열 갱신
     * @param ukey
     * @param userid
     * @param pw
     * @param name
     * @param age
     * @param sex
     * @param health1
     * @param health2
     * @param health3
     * @param health4
     * @param health5
     * @param health6
     * @param health7
     * @return uDB.update(UserDB.CreateDB.USERTABLE, values, "ukey=" + ukey, null) > 0
     */
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

    /**
     * 열 삭제
     * @param ukey
     * @return uDB.delete(UserDB.CreateDB.USERTABLE, "ukey=" + ukey, null) > 0
     */
    public boolean deleteColumn(long ukey){                                                                       //데이터베이스 삭제
        return uDB.delete(UserDB.CreateDB.USERTABLE, "ukey=" + ukey, null) > 0;
    }
}