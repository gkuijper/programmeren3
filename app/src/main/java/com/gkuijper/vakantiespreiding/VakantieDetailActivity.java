package com.gkuijper.vakantiespreiding;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import static com.gkuijper.vakantiespreiding.MainActivity.VAKANTIE;

public class VakantieDetailActivity extends AppCompatActivity {
    private TextView vakantieNaam;
    private ArrayList<Tijdvak> tijdvakken;
    private ListView list;
    private TijdvakAdapter tijdvak;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vakantie_detail);

        list = (ListView) findViewById(R.id.vakantie_row_id);
        vakantieNaam = (TextView) findViewById(R.id.vakantieItem_row_id);

        Bundle bundle = getIntent().getExtras();
        VakantieItem item = (VakantieItem) bundle.get(VAKANTIE);

        tijdvakken = item.getTijdvak();
        vakantieNaam.setText(item.getName());

        tijdvak = new TijdvakAdapter(this, getLayoutInflater(), tijdvakken);
        list.setAdapter(tijdvak);
    }

}

