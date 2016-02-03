package com.mendong.travel.asyncload;

import com.mendong.travel.R;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ImageCache
{
	private View baseView;
	private TextView textView;
	private TextView textPrice;
	private ImageView imageView;

	public ImageCache(View baseView)
	{
		this.baseView = baseView;
	}

	public TextView getTextView()
	{
		if (textView == null)
		{
			textView = (TextView) baseView.findViewById(R.id.item_goodsName);
		}
		return textView;
	}
	public TextView getTextPrice()
	{
		if (textPrice == null)
		{
			textPrice = (TextView) baseView.findViewById(R.id.item_goodsPrice);
		}
		return textPrice;
	}

	public ImageView getImageView()
	{
		if (imageView == null)
		{
			imageView = (ImageView) baseView.findViewById(R.id.item_image);
		}
		return imageView;
	}
}