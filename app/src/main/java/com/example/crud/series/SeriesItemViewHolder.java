package com.example.crud.series;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crud.R;

public class SeriesItemViewHolder extends RecyclerView.ViewHolder {

    ImageView seriesImg;
    TextView seriesTxt;
    ImageButton deleteBtn;

    public SeriesItemViewHolder(@NonNull View itemView) {
        super(itemView);
        seriesImg = itemView.findViewById(R.id.series_img);
        seriesTxt = itemView.findViewById(R.id.series_txt);
        deleteBtn = itemView.findViewById(R.id.delete_btn);
    }
}
