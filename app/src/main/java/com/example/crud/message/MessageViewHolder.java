package com.example.crud.message;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crud.R;
import com.example.crud.databinding.MessageItemBinding;

public class MessageViewHolder extends RecyclerView.ViewHolder {

    MessageItemBinding binding;

    public MessageViewHolder(MessageItemBinding messageItemBinding) {
        super(messageItemBinding.getRoot());
        binding = messageItemBinding;
    }
}
