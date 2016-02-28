package com.example.amosmadalinneculau.objects;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class login extends AppCompatActivity {
    //U.I. Elements
    protected Button login_button;
    protected Button register_button;
    protected Button info_button;

    protected EditText email;
    protected EditText password;

    //mysql connection
    public MySQLConnector connector;

    //dialog
    public AlertDialog.Builder builder;

    private static final String myPref = "MyPref";
    private SharedPreferences settings;
    private SharedPreferences.Editor editor;
    Boolean autoLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        settings = getSharedPreferences(myPref, Context.MODE_PRIVATE);
        editor = settings.edit();

        //To prevent crashing, autoLogin is set to false for now
        //autoLogin = settings.getBoolean("save",true);

        autoLogin = false;

        if(autoLogin){
            //gotoMainScreen method
        } else {
            setContentView(R.layout.activity_login);
            email = (EditText) findViewById(R.id.txtEmailx);
            password = (EditText) findViewById(R.id.txtPassword);

            //dialog for login
            builder = new AlertDialog.Builder(this);

            builder.setMessage("Couldn't login")
                    .setTitle("There was a problem with your email or password.");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // User clicked OK button
                }
            });
        }

        // goes to the registration page...
        register_button = (Button) findViewById(R.id.btnRegistor);
        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToRegisterScreen(v);

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /*
    Intent for REGISTER
     */
    public void goToRegisterScreen(View view) {
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
    }

    public void checkLogin(View view){
       if(!autoLogin){

           final String url = "http://nashdomain.esy.es/loginUser.php";

           //parameters to post to php file
           final Map<String, String> params = new HashMap<>();
           params.put("email", email.getText().toString());
           params.put("password", password.getText().toString());

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

                               if(success){goToMainMenu();}
                               else{toasty(message);}

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
    }

    private void toasty(String message){
        Toast.makeText(this.getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    public void goToMainMenu() {
        Intent intent = new Intent(this, UserHomepage.class);
        startActivity(intent);
    }

    /*
    Get the input of user_email text field
     */
    public String getEmail() {
        return email.getText().toString();
    }

    /*
    Get the input of password text field
     */
    public String getPassword() {
        return password.getText().toString();
    }
}
