package com.example.mapleleafcd.database;

public class DBConst {

    //albums table
    public static final String TABLE_ALBUMS ="albums";
    public static final String ALBUMS_COLUMN_ID = "id";
    public static final String ALBUMS_COLUMN_NAME = "name";
    public static final String ALBUMS_COLUMN_NUMSONGS = "numSongs";
    public static final String ALBUMS_COLUMN_RELEASEDATE = "releaseDate";
    public static final String ALBUMS_COLUMN_LENGTH = "length";
    public static final String ALBUMS_COLUMN_PRICE = "price";
    public static final String ALBUMS_COLUMN_RATING = "rating";
    public static final String ALBUMS_COLUMN_GENREID = "genre_id";
    public static final String ALBUMS_COLUMN_ARTISTID = "artist_id";
    public static final String ALBUMS_COLUMN_STUDIOID = "studio_id";


    //genres table
    public static final String TABLE_GENRES ="genres";
    public static final String GENRES_COLUMN_ID = "id";
    public static final String GENRES_COLUMN_GENRE = "genre";

    //artists table
    public static final String TABLE_ARTISTS ="artists";
    public static final String ARTISTS_COLUMN_ID = "id";
    public static final String ARTISTS_COLUMN_ARTIST = "artist";
    public static final String ARTISTS_COLUMN_BIRTHDAY = "birthday";

    //studios table
    public static final String TABLE_STUDIOS ="studios";
    public static final String STUDIOS_COLUMN_ID = "id";
    public static final String STUDIOS_COLUMN_STUDIO = "studio";
    public static final String STUDIOS_COLUMN_DATECREATED = "dateCreated";


    //TABLE CREATE STATEMENTS
    public static final String CREATE_TABLE_GENRES =
            "CREATE TABLE" + TABLE_GENRES + " ("
            + GENRES_COLUMN_ID + " int NOT NULL AUTO_INCREMENT PRIMARY KEY, "
            + GENRES_COLUMN_GENRE + " varchar(20) NOT NULL " + " );";

    public static final String CREATE_TABLE_ARTISTS =
            "CREATE TABLE" + TABLE_ARTISTS + " ("
            + ARTISTS_COLUMN_ID + " int NOT NULL AUTO_INCREMENT PRIMARY KEY, "
            + ARTISTS_COLUMN_ARTIST + " varchar(20) NOT NULL,"
            + ARTISTS_COLUMN_BIRTHDAY + " date NOT NULL" + " );";


    public static final String CREATE_TABLE_STUDIOS =
            "CREATE TABLE " + TABLE_STUDIOS + " ("
            + STUDIOS_COLUMN_ID + " int NOT NULL AUTO_INCREMENT PRIMARY KEY, "
                    + STUDIOS_COLUMN_STUDIO + " varchar(20) NOT NULL, "
            + STUDIOS_COLUMN_DATECREATED + " DATE NOT NULL" + ");";
    public static final String CREATE_TABLE_ALBUMS =
            "CREATE TABLE " + TABLE_ALBUMS  + " ("
            + ALBUMS_COLUMN_ID + " int NOT NULL AUTO_INCREMENT PRIMARY KEY, "
            + ALBUMS_COLUMN_NAME + " varchar(20) NOT NULL, "
            + ALBUMS_COLUMN_NUMSONGS + " int(15) NOT NULL, "
            + ALBUMS_COLUMN_RELEASEDATE + " date NOT NULL, "
            + ALBUMS_COLUMN_LENGTH + " double NOT NULL, "
            + ALBUMS_COLUMN_PRICE + " decimal(5,2) NOT NULL, "
            + ALBUMS_COLUMN_RATING + " decimal(5,2) NOT NULL, "
            + "FOREIGN KEY (" + ALBUMS_COLUMN_GENREID + ")" + " REFERENCES " + TABLE_GENRES + "(" + GENRES_COLUMN_ID + "), " +
            "FOREIGN KEY (" + ALBUMS_COLUMN_ARTISTID + ")" + " REFERENCES " + TABLE_ARTISTS + "(" + ARTISTS_COLUMN_ID + "), " +
            "FOREIGN KEY (" + ALBUMS_COLUMN_STUDIOID + ")" + " REFERENCES " + TABLE_STUDIOS + "(" + STUDIOS_COLUMN_ID + ")" + ");";
}
