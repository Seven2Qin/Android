package com.mendong.travel.ui.adapter;

import com.mendong.travel.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TabHost;

public class ViewTouch extends View{

	private Bitmap bmp;
	private CallBack callBack;
	public ViewTouch(Context context, AttributeSet attrs) 
	{
		super(context, attrs);
		bmp = BitmapFactory.decodeResource(getResources(),R.drawable.bg_viewtouch);
	}

	protected void onDraw(Canvas canvas) 
	{
		canvas.drawBitmap(bmp,0,0,null);
	}

	public boolean onTouchEvent(MotionEvent event)
	{
		callBack.setCurrentTab();
		Log.i("sysout","callback-Touch");
		return super.onTouchEvent(event);
	}
	
	
	public void addCallBackListener(CallBack callBack)
	{
		this.callBack = callBack;
	}
	
	public interface CallBack
	{
		void setCurrentTab();
	}
	
	

}
