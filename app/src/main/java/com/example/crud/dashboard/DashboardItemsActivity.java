package com.example.crud.dashboard;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.example.crud.R;
import com.example.crud.base.BaseActivity;

import java.util.ArrayList;

public class DashboardItemsActivity extends BaseActivity {

    private ArrayList<DashboardItem> dashboardItems;
    private RecyclerView dashboardItemsRv;
    private DashboardAdapter dashboardItemsAdapter;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        getSupportActionBar().setTitle("Dashboard");
        initViews();
        setupData();
        setupDashboardItemsAdapter();
        setupDashboardItemsRv();
    }

    private void setupData() {
        hideProgressBar();
        dashboardItems = new ArrayList<>();
        DashboardItem messages = new DashboardItem("Messages", "https://upload.wikimedia.org/wikipedia/commons/7/72/Message-icon-grey.png");
        dashboardItems.add(messages);

        DashboardItem templates = new DashboardItem("Templates", "https://static.thenounproject.com/png/1021190-200.png");
        dashboardItems.add(templates);

        DashboardItem series = new DashboardItem("Series", "https://upload.wikimedia.org/wikipedia/commons/7/72/Message-icon-grey.png");
        dashboardItems.add(series);

        DashboardItem movies = new DashboardItem("Movies","https://upload.wikimedia.org/wikipedia/commons/7/72/Message-icon-grey.png");
        dashboardItems.add(movies);
    }

    private void setupDashboardItemsAdapter() {
        dashboardItemsAdapter = new DashboardAdapter();
        dashboardItemsAdapter.setData(dashboardItems);
    }

    private void setupDashboardItemsRv() {
        dashboardItemsRv.setLayoutManager(new LinearLayoutManager(this));
        dashboardItemsRv.setAdapter(dashboardItemsAdapter);
    }

    private void initViews() {
        dashboardItemsRv = findViewById(R.id.dash_board_rv);
        progressBar = findViewById(R.id.progress_bar);
    }

    private void hideProgressBar(){
        progressBar.setVisibility(View.GONE);
    }
}