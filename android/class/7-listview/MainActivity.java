package com.example.ui;

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
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	int[]  imageIds = {R.drawable.image1,R.drawable.image2,R.drawable.image3,
			R.drawable.image4,R.drawable.image5,R.drawable.image6};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		

		BaseAdapter ba = new BaseAdapter() {
			
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {

				
				LinearLayout ll = new LinearLayout(MainActivity.this);
				ll.setOrientation(LinearLayout.HORIZONTAL);
				ll.setPadding(5, 5, 5, 5);
				
				ImageView iv = new ImageView(MainActivity.this);
				//设置image 可以这样调用本地资源id，可以传入bitmap(从网络or本地资源获取)
				iv.setImageDrawable(getResources().getDrawable(imageIds[position]));
				iv.setScaleType(ScaleType.FIT_XY);
				
				ll.addView(iv);
				
				TextView tv = new TextView(MainActivity.this);
				tv.setText("hello-"+position);
				tv.setTextSize(24);
				tv.setTextColor(getResources().getColor(R.color.blue));
				tv.setPadding(5, 5, 5, 5);
				tv.setGravity(Gravity.LEFT);
				ll.addView(tv);
				
				
				return ll;
			}
			
			@Override
			public long getItemId(int position) {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public Object getItem(int position) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return imageIds.length;
			}
		};
		
		ListView lv = (ListView)findViewById(R.id.listView1);
		lv.setAdapter(ba);
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				TextView tv = (TextView)findViewById(R.id.textView1);
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
