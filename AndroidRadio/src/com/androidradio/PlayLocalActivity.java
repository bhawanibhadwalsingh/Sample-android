package com.androidradio;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class PlayLocalActivity extends Activity implements OnClickListener,SeekBar.OnSeekBarChangeListener {
	SeekBar seek_bar;
	Button play_button, pause_button;
	MediaPlayer player;
	TextView text_shown;
	Handler seekHandler = new Handler();
	private Utilities utils;
	private Handler mHandler = new Handler();;
	
	int progress = 0;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.local_activity);

		getInit();
		seekUpdation();
	}

	public void getInit() {
		seek_bar = (SeekBar) findViewById(R.id.seek_bar);
		play_button = (Button) findViewById(R.id.play_button);
		pause_button = (Button) findViewById(R.id.pause_button);
		text_shown = (TextView) findViewById(R.id.text_shown);
		play_button.setOnClickListener(this);
		pause_button.setOnClickListener(this);
		player = MediaPlayer.create(this, R.raw.aaa);
		seek_bar.setMax(player.getDuration());
		// Listeners
		seek_bar.setOnSeekBarChangeListener(this); 
//		int maxVolume = player.getDuration();
//        int curVolume = player.getCurrentPosition();
//
//        seek_bar.setMax(maxVolume);
//        seek_bar.setProgress(curVolume);
		utils = new Utilities();

	}

	Runnable run = new Runnable() {

		@Override
		public void run() {
			seekUpdation();
		}
	};

	public void seekUpdation() {

		seek_bar.setProgress(player.getCurrentPosition());
//		seekHandler.postDelayed(run, 100);
		
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.play_button:
			text_shown.setText("Playing...");
			player.start();
			break;
		case R.id.pause_button:
			player.pause();
			text_shown.setText("Paused...");
		}

	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {
		// TODO Auto-generated method stub
		 progress = progress;
//		 seekUpdation();
		 player.seekTo(progress);
//		 seekUpdation();
		 
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
		
	}
	
	


}
