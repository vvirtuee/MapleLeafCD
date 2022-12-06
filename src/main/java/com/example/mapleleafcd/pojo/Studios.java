package com.example.mapleleafcd.pojo;

public class Studios {
    private int id;

    private String studio;

    private String dateCreated;


    public Studios(int id, String studio, String dateCreated) {
        this.id = id;
        this.studio = studio;
        this.dateCreated = dateCreated;
    }

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public String getStudio(){return studio;}
    public void setStudio(String studio){this.studio = studio;}

    public String getDateCreated(){return dateCreated;}
    public void setDateCreated(String dateCreated){this.dateCreated = dateCreated;}


}
