package com.example.crud.movie;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crud.R;

import java.util.ArrayList;

public class MoviesViewHolder extends RecyclerView.ViewHolder {
    ImageView moviesImg;
    TextView moviesTxt;
    public ImageButton deleteBtn;

    public MoviesViewHolder(@NonNull View itemView) {
        super(itemView);
        moviesImg = itemView.findViewById(R.id.movies_img);
        moviesTxt = itemView.findViewById(R.id.movies_txt);
        deleteBtn = itemView.findViewById(R.id.delete_btn);
    }
}
