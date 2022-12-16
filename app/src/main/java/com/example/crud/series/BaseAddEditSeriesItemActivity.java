package com.example.crud.series;

import android.os.Bundle;
import android.view.Menu;
import android.widget.EditText;

import com.example.crud.R;
import com.example.crud.base.BaseActivity;

public class BaseAddEditSeriesItemActivity extends BaseActivity {

    protected EditText seriesIdTxt;
    protected EditText seriesNameTxt;
    protected EditText imageUrlTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_add_edit_series_item);
        initViews();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.base_add_edit_series_item_menu, menu);
        return true;
    }

    private void initViews() {
        seriesIdTxt = findViewById(R.id.series_id_txt);
        seriesNameTxt = findViewById(R.id.series_name_txt);
        imageUrlTxt = findViewById(R.id.image_url_txt);
    }
}