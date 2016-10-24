package com.example.test_11.doctorappointmentapp;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import Util.AppConstants;

public class SettingActivity extends Activity
        {
            SharedPreferences sharedpreferences;
            RadioButton km,miles;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_screen2);
        final SeekBar radius=(SeekBar)findViewById(R.id.radiusSeekbar);
        findViewById(R.id.backBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                finish();
            }
        });
        findViewById(R.id.submmit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(km.isChecked())
               {
                   SharedPreferences.Editor editor = sharedpreferences.edit();
                   editor.putString("Unit", "km").commit();
               }else if(miles.isChecked())
               {
                   SharedPreferences.Editor editor = sharedpreferences.edit();
                   editor.putString("Unit", "miles").commit();
               }
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putInt("Radius", (radius.getProgress())).commit();
                AppConstants.change=true;
                finish();
            }
        });
         sharedpreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        km=(RadioButton)findViewById(R.id.kmradiobutton);
        miles=(RadioButton)findViewById(R.id.milesradiobutton);


if(sharedpreferences.getString("Unit","km").equals("km"))
{
    km.setChecked(true);
}else
{
    miles.setChecked(true);
}
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioUnits);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // checkedId is the RadioButton selected
                if(checkedId==R.id.kmradiobutton)
                {
                    ((TextView)findViewById(R.id.radiustxt)).setText("Radius " + (sharedpreferences.getInt("Radius", 4) + 1) + " Km");
                    ((TextView)findViewById(R.id.kmTxt)).setText("1 Km");
                    ((TextView)findViewById(R.id.kmtxt2)).setText("20 Km");


                }else if(checkedId==R.id.milesradiobutton)
                {
                    ((TextView)findViewById(R.id.radiustxt)).setText("Radius " + (sharedpreferences.getInt("Radius", 4) + 1) + " Miles");
                    ((TextView)findViewById(R.id.kmTxt)).setText("1 Miles");
                    ((TextView)findViewById(R.id.kmtxt2)).setText("20 Miles");

                }
            }
        });


        radius.setProgress(sharedpreferences.getInt("Radius", 4) + 1);
        ((TextView)findViewById(R.id.radiustxt)).setText("Radius " + (sharedpreferences.getInt("Radius", 4) + 1) + " " + sharedpreferences.getString("Unit", "km"));
        radius.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                if (km.isChecked()) {
                    ((TextView) findViewById(R.id.radiustxt)).setText("Radius " + (progress + 1) + " Km");
                } else if (miles.isChecked()) {
                    ((TextView) findViewById(R.id.radiustxt)).setText("Radius " + (progress + 1) + " Miles");
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {


            }
        });

    }


}
