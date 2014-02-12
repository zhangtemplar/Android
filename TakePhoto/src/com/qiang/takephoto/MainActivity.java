package com.qiang.takephoto;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity {
	private ImageView mImageView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mImageView=(ImageView) findViewById(R.id.imageView1);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	static final int REQUEST_IMAGE_CAPTURE=1;
	
	public void dispatchTakePictureIntent(View view)
	{
		Intent takePictureIntent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		if (takePictureIntent.resolveActivity(getPackageManager())!=null)
		{
			startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
			
			File photoFile=null;
			try
			{
				photoFile=createImageFile();
			}
			catch (IOException ex)
			{
				Log.d(getPackageName(), "Failed to create image file");
			}
			
			if (photoFile!=null)
			{
				takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
				startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
			}
		}
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		if (requestCode==REQUEST_IMAGE_CAPTURE && resultCode==RESULT_OK)
		{
			Bundle extras=data.getExtras();
			Bitmap imageBitmap=(Bitmap) extras.get("data");
			mImageView.setImageBitmap(imageBitmap);
		}
	}
	
	private String mCurrentPhotoPath;
	
	private File createImageFile() throws IOException
	{
		String timeStamp=new SimpleDateFormat("yyyy_mm_dd_HH_mm_ss").format(new Date());
		String imageFileName="JPEG_"+timeStamp+"_";
		File storageDir=Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
		File image=File.createTempFile(imageFileName, ".jpg", storageDir);
		
		mCurrentPhotoPath="file:"+image.getAbsolutePath();
		return image;
	}
	
	static final int REQUEST_TAKE_PHOTO = 2;
	
	public void galleryAddPic(View view)
	{
		Intent mediaScanIntent=new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
		File f=new File(mCurrentPhotoPath);
		Uri contentUri=Uri.fromFile(f);
		mediaScanIntent.setData(contentUri);
		this.sendBroadcast(mediaScanIntent);
	}
}
