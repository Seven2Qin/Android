/**
 * App Name:     Travel
 * File Name:    MyMapView.java
 * Description:  Street Map View
 * Author:       wangxin, qsw
 * Create Time:  2014.1
 * Copyright (C) 2013-2014 Around Circle
 *
 */
package com.mendong.travel.view.map;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.jump.utils.view.UiHelper;
import org.osmdroid.DefaultResourceProxyImpl;
import org.osmdroid.ResourceProxy;
import org.osmdroid.api.IMapController;
import org.osmdroid.bonuspack.routing.RoadNode;
import org.osmdroid.events.MapListener;
import org.osmdroid.events.ScrollEvent;
import org.osmdroid.events.ZoomEvent;
import org.osmdroid.tileprovider.tilesource.XYTileSource;
import org.osmdroid.util.BoundingBoxE6;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.OverlayItem;
import org.osmdroid.views.overlay.PathOverlay;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mendong.travel.R;
import com.mendong.travel.TravelApp;
import com.mendong.travel.camera.CameraView;
import com.mendong.travel.requestdata.DataXml;
import com.mendong.travel.requestdata.PointLatLng;
import com.mendong.travel.ui.AreaUI;
import com.mendong.travel.ui.CircleUI;
import com.mendong.travel.view.bzb.BZBListView;
import com.mendong.travel.view.bzbinfo.BZBInfoView;
import com.mendong.travel.view.news.NewsListView;
import com.mendong.travel.view.popups.Left1Menu;
import com.mendong.travel.view.popups.Left2Menu;
import com.mendong.travel.view.popups.Left3Menu;
import com.mendong.travel.view.popups.MenuItem;
import com.mendong.travel.view.popups.Right1Menu;
import com.mendong.travel.view.popups.Right2Menu;
import com.mendong.travel.wifi.DealData;
import com.mendong.travel.wifi.Position;
import com.mendong.travel.wifi.Spot;

public final class MyMapView extends FragmentActivity implements MapListener
{

    private final static String TAG = "MyMapView";
    private final static String ROUTE_OPTYPE = "showRoute";
	
	private Position m_Position; /* My location */	
	private OverlayItem m_MyLocItem;
	private OverlayItem m_DestItem;	
	private Drawable m_MyLocDrawable;
	private Drawable m_DestDrawable;
	private ItemizedIconOverlay<OverlayItem> m_MyLocationOverlay;	
	private PathOverlay m_MyPath;  
	private Drawable m_ShopDrawable;
	private OverlayItem m_ShopItem;
	private List<OverlayItem> m_ShopItemList;
	private ItemizedIconOverlay<OverlayItem> m_ShopOverlay;
	
	private View m_ShopPopView;
	private boolean m_bIsShowOverIcon;
	
	private MapView m_MapView; 		
	private IMapController m_MapController;	
	private ResourceProxy  m_ResourceProxy;
	
	private boolean m_bIsQuit = false;          /* show logic */
	private boolean m_bIsShowRoute=false;
	private int m_iStartId;
	private int m_iEndId;
	private int m_iAlarmCount=0;
	
    private static final int[] Left1MenuItem = { R.drawable.left_1_1_1,
            R.drawable.left_1_2_1, R.drawable.left_1_3_1,
            R.drawable.left_1_4_1, R.drawable.left_1_5_1, R.drawable.left_1_6_1 };

    private static final int[] Left3MenuItem = { R.drawable.left_3_1_1,
            R.drawable.left_3_2_1, R.drawable.left_3_3_1,
            R.drawable.left_3_4_1, R.drawable.left_3_5_1, R.drawable.left_3_6_1 };

    private static final int[] right1MenuItem = { R.drawable.right_1_1_1,
            R.drawable.right_1_2_1, R.drawable.right_1_3_1,
            R.drawable.right_1_4_1, R.drawable.right_1_5_1,
            R.drawable.right_1_6_1 };

    private static final int[] right2MenuItem = { R.drawable.right_1_2_1,
            R.drawable.right_2_2_1, R.drawable.right_2_3_1,
            R.drawable.right_2_4_1, R.drawable.right_2_5_1,
            R.drawable.right_2_6_1 };

    private ImageButton imgBtLeft1;
    private Left1Menu mLeft1Menu;
    private Left1MenuItem mLeft1MenuItem;
    private ImageButton imgBtLeft2;
    private Left2Menu mLeft2Menu;
    private Left2MenuItem mLeft2MenuItem;
    private ImageButton imgBtLeft3;
    private Left3Menu mLeft3Menu;
    private Left3MenuItem mLeft3MenuItem;
    private ImageButton imgBtRight1;
    private Right1Menu mRight1Menu;
    private Right1MenuItem mRight1MenuItem;
    private ImageButton imgBtRight2;
    private Right2Menu mRight2Menu;
    private Right2MenuItem mRight2MenuItem;
    
    private ImageButton m_btnCamera;
    private ImageButton m_btnLoc;
    private ImageButton m_btnClear;
    
    WifiManager m_WifiManager;
    
    Runnable m_LocRunnable = new Runnable(){
    	public void run() {
    		try 
			{
				while(TravelApp.getInstance().getTraceable())
				{
					DealData findLocLogic = new DealData();
					findLocLogic.getResponse(wifiScan());					
					m_Position = findLocLogic.getPosition();
					
					Message msg = new Message();
					if(m_bIsShowRoute)
					{
						List<PointLatLng> listPointLatLngs = null;
						m_iStartId = m_Position.getId();
						listPointLatLngs = new DataXml().getDataList(m_iStartId,m_iEndId);
						Bundle b = new Bundle();
						b.putSerializable("list", (Serializable) listPointLatLngs);
						msg.setData(b);
					}					
					m_PosHandler.sendMessage(msg);
					Thread.sleep(3000);
				}				
			}
			catch (IllegalStateException e) 
			{
				e.printStackTrace();
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
    	}
    };   
	
	Handler m_PosHandler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if(m_Position != null && TravelApp.getInstance().getTraceable())
			{
				TravelApp.getInstance().setCurrentUserPositionId(m_Position.getId());
				refreshPosition(m_Position.getLat(), m_Position.getLng());
				
				if(m_bIsShowRoute)
				{
					if( (List<PointLatLng>)msg.getData().getSerializable("list")==null)
					{
						if(m_iAlarmCount == 0)
						{
							Toast.makeText(MyMapView.this,"无法获取合适路线!",Toast.LENGTH_SHORT).show();
							m_iAlarmCount ++;
						}
						return;
					}
					//收到消息，并且处理
				    drawRoute((List<PointLatLng>) msg.getData().getSerializable("list"));
				    m_btnClear.setVisibility(View.VISIBLE);
				}
			}			
		}	
	};

	public void onCreate(Bundle savedInstanceState)
    {
        try
        {
            super.onCreate(savedInstanceState);
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            setContentView(R.layout.map);
            stopAll();
            // activity register
            TravelApp.getInstance().addActivity(this);           
            
            // init map
            initOSM();
            
            // init layout UI
            initLayout();
            
            
        }
        catch (Exception e) 
        {
        	e.printStackTrace();
        }
        
        m_WifiManager = (WifiManager)getSystemService(Context.WIFI_SERVICE);
		if(!m_WifiManager.isWifiEnabled())
		{
			m_WifiManager.setWifiEnabled(true);
		}
        
        getExtraIntent();
        
        // wifi sample by intent
        TravelApp app = TravelApp.getInstance();
        if(app.getTraceable())
		{
        	m_btnLoc.setBackgroundResource(R.drawable.custom_loc2);
        	Thread t = new Thread(m_LocRunnable);
        	t.start();
        }
        else
        {
        	m_btnLoc.setBackgroundResource(R.drawable.custom_loc);
        }
    }
	
	
	public void stopAll()
	{
		for (int i = TravelApp.getInstance().getList().size()-1; i > 0; i--) 
		{
			TravelApp.getInstance().getList().get(i).finish();
		}
	}
	
	
	
	private void initLayout()
    {
        initLeft1Menu();
        initLeft3Menu();
        initRight1Menu();
        initRight2Menu();
        
        // camera button init
        m_btnCamera = (ImageButton)findViewById(R.id.imageEye);
        m_btnCamera.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v)
            {
				Intent intent = new Intent(getApplicationContext(), CameraView.class);
				startActivity(intent);
            }
        });
        
        // my location button init
        m_btnLoc = (ImageButton)findViewById(R.id.imgButton_switcher);
        m_btnLoc.setOnClickListener(new OnClickListener(){			
			public void onClick(View v) 
			{
				TravelApp app = TravelApp.getInstance();
				app.setTraceable(!app.getTraceable());
				
				if(!app.getTraceable())
				{
					m_MyLocationOverlay.removeItem(m_MyLocItem);
			    	m_MapView.invalidate();
			    	m_btnLoc.setBackgroundResource(R.drawable.custom_loc);			    	
				}
				else
				{
					m_btnLoc.setBackgroundResource(R.drawable.custom_loc2);
					Thread t = new Thread(m_LocRunnable);
		        	t.start();
				}
			}
		});
        
        // route clear button init
        m_btnClear = (ImageButton)findViewById(R.id.clear);
        m_btnClear.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
            	m_bIsShowRoute = false;
            	m_MyLocationOverlay.removeItem(m_DestItem);
            	m_MapView.getOverlays().remove(m_MyPath);
            	m_MapView.invalidate();
            	m_btnClear.setVisibility(View.INVISIBLE);
            }
        });
        m_btnClear.setVisibility(View.INVISIBLE);
		
    }
   
    
    
   
    
    
    /**
     * 方法名称：  getExtraIntent
     * 作者：         Administrator
     * 方法描述：  获取外部传进的参数
     * 输入参数：  
     * 返回类型：  void
     */
    private void getExtraIntent()
    {
        Intent intent = this.getIntent();
        m_iStartId = intent.getIntExtra("startId",-1);
        m_iEndId = intent.getIntExtra("endId",-1);
        m_bIsShowRoute = intent.getBooleanExtra("isShowRoute", false);
    }   
   
    private void initOSM()
    {
    	ArrayList<OverlayItem> m_LocationList;    	
    	m_MapView = (MapView) findViewById(R.id.myOSMmapview); 
		m_MapView.setTileSource(new XYTileSource("MapQuest",ResourceProxy.string.unknown,3,5, 200,".jpg","")); 
		m_MapView.setMultiTouchControls(true);
		m_MapView.setUseDataConnection(false);
		m_MapView.setBuiltInZoomControls(true);
		m_MapView.setMapListener(this);
		m_MapView.setScrollableAreaLimit(new BoundingBoxE6(79.0, 180.0, -79.0, -180.0));
		m_MapController = m_MapView.getController();
		m_MapController.setZoom(3);
		GeoPoint center = new GeoPoint(0,0);  
		m_MapController.setCenter(center);
		m_ResourceProxy = new DefaultResourceProxyImpl(getApplicationContext());
		
		m_MyLocDrawable = this.getResources().getDrawable(R.drawable.icon_geo);
		m_DestDrawable = this.getResources().getDrawable(R.drawable.dest);
		
		// add my location overlay
		m_LocationList = new ArrayList<OverlayItem>();	 
		m_MyLocationOverlay = new ItemizedIconOverlay<OverlayItem>(m_LocationList,
				new ItemizedIconOverlay.OnItemGestureListener<OverlayItem>(){
			public boolean onItemSingleTapUp(final int index, final OverlayItem item)
			{
				return true;
			}
		 
			public boolean onItemLongPress(final int index,	final OverlayItem item) 
			{
				return true;
			}
			}, m_ResourceProxy);
		m_MapView.getOverlays().add(m_MyLocationOverlay);
		
		// add shop overlay
		Bitmap m_Bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.over);
		double m_dScale = 2 * Math.pow(2, m_MapView.getZoomLevel()) / 8;
		m_ShopDrawable= (Drawable)new BitmapDrawable(getScaleBitmap(m_Bitmap, (float)m_dScale));
		m_ShopItem = new OverlayItem("A shop","Shop info", new GeoPoint(-46.5,23.8));
		m_ShopItemList = new ArrayList<OverlayItem>();
		m_ShopDrawable.setAlpha(0);
		m_ShopItem.setMarker(m_ShopDrawable);
		m_ShopItemList.add(m_ShopItem);
		
		m_ShopOverlay = new ItemizedIconOverlay<OverlayItem>(m_ShopItemList,
			    new ItemizedIconOverlay.OnItemGestureListener<OverlayItem>(){
				public boolean onItemSingleTapUp(final int index, final OverlayItem item)
				{
					Log.i("sysout","singleTapUp");					
					m_ShopItemList.remove(m_ShopItem);
					m_ShopDrawable.setAlpha(100);
					m_ShopItem.setMarker(m_ShopDrawable);
					m_ShopItemList.add(m_ShopItem);
					
					setPopView();
					showPopView();
					m_bIsShowOverIcon = true;
					return true;
				}
				public boolean onItemLongPress(final int index,	final OverlayItem item) 
				{
					Log.i("sysout","isOk"+m_MapView.getZoomLevel());
					return true;
				}				
			  }, m_ResourceProxy);
		
    	m_MapView.getOverlays().add(m_ShopOverlay);    	
    	m_MapView.invalidate();
    
    }
    
    
    public void setPopView()
    {
    	m_ShopPopView = super.getLayoutInflater().inflate(R.layout.overlay_pop, null);  
    	m_MapView.addView(m_ShopPopView, new MapView.LayoutParams(MapView.LayoutParams.WRAP_CONTENT, MapView.LayoutParams.WRAP_CONTENT, 
    			null, MapView.LayoutParams.BOTTOM_CENTER, 0, 0));  
    	m_ShopPopView.setVisibility(View.GONE);
    }
    
    public void showPopView()
    {
    	MapView.LayoutParams geoLP = (MapView.LayoutParams) m_ShopPopView.getLayoutParams();  
    	geoLP.geoPoint = new GeoPoint(-42.0,25.0);  
    	m_MapView.updateViewLayout(m_ShopPopView, geoLP);  
    	m_ShopPopView.setVisibility(View.VISIBLE);  
    	TextView  txtView_Title = (TextView) m_ShopPopView.findViewById(R.id.txtView_title);  
    	ImageView imgView_go = (ImageView) m_ShopPopView.findViewById(R.id.imgView_go);  
    	m_ShopPopView.setOnClickListener(new OnClickListener() {			
			public void onClick(View v) 
			{
				 Intent intent = new Intent(getApplicationContext(), BZBInfoView.class);
                 intent.putExtra("TITLE","德云社");
                 intent.putExtra("REQ", true);
                 intent.putExtra("ID", Integer.valueOf(10));
                 startActivity(intent);
			}
		});
    	  


    }
   

    /*public void showPoiPop()
    {
    	
    	Drawable popDrawable = MyMapView.this.getResources().getDrawable(R.drawable.pop);
    	OverlayItem popItem = new OverlayItem("popItem","popItem info", new GeoPoint(-42.0,25.0));
    	List<OverlayItem> poplayItemList = new ArrayList<OverlayItem>();
    	ItemizedIconOverlay<OverlayItem> poplay = new ItemizedIconOverlay<OverlayItem>(poplayItemList,
    			new ItemizedIconOverlay.OnItemGestureListener<OverlayItem>(){
    		public boolean onItemSingleTapUp(final int index, final OverlayItem item)
    		{
    			Log.i("sysout","singleTapUp2");
    			return true;
    		}
    		public boolean onItemLongPress(final int index,	final OverlayItem item) 
    		{
    			Log.i("sysout","isOk"+mapView.getZoomLevel());
    			return true;
    		}
    	}, mResourceProxy);
    	popItem.setMarker(popDrawable);
    	poplay.addItem(popItem);
    	
    	mapView.getOverlays().add(poplay);
    	mapView.invalidate();
    	
    }
    
    *//**
     * 显示POP
     *//*
    public void showPoiPop2()
    {
    	Drawable popDrawable = MyMapView.this.getResources().getDrawable(R.drawable.pop);
    	OverlayItem popItem = new OverlayItem("popItem","popItem info", new GeoPoint(-42.0,25.0));
    	List<OverlayItem> poplayItemList = new ArrayList<OverlayItem>();
    	ItemizedOverlayWithFocus<OverlayItem> poplay = new ItemizedOverlayWithFocus<OverlayItem>
    	          (
    	            MyMapView.this, poplayItemList,
    	            new ItemizedIconOverlay.OnItemGestureListener<OverlayItem>(){
    	            public boolean onItemSingleTapUp(final int index, final OverlayItem item)
    	            {
    	            	
    	                 return true;
    	             }
    	             public boolean onItemLongPress(final int index,	final OverlayItem item) 
    	             {
    	                  Log.i("sysout","isOk"+mapView.getZoomLevel());
    	                  return true;
    	             }}
    	           );
    	popItem.setMarker(popDrawable);
    	poplay.draw(new Canvas(),mapView, false);
    	poplay.addItem(popItem);
    	mapView.getOverlays().add(poplay);
    	mapView.invalidate();
    }*/
    
    /**
     * 导航
     * 绘制路线
     * 
     */
    public void drawRoute(List<PointLatLng> listPointLatLngs)
    {
    	float startLat=0.0f;
    	float startLng=0.0f;
    	float endLat=0.0f;
    	float endLng=0.0f;
    	
    	//绘制起始点和终点标志
    	startLat = listPointLatLngs.get(listPointLatLngs.size()-1).getLat();
    	startLng = listPointLatLngs.get(listPointLatLngs.size()-1).getLng();
    	endLat =   listPointLatLngs.get(0).getLat();
    	endLng =   listPointLatLngs.get(0).getLng();
    	drawDestPosition(endLat,endLng);
    	
    	//线路  
    	m_MapView.getOverlays().remove(m_MyPath);
        m_MyPath = new PathOverlay(Color.rgb(30,136,180), MyMapView.this);
        m_MyPath.getPaint().setStrokeWidth(6);
    	for (int i = 0; i < listPointLatLngs.size(); i++) 
    	{
    		GeoPoint geoPoint = new GeoPoint(listPointLatLngs.get(i).getLat(),listPointLatLngs.get(i).getLng());
    		m_MyPath.addPoint(geoPoint);
		}
    	m_MapView.getOverlays().add(m_MyPath); 
    	
    	//刷新地图  
    	m_MapView.invalidate();    	
    }
    
    /**
     * 绘制起始点和终点标志
     * @param latStart
     * @param lngStart
     * @param latEnd
     * @param lngEnd
     */
    public void drawDestPosition(float latEnd,float lngEnd)
    {
    	//终点
    	m_MyLocationOverlay.removeItem(m_DestItem);
    	m_DestItem = new OverlayItem("oitemStartEndPosition", "oitemStartEndPosition info", new GeoPoint(latEnd,lngEnd));
    	m_DestItem.setMarker(m_DestDrawable);
    	m_MyLocationOverlay.addItem(m_DestItem);
    }
    
    /**
     * 刷新位置，确定用户当前位置
     * @param lat
     * @param lng
     */
    public void refreshPosition(float lat,float lng)
    {		 
    	m_MyLocationOverlay.removeItem(m_MyLocItem);
    	m_MyLocItem = new OverlayItem("item1", "item1 info",new GeoPoint(lat, lng));
    	m_MyLocItem.setMarker(m_MyLocDrawable);
    	m_MyLocationOverlay.addItem(m_MyLocItem);
    	m_MapView.invalidate();
    }
    
	public List<Spot> wifiScan()
	{
		Spot m_Spot = null;
		List<ScanResult> m_ListResult = new ArrayList<ScanResult>();
		List<Spot> m_List = new ArrayList<Spot>();
				
		m_WifiManager.startScan();
		m_ListResult = m_WifiManager.getScanResults();	
		
		for (int i = 0; i < m_ListResult.size(); i++)
		{	
			m_Spot = new Spot();
			m_Spot.setName(m_ListResult.get(i).SSID);
			m_Spot.setMac(m_ListResult.get(i).BSSID);
			m_Spot.setLevel(m_ListResult.get(i).level+"");
			m_List.add(m_Spot);
		}
		
		return m_List;
    }
	
    /**
     * 方法名称�? initLeft1Menu
     * 作者：         Administrator
     * 方法描述�? 初始化Left1Menu
     * 输入参数�
     * 返回类型�? void
     */
    private void initLeft1Menu()
    {
        imgBtLeft1 = (ImageButton) findViewById(R.id.imgleft1);
        mLeft1MenuItem = new Left1MenuItem();
        mLeft1Menu = new Left1Menu(MyMapView.this, Left1MenuItem, mLeft1MenuItem);
        imgBtLeft1.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                if (mLeft1Menu.isShowing())
                {
                    //                    reportMenu.closeAnimateReport();
                    //                    reportMenu.dismiss();
                }
                else
                {
                	Log.i("sysout","isIn");
                    int h = getWindow().getWindowManager().getDefaultDisplay()
                            .getHeight();
                    int x = -60;
                    int y = (imgBtLeft1.getBottom()
                            - imgBtLeft1.getMeasuredHeight() - h / 2)
                            - UiHelper.dip2px(getApplicationContext(), 220) / 2;// + UiHelper.dip2px(getApplicationContext(), 5);
                    mLeft1Menu.setPosition(x, y);
                    Log.i("sysout","isSetPosition");
                    mLeft1Menu.show();
                    Log.i("sysout","isShow");
                    mLeft1Menu.openAnimateReport();
                    Log.i("sysout","isOpen");
                }

            }
        });
    }

    /**
     * 方法名称�? initLeft3Menu
     * 作者：         Administrator
     * 方法描述�? 初始化Left3Menu
     * 输入参数�? 
     * 返回类型�? void
     */
    private void initLeft3Menu()
    {
        imgBtLeft3 = (ImageButton) findViewById(R.id.imgleft3);
        mLeft3MenuItem = new Left3MenuItem();
        mLeft3Menu = new Left3Menu(MyMapView.this, Left3MenuItem, mLeft3MenuItem);
        imgBtLeft3.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (mLeft3Menu.isShowing())
                {
                    //                    reportMenu.closeAnimateReport();
                    //                    reportMenu.dismiss();
                }
                else
                {
                    int h = getWindow().getWindowManager().getDefaultDisplay()
                            .getHeight();
                    int x = -60;
                    int y = -(h
                            / 2
                            - UiHelper.dip2px(getApplicationContext(),
                                    90 * 2 + 85) / 2 - imgBtLeft3.getBottom());

                    //                            -((imgBtLeft3.getBottom()
                    //                            - imgBtLeft3.getMeasuredHeight() - h / 2)
                    //                            - UiHelper.dip2px(getApplicationContext(), 220) / 2) ;// + UiHelper.dip2px(getApplicationContext(), 5);
                    mLeft3Menu.setPosition(x, y);
                    mLeft3Menu.show();
                    mLeft3Menu.openAnimateReport();
                }

            }
        });
    }

    private void initRight1Menu()
    {
        imgBtRight1 = (ImageButton) findViewById(R.id.imgright1);
        mRight1MenuItem = new Right1MenuItem();
        mRight1Menu = new Right1Menu(MyMapView.this, right1MenuItem,
                mRight1MenuItem);
        imgBtRight1.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                if (mRight1Menu.isShowing())
                {
                    //                    reportMenu.closeAnimateReport();
                    //                    reportMenu.dismiss();
                }
                else
                {
                    int h = getWindow().getWindowManager().getDefaultDisplay()
                            .getHeight();
                    int x = 60;
                    int y = (imgBtRight1.getBottom()
                            - imgBtRight1.getMeasuredHeight() - h / 2)
                            - UiHelper.dip2px(getApplicationContext(), 220) / 2;// + UiHelper.dip2px(getApplicationContext(), 5);
                    mRight1Menu.setPosition(x, y);
                    mRight1Menu.show();
                    mRight1Menu.openAnimateReport();
                }

            }
        });
    }

    /**
     * 方法名称�? initRight2Menu
     * 作者：         Administrator
     * 方法描述�? 初始化Right2Menu
     * 输入参数�? 
     * 返回类型�? void
     */
    private void initRight2Menu()
    {
        imgBtRight2 = (ImageButton) findViewById(R.id.imgright2);
        mRight2MenuItem = new Right2MenuItem();
        mRight2Menu = new Right2Menu(MyMapView.this, right2MenuItem,
                mRight2MenuItem);
        imgBtRight2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (mRight2Menu.isShowing())
                {
                    //                    reportMenu.closeAnimateReport();
                    //                    reportMenu.dismiss();
                }
                else
                {
                    int h = getWindow().getWindowManager().getDefaultDisplay()
                            .getHeight();
                    int x = 60;
                    int y = -(h
                            / 2
                            - UiHelper.dip2px(getApplicationContext(),
                                    90 * 2 + 85) / 2 - imgBtLeft3.getBottom());
                    mRight2Menu.setPosition(x, y);
                    mRight2Menu.show();
                    mRight2Menu.openAnimateReport();
                }

            }
        });
    }

    /* (non-Javadoc)
     * (覆盖方法)
     * 方法名称�? onDestroy
     * 作者：         Administrator
     * 方法描述�? 	
     * @see android.app.Activity#onDestroy()
     */
    @Override
    protected void onDestroy()
    {
        Log.i(TAG, "onDestroy");
        //        removeDialog(AbConstant.DIALOGPROGRESS);
        //        UiHelper.removeProgressDialog();
        try
        {
            if (null != mLeft1Menu)
            {
                mLeft1Menu.release();
                mLeft1Menu = null;
            }
            if (null != mLeft2Menu)
            {
                mLeft2Menu.release();
                mLeft2Menu = null;
            }
            if (null != mLeft3Menu)
            {
                mLeft3Menu.release();
                mLeft3Menu = null;
            }
            if (null != mRight1Menu)
            {
                mRight1Menu.release();
                mRight1Menu = null;
            }
            if (null != mRight2Menu)
            {
                mRight2Menu.release();
                mRight2Menu = null;
            }
            mLeft1MenuItem = null;
            mLeft2MenuItem = null;
            mLeft3MenuItem = null;
            mRight1MenuItem = null;
            mRight2MenuItem = null;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        super.onDestroy();
    }

    Handler handlerExit = new Handler() 
	{
		@Override
		public void handleMessage(Message msg) 
		{
			super.handleMessage(msg);
			m_bIsQuit = false;
		}
	};
	
	 /* (non-Javadoc)
     * (覆盖方法)
     * 方法名称: onKeyDown
     * 作者：         Administrator
     * 方法描述: 	
     * @see android.app.Activity#onKeyDown(int, android.view.KeyEvent)
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
    	if(keyCode == KeyEvent.KEYCODE_BACK)
		{
    		Intent intent = new Intent(MyMapView.this,AreaUI.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        	finish();
		}
    	
        return true;
    }
    
    
	

	private class Left1MenuItem implements MenuItem
    {

        public void ItemClickListener(View view, int position, long id)
        {
            switch (position)
            {
                case 0:
                {
                 //   imgControl.clearFillShape();
                    System.out.println("0");
                    Intent intent = new Intent(getApplicationContext(),
                            BZBListView.class);
                    intent.putExtra("ID", position + 1);
                    intent.putExtra("TITLE", "景点");
                    startActivity(intent);
                    break;
                }
                case 1:
                {
                 //   imgControl.clearFillShape();
                    System.out.println("1");
                    Intent intent = new Intent(getApplicationContext(),
                            BZBListView.class);
                    intent.putExtra("ID", position + 1);
                    intent.putExtra("TITLE", "购物");
                    startActivity(intent);
                    break;
                }
                case 2:
                {
                 //  imgControl.clearFillShape();
                    System.out.println("2");
                    Intent intent = new Intent(getApplicationContext(),
                            BZBListView.class);
                    intent.putExtra("ID", position + 1);
                    intent.putExtra("TITLE", "休闲");
                    startActivity(intent);
                    break;
                }
                case 3:
                {
                   // imgControl.clearFillShape();
                    System.out.println("3");
                    Intent intent = new Intent(getApplicationContext(),
                            BZBListView.class);
                    intent.putExtra("ID", position + 1);
                    intent.putExtra("TITLE", "酒吧");
                    startActivity(intent);
                    break;
                }
                case 4:
                {
                 //   imgControl.clearFillShape();
                    System.out.println("4");
                    Intent intent = new Intent(getApplicationContext(),
                            BZBListView.class);
                    intent.putExtra("ID", position + 1);
                    intent.putExtra("TITLE", "美食");
                    startActivity(intent);
                    break;
                }
                case 5:
                {
                  //  imgControl.clearFillShape();
                    System.out.println("5");
                    Intent intent = new Intent(getApplicationContext(),
                            BZBListView.class);
                    intent.putExtra("ID", position + 1);
                    intent.putExtra("TITLE", "娱乐");
                    startActivity(intent);
                    break;
                }
            }

        }

    }
    
    private class Right1MenuItem implements MenuItem
    {

        @Override
        public void ItemClickListener(View view, int position, long id)
        {
        	switch (position)
            {
                case 0:
                {
                   // imgControl.clearFillShape();
                    Intent intent = new Intent(getApplicationContext(),
                            NewsListView.class);
                    intent.putExtra("ID", position + 101);
                    intent.putExtra("TITLE", "活动");
                    startActivity(intent);
                    break;
                }
                case 1:
                {
                 //   imgControl.clearFillShape();
                    Intent intent = new Intent(getApplicationContext(),
                            NewsListView.class);
                    intent.putExtra("ID", position + 101);
                    intent.putExtra("TITLE", "文化");
                    startActivity(intent);
                    break;
                }
                case 2:
                {
                    
                    break;
                }
                case 3:
                {
                    
                    break;
                }
                case 4:
                {
                    
                    break;
                }
                case 5:
                {
                    
                    break;
                }
            }


        }

    }

    private class Left2MenuItem implements MenuItem
    {

        @Override
        public void ItemClickListener(View view, int position, long id)
        {
            // TODO Auto-generated method stub

        }

    }

    private static class Left3MenuItem implements MenuItem
    {

        public void ItemClickListener(View view, int position, long id)
        {
            // TODO Auto-generated method stub

        }

    }

    

    private static class Right2MenuItem implements MenuItem
    {

        @Override
        public void ItemClickListener(View view, int position, long id)
        {
            // TODO Auto-generated method stub

        }

    }

	@Override
	public boolean onScroll(ScrollEvent arg0) {
		return false;
	}

	public boolean onZoom(ZoomEvent arg0) 
	{
	   if(m_bIsShowOverIcon)
	   {
		   setPopView();
		   showPopView();
		   
		   Bitmap m_Bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.over);
		   double m_dScale = 2 * Math.pow(2, arg0.getZoomLevel()) / 8;
		   m_ShopDrawable= (Drawable)new BitmapDrawable(getScaleBitmap(m_Bitmap, (float)m_dScale));	   
		   m_ShopItemList.remove(m_ShopItem);
		   m_ShopDrawable.setAlpha(100);
		   m_ShopItem.setMarker(m_ShopDrawable);
		   m_ShopItemList.add(m_ShopItem);
	   }
		return false;
	}
	
	private Bitmap getScaleBitmap(Bitmap bitmap,float scale)
	{
		//缩放功能
        Matrix matrix=new Matrix();
        matrix.postScale(scale, scale);
        Bitmap newBitmap = Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(), bitmap.getHeight(), matrix,true);
        Log.i("sysout",newBitmap.getWidth()+","+newBitmap.getHeight());
        return newBitmap;
	}

}
