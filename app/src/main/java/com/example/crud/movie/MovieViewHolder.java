package com.example.crud.movie;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crud.R;

public class MovieViewHolder extends RecyclerView.ViewHolder {

    ImageView movieImg;
    TextView nameTxt;
    ImageButton deleteBtn;

    public MovieViewHolder(@NonNull View itemView) {
        super(itemView);
        movieImg = itemView.findViewById(R.id.movie_img);
        nameTxt = itemView.findViewById(R.id.name_txt);
        deleteBtn = itemView.findViewById(R.id.delete_btn);
    }
}
