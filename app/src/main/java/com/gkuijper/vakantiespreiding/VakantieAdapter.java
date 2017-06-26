package com.gkuijper.vakantiespreiding;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Gabrielle on 24-06-17.
 */

public class VakantieAdapter extends BaseAdapter{

    private Context context;
    private LayoutInflater layoutInflater;
    private ArrayList<VakantieItem> currentList;

    public VakantieAdapter(Context context, LayoutInflater layoutInflater, ArrayList<VakantieItem> vakantieItems) {
        this.context = context;
        this.layoutInflater = layoutInflater;
        this.currentList = vakantieItems;


    }

    @Override
    public int getCount() {
        return currentList.size();
    }

    @Override
    public VakantieItem getItem(int position) {
        return this.currentList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;

        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.layout_main_row, null);

            viewHolder = new ViewHolder();
            viewHolder.vakantie = (TextView) convertView.findViewById(R.id.Vakantie_naam_id);
            viewHolder.regio = (TextView) convertView.findViewById(R.id.regio_id);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final VakantieItem vakantieItem = currentList.get(position);

        viewHolder.vakantie.setText(vakantieItem.getName());
        if(vakantieItem.getTijdvak().size() == 1) {
            viewHolder.regio.setText(String.valueOf(vakantieItem.getTijdvak().size()) + " Regio");
        } else {
            viewHolder.regio.setText(String.valueOf(vakantieItem.getTijdvak().size()) + " Regio's");
            viewHolder.vakantie.setTextColor(Color.BLUE);
        }


        return convertView;
    }


    private static class ViewHolder {
        private TextView vakantie, regio;
    }
}
