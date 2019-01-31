package com.arrawdah.enjoy;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    TextView TitleMain,Title1,Title2;

    Button btnMenu,btnInfo;

    ImageView coverImage;

    public static DrawerLayout drawer;
    ListView list;

    private ArrayList<String> array1 = new ArrayList<String>();
    private ArrayList<Integer> array2 = new ArrayList<Integer>();
    LinearLayout linearTop;
    int heightTop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        linearTop = (LinearLayout)findViewById(R.id.linearTop);
        TitleMain = (TextView)findViewById(R.id.titleMain);
        Title1 = (TextView)findViewById(R.id.Title1);
        Title2 = (TextView)findViewById(R.id.Title2);
        coverImage= findViewById(R.id.bookCover);

        btnMenu = (Button)findViewById(R.id.BtnMenu);
        btnInfo = (Button)findViewById(R.id.BtnInfo);

        list = (ListView) findViewById(R.id.drawerList);
        drawer = (DrawerLayout)findViewById(R.id.drawerLayout);

        Typeface face = Typeface.createFromAsset(getAssets(), "fonts/SultanNahia_HelpMacNet_.ttf");
        TitleMain.setTypeface(face);
        Title1.setTypeface(face);
        Title2.setTypeface(face);

        coverImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MainActivity.this, ReaderHtml.class);
                startActivity(myIntent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });

        if (Build.VERSION.SDK_INT >=21) {
            //color StatusBar
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(Color.parseColor("#5c9ace"));
        }


        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.openDrawer(Gravity.RIGHT);

            }
        });
        btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent myIntent = new Intent(MainActivity.this, InfoApp.class);
                startActivity(myIntent);

            }
        });

        array1.add("مقدمة");
        array1.add("قراءة الكتاب");
        array1.add("الفهرس");
        array1.add("كتب أخرى");
        array1.add("مشاركة");

        array2.add(R.drawable.ic_intro);
        array2.add(R.drawable.ic_read);
        array2.add(R.drawable.ic_index);
        array2.add(R.drawable.ic_books);
        array2.add(R.drawable.ic_share);



        final CustomList adapter1 = new
                CustomList(MainActivity.this, array1,array2);


        // Header
        final TextView textView1 = new TextView(this.getApplicationContext());
        textView1.setTextColor(Color.parseColor("#eeeeee"));
        textView1.setBackgroundColor(Color.parseColor("#dcdcdc"));
        textView1.setGravity(Gravity.CENTER);
        linearTop.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                linearTop.getHeight(); //height is ready
                textView1.setHeight(linearTop.getHeight());

            }
        });

        textView1.setTextSize(25);
        list.addHeaderView(textView1);
        list.setAdapter(adapter1);



        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    final int position, long id) {
                String itemValue = (String) list.getItemAtPosition(position);
                //   Toast.makeText(getApplicationContext(), itemValue, Toast.LENGTH_SHORT).show();

                drawer.closeDrawer(Gravity.RIGHT);

                if (position == 1) {
                    Intent myIntent = new Intent(MainActivity.this, HtmlReader.class);
                    myIntent.putExtra("numberPages", 1);
                    myIntent.putExtra("TitlePass", "مقدمة");
                    startActivity(myIntent);
                }
                if (position == 2) {

                    Intent myIntent = new Intent(MainActivity.this, ReaderHtml.class);
                    startActivity(myIntent);

                }
                if (position == 3) {
                    Intent myIntent = new Intent(MainActivity.this, IndexBook.class);
                    startActivity(myIntent);

                }
                if (position == 4) {
                    Intent uriTwitter = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/developer?id=arrawdah"));
                    startActivity(uriTwitter);

                }
                if (position == 5) {
                    Intent i = new Intent(Intent.ACTION_SEND);
                    i.setType("text/plain");
                    i.putExtra(i.EXTRA_TEXT, "تطبيق كتاب (استمتع بحياتك) للشيخ : د.محمد العريفي" +
                                    "\n" +
                                    "\n" +
                                    "متجر أبل" +
                                    "\n" +
                                    "https://itunes.apple.com/sa/app/ktab-astmt-bhyatk-lldktwr/id722287232?mt=8\n" +
                                    "\n" +
                                    "البلاي ستور \n" +
                                    "https://play.google.com/store/apps/details?id=com.arrawdah.enjoy" + "\n"
                                    + "\n"
                                    + "#تطبيقات_جوال_الخير"
                                    + "\n"
                                    + "@Apps_Khair"
                    );

                    startActivity(Intent.createChooser(i, "مشاركة التطبيق ..."));
                }

            }

        });

        new CountDownTimer(5000, 1000) {

            public void onTick(long millisUntilFinished) {

            }

            public void onFinish() {
                Intent myIntent = new Intent(MainActivity.this, ReaderHtml.class);
                startActivity(myIntent);
            }

        };

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
