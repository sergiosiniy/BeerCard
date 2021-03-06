package com.example.sergiosiniy.beeradvicer.activities;

import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.transition.Slide;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
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
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.URL;
import java.util.ArrayList;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FindBeerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS);
        setContentView(R.layout.activity_find_beer);
        EditText findBeer = (EditText) findViewById(R.id.find_beer_edittext);
        new NetworkConnectionChecker().execute();
        findBeer.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    new BeerSearcher().execute();
                    return true;
                }
                return false;
            }
        });
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


    private class BeerSearcher extends AsyncTask<Void, Integer, String> {

        ProgressDialog progressDialog = new ProgressDialog(FindBeerActivity.this);
        HttpURLConnection serverConnection;
        BufferedReader br;
        String jsonResult = "fail";
        String request;


        @Override
        protected void onPreExecute() {
            new NetworkConnectionChecker().execute();
            this.progressDialog.setMessage("Please, wait");
            this.progressDialog.setCancelable(false);
            this.progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            this.progressDialog.setIcon(R.mipmap.connecting);
            this.progressDialog.show();
            EditText beerSearch = (EditText) findViewById(R.id.find_beer_edittext);
            String searchSequence = beerSearch.getText().toString();
            if(searchSequence.contains(" ")){
                searchSequence=searchSequence.replace(" ","%20");
            }
            if (beerSearch.getText().toString().length() != 0) {
                request = BeerRequests.FIND_ALL_BEERS_BY_STRING + searchSequence;
            } else {
                request = BeerRequests.ALL_BEERS;
            }

        }

        @Override
        protected String doInBackground(Void... s) {
            StringBuffer sb = new StringBuffer();
            try {
                URL beerServerUrl = new URL(request);
                serverConnection = (HttpURLConnection) beerServerUrl.openConnection();
                serverConnection.setRequestMethod("GET");
                serverConnection.connect();

                InputStream inputStream = serverConnection.getInputStream();
                br = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                    publishProgress();
                }

                jsonResult = sb.toString();
                return jsonResult;

            } catch (MalformedURLException e) {
                request = "Malformed URL Exception! Something wrong with url connection.";
                e.printStackTrace();
                return jsonResult;
            } catch (IOException e) {
                request = "Can't get data from server." +
                        "Server is unreachable.";
                e.printStackTrace();
                return jsonResult;
            }
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if(this.progressDialog.isShowing()){
                this.progressDialog.dismiss();
            }
            if (!result.equals("fail")) {
                try {
                    BeerBrand.beerBrandArrayList = new ArrayList<>();
                    JSONObject jsonObject = new JSONObject(result);
                    JSONArray beerArray = jsonObject.getJSONArray("beers");
                    for (int i = 0; i < beerArray.length(); i++) {
                        JSONObject beerBrand = beerArray.getJSONObject(i);
                        BeerBrand.beerBrandArrayList.add(new BeerBrand(beerBrand
                                .getString("beerBrand"), beerBrand.getString("beerDescription")
                                , beerBrand.getInt("beerType"), beerBrand.getString("beerImage")));
                    }

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        getWindow().setExitTransition(new Slide());
                        Intent beerList = new Intent(FindBeerActivity.this, BeerFragmentsActivity.class);
                        startActivity(beerList, ActivityOptions
                                .makeSceneTransitionAnimation(FindBeerActivity.this).toBundle());
                    }else {
                        Intent beerList = new Intent(FindBeerActivity.this, BeerFragmentsActivity.class);
                        startActivity(beerList);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Toast.makeText(FindBeerActivity.this, request, Toast.LENGTH_LONG).show();
            }
        }
    }

    private boolean isNetworkConnected(){

        final ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo()!=null;
    }

    private boolean isServerReachable(){

        try {
            SocketAddress socketAddress = new InetSocketAddress("31.134.121.230",55556);
            // Create an unbound socket
            Socket sock = new Socket();

            // This method will block no more than timeoutMs.
            // If the timeout occurs, SocketTimeoutException is thrown.
            sock.connect(socketAddress, 2000);
            sock.close();
            return true;
        }catch(Exception e){
            return false;
        }
    }

    private void noServerConnectionDialog(Boolean isServerReachable){

            if (!isServerReachable) {
                AlertDialog.Builder builder = new AlertDialog.Builder(FindBeerActivity.this);
                builder.setTitle("Server is unavailable.")
                        .setMessage(getResources().getString(R.string.dialog_no_srv_connection))
                        .setIcon(R.mipmap.no_connection)
                        .setCancelable(false)
                        .setPositiveButton(R.string.quit_dialog_button, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                System.exit(1);
                            }
                        })
                        .setNegativeButton(R.string.retry_dialog_button, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                new ServerAvailabilityChecker().execute();
                            }

                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
    }

    private void noNetworkConnectionDialog(Boolean isNetworkConnected){

        if(!isNetworkConnected){
            AlertDialog.Builder builder = new AlertDialog.Builder(FindBeerActivity.this);
            builder.setTitle("No network connection!")
                    .setMessage(getResources().getString(R.string.dialog_no_net_connection))
                    .setIcon(R.mipmap.no_connection)
                    .setCancelable(false)
                    .setPositiveButton(R.string.quit_dialog_button, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            System.exit(1);
                        }
                    })
                    .setNegativeButton(R.string.retry_dialog_button, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            new NetworkConnectionChecker().execute();
                        }

                    });
            AlertDialog alert = builder.create();
            alert.show();
        }else {
            new ServerAvailabilityChecker().execute();
        }
    }

    private class ServerAvailabilityChecker extends AsyncTask<Void,Void,Boolean> {

        @Override
        protected Boolean doInBackground(Void... voids) {
            return isServerReachable();
        }

        @Override
        protected void onPostExecute(Boolean isReachable) {
            noServerConnectionDialog(isReachable);

        }
    }

    private class NetworkConnectionChecker extends AsyncTask<Void,Void,Boolean> {

        @Override
        protected Boolean doInBackground(Void... voids) {
            return isNetworkConnected();
        }

        @Override
        protected void onPostExecute(Boolean isConnected) {
            noNetworkConnectionDialog(isConnected);

        }
    }

}