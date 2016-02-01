package com.mendong.travel.ui;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.Button;

import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.LocationListener;
import com.baidu.mapapi.MKLocationManager;
import com.mendong.travel.R;
import com.mendong.travel.TravelApp;
import com.mendong.travel.view.map.MyMapView;

public class AreaUI extends Activity implements LocationListener{

	private final double BASIC_DISTANCE =100.0;
	//中心点--德云社的经纬度
	private final double NI_LNG = 118.794197;
	private final double NI_LAT = 32.018382;
	private Button btn_area_go;
	private Button btn_area_stroll;
	private double lng;
	private double lat;
	private double m_distance;
	private String strLoc;
	
	private BMapManager mapManager;
	private MKLocationManager mLocationManager = null;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.layout_area);
		TravelApp.getInstance().addActivity(this);
		
		Intent intent = this.getIntent();
		strLoc = intent.getStringExtra("location");
		mapManager = new BMapManager(getApplication());
        mapManager.init("C66C0501D0280744759A6957C42543AE38F5D540", null);
        //	super.initMapActivity(mapManager);
        
        mLocationManager = mapManager.getLocationManager();
        mLocationManager.requestLocationUpdates(this);
        mLocationManager.enableProvider((int) MKLocationManager.MK_GPS_PROVIDER);
        
		
		btn_area_go = (Button)findViewById(R.id.btn_area_go);
		btn_area_stroll = (Button)findViewById(R.id.btn_area_stroll);
		//增加监听
		addListenerToButton();
	
		m_distance = distanceByLngLat(lng, lat, NI_LNG, NI_LAT);
		//Toast.makeText(this,lng+","+lat,Toast.LENGTH_SHORT).show();
	}
	
	
	
	private void addListenerToButton()
	{
		btn_area_go.setOnClickListener(new OnClickListener() 
		{
			public void onClick(View v) 
			{
				Intent intent = null; 
				//当前位置在景区外，使用百度地图
				if(m_distance>BASIC_DISTANCE)
				{
					//Uri uri = Uri.parse("geo:32.019026,118.794363,江苏省南京市秦淮区老门东景区");
					//Uri uri = Uri.parse("market://details?id=com.baidu.BaiduMap");  
					//intent.setData(uri); 
					//intent.setData(uri); 
					//intent.setPackage("com.baidu.BaiduMap");
					if(isAvilible(AreaUI.this,"com.baidu.BaiduMap"))
					{
						try 
						{
							intent = Intent.getIntent("intent://map/direction?origin=latlng:"+lat+","+lng+"|name:我的位置&destination=老门东&mode=driving&region=南京&src=yourCompanyName|yourAppName#Intent;"
									+"scheme=bdapp;package=com.baidu.BaiduMap;end");
						} catch (URISyntaxException e) {e.printStackTrace();}
					}
					else
					{
						String url = "http://map.baidu.com";
						intent = new Intent(Intent.ACTION_VIEW);
						Uri uri = Uri.parse(url);
						intent.setData(Uri.parse(url));
					}
							
				}
				else
				{
					intent = new Intent(AreaUI.this, MyMapView.class);
			        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				}
				AreaUI.this.startActivity(intent); 
				
			}
		});
		
		btn_area_stroll.setOnClickListener(new OnClickListener() 
		{
			public void onClick(View v) 
			{
				Intent intent =new Intent(AreaUI.this,ViewUI.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				intent.putExtra("location", strLoc);
				startActivity(intent);
			}
		});
	}

    /**
     * 两组经纬度--计算两点之间的距离
     * @param lng1
     * @param lat1
     * @param lng2
     * @param lat2
     * @return
     */
	public static double distanceByLngLat(double lng1, double lat1, double lng2, double lat2) 
	{
        double radLat1 = lat1 * Math.PI / 180;
        double radLat2 = lat2 * Math.PI / 180;
        double a = radLat1 - radLat2;
        double b = lng1 * Math.PI / 180 - lng2 * Math.PI / 180;
        double distance = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1)
                * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
        distance = distance * 6378137.0;// 取WGS84标准参考椭球中的地球长半径(单位:m)
        distance = Math.round(distance * 10000) / 10000;

        return distance;
    }
	
	/**
	 * 判断是否装了第三方软件
	 * @param context
	 * @param packageName
	 * @return
	 */
	private boolean isAvilible(Context context, String packageName)
	{           
		final PackageManager packageManager = context.getPackageManager();
		//获取packagemanager           
		List< PackageInfo> pinfo = packageManager.getInstalledPackages(0);
		//获取所有已安装程序的包信息           
		List<String> pName = new ArrayList<String>();
		//用于存储所有已安装程序的包名          
		//从pinfo中将包名字逐一取出，压入pName list中               
		if(pinfo != null)
		{               
			for(int i = 0; i < pinfo.size(); i++)
			{                   
				String pn = pinfo.get(i).packageName;                   
				pName.add(pn);               
			}          
		}         
		//判断pName中是否有目标程序的包名，有TRUE，没有FALSE
		return pName.contains(packageName);
	}

	public void onLocationChanged(Location location) 
	{
		if (location != null)
		{
			this.lng = location.getLongitude();
			this.lat = location.getLatitude();
		}
	}
	
	protected void onDestroyMap() {
		if (mapManager != null) {
			mapManager.destroy();
			mapManager = null;
		}
		mLocationManager = null;
		super.onDestroy();
	}

	protected void onPause() {
		if (mapManager != null) {
			mapManager.stop();
		}
		super.onPause();
	}

	protected void onResume() {
		if (mapManager != null) {
			mapManager.start();
		}
		super.onResume();
	}
	
	
	

}
