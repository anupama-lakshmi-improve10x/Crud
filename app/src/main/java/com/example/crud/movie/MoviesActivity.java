package com.example.crud.movie;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.example.crud.Constants;
import com.example.crud.R;
import com.example.crud.api.CrudApi;
import com.example.crud.api.CrudService;
import com.example.crud.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviesActivity extends BaseActivity {

    private ArrayList<Movie> movies = new ArrayList<>();
    private RecyclerView moviesRv;
    private MoviesAdapter moviesAdapter;
    private ProgressBar progressBar;
    private CrudService crudService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);
        log("onCreate");
        getSupportActionBar().setTitle("Movies");
        setupMoviesRv();
        setupApiService();
    }

    private void setupApiService() {
        CrudApi crudApi = new CrudApi();
        crudService = crudApi.createCrudService();
    }

    @Override
    protected void onResume() {
        super.onResume();
        log("onResume");
        // change fetchData method to "fetchMovies"
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
            Intent intent = new Intent(this, AddMovieActivity.class);
            startActivity(intent);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void fetchData() {
        //Change showVisible method name to showProgressBarVisible
        showVisible();
        Call<List<Movie>> call = crudService.fetchMovies();
        call.enqueue(new Callback<List<Movie>>() {
            @Override
            public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
                hideVisible();
                // Add toast message
                List<Movie> movies = response.body();
                moviesAdapter.setData(movies);
            }

            @Override
            public void onFailure(Call<List<Movie>> call, Throwable t) {
                //Change hideVisible method name to showProgressBarVisible
                hideVisible();
                showToast("Failed to load Movies");
            }
        });
    }

    private void setupMoviesRv() {
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
            public void onEdit(Movie movie) {
                editMovie(movie);
            }
        });
        moviesRv.setAdapter(moviesAdapter);
    }
// change the method name
    private void showVisible() {
        progressBar.setVisibility(View.VISIBLE);
    }
//change the method name
    private  void hideVisible(){
        progressBar.setVisibility(View.GONE);
    }

    private void deleteMovie(String id) {
        Call<Void> call = crudService.deleteMovie(id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                fetchData();
                // Display Toast message
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
              showToast("Failed to delete Movie");
            }
        });
    }

    private void editMovie(Movie movie) {
        Intent intent = new Intent(this, EditMovieActivity.class);
        intent.putExtra(Constants.KEY_MOVIES, movie);
        startActivity(intent);
    }
}