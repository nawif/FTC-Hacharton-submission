package com.arrawdah.enjoy;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class BrowserFragment extends Fragment {

    public static WebView myBrowser;
    public static WebSettings webSettings;
    String url;
    public static String text;
    int sizeTextHtml;
    SharedPreferences pref;
    SharedPreferences.Editor editor;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view=inflater.inflate(
                R.layout.custom_reader_html,
                container,
                false);
        myBrowser=(WebView)view.findViewById(R.id.web_swipe);
        webSettings = myBrowser.getSettings();
        webSettings.setJavaScriptEnabled(true);
        myBrowser.getSettings().setJavaScriptEnabled(true);
        myBrowser.addJavascriptInterface(new MyJavaScriptInterface(getActivity()), "HtmlViewer");

        myBrowser.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                myBrowser.loadUrl("javascript:window.HtmlViewer.showHTML" +
                        "(''+document.body.textContent+'');");

            }
        });
        myBrowser.setBackgroundColor(Color.WHITE);
        checkTextSize();
        webSettings.setTextZoom(sizeTextHtml);
        myBrowser.loadUrl(url);



        final GestureDetector gesture = new GestureDetector(getActivity(),
                new GestureDetector.SimpleOnGestureListener() {

                    @Override
                    public boolean onSingleTapConfirmed(MotionEvent e) {
                        ReaderHtml readerHtml = new ReaderHtml();
                        readerHtml.animCheckSeek = false;
                        readerHtml.show();

                        return true;
                    }

                });

        myBrowser.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return gesture.onTouchEvent(event);
            }
        });

/*
        ReaderHtml readerHtml = new ReaderHtml();
        readerHtml.BtnFontPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (sizeTextHtml == 200) {

                } else {
                    sizeTextHtml = sizeTextHtml + 10;
                    pref = getActivity().getSharedPreferences(
                            "SizeTextHtml", getActivity().MODE_PRIVATE);
                    editor = pref.edit();
                    editor.putInt("size_text", sizeTextHtml);
                    editor.commit();
                    Log.i("log", "showHTML: BtnFontPlus");
                    myBrowser.loadUrl("javascript:window.location.reload( true )");

                }

            }
        });
        readerHtml.BtnFontMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (sizeTextHtml == 100) {

                } else {
                    sizeTextHtml = sizeTextHtml - 10;
                    pref = getActivity().getSharedPreferences(
                            "SizeTextHtml", getActivity().MODE_PRIVATE);
                    editor = pref.edit();
                    editor.putInt("size_text", sizeTextHtml);
                    editor.commit();
                    Log.i("log", "showHTML: BtnFontMinus");
                    myBrowser.loadUrl("javascript:window.location.reload( true )");

                }

            }
        });
*/

        return view;
    }

    // This is the method the pager adapter will use
    // to create a new fragment
    public static Fragment newInstance(String url){
        BrowserFragment f=new BrowserFragment();
        f.url=url;
        return f;
    }

    class MyJavaScriptInterface {

        private Context ctx;

        MyJavaScriptInterface(Context ctx) {
            this.ctx = ctx;
        }

        @JavascriptInterface
        public void showHTML(String html) {
            //        Log.i("log","showHTML: "+html);
            text = html;
        }


    }
    private void checkTextSize() {

        pref = getActivity().getSharedPreferences(
                "SizeTextHtml", getActivity().MODE_PRIVATE);
        int value1 = pref.getInt("size_text", 0);
        Log.i("sizeText", "sizeTextHtml: " + value1);

        if (value1 == 0) {
            sizeTextHtml = 100;

        } else  {
            sizeTextHtml = value1;

        }

    }

}