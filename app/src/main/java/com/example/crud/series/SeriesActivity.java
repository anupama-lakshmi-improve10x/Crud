package com.example.crud.series;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.crud.Constants;
import com.example.crud.R;
import com.example.crud.message.AddEditMessageActivity;
import com.example.crud.message.Message;
import com.example.crud.template.OnItemActionListener;
import com.example.crud.template.Template;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SeriesActivity extends AppCompatActivity {
    public ArrayList<Series> series = new ArrayList<>();
    public RecyclerView seriesRv;
    public SeriesAdapter seriesAdapter;
    public ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_series);
        getSupportActionBar().setTitle("Series");
        progressBar = findViewById(R.id.series_progress_bar);
        setupSeriesRv();
    }

    @Override
    protected void onResume() {
        super.onResume();
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
            Intent intent = new Intent(this, AddEditSeriesActivity.class);
            startActivity(intent);
            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
            return true;
        } else{
            return super.onOptionsItemSelected(item);
        }
    }

    public void fetchData() {
        showVisible();
        SeriesApi seriesApi = new SeriesApi();
        SeriesService seriesService = seriesApi.createSeriesService();
        Call<List<Series>> call = seriesService.fetchSeries();
        call.enqueue(new Callback<List<Series>>() {
            @Override
            public void onResponse(Call<List<Series>> call, Response<List<Series>> response) {
                hideVisible();
                List<Series> series = response.body();
                seriesAdapter.setData(series);
            }

            @Override
            public void onFailure(Call<List<Series>> call, Throwable t) {
                hideVisible();
                Toast.makeText(SeriesActivity.this, " Failed to load data ", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void setupSeriesRv() {
        seriesRv = findViewById(R.id.series_rv);
        seriesRv.setLayoutManager(new LinearLayoutManager(this));
        seriesAdapter = new SeriesAdapter();
        seriesAdapter.setData(series);
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

    public void showVisible() {

        progressBar.setVisibility(View.VISIBLE);
    }

    public  void hideVisible(){

        progressBar.setVisibility(View.GONE);
    }

    public void deleteSeries(String id) {
        SeriesApi seriesApi = new SeriesApi();
        SeriesService seriesService = seriesApi.createSeriesService();
        Call<Void> call = seriesService.deleteSeries(id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(SeriesActivity.this, "Successfully deleted message", Toast.LENGTH_SHORT).show();
                fetchData();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(SeriesActivity.this, "Failed to delete message", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void editSeries(Series series) {
        Intent intent = new Intent(this, AddEditSeriesActivity.class);
        intent.putExtra(Constants.KEY_SERIES, series);
        startActivity(intent);
    }
}