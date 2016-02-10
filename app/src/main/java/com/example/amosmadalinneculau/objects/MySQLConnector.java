package com.example.amosmadalinneculau.objects;

/**
 * Created by Amos Madalin Neculau on 04/02/2016.
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.sql.*;

public class MySQLConnector {

    //Status
        //CONNECTED
        //NOT CONNECTED
        //MORE STATUSES TO BE ADDED
    public String status = "unknown";

    //EXTERNAL DATABASE
    //CONNECTION CREDENTIALS
    protected static final String DB_URL="mysql.hostinger.co.uk";
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
        try{
            URL url = new URL(DB_URL);
            String data  = URLEncoder.encode("username", "UTF-8")
                    + "=" + URLEncoder.encode(USER, "UTF-8");
            data += "&" + URLEncoder.encode("password", "UTF-8")
                    + "=" + URLEncoder.encode(PASSWORD, "UTF-8");
            URLConnection conn = url.openConnection();

            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write( data );
            BufferedReader reader = new BufferedReader(new
                    InputStreamReader(conn.getInputStream()));
        }
        catch(Exception ex){
            status = "NOT CONNECTED";
            ex.printStackTrace();
        }
    }

}
