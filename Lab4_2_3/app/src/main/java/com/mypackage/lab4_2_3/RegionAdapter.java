package com.mypackage.lab4_2_3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by user on 11.12.2016.
 */

public class RegionAdapter extends BaseAdapter {
    Context ctx;
    LayoutInflater lInflater;
    ArrayList<Region> objects;

    public RegionAdapter(Context context, ArrayList<Region> regions) {
        ctx = context;
        objects = regions;
        lInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    // кол-во элементов
    @Override
    public int getCount() {
        return objects.size();
    }

    // элемент по позиции
    @Override
    public Object getItem(int position) {
        return objects.get(position);
    }

    // id по позиции
    @Override
    public long getItemId(int position) {
        return position;
    }

    // пункт списка
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // используем созданные, но не используемые view
        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(R.layout.row_layout, parent, false);
        }

        Region r = getRegion(position);

        if(r.regionCode != -1)
            ((TextView) view.findViewById(R.id.regionCode)).setText(r.regionCode + "");
        else
            ((TextView) view.findViewById(R.id.regionCode)).setVisibility(View.GONE);
        if(r.regionName != null)
            ((TextView) view.findViewById(R.id.regionName)).setText(r.regionName);
        else
            ((TextView) view.findViewById(R.id.regionName)).setVisibility(View.GONE);
        if(r.areaName != null)
            ((TextView) view.findViewById(R.id.areaName)).setText("(" + r.areaName + ")");
        else
            ((TextView) view.findViewById(R.id.areaName)).setVisibility(View.GONE);
        if(r.population != -1)
            ((TextView) view.findViewById(R.id.population)).setText(r.population + " чел.");
        else
            ((TextView) view.findViewById(R.id.population)).setVisibility(View.GONE);

        return view;
    }

    Region getRegion(int position) {
        return ((Region) getItem(position));
    }

    ArrayList<Region> getAll() {
        return objects;
    }

}
