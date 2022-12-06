package com.example.mapleleafcd.daos;

import com.example.mapleleafcd.pojo.Albums;
import java.util.ArrayList;

public interface AlbumsDAO {
    public ArrayList<Albums> getAllAlbums();
    public Albums getAlbum(int id);
}
