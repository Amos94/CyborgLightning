package com.example.amosmadalinneculau.objects;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

/**
 * Created by Sackman on 2/13/2016.
 */

public class SqlRequest {
    private String output = "";
    private String url;
    private Context context;
    private final String server = "http://nashdomain.esy.es/";

    //Takes in the php file name
    //ex. Register.php
    public SqlRequest(Context context, String phpName){
        url = server + phpName;
        this.context = context;
    }

    public String Run(){
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        setOutput(response);
                    }
                }, new Response.ErrorListener(){

               @Override
               public void onErrorResponse(VolleyError error){
                   Log.d("SqlRequest: VolleyError", url);
               }
                }
        );
        queue.add(request);

        return output;
    }

    public void setOutput(String output){
        this.output = output;
        Log.d("Output while running", output);
    }
}
