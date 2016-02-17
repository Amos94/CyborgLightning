package com.example.amosmadalinneculau.objects;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class sendPassword {

    public sendPassword(Context context, final String email){

        final String url = "http://nashdomain.esy.es/get_user.php";

        //parameters to post to php file
        final Map<String, String> params = new HashMap<>();
        params.put("email", email);

        //request to get the user from the mysql database using php
        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getString("success").equals("1");
                            Log.d("Success", String.valueOf(success));

                            if (success){
                                String password = new JSONObject("user").getString("password");
                                Log.d("password is", password);

                                //send password to email
                                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                                emailIntent.setType("message/rfc822");
                                emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
                                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Forgotten password");
                                emailIntent.putExtra(Intent.EXTRA_TEXT, "Your password is: "+password);
                                try{
                                    //startActivity(Intent.createChooser(emailIntent, "Send mail..."));
                                } catch (android.content.ActivityNotFoundException ex){
                                    Log.d("No email client", "Error");
                                }

                            }
                            else{
                                String message = jsonResponse.getString("message");
                                Log.d("Message is", message);
                            }
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
        //put the request in the static queue
        VolleyQueue.getInstance(context).addToRequestQueue(request);
    }
}
