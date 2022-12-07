package com.example.crud.movie;

import com.example.crud.Movies;

public interface MovieOnItemActionListener {
    void onDelete(String id);

    void onEdit(Movies movies);

}
