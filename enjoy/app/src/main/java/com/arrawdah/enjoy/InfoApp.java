package com.arrawdah.enjoy;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;


public class InfoApp extends AppCompatActivity {

    TextView textIndex, textTitleInfo1, textTitleInfo2;
    TextView txt_info1, txt_info2, txt_info3, txt_info4, txt_info5, txt_info6;
    LinearLayout BtnInfo1, BtnInfo2, BtnInfo3, BtnInfo4, BtnInfo5, BtnInfo6;
    LinearLayout imgs_info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_app);

        textIndex = (TextView) findViewById(R.id.textIndex);
        textTitleInfo1 = (TextView) findViewById(R.id.textTitleInfo);
        textTitleInfo2 = (TextView) findViewById(R.id.textTitleInfo2);

        imgs_info = (LinearLayout) findViewById(R.id.imagesInfo);

        BtnInfo1 = (LinearLayout) findViewById(R.id.BtnInfo1);
        BtnInfo2 = (LinearLayout) findViewById(R.id.BtnInfo2);
        BtnInfo3 = (LinearLayout) findViewById(R.id.BtnInfo3);
        BtnInfo4 = (LinearLayout) findViewById(R.id.BtnInfo4);
        BtnInfo5 = (LinearLayout) findViewById(R.id.BtnInfo5);
        BtnInfo6 = (LinearLayout) findViewById(R.id.BtnInfo6);

        txt_info1 = (TextView) findViewById(R.id.textInfo1);
        txt_info2 = (TextView) findViewById(R.id.textInfo2);
        txt_info3 = (TextView) findViewById(R.id.textInfo3);
        txt_info4 = (TextView) findViewById(R.id.textInfo4);
        txt_info5 = (TextView) findViewById(R.id.textInfo5);
        txt_info6 = (TextView) findViewById(R.id.textInfo6);


        Typeface face = Typeface.createFromAsset(getAssets(), "fonts/SultanNahia_HelpMacNet_.ttf");
        txt_info1.setTypeface(face);
        txt_info2.setTypeface(face);
        txt_info3.setTypeface(face);
        txt_info4.setTypeface(face);
        txt_info5.setTypeface(face);
        txt_info6.setTypeface(face);

        textIndex.setTypeface(face);
        textTitleInfo1.setTypeface(face);
        textTitleInfo2.setTypeface(face);

        if (Build.VERSION.SDK_INT >= 21) {
            //color StatusBar
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(Color.parseColor("#5c9ace"));
        }


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
                onCallBtnClick();

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

        imgs_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent uriTwitter = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.jawalk.com"));
                startActivity(uriTwitter);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_info_app, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void onCallBtnClick(){
        if (Build.VERSION.SDK_INT < 23) {
            phoneCall();
        }else {

            if (ActivityCompat.checkSelfPermission(InfoApp.this,
                    Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {

                phoneCall();
            }else {
                final String[] PERMISSIONS_STORAGE = {Manifest.permission.CALL_PHONE};
                //Asking request Permissions
                ActivityCompat.requestPermissions(InfoApp.this, PERMISSIONS_STORAGE, 9);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        boolean permissionGranted = false;
        switch(requestCode){
            case 9:
                permissionGranted = grantResults[0]== PackageManager.PERMISSION_GRANTED;
                break;
        }
        if(permissionGranted){
            phoneCall();
        }else {
//            Toast.makeText(InfoApp.this, "You don't assign permission.", Toast.LENGTH_SHORT).show();
        }
    }

    private void phoneCall(){
        if (ActivityCompat.checkSelfPermission(InfoApp.this,
                Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:966558118112"));
            InfoApp.this.startActivity(callIntent);
        }else{
//            Toast.makeText(mActivity, "You don't assign permission.", Toast.LENGTH_SHORT).show();
        }
    }
}
