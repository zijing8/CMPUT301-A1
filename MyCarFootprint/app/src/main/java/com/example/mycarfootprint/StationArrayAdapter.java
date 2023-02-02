package com.example.mycarfootprint;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class StationArrayAdapter extends ArrayAdapter<Station> {

    public StationArrayAdapter(Context context, ArrayList<Station> stations) {
        super(context, 0, stations);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @Nullable ViewGroup parent) {
        View view;
        if (convertView == null) {
            view = LayoutInflater.from(super.getContext()).inflate(R.layout.content, parent, false);
        } else {
            view = convertView;
        }
        Station station = super.getItem(position);
        TextView stationName = view.findViewById(R.id.station_text);
        TextView datePlace = view.findViewById(R.id.date_text);
        TextView fuelType = view.findViewById(R.id.fuel_type_text);
        TextView amountPlace = view.findViewById(R.id.amount_text);
        TextView pricePlace = view.findViewById(R.id.price_text);
        TextView costPlace = view.findViewById(R.id.cost_text);
        TextView footprintPlace = view.findViewById(R.id.footprint_text);

        stationName.setText(station.getName());
        datePlace.setText(station.getDate().toString());
        fuelType.setText(station.getType());
        amountPlace.setText(String.valueOf(station.getAmount())+"L");
        pricePlace.setText("$"+Double.toString(station.getPrice()));
        costPlace.setText("$"+Double.toString((station.getCost())));
        footprintPlace.setText(String.valueOf(station.getFootprint())+"kg");

        return view;
    }
}
