package com.example.test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.PersistableBundle;
import android.preference.PreferenceManager;
import android.view.Window;
import android.view.WindowManager;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {
    private int timePassed = 0;
    private Date currentDate;
    private Context context;
    private SharedPreferences timePassedsp;
    private SharedPreferences.Editor editor;
    private final DateFormat timeFormat = new SimpleDateFormat("DD:HH:mm:ss",Locale.getDefault());
    private String timeText;
    private MediaPlayer mediaPlayer, mediaPlayerWash, mediaPlayerHit, mediaPlayerHappy;
    private String[] notificationStrings = {"Your fird is dirty :(", "Your fird is hungry :(", "Your fird is tiered :(", "Your fird is sad :("};
    class Timer extends CountDownTimer {
        public Timer() {
            super(5000, 1000);
        }
        @Override
        public void onTick(long millisUntilFinished) {
        }
        @Override
        public void onFinish() {
            mediaPlayerHit = MediaPlayer.create(context, R.raw.hitsong);
            mediaPlayerHit.start();
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationChannel channel = new NotificationChannel("Notification", "My Notification", NotificationManager.IMPORTANCE_DEFAULT);
                NotificationManager manager = getSystemService(NotificationManager.class);
                manager.createNotificationChannel(channel);
            }
            Intent intent = new Intent(context, ResultActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE);

            int i = (int) (Math.random() * 4);
            NotificationCompat.Builder builder = new NotificationCompat.Builder(context,"Notification")
                    .setSmallIcon(R.drawable.round_icon)
                    .setContentTitle("Fird")
                    .setContentText(notificationStrings[i])
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true);

            NotificationManagerCompat managerCompat = NotificationManagerCompat.from(MainActivity.this);
            managerCompat.notify(1,builder.build());
        }
    }
    Timer timer = new Timer();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        context = this;
        mediaPlayer = MediaPlayer.create(this, R.raw.songfon);
        mediaPlayerHit = MediaPlayer.create(this, R.raw.hitsong);
        mediaPlayerWash = MediaPlayer.create(this, R.raw.washsong);
        mediaPlayerHappy = MediaPlayer.create(this, R.raw.happysong);
        setContentView(new MyDraw(this, 0));
        //setContentView(R.layout.shopskin);
    }
    @Override
    protected void onResume() {
        super.onResume();
        mediaPlayer = MediaPlayer.create(this, R.raw.songfon);
        mediaPlayerHit = MediaPlayer.create(this, R.raw.hitsong);
        mediaPlayerWash = MediaPlayer.create(this, R.raw.washsong);
        mediaPlayerHappy = MediaPlayer.create(this, R.raw.happysong);
        timePassedsp = getApplicationContext().getSharedPreferences("TIMEPASSED", MODE_PRIVATE);
        editor = timePassedsp.edit();
        currentDate = new Date();
        timeText = timeFormat.format(currentDate);
        editor = timePassedsp.edit();
        timePassed = ((Integer.parseInt(timeText.charAt(0) + "")*10 + Integer.parseInt(timeText.charAt(1) + ""))*86400)+((Integer.parseInt(timeText.charAt(3) + ""))*10+(Integer.parseInt(timeText.charAt(4) + "")))*3600+((Integer.parseInt(timeText.charAt(6)+ ""))*10+
                Integer.parseInt(timeText.charAt(7) + ""))*60+((Integer.parseInt(timeText.charAt(9)+""))*10+Integer.parseInt(timeText.charAt(10)+""));
        editor.putInt("timePassed1",timePassed);
        editor.apply();
        mediaPlayer.start();
        setContentView(new MyDraw(context, timePassedsp.getInt("timePassed1",0) - timePassedsp.getInt("timePassed2",timePassed)));
    }
   @Override
   protected void onPause() {
       super.onPause();
       saveTimePassed();
       mediaPlayer.stop();
       //mediaPlayerHappy.stop();
       //mediaPlayerHappy.release();
   }

    @Override
    protected void onStop() {
        super.onStop();
        timer.start();
    }
    private void releaseMediaPlayerHappy() {
        try {
                mediaPlayerHappy.stop();
                mediaPlayerHappy.prepare();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void releaseMediaPlayerWash() {
        try {
            if (mediaPlayerWash != null) {
                if (mediaPlayerWash.isPlaying())
                    mediaPlayerWash.stop();
                mediaPlayerWash.release();
                mediaPlayerWash = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void releaseMediaPlayerHit() {
        try {
            if (mediaPlayerHit != null) {
                if (mediaPlayerHit.isPlaying())
                    mediaPlayerHit.stop();
                mediaPlayerHit.release();
                mediaPlayerHit = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void saveTimePassed() {
        currentDate = new Date();
        timeText = timeFormat.format(currentDate);
        editor = timePassedsp.edit();
        timePassed = ((Integer.parseInt(timeText.charAt(0) + "")*10 + Integer.parseInt(timeText.charAt(1) + ""))*86400)+((Integer.parseInt(timeText.charAt(3) + ""))*10+(Integer.parseInt(timeText.charAt(4) + "")))*3600+((Integer.parseInt(timeText.charAt(6)+ ""))*10+
                Integer.parseInt(timeText.charAt(7) + ""))*60+((Integer.parseInt(timeText.charAt(9)+""))*10+Integer.parseInt(timeText.charAt(10)+""));
        editor.putInt("timePassed2",timePassed);
        editor.apply();
    }
}