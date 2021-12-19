package org.techtown.myapplication;

import android.provider.BaseColumns;

public final class alarmDB {

    public static final class CreateDB implements BaseColumns {
        public static final String HOUR = "hour";
        public static final String MINUTE = "minute";
        public static final String MEDICINE = "medicine";
        public static final String START_YEAR = "start_year";
        public static final String START_MONTH = "start_month";
        public static final String START_DATE = "start_date";
        public static final String END_YEAR = "end_year";
        public static final String END_MONTH = "end_month";
        public static final String END_DATE = "end_date";
        public static final String DAY = "day";
        public static final String _TABLENAME0 = "alarmtable";
        public static final String _CREATE0 = "create table if not exists "+_TABLENAME0+"("
                +_ID+" integer primary key autoincrement, "
                +HOUR+" integer not null , "
                +MINUTE+" integer not null , "
                +MEDICINE+" text not null , "
                +START_YEAR+" integer not null , "
                +START_MONTH+" integer not null , "
                +START_DATE+" integer not null , "
                +END_YEAR+" integer not null , "
                +END_MONTH+" integer not null , "
                +END_DATE+" integer not null , "
                +DAY+" integer not null );";
    }
}
