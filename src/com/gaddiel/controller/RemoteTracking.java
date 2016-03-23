package com.gaddiel.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.telephony.SmsManager;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import com.gaddiel.controller.JSONParser; 

public class RemoteTracking extends Activity {
	
	private JSONParser jsonParser=new JSONParser();
	TextView name;
	TextView num;
	EditText startime;
	EditText endtime;
	EditText customize;
	private Calendar cal;
	 private int hour;
	 private int min;
	Button start;
	Button stop;
	Bundle bundle=new Bundle();
	Spinner spinner;
	ArrayAdapter<CharSequence> adapter;
	String comm;
	CheckBox mon;
	CheckBox tue;
	CheckBox wed;
	CheckBox thu;
	CheckBox fri;
	CheckBox sat;
	CheckBox sun;
	CheckBox repeat;
	int daysone;
	int daystwo;
	int daysthree;
	int daysfour;
	int daysfive;
	int dayssix;
	int daysseven;
	int days;
	TextView ph;
	int phone;
	String comma;
	int pos;
	String poss;
	
	String tsId;
	String tmId;
	String status;
	String startTime;
	String endTime;
	String monday;
	String tuesday;
	String wednesday;
	String thursday;
	String friday;
	String saturday;
	String sunday;
	String rOption;
	String freq;
	String btnStart;
	String btnStop;
	String createdBy;
	String updatedBy;
	String createdDate;
	String updatedDate;
	String crBy;
	String tsid;
	EditText predisp;
	String numbs;
	String numb;
	
	
	 
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.remote_tracking);
		
		predisp=(EditText) findViewById(R.id.editpredefine);
		name=(TextView) findViewById(R.id.textView2);
		num=(TextView) findViewById(R.id.textView4);
		start=(Button) findViewById(R.id.button1);
		 stop=(Button) findViewById(R.id.button2);
		spinner = (Spinner) findViewById(R.id.spinnerfrequ);
		// Create an ArrayAdapter using the string array and a default spinner layout
		adapter = ArrayAdapter.createFromResource(this,
		        R.array.remote_array, android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		spinner.setAdapter(adapter);
		bundle=getIntent().getExtras();
		pos=bundle.getInt("id");
		poss=Integer.toString(pos);
		String state=bundle.getString("state");
		String mobile=bundle.getString("mobile");
		String mob=bundle.getString("mob");
		crBy=bundle.getString("contmob");
		updatedBy=bundle.getString("contmob");
		name.setText(mobile);
		num.setText(mob);
		if(state.equals("Tracker OFF")){
			stop.setVisibility(View.GONE);
			predisp.setVisibility(View.GONE);
			startime=(EditText) findViewById(R.id.editText1);
			endtime=(EditText) findViewById(R.id.editText2);
			InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
			imm.hideSoftInputFromWindow(startime.getWindowToken(), 0);
			imm.hideSoftInputFromWindow(endtime.getWindowToken(), 0);
		}
		if(state.equals("Tracker ON")){
			startime=(EditText) findViewById(R.id.editText1);
			endtime=(EditText) findViewById(R.id.editText2);
			customize=(EditText) findViewById(R.id.editText3);
			start.setVisibility(View.GONE);
			spinner.setVisibility(View.GONE);
			predisp.setEnabled(false);
			startime.setEnabled(false);
			endtime.setEnabled(false);
			customize.setEnabled(false);
			InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
			imm.hideSoftInputFromWindow(startime.getWindowToken(), 0);
			
			imm.hideSoftInputFromWindow(endtime.getWindowToken(), 1);
		Intent trackRemote = getIntent();
		tsid= trackRemote.getStringExtra("tstoid");
        String tsgetStart_Time = trackRemote.getStringExtra("tstoStart_Time");
        String tsgetEnd_Time = trackRemote.getStringExtra("tstoEnd_Time");
        String tsgetMonday = trackRemote.getStringExtra("tstoMonday");
        String tsgetTuesday = trackRemote.getStringExtra("tstoTuesday");
        String tsgetWednesday = trackRemote.getStringExtra("tstoWednesday");
        String tsgetThursday = trackRemote.getStringExtra("tstoThursday");
        String tsgetFriday = trackRemote.getStringExtra("tstoFriday");
        String tsgetSaturday = trackRemote.getStringExtra("tstoSaturday");
        String tsgetSunday = trackRemote.getStringExtra("tstoSunday");
        String tsgetRepeat_option = trackRemote.getStringExtra("tstoRepeat_option");
        String tsgetFrequency = trackRemote.getStringExtra("tstoFrequency");
        String tsgetStart = trackRemote.getStringExtra("tstoStart");
        String tsgetStop = trackRemote.getStringExtra("tstoStop");
        String tsgetCreatedBy = trackRemote.getStringExtra("tstoCreatedBy");
        String tsgetCreatedDate = trackRemote.getStringExtra("tstoCreatedDate");
        
        
        TextView txtProduct1 = (TextView) findViewById(R.id.editText1);
        TextView txtProduct2 = (TextView) findViewById(R.id.editText2);
        CheckBox txtProduct3 = (CheckBox) findViewById(R.id.checkBox1);
        CheckBox txtProduct4 = (CheckBox) findViewById(R.id.checkBox2);
        CheckBox txtProduct5 = (CheckBox) findViewById(R.id.checkBox3);
        CheckBox txtProduct6 = (CheckBox) findViewById(R.id.checkBox4);
        CheckBox txtProduct7 = (CheckBox) findViewById(R.id.checkBox5);
        CheckBox txtProduct8 = (CheckBox) findViewById(R.id.checkBox6);
        CheckBox txtProduct9 = (CheckBox) findViewById(R.id.checkBox7);
        CheckBox txtProduct10 = (CheckBox) findViewById(R.id.checkBox8);
     //   Spinner txtProduct11 =(Spinner) findViewById(R.id.spinnerfrequ);
        TextView txtProduct12 = (TextView) findViewById(R.id.editText3);
                
        txtProduct1.setText(tsgetStart_Time);
        txtProduct2.setText(tsgetEnd_Time);
        
        if(tsgetMonday.equals("Y")){
        	txtProduct3.setChecked(true);
        }
        if(tsgetTuesday.equals("Y")){
        	txtProduct4.setChecked(true);
        }
        if(tsgetWednesday.equals("Y")){
        	txtProduct5.setChecked(true);
        }
        if(tsgetThursday.equals("Y")){
        	txtProduct6.setChecked(true);
        }
        if(tsgetFriday.equals("Y")){
        	txtProduct7.setChecked(true);
        }
        if(tsgetSaturday.equals("Y")){
        	txtProduct8.setChecked(true);
        }
        if(tsgetSunday.equals("Y")){
        	txtProduct9.setChecked(true);
        }
        if(tsgetRepeat_option.equals("Y")){
        	txtProduct10.setChecked(true);
        }
      
        /*txtProduct3.setText(tsgetMonday);
        txtProduct4.setText(tsgetTuesday);
        txtProduct5.setText(tsgetWednesday);
        txtProduct6.setText(tsgetThursday);
        txtProduct7.setText(tsgetFriday);
        txtProduct8.setText(tsgetSaturday);
        txtProduct9.setText(tsgetSunday);
        txtProduct10.setText(tsgetRepeat_option);*/
      //  txtProduct11.setText(tsgetEnd_Time);
        if(tsgetFrequency.startsWith("MINUTES")){
        	//Toast.makeText(RemoteTracking.this, "Spinner state " + adapter.getPosition("MINUTES"), Toast.LENGTH_LONG).show();
        	spinner.setSelection(0);
        	predisp.setText("MINUTES");
        }
        if(tsgetFrequency.startsWith("SECONDS")){
        	//Toast.makeText(RemoteTracking.this, "Spinner state " + adapter.getPosition("SECONDS"), Toast.LENGTH_LONG).show();
        	spinner.setSelection(1);
        	predisp.setText("SECONDS");
        }
        if(tsgetFrequency.startsWith("HOURS")){
        	//Toast.makeText(RemoteTracking.this, "Spinner state " + adapter.getPosition("HOURS"), Toast.LENGTH_LONG).show();
        	spinner.setSelection(2);
        	predisp.setText("HOURS");
        }
        String numberOnly= tsgetFrequency.replaceAll("[^0-9]", "");
     	//long num=Long.parseLong(numberOnly);
        txtProduct12.setText(numberOnly);
		}
		//Toast.makeText(RemoteTracking.this, "Status Remote is " + state, Toast.LENGTH_LONG).show();
		
		if(state.equals("Y")){
			start.setEnabled(false);
			stop.setEnabled(true);
		}
		if(state.equals("N")){
			start.setEnabled(true);
			stop.setEnabled(false);
		}
		
		//Toast.makeText(RemoteTracking.this, "Position Id is " + pos, Toast.LENGTH_LONG).show();
		
		phone=pos;
		
		spinner.setOnItemSelectedListener(new OnItemSelectedListener(){

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				if(spinner.getAdapter().equals("MINUTES")){
					comm="#min";
				}
				if(spinner.getAdapter().equals("SECONDS")){
					comm="#sec";
				}
				if(spinner.getAdapter().equals("HOURS")){
					comm="#hrs";
				}
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				spinner.getSelectedItem().equals("MINUTES");
				comm="#min";
				
			}
			
		});
		
		cal = Calendar.getInstance();
		  hour = cal.get(Calendar.HOUR_OF_DAY);
		  min = cal.get(Calendar.MINUTE);
		startime= (EditText) findViewById(R.id.editText1);
		endtime= (EditText) findViewById(R.id.editText2);
		mon=(CheckBox) findViewById(R.id.checkBox1);
		tue=(CheckBox) findViewById(R.id.checkBox2);
		wed=(CheckBox) findViewById(R.id.checkBox3);
		thu=(CheckBox) findViewById(R.id.checkBox4);
		fri=(CheckBox) findViewById(R.id.checkBox5);
		sat=(CheckBox) findViewById(R.id.checkBox6);
		sun=(CheckBox) findViewById(R.id.checkBox7);
		repeat=(CheckBox) findViewById(R.id.checkBox8);
		mon.setEnabled(false);
		tue.setEnabled(false);
		wed.setEnabled(false);
		thu.setEnabled(false);
		fri.setEnabled(false);
		sat.setEnabled(false);
		sun.setEnabled(false);
		repeat.setEnabled(false);
		
		addListenerOnButton();
		
		  startime.setOnClickListener(new OnClickListener()
			{
				 
				 
				

	@Override
	public void onClick(View v) {
		
		showDialog(0);
		mon.setEnabled(true);
		tue.setEnabled(true);
		wed.setEnabled(true);
		thu.setEnabled(true);
		fri.setEnabled(true);
		sat.setEnabled(true);
		sun.setEnabled(true);
		
		
		
	}});
			endtime.setOnClickListener(new OnClickListener()
			{
			 
		 
	

	@Override
	public void onClick(View v) {
		
		showDialog(0);
	}});
		
	mon.setOnClickListener(new OnClickListener(){

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			repeat.setEnabled(true);
			
		}
		
	});		
	tue.setOnClickListener(new OnClickListener(){

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			repeat.setEnabled(true);
			
		}
		
	});	
	wed.setOnClickListener(new OnClickListener(){

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			repeat.setEnabled(true);
			
		}
		
	});	
	thu.setOnClickListener(new OnClickListener(){

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			repeat.setEnabled(true);
			
		}
		
	});	
	fri.setOnClickListener(new OnClickListener(){

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			repeat.setEnabled(true);
			
		}
		
	});	
	sat.setOnClickListener(new OnClickListener(){

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			repeat.setEnabled(true);
			
		}
		
	});	
	sun.setOnClickListener(new OnClickListener(){

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			repeat.setEnabled(true);
			
		}
		
	});	
	
	}

	 @Override
	 @Deprecated
	 protected Dialog onCreateDialog(int id) {
	  return new TimePickerDialog(this, timePickerListener, hour, min, false);
	 }

	 private TimePickerDialog.OnTimeSetListener timePickerListener = new TimePickerDialog.OnTimeSetListener() {
	  @Override
	  public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
		  if(startime.hasFocus()){
	   int hour;
	   hour=hourOfDay;
	   String am_pm;
	   if (hourOfDay > 12) {
	    
	    am_pm = "PM";
	   } else {
	    hour = hourOfDay;
	    am_pm = "AM";
	   }
	   startime.setText(hour + ":" + minute);}
		  if(endtime.hasFocus()){
			  int hour;
			  hour=hourOfDay;
			   String am_pm;
			   if (hourOfDay > 12) {
			    
			    am_pm = "PM";
			   } else {
			    hour = hourOfDay;
			    am_pm = "AM";
			   }
	   endtime.setText(hour + ":" + minute);
		  }
		  
		  bundle.putLong("hour", hour);
		  bundle.putLong("minute", minute);
		  
	  }
	 };


	 public void addListenerOnButton(){
		 name=(TextView) findViewById(R.id.textView2);
			num=(TextView) findViewById(R.id.textView4);
		 ph=(TextView) findViewById(R.id.textView4);
		 start=(Button) findViewById(R.id.button1);
		 stop=(Button) findViewById(R.id.button2);
		 startime=(EditText) findViewById(R.id.editText1);
		 endtime=(EditText) findViewById(R.id.editText2);
		 spinner = (Spinner) findViewById(R.id.spinnerfrequ);
			// Create an ArrayAdapter using the string array and a default spinner layout
			adapter = ArrayAdapter.createFromResource(this,
			        R.array.remote_array, android.R.layout.simple_spinner_item);
			// Specify the layout to use when the list of choices appears
			adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			// Apply the adapter to the spinner
			spinner.setAdapter(adapter);
			
		 
		start.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				stop.setEnabled(true);
				Calendar calendar = Calendar.getInstance();
				
				int day=calendar.get(Calendar.DAY_OF_WEEK);
				String asd=Integer.toString(day);
				//Toast.makeText(getApplicationContext(),"Day of the week"+ asd, Toast.LENGTH_LONG).show();
				mon=(CheckBox) findViewById(R.id.checkBox1);
				tue=(CheckBox) findViewById(R.id.checkBox2);
				wed=(CheckBox) findViewById(R.id.checkBox3);
				thu=(CheckBox) findViewById(R.id.checkBox4);
				fri=(CheckBox) findViewById(R.id.checkBox5);
				sat=(CheckBox) findViewById(R.id.checkBox6);
				sun=(CheckBox) findViewById(R.id.checkBox7);
				repeat=(CheckBox) findViewById(R.id.checkBox8);
				if((mon.isChecked())||(tue.isChecked())||(wed.isChecked())||(thu.isChecked())||(fri.isChecked())||(sat.isChecked())||(sun.isChecked())){
					//Toast.makeText(RemoteTracking.this, "Is it hitting the if condition???? ", Toast.LENGTH_LONG).show();
					Intent intentAlarm = new Intent(getApplicationContext(), AlarmReceiver.class);
					
					String time=startime.getText().toString();
					
					if(spinner.getSelectedItem().equals("MINUTES")){
						comm="#min";
					}
					if(spinner.getSelectedItem().equals("SECONDS")){
						comm="#sec";
					}
					if(spinner.getSelectedItem().equals("HOURS")){
						comm="#hrs";
					}
					customize=(EditText) findViewById(R.id.editText3);
					String command=comm+customize.getText().toString();
					
					intentAlarm.putExtra("message", command);
					numb=num.getText().toString();
					intentAlarm.putExtra("phn", numb);
					
					AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
					
 
			        String[] hrs=time.split(":");
			        int hr=Integer.parseInt(hrs[0]);
			        int min=Integer.parseInt(hrs[1]);
			        if(mon.isChecked()){
					days=2;
					daystwo=2;
					Calendar cals=Calendar.getInstance();
		        	cals.setTimeInMillis(System.currentTimeMillis());
		        	cals.set(Calendar.DAY_OF_WEEK, 2);
		        	cals.set(Calendar.HOUR_OF_DAY, hr);
		        	cals.set(Calendar.MINUTE, min);
		        	cals.set(Calendar.SECOND, 1);
		        	PendingIntent pIntentmone =  PendingIntent.getBroadcast(getApplicationContext(), phone+1,  intentAlarm, 0);
		        	if(repeat.isChecked()==true){
		        	alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, cals.getTimeInMillis(), 7*AlarmManager.INTERVAL_DAY,pIntentmone);
		        	}
		        	if(repeat.isChecked()==false){
		        		alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, cals.getTimeInMillis(), 0,pIntentmone);
			        		
		        	}
				}
				if(tue.isChecked()){
					days=3;
					daysthree=3;
					Calendar cals=Calendar.getInstance();
		        	cals.setTimeInMillis(System.currentTimeMillis());
		        	cals.set(Calendar.DAY_OF_WEEK, 3);
		        	cals.set(Calendar.HOUR_OF_DAY, hr);
		        	cals.set(Calendar.MINUTE, min);
		        	cals.set(Calendar.SECOND, 1);
		        	PendingIntent pIntentmtwo =  PendingIntent.getBroadcast(getApplicationContext(), phone+2,  intentAlarm, 0);
		        	if(repeat.isChecked()==true){
		        	alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, cals.getTimeInMillis(), 7*AlarmManager.INTERVAL_DAY,pIntentmtwo);
		        	}
		        	if(repeat.isChecked()==false){
		        		alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, cals.getTimeInMillis(), 0,pIntentmtwo);
			        		
		        	}
				}
				if(wed.isChecked()){
					days=4;
					daysfour=4;
					Calendar cals=Calendar.getInstance();
		        	cals.setTimeInMillis(System.currentTimeMillis());
		        	cals.set(Calendar.DAY_OF_WEEK, 4);
		        	cals.set(Calendar.HOUR_OF_DAY, hr);
		        	cals.set(Calendar.MINUTE, min);
		        	cals.set(Calendar.SECOND, 1);
		        	PendingIntent pIntentmthree =  PendingIntent.getBroadcast(getApplicationContext(), phone+3,  intentAlarm, 0);
		        	if(repeat.isChecked()==true){
		        	alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, cals.getTimeInMillis(), 7*AlarmManager.INTERVAL_DAY,pIntentmthree);
		        	}
		        	if(repeat.isChecked()==false){
		        		alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, cals.getTimeInMillis(), 0,pIntentmthree);
			        		
		        	}
				}
				if(thu.isChecked()){
					days=5;
					daysfive=5;
					Calendar cals=Calendar.getInstance();
		        	cals.setTimeInMillis(System.currentTimeMillis());
		        	cals.set(Calendar.DAY_OF_WEEK, 5);
		        	cals.set(Calendar.HOUR_OF_DAY, hr);
		        	cals.set(Calendar.MINUTE, min);
		        	cals.set(Calendar.SECOND, 1);
		        	PendingIntent pIntentmfour =  PendingIntent.getBroadcast(getApplicationContext(), phone+4,  intentAlarm, 0);
		        	if(repeat.isChecked()==true){
		        	alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, cals.getTimeInMillis(), 7*AlarmManager.INTERVAL_DAY,pIntentmfour);
		        	}
		        	if(repeat.isChecked()==false){
		        		alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, cals.getTimeInMillis(), 0,pIntentmfour);
			        		
		        	}
				}
				if(fri.isChecked()){
					days=6;
					dayssix=6;
					Calendar cals=Calendar.getInstance();
		        	cals.setTimeInMillis(System.currentTimeMillis());
		        	cals.set(Calendar.DAY_OF_WEEK, 6);
		        	cals.set(Calendar.HOUR_OF_DAY, hr);
		        	cals.set(Calendar.MINUTE, min);
		        	cals.set(Calendar.SECOND, 1);
		        	PendingIntent pIntentmfive =  PendingIntent.getBroadcast(getApplicationContext(), phone+5,  intentAlarm, 0);
		        	if(repeat.isChecked()==true){
		        	alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, cals.getTimeInMillis(), 7*AlarmManager.INTERVAL_DAY,pIntentmfive);
		        	}
		        	if(repeat.isChecked()==false){
		        		alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, cals.getTimeInMillis(), 0,pIntentmfive);
			        		
		        	}
				}
				if(sat.isChecked()){
					days=7;
					daysseven=7;
					Calendar cals=Calendar.getInstance();
		        	cals.setTimeInMillis(System.currentTimeMillis());
		        	cals.set(Calendar.DAY_OF_WEEK, 7);
		        	cals.set(Calendar.HOUR_OF_DAY, hr);
		        	cals.set(Calendar.MINUTE, min);
		        	cals.set(Calendar.SECOND, 1);
		        	PendingIntent pIntentmsix =  PendingIntent.getBroadcast(getApplicationContext(), phone+6,  intentAlarm, 0);
		        	if(repeat.isChecked()==true){
		        	alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, cals.getTimeInMillis(), 7*AlarmManager.INTERVAL_DAY,pIntentmsix);
		        	}
		        	if(repeat.isChecked()==false){
		        		alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, cals.getTimeInMillis(), 0,pIntentmsix);
			        		
		        	}
				}
				/*if(sun.isChecked()){
					if(day==1){
					days=1;
					daysone=1;
					Calendar cals=Calendar.getInstance();
		        	cals.setTimeInMillis(System.currentTimeMillis());
		        	
		        	cals.set(Calendar.HOUR_OF_DAY, hr);
		        	cals.set(Calendar.MINUTE, min);
		        	cals.set(Calendar.SECOND, 1);
		        	PendingIntent pIntentmseven =  PendingIntent.getBroadcast(getApplicationContext(), phone+7,  intentAlarm, 0);
		        	if(repeat.isChecked()==true){
		        	alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, cals.getTimeInMillis(), 7*AlarmManager.INTERVAL_DAY,pIntentmseven);
		        	}
		        	if(repeat.isChecked()==false){
		        		alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, cals.getTimeInMillis(), 0,pIntentmseven);
			        		
		        	}}
				}*/
				//Toast.makeText(RemoteTracking.this, "what is the day? "+day, Toast.LENGTH_LONG).show();
				//Toast.makeText(RemoteTracking.this, "what is the dayss???? "+ days, Toast.LENGTH_LONG).show();

				        if(!endtime.getText().toString().equals("")){
					        Intent intentAlarms = new Intent(getApplicationContext(), AlarmReceiver1.class);
					        
					        String commands="gaddielTrack#Stop";
							intentAlarms.putExtra("message", commands);
							numbs=num.getText().toString();
							intentAlarms.putExtra("phn", numbs);
							AlarmManager alarmManagers = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
					        
					        String times=endtime.getText().toString();
					        
					        String[] hrss=times.split(":");
					        int hrr=Integer.parseInt(hrss[0]);
					        int minn=Integer.parseInt(hrss[1]);
					        
					        
					        
					        if(mon.isChecked()){
					        	Calendar calendars = Calendar.getInstance();
						        calendars.setTimeInMillis(System.currentTimeMillis());
						        calendars.set(Calendar.DAY_OF_WEEK, 2);
						        calendars.set(Calendar.HOUR_OF_DAY, hrr);
						        calendars.set(Calendar.MINUTE, minn);
						        calendars.set(Calendar.SECOND, 1);
					        PendingIntent pIntents =  PendingIntent.getBroadcast(getApplicationContext(), phone+8,  intentAlarms, 0);
					        
					        
					       // Toast.makeText(RemoteTracking.this, "Alarm scheduled for " + hrr + minn, Toast.LENGTH_LONG).show();
					        if(repeat.isChecked()){
					        alarmManagers.setRepeating(AlarmManager.RTC_WAKEUP, calendars.getTimeInMillis(), 7*AlarmManager.INTERVAL_DAY,pIntents);
					        }
					        if(!repeat.isChecked()){
						        alarmManagers.setRepeating(AlarmManager.RTC_WAKEUP, calendars.getTimeInMillis(), 0,pIntents);
						        }
					        }
					         if(tue.isChecked()){
					        	Calendar calendars = Calendar.getInstance();
						        calendars.setTimeInMillis(System.currentTimeMillis());
						        calendars.set(Calendar.DAY_OF_WEEK, 3);
						        calendars.set(Calendar.HOUR_OF_DAY, hrr);
						        calendars.set(Calendar.MINUTE, minn);
						        calendars.set(Calendar.SECOND, 1);
						        PendingIntent pIntents =  PendingIntent.getBroadcast(getApplicationContext(), phone+9,  intentAlarms, 0);
						        
						        
						     //   Toast.makeText(RemoteTracking.this, "Alarm scheduled for " + hrr + minn, Toast.LENGTH_LONG).show();
						        if(repeat.isChecked()){
						        alarmManagers.setRepeating(AlarmManager.RTC_WAKEUP, calendars.getTimeInMillis(), 7*AlarmManager.INTERVAL_DAY,pIntents);
						        }
						        if(!repeat.isChecked()){
							        alarmManagers.setRepeating(AlarmManager.RTC_WAKEUP, calendars.getTimeInMillis(), 0,pIntents);
							        }
						        }
					        if(wed.isChecked()){
					        	Calendar calendars = Calendar.getInstance();
						        calendars.setTimeInMillis(System.currentTimeMillis());
						        calendars.set(Calendar.DAY_OF_WEEK, 4);
						        calendars.set(Calendar.HOUR_OF_DAY, hrr);
						        calendars.set(Calendar.MINUTE, minn);
						        calendars.set(Calendar.SECOND, 1);
						        PendingIntent pIntents =  PendingIntent.getBroadcast(getApplicationContext(), phone+10,  intentAlarms, 0);
						        
						        
						       // Toast.makeText(RemoteTracking.this, "Alarm scheduled for " + hrr + minn, Toast.LENGTH_LONG).show();
						        if(repeat.isChecked()){
						        alarmManagers.setRepeating(AlarmManager.RTC_WAKEUP, calendars.getTimeInMillis(), 7*AlarmManager.INTERVAL_DAY,pIntents);
						        }
						        if(!repeat.isChecked()){
							        alarmManagers.setRepeating(AlarmManager.RTC_WAKEUP, calendars.getTimeInMillis(), 0,pIntents);
							        }
						        }
					        if(thu.isChecked()){
					        	Calendar calendars = Calendar.getInstance();
						        calendars.setTimeInMillis(System.currentTimeMillis());
						        calendars.set(Calendar.DAY_OF_WEEK, 5);
						        calendars.set(Calendar.HOUR_OF_DAY, hrr);
						        calendars.set(Calendar.MINUTE, minn);
						        calendars.set(Calendar.SECOND, 1);
						        PendingIntent pIntents =  PendingIntent.getBroadcast(getApplicationContext(), phone+11,  intentAlarms, 0);
						        
						        
						       // Toast.makeText(RemoteTracking.this, "Alarm scheduled for " + hrr + minn, Toast.LENGTH_LONG).show();
						        if(repeat.isChecked()){
						        alarmManagers.setRepeating(AlarmManager.RTC_WAKEUP, calendars.getTimeInMillis(), 7*AlarmManager.INTERVAL_DAY,pIntents);
						        }
						        if(!repeat.isChecked()){
							        alarmManagers.setRepeating(AlarmManager.RTC_WAKEUP, calendars.getTimeInMillis(), 0,pIntents);
							        }
						        }
					        if(fri.isChecked()){
					        	Calendar calendars = Calendar.getInstance();
						        calendars.setTimeInMillis(System.currentTimeMillis());
						        calendars.set(Calendar.DAY_OF_WEEK, 6);
						        calendars.set(Calendar.HOUR_OF_DAY, hrr);
						        calendars.set(Calendar.MINUTE, minn);
						        calendars.set(Calendar.SECOND, 1);
						        PendingIntent pIntents =  PendingIntent.getBroadcast(getApplicationContext(), phone+12,  intentAlarms, 0);
						        
						        
						      //  Toast.makeText(RemoteTracking.this, "Alarm scheduled for " + hrr + minn, Toast.LENGTH_LONG).show();
						        if(repeat.isChecked()){
						        alarmManagers.setRepeating(AlarmManager.RTC_WAKEUP, calendars.getTimeInMillis(), 7*AlarmManager.INTERVAL_DAY,pIntents);
						        }
						        if(!repeat.isChecked()){
							        alarmManagers.setRepeating(AlarmManager.RTC_WAKEUP, calendars.getTimeInMillis(), 0,pIntents);
							        }
						        }
					         if(sat.isChecked()){
					        	 Calendar calendars = Calendar.getInstance();
							        calendars.setTimeInMillis(System.currentTimeMillis());
							        calendars.set(Calendar.DAY_OF_WEEK, 7);
							        calendars.set(Calendar.HOUR_OF_DAY, hrr);
							        calendars.set(Calendar.MINUTE, minn);
							        calendars.set(Calendar.SECOND, 1);
						        PendingIntent pIntents =  PendingIntent.getBroadcast(getApplicationContext(), phone+13,  intentAlarms, 0);
						       
						        
						       // Toast.makeText(RemoteTracking.this, "Alarm scheduled for " + hrr + minn, Toast.LENGTH_LONG).show();
						        if(repeat.isChecked()){
						        alarmManagers.setRepeating(AlarmManager.RTC_WAKEUP, calendars.getTimeInMillis(), 7*AlarmManager.INTERVAL_DAY,pIntents);
						        }
						        if(!repeat.isChecked()){
							        alarmManagers.setRepeating(AlarmManager.RTC_WAKEUP, calendars.getTimeInMillis(), 0,pIntents);
							        }
						        }
					         /*    if(sun.isChecked()){
					        	if(day==1){
					        		Calendar calendars = Calendar.getInstance();
							        calendars.setTimeInMillis(System.currentTimeMillis());
							        
							        calendars.set(Calendar.HOUR_OF_DAY, hrr);
							        calendars.set(Calendar.MINUTE, minn);
							        calendars.set(Calendar.SECOND, 1);
						        PendingIntent pIntents =  PendingIntent.getBroadcast(getApplicationContext(), phone+14,  intentAlarms, 0);
						        
						        
						        Toast.makeText(RemoteTracking.this, "Alarm scheduled for " + hrr + minn, Toast.LENGTH_LONG).show();
						        if(repeat.isChecked()){
						        alarmManagers.setRepeating(AlarmManager.RTC_WAKEUP, calendars.getTimeInMillis(), 7*AlarmManager.INTERVAL_DAY,pIntents);
						        }
						        if(!repeat.isChecked()){
							        alarmManagers.setRepeating(AlarmManager.RTC_WAKEUP, calendars.getTimeInMillis(), 0,pIntents);
							        }
					        	}}*/
					        }
				 
				}
				
				if((mon.isChecked()==false)&&(tue.isChecked()==false)&&(wed.isChecked()==false)&&(thu.isChecked()==false)&&(fri.isChecked()==false)&&(sat.isChecked()==false)&&(sun.isChecked()==false)&&(!startime.getText().toString().equals(""))){
					
				spinner = (Spinner) findViewById(R.id.spinnerfrequ);
				
				Intent intentAlarm = new Intent(getApplicationContext(), AlarmReceiver.class);
				if(spinner.getSelectedItem().equals("MINUTES")){
					comm="#min";
				}
				if(spinner.getSelectedItem().equals("SECONDS")){
					comm="#sec";
				}
				if(spinner.getSelectedItem().equals("HOURS")){
					comm="#hrs";
				}
				customize=(EditText) findViewById(R.id.editText3);
				String command=comm+customize.getText().toString();
				intentAlarm.putExtra("message", command);
				numb=num.getText().toString();
				intentAlarm.putExtra("phn", numb);
		        PendingIntent pIntent =  PendingIntent.getBroadcast(getApplicationContext(), phone,  intentAlarm, 0);

		        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
		        
		        String time=startime.getText().toString();
		        
		        String[] hrs=time.split(":");
		        int hr=Integer.parseInt(hrs[0]);
		        int min=Integer.parseInt(hrs[1]);
		        
		        //Toast.makeText(RemoteTracking.this, "hrs[0]" + hr, Toast.LENGTH_LONG).show();
		        //Toast.makeText(RemoteTracking.this, "hrs[1]" + min, Toast.LENGTH_LONG).show();
		        
		        long milmin=min*60000;
		        long milhr=hr*3600*1000;
		        
		          long millisec=milhr+milmin;
		        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
		        String curtime=format.format(calendar.getTime());
		        String[] ct=curtime.split(":");
		        String ct1=ct[0];
		        String[] ct2=ct[1].split(":");
		        String ct3=ct[0];
		        int cthr=Integer.parseInt(ct1);
		       
		        int ctmin=Integer.parseInt(ct3);
		        long cthrmil=cthr*3600*1000;
		        long ctminmil=ctmin*60*1000;
		        
		           long curmil=cthrmil+ctminmil;
		        
		             long millireq=millisec-curmil;
		        
		        calendar.setTimeInMillis(System.currentTimeMillis());
		        calendar.set(Calendar.HOUR_OF_DAY, hr);
		        calendar.set(Calendar.MINUTE, min);
		        calendar.set(Calendar.SECOND, 1);
		        
		       // Toast.makeText(RemoteTracking.this, "Alarm scheduled for " + calendar.getTimeInMillis(), Toast.LENGTH_LONG).show();
		        
		        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pIntent);
		        //Toast.makeText(this, ("Alarm scheduled for " + month + "/" + day + " at " + time + "pm"), Toast.LENGTH_LONG).show();
		        if(!endtime.getText().toString().equals("")){
		        Intent intentAlarms = new Intent(getApplicationContext(), AlarmReceiver1.class);
		        
		        String commands="gaddielTrack#Stop";
				intentAlarms.putExtra("message", commands);
				numbs=num.getText().toString();
				intentAlarms.putExtra("phn", numbs);
		        
		        PendingIntent pIntents =  PendingIntent.getBroadcast(getApplicationContext(), phone,  intentAlarms, 0);
		        AlarmManager alarmManagers = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
		        
		        String times=endtime.getText().toString();
		        
		        String[] hrss=times.split(":");
		        int hrr=Integer.parseInt(hrss[0]);
		        int minn=Integer.parseInt(hrss[1]);
		        
		        Calendar calendars = Calendar.getInstance();
		        calendars.setTimeInMillis(System.currentTimeMillis());
		        calendars.set(Calendar.HOUR_OF_DAY, hrr);
		        calendars.set(Calendar.MINUTE, minn);
		        calendars.set(Calendar.SECOND, 1);
		        
		       // Toast.makeText(RemoteTracking.this, "Alarm scheduled for " + hrr + minn, Toast.LENGTH_LONG).show();
		        
		        alarmManagers.set(AlarmManager.RTC_WAKEUP, calendars.getTimeInMillis(), pIntents);
		        }
			}
				if((mon.isChecked()==false)&&(tue.isChecked()==false)&&(wed.isChecked()==false)&&(thu.isChecked()==false)&&(fri.isChecked()==false)&&(sat.isChecked()==false)&&(sun.isChecked()==false)&&(startime.getText().toString().equals(""))){
					
					spinner = (Spinner) findViewById(R.id.spinnerfrequ);
					if(spinner.getSelectedItem().equals("MINUTES")){
						comm="#min";
					}
					if(spinner.getSelectedItem().equals("SECONDS")){
						comm="#sec";
					}
					if(spinner.getSelectedItem().equals("HOURS")){
						comm="#hrs";
					}
					customize=(EditText) findViewById(R.id.editText3);
					String command=comm+customize.getText().toString();
					numb=num.getText().toString();
					
					String phoneNumberReciver="5556"; //my phone number usually entered here
			        String message="Hi I will be there later, See You soon";
			        SmsManager sms = SmsManager.getDefault(); 
			        sms.sendTextMessage(numb, null, command, null, null);
			        if(!endtime.getText().toString().equals("")){
				        Intent intentAlarms = new Intent(getApplicationContext(), AlarmReceiver1.class);
				        
				        String commands="gaddielTrack#Stop";
						intentAlarms.putExtra("message", commands);
						numbs=num.getText().toString();
						intentAlarms.putExtra("phn", numbs);
				        
				        PendingIntent pIntents =  PendingIntent.getBroadcast(getApplicationContext(), phone,  intentAlarms, 0);
				        AlarmManager alarmManagers = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
				        
				        String times=endtime.getText().toString();
				        
				        String[] hrss=times.split(":");
				        int hrr=Integer.parseInt(hrss[0]);
				        int minn=Integer.parseInt(hrss[1]);
				        
				        Calendar calendars = Calendar.getInstance();
				        calendars.setTimeInMillis(System.currentTimeMillis());
				        calendars.set(Calendar.HOUR_OF_DAY, hrr);
				        calendars.set(Calendar.MINUTE, minn);
				        calendars.set(Calendar.SECOND, 1);
				        
				        //Toast.makeText(RemoteTracking.this, "Alarm scheduled for " + hrr + minn, Toast.LENGTH_LONG).show();
				        
				        alarmManagers.set(AlarmManager.RTC_WAKEUP, calendars.getTimeInMillis(), pIntents);
				        }
				}
				start.setEnabled(false);
				
				String mydate = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
				createdDate=mydate;
				
				//Toast.makeText(RemoteTracking.this, "Created Date IS " + createdDate, Toast.LENGTH_LONG).show();
				
				createdBy=crBy;
				tmId=poss;
				status="Y";
				SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
				if(startime.getText().toString().equals("")){
					startTime=format.format(System.currentTimeMillis());
				}
				else{
				startTime=startime.getText().toString();
				}
				if(endtime.getText().toString().equals("")){
					endTime=format.format(System.currentTimeMillis());
				}
				else{
				endTime=endtime.getText().toString();
				}
				//Toast.makeText(RemoteTracking.this, "End Time IS " + endTime, Toast.LENGTH_LONG).show();
				if(mon.isChecked()==true){
					monday="Y";
				}
				if(tue.isChecked()==true){
					tuesday="Y";
				}
				if(thu.isChecked()==true){
					thursday="Y";
				}
				if(wed.isChecked()==true){
					wednesday="Y";
				}
				if(fri.isChecked()==true){
					friday="Y";
				}
				if(sat.isChecked()==true){
					saturday="Y";
				}
				if(sun.isChecked()==true){
					sunday="Y";
				}
				if(repeat.isChecked()==true){
					rOption="Y";
				}
				customize=(EditText)findViewById(R.id.editText3);
				
				comma=spinner.getSelectedItem().toString();
				
				
				freq=comma+customize.getText().toString();
				btnStart="Y";
				btnStop="N";
				
				insert();
			}
			
			
			
		});
		
		stop.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				start.setEnabled(true);
				AlarmManager alarmManagers = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
				Intent intentAlarms = new Intent(getApplicationContext(), AlarmReceiver1.class);
				PendingIntent pIntents =  PendingIntent.getBroadcast(getApplicationContext(), phone,  intentAlarms, 0);
				PendingIntent pIntentsone =  PendingIntent.getBroadcast(getApplicationContext(), phone+1,  intentAlarms, 0);
				PendingIntent pIntentstwo =  PendingIntent.getBroadcast(getApplicationContext(), phone+2,  intentAlarms, 0);
				PendingIntent pIntentsthree =  PendingIntent.getBroadcast(getApplicationContext(), phone+3,  intentAlarms, 0);
				PendingIntent pIntentsfour =  PendingIntent.getBroadcast(getApplicationContext(), phone+4,  intentAlarms, 0);
				PendingIntent pIntentsfive =  PendingIntent.getBroadcast(getApplicationContext(), phone+5,  intentAlarms, 0);
				PendingIntent pIntentssix =  PendingIntent.getBroadcast(getApplicationContext(), phone+6,  intentAlarms, 0);
				PendingIntent pIntentsseven =  PendingIntent.getBroadcast(getApplicationContext(), phone+7,  intentAlarms, 0);
				PendingIntent pIntentseight =  PendingIntent.getBroadcast(getApplicationContext(), phone+8,  intentAlarms, 0);
				PendingIntent pIntentsnine =  PendingIntent.getBroadcast(getApplicationContext(), phone+9,  intentAlarms, 0);
				PendingIntent pIntentsten =  PendingIntent.getBroadcast(getApplicationContext(), phone+10,  intentAlarms, 0);
				PendingIntent pIntentseleven =  PendingIntent.getBroadcast(getApplicationContext(), phone+11,  intentAlarms, 0);
				PendingIntent pIntentstwelve =  PendingIntent.getBroadcast(getApplicationContext(), phone+12,  intentAlarms, 0);
				PendingIntent pIntentsthirteen =  PendingIntent.getBroadcast(getApplicationContext(), phone+13,  intentAlarms, 0);
				PendingIntent pIntentsfourteen =  PendingIntent.getBroadcast(getApplicationContext(), phone+14,  intentAlarms, 0);
				
				alarmManagers.cancel(pIntents);
				alarmManagers.cancel(pIntentsone);
				alarmManagers.cancel(pIntentstwo);
				alarmManagers.cancel(pIntentsthree);
				alarmManagers.cancel(pIntentsfour);
				alarmManagers.cancel(pIntentsfive);
				alarmManagers.cancel(pIntentssix);
				alarmManagers.cancel(pIntentsseven);
				alarmManagers.cancel(pIntentseight);
				alarmManagers.cancel(pIntentsnine);
				alarmManagers.cancel(pIntentsten);
				alarmManagers.cancel(pIntentseleven);
				alarmManagers.cancel(pIntentstwelve);
				alarmManagers.cancel(pIntentsthirteen);
				alarmManagers.cancel(pIntentsfourteen);
				numb=num.getText().toString();
				
			//	String phoneNumberReciver="5556"; //my phone number usually entered here
		        String command="gaddielTrack#Stop";
				SmsManager sms = SmsManager.getDefault(); 
		        sms.sendTextMessage(numb, null, command, null, null);
				// TODO Auto-generated method stub
		        stop.setEnabled(false);
		        
		        String mydate = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
				update();
		        //status="N";
		        //updatedDate=mydate;
				//btnStart="N";
				//btnStop="Y";
				
				
				
			}
			
		});
	 }
	 
	 public void insert() {
	    	
	    	//Toast.makeText( MobileTracker.this, "Hit Save ", Toast.LENGTH_LONG).show();
	    	ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
	 
	    	nameValuePairs.add(new BasicNameValuePair("Tracking_Schedule_Id",""));	
	   	nameValuePairs.add(new BasicNameValuePair("Tracked_Mobile_Id",tmId));
	   	nameValuePairs.add(new BasicNameValuePair("CreatedBy",createdBy));
	   //	nameValuePairs.add(new BasicNameValuePair("UpdatedBy",updatedBy));
	   	nameValuePairs.add(new BasicNameValuePair("CreatedDate",createdDate));
	   //	nameValuePairs.add(new BasicNameValuePair("UpdatedDate",updatedDate));
		nameValuePairs.add(new BasicNameValuePair("Status",status));
		nameValuePairs.add(new BasicNameValuePair("Start_Time",startTime));
		nameValuePairs.add(new BasicNameValuePair("End_Time", endTime));
	   	nameValuePairs.add(new BasicNameValuePair("Monday",monday));
		nameValuePairs.add(new BasicNameValuePair("Tuesday",tuesday));
		nameValuePairs.add(new BasicNameValuePair("Wednesday",wednesday));
		nameValuePairs.add(new BasicNameValuePair("Thursday",thursday));
		nameValuePairs.add(new BasicNameValuePair("Friday",friday));
		nameValuePairs.add(new BasicNameValuePair("Saturday",saturday));
		nameValuePairs.add(new BasicNameValuePair("Sunday",sunday));
		nameValuePairs.add(new BasicNameValuePair("Repeat_option",rOption));
		nameValuePairs.add(new BasicNameValuePair("Frequency",freq));
		nameValuePairs.add(new BasicNameValuePair("Start",btnStart));
		nameValuePairs.add(new BasicNameValuePair("Stop",btnStop));
		
		String registerUrl="http://controller.gaddieltech.com/Remotetracker/remotetracker.php";
		String json = jsonParser.makeHttpRequest(registerUrl, "POST",nameValuePairs);
		
	   // Log.e("pass 1", "connection success ");
	    Toast.makeText( RemoteTracking.this, "Tracking Scheduled", Toast.LENGTH_LONG).show();
	    Intent dashBoard = new Intent(getApplicationContext(), Tracked_Mobiles.class);
		dashBoard.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		dashBoard.putExtra("phoneno", createdBy);
		startActivity(dashBoard);
	    this.finish();
		
		}
	 
	 public void update() {
	      
	     ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
	   nameValuePairs.add(new BasicNameValuePair("Tracking_Schedule_Id",tsid));
	   nameValuePairs.add(new BasicNameValuePair("Tracked_Mobile_Id",poss));
	  
	   /*nameValuePairs.add(new BasicNameValuePair("Tracking_Schedule_Id",""));    
	  nameValuePairs.add(new BasicNameValuePair("Status",status));
	  nameValuePairs.add(new BasicNameValuePair("Start_Time",startTime));
	  nameValuePairs.add(new BasicNameValuePair("End_Time", endTime));
	     nameValuePairs.add(new BasicNameValuePair("Monday",monday));
	  nameValuePairs.add(new BasicNameValuePair("Tuesday",tuesday));
	  nameValuePairs.add(new BasicNameValuePair("Wednesday",wednesday));
	  nameValuePairs.add(new BasicNameValuePair("Thursday",thursday));
	  nameValuePairs.add(new BasicNameValuePair("Friday",friday));
	  nameValuePairs.add(new BasicNameValuePair("Saturday",saturday));
	  nameValuePairs.add(new BasicNameValuePair("Sunday",sunday));
	  nameValuePairs.add(new BasicNameValuePair("Repeat_option",rOption));
	  nameValuePairs.add(new BasicNameValuePair("Frequency",freq));
	  nameValuePairs.add(new BasicNameValuePair("Start",btnStart));
	  nameValuePairs.add(new BasicNameValuePair("Stop",btnStop));*/
	  
	  String registerUrl="http://controller.gaddieltech.com/Remotetracker/rtstop.php";
	  String json = jsonParser.makeHttpRequest(registerUrl, "POST",nameValuePairs);
	  
	      //Log.d("pass 1", "connection success ");
	      Toast.makeText( RemoteTracking.this, "Tracking Stopped", Toast.LENGTH_LONG).show();
	     Intent dashBoard = new Intent(getApplicationContext(), Tracked_Mobiles.class);
	  dashBoard.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	  dashBoard.putExtra("phoneno", crBy);
	  startActivity(dashBoard);
	     this.finish();
	  
	  }
	 
	 
}


