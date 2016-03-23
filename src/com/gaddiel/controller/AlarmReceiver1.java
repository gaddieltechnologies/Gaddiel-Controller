package com.gaddiel.controller;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.widget.Toast;

public class AlarmReceiver1 extends BroadcastReceiver {

	@Override
	public void onReceive(Context arg0, Intent arg1) {
	
		Bundle bundle=arg1.getExtras();
		String messages=bundle.getString("message");
		String phoneNumberReciver=bundle.getString("phn");
		//Toast.makeText(arg0, "The Message is Stop"+messages, Toast.LENGTH_LONG).show();
		//String phoneNumberReciver="5556"; //my phone number usually entered here
        String message="Hi I will be there later, See You soon";
        SmsManager sms = SmsManager.getDefault(); 
        sms.sendTextMessage(phoneNumberReciver, null, messages, null, null);
		
	}

}
