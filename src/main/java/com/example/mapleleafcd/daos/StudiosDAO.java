package com.example.mapleleafcd.daos;

import com.example.mapleleafcd.pojo.Studios;

import java.util.ArrayList;

public interface StudiosDAO {
    public ArrayList<Studios> getAllStudios();
    public Studios getStudio(int id);
}
