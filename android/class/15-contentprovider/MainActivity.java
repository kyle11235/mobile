package com.example.test;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {

	
	SQLiteDatabase database;
	Button button1;
	TextView textView1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		System.out.println("main activity, thread is " + Thread.currentThread().getId());

		button1 = (Button) findViewById(R.id.button1);
		textView1 = (TextView) findViewById(R.id.textView1);
		
		
		
		button1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				System.out.println("insert row");
				
				// insert 
				ContentValues values = new ContentValues();
				values.put(FirstContentProviderMetaData.UserTableMetaData.USER_NAME, "zhangsan");
				Uri uri = getContentResolver().insert(FirstContentProviderMetaData.UserTableMetaData.CONTENT_URI, values);
				System.out.println(uri.toString());
				
				System.out.println("query");
				Cursor c = getContentResolver().query(FirstContentProviderMetaData.UserTableMetaData.CONTENT_URI, null, null, null, null);
				while (c.moveToNext()) {
					System.out.println(c.getString(c.getColumnIndex(FirstContentProviderMetaData.UserTableMetaData.USER_NAME)));
				}
				
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
