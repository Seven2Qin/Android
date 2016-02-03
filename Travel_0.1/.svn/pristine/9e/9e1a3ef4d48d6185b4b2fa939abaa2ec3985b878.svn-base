/**
 * 项目名：     Travel
 * 文件名：     BZBListView.java
 * 文件描述： 
 * 作者：         Administrator
 * 创建时间：  2013年7月18日
 * 版权声明 ： Copyright (C) 2008-2010 RichPeak
 *
 */
package com.mendong.travel.view.news;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.ab.activity.AbActivity;
import com.ab.global.AbConstant;
import com.ab.net.AbHttpCallback;
import com.ab.net.AbHttpItem;
import com.ab.net.AbHttpPool;
import com.ab.view.AbPlayView;
import com.ab.view.AbPullToRefreshListView;
import com.mendong.travel.R;
import com.mendong.travel.TravelApp;
import com.mendong.travel.Bean.bzb.BZBBean;
import com.mendong.travel.control.bzb.BZBLogic;
import com.mendong.travel.tools.Tools;
import com.mendong.travel.view.adapter.bzb.BZBAdapter;
import com.mendong.travel.view.bzb.BZBListView.MyOnClickListener;
import com.mendong.travel.view.bzbinfo.BZBInfoView;
import com.mendong.travel.view.newsinfo.NewsInfoView;

public class NewsListView extends AbActivity {
	
    private String mTitle;
    private AbPullToRefreshListView listNews;
    private BZBLogic mLogic;
    private BZBAdapter adapter;
	private AbPlayView mAbAutoPlayView = null;
	private AbHttpPool mAbHttpPool;
	
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
            Intent intent = new Intent(getApplicationContext(), BZBInfoView.class);
            intent.putExtra("TITLE",bzb.getName());
            intent.putExtra("REQ", true);
            intent.putExtra("ID", Integer.valueOf(bzb.getId()));
            startActivity(intent);
            
        }
        
    };
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
        setAbContentView(R.layout.bzb_list);
        //增加到Acitivity链表
        TravelApp.getInstance().addActivity(this);
        Intent intent = this.getIntent();       

        this.setLogo(R.drawable.back);
        this.setTitleLayoutBackground(R.drawable.bg_titlebar);
        
        //设置文本位置
        this.setTitleTextMargin(0,0,0, 0);
        
        this.setLogoBackOnClickListener(new View.OnClickListener(){
        	public void onClick(View v)
        	{
                finish();
            }
        });
       
        // this.setLogoLine(R.drawable.line);
        initTitleRightLayout();
        //this.setTitleLayoutGravity(Gravity.CENTER_HORIZONTAL, Gravity.CENTER_HORIZONTAL);
      //  initAutoPlayView();
        initListView();
        
        mTitle = intent.getStringExtra("TITLE");
        if (null != mTitle)
        {
            this.setTitleText(mTitle);
        }
        
        mLogic.setmId(intent.getIntExtra("ID", -1));
        request();
	}
	
	private void initTitleRightLayout()
    {
        clearRightView();
        View rightViewApp = mInflater.inflate(R.layout.app_btn, null);
        addRightView(rightViewApp);
        ImageButton appBtn = (ImageButton) rightViewApp
                .findViewById(R.id.btnback);

        appBtn.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                //Intent intent = new Intent(ImageListActivity.this,DankeActivity.class); 
                //startActivity(intent);
                return;
            }
        });

    }
	
	/*private void initAutoPlayView()
    {
        mAbAutoPlayView = (AbPlayView) findViewById(R.id.ivpopup2);
        
        for(int i = 0; i < 4; ++i)
        {
            addPlayView(R.drawable.header0 + i);
        }
        
        mAbAutoPlayView.startPlay();
    }*/
	
	private void addPlayView(int resId)
    {
        View mPlayView = mInflater.inflate(R.layout.play_view_item, null);
        Holder holder = new Holder();
        holder.mPlayImage = (ImageView) mPlayView
                .findViewById(R.id.mPlayImage);
        holder.mProgressBar = (ProgressBar)mPlayView
                .findViewById(R.id.imgloadbar);
        holder.mProgressBar.setVisibility(View.GONE);
        
        //        TextView mPlayText = (TextView) mPlayView.findViewById(R.id.mPlayText);
        //        mPlayText.setText("这是第" + mAbAutoPlayView.getCount() + "个");
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.header0);
        holder.mPlayImage.setBackgroundResource(resId);
        mAbAutoPlayView.setPageLineHorizontalGravity(Gravity.CENTER);
        mAbAutoPlayView.addView(mPlayView);
        
//        mImageViewList.add(holder);
    }
	
	private void initListView()
    {

        listNews = (AbPullToRefreshListView) findViewById(R.id.bzb_list_view);
        mLogic = BZBLogic.getInstance();
        adapter = new BZBAdapter(getBaseContext(), mLogic.getBZBList(),
                R.layout.list_items,
                new int[] { R.id.itemsIcon, R.id.itemsTitle, R.id.itemsText, R.id.itemsId}, mImgListener);
        listNews.setAdapter(adapter);

        //item被点击事件
        listNews.setOnItemClickListener(new OnItemClickListener()
        {
            public void onItemClick(AdapterView<?> parent, View view,
                    int position, long id)
            {
                
                List<BZBBean> mBZBBeanList= mLogic.getBZBList();
                if(null == mBZBBeanList || mBZBBeanList.isEmpty())
                {
                    return;
                }
                if(position < 1)
                {
                    return;
                }
                BZBBean bzb = mBZBBeanList.get(position - 1);
                Intent intent = new Intent(getApplicationContext(), NewsInfoView.class);
                intent.putExtra("TITLE",bzb.getName());
                intent.putExtra("REQ", true);
                intent.putExtra("ID", Integer.valueOf(bzb.getId()));
                startActivity(intent);

            }
        });
    }
	
	private void request()
    {
        mAbHttpPool = AbHttpPool.getInstance();

//        showDialog(AbConstant.DIALOGPROGRESS);
        
        this.showProgressDialog("请稍后", false);
        
//        UiHelper.showProgressDialog(this, null, "请稍后", null, null, null, null, false);

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
                    //访问网络下载数据
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
//                removeDialog(AbConstant.DIALOGPROGRESS);
                
//                UiHelper.removeProgressDialog();
                
                mLogic.updateData(true);
                
                listNews.onRefreshComplete();
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
                    if (mLogic.hasNext())
                    {
                        //访问网络下载数据
                        mLogic.requestData();
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
//                removeDialog(AbConstant.DIALOGPROGRESS);
                
//                UiHelper.removeProgressDialog();
                
                //更新数据和UI

                if (mLogic.updateData(false))
                {
                    listNews.onScrollComplete(AbConstant.HAVE);
                }
                else
                {
                    //没有新数据了
                	listNews.onScrollComplete(AbConstant.NOTHAVE);
                }

            }
        };

        listNews.setRefreshItem(upItem);
        listNews.setScrollItem(downItem);
        mAbHttpPool.download(upItem);
    }
	
	@Override
    protected void onDestroy()
    {
//        removeDialog(AbConstant.DIALOGPROGRESS);
        this.removeProgressDialog();

//        UiHelper.removeProgressDialog();
        

        super.onDestroy();
    }
	
	private static class Holder
    {
        ImageView mPlayImage;
        
        ProgressBar mProgressBar;
    }
	
	
}
