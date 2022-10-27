package com.example.mapleleafcd.tabs;

import javafx.scene.control.Tab;

public class removeItem extends Tab {
    private static removeItem instance;
    private removeItem(){this.setText("Remove Item");}

    public static removeItem getInstance(){
        if(instance == null){
            instance = new removeItem();
        }
        return instance;
    }
}
