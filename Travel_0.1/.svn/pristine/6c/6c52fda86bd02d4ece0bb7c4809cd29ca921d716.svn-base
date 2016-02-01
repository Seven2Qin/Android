package com.mendong.travel.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;

public final class ZlibUtil
{

    private ZlibUtil()
    {
    }

    /**
     * 方法名称：  compress
     * 作者：         Administrator
     * 方法描述： 压缩 
     * 输入参数：  @param data 需要压缩的数据
     * 输入参数：  @return
     * 返回类型：  byte[] 压缩完的数据
     */
    public static byte[] compress(byte[] data)
    {
        byte[] output = new byte[0];

        Deflater compresser = new Deflater();
        compresser.reset();
        compresser.setInput(data);
        compresser.finish();
        ByteArrayOutputStream bos = new ByteArrayOutputStream(data.length);
        try
        {
            byte[] buf = new byte[1024];
            while (!compresser.finished())
            {
                int i = compresser.deflate(buf);
                bos.write(buf, 0, i);
            }
            output = bos.toByteArray();
        }
        catch (Exception e)
        {
            output = data;
            e.printStackTrace();
        }
        finally
        {
            try
            {
                bos.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        compresser.end();
        return output;
    }

    /**
     * 方法名称：  compress
     * 作者：         Administrator
     * 方法描述：  压缩
     * 输入参数：  @param data 需要压缩的数据
     * 输入参数：  @param os 压缩完输出的流
     * 返回类型：  void
     */
    public static void compress(byte[] data, OutputStream os)
    {
        DeflaterOutputStream dos = new DeflaterOutputStream(os);

        try
        {
            dos.write(data, 0, data.length);

            dos.finish();

            dos.flush();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 方法名称：  decompress
     * 作者：         Administrator
     * 方法描述： 解压缩
     * 输入参数：  @param data  需要解压的数据
     * 输入参数：  @return
     * 返回类型：  byte[]    解压完的数据
     */
    public static byte[] decompress(byte[] data)
    {
        byte[] output = new byte[0];

        Inflater decompresser = new Inflater();
        decompresser.reset();
        decompresser.setInput(data);

        ByteArrayOutputStream o = new ByteArrayOutputStream(data.length);
        try
        {
            byte[] buf = new byte[1024];
            while (!decompresser.finished())
            {
                int i = decompresser.inflate(buf);
                o.write(buf, 0, i);
            }
            output = o.toByteArray();
        }
        catch (Exception e)
        {
            output = data;
            e.printStackTrace();
        }
        finally
        {
            try
            {
                o.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        decompresser.end();
        return output;
    }

    /**
     * 方法名称：  decompress
     * 作者：         Administrator
     * 方法描述： 解压缩 
     * 输入参数：  @param is 需要解压的流
     * 输入参数：  @return
     * 返回类型：  byte[] 解压完的数据
     */
    public static byte[] decompress(InputStream is)
    {
        InflaterInputStream iis = new InflaterInputStream(is);
        ByteArrayOutputStream o = new ByteArrayOutputStream(1024);
        try
        {
            int i = 1024;
            byte[] buf = new byte[i];

            while ((i = iis.read(buf, 0, i)) > 0)
            {
                o.write(buf, 0, i);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return o.toByteArray();
    }

    /**
     * 方法名称：  decompressToString
     * 作者：         Administrator
     * 方法描述： 解压缩 
     * 输入参数：  @param is 需要解压的流
     * 输入参数：  @return
     * 返回类型：  String 解压完的数据
     */
    public static String decompressToString(InputStream is)
    {
        InflaterInputStream iis = new InflaterInputStream(is);
        ByteArrayOutputStream o = new ByteArrayOutputStream(1024);
        try
        {
            int i = 1024;
            byte[] buf = new byte[i];

            while ((i = iis.read(buf, 0, i)) > 0)
            {
                o.write(buf, 0, i);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return o.toString();
    }

}
