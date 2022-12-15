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

//Todo: Change movieList to movies
    private List<Movie> movieList;
    private MovieOnItemActionListener movieOnItemActionListener;

    public void setData(List<Movie> movieList){
        this.movieList = movieList;
        notifyDataSetChanged();
    }

    public void setMovieOnItemActionListener(MovieOnItemActionListener actionListener) {
        movieOnItemActionListener = actionListener;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false);
        MovieViewHolder moviesViewHolder = new MovieViewHolder(view);
        return moviesViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = movieList.get(position);
        if(movie.imageUrl != null && movie.imageUrl.isEmpty() == false) {
            Picasso.get().load(movie.imageUrl).into(holder.moviesImg);
        }
        holder.moviesTxt.setText(movie.title);
        holder.deleteBtn.setOnClickListener(view -> {
            movieOnItemActionListener.onDelete(movie.id);
        });
        holder.itemView.setOnClickListener(view -> {
            movieOnItemActionListener.onEdit(movie);
        });
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }
}
