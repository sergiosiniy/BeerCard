package com.example.sergiosiniy.beeradvicer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class FindBeerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_beer);
    }


    public void onClickFindBeer(View view) {
        Spinner beerType = (Spinner) findViewById(R.id.beer_type);
        BeerBrand.setBeerBrandArrayList(beerType.getSelectedItem().toString());

        Intent lightLager = new Intent(this, BeerListActivity.class);
        startActivity(lightLager);
    }
}
