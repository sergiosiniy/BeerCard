package com.example.sergiosiniy.beeradvicer.activities;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sergiosiniy.beeradvicer.R;
import com.example.sergiosiniy.beeradvicer.fragments.BeerDetailsFragment;
import com.example.sergiosiniy.beeradvicer.fragments.BeerListFragment;

import static com.example.sergiosiniy.beeradvicer.activities.BeerDetailsActivity.EXTRA_BEER_ITEM_ID;

public class BeerFragmentsActivity extends Activity implements BeerListFragment.
        BeerListListener {

    private BeerDetailsFragment beerFrDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beer_fragments);
    }

    @Override
    public void beerItemClicked(long id) {
        View view = findViewById(R.id.fragment_container);
        if (view != null) {
            beerFrDetails = new BeerDetailsFragment();
            beerFrDetails.setBeerItemID(id);
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            fragmentTransaction.replace(R.id.fragment_container, beerFrDetails);
            fragmentTransaction.commit();
        } else {
            Intent beerDetailsActivity = new Intent(this, BeerDetailsActivity.class)
                    .putExtra(EXTRA_BEER_ITEM_ID, (int) id);
            startActivity(beerDetailsActivity);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_beer_details, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        try {
            TextView beerBrName = (TextView) beerFrDetails.getView()
                    .findViewById(R.id.beer_brand_name);
            TextView beerBrDetails = (TextView) beerFrDetails.getView()
                    .findViewById(R.id.beer_brand_details);

            switch (item.getItemId()) {
                case R.id.share_beer:
                    ShareCompat.IntentBuilder.from(BeerFragmentsActivity.this)
                            .setType("text/plain")
                            .setSubject(beerBrName.getText().toString())
                            .setText(beerBrName.getText().toString()+" - "
                                    +beerBrDetails.getText().toString())
                            .startChooser();
                default:
                    return super.onOptionsItemSelected(item);
            }
        } catch (NullPointerException e) {
            Toast.makeText(this, "No items were selected!", Toast.LENGTH_SHORT).show();
            return super.onOptionsItemSelected(item);
        }



    }


}
