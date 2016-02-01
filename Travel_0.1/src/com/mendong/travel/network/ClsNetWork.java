/**
 * 项目名：       SystemService
 * 文件名：       ClsNetWork.java
 * 文件描述： 
 * 作者：            Administrator
 * 创建时间：  2012-6-21
 * 版权声明 ： Copyright (C) 2008-2010 RichPeak
 *
 */
package com.mendong.travel.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.mendong.travel.utils.log.ClsLog;



/**
 * 类名称：       ClsNetWork
 * 作者：            Administrator
 * 创建时间：  2012-6-21 下午3:56:04
 * 类描述：    
 * 版权声明 ： Copyright (C) 2008-2010 RichPeak
 * 修改时间：  2012-6-21 下午3:56:04
 *
 */
public final class ClsNetWork
{
    private static final String TAG = "ClsNetWork ";

    public static String getNetworkInfo(Context c)
    {
        ConnectivityManager manager = (ConnectivityManager) c
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        String apn = null;
        if (null != info)
        {
            apn = info.getExtraInfo();
            if (apn == null)
            {
                apn = "1";//"Don't get mobile info";   
            }
        }
        else
        {
            apn = "2";//"Don't get network info ";   
        }
        ClsLog.I(TAG, "apn ==" + apn);
        return apn;
    }

    /**
     * 方法名称：  isConnectNetWork
     * 作者：            Administrator
     * 方法描述：  检测网络是否有效
     * 输入参数：  @param c
     * 输入参数：  @return
     * 返回类型：  boolean true-有效，false-无效
     */
    public static boolean isConnectNetWork(Context c)
    {
        ConnectivityManager manager = (ConnectivityManager) c
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] infos = manager.getAllNetworkInfo();
        if (null != infos)
        {
            for (int i = 0; i < infos.length; ++i)
            {
                if (infos[i].isConnected())
                {
                    return true;
                }
            }
        }
        return false;
    }

}
