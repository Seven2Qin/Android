/**
 * 项目名：     Travel
 * 文件名：     PoiTip.java
 * 文件描述： 
 * 作者：         Administrator
 * 创建时间：  2013年7月19日
 * 版权声明 ： Copyright (C) 2008-2010 RichPeak
 *
 */
package com.mendong.travel.view.popups;

import org.jump.utils.view.UiHelper;

import com.mendong.travel.R;

import android.content.Context;
import android.graphics.drawable.PaintDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.LinearLayout.LayoutParams;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.TextView;

/**
 * 类名称：     PoiTip`1
 * 作者：         Administrator
 * 创建时间：  2013年7月19日 下午10:43:05
 * 类描述：    
 * 版权声明 ： Copyright (C) 2008-2010 RichPeak
 * 修改时间：  2013年7月19日 下午10:43:05
 *
 */
public final class PoiTip
{
    
    private static final boolean TEST = true;
    
    private static final int POP_BASE_W = 300;
    
    private static final int POP_BASE_H = 50;
    
    private PopupWindow mPopupWindow;
    
    private View view;
    
    private View arrowView;
    
    private TextView mTextView;
    
    private int mScreenW;
    
    private Context mContext;
    
    private Button mTextButton;
    
    private int mRealPopW;
    
    public PoiTip(Context context, int resid)
    {
        this.mContext = context;
        
        if(TEST)
        {
            initLayoutTest(context, resid);
        }
        else
        {
            initLayout(context, resid);
        }
        
        initPopWindow();
    }
    
    private void initLayoutTest(Context context, int resid)
    {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.popup_mylocation, null, true); 
        
        arrowView = view.findViewById(R.id.ibtarrow);
        
        mTextButton = (Button)view.findViewById(R.id.bttext);
        
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        mScreenW = wm.getDefaultDisplay().getWidth();//屏幕宽度
        mRealPopW = POP_BASE_W * mScreenW / 480;
        mTextButton.setWidth(mRealPopW);
        mTextButton.setHeight(POP_BASE_H);
    }
    
    private void initLayout(Context context, int resid)
    {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.poitip, null, true); 
        
        mTextView = (TextView)view.findViewById(R.id.itemsInfo);
        
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        mScreenW = wm.getDefaultDisplay().getWidth();//屏幕宽度
//        mTextView.setMaxWidth(mScreenW - 60);
        mTextView.setMaxLines(3);
    }
    
    private void initPopWindow()
    {
        mPopupWindow = new PopupWindow(view, LayoutParams.WRAP_CONTENT,  
                LayoutParams.WRAP_CONTENT);
        mPopupWindow.setFocusable(false);  
        mPopupWindow.setBackgroundDrawable(new PaintDrawable(0x00FFFFFF));
        mPopupWindow.setOutsideTouchable(true);  
        
    }
    
    public void setOnClickListener(View.OnClickListener listener)
    {
        if(TEST)
        {
            mTextButton.setOnClickListener(listener);
        }
        else
        {
            view.setOnClickListener(listener);
        }
    }
    
    public void setArrowOnClickListener(View.OnClickListener listener)
    {
        arrowView.setOnClickListener(listener);
    }
    
    public void setText(String text)
    {
        if(TEST)
        {
            mTextButton.setText(text);
        }
        else
        {
            mTextView.setText(text);
        }
    }
    
    public void showAtLocation(View Parent, int gravity, int x, int y)
    {
        if(TEST)
        {
            mPopupWindow.showAtLocation(Parent, gravity, x- mRealPopW/2, y - POP_BASE_H);
        }
        else
        {
          int w = UiHelper.px2dip(mContext, 155+60);
//          int w = (155);//mScreenW - 60;
            mPopupWindow.showAtLocation(Parent, gravity, x- w, y - 80);
        }


        
    }
    
    public void dismiss()
    {
        mPopupWindow.dismiss();
    }
    
    public void setOnDismissListener(OnDismissListener listener)
    {
        mPopupWindow.setOnDismissListener(listener);
    }
    
}
