/**
 * 项目名：     Travel
 * 文件名：     reportMenu.java
 * 文件描述： 
 * 作者：         Administrator
 * 创建时间：  2013年7月18日
 * 版权声明 ： Copyright (C) 2008-2010 RichPeak
 *
 */
package com.mendong.travel.view.popups;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.mendong.travel.R;
import com.mendong.travel.view.adapter.map.MapGridViewAdapter;

/**
 * 类名称：     reportMenu
 * 作者：         Administrator
 * 创建时间：  2013年7月18日 上午10:36:45
 * 类描述：    
 * 版权声明 ： Copyright (C) 2008-2010 RichPeak
 * 修改时间：  2013年7月18日 上午10:36:45
 *
 */
public final class Left1Menu extends Dialog implements OnItemClickListener,
        android.view.animation.Animation.AnimationListener
{

    private Context context;

    private GridView menuGrid;

    private MenuItem Menu_Item;

    private ImageButton btnClose;

    private View view;

    private boolean isCloseAniEnd = true;

    private MapGridViewAdapter mAdapter = null;
    
    private TextView txtView_Title;

    public Left1Menu(Context context, int myMenuBit[], MenuItem Menu_Item)
    {
        super(context, R.style.dialog_fullscreen);
        setContentView(R.layout.report_menu);
        //        this.setTitle("11111111");
        this.context = context;
        this.Menu_Item = Menu_Item;
        setProperty();
        
        //显示菜单字样
        txtView_Title = (TextView)findViewById(R.id.textView1);
        txtView_Title.setText("菜单");

//        ImageView v = (ImageView)findViewById(R.id.ivpopup2);
//        v.setVisibility(View.INVISIBLE);
       
        View v = findViewById(R.id.report_menu);
        v.setOnClickListener(new View.OnClickListener()
        {
            
            @Override
            public void onClick(View v)
            {
                closeAnimateReport();
                
            }
        });
        
        FrameLayout frameLayout = (FrameLayout)findViewById(R.id.title);
        frameLayout.setBackgroundResource(R.drawable.left1_up);
        
        btnClose = (ImageButton) findViewById(R.id.report_close);

        btnClose.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                closeAnimateReport();
            }
        });

        menuGrid = (GridView) this.findViewById(R.id.GridView_toolbar);
        mAdapter = getMenuAdapter(myMenuBit);
        menuGrid.setAdapter(mAdapter);
        menuGrid.setOnItemClickListener(this);
        menuGrid.setBackgroundResource(R.drawable.left1_down);
        menuGrid.setSelector(new ColorDrawable(Color.TRANSPARENT));
//        menuGrid.setOnClickListener(new View.OnClickListener()
//        {
//            
//            @Override
//            public void onClick(View v)
//            {
//                closeAnimateReport();
//                
//            }
//        });

        // 设置背景透明度
        view = findViewById(R.id.report_menu);
        //        view.getBackground().setAlpha(0);// 120为透明的比率
        //        view.setBackgroundResource(R.drawable.popup_cont);// 设置背景图片

        // Dialog按对话框外面取消操作
        this.setCanceledOnTouchOutside(true);

    }

    @Override
    public void dismiss()
    {
        if (isCloseAniEnd)
        {
            super.dismiss();
        }
        else
        {
            closeAnimateReport();
        }

    }

    public void setPosition(int x, int y)
    {
        Window w = getWindow();
        WindowManager.LayoutParams lp = w.getAttributes();
        lp.x = x;
        lp.y = y;
        w.setAttributes(lp);
    }

    /**
     * 设置窗体属性
     */

    private void setProperty()
    {
        // // TODO Auto-generated method stub
        // window = getWindow();
        // WindowManager.LayoutParams wl = window.getAttributes();
        // // wl.alpha=0.6f;
        // wl.screenBrightness = 1;// 设置当前窗体亮度
        // wl.gravity = Gravity.CENTER_VERTICAL;
        // wl.setTitle("手机操作个性设定");
        // window.setAttributes(wl);

        Window w = getWindow();
        WindowManager.LayoutParams lp = w.getAttributes();
        lp.alpha = 1.0f;// 设置当前对话框的 透明度
        //        lp.windowAnimations = R.style.dialog_anim;
        // lp.dimAmount = 0.0f;// 设置对话框 悬浮的活动类透明度
        //        int h = w.getWindowManager().getDefaultDisplay().getHeight();
        //        lp.x = -60;
        //        lp.y = 240; // 对话框的显示位置
        // lp.gravity = Gravity.CENTER_VERTICAL;
        w.setAttributes(lp);

    }

    /**
     * 构造菜单Adapter
     * 
     * @param menuNameArray
     *            名称
     * @param imageResourceArray
     *            图片
     * @return SimpleAdapter
     */
    public MapGridViewAdapter getMenuAdapter(int[] imageResourceArray)
    {
        ArrayList<HashMap<String, Object>> data = new ArrayList<HashMap<String, Object>>();
        for (int i = 0; i < imageResourceArray.length; i++)
        {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("itemImage", imageResourceArray[i]);
            data.add(map);
        }

        MapGridViewAdapter simperAdapter = new MapGridViewAdapter(context,
                R.layout.report_menuitem, R.drawable.menubtn_left1_1, data);
        /*SimpleAdapter simpleAdapter = new SimpleAdapter(context, data,
                R.layout.report_menuitem, new String[] { "itemImage" },
                new int[] { R.id.item_image });*/
              /* MapGridViewAdapter simperAdapter = new MapGridViewAdapter(context, data,
                       R.layout.report_menuitem, new String[] { "itemImage" },
                       new int[] { R.id.item_image });*/
        return simperAdapter;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
            long id)
    {
        super.dismiss();
       // mAdapter.setSeclection(position);
        mAdapter.notifyDataSetChanged();
        Menu_Item.ItemClickListener(view, position, id);
    }

    public void openAnimateReport()
    {
        isCloseAniEnd = false;
        com.mendong.travel.view.anim.AnimationUtils.openAnimateMenu(view, this);
    }

    public void closeAnimateReport()
    {
        com.mendong.travel.view.anim.AnimationUtils
                .closeAnimateMenu(view, this);
        isCloseAniEnd = true;
    }

    public void onAnimationEnd(Animation animation)
    {
        if (isCloseAniEnd)
        {
            dismiss();
        }
    }

    @Override
    public void onAnimationRepeat(Animation animation)
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void onAnimationStart(Animation animation)
    {
        // TODO Auto-generated method stub

    }

    public void release()
    {
        context = null;
        Menu_Item = null;
        view = null;
    }
}
