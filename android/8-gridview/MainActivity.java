package com.example.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	int[]  imageIds = {R.drawable.image1,R.drawable.image2,R.drawable.image3,
			R.drawable.image4,R.drawable.image5,R.drawable.image6};
	
	public List<?extends Map<String,?>> generateData(){
		
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		for(int i = 0; i < imageIds.length ; i++){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("col1", imageIds[i]);
			map.put("col2", "col2-"+i);
			map.put("col3", "col3-"+i);
			list.add(map);
		}
		
		return list;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		SimpleAdapter ad = new SimpleAdapter(
				this, 
				generateData(), 
				R.layout.grid_row, 
				new String[]{"col1","col2","col3"}, 
				new int[]{R.id.imageView1,R.id.textView1,R.id.textView2}
				);
		
		
		
		GridView gv = (GridView)findViewById(R.id.gridView1);
		gv.setAdapter(ad);
		gv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				TextView tv = (TextView)findViewById(R.id.tv);
				LinearLayout ll = (LinearLayout)view;
				TextView si = (TextView)ll.getChildAt(1);//0 is imageview, 1 is text view
				
				tv.setText("you selected item " + si.getText());
				Toast.makeText(getApplicationContext(), "you selected item " + si.getText(), 3).show();
				
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
