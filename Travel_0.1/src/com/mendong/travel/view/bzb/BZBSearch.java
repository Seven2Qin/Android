package com.mendong.travel.view.bzb;

import android.content.Intent;
import android.os.Bundle;

import com.ab.activity.AbActivity;
import com.mendong.travel.R;

public class BZBSearch  extends AbActivity{

	
	private String mTitle;
	
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setAbContentView(R.layout.bzb_search);
		Intent intent = this.getIntent();
	    this.setLogo(R.drawable.back);
	    this.setTitleLayoutBackground(R.drawable.bg_titlebar);
	    mTitle = intent.getStringExtra("TITLE");
	    if (null != mTitle)
        {
        	this.setTitleText(mTitle);
        }
        //设置文本位置
      //  this.setTitleTextMargin(100, 0,0, 0);
	}
	
	
	


}
