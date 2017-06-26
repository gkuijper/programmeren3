package com.gkuijper.vakantiespreiding;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements VakantieAPIConnector.VakantieAvailable, AdapterView.OnItemClickListener {
    public final String TAG = this.getClass().getSimpleName();
    private ArrayList<VakantieItem> vakanties;
    private VakantieAdapter adapter;
    private ListView list;

    public static final String VAKANTIE = "vakantie";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = (ListView) findViewById(R.id.vakantie_id);

        vakanties = new ArrayList<>();

//        Tijdvak tv1 = new Tijdvak();
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z' ");
//        try
//        {
//            tv1.setStartdate(String.valueOf(dateFormat.parse("2015-10-16T22:00:00:000Z")));
//
//        } catch (ParseException e) {
//            Log.e(TAG, e.getLocalizedMessage());
//        }
//
//        VakantieItem vak = new VakantieItem();
//        vak.setName("Voorjaarsvakantie");
//        vak.setTijdvak(new ArrayList<Tijdvak>());
//        vak.getTijdvak().add(tv1);
//        vakanties.add(vak);
        adapter = new VakantieAdapter(this, getLayoutInflater(), vakanties);
        list.setAdapter(adapter);
        getVakantieItems();
        list.setOnItemClickListener(this);

    }

    public void getVakantieItems() {
        String[] URL = {
                "https://opendata.rijksoverheid.nl/v1/sources/rijksoverheid/infotypes/schoolholidays/schoolyear/2016-2017?output=json"
        };

        new VakantieAPIConnector(this).execute(URL);
    }

    @Override
    public void VakantieAvailable(VakantieItem vakantie) {
        Log.i("", "VakantieAvailable: " + vakantie);
        vakanties.add(vakantie);
        Log.i("", "VakantieAvailable: " + vakanties);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        VakantieItem v = vakanties.get(position);
        Intent i = new Intent(getApplicationContext(), VakantieDetailActivity.class);
        i.putExtra(VAKANTIE, v);
        startActivity(i);
    }
}

