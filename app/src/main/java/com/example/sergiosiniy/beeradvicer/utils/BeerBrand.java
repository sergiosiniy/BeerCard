package com.example.sergiosiniy.beeradvicer.utils;

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
        return this.beerName;
    }

    public int getBeerType() {
        return this.beerType;
    }

    public String getBeerDescription() {
        return this.beerDescription;
    }


}
