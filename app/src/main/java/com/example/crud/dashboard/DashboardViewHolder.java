package com.example.crud.dashboard;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crud.R;

public class DashboardViewHolder extends RecyclerView.ViewHolder {
//Todo : change the DashboardViewHolder to dashboardItemViewHolder
    ImageView dashboardImg;
    TextView titleTxt;

    public DashboardViewHolder(@NonNull View itemView) {
        super(itemView);
        dashboardImg = itemView.findViewById(R.id.image_img);
        titleTxt = itemView.findViewById(R.id.title_txt);
    }
}
