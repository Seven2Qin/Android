/**
 * 项目名：     Travel
 * 文件名：     PrograssBar.java
 * 文件描述： 
 * 作者：         Administrator
 * 创建时间：  2013年7月20日
 * 版权声明 ： Copyright (C) 2008-2010 RichPeak
 *
 */
package org.jump.utils.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface.OnClickListener;

/**
 * 类名称：     PrograssBar
 * 作者：         Administrator
 * 创建时间：  2013年7月20日 下午9:11:59
 * 类描述：    
 * 版权声明 ： Copyright (C) 2008-2010 RichPeak
 * 修改时间：  2013年7月20日 下午9:11:59
 *
 */
public class PrograssBar
{

    private Context mContext;

    private ProgressDialog mypDialog;

    public PrograssBar(Context context)
    {
        this.mContext = context;
        mypDialog = new ProgressDialog(context);
    }

    /**
     * 方法名称：  setProgressStyle
     * 作者：         Administrator
     * 方法描述：  设置进度条风格，风格为圆形，旋转的
     * 输入参数：  
     * 返回类型：  void
     */
    public void setProgressStyle(int style)
    {
        mypDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
    }

    /**
     * 方法名称：  setTitle
     * 作者：         Administrator
     * 方法描述：  设置ProgressDialog 标题
     * 输入参数：  @param text
     * 返回类型：  void
     */
    public void setTitle(String text)
    {
        mypDialog.setTitle(text);
    }

    /**
     * 方法名称：  setMessage
     * 作者：         Administrator
     * 方法描述：  设置ProgressDialog 提示信息
     * 输入参数：  
     * 返回类型：  void
     */
    public void setMessage(String text)
    {
        mypDialog.setMessage(text);
    }

    /**
     * 方法名称：  setIcon
     * 作者：         Administrator
     * 方法描述：  设置ProgressDialog 标题图标
     * 输入参数：  @param resId
     * 返回类型：  void
     */
    public void setIcon(int resId)
    {
        mypDialog.setIcon(resId);
    }

    /**
     * 方法名称：  setButton
     * 作者：         Administrator
     * 方法描述：  设置ProgressDialog 的一个Button
     * 输入参数：  @param text
     * 输入参数：  @param listener
     * 返回类型：  void
     */
    public void setButton(String text, OnClickListener listener)
    {
        mypDialog.setButton(text, listener);
    }

    /**
     * 方法名称：  setIndeterminate
     * 作者：         Administrator
     * 方法描述：  设置ProgressDialog 的进度条是否不明确
     * 输入参数：  @param flag
     * 返回类型：  void
     */
    public void setIndeterminate(boolean flag)
    {
        mypDialog.setIndeterminate(false);
    }

    /**
     * 方法名称：  setCancelable
     * 作者：         Administrator
     * 方法描述：  设置ProgressDialog 是否可以按退回按键取消
     * 输入参数：  @param flag
     * 返回类型：  void
     */
    public void setCancelable(boolean flag)
    {
        mypDialog.setCancelable(flag);
    }

    /**
     * 方法名称：  show
     * 作者：         Administrator
     * 方法描述：  让ProgressDialog显示
     * 输入参数：  
     * 返回类型：  void
     */
    public void show()
    {
        mypDialog.show();
    }

}
