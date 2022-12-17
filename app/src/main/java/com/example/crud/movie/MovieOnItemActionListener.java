package com.example.crud.movie;

public interface MovieOnItemActionListener {

    void onDelete(String id);

    void onEdit(Movie movie);
}
