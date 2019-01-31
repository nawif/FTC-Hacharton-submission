package com.alsahirAndroid.merth;

import android.com.merath.R;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {

    private TextView titleAbout,txtAboutTop,txtAboutMid;
    private Button btnWebsite;
    LinearLayout BtnInfo1, BtnInfo2, BtnInfo3, BtnInfo4, BtnInfo5, BtnInfo6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        titleAbout = findViewById(R.id.titleAbout);
        txtAboutTop = findViewById(R.id.txtAboutTop);
        txtAboutMid = findViewById(R.id.txtAboutMid);

        BtnInfo1 = findViewById(R.id.BtnInfo1);
        BtnInfo2 = findViewById(R.id.BtnInfo2);
        BtnInfo3 = findViewById(R.id.BtnInfo3);
        BtnInfo4 = findViewById(R.id.BtnInfo4);
        BtnInfo5 = findViewById(R.id.BtnInfo5);
        BtnInfo6 = findViewById(R.id.BtnInfo6);
        btnWebsite = findViewById(R.id.btnWebsite);


        String fontPath = "fonts/noor.ttf";
        Typeface face = Typeface.createFromAsset(getApplicationContext().getAssets(), fontPath);
        titleAbout.setTypeface(face);
        txtAboutTop.setTypeface(face);
        txtAboutMid.setTypeface(face);

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
}
