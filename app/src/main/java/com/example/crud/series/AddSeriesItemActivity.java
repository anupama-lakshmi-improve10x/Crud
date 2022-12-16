package com.example.crud.series;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.example.crud.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddSeriesItemActivity extends BaseAddEditSeriesItemsActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Add Series");
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.save) {
            String seriesId = seriesIdTxt.getText().toString();
            String title = seriesNameTxt.getText().toString();
            String image = imageUrlTxt.getText().toString();
            addSeries(seriesId, title, image);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void addSeries(String seriesId, String title, String imageUrl) {
        SeriesItems seriesItems = new SeriesItems(seriesId, title, imageUrl);
        Call<SeriesItems> call = crudService.createSeriesItem(seriesItems);
        call.enqueue(new Callback<SeriesItems>() {
            @Override
            public void onResponse(Call<SeriesItems> call, Response<SeriesItems> response) {
                showToast("Successfully added the Series");
                finish();
            }

            @Override
            public void onFailure(Call<SeriesItems> call, Throwable t) {
                showToast("Failed to add the series");
            }
        });
    }
}