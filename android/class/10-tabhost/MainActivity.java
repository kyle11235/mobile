package com.example.ui;

import android.app.TabActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;

public class MainActivity extends TabActivity {

	TabHost tabHost = null;
	
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		tabHost = this.getTabHost();
		
		// Inflate a new view hierarchy from the specified xml resource
		LayoutInflater.from(this).inflate(R.layout.activity_main, tabHost.getTabContentView());
		
		tabHost.addTab(tabHost.newTabSpec("tab1")
				.setIndicator("微信", 
						getResources().getDrawable(R.drawable.tab1))
				.setContent(R.id.linearLayout01)
					);
		tabHost.addTab(tabHost.newTabSpec("tab2")
				.setIndicator("通讯录", 
						getResources().getDrawable(R.drawable.tab2))
				.setContent(R.id.linearLayout02)
					);
		tabHost.addTab(tabHost.newTabSpec("tab3")
				.setIndicator("发现", 
						getResources().getDrawable(R.drawable.tab3))
				.setContent(R.id.linearLayout03)
					);
		tabHost.addTab(tabHost.newTabSpec("tab4")
				.setIndicator("我", 
						getResources().getDrawable(R.drawable.tab4))
				.setContent(R.id.linearLayout04)
					);
		
		
		
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
