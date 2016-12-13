package com.example.sergiosiniy.beeradvicer.utils;

import java.util.ArrayList;

public class BeerBrand {

    private String beerName;
    private int beerType;
    private String beerDescription;
    private String beerImageUrl;


    public static ArrayList<BeerBrand> beerBrandArrayList;


    public BeerBrand(String beerBrand, String beerDescription, int beerType, String beerImageUrl) {
        this.beerName = beerBrand;
        this.beerType=beerType;
        this.beerDescription=beerDescription;
        this.beerImageUrl=beerImageUrl;
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
    public String getBeerImageUrl() {
        return beerImageUrl;
    }

    public String toString(){
        return this.beerName;
    }


}
