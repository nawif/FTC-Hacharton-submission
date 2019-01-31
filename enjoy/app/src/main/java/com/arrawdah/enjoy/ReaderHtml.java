package com.arrawdah.enjoy;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Toast;

import java.util.ArrayList;

public class ReaderHtml extends FragmentActivity {

    ViewPager pager;
    FragmentStatePagerAdapter adapter;

    private Toast toast;
    public static Button share,BtnFontPlus,BtnFontMinus,BtnFav,ThumbsBookMark;
    public static SeekBar seekBar;

    public static LinearLayout top,bottom;
    public static Animation animFadein,animFadeout;

    public static boolean animCheck = true;
    public static boolean animCheckSeek = false;

    int numberPassImg = 0;
    boolean numberPassCheck = false;
    int sizeTextHtml;

    int currentPage;
    SharedPreferences pref;
    SharedPreferences.Editor editor;

    private ArrayList<String> array_html = new ArrayList<String>();

    WebView web;
    private WebSettings webSettings;
    public static String text_share;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reader_html);
        pager=(ViewPager)findViewById(R.id.viewPagerHtml);


        share = (Button)findViewById(R.id.BtnShare);
        BtnFontPlus = (Button)findViewById(R.id.BtnFontPlus);
        BtnFontMinus = (Button)findViewById(R.id.BtnFontMinus);
        BtnFav = (Button)findViewById(R.id.BtnFav);
        ThumbsBookMark = (Button)findViewById(R.id.BtnThumbsBookMark);
        web = (WebView)findViewById(R.id.webReaderShare);

        seekBar = (SeekBar) findViewById(R.id.seekBar);
        seekBar.setRotation(180);
        seekBar.setMax(461);
        top = (LinearLayout)findViewById(R.id.linearLayout_ReaderTop);
        bottom = (LinearLayout)findViewById(R.id.linearLayout_ReaderBottom);

        checkTextSize();

        // load the animation
        animFadein = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.fade_in);
        animFadeout = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.fade_out);

        for (int i = 461 ; i >= 1; i--) {
            array_html.add("file:///android_res/raw/join_" + i + ".html");
        }


        adapter=new FragmentStatePagerAdapter(
                getSupportFragmentManager()
        ){
            public int getItemPosition(Object object) {
                return POSITION_NONE;
            }

            @Override
            public int getCount() {
                // This makes sure getItem doesn't use a position
                // that is out of bounds of our array of URLs
                return array_html.size();
            }

            @Override
            public Fragment getItem(int position) {
                // Here is where all the magic of the adapter happens
                // As you can see, this is really simple.
                web();
                return BrowserFragment.newInstance(array_html.get(position));
            }
        };

        //Let the pager know which adapter it is supposed to use
        pager.setAdapter(adapter);
        pager.setCurrentItem(array_html.size());
        currentPage = pager.getCurrentItem();

        Intent mIntent = getIntent();
        numberPassCheck = mIntent.getBooleanExtra("numberPassCheck", false);
        if (numberPassCheck == true) {
            numberPassImg = mIntent.getIntExtra("numberPassImg", 0);
            Log.i("numberPagePass: " + numberPassImg, "");
            pager.setCurrentItem(numberPassImg);
            int numberPage = 461 - numberPassImg;
            seekBar.setProgress(numberPage);
        }

        checkFav();

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
/*
                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                //  Uri screenshotUri = Uri.parse("file:///android_res/raw/page" + 10 + ".jpeg");
                //  Uri screenshotUri = Uri.parse("android.resource://com.arrawdah.roadtoquran/" + R.raw.page1);
                Uri screenshotUri = Uri.parse("android.resource://com.arrawdah.roadtoquran/" + "" + array_images.get(pager.getCurrentItem()));
                sharingIntent.setType("image/*");
                sharingIntent.putExtra(Intent.EXTRA_STREAM, screenshotUri);
                startActivity(Intent.createChooser(sharingIntent, "مشاركة عبر ..."));
*/

                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, "" + text_share + "\n"
                + "#تطبيق_استمتع_بحياتك"
                                + "\n"
                                + "\n"
                                + "من تطبيقات #جوال_الخير"
                                + "\n"
                                + "\n"
                                +
                                "متجر أبل" +
                                "\n" +
                                "https://itunes.apple.com/sa/app/ktab-astmt-bhyatk-lldktwr/id722287232?mt=8\n" +
                                "\n" +
                                "البلاي ستور \n" +
                                "https://play.google.com/store/apps/details?id=com.arrawdah.enjoy" + "\n"
                );
                startActivity(Intent.createChooser(intent, "مشاركة.."));
            }
        });

        ThumbsBookMark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent myIntent = new Intent(ReaderHtml.this, FavoritePage.class);
                finish();
                startActivity(myIntent);

            }
        });

        BtnFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BtnFav.setBackgroundResource(R.drawable.reader_mark_highlight);

                pref = getApplicationContext().getSharedPreferences(
                        "PagesFav", MODE_PRIVATE);
                String value1 = pref.getString("favPages" + pager.getCurrentItem(), null);

                if (value1 != null) {
                    editor = pref.edit();
                    editor.remove("favPages" + pager.getCurrentItem());
                    editor.commit();
                } else {
                    editor = pref.edit();
                    editor.putString("favPages" + pager.getCurrentItem(), "" + pager.getCurrentItem());
                    editor.commit();
                }

                checkFav();

            }
        });

        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {


            }

            @Override
            public void onPageSelected(int position) {
                try {
                    toast.cancel();
                } catch (Exception e) {

                }

                int numberPage = 461 - pager.getCurrentItem();
                toast = Toast.makeText(getApplicationContext(), "461" + " of " + "" + numberPage, Toast.LENGTH_SHORT);
                toast.show();
                seekBar.setProgress(numberPage);
                checkFav();
                currentPage = pager.getCurrentItem();

                if (animCheck == true) {
                    show();
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {


            }


        });


        seekBar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    int progress = 461;

                    @Override
                    public void onProgressChanged(SeekBar seekBar,
                                                  int progresValue, boolean fromUser) {
                        progress = progresValue;
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {
                        // Do something here,
                        //if you want to do anything at the start of
                        // touching the seekbar
                        animCheckSeek = true;

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        // Display the value in textview
                        //textView.setText(progress + "/" + seekBar.getMax());
                        int numberPage = 461 - progress;
                        pager.setCurrentItem(numberPage);
                        show();
                        animCheckSeek = false;
                        currentPage = pager.getCurrentItem();

                    }
                });

        BtnFontPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (sizeTextHtml == 200) {

                } else {
                    sizeTextHtml = sizeTextHtml + 10;
                    pref = getApplicationContext().getSharedPreferences(
                            "SizeTextHtml", MODE_PRIVATE);
                    editor = pref.edit();
                    editor.putInt("size_text", sizeTextHtml);
                    editor.commit();
                    Log.i("log", "showHTML: BtnFontPlus");
              //      adapter.notifyDataSetChanged();
              //      pager.setAdapter(adapter);
              //      pager.setCurrentItem(currentPage);
                    adapter.notifyDataSetChanged();


                }

            }
        });
        BtnFontMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (sizeTextHtml == 100) {

                } else {
                    sizeTextHtml = sizeTextHtml - 10;
                    pref = getApplicationContext().getSharedPreferences(
                            "SizeTextHtml",MODE_PRIVATE);
                    editor = pref.edit();
                    editor.putInt("size_text", sizeTextHtml);
                    editor.commit();
                    Log.i("log", "showHTML: BtnFontMinus");
                    //      adapter.notifyDataSetChanged();
                    //      pager.setAdapter(adapter);
                    //      pager.setCurrentItem(currentPage);
                    adapter.notifyDataSetChanged();

                }

            }
        });

    }


    public void show() {

        if (animCheckSeek == true) {

        } else {
            if (animCheck == true) {
                top.startAnimation(animFadeout);
                bottom.startAnimation(animFadeout);
                seekBar.setEnabled(false);
                share.setEnabled(false);
                animCheck = false;
            } else {
                top.startAnimation(animFadein);
                bottom.startAnimation(animFadein);
                seekBar.setEnabled(true);
                share.setEnabled(true);
                animCheck = true;
            }

        }
    }
    private void checkFav() {

        pref = getApplicationContext().getSharedPreferences(
                "PagesFav", MODE_PRIVATE);
        String value1 = pref.getString("favPages" + pager.getCurrentItem(), null);

        if (value1 != null) {
            BtnFav.setBackgroundResource(R.drawable.reader_mark_highlight);
        } else {
            BtnFav.setBackgroundResource(R.drawable.reader_mark);
        }


    }

    private void checkTextSize() {

        pref = getApplicationContext().getSharedPreferences(
                "SizeTextHtml", MODE_PRIVATE);
        int value1 = pref.getInt("size_text", 0);
        Log.i("sizeText", "sizeTextHtml: " + value1);

        if (value1 == 0) {
            sizeTextHtml = 100;

        } else  {
            sizeTextHtml = value1;

        }

    }


    private void web() {

        //Web

        webSettings = web.getSettings();
        webSettings.setJavaScriptEnabled(true);
        web.getSettings().setJavaScriptEnabled(true);
        web.addJavascriptInterface(new MyJavaScriptInterface(getApplicationContext()), "HtmlViewer");

        web.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                web.loadUrl("javascript:window.HtmlViewer.showHTML" +
                        "(''+document.body.textContent+'');");

            }
        });
        web.setAlpha(0);
        webSettings.setTextZoom(sizeTextHtml);
        int Page = pager.getCurrentItem();
        web.loadUrl(array_html.get(Page));

    }
    class MyJavaScriptInterface {

        private Context ctx;

        MyJavaScriptInterface(Context ctx) {
            this.ctx = ctx;
        }

        @JavascriptInterface
        public void showHTML(String html) {
            Log.i("log", "showHTML: " + html);
            text_share = html;

            ReaderHtml.text_share = html;
        }
    }


}