package com.example.crud.dashboard;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.crud.R;
import com.example.crud.base.BaseActivity;
import com.example.crud.databinding.ActivityDashboardItemsBinding;

import java.util.ArrayList;

public class DashboardItemsActivity extends BaseActivity {

    private ArrayList<DashboardItem> dashboardItems;
    private ActivityDashboardItemsBinding binding;
    private DashboardItemsAdapter dashboardItemsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDashboardItemsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().setTitle("Dashboard");
        setupData();
        setupDashboardItemsAdapter();
        setupDashboardItemsRv();
    }

    private void setupData() {
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
        dashboardItemsAdapter = new DashboardItemsAdapter();
        dashboardItemsAdapter.setData(dashboardItems);
    }

    private void setupDashboardItemsRv() {
        binding.dashBoardRv.setLayoutManager(new LinearLayoutManager(this));
        binding.dashBoardRv.setAdapter(dashboardItemsAdapter);
    }
}