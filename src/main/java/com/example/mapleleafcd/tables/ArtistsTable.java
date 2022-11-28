package com.example.mapleleafcd.tables;

import com.example.mapleleafcd.daos.ArtistsDAO;
import com.example.mapleleafcd.database.DBConst;
import com.example.mapleleafcd.database.Database;
import com.example.mapleleafcd.pojo.Artists;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class ArtistsTable implements ArtistsDAO {

    private static ArtistsTable instance;
    Database db = Database.getInstance();
    ArrayList<Artists> artists;

    //getAllAlbums returns an ArrayList of all data entries
    @Override
    public ArrayList<Artists> getAllArtists(){
        //SELECT * FROM albums;
        String query = "SELECT * FROM " + DBConst.TABLE_ARTISTS;

        artists = new ArrayList<>();

        try{
            Statement getArtists = db.getConnection().createStatement();
            ResultSet data = getArtists.executeQuery(query); //this part specifically sends query to table

            while(data.next()){
                artists.add(
                        new Artists(
                                data.getInt(DBConst.ARTISTS_COLUMN_ID),
                                data.getString(DBConst.ARTISTS_COLUMN_ARTIST),
                                data.getString(DBConst.ARTISTS_COLUMN_BIRTHDAY)));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return artists;
    }

    @Override
    public Artists getArtist(int id){
        String query = "SELECT * FROM " + DBConst.TABLE_ARTISTS + " WHERE " + DBConst.ARTISTS_COLUMN_ID  + " = " + id;
        try{
            Statement getArtist = db.getConnection().createStatement();
            ResultSet data = getArtist.executeQuery(query);
            if(data.next()){
                Artists artists = new Artists(
                        data.getInt(DBConst.ARTISTS_COLUMN_ID),
                        data.getString(DBConst.ARTISTS_COLUMN_ARTIST),
                        data.getString(DBConst.ARTISTS_COLUMN_BIRTHDAY));
                return artists;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static ArtistsTable getInstance(){
        if(instance == null){
            instance = new ArtistsTable();
        }
        return instance;
    }
}
