package com.mendong.travel.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.w3c.dom.Comment;

import android.app.LocalActivityManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.Toast;

import com.ab.activity.AbActivity;
import com.ab.net.AbHttpCallback;
import com.ab.net.AbHttpItem;
import com.ab.net.AbImageDownloadCallback;
import com.ab.net.AbImageDownloadItem;
import com.ab.net.AbImageDownloadQueue;
import com.mendong.travel.R;
import com.mendong.travel.TravelApp;
import com.mendong.travel.Bean.bzb.BZBBean;
import com.mendong.travel.control.bzb.BZBLogic;
import com.mendong.travel.ui.adapter.MyPagerAdapter;
import com.mendong.travel.ui.adapter.ViewTouch;
import com.mendong.travel.view.TabActivity;
import com.mendong.travel.view.bzb.BZBListView.MyOnClickListener;

public class ViewUI extends AbActivity {

	private final static String NI_VIEW   = "景点";
	private final static String NI_SHOP   = "购物";
	private final static String NI_FALLOW = "休闲";
	private final static String NI_BAR    = "酒吧";
	private final static String NI_EAT    = "美食";
	private final static String NI_HAPPY  = "娱乐";
	private final static String POST_URL_PREFIX = "http://www.xiaooo.cn/mobile/post/h";
	private final static String POST_URL_SUFFIX = ".png";
	private final static int POST_NUM = 4;
	private ImageView[] imageViews = null;
	private ImageView imageView = null;
	private ViewPager advPager = null;
	private AtomicInteger what = new AtomicInteger(0);
	private boolean isContinue = true;
	
	private Thread thread;
	private String strLoc;	
	private AbImageDownloadQueue mAbImageDownloadQueue = null;
	private List<View> advPics;
	private TabHost tabHost;
	private TabWidget tabWidget;
	private Button btn_view;
	private Button btn_shop;
	private Button btn_fallow;
	private Button btn_bar;
	private Button btn_eat;
	private Button btn_happy;

	//按钮监听对象
	private ButtonClickMonitor buttonClickMonitor;	
	private int[]orderId={R.drawable.order1_1,R.drawable.order2_1,R.drawable.order3_1,R.drawable.order4_1};
	private int[]orderId_focus={R.drawable.order1_2,R.drawable.order2_2,R.drawable.order3_2,R.drawable.order4_2};
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setAbContentView(R.layout.layout_view_ui);
		TravelApp.getInstance().addActivity(this);
		//设置标题栏
		Intent intent = this.getIntent();
		strLoc = intent.getStringExtra("location");
		setTiTleBar();
		
		LocalActivityManager activityManager = new LocalActivityManager(ViewUI.this, true);
		 //这句很重要
		activityManager.dispatchCreate(savedInstanceState);
		//初始化TabHost选项卡
		InitTabHost(activityManager);
		//初始化选项卡上按钮
		InitTabHostButton();
		//初始化滑动页面
		initViewPager();	
		
		ViewTouch viewTouch = (ViewTouch) findViewById(R.id.myViewTouch);
		viewTouch.addCallBackListener(new CallBackMonitor());
	}
	
	private void InitTabHost(LocalActivityManager activityManager)
	{
		tabHost = (TabHost) findViewById(android.R.id.tabhost);		
		tabHost.setup(activityManager);
		tabWidget = tabHost.getTabWidget();
		
		Intent intent = new Intent(ViewUI.this,TabActivity.class);
		intent.putExtra("id", 1);
		tabHost.addTab(tabHost.newTabSpec(NI_VIEW)  
             .setIndicator(NI_VIEW)  
             .setContent(intent));
		
		intent = new Intent(ViewUI.this,TabActivity.class);
		intent.putExtra("id", 2);
		tabHost.addTab(tabHost.newTabSpec(NI_SHOP)  				
				.setIndicator(NI_SHOP)  				
				.setContent(intent));
		
		intent = new Intent(ViewUI.this,TabActivity.class);
		intent.putExtra("id", 3);
		tabHost.addTab(tabHost.newTabSpec(NI_FALLOW)				
				.setIndicator(NI_FALLOW)  				
				.setContent(intent));
		
		intent = new Intent(ViewUI.this,TabActivity.class);
		intent.putExtra("id", 4);
		tabHost.addTab(tabHost.newTabSpec(NI_BAR)				
				.setIndicator(NI_BAR)  				
				.setContent(intent));
		
		intent = new Intent(ViewUI.this,TabActivity.class);
		intent.putExtra("id", 5);
		tabHost.addTab(tabHost.newTabSpec(NI_EAT)				
				.setIndicator(NI_EAT)  				
				.setContent(intent));
		
		intent = new Intent(ViewUI.this,TabActivity.class);
		intent.putExtra("id", 6);
		tabHost.addTab(tabHost.newTabSpec(NI_HAPPY)  				
				.setIndicator(NI_HAPPY)  				
				.setContent(intent));  

		tabHost.setCurrentTab(0);
	}	
	
	private void InitTabHostButton()
	{
		//Button监听
		buttonClickMonitor = new ButtonClickMonitor();
		//初始化Button
		btn_view = (Button)findViewById(R.id.viewui_btn_view);
		btn_shop = (Button)findViewById(R.id.viewui_btn_shopping);
		btn_fallow = (Button)findViewById(R.id.viewui_btn_fallow);
		btn_bar = (Button)findViewById(R.id.viewui_btn_bar);
		btn_eat = (Button)findViewById(R.id.viewui_btn_eat);
		btn_happy = (Button)findViewById(R.id.viewui_btn_happy);
		
		btn_view.setOnClickListener(buttonClickMonitor);
		btn_shop.setOnClickListener(buttonClickMonitor);
		btn_fallow.setOnClickListener(buttonClickMonitor);
		btn_bar.setOnClickListener(buttonClickMonitor);
		btn_eat.setOnClickListener(buttonClickMonitor);
		btn_happy.setOnClickListener(buttonClickMonitor);
	}
	
	/**
	 * 设置按钮背景
	 */
	private void setButtonBg()
	{
		btn_view.setBackgroundResource(R.drawable.viewui_tab);
		btn_shop.setBackgroundResource(R.drawable.viewui_tab);
		btn_fallow.setBackgroundResource(R.drawable.viewui_tab);
		btn_bar.setBackgroundResource(R.drawable.viewui_tab);
		btn_eat.setBackgroundResource(R.drawable.viewui_tab);
		btn_happy.setBackgroundResource(R.drawable.viewui_tab);
	}	
	
	/**
	 * 设置TitleBar
	 */
	private void setTiTleBar()
	{
		
		this.setLogo(R.drawable.back);
	    this.setTitleLayoutBackground(R.drawable.bg_titlebar);
        this.setTitleText(strLoc);
        //设置文本位置
        this.setTitleTextMargin(20,0,0, 0);
        //返回按钮键
        this.setLogoBackOnClickListener(new View.OnClickListener(){
            public void onClick(View v)
            {
                finish();
            }});
        //初始化TitleBar右边按钮
        initTitleRightLayout();
	}
	
	private void initTitleRightLayout()
    {
        clearRightView();
        View rightViewApp = mInflater.inflate(R.layout.app_btn, null);
        addRightView(rightViewApp);
        ImageButton appBtn = (ImageButton) rightViewApp.findViewById(R.id.btnback);

    }	

	/**
	 * 按钮监听
	 * @author Administrator
	 *
	 */
	private class ButtonClickMonitor implements OnClickListener
	{

		public void onClick(View v) 
		{
			//设置TabHost的Button背景
			setButtonBg();
			if(v instanceof Button)
			{
				
				if(((Button) v).getText().equals(NI_VIEW))
				{
					btn_view.setBackgroundResource(R.drawable.viewui_tab_focus);
					tabHost.setCurrentTab(0);
				}
				else if(((Button) v).getText().equals(NI_SHOP))
				{
					btn_shop.setBackgroundResource(R.drawable.viewui_tab_focus);
					tabHost.setCurrentTab(1);
				}
				else if(((Button) v).getText().equals(NI_FALLOW))
				{
					btn_fallow.setBackgroundResource(R.drawable.viewui_tab_focus);
					tabHost.setCurrentTab(2);
				}
				else if(((Button) v).getText().equals(NI_BAR))
				{
					btn_bar.setBackgroundResource(R.drawable.viewui_tab_focus);
					tabHost.setCurrentTab(3);
				}
				else if(((Button) v).getText().equals(NI_EAT))
				{
					btn_eat.setBackgroundResource(R.drawable.viewui_tab_focus);
					tabHost.setCurrentTab(4);
				}
				else if(((Button) v).getText().equals(NI_HAPPY))
				{
					btn_happy.setBackgroundResource(R.drawable.viewui_tab_focus);
					tabHost.setCurrentTab(5);
				}
			}
		}
		
	}

	/**
	 * 操作圆点轮换变背景
	 */
	private void whatOption() {
		what.incrementAndGet();
		if (what.get() > imageViews.length - 1) {
			what.getAndAdd(-4);
		}
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			
		}
	}

	/**
	 * 处理定时切换广告栏图片的句柄
	 */
	private final Handler viewHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			advPager.setCurrentItem(msg.what);
			super.handleMessage(msg);
		}

	};

	/** 指引页面改监听器 */
	private final class GuidePageChangeListener implements OnPageChangeListener {

		@Override
		public void onPageScrollStateChanged(int arg0) {

		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {

		}

		@Override
		public void onPageSelected(int arg0) {
			for (int i = 0; i < imageViews.length; i++) {
				imageViews[arg0]
						.setBackgroundResource(orderId_focus[arg0]);
				if (arg0 != i) {
					imageViews[i]
							.setBackgroundResource(orderId[i]);
				}
			}

		}

	}
		
	/* init post view */
	private void initViewPager() {
		
		advPager = (ViewPager) findViewById(R.id.adv_pager);
		advPics = new ArrayList<View>();
		ViewGroup group = (ViewGroup) findViewById(R.id.viewGroup);
		imageViews = new ImageView[POST_NUM];
		
		for (int i = 0; i < POST_NUM; i++) 
		{
			ImageView img = new ImageView(this);
			img.setScaleType(ImageView.ScaleType.FIT_XY);
			img.setBackgroundResource(R.drawable.icon_default);
			advPics.add(img);
  
			/* set navigation bar */
			imageView = new ImageView(this);
			imageView.setOnClickListener(new OnClickListener() {				
				@Override
				public void onClick(View v) 
				{
					Toast.makeText(ViewUI.this, "q", Toast.LENGTH_SHORT).show();
				}
			});
			imageView.setLayoutParams(new LayoutParams(60, 60));
			imageView.setPadding(20,0,0,20);
			imageViews[i] = imageView;
			if (i == 0) {
				// 默认选中第一张图片
				imageViews[i].setBackgroundResource(orderId_focus[i]);
			} else {
				imageViews[i].setBackgroundResource(orderId[i]);
			}
			
			group.addView(imageViews[i]);
		}

		advPager.setAdapter(new MyPagerAdapter(advPics));
		advPager.setOnPageChangeListener(new GuidePageChangeListener());
		//按下时不继续定时滑动,弹起时继续定时滑动
		advPager.setOnTouchListener(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
				case MotionEvent.ACTION_MOVE:
					isContinue = false;
					break;
				case MotionEvent.ACTION_UP:
					isContinue = true;
					break;
				default:
					isContinue = true;
					break;
				}
				return false;
			}
		});
		// 定时滑动线程
		thread = new Thread(new Runnable()
		{
			@Override
			public void run() {
				while (true) {
					if (isContinue) {
						viewHandler.sendEmptyMessage(what.get());
						whatOption();
					}
				}				
			}			
		});
		thread.start();
		
		mAbImageDownloadQueue = AbImageDownloadQueue.getInstance();
		for(int i = 0; i < POST_NUM; i ++)
		{
			downLoadImage(i);
		}
	}
	
	/* update post bitmap at given index */
	public void updatePager(Bitmap bitmap, int imgIdx)
	{
		ImageView img = (ImageView)advPics.get(imgIdx);
		img.setImageBitmap(bitmap);
	}
	
	/* download bitmap resource for post */
	private void downLoadImage(int index)
	{
		final int imgIdx = index;
		String url = POST_URL_PREFIX+(index+1)+POST_URL_SUFFIX; 
				
		try
		{
			AbImageDownloadItem item = new AbImageDownloadItem();
			item.imageUrl = url;
			item.callback = new AbImageDownloadCallback()
			{
				public void update(Bitmap bitmap, String imageUrl)
				{
					if (bitmap != null && !bitmap.isRecycled())
					{
						updatePager(bitmap, imgIdx);
					}
				}
			};
			mAbImageDownloadQueue.download(item);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	private class CallBackMonitor implements com.mendong.travel.ui.adapter.ViewTouch.CallBack
	{

		public void setCurrentTab() 
		{
			ViewUI.this.tabHost.setCurrentTab(1);
		}
		
	}

}
