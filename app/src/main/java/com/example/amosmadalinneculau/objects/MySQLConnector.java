package com.example.amosmadalinneculau.objects;

/**
 * Created by Amos Madalin Neculau on 04/02/2016.
 */

import android.content.ContentValues;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.*;

import javax.net.ssl.HttpsURLConnection;

public class MySQLConnector {

    //Status
        //CONNECTED
        //NOT CONNECTED
        //MORE STATUSES TO BE ADDED
    public String status = "unknown";

    //EXTERNAL DATABASE
    //CONNECTION CREDENTIALS
    protected static final String DB_URL="http://cyborglightning.esy.es/Register.php";
    protected static final String USER="u923983532_user";
    protected static final String PASSWORD="pass123";

    //CONNECTION AND STATEMENT
    public Connection connection;
    Statement statement;

    public MySQLConnector() {
        sqlOpenConnection();
    }

    public String getStatus(){
        return status;
    }

    public void sqlOpenConnection(){
        //https://ihofmann.wordpress.com/2013/01/23/android-sending-post-requests-with-parameters/
        try{
            String urlParams = "name=testUserName&age=49&password=testPassword&email=joe@gmail.com";

            URL urlToRequest = new URL(DB_URL);
            HttpURLConnection urlConnection = (HttpURLConnection) urlToRequest.openConnection();
            urlConnection.setDoOutput(true);
            urlConnection.setRequestMethod("POST");
            urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            DataOutputStream dStream = new DataOutputStream(urlConnection.getOutputStream());
            dStream.writeBytes(urlParams);
            dStream.flush();
            dStream.close();

            status = "CONNECTED";
        }
        catch(Exception ex){
            status = "NOT CONNECTED";
            ex.printStackTrace();
        }
    }

}
