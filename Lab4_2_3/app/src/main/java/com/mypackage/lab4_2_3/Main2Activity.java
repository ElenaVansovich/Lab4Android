package com.mypackage.lab4_2_3;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {

    DBHelper dbHelper;
    ListView list;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        RegionAdapter regionAdapter;

        // создаем объект для создания и управления версиями БД
        dbHelper = new DBHelper(getApplicationContext());
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor c = db.query("regionBel", null, null, null, null, null, null);

        ArrayList<Region> regions = new ArrayList<>();
        // ставим позицию курсора на первую строку выборки
        // если в выборке нет строк, вернется false
        if (c.moveToFirst()) {

            // определяем номера столбцов по имени в выборке
            int regionCodeColIndex = c.getColumnIndex("regionCode");
            int regionNameColIndex = c.getColumnIndex("regionName");
            int populationColIndex = c.getColumnIndex("population");
            int areaNameColIndex = c.getColumnIndex("areaName");

            do {
                // получаем значения по номерам столбцов
                Region region = new Region(c.getInt(regionCodeColIndex), c.getString(regionNameColIndex),
                        c.getString(areaNameColIndex), c.getInt(populationColIndex));
                regions.add(region);
                // переход на следующую строку
                // а если следующей нет (текущая - последняя), то false - выходим из цикла
            } while (c.moveToNext());
        }
        regionAdapter = new RegionAdapter(this, regions);
        list = (ListView) findViewById(R.id.listView);
        list.setAdapter(regionAdapter);
        c.close();
    }

    public void onClicked(View view) {
        boolean check = ((CheckBox) view).isChecked();

        switch (view.getId()) {
            case R.id.checkName:
                if (check)
                    getSorted("regionName", true);
                break;
            case R.id.checkPopulation:
                if (check)
                    getSorted("population", false);
                break;
            case R.id.checkArea:
                if (check)
                    getSorted("areaName", true);
                break;
            default:
                break;
        }
    }

    private void getSorted(String sortColName, boolean isASC) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sortParam;
        if (isASC)
            sortParam = sortColName + " ASC";
        else
            sortParam = sortColName + " DESC";

        Cursor c = db.query("regionBel", null, null, null, null, null, sortParam);
        RegionAdapter regionAdapter;
        ArrayList<Region> regions = new ArrayList<>();
        // ставим позицию курсора на первую строку выборки
        // если в выборке нет строк, вернется false
        if (c.moveToFirst()) {

            // определяем номера столбцов по имени в выборке
            int regionCodeColIndex = c.getColumnIndex("regionCode");
            int regionNameColIndex = c.getColumnIndex("regionName");
            int populationColIndex = c.getColumnIndex("population");
            int areaNameColIndex = c.getColumnIndex("areaName");

            do {
                // получаем значения по номерам столбцов
                Region region = new Region(c.getInt(regionCodeColIndex), c.getString(regionNameColIndex),
                        c.getString(areaNameColIndex), c.getInt(populationColIndex));
                regions.add(region);
                // переход на следующую строку
                // а если следующей нет (текущая - последняя), то false - выходим из цикла
            } while (c.moveToNext());
        }
        regionAdapter = new RegionAdapter(this, regions);
        list = (ListView) findViewById(R.id.listView);
        list.setAdapter(regionAdapter);
        c.close();

    }
}