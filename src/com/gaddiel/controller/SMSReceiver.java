package com.gaddiel.controller;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



import android.content.*;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.*;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
 
public class SMSReceiver  extends BroadcastReceiver  

{
	private static final String TAG = "Message recieved";

	private JSONParser jsonParser=new JSONParser();
	
	 @Override
	 public void onReceive(Context context, Intent intent) 
	 {    
	     Bundle pudsBundle = intent.getExtras();
	     Object[] pdus = (Object[]) pudsBundle.get("pdus");
	     SmsMessage messages =SmsMessage.createFromPdu((byte[]) pdus[0]);    
	     Log.i(TAG,  messages.getMessageBody());
	     
	     String mydate = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());

	     SimpleDateFormat sdfDateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
	     String newtime =  sdfDateTime.format(new Date(System.currentTimeMillis()));
	     String gettime= sdfDateTime.format(new Date(messages.getTimestampMillis()));
	     //Toast.makeText(context, "MY DATE : "+mydate,Toast.LENGTH_LONG).show();
	     
	     
	     //Toast.makeText(context, "NEW TIME : "+newtime,Toast.LENGTH_LONG).show();
	     //Toast.makeText(context, "GET TIME : "+gettime,Toast.LENGTH_LONG).show();
	     
	  // Toast.makeText(context, "SMS Received : "+messages.getMessageBody(),Toast.LENGTH_LONG).show();
	     
	  // Toast.makeText(context, "Message From:"+messages.getOriginatingAddress(),    Toast.LENGTH_LONG).show();
	     
	     if(messages.getMessageBody().equalsIgnoreCase("yes"))
	     {
	    	 Toast.makeText(context, "Received a confirmation", Toast.LENGTH_LONG).show();
	     }
	           
	     if(messages.getMessageBody().startsWith("http://maps.google.com/map"))
         {
	    	// abortBroadcast();
	          // declare parameters that are passed to PHP script i.e. the name "birthyear" and its value submitted by user   
	          ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
	          
	          // define the parameter
	          postParameters.add(new BasicNameValuePair("response",""));
	          postParameters.add(new BasicNameValuePair("sender", messages.getOriginatingAddress()));
	          postParameters.add(new BasicNameValuePair("status",messages.getMessageBody()));
	          postParameters.add(new BasicNameValuePair("RTime",mydate));
	          String response = null;
	          String registerUrl="http://gaddieltech.com/controller/Remote_Location/saveLocation.php";
	        //  JSONObject json = jsonParser.getJSONFromUrl(registerUrl, postParameters);
	      	  String json = jsonParser.makeHttpRequest(registerUrl, "POST",postParameters);
	          String specificPhoneNumber = messages.getOriginatingAddress();

	          String addr = messages.getOriginatingAddress().trim();  // to get the SMS Number
	          
	       //   Toast.makeText(context, " Trim number Message From :"+addr,    Toast.LENGTH_LONG).show();
        	  
        	  //Toast.makeText(context, " Sender Device number :"+specificPhoneNumber,    Toast.LENGTH_LONG).show();
	          
	          if ( addr.trim().equals ( specificPhoneNumber.trim() ))
	          {
	        	  
	        	  //Toast.makeText(context, " Trim number :"+addr,    Toast.LENGTH_LONG).show();
	        	  
	        	 // Toast.makeText(context, " Sender Device number :"+specificPhoneNumber,    Toast.LENGTH_LONG).show();
	                // delete SMS
	        	  //Uri SMS_INBOX=Uri.parse("content://sms/inbox");
	                //Cursor c = context.getContentResolver().query(SMS_INBOX, null, null, null, null);
	                //if(c!=null && c.moveToFirst())
	                /*{
	                	do {
	                int pid = c.getInt(1);
	                String uri = "content://sms/conversations/" + pid;
	                context.getContentResolver().delete(Uri.parse(uri), null, null);
	                }while(c.moveToNext());
	                	}*/
	          }
         }
	 }
}
