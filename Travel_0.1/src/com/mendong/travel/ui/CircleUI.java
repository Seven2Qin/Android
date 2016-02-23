package com.mendong.travel.ui;

import java.util.Timer;
import java.util.TimerTask;

import com.mendong.travel.TravelApp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.Window;
import android.widget.Toast;

public class CircleUI extends Activity{

	private CircleView circleView;
	private Timer timer;
	private boolean isTouch;
	private Thread thread;
	private final static int NI_CIRCLE_COUNT = 6;
	private final static String[]ARR_VIEW_NAME = {"老门东","中山陵","总统府","夫子庙","明孝陵","紫金山"};
	private final static String[]ARR_MARKET_NAME = {"新百","金鹰","万达","八佰伴","水游城","德基"};
	private int niNo = 0;
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		//获得MainUI传递过来的数据
		getIntentData();
		//获取屏幕尺寸
		Display display = this.getWindowManager().getDefaultDisplay();
		if(niNo==1)
		   circleView = new CircleView(this, display.getWidth(), display.getHeight(),ARR_MARKET_NAME,NI_CIRCLE_COUNT);
		else if(niNo == 2)
			circleView = new CircleView(this, display.getWidth(), display.getHeight(),ARR_VIEW_NAME,NI_CIRCLE_COUNT);
		setContentView(circleView);
		TravelApp.getInstance().addActivity(this);
		//每次调用，重新设置
		isTouch = false;
		//构造气泡计时器线程
		timer = new Timer();
		//开启气泡线程
		//timer.schedule(new TimerMonitor(),500,20);
		
		thread= new Thread(runnable);
		thread.start();
	}
	
	private void getIntentData()
	{
		Intent intent = this.getIntent();
		niNo = intent.getIntExtra("NO",-1);
	}
	
	/**
	 * 气泡计时器逻辑类，调用气泡logic方法
	 * @author Administrator
	 *
	 */
	private class TimerMonitor extends TimerTask
	{

		public void run() 
		{
			handler.sendEmptyMessage(1);
		}		
	}
	
	Runnable runnable = new Runnable()
	{

		public void run() 
		{
			while(true)
			{
				handler.sendEmptyMessage(1);
				try {
					thread.sleep(20);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		
	};
	
	Handler handler = new Handler()
	{
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			circleView.logic();
		}
		
	};
	
	public boolean onTouchEvent(MotionEvent event) 
	{
		
		if(event.getAction()==MotionEvent.ACTION_UP)
		{
			String strNiTouch = circleView.onTouch(event);
			
			if(niNo==1)
			{
					if(strNiTouch=="新百")
					{
						Toast.makeText(this, "新百大厦正在建设中...", Toast.LENGTH_SHORT).show();
					}
					else if(strNiTouch=="金鹰")
					{
						Toast.makeText(this, "金鹰国际正在建设中...", Toast.LENGTH_SHORT).show();
					}
					else if(strNiTouch=="万达")
					{
						Toast.makeText(this, "万达广场正在建设中...", Toast.LENGTH_SHORT).show();
					}
					else if(strNiTouch=="八佰伴")
					{
						Toast.makeText(this, "八佰伴商厦正在建设中...", Toast.LENGTH_SHORT).show();
					}
					else if(strNiTouch=="水游城")
					{
						Toast.makeText(this, "水游城商厦正在建设中...", Toast.LENGTH_SHORT).show();
					}
					else if(strNiTouch=="德基")
					{
						Toast.makeText(this, "德基广场正在建设中...", Toast.LENGTH_SHORT).show();
					}
			}
			else if(niNo==2)
			{
					if(strNiTouch=="老门东")
					{
						Intent intent = new Intent(CircleUI.this,AreaUI.class);
						intent.putExtra("location","老门东");
						startActivity(intent);
					}
					else if(strNiTouch=="中山陵")
					{
						Toast.makeText(this, "中山陵景区正在建设中...", Toast.LENGTH_SHORT).show();
					}
					else if(strNiTouch=="总统府")
					{
						Toast.makeText(this, "总统府景区正在建设中...", Toast.LENGTH_SHORT).show();
					}
					else if(strNiTouch=="夫子庙")
					{
						Toast.makeText(this, "夫子庙景区正在建设中...", Toast.LENGTH_SHORT).show();
					}
					else if(strNiTouch=="明孝陵")
					{
						Toast.makeText(this, "明孝陵景区正在建设中...", Toast.LENGTH_SHORT).show();
					}
					else if(strNiTouch=="紫金山")
					{
						Toast.makeText(this, "紫金山景区正在建设中...", Toast.LENGTH_SHORT).show();
					}
			}
				
		}
		
		return super.onTouchEvent(event);
	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if(keyCode == KeyEvent.KEYCODE_BACK)
		{
			//thread.stop();
		}
		return super.onKeyDown(keyCode, event);
	}
	
	

}