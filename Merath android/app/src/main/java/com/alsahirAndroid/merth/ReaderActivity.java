package com.alsahirAndroid.merth;

import android.com.merath.R;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.sql.SQLException;

public class ReaderActivity extends AppCompatActivity {

    private Button shareBtn,favBtn;
    private TextView titleReader,rawiReader,bodyReader,sourceReader;
    private Title title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reader);

        shareBtn = (Button) findViewById(R.id.btnShare);
        favBtn = (Button) findViewById(R.id.btnFav);

        titleReader = (TextView) findViewById(R.id.titleReader);
        rawiReader = (TextView) findViewById(R.id.rawiReader);
        bodyReader = (TextView) findViewById(R.id.bodyReader);
        sourceReader = (TextView) findViewById(R.id.sourceReader);

        String fontPath = "fonts/noor.ttf";
        Typeface face = Typeface.createFromAsset(getApplicationContext().getAssets(), fontPath);

        titleReader.setTypeface(face);
        rawiReader.setTypeface(face);
        bodyReader.setTypeface(face);
        sourceReader.setTypeface(face);

        bodyReader.setMovementMethod(new ScrollingMovementMethod());

        title = (Title) getIntent().getSerializableExtra("title_obj");

        titleReader.setText(title.getTitle());
        rawiReader.setText(title.getRawi());
        bodyReader.setText(title.getBody());
        sourceReader.setText(title.getSource());

        checkFav();

        //Share
        shareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builderSingle = new AlertDialog.Builder(ReaderActivity.this);
                builderSingle.setTitle("مشاركة عبر :-");

                final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(ReaderActivity.this, android.R.layout.select_dialog_item);
                arrayAdapter.add("مشاركة كصورة");
                arrayAdapter.add("مشاركة كنص");

                builderSingle.setNegativeButton("إلغاء", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                builderSingle.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        if(which == 0)
                        {
                            Uri imageUri = Uri.parse("android.resource://"
                                    + getPackageName() + "/drawable/merath_img"+title.getId());
                            Intent intent = new Intent();
                            intent.setAction(Intent.ACTION_SEND);
                            intent.setType("image/*");
                            intent.putExtra(Intent.EXTRA_STREAM, imageUri);
                            startActivity(Intent.createChooser(intent, ""));

                        } else
                        {
                            String shareBody = "" + title.getTitle() + "\n" + title.getRawi() + " " + title.getBody() + "\n" + title.getSource();
                            Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                            sharingIntent.setType("text/plain");
                            //sharingIntent.setType("message/rfc822");
                            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                            startActivity(Intent.createChooser(sharingIntent, "مشاركة عبر"));

                        }
                    }
                });
                builderSingle.show();

            }
        });
        //Favorite
        favBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final DSDatabase dsDatabase = new DSDatabase(getApplicationContext());

                try {

                    Boolean isExist = dsDatabase.checkRecordIsExist("favorites",title.getId());

                    if (isExist)
                    {
                        dsDatabase.deleteFromDB("favorites","title_id",title.getId());
                    } else
                    {
                        dsDatabase.insertToDB("favorites","title_id",title.getId());

                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                checkFav();
            }
        });
    }

    private void  checkFav()
    {
        final DSDatabase dsDatabase = new DSDatabase(getApplicationContext());

        try {

            Boolean isExist = dsDatabase.checkRecordIsExist("favorites",title.getId());

            if (isExist)
            {
                favBtn.setBackgroundResource(R.drawable.fav_btn_fill);

            } else
            {
                favBtn.setBackgroundResource(R.drawable.fav_btn);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
