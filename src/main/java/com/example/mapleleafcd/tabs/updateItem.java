package com.example.mapleleafcd.tabs;

import com.example.mapleleafcd.HelloApplication;
import com.example.mapleleafcd.database.DBConst;
import com.example.mapleleafcd.database.Database;
import com.example.mapleleafcd.pojo.*;
import com.example.mapleleafcd.tables.AlbumsTable;
import com.example.mapleleafcd.tables.ArtistsTable;
import com.example.mapleleafcd.tables.GenresTable;
import com.example.mapleleafcd.tables.StudiosTable;
import com.example.mapleleafcd.verifications.Verifier;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

public class updateItem extends Tab {
    private static updateItem instance;

    TableView<Albums> table1 = new TableView();
    TableView<Genres> table2 = new TableView();
    TableView<Artists> table3 = new TableView();
    TableView<Studios> table4 = new TableView();

    Database db = Database.getInstance();
    private updateItem(){
        this.setText("Update Item");

        GridPane promptRoot = new GridPane();
        GridPane albumsRoot = new GridPane();
        albumsRoot.setPadding(new Insets(10,10,10,10));


        GridPane genresRoot = new GridPane();
        GridPane artistsRoot = new GridPane();
        GridPane studiosRoot = new GridPane();

        genresRoot.setPadding(new Insets(10,10,10,10));
        artistsRoot.setPadding(new Insets(10,10,10,10));
        studiosRoot.setPadding(new Insets(10,10,10,10));


        GenresTable genres = new GenresTable();
        ArtistsTable artists = new ArtistsTable();
        StudiosTable studios = new StudiosTable();

        /** PROMPT ROOT **/
        promptRoot.setPadding(new Insets(10,10,10,10));
        promptRoot.setVgap(10);
        promptRoot.setHgap(10);
        Text prompt = new Text("Please select a table to add an item to:");
        Button albumsBtn = new Button("Albums");
        Button genresBtn = new Button("Genres");
        Button artistsBtn = new Button("Artists");
        Button studiosBtn = new Button("Studios");
        promptRoot.add(prompt,0,0);

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

        HBox promptVBox = new HBox();
        promptVBox.setSpacing(10);
        promptVBox.getChildren().addAll(albumsBtn,genresBtn,artistsBtn,studiosBtn);
        promptRoot.add(promptVBox,0,1);

        /** Generation of Table 'Albums' **/


        table1.setEditable(true);
        TableColumn<Albums, String> name = new TableColumn<>("Name");
        name.setCellValueFactory(e->new SimpleStringProperty(e.getValue().getName()));
        TableColumn<Albums, String> numSongs = new TableColumn("# Songs");
        numSongs.setCellValueFactory(e->new SimpleStringProperty(String.valueOf(e.getValue().getNumSongs())));
        TableColumn<Albums, String> releaseDate = new TableColumn("Release Date");
        releaseDate.setCellValueFactory(e->new SimpleStringProperty(e.getValue().getReleaseDate()));
        TableColumn<Albums, String> length = new TableColumn("Length");
        length.setCellValueFactory(e->new SimpleStringProperty(e.getValue().getLength()));
        TableColumn<Albums, String> price = new TableColumn("Price");
        price.setCellValueFactory(e->new SimpleStringProperty(String.valueOf(e.getValue().getPrice())));
        TableColumn<Albums, String> rating = new TableColumn("Rating");
        rating.setCellValueFactory(e->new SimpleStringProperty(String.valueOf(e.getValue().getRating())));
        TableColumn<Albums, String> genre = new TableColumn("Genre");
        genre.setCellValueFactory(e->new SimpleStringProperty(genres.getGenre(Integer.parseInt(e.getValue().getGenreID())).getGenre()));
        TableColumn<Albums, String> artist = new TableColumn("Artist");
        artist.setCellValueFactory(e->new SimpleStringProperty(artists.getArtist(Integer.parseInt(e.getValue().getArtistID())).getArtist()));
        TableColumn<Albums, String> studio = new TableColumn("Studio");
        studio.setCellValueFactory(e->new SimpleStringProperty(studios.getStudio(Integer.parseInt(e.getValue().getStudioID())).getStudio()));
        ObservableList<Albums> albums = FXCollections.observableArrayList();
        AlbumsTable albumsTable = new AlbumsTable();
        for(Albums a: albumsTable.getAllAlbums()){
            albums.add(a);
            System.out.println("name: " + a.getName());
        }

        table1.getColumns().addAll(name,numSongs,releaseDate,length,price,rating,genre,artist,studio);

        table1.setItems(albums);

        /** Container popup (Albums) **/

        GridPane containerForItemData = new GridPane();
        containerForItemData.setPadding(new Insets(10,10,10,10));
        containerForItemData.setVgap(10);
        containerForItemData.setHgap(10);
        containerForItemData.setVisible(true);

        /** Albums Data **/
        //3. initialize column name
        Text nameText = new Text("Name: ");
        TextField albumName = new TextField();

        Text pseudo = new Text();

        Verifier v = new Verifier();

        Button submit = new Button("Update Album");
        submit.setDisable(true);



        containerForItemData.add(nameText, 0, 0);
        containerForItemData.add(albumName, 1, 0);

        /** Num Songs **/
        Text numSongsText = new Text("# Songs: ");
        TextField numSongs0 = new TextField();


        containerForItemData.add(numSongsText,0,1);
        containerForItemData.add(numSongs0, 1, 1);

        /** Release Date **/
        Text releaseDateText = new Text("Release Date (yyyy-mm-dd): ");
        TextField releaseDate0 = new TextField();


        containerForItemData.add(releaseDateText,0,2);
        containerForItemData.add(releaseDate0, 1, 2);

        /** Length **/

        Text lengthText = new Text("Length (hh:mm:ss): ");
        TextField length0 = new TextField();


        containerForItemData.add(lengthText,0,3);
        containerForItemData.add(length0, 1, 3);


        /** Price **/

        Text priceText = new Text("Price: ");
        TextField price0 = new TextField();
        containerForItemData.add(priceText,0,4);
        containerForItemData.add(price0, 1, 4);


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

        AtomicReference<Double> rating0 = new AtomicReference<>((double) 2.5);
        ratingContainerBEHIND.setOnMouseClicked(e->{
            int id = 0;
            try{
                selectionID.set(Integer.parseInt(e.getTarget().toString().substring(13, 14)));
                id = selectionID.getPlain();
            } catch(StringIndexOutOfBoundsException e1){
                System.out.println("Failed to access element");
            }
            System.out.println("id: " + id);
            if(id < 5) {
                System.out.println(starContainer[0].getFitWidth());

                double clippedValue = (2.5) * (Math.round(Math.abs(e.getX() / 2.5)));
                ratingContainerINFRONT.setClip(new Rectangle(0, 0, clippedValue, 25));
                rating0.set(clippedValue / (starContainer[0].getFitWidth()));
            }
        });

        containerForItemData.add(ratingText,0,5);
        containerForItemData.add(ratingContainerBEHIND, 1, 5);
        containerForItemData.add(ratingContainerINFRONT,1,5);


        /** Genre **/

        Text genreText = new Text("Genre: ");
        ComboBox genre0 = new ComboBox<>();
        ArrayList<String> listOfGenres = new ArrayList<>();
        GenresTable genresTable = new GenresTable();
        for(Genres name0 : genresTable.getAllGenres()){
            listOfGenres.add(name0.getGenre());
        }

        genre0.setItems(FXCollections.observableArrayList(listOfGenres));
        genre0.getSelectionModel().selectFirst();
        containerForItemData.add(genreText,0,6);
        containerForItemData.add(genre0,1,6);

        /** Artist **/

        Text artistText = new Text("Artist: ");
        ComboBox artist0 = new ComboBox<>();
        ArrayList<String> listOfArtists = new ArrayList<>();
        ArtistsTable artistsTable = new ArtistsTable();
        for(Artists name0 : artistsTable.getAllArtists()){
            listOfArtists.add(name0.getArtist());
        }

        artist0.setItems(FXCollections.observableArrayList(listOfArtists));
        artist0.getSelectionModel().selectFirst();
        containerForItemData.add(artistText,0,7);
        containerForItemData.add(artist0,1,7);

        /** Studio **/

        Text studioText = new Text("Studio: ");
        ComboBox studio0 = new ComboBox<>();
        ArrayList<String> listOfStudios = new ArrayList<>();
        StudiosTable studiosTable = new StudiosTable();
        for(Studios name0 : studiosTable.getAllStudios()){
            listOfStudios.add(name0.getStudio());
        }

        studio0.setItems(FXCollections.observableArrayList(listOfStudios));
        studio0.getSelectionModel().selectFirst();
        containerForItemData.add(studioText,0,8);
        containerForItemData.add(studio0,1,8);

        containerForItemData.add(submit,0,11);

        v.nameVerify(albumName,pseudo,submit);
        v.numSongsVerify(numSongs0,pseudo, submit);
        v.dateVerify(releaseDate0,pseudo, submit);
        v.lengthVerify(length0, pseudo, submit);
        v.priceVerify(price0,pseudo, submit);

        table1.setOnMouseClicked(e->{

            System.out.println("table was clicked");

            if(!albumsRoot.getChildren().contains(containerForItemData)){
                albumsRoot.add(containerForItemData,1,0);
            }

            Albums a = table1.getSelectionModel().getSelectedItem();
            containerForItemData.setVisible(true);
            albumName.setText(a.getName());
            numSongs0.setText(String.valueOf(a.getNumSongs()));
            releaseDate0.setText(a.getReleaseDate());
            length0.setText(a.getLength());
            price0.setText(String.valueOf(a.getPrice()));
            rating0.set(a.getRating());
            System.out.println("rating: " + rating0.getPlain());
            //reset clip position
            ratingContainerINFRONT.setClip(new Rectangle(0, 0, rating0.getPlain() * 26, 25));
            //genre0.setIte;

            genre0.getSelectionModel().select(genresTable.getGenre(Integer.parseInt(a.getGenreID())).getGenre());
            artist0.getSelectionModel().select(artistsTable.getArtist(Integer.parseInt(a.getArtistID())).getArtist());
            studio0.getSelectionModel().select(studiosTable.getStudio(Integer.parseInt(a.getStudioID())).getStudio());

            v.autoVerify();
            v.verify(submit);

        });

        submit.setOnAction(e->{
            albumsRoot.getChildren().remove(containerForItemData);

            Albums a = table1.getSelectionModel().getSelectedItem();
            int genreID = 0;
            int artistID = 0;
            int studioID = 0;
            for(Genres g : genresTable.getAllGenres()){
                if(g.getGenre().equals(genre0.getValue())){
                    genreID = g.getId();
                }
            }
            for(Artists a0 : artistsTable.getAllArtists()){
                if(a0.getArtist().equals(artist0.getValue())){
                    artistID = a0.getId();
                }
            }
            for(Studios s : studiosTable.getAllStudios()){
                if(s.getStudio().equals(studio0.getValue())){
                    studioID = s.getId();
                }
            }

            String query = "UPDATE " + DBConst.TABLE_ALBUMS + " SET " +
                    DBConst.ALBUMS_COLUMN_NAME + " = " + "\'" + albumName.getText() + "\'" + ", " +
                    DBConst.ALBUMS_COLUMN_NUMSONGS + " = " + numSongs0.getText() + ", " +
                    DBConst.ALBUMS_COLUMN_RELEASEDATE + " = " + "\'" + releaseDate0.getText() + "\'" + ", " +
                    DBConst.ALBUMS_COLUMN_LENGTH + " = " + "\'" + length0.getText() + "\'" + ", " +
                    DBConst.ALBUMS_COLUMN_PRICE + " = " + price0.getText() + ", " +
                    DBConst.ALBUMS_COLUMN_RATING + " = " + rating0.getPlain() + ", " +
                    DBConst.ALBUMS_COLUMN_GENREID + " = " + genreID + ", " +
                    DBConst.ALBUMS_COLUMN_ARTISTID + " = " + artistID + ", " +
                    DBConst.ALBUMS_COLUMN_STUDIOID + " = " + studioID + " WHERE " +
                    DBConst.ALBUMS_COLUMN_ID + " = " + a.getId() + ";";
            System.out.println("query: " + query);


            try{
                Statement updateItem = db.getConnection().createStatement();
                updateItem.executeUpdate(query);
                //reset all values if successful query
                //re-update button
                v.verify(submit);

            }catch(Exception e1){
                e1.printStackTrace();
                System.out.println("Failed to establish connection.");
            }

            refreshTable();

        });

        /** Genres TableView generation **/
        table2.setEditable(true);
        TableColumn<Genres, String> name0 = new TableColumn<>("Name");
        name0.setCellValueFactory(e->new SimpleStringProperty(e.getValue().getGenre()));
        ObservableList<Genres> genresOL = FXCollections.observableArrayList();
        for(Genres g: genresTable.getAllGenres()){
            genresOL.add(g);
        }

        table2.getColumns().addAll(name0);

        table2.setItems(genresOL);

        /** Container popup (Genres) **/

        GridPane containerForGenres = new GridPane();
        containerForGenres.setPadding(new Insets(10,10,10,10));
        containerForGenres.setVgap(10);
        containerForGenres.setHgap(10);
        containerForGenres.setVisible(true);

        /** Genres Data **/
        //3. initialize column name
        Text genreText0 = new Text("Genre: ");
        TextField genreField = new TextField();


        Button submit2 = new Button("Update Genre");
        submit2.setDisable(true);

        v.nameVerify(genreField,pseudo,submit2);

        containerForGenres.add(genreText0, 0, 0);
        containerForGenres.add(genreField, 1, 0);
        containerForGenres.add(submit2,0,1);

        table2.setOnMouseClicked(e->{
            if(!genresRoot.getChildren().contains(containerForGenres)){
                genresRoot.add(containerForGenres,1,0);
            }

            Genres g = table2.getSelectionModel().getSelectedItem();
            containerForItemData.setVisible(true);
            genreField.setText(g.getGenre());

            v.autoVerify();
            v.verify(submit);

            submit2.setDisable(false);
        });

        submit2.setOnAction(e->{
            genresRoot.getChildren().remove(containerForGenres);

            Genres g = table2.getSelectionModel().getSelectedItem();

            String query = "UPDATE " + DBConst.TABLE_GENRES + " SET " +
                    DBConst.GENRES_COLUMN_GENRE + " = " + "\'" + genreField.getText()+ "\'" +  " WHERE " +
                    DBConst.GENRES_COLUMN_ID + " = " + g.getId() + ";";
            try{
                Statement updateItem = db.getConnection().createStatement();
                updateItem.executeUpdate(query);


                v.verify(submit2);

            }catch(Exception e1){
                e1.printStackTrace();
                System.out.println("Failed to establish connection.");
            }

            refreshTable();

        });

        /** Genres TableView generation **/
        table3.setEditable(true);
        TableColumn<Artists, String> name1 = new TableColumn<>("Name");
        name1.setCellValueFactory(e->new SimpleStringProperty(e.getValue().getArtist()));
        TableColumn<Artists, String> birthday = new TableColumn<>("Birthday");
        birthday.setCellValueFactory(e->new SimpleStringProperty(e.getValue().getBirthday()));
        ObservableList<Artists> artistsOL = FXCollections.observableArrayList();
        for(Artists ar: artistsTable.getAllArtists()){
            artistsOL.add(ar);
        }

        table3.getColumns().addAll(name1, birthday);

        table3.setItems(artistsOL);

        /** Container popup (Genres) **/

        GridPane containerForArtists = new GridPane();
        containerForArtists.setPadding(new Insets(10,10,10,10));
        containerForArtists.setVgap(10);
        containerForArtists.setHgap(10);
        containerForArtists.setVisible(true);

        /** Artists Data **/
        Button submit3 = new Button("Update Artist");
        submit3.setDisable(true);

        //3. initialize column name
        Text artistText0 = new Text("Artist: ");
        TextField artistField = new TextField();
        v.nameVerify(artistField,pseudo,submit3);

        Text birthdayText = new Text("Birthday (yyyy/mm/dd): ");
        TextField birthdayField = new TextField();
        v.dateVerify(birthdayField,pseudo,submit3);

        containerForArtists.add(artistText0, 0, 0);
        containerForArtists.add(artistField, 1, 0);

        containerForArtists.add(birthdayText, 0, 1);
        containerForArtists.add(birthdayField, 1, 1);

        containerForArtists.add(submit3,0,2);

        table3.setOnMouseClicked(e->{
            if(!artistsRoot.getChildren().contains(containerForArtists)){
                artistsRoot.add(containerForArtists,1,0);
            }

            Artists ar = table3.getSelectionModel().getSelectedItem();
            containerForArtists.setVisible(true);
            artistField.setText(ar.getArtist());
            birthdayField.setText(ar.getBirthday());

            v.autoVerify();
            v.verify(submit3);

            submit3.setDisable(false);
        });

        submit3.setOnAction(e->{
            artistsRoot.getChildren().remove(containerForArtists);

            Artists ar = table3.getSelectionModel().getSelectedItem();

            String query = "UPDATE " + DBConst.TABLE_ARTISTS + " SET " +
                    DBConst.ARTISTS_COLUMN_ARTIST + " = " + "\'" + artistField.getText()+ "\'" + ", " +
                    DBConst.ARTISTS_COLUMN_BIRTHDAY + " = " + "\'" + birthdayField.getText()+ "\'" +  " WHERE " +
                    DBConst.ARTISTS_COLUMN_ID + " = " + ar.getId() + ";";
            try{
                Statement updateItem = db.getConnection().createStatement();
                updateItem.executeUpdate(query);


                v.verify(submit3);

            }catch(Exception e1){
                e1.printStackTrace();
                System.out.println("Failed to establish connection.");
            }

            refreshTable();

        });

        /** Studios TableView generation **/
        table4.setEditable(true);
        TableColumn<Studios, String> name2 = new TableColumn<>("Studio Name");
        name2.setCellValueFactory(e->new SimpleStringProperty(e.getValue().getStudio()));
        TableColumn<Studios, String> dateCreated = new TableColumn<>("Date Created");
        dateCreated.setCellValueFactory(e->new SimpleStringProperty(e.getValue().getDateCreated()));
        ObservableList<Studios> studiosOL = FXCollections.observableArrayList();
        for(Studios s: studiosTable.getAllStudios()){
            studiosOL.add(s);
        }

        table4.getColumns().addAll(name2, dateCreated);

        table4.setItems(studiosOL);

        /** Container popup (Studios) **/

        GridPane containerForStudios = new GridPane();
        containerForStudios.setPadding(new Insets(10,10,10,10));
        containerForStudios.setVgap(10);
        containerForStudios.setHgap(10);
        containerForStudios.setVisible(true);

        /** Studios Data **/
        Button submit4 = new Button("Update Studio");
        submit4.setDisable(true);

        //3. initialize column name
        Text studioText0 = new Text("Studio: ");
        TextField studioField = new TextField();
        v.nameVerify(studioField,pseudo,submit4);

        Text dateCreatedText = new Text("Date Created: (yyyy/mm/dd): ");
        TextField dateCreatedField = new TextField();
        v.dateVerify(dateCreatedField,pseudo,submit4);

        containerForStudios.add(studioText0, 0, 0);
        containerForStudios.add(studioField, 1, 0);

        containerForStudios.add(dateCreatedText, 0, 1);
        containerForStudios.add(dateCreatedField, 1, 1);

        containerForStudios.add(submit4,0,2);

        table4.setOnMouseClicked(e->{
            if(!studiosRoot.getChildren().contains(containerForStudios)){
                studiosRoot.add(containerForStudios,1,0);
            }

            Studios s = table4.getSelectionModel().getSelectedItem();
            containerForStudios.setVisible(true);
            studioField.setText(s.getStudio());
            dateCreatedField.setText(s.getDateCreated());

            v.autoVerify();
            v.verify(submit4);

            submit3.setDisable(false);
        });

        submit4.setOnAction(e->{
            studiosRoot.getChildren().remove(containerForStudios);

            Studios s = table4.getSelectionModel().getSelectedItem();

            String query = "UPDATE " + DBConst.TABLE_STUDIOS + " SET " +
                    DBConst.STUDIOS_COLUMN_STUDIO + " = " + "\'" + studioField.getText()+ "\'" + ", " +
                    DBConst.STUDIOS_COLUMN_DATECREATED + " = " + "\'" + dateCreatedField.getText()+ "\'" +  " WHERE " +
                    DBConst.STUDIOS_COLUMN_ID + " = " + s.getId() + ";";
            try{
                Statement updateItem = db.getConnection().createStatement();
                updateItem.executeUpdate(query);


                v.verify(submit4);

            }catch(Exception e1){
                e1.printStackTrace();
                System.out.println("Failed to establish connection.");
            }

            refreshTable();

        });


        //each time tab is switched
        this.setOnSelectionChanged(e->{
            refreshTable();
            this.setContent(promptRoot);
        });

        //adding all tables
        albumsRoot.add(table1,0,0);
        genresRoot.add(table2,0,0);
        artistsRoot.add(table3,0,0);
        studiosRoot.add(table4,0,0);
        //root.add(containerForItemData,1,0);
        this.setContent(promptRoot);
    }
    public void refreshTable(){
        AlbumsTable albumsTableRefresh = AlbumsTable.getInstance();
        table1.getItems().clear();
        table1.getItems().addAll(albumsTableRefresh.getAllAlbums());

        GenresTable genresTableRefresh = GenresTable.getInstance();
        table2.getItems().clear();
        table2.getItems().addAll(genresTableRefresh.getAllGenres());

        ArtistsTable artistsTableRefresh = ArtistsTable.getInstance();
        table3.getItems().clear();
        table3.getItems().addAll(artistsTableRefresh.getAllArtists());

        StudiosTable studiosTableRefresh = StudiosTable.getInstance();
        table4.getItems().clear();
        table4.getItems().addAll(studiosTableRefresh.getAllStudios());
    }

    public static updateItem getInstance(){
        if(instance == null){
            instance = new updateItem();
        }
        return instance;
    }
}
