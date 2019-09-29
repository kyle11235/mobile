package com.kyle.hello;

import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.kyle.hello.common.util.BitmapCache;
import com.kyle.view.MyView;

public class MainActivity extends ActionBarActivity {

	private BitmapCache bitmapCache = BitmapCache.getInstance();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		MyView iv = new MyView(this);
		this.setContentView(iv);
		
	}

	
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//
//		// 1.use defined layout
//		// setContentView(R.layout.activity_main);
//
//		// 2.create view dynamically
//		ImageView iv = new ImageView(this);
//		
//		
//		
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
//		// media player
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
