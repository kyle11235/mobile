package com.example.test;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class TestReciver extends BroadcastReceiver {

	public TestReciver(){
		System.out.println("test reciver");
	}
	
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		System.out.println("onReceive");
		
	}

}
