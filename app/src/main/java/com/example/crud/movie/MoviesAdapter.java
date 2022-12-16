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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false);
        MovieViewHolder moviesViewHolder = new MovieViewHolder(view);
        return moviesViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = movies.get(position);
        if (movie.imageUrl != null && movie.imageUrl.isEmpty() == false) {
            Picasso.get().load(movie.imageUrl).into(holder.movieImg);
        }
        holder.nameTxt.setText(movie.title);
        holder.deleteBtn.setOnClickListener(view -> {
            movieOnItemActionListener.onDelete(movie.id);
        });
        holder.itemView.setOnClickListener(view -> {
            movieOnItemActionListener.onEdit(movie);
        });
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }
}
