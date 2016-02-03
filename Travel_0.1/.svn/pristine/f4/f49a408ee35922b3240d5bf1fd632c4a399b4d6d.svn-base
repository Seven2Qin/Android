/**
 * 项目名：     Travel
 * 文件名：     BZBAdapter.java
 * 文件描述： 
 * 作者：         Administrator
 * 创建时间：  2013年7月20日
 * 版权声明 ： Copyright (C) 2008-2010 RichPeak
 *
 */
package com.mendong.travel.view.adapter.map;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.mendong.travel.R;

/**
 * 类名称：     BZBAdapter
 * 作者：         Administrator
 * 创建时间：  2013年7月20日 下午2:55:32
 * 类描述：    
 * 版权声明 ： Copyright (C) 2008-2010 RichPeak
 * 修改时间：  2013年7月20日 下午2:55:32
 *
 */
public final class MapGridViewAdapter extends BaseAdapter
{

    private Context mContext;

    //xml转View对象
    private LayoutInflater mInflater;

    private int clickTemp = -1;

    //单行的布局
    private int mResource;
    
    private int mBgResource;

    private ArrayList<HashMap<String, Object>> data;

    public MapGridViewAdapter(Context context, int resource, int bgresource, 
            ArrayList<HashMap<String, Object>> data)
    {
        this.mContext = context;
        this.data = data;
        this.mResource = resource;
        this.mBgResource = bgresource;
        //用于将xml转为View
        this.mInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount()
    {
        // TODO Auto-generated method stub
        return data.size();
    }

    @Override
    public Object getItem(int position)
    {
        // TODO Auto-generated method stub
        return data.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        // TODO Auto-generated method stub
        return position;
    }

    public void setSeclection(int position)
    {
        clickTemp = position;
    }

    /* (non-Javadoc)
     * (覆盖方法)
     * 方法名称：  getView
     * 作者：         Administrator
     * 方法描述：  	
     * @see android.widget.SimpleAdapter#getView(int, android.view.View, android.view.ViewGroup)
     */
    @Override
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
            holder.itemsIcon = ((ImageView) convertView
                    .findViewById(R.id.item_image));
            holder.itemsIcon.setBackgroundResource(mBgResource + position);
            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }



        return convertView;
    }

    /**
     * View元素
     */
    private static class ViewHolder
    {
        protected ImageView itemsIcon;
    }

}
