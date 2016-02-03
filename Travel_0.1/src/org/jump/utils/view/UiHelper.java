/**
 * 项目名：     Travel
 * 文件名：     UiHelper.java
 * 文件描述： 
 * 作者：         Administrator
 * 创建时间：  2013年7月26日
 * 版权声明 ： Copyright (C) 2008-2010 RichPeak
 *
 */
package org.jump.utils.view;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * 类名称：     UiHelper
 * 作者：         Administrator
 * 创建时间：  2013年7月26日 下午1:01:49
 * 类描述：    
 * 版权声明 ： Copyright (C) 2008-2010 RichPeak
 * 修改时间：  2013年7月26日 下午1:01:49
 *
 */
public final class UiHelper
{

    /** 全局的加载框对象，已经完成初始化. */
    private static ProgressDialog mProgressDialog;

    private static final List<Context> mDialogs = new ArrayList<Context>();

    /** 
     * 提问框的 Listener 
     *  
     * @author Lei 
     */
    // 因为本类不是activity所以通过继承接口的方法获取到点击的事件   
    public interface OnClickYesListener
    {
        abstract void onClickYes();
    }

    /** 
     * 提问框的 Listener 
     *  
     */
    public interface OnClickNoListener
    {
        abstract void onClickNo();
    }

    public static void removeProgressDialog()
    {
        if (null != mProgressDialog && mProgressDialog.isShowing())
        {
            mProgressDialog.dismiss();
        }
    }

    public static void showProgressDialog(Context context, String title,
            String text, final OnClickYesListener listenerYes,
            CharSequence yesText, final OnClickNoListener listenerNo,
            CharSequence noText, boolean cancelable)
    {
        if (null == mProgressDialog)
        {
            mProgressDialog = new ProgressDialog(context);
        }
        mProgressDialog.setTitle(title);
        mProgressDialog.setMessage(text);

        if (null != listenerYes)
        {
            // 设置确定按钮，固定用法声明一个按钮用这个setPositiveButton   
            mProgressDialog.setButton(DialogInterface.BUTTON_POSITIVE, yesText,
                    new OnClickListener()
                    {

                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {
                            // 如果确定被电击   
                            if (listenerYes != null)
                            {
                                listenerYes.onClickYes();
                            }

                        }
                    });
        }

        if (null != listenerNo)
        {
            // 设置取消按钮，固定用法声明第二个按钮要用setNegativeButton   
            mProgressDialog.setButton(DialogInterface.BUTTON_NEGATIVE, noText,
                    new OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {
                            // 如果确定被电击   
                            if (listenerNo != null)
                            {
                                listenerNo.onClickNo();
                            }

                        }
                    });
        }
        mProgressDialog.setCancelable(cancelable);
        mProgressDialog.show();
    }

    public static void showQuestionDialog(Context context, String title,
            String text, final OnClickYesListener listenerYes,
            final OnClickNoListener listenerNo)
    {
        Builder builder = new AlertDialog.Builder(context);

        if (!isBlank(text))
        {
            // 此方法为dialog写布局   
            final TextView textView = new TextView(context);
            textView.setText(text);
            LinearLayout layout = new LinearLayout(context);
            layout.setPadding(10, 0, 10, 0);
            layout.addView(textView, new LayoutParams(LayoutParams.FILL_PARENT,
                    LayoutParams.WRAP_CONTENT));
            builder.setView(layout);
        }
        // 设置title   
        builder.setTitle(title);
        // 设置确定按钮，固定用法声明一个按钮用这个setPositiveButton   
        builder.setPositiveButton("是", new OnClickListener()
        {
            public void onClick(DialogInterface dialog, int which)
            {
                // 如果确定被电击   
                if (listenerYes != null)
                {
                    listenerYes.onClickYes();
                }
            }
        });
        // 设置取消按钮，固定用法声明第二个按钮要用setNegativeButton   
        builder.setNegativeButton("否", new OnClickListener()
        {
            public void onClick(DialogInterface dialog, int which)
            {
                // 如果取消被点击   
                if (listenerNo != null)
                {
                    listenerNo.onClickNo();
                }
            }
        });

        // 控制这个dialog可不可以按返回键，true为可以，false为不可以   
        builder.setCancelable(false);
        // 显示dialog   
        builder.create().show();

    }

    /** 
     * 处理字符的方法 
     *  
     * @param str 
     * @return 
     */
    public static boolean isBlank(String str)
    {
        int strLen;
        if (str == null || (strLen = str.length()) == 0)
        {
            return true;
        }
        for (int i = 0; i < strLen; i++)
        {
            if ((Character.isWhitespace(str.charAt(i)) == false))
            {
                return false;
            }
        }
        return true;
    }

    public static int dip2px(Context context, float dipValue)
    {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    public static int px2dip(Context context, float pxValue)
    {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
