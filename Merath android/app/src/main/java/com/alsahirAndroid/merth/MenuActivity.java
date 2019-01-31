package com.alsahirAndroid.merth;

import android.com.merath.R;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

public class MenuActivity extends AppCompatActivity {

    private ArrayList<Title> titles = new ArrayList<>();
    private ListView listMenu;
    private TextView titleMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        listMenu = (ListView) findViewById(R.id.listMenu);
        titleMenu = (TextView) findViewById(R.id.titleMenu);

        String fontPath = "fonts/noor.ttf";
        Typeface face = Typeface.createFromAsset(getApplicationContext().getAssets(), fontPath);
        titleMenu.setTypeface(face);


        getData();
        MenuAdapter adapter = new MenuAdapter(this,titles);
        listMenu.setAdapter(adapter);

        listMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(MenuActivity.this,ReaderActivity.class);

                intent.putExtra("title_obj", titles.get(position));
                startActivity(intent);

            }
        });
    }

    private void getData() {

        final DSDatabase dsDatabase = new DSDatabase(getApplicationContext());


        try {
            String[] values = {
                    "id","title","rawi","body","source"
            };

            Map<String,String> myMap = dsDatabase.getDatabase("titles",values);

            for (int i = 0; i< myMap.size()/values.length; i++)
            {
                Title title = new Title();
                title.setId(myMap.get("id_"+i));
                title.setTitle(myMap.get("title_"+i));
                title.setRawi(myMap.get("rawi_"+i));
                title.setBody(myMap.get("body_"+i));
                title.setSource(myMap.get("source_"+i));
                titles.add(title);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
