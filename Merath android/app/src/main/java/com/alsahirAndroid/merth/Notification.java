package com.alsahirAndroid.merth;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Fahd on 14/10/2016.
 */

public class Notification {
    private static final String TAG = Notification.class.getName();
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    static public void createNotification(Context context, Integer id_notification, String text_notification,
                                          Calendar calendar,String sound, String repeat) {

        cancelNotification(context,id_notification);
        Intent intent = new Intent(context,NotificationReceiver.class);
        intent.putExtra("id",id_notification);
        intent.putExtra("title" +id_notification,text_notification);
        intent.putExtra("time" +id_notification, "" +calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE));
        intent.putExtra("repeat" +id_notification,repeat);
        intent.putExtra("sound" +id_notification,sound);
        intent.putExtra("is_active" +id_notification,true);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(context,id_notification,intent, PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(context.ALARM_SERVICE);

        System.out.println("time = " + calendar.getTime());
        int api = Integer.valueOf(Build.VERSION.SDK);

        if (api >= 19) {
            alarmManager.setExact(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pendingIntent);

        } else  {
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),Notification.getRepeat(repeat),pendingIntent);
        }


    }

    //Cancel Notification
    static public void cancelNotification(Context context, Integer id_Notifiaction) {

        NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancel(id_Notifiaction);

        Intent intent = new Intent(context, NotificationReceiver.class);
        PendingIntent sender = PendingIntent.getBroadcast(context, id_Notifiaction, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(context.ALARM_SERVICE);
        alarmManager.cancel(sender);

    }

    //get Calendar From String
    static public Calendar getCalendarFromString(String time,String timeFormat,String repeat,Boolean compare) throws Exception {

        SimpleDateFormat parseFormat = new SimpleDateFormat(timeFormat, Locale.ENGLISH);
        Date date = parseFormat.parse(time);
        Calendar calendarConvert = Calendar.getInstance();
        calendarConvert.setTime(date);

        //pass
        Calendar calendarResult = Calendar.getInstance();
        calendarResult.set(Calendar.HOUR_OF_DAY,calendarConvert.get(Calendar.HOUR_OF_DAY));
        calendarResult.set(Calendar.MINUTE,calendarConvert.get(Calendar.MINUTE));
        calendarResult.set(Calendar.SECOND,0);

        int rep = Integer.parseInt(repeat);

        //Now
        Calendar calendarNow = Calendar.getInstance();
        calendarNow.set(Calendar.SECOND,59);

        if (rep != 0 && rep != 1 && rep != 2  && rep != 3)
        {
            calendarResult.set(Calendar.DAY_OF_WEEK,rep -3);

        }

        if (compare)
        {
            int hour = calendarNow.get(Calendar.HOUR_OF_DAY);
            int min =  calendarNow.get(Calendar.MINUTE);
            int sec =  calendarNow.get(Calendar.SECOND);
            int compareTwo = calendarResult.compareTo(calendarNow);

            Log.e("compare",calendarResult.before(calendarNow)+"||"+compareTwo+"||"+calendarResult.toString()+"|"+calendarNow.toString());
            if (calendarResult.before(calendarNow) || compareTwo == 0) {

                if (rep == 0)
                {
                    //calendarResult.set(Calendar.HOUR_OF_DAY, hour);
                    //calendarResult.add(Calendar.MINUTE, 30);
                    while (calendarResult.before(calendarNow)){
                        calendarResult.add(Calendar.MINUTE, 30);
                    }

                }else if (rep == 1)
                {
                    if (hour == 23)
                        calendarResult.set(Calendar.HOUR_OF_DAY, hour + 1);
                    else if(min < calendarResult.get(Calendar.MINUTE))
                        calendarResult.set(Calendar.HOUR_OF_DAY, hour);
                    else
                        calendarResult.set(Calendar.HOUR_OF_DAY, hour + 1);
                }else if (rep == 2)
                {
                    while (calendarResult.before(calendarNow)){
                        calendarResult.add(Calendar.HOUR, 3);
                    }
                }
                else if (rep == 3)
                    calendarResult.add(Calendar.DATE, 1);
                else
                    calendarResult.add(Calendar.DATE, 7);
            } else
            {

                if (rep == 0)
                {
                    //calendarResult.add(Calendar.MINUTE, min);
                }
                else if (rep == 1)
                {
                    if (hour == 23)
                        calendarResult.set(Calendar.HOUR_OF_DAY, hour + 1);
                    else if(min < calendarResult.get(Calendar.MINUTE))
                        calendarResult.set(Calendar.HOUR_OF_DAY, hour);
                    else
                        calendarResult.set(Calendar.HOUR_OF_DAY, hour + 1);
                }
                else if (rep == 2)
                {
                    /*if (hour == 23)
                        calendarResult.set(Calendar.HOUR_OF_DAY, hour + _3);
                    else if(min < calendarResult.get(Calendar.MINUTE))
                        calendarResult.set(Calendar.HOUR_OF_DAY, hour+2);
                    else
                        calendarResult.set(Calendar.HOUR_OF_DAY, hour + _3);*/
                }
                else if (rep == 3)
                    if (calendarResult.equals(calendarNow) || calendarNow.after(calendarResult))
                        calendarResult.add(Calendar.DATE, 1);
                    else
                    if (calendarResult.equals(calendarNow) || calendarNow.after(calendarResult))
                        calendarResult.add(Calendar.DATE, 7);


            }

        }

        return calendarResult;
    }

    //get Calendar From String
    static public String getStringFromCalendar(int hour, int minute) throws Exception {
        String input = "" + hour + ":" + "" + minute;
        DateFormat inputFormat = new SimpleDateFormat("HH:mm", Locale.ENGLISH);
        DateFormat outputFormat = new SimpleDateFormat("hh:mm a", Locale.ENGLISH);
        String result = outputFormat.format(inputFormat.parse(input));
        return result;
    }

    //get Calendar From String
    static public String convertTo24(String time) throws Exception {
        DateFormat inputFormat = new SimpleDateFormat("hh:mm a", Locale.ENGLISH);
        DateFormat outputFormat = new SimpleDateFormat("HH:mm", Locale.ENGLISH);
        String result = outputFormat.format(inputFormat.parse(time));
        return result;
    }

    static public long getRepeat(String strRepeat)
    {
        long repeat = 0;
        int rep = Integer.parseInt(strRepeat);

        if (rep == 0)
            repeat = AlarmManager.INTERVAL_HALF_HOUR;
        else if (rep == 1)
            repeat = AlarmManager.INTERVAL_HOUR;
        else if (rep == 2)
            repeat = AlarmManager.INTERVAL_HOUR*3;
        else if (rep == 3)
            repeat = AlarmManager.INTERVAL_DAY;
        else
            repeat = 1000*60*60*24*7;

        return repeat;
    }

    static public Calendar getCalendarPlus(Calendar cal,String strRepeat)
    {

        int rep = Integer.parseInt(strRepeat);

        if (rep == 0)
            cal.add(Calendar.HOUR_OF_DAY, 1);
        else if (rep == 1)
            cal.add(Calendar.DAY_OF_MONTH, 1);
        else
            cal.add(Calendar.DAY_OF_WEEK,rep+1);

        return cal;
    }

}
