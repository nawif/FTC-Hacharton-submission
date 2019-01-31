package com.alsahirandroid.human_devil;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;


public class MyActivity extends TabActivity implements TabHost.OnTabChangeListener {

    /** Called when the activity is first created. */
    private static TabHost tabHost;
    View tab1;
    View tab2;
    View tab3;
    View tab4;
    ImageView image1;
    ImageView image2;
    ImageView image3;
    ImageView image4;
    TextView text1;
    TextView text2;
    TextView text3;
    TextView text4;
    public static TabHost getCurrentTabHost(){
        return tabHost;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        tabHost = getTabHost();

        // Set TabChangeListener called when tab changed
        tabHost.setOnTabChangedListener(this);

        TabHost.TabSpec spec;
        Intent intent;

        /************* TAB1 ************/
        // Create  Intents to launch an Activity for the tab (to be reused)
        intent = new Intent().setClass(this, Tab1.class);
        tab1 = LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        image1 = (ImageView) tab1.findViewById(R.id.icon);
        text1 = (TextView) tab1.findViewById(R.id.text);
        image1.setImageResource(R.drawable.info_normal);
        text1.setText("حول التطبيق");
        text1.setTextColor(Color.parseColor("#cccccc"));
        spec = tabHost.newTabSpec("First").setIndicator(tab1).setContent(intent);
        tabHost.addTab(spec);
        /************* TAB2 ************/
        intent = new Intent().setClass(this, Tab2.class);
        tab2 = LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        image2 = (ImageView) tab2.findViewById(R.id.icon);
        text2 = (TextView) tab2.findViewById(R.id.text);
        image2.setImageResource(R.drawable.christmas_star_normal);
        text2.setText("الصوتيات");
        text2.setTextColor(Color.parseColor("#cccccc"));
        spec = tabHost.newTabSpec("Second").setIndicator(tab2).setContent(intent);
        tabHost.addTab(spec);

        /************* TAB3 ************/
        intent = new Intent().setClass(this, Tab3.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        tab3 = LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        image3 = (ImageView) tab3.findViewById(R.id.icon);
        text3 = (TextView) tab3.findViewById(R.id.text);
        image3.setImageResource(R.drawable.audio_wave_normal);
        text3.setText("المفضلة");
        text3.setTextColor(Color.parseColor("#cccccc"));
        spec = tabHost.newTabSpec("Third").setIndicator(tab3).setContent(intent);
        tabHost.addTab(spec);

        /************* TAB4 ************/
        intent = new Intent().setClass(this, Tab4.class);
        tab4 = LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        image4 = (ImageView) tab4.findViewById(R.id.icon);
        text4 = (TextView) tab4.findViewById(R.id.text);
        image4.setImageResource(R.drawable.bookmark_normal);
        text4.setText("الرئيسية");
        text4.setTextColor(Color.parseColor("#cccccc"));
        spec = tabHost.newTabSpec("Fourth").setIndicator(tab4).setContent(intent);
        tabHost.addTab(spec);

        // Set drawable images to tab

        // Set Tab1 as Default tab and change image
        tabHost.getTabWidget().setCurrentTab(1);
       // tabHost.getTabWidget().setStripEnabled(true);

        //  tabHost.getTabWidget().getChildAt(0).setBackgroundResource(R.drawable.bookmark_normal);
        MyActivity.getCurrentTabHost().setCurrentTab(3);

    }

    @Override
    public void onTabChanged(String tabId) {

        /************ Called when tab changed *************/

        //********* Check current selected tab and change according images *******/

        for(int i=0;i<tabHost.getTabWidget().getChildCount();i++)
        {
            if(i==0) {
                image1.setImageResource(R.drawable.info_normal);
                text1.setText("حول التطبيق");
                text1.setTextColor(Color.parseColor("#cccccc"));
            }
            else if(i==1) {
                image2.setImageResource(R.drawable.audio_wave_normal);
                text2.setText("الصوتيات");
                text2.setTextColor(Color.parseColor("#cccccc"));
            }
            else if(i==2) {
                image3.setImageResource(R.drawable.christmas_star_normal);
                text3.setText("المفضلة");
                text3.setTextColor(Color.parseColor("#cccccc"));
            }
            else if(i==3) {
                image4.setImageResource(R.drawable.bookmark_normal);
                text4.setText("الرئيسية");
                text4.setTextColor(Color.parseColor("#cccccc"));
            }
        }


        Log.i("tabs", "CurrentTab: "+tabHost.getCurrentTab());

        if(tabHost.getCurrentTab()==0) {
            image1.setImageResource(R.drawable.info_high);
            text1.setText("حول التطبيق");
            text1.setTextColor(Color.parseColor("#506700"));
        }
        else if(tabHost.getCurrentTab()==1) {
            image2.setImageResource(R.drawable.audio_wave_high);
            text2.setText("الصوتيات");
            text2.setTextColor(Color.parseColor("#506700"));

        }
        else if(tabHost.getCurrentTab()==2) {
            image3.setImageResource(R.drawable.christmas_star_high);
            text3.setText("المفضلة");
            text3.setTextColor(Color.parseColor("#506700"));

        }

        else if(tabHost.getCurrentTab()==3) {
            image4.setImageResource(R.drawable.bookmark_high);
            text4.setText("الرئيسية");
            text4.setTextColor(Color.parseColor("#506700"));

        }

    }

}


