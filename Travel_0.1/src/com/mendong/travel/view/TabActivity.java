package com.mendong.travel.view;

import java.util.List;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;

import com.ab.activity.AbActivity;
import com.ab.net.AbHttpCallback;
import com.ab.net.AbHttpItem;
import com.ab.net.AbHttpPool;
import com.mendong.travel.R;
import com.mendong.travel.Bean.bzb.BZBBean;
import com.mendong.travel.control.bzb.BZBLogic;
import com.mendong.travel.ui.ShopUI;
import com.mendong.travel.ui.adapter.MySimpleAdapter;
import com.mendong.travel.view.bzb.BZBListView.MyOnClickListener;

public class TabActivity extends AbActivity {

	private BZBLogic mLogic;
	private GridView gridView;
	private AbHttpPool mAbHttpPool;
	private MySimpleAdapter adapter;
	
	private String URL;
	private MyOnClickListener mImgListener = new MyOnClickListener()
    {

        @Override
        public void onClick(View v, int position)
        {
            List<BZBBean> mBZBBeanList= mLogic.getBZBList();
            if(null == mBZBBeanList || mBZBBeanList.isEmpty())
            {
                return;
            }
            if(position < 0)
            {
                return;
            }
            BZBBean bzb = mBZBBeanList.get(position);
            Intent intent = new Intent(getApplicationContext(), ShopUI.class);
            intent.putExtra("TITLE",bzb.getName());
            intent.putExtra("ID", Integer.valueOf(bzb.getId()));
            intent.putExtra("HEADURL",bzb.getLogoUrl());
            intent.putExtra("REQ", true);
            startActivity(intent);
        }        
    };
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_tab);
		gridView = (GridView) findViewById(R.id.viewui_tab_gridView);
		Intent intent = this.getIntent();		
		mLogic = new BZBLogic();
		mLogic.setmId(intent.getIntExtra("id", 1));		
		request();
		
	}
	
	private void updateGridView()
	{	
		adapter = new MySimpleAdapter(this,R.drawable.icon_default, mLogic.getBZBList(), mImgListener);
		gridView.setAdapter(adapter);
		gridView.setSelector(new ColorDrawable(Color.TRANSPARENT));
	}
	
	private void request()
    {
        mAbHttpPool = AbHttpPool.getInstance();

//        showDialog(AbConstant.DIALOGPROGRESS);
        
        this.showProgressDialog("加载中...", false);
	
       //定义上拉和下拉查询的事件
        AbHttpItem upItem = new AbHttpItem();
        upItem.callback = new AbHttpCallback()
        {
            @Override
            public void get()
            {
                try
                {
                    Thread.sleep(1000);                    
                    mLogic.setmPage(0);
                    mLogic.requestData();
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }                
            }

            @Override
            public void update()
            {
                removeProgressDialog();
                mLogic.updateData(true);
                updateGridView();
            }            
        };        
        
        AbHttpItem downItem = new AbHttpItem();
        downItem.callback = new AbHttpCallback()
        {
            @Override
            public void get()
            {
                try
                {
                    Thread.sleep(1000);
                    if(mLogic!=null)
                    {                    	
                    	if (mLogic.hasNext())
                    	{
                    		mLogic.requestData();
                    	}
                    }
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }

            @Override
            public void update()
            {                
                removeProgressDialog();
                updateGridView();
            }
        };

        mAbHttpPool.download(upItem);
    }
}
