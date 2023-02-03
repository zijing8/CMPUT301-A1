package com.example.mycarfootprint;

import static java.lang.Integer.parseInt;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddStationFragment extends DialogFragment {

    interface AddStationDialogListener {
        void addStation(Station station);
    }


    private AddStationDialogListener listener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof AddStationDialogListener) {
            listener = (AddStationDialogListener) context;
        } else {
            throw new RuntimeException(context + "must implement AddStationListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_add_station, null);
        EditText editStationName = view.findViewById(R.id.edit_text_station_text);
        DatePicker editDatePlace = view.findViewById(R.id.simpleDatePicker);
        EditText editTypeName = view.findViewById(R.id.edit_text_fuel_type_text);
        EditText editAmountPlace = view.findViewById(R.id.edit_text_amount_text);
        EditText editPricePlace = view.findViewById(R.id.edit_text_price_text);

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            return builder
                    .setView(view)
                    .setTitle("Add a Station")
                    .setNegativeButton("Cancel", null)
                    .setPositiveButton("Add", (dialog, which) -> {
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
                        listener.addStation(new Station(stationName, datePlace, typeName, amountPlace, pricePlace));
                        MainActivity.getInstance().setText();
                    })
                    .create();
    }
}
