package com.example.mapleleafcd.pojo;

public class DisplayItem {
    private int id;
    private String name;
    private int numSongs;
    private String releaseDate;
    private Double length;
    private Double price;
    private Double rating;
    private String studio;

    private int genre_id;
    private int artist_id;
    private int studio_id;

    public DisplayItem(int id, String name, int numSongs, String releaseDate, Double length, Double price, Double rating, String studio, int genre_id, int artist_id, int studio_id) {
        this.id = id;
        this.name = name;
        this.numSongs = numSongs;
        this.releaseDate = releaseDate;
        this.length = length;
        this.price = price;
        this.rating = rating;
        this.studio = studio;

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

    public String getStudio(){return studio;}
    public void setStudio(String studio){this.studio = studio;}

    public int getGenreID(){return genre_id;}
    public void setGenreID(int genre_id){this.genre_id = genre_id;}

    public int getArtistID(){return artist_id;}
    public void setArtistID(int artist_id){this.artist_id = artist_id;}

    public int getStudioID(){return studio_id;}
    public void setStudioID(int studio_id){this.studio_id = studio_id;}

}
