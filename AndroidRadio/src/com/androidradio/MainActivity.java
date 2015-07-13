package com.androidradio;

import java.io.IOException;



import android.app.Activity;
import android.app.ProgressDialog;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity implements OnBufferingUpdateListener {

	final String songs_urIs = "http://2773.live.streamtheworld.com:80/WXLMFM_SC";
	// private TextView txt_song_title;
	private MediaPlayer mediaplayer;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ImageView btn_play = (ImageView) findViewById(R.id.button_play);
		ImageView btn_pause = (ImageView) findViewById(R.id.button_pause);

		// txt_song_title = (TextView) findViewById(R.id.txt_song_title);

		mediaplayer = new MediaPlayer();
		mediaplayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
		mediaplayer.setOnBufferingUpdateListener(this);

		btn_play.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
//				try {
//					mediaplayer.setDataSource(songs_urIs);
//				} catch (IllegalArgumentException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} catch (IllegalStateException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				try {
//					mediaplayer.prepare();
//				} catch (IllegalStateException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				mediaplayer.start();
//				
//				if(!mediaplayer.isPlaying()){
//					
//				}else{
//					
//				}
				new MediaAsync().execute();
				
				
			}
		});
		//

		btn_pause.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				try {

					mediaplayer.pause();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});

	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		 if(mediaplayer!=null && mediaplayer.isPlaying()){
			 mediaplayer.stop();
	        }
		
		
		
	}

	@Override
	public void onBufferingUpdate(MediaPlayer mp, int percent) {
		// TODO Auto-generated method stub
		
	}
	
	class MediaAsync extends AsyncTask<Void, String, Void> {
		String result = "";
		String resultText = "", Result;

	private final ProgressDialog dialog = new ProgressDialog(MainActivity.this);

		protected void onPreExecute() {
			this.dialog.setMessage("Buffering....");
			this.dialog.setCancelable(false);
			this.dialog.show();
		}

		@Override
		protected Void doInBackground(Void... unused) {

			try {
				
				try {
					mediaplayer.setDataSource(songs_urIs);
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					mediaplayer.prepare();
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				mediaplayer.start();
				
				
				
				
				
			} catch (Exception e) {

				e.printStackTrace();
			}

			return null;
		}

		@Override
		protected void onPostExecute(Void unused) {
			if(mediaplayer.isPlaying()){
				try {
					this.dialog.dismiss();
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
			
	

			

		}
	}
	
}