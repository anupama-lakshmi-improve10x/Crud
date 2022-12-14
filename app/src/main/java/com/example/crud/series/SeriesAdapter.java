package com.example.crud.series;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crud.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SeriesAdapter extends RecyclerView.Adapter<SeriesViewHolder> {
//Todo: Change SeriesAdapter to seriesItemsAdapter
    //Todo: change seriesList to seriesItems
    private List<Series> seriesList;
    private SeriesOnItemActionListener seriesOnItemActionListener;

    public void setData(List<Series> seriesList) {
        this.seriesList = seriesList;
        notifyDataSetChanged();
    }

    public void setSeriesOnItemActionListener(SeriesOnItemActionListener actionListener){
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
        Series series = this.seriesList.get(position);
        Picasso.get().load(series.imageUrl).into(holder.seriesImg);
        holder.seriesTxt.setText(series.title);
        holder.deleteBtn.setOnClickListener(view -> {
            seriesOnItemActionListener.onDelete(series.id);
        });
        holder.itemView.setOnClickListener(view -> {
            seriesOnItemActionListener.onEdit(series);
        });
    }

    @Override
    public int getItemCount() {
        return seriesList.size();
    }
}
