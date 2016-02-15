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

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sackman on 2/13/2016.
 */

public class SqlRequest {
    private JSONObject output = new JSONObject();
    private String url;
    private Context context;
    private final String server = "http://nashdomain.esy.es/";
    private Map<String, String> params = new HashMap<String, String>();

    //Takes in the php file name
    //ex. Register.php
    public SqlRequest(Context context, String phpName, Map<String, String> params){
        url = server + phpName;
        this.context = context;
        this.params = params;

        Run();
    }

    private void Run(){
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            setOutput(jsonResponse);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.d("JSON failed to parse: ", response);
                        }
                    }
                }, new Response.ErrorListener(){

               @Override
               public void onErrorResponse(VolleyError error){
                   Log.d("VolleyError at url ", url);
               }
                }
        ){
            //Parameters inserted
            @Override
            protected Map<String, String> getParams()
            {
                return params;
            }
        };
        queue.add(request);

        Log.d("Output returning ", output.toString());
    }

    private void setOutput(JSONObject output){
        this.output = output;
        Log.d("Output while running", this.output.toString());
    }

    public JSONObject getOutput(){
        return output;
    }
}
