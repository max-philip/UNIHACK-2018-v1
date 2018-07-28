package com.example.nicholas.unihack_2018_1;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.nicholas.unihack_2018_1.algorithm.AStar;
import com.example.nicholas.unihack_2018_1.algorithm.classes.Coordinate;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import static android.content.ContentValues.TAG;

import com.example.nicholas.unihack_2018_1.algorithm.DataParser;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private TextView testMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {



            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }



            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = (TextView) findViewById(R.id.message2);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
//        DataParser.readData();
    }

    @Override
    protected void onStart() {
        super.onStart();

        ValueEventListener postListener = new ValueEventListener() {
            //            Log.d("Listening", "Listening!");
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                DataParser.readData(dataSnapshot);
                Coordinate[] testPath = AStar.getBestPath(
                        new Coordinate(-37.800449, 144.963938),
                        new Coordinate(-37.807675, 144.973077)
                );

                for (Coordinate c : testPath) {
                    System.out.printf("Next: (%f, %f)\n", c.getLatitude(), c.getLongitude());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        };

        FirebaseAdapter.myRef.addListenerForSingleValueEvent(postListener);
    }
}
