package com.example.amosmadalinneculau.objects;

/**
 * Created by Amos Madalin Neculau on 04/02/2016.
 */
import android.os.StrictMode;

import java.net.URL;
import java.sql.*;

public class MySQLConnector {

    //Status
        //CONNECTED
        //NOT CONNECTED
        //MORE STATUSES TO BE ADDED
    public String status = "unknown";

    //EXTERNAL DATABASE
    //CONNECTION CREDENTIALS
    protected static final String DB_URL="http://cyborglightning.esy.es/mysql_connect.php";
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

            status = "CONNECTED";
        }
        catch(Exception ex){
            status = "NOT CONNECTED";
            ex.printStackTrace();
        }
    }

}
