/*
 * Copyright 2013 - learnNcode (learnncode@gmail.com)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.example.test_11.doctorappointmentapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentTabHost;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.SimpleImageLoadingListener;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import Adapters.DiseaseAdapter;
import Util.AppLog;


public class MapRotateTestActivity extends Activity implements SensorEventListener {

    int attribute=0;

		private View mView;
	// List view
	private ListView lv;
	ProgressDialog pDialog;
	// Listview Adapter
	ArrayAdapter<String> adapter;
	LinearLayout lyt;
	// Search EditText
//	EditText inputSearch;
	private SearchView mSearchView;
    DiseaseAdapter diseaseAdapter;
	private FragmentTabHost mTabHost;

	// ArrayList for Listview
	String TAG="Register";
	List<String> disease_list=new ArrayList<>();
	ProgressDialog mProgressDialog;
	private Handler handler;
	ImageView specialistBtn,locationBtn,conditionBtn,nameBtn,timeBtn;
	View child;
	private GoogleMap map;

	Marker marker;
	float[]	mMagnetic;
	float[]	mGravity;
	private ImageView imgCompass;
	private Hashtable<String, String> markers;
	private ImageLoader imageLoader;
	private DisplayImageOptions options;
	private float currentDegree=0f;
	private SensorManager sensorManager;
	boolean check=false;
	private TextView txtDegrees;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.doctor_searchtest);
         initView();
		imgCompass=(ImageView)findViewById(R.id.imgCompass);
		imgCompass.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				check=true;
			}
		});

	}

	void initView()
{
	txtDegrees=(TextView)findViewById(R.id.txtDegrees);

	sensorManager=(SensorManager)getSystemService(SENSOR_SERVICE);

	markers = new Hashtable<String, String>();


	map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
		map.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
			@Override
			public void onInfoWindowClick(Marker marker) {
//			startActivity(new Intent(getActivity(), DoctorDetailActivity.class));
			}
		});

	map.setMyLocationEnabled(true);
//							float bearing = map.getMyLocation().getBearing();

	//Get the current location
	Location startingLocation = new Location("starting point");
	startingLocation.setLatitude(map.getCameraPosition().target.latitude);
	startingLocation.setLongitude(map.getCameraPosition().target.longitude);

	//Get the target location
	Location endingLocation = new Location("ending point");
	endingLocation.setLatitude(map.getCameraPosition().target.latitude);
	endingLocation.setLongitude(map.getCameraPosition().target.longitude);

	//Find the Bearing from current location to next location
	float targetBearing = startingLocation.bearingTo(endingLocation);
	/*CameraPosition newCamPos = new CameraPosition(new LatLng(26.8623,81.0200),
			18f,
			65.5f,
			0f);
	map.animateCamera(CameraUpdateFactory.newCameraPosition(newCamPos), 2000, null);
*/
/*
	new Handler().postDelayed(new Runnable() {


		@Override
		public void run() {

			AppLog.logPrint("check");
			CameraPosition newCamPos = new CameraPosition(new LatLng(26.8623, 81.0200),
					18f,
					65.5f,
					270f);
			map.animateCamera(CameraUpdateFactory.newCameraPosition(newCamPos), 2000, null);
		}
	}, 5000);
*/


}

	@Override
	protected void onResume()
	{
// TODO: Implement this method
		super.onResume();
		sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION), SensorManager.SENSOR_DELAY_GAME);
	}

	@Override
	protected void onPause()
	{
// TODO: Implement this method
		super.onPause();
		sensorManager.unregisterListener(this);
	}
	private class CustomInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {

		private View view;

		public CustomInfoWindowAdapter() {
			view = getLayoutInflater().inflate(R.layout.custom_info_window,
					null);
		}

		@Override
		public View getInfoContents(Marker marker) {

			if (MapRotateTestActivity.this.marker != null
					&& MapRotateTestActivity.this.marker.isInfoWindowShown()) {
				MapRotateTestActivity.this.marker.hideInfoWindow();
				MapRotateTestActivity.this.marker.showInfoWindow();
			}
			return null;
		}

		@Override
		public View getInfoWindow(final Marker marker) {
			MapRotateTestActivity.this.marker = marker;

			String url = null;

			if (marker.getId() != null && markers != null && markers.size() > 0) {
				if ( markers.get(marker.getId()) != null &&
						markers.get(marker.getId()) != null) {
					url = markers.get(marker.getId());
				}
			}
			final ImageView image = ((ImageView) view.findViewById(R.id.badge));

			if (url != null && !url.equalsIgnoreCase("null")
					&& !url.equalsIgnoreCase("")) {
				imageLoader.displayImage(url, image, options,
						new SimpleImageLoadingListener() {
							@Override
							public void onLoadingComplete(String imageUri,
														  View view, Bitmap loadedImage) {
								super.onLoadingComplete(imageUri, view,
										loadedImage);
								getInfoContents(marker);
							}
						});
			} else {
				image.setImageResource(R.drawable.ic_launcher);
			}

			final String title = marker.getTitle();
			final TextView titleUi = ((TextView) view.findViewById(R.id.title));
			if (title != null) {
				titleUi.setText(title);
			} else {
				titleUi.setText("");
			}

			final String snippet = marker.getSnippet();
			final TextView snippetUi = ((TextView) view
					.findViewById(R.id.snippet));
			if (snippet != null) {
				snippetUi.setText(snippet);
			} else {
				snippetUi.setText("");
			}


			return view;
		}
	}
	@Override
	public void onSensorChanged(SensorEvent event)
	{


		int degree=Math.round(event.values[0]);
		txtDegrees.setText("Rotation: "+Float.toString(degree)+" degrees");
		RotateAnimation ra=new RotateAnimation(currentDegree,-degree, Animation.RELATIVE_TO_SELF,0.5f,
				Animation.RELATIVE_TO_SELF,0.5f);
		ra.setDuration(120);
		ra.setFillAfter(true);
		imgCompass.startAnimation(ra);
		currentDegree=-degree;
		AppLog.logPrint(degree+"");

		if( (degree-attribute)>10 || (attribute-degree)>10)
		{
			attribute=degree;
			CameraPosition newCamPos = new CameraPosition(new LatLng(26.8623, 81.0200),
					18f,
					65.5f,
					attribute);
			map.animateCamera(CameraUpdateFactory.newCameraPosition(newCamPos), 2000, null);
		}

	}

	@Override
	public void onAccuracyChanged(Sensor p1, int p2)
	{
// TODO: Implement this method
	}

	}
