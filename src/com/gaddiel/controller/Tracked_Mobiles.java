package com.gaddiel.controller;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import com.gaddiel.controller.JSONParser;
//import com.gaddiel.controller.SimpleAddressBookAdapter;
//import com.gaddiel.controllertool.JSONParser;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class Tracked_Mobiles extends Activity {
	Button addremote;
	Button removeremote;
	Context context;
	int posi;
	String state;
	Button rbtn;
	Button sbtn;
	String cnumber;
	TextView cpnum;
	Button statusactive;
	Button statusinactive;
	String statuss;
	//TextView hiddenstatus;
	TextView statusdisp;
	String tsid;
	String tstmid;
	String tsStatus;
	String tsStart_Time;
	String tsEnd_Time;
	String tsMonday;
	String tsTuesday;
	String tsWednesday;
	String tsThursday;
	String tsFriday;
	String tsSaturday;
	String tsSunday;
	String tsRepeat_option;
	String tsFrequency;
	String tsStart;
	String tsStop;
	String tsCreatedBy;
	String tsCreatedDate;
	String active="Tracker ON";
	String inactive="Tracker OFF";
	
	private JSONParser jsonParser=new JSONParser();

	private SimpleAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tracked_mobiles_listview);		
		//statusactive=(Button) findViewById(R.id.statusbtn2);
		//statusinactive=(Button) findViewById(R.id.statusbtn);
		//hiddenstatus=(TextView) findViewById(R.id.hstatus);
		statusdisp=(TextView) findViewById(R.id.hstatus);
		addListenerOnButton();	
		
		//Get Remote number from MainAvtivity
		Intent trackRemote = getIntent();
        String mobileno = trackRemote.getStringExtra("phoneno");
      
        TextView txtProduct2 = (TextView) findViewById(R.id.trackcpn);
       // Toast.makeText(Tracked_Mobiles.this,"CPN >>" + mobileno, Toast.LENGTH_SHORT).show();
        txtProduct2.setText(mobileno);
        
        
        
		     	
   
		 // Permission StrictMode
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        
        final ListView lisView1 = (ListView)findViewById(R.id.remoteuserlist); 
        String url = "http://gaddieltech.com/controller/TrackedMobiles/tracked_mobiles.php";
        String geturl ="http://controller.gaddieltech.com/TrackedMobiles/tracked_mobiles.php";
       
     	
        //cnumber = txtProduct2.getText().toString().trim();
        
       	
		    		    	 
		 try {
		    		
		    	List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		    	nameValuePairs.add(new BasicNameValuePair("Controller_Phone_Number", mobileno));
		    	String json = jsonParser.makeHttpRequest(geturl, "POST",nameValuePairs);		
		    	Log.d("Response: ", ">>>" + json);
			       			    		
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
                
               // inactive.setTextColor(Color.RED);
               // active.setTextColor(Color.GREEN);
               
                if(c.getString("Status").equals("Y")){
                    map.put("Status", active);}
                    if(c.getString("Status").equals("N")){
                        map.put("Status", inactive);}
                   
                //map.put("Designation", c.getString("Designation"));
    			//map.put("Base_Location", c.getString("Base_Location"));
    			//map.put("Activation_Date", c.getString("Activation_Date"));
    			//map.put("Inactivation_Date", c.getString("Inactivation_Date"));
               
               statuss=c.getString("Status");
    		
                MyArrList.add(map);
    			
    		
			
			}//for
		    	
			/* adapter = new SimpleAddressBookAdapter(Tracked_Mobiles.this, MyArrList, R.layout.tracked_mobiles,
		               new String[] {"Status","Name"}, new int[] {R.id.hstatus, R.id.remoteusername});      
		    //  lisView1.setItemsCanFocus(false); listView1.getContext(),
			 lisView1.setAdapter(adapter);*/
			
		 SimpleAdapter sAdap;
		        sAdap = new SimpleAdapter(Tracked_Mobiles.this, MyArrList, R.layout.tracked_mobiles,
		                new String[] {"Name", "Status"}, new int[] {R.id.remoteusername, R.id.hstatus});         
		        lisView1.setAdapter(sAdap);
		        registerForContextMenu(lisView1);  
		        
		        int count=sAdap.getCount();
		       // Toast.makeText(Tracked_Mobiles.this, "List count is "+count, Toast.LENGTH_LONG).show();		
		        
		        final AlertDialog.Builder viewDetail = new AlertDialog.Builder(this);
				// OnClick Item
				lisView1.setOnItemClickListener(new OnItemClickListener() {
				public void onItemClick(AdapterView<?> myAdapter, View myView,int position, long mylng) {
				//Toast.makeText(Tracked_Mobiles.this, "You Clicked at "+MyArrList.get(+position).get("Name"), Toast.LENGTH_LONG).show();
				//Toast.makeText(Tracked_Mobiles.this, "You Clicked at "+MyArrList.get(+position).get("Tracked_Mobile_Number"), Toast.LENGTH_LONG).show();	
			
							
				String rname = MyArrList.get(position).get("Name").toString();
				String rnumber = MyArrList.get(position).get("Tracked_Mobile_Number").toString();
				String cnum = MyArrList.get(position).get("Controller_Phone_Number").toString();
				String rnumid = MyArrList.get(position).get("Tracked_Mobile_Id").toString();
				
				Intent trackRemote = new Intent(getApplicationContext(), RemoteTrackingDetails.class);
				trackRemote.putExtra("remname", rname);
				trackRemote.putExtra("remphone", rnumber);
				trackRemote.putExtra("cnum", cnum);
				trackRemote.putExtra("rnumid", rnumid);
				//trackRemote.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	    	    startActivity(trackRemote);
				
					}
				});
				
					lisView1.setOnItemLongClickListener(new OnItemLongClickListener(){

					
					public boolean onItemLongClick(AdapterView<?> myAdapter, View myView,
							int position, long mylng) {
						// TODO Auto-generated method stub
						//int position=lisView1.getPositionForView(arg0);
						//if (position != ListView.INVALID_POSITION) {
							posi=Integer.parseInt(MyArrList.get(+position).get("Tracked_Mobile_Id"));
							 state=MyArrList.get(+position).get("Status");
							 //Toast.makeText(Tracked_Mobiles.this, "Your State"+state, Toast.LENGTH_LONG).show();	
				              //  if(("Status").equals("N")){
							 Toast.makeText(Tracked_Mobiles.this, "Your State"+posi, Toast.LENGTH_LONG).show();	 
							 if (state.equals(inactive)){
				                    // Toast.makeText(Tracked_Mobiles.this, "Your State N "+state, Toast.LENGTH_LONG).show();	
				                	 String mobile = MyArrList.get(+position).get("Name").toString();
									 String mob=MyArrList.get(+position).get("Tracked_Mobile_Number").toString();
									 String contnum=MyArrList.get(+position).get("Controller_Phone_Number").toString();
									Intent trackRemote = new Intent(getApplicationContext(), RemoteTracking.class);
									trackRemote.putExtra("phone", mobile);
									trackRemote.putExtra("id", posi);
									trackRemote.putExtra("state", state);
									trackRemote.putExtra("mobile", mobile);
									trackRemote.putExtra("mob", mob);
									trackRemote.putExtra("contmob", contnum);
									//trackRemote.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				    	        	startActivity(trackRemote);
				    	        //	return true;
				                }
				                   
				             if(state.equals(active)){  				                    
							
				            	 //Toast.makeText(Tracked_Mobiles.this, "Your State Y "+state, Toast.LENGTH_LONG).show();
				            	 String mobile = MyArrList.get(+position).get("Name").toString();
								 String mob=MyArrList.get(+position).get("Tracked_Mobile_Number").toString();
								 String contnum=MyArrList.get(+position).get("Controller_Phone_Number").toString();
								 String tmid=MyArrList.get(+position).get("Tracked_Mobile_Id").toString();
								 
				             	 String selecturl ="http://controller.gaddieltech.com/Remotetracker/Selectrt.php";
				            	 try {
				 		    		
				     		    	List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
				     		    	nameValuePairs.add(new BasicNameValuePair("Tracked_Mobile_Id", tmid));
				     		    	String json = jsonParser.makeHttpRequest(selecturl, "POST",nameValuePairs);		
				     		    	Log.d("RemoteTrackingUpdate: ", ">>>" + json);
				     			       			    		
				     		    	final ArrayList<HashMap<String, String>> SelectArrList = new ArrayList<HashMap<String, String>>();
				     				HashMap<String, String> map;
				     									 
				     				JSONObject jsonResponsee = new JSONObject(json);
				     				JSONArray jsonMainNodee = jsonResponsee.getJSONArray("ListOfArray");
				     		    	
				     		    	for(int i = 0; i < jsonMainNodee.length(); i++){    						    
				                     JSONObject c = jsonMainNodee.getJSONObject(i);
				                     
				                     map = new HashMap<String, String>();
				                     map.put("Tracking_Schedule_Id", c.getString("Tracking_Schedule_Id"));
				                     map.put("Tracked_Mobile_Id", c.getString("Tracked_Mobile_Id"));
				                     map.put("Status", c.getString("Status"));
				                     map.put("Start_Time", c.getString("Start_Time"));
				                     map.put("End_Time", c.getString("End_Time"));
				                     map.put("Monday",c.getString("Monday"));
				                     map.put("Tuesday",c.getString("Tuesday"));
				                     map.put("Wednesday",c.getString("Wednesday"));
				                     map.put("Thursday",c.getString("Thursday"));
				                     map.put("Friday",c.getString("Friday"));
				                     map.put("Saturday",c.getString("Saturday"));
				                     map.put("Sunday",c.getString("Sunday"));
				                     map.put("Repeat_option",c.getString("Repeat_option"));
				                     map.put("Frequency",c.getString("Frequency"));
				                     map.put("Start",c.getString("Start"));
				                     map.put("Stop",c.getString("Stop"));
				                     map.put("CreatedBy",c.getString("CreatedBy"));
				                     map.put("CreatedDate",c.getString("CreatedDate"));	
				                     
				                     
				                     tsid = c.getString("Tracking_Schedule_Id");
					     		     tstmid = c.getString("Tracked_Mobile_Id");
					     		     tsStatus = c.getString("Status");
					     		     tsStart_Time = c.getString("Start_Time");
					     		     tsEnd_Time = c.getString("End_Time");
					     		     tsMonday = c.getString("Monday");
					     		     tsTuesday = c.getString("Tuesday");
					     		     tsWednesday = c.getString("Wednesday");
					     		     tsThursday = c.getString("Thursday");
					     		     tsFriday = c.getString("Friday");
					     		     tsSaturday = c.getString("Saturday");
					     		     tsSunday = c.getString("Sunday");
					     		     tsRepeat_option = c.getString("Repeat_option");
					     		     tsFrequency = c.getString("Frequency");
					     		     tsStart = c.getString("Start");
					     		     tsStop = c.getString("Stop");
					     		     tsCreatedBy = c.getString("CreatedBy");
					     		     tsCreatedDate = c.getString("CreatedDate");
				                					         		
				                    SelectArrList.add(map);
				     			}//for
				     		    	
				     		    	
				     		    	
				     		    	 Intent trackRemote = new Intent(getApplicationContext(), RemoteTracking.class);
				     		    	 trackRemote.putExtra("id", posi);
									 trackRemote.putExtra("state", state);
									 trackRemote.putExtra("mobile", mobile);
									 trackRemote.putExtra("mob", mob);
									 trackRemote.putExtra("contmob", contnum);
									 
									 trackRemote.putExtra("tstoid", tsid);
									 trackRemote.putExtra("tstotmid", tstmid);
									 trackRemote.putExtra("tstoStatus", tsStatus);
									 trackRemote.putExtra("tstoStart_Time",tsStart_Time);
									 trackRemote.putExtra("tstoEnd_Time", tsEnd_Time);	
									 trackRemote.putExtra("tstoMonday", tsMonday);
									 trackRemote.putExtra("tstoTuesday", tsTuesday);
									 trackRemote.putExtra("tstoWednesday", tsWednesday);
									 trackRemote.putExtra("tstoThursday", tsThursday);
									 trackRemote.putExtra("tstoFriday", tsFriday);
									 trackRemote.putExtra("tstoSaturday", tsSaturday);
									 trackRemote.putExtra("tstoSunday", tsSunday);
									 trackRemote.putExtra("tstoRepeat_option", tsRepeat_option);
									 trackRemote.putExtra("tstoFrequency", tsFrequency);
									 trackRemote.putExtra("tstoStart", tsStart);
									 trackRemote.putExtra("tstoStop", tsStop);
									 trackRemote.putExtra("tstoCreatedBy", tsCreatedBy);
									 trackRemote.putExtra("tstoCreatedDate", tsCreatedDate);
								
								      startActivity(trackRemote);
				            	 }//try
				  		       catch (JSONException e) {
				  			    // TODO Auto-generated catch block
				  			     e.printStackTrace();
				  				}
				            }
							
				                return true;
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
	
	public void addListenerOnButton() {
		addremote = (Button) findViewById(R.id.btnaddremote);
		removeremote = (Button) findViewById(R.id.btndelremote);
		cpnum = (TextView) findViewById(R.id.trackcpn);
		addremote.setOnClickListener(new OnClickListener() {
					
			@Override
			public void onClick(View arg0) {	
			
				String mobileno = cpnum.getText().toString();
				Intent reg = new Intent(getApplicationContext(), MobileTracker.class);
				//Intent reg = new Intent(Tracked_Mobiles.this, MobileTracker.class);
				reg.putExtra("phoneno", mobileno);
				startActivity(reg);
			}
		});
		
		removeremote.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {	
			
				String mobileno = cpnum.getText().toString();
				Intent reg = new Intent(getApplicationContext(), MobileTrackerRemove.class);
				//Intent reg = new Intent(Tracked_Mobiles.this, MobileTracker.class);
				reg.putExtra("phoneno", mobileno);
				startActivity(reg);
			}
		});

		
	}
	
	@Override
    public void onBackPressed() {
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(Tracked_Mobiles.this);
		 
        // Setting Dialog Title
        alertDialog.setTitle("Confirm Logout...");
 
        // Setting Dialog Message
        alertDialog.setMessage("Do you want to Logout?");
 
        // Setting Icon to Dialog
       // alertDialog.setIcon(R.drawable.delete);
 
        // Setting Positive "Yes" Button
        alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int which) {
            	//Toast.makeText(Tracked_Mobiles.this, "User Has Been Logout ", Toast.LENGTH_LONG).show();
            	Log.d("HA", "Finishing");
            	Intent intent = new Intent();
                intent.setAction(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
               // userFunctions.logoutUser(getApplicationContext());
        		Intent login = new Intent(getApplicationContext(), MainActivity.class);
            	login.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            	startActivity(login);    	
        		finish();           
           
            }
        });
 
        // Setting Negative "NO" Button
        alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                       	
            dialog.cancel();
            }
        });
 
        // Showing Alert Message
        alertDialog.show();
		
	}
	
	/*
	 @Override   
	    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo)  
	    {  
	            super.onCreateContextMenu(menu, v, menuInfo);  
	            menu.setHeaderTitle("Select The Action");    
	            menu.add(0, v.getId(), 0, "Call");//groupId, itemId, order, title   
	            menu.add(0, v.getId(), 0, "SMS");   
	    }   
	      
	    @Override    
	    public boolean onContextItemSelected(MenuItem item){    
	            if(item.getTitle()=="Call"){  
	               // Toast.makeText(getApplicationContext(),"calling code",Toast.LENGTH_LONG).show();  
	            }    
	            else if(item.getTitle()=="SMS"){  
	                //Toast.makeText(getApplicationContext(),"sending sms code",Toast.LENGTH_LONG).show();  
	            }else{  
	               return false;  
	            }    
	          return true;    
	      }    
    */
}
