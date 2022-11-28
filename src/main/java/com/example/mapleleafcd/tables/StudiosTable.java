package com.example.mapleleafcd.tables;

import com.example.mapleleafcd.daos.StudiosDAO;
import com.example.mapleleafcd.database.DBConst;
import com.example.mapleleafcd.database.Database;
import com.example.mapleleafcd.pojo.Studios;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class StudiosTable implements StudiosDAO {

    private static StudiosTable instance;
    Database db = Database.getInstance();
    ArrayList<Studios> studios;

    //getAllAlbums returns an ArrayList of all data entries
    @Override
    public ArrayList<Studios> getAllStudios(){
        //SELECT * FROM albums;
        String query = "SELECT * FROM " + DBConst.TABLE_STUDIOS;

        studios = new ArrayList<>();

        try{
            Statement getStudios = db.getConnection().createStatement();
            ResultSet data = getStudios.executeQuery(query); //this part specifically sends query to table


            while(data.next()){
                studios.add(
                        new Studios(
                                data.getInt(DBConst.STUDIOS_COLUMN_ID),
                                data.getString(DBConst.STUDIOS_COLUMN_STUDIO),
                                data.getString(DBConst.STUDIOS_COLUMN_DATECREATED)));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return studios;
    }

    @Override
    public Studios getStudio(int id){
        String query = "SELECT * FROM " + DBConst.TABLE_STUDIOS + " WHERE " + DBConst.STUDIOS_COLUMN_ID  + " = " + id;
        try{
            Statement getStudio = db.getConnection().createStatement();
            ResultSet data = getStudio.executeQuery(query);
            if(data.next()){
                Studios studios = new Studios(
                        data.getInt(DBConst.STUDIOS_COLUMN_ID),
                        data.getString(DBConst.STUDIOS_COLUMN_STUDIO),
                        data.getString(DBConst.STUDIOS_COLUMN_DATECREATED));
                return studios;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static StudiosTable getInstance(){
        if(instance == null){
            instance = new StudiosTable();
        }
        return instance;
    }
}
