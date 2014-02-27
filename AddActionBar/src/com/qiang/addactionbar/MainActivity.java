package com.qiang.addactionbar;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		getActionBar().setDisplayHomeAsUpEnabled(true);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		/*getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;*/
		MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.activity_main, menu);
	    return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle presses on the action bar items
	    switch (item.getItemId()) {
	        case R.id.action_search:
	            openSearch();
	            return true;
	        case R.id.action_settings:
	            openSettings();
	            return true;
	        case R.id.button1:
	        	TextView textView=(TextView) findViewById(R.id.editText1);
	        	textView.setText(null);
	        	return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
	public void onReset(View view)
	{
		TextView textView=(TextView) findViewById(R.id.editText1);
    	textView.setText(null);
	}
	
	public void openSearch()
	{
		TextView textView=(TextView) findViewById(R.id.editText1);
    	textView.setText("Search");
	}
	
	public void openSettings()
	{
		TextView textView=(TextView) findViewById(R.id.editText1);
    	textView.setText("Settings");
	}
}
