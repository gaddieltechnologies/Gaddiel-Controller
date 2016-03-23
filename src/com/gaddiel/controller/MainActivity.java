package com.gaddiel.controller;

import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import com.gaddiel.controllertool.AlertDialogManager;
import com.gaddiel.controllertool.ConnectionDetector;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	Button btnlogin;
	EditText et,pass;
	TextView tv;
	TextView reg;
	HttpPost httppost;
	StringBuffer buffer;
	HttpResponse response;
	HttpClient httpclient;
	List<NameValuePair> nameValuePairs;
	ProgressDialog dialog = null;
	// alert dialog manager
    AlertDialogManager alert = new AlertDialogManager();
    // Internet detector
    ConnectionDetector cd;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		
		cd = new ConnectionDetector(getApplicationContext());
		 
        // Check if Internet present
        if (!cd.isConnectingToInternet()) {
            // Internet Connection is not present
            alert.showAlertDialog(MainActivity.this,
                    "Internet Connection Error",
                    "Please connect to working Internet connection", false);
            // stop executing code by return
            return;
        }
		
		addListenerOnButton();
		btnlogin= (Button)  findViewById(R.id.button1);
        et = (EditText)findViewById(R.id.logPhnu);
        pass= (EditText)findViewById(R.id.logPwd);
        tv = (TextView)findViewById(R.id.login_error);
		
        	btnlogin.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				  dialog = ProgressDialog.show(MainActivity.this, "", 
			                "Validating user...", true);
					 new Thread(new Runnable() {
						    public void run() {
						    	login();					      
						    }
						  }).start();
				
			}
		});
      	
	}
	
	void login(){
		try{			
			//Toast.makeText(MainActivity.this,"Login Success"+et.getText().toString().trim(), Toast.LENGTH_SHORT).show();
			
			
			
			httpclient=new DefaultHttpClient();
			httppost= new HttpPost("http://controller.gaddieltech.com/Login_Registration_php/check.php"); // make sure the url is correct.
			//add your data
			nameValuePairs = new ArrayList<NameValuePair>(2);
			// Always use the same variable name for posting i.e the android side variable name and php side variable name should be similar, 
			nameValuePairs.add(new BasicNameValuePair("username",et.getText().toString().trim()));  // $Edittext_value = $_POST['Edittext_value'];
			nameValuePairs.add(new BasicNameValuePair("password",pass.getText().toString().trim())); 
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			//Execute HTTP Post Request
			response=httpclient.execute(httppost);
			// edited by James from coderzheaven.. from here....
			ResponseHandler<String> responseHandler = new BasicResponseHandler();
			final String response = httpclient.execute(httppost, responseHandler);
			System.out.println("Response : " + response); 
			runOnUiThread(new Runnable() {
			    public void run() {
			    	tv.setText("Response : " + response);
					dialog.dismiss();
			    }
			});
			
			if(response.equalsIgnoreCase("User Found")){
				runOnUiThread(new Runnable() {
				    public void run() {
				    	Toast.makeText(MainActivity.this,"Login Success", Toast.LENGTH_SHORT).show();
				    }
				});
				String mobileno = et.getText().toString();
				Intent trackRemote = new Intent(getApplicationContext(), Tracked_Mobiles.class);
				trackRemote.putExtra("phoneno", mobileno);
				startActivity(trackRemote);
			}else{
				showAlert();				
			}
			
		}catch(Exception e){
			dialog.dismiss();
			System.out.println("Exception : " + e.getMessage());
		}
	}
	
	public void addListenerOnButton() {
		reg = (TextView) findViewById(R.id.textView3);
		btnlogin= (Button)  findViewById(R.id.button1);
		reg.setOnClickListener(new OnClickListener() {
					
			@Override
			public void onClick(View arg0) {			
				Intent reg = new Intent(MainActivity.this, Registration.class);
				startActivity(reg);
			}
		});
		
	}
	
	public void showAlert(){
		MainActivity.this.runOnUiThread(new Runnable() {
		    public void run() {
		    	AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
		    	builder.setTitle("Login Error");
		    	builder.setMessage("Invalid Phone# or PIN.")  
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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	 @Override
	    public boolean onOptionsItemSelected(MenuItem item){
 	   
 	  switch(item.getItemId())
 	  {
 	  case R.id.action_settings:
 		  Intent launchNewIntent = new Intent(MainActivity.this, AboutUs.class);
 	    	startActivityForResult(launchNewIntent, 0);
 	    	break;
 	  		}
		return true;

	 }
}
