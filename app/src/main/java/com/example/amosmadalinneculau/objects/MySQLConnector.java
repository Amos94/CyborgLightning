package com.example.amosmadalinneculau.objects;

/**
 * Created by Amos Madalin Neculau on 04/02/2016.
 */

import java.sql.*;

public class MySQLConnector {

    //Status
        //CONNECTED
        //NOT CONNECTED
        //MORE STATUSES TO BE ADDED
    public String status;

    //EXTERNAL DATABASE
    //CONNECTION CREDENTIALS
    protected static final String DB_URL="jdbc:mysql://localhost/cyborglightningschema?";
    protected static final String USER="root";
    protected static final String PASSWORD="Amos.expert94";
    protected static final String PORT="3306";

    //CONNECTION AND STATEMENT
    Connection connection;
    Statement statement;

    public MySQLConnector() {
        status = "not connected";
    }

    public String getStatus(){
        return status;
    }

    public void sqlOpenConnection(){
        try{
            System.out.println("--- TRYING TO CONNECT ---");
            Class.forName ("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL,USER,PASSWORD);
            System.out.println("--- CONNECTED ---");
            status = "CONNECTED";
        }
        catch(Exception ex){
            status = "NOT CONNECTED";
            ex.printStackTrace();
        }
    }

}
