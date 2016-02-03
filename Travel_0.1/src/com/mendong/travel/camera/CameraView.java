package com.mendong.travel.camera;

import android.app.Activity;
import android.content.Intent;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;
import android.widget.VideoView;

import com.mendong.travel.R;

public class CameraView extends Activity{

	private Camera mCamera;   
	private CameraPreview mPreview;
	FrameLayout preview;
	
	public static final int MEDIA_TYPE_IMAGE = 1;
	
	VideoView tileView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//if (!io.vov.vitamio.LibsChecker.checkVitamioLibs(this))
			//return;
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		setContentView(R.layout.camera);
		
		// Create an instance of Camera  
		mCamera = getCameraInstance();
		
		// Create our Preview view and set it as the content of our activity.        
		mPreview = new CameraPreview(this, mCamera);        
		preview = (FrameLayout) findViewById(R.id.camera_preview);      
		preview.addView(mPreview);
		
		tileView = new VideoView(this);	
		tileView.setZOrderMediaOverlay(true);
		tileView.setVideoPath("/storage/sdcard2/gtx2.mov");		
		
		Button captureButton = (Button) findViewById(R.id.button_capture);
		captureButton.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v)
			{
				// get an image from the camera
				mCamera.takePicture(null, null, mPicture);
			}
		});
		
		Button videoButton = (Button) findViewById(R.id.button_video);
		videoButton.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v)
			{
				CameraView.this.finish();
			}
		});
	}
	
	public static Camera getCameraInstance(){
		Camera c = null;    
		try 
		{
			c = Camera.open(); 
			// attempt to get a Camera instance  
		}
		catch (Exception e)
		{
			// Camera is not available (in use or does not exist)
		}
		
		return c; // returns null if camera is unavailable}		
	}
	
	public void startVideo()
	{		
		Intent intent = new Intent(getApplicationContext(),VideoActivity.class);
		startActivity(intent);
	}
	
	private PictureCallback mPicture = new PictureCallback() 
	{
		@Override
		public void onPictureTaken(byte[] data, Camera camera) {
			try{
				ImagePHash p = new ImagePHash();
				String image1 = p.getHash(data);
				//String doorOpen = "1111000100111100011100111000011000010001000000110";
				String door = "0000101110000100000000000000000000011010001001010";
				String ceo  = "1000101000010101101000110000101001010010001001000";
				String desk = "1101000000100110011100110111001110000101111000001";
				String video = "0010001000010111011100001110111100101100111111001";
				String wall = "0101011001000000000000000000000000000000000000000";
				String paifang = "1000001100011110000000011100101100011001100001100";
				String vally = "0011110011100110010100001110110001111001101100000";
				String play1 = "1110111011001111000111000100110111111001000110011";
				String play2 = "0100011111111111111111001011101110111111011101100";
				String post = "0100110001010011000011101010100011101010111011010";
				String san = "0100110001010011000011101010100011101010111011010";
				
				if(p.distance(image1, door) <= 10)
				{
					Toast.makeText(getApplicationContext(), "当前位置：公司大门", Toast.LENGTH_SHORT).show();
					
				}
				else if(p.distance(image1, ceo) <= 10)
				{
					Toast.makeText(getApplicationContext(), "当前位置：CEO办公室", Toast.LENGTH_SHORT).show();					
				}
				else if(p.distance(image1, desk) <= 10)
				{
					Toast.makeText(getApplicationContext(), "当前位置：办公桌", Toast.LENGTH_SHORT).show();
				}
				else if(p.distance(image1, wall) <= 10)
				{
					Toast.makeText(getApplicationContext(), "当前位置：3D秀", Toast.LENGTH_SHORT).show();
					startVideo();
				}				
				else if(p.distance(image1, paifang) <= 10)
				{
					Toast.makeText(getApplicationContext(), "当前位置：牌坊", Toast.LENGTH_SHORT).show();
				}
				else if(p.distance(image1, vally) <= 10)
				{
					Toast.makeText(getApplicationContext(), "当前位置：箍桶巷", Toast.LENGTH_SHORT).show();
					tileView.start();
					preview.addView(tileView);
				}
				else if(p.distance(image1, play1) <= 10 || p.distance(image1, play2) <= 10)
				{
					Toast.makeText(getApplicationContext(), "当前位置：踢毽子雕塑", Toast.LENGTH_SHORT).show();
				}
				else if(p.distance(image1, post) <= 10)
				{
					Toast.makeText(getApplicationContext(), "当前位置：寄信雕塑", Toast.LENGTH_SHORT).show();
				}
				else if(p.distance(image1, san) <= 10)
				{
					Toast.makeText(getApplicationContext(), "当前位置：三条营", Toast.LENGTH_SHORT).show();
				}
				else
				{
					Toast.makeText(getApplicationContext(), "当前位置：无法识别", Toast.LENGTH_SHORT).show();
				}
				/*String path = Environment. getExternalStorageDirectory()+"/a.jpg";
				Bitmap imageBitmap = BitmapFactory.decodeFile(path);
				String image1 = p.getHashBitmap(imageBitmap);
				System.out.println(image1);*/
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	};
}
