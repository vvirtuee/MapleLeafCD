package com.example.mapleleafcd;

import com.example.mapleleafcd.database.*;
import com.example.mapleleafcd.login.LoginSystem;
import com.example.mapleleafcd.tabs.addItem;
import com.example.mapleleafcd.tabs.itemStats;
import com.example.mapleleafcd.tabs.removeItem;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
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
        LoginSystem ls = new LoginSystem(); //Instantiate new Login System

        /*
        public static final String DB_NAME = "dvirtuemd";
        public static final String DB_USER = "dvirtue";
        public static final String DB_PASSWORD = "dx316dx316cpxwccpxwc";
        public static final String DB_LOGINPASS = "tknzstknzs";
         */

        ImageView logoContainer = new ImageView(new Image(HelloApplication.class.getResourceAsStream("/images/image.png")));

        logoContainer.setFitWidth(250);
        logoContainer.setFitHeight(250);

        Label databaseNameLabel = new Label("Database Name:");
        Label databaseUsernameLabel = new Label("Database Username:");
        Label databasePasswordLabel = new Label("Database Password:");
        Label databaseLoginPassLabel = new Label("Database Login Password:");
        TextField DBName = new TextField();
        TextField DBUsername = new TextField();
        TextField DBPassword = new TextField();
        TextField DBLoginPassword = new TextField();

        /*
        b.setOnAction(e->{
            boolean passwordAccepted = ls.authenticate(username.getText(), password.getText());
            System.out.println(passwordAccepted);
        });
         */

        ls.addAccount("dfdf","sdf");

        GridPane signInStructure = new GridPane();
        signInStructure.add(databaseNameLabel,0,0);
        signInStructure.add(databaseUsernameLabel,0,1);
        signInStructure.add(databasePasswordLabel,0,2);
        signInStructure.add(databaseLoginPassLabel,0,3);
        signInStructure.add(DBName,1,0);
        signInStructure.add(DBUsername,1,1);
        signInStructure.add(DBPassword,1,2);
        signInStructure.add(DBLoginPassword,1,3);
        signInStructure.setAlignment(Pos.CENTER);
        signInStructure.setHgap(10);
        signInStructure.setVgap(10);

        Button signInBtn = new Button("Login");

        VBox signIn = new VBox();
        signIn.setAlignment(Pos.CENTER);
        signIn.setSpacing(10);
        signIn.getChildren().addAll(logoContainer, signInStructure, signInBtn);

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
        pane.setTop(signIn);
        //pane.setBottom(v);  //location of VBox to be changed.



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