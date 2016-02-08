package com.Project_run.CyborgLighting.ahad;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class userProfile extends AppCompatActivity {

    ImageView profileIcon;
    EditText txtname;
    EditText txtpassword;
    EditText txtcomfirmPassword;
    EditText txtDOB;
    Spinner spCountry;
    ArrayList<String> countrylist = new ArrayList<>();
    Button btnnext;


    //
    Pattern pattern;
    Matcher matcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        profileIcon = (ImageView) findViewById(R.id.imageProfile);
        profileIcon.setImageResource(R.drawable.detail);
        txtname = (EditText) findViewById(R.id.txtname);
        txtpassword = (EditText) findViewById(R.id.txtpassword);
        txtcomfirmPassword = (EditText) findViewById(R.id.txtComfirmPassword);
        btnnext = (Button) findViewById(R.id.btnnext1);

        txtDOB = (EditText) findViewById(R.id.txtDOB);


        //listing all the countries
        country();
        spCountry = (Spinner) findViewById(R.id.spCountry);
        ArrayAdapter adapter = new ArrayAdapter(userProfile.this, R.layout.support_simple_spinner_dropdown_item, countrylist);
        spCountry.setAdapter(adapter);


        Intent getEmail = getIntent();
        final String userEmail = getEmail.getExtras().getString("email");

        //this will check for all validity of the user and than proceed to sql insertion...
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    if(validPassword(txtpassword.getText().toString(),txtcomfirmPassword.getText().toString()) == true && validateDOB(txtDOB.getText().toString())==true && name(txtname.getText().toString())== true){

                        System.out.println("User details are correct: " + userEmail + " " + txtname.getText().toString() + " " + txtpassword.getText().toString() + " " + txtDOB.getText().toString() + " " + spCountry.getSelectedItem().toString());
                        Intent intent = new Intent(userProfile.this, menu_layout.class);
                        intent.putExtra("name", txtname.getText().toString());
                        intent.putExtra("email", userEmail.toString());
                        intent.putExtra("dob",txtDOB.getText().toString());
                        startActivity(intent);

                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }
        });

    }


    //creating and storing countries inside an arraylist
    public void country() {

        String[] locale = Locale.getISOCountries();
        for (String countryName : locale) {
            Locale countryNamex = new Locale("", countryName);
            countrylist.add(countryNamex.getDisplayCountry());
        }
    }
    //creating  validity for password
    public boolean validPassword(String password, String comfirmPassword) {
        if (password.toString().equals(comfirmPassword.toString())) {
            return true;
        }
        txtpassword.setError("Please Enter same password");
        return false;
    }
    //creating valid DOB
    public boolean validateDOB(String date) throws ParseException {

        DateFormat df = new SimpleDateFormat("mm/dd/yyyy");
        Date dateofBirth = df.parse(date.toString());
        Calendar dob = Calendar.getInstance();
        dob.setTime(dateofBirth);

        Calendar todayDate = Calendar.getInstance();
        int age = todayDate.get(Calendar.YEAR) - dob.get(Calendar.YEAR);

        if (todayDate.get(Calendar.DAY_OF_YEAR) <= dob.get(Calendar.DAY_OF_YEAR)) {
            age--;
        }
        if(age >= 18){
            System.out.println("Above 18: " + age);
            return true;
        } else{

            txtDOB.setError("Please Enter Age Above 18..");
            System.out.println("Above 18: " + age);
            return false;


        }

    }
    public boolean name(String x){

        if(x.equals("")){
            txtname.setError("Please Enter Name");
            return false;
        }

        return true;
    }

    }




