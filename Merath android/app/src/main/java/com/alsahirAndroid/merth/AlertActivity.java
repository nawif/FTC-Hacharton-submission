package com.alsahirAndroid.merth;

import android.com.merath.R;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

public class AlertActivity extends AppCompatActivity {

    private ArrayList<Alert> alerts = new ArrayList<>();
    private SwipeMenuListView listAlert;
    private TextView titleAlert,emptyText;
    private FloatingActionButton btnAdd;
    AlertAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert);

        listAlert = (SwipeMenuListView) findViewById(R.id.listAlert);
        titleAlert = (TextView) findViewById(R.id.titleAlert);
        btnAdd = (FloatingActionButton) findViewById(R.id.btnAdd);
        emptyText = (TextView) findViewById(R.id.emptyText);

        String fontPath = "fonts/noor.ttf";
        Typeface face = Typeface.createFromAsset(getApplicationContext().getAssets(), fontPath);
        titleAlert.setTypeface(face);
        emptyText.setTypeface(face);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(AlertActivity.this,AddAlertActivity.class);
                startActivity(intent);

            }
        });

        listAlert.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(AlertActivity.this,AddAlertActivity.class);
                intent.putExtra("alert_obj", alerts.get(position));
                startActivity(intent);


            }
        });

        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {

                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
                // set item width
                deleteItem.setWidth((200));
                //set a title
                deleteItem.setTitle("حذف");
                //set color title
                deleteItem.setTitleColor(Color.WHITE);
                // set item title fontsize
                deleteItem.setTitleSize(18);
                // set a icon
                //   deleteItem.setIcon(R.mipmap.ic_launcher);

                menu.addMenuItem(deleteItem);
            }
        };

        listAlert.setMenuCreator(creator);

        //Button Delete
        listAlert.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        // Delete
                        final DSDatabase dsDatabase = new DSDatabase(getApplicationContext());
                        Notification.cancelNotification(getApplicationContext(),Integer.parseInt(alerts.get(position).getId()));
                        dsDatabase.deleteFromDB("alerts","id",alerts.get(position).getId());
                        alerts.remove(position);
                        adapter.notifyDataSetChanged();
                        listAlert.invalidateViews();
                        checkText();
                        break;

                }
                // false : close the menu; true : not close the menu
                return true;
            }
        });
    }

    private void getData() {

        final DSDatabase dsDatabase = new DSDatabase(getApplicationContext());

        alerts.clear();

        try {
            String[] values = {
                    "id","title","time","repeat","sound","is_active"
            };

            Map<String,String> myMap = dsDatabase.getDatabase("alerts",values);

            for (int i = 0; i < myMap.size()/values.length; i++)
            {
                Alert alert = new Alert();
                alert.setId(myMap.get("id_"+i));
                alert.setTitle(myMap.get("title_"+i));
                alert.setTime(myMap.get("time_"+i));
                alert.setRepeat(myMap.get("repeat_"+i));
                alert.setSound(myMap.get("sound_"+i));
                alert.setActive(Boolean.valueOf(myMap.get("is_active_"+i)));
                alerts.add(alert);
            }

            checkText();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void checkText()
    {
        if (alerts.size() == 0)
            emptyText.setText("اضغط على علامة +" + "\n" + "لاضافة تنبيهات جديدة");
        else if (alerts.size() == 1)
            emptyText.setText("لحذف التنبيه" + "\n" + "اسحب التنبيه من اليمين لليسار!");
         else
            emptyText.setText(null);
    }
    @Override
    protected void onResume() {
        super.onResume();
        getData();
        adapter = new AlertAdapter(this,alerts);
        listAlert.setAdapter(adapter);

    }
}
