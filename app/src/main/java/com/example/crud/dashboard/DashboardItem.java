package com.example.crud.dashboard;

public class DashboardItem {

    public String imageUrl;
    public String title;

    public DashboardItem() {
    }

    public DashboardItem(String title, String imageUrl) {
        this.title = title;
        this.imageUrl = imageUrl;
    }
}
