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
import com.example.crud.api.CrudApi;
import com.example.crud.api.CrudService;
import com.example.crud.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SeriesListActivity extends BaseActivity {

    private ArrayList<Series> seriesList = new ArrayList<>();
    private RecyclerView seriesRv;
    private SeriesAdapter seriesAdapter;
    private ProgressBar progressBar;
    private CrudService crudService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_series);
        log("onCreate");
        getSupportActionBar().setTitle("Series");
        progressBar = findViewById(R.id.series_progress_bar);
        setupSeriesRv();
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
        fetchData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.series_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.add) {
            Intent intent = new Intent(this, AddSeriesActivity.class);
            startActivity(intent);
            return true;
        } else{
            return super.onOptionsItemSelected(item);
        }
    }
//Change fetch-data Method to "fetchSeriesList"
    private void fetchData() {
        showVisible();
        Call<List<Series>> call = crudService.fetchSeries();
        call.enqueue(new Callback<List<Series>>() {
            @Override
            public void onResponse(Call<List<Series>> call, Response<List<Series>> response) {
                hideVisible();
                // Display Toast Message
                List<Series> series = response.body();
                seriesAdapter.setData(series);
            }

            @Override
            public void onFailure(Call<List<Series>> call, Throwable t) {
                hideVisible();
                showToast("Failed to load series");
            }
        });
    }
//Do we need change the method setupSeriesListRV.
    public void setupSeriesRv() {
        seriesRv = findViewById(R.id.series_rv);
        seriesRv.setLayoutManager(new LinearLayoutManager(this));
        seriesAdapter = new SeriesAdapter();
        seriesAdapter.setData(seriesList);
        seriesAdapter.setSeriesOnItemActionListener(new SeriesOnItemActionListener() {

            @Override
            public void onDelete(String id) {
                deleteSeries(id);
            }

            @Override
            public void onEdit(Series series) {
                editSeries(series);
            }
        });
        seriesRv.setAdapter(seriesAdapter);
    }

    private void showVisible() {
        progressBar.setVisibility(View.VISIBLE);
    }

    private void hideVisible(){
        progressBar.setVisibility(View.GONE);
    }

    private void deleteSeries(String id) {
        Call<Void> call = crudService.deleteSeries(id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                showToast("Successfully deleted Series");
                fetchData();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                showToast("Failed to delete series");
            }
        });
    }

    private void editSeries(Series series) {
        Intent intent = new Intent(this, EditSeriesActivity.class);
        intent.putExtra(Constants.KEY_SERIES, series);
        startActivity(intent);
    }
}