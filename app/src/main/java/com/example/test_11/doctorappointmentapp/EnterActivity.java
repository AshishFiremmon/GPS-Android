package com.example.test_11.doctorappointmentapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class EnterActivity extends Activity {
    ImageView mImageViewEmptying,mImageViewEmptying1,mImageViewEmptying2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter);
       mImageViewEmptying = (ImageView) findViewById(R.id.logoview);
         mImageViewEmptying1 = (ImageView) findViewById(R.id.logoview1);
         mImageViewEmptying2 = (ImageView) findViewById(R.id.logoview2);


        ((AnimationDrawable) mImageViewEmptying.getBackground()).start();
        ((AnimationDrawable) mImageViewEmptying1.getBackground()).start();

        ((AnimationDrawable) mImageViewEmptying2.getBackground()).start();

    callAsynchronousTask();

        findViewById(R.id.search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                startActivity(new Intent(EnterActivity.this,SearchActivity.class));
            }
        });

        findViewById(R.id.help).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                startActivity(new Intent(EnterActivity.this,WelcomeActivity.class));
            }
        });
        findViewById(R.id.settings).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                startActivity(new Intent(EnterActivity.this,SettingActivity.class));
            }
        });

    }
    public void callAsynchronousTask() {
        final Handler handler = new Handler();
        Timer timer = new Timer();
        TimerTask doAsynchronousTask = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        try {
                                  if (mImageViewEmptying.getVisibility()==View.VISIBLE)
                                  {
                                      mImageViewEmptying.setVisibility(View.INVISIBLE);
                                      mImageViewEmptying1.setVisibility(View.VISIBLE);

                                      mImageViewEmptying2.setVisibility(View.INVISIBLE);

                                  }else  if (mImageViewEmptying1.getVisibility()==View.VISIBLE)
                                  {
                                      mImageViewEmptying.setVisibility(View.INVISIBLE);
                                      mImageViewEmptying1.setVisibility(View.INVISIBLE);

                                      mImageViewEmptying2.setVisibility(View.VISIBLE);

                                  }else {
                                      mImageViewEmptying.setVisibility(View.VISIBLE);
                                      mImageViewEmptying1.setVisibility(View.INVISIBLE);

                                      mImageViewEmptying2.setVisibility(View.INVISIBLE);

                                  }


                                      } catch (Exception e) {
                            // TODO Auto-generated catch block
                        }
                    }
                });
            }
        };
        timer.schedule(doAsynchronousTask, 0, 2600); //execute in every 50000 ms
    }
}
