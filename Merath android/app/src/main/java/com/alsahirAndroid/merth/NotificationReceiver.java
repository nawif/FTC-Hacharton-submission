package com.alsahirAndroid.merth;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.com.merath.R;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.AudioAttributes;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;

import static android.support.v4.app.NotificationCompat.FLAG_ONLY_ALERT_ONCE;
import static android.support.v4.app.NotificationCompat.GROUP_ALERT_SUMMARY;

/**
 * Created by Fahd on 14/10/2016.
 */


public class NotificationReceiver extends BroadcastReceiver {
    private static final String NOTIFICATION_CHANNEL_ID = "my_notification_channel";
    static long lastNotification=1;
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onReceive(Context context, Intent intent) {

        try {

            if (intent.getAction() != null && intent.getAction().equalsIgnoreCase(Intent.ACTION_BOOT_COMPLETED) || intent.getAction() != null && intent.getAction().equalsIgnoreCase(Intent.ACTION_REBOOT)) {
                if (context != null) {
                    // your codes here
                    createNotifications(context);
                }
            } else
            {

                Boolean isChecked = intent.getBooleanExtra("is_active"+intent.getIntExtra("id",0),false);

                if (isChecked)
                {
                    //get Intent
                    Integer id_notification = intent.getIntExtra("id",0);
                    String title = intent.getStringExtra("title" + id_notification);
                    String time = intent.getStringExtra("time" + id_notification);
                    String sound = intent.getStringExtra("sound" + id_notification);
                    String repeat = intent.getStringExtra("repeat" + id_notification);

                    Notification.cancelNotification(context,id_notification);

                    //NotificationManager
                    NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

                    //Intent
                    Intent intent1 = new Intent(context,MainActivity.class);
                    intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);



                    //PendingIntent
                    PendingIntent pendingIntent = PendingIntent.getActivity(context,id_notification,intent1, PendingIntent.FLAG_UPDATE_CURRENT);

                    int api = Integer.valueOf(Build.VERSION.SDK);

                    if (api >= 19) {
                         Calendar calendar = null;
                        try {
                            calendar = Notification.getCalendarFromString(time,"HH:mm",repeat,true);
                            System.out.println("calender :"+calendar.toString());

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        Notification.createNotification(context,id_notification,title,calendar,sound,repeat);

                    }
                    Uri alarmSound = Uri.parse("android.resource://"
                            + context.getPackageName() + "/" + Integer.parseInt(sound));

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        AudioAttributes attributes = new AudioAttributes.Builder()
                                .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                                .build();

                        NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "My Notifications", NotificationManager.IMPORTANCE_DEFAULT);

                        // Configure the notification channel.
                        notificationChannel.setDescription("Channel description");
                        notificationChannel.enableLights(true);
                        notificationChannel.setLightColor(Color.BLUE);
                        notificationChannel.setVibrationPattern(new long[]{0, 1000, 500, 1000});
                        notificationChannel.enableVibration(true);
                        notificationManager.createNotificationChannel(notificationChannel);
                        // play sound
                        try {
                            Ringtone r = RingtoneManager.getRingtone(context, alarmSound);
                            r.play();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    //NotificationCompat
                    NotificationCompat.Builder builder = new NotificationCompat.Builder(context,NOTIFICATION_CHANNEL_ID)
                            .setContentIntent(pendingIntent)
                            .setSmallIcon(R.mipmap.ic_white)
                            .setContentTitle(context.getResources().getString(R.string.app_name))
                            .setContentText(title)
                            //.setSound(Uri.parse("android.resource://" + context.getPackageName() + "/" + "/raw/" + sound_name))
                            .setVibrate(new long[] { 1000, 1000, 1000, 1000, 1000 })
                            .setAutoCancel(true)
                            .setGroupAlertBehavior(FLAG_ONLY_ALERT_ONCE);


                    builder.setSound(alarmSound);

                    //notify
                    notificationManager.notify(id_notification,builder.build());

                } else
                {
                    NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
                    notificationManager.cancelAll();
                }
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }


    }

    private boolean canBePushed(Calendar calendar) {

        float currentNot=calendar.getTimeInMillis();
        Log.d("NotificationManager", "canBePushed:current= " +currentNot);
        Log.d("NotificationManager", "canBePushed:last= " +lastNotification);

        if(currentNot - lastNotification < 5000){
            return false;
        }
        return true;
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void createNotifications(Context context) {

        ArrayList<Alert> alerts = getData(context);

        if (alerts.size() != 0)
        {
            for (int i =0; i < alerts.size(); i++)
            {
                Calendar cal = null;
                if(alerts.get(i).getActive()){
                    try {
                        cal = Notification.getCalendarFromString(alerts.get(i).getTime(),"HH:mm",alerts.get(i).getRepeat(),true);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Notification.createNotification(context,Integer.parseInt(alerts.get(i).getId()),alerts.get(i).getTitle(),cal,alerts.get(i).getSound(),alerts.get(i).getRepeat());
                    System.out.println("alerts = " + cal.getTime() +" .. "+alerts.get(i).getActive());
                }else {
                    Notification.cancelNotification(context,Integer.parseInt(alerts.get(i).getId()));
                }
            }

        }

    }

    public ArrayList<Alert> getData(Context context) {

        final DSDatabase dsDatabase = new DSDatabase(context);
        ArrayList<Alert> alerts = new ArrayList<>();

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

            System.out.println(alerts);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return alerts;
    }

}