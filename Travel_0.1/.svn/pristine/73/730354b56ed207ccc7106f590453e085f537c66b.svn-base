/**
 * 项目名：     SystemService
 * 文件名：     GZipUtil.java
 * 文件描述： 
 * 作者：         Administrator
 * 创建时间：  2012-11-23
 * 版权声明 ： Copyright (C) 2008-2010 RichPeak
 *
 */
package com.mendong.travel.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * 类名称：     GZipUtil
 * 作者：         Administrator
 * 创建时间：  2012-11-23 下午4:21:59
 * 类描述：    
 * 版权声明 ： Copyright (C) 2008-2010 RichPeak
 * 修改时间：  2012-11-23 下午4:21:59
 *
 */
public class GZipUtil
{
    private GZipUtil()
    {
    }

    /**
     * 方法名称：  compress
     * 作者：         Administrator
     * 方法描述：  
     * 输入参数：  @param data
     * 输入参数：  @return
     * 返回类型：  byte[]
     */
    public static byte[] compress(byte[] data)
    {
        GZIPOutputStream gos = null;
        try
        {
            ByteArrayInputStream bais = new ByteArrayInputStream(data);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            gos = new GZIPOutputStream(baos);

            byte[] buf = new byte[1024];
            int num;
            while ((num = bais.read(buf)) != -1)
            {
                gos.write(buf, 0, num);
            }
            gos.finish();
            gos.flush();
            gos.close();
            byte[] output = baos.toByteArray();
            return output;
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 方法名称：  decompress
     * 作者：         Administrator
     * 方法描述：  
     * 输入参数：  @param data
     * 输入参数：  @return
     * 返回类型：  byte[]
     */
    public static byte[] decompress(byte[] data)
    {
        ByteArrayInputStream bais = null;
        GZIPInputStream gis = null;
        ByteArrayOutputStream baos = null;
        try
        {
            bais = new ByteArrayInputStream(data);
            gis = new GZIPInputStream(bais);
            baos = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            int num;
            while ((num = gis.read(buf)) != -1)
            {
                baos.write(buf, 0, num);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return null;
        }
        finally
        {
            if (null != gis)
            {
                try
                {
                    gis.close();
                }
                catch (IOException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            if (null != baos)
            {
                try
                {
                    baos.close();
                }
                catch (IOException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        return baos.toByteArray();
    }

    /**
     * 方法名称：  decompress
     * 作者：         Administrator
     * 方法描述：  
     * 输入参数：  @param in
     * 输入参数：  @return
     * 返回类型：  byte[]
     */
    public static byte[] decompress(InputStream in)
    {
        try
        {
            GZIPInputStream gis = new GZIPInputStream(in, 1024 * 10);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            int num;
            while ((num = gis.read(buf)) != -1)
            {
                baos.write(buf, 0, num);
            }
            gis.close();
            byte[] ret = baos.toByteArray();
            baos.close();
            return ret;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return null;
        }
    }
}
