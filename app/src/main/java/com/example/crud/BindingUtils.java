package com.example.crud;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.squareup.picasso.Picasso;

public class BindingUtils {

    @BindingAdapter("imageUrl")
    public static void loadImage(ImageView imageView, String url) {
        Picasso.get().load(url).into(imageView);
    }
}
