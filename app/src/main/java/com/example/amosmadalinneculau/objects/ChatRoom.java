package com.example.amosmadalinneculau.objects;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Map;

public class ChatRoom extends AppCompatActivity {

    EditText createMessege;
    ListView conversation;
    Button sendBtn;
    ArrayList<String> messages;
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);
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

        createMessege = (EditText) findViewById(R.id.createMessege);
        conversation = (ListView) findViewById(R.id.chatArea);
        sendBtn = (Button) findViewById(R.id.sendBtn);

        messages = new ArrayList<String>();

        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_activated_1, messages);



    }

    public void sendMessage(View view){
        messages.add(createMessege.getText().toString());
        createMessege.setText("");
        createMessege.setHint("Write your messege here");
        conversation.setAdapter(arrayAdapter);

    }

}
