package com.example.sergiosiniy.beeradvicer;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
}
