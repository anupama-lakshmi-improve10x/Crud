package com.example.crud.template;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crud.R;
import com.example.crud.databinding.TemplateItemBinding;

public class TemplateViewHolder extends RecyclerView.ViewHolder {

    TemplateItemBinding binding;

    public TemplateViewHolder(TemplateItemBinding templateItemBinding) {
        super(templateItemBinding.getRoot());
        binding = templateItemBinding;
    }
}
