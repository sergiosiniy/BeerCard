package com.example.sergiosiniy.beeradvicer.activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.sergiosiniy.beeradvicer.R;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

public class StartScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);
        noNetworkConnectionDialog(isNetworkConnected());
        new ServerAvailabilityChecker().execute();
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
                Intent sendBeer = new Intent(this, AdviceNewBeer.class);
                startActivity(sendBeer);
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

    private static boolean isServerReachable(){

        try {
            SocketAddress socketAddress = new InetSocketAddress("31.134.121.230",55556);
            // Create an unbound socket
            Socket sock = new Socket();
            // This method will block no more than timeoutMs.
            // If the timeout occurs, SocketTimeoutException is thrown.
            sock.connect(socketAddress, 5000);
            sock.close();
            return true;
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
}
