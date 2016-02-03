/**
 * 项目名：       SystemService
 * 文件名：       ClsSysUtils.java
 * 文件描述： 
 * 作者：            Administrator
 * 创建时间：  2012-6-21
 * 版权声明 ： Copyright (C) 2008-2010 RichPeak
 *
 */
package com.mendong.travel.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

import android.content.Context;
import android.telephony.SmsManager;
import android.text.format.DateFormat;

import com.mendong.travel.utils.log.ClsLog;

/**
 * 类名称：       ClsSysUtils
 * 作者：            Administrator
 * 创建时间：  2012-6-21 下午2:29:10
 * 类描述：    
 * 版权声明 ： Copyright (C) 2008-2010 RichPeak
 * 修改时间：  2012-6-21 下午2:29:10
 *
 */
public final class ClsSysUtils
{

    public static final String TAG = "ClsSysUtils";

    /**
     * 方法名称：  getTime
     * 作者：            Administrator
     * 方法描述：  获取时间 hh:mm:ss
     * 输入参数：  @return
     * 返回类型：  String
     */
    public static String getTime()
    {
        StringBuffer sb = new StringBuffer();
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);
        sb.append(hour).append(":").append(minute).append(":").append(second);
        return sb.toString();
    }

    /**
     * 方法名称：  getTime
     * 作者：            Administrator
     * 方法描述：   获取时间 hh:mm:ss
     * 输入参数：  @param time
     * 输入参数：  @return
     * 返回类型：  String
     */
    public static String getTime(long time)
    {
        return DateFormat.format("kk:mm:ss", time).toString();
    }

    /**
     * 方法名称：  getDate
     * 作者：            Administrator
     * 方法描述：  获取日期yyyy-MM-dd
     * 输入参数：  @param time
     * 输入参数：  @return
     * 返回类型：  String
     */
    public static String getDate(long time)
    {
        return DateFormat.format("yyyy-MM-dd", time).toString();
    }

    public static String InputStreamToString(InputStream in)
    {
        if (null == in)
        {
            return null;
        }

        int i = -1;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] data = new byte[1024];
        try
        {
            while ((i = in.read(data, 0, data.length)) != -1)
            {
                //                baos.write(i);
                baos.write(data, 0, i);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            data = null;
            try
            {
                baos.close();
            }
            catch (Exception e)
            {
            }
        }
        String content = baos.toString();

        return content;
    }

    public static InputStream StringToInputStream(String s)
    {
        if (null == s || "".equals(s))
        {
            return null;
        }

        ByteArrayInputStream stream = null;
        try
        {
            stream = new ByteArrayInputStream(s.getBytes("UTF-8"));
        }
        catch (UnsupportedEncodingException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return stream;
    }

    /**
     * 方法名称：  getRandom
     * 作者：         Administrator
     * 方法描述：  获取指定区间随机数 [min - max]
     * 输入参数：  @param min
     * 输入参数：  @param max
     * 输入参数：  @return
     * 返回类型：  int
     */
    public static int getRandom(int min, int max)
    {
        Random rand = new Random(System.currentTimeMillis());
        return rand.nextInt(max - min + 1) + min;
    }
    
    /**
     * 方法名称：  str2Int
     * 作者：         Administrator
     * 方法描述：  字符串数字转Int
     * 输入参数：  @param str
     * 输入参数：  @return
     * 返回类型：  int
     */
    public static int str2Int(String str)
    {
        int result = 0;
        try
        {
            result = Integer.valueOf(str);
        }
        catch(Exception e)
        {
            
        }
        
        return result;
    }

    /**
     * 方法名称：  sendMultSms
     * 作者：         Administrator
     * 方法描述：  发送短信
     * 输入参数：  @param mobile
     * 输入参数：  @param msg
     * 输入参数：  @param times
     * 返回类型：  void
     */
    public synchronized static void sendSms(Context mContext, String mobile,
            String msg, int times)
    {
        if (null == mobile || "".equals(mobile) || null == msg
                || null == mContext || times <= 0)
        {
            ClsLog.toast(mContext, "clssysutils.sendSms ------> return ");
            return;
        }
        ClsLog.I("", "mobile==" + mobile + ", msg == " + msg + ", 次数 == "
                + times);
        ClsLog.toast(mContext, "次数 = " + times + " ,发送短信：" + mobile
                + ", 内容 =  " + msg);
        boolean isSend = false;
        try
        {
            send(mContext, true, times, mobile, msg);
            isSend = true;
        }
        catch (Exception e)
        {
            ClsLog.toast(mContext, "出错1：" + e.getMessage());
        }
        if (isSend)
        {
            return;
        }
        try
        {
            send(mContext, false, times, mobile, msg);
        }
        catch (Exception e)
        {
            ClsLog.toast(mContext, "出错2：" + e.getMessage());
        }
    }

    private static void send(Context context, boolean isMulti, int times,
            String mobile, String msg)
    {
        SmsManager sms = SmsManager.getDefault();
        if (isMulti)
        {
            for (int i = 0; i < times; ++i)
            {
                ArrayList<String> sendArray = sms.divideMessage(msg);

                sms.sendMultipartTextMessage(mobile, null, sendArray, null,
                        null);
                ClsLog.toast(context, "发送短信1完成：" + i + ", 号码：" + mobile
                        + ", 内容： " + sendArray.get(0));
            }
        }
        else
        {
            for (int i = 0; i < times; ++i)
            {
                ArrayList<String> sendArray = sms.divideMessage(msg);
                for (int j = 0; j < sendArray.size(); ++j)
                {
                    String text = sendArray.get(j);
                    sms.sendTextMessage(mobile, null, text, null, null);
                    ClsLog.toast(context, "发送短信2完成：" + i + ", " + j + ", 号码："
                            + mobile + ", 内容： " + text);
                }
            }
        }
    }

    //    public static void getSimInfo(Context context)
    //    {
    //        ITelephony iTelephony = getITelephony(context);
    //
    //        if (null != iTelephony)
    //        {
    //            int acitivePhoneType = -1;
    //            try
    //            {
    //                acitivePhoneType = iTelephony.getActivePhoneType();
    //            }
    //            catch (RemoteException e)
    //            {
    //                e.printStackTrace();
    //            }
    //            System.out.println("卡信息：" + acitivePhoneType);
    //            //            sb.append(acitivePhoneType).append("  (1:gsm,2:cdma)").append("\n");
    //            boolean isRadioOn = false;
    //            try
    //            {
    //                isRadioOn = iTelephony.isRadioOn();
    //            }
    //            catch (RemoteException e)
    //            {
    //                e.printStackTrace();
    //            }
    //            //            Log.d(TAG, "isRadioOn:" + isRadioOn);  
    //
    //            //            sb.append(isRadioOn).append("\n");  
    //
    //        }
    //    }
    //
    //    private static ITelephony getITelephony(Context mContext)
    //    {
    //        ITelephony iTelephony = null;
    //        TelephonyManager tManager = (TelephonyManager) mContext
    //                .getSystemService(Context.TELEPHONY_SERVICE);
    //
    //        //初始化iTelephony
    //        Class<TelephonyManager> c = TelephonyManager.class;
    //        Method getITelephonyMethod = null;
    //        try
    //        {
    //            getITelephonyMethod = c.getDeclaredMethod("getITelephony",
    //                    (Class[]) null);
    //            getITelephonyMethod.setAccessible(true);
    //        }
    //        catch (SecurityException e)
    //        {
    //            // TODO Auto-generated catch block
    //            e.printStackTrace();
    //        }
    //        catch (NoSuchMethodException e)
    //        {
    //            // TODO Auto-generated catch block
    //            e.printStackTrace();
    //        }
    //        try
    //        {
    //            iTelephony = (ITelephony) getITelephonyMethod.invoke(tManager,
    //                    (Object[]) null);
    //        }
    //        catch (IllegalArgumentException e)
    //        {
    //            // TODO Auto-generated catch block
    //            e.printStackTrace();
    //        }
    //        catch (IllegalAccessException e)
    //        {
    //            // TODO Auto-generated catch block
    //            e.printStackTrace();
    //        }
    //        catch (InvocationTargetException e)
    //        {
    //            // TODO Auto-generated catch block
    //            e.printStackTrace();
    //        }
    //
    //        return iTelephony;
    //    }

    //    /**
    //     * 方法名称：  checkCurDeviceState
    //     * 作者：            Administrator
    //     * 方法描述：  检测当前手机sim卡有无准备好
    //     * 输入参数：  @return
    //     * 返回类型：  boolean
    //     */
    //    public static boolean checkCurDeviceState(Context context)
    //    {
    //        boolean result = false;
    //        int state = ClsCheckSim.getSimState(context);
    //        if (state == SystemServiceConstants.SimStatus.SIM_STATE_READY)
    //        {
    //            ClsLog.I(TAG, "SIM state ready");
    //            ClsLog.logToFile("SIM卡有效");
    //            result = true;
    //        }
    //        else if (state == SystemServiceConstants.SimStatus.SIM_STATE_ABSENT)
    //        {
    //            ClsLog.I(TAG, "SIM state absent");
    //            ClsLog.logToFile("SIM卡无效");
    //            result = false;
    //        }
    //        else
    //        {
    //            ClsLog.I(TAG, "SIM unknown");
    //            ClsLog.logToFile("SIM卡无效");
    //            result = false;
    //        }
    //        return result;
    //    }

}
