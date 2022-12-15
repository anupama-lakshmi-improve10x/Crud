package com.example.crud.movie;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.example.crud.R;
import com.example.crud.series.Series;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddMovieActivity extends BaseAddEditMovieActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Add Movie");
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
            addMovie(movieId, seriesId, movieName, imageUrl, description);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void addMovie(String movieId, String seriesId, String movieName, String imageUrl, String description) {
        Movie movie = new Movie(movieId, seriesId, movieName, imageUrl, description);

        Call<Movie> call = crudService.createMovie(movie);
        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                showToast("Successfully added the Movie");
                finish();
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                showToast("Failed to add movie");
            }
        });
    }
}
