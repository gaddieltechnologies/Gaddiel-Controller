package com.gaddiel.controller;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.media.MediaPlayer;

public class SplashScreen extends Activity{
    public  MediaPlayer splashSound;
 Handler mHandler;
	   @Override
	    public void onCreate(Bundle savedInstanceState) {
		   super.onCreate(savedInstanceState);
		   setContentView(R.layout.splashscreen);
           splashSound = MediaPlayer.create(SplashScreen.this, R.raw.intro);
          splashSound.start();
		   Thread splashThread = new Thread() {
		         @Override
		         public void run() {
		            try {
		               int waited = 0;
		               while (waited < 3000) {
		                  sleep(100);
		                  waited += 100;
		               }
		            } catch (InterruptedException e) {
		               // do nothing
		            } finally {
		               finish();
		               Intent i = new Intent(SplashScreen.this,MainActivity.class);
		           /*    i.setClassName("com.gaddiel.holyrosary.splashscre",
		                              "com.testing.splashscreensample.mainmenu");
		              */ startActivity(i);
		            }
		         }
		      };
		      splashThread.start();
		   
	    }

    @Override
    protected void onPause() {

        super.onPause();
        splashSound.stop();
        splashSound.release();
        finish();
    }
}

