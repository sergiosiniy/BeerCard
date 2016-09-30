package com.example.sergiosiniy.beeradvicer;

import android.content.Intent;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class BeerDetailsActivity extends AppCompatActivity {

    public static final String BEER_BRAND="brand";
    public static final String BEER_DETAILS="details";
    private String beerBrandText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beer_details);
        TextView beerBrand = (TextView) findViewById(R.id.beer_brand_name);
        TextView beerDetails = (TextView) findViewById(R.id.beer_brand_details);
        beerBrand.setText(getIntent().getStringExtra(BEER_BRAND));
        beerDetails.setText(getIntent().getStringExtra(BEER_DETAILS));
        beerBrandText=getIntent().getStringExtra(BEER_BRAND)+"\n"+
                getIntent().getStringExtra(BEER_DETAILS);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_beer_details, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.share_beer:
                ShareCompat.IntentBuilder
                        .from(this) // getActivity() or activity field if within Fragment
                        .setText(beerBrandText)
                        .setType("text/plain") // most general text sharing MIME type
                        .setChooserTitle(getResources().getString(R.string.share_beer_button))
                        .startChooser();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
