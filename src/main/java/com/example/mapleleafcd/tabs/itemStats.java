package com.example.mapleleafcd.tabs;

import com.example.mapleleafcd.database.Database;
import com.example.mapleleafcd.pojo.Albums;
import com.example.mapleleafcd.pojo.Genres;
import com.example.mapleleafcd.tables.AlbumsTable;
import com.example.mapleleafcd.tables.GenresTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

        //ComboBox
        ComboBox typesCombo = new ComboBox();
        ArrayList<String> dataNames = new ArrayList<>();
        dataNames.add("Genre Diversity");
        dataNames.add("Rating Diversity");
        dataNames.add("Artist's Birth Month Spread");
        dataNames.add("# of Albums per decade");
        typesCombo.setItems(FXCollections.observableArrayList(dataNames));
        typesCombo.getSelectionModel().selectFirst();

        root.setLeft(typesCombo);

        generateGenresChart();
        root.setRight(refresh);
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

        for(Albums a: albumsTable.getAllAlbums()){
            //String key = numTimesDataAppears.keySet().;
            //data.add(new PieChart.Data(genresTable.getGenre(numTimesDataAppears.get(a.getGenreID())).toString(), 1));
        }


        /*
        for(Albums album: albumsArray){
            int genre = Integer.parseInt(album.getGenreID());
            System.out.println("genres id: " + genre);
            //PieChart.Data("name as string", "data num as double"

            System.out.println("genres name: " + genresTable.getGenre(genre));
            data.add(new PieChart.Data(genresTable.getGenre(genre).toString(), Double.parseDouble(album.getGenreID())));
            //data.add(new PieChart.Data(album.getArtistID(), album.getPrice()));
        }

         */
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
