package com.qiang.creatingfragment;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class HeadlinesFragment extends ListFragment{
	OnHeadlineSelectedListener mCallback;
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		int layout=Build.VERSION.SDK_INT>=Build.VERSION_CODES.HONEYCOMB? android.R.layout.simple_list_item_activated_1: android.R.layout.simple_list_item_1;
		setListAdapter(new ArrayAdapter<String> (getActivity(), layout, Ipsum.Headlines));
	}
	
	@Override
	public void onStart()
	{
		super.onStart();
		
		if (getFragmentManager().findFragmentById(R.id.article_fragment)!=null)
		{
			getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		}
	}
	
	public interface OnHeadlineSelectedListener
	{
		public void onArticleSelected (int position);
	}
	
	@Override
	public void onAttach(Activity activity)
	{
		super.onAttach(activity);
		
		try
		{
			mCallback=(OnHeadlineSelectedListener) activity;
		}
		catch (ClassCastException e)
		{
			throw new ClassCastException (activity.toString()+" must implement OnHeadlineSelectedListener");
		}
	}
	
	/*@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		ListView view=(ListView) inflater.inflate(R.layout.activity_main, container, false);
		for (int i=1; i<10; i++)
		{
			TextView text=new TextView(getActivity());
			text.setText(new String("Article "+i));
			view.addFooterView(text);
		}
		return view;
	}*/
	
	/**
	 * on the list item selected
	 */
	@Override
	public void onListItemClick(ListView l, View v, int position, long id)
	{
		mCallback.onArticleSelected(position);
		getListView().setItemChecked(position, true);
	}
}
