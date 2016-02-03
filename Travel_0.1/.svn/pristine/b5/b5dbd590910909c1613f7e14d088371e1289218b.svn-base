/**
 * 项目名：     Travel
 * 文件名：     CollapsibleTextView.java
 * 文件描述： 
 * 作者：         Administrator
 * 创建时间：  2013年7月22日
 * 版权声明 ： Copyright (C) 2008-2010 RichPeak
 *
 */
package org.jump.utils.view;

import com.mendong.travel.R;

import android.content.Context;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TextView.BufferType;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;

/**
 * 类名称：     CollapsibleTextView
 * 作者：         Administrator
 * 创建时间：  2013年7月22日 下午4:51:44
 * 类描述：    
 * 版权声明 ： Copyright (C) 2008-2010 RichPeak
 * 修改时间：  2013年7月22日 下午4:51:44
 *
 */
public final class CollapsibleTextView extends LinearLayout implements
        OnClickListener
{
    /** default text show max lines */
    private static final int DEFAULT_MAX_LINE_COUNT = 100;

    private static final int COLLAPSIBLE_STATE_NONE = 0;
    private static final int COLLAPSIBLE_STATE_SHRINKUP = 1;
    private static final int COLLAPSIBLE_STATE_SPREAD = 2;

    private TextView desc;
    private ImageView mIBTExpand;

    private String shrinkup;
    private String spread;
    private int mState;
    private boolean flag;

    public CollapsibleTextView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        shrinkup = context.getString(R.string.desc_shrinkup);
        spread = context.getString(R.string.desc_spread);
        View view = inflate(context, R.layout.collapsible_textview, this);
        view.setPadding(0, -1, 0, 0);
        desc = (TextView) view.findViewById(R.id.desc_tv);
        mIBTExpand = (ImageView) view.findViewById(R.id.ibtexpand);
        mIBTExpand.setOnClickListener(this);
    }

    public CollapsibleTextView(Context context)
    {
        this(context, null);
    }

    public final void setDesc(CharSequence charSequence, BufferType bufferType)
    {
        if(null != desc)
        {
            desc.setText(charSequence, bufferType);
        }
        
        mState = COLLAPSIBLE_STATE_SPREAD;
        requestLayout();
    }

    @Override
    public void onClick(View v)
    {
        flag = false;
        requestLayout();
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b)
    {
        super.onLayout(changed, l, t, r, b);
        if (!flag)
        {
            flag = true;
            if(null != desc)
            {
            if (desc.getLineCount() <= DEFAULT_MAX_LINE_COUNT)
            {
                mState = COLLAPSIBLE_STATE_NONE;
                mIBTExpand.setVisibility(View.GONE);
                desc.setMaxLines(DEFAULT_MAX_LINE_COUNT + 1);
            }
            else
            {
                post(new InnerRunnable());
            }
            }
        }
    }

    class InnerRunnable implements Runnable
    {
        @Override
        public void run()
        {
            if (mState == COLLAPSIBLE_STATE_SPREAD)
            {
                desc.setMaxLines(DEFAULT_MAX_LINE_COUNT);
                mIBTExpand.setVisibility(View.VISIBLE);
                mIBTExpand.setImageResource(R.drawable.expand);
                mState = COLLAPSIBLE_STATE_SHRINKUP;
            }
            else if (mState == COLLAPSIBLE_STATE_SHRINKUP)
            {
                desc.setMaxLines(Integer.MAX_VALUE);
                mIBTExpand.setVisibility(View.VISIBLE);
                mIBTExpand.setImageResource(R.drawable.expand);
                mState = COLLAPSIBLE_STATE_SPREAD;
            }
        }
    }

}
