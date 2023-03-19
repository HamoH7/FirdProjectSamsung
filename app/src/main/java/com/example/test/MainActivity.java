package com.example.test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
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
    private SharedPreferences timePassedsp;
    private SharedPreferences.Editor editor;
    private DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss",Locale.getDefault());
    private String timeText;
    private MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        currentDate = new Date();
        timeText = timeFormat.format(currentDate);
        char a = timeText.charAt(0);
        timePassed = (Integer.parseInt(timeText.charAt(0) + "")*10+(Integer.parseInt(timeText.charAt(1) + ""))*3600)+(((Integer.parseInt(timeText.charAt(3)+ ""))*10+
                (Integer.parseInt(timeText.charAt(4) + ""))*60)+((Integer.parseInt(timeText.charAt(6)+""))*10+(Integer.parseInt(timeText.charAt(7)+""))));
        timePassedsp = getApplicationContext().getSharedPreferences("TIMEPASSED", MODE_PRIVATE);
        editor = timePassedsp.edit();
        mediaPlayer = MediaPlayer.create(this, R.raw.songfon);
        mediaPlayer.start();
        setContentView(new MyDraw(this, timePassed - timePassedsp.getInt("TIMEPASSED",timePassed)));
        //setContentView(R.layout.shopskin);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        currentDate = new Date();
        editor = timePassedsp.edit();
        timeText = timeFormat.format(currentDate);
        timePassed = (Integer.parseInt(timeText.charAt(0) + "")*10+(Integer.parseInt(timeText.charAt(1) + ""))*3600)+(((Integer.parseInt(timeText.charAt(3)+ ""))*10+
                (Integer.parseInt(timeText.charAt(4) + ""))*60)+((Integer.parseInt(timeText.charAt(6)+""))*10+(Integer.parseInt(timeText.charAt(7)+""))));
        editor.putInt("TIMEPASSED",timePassed);
        editor.apply();
        mediaPlayer.stop();
    }
    @Override
    protected void onStop() {
        super.onStop();
        currentDate = new Date();
        editor = timePassedsp.edit();
        timeText = timeFormat.format(currentDate);
        timePassed = (Integer.parseInt(timeText.charAt(0) + "")*10+(Integer.parseInt(timeText.charAt(1) + ""))*3600)+(((Integer.parseInt(timeText.charAt(3)+ ""))*10+
                (Integer.parseInt(timeText.charAt(4) + ""))*60)+((Integer.parseInt(timeText.charAt(6)+""))*10+(Integer.parseInt(timeText.charAt(7)+""))));
        editor.putInt("TIMEPASSED",timePassed);
        editor.apply();
        mediaPlayer.stop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mediaPlayer = MediaPlayer.create(this, R.raw.songfon);
        mediaPlayer.start();
    }
}