/**
 * 项目名：     SystemService
 * 文件名：     RootManager.java
 * 文件描述： 
 * 作者：         Administrator
 * 创建时间：  2012-12-3
 * 版权声明 ： Copyright (C) 2008-2010 RichPeak
 *
 */
package com.mendong.travel.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

/**
 * 类名称：     RootManager
 * 作者：         Administrator
 * 创建时间：  2012-12-3 下午4:18:54
 * 类描述：    
 * 版权声明 ： Copyright (C) 2008-2010 RichPeak
 * 修改时间：  2012-12-3 下午4:18:54
 *
 */
public final class RootManager
{

    public synchronized static boolean isRoot(Context context)
    {
        //        String apkRoot = "chmod 777 " + context.getPackageCodePath();
        //        boolean root = RootManager.RootCommand(apkRoot);
        //
        //        return root;
        return checkSystemBinSu() | checkSystemXbinSu() | checkRoSecure();
    }

    private static boolean fileIsExists(String path)
    {
        File f = null;
        try
        {
            f = new File(path);
            if (f.exists())
            {
                return true;
            }
        }
        catch (Exception e)
        {

        }

        return false;
    }

    /**
     * 方法名称：  checkSystemBinSu
     * 作者：         Administrator
     * 方法描述： 检测 /system/bin/su 文件是否存在
     * 输入参数：  @return
     * 返回类型：  boolean
     */
    private static boolean checkSystemBinSu()
    {
        return fileIsExists("/system/bin/su");
    }

    /**
     * 方法名称：  checkSystemXbinSu
     * 作者：         Administrator
     * 方法描述： 检测/system/xbin/su 文件是否存在 
     * 输入参数：  @return
     * 返回类型：  boolean
     */
    private static boolean checkSystemXbinSu()
    {
        return fileIsExists("/system/xbin/su");
    }

    /**
     * 方法名称：  checkRoSecure
     * 作者：         Administrator
     * 方法描述：  检查default.prop里的ro.secure是否等于0
     * 输入参数：  @return
     * 返回类型：  boolean
     */
    private static boolean checkRoSecure()
    {
        boolean result = false;
        FileReader fr = null;
        BufferedReader br = null;
        try
        {
            fr = new FileReader("/default.prop");
            br = new BufferedReader(fr);

            String text = br.readLine();
            while (!TextUtils.isEmpty(text))
            {
                String[] array = text.trim().split("=", 2);
                if ("ro.secure".equals(array[0]) && "0".equals(array[1]))
                {
                    result = true;
                    break;
                }
                text = br.readLine();
            }
        }
        catch (Exception e)
        {
        }
        finally
        {
            if (null != br)
            {
                try
                {
                    br.close();
                }
                catch (IOException e)
                {
                }
                br = null;
            }
            if (null != fr)
            {
                try
                {
                    fr.close();
                }
                catch (IOException e)
                {
                }
                fr = null;
            }
        }
        return result;
    }

    private static boolean RootCommand(String command)
    {
        Process process = null;
        DataOutputStream os = null;
        try
        {
            process = Runtime.getRuntime().exec("su");
            os = new DataOutputStream(process.getOutputStream());
            os.writeBytes(command + "\n");
            os.writeBytes("exit\n");
            os.flush();
            process.waitFor();
        }
        catch (Exception e)
        {
            Log.d("*** DEBUG ***", "ROOT REE" + e.getMessage());
            return false;
        }
        finally
        {
            try
            {
                if (os != null)
                {
                    os.close();
                }
                process.destroy();
            }
            catch (Exception e)
            {
            }
        }
        Log.d("*** DEBUG ***", "Root SUC ");
        return true;
    }
}
