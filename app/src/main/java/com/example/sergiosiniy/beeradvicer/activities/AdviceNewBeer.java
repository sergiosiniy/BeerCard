package com.example.sergiosiniy.beeradvicer.activities;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.sergiosiniy.beeradvicer.R;

public class AdviceNewBeer extends Activity {



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

        Spinner beerType = (Spinner) findViewById(R.id.beer_type_spinner_sendto);
        EditText beerBrand  = (EditText)findViewById(R.id.brand_edit_text);
        shareBody = beerType.getSelectedItem().toString()+" - "+beerBrand.getText().toString();
        Uri adminMail = Uri.parse("mailto:sergio.siniy@gmail.com");

        Intent sendMsg = new Intent (Intent.ACTION_SENDTO)
        .setData(adminMail)
        .putExtra(Intent.EXTRA_SUBJECT, "Beer advice from user")
        .putExtra(Intent.EXTRA_TEXT, shareBody);
        startActivity(sendMsg);
    }

}
