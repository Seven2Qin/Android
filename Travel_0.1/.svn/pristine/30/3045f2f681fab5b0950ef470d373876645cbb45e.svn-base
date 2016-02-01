/**
 * 项目名：     Travel
 * 文件名：     TravelApp.java
 * 文件描述： 
 * 作者：         Administrator
 * 创建时间：  2013年7月16日
 * 版权声明 ： Copyright (C) 2008-2010 RichPeak
 *
 */
package com.mendong.travel;

import java.util.ArrayList;
import java.util.List;

import com.baidu.mapapi.BMapManager;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.Looper;

/**
 * 类名称：     TravelApp
 * 作者：         Administrator
 * 创建时间：  2013年7月16日 上午10:50:29
 * 类描述：    
 * 版权声明 ： Copyright (C) 2008-2010 RichPeak
 * 修改时间：  2013年7月16日 上午10:50:29
 *
 */
public final class TravelApp extends Application
{

	private double lng;
	private double lat;
    private static TravelApp mInstance = null;
  	private boolean m_bIsTraceable= false;
	private int m_iCurrentUserPositionId;
	private TravelApp travelApp;
	private List<Activity> acitvityList = new ArrayList<Activity>();
	
	
	public void addActivity(Activity activity)
	{
		acitvityList.add(activity);
	}
	
	public void exit()
	{
		for (Activity activity : acitvityList) 
		{
			activity.finish();
		}
	}
	
	public List<Activity> getList()
	{
		return acitvityList;
	}
    
    public void setTraceable(boolean bTraceable)
    {
    	m_bIsTraceable = bTraceable;
    }
    
    public boolean getTraceable()
    {
    	return m_bIsTraceable;
    }
    
    public void setCurrentLng(double lng)
    {
    	this.lng = lng;
    }
    
    public double getCurrentLng()
    {
    	return lng;
    }
    
    public void setCurrentLat(double lat)
    {
    	this.lat = lat;
    }
    
    public double getCurrentLat()
    {
    	return lat;
    }
    
    public void setCurrentUserPositionId(int id)
    {
    	m_iCurrentUserPositionId = id;
    }
    
    public int getCurrentUserPositionId()
    {
    	return m_iCurrentUserPositionId;
    }
    
    @Override
    public void onCreate()
    {
        super.onCreate();
        mInstance = this;
    }

    public static TravelApp getInstance()
    {
        return mInstance;
    }

    public void endApp()
    {
        //        ActivityStack.getIns().popupAllActivity();
        int sdkVersion = android.os.Build.VERSION.SDK_INT;
        if (sdkVersion <= 7)
        {
            String name = getPackageName();
            ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
            manager.restartPackage(name);
        }
        else
        {
            android.os.Process.killProcess(android.os.Process.myPid());
        }
    }

}
