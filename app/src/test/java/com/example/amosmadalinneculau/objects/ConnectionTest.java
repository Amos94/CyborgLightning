package com.example.amosmadalinneculau.objects;

import junit.framework.Assert;

import org.junit.Test;

/**
 * Created by Sackman on 2/11/2016.
 */
public class ConnectionTest {

    @Test
    public void MySQLConnectionTest(){
        MySQLConnector con = new MySQLConnector();
        con.sqlOpenConnection();

        Assert.assertEquals(con.getStatus(), "CONNECTED");
    }
}
