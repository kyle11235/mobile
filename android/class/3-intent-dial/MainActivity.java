package com.kyle.hello;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.telephony.PhoneNumberUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.kyle.hello.common.util.BitmapCache;

public class MainActivity extends ActionBarActivity {

	private BitmapCache bitmapCache = BitmapCache.getInstance();
	
	private static MediaPlayer player;
	
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//
//		MyView iv = new MyView(this);
//		this.setContentView(iv);
//		
//	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		player = MediaPlayer.create(this, R.raw.kasabulanka);
		
		
		// button is defined in activity_main layout
		this.setContentView(R.layout.activity_main);
		
		Button btn = (Button)findViewById(R.id.button1);
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				EditText ev = (EditText)findViewById(R.id.editText1);
				String phoneNumber = ev.getText().toString();
				
				if (PhoneNumberUtils.isGlobalPhoneNumber(phoneNumber)) {
					Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel://"+phoneNumber));
					
					MainActivity.player.start();
					
					// use ...this, because it's in a new object
					MainActivity.this.startActivity(intent);
				}else{
					Toast.makeText(MainActivity.this, "ºÅÂë²»ÕýÈ·", 5000).show();
				}
			}
		});
		
	}

	
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//
//		/*1.use defined layout*/
//		// setContentView(R.layout.activity_main);
//
//		/*2.create view dynamically*/
//		ImageView iv = new ImageView(this);
//		// 2.1 use BitmapUtil, no cache, but this is non-synchronized operation
//		// BitmapUtil.loadBitmap(getResources(), R.drawable.ballon, iv, 576, 384, null);
//		
//		// 2.2 use BitmapCache, BitmapCache use BitmapUtil to scale image internally
//		Bitmap bitmap = bitmapCache.getBitmap(this, R.drawable.ballon, 576, 384);
//		// test get it from cache
//		bitmap = bitmapCache.getBitmap(this, R.drawable.ballon, 576, 384);
//		iv.setImageBitmap(bitmap);
//		this.setContentView(iv);
//		
//		
//		
//		/*3.media player*/
//		MediaPlayer player = MediaPlayer.create(this, R.raw.kasabulanka);
//		player.start();
//		
//	}

	
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
