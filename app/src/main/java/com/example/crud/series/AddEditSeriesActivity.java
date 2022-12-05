package com.example.crud.series;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.crud.R;
import com.example.crud.message.AddEditMessageActivity;
import com.example.crud.message.Message;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddEditSeriesActivity extends AppCompatActivity {
    public Series series;
    public EditText seriesId;
    public EditText seriesName;
    public EditText imageUrl;
    public SeriesApi seriesApi;
    public SeriesService seriesService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_series);
        initView();
        if (getIntent().hasExtra("Series")) {
            getSupportActionBar().setTitle("Edit Series");
            this.series = (Series) getIntent().getSerializableExtra("Series");
            showData();
        } else {
            getSupportActionBar().setTitle("Add Message");
        }
    }

    public void showData() {
        seriesId.setText(series.id);
        seriesName.setText(series.title);
        imageUrl.setText(series.imageUrl);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       getMenuInflater().inflate(R.menu.add_edit_series_menu, menu);
       return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.save) {
            String id = seriesId.getText().toString();
            String title = seriesName.getText().toString();
            String image = imageUrl.getText().toString();
            if(this.series == null) {
                addSeries(id, title, image);
        } else {
                upDateSeries(id, title, image);
            }
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    public void initView() {
        seriesId = findViewById(R.id.series_id_txt);
        seriesName = findViewById(R.id.series_name_txt);
        imageUrl = findViewById(R.id.img_url_txt);
    }


    public void addSeries(String id, String title, String imageUrl) {
        series = new Series();
        series.seriesId = id;
        series.title = title;
        series.imageUrl = imageUrl;

        seriesApi = new SeriesApi();
        seriesService = seriesApi.createSeriesService();
        Call<Series> call = seriesService.createSeries(series);
        call.enqueue(new Callback<Series>() {
            @Override
            public void onResponse(Call<Series> call, Response<Series> response) {
                Toast.makeText(AddEditSeriesActivity.this, "Sucessfully added", Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<Series> call, Throwable t) {
                Toast.makeText(AddEditSeriesActivity.this, "Failed to add", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void upDateSeries(String id, String title, String imageUrl) {
        series = new Series();
        series.seriesId = id;
        series.title = title;
        series.imageUrl = imageUrl;

        SeriesApi seriesApi = new SeriesApi();
        SeriesService seriesService = seriesApi.createSeriesService();
        Call<Void> call = seriesService.updateSeries(id,series);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(AddEditSeriesActivity.this, "Successfully edited series", Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(AddEditSeriesActivity.this, "Failed to edit", Toast.LENGTH_SHORT).show();
            }
        });
    }
}