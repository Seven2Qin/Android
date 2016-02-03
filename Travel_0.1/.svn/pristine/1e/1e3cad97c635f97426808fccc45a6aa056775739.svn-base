/**
 * 项目名：     Travel
 * 文件名：     BZBInfo.java
 * 文件描述： 
 * 作者：         Administrator
 * 创建时间：  2013年7月22日
 * 版权声明 ： Copyright (C) 2008-2010 RichPeak
 *
 */
package com.mendong.travel.view.bzbinfo;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.jump.utils.view.CollapsibleTextView;
import org.jump.utils.view.UiHelper;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
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
import com.mendong.travel.tools.Tools;
import com.mendong.travel.view.map.MyMapView;
import com.mendong.travel.wifi.Spot;
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
public final class BZBInfoView extends AbActivity
{
    private static final String TAG = "BZBInfo";

    private boolean mRequest;

    private int mBZBId;

    private BZBInfoLogic mLogic;
    //标题
    private String m_sTitle;
    //商家头像缩略图
    private ImageView imgView_BzbinfoHead;
    //商家名称
    private TextView txtView_BzbName;
    //商家简介
    private TextView txtView_BzbIntroduce;
    //预定商家
    private Button btn_orderBzb;
    //前往商家
    private Button btn_goBzb;
    //存储商家头像
    private Bitmap bitmapBzbHead;
    //商家图片浏览
    private AbPlayView mAbAutoPlayView = null;

 //   private CollapsibleTextView mInfoTextView;

    /**
     * 图片现在线程（按队列下载）
     */
    private AbImageDownloadQueue mAbImageDownloadQueue = null;

    /**
     * 缓存滑动图片
     */
    private List<Holder> mImageViewList = new ArrayList<Holder>();
    
    //这个用来 保存 bitmap  
    private Map<String,Bitmap> bitmapList = new HashMap<String, Bitmap>();
    
	//申明app对象
	private TravelApp app;
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setAbContentView(R.layout.bzb_info);

        //增加到Acitivity链表
        TravelApp.getInstance().addActivity(this);
        //构造TravelAPP对象
        app = (TravelApp) this.getApplication();
        getExtraIntent();

      //  this.setLogo(R.drawable.button_selector_logo);
        this.setTitleLayoutBackground(R.drawable.bg_titlebar);
        this.setTitleTextMargin(10, 0, 0, 0);
        this.setLogoBackOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
            	 finish();
            }
        });
      //  initTitleRightLayout();

        initLayout();

        mLogic = BZBInfoLogic.getInstance();
        mAbImageDownloadQueue = AbImageDownloadQueue.getInstance();

        request();
        if(m_sTitle!=null)
           txtView_BzbName.setText(m_sTitle);
        
        /*imgView_BzbinfoHead.setImageBitmap(Tools.combineBitmap(
        		BitmapFactory.decodeResource(getResources(), R.drawable.bzbinfo_icon), 
        		bitmapBzbHead));*/
        goBzb();
    }
    
    /**
     * 前往商家
     * 作者：秦盛伟
     * 返回类型:void
     */
    public void goBzb()
    {
    	btn_goBzb = (Button)findViewById(R.id.btn_goBzb);
    	//增加监听，返回地图界面
    	btn_goBzb.setOnClickListener(new OnClickListener() 
    	{
			public void onClick(View v) 
			{
				if(app.getTraceable())
				{
					changeActivityToMyMapView();
				}
				else
				{
					showTraceDialog();
				}
			}
		});
    }
    
    /**
     * Avtivity切换：由当前Activity切换至MyMapView Activity
     */
    public void changeActivityToMyMapView()
    {
    	finish();
		Intent intent = new Intent(BZBInfoView.this,MyMapView.class);
		intent.putExtra("startId",app.getCurrentUserPositionId());
		intent.putExtra("endId",mBZBId);
		intent.putExtra("isShowRoute",true);
		startActivity(intent);
    }
    
    public void showTraceDialog()
    {
    	Builder builder = new AlertDialog.Builder(this);
    	builder.setTitle("提示")
    	       .setMessage("定位功能尚未开启,是否立即打开？")
    	       .setPositiveButton("确定",new DialogInterface.OnClickListener(){

				public void onClick(DialogInterface dialog, int which) 
				{
					app.setTraceable(true);
					changeActivityToMyMapView();
				}})
    	       .setNegativeButton("取消",null)
    	       .show();
    	

    }
    
	 /**
	  * 从服务器上请求行走路线点
	  * @param Url
	  * @param startId:起始位置ID号
	  * @param endId:终点位置ID号
	  * @return
	  * @throws IllegalStateException
	  * @throws Exception
	  */
	public InputStream getPointDataStream(String Url,int startId,int endId) throws IllegalStateException, Exception
	{
		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(Url);
		try 
		{
		    StringEntity entity = new StringEntity(startId+","+endId);
		    post.setEntity(entity);
	    	HttpResponse httpResponse = client.execute(post);
	    	int ret = httpResponse.getStatusLine().getStatusCode();
	    	if(ret == HttpStatus.SC_OK)
	    	{
	    		return httpResponse.getEntity().getContent();
	    	}
	    	else
	    	{
	    		Toast.makeText(this, "数据请求失败！", Toast.LENGTH_SHORT).show();
	    	}
		} 
		catch (UnsupportedEncodingException e) {e.printStackTrace();} 
		catch (ClientProtocolException e) {e.printStackTrace();} 
		catch (IOException e) {e.printStackTrace();}
		
		return null;
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

        m_sTitle = intent.getStringExtra("TITLE");
        if (null != m_sTitle)
        {
            this.setTitleText(m_sTitle);
        }
        boolean req = intent.getBooleanExtra("REQ", false);
        mRequest = req;

        int id = intent.getIntExtra("ID", -1);
        mBZBId = id;
        //接收头像
        bitmapBzbHead = intent.getParcelableExtra("HEAD");
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

    private void initLayout()
    {
    	
    	txtView_BzbName = (TextView) findViewById(R.id.txtView_bzbName);
    	imgView_BzbinfoHead = (ImageView)findViewById(R.id.imgView_bzbinfoHead);
    	txtView_BzbIntroduce = (TextView) findViewById(R.id.txtView_bzbIntroduce);
    	btn_orderBzb = (Button) findViewById(R.id.btn_orderBzb);
    	btn_goBzb = (Button) findViewById(R.id.btn_goBzb);
    	
        mAbAutoPlayView = (AbPlayView) findViewById(R.id.mAbAutoPlayView);
        
        addPlayView();

    }

    private void downLoadImage(final int index, String url)
    {
        try
        {
            if (null == url || url.length() <= 0)
            {
                return;
            }
            final Holder holder = mImageViewList.get(index);
            if(null == holder)
            {
                return;
                
            }
            holder.mProgressBar.setVisibility(View.VISIBLE);
            AbImageDownloadItem item = new AbImageDownloadItem();
            item.imageUrl = BZBInfoLogic.PIC_PRE_URL + url;
            
          //  Log.i("sysout","url->"+url);
            //下载完成后更新界面
            item.callback = new AbImageDownloadCallback()
            {
                public void update(Bitmap bitmap, String imageUrl)
                {
                        if (bitmap != null && !bitmap.isRecycled())
                        {
//                            Log.d(TAG,
//                                    index + " --> update bitmap w * h "
//                                            + (bitmap.getWidth()) + " * "
//                                            + bitmap.getHeight());
                            putBitmap(bitmap, imageUrl);
//                            ImageView mPlayImage = mImageViewList.get(index);
                            ///最近更新
                            holder.mPlayImage.setImageBitmap(bitmap);
                           // imgView_BzbPicture.setImageBitmap(bitmap);
                         /**imgView_BzbPicture.setImageBitmap(bitmap);**/
                        }
                        holder.mProgressBar.setVisibility(View.GONE);

                }
            };
            mAbImageDownloadQueue.download(item);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public void putBitmap(Bitmap bitmap, String imageUrl)
    {
//        if (null != bitmapList && !bitmapList.containsKey(imageUrl))
//        {
//            bitmapList.put(imageUrl, bitmap);
//        }
    }

    private void addPlayView()
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
        //        mPlayImage.setBackgroundResource(R.drawable.info1);
        mAbAutoPlayView.setPageLineHorizontalGravity(Gravity.CENTER);
        mAbAutoPlayView.addView(mPlayView);

        mImageViewList.add(holder);
    }

    private void updatePlayView(BZBInfoBean info)
    {
        if (null == info)
        {
            return;
        }

        String text = info.getInfo();
        if (null != text && text.length() > 0)
        {
        	//内容简介
        	txtView_BzbIntroduce.setText(text);;
           // mInfoTextView.setDesc(info.getInfo(), BufferType.NORMAL);
        }

        List<String> banners = info.getBanners();

        if (null == banners || banners.isEmpty())
        {
            return;
        }

        int size = banners.size();

        for (int i = 1; i < size; ++i)
        {
            addPlayView();
        }

        for (int i = 0; i < size; ++i)
        {
            downLoadImage(i, banners.get(i));
        }

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
                    updatePlayView(bean);
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
