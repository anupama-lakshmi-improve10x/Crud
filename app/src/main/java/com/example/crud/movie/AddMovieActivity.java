package com.example.crud.movie;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.example.crud.R;
import com.example.crud.series.Series;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddMovieActivity extends BaseAddEditMovieActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Edit Movie");
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.save) {
            String movieId = movieIdTxt.getText().toString();
            String movieName = movieNameTxt.getText().toString();
            Series series = (Series) seriesSp.getSelectedItem();
            String seriesId = series.seriesId;
            String imageUrl = imageUrlTxt.getText().toString();
            String description = descriptionTxt.getText().toString();
            addMovie(movieId, movieName, seriesId, imageUrl, description);
            return true;
            } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void addMovie(String movieId, String movieName, String seriesId, String imageUrl, String description) {
        movie = new Movie();
        movie.movieId = movieId;
        movie.title = movieName;
        movie.seriesId = seriesId;
        movie.imageUrl = imageUrl;
        movie.description = description;

        Call<Movie> call = crudService.createMovie(movie);
        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                //Change the Toast Message as "Successfully added the movie"
                showToast("Successfully Loaded Movie");
                finish();
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                showToast("Failed to add movie");
            }
        });
    }
}
