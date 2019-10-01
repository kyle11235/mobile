package com.example.myfirstapp.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myfirstapp.R;

public class HeadlinesFragment extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		System.out.println("create headlines fragment");
		TextView textView = new TextView(getActivity());
		textView.setTextSize(40);
		textView.setText("this's a headline");
		container.addView(textView);
		
		//
		mCallback.onHeadlineSelected(10);
		
		
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.news_articles, container, false);

		// see fragment as sub activity
	}

	/*
	 * the following section shows how to communicate with activity
	 */
	OnHeadlineSelectedListener mCallback;

	// Container Activity must implement this interface
	public interface OnHeadlineSelectedListener {
		public void onHeadlineSelected(int position);
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

		// This makes sure that the container activity has implemented
		// the callback interface. If not, it throws an exception
		try {
			mCallback = (OnHeadlineSelectedListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString() + " must implement OnHeadlineSelectedListener");
		}
	}

}
