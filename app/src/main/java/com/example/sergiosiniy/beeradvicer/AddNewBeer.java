package com.example.sergiosiniy.beeradvicer;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

public class AddNewBeer extends AppCompatActivity {

    private Spinner beerType;
    private EditText beerBrand;
    private String shareBody;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_beer);
    }


    /**
     * Sending new beer to my mail
     * @param view
     */
    public void sendMessage(View view){

        beerType = (Spinner) findViewById(R.id.beer_type_spinner_sendto);
        beerBrand  = (EditText)findViewById(R.id.brand_edit_text);
        shareBody = beerType.getSelectedItem().toString()+" - "+beerBrand.getText().toString();
        Uri adminMail = Uri.parse("mailto:sergio.siniy@gmail.com");

        Intent sendMsg = new Intent (Intent.ACTION_SENDTO)
        .setData(adminMail)
        .putExtra(Intent.EXTRA_SUBJECT, "Beer advice from user")
        .putExtra(Intent.EXTRA_TEXT, shareBody);
        startActivity(sendMsg);

    }

    /**
     * This snippet wil be used in future for sharing beer with friends
     */
    /*public void shareBeer(View view){

        beerType = (EditText)findViewById(R.id.beer_type_edit_text);
        beerBrand  = (EditText)findViewById(R.id.brand_edit_text);
        shareBody = beerType.getText().toString()+" - "+beerBrand.getText().toString();

        ShareCompat.IntentBuilder
                .from(this) // getActivity() or activity field if within Fragment
                .setText(shareBody)
                .setType("text/plain") // most general text sharing MIME type
                .setChooserTitle(getResources().getString(R.string.share_beer_button))
                .startChooser();
    }*/



}
