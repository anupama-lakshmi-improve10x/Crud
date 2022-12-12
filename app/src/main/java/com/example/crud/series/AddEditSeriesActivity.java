package com.example.crud.series;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.crud.Constants;
import com.example.crud.R;
import com.example.crud.api.CrudApi;
import com.example.crud.api.CrudService;
import com.example.crud.base.BaseActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddEditSeriesActivity extends BaseActivity {
    private Series series;
    private EditText seriesId;
    private EditText seriesName;
    private EditText imageUrl;
    private CrudService crudService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_series);
        initView();
        setupApiService();
        if (getIntent().hasExtra(Constants.KEY_SERIES)) {
            getSupportActionBar().setTitle("Edit Series");
            this.series = (Series) getIntent().getSerializableExtra(Constants.KEY_SERIES);
            showData();
        } else {
            getSupportActionBar().setTitle("Add Message");
        }
    }

    private void setupApiService() {
        CrudApi crudApi = new CrudApi();
        crudService = crudApi.createCrudService();
    }

    private void showData() {
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

    private void initView() {
        seriesId = findViewById(R.id.series_id_txt);
        seriesName = findViewById(R.id.series_name_txt);
        imageUrl = findViewById(R.id.img_url_txt);
    }


    private void addSeries(String id, String title, String imageUrl) {
        series = new Series();
        series.seriesId = id;
        series.title = title;
        series.imageUrl = imageUrl;

        setupApiService();
        Call<Series> call = crudService.createSeries(series);
        call.enqueue(new Callback<Series>() {
            @Override
            public void onResponse(Call<Series> call, Response<Series> response) {
                showToast("Sucessfully added");
                finish();
            }

            @Override
            public void onFailure(Call<Series> call, Throwable t) {
                showToast("Failed to add");
            }
        });
    }

    private void upDateSeries(String id, String title, String imageUrl) {
        series = new Series();
        series.seriesId = id;
        series.title = title;
        series.imageUrl = imageUrl;

        setupApiService();
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