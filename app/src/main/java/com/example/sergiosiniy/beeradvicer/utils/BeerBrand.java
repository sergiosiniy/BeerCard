package com.example.sergiosiniy.beeradvicer.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class BeerBrand {

    private String beerName;
    private int beerType;
    private String beerDescription;


    public static ArrayList<BeerBrand> beerBrandArrayList;


    public BeerBrand(String beerBrand, String beerDescription, int beerType) {
        this.beerName = beerBrand;
        this.beerType=beerType;
        this.beerDescription=beerDescription;
    }


    public String getName() {
        return beerName;
    }

    public int getBeerType() {
        return beerType;
    }

    public String getBeerDescription() {
        return beerDescription;
    }


    public static void setBeerBrandArrayList(String jArray) {
        try {
            beerBrandArrayList = new ArrayList<>();
            JSONObject beers = new JSONObject(jArray);
            JSONArray beerArray = beers.getJSONArray("beers");
            for (int i = 0; i < beerArray.length(); i++) {
                JSONObject beerBrand = beerArray.getJSONObject(i);
                beerBrandArrayList.add(new BeerBrand(beerBrand.getString("beerBrand"),
                        beerBrand.getString("beerDescription"),beerBrand.getInt("beerType")));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


}
