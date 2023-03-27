package com.example.test;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ShopSkin extends AppCompatActivity {
    private Bundle arguments;
    private int foin = 0;
    private Button buttonBack;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.shopskin);
        arguments = getIntent().getExtras();
        textView = (TextView)findViewById(R.id.textView);
        foin = Integer.parseInt(arguments.get("foin").toString());
        textView.setText(foin + "");
        buttonBack = (Button)findViewById(R.id.button);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ShopSkin.this, MainActivity.class));
            }
        });
    }
}
