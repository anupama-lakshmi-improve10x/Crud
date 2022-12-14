package com.example.crud.series;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.example.crud.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddSeriesActivity extends BaseAddEditSeriesActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Add Series");
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.save) {
            String id = seriesIdTxt.getText().toString();
            String title = seriesNameTxt.getText().toString();
            String image = imageUrlTxt.getText().toString();
            addSeries(id, title, image);
                return true;
            } else {
                return super.onOptionsItemSelected(item);
            }
        }

    private void addSeries(String id, String title, String imageUrl) {
        Series series = new Series();
        series.seriesId = id;
        series.title = title;
        series.imageUrl = imageUrl;

        Call<Series> call = crudService.createSeries(series);
        call.enqueue(new Callback<Series>() {
            @Override
            public void onResponse(Call<Series> call, Response<Series> response) {
                showToast("Successfully added the Series");
                finish();
            }

            @Override
            public void onFailure(Call<Series> call, Throwable t) {
                showToast("Failed to add the series");
            }
        });
    }
}