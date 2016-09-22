package com.example.sergiosiniy.beeradvicer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class StartScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);
    }

    /**
     * Opens activity for beer searching
     * @param view
     */
    public void findBeer(View view){
        Intent findBeer = new Intent(this, FindBeerActivity.class);
        startActivity(findBeer);
    }

    /**
     * Opens activity for sending new beer brand
     * @param view
     */
    public void addNewBeer(View view){
        Intent addBeer = new Intent(this, AddNewBeer.class);
        startActivity(addBeer);
    }

    public void closeTheApp(View view){
        System.exit(1);
    }
}
