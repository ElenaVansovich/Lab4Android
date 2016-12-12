package com.mypackage.lab4_2_3;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by user on 11.12.2016.
 */

public class DBHelper extends SQLiteOpenHelper {

    public static final String tableName = "regionBel";

    public DBHelper(Context context) {
        // конструктор суперкласса
        super(context, "belRegionsDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // создаем таблицу с полями
        db.execSQL("create table regionBel ("
                + "regionCode integer primary key, "
                + "regionName text, "
                + "population integer, "
                + "areaName text);");
        // заполняем данными
        db.execSQL("insert into regionBel (regionCode, regionName, population, areaName) " +
                "values(162, 'Брест', 340122, 'Брестская обл.');");
        db.execSQL("insert into regionBel (regionCode, regionName, population, areaName) " +
                "values(163, 'Барановичи', 179122, 'Брестская обл.');");
        db.execSQL("insert into regionBel (regionCode, regionName, population, areaName) " +
                "values(165, 'Пинск', 138415, 'Брестская обл.');");

        db.execSQL("insert into regionBel (regionCode, regionName, population, areaName) " +
                "values(17, 'Минск', 1959781, 'Минская обл.');");
        db.execSQL("insert into regionBel (regionCode, regionName, population, areaName) " +
                "values(177, 'Борисов', 143919, 'Минская обл.');");
        db.execSQL("insert into regionBel (regionCode, regionName, population, areaName) " +
                "values(174, 'Солигорск', 106503, 'Минская обл.');");
        db.execSQL("insert into regionBel (regionCode, regionName, population, areaName) " +
                "values(176, 'Молодечно', 94922, 'Минская обл.');");

        db.execSQL("insert into regionBel (regionCode, regionName, population, areaName) " +
                "values(232, 'Гомель', 521452, 'Гомельская обл.');");
        db.execSQL("insert into regionBel (regionCode, regionName, population, areaName) " +
                "values(236, 'Мозырь', 112003, 'Гомельская обл.');");
        db.execSQL("insert into regionBel (regionCode, regionName, population, areaName) " +
                "values(2334, 'Жлобин', 75956, 'Гомельская обл.');");


        db.execSQL("insert into regionBel (regionCode, regionName, population, areaName) " +
                "values(222, 'Могилёв', 378077, 'Могилёвская обл.');");
        db.execSQL("insert into regionBel (regionCode, regionName, population, areaName) " +
                "values(225, 'Бобруйск', 217975, 'Могилёвская обл.');");

        db.execSQL("insert into regionBel (regionCode, regionName, population, areaName) " +
                "values(212, 'Витебск', 368574, 'Витебская обл.');");
        db.execSQL("insert into regionBel (regionCode, regionName, population, areaName) " +
                "values(216, 'Орша', 116552, 'Витебская обл.');");
        db.execSQL("insert into regionBel (regionCode, regionName, population, areaName) " +
                "values(214, 'Новополоцк', 102394, 'Витебская обл.');");
        db.execSQL("insert into regionBel (regionCode, regionName, population, areaName) " +
                "values(215, 'Полоцк', 85078, 'Витебская обл.');");

        db.execSQL("insert into regionBel (regionCode, regionName, population, areaName) " +
                "values(152, 'Гродно', 365610, 'Гродненская обл.');");
        db.execSQL("insert into regionBel (regionCode, regionName, population, areaName) " +
                "values(154, 'Лида', 100443, 'Гродненская обл.');");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int
            newVersion) {
    }
}
