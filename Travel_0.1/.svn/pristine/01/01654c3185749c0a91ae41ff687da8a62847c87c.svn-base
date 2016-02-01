/**
 * 项目名：     Travel
 * 文件名：     ClsLogic.java
 * 文件描述： 
 * 作者：         Administrator
 * 创建时间：  2013年7月22日
 * 版权声明 ： Copyright (C) 2008-2010 RichPeak
 *
 */
package com.mendong.travel.control;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import android.util.Log;

import com.ab.global.AbAppException;

/**
 * 类名称：     ClsLogic
 * 作者：         Administrator
 * 创建时间：  2013年7月22日 下午12:56:48
 * 类描述：    
 * 版权声明 ： Copyright (C) 2008-2010 RichPeak
 * 修改时间：  2013年7月22日 下午12:56:48
 *
 */
public abstract class ClsLogic
{
    
    private static final String TAG = "ClsLogic";
    
    protected static final String URL = "http://www.xiaooo.cn/mobile/client/mdFunc.php";
    
    public static final String PIC_PRE_URL = "http://www.xiaooo.cn/mobile/shop/";

    // 连接超时
    private static final int timeOut = 12000;
    // 建立连接
    private static final int connectOut = 12000;
    // 获取数据
    private static final int getOut = 30000;
    
    public abstract void init();

    public abstract void requestData() throws AbAppException;
    
    public abstract boolean updateData(boolean clearAllOld);
    
    public abstract void release();

    public InputStream request(byte[] data, String url) throws AbAppException
    {
        try
        {
            // 使用httppost对象提交数据
            HttpPost httpRequest = new HttpPost(url);
            // 超时设置
            HttpParams params = new BasicHttpParams();
            // 从连接池中取连接的超时时间，设置为1秒
            ConnManagerParams.setTimeout(params, timeOut);
            // 通过网络与服务器建立连接的超时时间
            HttpConnectionParams.setConnectionTimeout(params, connectOut);
            // 读响应数据的超时时间
            HttpConnectionParams.setSoTimeout(params, getOut);

            // 设置请求参数
            httpRequest.setParams(params);

            Log.e(TAG, "请求内容：" + new String(data));
            
            httpRequest.setEntity(new ByteArrayEntity(data));

            HttpResponse httpResponse = new DefaultHttpClient()
                    .execute(httpRequest);
            int ret = httpResponse.getStatusLine().getStatusCode();
            if (ret == HttpStatus.SC_OK)
            {
                return httpResponse.getEntity().getContent();
            }
            else
            {
                throw new IOException();
            }
        }
        catch (Exception e)
        {
            AbAppException mAbAppException = new AbAppException(e);
            throw mAbAppException;
        }

    }
}
