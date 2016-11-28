package com.example.sergiosiniy.beeradvicer.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class BeerBrand {

    private String beerName;
    private String beerType;
    private String beerDescription;


    public static ArrayList<BeerBrand> beerBrandArrayList;


    public BeerBrand(String beerBrand, String beerType, String beerDescription) {
        this.beerName = beerBrand;
        this.beerType = beerType;
        this.beerDescription=beerDescription;
    }


    public String getName() {
        return beerName;
    }

    public String getBeerType() {
        return beerType;
    }

    public String getBeerDescription() {
        return beerDescription;
    }


    public static void setBeerBrandArrayList(String jArray) {
        try {
            JSONObject beers = new JSONObject(jArray);
            JSONArray beerArray = beers.getJSONArray("beers");
            for (int i = 0; i < beerArray.length(); i++) {
                beerBrandArrayList.add((BeerBrand)beerArray.get(i));
            }
        } catch (JSONException e) {

        }

    }


}
