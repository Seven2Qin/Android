package com.mendong.travel.utils.log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;

import android.content.Context;
import android.util.Log;


/**
 * 类名称：       ClsLog
 * 作者：            Administrator
 * 创建时间：  2012-5-30 下午5:05:15
 * 类描述：       日志类
 * 版权声明 ： Copyright (C) 2008-2010 RichPeak
 * 修改时间：  2012-5-30 下午5:05:15
 *
 */
public final class ClsLog
{
    /**
     * 开启日志标志
     */
    private static final boolean isDebug = true;

    /**
     * 开启文件日志
     */
    private static final boolean isFile = true;

    /**
     * 构造方法
     * 输入参数：  
     */
    private ClsLog()
    {
    }

    /**
     * 方法名称：  I
     * 作者：            Administrator
     * 方法描述：  打印info日志
     * 输入参数：  @param tag
     * 输入参数：  @param value
     * 返回类型：  void
     */
    public static void I(String tag, String value)
    {
        if (isDebug)
        {
            Log.i(tag, value == null ? "" : value);
        }
    }

    /**
     * 方法名称：  E
     * 作者：            Administrator
     * 方法描述：  打印error日志
     * 输入参数：  @param tag
     * 输入参数：  @param value
     * 返回类型：  void
     */
    public static void E(String tag, String value)
    {
        if (isDebug)
        {
            Log.e(tag, value == null ? "" : value);
        }
    }

    /**
     * 方法名称：  D
     * 作者：            Administrator
     * 方法描述：  打印debug日志
     * 输入参数：  @param tag
     * 输入参数：  @param value
     * 返回类型：  void
     */
    public static void D(String tag, String value)
    {
        if (isDebug)
        {
            Log.d(tag, value == null ? "" : value);
        }
    }

    private static Context mContext;

    public static void toast(Context context, final String msg)
    {
        if (isFile)
        {
            mContext = context;
            //            Message m = new Message();
            //            m.what = 1;
            //            m.obj = msg;
            //            mHandler.sendMessage(m);
            logToFile(msg);
        }
    }

    public static void toast(Context context, final byte[] msg)
    {
        if (isFile)
        {
            toast(context, new String(msg));
        }
    }

    public static void toast(Context context, final String msg1,
            final byte[] msg2)
    {
        if (isFile)
        {
            toast(context, msg1 + new String(msg2));
        }
    }

    //    private static Handler mHandler = new Handler()
    //    {
    //        @Override
    //        public void handleMessage(Message msg)
    //        {
    //            if(null == msg)
    //            {
    //                return;
    //            }
    //            switch (msg.what)
    //            {
    //                case 1:
    //                {
    //                    if(null != msg)
    //                    {
    //                        String text = (String) msg.obj;
    //                        if(null != text)
    //                        {
    //                            Toast.makeText(mContext, text, Toast.LENGTH_LONG).show();
    //                        }
    //                    }
    //
    //                    break;
    //                }
    //                default:
    //                    break;
    //            }
    //        }
    //    };

    public static void logToFile(Object msg)
    {
        if (null == msg)
        {
            return;
        }
        if (isDebug && isFile)
        {
            ClsLog.E("logToFile", msg.toString());
            writeToSDCard(
                    "sspay.log",
                    "[" + new Timestamp(System.currentTimeMillis()).toString()
                            + "]" + "-----> "
                            + msg.toString() + "\n");
        }
    }

    private static void writeToSDCard(String fileName, String text)
    {
        File file = FileUtil.getFileExternal(fileName);
        if (null != file)
        {
            writeFile(file, text);
        }
    }

    private static void writeFile(File file, String logContent)
    {
        if (null == file)
            return;
        FileOutputStream fileOutputStream = null;
        FileInputStream fileInputStream = null;
        try
        {
            fileOutputStream = new FileOutputStream(file, true);
            fileOutputStream.write(logContent.getBytes());
        }
        catch (Exception e)
        {

        }
        finally
        {
            if (fileOutputStream != null)
            {
                try
                {
                    fileOutputStream.close();
                }
                catch (IOException e)
                {
                    //                    e.printStackTrace();
                }
                fileOutputStream = null;
            }
            if (fileInputStream != null)
            {
                try
                {
                    fileInputStream.close();
                }
                catch (IOException e)
                {
                    //                    e.printStackTrace();
                }
                fileInputStream = null;
            }
        }
    }
}
