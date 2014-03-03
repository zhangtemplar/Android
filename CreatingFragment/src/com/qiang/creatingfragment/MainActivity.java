package com.qiang.creatingfragment;

import com.qiang.creatingfragment.HeadlinesFragment.OnHeadlineSelectedListener;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;

public class MainActivity extends FragmentActivity implements OnHeadlineSelectedListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		/*
		 * check the layout we are using
		 * if we are under a normal layout, then we dynamically build up the layout
		 */
		if (findViewById(R.id.fragment_container)!=null)
		{
			/*
			 * if we are recovering from the previous state, we don't need to 
			 * recreate the layout
			 */
			if (savedInstanceState!=null)
			{
				return;
			}
			
			// create the headline
			HeadlinesFragment headline=new HeadlinesFragment();
			// ArticleFragment article=new ArticleFragment();
			
			// pass the intent's extra to the fragment as arguments
			headline.setArguments(getIntent().getExtras());
			
			// add the fragment to the framelayout
			getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, headline).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	/**
	 * deliver the article selection message from headlinefragment to articlefragment
	 */
	public void onArticleSelected(int position) {
		// TODO Auto-generated method stub
		ArticleFragment article=(ArticleFragment) getSupportFragmentManager().findFragmentById(R.id.article_fragment);
		
		if (article!=null)
		{
			article.updateArticleView(position);
		}
		else
		{
			article=new ArticleFragment();
			Bundle args=new Bundle();
			args.putInt(ArticleFragment.ARG_POSITION, position);
			
			article.setArguments(getIntent().getExtras());
			FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
			transaction.replace(R.id.fragment_container, article);
			transaction.addToBackStack(null);
			transaction.commit();
		}
	}

}
