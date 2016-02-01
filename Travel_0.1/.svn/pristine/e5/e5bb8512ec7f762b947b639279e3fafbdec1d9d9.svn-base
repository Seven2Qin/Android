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

public class ViewButton extends View{

	private Bitmap bmpSector;
	private Rect rectBmp;
	private Rect rectTouch;
	private final static int NI_TOUCH_SIZE =2;
	public ViewButton(Context context)
	{
		super(context);
	}
	
	public ViewButton(Context context,AttributeSet attributeSet)
	{
		super(context,attributeSet);
		bmpSector = BitmapFactory.decodeResource(getResources(),R.drawable.sector);
		rectBmp = new Rect(0,0,bmpSector.getWidth(),bmpSector.getHeight());
		rectTouch = new Rect();
		
	}

	protected void onDraw(Canvas canvas) 
	{
		canvas.drawBitmap(bmpSector,rectBmp.left,rectBmp.top,null);
	}

	public boolean onTouchEvent(MotionEvent event)
	{
		setRectTouch(event);
		if(rectTouch.intersect(rectBmp))
		{
			//上面部分
			if(rectTouch.top<rectBmp.bottom/2)
			{
				//左
				if(rectTouch.left<rectBmp.right/3 && rectTouch.top>rectBmp.bottom/4)
				{
					Log.i("sysout","上左");
				}
				if(rectTouch.left>rectBmp.right/3 && rectTouch.left<rectBmp.right/3*2 
						&& rectTouch.top<rectBmp.bottom/4)
				{
					Log.i("sysout","上中");
					this.setVisibility(-1);
				}
				if(rectTouch.left>rectBmp.right/3*2 && rectTouch.top>rectBmp.bottom/4)
				{
					Log.i("sysout","上右");
				}
				
			}
			else
			{
				if(rectTouch.left<rectBmp.right/3 && rectTouch.top>rectBmp.bottom/2)
				{
					Log.i("sysout","下左");
				}
				if(rectTouch.left>rectBmp.right/3 && rectTouch.left<rectBmp.right/2
						&&rectTouch.top>rectBmp.bottom/4*3)
				{
					Log.i("sysout","下中左");
				}
				if(rectTouch.left>rectBmp.right/2 && rectTouch.left<rectBmp.right/3*2
						&&rectTouch.top>rectBmp.bottom/4*3)
				{
					Log.i("sysout","下中右");
				}
				if(rectTouch.left>rectBmp.right/3*2 && rectTouch.top>rectBmp.bottom/2)
				{
					Log.i("sysout","下右");
				}
			}
		}
		return super.onTouchEvent(event);
	}
	
	
	public void setRectTouch(MotionEvent event)
	{
		rectTouch.left = (int) event.getX();
		rectTouch.top = (int) event.getY();
		rectTouch.right = rectTouch.left + NI_TOUCH_SIZE;
		rectTouch.bottom = rectTouch.top + NI_TOUCH_SIZE;
	}
	
	

}
