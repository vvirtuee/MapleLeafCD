package com.example.mapleleafcd.tabs;

import com.example.mapleleafcd.HelloApplication;
import com.example.mapleleafcd.database.DBConst;
import com.example.mapleleafcd.database.Database;
import com.example.mapleleafcd.pojo.Albums;
import com.example.mapleleafcd.pojo.Artists;
import com.example.mapleleafcd.pojo.Genres;
import com.example.mapleleafcd.pojo.Studios;
import com.example.mapleleafcd.prompts.Prompt;
import com.example.mapleleafcd.tables.AlbumsTable;
import com.example.mapleleafcd.tables.ArtistsTable;
import com.example.mapleleafcd.tables.GenresTable;
import com.example.mapleleafcd.tables.StudiosTable;
import com.example.mapleleafcd.verifications.Verifier;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

public class addItem extends Tab {
    private static addItem instance;

    public TableView tableView;

    Prompt root = new Prompt();
    GridPane albumsRoot = new GridPane();
    GridPane genresRoot = new GridPane();
    GridPane artistsRoot = new GridPane();
    GridPane studiosRoot = new GridPane();


    Database db = Database.getInstance();
    private addItem(){
        this.setText("Add Item");

        //initialize prompt control
        root.initializePrompt(this, albumsRoot, genresRoot, artistsRoot, studiosRoot);

        /** ALBUMS ROOT **/
        albumsRoot.setPadding(new Insets(10,10,10,10));
        albumsRoot.setVgap(10);
        albumsRoot.setHgap(10);

        //2. initialize AlbumsTable
        AlbumsTable albumsTable = new AlbumsTable();

        Button submit = new Button("Add Album");


        //3. initialize verifier
        Verifier v = new Verifier();


        //ComboBox: lists out items and allows for selection of items
        ComboBox<Albums> comboName = new ComboBox<>();

        //sets items from arrayList to ComboBox
        comboName.setItems(FXCollections.observableArrayList(albumsTable.getAllAlbums()));
        //3. initialize column name
        Text nameText = new Text("Name: ");
        TextField albumName = new TextField();
        Text textSpoiler = new Text("");
        textSpoiler.setVisible(false);
        textSpoiler.setFill(Paint.valueOf("FF0000"));

        v.nameVerify(albumName,textSpoiler,submit);

        albumsRoot.add(nameText, 0, 0);
        albumsRoot.add(albumName, 1, 0);
        albumsRoot.add(textSpoiler,2,0);

        /** Num Songs **/
        Text numSongsText = new Text("# Songs: ");
        TextField numSongs = new TextField();
        Text numSongsSpoiler = new Text("");
        numSongsSpoiler.setVisible(false);
        numSongsSpoiler.setFill(Paint.valueOf("FF0000"));

        v.numSongsVerify(numSongs,numSongsSpoiler, submit);

        albumsRoot.add(numSongsText,0,1);
        albumsRoot.add(numSongs, 1, 1);
        albumsRoot.add(numSongsSpoiler,2,1);

        /** Release Date **/
        Text releaseDateText = new Text("Release Date (yyyy-mm-dd): ");
        TextField releaseDate = new TextField();
        Text dateCreatedSpoilerText = new Text();
        dateCreatedSpoilerText.setVisible(false);
        dateCreatedSpoilerText.setFill(Paint.valueOf("FF0000"));

        v.dateVerify(releaseDate,dateCreatedSpoilerText, submit);

        albumsRoot.add(releaseDateText,0,2);
        albumsRoot.add(releaseDate, 1, 2);
        albumsRoot.add(dateCreatedSpoilerText,2,2);

        /** Length **/

        Text lengthText = new Text("Length (hh:mm:ss): ");
        TextField length = new TextField();
        Text lengthSpoilerText = new Text();
        lengthSpoilerText.setVisible(false);
        lengthSpoilerText.setFill(Paint.valueOf("FF0000"));

        v.lengthVerify(length, lengthSpoilerText, submit);

        albumsRoot.add(lengthText,0,3);
        albumsRoot.add(length, 1, 3);
        albumsRoot.add(lengthSpoilerText,2,3);


        /** Price **/

        Text priceText = new Text("Price: ");
        TextField price = new TextField();
        Text priceSpoilerText = new Text("");
        priceSpoilerText.setVisible(false);
        priceSpoilerText.setFill(Paint.valueOf("FF0000"));
        albumsRoot.add(priceText,0,4);
        albumsRoot.add(price, 1, 4);
        albumsRoot.add(priceSpoilerText,2,4);

        v.priceVerify(price,priceSpoilerText, submit);

        /** Rating **/

        Text ratingText = new Text("Rating: ");
        HBox ratingContainerBEHIND = new HBox();
        ratingContainerBEHIND.setPickOnBounds(true);
        HBox ratingContainerINFRONT = new HBox();
        ratingContainerINFRONT.setMouseTransparent(true);

        ImageView[] starContainer = {
                new ImageView(new Image(HelloApplication.class.getResourceAsStream("/images/starIcon.png"))),
                new ImageView(new Image(HelloApplication.class.getResourceAsStream("/images/starIcon.png"))),
                new ImageView(new Image(HelloApplication.class.getResourceAsStream("/images/starIcon.png"))),
                new ImageView(new Image(HelloApplication.class.getResourceAsStream("/images/starIcon.png"))),
                new ImageView(new Image(HelloApplication.class.getResourceAsStream("/images/starIcon.png"))),
                new ImageView(new Image(HelloApplication.class.getResourceAsStream("/images/starIcon.png"))),
                new ImageView(new Image(HelloApplication.class.getResourceAsStream("/images/starIcon.png"))),
                new ImageView(new Image(HelloApplication.class.getResourceAsStream("/images/starIcon.png"))),
                new ImageView(new Image(HelloApplication.class.getResourceAsStream("/images/starIcon.png"))),
                new ImageView(new Image(HelloApplication.class.getResourceAsStream("/images/starIcon.png")))

        };

        Double ratio = 512.0/488.0;
        ColorAdjust faded = new ColorAdjust();
        faded.setBrightness(-0.5);
        ColorAdjust selected = new ColorAdjust();
        selected.setBrightness(0);

        AtomicReference<Integer> selectionID = new AtomicReference<>();

        for(int i = 0;i < 10; i++){
            starContainer[i].setLayoutX(i*25);
            starContainer[i].setFitWidth(Math.round(ratio * 25));
            starContainer[i].setFitHeight(Math.round(1/ratio * 25));
            starContainer[i].setPickOnBounds(true);


            starContainer[i].setId(String.valueOf(i));
            if(i < 5){
                starContainer[i].setEffect(faded);
                ratingContainerBEHIND.getChildren().add(starContainer[i]);
            }else{
                starContainer[i].setEffect(selected);
                starContainer[i].setMouseTransparent(true);
                ratingContainerINFRONT.getChildren().add(starContainer[i]);
            }
        }

        ratingContainerINFRONT.setClip(new Rectangle(0, 0, 65, 25));


        AtomicReference<Double> rating = new AtomicReference<>((double) 2.5);
        ratingContainerBEHIND.setOnMouseClicked(e->{
            int id = 0;
            try{
                selectionID.set(Integer.parseInt(e.getTarget().toString().substring(13, 14)));
                id = selectionID.getPlain();
            } catch(StringIndexOutOfBoundsException e1){
                System.out.println("Failed to access element");
            }
            if(id < 5) {
                double clippedValue = (2.5) * (Math.round(Math.abs(e.getX() / 2.5)));
                ratingContainerINFRONT.setClip(new Rectangle(0, 0, clippedValue, 25));
                rating.set(5*(clippedValue / (starContainer[0].getFitWidth() * 5)));
            }
            DecimalFormat df = new DecimalFormat("0.0");
            rating.set(Double.parseDouble(df.format(rating.getPlain())));
            if(rating.getPlain() > 5){
                rating.set(5.0);
            }
        });

        albumsRoot.add(ratingText,0,5);
        albumsRoot.add(ratingContainerBEHIND, 1, 5);
        albumsRoot.add(ratingContainerINFRONT,1,5);


        /** Genre **/

        Text genreText = new Text("Genre: ");
        ComboBox genre = new ComboBox<>();
        ArrayList<String> listOfGenres = new ArrayList<>();
        GenresTable genresTable = new GenresTable();
        for(Genres name : genresTable.getAllGenres()){
            listOfGenres.add(name.getGenre());
        }

        genre.setItems(FXCollections.observableArrayList(listOfGenres));
        genre.getSelectionModel().selectFirst();
        albumsRoot.add(genreText,0,6);
        albumsRoot.add(genre,1,6);

        /** Artist **/

        Text artistText = new Text("Artist: ");
        ComboBox artist = new ComboBox<>();
        ArrayList<String> listOfArtists = new ArrayList<>();
        ArtistsTable artistsTable = new ArtistsTable();
        for(Artists name : artistsTable.getAllArtists()){
            listOfArtists.add(name.getArtist());
        }

        artist.setItems(FXCollections.observableArrayList(listOfArtists));
        artist.getSelectionModel().selectFirst();
        albumsRoot.add(artistText,0,7);
        albumsRoot.add(artist,1,7);

        /** Studio **/

        Text studioText = new Text("Studio: ");
        ComboBox studio = new ComboBox<>();
        ArrayList<String> listOfStudios = new ArrayList<>();
        StudiosTable studiosTable = new StudiosTable();
        for(Studios name : studiosTable.getAllStudios()){
            listOfStudios.add(name.getStudio());
        }

        studio.setItems(FXCollections.observableArrayList(listOfStudios));
        studio.getSelectionModel().selectFirst();
        albumsRoot.add(studioText,0,8);
        albumsRoot.add(studio,1,8);

        Text updatedSpoilerText = new Text("");
        updatedSpoilerText.setVisible(false);
        updatedSpoilerText.setFill(Paint.valueOf("00FF00"));
        albumsRoot.add(updatedSpoilerText,0,10);

        submit.setDisable(true);

        submit.setOnAction(e->{
            int genreID = 0;
            int artistID = 0;
            int studioID = 0;
            for(Genres g : genresTable.getAllGenres()){
                if(g.getGenre().equals(genre.getValue())){
                    genreID = g.getId();
                }
            }
            for(Artists a : artistsTable.getAllArtists()){
                if(a.getArtist().equals(artist.getValue())){
                    artistID = a.getId();
                }
            }
            for(Studios s : studiosTable.getAllStudios()){
                if(s.getStudio().equals(studio.getValue())){
                    studioID = s.getId();
                }
            }
            String query = "INSERT INTO " + DBConst.TABLE_ALBUMS + "(" +
                    DBConst.ALBUMS_COLUMN_ID + ", " +
                    DBConst.ALBUMS_COLUMN_NAME + ", " +
                    DBConst.ALBUMS_COLUMN_NUMSONGS + ", " +
                    DBConst.ALBUMS_COLUMN_RELEASEDATE + ", " +
                    DBConst.ALBUMS_COLUMN_LENGTH + ", " +
                    DBConst.ALBUMS_COLUMN_PRICE + ", " +
                    DBConst.ALBUMS_COLUMN_RATING + ", " +
                    DBConst.ALBUMS_COLUMN_GENREID + ", " +
                    DBConst.ALBUMS_COLUMN_ARTISTID + ", " +
                    DBConst.ALBUMS_COLUMN_STUDIOID + ") VALUES (" +
                    "NULL, " +
                    "\'" + albumName.getText() + "\'" + ", " +
                    numSongs.getText() + ", " +
                    "\'" + releaseDate.getText() + "\'" + ", " +
                    "\'" + length.getText() + "\'" + ", " +
                    price.getText() + ", " +
                    rating.getPlain() + ", " +
                    genreID + ", " +
                    artistID + ", " +
                    studioID + ");";


            try{
                Statement addItem = db.getConnection().createStatement();
                addItem.executeUpdate(query);
                updatedSpoilerText.setVisible(true);
                updatedSpoilerText.setText("Added " + albumName.getText() + " to " + DBConst.TABLE_ALBUMS);

                //reset all values if successful query
                albumName.setText("");
                numSongs.setText("");
                releaseDate.setText("");
                length.setText("");
                price.setText("");
                rating.set(2.5);

                //reset clip position
                ratingContainerINFRONT.setClip(new Rectangle(0, 0, 65, 25));

                //re-update button
                v.verify(submit);


            }catch(Exception e1){
                e1.printStackTrace();
                System.out.println("Failed to establish connection.");
            }

        });

        albumsRoot.add(submit, 0,9);

        /** GENRES ROOT **/
        genresRoot.setPadding(new Insets(10,10,10,10));
        genresRoot.setHgap(10);
        genresRoot.setVgap(10);

        /** Genre Name **/
        Text genreText1 = new Text("Genres: ");
        TextField genreField = new TextField();

        Text genreSpoiler = new Text("");
        genreSpoiler.setVisible(false);
        genreSpoiler.setFill(Paint.valueOf("FF0000"));
        genresRoot.add(genreText1,0,0);
        genresRoot.add(genreField,1,0);
        genresRoot.add(genreSpoiler,2,0);
        Button submit1 = new Button("Add Genre");
        genresRoot.add(submit1,0,1);

        /** Genre Button **/
        v.nameVerify(genreField,genreSpoiler,submit1);

        Text genreUpdated = new Text("");
        genreUpdated.setVisible(false);
        genreUpdated.setFill(Paint.valueOf("00FF00"));
        genresRoot.add(genreUpdated,0,2);
        submit1.setOnAction(e->{
            String query = "INSERT INTO " + DBConst.TABLE_GENRES + "(" +
                    DBConst.GENRES_COLUMN_ID + ", " +
                    DBConst.GENRES_COLUMN_GENRE + ") VALUES (" +
                    "NULL, " +
                    "\'" + genreField.getText() + "\'" + ");";
            try{
                Statement addItem = db.getConnection().createStatement();
                addItem.executeUpdate(query);
                genreUpdated.setVisible(true);
                genreUpdated.setText("Added " + genreField.getText() + " to " + DBConst.TABLE_GENRES);

                //reset all values if successful query
                genreField.setText("");

                //re-update button
                v.verify(submit1);


            }catch(Exception e1){
                e1.printStackTrace();
                System.out.println("Failed to establish connection.");
            }
        });

        /** ARTIST ROOT **/
        artistsRoot.setPadding(new Insets(10,10,10,10));
        artistsRoot.setHgap(10);
        artistsRoot.setVgap(10);

        /** Artist Name **/

        Text artistText1 = new Text("Artist: ");
        TextField artistField = new TextField();
        Text artistFieldSpoiler = new Text("");
        artistFieldSpoiler.setVisible(false);
        artistFieldSpoiler.setFill(Paint.valueOf("FF0000"));
        artistsRoot.add(artistText1,0,0);
        artistsRoot.add(artistField,1,0);
        artistsRoot.add(artistFieldSpoiler,2,0);

        /** Artist Birthday **/
        Text birthday = new Text("Birthday (yyyy-mm-dd): ");
        TextField birthdayField = new TextField();
        Text birthdaySpoiler = new Text("");
        birthdaySpoiler.setVisible(false);
        birthdaySpoiler.setFill(Paint.valueOf("FF0000"));
        artistsRoot.add(birthday,0,1);
        artistsRoot.add(birthdayField,1,1);
        artistsRoot.add(birthdaySpoiler,2,1);

        Button submit2 = new Button("Add Artist");
        artistsRoot.add(submit2,0,2);

        Text artistSpoiler = new Text("");
        artistSpoiler.setVisible(false);
        artistSpoiler.setFill(Paint.valueOf("00FF00"));
        artistsRoot.add(artistSpoiler,0,3);

        /** Artist Button **/
        v.nameVerify(artistField, artistFieldSpoiler,submit2);
        v.dateVerify(birthdayField,birthdaySpoiler, submit2);

        submit2.setOnAction(e->{
            String query = "INSERT INTO " + DBConst.TABLE_ARTISTS + "(" +
                    DBConst.ARTISTS_COLUMN_ID + ", " +
                    DBConst.ARTISTS_COLUMN_ARTIST + ", " +
                    DBConst.ARTISTS_COLUMN_BIRTHDAY + ") VALUES (" +
                    "NULL, " +
                    "\'" + artistField.getText() + "\'" + ", " +
                    "\'" + birthdayField.getText() + "\'" + ");";
            try{
                Statement addItem = db.getConnection().createStatement();
                addItem.executeUpdate(query);
                artistSpoiler.setVisible(true);
                artistSpoiler.setText("Added " + artistField.getText() + " to " + DBConst.TABLE_ARTISTS);

                //reset all values if successful query
                artistField.setText("");
                birthdayField.setText("");

                //re-update button
                v.verify(submit2);


            }catch(Exception e1){
                e1.printStackTrace();
                System.out.println("Failed to establish connection.");
            }
        });

        /** STUDIOS ROOT **/
        studiosRoot.setPadding(new Insets(10,10,10,10));
        studiosRoot.setHgap(10);
        studiosRoot.setVgap(10);

        /** Studio Name **/

        Text studioText1 = new Text("Studio: ");
        TextField studioField = new TextField();
        Text studioFieldSpoiler = new Text("");
        studioFieldSpoiler.setVisible(false);
        studioFieldSpoiler.setFill(Paint.valueOf("FF0000"));
        studiosRoot.add(studioText1,0,0);
        studiosRoot.add(studioField,1,0);
        studiosRoot.add(studioFieldSpoiler,2,0);

        /** Studio Date Established **/
        Text dateEstablishedText = new Text("Date Created (yyyy-mm-dd): ");
        TextField dateEstablishedField = new TextField();
        Text dateEstablishedSpoiler = new Text("");
        dateEstablishedSpoiler.setVisible(false);
        dateEstablishedSpoiler.setFill(Paint.valueOf("FF0000"));
        studiosRoot.add(dateEstablishedText,0,1);
        studiosRoot.add(dateEstablishedField,1,1);
        studiosRoot.add(dateEstablishedSpoiler,2,1);

        Button submit3 = new Button("Add Studio");
        studiosRoot.add(submit3,0,2);

        Text studioSpoiler = new Text("");
        studioSpoiler.setVisible(false);
        studioSpoiler.setFill(Paint.valueOf("00FF00"));
        studiosRoot.add(studioSpoiler,0,3);

        /** Studio Button **/
        v.nameVerify(studioField, studioFieldSpoiler,submit2);
        v.dateVerify(dateEstablishedField,dateEstablishedSpoiler, submit2);


        submit3.setOnAction(e->{
            String query = "INSERT INTO " + DBConst.TABLE_STUDIOS + "(" +
                    DBConst.STUDIOS_COLUMN_ID + ", " +
                    DBConst.STUDIOS_COLUMN_STUDIO + ", " +
                    DBConst.STUDIOS_COLUMN_DATECREATED + ") VALUES (" +
                    "NULL, " +
                    "\'" + studioField.getText() + "\'" + ", " +
                    "\'" + dateEstablishedField.getText() + "\'" + ");";
            try{
                Statement addItem = db.getConnection().createStatement();
                addItem.executeUpdate(query);
                studioSpoiler.setVisible(true);
                studioSpoiler.setText("Added " + studioField.getText() + " to " + DBConst.TABLE_STUDIOS);

                //reset all values if successful query
                studioField.setText("");
                dateEstablishedField.setText("");

                //re-update button
                v.verify(submit3);


            }catch(Exception e1){
                e1.printStackTrace();
                System.out.println("Failed to establish connection.");
            }
        });

        //each time tab is switched
        this.setOnSelectionChanged(e->{

            //remove all spoilers for albums

            /** Albums Spoilers **/
            textSpoiler.setVisible(false);
            numSongsSpoiler.setVisible(false);
            dateCreatedSpoilerText.setVisible(false);
            priceSpoilerText.setVisible(false);
            lengthSpoilerText.setVisible(false);
            updatedSpoilerText.setVisible(false);
            artistSpoiler.setVisible(false);
            studioSpoiler.setVisible(false);
            dateEstablishedSpoiler.setVisible(false);

            /** Genres Spoilers **/
            genreSpoiler.setVisible(false);

            this.setContent(root.initializePrompt(this,albumsRoot,genresRoot,artistsRoot,studiosRoot));

        });

        this.setContent(root.initializePrompt(this,albumsRoot,genresRoot,artistsRoot,studiosRoot));


    }

    public static addItem getInstance(){
        if(instance == null){
            instance = new addItem();
        }
        return instance;
    }
}
