package com.arrawdah.enjoy;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;


public class FavoritePage extends AppCompatActivity {

    private ListView list;
    private TextView titleFav;
    private GridViewAdapter gridAdapter;
    private ArrayList<String> array_fav = new ArrayList<String>();
    private ArrayList<Integer> array_index = new ArrayList<Integer>();
    SharedPreferences pref;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_gallery);

        list = (ListView)findViewById(R.id.list_fav);
        titleFav = (TextView)findViewById(R.id.titleFav);

        Typeface face = Typeface.createFromAsset(getAssets(), "fonts/SultanNahia_HelpMacNet_.ttf");
        titleFav.setTypeface(face);

        pref = getApplicationContext().getSharedPreferences(
                "PagesFav", MODE_PRIVATE);

        for (int i = 0 ; i <= 461; i++) {
            String value1 = pref.getString("favPages" + i, null);
            if (value1 != null) {
                int page = 461 - i;
                array_fav.add("الصفحة"+ page);
                array_index.add(page -1);
            }
        }


        final CustomListFav adapter1 = new
                CustomListFav(FavoritePage.this, array_fav);

        list.setAdapter(adapter1);


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    final int position, long id) {
                String itemValue = (String) list.getItemAtPosition(position);
                //   Toast.makeText(getApplicationContext(), itemValue, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(FavoritePage.this, ReaderHtml.class);
                //Start details activity
                int numberPage =  array_index.get(position);
                numberPage = 460 - numberPage;
                intent.putExtra("numberPassImg", numberPage);
                intent.putExtra("numberPassCheck", true);
                finish();
                startActivity(intent);

            }

        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_favorite_gallery, menu);
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
