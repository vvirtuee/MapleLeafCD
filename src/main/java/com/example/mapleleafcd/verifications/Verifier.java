package com.example.mapleleafcd.verifications;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * This class will determine if input given in boxes is valid, will return an integer which operates as an
 * indicator that will return a string indicating if there is an issue and a blank string if the verification
 * works correctly.
 */
public class Verifier {
    //
    public Verifier(){}

    AtomicBoolean atomName = new AtomicBoolean(false);
    AtomicBoolean atomNumSongs = new AtomicBoolean(false);
    AtomicBoolean atomDateAccepted = new AtomicBoolean(false);

    AtomicBoolean atomLength = new AtomicBoolean(false);
    AtomicBoolean atomPrice = new AtomicBoolean(false);

    Boolean nameAccepted = false;
    Boolean numSongsAccepted = false;
    Boolean dateAccepted = false;

    Boolean lengthAccepted = false;

    Boolean priceAccepted = false;
    //STRUCTURE
    //param 1: textBox to be checked
    //param 2: spoiler text updated based on specific error
    //param 3: modifies the button that needs to be updated
    //modifies the Booleans for the verify process

    public void nameVerify(TextField textBox, Text spoiler, Button a){
        textBox.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) { //when focus lost
                if(textBox.getText() != ""){
                    //when it not matches the pattern (1.0 - 6.0)
                    //set the textField empty
                    atomName.set(true);
                    spoiler.setVisible(false);
                    nameAccepted = atomName.getPlain();
                    verify(a);
                }else{
                    spoiler.setVisible(true);
                    spoiler.setText("You must enter a name.");
                    atomName.set(false);
                    nameAccepted = atomName.getPlain();
                    verify(a);
                }
                nameAccepted = atomName.getPlain();
                verify(a);
            }
        });
        nameAccepted = atomName.getPlain();
        verify(a);
    }
    public void numSongsVerify(TextField textBox, Text spoiler, Button a){

        textBox.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) { //when focus lost

                //if contains at least 1 number and that number is greater than 0
                if(textBox.getText().matches("[\\d]{1,2}")){
                    if(Integer.parseInt(textBox.getText()) <= 0){
                        spoiler.setVisible(true);
                        spoiler.setText("The data you entered is not large enough.");
                        textBox.setText("");
                        atomNumSongs.set(false);
                        numSongsAccepted = atomNumSongs.getPlain();
                    }else{
                        spoiler.setVisible(false);
                        atomNumSongs.set(true);
                        numSongsAccepted = atomNumSongs.getPlain();
                    }

                }else{
                    spoiler.setVisible(true);
                    spoiler.setText("The data you entered did not follow the correct format.");
                    textBox.setText("");
                    atomNumSongs.set(false);
                    numSongsAccepted = atomNumSongs.getPlain();
                }
                verify(a);
            }
        });
        numSongsAccepted = atomNumSongs.getPlain();
    }
    public void dateVerify(TextField textBox, Text spoiler, Button a1){
        textBox.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) { //when focus lost
                if(textBox.getText().matches("[0-9]{4}-[0-9]{2}-[0-9]{2}")){
                    //when it not matches the pattern (1.0 - 6.0)
                    //set the textField empty

                    String a = textBox.getText().substring(5,7);
                    String b = textBox.getText().substring(8,10);

                    int[] days = new int[]{31,28,31,30,31,30,31,31,30,31,30,31};

                    String c = textBox.getText().substring(0,4);

                    if(Integer.parseInt(c) % 4 == 0) {   //if leap year
                        days[1] = 29;
                    }

                    boolean legalDateEntry = false;
                    //if the month entered is greater than 0 and less than 12
                    //if the day entered is less than the number of days in that month
                    if(Integer.parseInt(a) > 0 && Integer.parseInt(a) < 13 &&
                            Integer.parseInt(b) > 0 && Integer.parseInt(b) <= days[Integer.parseInt(a)-1]){
                        legalDateEntry = true;
                    }else{
                        legalDateEntry = false;
                        spoiler.setVisible(true);
                        spoiler.setText("The date you entered is impossible.");
                        textBox.setText("");
                        atomDateAccepted.set(false);
                        dateAccepted = atomDateAccepted.getPlain();
                    }

                    //formatting for comparing time
                    String str = c + "-" + a + "-" + b;

                    //collecting current date
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    LocalDateTime now = LocalDateTime.now();
                    String currentDate = dtf.format(now);
                    SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");

                    //if legal date entry and date is not in the future

                    if(legalDateEntry){
                        try{
                            Date d1 = sdformat.parse(str);      //date entered
                            Date d2 = sdformat.parse(currentDate);  //current date
                            if (d1.compareTo(d2) > 0) { //date 1 happens after date 2

                                spoiler.setVisible(true);
                                spoiler.setText("Date is set passed possible range.");
                                textBox.setText("");
                                atomDateAccepted.set(false);
                                dateAccepted = atomDateAccepted.getPlain();
                            }
                        }catch (ParseException e){
                            System.out.println("Failed to parse");
                        }
                    }

                    //date does not follow proper format.
                }else{
                    spoiler.setVisible(true);
                    spoiler.setText("Date does not follow proper format.");
                    textBox.setText("");
                    atomDateAccepted.set(false);
                    dateAccepted = atomDateAccepted.getPlain();
                }
            }else{
                spoiler.setVisible(false);
                atomDateAccepted.set(true);
                dateAccepted = atomDateAccepted.getPlain();
            }
            dateAccepted = atomDateAccepted.getPlain();
            verify(a1);
        });
        dateAccepted = atomDateAccepted.getPlain();
        verify(a1);
    }
    public void lengthVerify(TextField textBox, Text spoiler, Button a1){
        textBox.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) { //when focus lost
                if(textBox.getText().matches("([0-9]{2}:)?([0-9]{2}):([0-9]{2})")){
                    String a, b;
                    if(textBox.getText().length() == 5) {
                        a = textBox.getText().substring(0, 2);
                        b = textBox.getText().substring(3, 5);
                    }else {
                        a = textBox.getText().substring(3, 5);
                        b = textBox.getText().substring(6, 8);
                    }
                    if (Integer.parseInt(a) >= 60 || Integer.parseInt(b) >= 60) {
                        spoiler.setVisible(true);
                        spoiler.setText("The length you entered is impossible.");
                        textBox.setText("");
                        atomLength.set(false);
                        lengthAccepted = atomLength.getPlain();
                        verify(a1);
                    }else{
                        spoiler.setVisible(false);
                        atomLength.set(true);
                        lengthAccepted = atomLength.getPlain();
                        verify(a1);
                    }
                }else{
                    spoiler.setVisible(true);
                    spoiler.setText("The data you entered did not follow the correct format.");
                    textBox.setText("");
                    atomLength.set(false);
                    lengthAccepted = atomLength.getPlain();
                    verify(a1);
                }
            }
            lengthAccepted = atomLength.getPlain();
            verify(a1);
        });
    }
    public void priceVerify(TextField textBox, Text spoiler, Button a){
        textBox.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) { //when focus lost
                if(!textBox.getText().matches("\\d{0,3}\\.\\d{2}")){
                    //when it not matches the pattern (1.0 - 6.0)
                    //set the textField empty
                    spoiler.setVisible(true);
                    spoiler.setText("The data you entered did not follow the correct format.");
                    textBox.setText("");
                    atomPrice.set(false);
                    priceAccepted = atomPrice.getPlain();
                    verify(a);
                }else{
                    spoiler.setVisible(false);
                    spoiler.setText("");
                    atomPrice.set(true);
                    priceAccepted = atomPrice.getPlain();
                    verify(a);
                }
                priceAccepted = atomPrice.getPlain();
                verify(a);
            }
        });
        priceAccepted = atomPrice.getPlain();
        verify(a);
    }

    public void verify(Button a){
        if(a.getText() == "Add Genre" || a.getText() == "Update Genre"){
            if(nameAccepted){
                a.setDisable(false);
            }else{
                a.setDisable(true);
            }
        }
        if(a.getText() == "Add Album" || a.getText() == "Update Album") {
            printStatus();
            if (nameAccepted && numSongsAccepted && dateAccepted && lengthAccepted && priceAccepted) {
                a.setDisable(false);
            }else{
                a.setDisable(true);
            }
        }
        if(a.getText() == "Add Artist" || a.getText() == "Update Artist"){
            if(nameAccepted && dateAccepted){
                a.setDisable(false);
            }else{
                a.setDisable(true);
            }
        }
        if(a.getText() == "Add Studio" || a.getText() == "Update Studio"){
            if(nameAccepted && dateAccepted){
                a.setDisable(false);
            }else{
                a.setDisable(true);
            }
        }
    }
    public void autoVerify(){
        nameAccepted = true;
        numSongsAccepted = true;
        dateAccepted = true;
        lengthAccepted = true;
        priceAccepted = true;
    }
    public void printStatus(){
        System.out.println("nameAccepted: " + nameAccepted);
        System.out.println("numSongsAccepted: " + numSongsAccepted);
        System.out.println("dateAccepted: " + dateAccepted);
        System.out.println("lengthAccepted: " + lengthAccepted);
        System.out.println("priceAccepted: " + priceAccepted);
    }
}
