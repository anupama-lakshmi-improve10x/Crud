package com.example.crud.series;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crud.R;
import com.example.crud.databinding.SeriesItemBinding;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SeriesItemsAdapter extends RecyclerView.Adapter<SeriesItemViewHolder> {

    private List<SeriesItem> seriesItems;
    private SeriesOnItemActionListener seriesOnItemActionListener;

    void setData(List<SeriesItem> seriesItems) {
        this.seriesItems = seriesItems;
        notifyDataSetChanged();
    }

    void setSeriesOnItemActionListener(SeriesOnItemActionListener seriesOnItemActionListener) {
        this.seriesOnItemActionListener = seriesOnItemActionListener;
    }

    @NonNull
    @Override
    public SeriesItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        SeriesItemBinding binding = SeriesItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        SeriesItemViewHolder seriesItemViewHolder = new SeriesItemViewHolder(binding);
        return seriesItemViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SeriesItemViewHolder holder, int position) {
        SeriesItem seriesItem = this.seriesItems.get(position);
        holder.binding.setSeriesItem(seriesItem);
        holder.binding.deleteImgBtn.setOnClickListener(view -> {
            seriesOnItemActionListener.onDelete(seriesItem.id);
        });
        holder.binding.getRoot().setOnClickListener(view -> {
            seriesOnItemActionListener.onEdit(seriesItem);
        });
    }

    @Override
    public int getItemCount() {
        return seriesItems.size();
    }
}
