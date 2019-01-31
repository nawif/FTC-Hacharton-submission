package com.alsahirandroid.human_devil;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class Search extends Activity {

    SqlLiteDbHelper dbHelper;
    Contact contacts ;
    ListView listView2;
    private ArrayAdapter adapter;
    String StringExist;

    final ArrayList<String> ArryName = new ArrayList<String>();
    final ArrayList<Integer> ArryName2 = new ArrayList<Integer>();
    EditText input;
    Button btn_Search;
    AlertDialog alertDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        listView2 = (ListView) findViewById(R.id.list2);
        btn_Search = (Button)findViewById(R.id.buttonSearch);
        input = new EditText(this);
        alertDialog = new AlertDialog.Builder(Search.this).create();

        btn_Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArryName.clear();
                ArryName2.clear();

                alertDialog.setTitle("البحث");
                alertDialog.setView(input);

                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, " بحث", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int id) {

                        Log.i("Search", "Search: " +input.getText());

                        resulte();

                    } });


                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "إلغاء", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int id) {

                        //...

                    }
                });
                alertDialog.show();



            }
        });


    }

    private void resulte() {
        dbHelper = new SqlLiteDbHelper(this);

        dbHelper.openDataBase();
        contacts = new Contact();
        contacts = dbHelper.Get_ContactDetails();


        SqlLiteDbHelper hh = new SqlLiteDbHelper(this);
        SQLiteDatabase db = hh.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM text_Search", null);
        cursor.moveToFirst();
        while (cursor.moveToNext()) {
            StringExist = cursor.getString(1);
            if (StringExist.contains(input.getText())) {
                ArryName.add(""+cursor.getString(1));
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                ArryName2.add(id);
            }

        }
/*
        for (int i = 0 ; i < 457; i++) {
            StringExist = cursor.getString(1);
            if (StringExist.contains("فتجد")) {
                ArryName2.add(""+i);
            }
            ArryName.add(""+cursor.getString(1));
            cursor.moveToNext();
            i ++;
            Log.i("i", "i: "+i);
        }

*/
        cursor.close();
        db.close();


        Log.i("ArryName2", "ArryName2: "+ArryName2);
        Log.i("ArryName1", "ArryName1: "+ArryName.size());


        //  tv1.setText("Name: "+contacts.getName()+"\n Mobile No: "+contacts.getMobileNo());
        // tv1.setText(""+contacts.getName());


        adapter=new ArrayAdapter<String>(
                this,android.R.layout.simple_list_item_1, ArryName){

            @Override
            public View getView(int position, View convertView,
                                ViewGroup parent) {
                View view =super.getView(position, convertView, parent);

                TextView textView=(TextView) view.findViewById(android.R.id.text1);
                // textView.setHeight(150);
                // textView.setMinimumHeight(150);
                textView.setMaxLines(2);

            /*YOUR CHOICE OF COLOR*/
                textView.setTextColor(Color.parseColor("#506700"));

                return view;
            }
        };
        // Assign adapter to ListView
        listView2.setAdapter(adapter);

        AlertDialog alertDialog = new AlertDialog.Builder(Search.this).create();
        alertDialog.setTitle("تم العثور على : " + ArryName.size() + " نتيجة");
        alertDialog.show();

        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // ListView Clicked item index
                int itemPosition     = position;

                // ListView Clicked item value
                String  itemValue    = (String) listView2.getItemAtPosition(position);
/*
                // Show Alert
                Toast.makeText(getApplicationContext(),
                        "Position :" + itemPosition + "  ListItem : " + itemValue, Toast.LENGTH_LONG)
                        .show();
                        */
                Log.i("id", "itemPosition: "+ArryName2.get(itemPosition));


                Intent myIntent = new Intent(Search.this, FavDetiles.class);
                myIntent.putExtra("numberFav", ArryName2.get(itemPosition));
                startActivity(myIntent);

            }

        });


    }
}
