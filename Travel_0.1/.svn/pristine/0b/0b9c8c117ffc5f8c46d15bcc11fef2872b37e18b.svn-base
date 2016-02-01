/**
 * 项目名：     SystemService
 * 文件名：     InputStreamUtils.java
 * 文件描述： 
 * 作者：         Administrator
 * 创建时间：  2012-11-26
 * 版权声明 ： Copyright (C) 2008-2010 RichPeak
 *
 */
package com.mendong.travel.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;

import com.mendong.travel.utils.log.ClsLog;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/**
 * 类名称：     InputStreamUtils
 * 作者：         Administrator
 * 创建时间：  2012-11-26 上午11:39:02
 * 类描述：      流工具
 * 版权声明 ： Copyright (C) 2008-2010 RichPeak
 * 修改时间：  2012-11-26 上午11:39:02
 *
 */
public final class InputStreamUtils
{
    private static final int BUFFER_SIZE = 4096;

    private boolean download = true;

    public InputStreamUtils()
    {
    }

    public void setDownload(boolean download)
    {
        this.download = download;
    }

    /**
     * 方法名称：  InputStreamTOString
     * 作者：         Administrator
     * 方法描述： 将InputStream转换成String  
     * 输入参数：  @param in
     * 输入参数：  @return
     * 输入参数：  @throws Exception
     * 返回类型：  String
     */
    public String InputStreamTOString(InputStream in) throws Exception
    {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] data = new byte[BUFFER_SIZE];
        int count = -1;
        while ((count = in.read(data, 0, BUFFER_SIZE)) != -1)
            outStream.write(data, 0, count);

        data = null;
        return new String(outStream.toByteArray(), "ISO-8859-1");
    }

    /**
     * 方法名称：  InputStreamTOString
     * 作者：         Administrator
     * 方法描述： 将InputStream转换成某种字符编码的String 
     * 输入参数：  @param in
     * 输入参数：  @param encoding
     * 输入参数：  @return
     * 输入参数：  @throws Exception
     * 返回类型：  String
     */
    public String InputStreamTOString(InputStream in, String encoding)
            throws Exception
    {

        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] data = new byte[BUFFER_SIZE];
        int count = -1;
        while ((count = in.read(data, 0, BUFFER_SIZE)) != -1)
            outStream.write(data, 0, count);

        data = null;
        return new String(outStream.toByteArray(), "ISO-8859-1");
    }

    /**
     * 方法名称：  StringTOInputStream
     * 作者：         Administrator
     * 方法描述： 将String转换成InputStream  
     * 输入参数：  @param in
     * 输入参数：  @return
     * 输入参数：  @throws Exception
     * 返回类型：  InputStream
     */
    public InputStream StringTOInputStream(String in) throws Exception
    {

        ByteArrayInputStream is = new ByteArrayInputStream(
                in.getBytes("ISO-8859-1"));
        return is;
    }

    /**
     * 方法名称：  InputStreamTOByte
     * 作者：         Administrator
     * 方法描述： 将InputStream转换成byte数组  
     * 输入参数：  @param in
     * 输入参数：  @return
     * 输入参数：  @throws IOException
     * 返回类型：  byte[]
     */
    public byte[] InputStreamTOByte(InputStream in, int time)
            throws IOException
    {
        if (null == in)
        {
            return null;
        }
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] data = new byte[BUFFER_SIZE];
        int count = -1;

        try
        {
            while (download && ((count = in.read(data, 0, BUFFER_SIZE)) != -1))
            {
                outStream.write(data, 0, count);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            data = null;
            try
            {
                outStream.close();
            }
            catch (Exception e)
            {
            }
        }
        ClsLog.logToFile("下载数据大小------------> " + outStream.size());
        return outStream.toByteArray();
    }

    /**
     * 方法名称：  byteTOInputStream
     * 作者：         Administrator
     * 方法描述： 将byte数组转换成InputStream 
     * 输入参数：  @param in
     * 输入参数：  @return
     * 输入参数：  @throws Exception
     * 返回类型：  InputStream
     */
    public InputStream byteTOInputStream(byte[] in) throws Exception
    {
        ByteArrayInputStream is = new ByteArrayInputStream(in);
        return is;
    }

    /**
     * 方法名称：  byteTOString
     * 作者：         Administrator
     * 方法描述： 将byte数组转换成String  
     * 输入参数：  @param in
     * 输入参数：  @return
     * 输入参数：  @throws Exception
     * 返回类型：  String
     */
    public String byteTOString(byte[] in) throws Exception
    {
        InputStream is = byteTOInputStream(in);
        return InputStreamTOString(is);
    }
}
