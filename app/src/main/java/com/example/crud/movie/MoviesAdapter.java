package com.example.crud.movie;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crud.R;
import com.example.crud.databinding.MovieItemBinding;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MovieViewHolder> {

    private List<Movie> movies;
    private MovieOnItemActionListener movieOnItemActionListener;

    void setData(List<Movie> movies) {
        this.movies = movies;
        notifyDataSetChanged();
    }

    void setMovieOnItemActionListener(MovieOnItemActionListener movieOnItemActionListener) {
        this.movieOnItemActionListener = movieOnItemActionListener;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MovieItemBinding binding = MovieItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        MovieViewHolder moviesViewHolder = new MovieViewHolder(binding);
        return moviesViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = movies.get(position);
        holder.binding.setMovie(movie);
        holder.binding.deleteImgBtn.setOnClickListener(view -> {
            movieOnItemActionListener.onDelete(movie.id);
        });
        holder.binding.getRoot().setOnClickListener(view -> {
            movieOnItemActionListener.onEdit(movie);
        });
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }
}
