package com.alsahirandroid.human_devil;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


public class Tab1 extends Activity {

    private TextView titleAbout,txtAboutTop,txtAboutMid;
    private Button btnFacebook,btnTwitter,btnCloud,btnYoutube,btnInfo,btnWebsite;
    LinearLayout BtnInfo1, BtnInfo2, BtnInfo3, BtnInfo4, BtnInfo5, BtnInfo6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab1);

        txtAboutTop = (TextView) findViewById(R.id.txtAboutTop);
        txtAboutMid = (TextView) findViewById(R.id.txtAboutMid);

        BtnInfo1 = (LinearLayout) findViewById(R.id.BtnInfo1);
        BtnInfo2 = (LinearLayout) findViewById(R.id.BtnInfo2);
        BtnInfo3 = (LinearLayout) findViewById(R.id.BtnInfo3);
        BtnInfo4 = (LinearLayout) findViewById(R.id.BtnInfo4);
        BtnInfo5 = (LinearLayout) findViewById(R.id.BtnInfo5);
        BtnInfo6 = (LinearLayout) findViewById(R.id.BtnInfo6);
        btnWebsite = (Button) findViewById(R.id.btnWebsite);


        String fontPath = "fonts/noor.ttf";
//        Typeface face = Typeface.createFromAsset(getApplicationContext().getAssets(), fontPath);
//        titleAbout.setTypeface(face);
//        txtAboutTop.setTypeface(face);
//        txtAboutMid.setTypeface(face);

        BtnInfo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent uriTwitter = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/arrawdah"));
                startActivity(uriTwitter);

            }
        });

        BtnInfo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent uriTwitter = new Intent(Intent.ACTION_VIEW, Uri.parse("https://wa.me/966558118112"));
                startActivity(uriTwitter);
            }
        });

        BtnInfo3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent uriTwitter = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/arrawda?fref=ts"));
                startActivity(uriTwitter);

            }
        });
        BtnInfo4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent uriSoundcloud = new Intent(Intent.ACTION_VIEW, Uri.parse("https://soundcloud.com/jawalk1"));
                startActivity(uriSoundcloud);

            }
        });
        BtnInfo5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent uriTwitter = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/user/arrawda"));
                startActivity(uriTwitter);

            }
        });
        BtnInfo6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent uri = new Intent(Intent.ACTION_VIEW, Uri.parse("http://jawalk.com/%D8%B9%D9%86-%D8%AC%D9%88%D8%A7%D9%84-%D8%A7%D9%84%D8%AE%D9%8A%D8%B1/"));
                startActivity(uri);

            }
        });


        btnWebsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent uri = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.jawalk.com"));
                startActivity(uri);

            }
        });

    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.tab1, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        return super.onOptionsItemSelected(item);
//    }
}
