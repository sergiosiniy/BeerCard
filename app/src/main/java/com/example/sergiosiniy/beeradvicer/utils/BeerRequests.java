package com.example.sergiosiniy.beeradvicer.utils;

public class BeerRequests {

    public static final String BASE_URL = "http://31.134.121.230:55556/beermap";
    public static final String ALL_BEERS = "http://31.134.121.230:55556/beermap/beers";
    public static final String ALL_TYPES = "http://31.134.121.230:55556/beermap/types";
    public static final String FIND_ONE_BEER_BY_ID =
            "http://31.134.121.230:55556/beermap/beer?id=";
    public static final String FIND_ONE_TYPE_BY_ID =
            "http://31.134.121.230:55556/beermap/type?id=";
    public static final String FIND_ALL_BEERS_BY_STRING =
            "http://31.134.121.230:55556/beermap/beersearch?str=";
    public static final String FIND_ALL_TYPES_BY_STRING =
            "http://31.134.121.230:55556/beermap/typesearch?str=";
}
