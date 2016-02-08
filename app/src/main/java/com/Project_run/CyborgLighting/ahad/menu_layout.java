package com.Project_run.CyborgLighting.ahad;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class menu_layout extends AppCompatActivity {

    DrawerLayout drawerLayout;
    Toolbar toolbar;
    ActionBar actionBar;
    TextView textView;
    TextView userName;
    TextView userEmail;
    TextView txtwelcome;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
        actionBar.setDisplayHomeAsUpEnabled(true);
        drawerLayout = (DrawerLayout) findViewById(R.id.navigation_drawer_layout);


        Intent getDetail = getIntent();
        String name = getDetail.getExtras().getString("name");
        String email = getDetail.getExtras().getString("email");
        String dob = getDetail.getExtras().getString("dob");

        userName = (TextView) findViewById(R.id.userName);
        userName.setText(name.toString());
        userEmail = (TextView) findViewById(R.id.email_id);
        userEmail.setText(email.toString());

        txtwelcome =(TextView) findViewById(R.id.txtwelcome);
        txtwelcome.setText(name + " " + dob);




        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        if (navigationView != null) {
            setupNavigationDrawerContent(navigationView);
        }

        setupNavigationDrawerContent(navigationView);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupNavigationDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        textView = (TextView) findViewById(R.id.txtwelcome);
                        switch (menuItem.getItemId()) {
                            case R.id.item_navigation_drawer_message:
                                menuItem.setChecked(true);
                                txtwelcome.setText(menuItem.getTitle());
                                drawerLayout.closeDrawer(GravityCompat.START);
                                return true;
                            case R.id.item_navigation_drawer_location:
                                menuItem.setChecked(true);
                                txtwelcome.setText(menuItem.getTitle());
                                drawerLayout.closeDrawer(GravityCompat.START);
                                return true;
                            case R.id.item_navigation_drawer_voice_call:
                                menuItem.setChecked(true);
                                txtwelcome.setText(menuItem.getTitle());
                                drawerLayout.closeDrawer(GravityCompat.START);
                                return true;
                            case R.id.item_navigation_drawer_chat_history:
                                menuItem.setChecked(true);
                                txtwelcome.setText(menuItem.getTitle());
                                drawerLayout.closeDrawer(GravityCompat.START);
                                return true;
                            case R.id.item_navigation_drawer_settings:
                                menuItem.setChecked(true);
                                txtwelcome.setText(menuItem.getTitle());
                                Toast.makeText(menu_layout.this, "Launching " + menuItem.getTitle().toString(), Toast.LENGTH_SHORT).show();
                                drawerLayout.closeDrawer(GravityCompat.START);
                                Intent intent = new Intent(menu_layout.this, settingActivity.class);
                                startActivity(intent);
                                return true;
                            case R.id.item_navigation_drawer_help_and_feedback:
                                menuItem.setChecked(true);
                                Toast.makeText(menu_layout.this, menuItem.getTitle().toString(), Toast.LENGTH_SHORT).show();
                                drawerLayout.closeDrawer(GravityCompat.START);
                                return true;
                        }
                        return true;
                    }
                });
    }
}

