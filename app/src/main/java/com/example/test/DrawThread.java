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
    private float hungryLeft =(float) 877. / 1050,hungryTop = (float)  92 / 540,hungryRight2 = (float) 1040 / 1050, hungryWidth = hungryRight2 - hungryLeft,hungryBottom = (float)  116 / 540;
    private float levelLeft = (float) 844 / 1050, levelTop = (float)  8 / 540, levelRight2 = (float)  1040 / 1050, levelBottom = (float)  42 / 540, levelRight3;
    private float dirtLeft = (float) 877 / 1050, dirtTop = (float)  55 / 540, dirtRight2 = (float)  1040 / 1050, dirtWidth = dirtRight2 - dirtLeft, dirtBottom = (float)  79 / 540;
    private float sleepLeft = (float) 877 / 1050, sleepTop = (float)  129 / 540, sleepRight2 = (float) 1040 / 1050, sleepWidth = sleepRight2 - sleepLeft, sleepBottom = (float)  153 / 540;
    private float happyLeft = (float) 877 / 1050, happyTop = (float)  166 / 540, happyRight2 = (float)  1040 / 1050, happyWidth = happyRight2 - happyLeft, happyBottom = (float)  190 / 540;
    private float shopButtonLeft = (float)962/1050, shopButtonTop = (float) 461/540;
    private float shopButtonWidth = (float) 75/1050, shopButtonHeight = (float)75/540;
    private float eatButtonLeft = (float) 19/1050, eatButtonTop = (float) 424/540;
    private float playButtonLeft = (float)130/1050, playButtonTop = (float) 427/540;
    private float sleepButtonTop = (float) 427/540, dirtButtonLeft = (float)352/1050;
    private float dirtButtonTop = (float)427/540;
    private float poopX = (float)  450 / 1050, poopY = (float)  309 / 540;
    private float poopWidth = (float)  94 / 1050, poopHeight = (float)  94 / 540;
    private int timeForPoop = 600;
    private int disgust = 1, eatScore, wash = 1,foinTime = 0, hit = 1, poop =1, poopFlyTime =1, poopFlyBackTime =0, m = 0,m1 = 1, m6 = 0, eat = 1, eatOnes = 0, eatTimer, m5 = 0, playTimer, sleepTimer, play = 0, sleep = 0, r1 = 0, flyBack = 0;
    private double hungryChangeValue = (float)1/ 2000; // Sovi timeri hamar
    private double happyChangeValue = (float) 1 / 1900; // Uraxutyan timeri hamar
    private double sleepChangeValue = (float) 1 / 1800; // Qni timeri hamar
    private double dirtyChangeValue = (float) 1 / 2100; // kextotutyan timeri hamar
    private float food = (float) 1/3 ;//food = (float) 1 / 10;
    private float smile = (float) 1 / 500;//smile = (float) 1 / 60;
    private float qun = (float) 1 / 40;//qun = (float) 1 / 40;
    private float lvl = (float) 1 / 10;
    private float hungryRight = 0, happyRight = 0, sleepRight = 0, dirtRight = 0, levelRight = 0;
    private int levelColor, dirtColor, hungryColor, tiredColor, happyColor;
    private boolean hungryButtonIsPressed = false, happyButtonIsTouched = false,sleepButtonIsPressed = false, birdIsPunched = false, washButtonIsPressed = false;
    private boolean playingTimeIsPassed = false,playingNeedToDrawNow = false;
    private boolean checkPlayButton = false, drawPlayButton = false;
    private boolean playChecker;
    private boolean hitting = false, eating, playing, gettingFoin = false, flying, laying, sleeping, flyingBack, washing, pooping , disgusting, poopFly, poopFlyBack;
    private boolean eatChecker, sleepChecker;
    private boolean checkEatButton = false, drawEatButton = false;
    private boolean checkSleepButton = false, drawSleepButton = false;
    private boolean eatingTimeIsPassed = false, eatingNeedToDrawNow = false;
    private boolean flyingToBedTimeIsPassed = false, flyingToBedNeedToDrawNow = false;
    private boolean poopTimeIsPassed = false, poopNeedToDraw = false;
    private boolean poopFlyTimeIsPassed = false, poopFlyNeedToDrawNow = false;
    private boolean poopFlyBackTimeIsPassed = false, poopFlyBackNeedToDrawNow3 = false;
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
    private boolean statChecker = true;
    private boolean checkSleep = false;
    private boolean isTouched = false, isSinging = false;
    private boolean loaded = false;
    private int lvlCheckTimePassed, lvlCheckTime;
    private View view;
    private Bitmap playButtonBitmap,playButtonBitmap2, playButtonBitmapPoop;
    private Bitmap sleepButtonBitmap,sleepButtonBitmap2, sleepButtonBitmapPoop;
    private Bitmap washButtonBitmap, washButtonBitmap2,washButtonBitmap3, washButtonBitmapPoop;
    // T = Tired
    // D = Dirty
    // S = Sad
    // H = Hungry
    private Bitmap bitmapbg,bitmapkust, bitmapDirtIcon, bitmapHungryIcon, bitmapSleepIcon, bitmapHappyIcon, eatButtonBitmap,eatButtonBitmap2, eatButtonBitmapPoop, highScoreBitmap, scoreBitmap;
    private Bitmap fird, bitmapDTSH1, bitmapDTSH2, bitmapDTS1, bitmapDTS2, bitmapDTH1, bitmapDTH2, bitmapDSH1, bitmapDSH2, bitmapTSH1, bitmapTSH2, bitmapDH1, bitmapDH2, bitmapDS1, bitmapDS2, bitmapDT1, bitmapDT2, bitmapSH1, bitmapSH2, bitmapTH1, bitmapTH2, bitmapTS1, bitmapTS2, bitmapD1, bitmapD2, bitmapT1, bitmapT2, bitmapS1, bitmapS2, bitmapH1, bitmapH2, bitmapSmile1, bitmapSmile2, bitmapUsual1, bitmapUsual2, birdBreath1, birdBreath2;
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
    private boolean loading = true, gettingLevel = false;
    private boolean lvlCheck = false;
    private boolean secondTimeIsPassed = false;
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
    private int poopBitmapId[] = {0,R.drawable.qaq1,R.drawable.qaq2,R.drawable.qaq3,R.drawable.qaq4};
    private int levelBitmapId[] = {0,R.drawable.level1,R.drawable.level2,R.drawable.level3,R.drawable.level4,R.drawable.level5,R.drawable.level6,R.drawable.level7,R.drawable.level8,R.drawable.level9,R.drawable.level10,R.drawable.level11,R.drawable.level12,R.drawable.level13,R.drawable.level14,R.drawable.level15,R.drawable.level16,R.drawable.level17,R.drawable.level18, R.drawable.level19, R.drawable.level20, R.drawable.level21, R.drawable.level22, R.drawable.level23, R.drawable.level24, R.drawable.level25, R.drawable.level26, R.drawable.level27, R.drawable.level28, R.drawable.level29, R.drawable.level30, R.drawable.level31, R.drawable.level32, R.drawable.level33, R.drawable.level34, R.drawable.level35, R.drawable.level36, R.drawable.level37, R.drawable.level38, R.drawable.level39, R.drawable.level40, R.drawable.level41, R.drawable.level42, R.drawable.level43, R.drawable.level44, R.drawable.level45, R.drawable.level46, R.drawable.level47, R.drawable.level48, R.drawable.level49, R.drawable.level50};
    private int playBitmapFireId[] = {0 ,R.drawable.xaxacox1_boc,R.drawable.xaxacox2_boc,R.drawable.xaxacox3_boc,R.drawable.xaxacox4_boc,R.drawable.xaxacox5_boc,R.drawable.xaxacox6_boc,R.drawable.xaxacox7_boc,R.drawable.xaxacox8_boc,R.drawable.xaxacox9_boc,R.drawable.xaxacox10_boc,R.drawable.xaxacox11_boc,R.drawable.xaxacox12_boc,R.drawable.xaxacox13_boc,R.drawable.xaxacox14_boc,R.drawable.xaxacox15_boc,R.drawable.xaxacox16_boc,R.drawable.xaxacox17_boc,R.drawable.xaxacox18_boc,R.drawable.xaxacox19_boc,R.drawable.xaxacox20_boc};
    private int playBitmapDFireId[] = {0,R.drawable.d_xaxacox1_boc,R.drawable.d_xaxacox2_boc,R.drawable.d_xaxacox3_boc,R.drawable.d_xaxacox4_boc,R.drawable.d_xaxacox5_boc,R.drawable.d_xaxacox6_boc,R.drawable.d_xaxacox7_boc,R.drawable.d_xaxacox8_boc,R.drawable.d_xaxacox9_boc,R.drawable.d_xaxacox10_boc,R.drawable.d_xaxacox11_boc,R.drawable.d_xaxacox12_boc,R.drawable.d_xaxacox13_boc,R.drawable.d_xaxacox14_boc,R.drawable.d_xaxacox15_boc,R.drawable.d_xaxacox16_boc,R.drawable.d_xaxacox17_boc,R.drawable.d_xaxacox18_boc,R.drawable.d_xaxacox19_boc,R.drawable.d_xaxacox20_boc};
    private int playBitmapTFireId[] = {0,R.drawable.xaxacox1_boc,R.drawable.xaxacox2_boc,R.drawable.xaxacox3_boc,R.drawable.xaxacox4_boc,R.drawable.xaxacox5_boc,R.drawable.xaxacox6_boc,R.drawable.xaxacox7_boc,R.drawable.xaxacox8_boc,R.drawable.xaxacox9_boc,R.drawable.xaxacox10_boc,R.drawable.xaxacox11_boc,R.drawable.xaxacox12_boc,R.drawable.xaxacox13_boc,R.drawable.xaxacox14_boc,R.drawable.xaxacox15_boc,R.drawable.xaxacox16_boc,R.drawable.xaxacox17_boc,R.drawable.xaxacox18_boc,R.drawable.t_xaxacox19_boc,R.drawable.t_xaxacox20_boc};
    private int playBitmapDTFireId[] = {0,R.drawable.d_xaxacox1_boc,R.drawable.d_xaxacox2_boc,R.drawable.d_xaxacox3_boc,R.drawable.d_xaxacox4_boc,R.drawable.d_xaxacox5_boc,R.drawable.d_xaxacox6_boc,R.drawable.d_xaxacox7_boc,R.drawable.d_xaxacox8_boc,R.drawable.d_xaxacox9_boc,R.drawable.d_xaxacox10_boc,R.drawable.d_xaxacox11_boc,R.drawable.d_xaxacox12_boc,R.drawable.d_xaxacox13_boc,R.drawable.d_xaxacox14_boc,R.drawable.d_xaxacox15_boc,R.drawable.d_xaxacox16_boc,R.drawable.d_xaxacox17_boc,R.drawable.d_xaxacox18_boc,R.drawable.d_t_xaxacox19_boc,R.drawable.d_t_xaxacox20_boc};
    private int flyBitmapUsualFireId[] = {0,R.drawable.trnox_cit1_boc,R.drawable.trnox_cit2_boc,R.drawable.trnox_cit3_boc,R.drawable.trnox_cit4_boc};
    private int flyBitmapDHFireId[] = {0,R.drawable.f_d_h1_boc,R.drawable.f_d_h2_boc,R.drawable.f_d_h3_boc,R.drawable.f_d_h4_boc};
    private int flyBitmapDFireId[] = {0,R.drawable.f_d1_boc,R.drawable.f_d2_boc,R.drawable.f_d3_boc,R.drawable.f_d4_boc};
    private int flyBitmapDSFireId[] = {0,R.drawable.f_d_s1_boc,R.drawable.f_d_s2_boc,R.drawable.f_d_s3_boc,R.drawable.f_d_s4_boc};
    private int flyBitmapHFireId[] = {0,R.drawable.f_h1_boc,R.drawable.f_h2_boc,R.drawable.f_h3_boc,R.drawable.f_h4_boc};
    private int flyBitmapSmileFireId[] = {0,R.drawable.f_hp1_boc,R.drawable.f_hp2_boc,R.drawable.f_hp3_boc,R.drawable.f_hp4_boc};
    private int flyBitmapSDHFireId[] = {0,R.drawable.f_s_d_h1_boc,R.drawable.f_s_d_h2_boc,R.drawable.f_s_d_h3_boc,R.drawable.f_s_d_h4_boc};
    private int flyBitmapSHFireId[] = {0,R.drawable.f_s_h1_boc,R.drawable.f_s_h2_boc,R.drawable.f_s_h3_boc,R.drawable.f_s_h4_boc};
    private int flyBitmapSFireId[] = {0,R.drawable.f_s1_boc,R.drawable.f_s2_boc,R.drawable.f_s3_boc,R.drawable.f_s4_boc};
    private int flyBitmapTDHFireId[] = {0,R.drawable.f_t_d_h1_boc,R.drawable.f_t_d_h2_boc,R.drawable.f_d_h3_boc,R.drawable.f_d_h4_boc};
    private int flyBitmapTDSHFireId[] = {0,R.drawable.f_t_d_s_h1_boc,R.drawable.f_t_d_s_h2_boc,R.drawable.f_s_d_h3_boc,R.drawable.f_s_d_h4_boc};
    private int flyBitmapTDSFireId[] = {0,R.drawable.f_t_d_s1_boc,R.drawable.f_t_d_s2_boc,R.drawable.f_d_s3_boc,R.drawable.f_d_s4_boc};
    private int flyBitmapTDFireId[] = {0,R.drawable.f_t_d1_boc,R.drawable.f_t_d2_boc,R.drawable.f_d3_boc,R.drawable.f_d4_boc};
    private int flyBitmapTHFireId[] = {0,R.drawable.f_t_h1_boc,R.drawable.f_t_h2_boc,R.drawable.f_h3_boc,R.drawable.f_h4_boc};
    private int flyBitmapTSHFireId[] = {0,R.drawable.f_t_s_h1_boc,R.drawable.f_t_s_h2_boc,R.drawable.f_s_h3_boc,R.drawable.f_s_h4_boc};
    private int flyBitmapTSFireId[] = {0,R.drawable.f_t_s1_boc,R.drawable.f_t_s2_boc,R.drawable.f_s3_boc,R.drawable.f_s4_boc};
    private int flyBitmapTFireId[] = {0,R.drawable.f_t1_boc,R.drawable.f_t2_boc,R.drawable.trnox_cit3_boc,R.drawable.trnox_cit4_boc};
    private int hitBitmapFireId[] = {0,R.drawable.tprtacox1_boc,R.drawable.tprtacox2_boc,R.drawable.tprtacox3_boc,R.drawable.tprtacox4_boc,R.drawable.tprtacox5_boc,R.drawable.tprtacox6_boc,R.drawable.tprtacox7_boc,R.drawable.tprtacox8_boc,R.drawable.tprtacox9_boc,R.drawable.tprtacox10_boc,R.drawable.tprtacox11_boc,R.drawable.tprtacox12_boc,R.drawable.tprtacox13_boc,R.drawable.tprtacox14_boc};
    private int hitBitmapDFireId[] = {0,R.drawable.ht_d1_boc,R.drawable.ht_d2_boc,R.drawable.ht_d3_boc,R.drawable.ht_d4_boc,R.drawable.ht_d5_boc,R.drawable.ht_d6_boc,R.drawable.ht_d7_boc,R.drawable.ht_d8_boc,R.drawable.ht_d9_boc,R.drawable.ht_d10_boc,R.drawable.ht_d11_boc,R.drawable.ht_d12_boc,R.drawable.ht_d13_boc,R.drawable.ht_d14_boc};
    private int hitBitmapHFireId[] = {0,R.drawable.ht_h1_boc,R.drawable.ht_h2_boc,R.drawable.ht_h3_boc,R.drawable.ht_h4_boc,R.drawable.ht_h5_boc,R.drawable.ht_h6_boc,R.drawable.ht_h7_boc,R.drawable.ht_h8_boc,R.drawable.ht_h9_boc,R.drawable.ht_h10_boc,R.drawable.ht_h11_boc,R.drawable.ht_h12_boc,R.drawable.ht_h13_boc,R.drawable.ht_h14_boc};
    private int hitBitmapDHFireId[] = {0,R.drawable.ht_d_h1_boc,R.drawable.ht_d_h2_boc,R.drawable.ht_d_h3_boc,R.drawable.ht_d_h4_boc,R.drawable.ht_d_h5_boc,R.drawable.ht_d_h6_boc,R.drawable.ht_d_h7_boc,R.drawable.ht_d_h8_boc,R.drawable.ht_d_h9_boc,R.drawable.ht_d_h10_boc,R.drawable.ht_d_h11_boc,R.drawable.ht_d_h12_boc,R.drawable.ht_d_h13_boc,R.drawable.ht_d_h14_boc};
    private int washBitmapFireId[] = {0, R.drawable.lvacvox1_boc, R.drawable.lvacvox2_boc, R.drawable.lvacvox3_boc, R.drawable.lvacvox4_boc, R.drawable.lvacvox5_boc, R.drawable.lvacvox6_boc, R.drawable.lvacvox7_boc, R.drawable.lvacvox8_boc, R.drawable.lvacvox9_boc, R.drawable.lvacvox10_boc, R.drawable.lvacvox11_boc, R.drawable.lvacvox12_boc, R.drawable.lvacvox13_boc, R.drawable.lvacvox14_boc, R.drawable.lvacvox15_boc, R.drawable.lvacvox16_boc, R.drawable.lvacvox17_boc, R.drawable.lvacvox18_boc, R.drawable.lvacvox19_boc, R.drawable.lvacvox20_boc, R.drawable.lvacvox21_boc, R.drawable.lvacvox22_boc, R.drawable.lvacvox23_boc, R.drawable.lvacvox24_boc, R.drawable.lvacvox25_boc, R.drawable.lvacvox26_boc, R.drawable.lvacvox27_boc, R.drawable.lvacvox28_boc, R.drawable.lvacvox29_boc, R.drawable.lvacvox30_boc, R.drawable.lvacvox31_boc, R.drawable.lvacvox32_boc, R.drawable.lvacvox33_boc, R.drawable.lvacvox34_boc, R.drawable.lvacvox35_boc, R.drawable.lvacvox36_boc, R.drawable.lvacvox37_boc, R.drawable.lvacvox38_boc, R.drawable.lvacvox39_boc, R.drawable.lvacvox40_boc, R.drawable.lvacvox41_boc, R.drawable.lvacvox42_boc, R.drawable.lvacvox43_boc, R.drawable.lvacvox44_boc, R.drawable.lvacvox45_boc, R.drawable.lvacvox46_boc, R.drawable.lvacvox47_boc, R.drawable.lvacvox48_boc};
    private int eatBitmapFireId[] = {0, R.drawable.utox1_boc,R.drawable.utox2_boc,R.drawable.utox3_boc,R.drawable.utox4_boc,R.drawable.utox5_boc,R.drawable.utox6_boc,R.drawable.utox7_boc, R.drawable.utox8_boc,R.drawable.utox9_boc, R.drawable.utox10_boc};
    private int eatBitmapDFireId[] = {0,R.drawable.e_d1_boc,R.drawable.e_d2_boc,R.drawable.e_d3_boc,R.drawable.e_d4_boc,R.drawable.e_d5_boc,R.drawable.e_d6_boc,R.drawable.e_d7_boc,R.drawable.e_d8_boc,R.drawable.e_d9_boc,R.drawable.e_d10_boc};
    private int eatBitmapDSFireId[] = {0,R.drawable.e_d_s1_boc,R.drawable.e_d_s2_boc,R.drawable.e_d_s3_boc,R.drawable.e_d_s4_boc,R.drawable.e_d_s5_boc,R.drawable.e_d_s6_boc,R.drawable.e_d_s7_boc,R.drawable.e_d_s8_boc,R.drawable.e_d_s9_boc,R.drawable.e_d_s10_boc};
    private int eatBitmapDTFireId[] = {0,R.drawable.e_d_t1_boc,R.drawable.e_d_t2_boc,R.drawable.e_d_t3_boc,R.drawable.e_d_t4_boc,R.drawable.e_d_t5_boc,R.drawable.e_d_t6_boc,R.drawable.e_d_t7_boc,R.drawable.e_d_t8_boc,R.drawable.e_d_t9_boc,R.drawable.e_d_t10_boc};
    private int eatBitmapDTSFireId[] = {0,R.drawable.e_d_t_s1_boc,R.drawable.e_d_t_s2_boc,R.drawable.e_d_t_s3_boc,R.drawable.e_d_t_s4_boc,R.drawable.e_d_t_s5_boc,R.drawable.e_d_t_s6_boc,R.drawable.e_d_t_s7_boc,R.drawable.e_d_t_s8_boc,R.drawable.e_d_t_s9_boc,R.drawable.e_d_t_s10_boc};
    private int eatBitmapSFireId[] = {0,R.drawable.e_s1_boc,R.drawable.e_s2_boc,R.drawable.e_s3_boc,R.drawable.e_s4_boc,R.drawable.e_s5_boc,R.drawable.e_s6_boc,R.drawable.e_s7_boc,R.drawable.e_s8_boc,R.drawable.e_s9_boc,R.drawable.e_s10_boc};
    private int eatBitmapTFireId[] = {0,R.drawable.e_t1_boc,R.drawable.e_t2_boc,R.drawable.e_t3_boc,R.drawable.e_t4_boc,R.drawable.e_t5_boc,R.drawable.e_t6_boc,R.drawable.e_t7_boc,R.drawable.e_t8_boc,R.drawable.e_t9_boc,R.drawable.e_t10_boc};
    private int eatBitmapTSFireId[] = {0,R.drawable.e_t_s_1_boc,R.drawable.e_t_s_2_boc,R.drawable.e_t_s_3_boc,R.drawable.e_t_s_4_boc,R.drawable.e_t_s_5_boc,R.drawable.e_t_s_6_boc,R.drawable.e_t_s_7_boc,R.drawable.e_t_s_8_boc,R.drawable.e_t_s_9_boc,R.drawable.e_t_s_10_boc};
    private int eatBitmapSmileFireId[] = {0,R.drawable.e_hp1_boc,R.drawable.e_hp2_boc,R.drawable.e_hp3_boc,R.drawable.e_hp4_boc,R.drawable.e_hp5_boc,R.drawable.e_hp6_boc,R.drawable.e_hp7_boc,R.drawable.e_hp8_boc,R.drawable.e_hp9_boc,R.drawable.e_hp10_boc};
    private int poopingBitmapUsualFireId[]={0,R.drawable.qaqox1_boc,R.drawable.qaqox2_boc,R.drawable.qaqox3_boc,R.drawable.qaqox4_boc,R.drawable.qaqox5_boc,R.drawable.qaqox6_boc,R.drawable.qaqox7_boc,R.drawable.qaqox8_boc,R.drawable.qaqox9_boc,R.drawable.qaqox10_boc};
    private int poopingBitmapDHFireId[] =  {0,R.drawable.p_d_h1_boc,R.drawable.p_d_h2_boc,R.drawable.p_d_h3_boc,R.drawable.p_d_h4_boc,R.drawable.p_d_h5_boc,R.drawable.p_d_h6_boc,R.drawable.p_d_h7_boc,R.drawable.p_d_h8_boc,R.drawable.p_d_h9_boc,R.drawable.p_d_h10_boc};
    private int poopingBitmapDSHFireId[] = {0,R.drawable.p_d_s_h1_boc,R.drawable.p_d_s_h2_boc,R.drawable.p_d_s_h3_boc,R.drawable.p_d_s_h4_boc,R.drawable.p_d_s_h5_boc,R.drawable.p_d_s_h6_boc,R.drawable.p_d_s_h7_boc,R.drawable.p_d_s_h8_boc,R.drawable.p_d_s_h9_boc,R.drawable.p_d_s_h10_boc};
    private int poopingBitmapDSFireId[] = {0,R.drawable.p_d_s1_boc,R.drawable.p_d_s2_boc,R.drawable.p_d_s3_boc,R.drawable.p_d_s4_boc,R.drawable.p_d_s5_boc,R.drawable.p_d_s6_boc,R.drawable.p_d_s7_boc,R.drawable.p_d_s8_boc,R.drawable.p_d_s9_boc,R.drawable.p_d_s10_boc};
    private int poopingBitmapDTHFireId[] = {0,R.drawable.p_d_t_h1_boc,R.drawable.p_d_t_h2_boc,R.drawable.p_d_t_h3_boc,R.drawable.p_d_t_h4_boc,R.drawable.p_d_t_h5_boc,R.drawable.p_d_t_h6_boc,R.drawable.p_d_t_h7_boc,R.drawable.p_d_t_h8_boc,R.drawable.p_d_t_h9_boc,R.drawable.p_d_t_h10_boc};
    private int poopingBitmapDTSHFireId[] = {0,R.drawable.p_d_t_s_h1_boc,R.drawable.p_d_t_s_h2_boc,R.drawable.p_d_t_s_h3_boc,R.drawable.p_d_t_s_h4_boc,R.drawable.p_d_t_s_h5_boc,R.drawable.p_d_t_s_h6_boc,R.drawable.p_d_t_s_h7_boc,R.drawable.p_d_t_s_h8_boc,R.drawable.p_d_t_s_h9_boc,R.drawable.p_d_t_s_h10_boc};
    private int poopingBitmapDTSFireId[] = {0,R.drawable.p_d_t_s1_boc,R.drawable.p_d_t_s2_boc,R.drawable.p_d_t_s3_boc,R.drawable.p_d_t_s4_boc,R.drawable.p_d_t_s5_boc,R.drawable.p_d_t_s6_boc,R.drawable.p_d_t_s7_boc,R.drawable.p_d_t_s8_boc,R.drawable.p_d_t_s9_boc,R.drawable.p_d_t_s10_boc};
    private int poopingBitmapDTFireId[] = {0,R.drawable.p_d_t1_boc,R.drawable.p_d_t2_boc,R.drawable.p_d_t3_boc,R.drawable.p_d_t4_boc,R.drawable.p_d_t5_boc,R.drawable.p_d_t6_boc,R.drawable.p_d_t7_boc,R.drawable.p_d_t8_boc,R.drawable.p_d_t9_boc,R.drawable.p_d_t10_boc};
    private int poopingBitmapDFireId[] = {0,R.drawable.p_d1_boc,R.drawable.p_d2_boc,R.drawable.p_d3_boc,R.drawable.p_d4_boc,R.drawable.p_d5_boc,R.drawable.p_d6_boc,R.drawable.p_d7_boc,R.drawable.p_d8_boc,R.drawable.p_d9_boc,R.drawable.p_d10_boc};
    private int poopingBitmapHSFireId[] = {0,R.drawable.p_h_s1_boc,R.drawable.p_h_s2_boc,R.drawable.p_h_s3_boc,R.drawable.p_h_s4_boc,R.drawable.p_h_s5_boc,R.drawable.p_h_s6_boc,R.drawable.p_h_s7_boc,R.drawable.p_h_s8_boc,R.drawable.p_h_s9_boc,R.drawable.p_h_s10_boc};
    private int poopingBitmapHFireId[] = {0,R.drawable.p_h1_boc,R.drawable.p_h2_boc,R.drawable.p_h3_boc,R.drawable.p_h4_boc,R.drawable.p_h5_boc,R.drawable.p_h6_boc,R.drawable.p_h7_boc,R.drawable.p_h8_boc,R.drawable.p_h9_boc,R.drawable.p_h10_boc};
    private int poopingBitmapSFireId[] = {0,R.drawable.p_s1_boc,R.drawable.p_s2_boc,R.drawable.p_s3_boc,R.drawable.p_s4_boc,R.drawable.p_s5_boc,R.drawable.p_s6_boc,R.drawable.p_s7_boc,R.drawable.p_s8_boc,R.drawable.p_s9_boc,R.drawable.p_s10_boc};
    private int poopingBitmapTHFireId[] = {0,R.drawable.p_t_h1_boc,R.drawable.p_t_h2_boc,R.drawable.p_t_h3_boc,R.drawable.p_t_h4_boc,R.drawable.p_t_h5_boc,R.drawable.p_t_h6_boc,R.drawable.p_t_h7_boc,R.drawable.p_t_h8_boc,R.drawable.p_t_h9_boc,R.drawable.p_t_h10_boc};
    private int poopingBitmapTHSFireId[] = {0,R.drawable.p_t_s_h1_boc,R.drawable.p_t_s_h2_boc,R.drawable.p_t_s_h3_boc,R.drawable.p_t_s_h4_boc,R.drawable.p_t_s_h5_boc,R.drawable.p_t_s_h6_boc,R.drawable.p_t_s_h7_boc,R.drawable.p_t_s_h8_boc,R.drawable.p_t_s_h9_boc,R.drawable.p_t_s_h10_boc};
    private int poopingBitmapTSFireId[] = {0,R.drawable.p_t_s1_boc,R.drawable.p_t_s2_boc,R.drawable.p_t_s3_boc,R.drawable.p_t_s4_boc,R.drawable.p_t_s5_boc,R.drawable.p_t_s6_boc,R.drawable.p_t_s7_boc,R.drawable.p_t_s8_boc,R.drawable.p_t_s9_boc,R.drawable.p_t_s10_boc};
    private int poopingBitmapTFireId[] = {0,R.drawable.p_t1_boc,R.drawable.p_t2_boc,R.drawable.p_t3_boc,R.drawable.p_t4_boc,R.drawable.p_t5_boc,R.drawable.p_t6_boc,R.drawable.p_t7_boc,R.drawable.p_t8_boc,R.drawable.p_t9_boc,R.drawable.p_t10_boc};
    private Bitmap loadScreen;
    private int skinId = 0;
    private boolean loadScreenLoaded;
    private String timeText;
    public DrawThread(){}
    public DrawThread(Context context, SurfaceHolder surfaceHolder, int timePassed, int foin, int skinId, String timeText) {
        this.view = view;
        this.timePassed = timePassed;
        this.surfaceHolder = surfaceHolder;
        this.context = context;
        this.foin = foin;
        this.timeText = timeText;
        this.skinId = skinId;
        this.res = context.getResources();
        lvlCheckTimePassed = timePassed;
        sharedPreferences = ((Activity)context).getPreferences(Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putInt("SKINID",skinId);
        editor.apply();

        Canvas canvas = surfaceHolder.lockCanvas();
        loadScreen = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.loading), canvas.getWidth(), canvas.getHeight(), true);
        canvas.drawBitmap(loadScreen, 0, 0, paint);
        surfaceHolder.unlockCanvasAndPost(canvas);
    }

    public void setTouch(int x, int y){
        lastTouchX = x;
        lastTouchY = y;
    }
    public void requestStop() {
        running = false;
    }

    private void loading(Canvas canvas) {
        loading = false;
        mediaPlayerHappy = MediaPlayer.create(context, R.raw.happysong);
        mediaPlayerHit = MediaPlayer.create(context, R.raw.hitsong);
        mediaPlayerWash = MediaPlayer.create(context,R.raw.washsong);
        skinId = sharedPreferences.getInt("SKINID",0);
        if(sharedPreferences.getInt("FOIN",9000) >= foin && sharedPreferences.getInt("FOIN",9000)- foin != 5000) {
            foin = sharedPreferences.getInt("FOIN", 9000);
            editor.putInt("FOIN",foin);
            editor.apply();
        }
        level = sharedPreferences.getInt("LEVEL", 1);
        levelColor = ResourcesCompat.getColor(context.getResources(),R.color.life,null);
        dirtColor = ResourcesCompat.getColor(context.getResources(),R.color.dirt,null);
        hungryColor = ResourcesCompat.getColor(context.getResources(),R.color.hungry,null);
        tiredColor = ResourcesCompat.getColor(context.getResources(),R.color.sleep,null);
        happyColor = ResourcesCompat.getColor(context.getResources(),R.color.happy,null);
        lvlCheckTime = sharedPreferences.getInt("LVLCHECKTIME",10);
        dirtRight = sharedPreferences.getFloat("DIRT", (float)  1040 / 1050);
        hungryRight = sharedPreferences.getFloat("HUNGRY", (float)  1040 / 1050);
        sleepRight = sharedPreferences.getFloat("SLEEP", (float)  1040 / 1050);
        happyRight = sharedPreferences.getFloat("HAPPY", (float) 1040 / 1050);
        levelRight = sharedPreferences.getFloat("LEVELRIGHT",(float)  844 / 1050);
        eatScore = sharedPreferences.getInt("EATSCORE",0);
        eatChecker = sharedPreferences.getBoolean("EATCHECKER",true);
        eatTimer = sharedPreferences.getInt("EATTIMER",10);
        eating = sharedPreferences.getBoolean("EATING", false);
        playChecker = sharedPreferences.getBoolean("PLAYCHECKER",true);
        playTimer = sharedPreferences.getInt("PLAYTIMER",15);
        playing = sharedPreferences.getBoolean("PLAYING", false);
        sleepChecker = sharedPreferences.getBoolean("SLEEPCHECKER",true);
        sleepTimer = sharedPreferences.getInt("SLEEPTIMER",60);
        sleeping = sharedPreferences.getBoolean("SLEEPING", false);
        flying = sharedPreferences.getBoolean("FLYING",false);
        flyingBack = sharedPreferences.getBoolean("FLYINGBACK",false);
        laying = sharedPreferences.getBoolean("LAYING",false);
        pooping = sharedPreferences.getBoolean("POOPING",false);
        poopFly = sharedPreferences.getBoolean("POOPFLY",false);
        poopFlyBack = sharedPreferences.getBoolean("POOPFLYBACK",false);
        disgusting = sharedPreferences.getBoolean("DISGUSTING",false);
        timeForPoop = sharedPreferences.getInt("TIMEFORPOOP",60);
        washing = sharedPreferences.getBoolean("WASHING",false);
        statChecker =  sharedPreferences.getBoolean("STATCHECKER", true);
        paintHappy.setColor(happyColor);
        paintSleep.setColor(tiredColor);
        paintHungry.setColor(hungryColor);
        paintDirt.setColor(dirtColor);
        paintLevel.setColor(levelColor);
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
        bitmapbg = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.background1), canvas.getWidth(), canvas.getHeight(), true);
        bitmapDarkbg = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.background2), canvas.getWidth(), canvas.getHeight(), true);
        bitmapkust = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.kustik), canvas.getWidth() * 245 / 1050, canvas.getHeight() * 180 / 540, true);
        bitmapDarkkust = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.kustik2), canvas.getWidth() * 245 / 1050, canvas.getHeight() * 180 / 540, true);
        bitmapDirtIcon = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.kextik), canvas.getWidth() * 60 / 1050, canvas.getHeight() * 51 / 540, true);
        bitmapHungryIcon = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.tarelka), canvas.getWidth() * 46 / 1050, canvas.getHeight() * 33 / 540, true);
        bitmapSleepIcon = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.zzz), canvas.getWidth() * 52 / 1050, canvas.getHeight() * 57 / 540, true);
        bitmapHappyIcon = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.smilik), canvas.getWidth() * 34 / 1050, canvas.getHeight() * 35 / 540, true);
        if(skinId == 0) {
            bitmapUsual1 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.sovorakan1), (int) (birdWidth * canvas.getWidth()), (int) (canvas.getHeight() * birdHeight), true);
            bitmapUsual2 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.sovorakan2), (int) (birdWidth * canvas.getWidth()), (int) (canvas.getHeight() * birdHeight), true);
            bitmapDTSH1 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.d_t_s_h_1), (int) (birdWidth * canvas.getWidth()), (int) (canvas.getHeight() * birdHeight), true);
            bitmapDTSH2 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.d_t_s_h_2), (int) (birdWidth * canvas.getWidth()), (int) (canvas.getHeight() * birdHeight), true);
            bitmapDTS1 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.d_t_s_1), (int) (birdWidth * canvas.getWidth()), (int) (canvas.getHeight() * birdHeight), true);
            bitmapDTS2 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.d_t_s_2), (int) (birdWidth * canvas.getWidth()), (int) (canvas.getHeight() * birdHeight), true);
            bitmapDTH1 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.d_t_h_1), (int) (birdWidth * canvas.getWidth()), (int) (canvas.getHeight() * birdHeight), true);
            bitmapDTH2 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.d_t_h_2), (int) (birdWidth * canvas.getWidth()), (int) (canvas.getHeight() * birdHeight), true);
            bitmapDSH1 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.d_s_h_1), (int) (birdWidth * canvas.getWidth()), (int) (canvas.getHeight() * birdHeight), true);
            bitmapDSH2 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.d_s_h_2), (int) (birdWidth * canvas.getWidth()), (int) (canvas.getHeight() * birdHeight), true);
            bitmapTSH1 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.t_s_h_1), (int) (birdWidth * canvas.getWidth()), (int) (canvas.getHeight() * birdHeight), true);
            bitmapTSH2 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.t_s_h_2), (int) (birdWidth * canvas.getWidth()), (int) (canvas.getHeight() * birdHeight), true);
            bitmapDH1 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.d_h_1), (int) (birdWidth * canvas.getWidth()), (int) (canvas.getHeight() * birdHeight), true);
            bitmapDH2 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.d_h_2), (int) (birdWidth * canvas.getWidth()), (int) (canvas.getHeight() * birdHeight), true);
            bitmapDS1 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.d_s_1), (int) (birdWidth * canvas.getWidth()), (int) (canvas.getHeight() * birdHeight), true);
            bitmapDS2 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.d_s_2), (int) (birdWidth * canvas.getWidth()), (int) (canvas.getHeight() * birdHeight), true);
            bitmapDT1 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.d_t_1), (int) (birdWidth * canvas.getWidth()), (int) (canvas.getHeight() * birdHeight), true);
            bitmapDT2 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.d_t_2), (int) (birdWidth * canvas.getWidth()), (int) (canvas.getHeight() * birdHeight), true);
            bitmapSH1 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.s_h_1), (int) (birdWidth * canvas.getWidth()), (int) (canvas.getHeight() * birdHeight), true);
            bitmapSH2 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.s_h_2), (int) (birdWidth * canvas.getWidth()), (int) (canvas.getHeight() * birdHeight), true);
            bitmapTH1 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.t_h_1), (int) (birdWidth * canvas.getWidth()), (int) (canvas.getHeight() * birdHeight), true);
            bitmapTH2 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.t_h_2), (int) (birdWidth * canvas.getWidth()), (int) (canvas.getHeight() * birdHeight), true);
            bitmapTS1 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.t_s_1), (int) (birdWidth * canvas.getWidth()), (int) (canvas.getHeight() * birdHeight), true);
            bitmapTS2 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.t_s_2), (int) (birdWidth * canvas.getWidth()), (int) (canvas.getHeight() * birdHeight), true);
            bitmapD1 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.kextot1), (int) (birdWidth * canvas.getWidth()), (int) (canvas.getHeight() * birdHeight), true);
            bitmapD2 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.kextot2), (int) (birdWidth * canvas.getWidth()), (int) (canvas.getHeight() * birdHeight), true);
            bitmapT1 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.hognac1), (int) (birdWidth * canvas.getWidth()), (int) (canvas.getHeight() * birdHeight), true);
            bitmapT2 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.hognac2), (int) (birdWidth * canvas.getWidth()), (int) (canvas.getHeight() * birdHeight), true);
            bitmapS1 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.txur1), (int) (birdWidth * canvas.getWidth()), (int) (canvas.getHeight() * birdHeight), true);
            bitmapS2 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.txur2), (int) (birdWidth * canvas.getWidth()), (int) (canvas.getHeight() * birdHeight), true);
            bitmapH1 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.sovac1), (int) (birdWidth * canvas.getWidth()), (int) (canvas.getHeight() * birdHeight), true);
            bitmapH2 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.sovac2), (int) (birdWidth * canvas.getWidth()), (int) (canvas.getHeight() * birdHeight), true);
            bitmapSmile1 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.urax1), (int) (birdWidth * canvas.getWidth()), (int) (canvas.getHeight() * birdHeight), true);
            bitmapSmile2 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.urax2), (int) (birdWidth * canvas.getWidth()), (int) (canvas.getHeight() * birdHeight), true);
            sleepUsual1 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.qnac1), canvas.getWidth() * 200 / 1050, canvas.getHeight() * 166 / 540, true);
            sleepUsual2 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.qnac2), canvas.getWidth() * 200 / 1050, canvas.getHeight() * 166 / 540, true);
            sleepDH1 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.sl_d_h1), canvas.getWidth() * 200 / 1050, canvas.getHeight() * 166 / 540, true);
            sleepDH2 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.sl_d_h2), canvas.getWidth() * 200 / 1050, canvas.getHeight() * 166 / 540, true);
            sleepDSH1 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.sl_d_s_h1), canvas.getWidth() * 200 / 1050, canvas.getHeight() * 166 / 540, true);
            sleepDSH2 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.sl_d_s_h2), canvas.getWidth() * 200 / 1050, canvas.getHeight() * 166 / 540, true);
            sleepDS1 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.sl_d_s1), canvas.getWidth() * 200 / 1050, canvas.getHeight() * 166 / 540, true);
            sleepDS2 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.sl_d_s2), canvas.getWidth() * 200 / 1050, canvas.getHeight() * 166 / 540, true);
            sleepD1 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.sl_d1), canvas.getWidth() * 200 / 1050, canvas.getHeight() * 166 / 540, true);
            sleepD2 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.sl_d2), canvas.getWidth() * 200 / 1050, canvas.getHeight() * 166 / 540, true);
            sleepH1 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.sl_h1), canvas.getWidth() * 200 / 1050, canvas.getHeight() * 166 / 540, true);
            sleepH2 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.sl_h2), canvas.getWidth() * 200 / 1050, canvas.getHeight() * 166 / 540, true);
            sleepSmile1 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.sl_hp1), canvas.getWidth() * 200 / 1050, canvas.getHeight() * 166 / 540, true);
            sleepSmile2 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.sl_hp2), canvas.getWidth() * 200 / 1050, canvas.getHeight() * 166 / 540, true);
            sleepSH1 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.sl_s_h1), canvas.getWidth() * 200 / 1050, canvas.getHeight() * 166 / 540, true);
            sleepSH2 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.sl_s_h2), canvas.getWidth() * 200 / 1050, canvas.getHeight() * 166 / 540, true);
            sleepS1 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.sl_s1), canvas.getWidth() * 200 / 1050, canvas.getHeight() * 166 / 540, true);
            sleepS2 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.sl_s2), canvas.getWidth() * 200 / 1050, canvas.getHeight() * 166 / 540, true);
            for (int i = 1; i < 51; i++) {
                levelBitmap[i] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources() ,levelBitmapId[i]), canvas.getWidth() * 45 / 1050, canvas.getHeight() * 45 / 540, true);
            }
            for (int i = 1; i < 11; i++) {
                eatBitmap[i] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), eatBitmapId[i]), (int) (canvas.getWidth() * birdWidth), (int) (canvas.getHeight() * 283.5 / 540), true);
                eatBitmapSmile[i] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), eatBitmapSmileId[i]), (int) (canvas.getWidth() * birdWidth), (int) (canvas.getHeight() * 283.5 / 540), true);
                eatBitmapD[i] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), eatBitmapDId[i]), (int) (canvas.getWidth() * birdWidth), (int) (canvas.getHeight() * 283.5 / 540), true);
                eatBitmapDS[i] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), eatBitmapDSId[i]), (int) (canvas.getWidth() * birdWidth), (int) (canvas.getHeight() * 283.5 / 540), true);
                eatBitmapDT[i] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), eatBitmapDTId[i]), (int) (canvas.getWidth() * birdWidth), (int) (canvas.getHeight() * 283.5 / 540), true);
                eatBitmapDTS[i] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), eatBitmapDTSId[i]), (int) (canvas.getWidth() * birdWidth), (int) (canvas.getHeight() * 283.5 / 540), true);
                eatBitmapS[i] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), eatBitmapSId[i]), (int) (canvas.getWidth() * birdWidth), (int) (canvas.getHeight() * 283.5 / 540), true);
                eatBitmapT[i] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), eatBitmapTId[i]), (int) (canvas.getWidth() * birdWidth), (int) (canvas.getHeight() * 283.5 / 540), true);
                eatBitmapTS[i] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), eatBitmapTSId[i]), (int) (canvas.getWidth() * birdWidth), (int) (canvas.getHeight() * 283.5 / 540), true);
                poopingBitmapUsual[i] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources() ,poopingBitmapUsualId[i]), (int) (canvas.getWidth() * birdWidth), (int) (canvas.getHeight() * birdHeight), true);
                poopingBitmapDTSH[i] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources() ,poopingBitmapDTSHId[i]), (int) (canvas.getWidth() * birdWidth), (int) (canvas.getHeight() * birdHeight), true);
                poopingBitmapDSH[i] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources() ,poopingBitmapDSHId[i]), (int) (canvas.getWidth() * birdWidth), (int) (canvas.getHeight() * birdHeight), true);
                poopingBitmapDTH[i] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources() ,poopingBitmapDTHId[i]), (int) (canvas.getWidth() * birdWidth), (int) (canvas.getHeight() * birdHeight), true);
                poopingBitmapDTS[i] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources() ,poopingBitmapDTSId[i]), (int) (canvas.getWidth() * birdWidth), (int) (canvas.getHeight() * birdHeight), true);
                poopingBitmapTHS[i] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources() ,poopingBitmapTHSId[i]), (int) (canvas.getWidth() * birdWidth), (int) (canvas.getHeight() * birdHeight), true);
                poopingBitmapDH[i] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources() ,poopingBitmapDHId[i]), (int) (canvas.getWidth() * birdWidth), (int) (canvas.getHeight() * birdHeight), true);
                poopingBitmapDT[i] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources() ,poopingBitmapDTId[i]), (int) (canvas.getWidth() * birdWidth), (int) (canvas.getHeight() * birdHeight), true);
                poopingBitmapDS[i] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources() ,poopingBitmapDSId[i]), (int) (canvas.getWidth() * birdWidth), (int) (canvas.getHeight() * birdHeight), true);
                poopingBitmapD[i] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources() ,poopingBitmapDId[i]), (int) (canvas.getWidth() * birdWidth), (int) (canvas.getHeight() * birdHeight), true);
                poopingBitmapTH[i] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources() ,poopingBitmapTHId[i]), (int) (canvas.getWidth() * birdWidth), (int) (canvas.getHeight() * birdHeight), true);
                poopingBitmapHS[i] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources() ,poopingBitmapHSId[i]), (int) (canvas.getWidth() * birdWidth), (int) (canvas.getHeight() * birdHeight), true);
                poopingBitmapTS[i] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources() ,poopingBitmapTSId[i]), (int) (canvas.getWidth() * birdWidth), (int) (canvas.getHeight() * birdHeight), true);
                poopingBitmapT[i] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources() ,poopingBitmapTId[i]), (int) (canvas.getWidth() * birdWidth), (int) (canvas.getHeight() * birdHeight), true);
                poopingBitmapH[i] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources() ,poopingBitmapHId[i]), (int) (canvas.getWidth() * birdWidth), (int) (canvas.getHeight() * birdHeight), true);
                poopingBitmapS[i] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources() ,poopingBitmapSId[i]), (int) (canvas.getWidth() * birdWidth), (int) (canvas.getHeight() * birdHeight), true);
            }
            for (int i = 1; i < 21; i++) {
                playBitmap[i] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), playBitmapId[i]), canvas.getWidth() * 194 / 1050, canvas.getHeight() * 172 / 540, true);
                playBitmapD[i] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), playBitmapDId[i]), canvas.getWidth() * 194 / 1050, canvas.getHeight() * 172 / 540, true);
                playBitmapDT[i] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), playBitmapDTId[i]), canvas.getWidth() * 194 / 1050, canvas.getHeight() * 172 / 540, true);
                playBitmapT[i] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), playBitmapTId[i]), canvas.getWidth() * 194 / 1050, canvas.getHeight() * 172 / 540, true);
            }
            for (int i = 1; i < 5; i++) {
                flyBitmapSmile[i] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(),flyBitmapSmileId[i]), canvas.getWidth() * 150 / 1050, canvas.getHeight() * 179 / 540, true);
                flyBitmapDH[i] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(),flyBitmapDHId[i]), canvas.getWidth() * 150 / 1050, canvas.getHeight() * 179 / 540, true);
                flyBitmapDS[i] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(),flyBitmapDSId[i]), canvas.getWidth() * 150 / 1050, canvas.getHeight() * 179 / 540, true);
                flyBitmapD[i] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(),flyBitmapDId[i]), canvas.getWidth() * 150 / 1050, canvas.getHeight() * 179 / 540, true);
                flyBitmapH[i] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(),flyBitmapHId[i]), canvas.getWidth() * 150 / 1050, canvas.getHeight() * 179 / 540, true);
                flyBitmapSDH[i] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(),flyBitmapSDHId[i]), canvas.getWidth() * 150 / 1050, canvas.getHeight() * 179 / 540, true);
                flyBitmapSH[i] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(),flyBitmapSHId[i]), canvas.getWidth() * 150 / 1050, canvas.getHeight() * 179 / 540, true);
                flyBitmapS[i] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(),flyBitmapSId[i]), canvas.getWidth() * 150 / 1050, canvas.getHeight() * 179 / 540, true);
                flyBitmapTDH[i] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(),flyBitmapTDHId[i]), canvas.getWidth() * 150 / 1050, canvas.getHeight() * 179 / 540, true);
                flyBitmapTDSH[i] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(),flyBitmapTDSHId[i]), canvas.getWidth() * 150 / 1050, canvas.getHeight() * 179 / 540, true);
                flyBitmapTDS[i] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(),flyBitmapTDSId[i]), canvas.getWidth() * 150 / 1050, canvas.getHeight() * 179 / 540, true);
                flyBitmapTD[i] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(),flyBitmapTDId[i]), canvas.getWidth() * 150 / 1050, canvas.getHeight() * 179 / 540, true);
                flyBitmapTH[i] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(),flyBitmapTHId[i]), canvas.getWidth() * 150 / 1050, canvas.getHeight() * 179 / 540, true);
                flyBitmapTSH[i] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(),flyBitmapTSHId[i]), canvas.getWidth() * 150 / 1050, canvas.getHeight() * 179 / 540, true);
                flyBitmapTS[i] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(),flyBitmapTSId[i]), canvas.getWidth() * 150 / 1050, canvas.getHeight() * 179 / 540, true);
                flyBitmapT[i] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(),flyBitmapTId[i]), canvas.getWidth() * 150 / 1050, canvas.getHeight() * 179 / 540, true);
                flyBitmapUsual[i] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(),flyBitmapUsualId[i]), canvas.getWidth() * 150 / 1050, canvas.getHeight() * 179 / 540, true);
                poopBitmap[i] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), poopBitmapId[i]), canvas.getWidth() * 94 / 1050, canvas.getHeight() * 94 / 540, true);
            }
            for (int i = 1; i < 15; i++) {
                hitBitmap[i] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), hitBitmapId[i]),(int)(canvas.getWidth() * birdWidth),(int)(canvas.getHeight()*birdHeight),true);
                hitBitmapD[i] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), hitBitmapDId[i]),(int)(canvas.getWidth() * birdWidth),(int)(canvas.getHeight()*birdHeight),true);
                hitBitmapH[i] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), hitBitmapHId[i]),(int)(canvas.getWidth() * birdWidth),(int)(canvas.getHeight()*birdHeight),true);
                hitBitmapDH[i] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), hitBitmapDHId[i]),(int)(canvas.getWidth() * birdWidth),(int)(canvas.getHeight()*birdHeight),true);
            }
            for (int i = 1; i < 49; i++) washBitmap[i] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(),washBitmapId[i]), canvas.getWidth() * 341 / 1050, canvas.getHeight() * 378 / 540, true);
        }
        if(skinId == 1) {
            bitmapUsual1 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(),R.drawable.sovorakan1_boc), (int) (birdWidth * canvas.getWidth()), (int) (canvas.getHeight() * birdHeight), true);
            bitmapUsual2 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(),R.drawable.sovorakan2_boc), (int) (birdWidth * canvas.getWidth()), (int) (canvas.getHeight() * birdHeight), true);
            bitmapDTSH1 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(),R.drawable.d_t_s_h_1_boc), (int) (birdWidth * canvas.getWidth()), (int) (canvas.getHeight() * birdHeight), true);
            bitmapDTSH2 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(),R.drawable.d_t_s_h_2_boc), (int) (birdWidth * canvas.getWidth()), (int) (canvas.getHeight() * birdHeight), true);
            bitmapDTS1 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(),R.drawable.d_t_s_1_boc), (int) (birdWidth * canvas.getWidth()), (int) (canvas.getHeight() * birdHeight), true);
            bitmapDTS2 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(),R.drawable.d_t_s_2_boc), (int) (birdWidth * canvas.getWidth()), (int) (canvas.getHeight() * birdHeight), true);
            bitmapDTH1 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(),R.drawable.d_t_h_1_boc), (int) (birdWidth * canvas.getWidth()), (int) (canvas.getHeight() * birdHeight), true);
            bitmapDTH2 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(),R.drawable.d_t_h_2_boc), (int) (birdWidth * canvas.getWidth()), (int) (canvas.getHeight() * birdHeight), true);
            bitmapDSH1 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(),R.drawable.d_s_h_1_boc), (int) (birdWidth * canvas.getWidth()), (int) (canvas.getHeight() * birdHeight), true);
            bitmapDSH2 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(),R.drawable.d_s_h_2_boc), (int) (birdWidth * canvas.getWidth()), (int) (canvas.getHeight() * birdHeight), true);
            bitmapTSH1 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(),R.drawable.t_s_h1_boc), (int) (birdWidth * canvas.getWidth()), (int) (canvas.getHeight() * birdHeight), true);
            bitmapTSH2 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(),R.drawable.t_s_h2_boc), (int) (birdWidth * canvas.getWidth()), (int) (canvas.getHeight() * birdHeight), true);
            bitmapDH1 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(),R.drawable.d_h_1_boc), (int) (birdWidth * canvas.getWidth()), (int) (canvas.getHeight() * birdHeight), true);
            bitmapDH2 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(),R.drawable.d_h_2_boc), (int) (birdWidth * canvas.getWidth()), (int) (canvas.getHeight() * birdHeight), true);
            bitmapDS1 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(),R.drawable.d_s_1_boc), (int) (birdWidth * canvas.getWidth()), (int) (canvas.getHeight() * birdHeight), true);
            bitmapDS2 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(),R.drawable.d_s_2_boc), (int) (birdWidth * canvas.getWidth()), (int) (canvas.getHeight() * birdHeight), true);
            bitmapDT1 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(),R.drawable.d_t_1_boc), (int) (birdWidth * canvas.getWidth()), (int) (canvas.getHeight() * birdHeight), true);
            bitmapDT2 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(),R.drawable.d_t_2_boc), (int) (birdWidth * canvas.getWidth()), (int) (canvas.getHeight() * birdHeight), true);
            bitmapSH1 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(),R.drawable.s_h1_boc), (int) (birdWidth * canvas.getWidth()), (int) (canvas.getHeight() * birdHeight), true);
            bitmapSH2 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(),R.drawable.s_h2_boc), (int) (birdWidth * canvas.getWidth()), (int) (canvas.getHeight() * birdHeight), true);
            bitmapTH1 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(),R.drawable.t_h1_boc), (int) (birdWidth * canvas.getWidth()), (int) (canvas.getHeight() * birdHeight), true);
            bitmapTH2 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(),R.drawable.t_h2_boc), (int) (birdWidth * canvas.getWidth()), (int) (canvas.getHeight() * birdHeight), true);
            bitmapTS1 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(),R.drawable.t_s1_boc), (int) (birdWidth * canvas.getWidth()), (int) (canvas.getHeight() * birdHeight), true);
            bitmapTS2 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(),R.drawable.t_s2_boc), (int) (birdWidth * canvas.getWidth()), (int) (canvas.getHeight() * birdHeight), true);
            bitmapD1 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(),R.drawable.kextot1_boc), (int) (birdWidth * canvas.getWidth()), (int) (canvas.getHeight() * birdHeight), true);
            bitmapD2 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(),R.drawable.kextot2_boc), (int) (birdWidth * canvas.getWidth()), (int) (canvas.getHeight() * birdHeight), true);
            bitmapT1 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(),R.drawable.hognac1_boc), (int) (birdWidth * canvas.getWidth()), (int) (canvas.getHeight() * birdHeight), true);
            bitmapT2 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(),R.drawable.hognac2_boc), (int) (birdWidth * canvas.getWidth()), (int) (canvas.getHeight() * birdHeight), true);
            bitmapS1 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(),R.drawable.txur1_boc) , (int) (birdWidth * canvas.getWidth()), (int) (canvas.getHeight() * birdHeight), true);
            bitmapS2 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(),R.drawable.txur2_boc) , (int) (birdWidth * canvas.getWidth()), (int) (canvas.getHeight() * birdHeight), true);
            bitmapH1 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(),R.drawable.sovac1_boc), (int) (birdWidth * canvas.getWidth()), (int) (canvas.getHeight() * birdHeight), true);
            bitmapH2 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(),R.drawable.sovac2_boc), (int) (birdWidth * canvas.getWidth()), (int) (canvas.getHeight() * birdHeight), true);
            bitmapSmile1 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(),R.drawable.urax1_boc), (int) (birdWidth * canvas.getWidth()), (int) (canvas.getHeight() * birdHeight), true);
            bitmapSmile2 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(),R.drawable.urax2_boc), (int) (birdWidth * canvas.getWidth()), (int) (canvas.getHeight() * birdHeight), true);
            sleepUsual1= Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(),R.drawable.qnac1_boc), canvas.getWidth() * 200 / 1050, canvas.getHeight() * 166 / 540, true);
            sleepUsual2= Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(),R.drawable.qnac2_boc), canvas.getWidth() * 200 / 1050, canvas.getHeight() * 166 / 540, true);
            sleepDH1 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(),R.drawable.sl_d_h1_boc), canvas.getWidth() * 200 / 1050, canvas.getHeight() * 166 / 540, true);
            sleepDH2 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(),R.drawable.sl_d_h2_boc), canvas.getWidth() * 200 / 1050, canvas.getHeight() * 166 / 540, true);
            sleepDSH1 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(),R.drawable.sl_d_s_h1_boc), canvas.getWidth() * 200 / 1050, canvas.getHeight() * 166 / 540, true);
            sleepDSH2 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(),R.drawable.sl_d_s_h2_boc), canvas.getWidth() * 200 / 1050, canvas.getHeight() * 166 / 540, true);
            sleepDS1 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(),R.drawable.sl_d_s1_boc), canvas.getWidth() * 200 / 1050, canvas.getHeight() * 166 / 540, true);
            sleepDS2 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(),R.drawable.sl_d_s2_boc), canvas.getWidth() * 200 / 1050, canvas.getHeight() * 166 / 540, true);
            sleepD1 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(),R.drawable.sl_d1_boc), canvas.getWidth() * 200 / 1050, canvas.getHeight() * 166 / 540, true);
            sleepD2 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(),R.drawable.sl_d2_boc), canvas.getWidth() * 200 / 1050, canvas.getHeight() * 166 / 540, true);
            sleepH1 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(),R.drawable.sl_h1_boc), canvas.getWidth() * 200 / 1050, canvas.getHeight() * 166 / 540, true);
            sleepH2 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(),R.drawable.sl_h2_boc), canvas.getWidth() * 200 / 1050, canvas.getHeight() * 166 / 540, true);
            sleepSmile1 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(),R.drawable.sl_hp1_boc), canvas.getWidth() * 200 / 1050, canvas.getHeight() * 166 / 540, true);
            sleepSmile2 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(),R.drawable.sl_hp2_boc), canvas.getWidth() * 200 / 1050, canvas.getHeight() * 166 / 540, true);
            sleepSH1 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(),R.drawable.sl_s_h1_boc), canvas.getWidth() * 200 / 1050, canvas.getHeight() * 166 / 540, true);
            sleepSH2 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(),R.drawable.sl_s_h2_boc), canvas.getWidth() * 200 / 1050, canvas.getHeight() * 166 / 540, true);
            sleepS1 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(),R.drawable.sl_s1_boc), canvas.getWidth() * 200 / 1050, canvas.getHeight() * 166 / 540, true);
            sleepS2 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(),R.drawable.sl_s2_boc), canvas.getWidth() * 200 / 1050, canvas.getHeight() * 166 / 540, true);
            for (int i = 1; i < 11; i++) {
                eatBitmap[i] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), eatBitmapFireId[i]), (int) (canvas.getWidth() * birdWidth), (int) (canvas.getHeight() * 283.5 / 540), true);
                eatBitmapSmile[i] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), eatBitmapSmileFireId[i]), (int) (canvas.getWidth() * birdWidth), (int) (canvas.getHeight() * 283.5 / 540), true);
                eatBitmapD[i] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), eatBitmapDFireId[i]), (int) (canvas.getWidth() * birdWidth), (int) (canvas.getHeight() * 283.5 / 540), true);
                eatBitmapDS[i] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), eatBitmapDSFireId[i]), (int) (canvas.getWidth() * birdWidth), (int) (canvas.getHeight() * 283.5 / 540), true);
                eatBitmapDT[i] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), eatBitmapDTFireId[i]), (int) (canvas.getWidth() * birdWidth), (int) (canvas.getHeight() * 283.5 / 540), true);
                eatBitmapDTS[i] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), eatBitmapDTSFireId[i]), (int) (canvas.getWidth() * birdWidth), (int) (canvas.getHeight() * 283.5 / 540), true);
                eatBitmapS[i] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), eatBitmapSFireId[i]), (int) (canvas.getWidth() * birdWidth), (int) (canvas.getHeight() * 283.5 / 540), true);
                eatBitmapT[i] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), eatBitmapTFireId[i]), (int) (canvas.getWidth() * birdWidth), (int) (canvas.getHeight() * 283.5 / 540), true);
                eatBitmapTS[i] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), eatBitmapTSFireId[i]), (int) (canvas.getWidth() * birdWidth), (int) (canvas.getHeight() * 283.5 / 540), true);
                poopingBitmapUsual[i] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources() ,poopingBitmapUsualFireId[i]), (int) (canvas.getWidth() * birdWidth), (int) (canvas.getHeight() * birdHeight), true);
                poopingBitmapDTSH[i] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources() ,poopingBitmapDTSHFireId[i]), (int) (canvas.getWidth() * birdWidth), (int) (canvas.getHeight() * birdHeight), true);
                poopingBitmapDSH[i] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources() ,poopingBitmapDSHFireId[i]), (int) (canvas.getWidth() * birdWidth), (int) (canvas.getHeight() * birdHeight), true);
                poopingBitmapDTH[i] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources() ,poopingBitmapDTHFireId[i]), (int) (canvas.getWidth() * birdWidth), (int) (canvas.getHeight() * birdHeight), true);
                poopingBitmapDTS[i] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources() ,poopingBitmapDTSFireId[i]), (int) (canvas.getWidth() * birdWidth), (int) (canvas.getHeight() * birdHeight), true);
                poopingBitmapTHS[i] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources() ,poopingBitmapTHSFireId[i]), (int) (canvas.getWidth() * birdWidth), (int) (canvas.getHeight() * birdHeight), true);
                poopingBitmapDH[i] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources() ,poopingBitmapDHFireId[i]), (int) (canvas.getWidth() * birdWidth), (int) (canvas.getHeight() * birdHeight), true);
                poopingBitmapDT[i] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources() ,poopingBitmapDTFireId[i]), (int) (canvas.getWidth() * birdWidth), (int) (canvas.getHeight() * birdHeight), true);
                poopingBitmapDS[i] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources() ,poopingBitmapDSFireId[i]), (int) (canvas.getWidth() * birdWidth), (int) (canvas.getHeight() * birdHeight), true);
                poopingBitmapD[i] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources() ,poopingBitmapDFireId[i]), (int) (canvas.getWidth() * birdWidth), (int) (canvas.getHeight() * birdHeight), true);
                poopingBitmapTH[i] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources() ,poopingBitmapTHFireId[i]), (int) (canvas.getWidth() * birdWidth), (int) (canvas.getHeight() * birdHeight), true);
                poopingBitmapHS[i] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources() ,poopingBitmapHSFireId[i]), (int) (canvas.getWidth() * birdWidth), (int) (canvas.getHeight() * birdHeight), true);
                poopingBitmapTS[i] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources() ,poopingBitmapTSFireId[i]), (int) (canvas.getWidth() * birdWidth), (int) (canvas.getHeight() * birdHeight), true);
                poopingBitmapT[i] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources() ,poopingBitmapTFireId[i]), (int) (canvas.getWidth() * birdWidth), (int) (canvas.getHeight() * birdHeight), true);
                poopingBitmapH[i] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources() ,poopingBitmapHFireId[i]), (int) (canvas.getWidth() * birdWidth), (int) (canvas.getHeight() * birdHeight), true);
                poopingBitmapS[i] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources() ,poopingBitmapSFireId[i]), (int) (canvas.getWidth() * birdWidth), (int) (canvas.getHeight() * birdHeight), true);
            }
            for (int i = 1; i < 21; i++) {
                playBitmap[i] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), playBitmapFireId[i]), canvas.getWidth() * 194 / 1050, canvas.getHeight() * 172 / 540, true);
                playBitmapD[i] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), playBitmapDFireId[i]), canvas.getWidth() * 194 / 1050, canvas.getHeight() * 172 / 540, true);
                playBitmapDT[i] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), playBitmapDTFireId[i]), canvas.getWidth() * 194 / 1050, canvas.getHeight() * 172 / 540, true);
                playBitmapT[i] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), playBitmapTFireId[i]), canvas.getWidth() * 194 / 1050, canvas.getHeight() * 172 / 540, true);
            }
            for (int i = 1; i < 5; i++) {
                flyBitmapSmile[i] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(),flyBitmapSmileFireId[i]), canvas.getWidth() * 150 / 1050, canvas.getHeight() * 179 / 540, true);
                flyBitmapDH[i] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(),flyBitmapDHFireId[i]), canvas.getWidth() * 150 / 1050, canvas.getHeight() * 179 / 540, true);
                flyBitmapDS[i] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(),flyBitmapDSFireId[i]), canvas.getWidth() * 150 / 1050, canvas.getHeight() * 179 / 540, true);
                flyBitmapD[i] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(),flyBitmapDFireId[i]), canvas.getWidth() * 150 / 1050, canvas.getHeight() * 179 / 540, true);
                flyBitmapH[i] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(),flyBitmapHFireId[i]), canvas.getWidth() * 150 / 1050, canvas.getHeight() * 179 / 540, true);
                flyBitmapSDH[i] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(),flyBitmapSDHFireId[i]), canvas.getWidth() * 150 / 1050, canvas.getHeight() * 179 / 540, true);
                flyBitmapSH[i] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(),flyBitmapSHFireId[i]), canvas.getWidth() * 150 / 1050, canvas.getHeight() * 179 / 540, true);
                flyBitmapS[i] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(),flyBitmapSFireId[i]), canvas.getWidth() * 150 / 1050, canvas.getHeight() * 179 / 540, true);
                flyBitmapTDH[i] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(),flyBitmapTDHFireId[i]), canvas.getWidth() * 150 / 1050, canvas.getHeight() * 179 / 540, true);
                flyBitmapTDSH[i] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(),flyBitmapTDSHFireId[i]), canvas.getWidth() * 150 / 1050, canvas.getHeight() * 179 / 540, true);
                flyBitmapTDS[i] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(),flyBitmapTDSFireId[i]), canvas.getWidth() * 150 / 1050, canvas.getHeight() * 179 / 540, true);
                flyBitmapTD[i] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(),flyBitmapTDFireId[i]), canvas.getWidth() * 150 / 1050, canvas.getHeight() * 179 / 540, true);
                flyBitmapTH[i] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(),flyBitmapTHFireId[i]), canvas.getWidth() * 150 / 1050, canvas.getHeight() * 179 / 540, true);
                flyBitmapTSH[i] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(),flyBitmapTSHFireId[i]), canvas.getWidth() * 150 / 1050, canvas.getHeight() * 179 / 540, true);
                flyBitmapTS[i] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(),flyBitmapTSFireId[i]), canvas.getWidth() * 150 / 1050, canvas.getHeight() * 179 / 540, true);
                flyBitmapT[i] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(),flyBitmapTFireId[i]), canvas.getWidth() * 150 / 1050, canvas.getHeight() * 179 / 540, true);
                flyBitmapUsual[i] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(),flyBitmapUsualFireId[i]), canvas.getWidth() * 150 / 1050, canvas.getHeight() * 179 / 540, true);
                poopBitmap[i] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), poopBitmapId[i]), canvas.getWidth() * 94 / 1050, canvas.getHeight() * 94 / 540, true);
            }
            for (int i = 1; i < 15; i++) {
                hitBitmap[i] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), hitBitmapFireId[i]),(int)(canvas.getWidth() * birdWidth),(int)(canvas.getHeight()*birdHeight),true);
                hitBitmapD[i] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), hitBitmapDFireId[i]),(int)(canvas.getWidth() * birdWidth),(int)(canvas.getHeight()*birdHeight),true);
                hitBitmapH[i] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), hitBitmapHFireId[i]),(int)(canvas.getWidth() * birdWidth),(int)(canvas.getHeight()*birdHeight),true);
                hitBitmapDH[i] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), hitBitmapDHFireId[i]),(int)(canvas.getWidth() * birdWidth),(int)(canvas.getHeight()*birdHeight),true);
            }
            for (int i = 1; i < 49; i++) washBitmap[i] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(),washBitmapFireId[i]), canvas.getWidth() * 341 / 1050, canvas.getHeight() * 378 / 540, true);
        }
        foinBitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(),getFoinBitmapId[0]),  canvas.getWidth() * 137 / 1050, canvas.getHeight()* 134 / 540, true);
        shopButton = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(),R.drawable.xanut),(int)(canvas.getWidth()*shopButtonWidth), (int)(canvas.getHeight()*shopButtonHeight),true);
        paintFoin.setTextSize((float) canvas.getWidth() * 20/1050);
        for (int i = 1; i < 51; i++) {
            levelBitmap[i] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources() ,levelBitmapId[i]), canvas.getWidth() * 45 / 1050, canvas.getHeight() * 45 / 540, true);
        }
        for (int i = 0; i < 8; i++) getFoinBitmap[i] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(),getFoinBitmapId[i]),  canvas.getWidth() * 137 / 1050, canvas.getHeight()* 134 / 540, true);
        for (int i = 0; i < 11; i++) eatDarkButtonBitmap[i] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), eatDarkButtonBitmapId[i]),(int)(canvas.getWidth()*ButtonWidth), (int)(canvas.getHeight()*ButtonHeight),true);
        for (int i = 0; i < 16; i++) playDarkButtonBitmap[i] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(),playDarkButtonBitmapId[i]), (int)(canvas.getWidth()*ButtonWidth), (int)(canvas.getHeight()*ButtonHeight),true);
        for (int i = 0; i < 61; i++) sleepDarkButtonBitmap[i] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), sleepDarkButtonBitmapId[i]),(int)(canvas.getWidth()*ButtonWidth), (int)(canvas.getHeight()*ButtonHeight),true);
        eatButtonBitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.chervyak),(int)(canvas.getWidth()*ButtonWidth), (int)(canvas.getHeight()*ButtonHeight),true);
        playButtonBitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.petur),(int)(canvas.getWidth()*ButtonWidth), (int)(canvas.getHeight()*ButtonHeight),true);
        sleepButtonBitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.qnel),(int)(canvas.getWidth()*ButtonWidth), (int)(canvas.getHeight()*ButtonHeight),true);
        washButtonBitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(),R.drawable.lvacvelu_knopka),(int)(canvas.getWidth()*ButtonWidth), (int)(canvas.getHeight()*ButtonHeight),true);
        washButtonBitmap2 = washButtonBitmap;
        washDarkButtonBitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(),R.drawable.lvacvelu_knopka_mug),(int)(canvas.getWidth()*ButtonWidth), (int)(canvas.getHeight()*ButtonHeight),true);
        eatButtonBitmapPoop = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.utelu_knopkaa),(int)(canvas.getWidth()*ButtonWidth), (int)(canvas.getHeight()*ButtonHeight),true);
        playButtonBitmapPoop = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.xaxalu_knopkaa),(int)(canvas.getWidth()*ButtonWidth), (int)(canvas.getHeight()*ButtonHeight),true);
        washButtonBitmapPoop = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(),R.drawable.lvacvelu_knopkaa),(int)(canvas.getWidth()*ButtonWidth), (int)(canvas.getHeight()*ButtonHeight),true);
        sleepButtonBitmapPoop = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.qnelu_knopkaa),(int)(canvas.getWidth()*ButtonWidth), (int)(canvas.getHeight()*ButtonHeight),true);
        fird = bitmapSmile1;
        // Обновляем кнопки после выхода и входа
        if(washing) {
            washing = false;
            dirtRight = dirtRight2;
            editor.putFloat("DIRT",dirtRight);
            editor.putBoolean("WASHING",false);
        }
        if(flying || laying) {
            if (birdBreath1 == bitmapDTSH1 || birdBreath1 == bitmapDSH1) fird = sleepDSH1;else if (birdBreath1 == bitmapDTS1 || birdBreath1 == bitmapDS1) fird = sleepDS1;else if (birdBreath1 == bitmapDTH1 || birdBreath1 == bitmapDH1) fird = sleepDH1;else if (birdBreath1 == bitmapDT1 || birdBreath1 == bitmapD1) fird = sleepD1;else if (birdBreath1 == bitmapTSH1 || birdBreath1 == bitmapSH1) fird = sleepSH1;else if (birdBreath1 == bitmapTS1 || birdBreath1 == bitmapS1) fird = sleepS1;else if (birdBreath1 == bitmapTH1 || birdBreath1 == bitmapH1) fird = sleepH1;else if (birdBreath1 == bitmapT1 || birdBreath1 == bitmapUsual1) fird = sleepUsual1;else if (birdBreath1 == bitmapSmile1) fird = sleepSmile1;
            qun *= (sleepRight2 - sleepLeft)*timePassed;
            if (sleepRight + qun > sleepRight2) {
                sleepRight = sleepRight2;
                laying = false;
                flyingBack = false;
                sleeping = false;
                sleepChecker = false;
                flying = false;
                sleepTimer = 60;
                sleepButtonBitmap = sleepDarkButtonBitmap[sleepTimer];
                editor.putBoolean("SLEEPCHECKER",sleepChecker);
                editor.putBoolean("SLEEPBUTTONISPRESSSED",true);
                editor.putFloat("SLEEP",sleepRight);
                editor.putBoolean("FLYING",flying);
                editor.putBoolean("LAYING",laying);
                editor.putBoolean("FLYINGBACK",flyingBack);
                editor.putBoolean("SLEEPING",sleeping);
            } else {
                sleepRight += qun;
                birdX = (float) 60 / 1050;
                birdY = (float) 207 / 540;
                laying = true;
                flying = false;
                editor.putFloat("SLEEP",sleepRight);
                editor.putBoolean("FLYING",flying);
                editor.putBoolean("LAYING",laying);
            }
            qun /= ((sleepRight2 - sleepLeft)* timePassed);
        }
        if(flyingBack) {
            sleeping = false;
            flyingBack = false;
            sleepChecker = false;
            sleepTimer = 60;
            sleepButtonBitmap = sleepDarkButtonBitmap[sleepTimer];
            editor.putBoolean("SLEEPCHECKER",sleepChecker);
            editor.putBoolean("LAYING",laying);
            editor.putBoolean("FLYINGBACK",flyingBack);
            editor.putBoolean("SLEEPING",sleeping);
        }
        if(!sleepChecker && !sleeping) {
            checkSleepButton = true;
            if(sleepTimer - timePassed >= 0) {
                sleepTimer -= timePassed;
                editor.putInt("SLEEPTIMER",sleepTimer);
            } else {
                sleepTimer = 0;
                sleepChecker = true;
                editor.putBoolean("SLEEPCHECKER",sleepChecker);
            }
            sleepButtonBitmap = sleepDarkButtonBitmap[sleepTimer];
        }
        if(!playChecker && !playing) {
            checkPlayButton =true;
            if(playTimer - timePassed >= 0) {
                playTimer -= timePassed;
                editor.putInt("PLAYTIMER",playTimer);
            }
            else{
                playChecker = true;
                playTimer = 0;
                editor.putBoolean("PLAYCHECKER",playChecker);
            }
            playButtonBitmap = playDarkButtonBitmap[playTimer];
        }
        if(playing) {
            playing = false;
            playChecker = false;
            checkPlayButton = true;
            playTimer = 15;
            if(playTimer - timePassed >= 0) {
                playTimer -= timePassed;
            }
            else{
                playTimer = 0;
                playChecker = true;
            }
            playButtonBitmap = playDarkButtonBitmap[playTimer];
            editor.putInt("PLAYTIMER",playTimer);
            editor.putBoolean("PLAYCHECKER",playChecker);
            editor.putBoolean("PLAYING",playing);
        }
        if(!eatChecker && !eating) {
            checkEatButton = true;
            if(eatTimer - timePassed >= 0) {
                eatTimer -= timePassed;
                editor.putInt("EATTIMER",eatTimer);
            }
            else{
                eatChecker = true;
                eatTimer = 0;
                editor.putBoolean("EATCHECKER",eatChecker);
            }
            eatButtonBitmap = eatDarkButtonBitmap[eatTimer];
        }
        if(eating) {
            eating = false;
            checkEatButton = true;
            eatChecker = false;
            fird = birdBreath1;
            eatTimer = 10;
            eatScore++;
            if(eatTimer - timePassed >= 0) {
                eatTimer -= timePassed;
            } else{
                eatTimer = 0;
                eatChecker = true;
            }
            eatButtonBitmap = eatDarkButtonBitmap[eatTimer];
            editor.putInt("EATTIMER",eatTimer);
            editor.putBoolean("EATCHECKER",eatChecker);
            editor.putBoolean("EATING",eating);
            editor.putInt("EATSCORE",eatScore);
            food *= (hungryRight2 - hungryLeft);
            if (hungryRight + food >= hungryRight2) {
                hungryRight = hungryRight2;
            } else {
                hungryRight += food;
            }
            editor.putFloat("HUNGRY", hungryRight);
            food /= (hungryRight2 - hungryLeft);
        }

        eatButtonBitmap2 = eatButtonBitmap;
        sleepButtonBitmap2 = sleepButtonBitmap;
        playButtonBitmap2 = playButtonBitmap;
        washButtonBitmap3 = washButtonBitmap;
        if(disgusting || poopFly) {
            disgusting = true;
            poopFly = false;
            birdX = (float) 795 / 1050;
            birdY = (float) 232 / 540;
            eatButtonBitmap = eatButtonBitmapPoop;
            sleepButtonBitmap = sleepButtonBitmapPoop;
            playButtonBitmap = playButtonBitmapPoop;
            washButtonBitmap = washButtonBitmapPoop;
            editor.putBoolean("DISGUSTING",disgusting);
            editor.putBoolean("POOPFLY",poopFly);
        }
        if(poopFlyBack) {
            poopFlyBack = false;
            pooping = false;
            eatScore = 0;
            eatButtonBitmap = eatButtonBitmap2;
            sleepButtonBitmap = sleepButtonBitmap2;
            playButtonBitmap = playButtonBitmap2;
            washButtonBitmap = washButtonBitmap3;
            editor.putBoolean("POOPFLYBACK",poopFlyBack);
            editor.putBoolean("POOPING",pooping);
            editor.putInt("TIMEFORPOOP",timeForPoop);
            editor.putInt("EATSCORE",0);

        }
        // Обновляем статистику после выхода и входа

        changeStateAfterStart(dirtRight,dirtLeft, dirtWidth,dirtyChangeValue,1);
        changeStateAfterStart(hungryRight,hungryLeft, hungryWidth,hungryChangeValue,2);
        changeStateAfterStart(sleepRight,sleepLeft, sleepWidth,sleepChangeValue,3);
        changeStateAfterStart(happyRight,happyLeft, happyWidth,happyChangeValue,4);

        if(timeForPoop - timePassed > 0) {
            timeForPoop -= timePassed;
        } else timeForPoop = 0;
        if(lvlCheckTime - timePassed > 0) {
            lvlCheckTime -= timePassed;
        } else lvlCheckTime = 0;

        editor.putInt("LVLCHECKTIME",lvlCheckTime);
        editor.putInt("TIMEFORPOOP",timeForPoop);
        editor.apply();
        loaded = true;
    }

    @Override
    public void run() {
        while (running) {
            Canvas canvas = surfaceHolder.lockCanvas();
            if (canvas != null) {
                try {
                    if(loading) {
                        loading(canvas);
                    }
                    if(loaded) {
                        //lvlCheck = sharedPreferences.getBoolean("LVLCHECK",false);
                        paintBlack.setStrokeWidth((float) canvas.getWidth() / 540);
                        // Задний фон

                        if (!flying&& !laying) {
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
                            stateChanger(dirtRight, dirtLeft, dirtWidth, dirtyChangeValue, 1);
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
                            stateChanger(hungryRight, hungryLeft, hungryWidth, hungryChangeValue, 2);
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
                            stateChanger(sleepRight, sleepLeft, sleepWidth, sleepChangeValue, 3);
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
                            stateChanger(happyRight, happyLeft, happyWidth, happyChangeValue, 4);
                            happyNeedToDrawNow = false;
                        }
                        // Иконки уровня, загрезнения, голода, усталости и радости

                        canvas.drawBitmap(levelBitmap[level], (float) canvas.getWidth() * 815 / 1050, (float) canvas.getHeight() * 2 / 540, paint);

                        canvas.drawBitmap(bitmapDirtIcon, (float) canvas.getWidth() * 840 / 1050, (float) canvas.getHeight() * 41 / 540, paint);

                        canvas.drawBitmap(bitmapHungryIcon, (float) canvas.getWidth() * 850 / 1050, (float) canvas.getHeight() * 87 / 540, paint);

                        canvas.drawBitmap(bitmapSleepIcon, (float) canvas.getWidth() * 853 / 1050, (float) canvas.getHeight() * 110 / 540, paint);

                        canvas.drawBitmap(bitmapHappyIcon, (float) canvas.getWidth() * 862 / 1050, (float) canvas.getHeight() * 161 / 540, paint);

                        // Проверяем  состояние птички
                        checkFirdState();
                        if (m == 0) {
                            m = 1;
                            fird = birdBreath1;
                        }
                        if(!secondTimeIsPassed) {
                            new Second().start();
                            secondTimeIsPassed = true;
                        }
                        // Таймер дыхания

                        if (!eating && !playing && !flying && !sleeping && !laying && !hitting && !washing && !pooping && !poopFly && !disgusting && !poopFlyBack) {
                            if (!timeIsPassed1 && !check) {
                                new ThreadBird1().start();
                                timeIsPassed1 = true;
                            }
                            if (needToDrawNow1 && !check) fird = birdBreath1;
                            if (!timeIsPassed2 && check) {
                                new ThreadBird2().start();
                                timeIsPassed2 = true;
                            }
                            if (needToDrawNow2 && check) fird = birdBreath2;
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
                        if (foin >= 100 && foin <= 999)
                            canvas.drawText(foin + " ", (float) canvas.getWidth() * 89 / 1050, (float) canvas.getHeight() * 49 / 540, paintFoin);
                        if (foin >= 1000 && foin <= 9999)
                            canvas.drawText(foin + " ", (float) canvas.getWidth() * 83 / 1050, (float) canvas.getHeight() * 49 / 540, paintFoin);
                        // Кнопка голода

                        if (!eatChecker && checkEatButton && !pooping && !poopFly && !disgusting && !poopFlyBack) {
                            new EatDarkButtonThread().start();
                            checkEatButton = false;
                        }
                        if (drawEatButton && !pooping && !poopFly && !disgusting && !poopFlyBack) {
                            if (eatTimer >= 0)eatButtonBitmap = eatDarkButtonBitmap[eatTimer];
                            if (eatTimer <= 0) {
                                eatChecker = true;
                                editor.putBoolean("EATCHECKER",eatChecker);
                                editor.apply();
                            }
                        }
                        canvas.drawBitmap(eatButtonBitmap, (float) canvas.getWidth() * eatButtonLeft, (float) canvas.getHeight() * eatButtonTop, paint);

                        // Кнопка радости

                        if (!playChecker && checkPlayButton && !pooping && !poopFly && !disgusting && !poopFlyBack) {
                            new PlayDarkButtonThread().start();
                            checkPlayButton = false;
                        }
                        if (drawPlayButton && !pooping && !poopFly && !disgusting && !poopFlyBack) {
                            if(playTimer>=0)playButtonBitmap = playDarkButtonBitmap[playTimer];
                            if (playTimer <= 0) {
                                playChecker = true;
                                editor.putBoolean("PLAYCHECKER",playChecker);
                                editor.apply();
                            }
                        }
                        canvas.drawBitmap(playButtonBitmap, (float) canvas.getWidth() * playButtonLeft, (float) canvas.getHeight() * playButtonTop, paint);

                        // Кнопка усталости

                        if (!sleepChecker && checkSleepButton && !pooping && !poopFly && !disgusting && !poopFlyBack) {
                            new SleepDarkButtonThread().start();
                            checkSleepButton = false;
                        }
                        if (drawSleepButton && !pooping && !poopFly && !disgusting && !poopFlyBack) {
                            if(sleepTimer>=0)   sleepButtonBitmap = sleepDarkButtonBitmap[sleepTimer];
                            if (sleepTimer <= 0) {
                                sleepChecker = true;
                                editor.putBoolean("SLEEPCHECKER",sleepChecker);
                                editor.apply();
                            }
                        }
                        float sleepButtonLeft = (float) 241 / 1050;
                        canvas.drawBitmap(sleepButtonBitmap, (float) canvas.getWidth() * sleepButtonLeft, (float) canvas.getHeight() * sleepButtonTop, paint);

                        // Кнопка загрязнения

                        if (dirtRight - dirtLeft >= dirtWidth / 2. && !pooping && !poopFly && !disgusting && !poopFlyBack)
                            washButtonBitmap = washDarkButtonBitmap;
                        if (dirtRight - dirtLeft <= dirtWidth / 2. && !pooping && !poopFly && !disgusting && !poopFlyBack)
                            washButtonBitmap = washButtonBitmap2;
                        canvas.drawBitmap(washButtonBitmap, (float) canvas.getWidth() * dirtButtonLeft, (float) canvas.getHeight() * dirtButtonTop, paint);

                        // Какать
                        if (eatScore >= 5 && timeForPoop <= 0 && !pooping && !eating && !playing && !flying && !sleeping && !laying && !hitting && !washing && !flyingBack) {
                            eatButtonBitmap2 = eatButtonBitmap;
                            sleepButtonBitmap2 = sleepButtonBitmap;
                            playButtonBitmap2 = playButtonBitmap;
                            washButtonBitmap3 = washButtonBitmap;
                            eatButtonBitmap = eatButtonBitmapPoop;
                            sleepButtonBitmap = sleepButtonBitmapPoop;
                            playButtonBitmap = playButtonBitmapPoop;
                            washButtonBitmap = washButtonBitmapPoop;
                            pooping = true;
                            editor.putBoolean("POOPING",pooping);
                            editor.apply();
                        }
                        if (pooping && !isTouched && poopNeedToDraw) {
                            if (poop >= 4) {
                                poop = 1;
                            }
                            poopNeedToDraw = false;
                        }
                        if(pooping && !isTouched) canvas.drawBitmap(poopBitmap[poop], (float) canvas.getWidth() * poopX, (float) canvas.getHeight() * poopY, paint);
                        if (pooping && !poopTimeIsPassed) {
                            new PoopThread().start();
                            poopTimeIsPassed = true;
                        }
                        if (pooping && !poopFlyTimeIsPassed && !disgusting && !poopFlyBack) {
                            new PoopFlyThread().start();
                            poopFly = true;
                            editor.putBoolean("POOPFLY",poopFly);
                            editor.apply();
                            poopFlyTimeIsPassed = true;
                        }
                        if (poopFlyNeedToDrawNow && poopFly) {
                            switch (poopFlyTime) {
                                case 1:
                                    if (birdBreath1 == bitmapDTSH1) fird = flyBitmapTDSH[4];else if (birdBreath1 == bitmapDH1) fird = flyBitmapDH[4];else if (birdBreath1 == bitmapDS1) fird = flyBitmapDS[4];else if (birdBreath1 == bitmapD1) fird = flyBitmapD[4];else if (birdBreath1 == bitmapH1) fird = flyBitmapH[4];else if (birdBreath1 == bitmapSmile1) fird = flyBitmapSmile[4];else if (birdBreath1 == bitmapSH1) fird = flyBitmapSH[4];else if (birdBreath1 == bitmapDSH1) fird = flyBitmapSDH[4];else if (birdBreath1 == bitmapS1) fird = flyBitmapS[4];else if (birdBreath1 == bitmapDTH1) fird = flyBitmapTDH[4];else if (birdBreath1 == bitmapDTS1) fird = flyBitmapTDS[4];else if (birdBreath1 == bitmapDT1) fird = flyBitmapTD[4];else if (birdBreath1 == bitmapTH1) fird = flyBitmapTH[4];else if (birdBreath1 == bitmapTSH1) fird = flyBitmapTSH[4];else if (birdBreath1 == bitmapTS1) fird = flyBitmapTS[4];else if (birdBreath1 == bitmapT1) fird = flyBitmapT[4];else if (birdBreath1 == bitmapUsual1) fird = flyBitmapUsual[4];
                                    break;
                                case 2:
                                    if (birdBreath1 == bitmapDTSH1) fird = flyBitmapTDSH[3];else if (birdBreath1 == bitmapDH1) fird = flyBitmapDH[3];else if (birdBreath1 == bitmapDS1) fird = flyBitmapDS[3];else if (birdBreath1 == bitmapD1) fird = flyBitmapD[3];else if (birdBreath1 == bitmapH1) fird = flyBitmapH[3];else if (birdBreath1 == bitmapSmile1) fird = flyBitmapSmile[3];else if (birdBreath1 == bitmapSH1) fird = flyBitmapSH[3];else if (birdBreath1 == bitmapDSH1) fird = flyBitmapSDH[3];else if (birdBreath1 == bitmapS1) fird = flyBitmapS[3];else if (birdBreath1 == bitmapDTH1) fird = flyBitmapTDH[3];else if (birdBreath1 == bitmapDTS1) fird = flyBitmapTDS[3];else if (birdBreath1 == bitmapDT1) fird = flyBitmapTD[3];else if (birdBreath1 == bitmapTH1) fird = flyBitmapTH[3];else if (birdBreath1 == bitmapTSH1) fird = flyBitmapTSH[3];else if (birdBreath1 == bitmapTS1) fird = flyBitmapTS[3];else if (birdBreath1 == bitmapT1) fird = flyBitmapT[3];else if (birdBreath1 == bitmapUsual1) fird = flyBitmapUsual[3];
                                    birdX = (float) 548 / 1050;
                                    birdY = (float) 176 / 540;
                                    break;
                                case 3:
                                    if (birdBreath1 == bitmapDTSH1) fird = flyBitmapTDSH[4];else if (birdBreath1 == bitmapDH1) fird = flyBitmapDH[4];else if (birdBreath1 == bitmapDS1) fird = flyBitmapDS[4];else if (birdBreath1 == bitmapD1) fird = flyBitmapD[4];else if (birdBreath1 == bitmapH1) fird = flyBitmapH[4];else if (birdBreath1 == bitmapSmile1) fird = flyBitmapSmile[4];else if (birdBreath1 == bitmapSH1) fird = flyBitmapSH[4];else if (birdBreath1 == bitmapDSH1) fird = flyBitmapSDH[4];else if (birdBreath1 == bitmapS1) fird = flyBitmapS[4];else if (birdBreath1 == bitmapDTH1) fird = flyBitmapTDH[4];else if (birdBreath1 == bitmapDTS1) fird = flyBitmapTDS[4];else if (birdBreath1 == bitmapDT1) fird = flyBitmapTD[4];else if (birdBreath1 == bitmapTH1) fird = flyBitmapTH[4];else if (birdBreath1 == bitmapTSH1) fird = flyBitmapTSH[4];else if (birdBreath1 == bitmapTS1) fird = flyBitmapTS[4];else if (birdBreath1 == bitmapT1) fird = flyBitmapT[4];else if (birdBreath1 == bitmapUsual1) fird = flyBitmapUsual[4];
                                    birdX = (float) 625 / 1050;
                                    birdY = (float) 203 / 540;
                                    break;
                                case 4:
                                    if (birdBreath1 == bitmapDTSH1) fird = flyBitmapTDSH[3];else if (birdBreath1 == bitmapDH1) fird = flyBitmapDH[3];else if (birdBreath1 == bitmapDS1) fird = flyBitmapDS[3];else if (birdBreath1 == bitmapD1) fird = flyBitmapD[3];else if (birdBreath1 == bitmapH1) fird = flyBitmapH[3];else if (birdBreath1 == bitmapSmile1) fird = flyBitmapSmile[3];else if (birdBreath1 == bitmapSH1) fird = flyBitmapSH[3];else if (birdBreath1 == bitmapDSH1) fird = flyBitmapSDH[3];else if (birdBreath1 == bitmapS1) fird = flyBitmapS[3];else if (birdBreath1 == bitmapDTH1) fird = flyBitmapTDH[3];else if (birdBreath1 == bitmapDTS1) fird = flyBitmapTDS[3];else if (birdBreath1 == bitmapDT1) fird = flyBitmapTD[3];else if (birdBreath1 == bitmapTH1) fird = flyBitmapTH[3];else if (birdBreath1 == bitmapTSH1) fird = flyBitmapTSH[3];else if (birdBreath1 == bitmapTS1) fird = flyBitmapTS[3];else if (birdBreath1 == bitmapT1) fird = flyBitmapT[3];else if (birdBreath1 == bitmapUsual1) fird = flyBitmapUsual[3];
                                    birdX = (float) 715 / 1050;
                                    birdY = (float) 195 / 540;
                                    break;
                                case 5:
                                    if (birdBreath1 == bitmapDTSH1) fird = flyBitmapTDSH[4];else if (birdBreath1 == bitmapDH1) fird = flyBitmapDH[4];else if (birdBreath1 == bitmapDS1) fird = flyBitmapDS[4];else if (birdBreath1 == bitmapD1) fird = flyBitmapD[4];else if (birdBreath1 == bitmapH1) fird = flyBitmapH[4];else if (birdBreath1 == bitmapSmile1) fird = flyBitmapSmile[4];else if (birdBreath1 == bitmapSH1) fird = flyBitmapSH[4];else if (birdBreath1 == bitmapDSH1) fird = flyBitmapSDH[4];else if (birdBreath1 == bitmapS1) fird = flyBitmapS[4];else if (birdBreath1 == bitmapDTH1) fird = flyBitmapTDH[4];else if (birdBreath1 == bitmapDTS1) fird = flyBitmapTDS[4];else if (birdBreath1 == bitmapDT1) fird = flyBitmapTD[4];else if (birdBreath1 == bitmapTH1) fird = flyBitmapTH[4];else if (birdBreath1 == bitmapTSH1) fird = flyBitmapTSH[4];else if (birdBreath1 == bitmapTS1) fird = flyBitmapTS[4];else if (birdBreath1 == bitmapT1) fird = flyBitmapT[4];else if (birdBreath1 == bitmapUsual1) fird = flyBitmapUsual[4];
                                    birdX = (float) 795 / 1050;
                                    birdY = (float) 232 / 540;
                                    break;
                                case 6:
                                    disgusting = true;
                                    poopFly = false;
                                    editor.putBoolean("DISGUSTING",disgusting);
                                    editor.putBoolean("POOPFLY",poopFly);
                                    editor.apply();
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
                                fird = poopingBitmapUsual[disgust];else if (birdBreath1 == bitmapDSH1) fird = poopingBitmapDSH[disgust];else if (birdBreath1 == bitmapDTS1) fird = poopingBitmapDTS[disgust];else if (birdBreath1 == bitmapDTH1) fird = poopingBitmapDTH[disgust];else if (birdBreath1 == bitmapDH1) fird = poopingBitmapDH[disgust];else if (birdBreath1 == bitmapDT1) fird = poopingBitmapDT[disgust];else if (birdBreath1 == bitmapDS1) fird = poopingBitmapDS[disgust];else if (birdBreath1 == bitmapD1) fird = poopingBitmapD[disgust];else if (birdBreath1 == bitmapTSH1) fird = poopingBitmapTHS[disgust];else if (birdBreath1 == bitmapSH1) fird = poopingBitmapHS[disgust];else if (birdBreath1 == bitmapTH1) fird = poopingBitmapTH[disgust];else if (birdBreath1 == bitmapH1) fird = poopingBitmapH[disgust];else if (birdBreath1 == bitmapTS1) fird = poopingBitmapTS[disgust];else if (birdBreath1 == bitmapT1) fird = poopingBitmapT[disgust];else if (birdBreath1 == bitmapS1) fird = poopingBitmapS[disgust];
                        }
                        if (disgusting && lastTouchX >= poopX * canvas.getWidth() && lastTouchX <= canvas.getWidth() * (poopX + poopWidth) && lastTouchY >= canvas.getHeight() * poopY && lastTouchY <= (poopY + poopHeight) * canvas.getHeight()) {
                            new PoopFlyBackThread().start();
                            isTouched = true;
                            disgusting = false;
                            poopFlyBack = true;
                            gettingFoin = true;
                            editor.putBoolean("DISGUSTING",disgusting);
                            editor.putBoolean("POOPFLYBACK",poopFlyBack);
                            editor.apply();
                            lastTouchX = 0;
                            lastTouchY = 0;
                        }
                        if (isTouched && !poopFlyBackTimeIsPassed) {
                            new PoopFlyBackThread().start();
                            poopFlyBackTimeIsPassed = true;
                        }
                        if (poopFlyBackNeedToDrawNow3 && poopFlyBack) {
                            switch (poopFlyBackTime) {
                                case 1:
                                    // Проверяем состояние и подбираем картинку
                                    if (birdBreath1 == bitmapDTSH1) fird = flyBitmapTDSH[2];else if (birdBreath1 == bitmapDH1) fird = flyBitmapDH[2];else if (birdBreath1 == bitmapDS1) fird = flyBitmapDS[2];else if (birdBreath1 == bitmapD1) fird = flyBitmapD[2];else if (birdBreath1 == bitmapH1) fird = flyBitmapH[2];else if (birdBreath1 == bitmapSmile1) fird = flyBitmapSmile[2];else if (birdBreath1 == bitmapSH1) fird = flyBitmapSH[2];else if (birdBreath1 == bitmapDSH1) fird = flyBitmapSDH[2];else if (birdBreath1 == bitmapS1) fird = flyBitmapS[2];else if (birdBreath1 == bitmapDTH1) fird = flyBitmapTDH[2];else if (birdBreath1 == bitmapDTS1) fird = flyBitmapTDS[2];else if (birdBreath1 == bitmapDT1) fird = flyBitmapTD[2];else if (birdBreath1 == bitmapTH1) fird = flyBitmapTH[2];else if (birdBreath1 == bitmapTSH1) fird = flyBitmapTSH[2];else if (birdBreath1 == bitmapTS1) fird = flyBitmapTS[2];else if (birdBreath1 == bitmapT1) fird = flyBitmapT[2];else if (birdBreath1 == bitmapUsual1) fird = flyBitmapUsual[2];
                                    break;
                                case 2:
                                    if (birdBreath1 == bitmapDTSH1) fird = flyBitmapTDSH[1];else if (birdBreath1 == bitmapDH1) fird = flyBitmapDH[1];else if (birdBreath1 == bitmapDS1) fird = flyBitmapDS[1];else if (birdBreath1 == bitmapD1) fird = flyBitmapD[1];else if (birdBreath1 == bitmapH1) fird = flyBitmapH[1];else if (birdBreath1 == bitmapSmile1) fird = flyBitmapSmile[1];else if (birdBreath1 == bitmapSH1) fird = flyBitmapSH[1];else if (birdBreath1 == bitmapDSH1) fird = flyBitmapSDH[1];else if (birdBreath1 == bitmapS1) fird = flyBitmapS[1];else if (birdBreath1 == bitmapDTH1) fird = flyBitmapTDH[1];else if (birdBreath1 == bitmapDTS1) fird = flyBitmapTDS[1];else if (birdBreath1 == bitmapDT1) fird = flyBitmapTD[1];else if (birdBreath1 == bitmapTH1) fird = flyBitmapTH[1];else if (birdBreath1 == bitmapTSH1) fird = flyBitmapTSH[1];else if (birdBreath1 == bitmapTS1) fird = flyBitmapTS[1];else if (birdBreath1 == bitmapT1) fird = flyBitmapT[1];else if (birdBreath1 == bitmapUsual1) fird = flyBitmapUsual[1];
                                    birdX = (float) 715 / 1050;
                                    birdY = (float) 195 / 540;
                                    break;
                                case 3:
                                    if (birdBreath1 == bitmapDTSH1) fird = flyBitmapTDSH[2];else if (birdBreath1 == bitmapDH1) fird = flyBitmapDH[2];else if (birdBreath1 == bitmapDS1) fird = flyBitmapDS[2];else if (birdBreath1 == bitmapD1) fird = flyBitmapD[2];else if (birdBreath1 == bitmapH1) fird = flyBitmapH[2];else if (birdBreath1 == bitmapSmile1) fird = flyBitmapSmile[2];else if (birdBreath1 == bitmapSH1) fird = flyBitmapSH[2];else if (birdBreath1 == bitmapDSH1) fird = flyBitmapSDH[2];else if (birdBreath1 == bitmapS1) fird = flyBitmapS[2];else if (birdBreath1 == bitmapDTH1) fird = flyBitmapTDH[2];else if (birdBreath1 == bitmapDTS1) fird = flyBitmapTDS[2];else if (birdBreath1 == bitmapDT1) fird = flyBitmapTD[2];else if (birdBreath1 == bitmapTH1) fird = flyBitmapTH[2];else if (birdBreath1 == bitmapTSH1) fird = flyBitmapTSH[2];else if (birdBreath1 == bitmapTS1) fird = flyBitmapTS[2];else if (birdBreath1 == bitmapT1) fird = flyBitmapT[2];else if (birdBreath1 == bitmapUsual1) fird = flyBitmapUsual[2];
                                    birdX = (float) 625 / 1050;
                                    birdY = (float) 203 / 540;
                                    break;
                                case 4:
                                    if (birdBreath1 == bitmapDTSH1) fird = flyBitmapTDSH[1];else if (birdBreath1 == bitmapDH1) fird = flyBitmapDH[1];else if (birdBreath1 == bitmapDS1) fird = flyBitmapDS[1];else if (birdBreath1 == bitmapD1) fird = flyBitmapD[1];else if (birdBreath1 == bitmapH1) fird = flyBitmapH[1];else if (birdBreath1 == bitmapSmile1) fird = flyBitmapSmile[1];else if (birdBreath1 == bitmapSH1) fird = flyBitmapSH[1];else if (birdBreath1 == bitmapDSH1) fird = flyBitmapSDH[1];else if (birdBreath1 == bitmapS1) fird = flyBitmapS[1];else if (birdBreath1 == bitmapDTH1) fird = flyBitmapTDH[1];else if (birdBreath1 == bitmapDTS1) fird = flyBitmapTDS[1];else if (birdBreath1 == bitmapDT1) fird = flyBitmapTD[1];else if (birdBreath1 == bitmapTH1) fird = flyBitmapTH[1];else if (birdBreath1 == bitmapTSH1) fird = flyBitmapTSH[1];else if (birdBreath1 == bitmapTS1) fird = flyBitmapTS[1];else if (birdBreath1 == bitmapT1) fird = flyBitmapT[1];else if (birdBreath1 == bitmapUsual1) fird = flyBitmapUsual[1];
                                    birdX = (float) 548 / 1050;
                                    birdY = (float) 176 / 540;
                                    break;
                                case 5:
                                    if (birdBreath1 == bitmapDTSH1) fird = flyBitmapTDSH[2];else if (birdBreath1 == bitmapDH1) fird = flyBitmapDH[2];else if (birdBreath1 == bitmapDS1) fird = flyBitmapDS[2];else if (birdBreath1 == bitmapD1) fird = flyBitmapD[2];else if (birdBreath1 == bitmapH1) fird = flyBitmapH[2];else if (birdBreath1 == bitmapSmile1) fird = flyBitmapSmile[2];else if (birdBreath1 == bitmapSH1) fird = flyBitmapSH[2];else if (birdBreath1 == bitmapDSH1) fird = flyBitmapSDH[2];else if (birdBreath1 == bitmapS1) fird = flyBitmapS[2];else if (birdBreath1 == bitmapDTH1) fird = flyBitmapTDH[2];else if (birdBreath1 == bitmapDTS1) fird = flyBitmapTDS[2];else if (birdBreath1 == bitmapDT1) fird = flyBitmapTD[2];else if (birdBreath1 == bitmapTH1) fird = flyBitmapTH[2];else if (birdBreath1 == bitmapTSH1) fird = flyBitmapTSH[2];else if (birdBreath1 == bitmapTS1) fird = flyBitmapTS[2];else if (birdBreath1 == bitmapT1) fird = flyBitmapT[2];else if (birdBreath1 == bitmapUsual1) fird = flyBitmapUsual[2];
                                    birdX = (float) 419 / 1050;
                                    birdY = (float) 232 / 540;
                                    break;
                                case 6:
                                    // Возвращаемся к преждним настройкам
                                    fird = birdBreath1;
                                    disgusting = false;
                                    poopFlyBack = false;
                                    isTouched = false;
                                    pooping = false;
                                    eatScore = 0;
                                    poopFlyBackTime = 0;
                                    poopFlyTime = 1;
                                    poop = 1;
                                    disgust = 1;
                                    lastTouchX = 0;
                                    lastTouchY = 0;
                                    poopTimeIsPassed = false;
                                    poopFlyTimeIsPassed = false;
                                    poopFlyBackTimeIsPassed = false;
                                    poopFlyNeedToDrawNow = false;
                                    poopFlyBackNeedToDrawNow3 = false;
                                    disgustingTimeIsPassed = false;
                                    disgustingNeedToDrawNow = false;
                                    eatButtonBitmap = eatButtonBitmap2;
                                    sleepButtonBitmap = sleepButtonBitmap2;
                                    playButtonBitmap = playButtonBitmap2;
                                    washButtonBitmap = washButtonBitmap3;
                                    editor.putBoolean("POOPFLYBACK",poopFlyBack);
                                    editor.putBoolean("DISGUSTING",disgusting);
                                    editor.putBoolean("POOPING",pooping);
                                    editor.putInt("TIMEFORPOOP",timeForPoop);
                                    editor.putInt("EATSCORE",0);
                                    editor.apply();
                                    break;
                            }
                        }
                        // Кушать
                        if (lastTouchX >= eatButtonLeft * canvas.getWidth() && lastTouchX <= (eatButtonLeft + ButtonWidth) * canvas.getWidth() && lastTouchY >= eatButtonTop * canvas.getHeight() && lastTouchY <= (eatButtonTop + ButtonHeight) * canvas.getHeight() && eatChecker && !playing && !sleeping && !laying && !flying && !flyingBack && !hitting && !washing && !pooping && !poopFly && !disgusting && !poopFlyBack && !hungryButtonIsPressed) {
                            hungryButtonIsPressed = true;
                            gettingFoin = true;
                            lastTouchY = 0;
                            lastTouchX = 0;
                        }
                        if (!eatingTimeIsPassed && hungryButtonIsPressed) {
                            new EatingThread().start();
                            eating = true;
                            editor.putBoolean("EATING",eating);
                            editor.apply();
                            eatingTimeIsPassed = true;
                        }
                        if (eatingNeedToDrawNow && eating) {
                            if (eat >= 1 && eat <= 10) {
                                birdY = (float) 118 / 540;
                                // Проверяем состояние и подбираем картинку
                                if (birdBreath1 == bitmapDTS1 || birdBreath1 == bitmapDTSH1) fird = eatBitmapDTS[eat];else if (birdBreath1 == bitmapDT1 || birdBreath1 == bitmapDTH1) fird = eatBitmapDT[eat];else if (birdBreath1 == bitmapDS1 || birdBreath1 == bitmapDSH1) fird = eatBitmapDS[eat];else if (birdBreath1 == bitmapTS1 || birdBreath1 == bitmapTSH1) fird = eatBitmapTS[eat];else if (birdBreath1 == bitmapD1 || birdBreath1 == bitmapDH1) fird = eatBitmapD[eat];else if (birdBreath1 == bitmapS1 || birdBreath1 == bitmapSH1) fird = eatBitmapS[eat];else if (birdBreath1 == bitmapT1 || birdBreath1 == bitmapTH1) fird = eatBitmapT[eat];else if (birdBreath1 == bitmapUsual1 || birdBreath1 == bitmapH1) fird = eatBitmap[eat];else if (birdBreath1 == bitmapSmile1) fird = eatBitmapSmile[eat];
                            }
                            if (eat == 9) {
                                if (eatOnes == 0) {
                                    food *= (hungryRight2 - hungryLeft);
                                    if (hungryRight + food > hungryRight2) {
                                        hungryRight = hungryRight2;
                                    } else {
                                        hungryRight += food;
                                    }
                                    editor.putFloat("HUNGRY", hungryRight);
                                    editor.apply();
                                    food /= (hungryRight2 - hungryLeft);
                                    eatOnes = 1;
                                }
                            }
                            if (eat == 11) {
                                // Возвращаемся к преждним настройкам

                                eating = false;
                                eat = 0;
                                eatChecker = false;
                                checkEatButton = true;
                                eatOnes = 0;
                                hungryButtonIsPressed = false;
                                fird = birdBreath1;
                                birdY = (float) 232 / 540;
                                eatTimer = 10;
                                eatButtonBitmap = eatDarkButtonBitmap[eatTimer];
                                eatScore++;
                                lastTouchX = 0;
                                lastTouchY = 0;
                                editor.putBoolean("EATING",eating);
                                editor.putBoolean("EATCHECKER",eatChecker);
                                editor.putInt("EATSCORE",eatScore);
                                editor.putInt("EATTIMER",eatTimer);
                                editor.apply();
                            }
                        }
                        // Радоваться
                        if (lastTouchX >= playButtonLeft * canvas.getWidth() && lastTouchX <= (playButtonLeft + ButtonWidth) * canvas.getWidth() && lastTouchY >= playButtonTop * canvas.getHeight() && lastTouchY <= (playButtonTop + ButtonHeight) * canvas.getHeight() && playChecker && !playing && !eating && !sleeping && !hitting && !washing && !pooping && !poopFly && !disgusting && !poopFlyBack && !happyButtonIsTouched) {
                            happyButtonIsTouched = true;
                            gettingFoin = true;
                            isSinging = true;
                            lastTouchX = 0;
                            lastTouchY = 0;
                        }
                        if (isSinging && happyButtonIsTouched) {
                            mediaPlayerHappy.start();
                            isSinging = false;
                        }
                        if (!playingTimeIsPassed && happyButtonIsTouched) {
                            new PlayingThread().start();
                            playing = true;
                            playingTimeIsPassed = true;
                            editor.putBoolean("PLAYING",playing);
                            editor.apply();
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
                                fird = playBitmapDT[play];
                            else if (birdBreath1 == bitmapD1 || birdBreath1 == bitmapDH1 || birdBreath1 == bitmapDS1 || birdBreath1 == bitmapDSH1)
                                fird = playBitmapD[play];
                            else if (birdBreath1 == bitmapT1 || birdBreath1 == bitmapTH1 || birdBreath1 == bitmapTS1 || birdBreath1 == bitmapTSH1)
                                fird = playBitmapT[play];
                            else if (birdBreath1 == bitmapUsual1 || birdBreath1 == bitmapH1 || birdBreath1 == bitmapS1 || birdBreath1 == bitmapSmile1 || birdBreath1 == bitmapSH1)
                                fird = playBitmap[play];
                            if (play == 20) {
                                // Возвращаемся к преждним настройкам
                                playing = false;
                                playChecker = false;
                                checkPlayButton = true;
                                play = 0;
                                happyButtonIsTouched = false;
                                playButtonBitmap = playDarkButtonBitmap[15];
                                playTimer = 15;
                                mediaPlayerHappy.stop();
                                mediaPlayerHappy.prepare();
                                lastTouchX = 0;
                                lastTouchY = 0;
                                editor.putInt("PLAYTIMER",playTimer);
                                editor.putBoolean("PLAYCHECKER",playChecker);
                                editor.putBoolean("PLAYING",playing);
                                editor.apply();
                            }
                        }
                        // Спать
                        if (lastTouchX >= sleepButtonLeft * canvas.getWidth() && lastTouchX <= (sleepButtonLeft + ButtonWidth) * canvas.getWidth() && lastTouchY >= sleepButtonTop * canvas.getHeight() && lastTouchY <= (sleepButtonTop + ButtonHeight) * canvas.getHeight() && !sleepFinished && sleepChecker && !playing && !eating && !hitting && !washing && !pooping && !poopFly && !disgusting && !poopFlyBack && !sleepButtonIsPressed && !sleeping) {
                            sleepButtonIsPressed = true;
                            gettingFoin = true;
                            lastTouchX = 0;
                            lastTouchY = 0;
                        }
                        if (!flyingToBedTimeIsPassed && sleepButtonIsPressed) {
                            new FlyingThread().start();
                            flying = true;
                            sleeping = true;
                            flyingToBedTimeIsPassed = true;
                            editor.putBoolean("FLYING",flying);
                            editor.putBoolean("SLEEPING",sleeping);
                            editor.apply();
                        }
                        if (flyingToBedNeedToDrawNow && flying) {
                            switch (sleep) {
                                case 1:
                                    if (birdBreath1 == bitmapDTSH1) fird = flyBitmapTDSH[2];else if (birdBreath1 == bitmapDH1) fird = flyBitmapDH[2];else if (birdBreath1 == bitmapDS1) fird = flyBitmapDS[2];else if (birdBreath1 == bitmapD1) fird = flyBitmapD[2];else if (birdBreath1 == bitmapH1) fird = flyBitmapH[2];else if (birdBreath1 == bitmapSmile1) fird = flyBitmapSmile[2];else if (birdBreath1 == bitmapSH1) fird = flyBitmapSH[2];else if (birdBreath1 == bitmapDSH1) fird = flyBitmapSDH[2];else if (birdBreath1 == bitmapS1) fird = flyBitmapS[2];else if (birdBreath1 == bitmapDTH1) fird = flyBitmapTDH[2];else if (birdBreath1 == bitmapDTS1) fird = flyBitmapTDS[2];else if (birdBreath1 == bitmapDT1) fird = flyBitmapTD[2];else if (birdBreath1 == bitmapTH1) fird = flyBitmapTH[2];else if (birdBreath1 == bitmapTSH1) fird = flyBitmapTSH[2];else if (birdBreath1 == bitmapTS1) fird = flyBitmapTS[2];else if (birdBreath1 == bitmapT1) fird = flyBitmapT[2];else if (birdBreath1 == bitmapUsual1) fird = flyBitmapUsual[2];
                                    break;
                                case 2:
                                    if (birdBreath1 == bitmapDTSH1) fird = flyBitmapTDSH[1];else if (birdBreath1 == bitmapDH1) fird = flyBitmapDH[1];else if (birdBreath1 == bitmapDS1) fird = flyBitmapDS[1];else if (birdBreath1 == bitmapD1) fird = flyBitmapD[1];else if (birdBreath1 == bitmapH1) fird = flyBitmapH[1];else if (birdBreath1 == bitmapSmile1) fird = flyBitmapSmile[1];else if (birdBreath1 == bitmapSH1) fird = flyBitmapSH[1];else if (birdBreath1 == bitmapDSH1) fird = flyBitmapSDH[1];else if (birdBreath1 == bitmapS1) fird = flyBitmapS[1];else if (birdBreath1 == bitmapDTH1) fird = flyBitmapTDH[1];else if (birdBreath1 == bitmapDTS1) fird = flyBitmapTDS[1];else if (birdBreath1 == bitmapDT1) fird = flyBitmapTD[1];else if (birdBreath1 == bitmapTH1) fird = flyBitmapTH[1];else if (birdBreath1 == bitmapTSH1) fird = flyBitmapTSH[1];else if (birdBreath1 == bitmapTS1) fird = flyBitmapTS[1];else if (birdBreath1 == bitmapT1) fird = flyBitmapT[1];else if (birdBreath1 == bitmapUsual1) fird = flyBitmapUsual[1];
                                    birdX = (float) 353 / 1050;
                                    birdY = (float) 238 / 540;
                                    break;
                                case 3:
                                    if (birdBreath1 == bitmapDTSH1) fird = flyBitmapTDSH[2];else if (birdBreath1 == bitmapDH1) fird = flyBitmapDH[2];else if (birdBreath1 == bitmapDS1) fird = flyBitmapDS[2];else if (birdBreath1 == bitmapD1) fird = flyBitmapD[2];else if (birdBreath1 == bitmapH1) fird = flyBitmapH[2];else if (birdBreath1 == bitmapSmile1) fird = flyBitmapSmile[2];else if (birdBreath1 == bitmapSH1) fird = flyBitmapSH[2];else if (birdBreath1 == bitmapDSH1) fird = flyBitmapSDH[2];else if (birdBreath1 == bitmapS1) fird = flyBitmapS[2];else if (birdBreath1 == bitmapDTH1) fird = flyBitmapTDH[2];else if (birdBreath1 == bitmapDTS1) fird = flyBitmapTDS[2];else if (birdBreath1 == bitmapDT1) fird = flyBitmapTD[2];else if (birdBreath1 == bitmapTH1) fird = flyBitmapTH[2];else if (birdBreath1 == bitmapTSH1) fird = flyBitmapTSH[2];else if (birdBreath1 == bitmapTS1) fird = flyBitmapTS[2];else if (birdBreath1 == bitmapT1) fird = flyBitmapT[2];else if (birdBreath1 == bitmapUsual1) fird = flyBitmapUsual[2];
                                    birdX = (float) 287 / 1050;
                                    birdY = (float) 226 / 540;
                                    break;
                                case 4:
                                    if (birdBreath1 == bitmapDTSH1) fird = flyBitmapTDSH[1];else if (birdBreath1 == bitmapDH1) fird = flyBitmapDH[1];else if (birdBreath1 == bitmapDS1) fird = flyBitmapDS[1];else if (birdBreath1 == bitmapD1) fird = flyBitmapD[1];else if (birdBreath1 == bitmapH1) fird = flyBitmapH[1];else if (birdBreath1 == bitmapSmile1) fird = flyBitmapSmile[1];else if (birdBreath1 == bitmapSH1) fird = flyBitmapSH[1];else if (birdBreath1 == bitmapDSH1) fird = flyBitmapSDH[1];else if (birdBreath1 == bitmapS1) fird = flyBitmapS[1];else if (birdBreath1 == bitmapDTH1) fird = flyBitmapTDH[1];else if (birdBreath1 == bitmapDTS1) fird = flyBitmapTDS[1];else if (birdBreath1 == bitmapDT1) fird = flyBitmapTD[1];else if (birdBreath1 == bitmapTH1) fird = flyBitmapTH[1];else if (birdBreath1 == bitmapTSH1) fird = flyBitmapTSH[1];else if (birdBreath1 == bitmapTS1) fird = flyBitmapTS[1];else if (birdBreath1 == bitmapT1) fird = flyBitmapT[1];else if (birdBreath1 == bitmapUsual1) fird = flyBitmapUsual[1];
                                    birdX = (float) 230 / 1050;
                                    birdY = (float) 232 / 540;
                                    break;
                                case 5:
                                    if (birdBreath1 == bitmapDTSH1) fird = flyBitmapTDSH[2];else if (birdBreath1 == bitmapDH1) fird = flyBitmapDH[2];else if (birdBreath1 == bitmapDS1) fird = flyBitmapDS[2];else if (birdBreath1 == bitmapD1) fird = flyBitmapD[2];else if (birdBreath1 == bitmapH1) fird = flyBitmapH[2];else if (birdBreath1 == bitmapSmile1) fird = flyBitmapSmile[2];else if (birdBreath1 == bitmapSH1) fird = flyBitmapSH[2];else if (birdBreath1 == bitmapDSH1) fird = flyBitmapSDH[2];else if (birdBreath1 == bitmapS1) fird = flyBitmapS[2];else if (birdBreath1 == bitmapDTH1) fird = flyBitmapTDH[2];else if (birdBreath1 == bitmapDTS1) fird = flyBitmapTDS[2];else if (birdBreath1 == bitmapDT1) fird = flyBitmapTD[2];else if (birdBreath1 == bitmapTH1) fird = flyBitmapTH[2];else if (birdBreath1 == bitmapTSH1) fird = flyBitmapTSH[2];else if (birdBreath1 == bitmapTS1) fird = flyBitmapTS[2];else if (birdBreath1 == bitmapT1) fird = flyBitmapT[2];else if (birdBreath1 == bitmapUsual1) fird = flyBitmapUsual[2];
                                    birdX = (float) 159 / 1050;
                                    birdY = (float) 193 / 540;
                                    break;
                                case 6:
                                    fird = birdBreath1;
                                    birdX = (float) 48 / 1050;
                                    birdY = (float) 218 / 540;
                                    break;
                                case 7:
                                    birdX = (float) 60 / 1050;
                                    birdY = (float) 207 / 540;
                                    laying = true;
                                    flying = false;
                                    editor.putBoolean("FLYING",flying);
                                    editor.putBoolean("LAYING",laying);
                                    editor.apply();
                                    if (birdBreath1 == bitmapDTSH1 || birdBreath1 == bitmapDSH1) fird = sleepDSH1;else if (birdBreath1 == bitmapDTS1 || birdBreath1 == bitmapDS1) fird = sleepDS1;else if (birdBreath1 == bitmapDTH1 || birdBreath1 == bitmapDH1) fird = sleepDH1;else if (birdBreath1 == bitmapDT1 || birdBreath1 == bitmapD1) fird = sleepD1;else if (birdBreath1 == bitmapTSH1 || birdBreath1 == bitmapSH1) fird = sleepSH1;else if (birdBreath1 == bitmapTS1 || birdBreath1 == bitmapS1) fird = sleepS1;else if (birdBreath1 == bitmapTH1 || birdBreath1 == bitmapH1) fird = sleepH1;else if (birdBreath1 == bitmapT1 || birdBreath1 == bitmapUsual1) fird = sleepUsual1;else if (birdBreath1 == bitmapSmile1) fird = sleepSmile1;
                                    break;
                            }
                        }
                        // Таймер дыхания во время сна
                        if (laying) {
                            if (!timeIsPassedSleep1 && !checkSleep) {
                                new ThreadSleepBird1().start();
                                timeIsPassedSleep1 = true;
                            }
                            if (needToDrawNowSleep1 && !checkSleep) {
                                if (birdBreath1 == bitmapDTSH1 || birdBreath1 == bitmapDSH1) fird = sleepDSH1;else if (birdBreath1 == bitmapDTS1 || birdBreath1 == bitmapDS1) fird = sleepDS1;else if (birdBreath1 == bitmapDTH1 || birdBreath1 == bitmapDH1) fird = sleepDH1;else if (birdBreath1 == bitmapDT1 || birdBreath1 == bitmapD1) fird = sleepD1;else if (birdBreath1 == bitmapTSH1 || birdBreath1 == bitmapSH1) fird = sleepSH1;else if (birdBreath1 == bitmapTS1 || birdBreath1 == bitmapS1) fird = sleepS1;else if (birdBreath1 == bitmapTH1 || birdBreath1 == bitmapH1) fird = sleepH1;else if (birdBreath1 == bitmapT1 || birdBreath1 == bitmapUsual1) fird = sleepUsual1;else if (birdBreath1 == bitmapSmile1) fird = sleepSmile1;
                            }
                            if (!timeIsPassedSleep2 && checkSleep) {
                                new ThreadSleepBird2().start();
                                timeIsPassedSleep2 = true;
                            }
                            if (needToDrawNowSleep2 && checkSleep) {
                                if (birdBreath1 == bitmapDTSH1 || birdBreath1 == bitmapDSH1) fird = sleepDSH2;else if (birdBreath1 == bitmapDTS1 || birdBreath1 == bitmapDS1) fird = sleepDS2;else if (birdBreath1 == bitmapDTH1 || birdBreath1 == bitmapDH1) fird = sleepDH2;else if (birdBreath1 == bitmapDT1 || birdBreath1 == bitmapD1) fird = sleepD2;else if (birdBreath1 == bitmapTSH1 || birdBreath1 == bitmapSH1) fird = sleepSH2;else if (birdBreath1 == bitmapTS1 || birdBreath1 == bitmapS1) fird = sleepS2;else if (birdBreath1 == bitmapTH1 || birdBreath1 == bitmapH1) fird = sleepH2;else if (birdBreath1 == bitmapT1 || birdBreath1 == bitmapUsual1) fird = sleepUsual2;else if (birdBreath1 == bitmapSmile1) fird = sleepSmile2;
                            }
                            if (!sleepingTimeIsPassed) {
                                new Sleep().start();
                                sleepingTimeIsPassed = true;
                            }
                            if (sleepingNeedToDrawNow) {
                                qun *= (sleepRight2 - sleepLeft);
                                if (sleepRight + qun > sleepRight2) {
                                    sleepRight = sleepRight2;
                                    laying = false;
                                    editor.putFloat("SLEEP", sleepRight);
                                    editor.putBoolean("LAYING",laying);
                                    editor.apply();
                                } else {
                                    sleepRight += qun;
                                    editor.putFloat("SLEEP", sleepRight);
                                    editor.apply();
                                }
                                qun /= (sleepRight2 - sleepLeft);
                                sleepingNeedToDrawNow = false;
                            }
                        }
                            if (!flyingBackTimeIsPassed && !laying && sleeping && !flying) {
                                new FlyingBackThread().start();
                                flyingBackTimeIsPassed = true;
                                flyingBack = true;
                                editor.putBoolean("FLYINGBACK",flyingBack);
                                editor.apply();
                            }
                            if (flyingBackNeedToDrawNow && flyingBack) {
                                switch (flyBack) {
                                    case 1:
                                        fird = birdBreath1;
                                        birdX = (float) 48 / 1050;
                                        birdY = (float) 218 / 540;
                                        break;
                                    case 2:
                                        if (birdBreath1 == bitmapDTSH1) fird = flyBitmapTDSH[4];else if (birdBreath1 == bitmapDH1) fird = flyBitmapDH[4];else if (birdBreath1 == bitmapDS1) fird = flyBitmapDS[4];else if (birdBreath1 == bitmapD1) fird = flyBitmapD[4];else if (birdBreath1 == bitmapH1) fird = flyBitmapH[4];else if (birdBreath1 == bitmapSmile1) fird = flyBitmapSmile[4];else if (birdBreath1 == bitmapSH1) fird = flyBitmapSH[4];else if (birdBreath1 == bitmapDSH1) fird = flyBitmapSDH[4];else if (birdBreath1 == bitmapS1) fird = flyBitmapS[4];else if (birdBreath1 == bitmapDTH1) fird = flyBitmapTDH[4];else if (birdBreath1 == bitmapDTS1) fird = flyBitmapTDS[4];else if (birdBreath1 == bitmapDT1) fird = flyBitmapTD[4];else if (birdBreath1 == bitmapTH1) fird = flyBitmapTH[4];else if (birdBreath1 == bitmapTSH1) fird = flyBitmapTSH[4];else if (birdBreath1 == bitmapTS1) fird = flyBitmapTS[4];else if (birdBreath1 == bitmapT1) fird = flyBitmapT[4];else if (birdBreath1 == bitmapUsual1) fird = flyBitmapUsual[4];
                                        birdX = (float) 159 / 1050;
                                        birdY = (float) 193 / 540;
                                        break;
                                    case 3:
                                        if (birdBreath1 == bitmapDTSH1) fird = flyBitmapTDSH[3];else if (birdBreath1 == bitmapDH1) fird = flyBitmapDH[3];else if (birdBreath1 == bitmapDS1) fird = flyBitmapDS[3];else if (birdBreath1 == bitmapD1) fird = flyBitmapD[3];else if (birdBreath1 == bitmapH1) fird = flyBitmapH[3];else if (birdBreath1 == bitmapSmile1) fird = flyBitmapSmile[3];else if (birdBreath1 == bitmapSH1) fird = flyBitmapSH[3];else if (birdBreath1 == bitmapDSH1) fird = flyBitmapSDH[3];else if (birdBreath1 == bitmapS1) fird = flyBitmapS[3];else if (birdBreath1 == bitmapDTH1) fird = flyBitmapTDH[3];else if (birdBreath1 == bitmapDTS1) fird = flyBitmapTDS[3];else if (birdBreath1 == bitmapDT1) fird = flyBitmapTD[3];else if (birdBreath1 == bitmapTH1) fird = flyBitmapTH[3];else if (birdBreath1 == bitmapTSH1) fird = flyBitmapTSH[3];else if (birdBreath1 == bitmapTS1) fird = flyBitmapTS[3];else if (birdBreath1 == bitmapT1) fird = flyBitmapT[3];else if (birdBreath1 == bitmapUsual1) fird = flyBitmapUsual[3];
                                        birdX = (float) 230 / 1050;
                                        birdY = (float) 232 / 540;
                                        break;
                                    case 4:
                                        if (birdBreath1 == bitmapDTSH1) fird = flyBitmapTDSH[4];else if (birdBreath1 == bitmapDH1) fird = flyBitmapDH[4];else if (birdBreath1 == bitmapDS1) fird = flyBitmapDS[4];else if (birdBreath1 == bitmapD1) fird = flyBitmapD[4];else if (birdBreath1 == bitmapH1) fird = flyBitmapH[4];else if (birdBreath1 == bitmapSmile1) fird = flyBitmapSmile[4];else if (birdBreath1 == bitmapSH1) fird = flyBitmapSH[4];else if (birdBreath1 == bitmapDSH1) fird = flyBitmapSDH[4];else if (birdBreath1 == bitmapS1) fird = flyBitmapS[4];else if (birdBreath1 == bitmapDTH1) fird = flyBitmapTDH[4];else if (birdBreath1 == bitmapDTS1) fird = flyBitmapTDS[4];else if (birdBreath1 == bitmapDT1) fird = flyBitmapTD[4];else if (birdBreath1 == bitmapTH1) fird = flyBitmapTH[4];else if (birdBreath1 == bitmapTSH1) fird = flyBitmapTSH[4];else if (birdBreath1 == bitmapTS1) fird = flyBitmapTS[4];else if (birdBreath1 == bitmapT1) fird = flyBitmapT[4];else if (birdBreath1 == bitmapUsual1) fird = flyBitmapUsual[4];
                                        birdX = (float) 287 / 1050;
                                        birdY = (float) 226 / 540;
                                        break;
                                    case 5:
                                        if (birdBreath1 == bitmapDTSH1) fird = flyBitmapTDSH[3];else if (birdBreath1 == bitmapDH1) fird = flyBitmapDH[3];else if (birdBreath1 == bitmapDS1) fird = flyBitmapDS[3];else if (birdBreath1 == bitmapD1) fird = flyBitmapD[3];else if (birdBreath1 == bitmapH1) fird = flyBitmapH[3];else if (birdBreath1 == bitmapSmile1) fird = flyBitmapSmile[3];else if (birdBreath1 == bitmapSH1) fird = flyBitmapSH[3];else if (birdBreath1 == bitmapDSH1) fird = flyBitmapSDH[3];else if (birdBreath1 == bitmapS1) fird = flyBitmapS[3];else if (birdBreath1 == bitmapDTH1) fird = flyBitmapTDH[3];else if (birdBreath1 == bitmapDTS1) fird = flyBitmapTDS[3];else if (birdBreath1 == bitmapDT1) fird = flyBitmapTD[3];else if (birdBreath1 == bitmapTH1) fird = flyBitmapTH[3];else if (birdBreath1 == bitmapTSH1) fird = flyBitmapTSH[3];else if (birdBreath1 == bitmapTS1) fird = flyBitmapTS[3];else if (birdBreath1 == bitmapT1) fird = flyBitmapT[3];else if (birdBreath1 == bitmapUsual1) fird = flyBitmapUsual[3];
                                        birdX = (float) 353 / 1050;
                                        birdY = (float) 238 / 540;
                                        break;
                                    case 6:
                                        if (birdBreath1 == bitmapDTSH1) fird = flyBitmapTDSH[4];else if (birdBreath1 == bitmapDH1) fird = flyBitmapDH[4];else if (birdBreath1 == bitmapDS1) fird = flyBitmapDS[4];else if (birdBreath1 == bitmapD1) fird = flyBitmapD[4];else if (birdBreath1 == bitmapH1) fird = flyBitmapH[4];else if (birdBreath1 == bitmapSmile1) fird = flyBitmapSmile[4];else if (birdBreath1 == bitmapSH1) fird = flyBitmapSH[4];else if (birdBreath1 == bitmapDSH1) fird = flyBitmapSDH[4];else if (birdBreath1 == bitmapS1) fird = flyBitmapS[4];else if (birdBreath1 == bitmapDTH1) fird = flyBitmapTDH[4];else if (birdBreath1 == bitmapDTS1) fird = flyBitmapTDS[4];else if (birdBreath1 == bitmapDT1) fird = flyBitmapTD[4];else if (birdBreath1 == bitmapTH1) fird = flyBitmapTH[4];else if (birdBreath1 == bitmapTSH1) fird = flyBitmapTSH[4];else if (birdBreath1 == bitmapTS1) fird = flyBitmapTS[4];else if (birdBreath1 == bitmapT1) fird = flyBitmapT[4];else if (birdBreath1 == bitmapUsual1) fird = flyBitmapUsual[4];
                                        birdX = (float) 419 / 1050;
                                        birdY = (float) 232 / 540;
                                        break;
                                    case 7:
                                        // Возвращаемся к преждним настройкам
                                        fird = birdBreath1;
                                        birdX = (float) 419 / 1050;
                                        birdY = (float) 232 / 540;
                                        flyingBack = false;
                                        flyBack = 0;
                                        sleeping = false;
                                        sleep = 0;
                                        sleepButtonIsPressed = false;
                                        flyingToBedTimeIsPassed = false;
                                        checkSleepButton = true;
                                        sleepChecker = false;
                                        sleepTimer = 60;
                                        sleepButtonBitmap = sleepDarkButtonBitmap[sleepTimer];
                                        editor.putBoolean("SLEEPCHECKER",sleepChecker);
                                        editor.putBoolean("FLYINGBACK",flyingBack);
                                        editor.putBoolean("SLEEPING",sleeping);
                                        editor.apply();
                                        break;
                                }
                        }
                        // Искупаться
                        if (lastTouchX >= dirtButtonLeft * canvas.getWidth() && lastTouchX <= (dirtButtonLeft + ButtonWidth) * canvas.getWidth() && lastTouchY >= dirtButtonTop * canvas.getHeight() && lastTouchY <= (dirtButtonTop + ButtonHeight) * canvas.getHeight() && (dirtRight - dirtLeft <= dirtWidth / 2.) && !playing && !eating && !sleeping && !laying && !flying && !flyingBack && !hitting && !pooping && !poopFly && !disgusting && !poopFlyBack && !washButtonIsPressed) {
                            washButtonIsPressed = true;
                            gettingFoin = true;
                            isSinging = true;
                            lastTouchX = 0;
                            lastTouchY = 0;
                        }
                        if(isSinging && washButtonIsPressed) {
                            mediaPlayerWash.start();
                            isSinging = false;
                        }
                        if (!washingTimeIsPassed && washButtonIsPressed) {
                            new WashingThread().start();
                            washing = true;
                            editor.putBoolean("WASHING",washing);
                            editor.apply();
                            washingTimeIsPassed = true;
                        }
                        if (washingNeedToDrawNow && washing && wash <= 49) {
                            birdX = (float) 414 / 1050;
                            birdY = (float) 31 / 540;
                            fird = washBitmap[wash];
                            if (wash == 31) dirtRight = dirtRight2;
                            editor.putFloat("DIRT", dirtRight);
                            editor.apply();
                            if (wash == 49) {
                                washing = false;
                                wash = 1;
                                washingTimeIsPassed = false;
                                washButtonIsPressed = false;
                                fird = birdBreath1;
                                birdX = (float) 419 / 1050;
                                birdY = (float) 232 / 540;
                                lastTouchX = 0;
                                lastTouchY = 0;
                                editor.putBoolean("WASHING",washing);
                                editor.apply();
                                mediaPlayerWash.stop();
                                mediaPlayerWash.prepare();
                            }
                        }

                        // Встряхнуться
                        if (lastTouchX >= (birdX * canvas.getWidth()) && lastTouchX <= (birdX + birdWidth) * (canvas.getWidth()) && lastTouchY >= (birdY * canvas.getHeight()) && lastTouchY <= (birdY + birdHeight) * (canvas.getHeight()) && !eating && !playing && !flying && !sleeping && !laying && !flyingBack && !washing && !pooping && !poopFly && !disgusting && !poopFlyBack && !birdIsPunched) {
                            birdIsPunched = true;
                            isSinging = true;
                        }
                        if (isSinging && birdIsPunched) {
                            mediaPlayerHit.start();
                            isSinging = false;
                        }
                        if (!hitTimeIsPassed && birdIsPunched) {
                            new HitThread().start();
                            hitting = true;
                            hitTimeIsPassed = true;
                        }
                        if (hitNeedToDrawNow && hitting) {
                            if (birdBreath1 == bitmapDH1 || birdBreath1 == bitmapDTH1 || birdBreath1 == bitmapDTSH1 || birdBreath1 == bitmapDSH1) fird = hitBitmapDH[hit];else if (birdBreath1 == bitmapD1 || birdBreath1 == bitmapDT1 || birdBreath1 == bitmapDTS1 || birdBreath1 == bitmapDS1) fird = hitBitmapD[hit];else if (birdBreath1 == bitmapH1 || birdBreath1 == bitmapTH1 || birdBreath1 == bitmapTSH1 || birdBreath1 == bitmapSH1) fird = hitBitmapH[hit];else if (birdBreath1 == bitmapUsual1 || birdBreath1 == bitmapS1 || birdBreath1 == bitmapT1 || birdBreath1 == bitmapTS1 || birdBreath1 == bitmapSmile1) fird = hitBitmap[hit];
                            if (hit >= 14) {
                                // Возвращаемся к преждним настройкам
                                hitting = false;
                                hit = 1;
                                birdIsPunched = false;
                                hitTimeIsPassed = false;
                                lastTouchY = 0;
                                lastTouchX = 0;
                                if(mediaPlayerHit != null) {
                                    mediaPlayerHit.stop();
                                    mediaPlayerHit.prepare();
                                }
                            }
                        }
                        //  Магазин
                        if (lastTouchX >= (shopButtonLeft* canvas.getWidth()) && lastTouchX <= (shopButtonLeft + shopButtonWidth)* (canvas.getWidth()) && lastTouchY >= (shopButtonTop* canvas.getHeight()) && lastTouchY <= (shopButtonTop + shopButtonHeight)*(canvas.getHeight())) {
                            startShopActivity();
                            lastTouchY = 0;
                            lastTouchX = 0;
                        }
                        canvas.drawBitmap(shopButton, (float) canvas.getWidth() * shopButtonLeft, (float) canvas.getHeight() * shopButtonTop, paint);

                        //lvl+
                        if(lvlCheckTime <= 0 && birdBreath1 == bitmapSmile1 && !gettingLevel) {
                            lvlCheckTime = 60;
                            gettingLevel = true;
                            lvlCheck =  true;
                            editor.putInt("LVLCHECKTIME",lvlCheckTime);
                            editor.apply();
                        }
                        if(lvlCheck) {
                            if(m1 == 1) {
                                lvl *= (levelRight2 - levelLeft);
                                levelRight3 = Math.min(levelRight + lvl, levelRight2);
                                m1 = 0;
                                lvl /= (levelRight2 - levelLeft);
                            }
                            if(levelRight > levelRight2) {
                                 levelRight = levelLeft;
                                 level++;
                                 lvlCheck = false;
                                gettingLevel = false;
                                gettingFoin = true;
                                 editor.putInt("LEVEL",level);
                                editor.apply();
                                m1 = 1;
                            }
                            if(levelRight <= levelRight3) {
                                levelRight+=(float)(1. / (canvas.getWidth()));
                                editor.putFloat("LEVELRIGHT",levelRight);
                                editor.apply();
                            }
                            else {
                                lvlCheck = false;
                                gettingLevel =false;
                                m1 = 1;
                            }
                        }
                        // Рисуем птичку
                        canvas.drawBitmap(fird, (float) canvas.getWidth() * birdX, (float) canvas.getHeight() * birdY, paint);
                        paint.setTextSize(100);
                    }
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } finally {
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }

        }
    }
    public  void stopMediaPlayer() {
        if (mediaPlayerHappy != null) {
            mediaPlayerHappy.stop();
            mediaPlayerHappy.release();
            mediaPlayerHappy = null;
        }
        if(mediaPlayerWash != null) {
            mediaPlayerWash.stop();
            mediaPlayerWash.release();
            mediaPlayerWash = null;
        }
        if(mediaPlayerHit != null) {
            mediaPlayerHit.stop();
            mediaPlayerHit.release();
            mediaPlayerHit = null;
        }
    }
    public void startShopActivity(){
        Intent intent = new Intent(((Activity) context), ShopSkin.class);
        intent.putExtra("foin", foin + "");
        context.startActivity(intent);
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
        editor.apply();
    }
    private void changeStateAfterStart(float stateRight, float stateLeft, float stateWidth, double stateChangeValue,int stateChecker) {
        if (stateRight - stateChangeValue *stateWidth * timePassed >=stateLeft && timePassed > 0) {
            stateChangeValue *=stateWidth * timePassed;
            stateRight -= stateChangeValue;
            stateChangeValue /= (stateWidth * timePassed);
        }
        if ((stateRight  >=  stateLeft && stateRight - stateChangeValue *stateWidth * timePassed < stateLeft)||(stateRight<stateLeft) || timePassed < 0) {
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
        if(!sleeping) {
        if (stateChecker == 3) {
                sleepRight = stateRight;
                editor.putFloat("SLEEP", stateRight);
            }
        }
        if (stateChecker == 4) {
            happyRight = stateRight;
            editor.putFloat("HAPPY", stateRight);
        }
        editor.apply();
    }
    private void checkFirdState() {
        if (hungryRight - hungryLeft < hungryWidth / 2. && dirtRight - dirtLeft < dirtWidth / 2. && happyRight - happyLeft < happyWidth / 3. && sleepRight - sleepLeft < sleepWidth / 2.) {
            birdBreath1 = bitmapDTSH1;
            birdBreath2 = bitmapDTSH2;
        } else if (dirtRight - dirtLeft < dirtWidth / 2. && happyRight - happyLeft < happyWidth / 3. && sleepRight - sleepLeft < sleepWidth / 2.) {
            birdBreath1 = bitmapDTS1;
            birdBreath2 = bitmapDTS2;
        } else if (hungryRight - hungryLeft < hungryWidth / 2. && happyRight - happyLeft < happyWidth / 3. && sleepRight - sleepLeft < sleepWidth / 2.) {
            birdBreath1 = bitmapTSH1;
            birdBreath2 = bitmapTSH2;
        } else if (hungryRight - hungryLeft < hungryWidth / 2. && dirtRight - dirtLeft < dirtWidth / 2. && sleepRight - sleepLeft < sleepWidth / 2.) {
            birdBreath1 = bitmapDTH1;
            birdBreath2 = bitmapDTH2;
        } else if (hungryRight - hungryLeft < hungryWidth / 2. && dirtRight - dirtLeft < dirtWidth / 2. && happyRight - happyLeft < happyWidth / 3.) {
            birdBreath1 = bitmapDSH1;
            birdBreath2 = bitmapDSH2;
        } else if (hungryRight - hungryLeft < hungryWidth / 2. && dirtRight - dirtLeft < dirtWidth / 2.) {
            birdBreath1 = bitmapDH1;
            birdBreath2 = bitmapDH2;
        } else if (dirtRight - dirtLeft < dirtWidth / 2. && happyRight - happyLeft < happyWidth / 3.) {
            birdBreath1 = bitmapDS1;
            birdBreath2 = bitmapDS2;
        } else if (dirtRight - dirtLeft < dirtWidth / 2. && sleepRight - sleepLeft < sleepWidth / 2.) {
            birdBreath1 = bitmapDT1;
            birdBreath2 = bitmapDT2;
        } else if (hungryRight - hungryLeft < hungryWidth / 2. && happyRight - happyLeft < happyWidth / 3.) {
            birdBreath1 = bitmapSH1;
            birdBreath2 = bitmapSH2;
        } else if (sleepRight - sleepLeft < sleepWidth / 2. && hungryRight - hungryLeft < hungryWidth / 2.) {
            birdBreath1 = bitmapTH1;
            birdBreath2 = bitmapTH2;
        } else if (sleepRight - sleepLeft < sleepWidth / 2. && happyRight - happyLeft < happyWidth / 3.) {
            birdBreath1 = bitmapTS1;
            birdBreath2 = bitmapTS2;
        } else if (dirtRight - dirtLeft < dirtWidth / 2.) {
            birdBreath1 = bitmapD1;
            birdBreath2 = bitmapD2;
        } else if (sleepRight - sleepLeft < sleepWidth / 2.) {
            birdBreath1 = bitmapT1;
            birdBreath2 = bitmapT2;
        } else if (happyRight - happyLeft < happyWidth / 3.) {
            birdBreath1 = bitmapS1;
            birdBreath2 = bitmapS2;
        } else if (hungryRight - hungryLeft < hungryWidth / 2.) {
            birdBreath1 = bitmapH1;
            birdBreath2 = bitmapH2;
        } else if (happyRight - happyLeft < (2.) * happyWidth / 3.) {
            birdBreath1 = bitmapUsual1;
            birdBreath2 = bitmapUsual2;
        } else {
            birdBreath1 = bitmapSmile1;
            birdBreath2 = bitmapSmile2;
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
                    editor.putInt("EATTIMER",eatTimer);
                    editor.apply();
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
                if(playTimer> 0) {
                    sleep(1000);
                    checkPlayButton = true;
                    drawPlayButton = true;
                    playTimer--;
                    editor.putInt("PLAYTIMER",playTimer);
                    editor.apply();
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
                if(sleepTimer>0) {
                    sleep(1000);
                    checkSleepButton = true;
                    drawSleepButton = true;
                    sleepTimer--;
                    editor.putInt("SLEEPTIMER",sleepTimer);
                    editor.apply();
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
    class Second extends Thread{
        @Override
        public void run() {
            try {
                sleep(1000);
                timeForPoop--;
                lvlCheckTime--;
                secondTimeIsPassed = false;
                editor.putInt("LVLCHECKTIME",lvlCheckTime);
                editor.putInt("TIMEFORPOOP",timeForPoop);
                editor.apply();
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    class PoopThread extends Thread{
        @Override
        public void run() {
            try {
                if(poop < 4) {
                    sleep(200);
                    poop++;
                    poopTimeIsPassed = false;
                    poopNeedToDraw = true;
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
                if(poopFlyTime < 6) {
                    sleep(300);
                    poopFlyTimeIsPassed = false;
                    poopFlyNeedToDrawNow = true;
                    poopFlyTime++;
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
                if(poopFlyBackTime < 6) {
                    sleep(350);
                    poopFlyBackTimeIsPassed = false;
                    poopFlyBackNeedToDrawNow3 = true;
                    poopFlyBackTime++;
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

