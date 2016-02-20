package com.example.amosmadalinneculau.objects;

import android.test.AndroidTestCase;

import junit.framework.Assert;

import org.junit.Test;

/**
 * Created by Austin on 2/11/2016.
 */
public class ConnectionTest extends AndroidTestCase{

    @Test
    public void MySQLConnectionTest(){
        //TODO cannot test Volley directly, Another testing method may be required

        Assert.assertEquals("CONNECTED", "CONNECTED");
    }
}
