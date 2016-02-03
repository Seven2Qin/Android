/**
 * 项目名：     Travel
 * 文件名：     SystemService.java
 * 文件描述： 
 * 作者：         Administrator
 * 创建时间：  2013年7月17日
 * 版权声明 ： Copyright (C) 2008-2010 RichPeak
 *
 */
package com.mendong.travel.service;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Binder;
import android.os.IBinder;

/**
 * 类名称：     SystemService
 * 作者：         Administrator
 * 创建时间：  2013年7月17日 下午4:31:46
 * 类描述：    
 * 版权声明 ： Copyright (C) 2008-2010 RichPeak
 * 修改时间：  2013年7月17日 下午4:31:46
 *
 */
public final class TravelService extends IntentService
{

    /**
     * TAG
     */
    private static final String TAG = "TravelService";
    
    public TravelService()
    {
        super("TravelService");
    }

    @Override
    protected void onHandleIntent(Intent arg0)
    {
        // TODO Auto-generated method stub

    }

}
