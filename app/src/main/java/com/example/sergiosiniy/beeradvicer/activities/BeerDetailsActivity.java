package com.example.sergiosiniy.beeradvicer.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.sergiosiniy.beeradvicer.R;
import com.example.sergiosiniy.beeradvicer.fragments.BeerDetailsFragment;
import com.example.sergiosiniy.beeradvicer.fragments.BeerListFragment;

public class BeerDetailsActivity extends AppCompatActivity {
    public static final String EXTRA_BEER_ITEM_ID="id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beer_details);

        BeerDetailsFragment beerDetailsFragment = (BeerDetailsFragment) getFragmentManager()
                .findFragmentById(R.id.beer_detail_frag);
        beerDetailsFragment.setBeerItemID((int) getIntent().getExtras().get(EXTRA_BEER_ITEM_ID));
    }


}
