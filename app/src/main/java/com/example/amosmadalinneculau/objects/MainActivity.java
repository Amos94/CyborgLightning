package com.example.amosmadalinneculau.objects;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import java.sql.*;

public class MainActivity extends AppCompatActivity {

    //U.I. Elements
    protected Button login_button;
    protected Button register_button;
    protected Button info_button;

    protected EditText email;
    protected EditText password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //setContentView(R.layout.activity_main_menu);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
/*        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        email = (EditText) findViewById(R.id.user_email);
        password = (EditText) findViewById(R.id.password_textfield);

        MySQLConnector connector = new MySQLConnector();

        connector.sqlOpenConnection();

        if(connector.getStatus() == "CONNECTED")
            Log.i("Connection: ", "successful");
        else Log.i("Connection: ","Couldn't establish the connection");
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
    public void goToRegisterScreen(View view){
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
    }

    /*
    Intent for Login
    Verify if the email and password works/ exists
     */
    public void goToMainScreen(View view){
        //to be done when I integrate with Simeon for DATABASES
        //really easy. search in the table
        //if ok then go to main screen
        //if not search if the eamil exists
        //if so -> problem with password
        //else suggest to register


    }

    public void continueRegistration(View view){

    }

    /*
    Get the input of user_email text field
     */
    public String getEmail(){
        return email.getText().toString();
    }

    /*
    Get the input of password text field
     */
    public String getPassword(){

        return password.getText().toString();
    }

}
