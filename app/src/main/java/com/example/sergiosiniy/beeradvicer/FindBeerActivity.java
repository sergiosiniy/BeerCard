package com.example.sergiosiniy.beeradvicer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

public class FindBeerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_beer);
    }

    /**
     * Gets beer from DB on button click
     * Doesn't works for now, since there is no DB for a while :)
     * @param view
     */
    public void onClickFindBeer(View view){
        BeerExpert beerExpert=new BeerExpert();
        StringBuffer sb = new StringBuffer();
        Spinner beerType = (Spinner) findViewById(R.id.beer_type);

        for(String brand:beerExpert.getBrands(String.valueOf(beerType.getSelectedItem()))){
            sb.append(brand+"\n");
        }

        TextView beerBrands = (TextView) findViewById(R.id.brands);
        beerBrands.setText(sb);
    }

}
