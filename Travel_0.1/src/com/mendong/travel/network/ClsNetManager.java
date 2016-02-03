/**
 * 项目名：       SystemService
 * 文件名：       ClsNetManager.java
 * 文件描述： 
 * 作者：            Administrator
 * 创建时间：  2012-6-21
 * 版权声明 ： Copyright (C) 2008-2010 RichPeak
 *
 */
package com.mendong.travel.network;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.mendong.travel.utils.log.ClsLog;


/**
 * 类名称：       ClsNetManager
 * 作者：            Administrator
 * 创建时间：  2012-6-21 下午4:08:06
 * 类描述：    
 * 版权声明 ： Copyright (C) 2008-2010 RichPeak
 * 修改时间：  2012-6-21 下午4:08:06
 *
 */
public class ClsNetManager
{
    private static final String TAG = "ClsNetManager ";

    /**
     * 取消请求
     */
    private static final int END = 0;

    //    /**
    //     * 回调接口
    //     */
    //    private ICallBack mCallback;

    /**
     * 
     */
    protected Context mContext;

    /**
     * 请求队列
     */
    //    private HashMap<Integer, ClsDownLoader> mRequestMap = new HashMap<Integer, ClsDownLoader>();

    /**
     * 构造方法
     * 输入参数：  @param c
     */
    public ClsNetManager(Context c)
    {
        mContext = c;
    }

    /**
     * 方法名称：  sendRequest
     * 作者：            Administrator
     * 方法描述：  发送请求
     * 输入参数：  @param requestCode
     * 输入参数：  @param parameter
     * 返回类型：  void
     */
    public void sendRequest(ClsRequest request)
    {
        ClsLog.toast(mContext, "sendRequest");
        ClsDownLoader load = new ClsDownLoader(mContext);
        //        load.doLoad(mContext, requestCode, parameter, request.getCallBack(), useThread);
        load.doLoad(mContext, request);
        //        mRequestMap.put(requestCode, load);
    }

    /**
     * 方法名称：  cancelRequest
     * 作者：            Administrator
     * 方法描述：  取消请求
     * 输入参数：  @param requestCode
     * 返回类型：  void
     */
    public void cancelRequest(int requestCode)
    {
        MyHandler mMainHandler = new MyHandler(requestCode);
        mMainHandler.sendMessageDelayed(mMainHandler.obtainMessage(END), 100);

    }

    //    /**
    //     * 方法名称：  setOnCallback
    //     * 作者：            Administrator
    //     * 方法描述：  设置回调接口
    //     * 输入参数：  @param c
    //     * 返回类型：  void
    //     */
    //    public void setOnCallback(ICallBack c)
    //    {
    //        mCallback = c;
    //    }

    /**
     * 类名称：       MyHandler
     * 作者：            Administrator
     * 创建时间：  2012-6-21 下午4:24:37
     * 类描述：    
     * 版权声明 ： Copyright (C) 2008-2010 RichPeak
     * 修改时间：  2012-6-21 下午4:24:37
     *
     */
    private class MyHandler extends Handler
    {
        private int mRequest;

        public MyHandler(int r)
        {
            mRequest = r;
        }

        @Override
        public void handleMessage(Message msg)
        {
            ClsLog.I(TAG, "handleMessage::cancel");
            switch (msg.what)
            {
                case END:
                {
                    //                    ClsDownLoader load = mRequestMap.get(mRequest);
                    //                    if (null != load)
                    //                    {
                    //                        load.cancelWorker();
                    //                    }
                    break;
                }
                default:
                {
                    break;
                }
            }
            super.handleMessage(msg);
        }

    }
}
