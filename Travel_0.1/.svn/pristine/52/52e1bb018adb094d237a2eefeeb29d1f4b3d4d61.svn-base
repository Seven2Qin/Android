/**
 * 项目名：     Travel
 * 文件名：     BZBAdapter.java
 * 文件描述： 
 * 作者：         Administrator
 * 创建时间：  2013年7月20日
 * 版权声明 ： Copyright (C) 2008-2010 RichPeak
 *
 */
package com.mendong.travel.view.adapter.bzb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ab.global.AbConstant;
import com.ab.net.AbImageDownloadCallback;
import com.ab.net.AbImageDownloadItem;
import com.ab.net.AbImageDownloadQueue;
import com.ab.util.AbFileUtil;
import com.ab.util.AbStrUtil;
import com.mendong.travel.R;
import com.mendong.travel.Bean.bzb.BZBBean;
import com.mendong.travel.control.ClsLogic;
import com.mendong.travel.tools.Tools;
import com.mendong.travel.view.bzb.BZBListView.MyOnClickListener;
import com.mendong.travel.view.bzbinfo.BZBInfoView;

/**
 * 类名称：     BZBAdapter
 * 作者：         Administrator
 * 创建时间：  2013年7月20日 下午2:55:32
 * 类描述：    
 * 版权声明 ： Copyright (C) 2008-2010 RichPeak
 * 修改时间：  2013年7月20日 下午2:55:32
 *
 */
public final class BZBAdapter extends BaseAdapter
{
    private Context mContext;
    //xml转View对象
    private LayoutInflater mInflater;
    //单行的布局
    private int mResource;
    private List<BZBBean> mBzbList;
    //商家头像黑色圆圈背景
    private Bitmap bitmapBzbIcon;
    //商家缩放圆圈头像
    private Bitmap bitmapBzb;
    //商家合成头像
    private Bitmap bitmapBzbCombine;
    //view的id
    private int[] mTo;
    /**
     * 图片现在线程（按队列下载）
     */
    private AbImageDownloadQueue mAbImageDownloadQueue = null;
    //这个用来保存 imageview 的引用  
    private ArrayList viewList = new ArrayList();
    //这个用来 保存 bitmap  
    private Map<String, Bitmap> bitmapList = new HashMap<String, Bitmap>();
    private MyOnClickListener mImgListener;
    public BZBAdapter(Context context, List<BZBBean> bzbList, int resource,
            int[] to, MyOnClickListener imgListener)
    {
        this.mContext = context;
        this.mBzbList = bzbList;
        this.mResource = resource;
        this.mTo = to;
        this.mImgListener = imgListener;
        //用于将xml转为View
        this.mInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.mAbImageDownloadQueue = AbImageDownloadQueue.getInstance();
        //商家头像黑色圆圈背景
        bitmapBzbIcon = BitmapFactory.decodeResource(mContext.getResources(),R.drawable.bzbicon);
    }

    public int getCount()
    {
        return mBzbList.size();
    }

    public Object getItem(int position)
    {
        return mBzbList.get(position);
    }

    public long getItemId(int position)
    {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent)
    {
        final ViewHolder holder;
        if (convertView == null)
        {
            //使用自定义的list_items作为Layout
            convertView = mInflater.inflate(mResource, parent, false);
            //减少findView的次数
            holder = new ViewHolder();
            //初始化布局中的元素
            holder.itemsIcon = ((ImageView) convertView.findViewById(mTo[0]));
            holder.itemsTitle = ((TextView) convertView.findViewById(mTo[1]));
            holder.itemsText = ((TextView) convertView.findViewById(mTo[2]));
            holder.itemsId = ((TextView) convertView.findViewById(mTo[3]));
            holder.itemsId.setVisibility(View.GONE);
            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }

        /*if (position == 0)
        {*/
            convertView.setBackgroundResource(R.drawable.list);
        /*}*/
        //有更改
        /*else if (position % 2 == 1)
        {
            convertView.setBackgroundResource(R.drawable.list1);
        }
        else if (position % 2 == 0)
        {
            convertView.setBackgroundResource(R.drawable.list2);
        }*/

        
        //获取该行的数据

        BZBBean mBZBBean = mBzbList.get(position);

        holder.itemsIcon.setImageResource(R.drawable.image_loading);
        holder.itemsTitle.setText(mBZBBean.getName());
        holder.itemsText.setText(mBZBBean.getInfo());
        //        holder.itemsId.setText(mBZBBean.getId());

        String url = mBZBBean.getLogoUrl();
        //        if (position % 2 == 0)
        //        {
        //            url = ClsLogic.PIC_PRE_URL + "dys_banner_01.png";//mBZBBean.getLogoUrl();
        //        }
        //        else
        //        {
        //            url = ClsLogic.PIC_PRE_URL + "dys_banner_02.png";//mBZBBean.getLogoUrl();
        //        }

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
                        putBitmap(bitmap, imageUrl);
                        //                        System.out.println("bitmap ----> " + bitmap.isRecycled());
//                        holder.itemsIcon.setImageBitmap(bitmap);
                        
                        bitmapBzb = Tools.toRoundBitmap(bitmap,1.0f);
                        bitmapBzbCombine = Tools.combineBitmap(bitmapBzbIcon, bitmapBzb);
                        holder.itemsIcon.setImageBitmap(bitmapBzbCombine);

                        holder.itemsIcon
                                .setOnClickListener(new View.OnClickListener()
                                {

                                    @Override
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
                        //                        holder.itemsIcon.setImageBitmap(AbFileUtil
                        //                                .getBitmapFormSrc("R.drawable.image_error"));
                        holder.itemsIcon
                                .setImageResource(R.drawable.image_error);
                    }

                }
            };
            mAbImageDownloadQueue.download(item);
        }
        else
        {
            holder.itemsIcon.setImageBitmap(AbFileUtil
                    .getBitmapFormSrc("image/image_no.png"));
        }

        return convertView;
    }
    
    /**
     * 获得商家圆形头像
     * @return
     */
    public Bitmap getBzbHeadBitmap()
    {
    	Bitmap bitmap = Tools.toRoundBitmap(bitmapBzb, 2.0f);
    	return bitmap;
    }

    public void putBitmap(Bitmap bitmap, String imageUrl)
    {
        //        if (null != bitmapList && !bitmapList.containsKey(imageUrl))
        //        {
        //            bitmapList.put(imageUrl, bitmap);
        //        }
    }

    public void release()
    {
        //        if (null != bitmapList)
        //        {
        //            Iterator iter = bitmapList.entrySet().iterator();
        //            while (iter.hasNext())
        //            {
        //                Map.Entry<String, Bitmap> entry = (Map.Entry<String, Bitmap>) iter
        //                        .next();
        //                //                String key = entry.getKey();
        //                Bitmap bitmap = entry.getValue();
        //                if (null != bitmap && !bitmap.isRecycled())
        //                {
        //                    bitmap.recycle();
        //                    bitmap = null;
        //                }
        //            }
        //            bitmapList.clear();
        //        }
        if (null != mBzbList)
        {
            mBzbList.clear();
            mBzbList = null;
        }
        mImgListener = null;
    }

    /**
     * View元素
     */
    private static class ViewHolder
    {
        protected ImageView itemsIcon;
        protected TextView itemsTitle;
        protected TextView itemsText;
        protected TextView itemsId;
    }
}
