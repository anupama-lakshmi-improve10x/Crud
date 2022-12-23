package com.example.crud.movie;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crud.R;
import com.example.crud.databinding.MovieItemBinding;

public class MovieViewHolder extends RecyclerView.ViewHolder {

    MovieItemBinding binding;

    public MovieViewHolder(MovieItemBinding movieItemBinding) {
        super(movieItemBinding.getRoot());
        binding = movieItemBinding;
    }
}
