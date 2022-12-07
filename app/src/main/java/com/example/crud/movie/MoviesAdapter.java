package com.example.crud.movie;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crud.Movies;
import com.example.crud.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesViewHolder> {
    public List<Movies> movieList;
    public MovieOnItemActionListener movieOnItemActionListener;

    public void setData(List<Movies> moviesList){
        this.movieList = moviesList;
        notifyDataSetChanged();
    }

    public void setMovieOnItemActionListener(MovieOnItemActionListener actionListener) {
        movieOnItemActionListener = actionListener;
    }


    @NonNull
    @Override
    public MoviesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movies_item, parent, false);
        MoviesViewHolder moviesViewHolder = new MoviesViewHolder(view);
        return moviesViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesViewHolder holder, int position) {
        Movies movies = movieList.get(position);
        Picasso.get().load(movies.imageUrl).into(holder.moviesImg);
        holder.moviesTxt.setText(movies.title);
        holder.deleteBtn.setOnClickListener(view -> {
            movieOnItemActionListener.onDelete(movies.id);
        });
        holder.itemView.setOnClickListener(view -> {
            movieOnItemActionListener.onEdit(movies);
        });
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }
}
