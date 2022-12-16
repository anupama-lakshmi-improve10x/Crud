package com.example.crud.movie;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.crud.R;
import com.example.crud.series.SeriesItem;

import java.util.List;

public class CustomSeriesItemsAdapter extends ArrayAdapter<SeriesItem> {

    public CustomSeriesItemsAdapter(@NonNull Context context, int resource, @NonNull List<SeriesItem> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.series_spinner_item, parent, false);
        SeriesItem seriesItems = getItem(position);
        TextView titleTxt = view.findViewById(R.id.title_txt);
        titleTxt.setText(seriesItems.seriesId + " _ " + seriesItems.title);
        return view;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.series_spinner_item, parent, false);
        SeriesItem seriesItem = getItem(position);
        TextView titleTxt = view.findViewById(R.id.title_txt);
        titleTxt.setText(seriesItem.seriesId + " _ " + seriesItem.title);
        return view;
    }
}
