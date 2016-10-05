package com.itfeige.my.jinxiunews.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;

import com.itfeige.my.jinxiunews.R;

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
     /*   //3秒钟后进入主界面
        UIUtils.getHandler().postDelayed(new Runnable(){
            public void run() {
                Intent intent = new Intent(SplashActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 3000);*/

        new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(3000);
                Intent intent = new Intent(SplashActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        }).start();



    }
}
