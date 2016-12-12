package com.mypackage.lab4_2_3;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Main3Activity extends AppCompatActivity {

    DBHelper dbHelper;
    ListView list;
    EditText minPopulation;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

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
        list = (ListView) findViewById(R.id.lvMain2);
        minPopulation = (EditText) findViewById(R.id.etMinPopulation);
        list.setAdapter(regionAdapter);
        c.close();
    }

    public void filter(View view) {
        int minPopulation = 0;
        try {
            minPopulation = Integer.parseInt(this.minPopulation.getText().toString());
        } catch (NumberFormatException ex) {
            Toast.makeText(this, "Min population.", Toast.LENGTH_SHORT).show();
            return;
        }
        RegionAdapter regionAdapter;


        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] columnNames = {
                "areaName",
                "sum(population) as areaPopulation" };
        String having = "sum(population) > " + minPopulation;
        String groupBy = "areaName";
        Cursor c = db.query("regionBel", columnNames, null, null, groupBy, having, null);
        ArrayList<Region> regions = new ArrayList<>();
        // ставим позицию курсора на первую строку выборки
        // если в выборке нет строк, вернется false
        if (c.moveToFirst()) {

            // определяем номера столбцов по имени в выборке
            int populationColIndex = c.getColumnIndex("areaPopulation");
            int areaNameColIndex = c.getColumnIndex("areaName");

            do {
                // получаем значения по номерам столбцов
                Region region = new Region(-1, c.getString(areaNameColIndex),
                        null, c.getInt(populationColIndex));
                regions.add(region);
                // переход на следующую строку
                // а если следующей нет (текущая - последняя), то false - выходим из цикла
            } while (c.moveToNext());
        }
        regionAdapter = new RegionAdapter(this, regions);
        list.setAdapter(regionAdapter);
        c.close();

    }

}
