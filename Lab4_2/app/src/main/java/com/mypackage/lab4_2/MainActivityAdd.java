package com.mypackage.lab4_2;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivityAdd extends Activity{

    final String LOG_TAG = "logs";

    EditText nameEd, timeEd, idEd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        idEd = (EditText) findViewById(R.id.editTextID);
        nameEd = (EditText) findViewById(R.id.editTextName);
        timeEd = (EditText) findViewById(R.id.editTextTime);
    }

    public void onClick(View v) {
        ContentValues cv = new ContentValues();

        SQLiteDatabase db = MainActivity.dbHelper.getWritableDatabase();

        String id_n = idEd.getText().toString();
        String name = nameEd.getText().toString();
        String time = timeEd.getText().toString();

        Log.d(LOG_TAG, "--- Insert in classmates: ---");
        cv.put("id_n", id_n);
        cv.put("name", name);
        cv.put("time", time);

        long rowID = db.insert("classmates", null, cv);
        Log.d(LOG_TAG, "row inserted, id = " + rowID);



        Log.d(LOG_TAG, "--- Rows in classmates: ---");
        Cursor c = db.query("classmates", null, null, null, null, null, null);

        if (c.moveToFirst()) {

            int idColIndex = c.getColumnIndex("id_n");
            int nameColIndex = c.getColumnIndex("name");
            int dateColIndex = c.getColumnIndex("time");

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
}
