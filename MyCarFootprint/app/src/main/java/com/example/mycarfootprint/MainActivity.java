package com.example.mycarfootprint;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AddStationFragment.AddStationDialogListener {

    private ArrayList<Station> dataList;
    private ListView stationList;
    private StationArrayAdapter stationAdapter;
    private TextView totalText;


    @Override
    public void addStation(Station station) {
        stationAdapter.add(station);
        stationAdapter.notifyDataSetChanged();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Station[] stations = {};

        dataList = new ArrayList<>();
        for (int i = 0; i < stations.length; i++) {
            dataList.add(new Station(stations[i].getName(), stations[i].getDate(), stations[i].getType(), stations[i].getAmount(), stations[i].getPrice()));
        }

        stationList = findViewById(R.id.station_list);
        stationAdapter = new StationArrayAdapter(this, dataList);
        stationList.setAdapter(stationAdapter);

        totalText = findViewById(R.id.total_text);


        stationList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int listItem, long l) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("do you want to remove " + dataList.get(listItem) + " from list?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dataList.remove(listItem);
                                stationAdapter.notifyDataSetChanged();
                                totalText.setText(updateTotal(dataList));
                            }
                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        }).create().show();

                return false;
            }
        });


        FloatingActionButton fab = findViewById(R.id.button_add_station);
//        fab.setOnClickListener(v -> {
//            new AddStationFragment().show(getSupportFragmentManager(), "Add City");
//            totalText.setText(updateTotal(dataList));
//        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AddStationFragment().show(getSupportFragmentManager(), "Add City");
                totalText.setText(updateTotal(dataList));
            }

        });


    }

    public String updateTotal(ArrayList<Station> dataList){
        double totalPrice = 0;
        int totalFootprint = 0;
        for (int i = 0; i < dataList.size(); i++) {
            totalPrice += dataList.get(i).getPrice();
            totalFootprint += dataList.get(i).getFootprint();
        }
        String totalString = "Total fuel cost: " + totalPrice  + " | Total carbon footprint: " + totalFootprint;
        return totalString;
    }

}