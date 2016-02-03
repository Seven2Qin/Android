package com.mendong.travel.ui;

import com.mendong.travel.R;
import com.mendong.travel.TravelApp;
import com.mendong.travel.view.map.MyMapView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

public class MainUI extends Activity  
{

	private Button btn_Shop;
	private Button btn_View;
	//是否退出
    private boolean m_bIsQuit = false;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.layout_main_ui);
		//TravelApp.getInstance().addActivity(this);
		btn_Shop = (Button)findViewById(R.id.btn_shop);
		btn_View = (Button)findViewById(R.id.btn_view);
		//为shop and view Button增加监听
		addListenerToButton();
	}
	
	
	
	public void addListenerToButton()
	{
		btn_Shop.setOnClickListener(new OnClickListener() 
		{
			public void onClick(View v) 
			{
				Intent intent =new Intent(MainUI.this,MyMapView.class);
				intent.putExtra("NO",1);
				startActivity(intent);
			}
		});
		
		btn_View.setOnClickListener(new OnClickListener() 
		{
			public void onClick(View v) 
			{
				Intent intent =new Intent(MainUI.this,CircleUI.class);
				intent.putExtra("NO",2);
				startActivity(intent);
			}
		});
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

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		
		if (keyCode == KeyEvent.KEYCODE_BACK)
        {
        	 if (!m_bIsQuit) 
        	 {  
        		 m_bIsQuit = true;  
        		 Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();  
        		 handlerExit.sendEmptyMessageDelayed(0, 2000);
             } 
        	 else 
        	 {  
        		 TravelApp.getInstance().exit();
        		 TravelApp.getInstance().endApp();
        	 }  
        }
		return true;
	}
	
	
	
	
	
}