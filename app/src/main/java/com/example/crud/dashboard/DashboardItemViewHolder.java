package com.example.crud.dashboard;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crud.R;
import com.example.crud.databinding.DashboardItemBinding;

public class DashboardItemViewHolder extends RecyclerView.ViewHolder {

    DashboardItemBinding binding;

    public DashboardItemViewHolder(DashboardItemBinding dashboardItemBinding) {
        super(dashboardItemBinding.getRoot());
        binding = dashboardItemBinding;
    }
}
