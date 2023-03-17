package com.example.test;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.View;

import androidx.core.content.res.ResourcesCompat;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class DrawThread extends Thread {
    private  SurfaceHolder surfaceHolder;
    private static  final int REQUEST_CODE = 1;
    private String IMAGE_NAME = "testImage.jpg";
    public MainActivity mainActivity = new MainActivity();
    public MyDraw myDraw;
    public Screenshot screenshot = new Screenshot();
    private Context context;
    private Bitmap bitmapScreen;
    private int foin = 0, level = 1;
    private float lastTouchX = 0;
    private float lastTouchY = 0;
    private float ButtonWidth = (float) 106/1050;
    private float ButtonHeight =(float) 101/540;
    private float birdX = (float) 419/1050;
    private float birdY = (float) 232/540;
    private float foinX = (float) 117/1050;
    private float foinY = (float) 117/540;
    private float birdWidth = (float) 150 / 1050;
    private float birdHeight = (float) 172/540;
    private float screenshotWidth = (float) 73 / 1050;
    private float screenshotHeight = (float) 73/540;
    private float screenshotX = (float) 962/1050;
    private float screenshotY = (float) 461/540;
    private float hungryLeft =(float) 877. / 1050;
    private float hungryTop = (float)  92 / 540;
    private float hungryRight2 = (float) 1040 / 1050;
    private float hungryWeight = hungryRight2 - hungryLeft;
    private float hungryBottom = (float)  116 / 540;
    private float levelLeft = (float) 844 / 1050;
    private float levelTop = (float)  8 / 540;
    private float levelRight2 = (float)  1040 / 1050;
    private float levelBottom = (float)  42 / 540;
    private float dirtLeft = (float) 877 / 1050;
    private float dirtTop = (float)  55 / 540;
    private float dirtRight2 = (float)  1040 / 1050;
    private float dirtWeight = dirtRight2 - dirtLeft;
    private float dirtBottom = (float)  79 / 540;
    private float sleepLeft = (float) 877 / 1050;
    private float sleepTop = (float)  129 / 540;
    private float sleepRight2 = (float) 1040 / 1050;
    private float sleepWeight = sleepRight2 - sleepLeft;
    private float sleepBottom = (float)  153 / 540;
    private float happyLeft = (float) 877 / 1050;
    private float happyTop = (float)  166 / 540;
    private float happyRight2 = (float)  1040 / 1050;
    private float happyWeight = happyRight2 - happyLeft;
    private float happyBottom = (float)  190 / 540;
    private float shopButtonLeft = (float)878/1050;
    private float shopButtonTop = (float) 462/540;
    private int shopButtonWidth = 75/1050;
    private int shopButtonHeight = 75/540;
    private float eatButtonLeft = (float) 19/1050;
    private float eatButtonTop = (float) 427/540;
    private float playButtonLeft = (float)130/1050;
    private float playButtonTop = (float) 427/540;
    private float sleepButtonLeft = (float) 241/1050;
    private float sleepButtonTop = (float) 427/540;
    private float dirtButtonLeft = (float)352/1050;
    private float dirtButtonTop = (float)427/540;
    private float poopX = (float)  450 / 1050;
    private float poopY = (float)  309 / 540;
    private float poopWidth = (float)  94 / 1050;
    private float poopHeight = (float)  94 / 540;
    private int disgust = 1, eatScore = 0, wash = 1,foinTime = 0, hit = 1,poop1=1,poop2=1,poop3=0, m = 0,m1 = 1,m2 = 1, m6 = 0, eat = 1, e = 0, eatTimer = 10, m5 = 0, p = 0, playTimer = 15, sleepTimer = 60, play = 0, sleep = 0, r1 = 0, flyBack = 0;
    private double h = (float)1/1000; // Sovi timeri hamar
    private double s = (float) 1 / 1000; // Uraxutyan timeri hamar
    private double q = (float) 1 / 1000; // Qni timeri hamar
    private double k = (float) 1 / 1; // kextotutyan timeri hamar
    private double food = (float) 1;//food = (float) 1 / 10;
    private double smile = (float) 1;//smile = (float) 1 / 60;
    private double qun = (float) 1 / 1;//qun = (float) 1 / 40;
    private double lvl = (float) 1 / 10;
    private float hungryRight = 0, happyRight = 0, sleepRight = 0, dirtRight = 0, levelRight = 0;
    private int levelColor, dirtColor, hungryColor, tiredColor, happyColor;
    private boolean ea = false, pl = false, sl = false, fl = false, sle = false, hi = false, wa = false, pop = false, po = false;
    private boolean playingTimeIsPassed = false;
    private boolean playingNeedToDrawNow = false;
    private boolean checkPlayButton = false;
    private boolean drawPlayButton = false;
    private boolean playChecker = true;
    private boolean hitting = false;
    private boolean eating = false;
    private boolean playing = false;
    private boolean gettingFoin = false;
    private boolean flying = false;
    private boolean laying = false;
    private boolean sleeping = false;
    private boolean isScreen = false;
    private boolean flyingBack = false;
    private boolean washing = false;
    private boolean pooping = false;
    private boolean disgusting = false;
    private boolean flyPoop = false;
    private boolean flyBackPoop = false;
    private boolean eatChecker = true;
    private boolean sleepChecker = true;
    private boolean checkEatButton = false;
    private boolean drawEatButton = false;
    private boolean checkSleepButton = false;
    private boolean drawSleepButton = false;
    private boolean eatingTimeIsPassed = false;
    private boolean eatingNeedToDrawNow = false;
    private boolean flyingTimeIsPassed = false;
    private boolean flyingNeedToDrawNow = false;
    private boolean poopingTimeIsPassed1 = false;
    private boolean poopingNeedToDrawNow1 = false;
    private boolean poopingTimeIsPassed2 = false;
    private boolean poopingNeedToDrawNow2 = false;
    private boolean poopingTimeIsPassed3 = false;
    private boolean poopingNeedToDrawNow3 = false;
    private boolean disgustingTimeIsPassed = false;
    private boolean disgustingNeedToDrawNow = false;
    private boolean flyingBackTimeIsPassed = false;
    private boolean flyingBackNeedToDrawNow = false;
    private boolean washingTimeIsPassed = false;
    private boolean washingNeedToDrawNow = false;
    private boolean hitTimeIsPassed = false;
    private boolean hitNeedToDrawNow = false;
    private boolean hungryTimeIsPassed = false;
    private boolean hungryNeedToDrawNow = false;
    private boolean happyTimeIsPassed = false;
    private boolean happyNeedToDrawNow = false;
    private boolean sleepTimeIsPassed = false;
    private boolean sleepNeedToDrawNow = false;
    private boolean dirtTimeIsPassed = false;
    private boolean dirtNeedToDrawNow = false;
    private boolean levelTimeIsPassed = false;
    private boolean levelNeedToDrawNow = false;
    private boolean timeIsPassed1 = false;
    private boolean needToDrawNow1 = false;
    private boolean timeIsPassed2 = false;
    private boolean needToDrawNow2 = false;
    private boolean timeIsPassedSleep1 = false;
    private boolean needToDrawNowSleep1 = false;
    private boolean timeIsPassedSleep2 = false;
    private boolean needToDrawNowSleep2 = false;
    private boolean getFoinTimeIsPassed = false;
    private boolean getFoinNeedToDrawNow = false;
    private boolean timeIsPassedSle= false;
    private boolean needToDrawNowSle = false;
    private boolean sleepFinished = false;
    private boolean check = false;
    private boolean checkSleep = false;
    private boolean isTouched = false;
    private boolean statChecker = true;
    private boolean lvlCheck = false;
    private boolean isSinging = false;
    private boolean loaded = false;
    private View view;
    private Bitmap playButtonBitmap,playButtonBitmap2, playButtonBitmapPoop;
    private Bitmap sleepButtonBitmap,sleepButtonBitmap2, sleepButtonBitmapPoop;
    private Bitmap washButtonBitmap, washButtonBitmap2,washButtonBitmap3, washButtonBitmapPoop;
    private Bitmap bitmapbg,bitmapkust,bitmapHeart, bitmapDirt, bitmapHungry,bitmapSleep, bitmapHappy, eatButtonBitmap,eatButtonBitmap2, eatButtonBitmapPoop;
    private Bitmap bitmap, bitmapDTSH1, bitmapDTSH2, bitmapDTS1, bitmapDTS2, bitmapDTH1, bitmapDTH2, bitmapDSH1, bitmapDSH2, bitmapTSH1, bitmapTSH2,
            bitmapDH1, bitmapDH2, bitmapDS1, bitmapDS2, bitmapDT1, bitmapDT2, bitmapSH1, bitmapSH2, bitmapTH1,bitmapTH2,bitmapTS1,bitmapTS2
            ,bitmapD1,bitmapD2,bitmapT1,bitmapT2,bitmapS1,bitmapS2,bitmapH1,bitmapH2,bitmapSmile1,bitmapSmile2,bitmapUsual1,bitmapUsual2, bitmap1, bitmap2;
    private Bitmap bitmapDarkbg,bitmapDarkkust;
    private Bitmap sleepDarkButtonBitmap[] = new Bitmap[61];
    private Bitmap eatDarkButtonBitmap[] = new Bitmap[11];
    private Bitmap playDarkButtonBitmap[] = new Bitmap[16];
    private Bitmap poopBitmap[] = new Bitmap[5];
    private Bitmap washDarkButtonBitmap;
    private Bitmap screenshotBitmap;
    private Bitmap hitBitmap[] = new Bitmap[15], hitBitmapH[] = new Bitmap[15], hitBitmapD[] = new Bitmap[15], hitBitmapDH[] = new Bitmap[15];
    private Bitmap eatBitmap[] = new Bitmap[11],eatBitmapD[] = new Bitmap[11], eatBitmapS[] = new Bitmap[11], eatBitmapT[] = new Bitmap[11], eatBitmapTS[] = new Bitmap[11], eatBitmapDS[] = new Bitmap[11], eatBitmapDT[] = new Bitmap[11], eatBitmapDTS[] = new Bitmap[11], eatBitmapSmile[] = new Bitmap[11];
    private Bitmap playBitmap[] = new Bitmap[21],playBitmapD[] = new Bitmap[21],playBitmapT[] = new Bitmap[21],playBitmapDT[] = new Bitmap[21];
    private Bitmap flyBitmapUsual[] = new Bitmap[5], flyBitmapDH[] = new Bitmap[5],flyBitmapDS[] = new Bitmap[5],flyBitmapD[] = new Bitmap[5], flyBitmapH[] = new Bitmap[5],flyBitmapSmile[] = new Bitmap[5], flyBitmapSDH[] = new Bitmap[5], flyBitmapSH[] = new Bitmap[5], flyBitmapS[] = new Bitmap[5], flyBitmapTDH[] = new Bitmap[5], flyBitmapTDSH[] = new Bitmap[5], flyBitmapTDS[] = new Bitmap[5], flyBitmapTD[] = new Bitmap[5], flyBitmapTH[] = new Bitmap[5], flyBitmapTSH[] = new Bitmap[5], flyBitmapTS[] = new Bitmap[5], flyBitmapT[] = new Bitmap[5];
    private Bitmap washBitmap[] = new Bitmap[50];
    private Bitmap getFoinBitmap[] = new Bitmap[8];
    private Bitmap levelBitmap[] = new Bitmap[51];
    private Bitmap foinBitmap;
    private Bitmap poopingBitmapUsual[] = new Bitmap[11],poopingBitmapDSH[] = new Bitmap[11],poopingBitmapDH[] = new Bitmap[11],poopingBitmapDS[] = new Bitmap[11],poopingBitmapDTH[] = new Bitmap[11],poopingBitmapDTSH[] = new Bitmap[11],poopingBitmapDTS[] = new Bitmap[11],poopingBitmapDT[] = new Bitmap[11],poopingBitmapD[] = new Bitmap[11],poopingBitmapHS[] = new Bitmap[11],poopingBitmapH[] = new Bitmap[11],poopingBitmapTH[] = new Bitmap[11],poopingBitmapS[] = new Bitmap[11],poopingBitmapTHS[] = new Bitmap[11],poopingBitmapTS[] = new Bitmap[11],poopingBitmapT[] = new Bitmap[11];
    private Bitmap sleepUsual1, sleepUsual2, sleepDH1, sleepDH2, sleepDSH1, sleepDSH2, sleepDS1, sleepDS2, sleepD1, sleepD2, sleepH1, sleepH2, sleepSmile1, sleepSmile2, sleepSH1, sleepSH2, sleepS1, sleepS2;
    private Bitmap shopButton;
    private volatile boolean running = true;
    private Class<Activity> activityClass;
    private Paint paint = new Paint();
    private Paint paintLevel = new Paint();
    private Paint paintDirt = new Paint();
    private Paint paintHungry = new Paint();
    private Paint paintSleep = new Paint();
    private Paint paintHappy = new Paint();
    private Paint paintBlack = new Paint();
    private Paint paintFoin = new Paint();
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private int timePassed;
    private float levelRight3;
    private Bitmap LoadingBitmapArray[] = new Bitmap[709];
    private MediaPlayer mediaPlayerHappy, mediaPlayerHit, mediaPlayerWash;
    public DrawThread(Context context, SurfaceHolder surfaceHolder, MyDraw myDraw, int timePassed, Bitmap[] array) {
        this.view = view;
        this.timePassed = timePassed;
        this.activityClass = activityClass;
        this.surfaceHolder = surfaceHolder;
        this.context = context;
        int i = 0;
        for (int j = 0; j < array.length; j++) {
            this.LoadingBitmapArray[j] = array[j];
        };
        mediaPlayerHappy = MediaPlayer.create(context, R.raw.happysong);
        mediaPlayerHit = MediaPlayer.create(context, R.raw.hitsong);
        mediaPlayerWash = MediaPlayer.create(context,R.raw.washsong);
        sharedPreferences = ((Activity)context).getPreferences(Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        foin = sharedPreferences.getInt("FOIN", 0);
        level = sharedPreferences.getInt("LEVEL", 1);
        bitmapUsual1= LoadingBitmapArray[i++];
        bitmapUsual2= LoadingBitmapArray[i++];
        bitmapDTSH1 = LoadingBitmapArray[i++];
        bitmapDTSH2 = LoadingBitmapArray[i++];
        bitmapDTS1 = LoadingBitmapArray[i++];
        bitmapDTS2 = LoadingBitmapArray[i++];
        bitmapDTH1 = LoadingBitmapArray[i++];
        bitmapDTH2 = LoadingBitmapArray[i++];
        bitmapDSH1 = LoadingBitmapArray[i++];
        bitmapDSH2 = LoadingBitmapArray[i++];
        bitmapTSH1 = LoadingBitmapArray[i++];
        bitmapTSH2 = LoadingBitmapArray[i++];
        bitmapDH1= LoadingBitmapArray[i++];
        bitmapDH2= LoadingBitmapArray[i++];
        bitmapDS1= LoadingBitmapArray[i++];
        bitmapDS2= LoadingBitmapArray[i++];
        bitmapDT1= LoadingBitmapArray[i++];
        bitmapDT2= LoadingBitmapArray[i++];
        bitmapSH1= LoadingBitmapArray[i++];
        bitmapSH2= LoadingBitmapArray[i++];
        bitmapTH1= LoadingBitmapArray[i++];
        bitmapTH2= LoadingBitmapArray[i++];
        bitmapTS1= LoadingBitmapArray[i++];
        bitmapTS2= LoadingBitmapArray[i++];
        bitmapD1 = LoadingBitmapArray[i++];
        bitmapD2 = LoadingBitmapArray[i++];
        bitmapT1 = LoadingBitmapArray[i++];
        bitmapT2 = LoadingBitmapArray[i++];
        bitmapS1 = LoadingBitmapArray[i++];
        bitmapS2 = LoadingBitmapArray[i++];
        bitmapH1 = LoadingBitmapArray[i++];
        bitmapH2 = LoadingBitmapArray[i++];
        bitmapSmile1 = LoadingBitmapArray[i++];
        bitmapSmile2 = LoadingBitmapArray[i++];
        eatBitmap[1] = LoadingBitmapArray[i++];
        eatBitmap[2] = LoadingBitmapArray[i++];
        eatBitmap[3] = LoadingBitmapArray[i++];
        eatBitmap[4] = LoadingBitmapArray[i++];
        eatBitmap[5] = LoadingBitmapArray[i++];
        eatBitmap[6] = LoadingBitmapArray[i++];
        eatBitmap[7] = LoadingBitmapArray[i++];
        eatBitmap[8] = LoadingBitmapArray[i++];
        eatBitmap[9] = LoadingBitmapArray[i++];
        eatBitmap[10]  = LoadingBitmapArray[i++];
        playBitmap[1]  = LoadingBitmapArray[i++];
        playBitmap[2]  = LoadingBitmapArray[i++];
        playBitmap[3]  = LoadingBitmapArray[i++];
        playBitmap[4] = LoadingBitmapArray[i++];
        playBitmap[5] = LoadingBitmapArray[i++];
        playBitmap[6] = LoadingBitmapArray[i++];
        playBitmap[7] = LoadingBitmapArray[i++];
        playBitmap[8] = LoadingBitmapArray[i++];
        playBitmap[9] = LoadingBitmapArray[i++];
        playBitmap[10] = LoadingBitmapArray[i++];
        playBitmap[11] = LoadingBitmapArray[i++];
        playBitmap[12] = LoadingBitmapArray[i++];
        playBitmap[13] = LoadingBitmapArray[i++];
        playBitmap[14] = LoadingBitmapArray[i++];
        playBitmap[15] = LoadingBitmapArray[i++];
        playBitmap[16] = LoadingBitmapArray[i++];
        playBitmap[17] = LoadingBitmapArray[i++];
        playBitmap[18] = LoadingBitmapArray[i++];
        playBitmap[19] = LoadingBitmapArray[i++];
        playBitmap[20] = LoadingBitmapArray[i++];
        playBitmapD[1] = LoadingBitmapArray[i++];
        playBitmapD[2] = LoadingBitmapArray[i++];
        playBitmapD[3] = LoadingBitmapArray[i++];
        playBitmapD[4] = LoadingBitmapArray[i++];
        playBitmapD[5] = LoadingBitmapArray[i++];
        playBitmapD[6] = LoadingBitmapArray[i++];
        playBitmapD[7] = LoadingBitmapArray[i++];
        playBitmapD[8] = LoadingBitmapArray[i++];
        playBitmapD[9] = LoadingBitmapArray[i++];
        playBitmapD[10] = LoadingBitmapArray[i++];
        playBitmapD[11] = LoadingBitmapArray[i++];
        playBitmapD[12] = LoadingBitmapArray[i++];
        playBitmapD[13] = LoadingBitmapArray[i++];
        playBitmapD[14] = LoadingBitmapArray[i++];
        playBitmapD[15] = LoadingBitmapArray[i++];
        playBitmapD[16] = LoadingBitmapArray[i++];
        playBitmapD[17] = LoadingBitmapArray[i++];
        playBitmapD[18] = LoadingBitmapArray[i++];
        playBitmapD[19] = LoadingBitmapArray[i++];
        playBitmapD[20] = LoadingBitmapArray[i++];
        playBitmapT[1] = LoadingBitmapArray[i++];
        playBitmapT[2] = LoadingBitmapArray[i++];
        playBitmapT[3] = LoadingBitmapArray[i++];
        playBitmapT[4] = LoadingBitmapArray[i++];
        playBitmapT[5] = LoadingBitmapArray[i++];
        playBitmapT[6] = LoadingBitmapArray[i++];
        playBitmapT[7] = LoadingBitmapArray[i++];
        playBitmapT[8] = LoadingBitmapArray[i++];
        playBitmapT[9] = LoadingBitmapArray[i++];
        playBitmapT[10] = LoadingBitmapArray[i++];
        playBitmapT[11] = LoadingBitmapArray[i++];
        playBitmapT[12] = LoadingBitmapArray[i++];
        playBitmapT[13] = LoadingBitmapArray[i++];
        playBitmapT[14] = LoadingBitmapArray[i++];
        playBitmapT[15] = LoadingBitmapArray[i++];
        playBitmapT[16] = LoadingBitmapArray[i++];
        playBitmapT[17] = LoadingBitmapArray[i++];
        playBitmapT[18] = LoadingBitmapArray[i++];
        playBitmapT[19] = LoadingBitmapArray[i++];
        playBitmapT[20] = LoadingBitmapArray[i++];
        playBitmapDT[1] = LoadingBitmapArray[i++];
        playBitmapDT[2] = LoadingBitmapArray[i++];
        playBitmapDT[3] = LoadingBitmapArray[i++];
        playBitmapDT[4] = LoadingBitmapArray[i++];
        playBitmapDT[5] = LoadingBitmapArray[i++];
        playBitmapDT[6] = LoadingBitmapArray[i++];
        playBitmapDT[7] = LoadingBitmapArray[i++];
        playBitmapDT[8] = LoadingBitmapArray[i++];
        playBitmapDT[9] = LoadingBitmapArray[i++];
        playBitmapDT[10] = LoadingBitmapArray[i++];
        playBitmapDT[11] = LoadingBitmapArray[i++];
        playBitmapDT[12] = LoadingBitmapArray[i++];
        playBitmapDT[13] = LoadingBitmapArray[i++];
        playBitmapDT[14] = LoadingBitmapArray[i++];
        playBitmapDT[15] = LoadingBitmapArray[i++];
        playBitmapDT[16] = LoadingBitmapArray[i++];
        playBitmapDT[17] = LoadingBitmapArray[i++];
        playBitmapDT[18] = LoadingBitmapArray[i++];
        playBitmapDT[19] = LoadingBitmapArray[i++];
        playBitmapDT[20] = LoadingBitmapArray[i++];
        flyBitmapUsual[1] = LoadingBitmapArray[i++];
        flyBitmapUsual[2] = LoadingBitmapArray[i++];
        flyBitmapUsual[3] = LoadingBitmapArray[i++];
        flyBitmapUsual[4] = LoadingBitmapArray[i++];
        flyBitmapDH[1] = LoadingBitmapArray[i++];
        flyBitmapDH[2] = LoadingBitmapArray[i++];
        flyBitmapDH[3] = LoadingBitmapArray[i++];
        flyBitmapDH[4] = LoadingBitmapArray[i++];
        flyBitmapD[1] = LoadingBitmapArray[i++];
        flyBitmapD[2] = LoadingBitmapArray[i++];
        flyBitmapD[3] = LoadingBitmapArray[i++];
        flyBitmapD[4] = LoadingBitmapArray[i++];
        flyBitmapDS[1] = LoadingBitmapArray[i++];
        flyBitmapDS[2] = LoadingBitmapArray[i++];
        flyBitmapDS[3] = LoadingBitmapArray[i++];
        flyBitmapDS[4] = LoadingBitmapArray[i++];
        flyBitmapH[1] = LoadingBitmapArray[i++];
        flyBitmapH[2] = LoadingBitmapArray[i++];
        flyBitmapH[3] = LoadingBitmapArray[i++];
        flyBitmapH[4] = LoadingBitmapArray[i++];
        flyBitmapSmile[1] = LoadingBitmapArray[i++];
        flyBitmapSmile[2] = LoadingBitmapArray[i++];
        flyBitmapSmile[3] = LoadingBitmapArray[i++];
        flyBitmapSmile[4] = LoadingBitmapArray[i++];
        flyBitmapSDH[1] = LoadingBitmapArray[i++];
        flyBitmapSDH[2] = LoadingBitmapArray[i++];
        flyBitmapSDH[3] = LoadingBitmapArray[i++];
        flyBitmapSDH[4] = LoadingBitmapArray[i++];
        flyBitmapSH[1] = LoadingBitmapArray[i++];
        flyBitmapSH[2] = LoadingBitmapArray[i++];
        flyBitmapSH[3] = LoadingBitmapArray[i++];
        flyBitmapSH[4] = LoadingBitmapArray[i++];
        flyBitmapS[1] = LoadingBitmapArray[i++];
        flyBitmapS[2] = LoadingBitmapArray[i++];
        flyBitmapS[3] = LoadingBitmapArray[i++];
        flyBitmapS[4] = LoadingBitmapArray[i++];
        flyBitmapTDH[1] = LoadingBitmapArray[i++];
        flyBitmapTDH[2] = LoadingBitmapArray[i++];
        flyBitmapTDH[3] = LoadingBitmapArray[i++];
        flyBitmapTDH[4] = LoadingBitmapArray[i++];
        flyBitmapTDSH[1] = LoadingBitmapArray[i++];
        flyBitmapTDSH[2] = LoadingBitmapArray[i++];
        flyBitmapTDSH[3] = LoadingBitmapArray[i++];
        flyBitmapTDSH[4] = LoadingBitmapArray[i++];
        flyBitmapTDS[1] = LoadingBitmapArray[i++];
        flyBitmapTDS[2] = LoadingBitmapArray[i++];
        flyBitmapTDS[3] = LoadingBitmapArray[i++];
        flyBitmapTDS[4] = LoadingBitmapArray[i++];
        flyBitmapTD[1] = LoadingBitmapArray[i++];
        flyBitmapTD[2] = LoadingBitmapArray[i++];
        flyBitmapTD[3] = LoadingBitmapArray[i++];
        flyBitmapTD[4] = LoadingBitmapArray[i++];
        flyBitmapTH[1] = LoadingBitmapArray[i++];
        flyBitmapTH[2] = LoadingBitmapArray[i++];
        flyBitmapTH[3] = LoadingBitmapArray[i++];
        flyBitmapTH[4] = LoadingBitmapArray[i++];
        flyBitmapTSH[1] = LoadingBitmapArray[i++];
        flyBitmapTSH[2] = LoadingBitmapArray[i++];
        flyBitmapTSH[3] = LoadingBitmapArray[i++];
        flyBitmapTSH[4] = LoadingBitmapArray[i++];
        flyBitmapTS[1] = LoadingBitmapArray[i++];
        flyBitmapTS[2] = LoadingBitmapArray[i++];
        flyBitmapTS[3] = LoadingBitmapArray[i++];
        flyBitmapTS[4] = LoadingBitmapArray[i++];
        flyBitmapT[1] = LoadingBitmapArray[i++];
        flyBitmapT[2] = LoadingBitmapArray[i++];
        flyBitmapT[3] = LoadingBitmapArray[i++];
        flyBitmapT[4] = LoadingBitmapArray[i++];
        hitBitmap[1] = LoadingBitmapArray[i++];
        hitBitmap[2] = LoadingBitmapArray[i++];
        hitBitmap[3] = LoadingBitmapArray[i++];
        hitBitmap[4] = LoadingBitmapArray[i++];
        hitBitmap[5] = LoadingBitmapArray[i++];
        hitBitmap[6] = LoadingBitmapArray[i++];
        hitBitmap[7] = LoadingBitmapArray[i++];
        hitBitmap[8] = LoadingBitmapArray[i++];
        hitBitmap[9] = LoadingBitmapArray[i++];
        hitBitmap[10] = LoadingBitmapArray[i++];
        hitBitmap[11] = LoadingBitmapArray[i++];
        hitBitmap[12] = LoadingBitmapArray[i++];
        hitBitmap[13] = LoadingBitmapArray[i++];
        hitBitmap[14] = LoadingBitmapArray[i++];
        hitBitmapD[1] = LoadingBitmapArray[i++];
        hitBitmapD[2] = LoadingBitmapArray[i++];
        hitBitmapD[3] = LoadingBitmapArray[i++];
        hitBitmapD[4] = LoadingBitmapArray[i++];
        hitBitmapD[5] = LoadingBitmapArray[i++];
        hitBitmapD[6] = LoadingBitmapArray[i++];
        hitBitmapD[7] = LoadingBitmapArray[i++];
        hitBitmapD[8] = LoadingBitmapArray[i++];
        hitBitmapD[9] = LoadingBitmapArray[i++];
        hitBitmapD[10] = LoadingBitmapArray[i++];
        hitBitmapD[11] = LoadingBitmapArray[i++];
        hitBitmapD[12] = LoadingBitmapArray[i++];
        hitBitmapD[13] = LoadingBitmapArray[i++];
        hitBitmapD[14] = LoadingBitmapArray[i++];
        hitBitmapH[1] = LoadingBitmapArray[i++];
        hitBitmapH[2] = LoadingBitmapArray[i++];
        hitBitmapH[3] = LoadingBitmapArray[i++];
        hitBitmapH[4] = LoadingBitmapArray[i++];
        hitBitmapH[5] = LoadingBitmapArray[i++];
        hitBitmapH[6] = LoadingBitmapArray[i++];
        hitBitmapH[7] = LoadingBitmapArray[i++];
        hitBitmapH[8] = LoadingBitmapArray[i++];
        hitBitmapH[9] = LoadingBitmapArray[i++];
        hitBitmapH[10] = LoadingBitmapArray[i++];
        hitBitmapH[11] = LoadingBitmapArray[i++];
        hitBitmapH[12] = LoadingBitmapArray[i++];
        hitBitmapH[13] = LoadingBitmapArray[i++];
        hitBitmapH[14] = LoadingBitmapArray[i++];
        hitBitmapDH[1] = LoadingBitmapArray[i++];
        hitBitmapDH[2] = LoadingBitmapArray[i++];
        hitBitmapDH[3] = LoadingBitmapArray[i++];
        hitBitmapDH[4] = LoadingBitmapArray[i++];
        hitBitmapDH[5] = LoadingBitmapArray[i++];
        hitBitmapDH[6] = LoadingBitmapArray[i++];
        hitBitmapDH[7] = LoadingBitmapArray[i++];
        hitBitmapDH[8] = LoadingBitmapArray[i++];
        hitBitmapDH[9] = LoadingBitmapArray[i++];
        hitBitmapDH[10] = LoadingBitmapArray[i++];
        hitBitmapDH[11] = LoadingBitmapArray[i++];
        hitBitmapDH[12] = LoadingBitmapArray[i++];
        hitBitmapDH[13] = LoadingBitmapArray[i++];
        hitBitmapDH[14] = LoadingBitmapArray[i++];
        washBitmap[1] = LoadingBitmapArray[i++];
        washBitmap[2] = LoadingBitmapArray[i++];
        washBitmap[3] = LoadingBitmapArray[i++];
        washBitmap[4] = LoadingBitmapArray[i++];
        washBitmap[5] = LoadingBitmapArray[i++];
        washBitmap[6] = LoadingBitmapArray[i++];
        washBitmap[7] = LoadingBitmapArray[i++];
        washBitmap[8] = LoadingBitmapArray[i++];
        washBitmap[9] = LoadingBitmapArray[i++];
        washBitmap[10] = LoadingBitmapArray[i++];
        washBitmap[11] = LoadingBitmapArray[i++];
        washBitmap[12] = LoadingBitmapArray[i++];
        washBitmap[13] = LoadingBitmapArray[i++];
        washBitmap[14] = LoadingBitmapArray[i++];
        washBitmap[15] = LoadingBitmapArray[i++];
        washBitmap[16] = LoadingBitmapArray[i++];
        washBitmap[17] = LoadingBitmapArray[i++];
        washBitmap[18] = LoadingBitmapArray[i++];
        washBitmap[19] = LoadingBitmapArray[i++];
        washBitmap[20] = LoadingBitmapArray[i++];
        washBitmap[21] = LoadingBitmapArray[i++];
        washBitmap[22] = LoadingBitmapArray[i++];
        washBitmap[23] = LoadingBitmapArray[i++];
        washBitmap[24] = LoadingBitmapArray[i++];
        washBitmap[25] = LoadingBitmapArray[i++];
        washBitmap[26] = LoadingBitmapArray[i++];
        washBitmap[27] = LoadingBitmapArray[i++];
        washBitmap[28] = LoadingBitmapArray[i++];
        washBitmap[29] = LoadingBitmapArray[i++];
        washBitmap[30] = LoadingBitmapArray[i++];
        washBitmap[31] = LoadingBitmapArray[i++];
        washBitmap[32] = LoadingBitmapArray[i++];
        washBitmap[33] = LoadingBitmapArray[i++];
        washBitmap[34] = LoadingBitmapArray[i++];
        washBitmap[35] = LoadingBitmapArray[i++];
        washBitmap[36] = LoadingBitmapArray[i++];
        washBitmap[37] = LoadingBitmapArray[i++];
        washBitmap[38] = LoadingBitmapArray[i++];
        washBitmap[39] = LoadingBitmapArray[i++];
        washBitmap[40] = LoadingBitmapArray[i++];
        washBitmap[41] = LoadingBitmapArray[i++];
        washBitmap[42] = LoadingBitmapArray[i++];
        washBitmap[43] = LoadingBitmapArray[i++];
        washBitmap[44] = LoadingBitmapArray[i++];
        washBitmap[45] = LoadingBitmapArray[i++];
        washBitmap[46] = LoadingBitmapArray[i++];
        washBitmap[47] = LoadingBitmapArray[i++];
        washBitmap[48] = LoadingBitmapArray[i++];
        sleepUsual1 = LoadingBitmapArray[i++];
        sleepUsual2 = LoadingBitmapArray[i++];
        sleepDH1 = LoadingBitmapArray[i++];
        sleepDH2 = LoadingBitmapArray[i++];
        sleepDSH1 = LoadingBitmapArray[i++];
        sleepDSH2 = LoadingBitmapArray[i++];
        sleepDS1 = LoadingBitmapArray[i++];
        sleepDS2 = LoadingBitmapArray[i++];
        sleepDH1 = LoadingBitmapArray[i++];
        sleepDH2 = LoadingBitmapArray[i++];
        sleepD1 = LoadingBitmapArray[i++];
        sleepD2 = LoadingBitmapArray[i++];
        sleepH1 = LoadingBitmapArray[i++];
        sleepH2 = LoadingBitmapArray[i++];
        sleepSmile1 = LoadingBitmapArray[i++];
        sleepSmile2 = LoadingBitmapArray[i++];
        sleepSH1 = LoadingBitmapArray[i++];
        sleepSH2 = LoadingBitmapArray[i++];
        sleepS1 = LoadingBitmapArray[i++];
        sleepS2 = LoadingBitmapArray[i++];
        eatBitmapD[1] = LoadingBitmapArray[i++];
        eatBitmapD[2] = LoadingBitmapArray[i++];
        eatBitmapD[3] = LoadingBitmapArray[i++];
        eatBitmapD[4] = LoadingBitmapArray[i++];
        eatBitmapD[5] = LoadingBitmapArray[i++];
        eatBitmapD[6] = LoadingBitmapArray[i++];
        eatBitmapD[7] = LoadingBitmapArray[i++];
        eatBitmapD[8] = LoadingBitmapArray[i++];
        eatBitmapD[9] = LoadingBitmapArray[i++];
        eatBitmapD[10] = LoadingBitmapArray[i++];
        eatBitmapDS[1] = LoadingBitmapArray[i++];
        eatBitmapDS[2] = LoadingBitmapArray[i++];
        eatBitmapDS[3] = LoadingBitmapArray[i++];
        eatBitmapDS[4] = LoadingBitmapArray[i++];
        eatBitmapDS[5] = LoadingBitmapArray[i++];
        eatBitmapDS[6] = LoadingBitmapArray[i++];
        eatBitmapDS[7] = LoadingBitmapArray[i++];
        eatBitmapDS[8] = LoadingBitmapArray[i++];
        eatBitmapDS[9] = LoadingBitmapArray[i++];
        eatBitmapDS[10] = LoadingBitmapArray[i++];
        eatBitmapDT[1] = LoadingBitmapArray[i++];
        eatBitmapDT[2] = LoadingBitmapArray[i++];
        eatBitmapDT[3] = LoadingBitmapArray[i++];
        eatBitmapDT[4] = LoadingBitmapArray[i++];
        eatBitmapDT[5] = LoadingBitmapArray[i++];
        eatBitmapDT[6] = LoadingBitmapArray[i++];
        eatBitmapDT[7] = LoadingBitmapArray[i++];
        eatBitmapDT[8] = LoadingBitmapArray[i++];
        eatBitmapDT[9] = LoadingBitmapArray[i++];
        eatBitmapDT[10] = LoadingBitmapArray[i++];
        eatBitmapDTS[1] = LoadingBitmapArray[i++];
        eatBitmapDTS[2] = LoadingBitmapArray[i++];
        eatBitmapDTS[3] = LoadingBitmapArray[i++];
        eatBitmapDTS[4] = LoadingBitmapArray[i++];
        eatBitmapDTS[5] = LoadingBitmapArray[i++];
        eatBitmapDTS[6] = LoadingBitmapArray[i++];
        eatBitmapDTS[7] = LoadingBitmapArray[i++];
        eatBitmapDTS[8] = LoadingBitmapArray[i++];
        eatBitmapDTS[9] = LoadingBitmapArray[i++];
        eatBitmapDTS[10] = LoadingBitmapArray[i++];
        eatBitmapS[1] = LoadingBitmapArray[i++];
        eatBitmapS[2] = LoadingBitmapArray[i++];
        eatBitmapS[3] = LoadingBitmapArray[i++];
        eatBitmapS[4] = LoadingBitmapArray[i++];
        eatBitmapS[5] = LoadingBitmapArray[i++];
        eatBitmapS[6] = LoadingBitmapArray[i++];
        eatBitmapS[7] = LoadingBitmapArray[i++];
        eatBitmapS[8] = LoadingBitmapArray[i++];
        eatBitmapS[9] = LoadingBitmapArray[i++];
        eatBitmapS[10] = LoadingBitmapArray[i++];
        eatBitmapT[1] = LoadingBitmapArray[i++];
        eatBitmapT[2] = LoadingBitmapArray[i++];
        eatBitmapT[3] = LoadingBitmapArray[i++];
        eatBitmapT[4] = LoadingBitmapArray[i++];
        eatBitmapT[5] = LoadingBitmapArray[i++];
        eatBitmapT[6] = LoadingBitmapArray[i++];
        eatBitmapT[7] = LoadingBitmapArray[i++];
        eatBitmapT[8] = LoadingBitmapArray[i++];
        eatBitmapT[9] = LoadingBitmapArray[i++];
        eatBitmapT[10] = LoadingBitmapArray[i++];
        eatBitmapTS[1] = LoadingBitmapArray[i++];
        eatBitmapTS[2] = LoadingBitmapArray[i++];
        eatBitmapTS[3] = LoadingBitmapArray[i++];
        eatBitmapTS[4] = LoadingBitmapArray[i++];
        eatBitmapTS[5] = LoadingBitmapArray[i++];
        eatBitmapTS[6] = LoadingBitmapArray[i++];
        eatBitmapTS[7] = LoadingBitmapArray[i++];
        eatBitmapTS[8] = LoadingBitmapArray[i++];
        eatBitmapTS[9] = LoadingBitmapArray[i++];
        eatBitmapTS[10] = LoadingBitmapArray[i++];
        eatBitmapSmile[1] = LoadingBitmapArray[i++];
        eatBitmapSmile[2] = LoadingBitmapArray[i++];
        eatBitmapSmile[3] = LoadingBitmapArray[i++];
        eatBitmapSmile[4] = LoadingBitmapArray[i++];
        eatBitmapSmile[5] = LoadingBitmapArray[i++];
        eatBitmapSmile[6] = LoadingBitmapArray[i++];
        eatBitmapSmile[7] = LoadingBitmapArray[i++];
        eatBitmapSmile[8] = LoadingBitmapArray[i++];
        eatBitmapSmile[9] = LoadingBitmapArray[i++];
        eatBitmapSmile[10] = LoadingBitmapArray[i++];
        eatDarkButtonBitmap[0] = LoadingBitmapArray[i++];
        eatDarkButtonBitmap[1] = LoadingBitmapArray[i++];
        eatDarkButtonBitmap[2] = LoadingBitmapArray[i++];
        eatDarkButtonBitmap[3] = LoadingBitmapArray[i++];
        eatDarkButtonBitmap[4] = LoadingBitmapArray[i++];
        eatDarkButtonBitmap[5] = LoadingBitmapArray[i++];
        eatDarkButtonBitmap[6] = LoadingBitmapArray[i++];
        eatDarkButtonBitmap[7] = LoadingBitmapArray[i++];
        eatDarkButtonBitmap[8] = LoadingBitmapArray[i++];
        eatDarkButtonBitmap[9] = LoadingBitmapArray[i++];
        eatDarkButtonBitmap[10] = LoadingBitmapArray[i++];
        playDarkButtonBitmap[0] = LoadingBitmapArray[i++];
        playDarkButtonBitmap[1] = LoadingBitmapArray[i++];
        playDarkButtonBitmap[2] = LoadingBitmapArray[i++];
        playDarkButtonBitmap[3] = LoadingBitmapArray[i++];
        playDarkButtonBitmap[4] = LoadingBitmapArray[i++];
        playDarkButtonBitmap[5] = LoadingBitmapArray[i++];
        playDarkButtonBitmap[6] = LoadingBitmapArray[i++];
        playDarkButtonBitmap[7] = LoadingBitmapArray[i++];
        playDarkButtonBitmap[8] = LoadingBitmapArray[i++];
        playDarkButtonBitmap[9] = LoadingBitmapArray[i++];
        playDarkButtonBitmap[10] = LoadingBitmapArray[i++];
        playDarkButtonBitmap[11] = LoadingBitmapArray[i++];
        playDarkButtonBitmap[12] = LoadingBitmapArray[i++];
        playDarkButtonBitmap[13] = LoadingBitmapArray[i++];
        playDarkButtonBitmap[14] = LoadingBitmapArray[i++];
        playDarkButtonBitmap[15] = LoadingBitmapArray[i++];
        sleepDarkButtonBitmap[0] = LoadingBitmapArray[i++];
        sleepDarkButtonBitmap[1] = LoadingBitmapArray[i++];
        sleepDarkButtonBitmap[2] = LoadingBitmapArray[i++];
        sleepDarkButtonBitmap[3] = LoadingBitmapArray[i++];
        sleepDarkButtonBitmap[4] = LoadingBitmapArray[i++];
        sleepDarkButtonBitmap[5] = LoadingBitmapArray[i++];
        sleepDarkButtonBitmap[6] = LoadingBitmapArray[i++];
        sleepDarkButtonBitmap[7] = LoadingBitmapArray[i++];
        sleepDarkButtonBitmap[8] = LoadingBitmapArray[i++];
        sleepDarkButtonBitmap[9] = LoadingBitmapArray[i++];
        sleepDarkButtonBitmap[10] = LoadingBitmapArray[i++];
        sleepDarkButtonBitmap[11] = LoadingBitmapArray[i++];
        sleepDarkButtonBitmap[12] = LoadingBitmapArray[i++];
        sleepDarkButtonBitmap[13] = LoadingBitmapArray[i++];
        sleepDarkButtonBitmap[14] = LoadingBitmapArray[i++];
        sleepDarkButtonBitmap[15] = LoadingBitmapArray[i++];
        sleepDarkButtonBitmap[16] = LoadingBitmapArray[i++];
        sleepDarkButtonBitmap[17] = LoadingBitmapArray[i++];
        sleepDarkButtonBitmap[18] = LoadingBitmapArray[i++];
        sleepDarkButtonBitmap[19] = LoadingBitmapArray[i++];
        sleepDarkButtonBitmap[20] = LoadingBitmapArray[i++];
        sleepDarkButtonBitmap[21] = LoadingBitmapArray[i++];
        sleepDarkButtonBitmap[22] = LoadingBitmapArray[i++];
        sleepDarkButtonBitmap[23] = LoadingBitmapArray[i++];
        sleepDarkButtonBitmap[24] = LoadingBitmapArray[i++];
        sleepDarkButtonBitmap[25] = LoadingBitmapArray[i++];
        sleepDarkButtonBitmap[26] = LoadingBitmapArray[i++];
        sleepDarkButtonBitmap[27] = LoadingBitmapArray[i++];
        sleepDarkButtonBitmap[28] = LoadingBitmapArray[i++];
        sleepDarkButtonBitmap[29] = LoadingBitmapArray[i++];
        sleepDarkButtonBitmap[30] = LoadingBitmapArray[i++];
        sleepDarkButtonBitmap[31] = LoadingBitmapArray[i++];
        sleepDarkButtonBitmap[32] = LoadingBitmapArray[i++];
        sleepDarkButtonBitmap[33] = LoadingBitmapArray[i++];
        sleepDarkButtonBitmap[34] = LoadingBitmapArray[i++];
        sleepDarkButtonBitmap[35] = LoadingBitmapArray[i++];
        sleepDarkButtonBitmap[36] = LoadingBitmapArray[i++];
        sleepDarkButtonBitmap[37] = LoadingBitmapArray[i++];
        sleepDarkButtonBitmap[38] = LoadingBitmapArray[i++];
        sleepDarkButtonBitmap[39] = LoadingBitmapArray[i++];
        sleepDarkButtonBitmap[40] = LoadingBitmapArray[i++];
        sleepDarkButtonBitmap[41] = LoadingBitmapArray[i++];
        sleepDarkButtonBitmap[42] = LoadingBitmapArray[i++];
        sleepDarkButtonBitmap[43] = LoadingBitmapArray[i++];
        sleepDarkButtonBitmap[44] = LoadingBitmapArray[i++];
        sleepDarkButtonBitmap[45] = LoadingBitmapArray[i++];
        sleepDarkButtonBitmap[46] = LoadingBitmapArray[i++];
        sleepDarkButtonBitmap[47] = LoadingBitmapArray[i++];
        sleepDarkButtonBitmap[48] = LoadingBitmapArray[i++];
        sleepDarkButtonBitmap[49] = LoadingBitmapArray[i++];
        sleepDarkButtonBitmap[50] = LoadingBitmapArray[i++];
        sleepDarkButtonBitmap[51] = LoadingBitmapArray[i++];
        sleepDarkButtonBitmap[52] = LoadingBitmapArray[i++];
        sleepDarkButtonBitmap[53] = LoadingBitmapArray[i++];
        sleepDarkButtonBitmap[54] = LoadingBitmapArray[i++];
        sleepDarkButtonBitmap[55] = LoadingBitmapArray[i++];
        sleepDarkButtonBitmap[56] = LoadingBitmapArray[i++];
        sleepDarkButtonBitmap[57] = LoadingBitmapArray[i++];
        sleepDarkButtonBitmap[58] = LoadingBitmapArray[i++];
        sleepDarkButtonBitmap[59] = LoadingBitmapArray[i++];
        sleepDarkButtonBitmap[60] = LoadingBitmapArray[i++];
        poopBitmap[1] = LoadingBitmapArray[i++];
        poopBitmap[2] = LoadingBitmapArray[i++];
        poopBitmap[3] = LoadingBitmapArray[i++];
        poopBitmap[4] = LoadingBitmapArray[i++];
        poopingBitmapUsual[1] = LoadingBitmapArray[i++];
        poopingBitmapUsual[2] = LoadingBitmapArray[i++];
        poopingBitmapUsual[3] = LoadingBitmapArray[i++];
        poopingBitmapUsual[4] = LoadingBitmapArray[i++];
        poopingBitmapUsual[5] = LoadingBitmapArray[i++];
        poopingBitmapUsual[6] = LoadingBitmapArray[i++];
        poopingBitmapUsual[7] = LoadingBitmapArray[i++];
        poopingBitmapUsual[8] = LoadingBitmapArray[i++];
        poopingBitmapUsual[9] = LoadingBitmapArray[i++];
        poopingBitmapUsual[10] = LoadingBitmapArray[i++];
        poopingBitmapDH[1] = LoadingBitmapArray[i++];
        poopingBitmapDH[2] = LoadingBitmapArray[i++];
        poopingBitmapDH[3] = LoadingBitmapArray[i++];
        poopingBitmapDH[4] = LoadingBitmapArray[i++];
        poopingBitmapDH[5] = LoadingBitmapArray[i++];
        poopingBitmapDH[6] = LoadingBitmapArray[i++];
        poopingBitmapDH[7] = LoadingBitmapArray[i++];
        poopingBitmapDH[8] = LoadingBitmapArray[i++];
        poopingBitmapDH[9] = LoadingBitmapArray[i++];
        poopingBitmapDH[10] = LoadingBitmapArray[i++];
        poopingBitmapDSH[1] = LoadingBitmapArray[i++];
        poopingBitmapDSH[2] = LoadingBitmapArray[i++];
        poopingBitmapDSH[3] = LoadingBitmapArray[i++];
        poopingBitmapDSH[4] = LoadingBitmapArray[i++];
        poopingBitmapDSH[5] = LoadingBitmapArray[i++];
        poopingBitmapDSH[6] = LoadingBitmapArray[i++];
        poopingBitmapDSH[7] = LoadingBitmapArray[i++];
        poopingBitmapDSH[8] = LoadingBitmapArray[i++];
        poopingBitmapDSH[9] = LoadingBitmapArray[i++];
        poopingBitmapDSH[10]= LoadingBitmapArray[i++];
        poopingBitmapDS[1] = LoadingBitmapArray[i++];
        poopingBitmapDS[2] = LoadingBitmapArray[i++];
        poopingBitmapDS[3] = LoadingBitmapArray[i++];
        poopingBitmapDS[4] = LoadingBitmapArray[i++];
        poopingBitmapDS[5] = LoadingBitmapArray[i++];
        poopingBitmapDS[6] = LoadingBitmapArray[i++];
        poopingBitmapDS[7] = LoadingBitmapArray[i++];
        poopingBitmapDS[8] = LoadingBitmapArray[i++];
        poopingBitmapDS[9] = LoadingBitmapArray[i++];
        poopingBitmapDS[10] = LoadingBitmapArray[i++];
        poopingBitmapDTH[1] = LoadingBitmapArray[i++];
        poopingBitmapDTH[2] = LoadingBitmapArray[i++];
        poopingBitmapDTH[3] = LoadingBitmapArray[i++];
        poopingBitmapDTH[4] = LoadingBitmapArray[i++];
        poopingBitmapDTH[5] = LoadingBitmapArray[i++];
        poopingBitmapDTH[6] = LoadingBitmapArray[i++];
        poopingBitmapDTH[7] = LoadingBitmapArray[i++];
        poopingBitmapDTH[8] = LoadingBitmapArray[i++];
        poopingBitmapDTH[9] = LoadingBitmapArray[i++];
        poopingBitmapDTH[10] = LoadingBitmapArray[i++];
        poopingBitmapDTSH[1] = LoadingBitmapArray[i++];
        poopingBitmapDTSH[2] = LoadingBitmapArray[i++];
        poopingBitmapDTSH[3] = LoadingBitmapArray[i++];
        poopingBitmapDTSH[4] = LoadingBitmapArray[i++];
        poopingBitmapDTSH[5] = LoadingBitmapArray[i++];
        poopingBitmapDTSH[6] = LoadingBitmapArray[i++];
        poopingBitmapDTSH[7] = LoadingBitmapArray[i++];
        poopingBitmapDTSH[8] = LoadingBitmapArray[i++];
        poopingBitmapDTSH[9] = LoadingBitmapArray[i++];
        poopingBitmapDTSH[10] = LoadingBitmapArray[i++];
        poopingBitmapDTS[1] = LoadingBitmapArray[i++];
        poopingBitmapDTS[2] = LoadingBitmapArray[i++];
        poopingBitmapDTS[3] = LoadingBitmapArray[i++];
        poopingBitmapDTS[4] = LoadingBitmapArray[i++];
        poopingBitmapDTS[5] = LoadingBitmapArray[i++];
        poopingBitmapDTS[6] = LoadingBitmapArray[i++];
        poopingBitmapDTS[7] = LoadingBitmapArray[i++];
        poopingBitmapDTS[8] = LoadingBitmapArray[i++];
        poopingBitmapDTS[9] = LoadingBitmapArray[i++];
        poopingBitmapDTS[10] = LoadingBitmapArray[i++];
        poopingBitmapDT[1] = LoadingBitmapArray[i++];
        poopingBitmapDT[2] = LoadingBitmapArray[i++];
        poopingBitmapDT[3] = LoadingBitmapArray[i++];
        poopingBitmapDT[4] = LoadingBitmapArray[i++];
        poopingBitmapDT[5] = LoadingBitmapArray[i++];
        poopingBitmapDT[6] = LoadingBitmapArray[i++];
        poopingBitmapDT[7] = LoadingBitmapArray[i++];
        poopingBitmapDT[8] = LoadingBitmapArray[i++];
        poopingBitmapDT[9] = LoadingBitmapArray[i++];
        poopingBitmapDT[10] = LoadingBitmapArray[i++];
        poopingBitmapD[1] = LoadingBitmapArray[i++];
        poopingBitmapD[2] = LoadingBitmapArray[i++];
        poopingBitmapD[3] = LoadingBitmapArray[i++];
        poopingBitmapD[4] = LoadingBitmapArray[i++];
        poopingBitmapD[5] = LoadingBitmapArray[i++];
        poopingBitmapD[6] = LoadingBitmapArray[i++];
        poopingBitmapD[7] = LoadingBitmapArray[i++];
        poopingBitmapD[8] = LoadingBitmapArray[i++];
        poopingBitmapD[9] = LoadingBitmapArray[i++];
        poopingBitmapD[10] = LoadingBitmapArray[i++];
        poopingBitmapHS[1] = LoadingBitmapArray[i++];
        poopingBitmapHS[2] = LoadingBitmapArray[i++];
        poopingBitmapHS[3] = LoadingBitmapArray[i++];
        poopingBitmapHS[4] = LoadingBitmapArray[i++];
        poopingBitmapHS[5] = LoadingBitmapArray[i++];
        poopingBitmapHS[6] = LoadingBitmapArray[i++];
        poopingBitmapHS[7] = LoadingBitmapArray[i++];
        poopingBitmapHS[8] = LoadingBitmapArray[i++];
        poopingBitmapHS[9] = LoadingBitmapArray[i++];
        poopingBitmapHS[10] = LoadingBitmapArray[i++];
        poopingBitmapH[1] = LoadingBitmapArray[i++];
        poopingBitmapH[2] = LoadingBitmapArray[i++];
        poopingBitmapH[3] = LoadingBitmapArray[i++];
        poopingBitmapH[4] = LoadingBitmapArray[i++];
        poopingBitmapH[5] = LoadingBitmapArray[i++];
        poopingBitmapH[6] = LoadingBitmapArray[i++];
        poopingBitmapH[7] = LoadingBitmapArray[i++];
        poopingBitmapH[8] = LoadingBitmapArray[i++];
        poopingBitmapH[9] = LoadingBitmapArray[i++];
        poopingBitmapH[10] = LoadingBitmapArray[i++];
        poopingBitmapS[1] = LoadingBitmapArray[i++];
        poopingBitmapS[2] = LoadingBitmapArray[i++];
        poopingBitmapS[3] = LoadingBitmapArray[i++];
        poopingBitmapS[4] = LoadingBitmapArray[i++];
        poopingBitmapS[5] = LoadingBitmapArray[i++];
        poopingBitmapS[6] = LoadingBitmapArray[i++];
        poopingBitmapS[7] = LoadingBitmapArray[i++];
        poopingBitmapS[8] = LoadingBitmapArray[i++];
        poopingBitmapS[9] = LoadingBitmapArray[i++];
        poopingBitmapS[10] = LoadingBitmapArray[i++];
        poopingBitmapTH[1] = LoadingBitmapArray[i++];
        poopingBitmapTH[2] = LoadingBitmapArray[i++];
        poopingBitmapTH[3] = LoadingBitmapArray[i++];
        poopingBitmapTH[4] = LoadingBitmapArray[i++];
        poopingBitmapTH[5] = LoadingBitmapArray[i++];
        poopingBitmapTH[6] = LoadingBitmapArray[i++];
        poopingBitmapTH[7] = LoadingBitmapArray[i++];
        poopingBitmapTH[8] = LoadingBitmapArray[i++];
        poopingBitmapTH[9] = LoadingBitmapArray[i++];
        poopingBitmapTH[10] = LoadingBitmapArray[i++];
        poopingBitmapTHS[1] = LoadingBitmapArray[i++];
        poopingBitmapTHS[2] = LoadingBitmapArray[i++];
        poopingBitmapTHS[3] = LoadingBitmapArray[i++];
        poopingBitmapTHS[4] = LoadingBitmapArray[i++];
        poopingBitmapTHS[5] = LoadingBitmapArray[i++];
        poopingBitmapTHS[6] = LoadingBitmapArray[i++];
        poopingBitmapTHS[7] = LoadingBitmapArray[i++];
        poopingBitmapTHS[8] = LoadingBitmapArray[i++];
        poopingBitmapTHS[9] = LoadingBitmapArray[i++];
        poopingBitmapTHS[10] = LoadingBitmapArray[i++];
        poopingBitmapTS[1] = LoadingBitmapArray[i++];
        poopingBitmapTS[2] = LoadingBitmapArray[i++];
        poopingBitmapTS[3] = LoadingBitmapArray[i++];
        poopingBitmapTS[4] = LoadingBitmapArray[i++];
        poopingBitmapTS[5] = LoadingBitmapArray[i++];
        poopingBitmapTS[6] = LoadingBitmapArray[i++];
        poopingBitmapTS[7] = LoadingBitmapArray[i++];
        poopingBitmapTS[8] = LoadingBitmapArray[i++];
        poopingBitmapTS[9] = LoadingBitmapArray[i++];
        poopingBitmapTS[10] = LoadingBitmapArray[i++];
        poopingBitmapT[1] = LoadingBitmapArray[i++];
        poopingBitmapT[2] = LoadingBitmapArray[i++];
        poopingBitmapT[3] = LoadingBitmapArray[i++];
        poopingBitmapT[4] = LoadingBitmapArray[i++];
        poopingBitmapT[5] = LoadingBitmapArray[i++];
        poopingBitmapT[6] = LoadingBitmapArray[i++];
        poopingBitmapT[7] = LoadingBitmapArray[i++];
        poopingBitmapT[8] = LoadingBitmapArray[i++];
        poopingBitmapT[9] = LoadingBitmapArray[i++];
        poopingBitmapT[10] = LoadingBitmapArray[i++];
        getFoinBitmap[0] = LoadingBitmapArray[i++];
        foinBitmap = LoadingBitmapArray[i++];
        getFoinBitmap[1] = LoadingBitmapArray[i++];
        getFoinBitmap[2] = LoadingBitmapArray[i++];
        getFoinBitmap[3] = LoadingBitmapArray[i++];
        getFoinBitmap[4] = LoadingBitmapArray[i++];
        getFoinBitmap[5] = LoadingBitmapArray[i++];
        getFoinBitmap[6] = LoadingBitmapArray[i++];
        getFoinBitmap[7] = LoadingBitmapArray[i++];
        levelBitmap[1] = LoadingBitmapArray[i++];
        levelBitmap[2] = LoadingBitmapArray[i++];
        levelBitmap[3] = LoadingBitmapArray[i++];
        levelBitmap[4] = LoadingBitmapArray[i++];
        levelBitmap[5] = LoadingBitmapArray[i++];
        levelBitmap[6] = LoadingBitmapArray[i++];
        levelBitmap[7] = LoadingBitmapArray[i++];
        levelBitmap[8] = LoadingBitmapArray[i++];
        levelBitmap[9] = LoadingBitmapArray[i++];
        levelBitmap[10] = LoadingBitmapArray[i++];
        levelBitmap[11] = LoadingBitmapArray[i++];
        levelBitmap[12] = LoadingBitmapArray[i++];
        levelBitmap[13] = LoadingBitmapArray[i++];
        levelBitmap[14] = LoadingBitmapArray[i++];
        levelBitmap[15] = LoadingBitmapArray[i++];
        levelBitmap[16] = LoadingBitmapArray[i++];
        levelBitmap[17] = LoadingBitmapArray[i++];
        levelBitmap[18] = LoadingBitmapArray[i++];
        washButtonBitmap = LoadingBitmapArray[i++];
        washButtonBitmap2 = LoadingBitmapArray[i++];
        washButtonBitmapPoop = LoadingBitmapArray[i++];
        washDarkButtonBitmap = LoadingBitmapArray[i++];
        bitmapbg = LoadingBitmapArray[i++];
        bitmapDarkbg = LoadingBitmapArray[i++];
        bitmapDarkkust = LoadingBitmapArray[i++];
        bitmapkust = LoadingBitmapArray[i++];
        bitmapHeart = LoadingBitmapArray[i++];
        bitmapDirt = LoadingBitmapArray[i++];
        bitmapHungry = LoadingBitmapArray[i++];
        bitmapSleep = LoadingBitmapArray[i++];
        bitmapHappy = LoadingBitmapArray[i++];
        eatButtonBitmap = LoadingBitmapArray[i++];
        playButtonBitmap = LoadingBitmapArray[i++];
        sleepButtonBitmap = LoadingBitmapArray[i++];
        eatButtonBitmapPoop = LoadingBitmapArray[i++];
        playButtonBitmapPoop = LoadingBitmapArray[i++];
        sleepButtonBitmapPoop = LoadingBitmapArray[i++];
        screenshotBitmap = LoadingBitmapArray[i++];
        shopButton = LoadingBitmapArray[i++];
        bitmap = bitmapSmile1;
        levelColor = ResourcesCompat.getColor(context.getResources(),R.color.life,null);
        dirtColor = ResourcesCompat.getColor(context.getResources(),R.color.dirt,null);
        hungryColor = ResourcesCompat.getColor(context.getResources(),R.color.hungry,null);
        tiredColor = ResourcesCompat.getColor(context.getResources(),R.color.sleep,null);
        happyColor = ResourcesCompat.getColor(context.getResources(),R.color.happy,null);
        dirtRight = sharedPreferences.getFloat("DIRT", (float)  1040 / 1050);
        hungryRight = sharedPreferences.getFloat("HUNGRY", (float)  1040 / 1050);
        sleepRight = sharedPreferences.getFloat("SLEEP", (float)  1040 / 1050);
        happyRight = sharedPreferences.getFloat("HAPPY", (float) 1040 / 1050);
        levelRight = sharedPreferences.getFloat("LEVELRIGHT",(float)  844 / 1050);
        paintHappy.setColor(happyColor);
        paintSleep.setColor(tiredColor);
        paintHungry.setColor(hungryColor);
        paintDirt.setColor(dirtColor);
        paintLevel.setColor(levelColor);
    }
    private void takeScreenshot() {
        try {
            // image naming and path  to include sd card  appending name you choose for file
            String mPath = Environment.getExternalStorageDirectory().toString() + "/DCIM/" +IMAGE_NAME;
            Log.i("path",mPath);
            // create bitmap screen capture
            View v1 = ((Activity)context).getWindow().getDecorView().getRootView();
            v1.setDrawingCacheEnabled(true);
            bitmapScreen = Bitmap.createBitmap(v1.getDrawingCache());
            v1.setDrawingCacheEnabled(false);
            File imageFile = new File(mPath);
            FileOutputStream outputStream = new FileOutputStream(imageFile);
            int quality = 100;
            bitmapScreen.compress(Bitmap.CompressFormat.JPEG, quality, outputStream);
            outputStream.flush();
            outputStream.close();
            m6 = 1;
        } catch (Throwable e) {
            // Several error may come out with file handling or DOM
            e.printStackTrace();
        }
    }
    private void sendScreen(){
        String mPath = Environment.getExternalStorageDirectory().toString() + "/DCIM/";
        try {
            File f=new File(mPath, IMAGE_NAME);
            Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.setType("image/*");
            sendIntent.putExtra(Intent.EXTRA_STREAM, getImageUri(context, b));
            context.startActivity(Intent.createChooser(sendIntent," "));
            m6 = 0;
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }
    private Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        int rand = (int) (Math.random() * 10000);
        String str = rand + "";
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, str, null);
        return Uri.parse(path);
    }
    public void setTouch(int x, int y){
        lastTouchX = x;
        lastTouchY = y;
    }
    public void requestStop() {
        running = false;
    }
    private void giveSize(Canvas canvas) {
        paintBlack.setColor(Color.BLACK);
        paintBlack.setStyle(Paint.Style.STROKE);
        paint.setSubpixelText(true);
        paint.setAntiAlias(true);
        paintFoin.setSubpixelText(true);
        paintFoin.setAntiAlias(true);
        paintLevel.setSubpixelText(true);
        paintLevel.setAntiAlias(true);
        paintBlack.setSubpixelText(true);
        paintBlack.setAntiAlias(true);
        paintFoin.setColor(Color.BLACK);
        bitmapbg = Bitmap.createScaledBitmap(bitmapbg, canvas.getWidth(), canvas.getHeight(), true);
        bitmapDarkbg = Bitmap.createScaledBitmap(bitmapDarkbg, canvas.getWidth(), canvas.getHeight(), true);
        bitmapkust = Bitmap.createScaledBitmap(bitmapkust, canvas.getWidth() * 245 / 1050, canvas.getHeight() * 180 / 540, true);
        bitmapDarkkust = Bitmap.createScaledBitmap(bitmapDarkkust, canvas.getWidth() * 245 / 1050, canvas.getHeight() * 180 / 540, true);
        for (int i = 1; i < 19; i++) {
            levelBitmap[i] = Bitmap.createScaledBitmap(levelBitmap[i], canvas.getWidth() * 45 / 1050, canvas.getHeight() * 45 / 540, true);
        }
        bitmapDirt = Bitmap.createScaledBitmap(bitmapDirt, canvas.getWidth() * 60 / 1050, canvas.getHeight() * 51 / 540, true);
        bitmapHungry = Bitmap.createScaledBitmap(bitmapHungry, canvas.getWidth() * 46 / 1050, canvas.getHeight() * 33 / 540, true);
        bitmapSleep = Bitmap.createScaledBitmap(bitmapSleep, canvas.getWidth() * 52 / 1050, canvas.getHeight() * 57 / 540, true);
        bitmapHappy = Bitmap.createScaledBitmap(bitmapHappy, canvas.getWidth() * 34 / 1050, canvas.getHeight() * 35 / 540, true);
        bitmapUsual1 = Bitmap.createScaledBitmap(bitmapUsual1, (int) (birdWidth * canvas.getWidth()), (int) (canvas.getHeight() * birdHeight), true);
        bitmapUsual2 = Bitmap.createScaledBitmap(bitmapUsual2, (int) (birdWidth * canvas.getWidth()), (int) (canvas.getHeight() * birdHeight), true);
        bitmapDTSH1 = Bitmap.createScaledBitmap(bitmapDTSH1, (int) (birdWidth * canvas.getWidth()), (int) (canvas.getHeight() * birdHeight), true);
        bitmapDTSH2 = Bitmap.createScaledBitmap(bitmapDTSH2, (int) (birdWidth * canvas.getWidth()), (int) (canvas.getHeight() * birdHeight), true);
        bitmapDTS1 = Bitmap.createScaledBitmap(bitmapDTS1, (int) (birdWidth * canvas.getWidth()), (int) (canvas.getHeight() * birdHeight), true);
        bitmapDTS2 = Bitmap.createScaledBitmap(bitmapDTS2, (int) (birdWidth * canvas.getWidth()), (int) (canvas.getHeight() * birdHeight), true);
        bitmapDTH1 = Bitmap.createScaledBitmap(bitmapDTH1, (int) (birdWidth * canvas.getWidth()), (int) (canvas.getHeight() * birdHeight), true);
        bitmapDTH2 = Bitmap.createScaledBitmap(bitmapDTH2, (int) (birdWidth * canvas.getWidth()), (int) (canvas.getHeight() * birdHeight), true);
        bitmapDSH1 = Bitmap.createScaledBitmap(bitmapDSH1, (int) (birdWidth * canvas.getWidth()), (int) (canvas.getHeight() * birdHeight), true);
        bitmapDSH2 = Bitmap.createScaledBitmap(bitmapDSH2, (int) (birdWidth * canvas.getWidth()), (int) (canvas.getHeight() * birdHeight), true);
        bitmapTSH1 = Bitmap.createScaledBitmap(bitmapTSH1, (int) (birdWidth * canvas.getWidth()), (int) (canvas.getHeight() * birdHeight), true);
        bitmapTSH2 = Bitmap.createScaledBitmap(bitmapTSH2, (int) (birdWidth * canvas.getWidth()), (int) (canvas.getHeight() * birdHeight), true);
        bitmapDH1 = Bitmap.createScaledBitmap(bitmapDH1, (int) (birdWidth * canvas.getWidth()), (int) (canvas.getHeight() * birdHeight), true);
        bitmapDH2 = Bitmap.createScaledBitmap(bitmapDH2, (int) (birdWidth * canvas.getWidth()), (int) (canvas.getHeight() * birdHeight), true);
        bitmapDS1 = Bitmap.createScaledBitmap(bitmapDS1, (int) (birdWidth * canvas.getWidth()), (int) (canvas.getHeight() * birdHeight), true);
        bitmapDS2 = Bitmap.createScaledBitmap(bitmapDS2, (int) (birdWidth * canvas.getWidth()), (int) (canvas.getHeight() * birdHeight), true);
        bitmapDT1 = Bitmap.createScaledBitmap(bitmapDT1, (int) (birdWidth * canvas.getWidth()), (int) (canvas.getHeight() * birdHeight), true);
        bitmapDT2 = Bitmap.createScaledBitmap(bitmapDT2, (int) (birdWidth * canvas.getWidth()), (int) (canvas.getHeight() * birdHeight), true);
        bitmapSH1 = Bitmap.createScaledBitmap(bitmapSH1, (int) (birdWidth * canvas.getWidth()), (int) (canvas.getHeight() * birdHeight), true);
        bitmapSH2 = Bitmap.createScaledBitmap(bitmapSH2, (int) (birdWidth * canvas.getWidth()), (int) (canvas.getHeight() * birdHeight), true);
        bitmapTH1 = Bitmap.createScaledBitmap(bitmapTH1, (int) (birdWidth * canvas.getWidth()), (int) (canvas.getHeight() * birdHeight), true);
        bitmapTH2 = Bitmap.createScaledBitmap(bitmapTH2, (int) (birdWidth * canvas.getWidth()), (int) (canvas.getHeight() * birdHeight), true);
        bitmapTS1 = Bitmap.createScaledBitmap(bitmapTS1, (int) (birdWidth * canvas.getWidth()), (int) (canvas.getHeight() * birdHeight), true);
        bitmapTS2 = Bitmap.createScaledBitmap(bitmapTS2, (int) (birdWidth * canvas.getWidth()), (int) (canvas.getHeight() * birdHeight), true);
        bitmapD1 = Bitmap.createScaledBitmap(bitmapD1, (int) (birdWidth * canvas.getWidth()), (int) (canvas.getHeight() * birdHeight), true);
        bitmapD2 = Bitmap.createScaledBitmap(bitmapD2, (int) (birdWidth * canvas.getWidth()), (int) (canvas.getHeight() * birdHeight), true);
        bitmapT1 = Bitmap.createScaledBitmap(bitmapT1, (int) (birdWidth * canvas.getWidth()), (int) (canvas.getHeight() * birdHeight), true);
        bitmapT2 = Bitmap.createScaledBitmap(bitmapT2, (int) (birdWidth * canvas.getWidth()), (int) (canvas.getHeight() * birdHeight), true);
        bitmapS1 = Bitmap.createScaledBitmap(bitmapS1, (int) (birdWidth * canvas.getWidth()), (int) (canvas.getHeight() * birdHeight), true);
        bitmapS2 = Bitmap.createScaledBitmap(bitmapS2, (int) (birdWidth * canvas.getWidth()), (int) (canvas.getHeight() * birdHeight), true);
        bitmapH1 = Bitmap.createScaledBitmap(bitmapH1, (int) (birdWidth * canvas.getWidth()), (int) (canvas.getHeight() * birdHeight), true);
        bitmapH2 = Bitmap.createScaledBitmap(bitmapH2, (int) (birdWidth * canvas.getWidth()), (int) (canvas.getHeight() * birdHeight), true);
        bitmapSmile1 = Bitmap.createScaledBitmap(bitmapSmile1, (int) (birdWidth * canvas.getWidth()), (int) (canvas.getHeight() * birdHeight), true);
        bitmapSmile2 = Bitmap.createScaledBitmap(bitmapSmile2, (int) (birdWidth * canvas.getWidth()), (int) (canvas.getHeight() * birdHeight), true);
        for (int i = 1; i < 11; i++) {
            eatBitmap[i] = Bitmap.createScaledBitmap(eatBitmap[i], (int) (canvas.getWidth() * birdWidth), (int) (canvas.getHeight() * birdHeight), true);eatBitmapSmile[i] = Bitmap.createScaledBitmap(eatBitmapSmile[i], (int) (canvas.getWidth() * birdWidth), (int) (canvas.getHeight() * birdHeight), true);eatBitmapD[i] = Bitmap.createScaledBitmap(eatBitmapD[i], (int) (canvas.getWidth() * birdWidth), (int) (canvas.getHeight() * birdHeight), true);eatBitmapDS[i] = Bitmap.createScaledBitmap(eatBitmapDS[i], (int) (canvas.getWidth() * birdWidth), (int) (canvas.getHeight() * birdHeight), true);eatBitmapDT[i] = Bitmap.createScaledBitmap(eatBitmapDT[i], (int) (canvas.getWidth() * birdWidth), (int) (canvas.getHeight() * birdHeight), true);eatBitmapDTS[i] = Bitmap.createScaledBitmap(eatBitmapDTS[i], (int) (canvas.getWidth() * birdWidth), (int) (canvas.getHeight() * birdHeight), true);eatBitmapS[i] = Bitmap.createScaledBitmap(eatBitmapS[i], (int) (canvas.getWidth() * birdWidth), (int) (canvas.getHeight() * birdHeight), true);eatBitmapT[i] = Bitmap.createScaledBitmap(eatBitmapT[i], (int) (canvas.getWidth() * birdWidth), (int) (canvas.getHeight() * birdHeight), true);eatBitmapTS[i] = Bitmap.createScaledBitmap(eatBitmapTS[i], (int) (canvas.getWidth() * birdWidth), (int) (canvas.getHeight() * birdHeight), true);
        }
        for (int i = 1; i < 21; i++) {
            playBitmap[i] = Bitmap.createScaledBitmap(playBitmap[i], canvas.getWidth() * 194 / 1050, canvas.getHeight() * 172 / 540, true);playBitmapD[i] = Bitmap.createScaledBitmap(playBitmapD[i], canvas.getWidth() * 194 / 1050, canvas.getHeight() * 172 / 540, true);playBitmapDT[i] = Bitmap.createScaledBitmap(playBitmapDT[i], canvas.getWidth() * 194 / 1050, canvas.getHeight() * 172 / 540, true);playBitmapT[i] = Bitmap.createScaledBitmap(playBitmapT[i], canvas.getWidth() * 194 / 1050, canvas.getHeight() * 172 / 540, true);
        }
        for (int i = 1; i < 5; i++) {
            flyBitmapSmile[i] = Bitmap.createScaledBitmap(flyBitmapSmile[i], canvas.getWidth() * 150 / 1050, canvas.getHeight() * 179 / 540, true);flyBitmapDH[i] = Bitmap.createScaledBitmap(flyBitmapDH[i], canvas.getWidth() * 150 / 1050, canvas.getHeight() * 179 / 540, true);flyBitmapDS[i] = Bitmap.createScaledBitmap(flyBitmapDS[i], canvas.getWidth() * 150 / 1050, canvas.getHeight() * 179 / 540, true);flyBitmapD[i] = Bitmap.createScaledBitmap(flyBitmapD[i], canvas.getWidth() * 150 / 1050, canvas.getHeight() * 179 / 540, true);flyBitmapH[i] = Bitmap.createScaledBitmap(flyBitmapH[i], canvas.getWidth() * 150 / 1050, canvas.getHeight() * 179 / 540, true);flyBitmapSDH[i] = Bitmap.createScaledBitmap(flyBitmapSDH[i], canvas.getWidth() * 150 / 1050, canvas.getHeight() * 179 / 540, true);flyBitmapSH[i] = Bitmap.createScaledBitmap(flyBitmapSH[i], canvas.getWidth() * 150 / 1050, canvas.getHeight() * 179 / 540, true);flyBitmapS[i] = Bitmap.createScaledBitmap(flyBitmapS[i], canvas.getWidth() * 150 / 1050, canvas.getHeight() * 179 / 540, true);flyBitmapTDH[i] = Bitmap.createScaledBitmap(flyBitmapTDH[i], canvas.getWidth() * 150 / 1050, canvas.getHeight() * 179 / 540, true);flyBitmapTDSH[i] = Bitmap.createScaledBitmap(flyBitmapTDSH[i], canvas.getWidth() * 150 / 1050, canvas.getHeight() * 179 / 540, true);flyBitmapTDS[i] = Bitmap.createScaledBitmap(flyBitmapTDS[i], canvas.getWidth() * 150 / 1050, canvas.getHeight() * 179 / 540, true);flyBitmapTD[i] = Bitmap.createScaledBitmap(flyBitmapTD[i], canvas.getWidth() * 150 / 1050, canvas.getHeight() * 179 / 540, true);flyBitmapTH[i] = Bitmap.createScaledBitmap(flyBitmapTH[i], canvas.getWidth() * 150 / 1050, canvas.getHeight() * 179 / 540, true);flyBitmapTSH[i] = Bitmap.createScaledBitmap(flyBitmapTSH[i], canvas.getWidth() * 150 / 1050, canvas.getHeight() * 179 / 540, true);flyBitmapTS[i] = Bitmap.createScaledBitmap(flyBitmapTS[i], canvas.getWidth() * 150 / 1050, canvas.getHeight() * 179 / 540, true);flyBitmapT[i] = Bitmap.createScaledBitmap(flyBitmapDH[i], canvas.getWidth() * 150 / 1050, canvas.getHeight() * 179 / 540, true);flyBitmapUsual[i] = Bitmap.createScaledBitmap(flyBitmapUsual[i], canvas.getWidth() * 150 / 1050, canvas.getHeight() * 179 / 540, true);
        }
        sleepUsual1 = Bitmap.createScaledBitmap(sleepUsual1, canvas.getWidth() * 200 / 1050, canvas.getHeight() * 166 / 540, true);
        sleepUsual2 = Bitmap.createScaledBitmap(sleepUsual2, canvas.getWidth() * 200 / 1050, canvas.getHeight() * 166 / 540, true);
        sleepDH1 = Bitmap.createScaledBitmap(sleepDH1, canvas.getWidth() * 200 / 1050, canvas.getHeight() * 166 / 540, true);
        sleepDH2 = Bitmap.createScaledBitmap(sleepDH2, canvas.getWidth() * 200 / 1050, canvas.getHeight() * 166 / 540, true);
        sleepDSH1 = Bitmap.createScaledBitmap(sleepDSH1, canvas.getWidth() * 200 / 1050, canvas.getHeight() * 166 / 540, true);
        sleepDSH2 = Bitmap.createScaledBitmap(sleepDSH2, canvas.getWidth() * 200 / 1050, canvas.getHeight() * 166 / 540, true);
        sleepDS1 = Bitmap.createScaledBitmap(sleepDS1, canvas.getWidth() * 200 / 1050, canvas.getHeight() * 166 / 540, true);
        sleepDS2 = Bitmap.createScaledBitmap(sleepDS2, canvas.getWidth() * 200 / 1050, canvas.getHeight() * 166 / 540, true);
        sleepD1 = Bitmap.createScaledBitmap(sleepD1, canvas.getWidth() * 200 / 1050, canvas.getHeight() * 166 / 540, true);
        sleepD2 = Bitmap.createScaledBitmap(sleepD2, canvas.getWidth() * 200 / 1050, canvas.getHeight() * 166 / 540, true);
        sleepH1 = Bitmap.createScaledBitmap(sleepH1, canvas.getWidth() * 200 / 1050, canvas.getHeight() * 166 / 540, true);
        sleepH2 = Bitmap.createScaledBitmap(sleepH2, canvas.getWidth() * 200 / 1050, canvas.getHeight() * 166 / 540, true);
        sleepSmile1 = Bitmap.createScaledBitmap(sleepSmile1, canvas.getWidth() * 200 / 1050, canvas.getHeight() * 166 / 540, true);
        sleepSmile2 = Bitmap.createScaledBitmap(sleepSmile2, canvas.getWidth() * 200 / 1050, canvas.getHeight() * 166 / 540, true);
        sleepSH1 = Bitmap.createScaledBitmap(sleepSH1, canvas.getWidth() * 200 / 1050, canvas.getHeight() * 166 / 540, true);
        sleepSH2 = Bitmap.createScaledBitmap(sleepSH2, canvas.getWidth() * 200 / 1050, canvas.getHeight() * 166 / 540, true);
        sleepS1 = Bitmap.createScaledBitmap(sleepS1, canvas.getWidth() * 200 / 1050, canvas.getHeight() * 166 / 540, true);
        sleepS2 = Bitmap.createScaledBitmap(sleepS2, canvas.getWidth() * 200 / 1050, canvas.getHeight() * 166 / 540, true);
        for (int i = 1; i < 48; i++) washBitmap[i] = Bitmap.createScaledBitmap(washBitmap[i], canvas.getWidth() * 341 / 1050, canvas.getHeight() * 378 / 540, true);
        for (int i = 0; i < 8; i++) getFoinBitmap[i] = Bitmap.createScaledBitmap(getFoinBitmap[i],  canvas.getWidth() * 137 / 1050, canvas.getHeight()* 134 / 540, true);
        foinBitmap = Bitmap.createScaledBitmap(foinBitmap, canvas.getWidth() * 137 / 1050, canvas.getHeight()* 134 / 540, true);
        shopButton = Bitmap.createScaledBitmap(shopButton,canvas.getWidth()*75/1050, canvas.getHeight()*75/540,true);
        paintFoin.setTextSize((float) canvas.getWidth() * 20/1050);
        for (int i = 0; i < 11; i++) {
            eatDarkButtonBitmap[i] = Bitmap.createScaledBitmap(eatDarkButtonBitmap[i],(int)(canvas.getWidth()*ButtonWidth), (int)(canvas.getHeight()*ButtonHeight),true);
        }
        eatButtonBitmap = Bitmap.createScaledBitmap(eatButtonBitmap,(int)(canvas.getWidth()*ButtonWidth), (int)(canvas.getHeight()*ButtonHeight),true);
        for (int i = 0; i < 16; i++) {
            playDarkButtonBitmap[i] = Bitmap.createScaledBitmap(playDarkButtonBitmap[i], (int)(canvas.getWidth()*ButtonWidth), (int)(canvas.getHeight()*ButtonHeight),true);
        }
        playButtonBitmap = Bitmap.createScaledBitmap(playButtonBitmap,(int)(canvas.getWidth()*ButtonWidth), (int)(canvas.getHeight()*ButtonHeight),true);
        sleepButtonBitmap = Bitmap.createScaledBitmap(sleepButtonBitmap,(int)(canvas.getWidth()*ButtonWidth), (int)(canvas.getHeight()*ButtonHeight),true);
        for (int i = 0; i < 61; i++) {
            sleepDarkButtonBitmap[i] = Bitmap.createScaledBitmap(sleepDarkButtonBitmap[i],(int)(canvas.getWidth()*ButtonWidth), (int)(canvas.getHeight()*ButtonHeight),true);
        }
        washButtonBitmap = Bitmap.createScaledBitmap(washButtonBitmap,(int)(canvas.getWidth()*ButtonWidth), (int)(canvas.getHeight()*ButtonHeight),true);
        washButtonBitmap2 = Bitmap.createScaledBitmap(washButtonBitmap2,(int)(canvas.getWidth()*ButtonWidth), (int)(canvas.getHeight()*ButtonHeight),true);
        washDarkButtonBitmap = Bitmap.createScaledBitmap(washDarkButtonBitmap,(int)(canvas.getWidth()*ButtonWidth), (int)(canvas.getHeight()*ButtonHeight),true);
        eatButtonBitmapPoop = Bitmap.createScaledBitmap(eatButtonBitmapPoop,(int)(canvas.getWidth()*ButtonWidth), (int)(canvas.getHeight()*ButtonHeight),true);
        playButtonBitmapPoop = Bitmap.createScaledBitmap(playButtonBitmapPoop,(int)(canvas.getWidth()*ButtonWidth), (int)(canvas.getHeight()*ButtonHeight),true);
        washButtonBitmapPoop = Bitmap.createScaledBitmap(washButtonBitmapPoop,(int)(canvas.getWidth()*ButtonWidth), (int)(canvas.getHeight()*ButtonHeight),true);
        sleepButtonBitmapPoop = Bitmap.createScaledBitmap(sleepButtonBitmapPoop,(int)(canvas.getWidth()*ButtonWidth), (int)(canvas.getHeight()*ButtonHeight),true);
        for (int i = 1; i < 5; i++) poopBitmap[i] = Bitmap.createScaledBitmap(poopBitmap[i], canvas.getWidth() * 94 / 1050, canvas.getHeight() * 94 / 540, true);
        for (int i = 1; i < 11; i++) {
            poopingBitmapUsual[i] = Bitmap.createScaledBitmap(poopingBitmapUsual[i], (int) (canvas.getWidth() * birdWidth), (int) (canvas.getHeight() * birdHeight), true);
            poopingBitmapDTSH[i] = Bitmap.createScaledBitmap(poopingBitmapDTSH[i], (int) (canvas.getWidth() * birdWidth), (int) (canvas.getHeight() * birdHeight), true);
            poopingBitmapDSH[i] = Bitmap.createScaledBitmap(poopingBitmapDSH[i], (int) (canvas.getWidth() * birdWidth), (int) (canvas.getHeight() * birdHeight), true);
            poopingBitmapDTH[i] = Bitmap.createScaledBitmap(poopingBitmapDTH[i], (int) (canvas.getWidth() * birdWidth), (int) (canvas.getHeight() * birdHeight), true);
            poopingBitmapDTS[i] = Bitmap.createScaledBitmap(poopingBitmapDTS[i], (int) (canvas.getWidth() * birdWidth), (int) (canvas.getHeight() * birdHeight), true);
            poopingBitmapTHS[i] = Bitmap.createScaledBitmap(poopingBitmapTHS[i], (int) (canvas.getWidth() * birdWidth), (int) (canvas.getHeight() * birdHeight), true);
            poopingBitmapDH[i] = Bitmap.createScaledBitmap(poopingBitmapDH[i], (int) (canvas.getWidth() * birdWidth), (int) (canvas.getHeight() * birdHeight), true);
            poopingBitmapDT[i] = Bitmap.createScaledBitmap(poopingBitmapDT[i], (int) (canvas.getWidth() * birdWidth), (int) (canvas.getHeight() * birdHeight), true);
            poopingBitmapDS[i] = Bitmap.createScaledBitmap(poopingBitmapDS[i], (int) (canvas.getWidth() * birdWidth), (int) (canvas.getHeight() * birdHeight), true);
            poopingBitmapD[i] = Bitmap.createScaledBitmap(poopingBitmapD[i], (int) (canvas.getWidth() * birdWidth), (int) (canvas.getHeight() * birdHeight), true);
            poopingBitmapTH[i] = Bitmap.createScaledBitmap(poopingBitmapTH[i], (int) (canvas.getWidth() * birdWidth), (int) (canvas.getHeight() * birdHeight), true);
            poopingBitmapHS[i] = Bitmap.createScaledBitmap(poopingBitmapHS[i], (int) (canvas.getWidth() * birdWidth), (int) (canvas.getHeight() * birdHeight), true);
            poopingBitmapTS[i] = Bitmap.createScaledBitmap(poopingBitmapTS[i], (int) (canvas.getWidth() * birdWidth), (int) (canvas.getHeight() * birdHeight), true);
            poopingBitmapT[i] = Bitmap.createScaledBitmap(poopingBitmapT[i], (int) (canvas.getWidth() * birdWidth), (int) (canvas.getHeight() * birdHeight), true);
            poopingBitmapH[i] = Bitmap.createScaledBitmap(poopingBitmapH[i], (int) (canvas.getWidth() * birdWidth), (int) (canvas.getHeight() * birdHeight), true);
            poopingBitmapS[i] = Bitmap.createScaledBitmap(poopingBitmapS[i], (int) (canvas.getWidth() * birdWidth), (int) (canvas.getHeight() * birdHeight), true);
        }
        for (int i = 1; i < 15; i++) {
            hitBitmap[i] = Bitmap.createScaledBitmap(hitBitmap[i],(int)(canvas.getWidth() * birdWidth),(int)(canvas.getHeight()*birdHeight),true);
            hitBitmapD[i] = Bitmap.createScaledBitmap(hitBitmapD[i],(int)(canvas.getWidth() * birdWidth),(int)(canvas.getHeight()*birdHeight),true);
            hitBitmapH[i] = Bitmap.createScaledBitmap(hitBitmapH[i],(int)(canvas.getWidth() * birdWidth),(int)(canvas.getHeight()*birdHeight),true);
            hitBitmapDH[i] = Bitmap.createScaledBitmap(hitBitmapDH[i],(int)(canvas.getWidth() * birdWidth),(int)(canvas.getHeight()*birdHeight),true);
        }
        loaded = true;
    }

    @Override
    public void run() {
        while (running) {
            Canvas canvas = surfaceHolder.lockCanvas();
            if (canvas != null) {
                try {
                    if(m2 == 1) {
                        giveSize(canvas);
                        m2 = 0;
                    }
                    if(loaded) {
                        //statChecker =  sharedPreferences.getBoolean("STATCHECKER", true);
                        //lvlCheck = sharedPreferences.getBoolean("LVLCHECK",false);
                        paintBlack.setStrokeWidth((float) canvas.getWidth() / 540);
                        //hetevi fon
                        if (!flying && !sleeping && !laying) {
                            canvas.drawBitmap(bitmapbg, 0, 0, paint);
                            canvas.drawBitmap(bitmapkust, (float) canvas.getWidth() * 29 / 1050, (float) canvas.getHeight() * 230 / 540, paint);
                        } else {
                            canvas.drawBitmap(bitmapDarkbg, 0, 0, paint);
                            canvas.drawBitmap(bitmapDarkkust, (float) canvas.getWidth() * 29 / 1050, (float) canvas.getHeight() * 230 / 540, paint);
                        }
                        // lvl
                        canvas.drawRect((float) canvas.getWidth() * 843 / 1050, (float) canvas.getHeight() * 7 / 540,
                                (float) canvas.getWidth() * 1041 / 1050, (float) canvas.getHeight() * 43 / 540, paintBlack);
                        canvas.drawRect((float) canvas.getWidth() * levelLeft, (float) canvas.getHeight() * levelTop, (float) canvas.getWidth() * levelRight, (float) canvas.getHeight() * levelBottom, paintLevel);
                        // kextotutyun
                        canvas.drawRect((float) canvas.getWidth() * 876 / 1050, (float) canvas.getHeight() * 54 / 540,
                                (float) canvas.getWidth() * 1041 / 1050, (float) canvas.getHeight() * 80 / 540, paintBlack);
                        // if(dirtRight-timePassed <= dirtLeft) {
                        //     dirtRight = dirtLeft;
                        //     editor.putFloat("DIRT",dirtRight);
                        //     editor.apply();
                        // }
                        // else{
                        //     dirtRight = dirtRight-timePassed;
                        //     editor.putFloat("DIRT",dirtRight);
                        //     editor.apply();
                        // }
                        canvas.drawRect((float) canvas.getWidth() * dirtLeft, (float) canvas.getHeight() * dirtTop, (float) canvas.getWidth() * dirtRight, (float) canvas.getHeight() * dirtBottom, paintDirt);
                        // kexti timer
                        if (!dirtTimeIsPassed) {
                            new DirtTimerThread().start();
                            dirtTimeIsPassed = true;
                        }
                        if (dirtNeedToDrawNow) {
                            if (dirtRight - k * dirtWeight >= dirtLeft) {
                                k *= dirtWeight;
                                dirtRight -= k;
                                editor.putFloat("DIRT", dirtRight);
                                editor.apply();
                                k /= dirtWeight;
                            }
                            if (dirtRight >= dirtLeft && dirtRight - k * dirtWeight < dirtLeft) {
                                dirtRight = dirtLeft;
                                editor.putFloat("DIRT", dirtRight);
                                editor.apply();
                            }
                            dirtNeedToDrawNow = false;
                        }
                        // sov
                        canvas.drawRect((float) canvas.getWidth() * 876 / 1050, (float) canvas.getHeight() * 91 / 540,
                                (float) canvas.getWidth() * 1041 / 1050, (float) canvas.getHeight() * 117 / 540, paintBlack);
                        canvas.drawRect((float) canvas.getWidth() * hungryLeft, (float) canvas.getHeight() * hungryTop, (float) canvas.getWidth() * hungryRight, (float) canvas.getHeight() * hungryBottom, paintHungry);
                        // sovi timer
                        if (!hungryTimeIsPassed) {
                            new HungryTimerThread().start();
                            hungryTimeIsPassed = true;
                        }
                        if (hungryNeedToDrawNow) {
                            if (hungryRight - h * hungryWeight >= hungryLeft) {
                                h *= hungryWeight;
                                hungryRight -= h;
                                editor.putFloat("HUNGRY", hungryRight);
                                editor.apply();
                                h /= hungryWeight;
                            }
                            if (hungryRight >= hungryLeft && hungryRight - h * hungryWeight < hungryLeft) {
                                hungryRight = hungryLeft;
                                editor.putFloat("HUNGRY", hungryRight);
                                editor.apply();
                            }
                            hungryNeedToDrawNow = false;
                        }
                        // qun
                        canvas.drawRect((float) canvas.getWidth() * 876 / 1050, (float) canvas.getHeight() * 128 / 540,
                                (float) canvas.getWidth() * 1041 / 1050, (float) canvas.getHeight() * 154 / 540, paintBlack);
                        canvas.drawRect((float) canvas.getWidth() * sleepLeft, (float) canvas.getHeight() * sleepTop, (float) canvas.getWidth() * sleepRight, (float) canvas.getHeight() * sleepBottom, paintSleep);
                        // qni timer
                        if (!sleepTimeIsPassed && !sleeping) {
                            new SleepTimerThread().start();
                            sleepTimeIsPassed = true;
                        }
                        if (sleepNeedToDrawNow) {
                            if (sleepRight - q * sleepWeight >= sleepLeft) {
                                q *= sleepWeight;
                                sleepRight -= q;
                                editor.putFloat("SLEEP", sleepRight);
                                editor.apply();
                                q /= sleepWeight;
                            }
                            if (sleepRight >= sleepLeft && sleepRight - q * sleepWeight < sleepLeft) {
                                sleepRight = sleepLeft;
                                editor.putFloat("SLEEP", sleepRight);
                                editor.apply();
                            }
                            sleepNeedToDrawNow = false;
                        }

                        // uraxutyun
                        canvas.drawRect((float) canvas.getWidth() * 876 / 1050, (float) canvas.getHeight() * 165 / 540,
                                (float) canvas.getWidth() * 1041 / 1050, (float) canvas.getHeight() * 191 / 540, paintBlack);
                        canvas.drawRect((float) canvas.getWidth() * happyLeft, (float) canvas.getHeight() * happyTop, (float) canvas.getWidth() * happyRight, (float) canvas.getHeight() * happyBottom, paintHappy);
                        // uraxutyan timer
                        if (!happyTimeIsPassed) {
                            new HappyTimerThread().start();
                            happyTimeIsPassed = true;
                        }
                        if (happyNeedToDrawNow) {
                            if (happyRight - s * happyWeight >= happyLeft) {
                                s *= happyWeight;
                                happyRight -= s;
                                editor.putFloat("HAPPY", happyRight);
                                editor.apply();
                                s /= happyWeight;
                            }
                            if (happyRight >= happyLeft && happyRight - s * happyWeight < happyLeft) {
                                happyRight = happyLeft;
                                editor.putFloat("HAPPY", happyRight);
                                editor.apply();
                            }
                            happyNeedToDrawNow = false;
                        }
                        // level
                        canvas.drawBitmap(levelBitmap[level], (float) canvas.getWidth() * 815 / 1050, (float) canvas.getHeight() * 2 / 540, paint);
                        // kext
                        canvas.drawBitmap(bitmapDirt, (float) canvas.getWidth() * 840 / 1050, (float) canvas.getHeight() * 41 / 540, paint);
                        // tarelka
                        canvas.drawBitmap(bitmapHungry, (float) canvas.getWidth() * 850 / 1050, (float) canvas.getHeight() * 87 / 540, paint);
                        // Zzz
                        canvas.drawBitmap(bitmapSleep, (float) canvas.getWidth() * 853 / 1050, (float) canvas.getHeight() * 110 / 540, paint);
                        // Smilik
                        canvas.drawBitmap(bitmapHappy, (float) canvas.getWidth() * 862 / 1050, (float) canvas.getHeight() * 161 / 540, paint);
                        // cter
                        if (hungryRight - hungryLeft < hungryWeight / 2. && dirtRight - dirtLeft < dirtWeight / 2. && happyRight - happyLeft < happyWeight / 3. && sleepRight - sleepLeft < sleepWeight / 2.) {
                            bitmap1 = bitmapDTSH1;
                            bitmap2 = bitmapDTSH2;
                        } else if (dirtRight - dirtLeft < dirtWeight / 2. && happyRight - happyLeft < happyWeight / 3. && sleepRight - sleepLeft < sleepWeight / 2.) {
                            bitmap1 = bitmapDTS1;
                            bitmap2 = bitmapDTS2;
                        } else if (hungryRight - hungryLeft < hungryWeight / 2. && happyRight - happyLeft < happyWeight / 3. && sleepRight - sleepLeft < sleepWeight / 2.) {
                            bitmap1 = bitmapTSH1;
                            bitmap2 = bitmapTSH2;
                        } else if (hungryRight - hungryLeft < hungryWeight / 2. && dirtRight - dirtLeft < dirtWeight / 2. && sleepRight - sleepLeft < sleepWeight / 2.) {
                            bitmap1 = bitmapDTH1;
                            bitmap2 = bitmapDTH2;
                        } else if (hungryRight - hungryLeft < hungryWeight / 2. && dirtRight - dirtLeft < dirtWeight / 2. && happyRight - happyLeft < happyWeight / 3.) {
                            bitmap1 = bitmapDSH1;
                            bitmap2 = bitmapDSH2;
                        } else if (hungryRight - hungryLeft < hungryWeight / 2. && dirtRight - dirtLeft < dirtWeight / 2.) {
                            bitmap1 = bitmapDH1;
                            bitmap2 = bitmapDH2;
                        } else if (dirtRight - dirtLeft < dirtWeight / 2. && happyRight - happyLeft < happyWeight / 3.) {
                            bitmap1 = bitmapDS1;
                            bitmap2 = bitmapDS2;
                        } else if (dirtRight - dirtLeft < dirtWeight / 2. && sleepRight - sleepLeft < sleepWeight / 2.) {
                            bitmap1 = bitmapDT1;
                            bitmap2 = bitmapDT2;
                        } else if (hungryRight - hungryLeft < hungryWeight / 2. && happyRight - happyLeft < happyWeight / 3.) {
                            bitmap1 = bitmapSH1;
                            bitmap2 = bitmapSH2;
                        } else if (sleepRight - sleepLeft < sleepWeight / 2. && hungryRight - hungryLeft < hungryWeight / 2.) {
                            bitmap1 = bitmapTH1;
                            bitmap2 = bitmapTH2;
                        } else if (sleepRight - sleepLeft < sleepWeight / 2. && happyRight - happyLeft < happyWeight / 3.) {
                            bitmap1 = bitmapTS1;
                            bitmap2 = bitmapTS2;
                        } else if (dirtRight - dirtLeft < dirtWeight / 2.) {
                            bitmap1 = bitmapD1;
                            bitmap2 = bitmapD2;
                        } else if (sleepRight - sleepLeft < sleepWeight / 2.) {
                            bitmap1 = bitmapT1;
                            bitmap2 = bitmapT2;
                        } else if (happyRight - happyLeft < happyWeight / 3.) {
                            bitmap1 = bitmapS1;
                            bitmap2 = bitmapS2;
                        } else if (hungryRight - hungryLeft < hungryWeight / 2.) {
                            bitmap1 = bitmapH1;
                            bitmap2 = bitmapH2;
                        } else if (happyRight - happyLeft < (2.) * happyWeight / 3.) {
                            bitmap1 = bitmapUsual1;
                            bitmap2 = bitmapUsual2;
                        } else {
                            bitmap1 = bitmapSmile1;
                            bitmap2 = bitmapSmile2;
                        }
                        if (m == 0) {
                            m = 1;
                            bitmap = bitmap1;
                        }
                        //shnchel
                        if (!eating && !playing && !flying && !sleeping && !laying && !hitting && !washing && !pooping && !flyPoop && !disgusting && !flyBackPoop) {
                            if (!timeIsPassed1 && !check) {
                                new ThreadBird1().start();
                                timeIsPassed1 = true;
                            }
                            if (needToDrawNow1 && !check) bitmap = bitmap1;
                            if (!timeIsPassed2 && check) {
                                new ThreadBird2().start();
                                timeIsPassed2 = true;
                            }
                            if (needToDrawNow2 && check) bitmap = bitmap2;
                        }
                        //foin
                        if (!getFoinTimeIsPassed && gettingFoin) {
                            new GetFoinThread().start();
                            getFoinTimeIsPassed = true;
                        }
                        if (getFoinNeedToDrawNow && gettingFoin) {
                            foinBitmap = getFoinBitmap[foinTime];
                            if (foinTime == 7) {
                                foinBitmap = getFoinBitmap[0];
                                foinTime = 0;
                                gettingFoin = false;
                                foin++;
                                editor.putInt("FOIN", foin);
                                editor.apply();
                            }
                        }
                        canvas.drawBitmap(foinBitmap, (float) canvas.getWidth() * 19 / 1050, (float) canvas.getHeight() * 19 / 540, paint);
                        if (foin >= 0 && foin <= 9)
                            canvas.drawText(foin + " ", (float) canvas.getWidth() * 101 / 1050, (float) canvas.getHeight() * 49 / 540, paintFoin);
                        if (foin >= 10 && foin <= 99)
                            canvas.drawText(foin + " ", (float) canvas.getWidth() * 95 / 1050, (float) canvas.getHeight() * 49 / 540, paintFoin);
                        // Xanuti knopka
                        canvas.drawBitmap(shopButton, (float) canvas.getWidth() * shopButtonLeft, (float) canvas.getHeight() * shopButtonTop, paint);
                        // utelu knopken
                        if (!eatChecker && checkEatButton && !pooping && !flyPoop && !disgusting && !flyBackPoop) {
                            new EatDarkButtonThread().start();
                            checkEatButton = false;
                        }
                        if (drawEatButton && !pooping && !flyPoop && !disgusting && !flyBackPoop) {
                            eatButtonBitmap = eatDarkButtonBitmap[eatTimer];
                            if (eatTimer == 1) {
                                eatChecker = true;
                                lastTouchX = 0;
                                lastTouchY = 0;
                            }
                        }
                        canvas.drawBitmap(eatButtonBitmap, (float) canvas.getWidth() * eatButtonLeft, (float) canvas.getHeight() * eatButtonTop, paint);
                        // xaxalu knopken
                        if (!playChecker && checkPlayButton && !pooping && !flyPoop && !disgusting && !flyBackPoop) {
                            new PlayDarkButtonThread().start();
                            checkPlayButton = false;
                        }
                        if (drawPlayButton && !pooping && !flyPoop && !disgusting && !flyBackPoop) {
                            playButtonBitmap = playDarkButtonBitmap[playTimer];
                            if (playTimer == 1) {
                                playChecker = true;
                                lastTouchX = 0;
                                lastTouchY = 0;
                            }
                        }
                        canvas.drawBitmap(playButtonBitmap, (float) canvas.getWidth() * playButtonLeft, (float) canvas.getHeight() * playButtonTop, paint);
                        //qnelu knopken
                        if (!sleepChecker && checkSleepButton && !pooping && !flyPoop && !disgusting && !flyBackPoop) {
                            new SleepDarkButtonThread().start();
                            checkSleepButton = false;
                        }
                        if (drawSleepButton && !pooping && !flyPoop && !disgusting && !flyBackPoop) {
                            if (sleepTimer >= 0)
                                sleepButtonBitmap = sleepDarkButtonBitmap[sleepTimer];
                            if (sleepTimer == 0) {
                                sleepChecker = true;
                                lastTouchX = 0;
                                lastTouchY = 0;
                                m5 = 0;
                            }
                        }
                        canvas.drawBitmap(sleepButtonBitmap, (float) canvas.getWidth() * sleepButtonLeft, (float) canvas.getHeight() * sleepButtonTop, paint);
                        // maqrvelu knopka
                        if (dirtRight - dirtLeft >= dirtWeight / 2. && !pooping && !flyPoop && !disgusting && !flyBackPoop)
                            washButtonBitmap = washDarkButtonBitmap;
                        if (dirtRight - dirtLeft <= dirtWeight / 2. && !pooping && !flyPoop && !disgusting && !flyBackPoop)
                            washButtonBitmap = washButtonBitmap2;
                        canvas.drawBitmap(washButtonBitmap, (float) canvas.getWidth() * dirtButtonLeft, (float) canvas.getHeight() * dirtButtonTop, paint);
                        //qaqel
                        if (m5 == 0) {
                            new Minute().start();
                            m5 = 1;
                        }
                        if (eatScore >= 4 && pop && !pooping && !eating && !playing && !flying && !sleeping && !laying && !hitting && !washing) {
                            eatButtonBitmap2 = eatButtonBitmap;
                            sleepButtonBitmap2 = sleepButtonBitmap;
                            playButtonBitmap2 = playButtonBitmap;
                            washButtonBitmap3 = washButtonBitmap;
                            eatButtonBitmap = eatButtonBitmapPoop;
                            sleepButtonBitmap = sleepButtonBitmapPoop;
                            playButtonBitmap = playButtonBitmapPoop;
                            washButtonBitmap = washButtonBitmapPoop;
                            po = true;
                        }
                        if (po && !isTouched) {
                            if (poop1 > 4) {
                                poop1 = 1;
                            }
                            canvas.drawBitmap(poopBitmap[poop1], (float) canvas.getWidth() * poopX, (float) canvas.getHeight() * poopY, paint);
                        }
                        if (po && !poopingTimeIsPassed1) {
                            new PoopThread().start();
                            pooping = true;
                            poopingTimeIsPassed1 = true;
                        }
                        if (po && !poopingTimeIsPassed2) {
                            new PoopFlyThread().start();
                            flyPoop = true;
                            poopingTimeIsPassed2 = true;
                        }
                        if (poopingNeedToDrawNow2 && flyPoop) {
                            switch (poop2) {
                                case 1:
                                    if (bitmap1 == bitmapDTSH1) bitmap = flyBitmapTDSH[4];
                                    else if (bitmap1 == bitmapDH1) bitmap = flyBitmapDH[4];
                                    else if (bitmap1 == bitmapDS1) bitmap = flyBitmapDS[4];
                                    else if (bitmap1 == bitmapD1) bitmap = flyBitmapD[4];
                                    else if (bitmap1 == bitmapH1) bitmap = flyBitmapH[4];
                                    else if (bitmap1 == bitmapSmile1) bitmap = flyBitmapSmile[4];
                                    else if (bitmap1 == bitmapSH1) bitmap = flyBitmapSH[4];
                                    else if (bitmap1 == bitmapDSH1) bitmap = flyBitmapSDH[4];
                                    else if (bitmap1 == bitmapS1) bitmap = flyBitmapS[4];
                                    else if (bitmap1 == bitmapDTH1) bitmap = flyBitmapTDH[4];
                                    else if (bitmap1 == bitmapDTS1) bitmap = flyBitmapTDS[4];
                                    else if (bitmap1 == bitmapDT1) bitmap = flyBitmapTD[4];
                                    else if (bitmap1 == bitmapTH1) bitmap = flyBitmapTH[4];
                                    else if (bitmap1 == bitmapTSH1) bitmap = flyBitmapTSH[4];
                                    else if (bitmap1 == bitmapTS1) bitmap = flyBitmapTS[4];
                                    else if (bitmap1 == bitmapT1) bitmap = flyBitmapT[4];
                                    else if (bitmap1 == bitmapUsual1) bitmap = flyBitmapUsual[4];
                                    break;
                                case 2:
                                    if (bitmap1 == bitmapDTSH1) bitmap = flyBitmapTDSH[3];
                                    else if (bitmap1 == bitmapDH1) bitmap = flyBitmapDH[3];
                                    else if (bitmap1 == bitmapDS1) bitmap = flyBitmapDS[3];
                                    else if (bitmap1 == bitmapD1) bitmap = flyBitmapD[3];
                                    else if (bitmap1 == bitmapH1) bitmap = flyBitmapH[3];
                                    else if (bitmap1 == bitmapSmile1) bitmap = flyBitmapSmile[3];
                                    else if (bitmap1 == bitmapSH1) bitmap = flyBitmapSH[3];
                                    else if (bitmap1 == bitmapDSH1) bitmap = flyBitmapSDH[3];
                                    else if (bitmap1 == bitmapS1) bitmap = flyBitmapS[3];
                                    else if (bitmap1 == bitmapDTH1) bitmap = flyBitmapTDH[3];
                                    else if (bitmap1 == bitmapDTS1) bitmap = flyBitmapTDS[3];
                                    else if (bitmap1 == bitmapDT1) bitmap = flyBitmapTD[3];
                                    else if (bitmap1 == bitmapTH1) bitmap = flyBitmapTH[3];
                                    else if (bitmap1 == bitmapTSH1) bitmap = flyBitmapTSH[3];
                                    else if (bitmap1 == bitmapTS1) bitmap = flyBitmapTS[3];
                                    else if (bitmap1 == bitmapT1) bitmap = flyBitmapT[3];
                                    else if (bitmap1 == bitmapUsual1) bitmap = flyBitmapUsual[3];
                                    birdX = (float) 548 / 1050;
                                    birdY = (float) 176 / 540;
                                    break;
                                case 3:
                                    if (bitmap1 == bitmapDTSH1) bitmap = flyBitmapTDSH[4];
                                    else if (bitmap1 == bitmapDH1) bitmap = flyBitmapDH[4];
                                    else if (bitmap1 == bitmapDS1) bitmap = flyBitmapDS[4];
                                    else if (bitmap1 == bitmapD1) bitmap = flyBitmapD[4];
                                    else if (bitmap1 == bitmapH1) bitmap = flyBitmapH[4];
                                    else if (bitmap1 == bitmapSmile1) bitmap = flyBitmapSmile[4];
                                    else if (bitmap1 == bitmapSH1) bitmap = flyBitmapSH[4];
                                    else if (bitmap1 == bitmapDSH1) bitmap = flyBitmapSDH[4];
                                    else if (bitmap1 == bitmapS1) bitmap = flyBitmapS[4];
                                    else if (bitmap1 == bitmapDTH1) bitmap = flyBitmapTDH[4];
                                    else if (bitmap1 == bitmapDTS1) bitmap = flyBitmapTDS[4];
                                    else if (bitmap1 == bitmapDT1) bitmap = flyBitmapTD[4];
                                    else if (bitmap1 == bitmapTH1) bitmap = flyBitmapTH[4];
                                    else if (bitmap1 == bitmapTSH1) bitmap = flyBitmapTSH[4];
                                    else if (bitmap1 == bitmapTS1) bitmap = flyBitmapTS[4];
                                    else if (bitmap1 == bitmapT1) bitmap = flyBitmapT[4];
                                    else if (bitmap1 == bitmapUsual1) bitmap = flyBitmapUsual[4];
                                    birdX = (float) 625 / 1050;
                                    birdY = (float) 203 / 540;
                                    break;
                                case 4:
                                    if (bitmap1 == bitmapDTSH1) bitmap = flyBitmapTDSH[3];
                                    else if (bitmap1 == bitmapDH1) bitmap = flyBitmapDH[3];
                                    else if (bitmap1 == bitmapDS1) bitmap = flyBitmapDS[3];
                                    else if (bitmap1 == bitmapD1) bitmap = flyBitmapD[3];
                                    else if (bitmap1 == bitmapH1) bitmap = flyBitmapH[3];
                                    else if (bitmap1 == bitmapSmile1) bitmap = flyBitmapSmile[3];
                                    else if (bitmap1 == bitmapSH1) bitmap = flyBitmapSH[3];
                                    else if (bitmap1 == bitmapDSH1) bitmap = flyBitmapSDH[3];
                                    else if (bitmap1 == bitmapS1) bitmap = flyBitmapS[3];
                                    else if (bitmap1 == bitmapDTH1) bitmap = flyBitmapTDH[3];
                                    else if (bitmap1 == bitmapDTS1) bitmap = flyBitmapTDS[3];
                                    else if (bitmap1 == bitmapDT1) bitmap = flyBitmapTD[3];
                                    else if (bitmap1 == bitmapTH1) bitmap = flyBitmapTH[3];
                                    else if (bitmap1 == bitmapTSH1) bitmap = flyBitmapTSH[3];
                                    else if (bitmap1 == bitmapTS1) bitmap = flyBitmapTS[3];
                                    else if (bitmap1 == bitmapT1) bitmap = flyBitmapT[3];
                                    else if (bitmap1 == bitmapUsual1) bitmap = flyBitmapUsual[3];
                                    birdX = (float) 715 / 1050;
                                    birdY = (float) 195 / 540;
                                    break;
                                case 5:
                                    if (bitmap1 == bitmapDTSH1) bitmap = flyBitmapTDSH[4];
                                    else if (bitmap1 == bitmapDH1) bitmap = flyBitmapDH[4];
                                    else if (bitmap1 == bitmapDS1) bitmap = flyBitmapDS[4];
                                    else if (bitmap1 == bitmapD1) bitmap = flyBitmapD[4];
                                    else if (bitmap1 == bitmapH1) bitmap = flyBitmapH[4];
                                    else if (bitmap1 == bitmapSmile1) bitmap = flyBitmapSmile[4];
                                    else if (bitmap1 == bitmapSH1) bitmap = flyBitmapSH[4];
                                    else if (bitmap1 == bitmapDSH1) bitmap = flyBitmapSDH[4];
                                    else if (bitmap1 == bitmapS1) bitmap = flyBitmapS[4];
                                    else if (bitmap1 == bitmapDTH1) bitmap = flyBitmapTDH[4];
                                    else if (bitmap1 == bitmapDTS1) bitmap = flyBitmapTDS[4];
                                    else if (bitmap1 == bitmapDT1) bitmap = flyBitmapTD[4];
                                    else if (bitmap1 == bitmapTH1) bitmap = flyBitmapTH[4];
                                    else if (bitmap1 == bitmapTSH1) bitmap = flyBitmapTSH[4];
                                    else if (bitmap1 == bitmapTS1) bitmap = flyBitmapTS[4];
                                    else if (bitmap1 == bitmapT1) bitmap = flyBitmapT[4];
                                    else if (bitmap1 == bitmapUsual1) bitmap = flyBitmapUsual[4];
                                    birdX = (float) 795 / 1050;
                                    birdY = (float) 232 / 540;
                                    break;
                                case 6:
                                    disgusting = true;
                                    flyPoop = false;
                                    break;
                            }
                        }
                        if (disgusting && !disgustingTimeIsPassed) {
                            new DisgustThread().start();
                            disgustingTimeIsPassed = true;
                        }
                        if (disgustingNeedToDrawNow && disgusting) {
                            if (disgust >= 11) {
                                disgust = 1;
                            }
                            if (bitmap1 == bitmapUsual1 || bitmap1 == bitmapSmile1)
                                bitmap = poopingBitmapUsual[disgust];
                            else if (bitmap1 == bitmapDSH1) bitmap = poopingBitmapDSH[disgust];
                            else if (bitmap1 == bitmapDTS1) bitmap = poopingBitmapDTS[disgust];
                            else if (bitmap1 == bitmapDTH1) bitmap = poopingBitmapDTH[disgust];
                            else if (bitmap1 == bitmapDH1) bitmap = poopingBitmapDH[disgust];
                            else if (bitmap1 == bitmapDT1) bitmap = poopingBitmapDT[disgust];
                            else if (bitmap1 == bitmapDS1) bitmap = poopingBitmapDS[disgust];
                            else if (bitmap1 == bitmapD1) bitmap = poopingBitmapD[disgust];
                            else if (bitmap1 == bitmapTSH1) bitmap = poopingBitmapTHS[disgust];
                            else if (bitmap1 == bitmapSH1) bitmap = poopingBitmapHS[disgust];
                            else if (bitmap1 == bitmapTH1) bitmap = poopingBitmapTH[disgust];
                            else if (bitmap1 == bitmapH1) bitmap = poopingBitmapH[disgust];
                            else if (bitmap1 == bitmapTS1) bitmap = poopingBitmapTS[disgust];
                            else if (bitmap1 == bitmapT1) bitmap = poopingBitmapT[disgust];
                            else if (bitmap1 == bitmapS1) bitmap = poopingBitmapS[disgust];
                        }
                        if (disgusting && lastTouchX >= poopX && lastTouchX <= poopX + poopWidth && lastTouchY >= poopY && lastTouchY <= poopY + poopHeight) {
                            new PoopFlyBackThread().start();
                            isTouched = true;
                            disgusting = false;
                            flyBackPoop = true;
                        }
                        if (isTouched && !poopingTimeIsPassed3) {
                            new PoopFlyBackThread().start();
                            poopingTimeIsPassed3 = true;
                        }
                        if (poopingNeedToDrawNow3 && flyBackPoop) {
                            switch (poop3) {
                                case 1:
                                    if (bitmap1 == bitmapDTSH1) bitmap = flyBitmapTDSH[2];
                                    else if (bitmap1 == bitmapDH1) bitmap = flyBitmapDH[2];
                                    else if (bitmap1 == bitmapDS1) bitmap = flyBitmapDS[2];
                                    else if (bitmap1 == bitmapD1) bitmap = flyBitmapD[2];
                                    else if (bitmap1 == bitmapH1) bitmap = flyBitmapH[2];
                                    else if (bitmap1 == bitmapSmile1) bitmap = flyBitmapSmile[2];
                                    else if (bitmap1 == bitmapSH1) bitmap = flyBitmapSH[2];
                                    else if (bitmap1 == bitmapDSH1) bitmap = flyBitmapSDH[2];
                                    else if (bitmap1 == bitmapS1) bitmap = flyBitmapS[2];
                                    else if (bitmap1 == bitmapDTH1) bitmap = flyBitmapTDH[2];
                                    else if (bitmap1 == bitmapDTS1) bitmap = flyBitmapTDS[2];
                                    else if (bitmap1 == bitmapDT1) bitmap = flyBitmapTD[2];
                                    else if (bitmap1 == bitmapTH1) bitmap = flyBitmapTH[2];
                                    else if (bitmap1 == bitmapTSH1) bitmap = flyBitmapTSH[2];
                                    else if (bitmap1 == bitmapTS1) bitmap = flyBitmapTS[2];
                                    else if (bitmap1 == bitmapT1) bitmap = flyBitmapT[2];
                                    else if (bitmap1 == bitmapUsual1) bitmap = flyBitmapUsual[2];
                                    break;
                                case 2:
                                    if (bitmap1 == bitmapDTSH1) bitmap = flyBitmapTDSH[1];
                                    else if (bitmap1 == bitmapDH1) bitmap = flyBitmapDH[1];
                                    else if (bitmap1 == bitmapDS1) bitmap = flyBitmapDS[1];
                                    else if (bitmap1 == bitmapD1) bitmap = flyBitmapD[1];
                                    else if (bitmap1 == bitmapH1) bitmap = flyBitmapH[1];
                                    else if (bitmap1 == bitmapSmile1) bitmap = flyBitmapSmile[1];
                                    else if (bitmap1 == bitmapSH1) bitmap = flyBitmapSH[1];
                                    else if (bitmap1 == bitmapDSH1) bitmap = flyBitmapSDH[1];
                                    else if (bitmap1 == bitmapS1) bitmap = flyBitmapS[1];
                                    else if (bitmap1 == bitmapDTH1) bitmap = flyBitmapTDH[1];
                                    else if (bitmap1 == bitmapDTS1) bitmap = flyBitmapTDS[1];
                                    else if (bitmap1 == bitmapDT1) bitmap = flyBitmapTD[1];
                                    else if (bitmap1 == bitmapTH1) bitmap = flyBitmapTH[1];
                                    else if (bitmap1 == bitmapTSH1) bitmap = flyBitmapTSH[1];
                                    else if (bitmap1 == bitmapTS1) bitmap = flyBitmapTS[1];
                                    else if (bitmap1 == bitmapT1) bitmap = flyBitmapT[1];
                                    else if (bitmap1 == bitmapUsual1) bitmap = flyBitmapUsual[1];
                                    birdX = (float) 715 / 1050;
                                    birdY = (float) 195 / 540;
                                    break;
                                case 3:
                                    if (bitmap1 == bitmapDTSH1) bitmap = flyBitmapTDSH[2];
                                    else if (bitmap1 == bitmapDH1) bitmap = flyBitmapDH[2];
                                    else if (bitmap1 == bitmapDS1) bitmap = flyBitmapDS[2];
                                    else if (bitmap1 == bitmapD1) bitmap = flyBitmapD[2];
                                    else if (bitmap1 == bitmapH1) bitmap = flyBitmapH[2];
                                    else if (bitmap1 == bitmapSmile1) bitmap = flyBitmapSmile[2];
                                    else if (bitmap1 == bitmapSH1) bitmap = flyBitmapSH[2];
                                    else if (bitmap1 == bitmapDSH1) bitmap = flyBitmapSDH[2];
                                    else if (bitmap1 == bitmapS1) bitmap = flyBitmapS[2];
                                    else if (bitmap1 == bitmapDTH1) bitmap = flyBitmapTDH[2];
                                    else if (bitmap1 == bitmapDTS1) bitmap = flyBitmapTDS[2];
                                    else if (bitmap1 == bitmapDT1) bitmap = flyBitmapTD[2];
                                    else if (bitmap1 == bitmapTH1) bitmap = flyBitmapTH[2];
                                    else if (bitmap1 == bitmapTSH1) bitmap = flyBitmapTSH[2];
                                    else if (bitmap1 == bitmapTS1) bitmap = flyBitmapTS[2];
                                    else if (bitmap1 == bitmapT1) bitmap = flyBitmapT[2];
                                    else if (bitmap1 == bitmapUsual1) bitmap = flyBitmapUsual[2];
                                    birdX = (float) 625 / 1050;
                                    birdY = (float) 203 / 540;
                                    break;
                                case 4:
                                    if (bitmap1 == bitmapDTSH1) bitmap = flyBitmapTDSH[1];
                                    else if (bitmap1 == bitmapDH1) bitmap = flyBitmapDH[1];
                                    else if (bitmap1 == bitmapDS1) bitmap = flyBitmapDS[1];
                                    else if (bitmap1 == bitmapD1) bitmap = flyBitmapD[1];
                                    else if (bitmap1 == bitmapH1) bitmap = flyBitmapH[1];
                                    else if (bitmap1 == bitmapSmile1) bitmap = flyBitmapSmile[1];
                                    else if (bitmap1 == bitmapSH1) bitmap = flyBitmapSH[1];
                                    else if (bitmap1 == bitmapDSH1) bitmap = flyBitmapSDH[1];
                                    else if (bitmap1 == bitmapS1) bitmap = flyBitmapS[1];
                                    else if (bitmap1 == bitmapDTH1) bitmap = flyBitmapTDH[1];
                                    else if (bitmap1 == bitmapDTS1) bitmap = flyBitmapTDS[1];
                                    else if (bitmap1 == bitmapDT1) bitmap = flyBitmapTD[1];
                                    else if (bitmap1 == bitmapTH1) bitmap = flyBitmapTH[1];
                                    else if (bitmap1 == bitmapTSH1) bitmap = flyBitmapTSH[1];
                                    else if (bitmap1 == bitmapTS1) bitmap = flyBitmapTS[1];
                                    else if (bitmap1 == bitmapT1) bitmap = flyBitmapT[1];
                                    else if (bitmap1 == bitmapUsual1) bitmap = flyBitmapUsual[1];
                                    birdX = (float) 548 / 1050;
                                    birdY = (float) 176 / 540;
                                    break;
                                case 5:
                                    if (bitmap1 == bitmapDTSH1) bitmap = flyBitmapTDSH[2];
                                    else if (bitmap1 == bitmapDH1) bitmap = flyBitmapDH[2];
                                    else if (bitmap1 == bitmapDS1) bitmap = flyBitmapDS[2];
                                    else if (bitmap1 == bitmapD1) bitmap = flyBitmapD[2];
                                    else if (bitmap1 == bitmapH1) bitmap = flyBitmapH[2];
                                    else if (bitmap1 == bitmapSmile1) bitmap = flyBitmapSmile[2];
                                    else if (bitmap1 == bitmapSH1) bitmap = flyBitmapSH[2];
                                    else if (bitmap1 == bitmapDSH1) bitmap = flyBitmapSDH[2];
                                    else if (bitmap1 == bitmapS1) bitmap = flyBitmapS[2];
                                    else if (bitmap1 == bitmapDTH1) bitmap = flyBitmapTDH[2];
                                    else if (bitmap1 == bitmapDTS1) bitmap = flyBitmapTDS[2];
                                    else if (bitmap1 == bitmapDT1) bitmap = flyBitmapTD[2];
                                    else if (bitmap1 == bitmapTH1) bitmap = flyBitmapTH[2];
                                    else if (bitmap1 == bitmapTSH1) bitmap = flyBitmapTSH[2];
                                    else if (bitmap1 == bitmapTS1) bitmap = flyBitmapTS[2];
                                    else if (bitmap1 == bitmapT1) bitmap = flyBitmapT[2];
                                    else if (bitmap1 == bitmapUsual1) bitmap = flyBitmapUsual[2];
                                    birdX = (float) 419 / 1050;
                                    birdY = (float) 232 / 540;
                                    break;
                                case 6:
                                    bitmap = bitmap1;
                                    flyBackPoop = false;
                                    isTouched = false;
                                    pop = false;
                                    po = false;
                                    pooping = false;
                                    eatScore = 0;
                                    poop3 = 0;
                                    poop2 = 1;
                                    poop1 = 1;
                                    lastTouchX = 0;
                                    lastTouchY = 0;
                                    m5 = 0;
                                    disgust = 1;
                                    poopingTimeIsPassed1 = false;
                                    poopingTimeIsPassed2 = false;
                                    poopingTimeIsPassed3 = false;
                                    poopingNeedToDrawNow1 = false;
                                    poopingNeedToDrawNow2 = false;
                                    poopingNeedToDrawNow3 = false;
                                    disgustingTimeIsPassed = false;
                                    disgustingNeedToDrawNow = false;
                                    eatButtonBitmap = eatButtonBitmap2;
                                    sleepButtonBitmap = sleepButtonBitmap2;
                                    playButtonBitmap = playButtonBitmap2;
                                    washButtonBitmap = washButtonBitmap3;
                                    break;
                            }
                        }
                        // utel
                        if (lastTouchX >= eatButtonLeft * canvas.getWidth() && lastTouchX <= (eatButtonLeft + ButtonWidth) * canvas.getWidth() && lastTouchY >= eatButtonTop * canvas.getHeight() && lastTouchY <= (eatButtonTop + ButtonHeight) * canvas.getHeight() && eatChecker && !playing && !sleeping && !laying && !flying && !flyingBack && !hitting && !washing && !pooping && !flyPoop && !disgusting && !flyBackPoop && !ea) {
                            ea = true;
                            gettingFoin = true;
                        }
                        if (!eatingTimeIsPassed && ea) {
                            new EatingThread().start();
                            eating = true;
                            eatingTimeIsPassed = true;
                        }
                        if (eatingNeedToDrawNow && eating) {
                            if (eat >= 1) {
                                if (bitmap1 == bitmapDTS1 || bitmap1 == bitmapDTSH1)
                                    bitmap = eatBitmapDTS[eat];
                                else if (bitmap1 == bitmapDT1 || bitmap1 == bitmapDTH1)
                                    bitmap = eatBitmapDT[eat];
                                else if (bitmap1 == bitmapDS1 || bitmap1 == bitmapDSH1)
                                    bitmap = eatBitmapDS[eat];
                                else if (bitmap1 == bitmapTS1 || bitmap1 == bitmapTSH1)
                                    bitmap = eatBitmapTS[eat];
                                else if (bitmap1 == bitmapD1 || bitmap1 == bitmapDH1)
                                    bitmap = eatBitmapD[eat];
                                else if (bitmap1 == bitmapS1 || bitmap1 == bitmapSH1)
                                    bitmap = eatBitmapS[eat];
                                else if (bitmap1 == bitmapT1 || bitmap1 == bitmapTH1)
                                    bitmap = eatBitmapT[eat];
                                else if (bitmap1 == bitmapUsual1 || bitmap1 == bitmapH1)
                                    bitmap = eatBitmap[eat];
                                else if (bitmap1 == bitmapSmile1) bitmap = eatBitmapSmile[eat];
                            }
                            if (eat == 9) {
                                if (e == 0) {
                                    food *= (hungryRight2 - hungryLeft);
                                    if (hungryRight + food > hungryRight2) {
                                        hungryRight = hungryRight2;
                                    } else {
                                        hungryRight += food;
                                    }
                                    editor.putFloat("HUNGRY", hungryRight);
                                    editor.apply();
                                    food /= (hungryRight2 - hungryLeft);
                                    e = 1;
                                }
                            }
                            if (eat == 10) {
                                eating = false;
                                eat = 0;
                                lastTouchY = 0;
                                lastTouchX = 0;
                                eatChecker = false;
                                checkEatButton = true;
                                e = 0;
                                ea = false;
                                eatButtonBitmap = eatDarkButtonBitmap[10];
                                eatTimer = 10;
                                eatScore++;
                            }
                        }
                        //xndal
                        if (lastTouchX >= playButtonLeft * canvas.getWidth() && lastTouchX <= (playButtonLeft + ButtonWidth) * canvas.getWidth() && lastTouchY >= playButtonTop * canvas.getHeight() && lastTouchY <= (playButtonTop + ButtonHeight) * canvas.getHeight() && playChecker && !eating && !sleeping && !laying && !flying && !flyingBack && !hitting && !washing && !pooping && !flyPoop && !disgusting && !flyBackPoop && !pl) {
                            pl = true;
                            gettingFoin = true;
                            isSinging = true;
                        }
                        if (isSinging && pl) {
                            mediaPlayerHappy.start();
                            isSinging = false;
                        }
                        if (!playingTimeIsPassed && pl) {
                            new PlayingThread().start();
                            playing = true;
                            playingTimeIsPassed = true;
                        }
                        if (playingNeedToDrawNow && playing) {
                            smile *= (happyRight2 - happyLeft);
                            if (happyRight + smile > happyRight2) {
                                happyRight = happyRight2;
                            } else {
                                happyRight += smile;
                            }
                            editor.putFloat("HAPPY", happyRight);
                            editor.apply();
                            smile /= (happyRight2 - happyLeft);
                            if (bitmap1 == bitmapDT1 || bitmap1 == bitmapDTH1 || bitmap1 == bitmapDTS1 || bitmap1 == bitmapDTSH1)
                                bitmap = playBitmapDT[play];
                            else if (bitmap1 == bitmapD1 || bitmap1 == bitmapDH1 || bitmap1 == bitmapDS1 || bitmap1 == bitmapDSH1)
                                bitmap = playBitmapD[play];
                            else if (bitmap1 == bitmapT1 || bitmap1 == bitmapTH1 || bitmap1 == bitmapTS1 || bitmap1 == bitmapTSH1)
                                bitmap = playBitmapT[play];
                            else if (bitmap1 == bitmapUsual1 || bitmap1 == bitmapH1 || bitmap1 == bitmapS1 || bitmap1 == bitmapSmile1)
                                bitmap = playBitmap[play];
                            if (play == 20) {
                                playing = false;
                                playChecker = false;
                                checkPlayButton = true;
                                play = 0;
                                lastTouchY = 0;
                                lastTouchX = 0;
                                p = 0;
                                pl = false;
                                playButtonBitmap = playDarkButtonBitmap[15];
                                playTimer = 15;
                                mediaPlayerHappy.stop();
                                mediaPlayerHappy.prepare();
                            }
                        }
                        //qnel
                        if (lastTouchX >= sleepButtonLeft * canvas.getWidth() && lastTouchX <= (sleepButtonLeft + ButtonWidth) * canvas.getWidth() && lastTouchY >= sleepButtonTop * canvas.getHeight() && lastTouchY <= (sleepButtonTop + ButtonHeight) * canvas.getHeight() && !sleepFinished && sleepChecker && !playing && !eating && !hitting && !washing && !pooping && !flyPoop && !disgusting && !flyBackPoop && !fl) {
                            fl = true;
                            gettingFoin = true;
                        }
                        if (!flyingTimeIsPassed && fl && !sleepFinished) {
                            new FlyingThread().start();
                            flying = true;
                            flyingTimeIsPassed = true;
                        }
                        if (flyingNeedToDrawNow && flying && !sleepFinished && !sleeping) {
                            switch (sleep) {
                                case 1:
                                    if (bitmap1 == bitmapDTSH1) bitmap = flyBitmapTDSH[2];
                                    else if (bitmap1 == bitmapDH1) bitmap = flyBitmapDH[2];
                                    else if (bitmap1 == bitmapDS1) bitmap = flyBitmapDS[2];
                                    else if (bitmap1 == bitmapD1) bitmap = flyBitmapD[2];
                                    else if (bitmap1 == bitmapH1) bitmap = flyBitmapH[2];
                                    else if (bitmap1 == bitmapSmile1) bitmap = flyBitmapSmile[2];
                                    else if (bitmap1 == bitmapSH1) bitmap = flyBitmapSH[2];
                                    else if (bitmap1 == bitmapDSH1) bitmap = flyBitmapSDH[2];
                                    else if (bitmap1 == bitmapS1) bitmap = flyBitmapS[2];
                                    else if (bitmap1 == bitmapDTH1) bitmap = flyBitmapTDH[2];
                                    else if (bitmap1 == bitmapDTS1) bitmap = flyBitmapTDS[2];
                                    else if (bitmap1 == bitmapDT1) bitmap = flyBitmapTD[2];
                                    else if (bitmap1 == bitmapTH1) bitmap = flyBitmapTH[2];
                                    else if (bitmap1 == bitmapTSH1) bitmap = flyBitmapTSH[2];
                                    else if (bitmap1 == bitmapTS1) bitmap = flyBitmapTS[2];
                                    else if (bitmap1 == bitmapT1) bitmap = flyBitmapT[2];
                                    else if (bitmap1 == bitmapUsual1) bitmap = flyBitmapUsual[2];
                                    break;
                                case 2:
                                    if (bitmap1 == bitmapDTSH1) bitmap = flyBitmapTDSH[1];
                                    else if (bitmap1 == bitmapDH1) bitmap = flyBitmapDH[1];
                                    else if (bitmap1 == bitmapDS1) bitmap = flyBitmapDS[1];
                                    else if (bitmap1 == bitmapD1) bitmap = flyBitmapD[1];
                                    else if (bitmap1 == bitmapH1) bitmap = flyBitmapH[1];
                                    else if (bitmap1 == bitmapSmile1) bitmap = flyBitmapSmile[1];
                                    else if (bitmap1 == bitmapSH1) bitmap = flyBitmapSH[1];
                                    else if (bitmap1 == bitmapDSH1) bitmap = flyBitmapSDH[1];
                                    else if (bitmap1 == bitmapS1) bitmap = flyBitmapS[1];
                                    else if (bitmap1 == bitmapDTH1) bitmap = flyBitmapTDH[1];
                                    else if (bitmap1 == bitmapDTS1) bitmap = flyBitmapTDS[1];
                                    else if (bitmap1 == bitmapDT1) bitmap = flyBitmapTD[1];
                                    else if (bitmap1 == bitmapTH1) bitmap = flyBitmapTH[1];
                                    else if (bitmap1 == bitmapTSH1) bitmap = flyBitmapTSH[1];
                                    else if (bitmap1 == bitmapTS1) bitmap = flyBitmapTS[1];
                                    else if (bitmap1 == bitmapT1) bitmap = flyBitmapT[1];
                                    else if (bitmap1 == bitmapUsual1) bitmap = flyBitmapUsual[1];
                                    birdX = (float) 353 / 1050;
                                    birdY = (float) 238 / 540;
                                    break;
                                case 3:
                                    if (bitmap1 == bitmapDTSH1) bitmap = flyBitmapTDSH[2];
                                    else if (bitmap1 == bitmapDH1) bitmap = flyBitmapDH[2];
                                    else if (bitmap1 == bitmapDS1) bitmap = flyBitmapDS[2];
                                    else if (bitmap1 == bitmapD1) bitmap = flyBitmapD[2];
                                    else if (bitmap1 == bitmapH1) bitmap = flyBitmapH[2];
                                    else if (bitmap1 == bitmapSmile1) bitmap = flyBitmapSmile[2];
                                    else if (bitmap1 == bitmapSH1) bitmap = flyBitmapSH[2];
                                    else if (bitmap1 == bitmapDSH1) bitmap = flyBitmapSDH[2];
                                    else if (bitmap1 == bitmapS1) bitmap = flyBitmapS[2];
                                    else if (bitmap1 == bitmapDTH1) bitmap = flyBitmapTDH[2];
                                    else if (bitmap1 == bitmapDTS1) bitmap = flyBitmapTDS[2];
                                    else if (bitmap1 == bitmapDT1) bitmap = flyBitmapTD[2];
                                    else if (bitmap1 == bitmapTH1) bitmap = flyBitmapTH[2];
                                    else if (bitmap1 == bitmapTSH1) bitmap = flyBitmapTSH[2];
                                    else if (bitmap1 == bitmapTS1) bitmap = flyBitmapTS[2];
                                    else if (bitmap1 == bitmapT1) bitmap = flyBitmapT[2];
                                    else if (bitmap1 == bitmapUsual1) bitmap = flyBitmapUsual[2];
                                    birdX = (float) 287 / 1050;
                                    birdY = (float) 226 / 540;
                                    break;
                                case 4:
                                    if (bitmap1 == bitmapDTSH1) bitmap = flyBitmapTDSH[1];
                                    else if (bitmap1 == bitmapDH1) bitmap = flyBitmapDH[1];
                                    else if (bitmap1 == bitmapDS1) bitmap = flyBitmapDS[1];
                                    else if (bitmap1 == bitmapD1) bitmap = flyBitmapD[1];
                                    else if (bitmap1 == bitmapH1) bitmap = flyBitmapH[1];
                                    else if (bitmap1 == bitmapSmile1) bitmap = flyBitmapSmile[1];
                                    else if (bitmap1 == bitmapSH1) bitmap = flyBitmapSH[1];
                                    else if (bitmap1 == bitmapDSH1) bitmap = flyBitmapSDH[1];
                                    else if (bitmap1 == bitmapS1) bitmap = flyBitmapS[1];
                                    else if (bitmap1 == bitmapDTH1) bitmap = flyBitmapTDH[1];
                                    else if (bitmap1 == bitmapDTS1) bitmap = flyBitmapTDS[1];
                                    else if (bitmap1 == bitmapDT1) bitmap = flyBitmapTD[1];
                                    else if (bitmap1 == bitmapTH1) bitmap = flyBitmapTH[1];
                                    else if (bitmap1 == bitmapTSH1) bitmap = flyBitmapTSH[1];
                                    else if (bitmap1 == bitmapTS1) bitmap = flyBitmapTS[1];
                                    else if (bitmap1 == bitmapT1) bitmap = flyBitmapT[1];
                                    else if (bitmap1 == bitmapUsual1) bitmap = flyBitmapUsual[1];
                                    birdX = (float) 230 / 1050;
                                    birdY = (float) 232 / 540;
                                    break;
                                case 5:
                                    if (bitmap1 == bitmapDTSH1) bitmap = flyBitmapTDSH[2];
                                    else if (bitmap1 == bitmapDH1) bitmap = flyBitmapDH[2];
                                    else if (bitmap1 == bitmapDS1) bitmap = flyBitmapDS[2];
                                    else if (bitmap1 == bitmapD1) bitmap = flyBitmapD[2];
                                    else if (bitmap1 == bitmapH1) bitmap = flyBitmapH[2];
                                    else if (bitmap1 == bitmapSmile1) bitmap = flyBitmapSmile[2];
                                    else if (bitmap1 == bitmapSH1) bitmap = flyBitmapSH[2];
                                    else if (bitmap1 == bitmapDSH1) bitmap = flyBitmapSDH[2];
                                    else if (bitmap1 == bitmapS1) bitmap = flyBitmapS[2];
                                    else if (bitmap1 == bitmapDTH1) bitmap = flyBitmapTDH[2];
                                    else if (bitmap1 == bitmapDTS1) bitmap = flyBitmapTDS[2];
                                    else if (bitmap1 == bitmapDT1) bitmap = flyBitmapTD[2];
                                    else if (bitmap1 == bitmapTH1) bitmap = flyBitmapTH[2];
                                    else if (bitmap1 == bitmapTSH1) bitmap = flyBitmapTSH[2];
                                    else if (bitmap1 == bitmapTS1) bitmap = flyBitmapTS[2];
                                    else if (bitmap1 == bitmapT1) bitmap = flyBitmapT[2];
                                    else if (bitmap1 == bitmapUsual1) bitmap = flyBitmapUsual[2];
                                    birdX = (float) 159 / 1050;
                                    birdY = (float) 193 / 540;
                                    break;
                                case 6:
                                    bitmap = bitmap1;
                                    birdX = (float) 48 / 1050;
                                    birdY = (float) 218 / 540;
                                    break;
                                case 7:
                                    birdX = (float) 60 / 1050;
                                    birdY = (float) 207 / 540;
                                    laying = true;
                                    flying = false;
                                    if (bitmap1 == bitmapDTSH1 || bitmap1 == bitmapDSH1)
                                        bitmap = sleepDSH1;
                                    else if (bitmap1 == bitmapDTS1 || bitmap1 == bitmapDS1)
                                        bitmap = sleepDS1;
                                    else if (bitmap1 == bitmapDTH1 || bitmap1 == bitmapDH1)
                                        bitmap = sleepDH1;
                                    else if (bitmap1 == bitmapDT1 || bitmap1 == bitmapD1)
                                        bitmap = sleepD1;
                                    else if (bitmap1 == bitmapTSH1 || bitmap1 == bitmapSH1)
                                        bitmap = sleepSH1;
                                    else if (bitmap1 == bitmapTS1 || bitmap1 == bitmapS1)
                                        bitmap = sleepS1;
                                    else if (bitmap1 == bitmapTH1 || bitmap1 == bitmapH1)
                                        bitmap = sleepH1;
                                    else if (bitmap1 == bitmapT1 || bitmap1 == bitmapUsual1)
                                        bitmap = sleepUsual1;
                                    else if (bitmap1 == bitmapSmile1) bitmap = sleepSmile1;
                                    sleepButtonBitmap = sleepDarkButtonBitmap[60];
                                    checkSleepButton = true;
                                    sleepChecker = false;
                                    sleeping = true;
                                    sleepTimer = 60;
                                    break;
                            }
                        }
                        if (laying && !sleepFinished) {
                            if (!timeIsPassedSleep1 && !checkSleep) {
                                new ThreadSleepBird1().start();
                                timeIsPassedSleep1 = true;
                            }
                            if (needToDrawNowSleep1 && !checkSleep) {
                                if (bitmap1 == bitmapDTSH1 || bitmap1 == bitmapDSH1)
                                    bitmap = sleepDSH1;
                                else if (bitmap1 == bitmapDTS1 || bitmap1 == bitmapDS1)
                                    bitmap = sleepDS1;
                                else if (bitmap1 == bitmapDTH1 || bitmap1 == bitmapDH1)
                                    bitmap = sleepDH1;
                                else if (bitmap1 == bitmapDT1 || bitmap1 == bitmapD1)
                                    bitmap = sleepD1;
                                else if (bitmap1 == bitmapTSH1 || bitmap1 == bitmapSH1)
                                    bitmap = sleepSH1;
                                else if (bitmap1 == bitmapTS1 || bitmap1 == bitmapS1)
                                    bitmap = sleepS1;
                                else if (bitmap1 == bitmapTH1 || bitmap1 == bitmapH1)
                                    bitmap = sleepH1;
                                else if (bitmap1 == bitmapT1 || bitmap1 == bitmapUsual1)
                                    bitmap = sleepUsual1;
                                else if (bitmap1 == bitmapSmile1) bitmap = sleepSmile1;
                            }
                            if (!timeIsPassedSleep2 && checkSleep) {
                                new ThreadSleepBird2().start();
                                timeIsPassedSleep2 = true;
                            }
                            if (needToDrawNowSleep2 && checkSleep) {
                                if (bitmap1 == bitmapDTSH1 || bitmap1 == bitmapDSH1)
                                    bitmap = sleepDSH2;
                                else if (bitmap1 == bitmapDTS1 || bitmap1 == bitmapDS1)
                                    bitmap = sleepDS2;
                                else if (bitmap1 == bitmapDTH1 || bitmap1 == bitmapDH1)
                                    bitmap = sleepDH2;
                                else if (bitmap1 == bitmapDT1 || bitmap1 == bitmapD1)
                                    bitmap = sleepD2;
                                else if (bitmap1 == bitmapTSH1 || bitmap1 == bitmapSH1)
                                    bitmap = sleepSH2;
                                else if (bitmap1 == bitmapTS1 || bitmap1 == bitmapS1)
                                    bitmap = sleepS2;
                                else if (bitmap1 == bitmapTH1 || bitmap1 == bitmapH1)
                                    bitmap = sleepH2;
                                else if (bitmap1 == bitmapT1 || bitmap1 == bitmapUsual1)
                                    bitmap = sleepUsual2;
                                else if (bitmap1 == bitmapSmile1) bitmap = sleepSmile2;
                            }
                            if (!timeIsPassedSle) {
                                new Sleep().start();
                                timeIsPassedSle = true;
                            }
                            if (needToDrawNowSle && r1 == 1) {
                                qun *= (sleepRight2 - sleepLeft);
                                if (sleepRight + qun > sleepRight2) {
                                    sleepRight = sleepRight2;
                                    sleepFinished = true;
                                    sleeping = false;
                                    laying = false;
                                    flying = false;
                                } else {
                                    sleepRight += qun;
                                }
                                editor.putFloat("SLEEP", sleepRight);
                                editor.apply();
                                qun /= (sleepRight2 - sleepLeft);
                                r1 = 0;
                            }
                        }
                        if (sleepFinished) {
                            sleeping = false;
                            laying = false;
                            flying = false;
                            if (!flyingBackTimeIsPassed) {
                                new FlyingBackThread().start();
                                flyingBackTimeIsPassed = true;
                                flyingBack = true;
                            }
                            if (flyingBackNeedToDrawNow && flyingBack) {
                                switch (flyBack) {
                                    case 1:
                                        bitmap = bitmap1;
                                        birdX = (float) 48 / 1050;
                                        birdY = (float) 218 / 540;
                                        break;
                                    case 2:
                                        if (bitmap1 == bitmapDTSH1) bitmap = flyBitmapTDSH[4];
                                        else if (bitmap1 == bitmapDH1) bitmap = flyBitmapDH[4];
                                        else if (bitmap1 == bitmapDS1) bitmap = flyBitmapDS[4];
                                        else if (bitmap1 == bitmapD1) bitmap = flyBitmapD[4];
                                        else if (bitmap1 == bitmapH1) bitmap = flyBitmapH[4];
                                        else if (bitmap1 == bitmapSmile1)
                                            bitmap = flyBitmapSmile[4];
                                        else if (bitmap1 == bitmapSH1) bitmap = flyBitmapSH[4];
                                        else if (bitmap1 == bitmapDSH1) bitmap = flyBitmapSDH[4];
                                        else if (bitmap1 == bitmapS1) bitmap = flyBitmapS[4];
                                        else if (bitmap1 == bitmapDTH1) bitmap = flyBitmapTDH[4];
                                        else if (bitmap1 == bitmapDTS1) bitmap = flyBitmapTDS[4];
                                        else if (bitmap1 == bitmapDT1) bitmap = flyBitmapTD[4];
                                        else if (bitmap1 == bitmapTH1) bitmap = flyBitmapTH[4];
                                        else if (bitmap1 == bitmapTSH1) bitmap = flyBitmapTSH[4];
                                        else if (bitmap1 == bitmapTS1) bitmap = flyBitmapTS[4];
                                        else if (bitmap1 == bitmapT1) bitmap = flyBitmapT[4];
                                        else if (bitmap1 == bitmapUsual1)
                                            bitmap = flyBitmapUsual[4];
                                        birdX = (float) 159 / 1050;
                                        birdY = (float) 193 / 540;
                                        break;
                                    case 3:
                                        if (bitmap1 == bitmapDTSH1) bitmap = flyBitmapTDSH[3];
                                        else if (bitmap1 == bitmapDH1) bitmap = flyBitmapDH[3];
                                        else if (bitmap1 == bitmapDS1) bitmap = flyBitmapDS[3];
                                        else if (bitmap1 == bitmapD1) bitmap = flyBitmapD[3];
                                        else if (bitmap1 == bitmapH1) bitmap = flyBitmapH[3];
                                        else if (bitmap1 == bitmapSmile1)
                                            bitmap = flyBitmapSmile[3];
                                        else if (bitmap1 == bitmapSH1) bitmap = flyBitmapSH[3];
                                        else if (bitmap1 == bitmapDSH1) bitmap = flyBitmapSDH[3];
                                        else if (bitmap1 == bitmapS1) bitmap = flyBitmapS[3];
                                        else if (bitmap1 == bitmapDTH1) bitmap = flyBitmapTDH[3];
                                        else if (bitmap1 == bitmapDTS1) bitmap = flyBitmapTDS[3];
                                        else if (bitmap1 == bitmapDT1) bitmap = flyBitmapTD[3];
                                        else if (bitmap1 == bitmapTH1) bitmap = flyBitmapTH[3];
                                        else if (bitmap1 == bitmapTSH1) bitmap = flyBitmapTSH[3];
                                        else if (bitmap1 == bitmapTS1) bitmap = flyBitmapTS[3];
                                        else if (bitmap1 == bitmapT1) bitmap = flyBitmapT[3];
                                        else if (bitmap1 == bitmapUsual1)
                                            bitmap = flyBitmapUsual[3];
                                        birdX = (float) 230 / 1050;
                                        birdY = (float) 232 / 540;
                                        break;
                                    case 4:
                                        if (bitmap1 == bitmapDTSH1) bitmap = flyBitmapTDSH[4];
                                        else if (bitmap1 == bitmapDH1) bitmap = flyBitmapDH[4];
                                        else if (bitmap1 == bitmapDS1) bitmap = flyBitmapDS[4];
                                        else if (bitmap1 == bitmapD1) bitmap = flyBitmapD[4];
                                        else if (bitmap1 == bitmapH1) bitmap = flyBitmapH[4];
                                        else if (bitmap1 == bitmapSmile1)
                                            bitmap = flyBitmapSmile[4];
                                        else if (bitmap1 == bitmapSH1) bitmap = flyBitmapSH[4];
                                        else if (bitmap1 == bitmapDSH1) bitmap = flyBitmapSDH[4];
                                        else if (bitmap1 == bitmapS1) bitmap = flyBitmapS[4];
                                        else if (bitmap1 == bitmapDTH1) bitmap = flyBitmapTDH[4];
                                        else if (bitmap1 == bitmapDTS1) bitmap = flyBitmapTDS[4];
                                        else if (bitmap1 == bitmapDT1) bitmap = flyBitmapTD[4];
                                        else if (bitmap1 == bitmapTH1) bitmap = flyBitmapTH[4];
                                        else if (bitmap1 == bitmapTSH1) bitmap = flyBitmapTSH[4];
                                        else if (bitmap1 == bitmapTS1) bitmap = flyBitmapTS[4];
                                        else if (bitmap1 == bitmapT1) bitmap = flyBitmapT[4];
                                        else if (bitmap1 == bitmapUsual1)
                                            bitmap = flyBitmapUsual[4];
                                        birdX = (float) 287 / 1050;
                                        birdY = (float) 226 / 540;
                                        break;
                                    case 5:
                                        if (bitmap1 == bitmapDTSH1) bitmap = flyBitmapTDSH[3];
                                        else if (bitmap1 == bitmapDH1) bitmap = flyBitmapDH[3];
                                        else if (bitmap1 == bitmapDS1) bitmap = flyBitmapDS[3];
                                        else if (bitmap1 == bitmapD1) bitmap = flyBitmapD[3];
                                        else if (bitmap1 == bitmapH1) bitmap = flyBitmapH[3];
                                        else if (bitmap1 == bitmapSmile1)
                                            bitmap = flyBitmapSmile[3];
                                        else if (bitmap1 == bitmapSH1) bitmap = flyBitmapSH[3];
                                        else if (bitmap1 == bitmapDSH1) bitmap = flyBitmapSDH[3];
                                        else if (bitmap1 == bitmapS1) bitmap = flyBitmapS[3];
                                        else if (bitmap1 == bitmapDTH1) bitmap = flyBitmapTDH[3];
                                        else if (bitmap1 == bitmapDTS1) bitmap = flyBitmapTDS[3];
                                        else if (bitmap1 == bitmapDT1) bitmap = flyBitmapTD[3];
                                        else if (bitmap1 == bitmapTH1) bitmap = flyBitmapTH[3];
                                        else if (bitmap1 == bitmapTSH1) bitmap = flyBitmapTSH[3];
                                        else if (bitmap1 == bitmapTS1) bitmap = flyBitmapTS[3];
                                        else if (bitmap1 == bitmapT1) bitmap = flyBitmapT[3];
                                        else if (bitmap1 == bitmapUsual1)
                                            bitmap = flyBitmapUsual[3];
                                        birdX = (float) 353 / 1050;
                                        birdY = (float) 238 / 540;
                                        break;
                                    case 6:
                                        if (bitmap1 == bitmapDTSH1) bitmap = flyBitmapTDSH[4];
                                        else if (bitmap1 == bitmapDH1) bitmap = flyBitmapDH[4];
                                        else if (bitmap1 == bitmapDS1) bitmap = flyBitmapDS[4];
                                        else if (bitmap1 == bitmapD1) bitmap = flyBitmapD[4];
                                        else if (bitmap1 == bitmapH1) bitmap = flyBitmapH[4];
                                        else if (bitmap1 == bitmapSmile1)
                                            bitmap = flyBitmapSmile[4];
                                        else if (bitmap1 == bitmapSH1) bitmap = flyBitmapSH[4];
                                        else if (bitmap1 == bitmapDSH1) bitmap = flyBitmapSDH[4];
                                        else if (bitmap1 == bitmapS1) bitmap = flyBitmapS[4];
                                        else if (bitmap1 == bitmapDTH1) bitmap = flyBitmapTDH[4];
                                        else if (bitmap1 == bitmapDTS1) bitmap = flyBitmapTDS[4];
                                        else if (bitmap1 == bitmapDT1) bitmap = flyBitmapTD[4];
                                        else if (bitmap1 == bitmapTH1) bitmap = flyBitmapTH[4];
                                        else if (bitmap1 == bitmapTSH1) bitmap = flyBitmapTSH[4];
                                        else if (bitmap1 == bitmapTS1) bitmap = flyBitmapTS[4];
                                        else if (bitmap1 == bitmapT1) bitmap = flyBitmapT[4];
                                        else if (bitmap1 == bitmapUsual1)
                                            bitmap = flyBitmapUsual[4];
                                        birdX = (float) 419 / 1050;
                                        birdY = (float) 232 / 540;
                                        break;
                                    case 7:
                                        bitmap = bitmap1;
                                        birdX = (float) 419 / 1050;
                                        birdY = (float) 232 / 540;
                                        flyingBack = false;
                                        flyBack = 0;
                                        sleeping = false;
                                        sleepFinished = false;
                                        sleep = 0;
                                        lastTouchY = 0;
                                        lastTouchX = 0;
                                        sl = false;
                                        fl = false;
                                        flying = false;
                                        flyingTimeIsPassed = false;
                                        break;
                                }
                            }
                        }
                        // loxanal
                        if (lastTouchX >= dirtButtonLeft * canvas.getWidth() && lastTouchX <= (dirtButtonLeft + ButtonWidth) * canvas.getWidth() && lastTouchY >= dirtButtonTop * canvas.getHeight() && lastTouchY <= (dirtButtonTop + ButtonHeight) * canvas.getHeight() && (dirtRight - dirtLeft <= dirtWeight / 2.) && !playing && !eating && !sleeping && !laying && !flying && !flyingBack && !hitting && !pooping && !flyPoop && !disgusting && !flyBackPoop && !wa) {
                            wa = true;
                            gettingFoin = true;
                            isSinging = true;
                        }
                        if(isSinging && wa) {
                            mediaPlayerWash.start();
                            isSinging = false;
                        }
                        if (!washingTimeIsPassed && wa) {
                            new WashingThread().start();
                            washing = true;
                            washingTimeIsPassed = true;
                        }
                        if (washingNeedToDrawNow && washing && wash <= 48) {
                            birdX = (float) 414 / 1050;
                            birdY = (float) 31 / 540;
                            bitmap = washBitmap[wash];
                            if (wash == 31) dirtRight = dirtRight2;
                            editor.putFloat("DIRT", dirtRight);
                            editor.apply();
                            if (wash == 48) {
                                washing = false;
                                wash = 1;
                                washingTimeIsPassed = false;
                                lastTouchY = 0;
                                lastTouchX = 0;
                                wa = false;
                                bitmap = bitmap1;
                                birdX = (float) 419 / 1050;
                                birdY = (float) 232 / 540;
                                mediaPlayerWash.stop();
                                mediaPlayerWash.prepare();
                            }
                        }

                        // tprtal
                        if (lastTouchX >= (birdX * canvas.getWidth()) && lastTouchX <= (birdX + birdWidth) * (canvas.getWidth()) && lastTouchY >= (birdY * canvas.getHeight()) && lastTouchY <= (birdY + birdHeight) * (canvas.getHeight()) && !eating && !playing && !flying && !sleeping && !laying && !flyingBack && !washing && !pooping && !flyPoop && !disgusting && !flyBackPoop && !hi) {
                            hi = true;
                            gettingFoin = true;
                            isSinging = true;
                        }
                        if (isSinging && hi) {
                            mediaPlayerHit.start();
                            isSinging = false;
                        }
                        if (m6 == 1 && lastTouchX >= (birdX * canvas.getWidth()) && lastTouchX <= (birdX + birdWidth) * (canvas.getWidth()) && lastTouchY >= (birdY * canvas.getHeight()) && lastTouchY <= (birdY + birdHeight) * (canvas.getHeight()) && !eating && !playing && !flying && !sleeping && !laying && !flyingBack && !washing && !pooping && !flyPoop && !disgusting && !flyBackPoop) {
                            sendScreen();
                        }
                        if (!hitTimeIsPassed && hi) {
                            new HitThread().start();
                            hitting = true;
                            hitTimeIsPassed = true;
                        }
                        if (hitNeedToDrawNow && hitting) {
                            if (bitmap1 == bitmapDH1 || bitmap1 == bitmapDTH1 || bitmap1 == bitmapDTSH1 || bitmap1 == bitmapDSH1)
                                bitmap = hitBitmapDH[hit];
                            if (bitmap1 == bitmapD1 || bitmap1 == bitmapDT1 || bitmap1 == bitmapDTS1 || bitmap1 == bitmapDS1)
                                bitmap = hitBitmapD[hit];
                            if (bitmap1 == bitmapH1 || bitmap1 == bitmapTH1 || bitmap1 == bitmapTSH1 || bitmap1 == bitmapSH1)
                                bitmap = hitBitmapH[hit];
                            if (bitmap1 == bitmapUsual1 || bitmap1 == bitmapS1 || bitmap1 == bitmapT1 || bitmap1 == bitmapTS1 || bitmap1 == bitmapSmile1)
                                bitmap = hitBitmap[hit];
                            if (hit >= 14) {
                                hitting = false;
                                hit = 1;
                                hi = false;
                                hitTimeIsPassed = false;
                                lastTouchY = 0;
                                lastTouchX = 0;
                                mediaPlayerHit.stop();
                                mediaPlayerHit.prepare();
                            }
                        }
                        // screenshot
                        if (lastTouchX >= (screenshotX * canvas.getWidth()) && lastTouchX <= (screenshotX + screenshotWidth) * (canvas.getWidth()) && lastTouchY >= (screenshotY * canvas.getHeight()) && lastTouchY <= (screenshotY + screenshotHeight) * (canvas.getHeight()) && m6 == 0) {
                            takeScreenshot();
                            lastTouchY = 0;
                            lastTouchX = 0;
                        }
                        if (lastTouchX >= (screenshotX * canvas.getWidth()) && lastTouchX <= (screenshotX + screenshotWidth) * (canvas.getWidth()) && lastTouchY >= (screenshotY * canvas.getHeight()) && lastTouchY <= (screenshotY + screenshotHeight) * (canvas.getHeight()) && m6 == 1) {
                            sendScreen();
                            lastTouchY = 0;
                            lastTouchX = 0;
                        }
                        screenshotBitmap = Bitmap.createScaledBitmap(screenshotBitmap, canvas.getWidth() * 73 / 1050, canvas.getHeight() * 73 / 540, true);
                        canvas.drawBitmap(screenshotBitmap, (float) canvas.getWidth() * screenshotX, (float) canvas.getHeight() * screenshotY, paint);
                        // xanut
                        if (lastTouchX >= shopButtonLeft && lastTouchX <= (shopButtonLeft + shopButtonWidth) && lastTouchY >= (shopButtonTop) && lastTouchY <= (shopButtonTop + shopButtonHeight)) {
                            context.startActivity(new Intent(((Activity) context), ShopSkin.class));
                            lastTouchY = 0;
                            lastTouchX = 0;
                        }
                        //lvl+
                        //if (bitmap1 == bitmapSmile1 && statChecker) {
                        //    statChecker = false;
                        //    //editor.putBoolean("STATCHECKER",false);
                        //    //editor.apply();
                        //    lvlCheck = true;
                        //    //editor.putBoolean("LVLCHECK",true);
                        //    // editor.apply();
                        //    new LevelTimerThread().start();
                        //    canvas.drawCircle(0, 0, 100, paintDirt);
                        //}
                        //if (lvlCheck) {
                        //    lvl *= (levelRight2 - levelLeft);
                        //    if (m1 == 1) {
                        //        levelRight3 = levelRight;
                        //        m1 = 0;
                        //    }
                        //    if (levelRight <= lvl + levelRight3) {
                        //        if (levelRight + lvl > levelRight2) {
                        //            level++;
                        //            editor.putInt("LEVEL", level);
                        //            editor.apply();
                        //            levelRight = levelLeft;
                        //            editor.putFloat("LEVELRIGHT", levelRight);
                        //            editor.apply();
                        //        } else {
                        //            levelRight += 1;
                        //            editor.putFloat("LEVELRIGHT", levelRight);
                        //            editor.apply();
                        //        }
                        //    } else {
                        //        lvlCheck = false;
                        //        //editor.putBoolean("LVLCHECK",false);
                        //        //editor.apply();
                        //        m1 = 1;
                        //        canvas.drawCircle(0, 0, 100, paintDirt);
                        //    }
                        //    lvl /= (levelRight2 - levelLeft);
                        //  }
                        // cit
                        canvas.drawBitmap(bitmap, (float) canvas.getWidth() * birdX, (float) canvas.getHeight() * birdY, paint);
                        canvas.drawText(hit + "", 500, 500, paintDirt);
                    }
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } finally {
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
        }
    }
    class LevelThread extends  Thread{
        @Override
        public void run() {
            try {
                sleep(1000);
                levelTimeIsPassed = false;
                levelNeedToDrawNow = true;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
    class LevelTimerThread extends Thread{
        @Override
        public void run() {
            try {
                sleep(300000);
                statChecker = true;
                editor.putBoolean("STATCHECKER",true);
                editor.apply();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
    class DirtTimerThread extends  Thread{
        @Override
        public void run() {
            try {
                sleep(1000);
                dirtTimeIsPassed = false;
                dirtNeedToDrawNow = true;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
    class HungryTimerThread extends  Thread{
        @Override
        public void run() {
            try {
                sleep(1000);
                hungryTimeIsPassed = false;
                hungryNeedToDrawNow = true;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
    class SleepTimerThread extends  Thread{
        @Override
        public void run() {
            try {
                sleep(1000);
                sleepTimeIsPassed = false;
                sleepNeedToDrawNow = true;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
    class HappyTimerThread extends  Thread{
        @Override
        public void run() {
            try {
                sleep(1000);
                happyTimeIsPassed = false;
                happyNeedToDrawNow = true;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
    class ThreadBird1 extends Thread{
        @Override
        public void run() {
            try {
                sleep(1300);
                timeIsPassed1 = false;
                needToDrawNow1 = true;
                check = true;
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    class ThreadBird2 extends Thread{
        @Override
        public void run() {
            try {
                sleep(900);
                timeIsPassed2 = false;
                needToDrawNow2 = true;
                check = false;
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    class ThreadSleepBird1 extends Thread{
        @Override
        public void run() {
            try {
                sleep(1500);
                timeIsPassedSleep1 = false;
                needToDrawNowSleep1 = true;
                checkSleep = true;
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    class ThreadSleepBird2 extends Thread{
        @Override
        public void run() {
            try {
                sleep(1000);
                timeIsPassedSleep2 = false;
                needToDrawNowSleep2 = true;
                checkSleep = false;
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    class Sleep extends Thread{
        @Override
        public void run() {
            try {
                sleep(600);
                timeIsPassedSle = false;
                needToDrawNowSle = true;
                r1 = 1;
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    class EatingThread extends Thread{
        @Override
        public void run() {
            try {
                if(eat<10) {
                    sleep(500);
                    eat++;
                    eatingTimeIsPassed = false;
                    eatingNeedToDrawNow = true;
                }
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    class PlayingThread extends Thread{
        @Override
        public void run() {
            if(play<20) {
                try {
                    sleep(200);
                    play++;
                    playingTimeIsPassed = false;
                    playingNeedToDrawNow = true;
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
    class FlyingThread extends Thread{
        @Override
        public void run() {
            try {
                if(sleep<7) {
                    sleep(300);
                    sleep++;
                    flyingTimeIsPassed = false;
                    flyingNeedToDrawNow = true;
                }
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    class FlyingBackThread extends Thread{
        @Override
        public void run() {
            try {
                if(flyBack<7) {
                    sleep(300);
                    flyBack++;
                    flyingBackTimeIsPassed = false;
                    flyingBackNeedToDrawNow = true;
                }
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    class WashingThread extends Thread{
        @Override
        public void run() {
            try {
                if (wash <= 30) {
                    sleep(100);
                    wash++;
                    washingTimeIsPassed = false;
                    washingNeedToDrawNow = true;
                }
                if(wash >= 31 && wash <= 49) {
                    sleep(300);
                    wash++;
                    washingTimeIsPassed = false;
                    washingNeedToDrawNow = true;
                }
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    class EatDarkButtonThread extends Thread{
        @Override
        public void run() {
            try {
                if(eatTimer>=0) {
                    sleep(1000);
                    checkEatButton = true;
                    drawEatButton = true;
                    eatTimer--;
                }
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    class PlayDarkButtonThread extends Thread{
        @Override
        public void run() {
            try {
                if(playTimer>=0) {
                    sleep(1000);
                    checkPlayButton = true;
                    drawPlayButton = true;
                    playTimer--;
                }
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    class SleepDarkButtonThread extends Thread{
        @Override
        public void run() {
            try {
                if(sleepTimer>=0) {
                    sleep(1000);
                    checkSleepButton = true;
                    drawSleepButton = true;
                    sleepTimer--;
                }
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    class HitThread extends Thread{
        @Override
        public void run() {
                    try {
                        if(hit < 14) {
                            hit++;
                            sleep(150);
                            hitTimeIsPassed = false;
                            hitNeedToDrawNow = true;
                        }
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
            }
        }
    class Minute extends Thread{
        @Override
        public void run() {
            try {
                sleep(12000);
                pop = true;
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    class PoopThread extends Thread{
        @Override
        public void run() {
            try {
                if(poop1 <= 4) {
                    sleep(100);
                    poop1++;
                    poopingTimeIsPassed1 = false;
                }
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    class PoopFlyThread extends Thread{
        @Override
        public void run() {
            try {
                if(poop2 < 6) {
                    sleep(300);
                    poopingTimeIsPassed2 = false;
                    poopingNeedToDrawNow2 = true;
                    poop2++;
                }
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    class PoopFlyBackThread extends Thread{
        @Override
        public void run() {
            try {
                if(poop3 < 6) {
                    sleep(350);
                    poopingTimeIsPassed3 = false;
                    poopingNeedToDrawNow3 = true;
                    poop3++;
                }
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    class DisgustThread extends Thread{
        @Override
        public void run() {
            try {
                if(disgust <= 10) {
                    sleep(300);
                    disgust++;
                    disgustingTimeIsPassed = false;
                    disgustingNeedToDrawNow = true;
                }
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    class GetFoinThread extends Thread{
        @Override
        public void run() {
            try{
            if(foinTime<7) {
                sleep(200);
                foinTime++;
                getFoinTimeIsPassed = false;
                getFoinNeedToDrawNow = true;
            }
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}

