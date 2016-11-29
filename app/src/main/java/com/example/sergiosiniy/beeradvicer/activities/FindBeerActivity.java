package com.example.sergiosiniy.beeradvicer.activities;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sergiosiniy.beeradvicer.R;
import com.example.sergiosiniy.beeradvicer.utils.BeerBrand;
import com.example.sergiosiniy.beeradvicer.utils.BeerRequests;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class FindBeerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_beer);
    }

    /**
     * Get selected item from Spinner and filter array.
     * If there is such items start activity with list of filtered items.
     *
     * @param view
     */
    public void onClickFindBeer(View view) {
        new BeerSearcher().execute();
    }


    private class BeerSearcher extends AsyncTask<Void, Void, String> {
        HttpURLConnection serverConnection;
        BufferedReader br;
        String jsonResult = "fail";
        String url;


        @Override
        protected void onPreExecute() {
            EditText beerSearch = (EditText) findViewById(R.id.find_beer_edittext);
            String searchSequence = beerSearch.getText().toString();
            if(searchSequence.contains(" ")){
                searchSequence=searchSequence.replace(" ","%20");
            }
            if (beerSearch.getText().toString().length() != 0) {
                url = BeerRequests.FIND_ALL_BEERS_BY_STRING + searchSequence;
            } else {
                url = BeerRequests.ALL_BEERS;
            }

        }

        @Override
        protected String doInBackground(Void... s) {
            StringBuffer sb = new StringBuffer();
            try {
                URL beerServerUrl = new URL(url);
                serverConnection = (HttpURLConnection) beerServerUrl.openConnection();
                serverConnection.setRequestMethod("GET");
                serverConnection.connect();

                InputStream inputStream = serverConnection.getInputStream();
                br = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }

                jsonResult = sb.toString();
                return jsonResult;

            } catch (MalformedURLException e) {
                url = "Malformed URL Exception! Something wrong with url connection.";
                e.printStackTrace();

            } catch (IOException e) {
                url = "IO Exception";
                e.printStackTrace();

            }

            return jsonResult;
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (!result.equals("fail")) {
                try {
                    BeerBrand.beerBrandArrayList = new ArrayList<>();
                    JSONObject jsonObject = new JSONObject(result);
                    JSONArray beerArray = jsonObject.getJSONArray("beers");
                    for (int i = 0; i < beerArray.length(); i++) {
                        JSONObject beerBrand = beerArray.getJSONObject(i);
                        BeerBrand.beerBrandArrayList.add(new BeerBrand(beerBrand
                                .getString("beerBrand"), beerBrand.getString("beerDescription")
                                , beerBrand.getInt("beerType")));
                    }
                    Intent beerList = new Intent(FindBeerActivity.this, BeerFragmentsActivity.class);
                    startActivity(beerList);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Toast.makeText(FindBeerActivity.this, url, Toast.LENGTH_SHORT).show();
            }
        }
    }

}