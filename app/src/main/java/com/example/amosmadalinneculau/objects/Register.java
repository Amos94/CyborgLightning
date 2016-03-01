package com.example.amosmadalinneculau.objects;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Register extends AppCompatActivity {

    //Info for register
    private EditText emailET;
    private EditText nameET;
    private EditText passwordET;
    private EditText dobET;
    private EditText locationET;
    private boolean isMale;
    private boolean isActivated;
    private RadioButton maleRadioRegister;
    private RadioButton femaleRadioRegister;
    private RadioGroup radioGroup;
    private int toAddGender;
    private int toAddActivated;
    private JSONObject response;
    //MYSQL DATABASE
    public MySQLConnector connector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
*/
        //USER INPUT
        emailET = (EditText) findViewById(R.id.emailRegister);
        nameET = (EditText) findViewById(R.id.nameRegister);
        passwordET = (EditText) findViewById(R.id.passwordRegister);
        dobET = (EditText) findViewById(R.id.dateRegister);
        locationET = (EditText) findViewById(R.id.locationET);

        radioGroup = (RadioGroup) findViewById(R.id.radioGroupRegister);
        maleRadioRegister = (RadioButton) findViewById(R.id.maleRadioRegister);
        femaleRadioRegister = (RadioButton) findViewById(R.id.femaleRadioRegister);

        //help for the insertion
        if(maleRadioRegister.isChecked())
            isMale = true;
        if(femaleRadioRegister.isChecked())
            isMale = false;

        if(isMale) {
            toAddGender = 1;
        }
        else {
            toAddGender = 0;
        }

        isActivated = false;
        toAddActivated = 0;

        //DB
        connector = new MySQLConnector();

        if(toAddActivated == 0 ){
            //intent fo to another screen
            //insert the code send by email
        }
        else{
            //insertData();
            //intent to main menu
        }
    }

    public void insertData(View view){
        final String url = "http://nashdomain.esy.es/insertNewUser.php";

        //parameters to post to php file
        final Map<String, String> params = new HashMap<String, String>();
        params.put("email", emailET.getText().toString());
        params.put("name", nameET.getText().toString());
        params.put("password", passwordET.getText().toString());
        params.put("dob", dobET.getText().toString());
        params.put("gender", "Test");
        params.put("location", locationET.getText().toString());

        //request to insert the user into the mysql database using php
        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);

                            boolean success = jsonResponse.getString("success").equals("1");
                            Log.d("Success", String.valueOf(success));

                            String message = jsonResponse.getString("message");
                            Log.d("Message is", message);
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
        VolleyQueue.getInstance(this).addToRequestQueue(request);
    }

    public void sendPassword(View view) {
        final String url = "http://nashdomain.esy.es/get_user.php";

        //parameters to post to php file
        final Map<String, String> params = new HashMap<>();
        params.put("email", emailET.getText().toString());

        //request to get the user from the mysql database using php
        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getString("success").equals("1");
                            Log.d("Success", String.valueOf(success));
                            String message = jsonResponse.getString("message");
                            Log.d("Message is", message);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.d("JSON failed to parse: ", response);
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("VolleyError at url ", url);
            }
        }
        ) {
            //Parameters inserted
            @Override
            protected Map<String, String> getParams() {
                return params;
            }
        };
        //put the request in the static queue
        VolleyQueue.getInstance(this).addToRequestQueue(request);
    }

    public void insertFriend(View view){
        final String url = "http://nashdomain.esy.es/insert_friend.php";

        //parameters to post to php file
        final Map<String, String> params = new HashMap<String, String>();
        params.put("emailA", emailET.getText().toString());
        params.put("emailB", nameET.getText().toString());

        //request to insert the user into the mysql database using php
        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);

                            boolean success = jsonResponse.getString("success").equals("1");
                            Log.d("Success", String.valueOf(success));

                            String message = jsonResponse.getString("message");
                            Log.d("Message is", message);
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
        VolleyQueue.getInstance(this).addToRequestQueue(request);
    }

    public void insertInterest(View view){
        final String url = "http://nashdomain.esy.es/insert_interest.php";

        //parameters to post to php file
        final Map<String, String> params = new HashMap<String, String>();
        params.put("email", emailET.getText().toString());
        params.put("interest", nameET.getText().toString());

        //request to insert the user into the mysql database using php
        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);

                            boolean success = jsonResponse.getString("success").equals("1");
                            Log.d("Success", String.valueOf(success));

                            String message = jsonResponse.getString("message");
                            Log.d("Message is", message);
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
        VolleyQueue.getInstance(this).addToRequestQueue(request);
    }

    public void deleteInterest(View view){
        final String url = "http://nashdomain.esy.es/delete_interest.php";

        //parameters to post to php file
        final Map<String, String> params = new HashMap<String, String>();
        params.put("email", emailET.getText().toString());
        params.put("interest", nameET.getText().toString());

        //request to insert the user into the mysql database using php
        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);

                            boolean success = jsonResponse.getString("success").equals("1");
                            Log.d("Success", String.valueOf(success));

                            String message = jsonResponse.getString("message");
                            Log.d("Message is", message);
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
        VolleyQueue.getInstance(this).addToRequestQueue(request);
    }

    public void getFriends(View view) {
        final String url = "http://nashdomain.esy.es/get_friends.php";

        //parameters to post to php file
        final Map<String, String> params = new HashMap<String, String>();
        params.put("email", emailET.getText().toString());

        //request to insert the user into the mysql database using php
        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);

                            boolean success = jsonResponse.getString("success").equals("1");
                            Log.d("Success", String.valueOf(success));

                            String message = jsonResponse.getString("message");
                            Log.d("Message is", message);

                            //clips the brackets off of the php array
                            String friends = jsonResponse.getString("friends");
                            friends = friends.substring(1, friends.length() - 1);
                            Log.d("Friends is", friends);

                            //clips the quotation marks off of emails
                            String[] test = friends.split(",");
                            for (int i = 0; i < test.length; i++) {
                                test[i] = test[i].substring(1, test[i].length() - 1);
                            }
                            Log.d("Test is", test[0] + " " + test[1]);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.d("JSON failed to parse: ", response);
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("VolleyError at url ", url);
            }
        }
        ) {
            //Parameters inserted
            @Override
            protected Map<String, String> getParams() {
                return params;
            }
        };
        //put the request in the static queue
        VolleyQueue.getInstance(this).addToRequestQueue(request);
    }

    public void goToMainMenuFromRegistration(View view){
        Intent intent = new Intent(this, MainMenu.class);
        startActivity(intent);
    }

    /*
    Intent for MainActivity
     */
    public void changeToMainScreen(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
