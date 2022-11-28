package com.example.mapleleafcd.prompts;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class Prompt {

    public GridPane initializePrompt(Tab t, GridPane albumsRoot, GridPane genresRoot, GridPane artistsRoot, GridPane studiosRoot){
        GridPane prompt = new GridPane();
        prompt.setPadding(new Insets(10,10,10,10));
        prompt.setVgap(10);
        prompt.setHgap(10);

        Text promptText = new Text("Please select a table to remove an item from:");
        Button albumsBtn = new Button("Albums");
        Button genresBtn = new Button("Genres");
        Button artistsBtn = new Button("Artists");
        Button studiosBtn = new Button("Studios");
        prompt.add(promptText,0,0);

        albumsBtn.setOnAction(e->{t.setContent(albumsRoot);});
        genresBtn.setOnAction(e->{t.setContent(genresRoot);});
        artistsBtn.setOnAction(e->{t.setContent(artistsRoot);});
        studiosBtn.setOnAction(e->{t.setContent(studiosRoot);});

        HBox promptVBox = new HBox();
        promptVBox.setSpacing(10);
        promptVBox.getChildren().addAll(albumsBtn,genresBtn,artistsBtn,studiosBtn);
        prompt.add(promptVBox,0,1);

        return prompt;
    }
}
