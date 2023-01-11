package com.example.crud.series;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crud.R;
import com.example.crud.databinding.SeriesItemBinding;

public class SeriesItemViewHolder extends RecyclerView.ViewHolder {

    SeriesItemBinding binding;

    public SeriesItemViewHolder(SeriesItemBinding seriesItemBinding) {
        super(seriesItemBinding.getRoot());
        binding = seriesItemBinding;
    }
}
