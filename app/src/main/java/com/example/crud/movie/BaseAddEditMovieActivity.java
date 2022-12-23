package com.example.crud.movie;

import android.os.Bundle;
import android.view.Menu;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.crud.R;
import com.example.crud.base.BaseActivity;
import com.example.crud.databinding.ActivityBaseAddEditMovieBinding;
import com.example.crud.series.SeriesItem;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BaseAddEditMovieActivity extends BaseActivity {

    protected ActivityBaseAddEditMovieBinding binding;
    protected CustomSeriesItemsAdapter customSeriesItemsAdapter;
    private ArrayList<SeriesItem> seriesItems = new ArrayList<>();
    protected Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBaseAddEditMovieBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setupSeriesItemsSp();
        fetchSeriesItems();
    }

    private void setupSeriesItemsSp() {
        customSeriesItemsAdapter = new CustomSeriesItemsAdapter(this, android.R.layout.simple_list_item_1, seriesItems);
        binding.seriesItemsSp.setAdapter(customSeriesItemsAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.base_add_edit_movie_menu, menu);
        return true;
    }

    private void fetchSeriesItems() {
        Call<List<SeriesItem>> call = crudService.fetchSeriesItems();
        call.enqueue(new Callback<List<SeriesItem>>() {
            @Override
            public void onResponse(Call<List<SeriesItem>> call, Response<List<SeriesItem>> response) {
                List<SeriesItem> seriesList = response.body();
                customSeriesItemsAdapter.addAll(seriesList);
                if (movie != null) {
                    showData();
                }
            }

            @Override
            public void onFailure(Call<List<SeriesItem>> call, Throwable t) {
            }
        });
    }

    protected void showData() {
        binding.movieIdTxt.setText(movie.movieId);
        binding.movieNameTxt.setText(movie.title);
        binding.imageUrlTxt.setText(movie.imageUrl);
        binding.descriptionTxt.setText(movie.description);
        for (int i = 0; i < customSeriesItemsAdapter.getCount(); i++) {
            SeriesItem seriesItem = customSeriesItemsAdapter.getItem(i);
            if (movie.seriesId.equals(seriesItem.seriesId)) {
                binding.seriesItemsSp.setSelection(i);
            }
        }
    }
}