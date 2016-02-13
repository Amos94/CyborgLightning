package com.example.amosmadalinneculau.objects;

/**
 * Created by Amos Madalin Neculau on 04/02/2016.
 */

import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.Request.Method;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;


public class MySQLConnector {

    public String output = "unknown";
    private final String url="http://nashdomain.esy.es/";

    public MySQLConnector() {
        sqlOpenConnection();
    }

    public String getOutput(){
        return output;
    }

    public void sqlOpenConnection(){
        //https://ihofmann.wordpress.com/2013/01/23/android-sending-post-requests-with-parameters/
        String phpFile = "get_all_users.php";
        try{
            JsonObjectRequest request = new JsonObjectRequest(Method.GET, url+phpFile, (String)null,
                    new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {
                            //TODO stuff
                        }
                    }, new Response.ErrorListener(){

                        @Override
                        public void onErrorResponse(VolleyError error){
                            //TODO stuff
                        }
                    }
                    );
        }
        catch(Exception ex){
            output = "NOT CONNECTED";
            ex.printStackTrace();
        }
    }
}
