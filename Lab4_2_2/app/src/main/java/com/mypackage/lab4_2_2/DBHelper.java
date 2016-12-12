package com.mypackage.lab4_2_2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by user on 11.12.2016.
 */

public class DBHelper extends SQLiteOpenHelper {
    final String LOG_TAG = "myLogs";


    public DBHelper(Context context) {
        super(context, "classmates", null, 4);
    }
    @Override
    public void onCreate(SQLiteDatabase db2) {
        db2.execSQL("create table classmates ("
                + "id integer primary key autoincrement, "
                + "id_n,"
                + "name text, "
                + "second_name text, "
                + "surname text, "
                + "time text);");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db2, int oldVersion, int
            newVersion) {
        Log.d(LOG_TAG, " --- onUpgrade database from " + oldVersion
                + " to " + newVersion + " version --- ");
        if (oldVersion < newVersion) {
            db2.execSQL("drop table " + "classmates" + ";");
            onCreate(db2);
        }
    }
}