package com.example.sergiosiniy.beeradvicer.activities;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.sergiosiniy.beeradvicer.R;
import com.example.sergiosiniy.beeradvicer.fragments.BeerDetailsFragment;
import com.example.sergiosiniy.beeradvicer.fragments.BeerListFragment;

import static com.example.sergiosiniy.beeradvicer.activities.BeerDetailsActivity.EXTRA_BEER_ITEM_ID;

public class BeerFragmentsActivity extends AppCompatActivity implements BeerListFragment.
        BeerListListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beer_fragments);
    }

    @Override
    public void beerItemClicked(long id) {
        View view = findViewById(R.id.fragment_container);
        if(view!=null){
            BeerDetailsFragment detailsFragment = new BeerDetailsFragment();
            detailsFragment.setBeerItemID(id);
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            fragmentTransaction.replace(R.id.fragment_container,detailsFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }else{
            Intent beerDetailsActivity = new Intent(this,BeerDetailsActivity.class)
                    .putExtra(EXTRA_BEER_ITEM_ID, (int) id);
            startActivity(beerDetailsActivity);
        }
    }


}
