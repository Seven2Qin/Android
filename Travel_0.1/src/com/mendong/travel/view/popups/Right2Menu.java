/**
 * 项目名：     Travel
 * 文件名：     Left3Menu.java
 * 文件描述： 
 * 作者：         Administrator
 * 创建时间：  2013年7月31日
 * 版权声明 ： Copyright (C) 2008-2010 RichPeak
 *
 */
package com.mendong.travel.view.popups;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;

import com.mendong.travel.R;
import com.mendong.travel.view.adapter.map.MapGridViewAdapter;

/**
 * 类名称：     Left3Menu
 * 作者：         Administrator
 * 创建时间：  2013年7月31日 上午11:37:01
 * 类描述：    
 * 版权声明 ： Copyright (C) 2008-2010 RichPeak
 * 修改时间：  2013年7月31日 上午11:37:01
 *
 */
public final class Right2Menu extends Dialog implements OnItemClickListener,
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
    
	public Right2Menu(Context context, int myMenuBit[], MenuItem Menu_Item)
    {
        super(context, R.style.dialog_fullscreen);
        setContentView(R.layout.report_menu);
        //        this.setTitle("11111111");
        this.context = context;
        this.Menu_Item = Menu_Item;
        setProperty();
        
      //显示菜单字样
        txtView_Title = (TextView)findViewById(R.id.textView1);
        txtView_Title.setText("分享");
        
//        ImageView v = (ImageView)findViewById(R.id.ivpopup2);
//        v.setBackgroundResource(R.drawable.popup_title_up3);
//        v.setVisibility(View.VISIBLE);
        
        View v = findViewById(R.id.report_menu);
        v.setOnClickListener(new View.OnClickListener()
        {
            
            public void onClick(View v)
            {
                closeAnimateReport();
                
            }
        });
        
        Bitmap bitmap111 = BitmapFactory.decodeResource(context.getResources(),R.drawable.menuright_2);
        
        FrameLayout fl = (FrameLayout)findViewById(R.id.title);
        fl.setBackgroundResource(R.drawable.right2_up);

        btnClose = (ImageButton) findViewById(R.id.report_close);

        btnClose.setOnClickListener(new View.OnClickListener()
        {

            public void onClick(View v)
            {
                closeAnimateReport();
            }
        });

        menuGrid = (GridView) this.findViewById(R.id.GridView_toolbar);
        mAdapter = getMenuAdapter(myMenuBit);
        menuGrid.setAdapter(mAdapter);
        menuGrid.setOnItemClickListener(this);
        menuGrid.setBackgroundResource(R.drawable.right2_down);
        menuGrid.setSelector(new ColorDrawable(Color.TRANSPARENT));
        
        
        
    /*    menuGrid.setBackground(new BitmapDrawable(
        		  Bitmap.createBitmap(bitmap111, 0, 156, bitmap111.getWidth(),bitmap111.getHeight()-156)
        		  ));*/
        Log.i("sysout","bitmap->width"+bitmap111.getWidth()+","+bitmap111.getHeight());

        // 设置背景透明度
        view = findViewById(R.id.report_menu);
        //        view.getBackground().setAlpha(0);// 120为透明的比率
        //        view.setBackgroundResource(R.drawable.popup_cont);// 设置背景图片

        // Dialog按对话框外面取消操作
        this.setCanceledOnTouchOutside(true);

    }
    
    /**
     * 设置窗体属性
     */

    private void setProperty()
    {
        Window w = getWindow();
        WindowManager.LayoutParams lp = w.getAttributes();
        lp.alpha = 1.0f;// 设置当前对话框的 透明度
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
                R.layout.report_menuitem, R.drawable.menubtn_right2_1, data);
        return simperAdapter;
    }
    
    public void setPosition(int x, int y)
    {
        Window w = getWindow();
        WindowManager.LayoutParams lp = w.getAttributes();
        lp.x = x;
        lp.y = y;
        w.setAttributes(lp);
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
    
    @Override
    public void onAnimationStart(Animation animation)
    {

        
    }

    @Override
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
    public void onItemClick(AdapterView<?> parent, View view, int position,
            long id)
    {
        super.dismiss();
        mAdapter.setSeclection(position);
        mAdapter.notifyDataSetChanged();
        Menu_Item.ItemClickListener(view, position, id);
        
    }
    
    public void openAnimateReport()
    {
        isCloseAniEnd = false;
        com.mendong.travel.view.anim.AnimationUtils.openAnimateRight2(view, this);
    }

    public void closeAnimateReport()
    {
        com.mendong.travel.view.anim.AnimationUtils
                .closeAnimateRight2(view, this);
        isCloseAniEnd = true;
    }
    
    public void release()
    {
        context = null;
        Menu_Item = null;
        view = null;
    }
}
