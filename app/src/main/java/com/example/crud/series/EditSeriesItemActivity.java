package com.example.crud.series;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.example.crud.Constants;
import com.example.crud.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditSeriesItemActivity extends BaseAddEditSeriesItemsActivity {

    private SeriesItem seriesItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Edit Series");
        //Todo: change constants to KEY_SERIES_ITEM
        if (getIntent().hasExtra(Constants.KEY_SERIES_ITEMS)) {
            seriesItem = (SeriesItem) getIntent().getSerializableExtra(Constants.KEY_SERIES_ITEMS);
            showData();
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.save) {
            String seriesId = seriesIdTxt.getText().toString();
            String title = seriesNameTxt.getText().toString();
            String imageUrl = imageUrlTxt.getText().toString();
            updateSeries(seriesItem.id, seriesId, title, imageUrl);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void showData() {
        seriesIdTxt.setText(seriesItem.seriesId);
        seriesNameTxt.setText(seriesItem.title);
        imageUrlTxt.setText(seriesItem.imageUrl);
    }
//Todo: Change updateSeries method to updateSeriesItem
    private void updateSeries(String id, String seriesId, String title, String imageUrl) {
        SeriesItem seriesItem = new SeriesItem(seriesId, title, imageUrl);
        Call<Void> call = crudService.updateSeriesItem(id, seriesItem);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                showToast("Successfully updated series");
                finish();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                showToast("Failed to update series");
            }
        });
    }
}
