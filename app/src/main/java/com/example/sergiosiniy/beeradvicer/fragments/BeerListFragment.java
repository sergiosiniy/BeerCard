package com.example.sergiosiniy.beeradvicer.fragments;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ListFragment;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.sergiosiniy.beeradvicer.utils.BeerBrand;


public class BeerListFragment extends ListFragment {

    public BeerListFragment() {
        //Required empty public constructor
    }

    /**
     * Used for interaction of ListFragment with BeerFragmentsActivity
     * Implemented in BeerFragmentsActivity
     */
    public interface BeerListListener {
        void beerItemClicked(long id);
    }

    private BeerListListener beerListener;

    /**
     * Will be called on API 23 or above
     */
    @TargetApi(23)
    @Override
    public void onAttach(Context context) {
        //This method avoid to call super.onAttach(context) if I'm not using api 23 or more
        super.onAttach(context);
        onAttachToContext(context);
    }

    /*
     * Deprecated on API 23
     * Use onAttachToContext instead
     */
    @SuppressWarnings("deprecation")
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (Build.VERSION.SDK_INT < 23) {
            onAttachToContext(activity);
        }
    }

    /*
    * This method will be called from one of the two previous methods
    * and activity or context will be used
    */
    protected void onAttachToContext(Context context) {
        this.beerListener = (BeerListListener) context;
    }

    /**
     * Set list adapter by ArrayList of beers filtered by beer type.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ListAdapter beerList = new ArrayAdapter<>(inflater.getContext(),
                android.R.layout.simple_list_item_1,
                BeerBrand.beerBrandArrayList);
        setListAdapter(beerList);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    /**
     * Pass the id of clicked item in the FragmentList of beers and call method.
     */
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        if(beerListener!=null){
            beerListener.beerItemClicked(id);
        }
    }
}
