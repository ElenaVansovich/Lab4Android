package com.mypackage.lab4_2;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
public class MainActivity extends AppCompatActivity {

    final String LOG_TAG = "logs";
    static DBHelper dbHelper;
    SQLiteDatabase db;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DBHelper(this);

        db = dbHelper.getWritableDatabase();

        ContentValues cv = new ContentValues();

        Log.d(LOG_TAG, "--- Clear classmates: ---");
        int clearCount = db.delete("classmates", null, null);
        Log.d(LOG_TAG, "deleted rows count = " + clearCount);

        Log.d(LOG_TAG, "--- Insert in classmates: ---");
        cv.put("id_n", 1);
        cv.put("name", "Савицкая Анастасия Олеговна");
        cv.put("time", "28.07.2016");
        long rowID = db.insert("classmates", null, cv);
        Log.d(LOG_TAG, "row inserted, id = " + rowID);

        cv.put("id_n", 2);
        cv.put("name", "Вайтюль Лариса Андреевна");
        cv.put("time", "23.09.2016");
        rowID = db.insert("classmates", null, cv);
        Log.d(LOG_TAG, "row inserted, id = " + rowID);
        cv.put("id_n", 3);
        cv.put("name", "Петух Леонид Евгеньевич");
        cv.put("time", "25.10.2016");
        rowID = db.insert("classmates", null, cv);
        Log.d(LOG_TAG, "row inserted, id = " + rowID);
        cv.put("id_n", 4);
        cv.put("name", "Маркевич Каролина Дмитриевна");
        cv.put("time", "03.11.2016");
        rowID = db.insert("classmates", null, cv);
        Log.d(LOG_TAG, "row inserted, id = " + rowID);
        cv.put("id_n", 5);
        cv.put("name", "Адамович Артем Константинович");
        cv.put("time", "01.12.2016");
        rowID = db.insert("classmates", null, cv);
        Log.d(LOG_TAG, "row inserted, id = " + rowID);
    }

    public void onClick1(View v) {
        Intent intent2 = new Intent(MainActivity.this, MainActivityShow.class);
        startActivity(intent2);
    }

    public void onClick2(View v) {
        Intent intent1 = new Intent(MainActivity.this, MainActivityAdd.class);
        startActivity(intent1);
    }

    public void onClick3(View v) {
        ContentValues cv = new ContentValues();
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Log.d(LOG_TAG, "--- Update classmates: ---");
        Cursor c = db.query("classmates", null, null, null, null, null, null);
        c.moveToLast();

        int id_nColIndex = c.getColumnIndex("id_n");
        int dateColIndex = c.getColumnIndex("time");

        cv.put("id_n", c.getString(id_nColIndex));
        cv.put("name", "Иванов Иван Иванович");
        cv.put("time", c.getString(dateColIndex));

        int updCount = db.update("classmates", cv, "id_n = ?",
                new String[] { c.getString(id_nColIndex) });
        Log.d(LOG_TAG, "updated rows count = " + updCount);
        c.moveToNext();


        Log.d(LOG_TAG, "--- Rows in classmates: ---");

        c = db.query("classmates", null, null, null, null, null, null);

        if (c.moveToFirst()) {

            int idColIndex = c.getColumnIndex("id_n");
            int nameColIndex = c.getColumnIndex("name");
            dateColIndex = c.getColumnIndex("time");

            do {
                Log.d(LOG_TAG,
                        "id_n = " + c.getInt(idColIndex) +
                                ", name = " + c.getString(nameColIndex) +
                                ", time = " + c.getString(dateColIndex));
            } while (c.moveToNext());
        } else
            Log.d(LOG_TAG, "0 rows");
        c.close();
    }

    class DBHelper extends SQLiteOpenHelper {
        public DBHelper(Context context) {
            super(context, "myDB2", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            Log.d(LOG_TAG, "--- onCreate database ---");
            db.execSQL("create table classmates ("
                    + "id integer primary key autoincrement,"
                    + "id_n,"
                    + "name text,"
                    + "time text" + ");");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int
                newVersion) {
        }
    }
}
