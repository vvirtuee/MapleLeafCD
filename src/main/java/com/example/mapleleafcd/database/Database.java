package com.example.mapleleafcd.database;

import javax.xml.transform.Result;
import java.sql.*;

public class Database {
    /**
     * This class is using a singleton pattern. So that the entire application is only using one connection.
     * We do this through the use of a private Constructor and a static instance variable.
     * the static method getInstance() will create/return the one instance of the Database class.
     * Allowing the application to only ever have 1 instance of the Database class.
     */
    private static Database instance;   //creates an instance variable (instance of class Database) this is the only Database instance to exist.
    //Database db = new Database() <-- wont work outside of classes
    private Connection connection = null;


    private Database(){
        if(connection == null){
            try{
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection("jdbc:mysql://localhost/" + Const.DB_NAME + "?serverTimezone", Const.DB_USER, Const.DB_PASSWORD);
                System.out.println("Created connection.");
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    private void createTable(String tableName, String query, Connection connection) throws SQLException{
        Statement createTable;
        DatabaseMetaData md = connection.getMetaData();
        ResultSet resultSet = md.getTables("dvirtuedb", null, tableName, null);
        if(resultSet.next()){
            System.out.println(tableName + " table already exists!");
        }else{
            createTable = connection.createStatement();
            createTable.execute(query);
            System.out.println("the " + tableName + " is created.");
        }
    }

    //singleton structure like this, it checks if the database instance already exists, otherwise create a new instance. This assures that only one will exist.
    //Database.getInstance()
    public static Database getInstance(){
        if(instance == null){
            instance = new Database();
        }
        return instance;
    }

    public void close(){
        System.out.println("Closing Connection");
        try {
            connection.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
