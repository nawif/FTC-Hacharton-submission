package com.alsahirAndroid.merth;

import android.app.NotificationManager;
import android.com.merath.R;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;

public class MainActivity extends TabActivity implements TabHost.OnTabChangeListener {


    /**
     * Called when the activity is first created.
     */
    public static TabHost tabHost;
    View tab1;
    View tab2;
    View tab3;
    View tab4;

    ImageView image1;
    ImageView image2;
    ImageView image3;
    ImageView image4;

    public static TextView text1;
    public static TextView text2;
    public static TextView text3;
    public static TextView text4;

    public static Context context;
    public static Typeface face;

    public static TabHost getCurrentTabHost() {
        return tabHost;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = getApplicationContext();

        tabHost = getTabHost();

        // Set TabChangeListener called when tab changed
        tabHost.setOnTabChangedListener(this);

        TabHost.TabSpec spec;
        Intent intent;

        String fontPath = "fonts/noor.ttf";
        face = Typeface.createFromAsset(getApplicationContext().getAssets(), fontPath);


        /************* TAB4 ************/
        // Create  Intents to launch an Activity for the tab (to be reused)
        intent = new Intent().setClass(this, AboutActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        tab1 = LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        image1 = (ImageView) tab1.findViewById(R.id.icon);
        text1 = (TextView) tab1.findViewById(R.id.text);
        image1.setImageResource(R.drawable.info);
        text1.setText("حول التطبيق");
        text1.setTextAppearance(this, android.R.style.TextAppearance_Small);
        text1.setTypeface(face);
        text1.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.colorLight));
        spec = tabHost.newTabSpec("one").setIndicator(tab1).setContent(intent);
        tabHost.addTab(spec);

        /************* TAB3 ************/
        intent = new Intent().setClass(this, AlertActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        tab2 = LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        image2 = (ImageView) tab2.findViewById(R.id.icon);
        text2 = (TextView) tab2.findViewById(R.id.text);
        image2.setImageResource(R.drawable.ic_bell);
        image2.setColorFilter(Color.argb(255, 255, 255, 255));
        text2.setText("التنبيهات");
        text2.setTextAppearance(this, android.R.style.TextAppearance_Small);
        text2.setTypeface(face);
        text2.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.colorLight));
        spec = tabHost.newTabSpec("tow").setIndicator(tab2).setContent(intent);
        tabHost.addTab(spec);

        /************* TAB2 ************/
        intent = new Intent().setClass(this, FavoriteActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        tab3 = LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        image3 = (ImageView) tab3.findViewById(R.id.icon);
        text3 = (TextView) tab3.findViewById(R.id.text);
        image3.setImageResource(R.drawable.favorite);
        text3.setText("المفضلة");
        text3.setTextAppearance(this, android.R.style.TextAppearance_Small);
        text3.setTypeface(face);
        text3.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.colorLight));
        spec = tabHost.newTabSpec("three").setIndicator(tab3).setContent(intent);
        tabHost.addTab(spec);

        /************* TAB1 ************/
        intent = new Intent().setClass(this, MenuActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        tab4 = LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        image4 = (ImageView) tab4.findViewById(R.id.icon);
        text4 = (TextView) tab4.findViewById(R.id.text);
        image4.setImageResource(R.drawable.menu);
        text4.setText("الرئيسية");
        text4.setTextAppearance(this, android.R.style.TextAppearance_Small);
        text4.setTypeface(face);
        text4.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.colorLight));
        spec = tabHost.newTabSpec("four").setIndicator(tab4).setContent(intent);
        tabHost.addTab(spec);

        DSDatabase dsDatabase = new DSDatabase(getApplicationContext());
        try {
            dsDatabase.createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Set Tab1 as Default tab and change image
        tabHost.getTabWidget().setCurrentTab(0);
        // tabHost.getTabWidget().setStripEnabled(true);

        //  tabHost.getTabWidget().getChildAt(0).setBackgroundResource(R.mipmap.bookmark_normal);
        MainActivity.getCurrentTabHost().setCurrentTab(3);

    }

    @Override
    public void onTabChanged(String tabId) {

        /************ Called when tab changed *************/

        //********* Check current selected tab and change according images *******/

        for (int i = 0; i < tabHost.getTabWidget().getChildCount(); i++) {
            if (i == 0) {
                image1.setImageResource(R.drawable.info);
                text1.setText("حول التطبيق");
                text1.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.colorLight));
            } else if (i == 1) {
                image2.setImageResource(R.drawable.ic_bell);
                image2.setColorFilter(Color.argb(255, 255, 255, 255));
                text2.setText("التنبيهات");
                text2.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.colorLight));
            } else if (i == 2) {
                image3.setImageResource(R.drawable.favorite);
                text3.setText("المفضلة");
                text3.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.colorLight));
            } else if (i == 3) {
                image4.setImageResource(R.drawable.menu);
                text4.setText("الرئيسية");
                text4.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.colorLight));
            }
        }

        //highlight
        if (tabHost.getCurrentTab() == 0) {
            image1.setImageResource(R.drawable.info_h);
            text1.setText("حول التطبيق");
            text1.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.colorAccent));
        } else if (tabHost.getCurrentTab() == 1) {
            image2.setImageResource(R.drawable.ic_bell);
            image2.setColorFilter(ContextCompat.getColor(getApplicationContext(),R.color.colorAccent));
            text2.setText("التنبيهات");
            text2.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.colorAccent));

        } else if (tabHost.getCurrentTab() == 2) {
            image3.setImageResource(R.drawable.favorite_h);
            text3.setText("المفضلة");
            text3.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.colorAccent));

        } else if (tabHost.getCurrentTab() == 3) {
            image4.setImageResource(R.drawable.menu_h);
            text4.setText("الرئيسية");
            text4.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.colorAccent));

        }

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onPause() {
        super.onPause();

        ArrayList<Alert> alerts = getData();

        if (alerts.size() != 0)
        {
            for (int i =0; i < alerts.size(); i++)
            {
                Calendar cal = null;
                if(alerts.get(i).getActive()){
                    try {
                        cal = Notification.getCalendarFromString(alerts.get(i).getTime(),"HH:mm",alerts.get(i).getRepeat(),true);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Notification.createNotification(getApplicationContext(),Integer.parseInt(alerts.get(i).getId()),alerts.get(i).getTitle(),cal,alerts.get(i).getSound(),alerts.get(i).getRepeat());
                    System.out.println("alerts = " + cal.getTime());
                }else {
                    Notification.cancelNotification(getApplicationContext(),Integer.parseInt(alerts.get(i).getId()));
                }


            }

        }
    }

    public ArrayList<Alert> getData() {

        final DSDatabase dsDatabase = new DSDatabase(getApplicationContext());
        ArrayList<Alert> alerts = new ArrayList<>();

        try {
            String[] values = {
                    "id","title","time","repeat","sound","is_active"
            };

            Map<String,String> myMap = dsDatabase.getDatabase("alerts",values);

            for (int i = 0; i < myMap.size()/values.length; i++)
            {
                Alert alert = new Alert();
                alert.setId(myMap.get("id_"+i));
                alert.setTitle(myMap.get("title_"+i));
                alert.setTime(myMap.get("time_"+i));
                alert.setRepeat(myMap.get("repeat_"+i));
                alert.setSound(myMap.get("sound_"+i));
                alert.setActive(Boolean.valueOf(myMap.get("is_active_"+i)));
                alerts.add(alert);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return alerts;
    }

}


