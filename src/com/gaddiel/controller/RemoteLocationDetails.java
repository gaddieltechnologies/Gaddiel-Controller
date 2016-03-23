package com.gaddiel.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.gaddiel.controller.JSONParser;
import com.gaddiel.controllertool.AlertDialogManager;
import com.gaddiel.controllertool.ConnectionDetector;

public class RemoteLocationDetails extends Activity {
	 AlertDialogManager alert = new AlertDialogManager();
	 ConnectionDetector cd;		
	 TextView rename;
	 TextView renumber;
	 TextView redate;
	private JSONParser jsonParser=new JSONParser();
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.remote_location_details);
	        
	        cd = new ConnectionDetector(getApplicationContext());
			 
	        // Check if Internet present
	        if (!cd.isConnectingToInternet()) {
	            // Internet Connection is not present
	            alert.showAlertDialog(RemoteLocationDetails.this,
	                    "Internet Connection Error",
	                    "Please connect to working Internet connection", false);
	            // stop executing code by return
	            return;
	        }
	        
	      //Get Remote number from RemoteTrackingDetails
    		Intent rTD_RLD = getIntent();
    		
    		String rtdname = rTD_RLD.getStringExtra("rldName");
            String rtdnumber = rTD_RLD.getStringExtra("rldNumber");
            String rtddate = rTD_RLD.getStringExtra("rlddate");
         
            
            //Toast.makeText(RemoteLocationDetails.this, "You Clicked at "+rtddate, Toast.LENGTH_LONG).show();
			            
            rename = (TextView) findViewById(R.id.rld_name);
            renumber = (TextView) findViewById(R.id.rld_number);
    		redate = (TextView) findViewById(R.id.rld_date);
    		
    		rename.setText(rtdname);
            renumber.setText(rtdnumber);
    		redate.setText(rtddate);
    		
    		final ListView lisView12 = (ListView)findViewById(R.id.rlocald_list); 
            String urll = "http://controller.gaddieltech.com/remote_track_list/rll.php";
            
    	    		
            try {
            	//String testdate="12";
            	List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		    	nameValuePairs.add(new BasicNameValuePair("trackedremote_number", rtdnumber));
		    	nameValuePairs.add(new BasicNameValuePair("tracked_date", rtddate));
		    	
		    	String json = jsonParser.makeHttpRequest(urll, "POST", nameValuePairs);		
		    	Log.d("Filter With Date: ", ">>>" + json);
		    	
		    	final ArrayList<HashMap<String, String>> MyArrListl = new ArrayList<HashMap<String, String>>();
	  			HashMap<String, String> map;
	  			
	  			JSONObject jsonResponsel = new JSONObject(json);
			    JSONArray jsonMainNodel = jsonResponsel.getJSONArray("ListOfArrayRLL");
			    
			    for(int i = 0; i < jsonMainNodel.length(); i++){ 
					JSONObject c = jsonMainNodel.getJSONObject(i);	                
	               
					map = new HashMap<String, String>();
					//map.put("trackedlocation_id", c.getString("trackedlocation_id"));
		            //map.put("trackedremote_number", c.getString("trackedremote_number"));
		            map.put("tracked_lattitude", c.getString("tracked_lattitude"));
		            map.put("tracked_longtitude", c.getString("tracked_longtitude"));
		            map.put("tracked_date", c.getString("tracked_date"));
		            map.put("tracked_time", c.getString("tracked_time"));
		            map.put("tracked_Location_Name", c.getString("tracked_Location_Name"));
		           
		            MyArrListl.add(map);
				}
			    
			    SimpleAdapter sAdap;
		        sAdap = new SimpleAdapter(RemoteLocationDetails.this, MyArrListl, R.layout.remote_location_list,
		                new String[] {"tracked_Location_Name","tracked_time"}, new int[] {R.id.rll_location,R.id.rll_time});         
		        lisView12.setAdapter(sAdap);
		        
		        // OnClick Item
				lisView12.setOnItemClickListener(new OnItemClickListener() {
				public void onItemClick(AdapterView<?> myAdapter, View myView,int position, long mylng) {
			//	Toast.makeText(RemoteLocationDetails.this, "You Clicked at "+MyArrListl.get(+position).get("tracked_Location_Name"), Toast.LENGTH_LONG).show();
				
				 // TextView rename1 = (TextView) findViewById(R.id.rld_name);
		         // TextView renumber1 = (TextView) findViewById(R.id.rld_number);
	              
			
				String rlmvlattitude = MyArrListl.get(position).get("tracked_lattitude").toString();
				String rlmvlongtitude = MyArrListl.get(position).get("tracked_longtitude").toString();
				String rlmvdate = MyArrListl.get(position).get("tracked_date").toString();
				String rlmvtime = MyArrListl.get(position).get("tracked_time").toString();
				String rlmvLocation_Name = MyArrListl.get(position).get("tracked_Location_Name").toString();
				String rlmvname = rename.getText().toString();
				String rlmvnumber = renumber.getText().toString();
				
				Intent rLD_MV = new Intent(getApplicationContext(), RLMapView.class);
				rLD_MV.putExtra("mvName", rlmvname);
				rLD_MV.putExtra("mvNumber", rlmvnumber);
				rLD_MV.putExtra("mvLocation_Name", rlmvLocation_Name);
				rLD_MV.putExtra("mvtime", rlmvtime);
				rLD_MV.putExtra("mvdate", rlmvdate);
				rLD_MV.putExtra("mvlongtitude", rlmvlongtitude);
				rLD_MV.putExtra("mvlattitude", rlmvlattitude);
							
				//trackRemote.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	    	    startActivity(rLD_MV);
				
					}
				});
		        
            }//try
		       catch (JSONException e) {
			    // TODO Auto-generated catch block
			     e.printStackTrace();
				}
	 }
}
