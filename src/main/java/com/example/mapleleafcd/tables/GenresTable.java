package com.example.mapleleafcd.tables;

import com.example.mapleleafcd.daos.AlbumsDAO;
import com.example.mapleleafcd.daos.GenresDAO;
import com.example.mapleleafcd.database.DBConst;
import com.example.mapleleafcd.database.Database;
import com.example.mapleleafcd.pojo.Albums;
import com.example.mapleleafcd.pojo.Genres;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class GenresTable implements GenresDAO {

    private static GenresTable instance;
    Database db = Database.getInstance();
    ArrayList<Genres> genres;

    //getAllAlbums returns an ArrayList of all data entries
    @Override
    public ArrayList<Genres> getAllGenres(){
        //SELECT * FROM albums;
        String query = "SELECT * FROM " + DBConst.TABLE_GENRES;

        genres = new ArrayList<>();

        try{
            Statement getGenres = db.getConnection().createStatement();
            ResultSet data = getGenres.executeQuery(query); //this part specifically sends query to table

            while(data.next()){
                genres.add(
                        new Genres(
                                data.getInt(DBConst.GENRES_COLUMN_ID),
                                data.getString(DBConst.GENRES_COLUMN_GENRE)));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return genres;
    }

    @Override
    public Genres getGenre(int id){
        String query = "SELECT * FROM " + DBConst.TABLE_GENRES + " WHERE " + DBConst.GENRES_COLUMN_ID  + " = " + id;
        try{
            Statement getGenre = db.getConnection().createStatement();
            ResultSet data = getGenre.executeQuery(query);
            if(data.next()){
                Genres genre = new Genres(
                        data.getInt(DBConst.GENRES_COLUMN_ID),
                        data.getString(DBConst.GENRES_COLUMN_GENRE));
                return genre;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static GenresTable getInstance(){
        if(instance == null){
            instance = new GenresTable();
        }
        return instance;
    }

}
