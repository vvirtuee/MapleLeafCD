package com.example.mapleleafcd.pojo;

public class Artists {
    private int id;

    private String artist;

    private String birthday;


    public Artists(int id, String artist, String birthday) {
        this.id = id;
        this.artist = artist;
        this.birthday = birthday;
    }

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public String getArtist(){return artist;}
    public void setArtist(String artist){this.artist = artist;}

    public String getBirthday(){return birthday;}
    public void setBirthday(String birthday){this.birthday = birthday;}
}
