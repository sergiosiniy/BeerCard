package com.example.sergiosiniy.beeradvicer.activities;

import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.sergiosiniy.beeradvicer.R;
import com.example.sergiosiniy.beeradvicer.fragments.BeerDetailsFragment;

public class BeerDetailsActivity extends AppCompatActivity {
    public static final String EXTRA_BEER_ITEM_ID="id";
    private BeerDetailsFragment beerFrDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beer_details);

        beerFrDetails = (BeerDetailsFragment) getFragmentManager()
                .findFragmentById(R.id.beer_detail_frag);

        beerFrDetails.setBeerItemID((int) getIntent().getExtras().get(EXTRA_BEER_ITEM_ID));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_beer_details, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item){
        TextView beerBrName = (TextView) beerFrDetails.getView().findViewById(R.id.beer_brand_name);
        TextView beerBrDetails = (TextView) beerFrDetails.getView().findViewById(R.id.beer_brand_details);
        switch(item.getItemId()){
            case R.id.share_beer:
                ShareCompat.IntentBuilder.from(BeerDetailsActivity.this)
                        .setType("text/plain")
                        .setSubject(beerBrName.getText().toString())
                        .setText(beerBrDetails.getText().toString())
                        .startChooser();
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
