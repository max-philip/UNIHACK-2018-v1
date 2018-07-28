package com.example.nicholas.unihack_2018_1;

import android.util.Log;

import com.google.firebase.FirebaseError;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by roflo on 28/07/2018.
 */

public class FirebaseAdapter {
    // Write a message to the database

    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private Query query;

    public FirebaseAdapter() {
        this.database = FirebaseDatabase.getInstance();
        this.myRef = database.getReference("message");
        myRef.setValue("Hello World!");
        Log.d("Create","Created adapter!");
    }

    public String pull() {
        Log.d("Pull", "Hi");
//        Log.d("Pull", "Testing Pull...");
//        String str = myRef.equalTo("message").toString();
//        Log.d("Pull", myRef.getKey());
//        Log.d("Pull", myRef.getParent());
//        Log.d("Pull", myRef.getKey());
//        Log.d("Pull data", str);
        return "lol";
    }

}
