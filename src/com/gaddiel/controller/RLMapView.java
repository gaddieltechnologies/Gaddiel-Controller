package com.gaddiel.controller;

import com.gaddiel.controllertool.AlertDialogManager;
import com.gaddiel.controllertool.ConnectionDetector;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;


public class RLMapView extends Activity {
	 private GoogleMap googleMap;
	    private int mapType = GoogleMap.MAP_TYPE_NORMAL;
	    double latitude, longitude;	
	    AlertDialogManager alert = new AlertDialogManager();
		 ConnectionDetector cd;		
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.map_view);
	 
	        cd = new ConnectionDetector(getApplicationContext());
			 
	        // Check if Internet present
	        if (!cd.isConnectingToInternet()) {
	            // Internet Connection is not present
	            alert.showAlertDialog(RLMapView.this,
	                    "Internet Connection Error",
	                    "Please connect to working Internet connection", false);
	            // stop executing code by return
	            return;
	        }
	      //Get Remote number from RemoteTrackingDetails
    		Intent rLD_MV = getIntent();
    		
    		String mvName = rLD_MV.getStringExtra("mvName");
            String mvNumber = rLD_MV.getStringExtra("mvNumber");
            String mvLocation_Name = rLD_MV.getStringExtra("mvLocation_Name");
            String mvtime = rLD_MV.getStringExtra("mvtime");
            String mvdate = rLD_MV.getStringExtra("mvdate");
            String mvlongtitude = rLD_MV.getStringExtra("mvlongtitude");
            String mvlattitude = rLD_MV.getStringExtra("mvlattitude");
          
           // Toast.makeText(RLMapView.this, "mvlattitude "+mvlattitude, Toast.LENGTH_LONG).show();
           // Toast.makeText(RLMapView.this, "mvlongtitude "+mvlongtitude, Toast.LENGTH_LONG).show();
			            
            TextView rlmvName = (TextView) findViewById(R.id.mv_name);
            TextView rlmvNumber = (TextView) findViewById(R.id.mv_number);
    		TextView rlmvLocation_Name = (TextView) findViewById(R.id.mv_location);
    		TextView rlmvtime = (TextView) findViewById(R.id.mv_time);
            TextView rlmvdate = (TextView) findViewById(R.id.mv_date);
     		TextView rlmvlongtitude = (TextView) findViewById(R.id.mv_longtitude);
     		TextView rlmvlattitude = (TextView) findViewById(R.id.mv_lattitude);
    		
     		rlmvName.setText(mvName);
     		rlmvNumber.setText(mvNumber);
     		rlmvLocation_Name.setText(mvLocation_Name);
     		rlmvtime.setText(mvtime);
     		rlmvdate.setText(mvdate);
     		rlmvlongtitude.setText(mvlongtitude);
     		rlmvlattitude.setText(mvlattitude);
     		
     		
     		
     		
     		try {
    			// Loading map
    			initilizeMap();

    			// Changing map type
    			googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
    			// googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
    			// googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
    			// googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
    			// googleMap.setMapType(GoogleMap.MAP_TYPE_NONE);

    			// Showing / hiding your current location
    			googleMap.setMyLocationEnabled(true);

    			// Enable / Disable zooming controls
    			googleMap.getUiSettings().setZoomControlsEnabled(false);

    			// Enable / Disable my location button
    			googleMap.getUiSettings().setMyLocationButtonEnabled(true);

    			// Enable / Disable Compass icon
    			googleMap.getUiSettings().setCompassEnabled(true);

    			// Enable / Disable Rotate gesture
    			googleMap.getUiSettings().setRotateGesturesEnabled(true);

    			// Enable / Disable zooming functionality
    			googleMap.getUiSettings().setZoomGesturesEnabled(true);
    			
    			latitude=Double.valueOf(mvlattitude).doubleValue();
         		longitude=Double.valueOf(mvlongtitude).doubleValue();
         		
         		// Toast.makeText(RLMapView.this, "mvlattitude "+latitude, Toast.LENGTH_LONG).show();
                // Toast.makeText(RLMapView.this, "mvlongtitude "+longitude, Toast.LENGTH_LONG).show();

    			//double latitude = mvlattitude;
    			//double longitude = mvlongtitude;
    			
    			   			 
    			// create marker
    			//MarkerOptions marker = new MarkerOptions().position(new LatLng(latitude, longitude)).title("Hello Maps ");
    			MarkerOptions marker = new MarkerOptions().position(new LatLng(latitude, longitude)).title(mvLocation_Name);
    			
    			// ROSE color icon
    			marker.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET));
    			 
    			CameraPosition cameraPosition = new CameraPosition.Builder()
				.target(new LatLng(latitude,longitude)).zoom(15).build();
    			googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    			// adding marker
    			googleMap.addMarker(marker);
    			
    			/*if (i == 9) {
				CameraPosition cameraPosition = new CameraPosition.Builder()
						.target(new LatLng(randomLocation[0],
								randomLocation[1])).zoom(15).build();

				googleMap.animateCamera(CameraUpdateFactory	.newCameraPosition(cameraPosition));
			}*/

    			// lets place some 10 random markers
    			/*for (int i = 0; i < 10; i++) {
    				// random latitude and logitude
    				double[] randomLocation = createRandLocation(latitude,
    						longitude);

    				// Adding a marker
    				MarkerOptions marker = new MarkerOptions().position(
    						new LatLng(randomLocation[0], randomLocation[1]))
    						.title("Hello Maps " + i);

    				Log.e("Random", "> " + randomLocation[0] + ", "
    						+ randomLocation[1]);

    				// changing marker color
    				if (i == 0)
    					marker.icon(BitmapDescriptorFactory
    							.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
    				if (i == 1)
    					marker.icon(BitmapDescriptorFactory
    							.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
    				if (i == 2)
    					marker.icon(BitmapDescriptorFactory
    							.defaultMarker(BitmapDescriptorFactory.HUE_CYAN));
    				if (i == 3)
    					marker.icon(BitmapDescriptorFactory
    							.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
    				if (i == 4)
    					marker.icon(BitmapDescriptorFactory
    							.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
    				if (i == 5)
    					marker.icon(BitmapDescriptorFactory
    							.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));
    				if (i == 6)
    					marker.icon(BitmapDescriptorFactory
    							.defaultMarker(BitmapDescriptorFactory.HUE_RED));
    				if (i == 7)
    					marker.icon(BitmapDescriptorFactory
    							.defaultMarker(BitmapDescriptorFactory.HUE_ROSE));
    				if (i == 8)
    					marker.icon(BitmapDescriptorFactory
    							.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET));
    				if (i == 9)
    					marker.icon(BitmapDescriptorFactory
    							.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW));*/

    			//	googleMap.addMarker(marker);

    				// Move the camera to last position with a zoom level
    				/*if (i == 9) {
    					CameraPosition cameraPosition = new CameraPosition.Builder()
    							.target(new LatLng(randomLocation[0],
    									randomLocation[1])).zoom(15).build();

    					googleMap.animateCamera(CameraUpdateFactory
    							.newCameraPosition(cameraPosition));
    				}*/
    			//}

    		} catch (Exception e) {
    			e.printStackTrace();
    		}

	 }
	
	 @Override
		protected void onResume() {
			super.onResume();
			initilizeMap();
			
		}
		/**
		 * function to load map If map is not created it will create it for you
		 * */
		private void initilizeMap() {
			if (googleMap == null) {
				googleMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();

				// check if map is created successfully or not
				if (googleMap == null) {
					Toast.makeText(getApplicationContext(),
							"Sorry! unable to create maps", Toast.LENGTH_SHORT)
							.show();
				}
			}
		}

		/*
		 * creating random postion around a location for testing purpose only
		 */
		private double[] createRandLocation(double latitude, double longitude) {

			return new double[] { latitude + ((Math.random() - 0.5) / 500),
					longitude + ((Math.random() - 0.5) / 500),
					150 + ((Math.random() - 0.5) * 10) };
		}
	
}
