package com.example.mapleleafcd.tabs;

import com.example.mapleleafcd.pojo.Albums;
import com.example.mapleleafcd.pojo.Artists;
import com.example.mapleleafcd.pojo.Genres;
import com.example.mapleleafcd.pojo.Studios;
import com.example.mapleleafcd.tables.AlbumsTable;
import com.example.mapleleafcd.tables.ArtistsTable;
import com.example.mapleleafcd.tables.GenresTable;
import com.example.mapleleafcd.tables.StudiosTable;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class removeItem extends Tab {
    private static removeItem instance;
    private removeItem(){
        this.setText("Remove Item");

        //1. initialize containers
        GridPane promptRoot = new GridPane();
        GridPane albumsRoot = new GridPane();
        GridPane genresRoot = new GridPane();
        GridPane artistsRoot = new GridPane();
        GridPane studiosRoot = new GridPane();

        /* Prompt */
        promptRoot.setPadding(new Insets(10,10,10,10));
        promptRoot.setVgap(5);
        promptRoot.setHgap(10);

        Text prompt = new Text("Please select a table to remove an item from:");

        //Buttons for remove item
        Button albumsBtn = new Button("Albums");
        Button genresBtn = new Button("Genres");
        Button artistsBtn = new Button("Artists");
        Button studiosBtn = new Button("Studios");

        Button returnBtn = new Button("Return");

        promptRoot.add(prompt,0,0);


        HBox promptVBox = new HBox();
        promptVBox.setSpacing(10);
        promptVBox.getChildren().addAll(albumsBtn,genresBtn,artistsBtn,studiosBtn);
        promptRoot.add(promptVBox,0,1);

        //Album table initialized
        AlbumsTable albumsTable = new AlbumsTable();
        //ComboBox: lists out items and allows for selection of items
        ComboBox comboName = new ComboBox<>();
        ArrayList<String> listOfAlbums = new ArrayList<>();
        for(Albums album : albumsTable.getAllAlbums()){
            listOfAlbums.add(album.getName());
        }

        //sets items from arrayList to ComboBox
        comboName.setItems(FXCollections.observableArrayList(listOfAlbums));
        comboName.getSelectionModel().selectFirst();

        System.out.println(albumsTable.getAlbum(0));


        //Genres Table initialized
        GenresTable genresTable = new GenresTable();

        //ComboBox: lists out items and allows for selection of items
        ComboBox genresCombo = new ComboBox<>();
        ArrayList<String> listOfGenres = new ArrayList<>();
        for(Genres genre : genresTable.getAllGenres()){
            listOfGenres.add(genre.getGenre());
        }

        //sets items from arrayList to ComboBox
        genresCombo.setItems(FXCollections.observableArrayList(listOfGenres));
        genresCombo.getSelectionModel().selectFirst();

        System.out.println(genresTable.getGenre(0));


        //Artists Table initialized
        ArtistsTable artistsTable = new ArtistsTable();

        //ComboBox: lists out items and allows for selection of items
        ComboBox artistsCombo = new ComboBox<>();
        ArrayList<String> listOfArtists = new ArrayList<>();
        for(Artists artist : artistsTable.getAllArtists()){
            listOfArtists.add(artist.getArtist());
        }

        //sets items from arrayList to ComboBox
        artistsCombo.setItems(FXCollections.observableArrayList(listOfArtists));
        artistsCombo.getSelectionModel().selectFirst();

        System.out.println(artistsTable.getArtist(0));


        //Studios Table initialized
        StudiosTable studiosTable = new StudiosTable();

        //ComboBox: lists out items and allows for selection of items
        ComboBox studiosCombo = new ComboBox<>();
        ArrayList<String> listOfStudios = new ArrayList<>();
        for(Studios studio : studiosTable.getAllStudios()){
            listOfStudios.add(studio.getStudio());
        }

        //sets items from arrayList to ComboBox
        studiosCombo.setItems(FXCollections.observableArrayList(listOfStudios));
        studiosCombo.getSelectionModel().selectFirst();

        System.out.println(studiosTable.getStudio(0));


        /** Button Navigation to different roots **/
        albumsBtn.setOnAction(e->{
            this.setContent(albumsRoot);
        });
        genresBtn.setOnAction(e->{
            this.setContent(genresRoot);
        });
        artistsBtn.setOnAction(e->{
            this.setContent(artistsRoot);
        });
        studiosBtn.setOnAction(e->{
            this.setContent(studiosRoot);
        });

        returnBtn.setOnAction(e->{
            this.setContent(promptRoot);
        });

        /** ALBUMS ROOT**/
        albumsRoot.setPadding(new Insets(10,10,10,10));
        albumsRoot.setHgap(10);
        albumsRoot.setVgap(5);

        //initializing the columns (i.e numSongs, releaseDate)
        Text name = new Text("Name: ");
        Text numSongs = new Text("# of Songs: ");
        Text releaseDate = new Text("Release Date: ");
        Text length = new Text("Length of Album: ");
        Text price = new Text("Price: $");
        Text genre = new Text("Genre: ");
        Text artist = new Text("Artist: ");
        Text studio = new Text("Studio: ");

        Text songsNum = new Text();
        Text dateReleased = new Text();
        Text albumLength = new Text();
        Text albumPrice = new Text();
        Text albumGenre = new Text();
        Text artistAlbum = new Text();
        Text studioAlbum = new Text();


        comboName.setOnAction(e->{
            int selectedIndex = comboName.getSelectionModel().getSelectedIndex();
            songsNum.setText(albumsTable.getAlbum(selectedIndex).getName());
        });

        Button albumRemoved = new Button("Remove Album");
        albumRemoved.setAlignment(Pos.BOTTOM_LEFT);


        albumsRoot.add(albumRemoved, 0, 30);
        albumsRoot.add(name, 0, 0);
        albumsRoot.add(comboName, 1, 0);
        albumsRoot.add(numSongs,0,1);
        albumsRoot.add(songsNum, 1, 1);
        albumsRoot.add(releaseDate,0,2);
        albumsRoot.add(dateReleased, 1, 2);
        albumsRoot.add(length,0,3);
        albumsRoot.add(albumLength, 1, 3);
        albumsRoot.add(price,0,4);
        albumsRoot.add(albumPrice, 1, 4);
        albumsRoot.add(genre,0,5);
        albumsRoot.add(albumGenre, 1, 5);
        albumsRoot.add(artist,0,6);
        albumsRoot.add(artistAlbum, 1, 6);
        albumsRoot.add(studio,0,7);
        albumsRoot.add(studioAlbum, 1, 7);
        albumsRoot.add(returnBtn, 0, 8);


        /** GENRES ROOT**/
        genresRoot.setPadding(new Insets(10,10,10,10));
        genresRoot.setHgap(10);
        genresRoot.setVgap(5);

        Button genreRemoved = new Button("Remove Genre");
        genreRemoved.setAlignment(Pos.BOTTOM_LEFT);

        Text genre1 = new Text("Genre: ");


        genresRoot.add(genre1, 0 , 0);
        genresRoot.add(genresCombo, 1, 0);
        genresRoot.add(genreRemoved, 0, 1);
        genresRoot.add(returnBtn, 0, 0);


        /** ARTIST ROOT**/
        artistsRoot.setPadding(new Insets(10,10,10,10));
        artistsRoot.setHgap(10);
        artistsRoot.setVgap(5);

        Button artistRemoved = new Button("Remove Artist");
        artistRemoved.setAlignment(Pos.BOTTOM_LEFT);

        Text artist1 = new Text("Artist: ");
        Text birthday = new Text("Birthday: ");
        Text birthdayField = new Text("");


        artistsRoot.add(artist1, 0 , 0);
        artistsRoot.add(artistsCombo, 1, 0);
        artistsRoot.add(birthday,0,1);
        artistsRoot.add(birthdayField,1,1);
        artistsRoot.add(artistRemoved, 0, 2);
        artistsRoot.add(returnBtn, 0, 0);

        /** STUDIO ROOT**/
        studiosRoot.setPadding(new Insets(10,10,10,10));
        studiosRoot.setHgap(10);
        studiosRoot.setVgap(5);


        Button studioRemoved = new Button("Remove Studio");
        //when pressed, the studio should be removed from the array
//        studiosCombo.setOnAction(
//                studiosCombo.getChildrenUnmodifiable().remove(studiosTable)
//        );


        studioRemoved.setAlignment(Pos.BOTTOM_LEFT);

        Text studio1 = new Text("Studio: ");
        Text studioField = new Text("");
        Text dateCreated = new Text("Date Created:");
        Text dateCreatedField = new Text("");

        studiosRoot.add(studio1, 0 , 0);
        studiosRoot.add(studiosCombo, 1, 0);

        studiosRoot.add(studioField,1,1);
        studiosRoot.add(dateCreated,0,2);
        studiosRoot.add(dateCreatedField,1,2);

        studiosRoot.add(studioRemoved, 0, 10);
        studiosRoot.add(returnBtn, 1, 10);

        this.setContent(promptRoot);
    }

    public static removeItem getInstance(){
        if(instance == null){
            instance = new removeItem();
        }
        return instance;
    }
}

