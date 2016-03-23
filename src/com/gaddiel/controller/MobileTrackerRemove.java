package com.gaddiel.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.gaddiel.controllertool.AlertDialogManager;
import com.gaddiel.controllertool.ConnectionDetector;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;

public class MobileTrackerRemove extends Activity {
	String mobileno;
	 AlertDialogManager alert = new AlertDialogManager();
	 ConnectionDetector cd;		
	private JSONParser jsonParser=new JSONParser();
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.remove_mobile_tracker);
		
		//Get Remote number from Dashboard avtivity
		Intent reg = getIntent();
        mobileno = reg.getStringExtra("phoneno");
        //TextView txtProduct2 = (TextView) findViewById(R.id.removecpn);
       // txtProduct2.setText(mobileno);
       
    
     		
        final ListView lisView1 = (ListView)findViewById(R.id.listView1); 
        String url = "http://controller.gaddieltech.com/TrackedMobiles/tracked_mobiles_del.php";
        try {
    		
	    	List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
	    	nameValuePairs.add(new BasicNameValuePair("Controller_Phone_Number", mobileno));
	    	String json = jsonParser.makeHttpRequest(url, "POST",nameValuePairs);		
	    	Log.d("Delete List", ">>>" + json);
		       			    		
	    	final ArrayList<HashMap<String, String>> MyArrList = new ArrayList<HashMap<String, String>>();
			HashMap<String, String> map;
								 
			JSONObject jsonResponse = new JSONObject(json);
			JSONArray jsonMainNode = jsonResponse.getJSONArray("ListOfArray");
	    	
	    	for(int i = 0; i < jsonMainNode.length(); i++){    						    
            JSONObject c = jsonMainNode.getJSONObject(i);
            
            map = new HashMap<String, String>();
            map.put("Tracked_Mobile_Id", c.getString("Tracked_Mobile_Id"));
            map.put("Controller_Phone_Number", c.getString("Controller_Phone_Number"));
            map.put("Tracked_Mobile_Number", c.getString("Tracked_Mobile_Number"));
            map.put("Status", c.getString("Status"));
            map.put("Name", c.getString("Name"));                                   
		
            MyArrList.add(map);
            
		}//for
	    	
	    			
	   SimpleAdapter sAdap;
	        sAdap = new SimpleAdapter(MobileTrackerRemove.this, MyArrList, R.layout.remove_remote,
	        		  new String[] {"Name"}, new int[] {R.id.remoteusernamew});      
	        lisView1.setAdapter(sAdap);

	        final AlertDialog.Builder viewDetail = new AlertDialog.Builder(this);
			// OnClick Item
			lisView1.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> myAdapter, View myView,final int position, long mylng) {
			
				AlertDialog.Builder alertDialog = new AlertDialog.Builder(MobileTrackerRemove.this);				 		       
		        alertDialog.setTitle("Confirm Delete...");
		        alertDialog.setMessage("Are you sure you want to delete this Remote?");
		       // alertDialog.setIcon(R.drawable.);
		 
		        // Setting Positive "Yes" Button
		        alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
		            public void onClick(DialogInterface dialog,int which) {
		            	
		            	String rnumber = MyArrList.get(position).get("Tracked_Mobile_Id").toString();
		    			// TODO Auto-generated method stub
		    			ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		    			   //nameValuePairs.add(new BasicNameValuePair("Tracking_Schedule_Id",tsid));
		    			   nameValuePairs.add(new BasicNameValuePair("Tracked_Mobile_Id",rnumber));
		    			  						  
		    			  String registerUrl="http://controller.gaddieltech.com/Remotetracker/rtremove.php";
		    			  String json = jsonParser.makeHttpRequest(registerUrl, "POST",nameValuePairs);
		    			  
		    		       Log.d("Deleted Iterm", ">>>" + json);
		    			    
		    			     Intent dashBoard = new Intent(getApplicationContext(), Tracked_Mobiles.class);
		    			  dashBoard.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		    			  dashBoard.putExtra("phoneno", mobileno);
		    			  startActivity(dashBoard);
		    			  finish();

		 
		            // Write your code here to invoke YES event
		         //   Toast.makeText(getApplicationContext(), "You clicked on YES", Toast.LENGTH_SHORT).show();
		            }
		        });
		 
		        // Setting Negative "NO" Button
		        alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
		            public void onClick(DialogInterface dialog, int which) {
		            // Write your code here to invoke NO event
		         //   Toast.makeText(getApplicationContext(), "You clicked on NO", Toast.LENGTH_SHORT).show();
		            dialog.cancel();
		            }
		        });
		 
		        // Showing Alert Message
		        alertDialog.show();
			
			
				}
			});
								        
			
		 }//try
	       catch (JSONException e) {
		    // TODO Auto-generated catch block
		     e.printStackTrace();
			}
	
	     
 
}
}
