package com.example.sergiosiniy.beeradvicer.fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sergiosiniy.beeradvicer.R;
import com.example.sergiosiniy.beeradvicer.resources.BeerBrand;

/**
 * A simple {@link Fragment} subclass.
 */
public class BeerDetailsFragment extends Fragment {

    private long beerItemID;

    public BeerDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putLong("beerItemID",beerItemID);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if(savedInstanceState!=null){
            beerItemID = savedInstanceState.getLong("beerItemID");
        }
        return inflater.inflate(R.layout.fragment_beer_details, container, false);
    }

    /**
     * On fragment start gets fragment's View and finds title and details by id.
     * Uses beerItemID to get entry from ArrayList of beer brands.
     * Sets the TextViews by BeerBrand object's values of name and description.
     */
    @Override
    public void onStart() {
        super.onStart();
        View view = getView();
        if(view!=null){
            TextView beerTitile = (TextView) view.findViewById(R.id.beer_brand_name);
            TextView beerDetails = (TextView) view.findViewById(R.id.beer_brand_details);
            BeerBrand beerBrand = BeerBrand.beerBrandArrayList.get((int) beerItemID);
            beerTitile.setText(beerBrand.getName());
            beerDetails.setText(beerBrand.getBeerDescription());
        }
    }

    public void setBeerItemID(long id){
        this.beerItemID=id;
    }
}
