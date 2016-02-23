/**
 * 项目名：     Travel
 * 文件名：     BZBListView.java
 * 文件描述： 
 * 作者：         Administrator
 * 创建时间：  2013年7月18日
 * 版权声明 ： Copyright (C) 2008-2010 RichPeak
 *
 */
package com.mendong.travel.view.bzb;

import java.util.List;
import java.util.zip.Inflater;

import org.jump.utils.view.UiHelper;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

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
import com.mendong.travel.view.Main;
import com.mendong.travel.view.adapter.bzb.BZBAdapter;
import com.mendong.travel.view.bzbinfo.BZBInfoView;

/**
 * 类名称：     BZBListView
 * 作者：         Administrator
 * 创建时间：  2013年7月18日 下午4:40:30
 * 类描述：     商家列表
 * 版权声明 ： Copyright (C) 2008-2010 RichPeak
 * 修改时间：  2013年7月18日 下午4:40:30
 *
 */
public final class BZBListView extends AbActivity
{

    private static final String TAG = "BZBListView";

    private AbPullToRefreshListView listBZB;

    private BZBLogic mLogic;

    private BZBAdapter adapter;
    
    private AbHttpPool mAbHttpPool;
    
    private String mTitle;
    
    private AbPlayView mAbAutoPlayView = null;
    
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
            intent.putExtra("HEAD",adapter.getBzbHeadBitmap());
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
        
        mTitle = intent.getStringExtra("TITLE");
     //   float length = 0;
        if (null != mTitle)
        {
        	this.setTitleText(mTitle);
        	//this.setTitleLayoutGravity(gravity1, gravity2);;
       // 	Paint paint = new Paint();
      //  	length = paint.measureText(mTitle);
        }
        
        
        //设置文本位置
        this.setTitleTextMargin(0,0,0, 0);
        
        //返回按钮键
        this.setLogoBackOnClickListener(new View.OnClickListener(){
            public void onClick(View v)
            {
                finish();
            }});
    //    this.setLogoLine(R.drawable.line);
        initTitleRightLayout();
        //this.setTitleLayoutGravity(Gravity.CENTER_HORIZONTAL, Gravity.CENTER_HORIZONTAL);
       // initAutoPlayView();
        initListView();
        
        
        mLogic.setmId(intent.getIntExtra("ID", -1));
        request();
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
                Intent intent = new Intent(BZBListView.this,BZBSearch.class); 
                intent.putExtra("TITLE", mTitle);
                startActivity(intent);
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
    }
    
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
    }*/

    private void initListView()
    {
    	
    	/*//Test 
        View v = mInflater.inflate(R.layout.list_items,null);
        TextView tv = (TextView) v.findViewById(R.id.itemsText);
        tv.setText("蒋寿山故居俗称“蒋百万宅”，建于光绪四年（1878年），现为江苏省文物保护单位...");*/
        
        listBZB = (AbPullToRefreshListView) findViewById(R.id.bzb_list_view);
        mLogic = BZBLogic.getInstance();
        adapter = new BZBAdapter(getBaseContext(), mLogic.getBZBList(),
                R.layout.list_items,
                new int[] { R.id.itemsIcon, R.id.itemsTitle, R.id.itemsText, R.id.itemsId}, mImgListener);
        listBZB.setAdapter(adapter);

        //item被点击事件
        listBZB.setOnItemClickListener(new OnItemClickListener()
        {
            @Override
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
                Intent intent = new Intent(getApplicationContext(), BZBInfoView.class);
                //更改
//                intent.putExtra("TITLE", mTitle + " > " + bzb.getName());
                intent.putExtra("TITLE",bzb.getName());
                intent.putExtra("REQ", true);
                intent.putExtra("ID", Integer.valueOf(bzb.getId()));
                startActivity(intent);

            }
        });



        //        adapter = new ImageListAdapter(getBaseContext(), logic.getBZBList());
        //        listBZB.setAdapter(adapter);
        //        listBZB.setOnItemClickListener(this);

        //        //获取ListView对象
        //        ListView mListView = (ListView) this.findViewById(R.id.bzb_list_view);
        //
        //        //ListView数据
        //        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        //        Map<String, Object> map = null;
        //
        //        mPhotoList
        //                .add("http://img01.taobaocdn.com/bao/uploaded/i3/13215035600700175/T1C2mzXthaXXXXXXXX_!!0-item_pic.jpg_230x230.jpg");
        //        mPhotoList
        //                .add("http://img01.taobaocdn.com/bao/uploaded/i2/13215025617307680/T1AQqAXqpeXXXXXXXX_!!0-item_pic.jpg_230x230.jpg");
        //        mPhotoList
        //                .add("http://img01.taobaocdn.com/bao/uploaded/i1/13215035569460099/T16GuzXs0cXXXXXXXX_!!0-item_pic.jpg_230x230.jpg");
        //        mPhotoList
        //                .add("http://img01.taobaocdn.com/bao/uploaded/i2/13215023694438773/T1lImmXElhXXXXXXXX_!!0-item_pic.jpg_230x230.jpg");
        //        mPhotoList
        //                .add("http://img01.taobaocdn.com/bao/uploaded/i3/13215023521330093/T1BWuzXrhcXXXXXXXX_!!0-item_pic.jpg_230x230.jpg");
        //        mPhotoList
        //                .add("http://img01.taobaocdn.com/bao/uploaded/i4/13215035563144015/T1Q.eyXsldXXXXXXXX_!!0-item_pic.jpg_230x230.jpg");
        //        mPhotoList
        //                .add("http://img01.taobaocdn.com/bao/uploaded/i3/13215023749568975/T1UKWCXvpXXXXXXXXX_!!0-item_pic.jpg_230x230.jpg");
        //
        //        for (int i = 0; i < 50; i++)
        //        {
        //            map = new HashMap<String, Object>();
        //            map.put("itemsIcon",
        //                    mPhotoList.get(new Random().nextInt(mPhotoList.size())));
        //            map.put("itemsTitle", "item" + i);
        //            map.put("itemsText", "item..." + i);
        //            list.add(map);
        //        }
        //
        //        //使用自定义的Adapter
        //        ImageListAdapter myListViewAdapter = new ImageListAdapter(this, list,
        //                R.layout.list_items, new String[] { "itemsIcon", "itemsTitle",
        //                        "itemsText" }, new int[] { R.id.itemsIcon,
        //                        R.id.itemsTitle, R.id.itemsText });
        //        mListView.setAdapter(myListViewAdapter);
        //        //item被点击事件
        //        mListView.setOnItemClickListener(new OnItemClickListener()
        //        {
        //            @Override
        //            public void onItemClick(AdapterView<?> parent, View view,
        //                    int position, long id)
        //            {
        //            }
        //        });
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
                
                listBZB.onRefreshComplete();
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
                    if(mLogic!=null)
                    {
                    	
                    	if (mLogic.hasNext())
                    	{
                    		//访问网络下载数据
                    		mLogic.requestData();
                    	}
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
                    listBZB.onScrollComplete(AbConstant.HAVE);
                }
                else
                {
                    //没有新数据了
                    listBZB.onScrollComplete(AbConstant.NOTHAVE);
                }

            }
        };

        listBZB.setRefreshItem(upItem);
        listBZB.setScrollItem(downItem);
        mAbHttpPool.download(upItem);
    }
    
    public interface MyOnClickListener {
        /**
         * Called when a view has been clicked.
         *
         * @param v The view that was clicked.
         */
        void onClick(View v, int position);
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
        
        if (null != listBZB)
        {
            listBZB.setAdapter(null);
            listBZB = null;
        }
        if(null != adapter)
        {
            adapter.release();
            adapter = null;
        }
        if (null != mLogic)
        {
            mLogic.release();
            mLogic = null;
        }
        if (null != mAbAutoPlayView)
        {
            mAbAutoPlayView.stopPlay();
            mAbAutoPlayView.removeAllViews();
            mAbAutoPlayView.destroyDrawingCache();
            mAbAutoPlayView = null;
        }
        mImgListener = null;
        super.onDestroy();
    }
    
    

    @Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
    {
    	finish();
		return super.onKeyDown(keyCode, event);
	}

	private static class Holder
    {
        ImageView mPlayImage;
        
        ProgressBar mProgressBar;
    }
    
    
}