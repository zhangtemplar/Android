package com.qiang.activitylifecycle;

import android.os.Build;
import android.os.Bundle;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.content.ContentValues;
import android.view.Menu;

public class MainActivity extends Activity {

	private static final String USERNAME="user name";
	
	@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		if (savedInstanceState!=null)
		{
			String userName=savedInstanceState.getString(USERNAME);
		}
		setContentView(R.layout.activity_main);
		
		if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.HONEYCOMB)
		{
			ActionBar actionBar=getActionBar();
			actionBar.setHomeButtonEnabled(false);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public void onDestroy()
	{
		super.onDestroy();
		
		android.os.Debug.stopMethodTracing();
	}
	
	@Override
	public void onPause()
	{
		super.onPause();
		// potentially suspend the urrent actitivy
		// keep this method simple
	}
	
	@Override
	public void onResume()
	{
		super.onResume();
	}
	
	@Override
	public void onStop()
	{
		super.onStop();
	}
	
	@Override 
	public void onSaveInstanceState(Bundle savedInstanceState)
	{
		savedInstanceState.putString(USERNAME, "qiang");
		
		super.onSaveInstanceState(savedInstanceState);
	}
}
