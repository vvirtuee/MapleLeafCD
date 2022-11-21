package com.example.mapleleafcd.pojo;

public class Albums {
    private int id;
    private String name;
    private int numSongs;
    private String releaseDate;
    private Double length;
    private Double price;
    private Double rating;

    private String genre_id;
    private String artist_id;
    private String studio_id;

    public Albums(int id, String name, int numSongs, String releaseDate, Double length, Double price, Double rating, String genre_id, String artist_id, String studio_id) {
        this.id = id;
        this.name = name;
        this.numSongs = numSongs;
        this.releaseDate = releaseDate;
        this.length = length;
        this.price = price;
        this.rating = rating;

        this.genre_id = genre_id;
        this.artist_id = artist_id;
        this.studio_id = studio_id;
    }

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public int getNumSongs(){return numSongs;}
    public void setNumSongs(int numSongs){this.numSongs = numSongs;}

    public String getReleaseDate(){return releaseDate;}
    public void setReleaseDate(String releaseDate){this.releaseDate = releaseDate;}

    public Double getLength(){return length;}
    public void setLength(Double length){this.length = length;}

    public Double getPrice(){return price;}
    public void setPrice(Double price){this.price = price;}

    public double getRating(){return rating;}
    public void setRating(Double rating){this.rating = rating;}

    public String getGenreID(){return genre_id;}
    public void setGenreID(String genre_id){this.genre_id = genre_id;}

    public String getArtistID(){return artist_id;}
    public void setArtistID(String artist_id){this.artist_id = artist_id;}

    public String getStudioID(){return studio_id;}
    public void setStudioID(String studio_id){this.studio_id = studio_id;}

}
