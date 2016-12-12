package com.mypackage.lab4_2_3;

/**
 * Created by user on 11.12.2016.
 */

public class Region {
    String regionName;
    String areaName;
    int population;
    int regionCode;
    public Region(int regionCode, String regionName, String areaName, int population){
        this.regionName = regionName;
        this.areaName = areaName;
        this.regionCode = regionCode;
        this.population = population;
    }

}
