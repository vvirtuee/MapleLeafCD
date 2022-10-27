package com.example.mapleleafcd.tabs;

import javafx.scene.control.Tab;

public class updateItem extends Tab {
    private static updateItem instance;
    private updateItem(){this.setText("Add Item");}

    public static updateItem getInstance(){
        if(instance == null){
            instance = new updateItem();
        }
        return instance;
    }
}
