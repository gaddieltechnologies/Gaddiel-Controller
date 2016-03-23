package com.gaddiel.controller;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.gaddiel.controller.JSONParser;
import com.gaddiel.controllertool.AlertDialogManager;
import com.gaddiel.controllertool.ConnectionDetector;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class RemoteTrackingDetails extends Activity{
	private JSONParser jsonParser=new JSONParser();
	 AlertDialogManager alert = new AlertDialogManager();
	 ConnectionDetector cd;	
	 TextView rename;
	 TextView renumber;
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.remote_tracking_details);
	        
	        cd = new ConnectionDetector(getApplicationContext());
			 
	        // Check if Internet present
	        if (!cd.isConnectingToInternet()) {
	            // Internet Connection is not present
	            alert.showAlertDialog(RemoteTrackingDetails.this,
	                    "Internet Connection Error",
	                    "Please connect to working Internet connection", false);
	            // stop executing code by return
	            return;
	        }
	       
	        // Permission StrictMode
	        if (android.os.Build.VERSION.SDK_INT > 9) {
	            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
	            StrictMode.setThreadPolicy(policy);
	        }
	        
	        //Get Remote number from Dashboard avtivity
      		  Intent trackRemote = getIntent();
              String rname = trackRemote.getStringExtra("remname");
              String rnumber = trackRemote.getStringExtra("remphone");
              String cnum = trackRemote.getStringExtra("cnum");
			  String rnumid = trackRemote.getStringExtra("rnumid");
				
             
              rename = (TextView) findViewById(R.id.remoteGetname);
              renumber = (TextView) findViewById(R.id.remoteGetnumber);
             
              rename.setText(rname);
              renumber.setText(rnumber);
              
              
          	final ListView lisView1 = (ListView)findViewById(R.id.rtd_list); 
            String url = "http://controller.gaddieltech.com/remote_track_list/rtl.php";
             
              
            try {
            	
            	List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		    	nameValuePairs.add(new BasicNameValuePair("trackedremote_number", rnumber));
		    	String json = jsonParser.makeHttpRequest(url, "POST",nameValuePairs);		
		    	Log.d("Filter With Number : ", ">>>" + json);
  				
  			
		    	
		    	final ArrayList<HashMap<String, String>> MyArrList = new ArrayList<HashMap<String, String>>();
	  			HashMap<String, String> map;
            	
	  			//JSONArray data = new JSONArray(getJSONUrl(url));
	  			
		    	JSONObject jsonResponse = new JSONObject(json);
			    JSONArray jsonMainNode = jsonResponse.getJSONArray("ListOfArray");
				
				for(int i = 0; i < jsonMainNode.length(); i++){ 
					JSONObject c = jsonMainNode.getJSONObject(i);	                
	               
					map = new HashMap<String, String>();
					//map.put("trackedlocation_id", c.getString("trackedlocation_id"));
		         //   map.put("trackedremote_number", c.getString("trackedremote_number"));
		            map.put("tracked_date", c.getString("tracked_date"));
		            MyArrList.add(map);
				}
				
				 SimpleAdapter sAdap;
			        sAdap = new SimpleAdapter(RemoteTrackingDetails.this, MyArrList, R.layout.remote_tracking_list,
			                new String[] {"tracked_date"}, new int[] {R.id.rtl_date});         
			        lisView1.setAdapter(sAdap);
			        
			     // OnClick Item
					lisView1.setOnItemClickListener(new OnItemClickListener() {
					public void onItemClick(AdapterView<?> myAdapter, View myView,int position, long mylng) {
					//Toast.makeText(RemoteTrackingDetails.this, "You Clicked at "+MyArrList.get(+position).get("tracked_date"), Toast.LENGTH_LONG).show();
					
					// TextView rename1 = (TextView) findViewById(R.id.remoteGetname);
		             //TextView renumber1 = (TextView) findViewById(R.id.remoteGetnumber);
		              
					String rtddate = MyArrList.get(position).get("tracked_date").toString();
					String rtdname = rename.getText().toString();
					String rtdnumber = renumber.getText().toString();
					
					Intent rTD_RLD = new Intent(getApplicationContext(), RemoteLocationDetails.class);
					rTD_RLD.putExtra("rldName", rtdname);
					rTD_RLD.putExtra("rldNumber", rtdnumber);
					rTD_RLD.putExtra("rlddate", rtddate);
					
					//trackRemote.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		    	    startActivity(rTD_RLD);
					
						}
					});
			        
              }//try
		       catch (JSONException e) {
			    // TODO Auto-generated catch block
			     e.printStackTrace();
				}
	 }
		
	 public String getJSONUrl(String url) {
			StringBuilder str = new StringBuilder();
			HttpClient client = new DefaultHttpClient();
			HttpGet httpGet = new HttpGet(url);
			try {
				HttpResponse response = client.execute(httpGet);
				StatusLine statusLine = response.getStatusLine();
				int statusCode = statusLine.getStatusCode();
				if (statusCode == 200) { // Download OK
					HttpEntity entity = response.getEntity();
					InputStream content = entity.getContent();
					BufferedReader reader = new BufferedReader(new InputStreamReader(content));
					String line;
					while ((line = reader.readLine()) != null) {
						str.append(line);
					}
				} else {
					Log.e("Log", "Failed to download result..");
				}
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return str.toString();
		}
}
