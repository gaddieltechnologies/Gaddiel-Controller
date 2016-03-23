package com.gaddiel.controller;



import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.regex.Pattern;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;
import com.gaddiel.controller.JSONParser;
import com.gaddiel.controllertool.AlertDialogManager;
import com.gaddiel.controllertool.ConnectionDetector;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;


import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Registration extends Activity {
	
	private JSONParser jsonParser=new JSONParser();
 
	 AlertDialogManager alert = new AlertDialogManager();
	 ConnectionDetector cd;		
	String Name;
	String Controller_Phone_Number; 
	String Password;
	String Confirm_Password;
	String Email;
	String Location;
	String City;
	String State_Province;
	String Country;
	String PIN_ZIP_Code; 
	String Activation_Date;
	public EditText c_Name;
	public EditText c_Controller_Phone_Number;
	public EditText c_Password;
	public EditText c_Confirm_Password;
	public EditText c_Email;
	public final Pattern EMAIL_ADDS_PATTERN=Pattern.compile("[a-zA-Z0-9+._%-+]{1,256}" +
            "@" + "[a-zA-Z0-9][a-zA-Z0-9-]{0,64}" +
            "(" + "." + "[a-zA-Z0-9][a-zA-Z0-9-]{0,25}" +
            ")+");
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.registration);		
		cd = new ConnectionDetector(getApplicationContext());
		 
        // Check if Internet present
        if (!cd.isConnectingToInternet()) {
            // Internet Connection is not present
            alert.showAlertDialog(Registration.this,
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
		 
		final EditText c_Name =(EditText) findViewById(R.id.editText1name);
	    final EditText c_Controller_Phone_Number =(EditText) findViewById(R.id.editText2phone);
	    final EditText c_Password  =(EditText) findViewById(R.id.editText3pin);
	    final EditText c_Confirm_Password =(EditText) findViewById(R.id.editText4cpin);
	    final EditText c_Email =(EditText) findViewById(R.id.editText5mail);
	    final EditText c_Location =(EditText) findViewById(R.id.editText6loca);
	    final EditText c_City =(EditText) findViewById(R.id.editTextCity);
	    final EditText c_State_Province =(EditText) findViewById(R.id.editTextState);
	    final EditText c_Country =(EditText) findViewById(R.id.editTextCountry);
	    final EditText c_PIN_ZIP_Code =(EditText) findViewById(R.id.editTextZipCode);

	    Button insert=(Button) findViewById(R.id.regme);
	        
	        insert.setOnClickListener(new View.OnClickListener() {
	        	@Override
	    		public void onClick(View v) {
	    			// TODO Auto-generated method stub
	    			
	        		Name = c_Name.getText().toString().toUpperCase();
	        		 if (Name.matches("")) {
	        			 showAlert();
	        			 c_Name.setError("please enter the Name");
	             	 	
	             	    return;
	             	}
	        		 Controller_Phone_Number = c_Controller_Phone_Number.getText().toString();
	        		 if (Controller_Phone_Number.matches("")) {
	        			 showAlert();
	        			 c_Controller_Phone_Number.setError("please enter the Phone #");
	             	 	
	             	    return;
	             	}
	        		 Password = c_Password.getText().toString();
	        		 if (Password.matches("")) {
	        			 showAlert();
	        			 c_Password.setError("please enter the Password");
	             	 	
	             	    return;
	             	}
	        		 Confirm_Password = c_Confirm_Password.getText().toString();
	        		 if (Confirm_Password.matches("")) {
	        			 showAlert();
	        			 c_Confirm_Password.setError("please enter the Confirm Password");
	             	 	
	             	    return;
	             	}
	        		 if(Confirm_Password.equalsIgnoreCase(Password)){// It is check the condition
	                      Toast.makeText(Registration.this,"Password Matched",Toast.LENGTH_SHORT).show();
	        		 }else{
	            	 c_Confirm_Password.setError("Password Not Match");
	         
	            	 return;
					}
	        		 Email = c_Email.getText().toString();
	        		 if (Email.matches("")) {
	        			 showAlert();
	        			 c_Email.setError("please enter the Email");
	             	 	
	             	    return;
	             	}
	        		 if(CheckEmail(Email))
	        	       {
	        	         Toast.makeText(Registration.this,"Valid Email Addresss", Toast.LENGTH_SHORT).show();
	        	         //.....................................       
	        	         
	        	          }
	        	      else
	        	          {   
	        	    	  c_Email.setError("Invalid Email Addresss");
	        	            Toast.makeText(Registration.this,"Invalid Email Addresss", Toast.LENGTH_SHORT).show();
	        	            return;
	        	           }
	        	         
	        		 
	        		 
	        		 Location = c_Location.getText().toString().toUpperCase();
	        		 City = c_City.getText().toString().toUpperCase();
	        		 State_Province = c_State_Province.getText().toString().toUpperCase(); 
	        		 Country = c_Country .getText().toString().toUpperCase();
	        		 PIN_ZIP_Code =  c_PIN_ZIP_Code.getText().toString();
	    			 Activation_Date = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
	    			insert();
	    		}
	        	public void showAlert(){
	        		Registration.this.runOnUiThread(new Runnable() {
	        		    public void run() {
	        		    	AlertDialog.Builder builder = new AlertDialog.Builder(Registration.this);
	        		    	builder.setTitle("Error");
	        		    	builder.setMessage("Please enter mandatory fields marked with *")  
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
	        	
	        	  private boolean CheckEmail(String email) {
	        		    // TODO Auto-generated method stub
	        		      return  EMAIL_ADDS_PATTERN.matcher(email).matches();
	        		              }
	    	});
	        }
	     
	        public void insert()
	        {
	        	
	        	//Toast.makeText( Registration.this, "Hit Save ", Toast.LENGTH_LONG).show();
	        	ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
	     
	       	nameValuePairs.add(new BasicNameValuePair("controllerId",""));
	       	nameValuePairs.add(new BasicNameValuePair("Name", Name));
	       	nameValuePairs.add(new BasicNameValuePair("Controller_Phone_Number",Controller_Phone_Number));
	       	nameValuePairs.add(new BasicNameValuePair("Password",Password));
	    	nameValuePairs.add(new BasicNameValuePair("Confirm_Password",Confirm_Password));
	    	nameValuePairs.add(new BasicNameValuePair("Email",Email));
	    	nameValuePairs.add(new BasicNameValuePair("Location",Location));
	       	nameValuePairs.add(new BasicNameValuePair("City", City));
	       	nameValuePairs.add(new BasicNameValuePair("State_Province",State_Province));
	       	nameValuePairs.add(new BasicNameValuePair("Country",Country));
	    	nameValuePairs.add(new BasicNameValuePair("PIN_ZIP_Code",PIN_ZIP_Code));
	    	nameValuePairs.add(new BasicNameValuePair("Activation_Date",Activation_Date));
	    	
	    	String registerUrl="http://gaddieltech.com/controller/Login_Registration_php/register.php";
	    	String json = jsonParser.makeHttpRequest(registerUrl, "POST",nameValuePairs);
	        //JSONObject json = jsonParser.getJSONFromUrl(registerUrl, nameValuePairs);
	        Toast.makeText( Registration.this, "Thanks for Registration.  Your account will be activated within 24 hours.", Toast.LENGTH_LONG).show();
	        Intent dashBoard = new Intent(getApplicationContext(), MainActivity.class);
	    	dashBoard.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	    	startActivity(dashBoard);
	        this.finish();
	        }
	        
	    }