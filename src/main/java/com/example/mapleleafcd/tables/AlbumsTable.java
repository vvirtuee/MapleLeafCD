package com.example.mapleleafcd.tables;

import com.example.mapleleafcd.daos.AlbumsDAO;
import com.example.mapleleafcd.database.DBConst;
import com.example.mapleleafcd.database.Database;
import com.example.mapleleafcd.pojo.Albums;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class AlbumsTable implements AlbumsDAO {


    Database db = Database.getInstance();
    ArrayList<Albums> albums;

    //getAllAlbums returns an ArrayList of all data entries
    @Override
    public ArrayList<Albums> getAllAlbums(){
        //SELECT * FROM albums;
        String query = "SELECT * FROM " + DBConst.TABLE_ALBUMS;

        albums = new ArrayList<>();

        try{
            Statement getAlbums = db.getConnection().createStatement();
            ResultSet data = getAlbums.executeQuery(query); //this part specifically sends query to table

            while(data.next()){
                System.out.println(data.getString(DBConst.ALBUMS_COLUMN_ID));
                albums.add(
                        new Albums(
                                data.getInt(DBConst.ALBUMS_COLUMN_ID),
                                data.getString(DBConst.ALBUMS_COLUMN_NAME)));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return albums;
    }

    @Override
    public Albums getAlbum(int id){
        String query = "SELECT * FROM " + DBConst.TABLE_ALBUMS + " WHERE " + DBConst.ALBUMS_COLUMN_ID  + " = " + id;
        try{
            Statement getCoin = db.getConnection().createStatement();
            ResultSet data = getCoin.executeQuery(query);
            if(data.next()){
                Albums album = new Albums(
                        data.getInt(DBConst.ALBUMS_COLUMN_ID),
                        data.getString(DBConst.ALBUMS_COLUMN_NAME));
                return album;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
