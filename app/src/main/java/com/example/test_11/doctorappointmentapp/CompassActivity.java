package com.example.test_11.doctorappointmentapp;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;



public class CompassActivity extends Activity implements SensorEventListener
{
	/** Called when the activity is first created. */
	private TextView txtDegrees;
	private ImageView imgCompass;
	private float currentDegree=0f;
	private SensorManager sensorManager;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		imgCompass=(ImageView)findViewById(R.id.imgCompass);
		txtDegrees=(TextView)findViewById(R.id.txtDegrees);
		sensorManager=(SensorManager)getSystemService(SENSOR_SERVICE);
	}

	@Override
	protected void onResume()
	{
// TODO: Implement this method
		super.onResume();
		sensorManager.registerListener(this,sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),SensorManager.SENSOR_DELAY_GAME);
	}

	@Override
	protected void onPause()
	{
// TODO: Implement this method
		super.onPause();
		sensorManager.unregisterListener(this);
	}

	@Override
	public void onSensorChanged(SensorEvent event)
	{
		float degree=Math.round(event.values[0]);
		txtDegrees.setText("Rotation: "+Float.toString(degree)+" degrees");
		RotateAnimation ra=new RotateAnimation(currentDegree,-degree,Animation.RELATIVE_TO_SELF,0.5f,
				Animation.RELATIVE_TO_SELF,0.5f);
		ra.setDuration(120);
		ra.setFillAfter(true);
		imgCompass.startAnimation(ra);
		currentDegree=-degree;
	}

	@Override
	public void onAccuracyChanged(Sensor p1, int p2)
	{
// TODO: Implement this method
	}
}
