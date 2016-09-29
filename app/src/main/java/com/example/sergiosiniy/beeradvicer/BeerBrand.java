package com.example.sergiosiniy.beeradvicer;

import android.widget.Toast;

import java.util.ArrayList;

public class BeerBrand {

    private String name;
    private String beerType;
    private String beerDescription;
    private int imageResourceId;
    private static final BeerBrand allBeerBrands[] = {
            new BeerBrand("Chernigovskoe svetloe", "Light Lager", "Just good and cheap beer"),
            new BeerBrand("Mudachevskoe", "Light Lager", "Just good and cheap beer"),
            new BeerBrand("LenPridumat Brand", "Light Lager", "Just good and cheap beer"),
            new BeerBrand("Persha privatna browarnya Lager", "Pilsner", "Just good and cheap beer"),
            new BeerBrand("Just for test Lager", "Pilsner", "Just good and cheap beer"),
            new BeerBrand("Mikulin Lager", "European amber lager", "Just good and cheap beer"),
            new BeerBrand("MikuleVisch", "European amber lager", "Just good and cheap beer"),
            new BeerBrand("Obrigaysa Pivo", "European amber lager", "Just good and cheap beer"),
            new BeerBrand("Dlya gopoti na Troyeschine", "European amber lager", "Just good and cheap beer"),
            new BeerBrand("Gitomirskoe Svetloe", "Dark Lager", "Just good and cheap beer"),
            new BeerBrand("Lvivske 1715", "Bock", "Just good and cheap beer")
    };

    static ArrayList<BeerBrand> beerBrandArrayList;

    private BeerBrand(String name, String beerType, String beerDescription){
        this.name=name;
        this.beerDescription=beerDescription;
        this.beerType=beerType;
    }

    static void setBeerBrandArrayList(String beerType){
        beerBrandArrayList = new ArrayList<>();
        for(BeerBrand brand:allBeerBrands){
            if(brand.getBeerType().equals(beerType)) {
                beerBrandArrayList.add(brand);
            }
        }
    }

    private String getBeerType(){
        return this.beerType;
    }
    public String getName(){
        return this.name;
    }
    public String getBeerDescription(){
        return this.beerDescription;
    }
    public String toString(){
        return this.name;
    }
}
