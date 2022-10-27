package com.example.mapleleafcd;

import com.example.mapleleafcd.database.*;
import com.example.mapleleafcd.login.LoginSystem;
import com.example.mapleleafcd.tabs.addItem;
import com.example.mapleleafcd.tabs.itemStats;
import com.example.mapleleafcd.tabs.removeItem;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        BorderPane pane = new BorderPane();


        /** admin system **/
        LoginSystem ls = new LoginSystem();

        Label label1 = new Label("username: ");
        TextField username = new TextField();
        HBox hb1 = new HBox();
        hb1.getChildren().addAll(label1, username);

        Label label2 = new Label("password: ");
        TextField password = new TextField();
        HBox hb2 = new HBox();
        hb2.getChildren().addAll(label2, password);

        Button b = new Button("Login");

        b.setOnAction(e->{
            boolean passwordAccepted = ls.authenticate(username.getText(), password.getText());
            System.out.println(passwordAccepted);
        });

        VBox v = new VBox();
        v.getChildren().addAll(hb1,hb2, b);

        //boolean passwordAccepted = ls.authenticate(username,password);
        //System.out.println(passwordAccepted);



        //build a MenuBar
        MenuBar menu = new MenuBar();

        //build Menu items
        Menu fileMenu = new Menu("File");
        Menu creditsMenu = new Menu("Credits");

        MenuItem exit = new MenuItem("Exit");
        exit.setOnAction(e->{
            System.exit(0);
        });
        fileMenu.getItems().add(exit);

        //here we are adding file menu and credits menu to the top menu bar
        menu.getMenus().addAll(fileMenu, creditsMenu);

        //create a tabpane
        TabPane tabPane = new TabPane();
        tabPane.getTabs().addAll(addItem.getInstance(), removeItem.getInstance(), itemStats.getInstance());
        pane.setCenter(tabPane);
        pane.setBottom(v);



        //Database connection
        //Database connection = Database.getInstance();
        //connection.close();


        Scene scene = new Scene(pane, 800, 600);
        stage.setTitle("Maple Leaf CD");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}