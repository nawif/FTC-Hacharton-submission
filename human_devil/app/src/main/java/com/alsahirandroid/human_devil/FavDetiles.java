package com.alsahirandroid.human_devil;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;


public class FavDetiles extends Activity {

    WebView myBrowser;
    WebSettings webSettings;
    Button button1,button2,button3,button4;
    TextView text1,text2,text3,text4;
    int numberPages,sizeTextHtml;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav_detiles);
        Context context = getApplicationContext();
        Intent mIntent = getIntent();

        text = "ok";
        myBrowser = (WebView)findViewById(R.id.webView1);
        button1 = (Button)findViewById(R.id.buttonfav1);
        button2 = (Button)findViewById(R.id.buttonfav2);
        button3 = (Button)findViewById(R.id.buttonfav3);
        button4 = (Button)findViewById(R.id.buttonfav4);
        text1 = (TextView)findViewById(R.id.textViewFav3);
        text2 = (TextView)findViewById(R.id.textViewFav4);
        text3 = (TextView)findViewById(R.id.textViewFav5);
        text4 = (TextView)findViewById(R.id.textViewFav6);
        checkTextSize();
        checkFav();

        numberPages = mIntent.getIntExtra("numberFav", 0);

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
        myBrowser.loadUrl("file:///android_res/raw/" + "human" + numberPages + ".html");
        webSettings.setTextZoom(sizeTextHtml);

        if (numberPages == 600) {
            myBrowser.loadUrl("file:///android_res/raw/" + "info" + ".html");
            button2.setVisibility(View.GONE);
            text2.setVisibility(View.GONE);
        }




        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sizeTextHtml == 200) {

                } else  {
                    sizeTextHtml = sizeTextHtml + 10;
                    webSettings.setTextZoom(sizeTextHtml);
                    pref = getApplicationContext().getSharedPreferences(
                            "SizeTextHtml", MODE_PRIVATE);
                    editor = pref.edit();
                    editor.putInt("size_text", sizeTextHtml);
                    editor.commit();
                }
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (button2.getText() == "★") {
                    pref = getApplicationContext().getSharedPreferences(
                            "HtmlFav", MODE_PRIVATE);
                    editor = pref.edit();
                    editor.remove("fav" + numberPages);
                    editor.commit();
                    button2.setText("☆");

                } else {

                }

            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(android.content.Intent.EXTRA_TEXT, "" + text);
                startActivity(Intent.createChooser(intent, "مشاركة.."));

            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sizeTextHtml == 100) {

                } else  {
                    sizeTextHtml = sizeTextHtml - 10;
                    webSettings.setTextZoom(sizeTextHtml);
                    pref = getApplicationContext().getSharedPreferences(
                            "SizeTextHtml", MODE_PRIVATE);
                    editor = pref.edit();
                    editor.putInt("size_text", sizeTextHtml);
                    editor.commit();
                    Log.i("sizeText", "sizeTextHtml: " + sizeTextHtml);

                }

            }
        });


    }

    private String readTextFromResource(int resourceID)
    {
        InputStream raw = getResources().openRawResource(resourceID);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        int i;
        try
        {
            i = raw.read();
            while (i != -1)
            {
                stream.write(i);
                i = raw.read();
            }
            raw.close();

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        text = raw.toString();

        return stream.toString();
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.tab4, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return super.onOptionsItemSelected(item);
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
    private void checkFav() {

        pref = getApplicationContext().getSharedPreferences(
                "HtmlFav", MODE_PRIVATE);
        int value1 = pref.getInt("fav"+numberPages, 0);

        Log.i("numberPages", "numberPages: " + numberPages);
        Log.i("value1", "value1: " + value1);

        if (value1 == numberPages) {
            button2.setText("★");
        } else {
            button2.setText("☆");
        }


    }
}
