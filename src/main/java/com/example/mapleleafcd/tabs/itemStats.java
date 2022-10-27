package com.example.mapleleafcd.tabs;

import javafx.scene.control.Tab;

public class itemStats extends Tab {
    private static itemStats instance;
    
    private itemStats(){this.setText("Stats");}

    public static itemStats getInstance(){
        if(instance == null){
            instance = new itemStats();
        }
        return instance;
    }

}
