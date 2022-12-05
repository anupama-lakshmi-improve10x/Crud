package com.example.crud.dashboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.crud.R;

import java.util.ArrayList;

public class DashboardActivity extends AppCompatActivity {
    public ArrayList<Dashboard> dashboardList;
    public RecyclerView dashboardRv;
    public DashboardAdapter dashboardAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        getSupportActionBar().setTitle("Dashboard");
        setupData();
        setupDashboardsRV();
    }

    public void setupData() {
        dashboardList = new ArrayList<>();
        Dashboard dashboardOne = new Dashboard();
        dashboardOne.imageUrl = "https://upload.wikimedia.org/wikipedia/commons/7/72/Message-icon-grey.png";
        dashboardOne.title = "Messages";
        dashboardList.add(dashboardOne);

        Dashboard dashboardTwo = new Dashboard();
        dashboardTwo.imageUrl = "https://static.thenounproject.com/png/1021190-200.png";
        dashboardTwo.title = "Templates";
        dashboardList.add(dashboardTwo);

        Dashboard dashboardThree = new Dashboard();
        dashboardThree.imageUrl = "https://upload.wikimedia.org/wikipedia/commons/7/72/Message-icon-grey.png";
        dashboardThree.title = "Series";
        dashboardList.add(dashboardThree);

        Dashboard dashboardFour = new Dashboard();
        dashboardFour.imageUrl = "https://upload.wikimedia.org/wikipedia/commons/7/72/Message-icon-grey.png";
        dashboardFour.title = "Movies";
        dashboardList.add(dashboardFour);
    }

    public void setupDashboardsRV() {
        dashboardRv = findViewById(R.id.dash_board_rv);
        dashboardRv.setLayoutManager(new LinearLayoutManager(this));
        dashboardAdapter = new DashboardAdapter();
        dashboardAdapter.setData(dashboardList);
        dashboardRv.setAdapter(dashboardAdapter);
    }
}