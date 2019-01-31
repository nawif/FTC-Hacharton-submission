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

public class FavoriteActivity extends AppCompatActivity {

    private ArrayList<Title> titles = new ArrayList<>();
    private ListView listFav;
    private TextView titleFav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);


        listFav = (ListView) findViewById(R.id.listFav);
        titleFav = (TextView) findViewById(R.id.titleFav);

        String fontPath = "fonts/noor.ttf";
        Typeface face = Typeface.createFromAsset(getApplicationContext().getAssets(), fontPath);
        titleFav.setTypeface(face);

        getData();
        MenuAdapter adapter = new MenuAdapter(this,titles);
        listFav.setAdapter(adapter);

        listFav.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(FavoriteActivity.this,ReaderActivity.class);

                intent.putExtra("title_obj", titles.get(position));
                startActivity(intent);

            }
        });
    }

    private void getData() {

        final DSDatabase dsDatabase = new DSDatabase(getApplicationContext());

        try {
            String[] values = {
                    "title_id"
            };

            Map<String,String> myMap = dsDatabase.getDatabase("favorites",values);

            for (int i = 0; i < myMap.size(); i++)
            {
                titles.add(dsDatabase.getRecordByID("titles","id",myMap.get("title_id_"+i)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}

