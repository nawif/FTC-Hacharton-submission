package com.alsahirandroid.human_devil;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


import java.util.ArrayList;


public class Tab3 extends Activity {
    ListView listView;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    private ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab3);

        listView = (ListView) findViewById(R.id.list);
        listView.invalidateViews();
        final ArrayList<String> values = new ArrayList<String>();
        final ArrayList<Integer> indexArry = new ArrayList<Integer>();


        pref = getApplicationContext().getSharedPreferences(
                "HtmlFav", MODE_PRIVATE);

        for (int i = 0; i < 456; i ++ ) {
            int numberFav = pref.getInt("fav"+i, 0);

            if (numberFav == 0) {

            } else {
                values.add("صفحة" + numberFav);
                indexArry.add(numberFav);
            }

        }

/*
        String[] values = new String[]{

                "ثواب من أسبغ الوضوء في البرد الشديد وهو يشق عليه",

                "ثواب السواك",
        };
        */

        adapter=new ArrayAdapter<String>(
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
        listView.setAdapter(adapter);

        // ListView Item Click Listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // ListView Clicked item index
                int itemPosition     = position;

                // ListView Clicked item value
                String  itemValue    = (String) listView.getItemAtPosition(position);
/*
                // Show Alert
                Toast.makeText(getApplicationContext(),
                        "Position :" + itemPosition + "  ListItem : " + itemValue, Toast.LENGTH_LONG)
                        .show();
                        */
                Intent myIntent = new Intent(Tab3.this, FavDetiles.class);
                myIntent.putExtra("numberFav", indexArry.get(itemPosition));
                startActivity(myIntent);

            }

        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.tab3, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return super.onOptionsItemSelected(item);
    }
}
