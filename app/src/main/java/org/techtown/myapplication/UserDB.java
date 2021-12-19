package org.techtown.myapplication;

import android.provider.BaseColumns;

/**
 * 사용자 DB
 * -사용자를 관리하는 데이터베이스입니다.
 * @author 유세빈, 김은석, 이하나, 김동권
 */
public final class UserDB {

    /**
     * DB 생성
     */
    public static final class CreateDB implements BaseColumns {
        public static final String UKEY = "ukey";
        public static final String USERID = "userid";
        public static final String PW = "pw";
        public static final String NAME = "name";
        public static final String AGE = "age";
        public static final String SEX = "sex";
        public static final String HEALTH1 = "health1";
        public static final String HEALTH2 = "health2";
        public static final String HEALTH3 = "health3";
        public static final String HEALTH4 = "health4";
        public static final String HEALTH5 = "health5";
        public static final String HEALTH6 = "health6";
        public static final String HEALTH7 = "health7";
        public static final String USERTABLE = "usertable";

        public static final String _CREATEUSERDB = "create table if not exists "+USERTABLE+"("
                +UKEY+" integer primary key autoincrement, "
                +USERID+" string not null , "
                +PW+" string not null , "
                +NAME+" string not null , "
                +AGE+" integer not null , "
                +SEX+" integer not null , "
                +HEALTH1+" boolean not null , "
                +HEALTH2+" boolean not null , "
                +HEALTH3+" boolean not null , "
                +HEALTH4+" boolean not null , "
                +HEALTH5+" boolean not null , "
                +HEALTH6+" boolean not null , "
                +HEALTH7+" boolean not null );";
    }
}
