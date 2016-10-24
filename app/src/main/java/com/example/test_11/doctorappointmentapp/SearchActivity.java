package com.example.test_11.doctorappointmentapp;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import Util.AppConstants;

public class SearchActivity extends AppCompatActivity {
    FragmentTransaction fragmentTransaction;
    FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedpreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        StringBuilder googlePlacesUrl = new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
         googlePlacesUrl.append("location=" + "26.8623"  + "," + "81.0200");
       // googlePlacesUrl.append("location=" +  AppConstants.lat  + "," +  AppConstants.longitute);
        googlePlacesUrl.append("&radius=" + ((sharedpreferences.getInt("Radius", 4)+1)*1000));
        googlePlacesUrl.append("&types=" + AppConstants.type);
        googlePlacesUrl.append("&sensor=true");
        googlePlacesUrl.append("&key=" + AppConstants.GOOGLE_API_KEY);

      /*  GooglePlacesReadTask googlePlacesReadTask = new GooglePlacesReadTask(SearchActivity.this);
        Object[] toPass = new Object[2];
        toPass[0] = googlePlacesUrl.toString();
        googlePlacesReadTask.execute(toPass);
        AppLog.logPrint(googlePlacesUrl.toString());
*/
        setContentView(R.layout.activity_main3);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
findViewById(R.id.listbtn).setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {


        startActivity(new Intent(SearchActivity.this, PlaceListActivity.class));
    }
});
        findViewById(R.id.settings).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                startActivity(new Intent(SearchActivity.this,SettingActivity.class));
            }
        });
        findViewById(R.id.help).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                startActivity(new Intent(SearchActivity.this,WelcomeActivity.class));
            }
        });
        findViewById(R.id.backBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

       /* if(AppConstants.change)
        {
            AppConstants.change=false;
            startActivity(new Intent(SearchActivity.this,SearchActivity.class));
            finish();
        }*/
    }
}
