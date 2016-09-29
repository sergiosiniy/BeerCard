package com.example.sergiosiniy.beeradvicer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.sergiosiniy.beeradvicer.BeerBrand.beerBrandArrayList;

public class FindBeerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_beer);
    }


    public void onClickFindBeer(View view) {
        Spinner beerType = (Spinner) findViewById(R.id.beer_type);

        BeerBrand.setBeerBrandArrayList(beerType.getSelectedItem().toString());
        if(beerBrandArrayList.size()==0){
            Toast.makeText(this,"There is no beer brands of\n"+beerType.getSelectedItem()
                    .toString()+" type in the list.",Toast.LENGTH_SHORT).show();
        }else{
            Intent lightLager = new Intent(this, BeerListActivity.class);
            startActivity(lightLager);
        }

    }
}
