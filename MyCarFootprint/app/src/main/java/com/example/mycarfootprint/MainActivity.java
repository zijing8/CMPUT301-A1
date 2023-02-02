package com.example.mycarfootprint;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AddStationFragment.AddStationDialogListener {

    private ArrayList<Station> dataList;
    private ListView stationList;
    private StationArrayAdapter stationAdapter;

    @Override
    public void addStation(Station station) {
        stationAdapter.add(station);
        stationAdapter.notifyDataSetChanged();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Station[] stations = {

        };

        dataList = new ArrayList<>();
        for (int i = 0; i < stations.length; i++){
            dataList.add(new Station(stations[i].getName(), stations[i].getDate(), stations[i].getType(), stations[i].getAmount(), stations[i].getPrice()));
        }

        stationList = findViewById(R.id.station_list);
        stationAdapter = new StationArrayAdapter(this, dataList);
        stationList.setAdapter(stationAdapter);

        FloatingActionButton fab = findViewById(R.id.button_add_station);
        fab.setOnClickListener(v -> {
            new AddStationFragment().show(getSupportFragmentManager(), "Add City");
        });
    }
}