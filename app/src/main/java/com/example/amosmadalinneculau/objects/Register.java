package com.example.amosmadalinneculau.objects;

import android.content.Intent;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLException;
import java.sql.Statement;

public class Register extends AppCompatActivity {


    //Info for register
    private EditText email;
    private EditText name;
    private EditText password;
    private EditText dob;
    private boolean isMale;
    private boolean isActivated;
    private RadioButton maleRadioRegister;
    private RadioButton femaleRadioRegister;
    private RadioGroup radioGroup;
    private int toAddGender;
    private int toAddActivated;

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
        email = (EditText) findViewById(R.id.emailRegister);
        name = (EditText) findViewById(R.id.nameRegister);
        password = (EditText) findViewById(R.id.passwordRegister);
        dob = (EditText) findViewById(R.id.dateRegister);

        radioGroup = (RadioGroup) findViewById(R.id.radioGroupRegister);
        maleRadioRegister = (RadioButton) findViewById(R.id.maleRadioRegister);
        femaleRadioRegister = (RadioButton) findViewById(R.id.femaleRadioRegister);

        //help for the insertion
        if(maleRadioRegister.isChecked() == true)
            isMale = true;
        if(femaleRadioRegister.isChecked() == true)
            isMale = false;

        if(isMale == true) {
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
            insertData();
            //intent to main menu

        }
    }



    public void insertData(){

    }

    public void testGetData(View v){
        String output;

        SqlRequest sqlRequest = new SqlRequest(this, "get_all_users.php");

        output = sqlRequest.Run();
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
