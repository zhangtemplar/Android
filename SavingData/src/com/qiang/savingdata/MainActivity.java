package com.qiang.savingdata;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Calendar;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {
	public final static String LAST_VISIT_TIME="last_visit_time";
	private BufferedWriter stream;
	private ArrayAdapter<String> adapter;
	private ListView list;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		list=(ListView) findViewById(R.id.listView1);
		adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
		try
		{
			BufferedReader in=new BufferedReader(new FileReader(getFilesDir()+getString(R.string.history_file)));
			while(true)
			{
				String str=in.readLine();
				if (str==null || str.length()<1)
				{
					break;
				}
				adapter.add(str);
			}
			list.setAdapter(adapter);
			in.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		try
		{
			stream=new BufferedWriter(new FileWriter(getFilesDir()+getString(R.string.history_file), true));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	/**
	 * on resume, we provide a welcome message showing its last visit
	 */
	@Override
	public void onResume()
	{
		super.onResume();
		
		SharedPreferences pref=getPreferences(MODE_PRIVATE);
		String time=pref.getString(LAST_VISIT_TIME, "N.A.");
		TextView text=(TextView) findViewById(R.id.welcome_message);
		text.setText(getString(R.string.welcome_message)+time);
		
		adapter.add(time);
		list.setAdapter(adapter);
	}
	
	/**
	 * on pause, we keep a record of the last visit
	 */
	@Override
	public void onPause()
	{
		super.onPause();
		
		SharedPreferences pref=getPreferences(MODE_PRIVATE);
		SharedPreferences.Editor editor=pref.edit();
		Calendar now=Calendar.getInstance();
		String time=now.get(Calendar.YEAR)+"/"+now.get(Calendar.MONTH)+"/"+now.get(Calendar.DAY_OF_MONTH)+":"+now.get(Calendar.HOUR)+":"+now.get(Calendar.MINUTE)+":"+now.get(Calendar.SECOND);
		editor.putString(LAST_VISIT_TIME, time);
		editor.commit();
		
		// we also write all the history to the file
		try
		{
			stream.write(time);
			stream.newLine();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * on destroy, we close the record file
	 */
	public void onDestory()
	{
		super.onDestroy();
		
		try
		{
			stream.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
