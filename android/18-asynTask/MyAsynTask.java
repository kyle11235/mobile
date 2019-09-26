package com.example.test;

import android.os.AsyncTask;
import android.widget.TextView;

// input type, update type, return type
public class MyAsynTask extends AsyncTask<String, Integer, Boolean> {

	
	TextView textView;
	
	public MyAsynTask(TextView textView){
		this.textView = textView;
	}
	
	
	@Override
	protected Boolean doInBackground(String... params) {
		
		System.out.println("do in background");
		
		try {
			for (int i = 0; i < params.length; i++) {
				
				// update progress
				publishProgress(i);
				Thread.sleep(1000);
			}
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("done task");
		
		
		return true;
	}
	
	@Override
	protected void onProgressUpdate(Integer... values) {
		// TODO Auto-generated method stub
		super.onProgressUpdate(values[0]);
		System.out.println(values[0]);
	}
	
	@Override
	protected void onPostExecute(Boolean result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		textView.setText("end");
	}
	
	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
		
		textView.setText("begin");
		
	}
	
	
	
	

}
