package com.Project_run.CyborgLighting.ahad;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;


import java.util.regex.Pattern;

public class emailDetails extends AppCompatActivity {


    ImageView emailIcon;
    EditText txtemail;
    Button btnnext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_section);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //instatiating components of the UI..
        emailIcon = (ImageView) findViewById(R.id.emailHolder);
        emailIcon.setImageResource(R.drawable.lol);
        txtemail = (EditText) findViewById(R.id.txtemail);
        btnnext = (Button) findViewById(R.id.btnnext);



        btnnext.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(emailDetails.this, userProfile.class);
                if(!validEmail(txtemail.getText().toString())){
                    txtemail.setError("Invalid Email");
                } else{
                    intent.putExtra("email", txtemail.getText().toString());
                    startActivity(intent);
                }
            }
        });

    }

    //creating validation for email
    public boolean validEmail(String email){
        Pattern pattern   = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();

    }

}
