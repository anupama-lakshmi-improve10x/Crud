package com.example.crud.movie;

import android.os.Bundle;
import android.view.Menu;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.crud.R;
import com.example.crud.base.BaseActivity;
import com.example.crud.series.SeriesItem;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BaseAddEditMovieActivity extends BaseActivity {

    protected EditText movieIdTxt;
    protected EditText movieNameTxt;
    protected Spinner seriesItemsSp;
    protected EditText imageUrlTxt;
    protected EditText descriptionTxt;
    protected CustomSeriesItemsAdapter customSeriesItemsAdapter;
    //Todo: Change the variable name seriesList to seriesItems
    private ArrayList<SeriesItem> seriesItems = new ArrayList<>();
    protected Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_add_edit_movie);
        initViews();
        setupSeriesItemsSp();
        fetchSeriesItems();
    }

    private void setupSeriesItemsSp() {
        customSeriesItemsAdapter = new CustomSeriesItemsAdapter(this, android.R.layout.simple_list_item_1, seriesItems);
        seriesItemsSp.setAdapter(customSeriesItemsAdapter);
    }

    private void initViews() {
        movieIdTxt = findViewById(R.id.movie_id_txt);
        movieNameTxt = findViewById(R.id.movie_name_txt);
        seriesItemsSp = findViewById(R.id.series_items_sp);
        imageUrlTxt = findViewById(R.id.image_url_txt);
        descriptionTxt = findViewById(R.id.description_txt);
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
        movieIdTxt.setText(movie.movieId);
        movieNameTxt.setText(movie.title);
        imageUrlTxt.setText(movie.imageUrl);
        descriptionTxt.setText(movie.description);
        for (int i = 0; i < customSeriesItemsAdapter.getCount(); i++) {
            SeriesItem seriesItem = customSeriesItemsAdapter.getItem(i);
            if (movie.seriesId.equals(seriesItem.seriesId)) {
                seriesItemsSp.setSelection(i);
            }
        }
    }
}