package com.example.mapleleafcd.tabs;

import javafx.scene.control.Tab;

public class addItem extends Tab {
    private static addItem instance;
    private addItem(){this.setText("Add Item");}

    public static addItem getInstance(){
        if(instance == null){
            instance = new addItem();
        }
        return instance;
    }
}
