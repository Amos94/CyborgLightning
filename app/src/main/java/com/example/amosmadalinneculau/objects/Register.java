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
            insertData();
            //intent to main menu
        }
    }

    public void insertData(){
    }

    public void testGetData(View v){
        String email = emailET.getText().toString();
        String name = "John";
        String password = "thed";
        String dob = "2/2/2012";
        String gender = "T";

        //parameters to post to php file
        Map<String, String> params = new HashMap<String, String>();
        params.put("email", email);
        params.put("name", name);
        params.put("password", password);
        params.put("dob", dob);
        params.put("gender", gender);

        SqlTask sqlWork = new SqlTask();
        sqlWork.execute(params,this.getApplicationContext());
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
    private class SqlTask extends AsyncTask<Object,Void,SqlRequest> {
        @Override
        protected void onPostExecute(SqlRequest sqlRequest) {
            super.onPostExecute(sqlRequest);
            try {
                Log.d("Response is ", response.toString());
                //gets 'success' part of json output. 1 if good, 0 if fail
                String success = response.getString("success");
                Log.d("success is ", success);

                //gets 'user' part of json object
                String temp = response.getString("user");
                JSONObject jsonObject1 = new JSONObject(temp.substring(1,temp.length()-1));
                Log.d("user is ", jsonObject1.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        protected SqlRequest doInBackground(Object... maps) {
            Map params = (Map)maps[0];
            //sending request to php file with php file name and params
            SqlRequest sqlRequest = new SqlRequest((Context)maps[1], "get_user.php", params);

            //runs the php post request and returns result as json object
            JSONObject response = sqlRequest.getOutput();

            setRequest(response);
            return sqlRequest;
        }

        //TODO wait for server to respond before calling getoutput
    }

    public void setRequest(JSONObject temp){
        response = temp;
    }
}
