/**
 * 项目名：     SystemService
 * 文件名：     FileUtil.java
 * 文件描述： 
 * 作者：         Administrator
 * 创建时间：  2012-8-6
 * 版权声明 ： Copyright (C) 2008-2010 RichPeak
 *
 */
package com.mendong.travel.utils.log;

import java.io.File;

import android.content.Context;
import android.os.Environment;

/**
 * 类名称：     FileUtil
 * 作者：         Administrator
 * 创建时间：  2012-8-6 下午2:53:27
 * 类描述：    
 * 版权声明 ： Copyright (C) 2008-2010 RichPeak
 * 修改时间：  2012-8-6 下午2:53:27
 *
 */

public class FileUtil
{

    /**
     * 根據文件相对路径查找文件
     * 
     * @param fileName
     * @return
     */
    public static File getFile(Context context, String fileName)
    {
        File file = getFileExternal(fileName);
        if (null == file)
        {
            file = new File(context.getFilesDir(), fileName);
        }
        return file;
    }

    public static File getFileExternal(String fileName)
    {
        File file = null;
        try
        {
            if (externalStorageAvilable())
            {
                file = new File(Environment.getExternalStorageDirectory(),
                        fileName);
            }
            else
            {
                file = new File(Environment.getDataDirectory(), fileName);
            }
        }
        catch (Exception e)
        {
        }
        return file;
    }

    public static boolean externalStorageAvilable()
    {
        return Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED);
    }

    public static String getBasePath()
    {
        return Environment.getExternalStorageDirectory().getAbsolutePath();
    }
}
