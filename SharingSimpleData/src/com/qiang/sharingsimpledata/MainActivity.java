package com.qiang.sharingsimpledata;

import java.util.Arrays;

import android.os.Build;
import android.os.Bundle;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ShareActionProvider;
import android.widget.TextView;

public class MainActivity extends Activity {
	private ShareActionProvider mShareActionProvider;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// to deal with incoming intent
		Intent intent=getIntent();
		String action=intent.getAction();
		String type=intent.getType();
		// plain text receviced
		if (Intent.ACTION_SEND.equals(action) && "text/plain".equals(type))
		{
			onSendText(intent);
		}
	}

	@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		
		// enable the share action button on menu
		MenuItem item=menu.findItem(R.id.menu_item_share);
		mShareActionProvider=(ShareActionProvider) item.getActionProvider();
		
		return true;
	}

	public void sendText(View view)
	{
		Intent intent=new Intent();
		TextView text=(TextView) findViewById(R.id.EditText1);
		intent.setAction(Intent.ACTION_SEND);
		intent.putExtra(Intent.EXTRA_TEXT, text.getText().toString());
		intent.setType("text/plain");
		// startActivity(intent);
		/*
		 * a better choice would be use createChooser()
		 * Even if the user has previously selected a default action for this intent, the chooser will still be displayed.
		 * If no applications match, Android displays a system message.
		 * You can specify a title for the chooser dialog.
		 */
		startActivity(Intent.createChooser(intent, getResources().getText(R.string.send_to)));
	}
	
	public void forwardText(View view)
	{
		Intent intent=new Intent();
		TextView text=(TextView) findViewById(R.id.textView1);
		intent.setAction(Intent.ACTION_SEND);
		intent.putExtra(Intent.EXTRA_TEXT, text.getText().toString());
		intent.setType("text/plain");
		startActivity(Intent.createChooser(intent, getResources().getText(R.string.send_to)));
	}
	
	/**
	 * on receive an intent
	 * note that, findViewById must be called after setContentView
	 * @param intent
	 */
	public void onSendText(Intent intent)
	{
		String text=intent.getStringExtra(Intent.EXTRA_TEXT);
		if (text!=null)
		{
			TextView textView=(TextView) findViewById(R.id.textView1);
			if (textView!=null)
			{
				textView.setText(text);
			}
		}
	}
	
	@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
	private void setShareItent(Intent shareIntent)
	{
		if (mShareActionProvider!=null)
		{
			mShareActionProvider.setShareIntent(shareIntent);
		}
	}
}
