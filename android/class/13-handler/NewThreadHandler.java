package com.example.test;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends ActionBarActivity {

	private static int count;
	ProgressBar progressBar;
	Button startButton;
	Button resetButton;


	// When you create a new Handler, it is bound to the thread / message queue of the thread that is creating it 
	// You can create your own threads, and communicate back with the main application thread through a Handler
	class MyHandler extends Handler{
		public MyHandler(){
			
		}
		
		public MyHandler(Looper looper){
			super(looper);
		}
		
		@Override
		public void handleMessage(Message msg) {
			
			Bundle data = msg.getData();
			
			System.out.println("name is "+data.getString("name"));
			System.out.println("age is "+data.getInt("age"));
			
			// start to work
			while(count < 100){
				try {
					count += 10;
					System.out.println("working on count " + count + ", thread is " + Thread.currentThread().getId());
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				// update UI
				progressBar.setProgress(count);
			}
			
		}
			
	}
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		System.out.println("main activity, thread is " + Thread.currentThread().getId());

		startButton = (Button) findViewById(R.id.button1);
		startButton.setOnClickListener(new StartButtonListener());
		resetButton = (Button) findViewById(R.id.button2);
		resetButton.setOnClickListener(new EndButtonListener());

		progressBar = (ProgressBar) findViewById(R.id.progressBar1);

	}

	class StartButtonListener implements OnClickListener {

		@Override
		public void onClick(View v) {

			progressBar.setVisibility(v.VISIBLE);

			// new thread
			HandlerThread handlerThread = new HandlerThread("handlerThread");
			handlerThread.start();

			MyHandler handler = new MyHandler(handlerThread.getLooper());
			Message msg = handler.obtainMessage();
			
			Bundle bundle = new Bundle();
			bundle.putString("name", "kyle");
			bundle.putInt("age", 20);
			msg.setData(bundle);
			msg.sendToTarget(); // -> handleMessage
			
		}

	}

	class EndButtonListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			progressBar.setVisibility(-1);
			progressBar.setProgress(0);
			count = 0;

		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
