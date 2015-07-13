package com.example.apiexampleinservices;





import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.apiexampleinservices.SubViewAdapter.SubClickListener;
import com.softprodigy.librarymsspro.MobileConnectivity;

public class BackgroundServicesActivity extends Activity implements SubClickListener{
	private static final String TAG = "APIExampleInServices";
	private Intent intent;
	TextView tv1;
	
	
	//variable initialize
	
	MobileConnectivity mobile;
//	List<SubViewChildModel> customListchild = new ArrayList<SubViewChildModel>();
	SubViewAdapter adapter11;
	ListView mlist;
	
	
	ProgressBar progressBar;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		init();
		
		 intent = new Intent(this, ServiceClass.class);
		
		
		
	}
	
	
	    private void init() {
		// TODO Auto-generated method stub
	    	mlist = (ListView) findViewById(R.id.lv_search);
			progressBar = (ProgressBar)findViewById(R.id.progressBar1);
			
			tv1 = (TextView)findViewById(R.id.oneidtext);
	}


		//onresume method
	 @Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
		startService(intent);
		registerReceiver(broadcastReceiver, new IntentFilter(ServiceClass.BROADCAST_ACTION));
	}
	 
	 
	 
	 
	 //onpause method
	 @Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		unregisterReceiver(broadcastReceiver);
		stopService(intent); 
	}
	 
	 //broad cast recevier
	 private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
	        @Override
	        public void onReceive(Context context, Intent intent) {
	        	updateUI(intent);       
	        }
	    };


	protected void updateUI(Intent intent) {
		// TODO Auto-generated method stub
		String counter = intent.getStringExtra("counter"); 
    	String time = intent.getStringExtra("time");
    	Log.d(TAG, counter);
    	Log.d(TAG, time);
    	
    	if(time.equalsIgnoreCase("time")){
    		progressBar.setVisibility(View.INVISIBLE);
			mlist.setVisibility(View.VISIBLE);
			
			tv1.setText(counter);
			if(ServiceClass.customListchild!=null){
    		adapter11 = new SubViewAdapter(BackgroundServicesActivity.this,
					R.layout.searchscreenitem, ServiceClass.customListchild);
			mlist.setAdapter(adapter11);
			adapter11.notifyDataSetChanged();
			}
    		
    		
    		
    	}
    	
    	
	}


	@Override
	public void onItemClicked1(SubViewChildModel s) {
		// TODO Auto-generated method stub
		
	}
	   
	    
	    
	    
}
