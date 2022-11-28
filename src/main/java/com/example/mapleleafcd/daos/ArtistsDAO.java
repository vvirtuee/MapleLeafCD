package com.example.mapleleafcd.daos;

import com.example.mapleleafcd.pojo.Artists;

import java.util.ArrayList;

public interface ArtistsDAO {
    public ArrayList<Artists> getAllArtists();
    public Artists getArtist(int id);
}
