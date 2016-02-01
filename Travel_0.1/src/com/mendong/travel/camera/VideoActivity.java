package com.mendong.travel.camera;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Environment;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.SeekBar;

import com.mendong.travel.R;

public class VideoActivity extends Activity 
{
	private SurfaceView surfaceView;
	private Button btnPause, btnPlayUrl, btnStop;
	private SeekBar skbProgress;
	private Player player;

	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		
		// Full screen
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.video_main);		
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
				
		surfaceView = (SurfaceView) this.findViewById(R.id.surfaceView1);

		btnPlayUrl = (Button) this.findViewById(R.id.btnPlayUrl);
		btnPlayUrl.setOnClickListener(new ClickEvent());

		btnPause = (Button) this.findViewById(R.id.btnPause);
		btnPause.setOnClickListener(new ClickEvent());

		btnStop = (Button) this.findViewById(R.id.btnStop);
		btnStop.setOnClickListener(new ClickEvent());

		skbProgress = (SeekBar) this.findViewById(R.id.skbProgress);
		skbProgress.setOnSeekBarChangeListener(new SeekBarChangeEvent());
		player = new Player(surfaceView, skbProgress);	
		String url=Environment.getExternalStorageDirectory()+"/bk.mp4";
		player.playUrl(url);
	}
	
	class ClickEvent implements OnClickListener {

		@Override
		public void onClick(View arg0) {
			if (arg0 == btnPause) {
				player.pause();
			} else if (arg0 == btnPlayUrl) {
				String url=Environment.getExternalStorageDirectory()+"/bk.mp4";
				player.playUrl(url);				
			} else if (arg0 == btnStop) {
				VideoActivity.this.finish();
			}

		}
	}

	class SeekBarChangeEvent implements SeekBar.OnSeekBarChangeListener {
		int progress;

		@Override
		public void onProgressChanged(SeekBar seekBar, int progress,
				boolean fromUser) {
			this.progress = progress * player.mediaPlayer.getDuration()
					/ seekBar.getMax();
		}

		@Override
		public void onStartTrackingTouch(SeekBar seekBar) {

		}

		@Override
		public void onStopTrackingTouch(SeekBar seekBar) {
			player.mediaPlayer.seekTo(progress);
		}
	}
}