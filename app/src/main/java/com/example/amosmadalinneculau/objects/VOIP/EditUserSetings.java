package com.example.amosmadalinneculau.objects.VOIP;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.amosmadalinneculau.objects.R;


/**
 * Created by Amos Madalin Neculau on 25/02/2016.
 */
public class EditUserSetings extends Activity{

    protected TextView user;
    protected TextView password;
    protected TextView server;
    protected Button done;
    protected Button back;
    public UserInformation ui;
    private String usr;
    private String pwd;
    private String svr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_user_information);

        user = (TextView) findViewById(R.id.usernameTextField);
        password = (TextView) findViewById(R.id.passwordTextField);
        server = (TextView) findViewById(R.id.serverTextField);
        done = (Button) findViewById(R.id.doneBtn);
        back = (Button) findViewById(R.id.backBtn);
    }

    public EditUserSetings(){
        EditInformation();
    }

    private void EditInformation(){

        usr = user.getText().toString();
        pwd = password.getText().toString();
        svr = server.getText().toString();

        ui = new UserInformation(usr,pwd,svr);
        //ADD TO DATABASE
    }

    public UserInformation getUserInformation(){
        return ui;
    }
}
