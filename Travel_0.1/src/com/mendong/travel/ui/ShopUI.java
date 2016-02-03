package com.mendong.travel.ui;


import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.SimpleAdapter.ViewBinder;

import com.ab.activity.AbActivity;
import com.ab.net.AbImageDownloadCallback;
import com.ab.net.AbImageDownloadItem;
import com.ab.net.AbImageDownloadQueue;
import com.mendong.travel.R;
import com.mendong.travel.TravelApp;
import com.mendong.travel.asyncload.SetItem;
import com.mendong.travel.asyncload.MyAsyncGridViewAdapter;
import com.mendong.travel.requestdata.DataXml;
import com.mendong.travel.requestdata.ShopItem;
import com.mendong.travel.ui.adapter.ViewButton;


public class ShopUI extends AbActivity {

	public static final String PIC_PRE_URL = "http://www.xiaooo.cn/mobile/item/";
	public static final String PIC_SHOP_LOGO_URL = "http://www.xiaooo.cn/mobile/shop/";
	private GridView gridView;
	private Button btn_showSector;
	private ViewButton viewButton_sector;
	private int m_niVisibi = -1;
	
	private List<ShopItem> list;
	private DataXml dataXml ;
	private InputStream inputStream;
	
	private ImageView imgShopLogo;
	private int mShopID;
	private String m_shopTiTle;
	private String m_shopLogoUrl;
	private AbImageDownloadQueue mAbImageDownloadQueue = null;
	
	private List<SetItem> setItemList;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setAbContentView(R.layout.layout_shop_ui);
		TravelApp.getInstance().addActivity(this);
		
		Intent intent = this.getIntent();
		mShopID = intent.getIntExtra("ID", 1);
		m_shopTiTle = intent.getStringExtra("TITLE");
		m_shopLogoUrl = intent.getStringExtra("HEADURL");
		
		setTiTleBar();
		
		
		gridView = (GridView)findViewById(R.id.grid_shopGoods);
		//gridView.setAdapter(simpleAdapter);
		gridView.setOnItemClickListener(new OnItemClickListenerMonitor());
		//初始化btn_showSector,并且增加click监听
		viewButton_sector = (ViewButton) findViewById(R.id.imgView_sector);
		viewButton_sector.setOnTouchListener(new OnTouchListener() {
			
			public boolean onTouch(View v, MotionEvent event) 
			{
				
				return false;
			}
		});
		
		imgShopLogo = (ImageView)findViewById(R.id.imgView_shopHead);
	//	imgShopLogo.setBackground(Bitmap2Drawable(m_shopLogoUrl));
		
		btn_showSector = (Button)findViewById(R.id.btn_showSector);
		btn_showSector.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				if(m_niVisibi==-1)
				{
					m_niVisibi = 1;
					gridView.setEnabled(false);
				}
					
				else if(m_niVisibi==1)
				{
					m_niVisibi = -1;
					gridView.setEnabled(true);
				}
				viewButton_sector.setVisibility(m_niVisibi);
					
			}
		});
		
		mAbImageDownloadQueue = AbImageDownloadQueue.getInstance();
        dataXml = new DataXml();
		
		new Thread(runnable).start();
        
		
		
	}
	
	/**
	 * 设置TitleBar
	 */
	private void setTiTleBar()
	{
		
		this.setLogo(R.drawable.back);
	    this.setTitleLayoutBackground(R.drawable.bg_titlebar);
        this.setTitleText(m_shopTiTle);
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
        this.showProgressDialog("加载中...", false);
	}
	
	private void initTitleRightLayout()
    {
        clearRightView();
        View rightViewApp = mInflater.inflate(R.layout.app_btn, null);
        addRightView(rightViewApp);
        ImageButton appBtn = (ImageButton) rightViewApp.findViewById(R.id.btnback);

    }
	
	  Handler handler = new Handler()
	  {
	
		public void handleMessage(Message msg) 
		{
			super.handleMessage(msg);
			gridView.setAdapter(new MyAsyncGridViewAdapter(ShopUI.this, setItemList, gridView));
			//加载商家logo
			downLoadImage(m_shopLogoUrl);
			
			ShopUI.this.removeProgressDialog();
		}
		   
	  };
	
	
	 Runnable runnable = new Runnable() 
	 {
	
		public void run() 
		{
			try 
			{
				inputStream = dataXml.getDataStream(dataXml.shopXml(mShopID));
				list = dataXml.ShopPullParseXML(inputStream);
				
				setItemList = new ArrayList<SetItem>();
				for (int i = 0; i < list.size(); i++) 
				{
					setItemList.add(new SetItem
							(
							 PIC_PRE_URL+list.get(i).getPicName(),
							 list.get(i).getName(),
							 cutPriceRadixPoint(list.get(i).getPrice())));//去除商品价格小数点
				}
				
				Message message = new Message();
				handler.sendMessage(message);
				
				
				
			} catch (Exception e) {e.printStackTrace();}
		}
	 };
	
	
	
	 /** 
     * 根据一个网络连接(String)获取bitmap图像 
     *  
     * @param imageUri 
     * @return 
     * @throws MalformedURLException 
     */  
	public Bitmap downLoadBitmap(String imageUri) 
	{  
          // 显示网络上的图片  
          Bitmap bitmap = null;  
          try 
          {  
              URL url = new URL(imageUri);  
              HttpURLConnection conn = (HttpURLConnection) url.openConnection(); 
              conn.setConnectTimeout(5000);
              conn.setDoInput(true);  
              conn.connect();  
              InputStream is = conn.getInputStream();  
              bitmap = BitmapFactory.decodeStream(is);  
              is.close();  
     
           } catch (IOException e) {e.printStackTrace();return null;} 
          
          return bitmap;  
	} 
	
	/*private void bindAdapter(List<Bitmap> bmpList)
	{
		ArrayList<HashMap<String, Object>> mapList = new ArrayList<HashMap<String, Object>>();  
		for(int i=0;i<bmpList.size();i++)  
		{  
			 //进行小数点剪切
			 String strGoodsPrice = cutPriceRadixPoint(list.get(i).getPrice());
		     HashMap<String, Object> map = new HashMap<String, Object>();  
		     map.put("ItemImage", bmpList.get(i));
		     map.put("ItemName",  list.get(i).getName());
		     map.put("ItemPrice", strGoodsPrice);
		     mapList.add(map);  
		}  
		simpleAdapter = new SimpleAdapter(this,
                                          mapList, 
                                          R.layout.layout_shop_item, 
                                          new String[] {"ItemImage","ItemName","ItemPrice"},   
                                          new int[] {R.id.item_image,R.id.item_goodsName,R.id.item_goodsPrice});
		simpleAdapter.setViewBinder(new ViewBinder() {                          
			public boolean setViewValue(View view, Object data,String textRepresentation) 
			{    //判断是否为我们要处理的对象                  
				if(view instanceof ImageView  && data instanceof Bitmap)
				{                      
					ImageView iv = (ImageView) view;                                        
					iv.setImageBitmap((Bitmap) data);                      
					return true;                  
				}
				else                  
					return false;              
			 }}
			); 
	}*/
	
	
	private Drawable Bitmap2Drawable(Bitmap bitmap)
	{
		BitmapDrawable bitmapDrawable = new BitmapDrawable(bitmap);
		Drawable drawable = (Drawable)bitmapDrawable;
		return drawable;
	}

	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		this.removeProgressDialog();
		return super.onKeyDown(keyCode, event);
	}



	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	private class OnItemClickListenerMonitor implements OnItemClickListener
	{

		public void onItemClick(AdapterView<?> arg0, View view, int position,long id) 
		{
			Intent intent =new Intent(ShopUI.this,GoodsUI.class);
			intent.putExtra("id",list.get(position).getId());
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(intent);
		}
		
	}
	
	/**
	 * 剪切小数点
	 * @return
	 */
    private String cutPriceRadixPoint(float price)
    {
    	String strPrice = price + "";
    	String cutStr = strPrice.substring(0,strPrice.indexOf("."));
    	return cutStr;
    }
	
    
    /* download bitmap resource for post */
	private void downLoadImage(String logoUrl)
	{
		String url = PIC_SHOP_LOGO_URL+logoUrl;
				
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
						//更新UI
						imgShopLogo.setBackgroundDrawable(Bitmap2Drawable(bitmap));
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
	
	

}
