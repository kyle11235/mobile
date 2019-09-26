package com.example.test;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
		
		DatabaseHelper databaseHelper = new DatabaseHelper(this, "mydatabase");
		database = databaseHelper.getWritableDatabase();
		
		button1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				System.out.println("insert row");

				
				// insert 
				ContentValues values = new ContentValues();
				values.put("id", 1);
				values.put("name", "kyle");
				database.insert("user", null, values);
				
				System.out.println("query row");

				// query
				// database.query("user", new String[]{"id","name"}, "id=?", new String[]{"1"}, null, null, null);
				Cursor cursor = database.query("user", new String[]{"id","name"}, null, null, null, null, null);
				textView1.setText("");
				while (cursor.moveToNext()) {
					int id = cursor.getInt(0);
					String name = cursor.getString(cursor.getColumnIndex("name"));
					textView1.append("id = "+id+" name = "+name+"\n");
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
