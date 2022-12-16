package com.example.crud.series;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.example.crud.Constants;
import com.example.crud.R;
import com.example.crud.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SeriesItemsActivity extends BaseActivity {

    private ArrayList<SeriesItem> seriesItems = new ArrayList<>();
    private RecyclerView seriesItemsRv;
    private SeriesItemsAdapter seriesItemsAdapter;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Todo: Change id to activity_series_item
        setContentView(R.layout.activity_series);
        log("onCreate");
        getSupportActionBar().setTitle("Series");
        progressBar = findViewById(R.id.series_progress_bar);
        setupSeriesItemsAdapter();
        setupSeriesItemsRv();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //Todo create onResume in base activity
        log("onResume");
        fetchSeriesList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Todo: change id to series_item_menu
        getMenuInflater().inflate(R.menu.series_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.add) {
            Intent intent = new Intent(this, AddSeriesItemActivity.class);
            startActivity(intent);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
//Todo: change methodName to fetchSeriesItems
    private void fetchSeriesList() {
        showProgressBar();
        Call<List<SeriesItem>> call = crudService.fetchSeriesItems();
        call.enqueue(new Callback<List<SeriesItem>>() {
            @Override
            public void onResponse(Call<List<SeriesItem>> call, Response<List<SeriesItem>> response) {
                hideProgressBar();
                showToast("Successfully loaded Series");
                List<SeriesItem> seriesItems = response.body();
                seriesItemsAdapter.setData(seriesItems);
            }

            @Override
            public void onFailure(Call<List<SeriesItem>> call, Throwable t) {
                hideProgressBar();
                showToast("Failed to load series");
            }
        });
    }

    private void setupSeriesItemsAdapter() {
        seriesItemsAdapter = new SeriesItemsAdapter();
        seriesItemsAdapter.setData(seriesItems);
        seriesItemsAdapter.setSeriesOnItemActionListener(new SeriesOnItemActionListener() {

            @Override
            public void onDelete(String id) {
                deleteSeries(id);
            }

            @Override
            public void onEdit(SeriesItem seriesItem) {
                editSeries(seriesItem);
            }
        });
    }

    public void setupSeriesItemsRv() {
        //Todo: Change id to series_items_rv
        seriesItemsRv = findViewById(R.id.series_rv);
        seriesItemsRv.setLayoutManager(new LinearLayoutManager(this));
        seriesItemsRv.setAdapter(seriesItemsAdapter);
    }

    private void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }
//Todo: Change deleteSeries method to deleteSeriesItem
    private void deleteSeries(String id) {
        Call<Void> call = crudService.deleteSeriesItem(id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                showToast("Successfully deleted Series");
                fetchSeriesList();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                showToast("Failed to delete series");
            }
        });
    }
//Todo: change editSeries to editSeriesItem
    private void editSeries(SeriesItem seriesItem) {
        Intent intent = new Intent(this, EditSeriesItemActivity.class);
        intent.putExtra(Constants.KEY_SERIES_ITEMS, seriesItem);
        startActivity(intent);
    }
}