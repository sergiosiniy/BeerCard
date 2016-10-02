package com.example.sergiosiniy.beeradvicer.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.sergiosiniy.beeradvicer.R;

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
        Intent addBeer = new Intent(this, AdviceNewBeer.class);
        startActivity(addBeer);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.send_beer:
                Intent addBeer = new Intent(this, AdviceNewBeer.class);
                startActivity(addBeer);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void closeTheApp(View view){
        System.exit(1);
    }
}
