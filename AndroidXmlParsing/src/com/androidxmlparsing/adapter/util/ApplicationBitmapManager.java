package com.androidxmlparsing.adapter.util;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.androidxmlparsing.R;





import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ProgressBar;

public enum ApplicationBitmapManager {
	INSTANCE;

	private final Map<String, SoftReference<Bitmap>> cache;
	private final ExecutorService pool;
	private Map<ImageView, String> imageViews = Collections
			.synchronizedMap(new WeakHashMap<ImageView, String>());
	private Bitmap placeholder;

	ApplicationBitmapManager() {
		cache = new HashMap<String, SoftReference<Bitmap>>();
		pool = Executors.newFixedThreadPool(5);
	}

	public void setPlaceholder(Bitmap bmp) {
		placeholder = bmp;
	}

	public Bitmap getBitmapFromCache(String url) {
		if (cache.containsKey(url)) {
			return cache.get(url).get();
		}

		return null;
	}

	public void queueJob(final String url, final ImageView imageView,final ProgressBar progress) {
		/* Create handler in UI thread. */
		final Handler handler = new Handler() {

			public void handleMessage(Message msg) {
				String tag = imageViews.get(imageView);
				if (tag != null && tag.equals(url)) {
					if (msg.obj != null) {
						imageView.setImageBitmap((Bitmap) msg.obj);
					} else {
//						imageView.setImageBitmap(placeholder);
						imageView.setImageResource(R.drawable.ic_launcher);
						Log.d(null, "fail " + url);
					}
				}

				try{
					if(progress!=null)
					progress.setVisibility(progress.GONE);
				}catch(Exception e)
				{
					
				}
			}
		};

		pool.submit(new Runnable() {
			@Override
			public void run() {
				final Bitmap bmp = downloadBitmap(url);
				Message message = Message.obtain();
				message.obj = bmp;
				Log.d(null, "Item downloaded: " + url);

				handler.sendMessage(message);
			}
		});
	}
	public static Bitmap decodeSampledBitmapFromResource(String filePath,
			int reqWidth, int reqHeight) {

		// First decode with inJustDecodeBounds=true to check dimensions
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(filePath, options);

		// Calculate inSampleSize
		options.inSampleSize = calculateInSampleSize(options, reqWidth,
				reqHeight);

		// Decode bitmap with inSampleSize set
		options.inJustDecodeBounds = false;
		return BitmapFactory.decodeFile(filePath, options);
	}
	public static int calculateInSampleSize(BitmapFactory.Options options,
			int reqWidth, int reqHeight) {
		// Raw height and width of image
		final int height = options.outHeight;
		final int width = options.outWidth;
		int inSampleSize = 1;

		if (height > reqHeight || width > reqWidth) {

			// Calculate ratios of height and width to requested height and
			// width
			final int heightRatio = Math.round((float) height
					/ (float) reqHeight);
			final int widthRatio = Math.round((float) width / (float) reqWidth);

			inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
		}

		return inSampleSize;
	}
	public void loadBitmap(final String url, final ImageView imageView) {
		imageViews.put(imageView, url);
		Bitmap bitmapOrig = getBitmapFromCache(url);


//		if ((bitmapOrig.getByteCount() / (1020 * 1024)) > 5) {
//
//			bitmapOrig = getCropedResizedImageSIRThumbnail(
//					bitmapOrig, widthPixels, 120);
//
//		} else {
//			bitmapOrig = bitmapOrig;
//		}
		
		// check in UI thread, so no concurrency issues
		if (bitmapOrig != null) {
//			Bitmap bitmap = Bitmap.createScaledBitmap(bitmapOrig, 45, 45, true);
			Log.d(null, "Item loaded from cache: " + url);
			imageView.setImageBitmap(bitmapOrig);
		} else {
			imageView.setImageBitmap(placeholder);
			queueJob(url, imageView,null);
		}
	}
	public void loadBitmap(final String url, final ImageView imageView,ProgressBar progress) {
		try{
			progress.setVisibility(progress.VISIBLE);
		}catch(Exception e)
		{
			
		}
		
		imageViews.put(imageView, url);
		Bitmap bitmapOrig = getBitmapFromCache(url);

		//bitmap = getResizedBitmap(bitmap, 70, 70);
		
		// check in UI thread, so no concurrency issues
		if (bitmapOrig != null) {
//			Bitmap bitmap = Bitmap.createScaledBitmap(bitmapOrig, 45, 45, true);
//			Log.d(null, "Item loaded from cache: " + url);
			
			imageView.setImageBitmap(bitmapOrig);
			progress.setVisibility(progress.GONE);
		} else {
			imageView.setImageBitmap(placeholder);
			queueJob(url, imageView,progress);
		}
	}
	
	
	public void loadBitmap(final String url, final ImageView imageView,ProgressBar progress, int newHeight, int newWidth) {
		try{
			progress.setVisibility(progress.VISIBLE);
		}catch(Exception e)
		{
			
		}
		
		System.out.println(">>>in library height>>"+newHeight);
		System.out.println(">>>in library newWidth>>"+newWidth);
		
		imageViews.put(imageView, url);
		
		Bitmap bitmapOrig;
		try {
			bitmapOrig=null;
		} catch (Exception e) {
			// TODO: handle exception
		}
		bitmapOrig = getBitmapFromCache(url);

		//bitmap = getResizedBitmap(bitmap, 70, 70);
		
		// check in UI thread, so no concurrency issues
		if (bitmapOrig != null) {
			Bitmap bitmap = Bitmap.createScaledBitmap(bitmapOrig, newWidth, newHeight, true);
//			Log.d(null, "Item loaded from cache: " + url);
//			bitmapOrig = getResizedBitmap(bitmapOrig,newHeight,newWidth);
			
			imageView.setImageBitmap(bitmap);
			progress.setVisibility(progress.GONE);
		} else {
			imageView.setImageBitmap(placeholder);
			queueJob(url, imageView,progress);
		}
	}
	 Bitmap resizedBitmap;
	public Bitmap getResizedBitmap(Bitmap bm, int newHeight, int newWidth) {

//        int width = bm.getWidth();
//
//        int height = bm.getHeight();

        float scaleWidth = ((float) newWidth) ;

        float scaleHeight = ((float) newHeight) ;

        // create a matrix for the manipulation

        Matrix matrix = new Matrix();

        // resize the bit map

        matrix.postScale(scaleWidth, scaleHeight);

        // recreate the new Bitmap
       try {
		if(resizedBitmap!=null){
			resizedBitmap.recycle();
		}
	} catch (Exception e) {
		// TODO: handle exception
	}
        resizedBitmap  = Bitmap.createBitmap(bm, 0, 0, newWidth, newHeight,
                matrix, false);

        System.out.println("in getresizebitmap"+resizedBitmap.toString());



        return resizedBitmap;

    }


	private Bitmap downloadBitmap(String url) {
		try {
			Bitmap bitmap = BitmapFactory.decodeStream((InputStream) new URL(
					url).getContent());
			// bitmap = Bitmap.createScaledBitmap(bitmap, width, height, true);
			cache.put(url, new SoftReference<Bitmap>(bitmap));
			return bitmap;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}
}
