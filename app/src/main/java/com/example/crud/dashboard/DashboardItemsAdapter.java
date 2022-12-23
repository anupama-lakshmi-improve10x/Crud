package com.example.crud.dashboard;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crud.databinding.DashboardItemBinding;
import com.example.crud.movie.MoviesActivity;
import com.example.crud.series.SeriesItemsActivity;
import com.example.crud.template.TemplatesActivity;
import com.example.crud.message.MessagesActivity;
import com.example.crud.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DashboardItemsAdapter extends RecyclerView.Adapter<DashboardItemViewHolder> {

    private ArrayList<DashboardItem> dashboardItems;

    void setData(ArrayList<DashboardItem> dashboardItems){
        this.dashboardItems = dashboardItems;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DashboardItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        DashboardItemBinding binding = DashboardItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        DashboardItemViewHolder dashboardItemViewHolder = new DashboardItemViewHolder(binding);
        return dashboardItemViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DashboardItemViewHolder holder, int position) {
        DashboardItem dashboardItem = dashboardItems.get(position);
        Picasso.get().load(dashboardItem.imageUrl).into(holder.binding.dashboardImg);
        holder.binding.titleTxt.setText(dashboardItem.title);
        holder.binding.getRoot().setOnClickListener(view -> {
            if(holder.binding.titleTxt.getText().toString().equalsIgnoreCase("Messages")) {
                Intent intent = new Intent(holder.binding.getRoot().getContext(), MessagesActivity.class);
                holder.binding.getRoot().getContext().startActivity(intent);
            } else if(holder.binding.titleTxt.getText().toString().equalsIgnoreCase("Templates")) {
                Intent intent = new Intent(holder.binding.getRoot().getContext(), TemplatesActivity.class);
                holder.binding.getRoot().getContext().startActivity(intent);
            } else if(holder.binding.titleTxt.getText().toString().equalsIgnoreCase("Series")) {
                Intent intent = new Intent(holder.binding.getRoot().getContext(), SeriesItemsActivity.class);
                holder.binding.getRoot().getContext().startActivity(intent);
            } else if(holder.binding.titleTxt.getText().toString().equalsIgnoreCase("Movies")) {
                Intent intent = new Intent(holder.itemView.getContext(), MoviesActivity.class);
                holder.binding.getRoot().getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dashboardItems.size();
    }
}
