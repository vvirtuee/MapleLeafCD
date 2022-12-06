package com.example.mapleleafcd.tabs;

import com.example.mapleleafcd.database.Database;
import com.example.mapleleafcd.pojo.Albums;
import com.example.mapleleafcd.pojo.Artists;
import com.example.mapleleafcd.pojo.Genres;
import com.example.mapleleafcd.tables.AlbumsTable;
import com.example.mapleleafcd.tables.ArtistsTable;
import com.example.mapleleafcd.tables.GenresTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.layout.BorderPane;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class itemStats extends Tab {
    private static itemStats instance;
    private PieChart chart;

    private itemStats(){
        this.setText("Stats");

        BorderPane root = new BorderPane();
        chart = new PieChart();
        chart.setTitle("");
        chart.setLabelsVisible(true);
        root.setCenter(chart);
        Button refresh = new Button("Refresh");
        refresh.setOnAction(e->{
            generateGenresChart();
        });
        root.setPadding(new Insets(10,10,10,10));

        //ComboBox
        ComboBox typesCombo = new ComboBox();
        ArrayList<String> dataNames = new ArrayList<>();
        dataNames.add("Genre Diversity");
        dataNames.add("Rating Diversity");
        dataNames.add("Artist's Birth Month Spread");
        dataNames.add("# of Albums per decade");
        typesCombo.setItems(FXCollections.observableArrayList(dataNames));
        typesCombo.getSelectionModel().selectFirst();

        typesCombo.setOnAction(e->{
            String selection = typesCombo.getSelectionModel().getSelectedItem().toString();
            switch(selection){
                case "Genre Diversity":
                    generateGenresChart();
                    break;
                case "Rating Diversity":
                    generateRatingsChart();
                    break;
                case "Artist's Birth Month Spread":
                    generateBirthdaysChart();
                    break;
                case "# of Albums per decade":
                    generateDecadesChart();
                    break;
            }
        });

        root.setLeft(typesCombo);

        generateGenresChart();
        root.setBottom(refresh);
        this.setContent(root);
    }

    public void generateGenresChart(){
        AlbumsTable albums = AlbumsTable.getInstance();

        //Grab a list of the album types
        ArrayList<Albums> albumsArray = AlbumsTable.getInstance().getAllAlbums();

        GenresTable genresTable = new GenresTable();

        ArrayList<PieChart.Data> data = new ArrayList<>();
        //1. parse through the albums data

        AlbumsTable albumsTable = new AlbumsTable();
        HashMap<String, Integer> numTimesDataAppears = new HashMap<>();
        System.out.println(numTimesDataAppears);

        //populate hashmap with 0s
        for(Albums a: albumsTable.getAllAlbums()){
            numTimesDataAppears.put(a.getGenreID(),0);
        }

        //add to hashmap each time a key of the genreID thats the same is detected
        for(Albums a: albumsTable.getAllAlbums()){
            numTimesDataAppears.put(a.getGenreID(),numTimesDataAppears.get(a.getGenreID()) + 1);
        }
        System.out.println("data collected: " + numTimesDataAppears);
        System.out.println("titles collected: " + numTimesDataAppears.keySet());

        for(Map.Entry<String, Integer> entry: numTimesDataAppears.entrySet()){

            //collecting the key
            int id = Integer.parseInt(entry.getKey());
            String key = genresTable.getGenre(id).getGenre();
            int value = entry.getValue();
            data.add(new PieChart.Data(key, value));

        }
        ObservableList<PieChart.Data> chartData
                = FXCollections.observableArrayList(data);
        chart.setData(chartData);
    }
    public void generateRatingsChart(){
        AlbumsTable albums = AlbumsTable.getInstance();

        //Grab a list of the album types
        ArrayList<Albums> albumsArray = AlbumsTable.getInstance().getAllAlbums();

        ArrayList<PieChart.Data> data = new ArrayList<>();
        //1. parse through the albums data

        AlbumsTable albumsTable = new AlbumsTable();
        HashMap<Integer, Integer> numTimesDataAppears = new HashMap<>();

        //populate hashmap with 0s

        for(Albums a: albumsTable.getAllAlbums()){
            int rating = (int)(Math.round(a.getRating()));
            System.out.println("rating: " + rating);
            numTimesDataAppears.put(rating,0);
        }

        //add to hashmap each time a key of the genreID thats the same is detected
        for(Albums a: albumsTable.getAllAlbums()){
            int rating = (int)(Math.round(a.getRating()));
            numTimesDataAppears.put(rating, numTimesDataAppears.get(rating) + 1);
        }
        System.out.println("data collected: " + numTimesDataAppears);
        System.out.println("titles collected: " + numTimesDataAppears.keySet());

        for(Map.Entry<Integer, Integer> entry: numTimesDataAppears.entrySet()){
            //collecting the key
            int key = entry.getKey();
            int value = entry.getValue();
            data.add(new PieChart.Data(String.valueOf(key), value));

        }

        ObservableList<PieChart.Data> chartData
                = FXCollections.observableArrayList(data);
        chart.setData(chartData);
    }

    public void generateBirthdaysChart(){
        ArtistsTable artists = ArtistsTable.getInstance();

        //Grab a list of the album types
        ArrayList<Albums> albumsArray = AlbumsTable.getInstance().getAllAlbums();

        ArrayList<PieChart.Data> data = new ArrayList<>();
        //1. parse through the albums data

        ArtistsTable artistsTable = new ArtistsTable();
        HashMap<String, Integer> numTimesDataAppears = new HashMap<>();


        //populate hashmap with 0s
        for(Artists ar: artistsTable.getAllArtists()){
            //1. determine month of birth

            String birthMonth = ar.getBirthday().substring(5,7);
            String month = "";

            switch(birthMonth){
                case "01":
                    month = "January";
                    break;
                case "02":
                    month = "February";
                    break;
                case "03":
                    month = "March";
                    break;
                case "04":
                    month = "April";
                    break;
                case "05":
                    month = "May";
                    break;
                case "06":
                    month = "June";
                    break;
                case "07":
                    month = "July";
                    break;
                case "08":
                    month = "August";
                    break;
                case "09":
                    month = "September";
                    break;
                case "10":
                    month = "October";
                    break;
                case "11":
                    month = "November";
                    break;
                case "12":
                    month = "December";
                    break;
                default:
                    month = "";
                    break;
            }

            System.out.println("birthday month: " + month);

            numTimesDataAppears.put(month,0);
        }

        //populate them
        for(Artists ar: artistsTable.getAllArtists()){
            String birthMonth = ar.getBirthday().substring(5,7);
            String month = "";

            switch(birthMonth){
                case "01":
                    month = "January";
                    break;
                case "02":
                    month = "February";
                    break;
                case "03":
                    month = "March";
                    break;
                case "04":
                    month = "April";
                    break;
                case "05":
                    month = "May";
                    break;
                case "06":
                    month = "June";
                    break;
                case "07":
                    month = "July";
                    break;
                case "08":
                    month = "August";
                    break;
                case "09":
                    month = "September";
                    break;
                case "10":
                    month = "October";
                    break;
                case "11":
                    month = "November";
                    break;
                case "12":
                    month = "December";
                    break;
                default:
                    month = "";
                    break;
            }

            numTimesDataAppears.put(month,numTimesDataAppears.get(month) + 1);
        }

        for(Map.Entry<String, Integer> entry: numTimesDataAppears.entrySet()){
            //collecting the key
            String key = entry.getKey();
            int value = entry.getValue();
            data.add(new PieChart.Data(key, value));

        }

        ObservableList<PieChart.Data> chartData
                = FXCollections.observableArrayList(data);
        chart.setData(chartData);
    }

    public void generateDecadesChart(){
        AlbumsTable albumsTable = AlbumsTable.getInstance();

        //Grab a list of the album types
        ArrayList<Albums> albumsArray = AlbumsTable.getInstance().getAllAlbums();

        ArrayList<PieChart.Data> data = new ArrayList<>();
        //1. parse through the albums data

        HashMap<String, Integer> numTimesDataAppears = new HashMap<>();


        //populate hashmap with 0s
        for(Albums a: albumsTable.getAllAlbums()){
            //1. determine month of birth

            int eraNum = Integer.parseInt(a.getReleaseDate().substring(0,2));

            String era = "";

            if(eraNum < 19){
                era = "Pre-Modern";
            }else if(eraNum == 19){
                eraNum = Integer.parseInt(a.getReleaseDate().substring(2,4));
                if(eraNum < 10){
                    era = "10s";
                }else{
                    eraNum = Integer.parseInt(a.getReleaseDate().substring(2,3));
                    era = String.valueOf(eraNum) + "0s";
                }
            }else{  //ie. it's the 2000s
                eraNum = Integer.parseInt(a.getReleaseDate().substring(2,4));
                if(eraNum < 10){
                    era = "'00s";
                }else{
                    eraNum = Integer.parseInt(a.getReleaseDate().substring(2,3));
                    era = "'" + String.valueOf(eraNum) + "0s";
                }
            }

            numTimesDataAppears.put(era,0);
        }

        //populate them

        for(Albums a: albumsTable.getAllAlbums()){
            //1. determine month of birth

            int eraNum = Integer.parseInt(a.getReleaseDate().substring(0,2));

            String era = "";

            if(eraNum < 19){
                era = "Pre-Modern";
            }else if(eraNum == 19){
                eraNum = Integer.parseInt(a.getReleaseDate().substring(2,4));
                if(eraNum < 10){
                    era = "10s";
                }else{
                    eraNum = Integer.parseInt(a.getReleaseDate().substring(2,3));
                    era = String.valueOf(eraNum) + "0s";
                }
            }else{  //ie. it's the 2000s
                eraNum = Integer.parseInt(a.getReleaseDate().substring(2,4));
                if(eraNum < 10){
                    era = "'00s";
                }else{
                    eraNum = Integer.parseInt(a.getReleaseDate().substring(2,3));
                    era = "'" + String.valueOf(eraNum) + "0s";
                }
            }

            numTimesDataAppears.put(era,numTimesDataAppears.get(era) + 1);
        }


        for(Map.Entry<String, Integer> entry: numTimesDataAppears.entrySet()){
            //collecting the key
            String key = entry.getKey();
            int value = entry.getValue();
            data.add(new PieChart.Data(key, value));

        }

        ObservableList<PieChart.Data> chartData
                = FXCollections.observableArrayList(data);
        chart.setData(chartData);
    }

    public static itemStats getInstance(){
        if(instance == null){
            instance = new itemStats();
        }
        return instance;
    }

}
