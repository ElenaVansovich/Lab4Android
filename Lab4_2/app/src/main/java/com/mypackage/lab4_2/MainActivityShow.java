package com.mypackage.lab4_2;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivityShow extends Activity {

    final String LOG_TAG = "logs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_show);

        SQLiteDatabase db = MainActivity.dbHelper.getWritableDatabase();

        Log.d(LOG_TAG, "--- Rows in classmates: ---");

        Cursor c = db.query("classmates", null, null, null, null, null, null);

        List<String> list = new ArrayList<>();
        if (c.moveToFirst()) {

            int idColIndex = c.getColumnIndex("id_n");
            int nameColIndex = c.getColumnIndex("name");
            int timeColIndex = c.getColumnIndex("time");
            do {
                list.add("id_n = " + c.getInt(idColIndex) +
                        ", name = " + c.getString(nameColIndex) +
                        ", time = " + c.getString(timeColIndex));
                Log.d(LOG_TAG,
                        "id_n = " + c.getInt(idColIndex) +
                                ", name = " + c.getString(nameColIndex) +
                                ", time = " + c.getString(timeColIndex));

            } while (c.moveToNext());
        } else
            Log.d(LOG_TAG, "0 rows");
        c.close();

        ListView lvMain = (ListView) findViewById(R.id.listView);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, list);

        lvMain.setAdapter(adapter);
    }
}
