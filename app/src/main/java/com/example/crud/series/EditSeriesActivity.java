package com.example.crud.series;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.example.crud.Constants;
import com.example.crud.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditSeriesActivity extends BaseAddEditSeriesActivity{
    private Series series;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Edit Series");
        if (getIntent().hasExtra(Constants.KEY_SERIES)) {
            getSupportActionBar().setTitle("Edit Series");
            this.series = (Series) getIntent().getSerializableExtra(Constants.KEY_SERIES);
            showData();
        }
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.save) {
            String id = seriesId.getText().toString();
            String title = seriesName.getText().toString();
            String image = imageUrl.getText().toString();
            upDateSeries(id, title, image);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void showData() {
        seriesId.setText(series.id);
        seriesName.setText(series.title);
        imageUrl.setText(series.imageUrl);
    }

    private void upDateSeries(String id, String title, String imageUrl) {
        series = new Series();
        series.seriesId = id;
        series.title = title;
        series.imageUrl = imageUrl;

        Call<Void> call = crudService.updateSeries(id,series);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                showToast("Successfully edited series");
                finish();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                showToast("Failed to edit");
            }
        });
    }
}
