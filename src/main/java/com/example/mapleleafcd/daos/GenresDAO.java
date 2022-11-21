package com.example.mapleleafcd.daos;

import com.example.mapleleafcd.pojo.Albums;
import com.example.mapleleafcd.pojo.Genres;

import java.util.ArrayList;

public interface GenresDAO {
    public ArrayList<Genres> getAllGenres();
    public Genres getGenre(int id);
}
