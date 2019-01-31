package com.alsahirandroid.human_devil;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;


public class Tab2 extends Activity {

    ListView listView2;
    private ArrayAdapter adapter;
    int itemPosition;
    SeekBar seek;
    MediaPlayer playerONLINE,player;
    Handler seekHandler = new Handler();

    public static final int DIALOG_DOWNLOAD_PROGRESS = 0;
    private ProgressDialog mProgressDialog;

    final ArrayList<String> ArryName = new ArrayList<String>();
    final ArrayList<String> ArryLink = new ArrayList<String>();
    Button PlayPause;
    TextView txtDuration,textNamePlayer;
    Thread myThread = null;
    private int seekForwardTime = 5 * 1000; // default 5 second
    private int seekBackwardTime = 5 * 1000; // default 5 second
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab2);

        listView2 = (ListView) findViewById(R.id.list2);
        seek = (SeekBar)findViewById(R.id.seekBar);
        listView2.invalidateViews();
        PlayPause = (Button)findViewById(R.id.buttonPlay_Pause);
        txtDuration = (TextView)findViewById(R.id.textDuration);
        textNamePlayer = (TextView)findViewById(R.id.textNamePlayer);
        ArryName.add("الرقية الشرعية - الشيخ سعود الفايز");
        ArryName.add("الرقية الشرعية - عمر أحمد العنزي");
        ArryName.add("الرقية الشرعية ـ ماجد الزامل");
        ArryName.add("الرقية الشرعية ـ ماهر المعيقلي");
        ArryName.add("الرقية الشرعية ـ ناصر القطامي");
        ArryName.add("الرقية الشرعية ـ ياسر الدوسري");
        ArryName.add("الرقية الشرعية مختصرة ـ سعود الفايز");
        ArryName.add("الإستشفاء بالدعاء ـ للشيخ سعود الفايز");


        /*
        ArryLink.add("https://api.soundcloud.com/tracks/176230521/download?client_id=b45b1aa10f1ac2941910a7f0d10f8e28");
        ArryLink.add("https://api.soundcloud.com/tracks/176230524/download?client_id=b45b1aa10f1ac2941910a7f0d10f8e28");
        ArryLink.add("https://api.soundcloud.com/tracks/176230529/download?client_id=b45b1aa10f1ac2941910a7f0d10f8e28");
        ArryLink.add("https://api.soundcloud.com/tracks/176230551/download?client_id=b45b1aa10f1ac2941910a7f0d10f8e28");
        ArryLink.add("https://api.soundcloud.com/tracks/176230554/download?client_id=b45b1aa10f1ac2941910a7f0d10f8e28");
        ArryLink.add("https://api.soundcloud.com/tracks/176230557/download?client_id=b45b1aa10f1ac2941910a7f0d10f8e28");
        ArryLink.add("https://api.soundcloud.com/tracks/176230560/download?client_id=b45b1aa10f1ac2941910a7f0d10f8e28");
        ArryLink.add("https://api.soundcloud.com/tracks/168859325/download?client_id=b45b1aa10f1ac2941910a7f0d10f8e28");

*/
        ArryLink.add("https://dl.dropboxusercontent.com/u/100937100/human1.mp3");
        ArryLink.add("https://dl.dropboxusercontent.com/u/100937100/human2.mp3");
        ArryLink.add("https://dl.dropboxusercontent.com/u/100937100/human3.mp3");
        ArryLink.add("https://dl.dropboxusercontent.com/u/100937100/human4.mp3");
        ArryLink.add("https://dl.dropboxusercontent.com/u/100937100/human5.mp3");
        ArryLink.add("https://dl.dropboxusercontent.com/u/100937100/human6.mp3");
        ArryLink.add("https://dl.dropboxusercontent.com/u/100937100/human7.mp3");
        ArryLink.add("https://dl.dropboxusercontent.com/u/100937100/human8.mp3");

        adapter=new ArrayAdapter<String>(
                this,android.R.layout.simple_list_item_1, ArryName){

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
        listView2.setAdapter(adapter);

        // ListView Item Click Listener
        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // ListView Clicked item index
                    itemPosition     = position;

                // ListView Clicked item value
                String  itemValue    = (String) listView2.getItemAtPosition(position);


/*

                // Show Alert
                Toast.makeText(getApplicationContext(),
                        "Position :" + itemPosition + "  ListItem : " + itemValue, Toast.LENGTH_LONG)
                        .show();
                        */

                /*
                try {
                    MediaPlayer player = new MediaPlayer();
                    player.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    player.setDataSource(ArryLink.get(itemPosition));
                    player.prepare();
                    player.start();

                } catch (Exception e) {
                    // TODO: handle exception
                }
*/

                File file = new File("/sdcard/الرقية الشرعية/" + "" +ArryName.get(itemPosition)+".mp3");
                if(file.exists()) {

                    if (player != null) {
                        player.stop();
                        player.release();
                        player = null;
                        seekHandler.removeCallbacks(run);
                    }
                        try {
                        String filePath = "/sdcard/الرقية الشرعية/" + "" +ArryName.get(itemPosition)+".mp3";
                        player = new  MediaPlayer();
                        player.setDataSource(filePath);
                        player.prepare();
                        player.start();
                        seek.setMax(player.getDuration());
                        seekUpdation();
                        Runnable myRunnableThread = new CountDownRunner();
                        myThread= new Thread(myRunnableThread);
                        myThread.start();

                        if (player.isPlaying()) {
                            PlayPause.setText("■");
                        }

                    } catch (Exception e) {

                    }
                }

                else {
                    AlertDialog alertDialog = new AlertDialog.Builder(Tab2.this).create();

                    alertDialog.setTitle("اختر احد الخيارات :");
                    Log.i("player", "player: " );

                    alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, " تشغيل عبر الإنترنت", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int id) {
                            Log.i("تشغيل عبر الإنترنت", "تشغيل عبر الإنترنت: " );

                            if (player != null) {
                                player.stop();
                                player.release();
                                player = null;
                                seekHandler.removeCallbacks(run);
                            }

                            try {
                                player = new MediaPlayer();
                                player.setAudioStreamType(AudioManager.STREAM_MUSIC);
                                player.setDataSource(ArryLink.get(itemPosition));
                                player.setAudioStreamType(AudioManager.STREAM_RING);
                                player.prepare();
                                player.start();
                                seek.setMax(player.getDuration());
                                seekUpdation();
                                Runnable myRunnableThread = new CountDownRunner();
                                myThread= new Thread(myRunnableThread);
                                myThread.start();

                                if (player.isPlaying()) {
                                    PlayPause.setText("■");
                                }
                            } catch (Exception e) {
                                // TODO: handle exception
                            }

                        } });

                    alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "حفظ", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int id) {
                            Log.i("حفظ", "حفظ: " );

                            startDownload();


                        }});

                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "إلغاء", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int id) {

                            //...

                        }
                    });
                    alertDialog.show();

                }

            }


        });


        PlayPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    if (player.isPlaying()) {
                        player.stop();
                        seek.setProgress(0);
                        seekHandler.removeCallbacks(run);
                        PlayPause.setText("►");
                        txtDuration.setText("");
                        textNamePlayer.setText("");

                    }

                } catch (Exception e) {

                }

            }
        });

        seek.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {


                        if (arg2) {
                            player.seekTo(arg1);
                            seek.setProgress(arg1);
                        }

                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar arg0) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar arg0) {

                    }
                });

}

    public void rewindSong() {
        if (player != null) {
            int currentPosition = player.getCurrentPosition();
            if (currentPosition - seekBackwardTime >= 0) {
                player.seekTo(currentPosition - seekBackwardTime);
            } else {
                player.seekTo(0);
            }
        }
    }

    public void forwardSong() {
        if (player != null) {
            int currentPosition = player.getCurrentPosition();
            if (currentPosition + seekForwardTime <= player.getDuration()) {
                player.seekTo(currentPosition + seekForwardTime);
            } else {
                player.seekTo(player.getDuration());
            }
        }
    }


    public void doWork() {
        runOnUiThread(new Runnable() {
            public void run() {
                try{
                    if (player.isPlaying()) {

                        final int HOUR = 60*60*1000;
                        final int MINUTE = 60*1000;
                        final int SECOND = 1000;

                        int durationInMillis = player.getDuration();
                        int curVolume = player.getCurrentPosition();

                        int durationHour = durationInMillis/HOUR;
                        int durationMint = (durationInMillis%HOUR)/MINUTE;
                        int durationSec = (durationInMillis%MINUTE)/SECOND;

                        int currentHour = curVolume/HOUR;
                        int currentMint = (curVolume%HOUR)/MINUTE;
                        int currentSec = (curVolume%MINUTE)/SECOND;

                        if(durationHour>0){
                            System.out.println(" 1 = "+String.format("%02d:%02d:%02d/%02d:%02d:%02d",
                                    currentHour,currentMint,currentSec, durationHour,durationMint,durationSec));
                        }else{
                            System.out.println(" 1 = "+String.format("%02d:%02d/%02d:%02d",
                                    currentMint,currentSec, durationMint,durationSec));
                        }
                        txtDuration.setText("" + String.format("%02d:%02d:%02d/%02d:%02d:%02d",
                                currentHour,currentMint,currentSec, durationHour,durationMint,durationSec));
                        textNamePlayer.setText("" + ArryName.get(itemPosition));


                    } else {
                        myThread.stop();
                        txtDuration.setText("");
                        textNamePlayer.setText("");
                    }
                }catch (Exception e) {}
            }
        });
    }


    class CountDownRunner implements Runnable{
        // @Override
        public void run() {
            while(!Thread.currentThread().isInterrupted()){
                try {
                    doWork();
                    Thread.sleep(1000); // Pause of 1 Second
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }catch(Exception e){
                }
            }
        }
    }

    Runnable run = new Runnable() {
        @Override
        public void run() {
            seekUpdation();
      //      Log.i("Runnable", "Runnable: " );
            if (player.isPlaying()) {

            } else {
                player.stop();
                seek.setProgress(0);
                seekHandler.removeCallbacks(run);
                PlayPause.setText("►");
                txtDuration.setText("");
                textNamePlayer.setText("");

            }


         //   txtDuration.setText("" + player.getDuration());
        }
    };

    public void seekUpdation() {
        seek.setProgress(player.getCurrentPosition());
        seekHandler.postDelayed(run, 1000);

      //  Log.i("seekUpdation", "seekUpdation: " );


    }

    private void startDownload() {
        String url = "" + ArryLink.get(itemPosition);
        Log.i("link","link : " + ArryLink.get(itemPosition) );
        new DownloadFileAsync().execute(url);
    }
    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DIALOG_DOWNLOAD_PROGRESS:
                mProgressDialog = new ProgressDialog(this);
                mProgressDialog.setMessage("جاري تحميل الملف ...");
                mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                mProgressDialog.setCancelable(false);
                mProgressDialog.show();
                return mProgressDialog;
            default:
                return null;
        }
    }

    class DownloadFileAsync extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showDialog(DIALOG_DOWNLOAD_PROGRESS);
        }

        @Override
        protected String doInBackground(String... aurl) {
            int count;

            try {
                File rootPath = new File(Environment.getExternalStorageDirectory(), "الرقية الشرعية");
                if(!rootPath.exists()) {
                    rootPath.mkdirs();
                }

                URL url = new URL(aurl[0]);
                URLConnection conexion = url.openConnection();
                conexion.connect();

                int lenghtOfFile = conexion.getContentLength();
                Log.d("ANDRO_ASYNC", "Lenght of file: " + lenghtOfFile);


               // OutputStream output = new FileOutputStream("/sdcard/"+ArryName.get(itemPosition)+".mp3");

                InputStream input = new BufferedInputStream(url.openStream());
                OutputStream output = new FileOutputStream("/sdcard/الرقية الشرعية/" + "" +ArryName.get(itemPosition)+".mp3");

                byte data[] = new byte[1024];

                long total = 0;

                while ((count = input.read(data)) != -1) {
                    total += count;
                    publishProgress(""+(int)((total*100)/lenghtOfFile));
                    output.write(data, 0, count);
                }

                output.flush();
                output.close();
                input.close();
            } catch (Exception e) {}
            return null;

        }
        protected void onProgressUpdate(String... progress) {
            Log.d("ANDRO_ASYNC",progress[0]);
            mProgressDialog.setProgress(Integer.parseInt(progress[0]));
        }

        @Override
        protected void onPostExecute(String unused) {
            dismissDialog(DIALOG_DOWNLOAD_PROGRESS);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.tab2, menu);
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



