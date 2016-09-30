package com.example.sergiosiniy.beeradvicer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class BeerDetailsActivity extends AppCompatActivity {

    public static final String BEER_BRAND="brand";
    public static final String BEER_DETAILS="details";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beer_details);
        TextView beerBrand = (TextView) findViewById(R.id.beer_brand_name);
        TextView beerDetails = (TextView) findViewById(R.id.beer_brand_details);
        beerBrand.setText(getIntent().getStringExtra(BEER_BRAND));
        beerDetails.setText(getIntent().getStringExtra(BEER_DETAILS));

    }
}
