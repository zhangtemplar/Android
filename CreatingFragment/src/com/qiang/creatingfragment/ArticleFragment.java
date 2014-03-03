package com.qiang.creatingfragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ArticleFragment extends Fragment{
	public final static String ARG_POSITION="position";
	
	private int mPosition;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		if (savedInstanceState!=null)
		{
			mPosition=savedInstanceState.getInt(ARG_POSITION);
		}
		return inflater.inflate(R.layout.activity_main, container, false);
	}
	
	@Override
	public void onStart()
	{
		super.onStart();
		
		Bundle args=getArguments();
		if (args!=null)
		{
			updateArticleView(args.getInt(ARG_POSITION));
		}
		else if(mPosition!=-1)
		{
			updateArticleView(mPosition);
		}
	}
	
	/**
	 * once we get the message that an article is selected
	 * we show the article in the view
	 * @param position
	 */
	public void updateArticleView(int position)
	{
		TextView text=(TextView) getActivity().findViewById(R.id.article);
		text.setText(Ipsum.Articles[position]);
		mPosition=position;
	}
}
