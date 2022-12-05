package com.example.crud.template;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crud.R;

import java.util.List;

public class TemplatesAdapter extends RecyclerView.Adapter<TemplateViewHolder> {
    public List<Template> templates;
    public OnItemActionListener onItemActionListener;

    public void setData(List<Template> templateList) {
        templates = templateList;
        notifyDataSetChanged();
    }

    public  void setOnItemActionListener(OnItemActionListener actionListener) {
        onItemActionListener = actionListener;
    }

    @NonNull
    @Override
    public TemplateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.template_item, parent, false);
        TemplateViewHolder templateViewHolder = new TemplateViewHolder(view);
        return templateViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TemplateViewHolder holder, int position) {
        Template template = templates.get(position);
        holder.textTxt.setText(template.messageText);
        holder.deleteBtn.setOnClickListener(view -> {
            onItemActionListener.onDelete(template.id);
        });
        holder.itemView.setOnClickListener(view -> {
            onItemActionListener.onEdit(template);
        });

    }

    @Override
    public int getItemCount() {
        return templates.size();
    }
}
