package com.alsahirandroid.human_devil;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.text.Html;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


public class Tab4 extends Activity {

    WebView myBrowser;
    ListView list_Menu;
    WebSettings webSettings;
    Button button1,button2,button3,button4,button5,button6,button7,button8;
    int numberPages,sizeTextHtml;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    String text;
    View alwaysAppearingView;
    FrameLayout fram;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab4);
        Context context = getApplicationContext();
        Intent mIntent = getIntent();
        text = "ok";
        myBrowser = (WebView)findViewById(R.id.webView1);
        fram = (FrameLayout) findViewById(R.id.FrameLayout1);
        list_Menu = (ListView)findViewById(R.id.listMenu);
        list_Menu.setBackgroundColor(Color.WHITE);

        list_Menu.setVisibility(View.GONE);

        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);
        button3 = (Button)findViewById(R.id.button3);
        button4 = (Button)findViewById(R.id.button4);
        button5 = (Button)findViewById(R.id.button5);
        button6 = (Button)findViewById(R.id.button6);
        button7 = (Button)findViewById(R.id.button7);
        button8 = (Button)findViewById(R.id.button8);
        checkNumber();
        checkTextSize();
        checkFav();

        //list Menu

        String[] values = new String[]{

                "مقدمة",
                "المدخل",
                "حقيقة المس واقتران الشيطان بالإنسان",
                "أنواع المـس والاقتران",
                "المس الطائف",
                "المس العارض",
                "الاقتران الدائم ( التلبس الحقيقي )",
                "المس الخارجي",
                "المس المتعدي",
                "المس الوهمي",
                "المس الكاذب ( التمثيلي )",
                "الجنون ، اللمم ، المس ، الطيف",
                "أنواع الصرع ( صرع الإغماء )",
                "الفرق بين الصرعين العضوي والروحي",
                "تحكم الشيطان في جوارح الإنسان",
                "حضور وإنصراف الجان على بدن",
                "الإنسان أنواعه وأشكاله",
                "حضور الوسوسة",
                "حضور على عقل المريض",
                "حضور على عقل المريض من غير تلبس",
                "حضور على بدن المريض",
                "حضور مزدوج",
                "حضور كلى",
                "حضور مشترك",
                "كيف معرفة حضور الجان ؟",
                "وقت الحضور هل يشعر المريض بما يحصل له ؟",
                "هل حضور الجان يتعب الإنسان ؟",
                "هل يتعب الجان عندما يحضر على جسد الإنسان ؟",
                "هل تحصل حركة قبل الحضور ؟",
                "هل تحصل حركة ملحوظة قبل الإنصراف ؟",
                "هل يشعر المريض بتعب في جسده بعد الإنصراف ؟",
                "ما هو الوضع الذي يجب أن يكون عليه المريض حال إنصراف الجان؟",
                "كيف يُصرف الجان إذا حضر ؟",
                "هل يمكن منع الجان من الإنصراف؟",
                "باب أسباب اقتران الشيطان بالإنسان",
                "كيف يدخل الجني جسد الإنسي ؟",
                "باب عشق الجن للإنس",
                "علاج وطرد عاشق الجن",
                "حقيقة التناكح بين الإنس والجن",
                "الـــزار",
                "باب أعراض اقتران الشيطان بالإنسان",
                "أعراض اقتران الشيطان بالإنسان في المنام",
                "أعراض اقتران الشيطان بالإنسان في اليقظة",
                "بـاب العقــم",
                "العقم عند الرجال",
                "العقم عند النساء",
                "بـاب الاستحاضة",
                "باب السـرطان",
                "باب اعتداء الجن على مساكن الإنس",
                "مؤشرات وجود الشياطين في مساكن الإنس",
                "أسباب اعتداء الجن على مساكن الإنس",
                "طرد شياطين الجن من مساكن الإنس",
                "باب الســحر",
                "أطــراف السحــــر",
                "تأثير السحر في المسحـور",
                "بعض أنواع السحر وأعراضهـا",
                "علاج السحر الخارجي والسحر الداخلي",
                "إتلاف وابطال مادة السحر",
                "رقية المسحــور",
                "العين والحســد",
                "أعين الإنس وأعين الجن",
                "العائن والمعيان و المعين والمعيون",
                "الحسـد",
                "العـين والحسـد",
                "أقســـام العين والحسـد",
                "أعراض العيـن",
                "شيطـان العيـن",
                "بعض الأحاديث الواردة في العين",
                "تساؤلات في العين والحسـد",
                "الوقاية من العين والحسد",
                "علاج المعيون والمحسود",
                "رقية للعيــن والحسـد",
                "إعـداد المعـالـج",
                "باب الرقيــة",
                "التعامل مع المريض",
                "كيفية الرقيــة",
                "وضع اليد على مكان الألـم والمسـح بـها والنفث والتفل",
                "النفث والتفل",
                "الضرب والصعق بالكهرباء",
                "الضغط على الأوداج",
                "الحجامة",
                "الأعشــــاب",
                "مـاء زمزم",
                "زيت الزيتون",
                "عسل النحل",
                "تمر العجوة",
                "ألبان البقر",
                "الحبة السوداء",
                "الســدر",
                "الســنا",
                "القسط الهندي",
                "حبوب اللقاح ( الطلع )",
                "الحلتيت ( صمغ الانجدان ، الكبير)",
                "السـذاب ( الفيجن )",
                "الصـبر",
                "الكندر",
                "دم الأخوين",
                "    جوز القيء )حبة المنفل(",
                "ورق الغـار",
                "العنــبر",
                "المســك",
                "الـمحـــــو",
                "الـجمع بين الأدوية والرقية",
                "بـخــور",
                "الإغتسال والإدهان",
                "سحب الحسد والسحر من العروق",
                "الاغتســال",
                "العلاج عند أطباء النفـس بين القبول والرفض",
                "أصنـاف الجن",
                "تعذيـــب الجــــان وحرقــه",
                "القراءة الجماعية إيجابيـــات وسلبيــات",
                "الحكمة من استخدام مكبرات الصوت في الرقية",
                "الرقية العامة الشاملة دعـــاء وتحصينات",
                "منهج اخيار الآيـات",
                "تذكرة الإخوان ببعض آيات القرآن",
                "وصــايا وإرشـادات للمريض",
                "الاستعداد النفسي للتخبط وقت الرقية",
                "استمراء البـلاء",
                "طرق الشيطان في صرف المرضى عن العلاج بالقرآن",
        };
        final ArrayList<Integer> indexNumber = new ArrayList<Integer>();

        indexNumber.add(2);
        indexNumber.add(4);
        indexNumber.add(7);
        indexNumber.add(23);
        indexNumber.add(23);
        indexNumber.add(24);
        indexNumber.add(24);
        indexNumber.add(25);
        indexNumber.add(26);
        indexNumber.add(28);
        indexNumber.add(32);
        indexNumber.add(35);
        indexNumber.add(37);
        indexNumber.add(39);
        indexNumber.add(42);
        indexNumber.add(46);
        indexNumber.add(47);
        indexNumber.add(47);
        indexNumber.add(48);
        indexNumber.add(48);
        indexNumber.add(48);
        indexNumber.add(49);
        indexNumber.add(49);
        indexNumber.add(50);
        indexNumber.add(51);
        indexNumber.add(52);
        indexNumber.add(53);
        indexNumber.add(53);
        indexNumber.add(54);
        indexNumber.add(54);
        indexNumber.add(55);
        indexNumber.add(56);
        indexNumber.add(57);
        indexNumber.add(59);
        indexNumber.add(60);
        indexNumber.add(62);
        indexNumber.add(63);
        indexNumber.add(70);
        indexNumber.add(72);
        indexNumber.add(73);
        indexNumber.add(80);
        indexNumber.add(81);
        indexNumber.add(85);
        indexNumber.add(92);
        indexNumber.add(93);
        indexNumber.add(94);
        indexNumber.add(96);
        indexNumber.add(100);
        indexNumber.add(109);
        indexNumber.add(111);
        indexNumber.add(113);
        indexNumber.add(114);
        indexNumber.add(119);
        indexNumber.add(124);
        indexNumber.add(133);
        indexNumber.add(145);
        indexNumber.add(163);
        indexNumber.add(174);
        indexNumber.add(177);
        indexNumber.add(183);
        indexNumber.add(186);
        indexNumber.add(187);
        indexNumber.add(190);
        indexNumber.add(191);
        indexNumber.add(192);
        indexNumber.add(199);
        indexNumber.add(205);
        indexNumber.add(207);
        indexNumber.add(219);
        indexNumber.add(231);
        indexNumber.add(235);
        indexNumber.add(240);
        indexNumber.add(251);
        indexNumber.add(266);
        indexNumber.add(272);
        indexNumber.add(278);
        indexNumber.add(293);
        indexNumber.add(300);
        indexNumber.add(303);
        indexNumber.add(306);
        indexNumber.add(310);
        indexNumber.add(312);
        indexNumber.add(314);
        indexNumber.add(315);
        indexNumber.add(316);
        indexNumber.add(317);
        indexNumber.add(318);
        indexNumber.add(319);
        indexNumber.add(320);
        indexNumber.add(321);
        indexNumber.add(323);
        indexNumber.add(324);
        indexNumber.add(325);
        indexNumber.add(326);
        indexNumber.add(327);
        indexNumber.add(328);
        indexNumber.add(329);
        indexNumber.add(330);
        indexNumber.add(331);
        indexNumber.add(332);
        indexNumber.add(333);
        indexNumber.add(335);
        indexNumber.add(336);
        indexNumber.add(337);
        indexNumber.add(338);
        indexNumber.add(339);
        indexNumber.add(340);
        indexNumber.add(342);
        indexNumber.add(346);
        indexNumber.add(354);
        indexNumber.add(361);
        indexNumber.add(362);
        indexNumber.add(366);
        indexNumber.add(379);
        indexNumber.add(390);
        indexNumber.add(427);
        indexNumber.add(442);
        indexNumber.add(445);
        indexNumber.add(448);



        Log.i("indexNumber", "indexNumber: " + indexNumber.size());

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(
                this,android.R.layout.simple_list_item_1, values){

            @Override
            public View getView(int position, View convertView,
                                ViewGroup parent) {
                View view =super.getView(position, convertView, parent);

                TextView textView=(TextView) view.findViewById(android.R.id.text1);

            /*YOUR CHOICE OF COLOR*/
                textView.setTextColor(Color.parseColor("#506700"));

                return view;
            }
        };
        // Assign adapter to ListView
        list_Menu.setAdapter(adapter);

        // ListView Item Click Listener
        list_Menu.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // ListView Clicked item index
                int itemPosition     = position;

                // ListView Clicked item value
                String  itemValue    = (String) list_Menu.getItemAtPosition(position);
/*
                // Show Alert
                Toast.makeText(getApplicationContext(),
                        "Position :" + itemPosition + "  ListItem : " + itemValue, Toast.LENGTH_LONG)
                        .show();
                        */

                numberPages = indexNumber.get(itemPosition);

                pref = getApplicationContext().getSharedPreferences(
                        "NumberPages", MODE_PRIVATE);
                editor = pref.edit();
                editor.putInt("pages", numberPages);
                editor.commit();
                myBrowser.loadUrl("file:///android_res/raw/" + "human" + numberPages + ".html");
                list_Menu.setVisibility(View.GONE);
                Log.i("indexNumber", "indexNumber: " + indexNumber.get(itemPosition));

            }

        });

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


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list_Menu.setVisibility(View.VISIBLE);

            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (numberPages == 456) {

                } else {
                    numberPages = numberPages + 1;

                    pref = getApplicationContext().getSharedPreferences(
                            "NumberPages", MODE_PRIVATE);
                    editor = pref.edit();
                    editor.putInt("pages", numberPages);
                    editor.commit();
                    myBrowser.loadUrl("file:///android_res/raw/" + "human" + numberPages + ".html");

                }
                checkFav();

            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                webSettings.setTextSize(WebSettings.TextSize.SMALLEST);
                webSettings.setTextSize(WebSettings.TextSize.SMALLER);
                webSettings.setTextSize(WebSettings.TextSize.NORMAL);
                webSettings.setTextSize(WebSettings.TextSize.LARGER);
                webSettings.setTextSize(WebSettings.TextSize.LARGEST);
                myBrowser.loadUrl("javascript:(document.body.style.fontSize ='20pt');");
                */
                if (sizeTextHtml == 200) {

                } else  {
                    sizeTextHtml = sizeTextHtml + 10;
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
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (button4.getText() == "★") {
                    pref = getApplicationContext().getSharedPreferences(
                            "HtmlFav", MODE_PRIVATE);
                    editor = pref.edit();
                    editor.remove("fav" + numberPages);
                    editor.commit();
                    button4.setText("☆");

                } else {
                    pref = getApplicationContext().getSharedPreferences(
                            "HtmlFav", MODE_PRIVATE);
                    editor = pref.edit();
                    editor.putInt("fav" + numberPages, numberPages);
                    editor.commit();
                    button4.setText("★");

                }

            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(android.content.Intent.EXTRA_TEXT, "" + text);
                startActivity(Intent.createChooser(intent, "مشاركة.."));

            }
        });
        button6.setOnClickListener(new View.OnClickListener() {
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
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //button7.setTextColor(Color.parseColor("#cccccc"));

                if (numberPages == 1) {

                } else {
                    numberPages = numberPages - 1;
                    pref = getApplicationContext().getSharedPreferences(
                            "NumberPages", MODE_PRIVATE);
                    editor = pref.edit();
                    editor.putInt("pages", numberPages);
                    editor.commit();
                    myBrowser.loadUrl("file:///android_res/raw/" + "human" + numberPages + ".html");

                }
                checkFav();

            }

        });
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Tab4.this,Search.class);
                startActivity(intent);

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

    private void checkNumber() {

        pref = getApplicationContext().getSharedPreferences(
                "NumberPages", MODE_PRIVATE);
        int value1 = pref.getInt("pages", 0);

        if (value1 == 0) {
            numberPages = 1;

        } else  {
            numberPages = value1;

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
    private void checkFav() {

        pref = getApplicationContext().getSharedPreferences(
                "HtmlFav", MODE_PRIVATE);
        int value1 = pref.getInt("fav"+numberPages, 0);

        Log.i("numberPages", "numberPages: " + numberPages);
        Log.i("value1", "value1: " + value1);

        if (value1 == numberPages) {
                button4.setText("★");
        } else {
            button4.setText("☆");
        }


    }
    // Array list of all emoji icon
    private static final String[] ArrayEUnicodeString ={
            "\uE415",
            "\uE056",
            "\uE057",

};




// Nothing matched when it receive emoji icon with unicode "\uE415" from iphone. 'input' is message received from XMPP server

    @Override
    public void onBackPressed() {

        if (list_Menu.getVisibility() == View.VISIBLE) {
            list_Menu.setVisibility(View.GONE);

        } else  {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);

            startActivity(intent);

        }
    }

}
