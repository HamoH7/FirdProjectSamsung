package com.example.test;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;

import java.io.IOException;
import java.util.Timer;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MyDraw extends SurfaceView implements SurfaceHolder.Callback{
    private final int timepassed, foin, skinId;
    private final String  timeText;
    public MyDraw(Context context, int timepassed, int foin, int skinId, String timeText) {
        super(context);
        this.foin = foin;
        this.timeText = timeText;
        this.timepassed = timepassed;
        this.skinId = skinId;
        getHolder().addCallback(this);
    }
    public DrawThread drawThread;
    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        drawThread = new DrawThread(getContext(), getHolder(),  timepassed, foin,skinId,timeText);
        drawThread.start();
    }
    public DrawThread getDrawThread(){
        return drawThread;
    }
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        drawThread.setTouch((int)event.getX(),(int)event.getY());
        return false;
    }
    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

    }
    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
        drawThread.requestStop();
        boolean retry = true;
        while (retry) {
            try {
                drawThread.join();
                retry = false;
            } catch (InterruptedException e) {
                //
            }
        }
    }
}
