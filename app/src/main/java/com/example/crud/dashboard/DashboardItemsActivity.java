package com.example.crud.dashboard;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.crud.R;
import com.example.crud.base.BaseActivity;

import java.util.ArrayList;

public class DashboardItemsActivity extends BaseActivity {

    private ArrayList<DashboardItem> dashboardItems;
    private RecyclerView dashboardItemsRv;
    private DashboardAdapter dashboardItemsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        getSupportActionBar().setTitle("Dashboard");
        setupData();
        setupDashboardItemsAdapter();
        //Todo: In the method setupDashboardItemsRV the letter 'v' should be small
        setupDashboardItemsRV();
    }

    private void setupData() {
        dashboardItems = new ArrayList<>();
        DashboardItem messages = new DashboardItem();
        // Todo: use Constructor for all.
        messages.imageUrl = "https://upload.wikimedia.org/wikipedia/commons/7/72/Message-icon-grey.png";
        messages.title = "Messages";
        dashboardItems.add(messages);

        DashboardItem templates = new DashboardItem();
        templates.imageUrl = "https://static.thenounproject.com/png/1021190-200.png";
        templates.title = "Templates";
        dashboardItems.add(templates);

        DashboardItem series = new DashboardItem();
        series.imageUrl = "https://upload.wikimedia.org/wikipedia/commons/7/72/Message-icon-grey.png";
        series.title = "Series";
        dashboardItems.add(series);

        DashboardItem movies = new DashboardItem();
        movies.imageUrl = "https://upload.wikimedia.org/wikipedia/commons/7/72/Message-icon-grey.png";
        movies.title = "Movies";
        dashboardItems.add(movies);
    }

    private void setupDashboardItemsAdapter() {
        dashboardItemsAdapter = new DashboardAdapter();
        dashboardItemsAdapter.setData(dashboardItems);
    }

    private void setupDashboardItemsRV() {
        //Todo: create separate method for id's and add ProgressBar
        dashboardItemsRv = findViewById(R.id.dash_board_rv);
        dashboardItemsRv.setLayoutManager(new LinearLayoutManager(this));
        dashboardItemsRv.setAdapter(dashboardItemsAdapter);
    }
}