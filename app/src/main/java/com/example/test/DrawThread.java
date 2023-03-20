package com.example.test;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
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
    private Context context;
    private Resources res;
    private Bitmap bitmapScreen;
    private int foin = 0, level = 1;
    private float lastTouchX = 0, lastTouchY = 0;
    private float ButtonWidth = (float) 106/1050, ButtonHeight =(float) 101/540;
    private float birdX = (float) 419/1050, birdY = (float) 232/540;
    private float birdWidth = (float) 150 / 1050, birdHeight = (float) 172/540;
    private float screenshotWidth = (float) 73 / 1050, screenshotHeight = (float) 73/540;
    private float screenshotX = (float) 962/1050, screenshotY = (float) 461/540;
    private float hungryLeft =(float) 877. / 1050,hungryTop = (float)  92 / 540,hungryRight2 = (float) 1040 / 1050,hungryWeight = hungryRight2 - hungryLeft,hungryBottom = (float)  116 / 540;
    private float levelLeft = (float) 844 / 1050, levelTop = (float)  8 / 540, levelRight2 = (float)  1040 / 1050, levelBottom = (float)  42 / 540;
    private float dirtLeft = (float) 877 / 1050, dirtTop = (float)  55 / 540, dirtRight2 = (float)  1040 / 1050, dirtWeight = dirtRight2 - dirtLeft, dirtBottom = (float)  79 / 540;
    private float sleepLeft = (float) 877 / 1050, sleepTop = (float)  129 / 540, sleepRight2 = (float) 1040 / 1050, sleepWeight = sleepRight2 - sleepLeft, sleepBottom = (float)  153 / 540;
    private float happyLeft = (float) 877 / 1050, happyTop = (float)  166 / 540, happyRight2 = (float)  1040 / 1050, happyWeight = happyRight2 - happyLeft, happyBottom = (float)  190 / 540;
    private float shopButtonLeft = (float)878/1050, shopButtonTop = (float) 462/540;
    private int shopButtonWidth = 75/1050, shopButtonHeight = 75/540;
    private float eatButtonLeft = (float) 19/1050, eatButtonTop = (float) 427/540;
    private float playButtonLeft = (float)130/1050, playButtonTop = (float) 427/540;
    private float sleepButtonTop = (float) 427/540, dirtButtonLeft = (float)352/1050;
    private float dirtButtonTop = (float)427/540;
    private float poopX = (float)  450 / 1050, poopY = (float)  309 / 540;
    private float poopWidth = (float)  94 / 105, poopHeight = (float)  94 / 540;
    private int disgust = 1, eatScore = 0, wash = 1,foinTime = 0, hit = 1,poop1=1,poop2=1,poop3=0, m = 0,m1 = 1,m2 = 1, m6 = 0, eat = 1, e = 0, eatTimer = 10, m5 = 0, p = 0, playTimer = 15, sleepTimer = 60, play = 0, sleep = 0, r1 = 0, flyBack = 0;
    private double hungryChangeValue = (float)1/ 100; // Sovi timeri hamar
    private double happyChangeValue = (float) 1 / 70; // Uraxutyan timeri hamar
    private double sleepChangeValue = (float) 1 / 120; // Qni timeri hamar
    private double dirtyChangeValue = (float) 1 / 50; // kextotutyan timeri hamar
    private double food = (float) 1 ;//food = (float) 1 / 10;
    private double smile = (float) 1 / 300;//smile = (float) 1 / 60;
    private double qun = (float) 1 / 40;//qun = (float) 1 / 40;
    private float hungryRight = 0, happyRight = 0, sleepRight = 0, dirtRight = 0, levelRight = 0;
    private int levelColor, dirtColor, hungryColor, tiredColor, happyColor;
    private boolean hungryButtonIsPressed = false, happyButtonIsTouched = false, sl = false, sleepButtonIsPressed = false, sle = false, birdIsPunched = false, washButtonIsPressed = false, pop = false, po = false;
    private boolean playingTimeIsPassed = false,playingNeedToDrawNow = false;
    private boolean checkPlayButton = false, drawPlayButton = false;
    private boolean playChecker = true;
    private boolean hitting = false, eating = false, playing = false, gettingFoin = false, flying = false, laying = false, sleeping = false, flyingBack = false, washing = false, pooping = false, disgusting = false, flyPoop = false, flyBackPoop = false;
    private boolean eatChecker = true;
    private boolean sleepChecker = true;
    private boolean checkEatButton = false, drawEatButton = false;
    private boolean checkSleepButton = false, drawSleepButton = false;
    private boolean eatingTimeIsPassed = false, eatingNeedToDrawNow = false;
    private boolean flyingToBedTimeIsPassed = false, flyingToBedNeedToDrawNow = false;
    private boolean poopingTimeIsPassed1 = false;
    private boolean poopingTimeIsPassed2 = false, poopingNeedToDrawNow2 = false;
    private boolean poopingTimeIsPassed3 = false, poopingNeedToDrawNow3 = false;
    private boolean disgustingTimeIsPassed = false, disgustingNeedToDrawNow = false;
    private boolean flyingBackTimeIsPassed = false, flyingBackNeedToDrawNow = false;
    private boolean washingTimeIsPassed = false, washingNeedToDrawNow = false;
    private boolean hitTimeIsPassed = false, hitNeedToDrawNow = false;
    private boolean hungryTimeIsPassed = false, hungryNeedToDrawNow = false;
    private boolean happyTimeIsPassed = false, happyNeedToDrawNow = false;
    private boolean sleepTimeIsPassed = false, sleepNeedToDrawNow = false;
    private boolean dirtTimeIsPassed = false, dirtNeedToDrawNow = false;
    private boolean timeIsPassed1 = false, needToDrawNow1 = false;
    private boolean timeIsPassed2 = false, needToDrawNow2 = false;
    private boolean timeIsPassedSleep1 = false,needToDrawNowSleep1 = false;
    private boolean timeIsPassedSleep2 = false, needToDrawNowSleep2 = false;
    private boolean getFoinTimeIsPassed = false, getFoinNeedToDrawNow = false;
    private boolean sleepingTimeIsPassed = false, sleepingNeedToDrawNow = false;
    private boolean levelTimeIsPassed = false, levelNeedToDrawNow = false;
    private boolean sleepFinished = false;
    private boolean check = false;
    private boolean statChecker = false;
    private boolean checkSleep = false;
    private boolean isTouched = false, isSinging = false;
    private boolean loaded = false;
    private View view;
    private Bitmap playButtonBitmap,playButtonBitmap2, playButtonBitmapPoop;
    private Bitmap sleepButtonBitmap,sleepButtonBitmap2, sleepButtonBitmapPoop;
    private Bitmap washButtonBitmap, washButtonBitmap2,washButtonBitmap3, washButtonBitmapPoop;
    // T = Tired
    // D = Dirty
    // S = Sad
    // H = Hungry
    private Bitmap bitmapbg,bitmapkust, bitmapDirt, bitmapHungry,bitmapSleep, bitmapHappy, eatButtonBitmap,eatButtonBitmap2, eatButtonBitmapPoop, highScoreBitmap, scoreBitmap;
    private Bitmap bird, bitmapDTSH1, bitmapDTSH2, bitmapDTS1, bitmapDTS2, bitmapDTH1, bitmapDTH2, bitmapDSH1, bitmapDSH2, bitmapTSH1, bitmapTSH2,
            bitmapDH1, bitmapDH2, bitmapDS1, bitmapDS2, bitmapDT1, bitmapDT2, bitmapSH1, bitmapSH2, bitmapTH1,bitmapTH2,bitmapTS1,bitmapTS2
            ,bitmapD1,bitmapD2,bitmapT1,bitmapT2,bitmapS1,bitmapS2,bitmapH1,bitmapH2,bitmapSmile1,bitmapSmile2,bitmapUsual1,bitmapUsual2, birdBreath1, birdBreath2;
    private Bitmap bitmapDarkbg,bitmapDarkkust;
    private Bitmap sleepDarkButtonBitmap[] = new Bitmap[61], eatDarkButtonBitmap[] = new Bitmap[11], playDarkButtonBitmap[] = new Bitmap[16],washDarkButtonBitmap;
    private Bitmap poopBitmap[] = new Bitmap[5];
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
    private Paint paint = new Paint(),paintLevel = new Paint(),paintDirt = new Paint(),paintHungry = new Paint(),paintSleep = new Paint(),paintHappy = new Paint(),paintBlack = new Paint(),paintFoin = new Paint();
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private int timePassed;
    private MediaPlayer mediaPlayerHappy, mediaPlayerHit, mediaPlayerWash;
    private int playBitmapId[] = {0 ,R.drawable.xaxacox1,R.drawable.xaxacox2,R.drawable.xaxacox3,R.drawable.xaxacox4,R.drawable.xaxacox5,R.drawable.xaxacox6,R.drawable.xaxacox7,R.drawable.xaxacox8,R.drawable.xaxacox9,R.drawable.xaxacox10,R.drawable.xaxacox11,R.drawable.xaxacox12,R.drawable.xaxacox13,R.drawable.xaxacox14,R.drawable.xaxacox15,R.drawable.xaxacox16,R.drawable.xaxacox17,R.drawable.xaxacox18,R.drawable.xaxacox19,R.drawable.xaxacox20};
    private int playBitmapDId[] = {0,R.drawable.d_xaxacox1,R.drawable.d_xaxacox2,R.drawable.d_xaxacox3,R.drawable.d_xaxacox4,R.drawable.d_xaxacox5,R.drawable.d_xaxacox6,R.drawable.d_xaxacox7,R.drawable.d_xaxacox8,R.drawable.d_xaxacox9,R.drawable.d_xaxacox10,R.drawable.d_xaxacox11,R.drawable.d_xaxacox12,R.drawable.d_xaxacox13,R.drawable.d_xaxacox14,R.drawable.d_xaxacox15,R.drawable.d_xaxacox16,R.drawable.d_xaxacox17,R.drawable.d_xaxacox18,R.drawable.d_xaxacox19,R.drawable.d_xaxacox20};
    private int playBitmapTId[] = {0,R.drawable.xaxacox1,R.drawable.xaxacox2,R.drawable.xaxacox3,R.drawable.xaxacox4,R.drawable.xaxacox5,R.drawable.xaxacox6,R.drawable.xaxacox7,R.drawable.xaxacox8,R.drawable.xaxacox9,R.drawable.xaxacox10,R.drawable.xaxacox11,R.drawable.xaxacox12,R.drawable.xaxacox13,R.drawable.xaxacox14,R.drawable.xaxacox15,R.drawable.xaxacox16,R.drawable.xaxacox17,R.drawable.xaxacox18,R.drawable.t_xaxacox19,R.drawable.t_xaxacox20};
    private int playBitmapDTId[] = {0,R.drawable.d_xaxacox1,R.drawable.d_xaxacox2,R.drawable.d_xaxacox3,R.drawable.d_xaxacox4,R.drawable.d_xaxacox5,R.drawable.d_xaxacox6,R.drawable.d_xaxacox7,R.drawable.d_xaxacox8,R.drawable.d_xaxacox9,R.drawable.d_xaxacox10,R.drawable.d_xaxacox11,R.drawable.d_xaxacox12,R.drawable.d_xaxacox13,R.drawable.d_xaxacox14,R.drawable.d_xaxacox15,R.drawable.d_xaxacox16,R.drawable.d_xaxacox17,R.drawable.d_xaxacox18,R.drawable.d_t_xaxacox19,R.drawable.d_t_xaxacox20};
    private int flyBitmapUsualId[] = {0,R.drawable.trnox_cit1,R.drawable.trnox_cit2,R.drawable.trnox_cit3,R.drawable.trnox_cit4 };
    private int flyBitmapDHId[] = {0,R.drawable.f_d_h1,R.drawable.f_d_h2,R.drawable.f_d_h3,R.drawable.f_d_h4};
    private int flyBitmapDId[] = {0,R.drawable.f_d1,R.drawable.f_d2,R.drawable.f_d3,R.drawable.f_d4};
    private int flyBitmapDSId[] = {0,R.drawable.f_d_s1,R.drawable.f_d_s2,R.drawable.f_d_s3,R.drawable.f_d_s4};
    private int flyBitmapHId[] = {0,R.drawable.f_h1,R.drawable.f_h2,R.drawable.f_h3,R.drawable.f_h4};
    private int flyBitmapSmileId[] = {0,R.drawable.f_hp1,R.drawable.f_hp2,R.drawable.f_hp3,R.drawable.f_hp4};
    private int flyBitmapSDHId[] = {0,R.drawable.f_s_d_h1,R.drawable.f_s_d_h2,R.drawable.f_s_d_h3,R.drawable.f_s_d_h4};
    private int flyBitmapSHId[] = {0,R.drawable.f_s_h1,R.drawable.f_s_h2,R.drawable.f_s_h3,R.drawable.f_s_h4};
    private int flyBitmapSId[] = {0,R.drawable.f_s1,R.drawable.f_s2,R.drawable.f_s3,R.drawable.f_s4};
    private int flyBitmapTDHId[] = {0,R.drawable.f_t_d_h1,R.drawable.f_t_d_h2,R.drawable.f_d_h3,R.drawable.f_d_h4};
    private int flyBitmapTDSHId[] = {0,R.drawable.f_t_d_s_h1,R.drawable.f_t_d_s_h2,R.drawable.f_s_d_h3,R.drawable.f_s_d_h4};
    private int flyBitmapTDSId[] = {0,R.drawable.f_t_d_s1,R.drawable.f_t_d_s2,R.drawable.f_d_s3,R.drawable.f_d_s4};
    private int flyBitmapTDId[] = {0,R.drawable.f_t_d1,R.drawable.f_t_d2,R.drawable.f_d3,R.drawable.f_d4};
    private int flyBitmapTHId[] = {0,R.drawable.f_t_h1,R.drawable.f_t_h2,R.drawable.f_h3,R.drawable.f_h4};
    private int flyBitmapTSHId[] = {0,R.drawable.f_t_s_h1,R.drawable.f_t_s_h2,R.drawable.f_s_h3,R.drawable.f_s_h4};
    private int flyBitmapTSId[] = {0,R.drawable.f_t_s1,R.drawable.f_t_s2,R.drawable.f_s3,R.drawable.f_s4};
    private int flyBitmapTId[] = {0,R.drawable.f_t1,R.drawable.f_t2,R.drawable.trnox_cit3,R.drawable.trnox_cit4};
    private int hitBitmapId[] = {0,R.drawable.tprtacox1,R.drawable.tprtacox2,R.drawable.tprtacox3,R.drawable.tprtacox4,R.drawable.tprtacox5,R.drawable.tprtacox6,R.drawable.tprtacox7,R.drawable.tprtacox8,R.drawable.tprtacox9,R.drawable.tprtacox10,R.drawable.tprtacox11,R.drawable.tprtacox12,R.drawable.tprtacox13,R.drawable.tprtacox14};
    private int hitBitmapDId[] = {0,R.drawable.ht_d1,R.drawable.ht_d2,R.drawable.ht_d3,R.drawable.ht_d4,R.drawable.ht_d5,R.drawable.ht_d6,R.drawable.ht_d7,R.drawable.ht_d8,R.drawable.ht_d9,R.drawable.ht_d10,R.drawable.ht_d11,R.drawable.ht_d12,R.drawable.ht_d13,R.drawable.ht_d14};
    private int hitBitmapHId[] = {0,R.drawable.ht_h1,R.drawable.ht_h2,R.drawable.ht_h3,R.drawable.ht_h4,R.drawable.ht_h5,R.drawable.ht_h6,R.drawable.ht_h7,R.drawable.ht_h8,R.drawable.ht_h9,R.drawable.ht_h10,R.drawable.ht_h11,R.drawable.ht_h12,R.drawable.ht_h13,R.drawable.ht_h14};
    private int hitBitmapDHId[] = {0,R.drawable.ht_d_h1,R.drawable.ht_d_h2,R.drawable.ht_d_h3,R.drawable.ht_d_h4,R.drawable.ht_d_h5,R.drawable.ht_d_h6,R.drawable.ht_d_h7,R.drawable.ht_d_h8,R.drawable.ht_d_h9,R.drawable.ht_d_h10,R.drawable.ht_d_h11,R.drawable.ht_d_h12,R.drawable.ht_d_h13,R.drawable.ht_d_h14};
    private int washBitmapId[] = {0,R.drawable.lvacvox1,R.drawable.lvacvox2,R.drawable.lvacvox3,R.drawable.lvacvox4,R.drawable.lvacvox5,R.drawable.lvacvox6,R.drawable.lvacvox7,R.drawable.lvacvox8,R.drawable.lvacvox9,R.drawable.lvacvox10,R.drawable.lvacvox11,R.drawable.lvacvox12,R.drawable.lvacvox13,R.drawable.lvacvox14,R.drawable.lvacvox15,R.drawable.lvacvox16,R.drawable.lvacvox17,R.drawable.lvacvox18,R.drawable.lvacvox19,R.drawable.lvacvox20,R.drawable.lvacvox21,R.drawable.lvacvox22,R.drawable.lvacvox23,R.drawable.lvacvox24,R.drawable.lvacvox25,R.drawable.lvacvox26,R.drawable.lvacvox27,R.drawable.lvacvox28,R.drawable.lvacvox29,R.drawable.lvacvox30,R.drawable.lvacvox31,R.drawable.lvacvox32,R.drawable.lvacvox33,R.drawable.lvacvox34,R.drawable.lvacvox35,R.drawable.lvacvox36,R.drawable.lvacvox37,R.drawable.lvacvox38,R.drawable.lvacvox39,R.drawable.lvacvox40,R.drawable.lvacvox41,R.drawable.lvacvox42,R.drawable.lvacvox43,R.drawable.lvacvox44,R.drawable.lvacvox45,R.drawable.lvacvox46,R.drawable.lvacvox47,R.drawable.lvacvox48};
    private int eatBitmapId[] = {0, R.drawable.utox1,R.drawable.utox2,R.drawable.utox3,R.drawable.utox4,R.drawable.utox5,R.drawable.utox6,R.drawable.utox7, R.drawable.utox8,R.drawable.utox9, R.drawable.utox10};
    private int eatBitmapDId[] = {0,R.drawable.e_d_1,R.drawable.e_d_2,R.drawable.e_d_3,R.drawable.e_d_4,R.drawable.e_d_5,R.drawable.e_d_6,R.drawable.e_d_7,R.drawable.e_d_8,R.drawable.e_d_9,R.drawable.e_d_10};
    private int eatBitmapDSId[] = {0,R.drawable.e_d_s_1,R.drawable.e_d_s_2,R.drawable.e_d_s_3,R.drawable.e_d_s_4,R.drawable.e_d_s_5,R.drawable.e_d_s_6,R.drawable.e_d_s_7,R.drawable.e_d_s_8,R.drawable.e_d_s_9,R.drawable.e_d_s_10};
    private int eatBitmapDTId[] = {0,R.drawable.e_d_t_1,R.drawable.e_d_t_2,R.drawable.e_d_t_3,R.drawable.e_d_t_4,R.drawable.e_d_t_5,R.drawable.e_d_t_6,R.drawable.e_d_t_7,R.drawable.e_d_t_8,R.drawable.e_d_t_9,R.drawable.e_d_t_10};
    private int eatBitmapDTSId[] = {0,R.drawable.e_d_t_s_1,R.drawable.e_d_t_s_2,R.drawable.e_d_t_s_3,R.drawable.e_d_t_s_4,R.drawable.e_d_t_s_5,R.drawable.e_d_t_s_6,R.drawable.e_d_t_s_7,R.drawable.e_d_t_s_8,R.drawable.e_d_t_s_9,R.drawable.e_d_t_s_10};
    private int eatBitmapSId[] = {0,R.drawable.e_s_1,R.drawable.e_s_2,R.drawable.e_s_3,R.drawable.e_s_4,R.drawable.e_s_5,R.drawable.e_s_6,R.drawable.e_s_7,R.drawable.e_s_8,R.drawable.e_s_9,R.drawable.e_s_10};
    private int eatBitmapTId[] = {0,R.drawable.e_t_1,R.drawable.e_t_2,R.drawable.e_t_3,R.drawable.e_t_4,R.drawable.e_t_5,R.drawable.e_t_6,R.drawable.e_t_7,R.drawable.e_t_8,R.drawable.e_t_9, R.drawable.e_t_10};
    private int eatBitmapTSId[] = {0,R.drawable.e_t_s_1,R.drawable.e_t_s_2,R.drawable.e_t_s_3,R.drawable.e_t_s_4,R.drawable.e_t_s_5,R.drawable.e_t_s_6,R.drawable.e_t_s_7,R.drawable.e_t_s_8,R.drawable.e_t_s_9,R.drawable.e_t_s_10};
    private int eatBitmapSmileId[] = {0,R.drawable.e_hp_1,R.drawable.e_hp_2,R.drawable.e_hp_3,R.drawable.e_hp_4,R.drawable.e_hp_5,R.drawable.e_hp_6,R.drawable.e_hp_7,R.drawable.e_hp_8,R.drawable.e_hp_9,R.drawable.e_hp_10};
    private int eatDarkButtonBitmapId[] = {R.drawable.chervyak,R.drawable.mc1,R.drawable.mc2,R.drawable.mc3,R.drawable.mc4,R.drawable.mc5,R.drawable.mc6,R.drawable.mc7,R.drawable.mc8,R.drawable.mc9,R.drawable.mc10};
    private int playDarkButtonBitmapId[] = {R.drawable.petur,R.drawable.mp1,R.drawable.mp2,R.drawable.mp3,R.drawable.mp4,R.drawable.mp5,R.drawable.mp6,R.drawable.mp7,R.drawable.mp8,R.drawable.mp9,R.drawable.mp10,R.drawable.mp11,R.drawable.mp12,R.drawable.mp13,R.drawable.mp14,R.drawable.mp15};
    private int sleepDarkButtonBitmapId[] = {R.drawable.qnel, R.drawable.mq1, R.drawable.mq2, R.drawable.mq3, R.drawable.mq4, R.drawable.mq5, R.drawable.mq6, R.drawable.mq7, R.drawable.mq8, R.drawable.mq9,R.drawable.mq10,R.drawable.mq11,R.drawable.mq12,R.drawable.mq13,R.drawable.mq14,R.drawable.mq15,R.drawable.mq16,R.drawable.mq17,R.drawable.mq18,R.drawable.mq19,R.drawable.mq20,R.drawable.mq21,R.drawable.mq22,R.drawable.mq23,R.drawable.mq24,R.drawable.mq25,R.drawable.mq26,R.drawable.mq27,R.drawable.mq28,R.drawable.mq29,R.drawable.mq30,R.drawable.mq31,R.drawable.mq32,R.drawable.mq33,R.drawable.mq34,R.drawable.mq35,R.drawable.mq36,R.drawable.mq37,R.drawable.mq38,R.drawable.mq39,R.drawable.mq40,R.drawable.mq41,R.drawable.mq42,R.drawable.mq43,R.drawable.mq44,R.drawable.mq45,R.drawable.mq46,R.drawable.mq47,R.drawable.mq48,R.drawable.mq49,R.drawable.mq50,R.drawable.mq51,R.drawable.mq52,R.drawable.mq53,R.drawable.mq54,R.drawable.mq55,R.drawable.mq56,R.drawable.mq57,R.drawable.mq58,R.drawable.mq59,R.drawable.mq60};
    private int poopBitmapId[] = {0,R.drawable.qaq1,R.drawable.qaq2,R.drawable.qaq3,R.drawable.qaq4};
    private int poopingBitmapUsualId[] = {0,R.drawable.qaqox1,R.drawable.qaqox2,R.drawable.qaqox3,R.drawable.qaqox4,R.drawable.qaqox5,R.drawable.qaqox6,R.drawable.qaqox7,R.drawable.qaqox8,R.drawable.qaqox9,R.drawable.qaqox10};
    private int poopingBitmapDHId[] = {0,R.drawable.p_d_h1,R.drawable.p_d_h2,R.drawable.p_d_h3,R.drawable.p_d_h4,R.drawable.p_d_h5,R.drawable.p_d_h6,R.drawable.p_d_h7,R.drawable.p_d_h8,R.drawable.p_d_h9,R.drawable.p_d_h10};
    private int poopingBitmapDSHId[] = {0,R.drawable.p_d_s_h1,R.drawable.p_d_s_h2,R.drawable.p_d_s_h3,R.drawable.p_d_s_h4,R.drawable.p_d_s_h5,R.drawable.p_d_s_h6,R.drawable.p_d_s_h7,R.drawable.p_d_s_h8,R.drawable.p_d_s_h9,R.drawable.p_d_s_h10};
    private int poopingBitmapDSId[] = {0,R.drawable.p_d_s1,R.drawable.p_d_s2,R.drawable.p_d_s3,R.drawable.p_d_s4,R.drawable.p_d_s5,R.drawable.p_d_s6,R.drawable.p_d_s7,R.drawable.p_d_s8,R.drawable.p_d_s9,R.drawable.p_d_s10};
    private int poopingBitmapDTHId[] = {0,R.drawable.p_d_t_h1,R.drawable.p_d_t_h2,R.drawable.p_d_t_h3,R.drawable.p_d_t_h4,R.drawable.p_d_t_h5,R.drawable.p_d_t_h6,R.drawable.p_d_t_h7,R.drawable.p_d_t_h8,R.drawable.p_d_t_h9,R.drawable.p_d_t_h10};
    private int poopingBitmapDTSHId[] = {0,R.drawable.p_d_t_s_h1,R.drawable.p_d_t_s_h2,R.drawable.p_d_t_s_h3,R.drawable.p_d_t_s_h4,R.drawable.p_d_t_s_h5,R.drawable.p_d_t_s_h6,R.drawable.p_d_t_s_h7,R.drawable.p_d_t_s_h8,R.drawable.p_d_t_s_h9,R.drawable.p_d_t_s_h10};
    private int poopingBitmapDTSId[] = {0,R.drawable.p_d_t_s1,R.drawable.p_d_t_s2,R.drawable.p_d_t_s3,R.drawable.p_d_t_s4,R.drawable.p_d_t_s5,R.drawable.p_d_t_s6,R.drawable.p_d_t_s7,R.drawable.p_d_t_s8,R.drawable.p_d_t_s9,R.drawable.p_d_t_s10};
    private int poopingBitmapDTId[] = {0,R.drawable.p_d_t1,R.drawable.p_d_t2,R.drawable.p_d_t3,R.drawable.p_d_t4,R.drawable.p_d_t5,R.drawable.p_d_t6,R.drawable.p_d_t7,R.drawable.p_d_t8,R.drawable.p_d_t9,R.drawable.p_d_t10};
    private int poopingBitmapDId[] = {0,R.drawable.p_d1,R.drawable.p_d2,R.drawable.p_d3,R.drawable.p_d4,R.drawable.p_d5,R.drawable.p_d6,R.drawable.p_d7,R.drawable.p_d8,R.drawable.p_d9,R.drawable.p_d10};
    private int poopingBitmapHSId[] = {0,R.drawable.p_h_s1,R.drawable.p_h_s2,R.drawable.p_h_s3,R.drawable.p_h_s4,R.drawable.p_h_s5,R.drawable.p_h_s6,R.drawable.p_h_s7,R.drawable.p_h_s8,R.drawable.p_h_s9,R.drawable.p_h_s10};
    private int poopingBitmapHId[] = {0,R.drawable.p_h1,R.drawable.p_h2,R.drawable.p_h3,R.drawable.p_h4,R.drawable.p_h5,R.drawable.p_h6,R.drawable.p_h7,R.drawable.p_h8,R.drawable.p_h9,R.drawable.p_h10};
    private int poopingBitmapSId[] = {0,R.drawable.p_s1,R.drawable.p_s2,R.drawable.p_s3,R.drawable.p_s4,R.drawable.p_s5,R.drawable.p_s6,R.drawable.p_s7,R.drawable.p_s8,R.drawable.p_s9,R.drawable.p_s10};
    private int poopingBitmapTHId[] = {0,R.drawable.p_t_h1,R.drawable.p_t_h2,R.drawable.p_t_h3,R.drawable.p_t_h4,R.drawable.p_t_h5,R.drawable.p_t_h6,R.drawable.p_t_h7,R.drawable.p_t_h8,R.drawable.p_t_h9,R.drawable.p_t_h10};
    private int poopingBitmapTHSId[] = {0,R.drawable.p_t_s_h1,R.drawable.p_t_s_h2,R.drawable.p_t_s_h3,R.drawable.p_t_s_h4,R.drawable.p_t_s_h5,R.drawable.p_t_s_h6,R.drawable.p_t_s_h7,R.drawable.p_t_s_h8,R.drawable.p_t_s_h9,R.drawable.p_t_s_h10};
    private int poopingBitmapTSId[] = {0,R.drawable.p_t_s1,R.drawable.p_t_s2,R.drawable.p_t_s3,R.drawable.p_t_s4,R.drawable.p_t_s5,R.drawable.p_t_s6,R.drawable.p_t_s7,R.drawable.p_t_s8,R.drawable.p_t_s9,R.drawable.p_t_s10};
    private int poopingBitmapTId[] = {0,R.drawable.p_t1,R.drawable.p_t2,R.drawable.p_t3,R.drawable.p_t4,R.drawable.p_t5,R.drawable.p_t6,R.drawable.p_t7,R.drawable.p_t8,R.drawable.p_t9,R.drawable.p_t10};
    private int getFoinBitmapId[] = {R.drawable.foin7,R.drawable.foin1,R.drawable.foin2,R.drawable.foin3,R.drawable.foin4,R.drawable.foin5,R.drawable.foin6,R.drawable.foin7};
    private int levelBitmapId[] = {0,R.drawable.level1,R.drawable.level2,R.drawable.level3,R.drawable.level4,R.drawable.level5,R.drawable.level6,R.drawable.level7,R.drawable.level8,R.drawable.level9,R.drawable.level10,R.drawable.level11,R.drawable.level12,R.drawable.level13,R.drawable.level14,R.drawable.level15,R.drawable.level16,R.drawable.level17,R.drawable.level18};
    public DrawThread(){}
    public DrawThread(Context context, SurfaceHolder surfaceHolder, MyDraw myDraw, int timePassed) {
        this.view = view;
        this.timePassed = timePassed;
        this.surfaceHolder = surfaceHolder;
        this.context = context;
        this.res = context.getResources();
        mediaPlayerHappy = MediaPlayer.create(context, R.raw.happysong);
        mediaPlayerHit = MediaPlayer.create(context, R.raw.hitsong);
        mediaPlayerWash = MediaPlayer.create(context,R.raw.washsong);
        sharedPreferences = ((Activity)context).getPreferences(Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        foin = sharedPreferences.getInt("FOIN", 0);
        level = sharedPreferences.getInt("LEVEL", 1);
        for (int i = 1; i < 11; i++) {
            eatBitmap[i] = BitmapFactory.decodeResource(context.getResources(), eatBitmapId[i]);
            eatBitmapD[i] = BitmapFactory.decodeResource(context.getResources(), eatBitmapDId[i]);
            eatBitmapDS[i] = BitmapFactory.decodeResource(context.getResources(), eatBitmapDSId[i]);
            eatBitmapDT[i] = BitmapFactory.decodeResource(context.getResources(), eatBitmapDTId[i]);
            eatBitmapDTS[i] = BitmapFactory.decodeResource(context.getResources(), eatBitmapDTSId[i]);
            eatBitmapS[i] = BitmapFactory.decodeResource(context.getResources(), eatBitmapSId[i]);
            eatBitmapT[i] = BitmapFactory.decodeResource(context.getResources(), eatBitmapTId[i]);
            eatBitmapTS[i] = BitmapFactory.decodeResource(context.getResources(), eatBitmapTSId[i]);
            eatBitmapSmile[i] = BitmapFactory.decodeResource(context.getResources(), eatBitmapSmileId[i]);
            poopingBitmapUsual[i] = BitmapFactory.decodeResource(context.getResources() ,poopingBitmapUsualId[i]);
            poopingBitmapDH[i] = BitmapFactory.decodeResource(context.getResources() ,poopingBitmapDHId[i]);
            poopingBitmapDSH[i] = BitmapFactory.decodeResource(context.getResources() ,poopingBitmapDSHId[i]);
            poopingBitmapDS[i] = BitmapFactory.decodeResource(context.getResources() ,poopingBitmapDSId[i]);
            poopingBitmapDTH[i] = BitmapFactory.decodeResource(context.getResources() ,poopingBitmapDTHId[i]);
            poopingBitmapDTSH[i] = BitmapFactory.decodeResource(context.getResources() ,poopingBitmapDTSHId[i]);
            poopingBitmapDTS[i] = BitmapFactory.decodeResource(context.getResources() ,poopingBitmapDTSId[i]);
            poopingBitmapDT[i] = BitmapFactory.decodeResource(context.getResources() ,poopingBitmapDTId[i]);
            poopingBitmapD[i] = BitmapFactory.decodeResource(context.getResources() ,poopingBitmapDId[i]);
            poopingBitmapHS[i] = BitmapFactory.decodeResource(context.getResources() ,poopingBitmapHSId[i]);
            poopingBitmapH[i] = BitmapFactory.decodeResource(context.getResources() ,poopingBitmapHId[i]);
            poopingBitmapS[i] = BitmapFactory.decodeResource(context.getResources() ,poopingBitmapSId[i]);
            poopingBitmapTH[i] = BitmapFactory.decodeResource(context.getResources() ,poopingBitmapTHId[i]);
            poopingBitmapTHS[i] = BitmapFactory.decodeResource(context.getResources() ,poopingBitmapTHSId[i]);
            poopingBitmapTS[i] = BitmapFactory.decodeResource(context.getResources() ,poopingBitmapTSId[i]);
            poopingBitmapT[i] = BitmapFactory.decodeResource(context.getResources() ,poopingBitmapTId[i]);
        }
        for (int i = 1; i < 21; i++) {
            playBitmap[i] = BitmapFactory.decodeResource(context.getResources(), playBitmapId[i]);
            playBitmapD[i] = BitmapFactory.decodeResource(context.getResources(), playBitmapDId[i]);
            playBitmapT[i] = BitmapFactory.decodeResource(context.getResources(), playBitmapTId[i]);
            playBitmapDT[i] = BitmapFactory.decodeResource(context.getResources(), playBitmapDTId[i]);
        }
        for (int i = 1; i < 5; i++) {
            flyBitmapUsual[i] = BitmapFactory.decodeResource(context.getResources(),flyBitmapUsualId[i]);
            flyBitmapDH[i] = BitmapFactory.decodeResource(context.getResources(),flyBitmapDHId[i]);
            flyBitmapD[i] = BitmapFactory.decodeResource(context.getResources(),flyBitmapDId[i]);
            flyBitmapDS[i] = BitmapFactory.decodeResource(context.getResources(),flyBitmapDSId[i]);
            flyBitmapH[i] = BitmapFactory.decodeResource(context.getResources(),flyBitmapHId[i]);
            flyBitmapSmile[i] = BitmapFactory.decodeResource(context.getResources(),flyBitmapSmileId[i]);
            flyBitmapSDH[i] = BitmapFactory.decodeResource(context.getResources(),flyBitmapSDHId[i]);
            flyBitmapSH[i] = BitmapFactory.decodeResource(context.getResources(),flyBitmapSHId[i]);
            flyBitmapS[i] = BitmapFactory.decodeResource(context.getResources(),flyBitmapSId[i]);
            flyBitmapTDH[i] = BitmapFactory.decodeResource(context.getResources(),flyBitmapTDHId[i]);
            flyBitmapTDSH[i] = BitmapFactory.decodeResource(context.getResources(),flyBitmapTDSHId[i]);
            flyBitmapTDS[i] = BitmapFactory.decodeResource(context.getResources(),flyBitmapTDSId[i]);
            flyBitmapTD[i] = BitmapFactory.decodeResource(context.getResources(),flyBitmapTDId[i]);
            flyBitmapTH[i] = BitmapFactory.decodeResource(context.getResources(),flyBitmapTHId[i]);
            flyBitmapTSH[i] = BitmapFactory.decodeResource(context.getResources(),flyBitmapTSHId[i]);
            flyBitmapTS[i] = BitmapFactory.decodeResource(context.getResources(),flyBitmapTSId[i]);
            flyBitmapT[i] = BitmapFactory.decodeResource(context.getResources(),flyBitmapTId[i]);
            poopBitmap[i] = BitmapFactory.decodeResource(context.getResources(), poopBitmapId[i]);

        }
        for (int i = 1; i < 15; i++) {
            hitBitmap[i] = BitmapFactory.decodeResource(context.getResources(), hitBitmapId[i]);
            hitBitmapD[i] = BitmapFactory.decodeResource(context.getResources(), hitBitmapDId[i]);
            hitBitmapH[i] = BitmapFactory.decodeResource(context.getResources(), hitBitmapHId[i]);
            hitBitmapDH[i] = BitmapFactory.decodeResource(context.getResources(), hitBitmapDHId[i]);
        }
        for (int i = 1; i < 49; i++) {
            washBitmap[i] = BitmapFactory.decodeResource(context.getResources(),washBitmapId[i]);
        }
        for (int i = 0; i < 11; i++) {
            eatDarkButtonBitmap[i] = BitmapFactory.decodeResource(context.getResources(), eatDarkButtonBitmapId[i]);
        }
        for (int i = 0; i < 16; i++) {
            playDarkButtonBitmap[i] =BitmapFactory.decodeResource(context.getResources(),playDarkButtonBitmapId[i]);
        }
        for (int i = 0; i < 61; i++) {
            sleepDarkButtonBitmap[i] = BitmapFactory.decodeResource(context.getResources(), sleepDarkButtonBitmapId[i]);
        }
        for (int i = 0; i < 8; i++) {
            getFoinBitmap[i] = BitmapFactory.decodeResource(context.getResources(),getFoinBitmapId[i]);
        }
        for (int i = 1; i < 19; i++) {
            levelBitmap[i] = BitmapFactory.decodeResource(context.getResources() ,levelBitmapId[i]);
        }
        washButtonBitmap = BitmapFactory.decodeResource(context.getResources(),R.drawable.lvacvelu_knopka);
        washButtonBitmap2 = BitmapFactory.decodeResource(context.getResources(),R.drawable.lvacvelu_knopka);
        washButtonBitmapPoop = BitmapFactory.decodeResource(context.getResources(),R.drawable.lvacvelu_knopkaa);
        washDarkButtonBitmap = BitmapFactory.decodeResource(context.getResources(),R.drawable.lvacvelu_knopka_mug);
        bitmapbg = BitmapFactory.decodeResource(context.getResources(), R.drawable.background1);
        bitmapDarkbg = BitmapFactory.decodeResource(context.getResources(), R.drawable.background2);
        bitmapDarkkust = BitmapFactory.decodeResource(context.getResources(), R.drawable.kustik2);
        bitmapkust = BitmapFactory.decodeResource(context.getResources(), R.drawable.kustik);
        bitmapDirt = BitmapFactory.decodeResource(context.getResources(),R.drawable.kextik);
        bitmapHungry = BitmapFactory.decodeResource(context.getResources(),R.drawable.tarelka);
        bitmapSleep = BitmapFactory.decodeResource(context.getResources(),R.drawable.zzz);
        bitmapHappy = BitmapFactory.decodeResource(context.getResources(),R.drawable.smilik);
        eatButtonBitmap =  BitmapFactory.decodeResource(context.getResources(), R.drawable.chervyak);
        playButtonBitmap =  BitmapFactory.decodeResource(context.getResources(), R.drawable.petur);
        sleepButtonBitmap =  BitmapFactory.decodeResource(context.getResources(), R.drawable.qnel);
        eatButtonBitmapPoop =  BitmapFactory.decodeResource(context.getResources(), R.drawable.utelu_knopkaa);
        playButtonBitmapPoop =  BitmapFactory.decodeResource(context.getResources(), R.drawable.xaxalu_knopkaa);
        sleepButtonBitmapPoop =  BitmapFactory.decodeResource(context.getResources(), R.drawable.qnelu_knopkaa);
        screenshotBitmap =  BitmapFactory.decodeResource(context.getResources(),R.drawable.screenshot);
        shopButton =  BitmapFactory.decodeResource(context.getResources(),R.drawable.xanut);
        bird = bitmapSmile1;
        bitmapUsual1 = BitmapFactory.decodeResource(context.getResources(),R.drawable.sovorakan1);
        bitmapUsual2 = BitmapFactory.decodeResource(context.getResources(),R.drawable.sovorakan2);
        bitmapDTSH1 = BitmapFactory.decodeResource(context.getResources(),R.drawable.d_t_s_h_1);
        bitmapDTSH2 = BitmapFactory.decodeResource(context.getResources(),R.drawable.d_t_s_h_2);
        bitmapDTS1 = BitmapFactory.decodeResource(context.getResources(),R.drawable.d_t_s_1);
        bitmapDTS2 = BitmapFactory.decodeResource(context.getResources(),R.drawable.d_t_s_2);
        bitmapDTH1 = BitmapFactory.decodeResource(context.getResources(),R.drawable.d_t_h_1);
        bitmapDTH2 = BitmapFactory.decodeResource(context.getResources(),R.drawable.d_t_h_2);
        bitmapDSH1 = BitmapFactory.decodeResource(context.getResources(),R.drawable.d_s_h_1);
        bitmapDSH2 = BitmapFactory.decodeResource(context.getResources(),R.drawable.d_s_h_2);
        bitmapTSH1 = BitmapFactory.decodeResource(context.getResources(),R.drawable.t_s_h_1);
        bitmapTSH2 = BitmapFactory.decodeResource(context.getResources(),R.drawable.t_s_h_2);
        bitmapDH1 = BitmapFactory.decodeResource(context.getResources(),R.drawable.d_h_1);
        bitmapDH2 = BitmapFactory.decodeResource(context.getResources(),R.drawable.d_h_2);
        bitmapDS1 = BitmapFactory.decodeResource(context.getResources(),R.drawable.d_s_1);
        bitmapDS2 = BitmapFactory.decodeResource(context.getResources(),R.drawable.d_s_2);
        bitmapDT1 = BitmapFactory.decodeResource(context.getResources(),R.drawable.d_t_1);
        bitmapDT2 = BitmapFactory.decodeResource(context.getResources(),R.drawable.d_t_2);
        bitmapSH1 = BitmapFactory.decodeResource(context.getResources(),R.drawable.s_h_1);
        bitmapSH2 = BitmapFactory.decodeResource(context.getResources(),R.drawable.s_h_2);
        bitmapTH1 = BitmapFactory.decodeResource(context.getResources(),R.drawable.t_h_1);
        bitmapTH2 = BitmapFactory.decodeResource(context.getResources(),R.drawable.t_h_2);
        bitmapTS1 = BitmapFactory.decodeResource(context.getResources(),R.drawable.t_s_1);
        bitmapTS2 = BitmapFactory.decodeResource(context.getResources(),R.drawable.t_s_2);
        bitmapD1 = BitmapFactory.decodeResource(context.getResources(),R.drawable.kextot1);
        bitmapD2 = BitmapFactory.decodeResource(context.getResources(),R.drawable.kextot2);
        bitmapT1 = BitmapFactory.decodeResource(context.getResources(),R.drawable.hognac1);
        bitmapT2 = BitmapFactory.decodeResource(context.getResources(),R.drawable.hognac2);
        bitmapS1 = BitmapFactory.decodeResource(context.getResources(),R.drawable.txur1);
        bitmapS2 = BitmapFactory.decodeResource(context.getResources(),R.drawable.txur2);
        bitmapH1 = BitmapFactory.decodeResource(context.getResources(),R.drawable.sovac1);
        bitmapH2 = BitmapFactory.decodeResource(context.getResources(),R.drawable.sovac2);
        bitmapSmile1 = BitmapFactory.decodeResource(context.getResources(),R.drawable.urax1);
        bitmapSmile2 = BitmapFactory.decodeResource(context.getResources(),R.drawable.urax2);
        sleepUsual1 = BitmapFactory.decodeResource(context.getResources(),R.drawable.qnac1);
        sleepUsual2 = BitmapFactory.decodeResource(context.getResources(),R.drawable.qnac2);
        sleepDH1 = BitmapFactory.decodeResource(context.getResources(),R.drawable.sl_d_h1);
        sleepDH2 = BitmapFactory.decodeResource(context.getResources(),R.drawable.sl_d_h2);
        sleepDSH1 = BitmapFactory.decodeResource(context.getResources(),R.drawable.sl_d_s_h1);
        sleepDSH2 = BitmapFactory.decodeResource(context.getResources(),R.drawable.sl_d_s_h2);
        sleepDS1 = BitmapFactory.decodeResource(context.getResources(),R.drawable.sl_d_s1);
        sleepDS2 = BitmapFactory.decodeResource(context.getResources(),R.drawable.sl_d_s2);
        sleepDH1 = BitmapFactory.decodeResource(context.getResources(),R.drawable.sl_d_h1);
        sleepDH2 = BitmapFactory.decodeResource(context.getResources(),R.drawable.sl_d_h2);
        sleepD1 = BitmapFactory.decodeResource(context.getResources(),R.drawable.sl_d1);
        sleepD2 = BitmapFactory.decodeResource(context.getResources(),R.drawable.sl_d2);
        sleepH1 = BitmapFactory.decodeResource(context.getResources(),R.drawable.sl_h1);
        sleepH2 = BitmapFactory.decodeResource(context.getResources(),R.drawable.sl_h2);
        sleepSmile1 = BitmapFactory.decodeResource(context.getResources(),R.drawable.sl_hp1);
        sleepSmile2 = BitmapFactory.decodeResource(context.getResources(),R.drawable.sl_hp2);
        sleepSH1 = BitmapFactory.decodeResource(context.getResources(),R.drawable.sl_s_h1);
        sleepSH2 = BitmapFactory.decodeResource(context.getResources(),R.drawable.sl_s_h2);
        sleepS1 = BitmapFactory.decodeResource(context.getResources(),R.drawable.sl_s1);
        sleepS2 = BitmapFactory.decodeResource(context.getResources(),R.drawable.sl_s2);
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
            sendScreen();
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
            eatBitmap[i] = Bitmap.createScaledBitmap(eatBitmap[i], (int) (canvas.getWidth() * birdWidth), (int) (canvas.getHeight() * 283.5 / 540), true);
            eatBitmapSmile[i] = Bitmap.createScaledBitmap(eatBitmapSmile[i], (int) (canvas.getWidth() * birdWidth), (int) (canvas.getHeight() * 283.5 / 540), true);
            eatBitmapD[i] = Bitmap.createScaledBitmap(eatBitmapD[i], (int) (canvas.getWidth() * birdWidth), (int) (canvas.getHeight() * 283.5 / 540), true);
            eatBitmapDS[i] = Bitmap.createScaledBitmap(eatBitmapDS[i], (int) (canvas.getWidth() * birdWidth), (int) (canvas.getHeight() * 283.5 / 540), true);
            eatBitmapDT[i] = Bitmap.createScaledBitmap(eatBitmapDT[i], (int) (canvas.getWidth() * birdWidth), (int) (canvas.getHeight() * 283.5 / 540), true);
            eatBitmapDTS[i] = Bitmap.createScaledBitmap(eatBitmapDTS[i], (int) (canvas.getWidth() * birdWidth), (int) (canvas.getHeight() * 283.5 / 540), true);
            eatBitmapS[i] = Bitmap.createScaledBitmap(eatBitmapS[i], (int) (canvas.getWidth() * birdWidth), (int) (canvas.getHeight() * 283.5 / 540), true);
            eatBitmapT[i] = Bitmap.createScaledBitmap(eatBitmapT[i], (int) (canvas.getWidth() * birdWidth), (int) (canvas.getHeight() * 283.5 / 540), true);
            eatBitmapTS[i] = Bitmap.createScaledBitmap(eatBitmapTS[i], (int) (canvas.getWidth() * birdWidth), (int) (canvas.getHeight() * 283.5 / 540), true);
        }
        for (int i = 1; i < 21; i++) {
            playBitmap[i] = Bitmap.createScaledBitmap(playBitmap[i], canvas.getWidth() * 194 / 1050, canvas.getHeight() * 172 / 540, true);
            playBitmapD[i] = Bitmap.createScaledBitmap(playBitmapD[i], canvas.getWidth() * 194 / 1050, canvas.getHeight() * 172 / 540, true);
            playBitmapDT[i] = Bitmap.createScaledBitmap(playBitmapDT[i], canvas.getWidth() * 194 / 1050, canvas.getHeight() * 172 / 540, true);
            playBitmapT[i] = Bitmap.createScaledBitmap(playBitmapT[i], canvas.getWidth() * 194 / 1050, canvas.getHeight() * 172 / 540, true);
        }
        for (int i = 1; i < 5; i++) {
            flyBitmapSmile[i] = Bitmap.createScaledBitmap(flyBitmapSmile[i], canvas.getWidth() * 150 / 1050, canvas.getHeight() * 179 / 540, true);
            flyBitmapDH[i] = Bitmap.createScaledBitmap(flyBitmapDH[i], canvas.getWidth() * 150 / 1050, canvas.getHeight() * 179 / 540, true);
            flyBitmapDS[i] = Bitmap.createScaledBitmap(flyBitmapDS[i], canvas.getWidth() * 150 / 1050, canvas.getHeight() * 179 / 540, true);
            flyBitmapD[i] = Bitmap.createScaledBitmap(flyBitmapD[i], canvas.getWidth() * 150 / 1050, canvas.getHeight() * 179 / 540, true);
            flyBitmapH[i] = Bitmap.createScaledBitmap(flyBitmapH[i], canvas.getWidth() * 150 / 1050, canvas.getHeight() * 179 / 540, true);
            flyBitmapSDH[i] = Bitmap.createScaledBitmap(flyBitmapSDH[i], canvas.getWidth() * 150 / 1050, canvas.getHeight() * 179 / 540, true);
            flyBitmapSH[i] = Bitmap.createScaledBitmap(flyBitmapSH[i], canvas.getWidth() * 150 / 1050, canvas.getHeight() * 179 / 540, true);
            flyBitmapS[i] = Bitmap.createScaledBitmap(flyBitmapS[i], canvas.getWidth() * 150 / 1050, canvas.getHeight() * 179 / 540, true);
            flyBitmapTDH[i] = Bitmap.createScaledBitmap(flyBitmapTDH[i], canvas.getWidth() * 150 / 1050, canvas.getHeight() * 179 / 540, true);
            flyBitmapTDSH[i] = Bitmap.createScaledBitmap(flyBitmapTDSH[i], canvas.getWidth() * 150 / 1050, canvas.getHeight() * 179 / 540, true);
            flyBitmapTDS[i] = Bitmap.createScaledBitmap(flyBitmapTDS[i], canvas.getWidth() * 150 / 1050, canvas.getHeight() * 179 / 540, true);
            flyBitmapTD[i] = Bitmap.createScaledBitmap(flyBitmapTD[i], canvas.getWidth() * 150 / 1050, canvas.getHeight() * 179 / 540, true);
            flyBitmapTH[i] = Bitmap.createScaledBitmap(flyBitmapTH[i], canvas.getWidth() * 150 / 1050, canvas.getHeight() * 179 / 540, true);
            flyBitmapTSH[i] = Bitmap.createScaledBitmap(flyBitmapTSH[i], canvas.getWidth() * 150 / 1050, canvas.getHeight() * 179 / 540, true);
            flyBitmapTS[i] = Bitmap.createScaledBitmap(flyBitmapTS[i], canvas.getWidth() * 150 / 1050, canvas.getHeight() * 179 / 540, true);
            flyBitmapT[i] = Bitmap.createScaledBitmap(flyBitmapT[i], canvas.getWidth() * 150 / 1050, canvas.getHeight() * 179 / 540, true);
            flyBitmapUsual[i] = Bitmap.createScaledBitmap(flyBitmapUsual[i], canvas.getWidth() * 150 / 1050, canvas.getHeight() * 179 / 540, true);
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

    private void stateChanger(float stateRight, float stateLeft, float stateWeight, double stateChangeValue,int stateChecker) {
            if (stateRight - stateChangeValue * stateWeight >= stateLeft) {
                stateChangeValue *= stateWeight;
                stateRight -= stateChangeValue;
                stateChangeValue /= stateWeight;
            }
            if (stateRight >= stateLeft && stateRight - stateChangeValue * stateWeight < stateLeft) {
                stateRight = stateLeft;
            }
            if(stateChecker == 1){
                dirtRight = stateRight;
                editor.putFloat("DIRT", stateRight);
            }
            else if (stateChecker == 2) {
                hungryRight = stateRight;
                editor.putFloat("HUNGRY", stateRight);
            }
            else if (stateChecker == 3) {
                sleepRight = stateRight;
                editor.putFloat("SLEEP", stateRight);
            }
            else if (stateChecker == 4) {
                happyRight = stateRight;
                editor.putFloat("HAPPY", stateRight);
            }
            editor.apply();
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
                        // Задний фон

                        if (!flying && !sleeping && !laying) {
                            canvas.drawBitmap(bitmapbg, 0, 0, paint);
                            canvas.drawBitmap(bitmapkust, (float) canvas.getWidth() * 29 / 1050, (float) canvas.getHeight() * 230 / 540, paint);
                        } else {
                            canvas.drawBitmap(bitmapDarkbg, 0, 0, paint);
                            canvas.drawBitmap(bitmapDarkkust, (float) canvas.getWidth() * 29 / 1050, (float) canvas.getHeight() * 230 / 540, paint);
                        }

                        // Диаграмма уровня

                        canvas.drawRect((float) canvas.getWidth() * 843 / 1050, (float) canvas.getHeight() * 7 / 540,
                                (float) canvas.getWidth() * 1041 / 1050, (float) canvas.getHeight() * 43 / 540, paintBlack);
                        canvas.drawRect((float) canvas.getWidth() * levelLeft, (float) canvas.getHeight() * levelTop, (float) canvas.getWidth() * levelRight, (float) canvas.getHeight() * levelBottom, paintLevel);

                        // Диаграмма загрязнения

                        canvas.drawRect((float) canvas.getWidth() * 876 / 1050, (float) canvas.getHeight() * 54 / 540,
                                (float) canvas.getWidth() * 1041 / 1050, (float) canvas.getHeight() * 80 / 540, paintBlack);
                        canvas.drawRect((float) canvas.getWidth() * dirtLeft, (float) canvas.getHeight() * dirtTop, (float) canvas.getWidth() * dirtRight, (float) canvas.getHeight() * dirtBottom, paintDirt);

                        // Таймер загрязнения
                        if (!dirtTimeIsPassed && !washing) {
                            new DirtTimerThread().start();
                            dirtTimeIsPassed = true;
                        }
                        if (dirtNeedToDrawNow) {
                            stateChanger(dirtRight, dirtLeft, dirtWeight, dirtyChangeValue, 1);
                            dirtNeedToDrawNow = false;
                        }

                        // Диаграмма голода

                        canvas.drawRect((float) canvas.getWidth() * 876 / 1050, (float) canvas.getHeight() * 91 / 540,
                                (float) canvas.getWidth() * 1041 / 1050, (float) canvas.getHeight() * 117 / 540, paintBlack);
                        canvas.drawRect((float) canvas.getWidth() * hungryLeft, (float) canvas.getHeight() * hungryTop, (float) canvas.getWidth() * hungryRight, (float) canvas.getHeight() * hungryBottom, paintHungry);
                        // Таймер голода
                        if (!hungryTimeIsPassed && !eating) {
                            new HungryTimerThread().start();
                            hungryTimeIsPassed = true;
                        }
                        if (hungryNeedToDrawNow) {
                            stateChanger(hungryRight, hungryLeft, hungryWeight, hungryChangeValue, 2);
                            hungryNeedToDrawNow = false;
                        }

                        // Диаграмма усталости

                        canvas.drawRect((float) canvas.getWidth() * 876 / 1050, (float) canvas.getHeight() * 128 / 540,
                                (float) canvas.getWidth() * 1041 / 1050, (float) canvas.getHeight() * 154 / 540, paintBlack);
                        canvas.drawRect((float) canvas.getWidth() * sleepLeft, (float) canvas.getHeight() * sleepTop, (float) canvas.getWidth() * sleepRight, (float) canvas.getHeight() * sleepBottom, paintSleep);

                        // Таймер усталости
                        if (!sleepTimeIsPassed && !sleeping && !flying && !flyingBack && !laying) {
                            new SleepTimerThread().start();
                            sleepTimeIsPassed = true;
                        }
                        if (sleepNeedToDrawNow) {
                            stateChanger(sleepRight, sleepLeft, sleepWeight, sleepChangeValue, 3);
                            sleepNeedToDrawNow = false;
                        }

                        // Диаграмма радости

                        canvas.drawRect((float) canvas.getWidth() * 876 / 1050, (float) canvas.getHeight() * 165 / 540,
                                (float) canvas.getWidth() * 1041 / 1050, (float) canvas.getHeight() * 191 / 540, paintBlack);
                        canvas.drawRect((float) canvas.getWidth() * happyLeft, (float) canvas.getHeight() * happyTop, (float) canvas.getWidth() * happyRight, (float) canvas.getHeight() * happyBottom, paintHappy);

                        // Таймер радости
                        if (!happyTimeIsPassed && !playing) {
                            new HappyTimerThread().start();
                            happyTimeIsPassed = true;
                        }
                        if (happyNeedToDrawNow) {
                            stateChanger(happyRight, happyLeft, happyWeight, happyChangeValue, 4);
                            happyNeedToDrawNow = false;
                        }

                        // Иконки уровня, загрезнения, голода, усталости и радости

                        canvas.drawBitmap(levelBitmap[level], (float) canvas.getWidth() * 815 / 1050, (float) canvas.getHeight() * 2 / 540, paint);

                        canvas.drawBitmap(bitmapDirt, (float) canvas.getWidth() * 840 / 1050, (float) canvas.getHeight() * 41 / 540, paint);

                        canvas.drawBitmap(bitmapHungry, (float) canvas.getWidth() * 850 / 1050, (float) canvas.getHeight() * 87 / 540, paint);

                        canvas.drawBitmap(bitmapSleep, (float) canvas.getWidth() * 853 / 1050, (float) canvas.getHeight() * 110 / 540, paint);

                        canvas.drawBitmap(bitmapHappy, (float) canvas.getWidth() * 862 / 1050, (float) canvas.getHeight() * 161 / 540, paint);

                        // Проверяем  состояние птички
                        if (hungryRight - hungryLeft < hungryWeight / 2. && dirtRight - dirtLeft < dirtWeight / 2. && happyRight - happyLeft < happyWeight / 3. && sleepRight - sleepLeft < sleepWeight / 2.) {
                            birdBreath1 = bitmapDTSH1;
                            birdBreath2 = bitmapDTSH2;
                        } else if (dirtRight - dirtLeft < dirtWeight / 2. && happyRight - happyLeft < happyWeight / 3. && sleepRight - sleepLeft < sleepWeight / 2.) {
                            birdBreath1 = bitmapDTS1;
                            birdBreath2 = bitmapDTS2;
                        } else if (hungryRight - hungryLeft < hungryWeight / 2. && happyRight - happyLeft < happyWeight / 3. && sleepRight - sleepLeft < sleepWeight / 2.) {
                            birdBreath1 = bitmapTSH1;
                            birdBreath2 = bitmapTSH2;
                        } else if (hungryRight - hungryLeft < hungryWeight / 2. && dirtRight - dirtLeft < dirtWeight / 2. && sleepRight - sleepLeft < sleepWeight / 2.) {
                            birdBreath1 = bitmapDTH1;
                            birdBreath2 = bitmapDTH2;
                        } else if (hungryRight - hungryLeft < hungryWeight / 2. && dirtRight - dirtLeft < dirtWeight / 2. && happyRight - happyLeft < happyWeight / 3.) {
                            birdBreath1 = bitmapDSH1;
                            birdBreath2 = bitmapDSH2;
                        } else if (hungryRight - hungryLeft < hungryWeight / 2. && dirtRight - dirtLeft < dirtWeight / 2.) {
                            birdBreath1 = bitmapDH1;
                            birdBreath2 = bitmapDH2;
                        } else if (dirtRight - dirtLeft < dirtWeight / 2. && happyRight - happyLeft < happyWeight / 3.) {
                            birdBreath1 = bitmapDS1;
                            birdBreath2 = bitmapDS2;
                        } else if (dirtRight - dirtLeft < dirtWeight / 2. && sleepRight - sleepLeft < sleepWeight / 2.) {
                            birdBreath1 = bitmapDT1;
                            birdBreath2 = bitmapDT2;
                        } else if (hungryRight - hungryLeft < hungryWeight / 2. && happyRight - happyLeft < happyWeight / 3.) {
                            birdBreath1 = bitmapSH1;
                            birdBreath2 = bitmapSH2;
                        } else if (sleepRight - sleepLeft < sleepWeight / 2. && hungryRight - hungryLeft < hungryWeight / 2.) {
                            birdBreath1 = bitmapTH1;
                            birdBreath2 = bitmapTH2;
                        } else if (sleepRight - sleepLeft < sleepWeight / 2. && happyRight - happyLeft < happyWeight / 3.) {
                            birdBreath1 = bitmapTS1;
                            birdBreath2 = bitmapTS2;
                        } else if (dirtRight - dirtLeft < dirtWeight / 2.) {
                            birdBreath1 = bitmapD1;
                            birdBreath2 = bitmapD2;
                        } else if (sleepRight - sleepLeft < sleepWeight / 2.) {
                            birdBreath1 = bitmapT1;
                            birdBreath2 = bitmapT2;
                        } else if (happyRight - happyLeft < happyWeight / 3.) {
                            birdBreath1 = bitmapS1;
                            birdBreath2 = bitmapS2;
                        } else if (hungryRight - hungryLeft < hungryWeight / 2.) {
                            birdBreath1 = bitmapH1;
                            birdBreath2 = bitmapH2;
                        } else if (happyRight - happyLeft < (2.) * happyWeight / 3.) {
                            birdBreath1 = bitmapUsual1;
                            birdBreath2 = bitmapUsual2;
                        } else {
                            birdBreath1 = bitmapSmile1;
                            birdBreath2 = bitmapSmile2;
                        }
                        if (m == 0) {
                            m = 1;
                            bird = birdBreath1;
                        }
                        // Таймер дыхания

                        if (!eating && !playing && !flying && !sleeping && !laying && !hitting && !washing && !pooping && !flyPoop && !disgusting && !flyBackPoop) {
                            if (!timeIsPassed1 && !check) {
                                new ThreadBird1().start();
                                timeIsPassed1 = true;
                            }
                            if (needToDrawNow1 && !check) bird = birdBreath1;
                            if (!timeIsPassed2 && check) {
                                new ThreadBird2().start();
                                timeIsPassed2 = true;
                            }
                            if (needToDrawNow2 && check) bird = birdBreath2;
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
                        canvas.drawBitmap(getFoinBitmap[0], (float) canvas.getWidth() * 19 / 1050, (float) canvas.getHeight() * 19 / 540, paint);
                        if (foin >= 0 && foin <= 9)
                            canvas.drawText(foin + " ", (float) canvas.getWidth() * 101 / 1050, (float) canvas.getHeight() * 49 / 540, paintFoin);
                        if (foin >= 10 && foin <= 99)
                            canvas.drawText(foin + " ", (float) canvas.getWidth() * 95 / 1050, (float) canvas.getHeight() * 49 / 540, paintFoin);
                        if (foin >= 100 && foin <= 999)
                            canvas.drawText(foin + " ", (float) canvas.getWidth() * 89 / 1050, (float) canvas.getHeight() * 49 / 540, paintFoin);
                        if (foin >= 1000 && foin <= 9999)
                            canvas.drawText(foin + " ", (float) canvas.getWidth() * 83 / 1050, (float) canvas.getHeight() * 49 / 540, paintFoin);
                        // Кнопка магазина

                        canvas.drawBitmap(shopButton, (float) canvas.getWidth() * shopButtonLeft, (float) canvas.getHeight() * shopButtonTop, paint);

                        // Кнопка голода

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

                        // Кнопка радости

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

                        // Кнопка усталости

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
                        float sleepButtonLeft = (float) 241 / 1050;
                        canvas.drawBitmap(sleepButtonBitmap, (float) canvas.getWidth() * sleepButtonLeft, (float) canvas.getHeight() * sleepButtonTop, paint);

                        // Кнопка загрязнения

                        if (dirtRight - dirtLeft >= dirtWeight / 2. && !pooping && !flyPoop && !disgusting && !flyBackPoop)
                            washButtonBitmap = washDarkButtonBitmap;
                        if (dirtRight - dirtLeft <= dirtWeight / 2. && !pooping && !flyPoop && !disgusting && !flyBackPoop)
                            washButtonBitmap = washButtonBitmap2;
                        canvas.drawBitmap(washButtonBitmap, (float) canvas.getWidth() * dirtButtonLeft, (float) canvas.getHeight() * dirtButtonTop, paint);

                        // Какать
                        if (m5 == 0) {
                            new Minute().start();
                            m5 = 1;
                        }
                        if (eatScore >= 3 && pop && !pooping && !eating && !playing && !flying && !sleeping && !laying && !hitting && !washing) {
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
                                    if (birdBreath1 == bitmapDTSH1) bird = flyBitmapTDSH[4];
                                    else if (birdBreath1 == bitmapDH1) bird = flyBitmapDH[4];
                                    else if (birdBreath1 == bitmapDS1) bird = flyBitmapDS[4];
                                    else if (birdBreath1 == bitmapD1) bird = flyBitmapD[4];
                                    else if (birdBreath1 == bitmapH1) bird = flyBitmapH[4];
                                    else if (birdBreath1 == bitmapSmile1) bird = flyBitmapSmile[4];
                                    else if (birdBreath1 == bitmapSH1) bird = flyBitmapSH[4];
                                    else if (birdBreath1 == bitmapDSH1) bird = flyBitmapSDH[4];
                                    else if (birdBreath1 == bitmapS1) bird = flyBitmapS[4];
                                    else if (birdBreath1 == bitmapDTH1) bird = flyBitmapTDH[4];
                                    else if (birdBreath1 == bitmapDTS1) bird = flyBitmapTDS[4];
                                    else if (birdBreath1 == bitmapDT1) bird = flyBitmapTD[4];
                                    else if (birdBreath1 == bitmapTH1) bird = flyBitmapTH[4];
                                    else if (birdBreath1 == bitmapTSH1) bird = flyBitmapTSH[4];
                                    else if (birdBreath1 == bitmapTS1) bird = flyBitmapTS[4];
                                    else if (birdBreath1 == bitmapT1) bird = flyBitmapT[4];
                                    else if (birdBreath1 == bitmapUsual1) bird = flyBitmapUsual[4];
                                    break;
                                case 2:
                                    if (birdBreath1 == bitmapDTSH1) bird = flyBitmapTDSH[3];
                                    else if (birdBreath1 == bitmapDH1) bird = flyBitmapDH[3];
                                    else if (birdBreath1 == bitmapDS1) bird = flyBitmapDS[3];
                                    else if (birdBreath1 == bitmapD1) bird = flyBitmapD[3];
                                    else if (birdBreath1 == bitmapH1) bird = flyBitmapH[3];
                                    else if (birdBreath1 == bitmapSmile1) bird = flyBitmapSmile[3];
                                    else if (birdBreath1 == bitmapSH1) bird = flyBitmapSH[3];
                                    else if (birdBreath1 == bitmapDSH1) bird = flyBitmapSDH[3];
                                    else if (birdBreath1 == bitmapS1) bird = flyBitmapS[3];
                                    else if (birdBreath1 == bitmapDTH1) bird = flyBitmapTDH[3];
                                    else if (birdBreath1 == bitmapDTS1) bird = flyBitmapTDS[3];
                                    else if (birdBreath1 == bitmapDT1) bird = flyBitmapTD[3];
                                    else if (birdBreath1 == bitmapTH1) bird = flyBitmapTH[3];
                                    else if (birdBreath1 == bitmapTSH1) bird = flyBitmapTSH[3];
                                    else if (birdBreath1 == bitmapTS1) bird = flyBitmapTS[3];
                                    else if (birdBreath1 == bitmapT1) bird = flyBitmapT[3];
                                    else if (birdBreath1 == bitmapUsual1) bird = flyBitmapUsual[3];
                                    birdX = (float) 548 / 1050;
                                    birdY = (float) 176 / 540;
                                    break;
                                case 3:
                                    if (birdBreath1 == bitmapDTSH1) bird = flyBitmapTDSH[4];
                                    else if (birdBreath1 == bitmapDH1) bird = flyBitmapDH[4];
                                    else if (birdBreath1 == bitmapDS1) bird = flyBitmapDS[4];
                                    else if (birdBreath1 == bitmapD1) bird = flyBitmapD[4];
                                    else if (birdBreath1 == bitmapH1) bird = flyBitmapH[4];
                                    else if (birdBreath1 == bitmapSmile1) bird = flyBitmapSmile[4];
                                    else if (birdBreath1 == bitmapSH1) bird = flyBitmapSH[4];
                                    else if (birdBreath1 == bitmapDSH1) bird = flyBitmapSDH[4];
                                    else if (birdBreath1 == bitmapS1) bird = flyBitmapS[4];
                                    else if (birdBreath1 == bitmapDTH1) bird = flyBitmapTDH[4];
                                    else if (birdBreath1 == bitmapDTS1) bird = flyBitmapTDS[4];
                                    else if (birdBreath1 == bitmapDT1) bird = flyBitmapTD[4];
                                    else if (birdBreath1 == bitmapTH1) bird = flyBitmapTH[4];
                                    else if (birdBreath1 == bitmapTSH1) bird = flyBitmapTSH[4];
                                    else if (birdBreath1 == bitmapTS1) bird = flyBitmapTS[4];
                                    else if (birdBreath1 == bitmapT1) bird = flyBitmapT[4];
                                    else if (birdBreath1 == bitmapUsual1) bird = flyBitmapUsual[4];
                                    birdX = (float) 625 / 1050;
                                    birdY = (float) 203 / 540;
                                    break;
                                case 4:
                                    if (birdBreath1 == bitmapDTSH1) bird = flyBitmapTDSH[3];
                                    else if (birdBreath1 == bitmapDH1) bird = flyBitmapDH[3];
                                    else if (birdBreath1 == bitmapDS1) bird = flyBitmapDS[3];
                                    else if (birdBreath1 == bitmapD1) bird = flyBitmapD[3];
                                    else if (birdBreath1 == bitmapH1) bird = flyBitmapH[3];
                                    else if (birdBreath1 == bitmapSmile1) bird = flyBitmapSmile[3];
                                    else if (birdBreath1 == bitmapSH1) bird = flyBitmapSH[3];
                                    else if (birdBreath1 == bitmapDSH1) bird = flyBitmapSDH[3];
                                    else if (birdBreath1 == bitmapS1) bird = flyBitmapS[3];
                                    else if (birdBreath1 == bitmapDTH1) bird = flyBitmapTDH[3];
                                    else if (birdBreath1 == bitmapDTS1) bird = flyBitmapTDS[3];
                                    else if (birdBreath1 == bitmapDT1) bird = flyBitmapTD[3];
                                    else if (birdBreath1 == bitmapTH1) bird = flyBitmapTH[3];
                                    else if (birdBreath1 == bitmapTSH1) bird = flyBitmapTSH[3];
                                    else if (birdBreath1 == bitmapTS1) bird = flyBitmapTS[3];
                                    else if (birdBreath1 == bitmapT1) bird = flyBitmapT[3];
                                    else if (birdBreath1 == bitmapUsual1) bird = flyBitmapUsual[3];
                                    birdX = (float) 715 / 1050;
                                    birdY = (float) 195 / 540;
                                    break;
                                case 5:
                                    if (birdBreath1 == bitmapDTSH1) bird = flyBitmapTDSH[4];
                                    else if (birdBreath1 == bitmapDH1) bird = flyBitmapDH[4];
                                    else if (birdBreath1 == bitmapDS1) bird = flyBitmapDS[4];
                                    else if (birdBreath1 == bitmapD1) bird = flyBitmapD[4];
                                    else if (birdBreath1 == bitmapH1) bird = flyBitmapH[4];
                                    else if (birdBreath1 == bitmapSmile1) bird = flyBitmapSmile[4];
                                    else if (birdBreath1 == bitmapSH1) bird = flyBitmapSH[4];
                                    else if (birdBreath1 == bitmapDSH1) bird = flyBitmapSDH[4];
                                    else if (birdBreath1 == bitmapS1) bird = flyBitmapS[4];
                                    else if (birdBreath1 == bitmapDTH1) bird = flyBitmapTDH[4];
                                    else if (birdBreath1 == bitmapDTS1) bird = flyBitmapTDS[4];
                                    else if (birdBreath1 == bitmapDT1) bird = flyBitmapTD[4];
                                    else if (birdBreath1 == bitmapTH1) bird = flyBitmapTH[4];
                                    else if (birdBreath1 == bitmapTSH1) bird = flyBitmapTSH[4];
                                    else if (birdBreath1 == bitmapTS1) bird = flyBitmapTS[4];
                                    else if (birdBreath1 == bitmapT1) bird = flyBitmapT[4];
                                    else if (birdBreath1 == bitmapUsual1) bird = flyBitmapUsual[4];
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
                            if (birdBreath1 == bitmapUsual1 || birdBreath1 == bitmapSmile1)
                                bird = poopingBitmapUsual[disgust];
                            else if (birdBreath1 == bitmapDSH1) bird = poopingBitmapDSH[disgust];
                            else if (birdBreath1 == bitmapDTS1) bird = poopingBitmapDTS[disgust];
                            else if (birdBreath1 == bitmapDTH1) bird = poopingBitmapDTH[disgust];
                            else if (birdBreath1 == bitmapDH1) bird = poopingBitmapDH[disgust];
                            else if (birdBreath1 == bitmapDT1) bird = poopingBitmapDT[disgust];
                            else if (birdBreath1 == bitmapDS1) bird = poopingBitmapDS[disgust];
                            else if (birdBreath1 == bitmapD1) bird = poopingBitmapD[disgust];
                            else if (birdBreath1 == bitmapTSH1) bird = poopingBitmapTHS[disgust];
                            else if (birdBreath1 == bitmapSH1) bird = poopingBitmapHS[disgust];
                            else if (birdBreath1 == bitmapTH1) bird = poopingBitmapTH[disgust];
                            else if (birdBreath1 == bitmapH1) bird = poopingBitmapH[disgust];
                            else if (birdBreath1 == bitmapTS1) bird = poopingBitmapTS[disgust];
                            else if (birdBreath1 == bitmapT1) bird = poopingBitmapT[disgust];
                            else if (birdBreath1 == bitmapS1) bird = poopingBitmapS[disgust];
                        }
                        if (disgusting && lastTouchX >= poopX * canvas.getWidth() && lastTouchX <= canvas.getWidth() * (poopX + poopWidth) && lastTouchY >= canvas.getHeight() * poopY && lastTouchY <= (poopY + poopHeight) * canvas.getHeight()) {
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
                                    // Проверяем состояние и подбираем картинку
                                    if (birdBreath1 == bitmapDTSH1) bird = flyBitmapTDSH[2];
                                    else if (birdBreath1 == bitmapDH1) bird = flyBitmapDH[2];
                                    else if (birdBreath1 == bitmapDS1) bird = flyBitmapDS[2];
                                    else if (birdBreath1 == bitmapD1) bird = flyBitmapD[2];
                                    else if (birdBreath1 == bitmapH1) bird = flyBitmapH[2];
                                    else if (birdBreath1 == bitmapSmile1) bird = flyBitmapSmile[2];
                                    else if (birdBreath1 == bitmapSH1) bird = flyBitmapSH[2];
                                    else if (birdBreath1 == bitmapDSH1) bird = flyBitmapSDH[2];
                                    else if (birdBreath1 == bitmapS1) bird = flyBitmapS[2];
                                    else if (birdBreath1 == bitmapDTH1) bird = flyBitmapTDH[2];
                                    else if (birdBreath1 == bitmapDTS1) bird = flyBitmapTDS[2];
                                    else if (birdBreath1 == bitmapDT1) bird = flyBitmapTD[2];
                                    else if (birdBreath1 == bitmapTH1) bird = flyBitmapTH[2];
                                    else if (birdBreath1 == bitmapTSH1) bird = flyBitmapTSH[2];
                                    else if (birdBreath1 == bitmapTS1) bird = flyBitmapTS[2];
                                    else if (birdBreath1 == bitmapT1) bird = flyBitmapT[2];
                                    else if (birdBreath1 == bitmapUsual1) bird = flyBitmapUsual[2];
                                    break;
                                case 2:
                                    if (birdBreath1 == bitmapDTSH1) bird = flyBitmapTDSH[1];
                                    else if (birdBreath1 == bitmapDH1) bird = flyBitmapDH[1];
                                    else if (birdBreath1 == bitmapDS1) bird = flyBitmapDS[1];
                                    else if (birdBreath1 == bitmapD1) bird = flyBitmapD[1];
                                    else if (birdBreath1 == bitmapH1) bird = flyBitmapH[1];
                                    else if (birdBreath1 == bitmapSmile1) bird = flyBitmapSmile[1];
                                    else if (birdBreath1 == bitmapSH1) bird = flyBitmapSH[1];
                                    else if (birdBreath1 == bitmapDSH1) bird = flyBitmapSDH[1];
                                    else if (birdBreath1 == bitmapS1) bird = flyBitmapS[1];
                                    else if (birdBreath1 == bitmapDTH1) bird = flyBitmapTDH[1];
                                    else if (birdBreath1 == bitmapDTS1) bird = flyBitmapTDS[1];
                                    else if (birdBreath1 == bitmapDT1) bird = flyBitmapTD[1];
                                    else if (birdBreath1 == bitmapTH1) bird = flyBitmapTH[1];
                                    else if (birdBreath1 == bitmapTSH1) bird = flyBitmapTSH[1];
                                    else if (birdBreath1 == bitmapTS1) bird = flyBitmapTS[1];
                                    else if (birdBreath1 == bitmapT1) bird = flyBitmapT[1];
                                    else if (birdBreath1 == bitmapUsual1) bird = flyBitmapUsual[1];
                                    birdX = (float) 715 / 1050;
                                    birdY = (float) 195 / 540;
                                    break;
                                case 3:
                                    if (birdBreath1 == bitmapDTSH1) bird = flyBitmapTDSH[2];
                                    else if (birdBreath1 == bitmapDH1) bird = flyBitmapDH[2];
                                    else if (birdBreath1 == bitmapDS1) bird = flyBitmapDS[2];
                                    else if (birdBreath1 == bitmapD1) bird = flyBitmapD[2];
                                    else if (birdBreath1 == bitmapH1) bird = flyBitmapH[2];
                                    else if (birdBreath1 == bitmapSmile1) bird = flyBitmapSmile[2];
                                    else if (birdBreath1 == bitmapSH1) bird = flyBitmapSH[2];
                                    else if (birdBreath1 == bitmapDSH1) bird = flyBitmapSDH[2];
                                    else if (birdBreath1 == bitmapS1) bird = flyBitmapS[2];
                                    else if (birdBreath1 == bitmapDTH1) bird = flyBitmapTDH[2];
                                    else if (birdBreath1 == bitmapDTS1) bird = flyBitmapTDS[2];
                                    else if (birdBreath1 == bitmapDT1) bird = flyBitmapTD[2];
                                    else if (birdBreath1 == bitmapTH1) bird = flyBitmapTH[2];
                                    else if (birdBreath1 == bitmapTSH1) bird = flyBitmapTSH[2];
                                    else if (birdBreath1 == bitmapTS1) bird = flyBitmapTS[2];
                                    else if (birdBreath1 == bitmapT1) bird = flyBitmapT[2];
                                    else if (birdBreath1 == bitmapUsual1) bird = flyBitmapUsual[2];
                                    birdX = (float) 625 / 1050;
                                    birdY = (float) 203 / 540;
                                    break;
                                case 4:
                                    if (birdBreath1 == bitmapDTSH1) bird = flyBitmapTDSH[1];
                                    else if (birdBreath1 == bitmapDH1) bird = flyBitmapDH[1];
                                    else if (birdBreath1 == bitmapDS1) bird = flyBitmapDS[1];
                                    else if (birdBreath1 == bitmapD1) bird = flyBitmapD[1];
                                    else if (birdBreath1 == bitmapH1) bird = flyBitmapH[1];
                                    else if (birdBreath1 == bitmapSmile1) bird = flyBitmapSmile[1];
                                    else if (birdBreath1 == bitmapSH1) bird = flyBitmapSH[1];
                                    else if (birdBreath1 == bitmapDSH1) bird = flyBitmapSDH[1];
                                    else if (birdBreath1 == bitmapS1) bird = flyBitmapS[1];
                                    else if (birdBreath1 == bitmapDTH1) bird = flyBitmapTDH[1];
                                    else if (birdBreath1 == bitmapDTS1) bird = flyBitmapTDS[1];
                                    else if (birdBreath1 == bitmapDT1) bird = flyBitmapTD[1];
                                    else if (birdBreath1 == bitmapTH1) bird = flyBitmapTH[1];
                                    else if (birdBreath1 == bitmapTSH1) bird = flyBitmapTSH[1];
                                    else if (birdBreath1 == bitmapTS1) bird = flyBitmapTS[1];
                                    else if (birdBreath1 == bitmapT1) bird = flyBitmapT[1];
                                    else if (birdBreath1 == bitmapUsual1) bird = flyBitmapUsual[1];
                                    birdX = (float) 548 / 1050;
                                    birdY = (float) 176 / 540;
                                    break;
                                case 5:
                                    if (birdBreath1 == bitmapDTSH1) bird = flyBitmapTDSH[2];
                                    else if (birdBreath1 == bitmapDH1) bird = flyBitmapDH[2];
                                    else if (birdBreath1 == bitmapDS1) bird = flyBitmapDS[2];
                                    else if (birdBreath1 == bitmapD1) bird = flyBitmapD[2];
                                    else if (birdBreath1 == bitmapH1) bird = flyBitmapH[2];
                                    else if (birdBreath1 == bitmapSmile1) bird = flyBitmapSmile[2];
                                    else if (birdBreath1 == bitmapSH1) bird = flyBitmapSH[2];
                                    else if (birdBreath1 == bitmapDSH1) bird = flyBitmapSDH[2];
                                    else if (birdBreath1 == bitmapS1) bird = flyBitmapS[2];
                                    else if (birdBreath1 == bitmapDTH1) bird = flyBitmapTDH[2];
                                    else if (birdBreath1 == bitmapDTS1) bird = flyBitmapTDS[2];
                                    else if (birdBreath1 == bitmapDT1) bird = flyBitmapTD[2];
                                    else if (birdBreath1 == bitmapTH1) bird = flyBitmapTH[2];
                                    else if (birdBreath1 == bitmapTSH1) bird = flyBitmapTSH[2];
                                    else if (birdBreath1 == bitmapTS1) bird = flyBitmapTS[2];
                                    else if (birdBreath1 == bitmapT1) bird = flyBitmapT[2];
                                    else if (birdBreath1 == bitmapUsual1) bird = flyBitmapUsual[2];
                                    birdX = (float) 419 / 1050;
                                    birdY = (float) 232 / 540;
                                    break;
                                case 6:
                                    // Возвращаемся к преждним настройкам
                                    bird = birdBreath1;
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
                        // Кушать
                        if (lastTouchX >= eatButtonLeft * canvas.getWidth() && lastTouchX <= (eatButtonLeft + ButtonWidth) * canvas.getWidth() && lastTouchY >= eatButtonTop * canvas.getHeight() && lastTouchY <= (eatButtonTop + ButtonHeight) * canvas.getHeight() && eatChecker && !playing && !sleeping && !laying && !flying && !flyingBack && !hitting && !washing && !pooping && !flyPoop && !disgusting && !flyBackPoop && !hungryButtonIsPressed) {
                            hungryButtonIsPressed = true;
                            gettingFoin = true;
                        }
                        if (!eatingTimeIsPassed && hungryButtonIsPressed) {
                            new EatingThread().start();
                            eating = true;
                            eatingTimeIsPassed = true;
                        }
                        if (eatingNeedToDrawNow && eating) {
                            if (eat >= 1 && eat <= 10) {
                                birdY = (float) 118 / 540;
                                // Проверяем состояние и подбираем картинку
                                if (birdBreath1 == bitmapDTS1 || birdBreath1 == bitmapDTSH1)
                                    bird = eatBitmapDTS[eat];
                                else if (birdBreath1 == bitmapDT1 || birdBreath1 == bitmapDTH1)
                                    bird = eatBitmapDT[eat];
                                else if (birdBreath1 == bitmapDS1 || birdBreath1 == bitmapDSH1)
                                    bird = eatBitmapDS[eat];
                                else if (birdBreath1 == bitmapTS1 || birdBreath1 == bitmapTSH1)
                                    bird = eatBitmapTS[eat];
                                else if (birdBreath1 == bitmapD1 || birdBreath1 == bitmapDH1)
                                    bird = eatBitmapD[eat];
                                else if (birdBreath1 == bitmapS1 || birdBreath1 == bitmapSH1)
                                    bird = eatBitmapS[eat];
                                else if (birdBreath1 == bitmapT1 || birdBreath1 == bitmapTH1)
                                    bird = eatBitmapT[eat];
                                else if (birdBreath1 == bitmapUsual1 || birdBreath1 == bitmapH1)
                                    bird = eatBitmap[eat];
                                else if (birdBreath1 == bitmapSmile1) bird = eatBitmapSmile[eat];
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
                            if (eat == 11) {
                                // Возвращаемся к преждним настройкам

                                eating = false;
                                eat = 0;
                                lastTouchY = 0;
                                lastTouchX = 0;
                                eatChecker = false;
                                checkEatButton = true;
                                e = 0;
                                hungryButtonIsPressed = false;
                                bird = birdBreath1;
                                birdY = (float) 232 / 540;
                                eatButtonBitmap = eatDarkButtonBitmap[10];
                                eatTimer = 10;
                                eatScore++;
                            }
                        }
                        // Радоваться
                        if (lastTouchX >= playButtonLeft * canvas.getWidth() && lastTouchX <= (playButtonLeft + ButtonWidth) * canvas.getWidth() && lastTouchY >= playButtonTop * canvas.getHeight() && lastTouchY <= (playButtonTop + ButtonHeight) * canvas.getHeight() && playChecker && !eating && !sleeping && !laying && !flying && !flyingBack && !hitting && !washing && !pooping && !flyPoop && !disgusting && !flyBackPoop && !happyButtonIsTouched) {
                            happyButtonIsTouched = true;
                            gettingFoin = true;
                            isSinging = true;
                        }
                        if (isSinging && happyButtonIsTouched) {
                            mediaPlayerHappy.start();
                            isSinging = false;
                        }
                        if (!playingTimeIsPassed && happyButtonIsTouched) {
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
                            if (birdBreath1 == bitmapDT1 || birdBreath1 == bitmapDTH1 || birdBreath1 == bitmapDTS1 || birdBreath1 == bitmapDTSH1)
                                bird = playBitmapDT[play];
                            else if (birdBreath1 == bitmapD1 || birdBreath1 == bitmapDH1 || birdBreath1 == bitmapDS1 || birdBreath1 == bitmapDSH1)
                                bird = playBitmapD[play];
                            else if (birdBreath1 == bitmapT1 || birdBreath1 == bitmapTH1 || birdBreath1 == bitmapTS1 || birdBreath1 == bitmapTSH1)
                                bird = playBitmapT[play];
                            else if (birdBreath1 == bitmapUsual1 || birdBreath1 == bitmapH1 || birdBreath1 == bitmapS1 || birdBreath1 == bitmapSmile1)
                                bird = playBitmap[play];
                            if (play == 20) {
                                // Возвращаемся к преждним настройкам
                                playing = false;
                                playChecker = false;
                                checkPlayButton = true;
                                play = 0;
                                lastTouchY = 0;
                                lastTouchX = 0;
                                p = 0;
                                happyButtonIsTouched = false;
                                playButtonBitmap = playDarkButtonBitmap[15];
                                playTimer = 15;
                                mediaPlayerHappy.stop();
                                mediaPlayerHappy.prepare();
                            }
                        }
                        // Спать
                        if (lastTouchX >= sleepButtonLeft * canvas.getWidth() && lastTouchX <= (sleepButtonLeft + ButtonWidth) * canvas.getWidth() && lastTouchY >= sleepButtonTop * canvas.getHeight() && lastTouchY <= (sleepButtonTop + ButtonHeight) * canvas.getHeight() && !sleepFinished && sleepChecker && !playing && !eating && !hitting && !washing && !pooping && !flyPoop && !disgusting && !flyBackPoop && !sleepButtonIsPressed) {
                            sleepButtonIsPressed = true;
                            gettingFoin = true;
                        }
                        if (!flyingToBedTimeIsPassed && sleepButtonIsPressed && !sleepFinished) {
                            new FlyingThread().start();
                            flying = true;
                            flyingToBedTimeIsPassed = true;
                        }
                        if (flyingToBedNeedToDrawNow && flying && !sleepFinished && !sleeping) {
                            switch (sleep) {
                                case 1:
                                    if (birdBreath1 == bitmapDTSH1) bird = flyBitmapTDSH[2];
                                    else if (birdBreath1 == bitmapDH1) bird = flyBitmapDH[2];
                                    else if (birdBreath1 == bitmapDS1) bird = flyBitmapDS[2];
                                    else if (birdBreath1 == bitmapD1) bird = flyBitmapD[2];
                                    else if (birdBreath1 == bitmapH1) bird = flyBitmapH[2];
                                    else if (birdBreath1 == bitmapSmile1) bird = flyBitmapSmile[2];
                                    else if (birdBreath1 == bitmapSH1) bird = flyBitmapSH[2];
                                    else if (birdBreath1 == bitmapDSH1) bird = flyBitmapSDH[2];
                                    else if (birdBreath1 == bitmapS1) bird = flyBitmapS[2];
                                    else if (birdBreath1 == bitmapDTH1) bird = flyBitmapTDH[2];
                                    else if (birdBreath1 == bitmapDTS1) bird = flyBitmapTDS[2];
                                    else if (birdBreath1 == bitmapDT1) bird = flyBitmapTD[2];
                                    else if (birdBreath1 == bitmapTH1) bird = flyBitmapTH[2];
                                    else if (birdBreath1 == bitmapTSH1) bird = flyBitmapTSH[2];
                                    else if (birdBreath1 == bitmapTS1) bird = flyBitmapTS[2];
                                    else if (birdBreath1 == bitmapT1) bird = flyBitmapT[2];
                                    else if (birdBreath1 == bitmapUsual1) bird = flyBitmapUsual[2];
                                    break;
                                case 2:
                                    if (birdBreath1 == bitmapDTSH1) bird = flyBitmapTDSH[1];
                                    else if (birdBreath1 == bitmapDH1) bird = flyBitmapDH[1];
                                    else if (birdBreath1 == bitmapDS1) bird = flyBitmapDS[1];
                                    else if (birdBreath1 == bitmapD1) bird = flyBitmapD[1];
                                    else if (birdBreath1 == bitmapH1) bird = flyBitmapH[1];
                                    else if (birdBreath1 == bitmapSmile1) bird = flyBitmapSmile[1];
                                    else if (birdBreath1 == bitmapSH1) bird = flyBitmapSH[1];
                                    else if (birdBreath1 == bitmapDSH1) bird = flyBitmapSDH[1];
                                    else if (birdBreath1 == bitmapS1) bird = flyBitmapS[1];
                                    else if (birdBreath1 == bitmapDTH1) bird = flyBitmapTDH[1];
                                    else if (birdBreath1 == bitmapDTS1) bird = flyBitmapTDS[1];
                                    else if (birdBreath1 == bitmapDT1) bird = flyBitmapTD[1];
                                    else if (birdBreath1 == bitmapTH1) bird = flyBitmapTH[1];
                                    else if (birdBreath1 == bitmapTSH1) bird = flyBitmapTSH[1];
                                    else if (birdBreath1 == bitmapTS1) bird = flyBitmapTS[1];
                                    else if (birdBreath1 == bitmapT1) bird = flyBitmapT[1];
                                    else if (birdBreath1 == bitmapUsual1) bird = flyBitmapUsual[1];
                                    birdX = (float) 353 / 1050;
                                    birdY = (float) 238 / 540;
                                    break;
                                case 3:
                                    if (birdBreath1 == bitmapDTSH1) bird = flyBitmapTDSH[2];
                                    else if (birdBreath1 == bitmapDH1) bird = flyBitmapDH[2];
                                    else if (birdBreath1 == bitmapDS1) bird = flyBitmapDS[2];
                                    else if (birdBreath1 == bitmapD1) bird = flyBitmapD[2];
                                    else if (birdBreath1 == bitmapH1) bird = flyBitmapH[2];
                                    else if (birdBreath1 == bitmapSmile1) bird = flyBitmapSmile[2];
                                    else if (birdBreath1 == bitmapSH1) bird = flyBitmapSH[2];
                                    else if (birdBreath1 == bitmapDSH1) bird = flyBitmapSDH[2];
                                    else if (birdBreath1 == bitmapS1) bird = flyBitmapS[2];
                                    else if (birdBreath1 == bitmapDTH1) bird = flyBitmapTDH[2];
                                    else if (birdBreath1 == bitmapDTS1) bird = flyBitmapTDS[2];
                                    else if (birdBreath1 == bitmapDT1) bird = flyBitmapTD[2];
                                    else if (birdBreath1 == bitmapTH1) bird = flyBitmapTH[2];
                                    else if (birdBreath1 == bitmapTSH1) bird = flyBitmapTSH[2];
                                    else if (birdBreath1 == bitmapTS1) bird = flyBitmapTS[2];
                                    else if (birdBreath1 == bitmapT1) bird = flyBitmapT[2];
                                    else if (birdBreath1 == bitmapUsual1) bird = flyBitmapUsual[2];
                                    birdX = (float) 287 / 1050;
                                    birdY = (float) 226 / 540;
                                    break;
                                case 4:
                                    if (birdBreath1 == bitmapDTSH1) bird = flyBitmapTDSH[1];
                                    else if (birdBreath1 == bitmapDH1) bird = flyBitmapDH[1];
                                    else if (birdBreath1 == bitmapDS1) bird = flyBitmapDS[1];
                                    else if (birdBreath1 == bitmapD1) bird = flyBitmapD[1];
                                    else if (birdBreath1 == bitmapH1) bird = flyBitmapH[1];
                                    else if (birdBreath1 == bitmapSmile1) bird = flyBitmapSmile[1];
                                    else if (birdBreath1 == bitmapSH1) bird = flyBitmapSH[1];
                                    else if (birdBreath1 == bitmapDSH1) bird = flyBitmapSDH[1];
                                    else if (birdBreath1 == bitmapS1) bird = flyBitmapS[1];
                                    else if (birdBreath1 == bitmapDTH1) bird = flyBitmapTDH[1];
                                    else if (birdBreath1 == bitmapDTS1) bird = flyBitmapTDS[1];
                                    else if (birdBreath1 == bitmapDT1) bird = flyBitmapTD[1];
                                    else if (birdBreath1 == bitmapTH1) bird = flyBitmapTH[1];
                                    else if (birdBreath1 == bitmapTSH1) bird = flyBitmapTSH[1];
                                    else if (birdBreath1 == bitmapTS1) bird = flyBitmapTS[1];
                                    else if (birdBreath1 == bitmapT1) bird = flyBitmapT[1];
                                    else if (birdBreath1 == bitmapUsual1) bird = flyBitmapUsual[1];
                                    birdX = (float) 230 / 1050;
                                    birdY = (float) 232 / 540;
                                    break;
                                case 5:
                                    if (birdBreath1 == bitmapDTSH1) bird = flyBitmapTDSH[2];
                                    else if (birdBreath1 == bitmapDH1) bird = flyBitmapDH[2];
                                    else if (birdBreath1 == bitmapDS1) bird = flyBitmapDS[2];
                                    else if (birdBreath1 == bitmapD1) bird = flyBitmapD[2];
                                    else if (birdBreath1 == bitmapH1) bird = flyBitmapH[2];
                                    else if (birdBreath1 == bitmapSmile1) bird = flyBitmapSmile[2];
                                    else if (birdBreath1 == bitmapSH1) bird = flyBitmapSH[2];
                                    else if (birdBreath1 == bitmapDSH1) bird = flyBitmapSDH[2];
                                    else if (birdBreath1 == bitmapS1) bird = flyBitmapS[2];
                                    else if (birdBreath1 == bitmapDTH1) bird = flyBitmapTDH[2];
                                    else if (birdBreath1 == bitmapDTS1) bird = flyBitmapTDS[2];
                                    else if (birdBreath1 == bitmapDT1) bird = flyBitmapTD[2];
                                    else if (birdBreath1 == bitmapTH1) bird = flyBitmapTH[2];
                                    else if (birdBreath1 == bitmapTSH1) bird = flyBitmapTSH[2];
                                    else if (birdBreath1 == bitmapTS1) bird = flyBitmapTS[2];
                                    else if (birdBreath1 == bitmapT1) bird = flyBitmapT[2];
                                    else if (birdBreath1 == bitmapUsual1) bird = flyBitmapUsual[2];
                                    birdX = (float) 159 / 1050;
                                    birdY = (float) 193 / 540;
                                    break;
                                case 6:
                                    bird = birdBreath1;
                                    birdX = (float) 48 / 1050;
                                    birdY = (float) 218 / 540;
                                    break;
                                case 7:
                                    birdX = (float) 60 / 1050;
                                    birdY = (float) 207 / 540;
                                    laying = true;
                                    flying = false;
                                    if (birdBreath1 == bitmapDTSH1 || birdBreath1 == bitmapDSH1)
                                        bird = sleepDSH1;
                                    else if (birdBreath1 == bitmapDTS1 || birdBreath1 == bitmapDS1)
                                        bird = sleepDS1;
                                    else if (birdBreath1 == bitmapDTH1 || birdBreath1 == bitmapDH1)
                                        bird = sleepDH1;
                                    else if (birdBreath1 == bitmapDT1 || birdBreath1 == bitmapD1)
                                        bird = sleepD1;
                                    else if (birdBreath1 == bitmapTSH1 || birdBreath1 == bitmapSH1)
                                        bird = sleepSH1;
                                    else if (birdBreath1 == bitmapTS1 || birdBreath1 == bitmapS1)
                                        bird = sleepS1;
                                    else if (birdBreath1 == bitmapTH1 || birdBreath1 == bitmapH1)
                                        bird = sleepH1;
                                    else if (birdBreath1 == bitmapT1 || birdBreath1 == bitmapUsual1)
                                        bird = sleepUsual1;
                                    else if (birdBreath1 == bitmapSmile1) bird = sleepSmile1;
                                    sleepButtonBitmap = sleepDarkButtonBitmap[60];
                                    checkSleepButton = true;
                                    sleepChecker = false;
                                    sleeping = true;
                                    sleepTimer = 60;
                                    break;
                            }
                        }
                        // Таймер дыхания во время сна
                        if (laying && !sleepFinished) {
                            if (!timeIsPassedSleep1 && !checkSleep) {
                                new ThreadSleepBird1().start();
                                timeIsPassedSleep1 = true;
                            }
                            if (needToDrawNowSleep1 && !checkSleep) {
                                if (birdBreath1 == bitmapDTSH1 || birdBreath1 == bitmapDSH1) bird = sleepDSH1;else if (birdBreath1 == bitmapDTS1 || birdBreath1 == bitmapDS1) bird = sleepDS1;else if (birdBreath1 == bitmapDTH1 || birdBreath1 == bitmapDH1) bird = sleepDH1;else if (birdBreath1 == bitmapDT1 || birdBreath1 == bitmapD1) bird = sleepD1;else if (birdBreath1 == bitmapTSH1 || birdBreath1 == bitmapSH1) bird = sleepSH1;else if (birdBreath1 == bitmapTS1 || birdBreath1 == bitmapS1) bird = sleepS1;else if (birdBreath1 == bitmapTH1 || birdBreath1 == bitmapH1) bird = sleepH1;else if (birdBreath1 == bitmapT1 || birdBreath1 == bitmapUsual1) bird = sleepUsual1;else if (birdBreath1 == bitmapSmile1) bird = sleepSmile1;
                            }
                            if (!timeIsPassedSleep2 && checkSleep) {
                                new ThreadSleepBird2().start();
                                timeIsPassedSleep2 = true;
                            }
                            if (needToDrawNowSleep2 && checkSleep) {
                                if (birdBreath1 == bitmapDTSH1 || birdBreath1 == bitmapDSH1) bird = sleepDSH2;else if (birdBreath1 == bitmapDTS1 || birdBreath1 == bitmapDS1) bird = sleepDS2;else if (birdBreath1 == bitmapDTH1 || birdBreath1 == bitmapDH1) bird = sleepDH2;else if (birdBreath1 == bitmapDT1 || birdBreath1 == bitmapD1) bird = sleepD2;else if (birdBreath1 == bitmapTSH1 || birdBreath1 == bitmapSH1) bird = sleepSH2;else if (birdBreath1 == bitmapTS1 || birdBreath1 == bitmapS1) bird = sleepS2;else if (birdBreath1 == bitmapTH1 || birdBreath1 == bitmapH1) bird = sleepH2;else if (birdBreath1 == bitmapT1 || birdBreath1 == bitmapUsual1) bird = sleepUsual2;else if (birdBreath1 == bitmapSmile1) bird = sleepSmile2;
                            }
                            if (!sleepingTimeIsPassed) {
                                new Sleep().start();
                                sleepingTimeIsPassed = true;
                            }
                            if (sleepingNeedToDrawNow && r1 == 1) {
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
                                        bird = birdBreath1;
                                        birdX = (float) 48 / 1050;
                                        birdY = (float) 218 / 540;
                                        break;
                                    case 2:
                                        if (birdBreath1 == bitmapDTSH1) bird = flyBitmapTDSH[4];else if (birdBreath1 == bitmapDH1) bird = flyBitmapDH[4];else if (birdBreath1 == bitmapDS1) bird = flyBitmapDS[4];else if (birdBreath1 == bitmapD1) bird = flyBitmapD[4];else if (birdBreath1 == bitmapH1) bird = flyBitmapH[4];else if (birdBreath1 == bitmapSmile1) bird = flyBitmapSmile[4];else if (birdBreath1 == bitmapSH1) bird = flyBitmapSH[4];else if (birdBreath1 == bitmapDSH1) bird = flyBitmapSDH[4];else if (birdBreath1 == bitmapS1) bird = flyBitmapS[4];else if (birdBreath1 == bitmapDTH1) bird = flyBitmapTDH[4];else if (birdBreath1 == bitmapDTS1) bird = flyBitmapTDS[4];else if (birdBreath1 == bitmapDT1) bird = flyBitmapTD[4];else if (birdBreath1 == bitmapTH1) bird = flyBitmapTH[4];else if (birdBreath1 == bitmapTSH1) bird = flyBitmapTSH[4];else if (birdBreath1 == bitmapTS1) bird = flyBitmapTS[4];else if (birdBreath1 == bitmapT1) bird = flyBitmapT[4];else if (birdBreath1 == bitmapUsual1) bird = flyBitmapUsual[4];
                                        birdX = (float) 159 / 1050;
                                        birdY = (float) 193 / 540;
                                        break;
                                    case 3:
                                        if (birdBreath1 == bitmapDTSH1) bird = flyBitmapTDSH[3];else if (birdBreath1 == bitmapDH1) bird = flyBitmapDH[3];else if (birdBreath1 == bitmapDS1) bird = flyBitmapDS[3];else if (birdBreath1 == bitmapD1) bird = flyBitmapD[3];else if (birdBreath1 == bitmapH1) bird = flyBitmapH[3];else if (birdBreath1 == bitmapSmile1) bird = flyBitmapSmile[3];else if (birdBreath1 == bitmapSH1) bird = flyBitmapSH[3];else if (birdBreath1 == bitmapDSH1) bird = flyBitmapSDH[3];else if (birdBreath1 == bitmapS1) bird = flyBitmapS[3];else if (birdBreath1 == bitmapDTH1) bird = flyBitmapTDH[3];else if (birdBreath1 == bitmapDTS1) bird = flyBitmapTDS[3];else if (birdBreath1 == bitmapDT1) bird = flyBitmapTD[3];else if (birdBreath1 == bitmapTH1) bird = flyBitmapTH[3];else if (birdBreath1 == bitmapTSH1) bird = flyBitmapTSH[3];else if (birdBreath1 == bitmapTS1) bird = flyBitmapTS[3];else if (birdBreath1 == bitmapT1) bird = flyBitmapT[3];else if (birdBreath1 == bitmapUsual1) bird = flyBitmapUsual[3];
                                        birdX = (float) 230 / 1050;
                                        birdY = (float) 232 / 540;
                                        break;
                                    case 4:
                                        if (birdBreath1 == bitmapDTSH1) bird = flyBitmapTDSH[4];else if (birdBreath1 == bitmapDH1) bird = flyBitmapDH[4];else if (birdBreath1 == bitmapDS1) bird = flyBitmapDS[4];else if (birdBreath1 == bitmapD1) bird = flyBitmapD[4];else if (birdBreath1 == bitmapH1) bird = flyBitmapH[4];else if (birdBreath1 == bitmapSmile1) bird = flyBitmapSmile[4];else if (birdBreath1 == bitmapSH1) bird = flyBitmapSH[4];else if (birdBreath1 == bitmapDSH1) bird = flyBitmapSDH[4];else if (birdBreath1 == bitmapS1) bird = flyBitmapS[4];else if (birdBreath1 == bitmapDTH1) bird = flyBitmapTDH[4];else if (birdBreath1 == bitmapDTS1) bird = flyBitmapTDS[4];else if (birdBreath1 == bitmapDT1) bird = flyBitmapTD[4];else if (birdBreath1 == bitmapTH1) bird = flyBitmapTH[4];else if (birdBreath1 == bitmapTSH1) bird = flyBitmapTSH[4];else if (birdBreath1 == bitmapTS1) bird = flyBitmapTS[4];else if (birdBreath1 == bitmapT1) bird = flyBitmapT[4];else if (birdBreath1 == bitmapUsual1) bird = flyBitmapUsual[4];
                                        birdX = (float) 287 / 1050;
                                        birdY = (float) 226 / 540;
                                        break;
                                    case 5:
                                        if (birdBreath1 == bitmapDTSH1) bird = flyBitmapTDSH[3];else if (birdBreath1 == bitmapDH1) bird = flyBitmapDH[3];else if (birdBreath1 == bitmapDS1) bird = flyBitmapDS[3];else if (birdBreath1 == bitmapD1) bird = flyBitmapD[3];else if (birdBreath1 == bitmapH1) bird = flyBitmapH[3];else if (birdBreath1 == bitmapSmile1) bird = flyBitmapSmile[3];else if (birdBreath1 == bitmapSH1) bird = flyBitmapSH[3];else if (birdBreath1 == bitmapDSH1) bird = flyBitmapSDH[3];else if (birdBreath1 == bitmapS1) bird = flyBitmapS[3];else if (birdBreath1 == bitmapDTH1) bird = flyBitmapTDH[3];else if (birdBreath1 == bitmapDTS1) bird = flyBitmapTDS[3];else if (birdBreath1 == bitmapDT1) bird = flyBitmapTD[3];else if (birdBreath1 == bitmapTH1) bird = flyBitmapTH[3];else if (birdBreath1 == bitmapTSH1) bird = flyBitmapTSH[3];else if (birdBreath1 == bitmapTS1) bird = flyBitmapTS[3];else if (birdBreath1 == bitmapT1) bird = flyBitmapT[3];else if (birdBreath1 == bitmapUsual1) bird = flyBitmapUsual[3];
                                        birdX = (float) 353 / 1050;
                                        birdY = (float) 238 / 540;
                                        break;
                                    case 6:
                                        if (birdBreath1 == bitmapDTSH1) bird = flyBitmapTDSH[4];else if (birdBreath1 == bitmapDH1) bird = flyBitmapDH[4];else if (birdBreath1 == bitmapDS1) bird = flyBitmapDS[4];else if (birdBreath1 == bitmapD1) bird = flyBitmapD[4];else if (birdBreath1 == bitmapH1) bird = flyBitmapH[4];else if (birdBreath1 == bitmapSmile1) bird = flyBitmapSmile[4];else if (birdBreath1 == bitmapSH1) bird = flyBitmapSH[4];else if (birdBreath1 == bitmapDSH1) bird = flyBitmapSDH[4];else if (birdBreath1 == bitmapS1) bird = flyBitmapS[4];else if (birdBreath1 == bitmapDTH1) bird = flyBitmapTDH[4];else if (birdBreath1 == bitmapDTS1) bird = flyBitmapTDS[4];else if (birdBreath1 == bitmapDT1) bird = flyBitmapTD[4];else if (birdBreath1 == bitmapTH1) bird = flyBitmapTH[4];else if (birdBreath1 == bitmapTSH1) bird = flyBitmapTSH[4];else if (birdBreath1 == bitmapTS1) bird = flyBitmapTS[4];else if (birdBreath1 == bitmapT1) bird = flyBitmapT[4];else if (birdBreath1 == bitmapUsual1) bird = flyBitmapUsual[4];
                                        birdX = (float) 419 / 1050;
                                        birdY = (float) 232 / 540;
                                        break;
                                    case 7:
                                        // Возвращаемся к преждним настройкам
                                        bird = birdBreath1;
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
                                        sleepButtonIsPressed = false;
                                        flying = false;
                                        flyingToBedTimeIsPassed = false;
                                        break;
                                }
                            }
                        }
                        // Искупаться
                        if (lastTouchX >= dirtButtonLeft * canvas.getWidth() && lastTouchX <= (dirtButtonLeft + ButtonWidth) * canvas.getWidth() && lastTouchY >= dirtButtonTop * canvas.getHeight() && lastTouchY <= (dirtButtonTop + ButtonHeight) * canvas.getHeight() && (dirtRight - dirtLeft <= dirtWeight / 2.) && !playing && !eating && !sleeping && !laying && !flying && !flyingBack && !hitting && !pooping && !flyPoop && !disgusting && !flyBackPoop && !washButtonIsPressed) {
                            washButtonIsPressed = true;
                            gettingFoin = true;
                            isSinging = true;
                        }
                        if(isSinging && washButtonIsPressed) {
                            mediaPlayerWash.start();
                            isSinging = false;
                        }
                        if (!washingTimeIsPassed && washButtonIsPressed) {
                            new WashingThread().start();
                            washing = true;
                            washingTimeIsPassed = true;
                        }
                        if (washingNeedToDrawNow && washing && wash <= 48) {
                            birdX = (float) 414 / 1050;
                            birdY = (float) 31 / 540;
                            bird = washBitmap[wash];
                            if (wash == 31) dirtRight = dirtRight2;
                            editor.putFloat("DIRT", dirtRight);
                            editor.apply();
                            if (wash == 48) {
                                washing = false;
                                wash = 1;
                                washingTimeIsPassed = false;
                                lastTouchY = 0;
                                lastTouchX = 0;
                                washButtonIsPressed = false;
                                bird = birdBreath1;
                                birdX = (float) 419 / 1050;
                                birdY = (float) 232 / 540;
                                mediaPlayerWash.stop();
                                mediaPlayerWash.prepare();
                            }
                        }

                        // Встряхнуться
                        if (lastTouchX >= (birdX * canvas.getWidth()) && lastTouchX <= (birdX + birdWidth) * (canvas.getWidth()) && lastTouchY >= (birdY * canvas.getHeight()) && lastTouchY <= (birdY + birdHeight) * (canvas.getHeight()) && !eating && !playing && !flying && !sleeping && !laying && !flyingBack && !washing && !pooping && !flyPoop && !disgusting && !flyBackPoop && !birdIsPunched) {
                            birdIsPunched = true;
                            gettingFoin = true;
                            isSinging = true;
                        }
                        if (isSinging && birdIsPunched) {
                            mediaPlayerHit.start();
                            isSinging = false;
                        }
                        if (m6 == 1 && lastTouchX >= (birdX * canvas.getWidth()) && lastTouchX <= (birdX + birdWidth) * (canvas.getWidth()) && lastTouchY >= (birdY * canvas.getHeight()) && lastTouchY <= (birdY + birdHeight) * (canvas.getHeight()) && !eating && !playing && !flying && !sleeping && !laying && !flyingBack && !washing && !pooping && !flyPoop && !disgusting && !flyBackPoop) {
                            sendScreen();
                        }
                        if (!hitTimeIsPassed && birdIsPunched) {
                            new HitThread().start();
                            hitting = true;
                            hitTimeIsPassed = true;
                        }
                        if (hitNeedToDrawNow && hitting) {
                            if (birdBreath1 == bitmapDH1 || birdBreath1 == bitmapDTH1 || birdBreath1 == bitmapDTSH1 || birdBreath1 == bitmapDSH1) bird = hitBitmapDH[hit];else if (birdBreath1 == bitmapD1 || birdBreath1 == bitmapDT1 || birdBreath1 == bitmapDTS1 || birdBreath1 == bitmapDS1) bird = hitBitmapD[hit];else if (birdBreath1 == bitmapH1 || birdBreath1 == bitmapTH1 || birdBreath1 == bitmapTSH1 || birdBreath1 == bitmapSH1) bird = hitBitmapH[hit];else if (birdBreath1 == bitmapUsual1 || birdBreath1 == bitmapS1 || birdBreath1 == bitmapT1 || birdBreath1 == bitmapTS1 || birdBreath1 == bitmapSmile1) bird = hitBitmap[hit];
                            if (hit >= 14) {
                                // Возвращаемся к преждним настройкам
                                hitting = false;
                                hit = 1;
                                birdIsPunched = false;
                                hitTimeIsPassed = false;
                                lastTouchY = 0;
                                lastTouchX = 0;
                                mediaPlayerHit.stop();
                                mediaPlayerHit.prepare();
                            }
                        }
                        // скриншот
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
                        //  Магазин
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
                        // Рисуем птичку
                        canvas.drawBitmap(bird, (float) canvas.getWidth() * birdX, (float) canvas.getHeight() * birdY, paint);
                        // paintDirt.setTextSize(50);
                        // canvas.drawText(timePassed + "", 500, 500, paintDirt);
                    }
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } finally {
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
        }
    }


    public int getFoinTime() {
        return foinTime;
    }
    public void setFoinTime(int foinTime) {
        this.foinTime = foinTime;
    }
    public boolean isGetFoinTimeIsPassed() {
        return getFoinTimeIsPassed;
    }

    public void setGetFoinTimeIsPassed(boolean getFoinTimeIsPassed) {
        this.getFoinTimeIsPassed = getFoinTimeIsPassed;
    }

    public boolean isGetFoinNeedToDrawNow() {
        return getFoinNeedToDrawNow;
    }

    public void setGetFoinNeedToDrawNow(boolean getFoinNeedToDrawNow) {
        this.getFoinNeedToDrawNow = getFoinNeedToDrawNow;
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
                sleepingTimeIsPassed = false;
                sleepingNeedToDrawNow = true;
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
                if(eat<11) {
                    sleep(200);
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
                    flyingToBedTimeIsPassed = false;
                    flyingToBedNeedToDrawNow = true;
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

