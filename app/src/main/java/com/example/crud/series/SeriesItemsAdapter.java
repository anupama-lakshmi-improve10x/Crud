package com.example.crud.series;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crud.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SeriesItemsAdapter extends RecyclerView.Adapter<SeriesViewHolder> {

    private List<SeriesItems> seriesItems;
    private SeriesOnItemActionListener seriesOnItemActionListener;

    void setData(List<SeriesItems> seriesItems) {
        this.seriesItems = seriesItems;
        notifyDataSetChanged();
    }

    void setSeriesOnItemActionListener(SeriesOnItemActionListener actionListener){
        seriesOnItemActionListener = actionListener;
    }

    @NonNull
    @Override
    public SeriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.series_item, parent, false);
        SeriesViewHolder seriesViewHolder = new SeriesViewHolder(view);
        return seriesViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SeriesViewHolder holder, int position) {
        SeriesItems seriesItems = this.seriesItems.get(position);
        Picasso.get().load(seriesItems.imageUrl).into(holder.seriesImg);
        holder.seriesTxt.setText(seriesItems.title);
        holder.deleteBtn.setOnClickListener(view -> {
            seriesOnItemActionListener.onDelete(seriesItems.id);
        });
        holder.itemView.setOnClickListener(view -> {
            seriesOnItemActionListener.onEdit(seriesItems);
        });
    }

    @Override
    public int getItemCount() {
        return seriesItems.size();
    }
}
