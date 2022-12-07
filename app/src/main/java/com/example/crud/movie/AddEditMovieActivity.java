package com.example.crud.movie;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.crud.Constants;
import com.example.crud.R;
import com.example.crud.series.Series;
import com.example.crud.series.SeriesApi;
import com.example.crud.series.SeriesService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddEditMovieActivity extends AppCompatActivity {
    private EditText moviesIdTxt;
    private EditText movieNameTxt;
    private Spinner seriesSp;
    private EditText imageUrlTxt;
    private EditText descriptionTxt;
    private CustomSeriesAdapter customSeriesAdapter;
    private ArrayList<Series> seriesList = new ArrayList<>();
    private Movies movies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_movie);
        initViews();
        setupSeriesListSp();
        fetchSeriesList();
        if(getIntent().hasExtra(Constants.KEY_MOVIES)) {
            getSupportActionBar().setTitle("Add Movie");
            movies = (Movies) getIntent().getSerializableExtra(Constants.KEY_MOVIES);
            showData();
        } else{
            getSupportActionBar().setTitle("Edit Movie");
        }
    }

    private void showData() {
        moviesIdTxt.setText(movies.movieId);
        movieNameTxt.setText(movies.title);
        imageUrlTxt.setText(movies.imageUrl);
        descriptionTxt.setText(movies.description);
        for(int i = 0; i < customSeriesAdapter.getCount(); i++) {
            Series series = customSeriesAdapter.getItem(i);
            if(movies.seriesId.equals(series.seriesId)) {
                seriesSp.setSelection(i);
            }
        }
    }

    private void setupSeriesListSp() {
        customSeriesAdapter = new CustomSeriesAdapter(this, android.R.layout.simple_list_item_1, seriesList);
        seriesSp.setAdapter(customSeriesAdapter);
    }

    private void initViews() {
        moviesIdTxt = findViewById(R.id.movie_id_txt);
        movieNameTxt = findViewById(R.id.movie_name_txt);
        seriesSp = findViewById(R.id.series_sp);
        imageUrlTxt = findViewById(R.id.image_url_txt);
        descriptionTxt = findViewById(R.id.description_txt);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_edit_movie_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.save) {
            Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT).show();
            String movieId = moviesIdTxt.getText().toString();
            String movieName = movieNameTxt.getText().toString();
            Series series = (Series) seriesSp.getSelectedItem();
            String seriesId = series.seriesId;
            String imageUrl = imageUrlTxt.getText().toString();
            String description = descriptionTxt.getText().toString();
            if(movies == null) {
                addMovie(movieId, movieName, seriesId, imageUrl, description);
            } else {
                upDateMovies(movies.id, movieId, movieName, seriesId, imageUrl, description);
            }
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void fetchSeriesList() {
        SeriesApi seriesApi = new SeriesApi();
        SeriesService seriesService = seriesApi.createSeriesService();
        Call<List<Series>> call = seriesService.fetchSeries();
        call.enqueue(new Callback<List<Series>>() {
            @Override
            public void onResponse(Call<List<Series>> call, Response<List<Series>> response) {
                List<Series> seriesList = response.body();
                customSeriesAdapter.addAll(seriesList);
                if (movies != null){
                    showData();
                }
            }

            @Override
            public void onFailure(Call<List<Series>> call, Throwable t) {
            }
        });
    }

    private void addMovie(String movieId, String movieName, String seriesId, String imageUrl, String description) {
        movies = new Movies();
        movies.movieId = movieId;
        movies.title = movieName;
        movies.seriesId = seriesId;
        movies.imageUrl = imageUrl;
        movies.description = description;

        MoviesApi moviesApi = new MoviesApi();
        MoviesService moviesService = moviesApi.createMoviesService();
        Call<Movies> call = moviesService.createMovie(movies);
        call.enqueue(new Callback<Movies>() {
            @Override
            public void onResponse(Call<Movies> call, Response<Movies> response) {
                Toast.makeText(AddEditMovieActivity.this, "Successfully Loaded Movie", Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<Movies> call, Throwable t) {
                Toast.makeText(AddEditMovieActivity.this, "Failed to add movie", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void upDateMovies(String id, String movieId, String movieName, String seriesId, String imageUrl, String description) {
        movies = new Movies();
        movies.movieId = movieId;
        movies.title = movieName;
        movies.seriesId = seriesId;
        movies.imageUrl = imageUrl;
        movies.description = description;

        MoviesApi moviesApi = new MoviesApi();
        MoviesService moviesService = moviesApi.createMoviesService();
        Call<Void> call = moviesService.updateMovie(id, movies);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                finish();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(AddEditMovieActivity.this, "Failed to edit", Toast.LENGTH_SHORT).show();
            }
        });
    }
}