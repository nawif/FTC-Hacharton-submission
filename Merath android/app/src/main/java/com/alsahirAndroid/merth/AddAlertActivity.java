package com.alsahirAndroid.merth;

import android.app.Dialog;
import android.com.merath.R;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.annotation.IdRes;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AddAlertActivity extends AppCompatActivity {

    private TextView titleAddAlert;
    private FloatingActionButton btnSave;
    private Button btnRepeat;
    private Button btnAlert;
    private TimePicker timePicker;
    public static MediaPlayer mediaPlayer;
    private String title,time,repeat = null;
    private int soundName = 0;
    private Boolean isUpdate = false;
    private Alert alertObj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_alert);

        titleAddAlert = (TextView) findViewById(R.id.titleAddAlert);
        timePicker = (TimePicker) findViewById(R.id.timePicker);
        btnSave = (FloatingActionButton) findViewById(R.id.btnSave);
        btnRepeat = (Button) findViewById(R.id.btnRepeat);
        btnAlert = (Button) findViewById(R.id.btnAlert);

        String fontPath = "fonts/noor.ttf";
        final Typeface face = Typeface.createFromAsset(getApplicationContext().getAssets(), fontPath);
        titleAddAlert.setTypeface(face);
        btnRepeat.setTypeface(face);
        btnAlert.setTypeface(face);

        AudioManager am = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        am.setStreamVolume(AudioManager.STREAM_MUSIC, am.getStreamMaxVolume(AudioManager.STREAM_MUSIC), 0);

        alertObj = (Alert) getIntent().getSerializableExtra("alert_obj");

        if (alertObj != null)
        {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

            Date date = null;
            try {
                date = sdf.parse(alertObj.getTime());
            } catch (ParseException e) {
            }
            Calendar c = Calendar.getInstance();
            c.setTime(date);

            timePicker.setCurrentHour(c.get(Calendar.HOUR_OF_DAY));
            timePicker.setCurrentMinute(c.get(Calendar.MINUTE));

            title = alertObj.getTitle();
            time = alertObj.getTime();
            repeat = alertObj.getRepeat();
            soundName = Integer.parseInt(alertObj.getSound());

            isUpdate = true;

        }
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                time = timePicker.getCurrentHour() + ":" + timePicker.getCurrentMinute();

                final DSDatabase dsDatabase = new DSDatabase(getApplicationContext());


                if (soundName == 0)
                {
                    Toast.makeText(AddAlertActivity.this, "الرجاء اختيار احد التنبيهات", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (repeat == null)
                {
                    Toast.makeText(AddAlertActivity.this, "الرجاء اختيار مدة التكرار", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!isUpdate)
                {
                    String data = "'" + title.toString().trim() + "'" + "," + "'" + time.toString().trim() + "'" + "," + "'" + repeat.toString().trim() + "'"
                            + "," + "'" + soundName + "'" + "," + "'" + "true" + "'";

                    dsDatabase.insertToDB("alerts","title,time,repeat,sound,is_active", data);

                } else
                {
                    String data = "title=" + "'" + title.toString().trim() + "'" + "," + "time=" + "'" + time.toString().trim() + "'" + "," + "repeat=" + "'" + repeat.toString().trim() + "'"
                            + ","  + "sound=" + "'" + soundName + "'" + "," + "is_active=" + "'" + "true" + "'";

                    dsDatabase.updateCoulmnByID("alerts",data,alertObj.getId());

                }


                Toast.makeText(AddAlertActivity.this, "تم حفظ التنبيه بنجاح!", Toast.LENGTH_SHORT).show();
                finish();

            }
        });

        btnRepeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // custom dialog
                final Dialog dialog = new Dialog(AddAlertActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.radiobutton_dialog);
                TextView titleDialog = (TextView)dialog.findViewById(R.id.titleDialog);
                titleDialog.setText(btnRepeat.getText().toString() + " " + "كل: ");
                titleDialog.setTypeface(face);
                dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

                final List<String> stringList=new ArrayList<>();
                stringList.add("نصف ساعة");
                stringList.add("ساعة");
                stringList.add("ثلاث ساعات");
                stringList.add("يوم");
                stringList.add("أحد");
                stringList.add("أثنين");
                stringList.add("ثلاثاء");
                stringList.add("أربعاء");
                stringList.add("خميس");
                stringList.add("جمعة");
                stringList.add("سبت");

                RadioGroup rg = (RadioGroup) dialog.findViewById(R.id.radioGroupDialog);

                for(int i=0;i<stringList.size();i++){
                    RadioButton rb=new RadioButton(AddAlertActivity.this); // dynamically creating RadioButton and adding to RadioGroup.
                    rb.setGravity(Gravity.RIGHT | Gravity.CENTER_VERTICAL);
                    rb.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.colorLight));
                    rb.setText(stringList.get(i));
                    rb.setTypeface(face);
                    rb.setId(i);

                    if (repeat != null && Integer.parseInt(repeat) == i)
                        rb.setChecked(true);

                    if (isUpdate && Integer.parseInt(alertObj.getRepeat()) == i)
                        rb.setChecked(true);
                    rg.addView(rb);
                }


                rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {

                        repeat = "" + checkedId;
                    }
                });

                Button btnSaveDialog = (Button) dialog.findViewById(R.id.btnSaveDialog);

                btnSaveDialog.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();


            }
        });

        btnAlert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // custom dialog
                final Dialog dialog = new Dialog(AddAlertActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.radiobutton_dialog);
                TextView titleDialog = (TextView)dialog.findViewById(R.id.titleDialog);
                titleDialog.setText(btnAlert.getText().toString());
                titleDialog.setTypeface(face);
                dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);


                final List<String> stringList=new ArrayList<>();
                stringList.add( "الصلاة على النبي ﷺ 1");
                stringList.add( "الصلاة على النبي ﷺ 2");
                stringList.add("قيام الليل 1");
                stringList.add("قيام الليل 2");
                stringList.add("صيام الأثنين 1");
                stringList.add("صيام الأثنين 2");
                stringList.add("صيام الخميس 1");
                stringList.add("صيام الخميس 2");
                stringList.add("ذكر الله 1");
                stringList.add("ذكر الله 2");
                stringList.add("التسبيح 1");
                stringList.add("التسبيح 2");
                stringList.add("سيد الإستغفار 1");
                stringList.add("سيد الإستغفار 2");
                stringList.add("أحب الكلام إلى الله");
                stringList.add("أذكار الصباح");
                stringList.add("أذكار المساء");

                final List<Integer> soundList = new ArrayList<Integer>();
                soundList.add(R.raw.salee);
                soundList.add(R.raw._5);
                soundList.add(R.raw.qyeam);
                soundList.add(R.raw._4);
                soundList.add(R.raw.fasting_monday);
                soundList.add(R.raw._3);
                soundList.add(R.raw.fasting_thursday);
                soundList.add(R.raw._7);
                soundList.add(R.raw.daker_allah);
                soundList.add(R.raw._2);
                soundList.add(R.raw.sobhan_allah);
                soundList.add(R.raw._6);
                soundList.add(R.raw.saeed_alstgifar);
                soundList.add(R.raw._1);
                soundList.add(R.raw.ahabo_alkalam);
                soundList.add(R.raw.alert_morning);
                soundList.add(R.raw.alert_evening);

                final List<String> soundListName = new ArrayList<String>();
                soundListName.add("salee");
                soundListName.add("_5");
                soundListName.add("qyeam");
                soundListName.add("_4");
                soundListName.add("fasting_monday");
                soundListName.add("_3");
                soundListName.add("fasting_thursday");
                soundListName.add("_7");
                soundListName.add("daker_allah");
                soundListName.add("_2");
                soundListName.add("sobhan_allah");
                soundListName.add("_6");
                soundListName.add("saeed_alstgifar");
                soundListName.add("_1");
                soundListName.add("ahabo_alkalam");
                soundListName.add("alert_morning");
                soundListName.add("alert_evening");

                final RadioGroup rg = (RadioGroup) dialog.findViewById(R.id.radioGroupDialog);

                Toast.makeText(AddAlertActivity.this, "لمشاركة التنبيهات اضغط مطولاً على التنبيه!", Toast.LENGTH_LONG).show();

                for(int i=0;i<stringList.size();i++){
                    Log.e("string",stringList.get(i)+"h");
                    RadioButton rb=new RadioButton(AddAlertActivity.this); // dynamically creating RadioButton and adding to RadioGroup.
                    rb.setGravity(Gravity.RIGHT | Gravity.CENTER_VERTICAL);
                    rb.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.colorLight));
                    rb.setText(stringList.get(i));
                    rb.setId(i);
                    rg.addView(rb);

                    if (soundName != 0 && soundName == soundList.get(i))
                        rb.setChecked(true);

                    if (isUpdate && Integer.parseInt(alertObj.getSound()) == soundList.get(i))
                        rb.setChecked(true);


                    rb.setTypeface(face);
                }

                rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, @IdRes final int checkedId) {

                        title = stringList.get(checkedId);
                        soundName = soundList.get(checkedId);
                        playBeep(soundList.get(rg.getCheckedRadioButtonId()));

                        RadioButton rb = (RadioButton) rg.findViewById(rg.getCheckedRadioButtonId());

                        rb.setOnLongClickListener(new View.OnLongClickListener() {
                            @Override
                            public boolean onLongClick(View v) {
                                // TODO Auto-generated method stub


                                Uri uri = Uri.parse("android.resource://"
                                        + getPackageName() + "/raw/" + soundListName.get(checkedId));

                                Intent intent = new Intent();
                                intent.setAction(Intent.ACTION_SEND);
                                intent.setType("audio/mp3");
                                intent.putExtra(Intent.EXTRA_STREAM, uri);
                                startActivity(Intent.createChooser(intent, ""));

                                return true;
                            }
                        });

                    }
                });


                Button btnSaveDialog = (Button) dialog.findViewById(R.id.btnSaveDialog);

                btnSaveDialog.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();

                /*
                final DSDatabase dsDatabase = new DSDatabase(getApplicationContext());
                String[] values = {
                        "id","title","time","repeat","is_active"
                };

                dsDatabase.insertToDB("favorites","title,time,repeat,is_active","");*/

            }
        });

    }

    public void playBeep(int url) {
        try {

            if (mediaPlayer != null)
            {
                mediaPlayer.stop();
            }

            mediaPlayer = new MediaPlayer();
            AssetFileDescriptor afd = getApplicationContext().getResources().openRawResourceFd(url);
            mediaPlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
            afd.close();
            mediaPlayer.prepare();
            mediaPlayer.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
