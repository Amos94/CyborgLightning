package com.example.amosmadalinneculau.objects;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

/*        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        ListView lv = (ListView) findViewById(R.id.listView);

        final ArrayList<String> test = new ArrayList<String>();
        test.add("Amos");
        test.add("Cata");
        test.add("Mihaela");
        test.add("Bobo");
        test.add("Amos");
        test.add("Cata");
        test.add("Mihaela");
        test.add("Bobo");
        test.add("Amos");
        test.add("Cata");
        test.add("Mihaela");
        test.add("Bobo");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_activated_1,test);
        lv.setAdapter(arrayAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                Log.i("a: ",test.get(position));
                changeToConversation(view);
            }
        });

    }

    public void changeToConversation(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }



}
