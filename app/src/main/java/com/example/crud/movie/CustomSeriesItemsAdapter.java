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
import com.example.crud.databinding.MovieItemBinding;
import com.example.crud.databinding.SeriesSpinnerItemBinding;
import com.example.crud.series.SeriesItem;

import java.util.List;

public class CustomSeriesItemsAdapter extends ArrayAdapter<SeriesItem> {

    private SeriesSpinnerItemBinding binding;

    public CustomSeriesItemsAdapter(@NonNull Context context, int resource, @NonNull List<SeriesItem> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        binding = SeriesSpinnerItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        SeriesItem seriesItems = getItem(position);
        binding.titleTxt.setText(seriesItems.seriesId + " _ " + seriesItems.title);
        return binding.getRoot();
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        SeriesSpinnerItemBinding binding = SeriesSpinnerItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        SeriesItem seriesItems = getItem(position);
        binding.titleTxt.setText(seriesItems.seriesId + " _ " + seriesItems.title);
        return binding.getRoot();
    }
}
