package com.example.mapleleafcd.pojo;

public class Albums {
    private int idNum;
    private String name;

    public Albums(int idNum, String name){
        this.idNum = idNum;
        this.name = name;
    }
    public int getIdNum(){return idNum;}
    public void setIdNum(int id){this.idNum = idNum;}

    public String getName(){return name;}
    public void setName(String name){this.name = name;}
}
