package com.newapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		try {
			stopService(new Intent(getApplicationContext(), MainService.class));
		} catch (Exception e) {
			// TODO: handle exception
		}
Toast.makeText(MainActivity.this, "Start service", 5000).show();
		Intent intent = new Intent(getApplicationContext(), MainService.class);
		startService(intent);
		finish();

	}

}
