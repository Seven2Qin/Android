package org.jump.utils.image;

import java.io.File;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.Hashtable;

import org.jump.utils.FileUtil;
import org.jump.utils.down.download.DownloadPool;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;



public class ImageCache
{

	private static ImageCache instance = null;

	private Hashtable<String, BtimapRef> bitmapRefs;

	private ReferenceQueue<Bitmap> q;

	private int screenWidth;

	public synchronized static ImageCache getInstance(int screenWidth)
	{
		if (null == instance)
		{
			instance = new ImageCache(screenWidth);
		}
		return instance;
	}

	private ImageCache(int screenWidth)
	{
		bitmapRefs = new Hashtable<String, BtimapRef>();
		q = new ReferenceQueue<Bitmap>();
		this.screenWidth = screenWidth;
	}

	public Bitmap getBitmap(PictureParam param)
	{
		Bitmap bitmap = getCacheBitmap(param);
		if (null != bitmap)
		{
			return bitmap;
		} else
		{
			getRemoteBitmap(param);
		}
		return null;
	}

	private void getRemoteBitmap(PictureParam param)
	{
		DownloadPool.getInstance().sendMessage(param);
	}

	private Bitmap getCacheBitmap(PictureParam param)
	{
		if (null == param)
			return null;
		Bitmap bitmapImage = null;
		// 缓存中是否有该Bitmap实例的软引用，如果有，从软引用中取得。
		if (bitmapRefs.containsKey(param.localPath)
				&& param.width < screenWidth / 2)
		{
			BtimapRef ref = (BtimapRef) bitmapRefs.get(param.localPath);
			bitmapImage = (Bitmap) ref.get();
		}
		// 如果没有软引用，或者从软引用中得到的实例是null，重新构建一个实例，
		// 并保存对这个新建实例的软引用
		if (bitmapImage == null)
		{
			// 文件不存在，返回空值
			File tempFile = FileUtil.newInstance().getFile(param.localPath,
					false);
			if (null == tempFile || !tempFile.exists())
				return null;

			// 确定缩放比例，如果在确定的缩放比例下无法满足内存需求，则增大缩放比例
			Options options = new Options();
			options.inJustDecodeBounds = true;
			bitmapImage = BitmapFactory.decodeFile(tempFile.getPath(), options);
			int sampleSize = options.outWidth / param.width;
			if (sampleSize == 0)
				sampleSize = 1;
			options.inSampleSize = sampleSize;
			while (Runtime.getRuntime().freeMemory() < options.outHeight
					* options.outWidth * 4 / (sampleSize * sampleSize))
			{
				sampleSize++;
			}
			options.inJustDecodeBounds = false;

			// 解析图片
			try
			{
				bitmapImage = BitmapFactory.decodeFile(tempFile.getPath(),
						options);
				// if (param.circle)
				// {
				// bitmapImage = getRoundedCornerBitmap(Bitmap
				// .createScaledBitmap(bitmapImage, param.width,
				// param.width, false));
				// }

				// 宽度小于登录屏幕宽度1/2的图片放入缓存
				if (bitmapImage.getWidth() <= screenWidth / 2)
				{
					addCacheBitmap(bitmapImage, param.localPath);
				}
			} catch (Exception e)
			{
				bitmapImage = null;
			}
		}
		return bitmapImage;
	}

	public Bitmap getRoundedCornerBitmap(Bitmap bitmap)
	{
		if (null == bitmap)
			return null;
		Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
				bitmap.getHeight(), Config.ARGB_8888);
		Canvas canvas = new Canvas(output);

		final int color = 0xff424242;
		final Paint paint = new Paint();
		final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
		final RectF rectF = new RectF(rect);
		paint.setAntiAlias(true);
		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(color);
		int radio = bitmap.getWidth() / 15;
		canvas.drawRoundRect(rectF, radio, radio, paint);
		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(bitmap, rect, rect, paint);
		return output;
	}

	private void addCacheBitmap(Bitmap bmp, String key)
	{
		cleanCache();// 清除垃圾引用
		BtimapRef ref = new BtimapRef(bmp, q, key);
		bitmapRefs.put(key, ref);
	}

	private void cleanCache()
	{
		BtimapRef ref = null;
		while ((ref = (BtimapRef) q.poll()) != null)
		{
			bitmapRefs.remove(ref._key);
		}
	}

	public void clearCache()
	{
		cleanCache();
		bitmapRefs.clear();
	}

	class BtimapRef extends SoftReference<Bitmap>
	{
		private String _key = "";

		public BtimapRef(Bitmap bmp, ReferenceQueue<Bitmap> q, String key)
		{
			super(bmp, q);
			_key = key;
		}
	}

}
