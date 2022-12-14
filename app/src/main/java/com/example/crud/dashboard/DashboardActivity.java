package com.example.crud.dashboard;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.crud.R;
import com.example.crud.base.BaseActivity;

import java.util.ArrayList;

public class DashboardActivity extends BaseActivity {

    private ArrayList<Dashboard> dashboardItems;
    private RecyclerView dashboardRv;
    private DashboardAdapter dashboardAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        log("onCreate");
        setContentView(R.layout.activity_dashboard);
        getSupportActionBar().setTitle("Dashboard");
        setupData();
        setupDashboardItemsAdapter();
        setupDashboardItemsRV();
    }

    private void setupData() {
        dashboardItems = new ArrayList<>();
        Dashboard messages = new Dashboard();
        messages.imageUrl = "https://upload.wikimedia.org/wikipedia/commons/7/72/Message-icon-grey.png";
        messages.title = "Messages";
        dashboardItems.add(messages);

        Dashboard templates = new Dashboard();
        templates.imageUrl = "https://static.thenounproject.com/png/1021190-200.png";
        templates.title = "Templates";
        dashboardItems.add(templates);

        Dashboard series = new Dashboard();
        series.imageUrl = "https://upload.wikimedia.org/wikipedia/commons/7/72/Message-icon-grey.png";
        series.title = "Series";
        dashboardItems.add(series);

        Dashboard movies = new Dashboard();
        movies.imageUrl = "https://upload.wikimedia.org/wikipedia/commons/7/72/Message-icon-grey.png";
        movies.title = "Movies";
        dashboardItems.add(movies);
    }

    private void setupDashboardItemsAdapter() {
        dashboardAdapter = new DashboardAdapter();
        dashboardAdapter.setData(dashboardItems);
    }

    private void setupDashboardItemsRV() {
        dashboardRv = findViewById(R.id.dash_board_rv);
        dashboardRv.setLayoutManager(new LinearLayoutManager(this));
        dashboardRv.setAdapter(dashboardAdapter);
    }
}