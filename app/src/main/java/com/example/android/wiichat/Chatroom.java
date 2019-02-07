package com.example.android.wiichat;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;


public class Chatroom extends Activity{
    ArrayList<ChatUser> chat = new ArrayList<ChatUser>();
    private String chatName = MainActivity.getChatName();
    private TextView number_of_people;
    private int counter;
    private TextView location;
    private DatabaseReference myDB;
    private Context thisChatroom;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chatroom);
        counter = MainActivity.getCounter();
        number_of_people = (TextView) findViewById(R.id.number_of_people);
        number_of_people.setText(counter + "");

        location = (TextView) findViewById(R.id.location);
        location.setText("\n Current Location: " + MainActivity.getLocation());

        thisChatroom = this;


        MainActivity.setupDatabase();
        myDB = MainActivity.getDatabase();
        myDB.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                chat.add(new ChatUser(R.drawable.anonpic1, chatName, dataSnapshot.getValue().toString()));
                ChatUserAdapter dis = new ChatUserAdapter(thisChatroom, chat);
                ListView viewChat = (ListView) findViewById(R.id.chatrooom);
                viewChat.setAdapter(dis);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                chat.add(new ChatUser(R.drawable.anonpic1, chatName, "ERROR"));
                ChatUserAdapter dis = new ChatUserAdapter(thisChatroom, chat);
                ListView viewChat = (ListView) findViewById(R.id.chatrooom);
                viewChat.setAdapter(dis);
            }
        });


    }



    @Override
    public void onBackPressed() {
        MainActivity.decCounter();
        super.onBackPressed();
    }

    public void chatEnter(View v) {
        EditText message = (EditText) findViewById(R.id.editText);
        String messageWritten = String.valueOf(message.getText());
        if (checkMessage(messageWritten)) {
            myDB.setValue(messageWritten);
            message.setText("");

        }


    }
    public boolean checkMessage(String s){
        return s.length()>0 && s.split(" ").length != 0;
    }
}