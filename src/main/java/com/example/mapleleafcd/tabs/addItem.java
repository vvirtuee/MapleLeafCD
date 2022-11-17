package com.example.mapleleafcd.tabs;

import com.example.mapleleafcd.pojo.Albums;
import com.example.mapleleafcd.tables.AlbumsTable;
import javafx.collections.FXCollections;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class addItem extends Tab {
    private static addItem instance;

    public TableView tableView;
    private addItem(){
        this.setText("Add Item");

        //1. initialize container
        GridPane root = new GridPane();

        //2. initialize AlbumsTable
        AlbumsTable albumsTable = new AlbumsTable();

        //3. initialize column name
        Text name = new Text("Name: ");

        //ComboBox: lists out items and allows for selection of items
        ComboBox<Albums> comboName = new ComboBox<>();

        //sets items from arrayList to ComboBox
        comboName.setItems(FXCollections.observableArrayList(albumsTable.getAllAlbums()));

        System.out.println(albumsTable.getAlbum(0));
        //System.out.println(comboName.getSelectionModel().getSelectedItem().getName());

        root.add(name, 0, 0);       //|x| | |...
        root.add(comboName, 1, 0);  //| |x| |...

        //4. initialize second column name
        Text location = new Text("Location: ");
        root.add(location,0,3);

        //5. Submit Button
        /*
        Button submit = new Button("Submit");
        submit.setOnAction(e->{
            Albums album = new Albums(
                    comboName.getSelectionModel().getSelectedItem().getIdNum(),
                    Integer.parseInt(name.getText());
                    comboName.getSelectionModel().getSelectedItem().getIdNum();
            albumsTable(album);
            System.out.println("Item added");
        });
         */

        this.setContent(root);


    }

    public static addItem getInstance(){
        if(instance == null){
            instance = new addItem();
        }
        return instance;
    }
}
