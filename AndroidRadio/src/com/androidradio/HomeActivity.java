package com.androidradio;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends Activity{
@Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	setContentView(R.layout.main);
	
	Button playinternet =(Button)findViewById(R.id.play_internet);
	Button playlocal =(Button)findViewById(R.id.play_local);
	playinternet.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intternet = new Intent(HomeActivity.this,MainActivity.class);
			startActivity(intternet);
		}
	});
	
	playlocal.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intternet = new Intent(HomeActivity.this,PlayLocalActivity.class);
			startActivity(intternet);
		}
	});
	
	
	
}



}
