package com.example.crud.movie;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.crud.Constants;
import com.example.crud.Movies;
import com.example.crud.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviesActivity extends AppCompatActivity {
    public ArrayList<Movies> movies = new ArrayList<>();
    public RecyclerView moviesRv;
    public MoviesAdapter moviesAdapter;
    public ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);
        getSupportActionBar().setTitle("Movies");
        setupMoviesRv();
    }

    @Override
    protected void onResume() {
        super.onResume();
        fetchData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.movies_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.add) {
            Intent intent = new Intent(this, AddEditMovieActivity.class);
            startActivity(intent);
            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    public void fetchData() {
        showVisible();
        MoviesApi moviesApi = new MoviesApi();
        MoviesService moviesService = moviesApi.createMoviesService();
        Call<List<Movies>> call = moviesService.fetchMovies();
        call.enqueue(new Callback<List<Movies>>() {
            @Override
            public void onResponse(Call<List<Movies>> call, Response<List<Movies>> response) {
                hideVisible();
                List<Movies> movies = response.body();
                moviesAdapter.setData(movies);
            }

            @Override
            public void onFailure(Call<List<Movies>> call, Throwable t) {
                hideVisible();
                Toast.makeText(MoviesActivity.this, "Failed to load data", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void setupMoviesRv() {
        moviesRv = findViewById(R.id.movies_rv);
        progressBar = findViewById(R.id.movie_progress_bar);
        moviesRv.setLayoutManager(new GridLayoutManager(this,2));
        moviesAdapter = new MoviesAdapter();
        moviesAdapter.setData(movies);
        moviesAdapter.setMovieOnItemActionListener(new MovieOnItemActionListener() {
            @Override
            public void onDelete(String id) {
                deleteMovie(id);
                fetchData();
            }

            @Override
            public void onEdit(Movies movies) {
                editMovies(movies);
            }
        });
        moviesRv.setAdapter(moviesAdapter);
    }

    public void showVisible() {
        progressBar.setVisibility(View.VISIBLE);
    }

    public  void hideVisible(){
        progressBar.setVisibility(View.GONE);
    }

    public void deleteMovie(String id) {
        MoviesApi moviesApi = new MoviesApi();
        MoviesService moviesService = moviesApi.createMoviesService();
        Call<Void> call = moviesService.deleteMovie(id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                fetchData();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(MoviesActivity.this, "Failed to delete Movie", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void editMovies(Movies movies) {
        Intent intent = new Intent(this, AddEditMovieActivity.class);
        intent.putExtra(Constants.KEY_MOVIES, movies);
        startActivity(intent);
    }
}