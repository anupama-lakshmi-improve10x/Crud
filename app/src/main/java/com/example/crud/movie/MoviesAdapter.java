package com.example.crud.movie;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crud.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MovieViewHolder> {
    private List<Movie> movieList;
    private MovieOnItemActionListener movieOnItemActionListener;

    public void setData(List<Movie> moviesList){
        this.movieList = moviesList;
        notifyDataSetChanged();
    }

    public void setMovieOnItemActionListener(MovieOnItemActionListener actionListener) {
        movieOnItemActionListener = actionListener;
    }


    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movies_item, parent, false);
        MovieViewHolder moviesViewHolder = new MovieViewHolder(view);
        return moviesViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movies = movieList.get(position);
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
