package com.example.amosmadalinneculau.objects;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
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

    public void goToMainScreen(View view){
       /* if(!autoLogin) {
        }
        */
        Intent intent = new Intent(this, MainMenu.class);
        startActivity(intent);
    }

    /*
    Intent for Login
    Verify if the email and password works/ exists
     */
//    public void goToMainScreen(View view) {
//        //to be done when I integrate with Simeon for DATABASES
//        //really easy. search in the table
//        //if ok then go to main screen
//        //if not search if the eamil exists
//        //if so -> problem with password
//        //else suggest to register
//
//        Intent intent = new Intent(this, MainMenu.class);
//        if (validLogin())
//            startActivity(intent);
//        else {
//            //dialog
//            builder.create();
//        }
//
//
//    }

//    public boolean validLogin() {
//        boolean toReturn = false;
//
//        try {
//            Statement statement = connector.connection.createStatement();
//            ResultSet resultSet = statement.executeQuery("Select * from `project_run`.`users`");
//
//            while (resultSet.next()) {
//                //read the data from database
//                //compare with the user input
//
//                if (resultSet.getString("email") == email.getText().toString() && resultSet.getString("password") == password.getText().toString()) {
//                    toReturn = true;
//                    break;
//                }
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return toReturn;
//    }

    public void continueRegistration(View view) {

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
