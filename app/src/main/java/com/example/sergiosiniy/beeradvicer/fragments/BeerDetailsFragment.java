package com.example.sergiosiniy.beeradvicer.fragments;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sergiosiniy.beeradvicer.R;
import com.example.sergiosiniy.beeradvicer.utils.BeerBrand;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

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
            BeerBrand beerBrand = BeerBrand.beerBrandArrayList.get((int) beerItemID);
            if(beerBrand.getBeerImageUrl()!=null) {
                new GetImage().execute(beerBrand);
            }
            TextView beerName = (TextView) view.findViewById(R.id.beer_brand_name);
            TextView beerDescription = (TextView) view.findViewById(R.id.beer_brand_description);
            beerName.setText(beerBrand.getName());
            beerDescription.setText(beerBrand.getBeerDescription());

        }
    }

    public void setBeerItemID(long id){
        this.beerItemID=id;
    }

    private class GetImage extends AsyncTask<BeerBrand,Void,Bitmap> {

        ImageView beerImage;
        @Override
        protected void onPreExecute() {
            View view = getView();
            beerImage = (ImageView) view.findViewById(R.id.beer_image);
        }

        @Override
        protected Bitmap doInBackground(BeerBrand... beerBrands) {
            try {
                URL url = new URL(beerBrands[0].getBeerImageUrl());
                Bitmap bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                return bitmap;
            }catch(IOException e){
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if(bitmap!=null)
            beerImage.setImageBitmap(bitmap);
        }
    }


}
