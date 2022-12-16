package com.example.crud.movie;

import android.os.Bundle;
import android.view.Menu;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.crud.R;
import com.example.crud.base.BaseActivity;
import com.example.crud.series.SeriesItems;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BaseAddEditMovieActivity extends BaseActivity {

    protected EditText movieIdTxt;
    protected EditText movieNameTxt;
    protected Spinner seriesSp;
    protected EditText imageUrlTxt;
    protected EditText descriptionTxt;
    protected CustomSeriesItemsAdapter customSeriesItemsAdapter;
    private ArrayList<SeriesItems> seriesList = new ArrayList<>();
    protected Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_movie);
        initViews();
        setupSeriesItemsSp();
        fetchSeriesItems();
    }

    private void setupSeriesItemsSp() {
        customSeriesItemsAdapter = new CustomSeriesItemsAdapter(this, android.R.layout.simple_list_item_1, seriesList);
        seriesSp.setAdapter(customSeriesItemsAdapter);
    }

    private void initViews() {
        movieIdTxt = findViewById(R.id.movie_id_txt);
        movieNameTxt = findViewById(R.id.movie_name_txt);
        seriesSp = findViewById(R.id.series_sp);
        imageUrlTxt = findViewById(R.id.image_url_txt);
        descriptionTxt = findViewById(R.id.description_txt);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_edit_movie_menu, menu);
        return true;
    }

    private void fetchSeriesItems() {
        Call<List<SeriesItems>> call = crudService.fetchSeriesItems();
        call.enqueue(new Callback<List<SeriesItems>>() {
            @Override
            public void onResponse(Call<List<SeriesItems>> call, Response<List<SeriesItems>> response) {
                List<SeriesItems> seriesList = response.body();
                customSeriesItemsAdapter.addAll(seriesList);
                if (movie != null) {
                    showData();
                }
            }

            @Override
            public void onFailure(Call<List<SeriesItems>> call, Throwable t) {
            }
        });
    }

    protected void showData() {
        movieIdTxt.setText(movie.movieId);
        movieNameTxt.setText(movie.title);
        imageUrlTxt.setText(movie.imageUrl);
        descriptionTxt.setText(movie.description);
        for (int i = 0; i < customSeriesItemsAdapter.getCount(); i++) {
            SeriesItems seriesItems = customSeriesItemsAdapter.getItem(i);
            if (movie.seriesId.equals(seriesItems.seriesId)) {
                seriesSp.setSelection(i);
            }
        }
    }
}