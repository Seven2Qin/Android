/**
 * 项目名：     Travel
 * 文件名：     BZBInfo.java
 * 文件描述： 
 * 作者：         Administrator
 * 创建时间：  2013年7月22日
 * 版权声明 ： Copyright (C) 2008-2010 RichPeak
 *
 */
package com.mendong.travel.view.newsinfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.jump.utils.view.CollapsibleTextView;
import org.jump.utils.view.UiHelper;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.TextView.BufferType;

import com.ab.activity.AbActivity;
import com.ab.global.AbConstant;
import com.ab.net.AbHttpCallback;
import com.ab.net.AbHttpItem;
import com.ab.net.AbHttpQueue;
import com.ab.net.AbImageDownloadCallback;
import com.ab.net.AbImageDownloadItem;
import com.ab.net.AbImageDownloadQueue;
import com.ab.view.AbOnChangeListener;
import com.ab.view.AbPlayView;
import com.mendong.travel.R;
import com.mendong.travel.TravelApp;
import com.mendong.travel.Bean.bzbinfo.BZBInfoBean;
import com.mendong.travel.control.bzbinfo.BZBInfoLogic;
import com.ab.activity.AbActivity;
/**
 * 类名称：     BZBInfo
 * 作者：         Administrator
 * 创建时间：  2013年7月22日 上午10:35:21
 * 类描述：    
 * 版权声明 ： Copyright (C) 2008-2010 RichPeak
 * 修改时间：  2013年7月22日 上午10:35:21
 *
 */
public final class NewsInfoView extends AbActivity
{
    private static final String TAG = "NewsInfo";

    private boolean mRequest;

    private int mBZBId;

    private BZBInfoLogic mLogic;

    private AbPlayView mAbAutoPlayView = null;

    private CollapsibleTextView mInfoTextView;

    /**
     * 缓存滑动图片
     */
    private List<Holder> mImageViewList = new ArrayList<Holder>();
    
    //这个用来 保存 bitmap  
    private Map<String, Bitmap> bitmapList = new HashMap<String, Bitmap>();
    
    private WebView wv;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setAbContentView(R.layout.news_info);
        //增加到Acitivity链表
        TravelApp.getInstance().addActivity(this);
        getExtraIntent();

        this.setLogo(R.drawable.back);
        this.setTitleLayoutBackground(R.drawable.bg_titlebar);
        this.setTitleTextMargin(10, 0, 0, 0);
        this.setLogoBackOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
            	finish();
            }
        });
    //    initTitleRightLayout();
        
        wv = (WebView)findViewById(R.id.webView1);
		WebSettings settings = wv.getSettings(); 
		settings.setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN); 
		
        //initLayout();

        mLogic = BZBInfoLogic.getInstance();
        request();
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

        String title = intent.getStringExtra("TITLE");
        if (null != title)
        {
            this.setTitleText(title);
        }
        boolean req = intent.getBooleanExtra("REQ", false);
        mRequest = req;

        int id = intent.getIntExtra("ID", -1);
        mBZBId = id;
    }

    /**
     * 方法名称：  initTitleRightLayout
     * 作者：         Administrator
     * 方法描述：  初始化Title栏右侧布局
     * 输入参数：  
     * 返回类型：  void
     */
    private void initTitleRightLayout()
    {
        clearRightView();
        View rightViewApp = mInflater.inflate(R.layout.app_btn, null);
        addRightView(rightViewApp);
        ImageButton appBtn = (ImageButton) rightViewApp
                .findViewById(R.id.btnback);

        appBtn.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                finish();
            }
        });

    }   

    private void request()
    {
        if (!mRequest || mBZBId == -1)
        {
            return;
        }

        //        showDialog(AbConstant.DIALOGPROGRESS);

        this.showProgressDialog("请稍后", false);

        //        UiHelper.showProgressDialog(this, null, "请稍后", null, null, null, null, false);

        AbHttpQueue mAbHttpQueue = AbHttpQueue.getInstance();

        final AbHttpItem item = new AbHttpItem();

        item.callback = new AbHttpCallback()
        {
            boolean error = false;

            @Override
            public void get()
            {
                try
                {
                    Thread.sleep(1000);
                    //访问网络下载数据
                    mLogic.setId(mBZBId);
                    mLogic.requestData();
                }
                catch (Exception e)
                {
                    error = true;
                    e.printStackTrace();
                }

            }

            @Override
            public void update()
            {
                removeProgressDialog();
                //                removeDialog(AbConstant.DIALOGPROGRESS);

                //                UiHelper.removeProgressDialog();

                if (error)
                {
                    Toast.makeText(getApplicationContext(), "数据获取失败",
                            Toast.LENGTH_LONG).show();
                    return;
                }
                if (mLogic.updateData(false))
                {
                    BZBInfoBean bean = mLogic.getBZBInfoBean();
                    String url = "http://www.xiaooo.cn/mobile/shop/"+bean.getInfo();
                    System.out.println("load url from: "+ url);
                    wv.loadUrl(url);
            		wv.setWebViewClient(new WebViewClient(){
            			public boolean shouldOverrideUrlLoading(WebView view, String url){
            				view.loadUrl(url);
            				return true;
            			}
            		});

                }

            }

        };
        mAbHttpQueue.download(item);
    }

    /* (non-Javadoc)
     * (覆盖方法)
     * 方法名称：  onDestroy
     * 作者：         Administrator
     * 方法描述：    
     * @see android.support.v4.app.FragmentActivity#onDestroy()
     */
    @Override
    protected void onDestroy()
    {
        Log.i(TAG, "onDestroy");
        //        removeDialog(AbConstant.DIALOGPROGRESS);
        this.removeProgressDialog();
        //        UiHelper.removeProgressDialog();
        if (null != mImageViewList)
        {
            mImageViewList.clear();
            mImageViewList = null;
        }
        if (null != mAbAutoPlayView)
        {
            mAbAutoPlayView.removeAllViews();
            mAbAutoPlayView.destroyDrawingCache();
            mAbAutoPlayView = null;
        }
        if (null != mLogic)
        {
            mLogic.release();
            mLogic = null;
        }
        if (null != bitmapList)
        {
//            Iterator iter = bitmapList.entrySet().iterator();
//            while (iter.hasNext())
//            {
//                Map.Entry<String, Bitmap> entry = (Map.Entry<String, Bitmap>) iter
//                        .next();
//                //                String key = entry.getKey();
//                Bitmap bitmap = entry.getValue();
//                if (null != bitmap && !bitmap.isRecycled())
//                {
//                    bitmap.recycle();
//                    bitmap = null;
//                }
//            }
            bitmapList.clear();
            bitmapList = null;
        }
        super.onDestroy();
    }
    
    private static class Holder
    {
        ImageView mPlayImage;
        
        ProgressBar mProgressBar;
    }
}
