package com.example.mapleleafcd.tabs;

import com.example.mapleleafcd.pojo.Albums;
import com.example.mapleleafcd.pojo.Genres;
import com.example.mapleleafcd.tables.AlbumsTable;
import com.example.mapleleafcd.tables.GenresTable;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class addItem extends Tab {
    private static addItem instance;

    public TableView tableView;
    private addItem(){
        this.setText("Add Item");

        //1. initialize container
        GridPane root = new GridPane();

        root.setPadding(new Insets(10,10,10,10));
        root.setVgap(10);
        root.setHgap(10);

        //2. initialize AlbumsTable
        AlbumsTable albumsTable = new AlbumsTable();


        //ComboBox: lists out items and allows for selection of items
        ComboBox<Albums> comboName = new ComboBox<>();

        //sets items from arrayList to ComboBox
        comboName.setItems(FXCollections.observableArrayList(albumsTable.getAllAlbums()));

        System.out.println(albumsTable.getAlbum(0));
        //System.out.println(comboName.getSelectionModel().getSelectedItem().getName());

        //3. initialize column name
        Text nameText = new Text("Name: ");
        TextField albumName = new TextField();
        root.add(nameText, 0, 0);
        root.add(albumName, 1, 0);

        //4. initialize second column name
        Text numSongsText = new Text("# Songs: ");
        TextField numSongs = new TextField();
        root.add(numSongsText,0,1);
        root.add(numSongs, 1, 1);

        Text releaseDateText = new Text("Release Date (yyyy-mm-dd): ");
        TextField releaseDate = new TextField();
        root.add(releaseDateText,0,2);
        root.add(releaseDate, 1, 2);

        Text lengthText = new Text("Length (mm:ss): ");
        TextField length = new TextField();
        root.add(lengthText,0,3);
        root.add(length, 1, 3);

        Text priceText = new Text("Price: ");
        TextField price = new TextField();
        root.add(priceText,0,4);
        root.add(price, 1, 4);

        Text ratingText = new Text("Rating: ");
        TextField rating = new TextField();
        root.add(ratingText,0,5);
        root.add(rating, 1, 5);

        Text genreText = new Text("Genre: ");
        ComboBox genre = new ComboBox<>();
        ArrayList<String> listOfGenres = new ArrayList<>();
        GenresTable genresTable = new GenresTable();
        for(Genres name : genresTable.getAllGenres()){
            listOfGenres.add(name.getGenre());
        }

        genre.setItems(FXCollections.observableArrayList(listOfGenres));
        root.add(genreText,0,6);
        root.add(genre,1,6);




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
