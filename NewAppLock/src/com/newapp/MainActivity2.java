package com.newapp;



import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity2 extends Activity {

	private static ImageView closeImg;
	MainActivity2 act;
	static Context context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		System.out.println("main2 oncreate");
		act = MainActivity2.this;

		GetControl();
		close();

	}
	
	

	public void GetControl() {
		closeImg = (ImageView) findViewById(R.id.imageView2);

	}

	public void close() {
		
		closeImg.setOnClickListener(new OnClickListener() {

			@SuppressWarnings("deprecation")
			@Override
			public void onClick(View v) {


				SharedPreferences pref = act.getSharedPreferences("block", 0);
				Editor edit = pref.edit();
				edit.putString("check", "check");
				edit.commit();
				
//				stopService(new Intent(MainActivity2.this,MainService.class));
//				MainService.timer.cancel();
				act.finish();
				


			}
		});

	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {

		if (keyCode == KeyEvent.KEYCODE_BACK) {

			// MainService.am.killBackgroundProcesses(MainService.packageName);
			Intent intent = new Intent(Intent.ACTION_MAIN);
			intent.addCategory(Intent.CATEGORY_HOME);
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(intent);

			return true;

		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		act.finish();
		super.onPause();
		
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
//		super.onDestroy();
//	stopService(new Intent(MainActivity2.this,MainService.class));
		
		
		

//		Intent intent = new Intent(getApplicationContext(), MainService.class);
//		startService(intent);
//		finish();
		super.onDestroy();
		
//		Intent serviceIntent = new Intent(MainActivity2.this, MainService.class);
//		
//		MainActivity2.this.startService(serviceIntent);

	}

}
