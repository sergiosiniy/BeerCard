package com.example.sergiosiniy.beeradvicer.activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.sergiosiniy.beeradvicer.R;

import java.net.InetAddress;

public class StartScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);
        noNetworkConnectionDialog(isNetworkConnected());
        noServerConnectionDialog(isServerReachable());
    }

    /**
     * Opens activity for beer searching
     */
    public void findBeer(View view){
        Intent findBeer = new Intent(this, FindBeerActivity.class);
        startActivity(findBeer);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.send_beer:
                Intent addBeer = new Intent(this, AdviceNewBeer.class);
                startActivity(addBeer);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private boolean isNetworkConnected(){
        final ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo()!=null;
    }

    private boolean isServerReachable(){
        try{
            InetAddress address = InetAddress.getByName("31.134.121.230");
            return !address.equals("");
        }catch(Exception e){
            return false;
        }
    }

    private void noNetworkConnectionDialog(Boolean isNetworkConnected){
        if(!isNetworkConnected){
            AlertDialog.Builder builder = new AlertDialog.Builder(StartScreen.this);
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
                            noNetworkConnectionDialog(isNetworkConnected());
                        }

                    });
            AlertDialog alert = builder.create();
            alert.show();
        }
    }

    private void noServerConnectionDialog(Boolean isNetworkConnected){
        if(!isNetworkConnected){
            AlertDialog.Builder builder = new AlertDialog.Builder(StartScreen.this);
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
                            noNetworkConnectionDialog(isNetworkConnected());
                        }

                    });
            AlertDialog alert = builder.create();
            alert.show();
        }
    }




}
