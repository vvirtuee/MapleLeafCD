package com.example.mapleleafcd;

import com.example.mapleleafcd.database.*;
import com.example.mapleleafcd.login.LoginSystem;
import com.example.mapleleafcd.tabs.addItem;
import com.example.mapleleafcd.tabs.itemStats;
import com.example.mapleleafcd.tabs.removeItem;
import com.example.mapleleafcd.tabs.updateItem;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.PopupWindow;
import javafx.stage.Stage;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        BorderPane pane = new BorderPane();

        ImageView logoContainer = new ImageView(new Image(HelloApplication.class.getResourceAsStream("/images/logo.png")));
        logoContainer.setFitWidth(250);
        logoContainer.setFitHeight(125);

        Label databaseNameLabel = new Label("Database Name:");
        Label databaseUsernameLabel = new Label("Database Username:");
        Label databasePasswordLabel = new Label("Database Password:");
        Label databaseLoginPassLabel = new Label("Database Login Password:");
        TextField DBName = new TextField();
        TextField DBUsername = new TextField();

        PasswordField DBPassword = new PasswordField();
        PasswordField DBLoginPassword = new PasswordField();

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

        /** admin system **/
        LoginSystem ls = new LoginSystem(); //Instantiate new Login System

        Text loginSpoilerText = new Text();
        loginSpoilerText.setVisible(false);





        VBox signIn = new VBox();
        signIn.setPadding(new Insets(125,0,0,0));
        signIn.setAlignment(Pos.CENTER);
        signIn.setSpacing(10);
        signIn.getChildren().addAll(logoContainer, signInStructure, signInBtn, loginSpoilerText);

        //boolean passwordAccepted = ls.authenticate(username,password);
        //System.out.println(passwordAccepted);



        //build a MenuBar
        MenuBar menu = new MenuBar();

        //build Menu items
        Menu fileMenu = new Menu("File");
        Menu creditsMenu = new Menu("Credits");
        MenuItem creditsItem = new MenuItem("Credits");
        creditsMenu.getItems().add(creditsItem);

        creditsItem.setOnAction(e->{

            Stage popupWindow =new Stage();
            popupWindow.initModality(Modality.APPLICATION_MODAL);
            popupWindow.setTitle("Credits");
            Label label1= new Label("Dalton Virtue");
            Label label2= new Label("Andre Arnaut");


            VBox layout= new VBox(10);
            layout.getChildren().addAll(label1, label2);
            layout.setAlignment(Pos.CENTER);
            Scene scene1= new Scene(layout, 300, 250);
            popupWindow.setScene(scene1);

            popupWindow.showAndWait();

        });


        MenuItem exit = new MenuItem("Exit");
        exit.setOnAction(e->{
            //connection.close();
            System.exit(0);
        });
        fileMenu.getItems().add(exit);

        //here we are adding file menu and credits menu to the top menu bar
        menu.getMenus().addAll(fileMenu, creditsMenu);


        //create a tabpane
        TabPane tabPane = new TabPane();
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        tabPane.getTabs().addAll(addItem.getInstance(), removeItem.getInstance(), updateItem.getInstance(), itemStats.getInstance());
        pane.setTop(signIn);

        //if account already exists
        if(ls.checkIfExists()){
            pane.setTop(menu);
            pane.setCenter(tabPane);
        }


        signInBtn.setOnAction(e->{
            if(Const.DB_NAME.equals(DBName.getText()) && Const.DB_USER.equals(DBUsername.getText()) && Const.DB_PASSWORD.equals(DBPassword.getText()) && Const.DB_LOGINPASS.equals(DBLoginPassword.getText())){
                ls.addAccount(DBName.getText(), DBUsername.getText(), DBPassword.getText(), DBLoginPassword.getText());
            }
            boolean passwordAccepted = ls.authenticate(DBName.getText(), DBUsername.getText(), DBPassword.getText(), DBLoginPassword.getText());
            if(!passwordAccepted){
                loginSpoilerText.setVisible(true);
                loginSpoilerText.setFill(Paint.valueOf("FF0000"));
                loginSpoilerText.setText("Incorrect password. Please try again!");
            }else{
                //enter system
                loginSpoilerText.setVisible(true);
                loginSpoilerText.setFill(Paint.valueOf("00FF00"));
                loginSpoilerText.setText("Correct password. Logging into database.");
                pane.setTop(menu);
                pane.setCenter(tabPane);
            }
        });



        //Database connection
        Database connection = Database.getInstance();
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