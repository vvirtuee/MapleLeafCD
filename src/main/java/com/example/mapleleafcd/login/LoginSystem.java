package com.example.mapleleafcd.login;

import java.io.*;

public class LoginSystem {
    File file = new File("accounts");
    String[] credentials = {};
    //authenticate function

    public boolean authenticate(String dbname, String dbuser, String dbpassword, String dbloginpass){
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));
            String str;
            String txt = "";
            while((str = br.readLine()) != null) {
                txt = txt + str + "\n";
            }
            credentials = txt.split("(dbname = )|(dbuser = )|(dbpassword = )|(dbloginpass = )");
        }catch(IOException e){
            e.printStackTrace();
        }
        boolean correctPassword = false;
        for(int i = 0; i < credentials.length; i++){
            if(i % 2 == 1) {     //indicates all odd cases, ie. username
                if ((dbname+"\n").equals(credentials[i])) {
                    if ((dbuser+"\n").equals(credentials[i + 1])) {
                        if((dbpassword+"\n").equals(credentials[i+2])){
                            if((dbloginpass+"\n").equals(credentials[i+3])){
                                correctPassword = true;
                                i = credentials.length;
                            }
                        }

                    }
                }
            }
        }
        return correctPassword;
    }

    public void addAccount(String dbname, String dbuser, String dbpass, String dbloginpass){
        if(!authenticate(dbname,dbuser,dbpass,dbloginpass)){
            try{
                FileWriter fw = new FileWriter(file.getName(), true);
                String str = "dbname = " + dbname+ "\ndbuser = " + dbuser + "\ndbpassword = " + dbpass + "\ndbloginpass = " + dbloginpass + "\n";
                System.out.println(str);

                fw.write(str);
                fw.close();
                System.out.println("successfully wrote to file");
            }catch(Exception e){
                e.printStackTrace();
            }
        }

    }

}
