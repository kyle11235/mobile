package com.example.test;

import android.os.Bundle;
import android.os.Handler;
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

	// There are two main uses for a Handler: 
	// (1) to schedule messages and runnables to be executed at some point in the future; and 
	// (2) to enqueue an action to be performed on a different thread than your own.

	Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// callback of thread, used to update UI
			progressBar.setProgress(msg.arg1);
		}
	};

	Runnable worker = new Runnable() {

		@Override
		public void run() {
			// start to work
			try {
				count += 10;
				System.out.println("working on count " + count + ", thread is" + Thread.currentThread().getId());
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			// have a rest and update progress
			Message msg = handler.obtainMessage();
			msg.arg1 = count;
			handler.sendMessage(msg); // sendMessage, sendMessageDeplay -> handleMessage

			// continue to work
			if (count < 100) {
				handler.post(worker); // post, postDelay -> run
			}

		}

	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

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

			// start the thread, add it to handler's queue
			handler.post(worker);
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
