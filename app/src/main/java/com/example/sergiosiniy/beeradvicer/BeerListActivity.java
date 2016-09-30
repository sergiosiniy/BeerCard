package com.example.sergiosiniy.beeradvicer;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.List;

public class BeerListActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ListView beerList = getListView();
        ListAdapter beerBrandArrayAdapter = new ArrayAdapter<BeerBrand>(this,
                android.R.layout.simple_list_item_1,
                BeerBrand.beerBrandArrayList);
        beerList.setAdapter(beerBrandArrayAdapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Intent beerDetails = new Intent(this,BeerDetailsActivity.class)
                .putExtra(BeerDetailsActivity.BEER_BRAND, BeerBrand.beerBrandArrayList.get((int) id).getName())
                .putExtra(BeerDetailsActivity.BEER_DETAILS,BeerBrand.beerBrandArrayList.get((int) id).getBeerDescription());
        startActivity(beerDetails);


    }
}
