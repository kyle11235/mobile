package com.example.myfirstapp.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myfirstapp.R;

public class ArticleFragment extends Fragment {

	public static String ARG_POSITION;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		System.out.println("create article fragment");
		container.removeAllViews();
		TextView textView = new TextView(getActivity());
		textView.setTextSize(40);
		textView.setText("this is a article");
		container.addView(textView);
		
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.news_articles, container, false);

		// see fragment as sub activity

	}

	public void updateArticleView(int position) {
		System.out.println("updateArticleView");
	}

}
