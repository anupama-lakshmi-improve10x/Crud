package com.example.crud.series;

import android.os.Bundle;
import android.view.Menu;
import android.widget.EditText;

import com.example.crud.R;
import com.example.crud.base.BaseActivity;
//Todo: change class BaseAddEditSeriesItemActivity
public class BaseAddEditSeriesItemsActivity extends BaseActivity {

    protected EditText seriesIdTxt;
    protected EditText seriesNameTxt;
    protected EditText imageUrlTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Todo: change id to activity_add_edit_series_item
        setContentView(R.layout.activity_add_edit_series);
        initViews();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Todo: change id to add_edit_series_item_menu
        getMenuInflater().inflate(R.menu.add_edit_series_menu, menu);
        return true;
    }

    private void initViews() {
        seriesIdTxt = findViewById(R.id.series_id_txt);
        seriesNameTxt = findViewById(R.id.series_name_txt);
        //Todo: change id to image_url_txt
        imageUrlTxt = findViewById(R.id.img_url_txt);
    }
}