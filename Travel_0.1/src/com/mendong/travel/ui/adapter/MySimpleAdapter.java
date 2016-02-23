package com.mendong.travel.ui.adapter;


import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.ab.global.AbConstant;
import com.ab.net.AbImageDownloadCallback;
import com.ab.net.AbImageDownloadItem;
import com.ab.net.AbImageDownloadQueue;
import com.ab.util.AbFileUtil;
import com.ab.util.AbStrUtil;
import com.mendong.travel.R;
import com.mendong.travel.Bean.bzb.BZBBean;
import com.mendong.travel.control.ClsLogic;
import com.mendong.travel.view.bzb.BZBListView.MyOnClickListener;

public class MySimpleAdapter  extends BaseAdapter{

	private Context context;
	private int mBgResource;
	private List<BZBBean> mBzbList;
	private Bitmap bitmapBzbIcon;
	private AbImageDownloadQueue mAbImageDownloadQueue = null;
	private MyOnClickListener mImgListener;
	private Bitmap headBmp;
	
	public MySimpleAdapter(Context context, int mBgResource, List<BZBBean> bzbList, MyOnClickListener imgListener) 
	{
		this.context = context;
		this.mBgResource = mBgResource;
		this.mBzbList = bzbList;
		this.mAbImageDownloadQueue = AbImageDownloadQueue.getInstance();
		bitmapBzbIcon = BitmapFactory.decodeResource(context.getResources(),R.drawable.bzbicon);
		mImgListener = imgListener;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mBzbList.size();
	}

	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mBzbList.get(position);
	}

	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
	
	public Bitmap getShopHeadLogo()
	{
		return headBmp;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) 
	{
		 final ViewHolder holder;

	        if (convertView == null)
	        {
	            //使用自定义的list_items作为Layout
	            convertView = View.inflate(context,R.layout.layout_tab_item,null);
	            //减少findView的次数
	            holder = new ViewHolder();
	            //初始化布局中的元素
	            holder.imgView_item = ((ImageView) convertView
	                    .findViewById(R.id.item_image));
	            holder.imgView_item.setBackgroundResource(mBgResource);
	            convertView.setTag(holder);
	        }
	        else
	        {
	            holder = (ViewHolder) convertView.getTag();
	        }

	        BZBBean mBZBBean = mBzbList.get(position);
	        String url = mBZBBean.getLogoUrl();
	        
	        if (!AbStrUtil.isEmpty(url))
	        {
	            final View cv = convertView;
	            final int pos = position;
	            //设置下载项 
	            AbImageDownloadItem item = new AbImageDownloadItem();
	            //设置显示的大小
	            item.width = 80;
	            item.height = 80;
	            //设置为缩放
	            item.type = AbConstant.NONEIMG;
	            item.imageUrl = ClsLogic.PIC_PRE_URL + url;

	            //            holder.itemsIcon.setImageBitmap(AbFileUtil.getBitmapFromSDCache(url, type, newWidth, newHeight)
	            //                    .getBitmapFormSrc("image/image_loading.png"));

	            //下载完成后更新界面
	            item.callback = new AbImageDownloadCallback()
	            {
	                public void update(Bitmap bitmap, String imageUrl)
	                {
	                    if (bitmap != null && !bitmap.isRecycled())
	                    {
	                    	headBmp = bitmap;
	                        holder.imgView_item.setImageBitmap(bitmap);
	                        holder.imgView_item.setOnClickListener(new View.OnClickListener()
	                        {
	                        	public void onClick(View v)
	                        	{
	                        		if (null != mImgListener)
	                        		{
	                        			mImgListener.onClick(cv, pos);
	                        		}
	                        	}
	                        });
	                    }
	                    else
	                    {
	                        holder.imgView_item.setImageResource(R.drawable.image_error);
	                    }

	                }
	            };
	            mAbImageDownloadQueue.download(item);
	        }
	        else
	        {
	            holder.imgView_item.setImageBitmap(AbFileUtil
	                    .getBitmapFormSrc("image/image_no.png"));
	        }

	        return convertView;
	}
	
	 /**
     * View元素
     */
    private static class ViewHolder
    {
        protected ImageView imgView_item;
    }

}