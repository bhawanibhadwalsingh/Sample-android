package com.newapp;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.Toast;

public class MainService extends Service {

	private Handler handler;
	public static Thread thread;

//	public static ActivityManager am;
//	public static String packageName;
	private Intent in;
	Activity activity;
	public static MainService mainService;
	boolean value = true;
	public static  Timer timer;
	String check;
	public static int flag =0;

	@Override
	public void onCreate() {
		super.onCreate();
		LayoutInflater inflater = (LayoutInflater) getApplicationContext()
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		System.out.println(" oncreate");
		mainService = new MainService();


		final SharedPreferences pref = getApplicationContext().getSharedPreferences("block", 0);

		check = pref.getString("check", "");
		Editor edit = pref.edit();
		if (check.equals("check")) {
			edit.putString("check", "");
			edit.commit();
			Log.e("", "launcher closed and control is in if bloc of shared prefrence main service");
//			Toast.makeText(activity, "Close clicked", Toast.LENGTH_SHORT).show();
		}
		else {
//			Editor edit = pref.edit();
			edit.putString("check", "check");
			edit.commit();
			
			OpenMyAppactivity();
		}

	}



	public void OpenMyAppactivity() {
		if(timer == null) timer = new Timer();
		
		timer.scheduleAtFixedRate(new TimerTask() {

			@SuppressWarnings("deprecation")
			@Override
			public void run() {
				String packageName;
				ActivityManager mActivityManager = (ActivityManager)getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
				if(Build.VERSION.SDK_INT > 20){
				packageName = mActivityManager.getRunningAppProcesses().get(0).processName;
				}
				else{ 
					packageName = mActivityManager.getRunningTasks(1).get(0).topActivity.getPackageName();
				}
				/*am = (ActivityManager) getApplicationContext()
						.getSystemService(Activity.ACTIVITY_SERVICE);
				packageName = am.getRunningTasks(1).get(0).topActivity
						.getPackageName();*/

				System.out.println("package name   " + packageName);

				if (packageName.equalsIgnoreCase("com.android.launcher") || packageName.equalsIgnoreCase("com.google.android.googlequicksearchbox")||packageName.equalsIgnoreCase("com.sec.android.app.launcher")||packageName.equalsIgnoreCase("com.htc.launcher")||packageName.contains("launcher")) {
				Log.e("", "Yo to launcher tha bhai");
					flag = 1;
				} 
				else{
					if(flag == 1){
						Intent in = new Intent(getApplicationContext(),
								MainActivity2.class);
						in.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						in.setAction(Intent.ACTION_MAIN);
						in.addCategory(Intent.CATEGORY_LAUNCHER);
						startActivity(in);
						flag =0;
//						stopSelf();
					}
					
				}

			}
		}, 1000, 1000);

	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
//		stopSelf();
		
	
//		Toast.makeText(MainService.this, "Activity Destroyed", 5000).show();



//		Process suProcess;
//		try {
//			suProcess = Runtime.getRuntime().exec("su");
//
//			DataOutputStream os = new DataOutputStream(suProcess.getOutputStream());
//
//			os.writeBytes("adb shell" + "\n");
//
//			os.flush();
//
//			os.writeBytes("am force-stop com.xxxxxx" + "\n");
//
//			os.flush();
//
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

	}
	
	@Override
	  public int onStartCommand(Intent intent, int flags, int startId) {
	    //TODO do something useful
	    return Service.START_STICKY;
	  }



}
