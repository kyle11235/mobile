package com.example.myfirstapp;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class MyFragmentActivity extends FragmentActivity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.news_articles);
		// the fragments are defined in news_article.xml in layout-large folder,
		// associated with this activity static

		// to dynamically add and remove fragments ,must sue fragment manager

		// why
		/*
		 * When designing your application to support a wide range of screen
		 * sizes, you can reuse your fragments in different layout
		 * configurations to optimize the user experience based on the available
		 * screen space.
		 */
	}
}
