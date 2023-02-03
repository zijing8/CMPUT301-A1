package com.example.mycarfootprint;

import static java.lang.Integer.parseInt;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.content.Context;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements AddStationFragment.AddStationDialogListener {

    private ArrayList<Station> dataList;
    private ListView stationList;
    private StationArrayAdapter stationAdapter;
    private TextView totalText;
    private static MainActivity instance;


    @Override
    public void addStation(Station station) {
        stationAdapter.add(station);
        stationAdapter.notifyDataSetChanged();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        instance = this;

        Station[] stations = {};

        dataList = new ArrayList<>();
        for (int i = 0; i < stations.length; i++) {
            dataList.add(new Station(stations[i].getName(), stations[i].getDate(), stations[i].getType(), stations[i].getAmount(), stations[i].getPrice()));
        }

        stationList = findViewById(R.id.station_list);
        stationAdapter = new StationArrayAdapter(this, dataList);
        stationList.setAdapter(stationAdapter);

        totalText = findViewById(R.id.total_text);




        // Edit Station and delete station
        View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.fragment_add_station, null);
        EditText editStationName = view.findViewById(R.id.edit_text_station_text);
        DatePicker editDatePlace = view.findViewById(R.id.simpleDatePicker);
        EditText editTypeName = view.findViewById(R.id.edit_text_fuel_type_text);
        EditText editAmountPlace = view.findViewById(R.id.edit_text_amount_text);
        EditText editPricePlace = view.findViewById(R.id.edit_text_price_text);

        stationList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View eview, int position, long l) {
                if(view.getParent() != null) {
                    ((ViewGroup)view.getParent()).removeView(view);
                }
                int listIndex = position;
                new AlertDialog.Builder(MainActivity.this)
                        .setView(view)
                        .setTitle("Edit Station")
                        .setNeutralButton("Cancel", null)
                        .setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dataList.remove(position);
                                stationAdapter.notifyDataSetChanged();
                                totalText.setText(updateTotal(dataList));
                            }
                        })
                        .setPositiveButton("Edit", (dialog, which) -> {
                            String stationName = editStationName.getText().toString();
                            Date datePlace = new Date();
                            String stringDate = editDatePlace.getYear() + "/" + (editDatePlace.getMonth() + 1) + "/" + editDatePlace.getDayOfMonth();
                            try {
                                datePlace = new SimpleDateFormat("yyyy/MM/dd").parse(stringDate);
                            } catch (ParseException exception) {

                            }
                            String typeName = editTypeName.getText().toString();
                            int amountPlace = parseInt(editAmountPlace.getText().toString());
                            double pricePlace = Double.parseDouble(editPricePlace.getText().toString());
                            Station stationNew = new Station(stationName, datePlace, typeName, amountPlace, pricePlace);
                            dataList.set(listIndex, stationNew);
                            stationAdapter.notifyDataSetChanged();
                            totalText.setText(updateTotal(dataList));
                        })
                        .create().show();
                };


        });


        // Add Station Button
        FloatingActionButton fab = findViewById(R.id.button_add_station);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AddStationFragment().show(getSupportFragmentManager(), "Add Station");
            }
        });
    }

    public static MainActivity getInstance() {
        return instance;
    }
    public void setText() {
        totalText.setText(updateTotal(dataList));
    }

    // Update the total price and total carbon footprint
    public String updateTotal(ArrayList<Station> dataList){
        double totalPrice = 0;
        int totalFootprint = 0;
        for (int i = 0; i < dataList.size(); i++) {
            totalPrice += dataList.get(i).getCost();
            totalFootprint += dataList.get(i).getFootprint();
        }
        String totalString = "Total fuel cost: " + totalPrice  + " | Total carbon footprint: " + totalFootprint;
        return totalString;
    }

}