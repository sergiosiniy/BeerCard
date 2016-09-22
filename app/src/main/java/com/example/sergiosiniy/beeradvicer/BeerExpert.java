package com.example.sergiosiniy.beeradvicer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by helpdeskss on 22.07.2016.
 */
public class BeerExpert {
    /**
     * Used as beer DB for a while.
     * @param beerType
     * @return
     */
    List<String> getBrands(String beerType){
        List<String> listOfBrands = new ArrayList<String>();
        switch(beerType.toLowerCase()){
            case "light lager":
                listOfBrands.add("Черниговское светлое");
                listOfBrands.add("Перша приватна броварня Лагер");
                listOfBrands.add("Микулин Лагер");
                listOfBrands.add("Житомирское светлое");
                listOfBrands.add("Львовское 1715");
                listOfBrands.add("Оболонь премиум");
                listOfBrands.add("Сармат светлое");
                listOfBrands.add("Славутич светлое");


                break;
            case "dark":
                listOfBrands.add("Tuborg Black");
                listOfBrands.add("Staropramen Temne");
                listOfBrands.add("Дубовий гай");
                listOfBrands.add("Княже");
                listOfBrands.add("Бiла Нiч");
                listOfBrands.add("Авторське");
                listOfBrands.add("Портер");
                break;
            case "amber":
                listOfBrands.add("Faxe Amber");
                listOfBrands.add("Dubuisson, \"Bush Amber\"");
                listOfBrands.add("Pale Ale");
                break;
            case "brown":
                listOfBrands.add("Newcastle \" Brown Ale\"");
                listOfBrands.add("Радомышль Олд браун");
                break;
            default:
                listOfBrands.add("Incorrect input! Please, contact the developer: \nserhii.synohub@gmail.com");
        }

        return listOfBrands;
    }
}
