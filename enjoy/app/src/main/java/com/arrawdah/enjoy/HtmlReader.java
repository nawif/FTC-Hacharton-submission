package com.arrawdah.enjoy;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;


public class HtmlReader extends AppCompatActivity {

    WebView myBrowser;
    WebSettings webSettings;

    SharedPreferences pref;
    SharedPreferences.Editor editor;

    String text;

    int numberPages;
    TextView TitleHtml;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_html_reader);


        myBrowser = (WebView)findViewById(R.id.web);
        TitleHtml = (TextView)findViewById(R.id.titleHtml);

        Typeface face = Typeface.createFromAsset(getAssets(), "fonts/SultanNahia_HelpMacNet_.ttf");

        TitleHtml.setTypeface(face);

        if (Build.VERSION.SDK_INT >=21) {
            //color StatusBar
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(Color.parseColor("#5c9ace"));
        }

        webSettings = myBrowser.getSettings();
        webSettings.setJavaScriptEnabled(true);
        myBrowser.getSettings().setJavaScriptEnabled(true);
        myBrowser.addJavascriptInterface(new MyJavaScriptInterface(this), "HtmlViewer");

        myBrowser.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                myBrowser.loadUrl("javascript:window.HtmlViewer.showHTML" +
                        "(''+document.body.textContent+'');");

            }
        });

        myBrowser.setBackgroundColor(Color.TRANSPARENT);

        Intent mIntent = getIntent();
        numberPages = mIntent.getIntExtra("numberPages", 0);

        TitleHtml.setText("" + mIntent.getStringExtra("TitlePass"));
        if (numberPages == 1) {
            myBrowser.loadUrl("file:///android_res/raw/begin_join.html");
        } else {
            myBrowser.loadUrl("file:///android_res/raw/office.html");
        }

    }
    class MyJavaScriptInterface {

        private Context ctx;

        MyJavaScriptInterface(Context ctx) {
            this.ctx = ctx;
        }

        @JavascriptInterface
        public void showHTML(String html) {
                    Log.i("log", "showHTML: " + html);
            text = html;
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_html_reader, menu);
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
}
