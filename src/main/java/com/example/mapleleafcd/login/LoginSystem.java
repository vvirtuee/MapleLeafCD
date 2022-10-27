package com.example.mapleleafcd.login;

import java.io.*;

public class LoginSystem {
    File file = new File("accounts");
    String[] credentials = {};
    //authenticate function

    public boolean authenticate(String username, String password){
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));
            String str;
            String txt = "";
            while((str = br.readLine()) != null) {
                txt = txt + str + "\n";
            }
            credentials = txt.split("(username:   )|(password:   )");
        }catch(IOException e){
            e.printStackTrace();
        }
        boolean correctPassword = false;
        for(int i = 0; i < credentials.length; i++){
            if(i % 2 == 1) {     //indicates all odd cases, ie. username
                if ((username+"\n").equals(credentials[i])) {
                    if ((password+"\n").equals(credentials[i + 1])) {
                        correctPassword = true;
                        i = credentials.length;
                    }
                }
            }
        }
        return correctPassword;
    }

    public void addAccount(String username, String password){
        try{
            FileWriter fw = new FileWriter(file.getName(), true);
            String str = "username:   " + username + "\npassword:   " + password + "\n";
            System.out.println(str);

            fw.write(str);
            fw.close();
            System.out.println("successfully wrote to file");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
