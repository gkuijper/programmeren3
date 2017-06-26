package com.gkuijper.vakantiespreiding;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Gabrielle on 24-06-17.
 */

public class TijdvakAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private ArrayList<Tijdvak> currentList;

    public TijdvakAdapter(Context context, LayoutInflater layoutInflater, ArrayList<Tijdvak> tijdvakArrayList) {
        this.context = context;
        this.layoutInflater = layoutInflater;
        this.currentList = tijdvakArrayList;


    }

    @Override
    public int getCount() {
        return currentList.size();
    }

    @Override
    public Tijdvak getItem(int position) {
        return this.currentList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final TijdvakAdapter.ViewHolder viewHolder;

        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.layout_detail_row, null);

            viewHolder = new TijdvakAdapter.ViewHolder();
            viewHolder.vakantietijd = (TextView) convertView.findViewById(R.id.vakanties_row_id);
            viewHolder.plaats = (TextView) convertView.findViewById(R.id.regio_row_id);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (TijdvakAdapter.ViewHolder) convertView.getTag();
        }
        final Tijdvak tijdvak = currentList.get(position);

        viewHolder.vakantietijd.setText(tijdvak.getRegion());
        viewHolder.plaats.setText("Van " + tijdvak.getStartdate() + " tot " + tijdvak.getEnddate());


        return convertView;
    }


    private static class ViewHolder {
        private TextView vakantietijd, plaats;
    }
}
