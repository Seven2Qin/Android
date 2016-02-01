/**
 * 项目名：       SystemService
 * 文件名：       INetCallBack.java
 * 文件描述： 
 * 作者：            Administrator
 * 创建时间：  2012-6-21
 * 版权声明 ： Copyright (C) 2008-2010 RichPeak
 *
 */
package com.mendong.travel.network;

/**
 * 类名称：       INetCallBack
 * 作者：            Administrator
 * 创建时间：  2012-6-21 下午3:50:30
 * 类描述：    
 * 版权声明 ： Copyright (C) 2008-2010 RichPeak
 * 修改时间：  2012-6-21 下午3:50:30
 *
 */
public interface ICallBack
{
    public abstract void onDownloadCallback(ClsRequest req, int resultcode,
            Object resultObj);

}
