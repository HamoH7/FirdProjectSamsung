package com.example.test;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.load.engine.cache.SafeKeyGenerator;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ShopSkin extends AppCompatActivity {
    private Bundle arguments;
    private int foin = 0, skinId = 0;
    private Button buttonBack;
    private TextView textView;
    private ImageView goBack, fireFird, fird;
    private boolean firdChoosen, fireFirdBuyed, fireFirdChoosen;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.shopskin);
        sharedPreferences = getApplicationContext().getSharedPreferences("SHAREDPREFERENCES", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        loadInfo(this);
        arguments = getIntent().getExtras();
        fireFird = findViewById(R.id.fireFird);
        fird = findViewById(R.id.fird);
        textView = findViewById(R.id.textView);
        foin = Integer.parseInt(arguments.get("foin").toString());
        goBack = (ImageView) findViewById(R.id.backButton);
        goBack.setOnClickListener(v ->  {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("foin", foin + "");
            intent.putExtra("skinId",skinId + "");
            this.startActivity(intent);
        });
        checkInfo();
        textView.setText(foin + "");
        fireFird.setOnClickListener(v ->  {
            if(!fireFirdBuyed && foin >= 5000) {
                fireFirdBuyed = true;
                fireFird.setBackgroundResource(R.drawable.fire_fird_select);
                foin -= 5000;
                textView.setText(foin + "");
                editor.putBoolean("FIREFIRDBUYED",true);
                editor.apply();
            }
            else if(fireFirdBuyed && !fireFirdChoosen) {
                fireFirdChoosen = true;
                firdChoosen = false;
                editor.putBoolean("FIREFIRDCHOOSEN",fireFirdChoosen);
                editor.putBoolean("FIRDCHOOSEN",firdChoosen);
                editor.apply();
            }
            else if(fireFirdBuyed && fireFirdChoosen) {
                fireFirdChoosen = false;
                firdChoosen = true;
                editor.putBoolean("FIREFIRDCHOOSEN",fireFirdChoosen);
                editor.putBoolean("FIRDCHOOSEN",firdChoosen);
                editor.apply();
            }
            checkInfo();
        });
        fird.setOnClickListener(v ->  {
            if(fireFirdBuyed && !firdChoosen) {
                fireFirdChoosen = false;
                firdChoosen = true;
                editor.putBoolean("FIREFIRDCHOOSEN",fireFirdChoosen);
                editor.putBoolean("FIRDCHOOSEN",firdChoosen);
                editor.apply();
            }
            else if(fireFirdBuyed && firdChoosen) {
                fireFirdChoosen = true;
                firdChoosen = false;
                editor.putBoolean("FIREFIRDCHOOSEN",fireFirdChoosen);
                editor.putBoolean("FIRDCHOOSEN",firdChoosen);
                editor.apply();
            }
            checkInfo();
        });
    }
    public void checkInfo() {
        if(!firdChoosen) fird.setBackgroundResource(R.drawable.fird_select);
        if(firdChoosen) {
            fird.setBackgroundResource(R.drawable.fird_selected);
            skinId = 0;
            editor.putInt("SKINID",skinId);
        }
        if(!fireFirdChoosen && fireFirdBuyed) fireFird.setBackgroundResource(R.drawable.fire_fird_select);
        if(fireFirdChoosen && fireFirdBuyed) {
            fireFird.setBackgroundResource(R.drawable.fire_fird_selected);
            skinId = 1;
            editor.putInt("SKINID",skinId);
        }
        if(!fireFirdBuyed) fireFird.setBackgroundResource(R.drawable.fire_fird_buy);
    }
    public void loadInfo(Context context) {
        skinId = sharedPreferences.getInt("SKINID",0);
        firdChoosen = sharedPreferences.getBoolean("FIRDCHOOSEN", true);
        fireFirdBuyed = sharedPreferences.getBoolean("FIREFIRDBUYED", false);
        fireFirdChoosen = sharedPreferences.getBoolean("FIREFIRDCHOOSEN",false);
    }
}
