package com.mendong.travel.tools;
import com.ab.activity.AbActivity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.view.Display;

public final class Tools {

	public Tools() {}
	
	
	/**
     * 转换图片成圆形--并且缩放
     * @param bitmap 传入Bitmap对象
     * @return
     */
    public static Bitmap toRoundBitmap(Bitmap bitmap,float scale) {
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            float roundPx;
            float left,top,right,bottom,dst_left,dst_top,dst_right,dst_bottom;
            if (width <= height) {
                    roundPx = width / 2;
                    top = 0;
                    bottom = width;
                    left = 0;
                    right = width;
                    height = width;
                    dst_left = 0;
                    dst_top = 0;
                    dst_right = width;
                    dst_bottom = width;
            } else {
                    roundPx = height / 2;
                    float clip = (width - height) / 2;
                    left = clip;
                    right = width - clip;
                    top = 0;
                    bottom = height;
                    width = height;
                    dst_left = 0;
                    dst_top = 0;
                    dst_right = height;
                    dst_bottom = height;
            }
             
            Bitmap output = Bitmap.createBitmap(width,
                            height, Config.ARGB_8888);
            Canvas canvas = new Canvas(output);
             
            final int color = 0xff424242;
            final Paint paint = new Paint();
            final Rect src = new Rect((int)left, (int)top, (int)right, (int)bottom);
            final Rect dst = new Rect((int)dst_left, (int)dst_top, (int)dst_right, (int)dst_bottom);
            final RectF rectF = new RectF(dst);

            paint.setAntiAlias(true);
             
            canvas.drawARGB(0, 0, 0, 0);
            paint.setColor(color);
            canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

            paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
            canvas.drawBitmap(bitmap, src, dst, paint);
            
            //缩放功能
            Matrix matrix=new Matrix();
            matrix.postScale(scale, scale);
            Bitmap temp = Bitmap.createBitmap(output,0,0,output.getWidth(),output.getHeight(),matrix,true);
            return temp;//否则return output即可
    }
    
    /**
	 * 合并两张bitmap为一张
	 * @param background
	 * @param foreground
	 * @return Bitmap
	 */
	public static Bitmap combineBitmap(Bitmap background, Bitmap foreground) 
       {
		if (background == null) 
                {
			return null;
		}
		int bgWidth = background.getWidth();
		int bgHeight = background.getHeight();
		int fgWidth = foreground.getWidth();
		int fgHeight = foreground.getHeight();
		Bitmap newmap = Bitmap.createBitmap(bgWidth, bgHeight, Config.ARGB_8888);
		Canvas canvas = new Canvas(newmap);
		canvas.drawBitmap(background, 0, 0, null);
		canvas.drawBitmap(foreground, (bgWidth - fgWidth) / 2,
				(bgHeight - fgHeight) / 2, null);
		canvas.save(Canvas.ALL_SAVE_FLAG);
		canvas.restore();
		return newmap;
	}
	
	/**
	 * 将Bitmap转换为Drawable类型
	 * @param bitmap
	 * @return
	 */
	public static Drawable Bitmap2Drawable(Bitmap bitmap)
	{
		return new BitmapDrawable(bitmap);
	}
	
	public static Display getScreenDisplay(Context context)
    {
    	Display display = ((AbActivity) context).getWindowManager().getDefaultDisplay();
    	return display;
    }
	

}
