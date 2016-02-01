package com.mendong.travel.ui;


import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ab.activity.AbActivity;
import com.ab.net.AbImageDownloadCallback;
import com.ab.net.AbImageDownloadItem;
import com.ab.net.AbImageDownloadQueue;
import com.mendong.travel.R;
import com.mendong.travel.TravelApp;
import com.mendong.travel.requestdata.DataXml;
import com.mendong.travel.requestdata.Goods;
import com.mendong.travel.ui.adapter.MyPagerAdapter;

public class GoodsUI extends AbActivity{

	public static final int POST_NUM = 1;
	public static final String PIC_PRE_URL = "http://www.xiaooo.cn/mobile/item/";
	private ImageView[] imageViews = null;
	private ImageView imageView = null;
	private ViewPager advPager = null;
	private List<View> advPics;
	private AtomicInteger what = new AtomicInteger(0);
	private boolean isContinue = true;
	//商品ID
	private int m_goodsId;
	private Thread thread;
	//网络数据处理类
	private DataXml dataXml;
	//存储Goods数据
	private Goods goods;
	//网络数据流
	private InputStream inputStream;
	//存储网络图片数据
	private List<Bitmap> bmpList ;
	//图片名称链表
	private List<String> picNameList;
	
	//数据加载情况--判断
	private boolean m_bIsTextUpdate;
	
 	
	//UI控件
	private Button btn_detail;
	private Button btn_evaluate;
	private TextView txtView_price;
	private ImageView imgView_logo ;
	private TextView txt_DetailEvaluate;
	
	private AbImageDownloadQueue mAbImageDownloadQueue = null;
	
	private int[]orderId={R.drawable.order1_1,R.drawable.order2_1,R.drawable.order3_1,R.drawable.order4_1};
	private int[]orderId_focus={R.drawable.order1_2,R.drawable.order2_2,R.drawable.order3_2,R.drawable.order4_2};
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setAbContentView(R.layout.layout_goods_ui);
		setTiTleBar();
		TravelApp.getInstance().addActivity(this);
		getIntentData();

		//初始化UI控件
		txtView_price = (TextView) findViewById(R.id.goodsui_txtView_price);
		imgView_logo = (ImageView) findViewById(R.id.goodsui_imgView_logo);
		
		txt_DetailEvaluate = (TextView)findViewById(R.id.goodsui_txtView_detailEvaluate);
		btn_detail = (Button)findViewById(R.id.goodsui_btn_detail);
		btn_evaluate = (Button)findViewById(R.id.goodsui_btn_evaluate);
		//设置button背景色透明
		btn_detail.setBackgroundColor(Color.TRANSPARENT);
		btn_evaluate.setBackgroundColor(Color.TRANSPARENT);
		//添加监听
		btn_detail.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) 
			{
				txt_DetailEvaluate.setBackgroundResource(R.drawable.goodsui_detail);
			}
		});
		btn_evaluate.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) 
			{
				txt_DetailEvaluate.setBackgroundResource(R.drawable.goodsui_evaluate);
			}
		});
		
		initViewPager();
		
		this.showProgressDialog("加载中...", false);
		//获取网络数据资源
		goods = new Goods();
		dataXml = new DataXml();
		bmpList = new ArrayList<Bitmap>();
		picNameList = new ArrayList<String>();
		new Thread(runnable).start();
	
	}
	
	/**
	 * 设置TitleBar
	 */
	private void setTiTleBar()
	{
		
		this.setLogo(R.drawable.back);
	    this.setTitleLayoutBackground(R.drawable.bg_titlebar);
        
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
	 * 获取Intent传过来的参数
	 */
	private void getIntentData()
	{
		Intent intent = this.getIntent();
		m_goodsId = intent.getIntExtra("id",-1);
		//Toast.makeText(GoodsUI.this, "m_goodsId:"+m_goodsId+"...", Toast.LENGTH_SHORT).show();
		
	}
	
	Handler handler = new Handler()
	{

		public void handleMessage(Message msg) 
		{
			super.handleMessage(msg);
			
			txtView_price.setText(goods.getPrice()+"元");
			txt_DetailEvaluate.append("\n");
			txt_DetailEvaluate.append("\n");
			txt_DetailEvaluate.append("\n");
			txt_DetailEvaluate.append(goods.getInfo()+"\n");
			txt_DetailEvaluate.append(goods.getParam()+"\n");
			txt_DetailEvaluate.append(goods.getAddress()+"\n");
			m_bIsTextUpdate = false;
			GoodsUI.this.removeProgressDialog();
			
			mAbImageDownloadQueue = AbImageDownloadQueue.getInstance();
			for(int i = 0; i < picNameList.size(); i ++)
			{
				downLoadImage(picNameList.get(i),i);
			}
			
			//设置标题
			GoodsUI.this.setTitleText(goods.getName());
	        //设置文本位置
			GoodsUI.this.setTitleTextMargin(20,0,0, 0);
		}
		
	};
	
	Runnable runnable = new Runnable() 
	{
		
		public void run() 
		{
			Message message = null;
			try 
			{
				inputStream = dataXml.getDataStream(dataXml.goodsXml(m_goodsId));
				goods = dataXml.GoodsPullParseXML(inputStream);
				//数据加载进来
				m_bIsTextUpdate = true;
				//存储到商品链表
				picNameList = goods.getPicNameList();
				bmpList = new ArrayList<Bitmap>();
				
				//文字数据加载进来，更新文字
				message = new Message();
				handler.sendMessage(message);
				
			} 
			catch (Exception e) {e.printStackTrace();}
			
		}
	};
	
	
	
	
	
	/**
	 * 设置广告栏的图片及切换效果
	 */
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
					Toast.makeText(GoodsUI.this, "q", Toast.LENGTH_SHORT).show();
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
		/*thread = new Thread(new Runnable()
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
		thread.start();*/
		
		
	}
	
	/* update post bitmap at given index */
	public void updatePager(Bitmap bitmap, int imgIdx)
	{
		ImageView img = (ImageView)advPics.get(imgIdx);
		img.setImageBitmap(bitmap);
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
	
	
	 /* download bitmap resource for post */
		private void downLoadImage(String picName,int index)
		{
			final int niIndex = index;
			String url = PIC_PRE_URL+picName;
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
							//默认第一张为商品Logo
							if(niIndex == 0)
							   imgView_logo.setBackgroundDrawable(Bitmap2Drawable(bitmap));
							//更新UI
							updatePager(bitmap, niIndex);
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
	
		private Drawable Bitmap2Drawable(Bitmap bitmap)
		{
			BitmapDrawable bitmapDrawable = new BitmapDrawable(bitmap);
			Drawable drawable = (Drawable)bitmapDrawable;
			return drawable;
		}
		
		public boolean onKeyDown(int keyCode, KeyEvent event) 
		{
			
			return super.onKeyDown(keyCode, event);
		}
	
  }
