package com.example.test_11.doctorappointmentapp;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;

import java.util.Timer;
import java.util.TimerTask;

import Util.AppConstants;

public class GooglePlacesActivity extends FragmentActivity  {

   // GoogleMap googleMap;
 //  Spinner placeText;
  //  double latitude = 0;
  //  double longitude = 0;
    GPSTracker gps;
    ImageView mImageViewEmptying,mImageViewEmptying1,mImageViewEmptying2;
    AutoCompleteTextView placeText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //show error dialog if GoolglePlayServices not available
        if (!isGooglePlayServicesAvailable()) {
            finish();
        }
        setContentView(R.layout.activity_enter);
        //Creating the instance of ArrayAdapter containing list of language names
        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this,android.R.layout.select_dialog_item,getResources().getStringArray(R.array.place_arrays));
         placeText= (AutoCompleteTextView)findViewById(R.id.placeTxt);
        placeText.setThreshold(1);//will start working from first character
        placeText.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
        placeText.setTextColor(Color.BLACK);
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

                AppConstants.type=placeText.getText().toString();
                startActivity(new Intent(GooglePlacesActivity.this,SearchActivity.class));
            }
        });

        findViewById(R.id.help).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                startActivity(new Intent(GooglePlacesActivity.this,WelcomeActivity.class));
            }
        });
        findViewById(R.id.settings).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                startActivity(new Intent(GooglePlacesActivity.this, SettingActivity.class));
            }
        });
    //    placeText = (Spinner) findViewById(R.id.placeTxt);
        Button btnFind = (Button) findViewById(R.id.search);
       /*         gps=new GPSTracker(this);
        AppConstants.lat=gps.getLatitude();
        AppConstants.longitute=gps.getLongitude();
     */ /*  btnFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedpreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                String type = placeText.getSelectedItem().toString();
                AppConstants.type=placeText.getSelectedItem().toString();
                StringBuilder googlePlacesUrl = new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
               // googlePlacesUrl.append("location=" + "26.8623"  + "," + "81.0200");
                googlePlacesUrl.append("location=" +  AppConstants.lat  + "," +  AppConstants.longitute);
                googlePlacesUrl.append("&radius=" + ((sharedpreferences.getInt("Radius", 4)+1)*1000));
                googlePlacesUrl.append("&types=" + type);
                googlePlacesUrl.append("&sensor=true");
                googlePlacesUrl.append("&key=" + AppConstants.GOOGLE_API_KEY);

                GooglePlacesReadTask googlePlacesReadTask = new GooglePlacesReadTask(GooglePlacesActivity.this);
                Object[] toPass = new Object[1];
                toPass[0] = googlePlacesUrl.toString();
                googlePlacesReadTask.execute(toPass);
                AppLog.logPrint(googlePlacesUrl.toString());
            }
        });*/
    }

    private boolean isGooglePlayServicesAvailable() {
        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (ConnectionResult.SUCCESS == status) {
            return true;
        } else {
          //  GooglePlayServicesUtil.getErrorDialog(status, this, 0).show();
            return false;
        }
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


