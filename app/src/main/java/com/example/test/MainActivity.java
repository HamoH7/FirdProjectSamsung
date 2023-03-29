package com.example.test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

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
import android.view.Window;
import android.view.WindowManager;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private int timePassed = 0, foin, skinId;
    private Date currentDate;
    private Context context;
    private SharedPreferences timePassedsp;
    private SharedPreferences.Editor editor;
    private final DateFormat timeFormat = new SimpleDateFormat("DD:HH:mm:ss",Locale.getDefault());
    private String timeText;
    private MediaPlayer mediaPlayer, mediaPlayerWash, mediaPlayerHit, mediaPlayerHappy;
    private String[] notificationStrings = {"Your fird is dirty :(", "Your fird is hungry :(", "Your fird is tiered :(", "Your fird is sad :("};
    private Bundle arguments;
    class Timer extends CountDownTimer {
        public Timer() {
            super(1000000, 1000);
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
    public DrawThread drawThread;
    public MyDraw myDraw;
    Timer timer = new Timer();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        arguments = getIntent().getExtras();
        if(arguments != null) foin = Integer.parseInt(arguments.get("foin").toString());
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        timePassedsp = getApplicationContext().getSharedPreferences("TIMEPASSED", MODE_PRIVATE);
        editor = timePassedsp.edit();
        foin = timePassedsp.getInt("FOIN",9000);
        context = this;
        mediaPlayer = MediaPlayer.create(this, R.raw.songfon);
        mediaPlayerHit = MediaPlayer.create(this, R.raw.hitsong);
        mediaPlayerWash = MediaPlayer.create(this, R.raw.washsong);
        mediaPlayerHappy = MediaPlayer.create(this, R.raw.happysong);
        myDraw = new MyDraw(this,0, foin,skinId);
        setContentView(myDraw);
        //setContentView(R.layout.shopskin);
    }

    @Override
    protected void onStart() {
        super.onStart();
        arguments = getIntent().getExtras();
        if(arguments != null) {
            foin = Integer.parseInt(arguments.get("foin").toString());
            skinId = Integer.parseInt(arguments.get("skinId").toString());
        }
        editor.putInt("FOIN",foin);
        editor.apply();
    }

    @Override
    protected void onResume() {
        super.onResume();
        arguments = getIntent().getExtras();
        if(arguments != null) foin = Integer.parseInt(arguments.get("foin").toString());
        mediaPlayer = MediaPlayer.create(this, R.raw.songfon);
        mediaPlayerHit = MediaPlayer.create(this, R.raw.hitsong);
        mediaPlayerWash = MediaPlayer.create(this, R.raw.washsong);
        mediaPlayerHappy = MediaPlayer.create(this, R.raw.happysong);
        timePassedsp = getApplicationContext().getSharedPreferences("TIMEPASSED", MODE_PRIVATE);
        editor = timePassedsp.edit();
        foin = timePassedsp.getInt("FOIN",0);
        currentDate = new Date();
        timeText = timeFormat.format(currentDate);
        editor = timePassedsp.edit();
        timePassed = ((Integer.parseInt(timeText.charAt(0) + "")*10 + Integer.parseInt(timeText.charAt(1) + ""))*86400)+((Integer.parseInt(timeText.charAt(3) + ""))*10+(Integer.parseInt(timeText.charAt(4) + "")))*3600+((Integer.parseInt(timeText.charAt(6)+ ""))*10+
                Integer.parseInt(timeText.charAt(7) + ""))*60+((Integer.parseInt(timeText.charAt(9)+""))*10+Integer.parseInt(timeText.charAt(10)+""));
        editor.putInt("timePassed1",timePassed);
        editor.apply();
        mediaPlayer.start();
        myDraw = new MyDraw(this,timePassedsp.getInt("timePassed1",0) - timePassedsp.getInt("timePassed2",timePassed), foin,skinId);
        setContentView(myDraw);
        drawThread = myDraw.getDrawThread();
    }
   @Override
   protected void onPause() {
       super.onPause();
       saveTimePassed();
       mediaPlayer.stop();
       drawThread = myDraw.getDrawThread();
       drawThread.stopMediaPlayer();
       //mediaPlayerHappy.stop();
       //mediaPlayerHappy.release();
   }

    @Override
    protected void onStop() {
        super.onStop();
        timer.start();
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