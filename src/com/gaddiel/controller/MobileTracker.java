package com.gaddiel.controller;



import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import com.gaddiel.controller.JSONParser;
import com.gaddiel.controllertool.ConnectionDetector;
import com.gaddiel.controllertool.AlertDialogManager;
import org.json.JSONObject;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MobileTracker extends Activity {
	private JSONParser jsonParser=new JSONParser();
	Button addremote;

	String id;
	String cnumber;
	String rnumber;
	String rstatus;
	String rname;	
	String rdesignation;
	String rlocation;
	  AlertDialogManager alert = new AlertDialogManager();
	 ConnectionDetector cd;		
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mobile_track);
		cd = new ConnectionDetector(getApplicationContext());
		 
        // Check if Internet present
        if (!cd.isConnectingToInternet()) {
            // Internet Connection is not present
            alert.showAlertDialog(MobileTracker.this,
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
				Intent reg = getIntent();
		        String mobileno = reg.getStringExtra("phoneno");
		        TextView txtProduct2 = (TextView) findViewById(R.id.cpn);
		        txtProduct2.setText(mobileno);
		
		//addListenerOnButton();
		final TextView t_rcpn=(TextView) findViewById(R.id.cpn);
		final EditText e_rphno=(EditText) findViewById(R.id.rphoneno);
		final EditText e_rname=(EditText) findViewById(R.id.rname);       
        final EditText e_rdesign=(EditText) findViewById(R.id.rdesign);
        final EditText e_rlocation=(EditText) findViewById(R.id.rlocal);
        
        Button insert=(Button) findViewById(R.id.addremoteuser);
        
        insert.setOnClickListener(new View.OnClickListener() {
        
        @Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
        
        	cnumber = t_rcpn.getText().toString();
        	rnumber = e_rphno.getText().toString();
        	if (rnumber.matches("")) {
        	 	showAlert();
        	    return;
        	}
        	rname = e_rname.getText().toString().toUpperCase();	
        	if (rname.matches("")) {
        	 	showAlert();
        	    return;
        	}
			rdesignation = e_rdesign.getText().toString().toUpperCase();
			rlocation = e_rlocation.getText().toString().toUpperCase(); 
			insert();
		}
	});
        
	}
	public void showAlert(){
		MobileTracker.this.runOnUiThread(new Runnable() {
		    public void run() {
		    	AlertDialog.Builder builder = new AlertDialog.Builder(MobileTracker.this);
		    	builder.setTitle("Error");
		    	builder.setMessage("Invalid Name or Phone#.")  
		    	       .setCancelable(false)
		    	       .setPositiveButton("OK", new DialogInterface.OnClickListener() {
		    	           public void onClick(DialogInterface dialog, int id) {
		    	           }
		    	       });		    	       
		    	AlertDialog alert = builder.create();
		    	alert.show();		    	
		    }
		});
	}
	public void insert() {
    	
    	//Toast.makeText( MobileTracker.this, "Hit Save ", Toast.LENGTH_LONG).show();
    	ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
 
     	
   	nameValuePairs.add(new BasicNameValuePair("Tracked_Mobile_Id",""));
	nameValuePairs.add(new BasicNameValuePair("Controller_Phone_Number",cnumber));
	nameValuePairs.add(new BasicNameValuePair("Tracked_Mobile_Number",rnumber));
	nameValuePairs.add(new BasicNameValuePair("Name", rname));
   	nameValuePairs.add(new BasicNameValuePair("Designation",rdesignation));
   	nameValuePairs.add(new BasicNameValuePair("Base_Location",rlocation));
	
	
	
	String registerUrl="http://controller.gaddieltech.com/Addremote/addremotenum.php";
	String json = jsonParser.makeHttpRequest(registerUrl, "POST",nameValuePairs);
	
   // Log.e("pass 1", "connection success ");
    Toast.makeText( MobileTracker.this, "Remote added successfully. Please schedule the tracking", Toast.LENGTH_LONG).show();
    Intent dashBoard = new Intent(getApplicationContext(), Tracked_Mobiles.class);
	//dashBoard.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    dashBoard.putExtra("phoneno", cnumber);
	startActivity(dashBoard);
    this.finish();
	
	}

}
