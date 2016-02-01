/**
 * 项目名：       SystemService
 * 文件名：       ClsDownloader.java
 * 文件描述： 
 * 作者：            Administrator
 * 创建时间：  2012-6-21
 * 版权声明 ： Copyright (C) 2008-2010 RichPeak
 *
 */
package com.mendong.travel.network;

import com.mendong.travel.utils.InputStreamUtils;
import com.mendong.travel.utils.log.ClsLog;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/**
 * 类名称：       ClsDownloader
 * 作者：            Administrator
 * 创建时间：  2012-6-21 下午3:49:36
 * 类描述：    
 * 版权声明 ： Copyright (C) 2008-2010 RichPeak
 * 修改时间：  2012-6-21 下午3:49:36
 *
 */
public class ClsBaseDownloader
{
    public static final String TAG = "ClsDownload";
    public DownLoadWorker mWorker;
    public ClsUrlConnect mUrlCon = new ClsUrlConnect();
    public Context mContext;
    public boolean IsThreadRun = true;
    public InputStreamUtils mInputStreamUtils = new InputStreamUtils();

    //    public ICallBack mCallBack;

    public ClsBaseDownloader(Context c)
    {
        mContext = c;
    }

    public synchronized void setCurrentWorker(DownLoadWorker worker)
    {
        try
        {
            if (null != mWorker)
            {
                IsThreadRun = false;
                mWorker.interrupt();
                mWorker.join();
            }
            mWorker = worker;
        }
        catch (InterruptedException e)
        {
            //            e.printStackTrace();
        }
    }

    public void cancelWorker()
    {
        if (null != mWorker)
        {
            IsThreadRun = false;
            mUrlCon.cancelPost();
            mWorker.interrupt();
            mWorker = null;
        }
    }

    public void isworker()
    {
        if (null == mWorker)
        {
            ClsLog.I(TAG, "mworker == null");
        }
        else
        {
            ClsLog.I(TAG, "mworker != null");
        }
    }

    public synchronized boolean isCurrentWorker(DownLoadWorker worker)
    {
        return (mWorker == worker);
    }

    //    public boolean doLoad(Context c, int requestCode, Object parameter,
    //            ICallBack call, boolean useThread)
    //    {
    //        if (!ClsNetWork.isConnectNetWork(c))
    //        {
    //            if (null != call)
    //            {
    //                ClsLog.I(TAG, "callback");
    //                call.onDownloadCallback(
    //                        requestCode,
    //                        SystemServiceConstants.ResultCode.RESULT_FAILE,
    //                        String.valueOf(SystemServiceConstants.ErrorCode.ERROR_DOWNLOAD));
    //            }
    //            return false;
    //        }
    //        mCallBack = call;
    //        DownLoadWorker worker = new DownLoadWorker(c, requestCode, parameter,
    //                call);
    //        setCurrentWorker(worker);
    //        IsThreadRun = true;
    //        if (useThread)
    //        {
    //            worker.start();
    //        }
    //        else
    //        {
    //            worker.run();
    //        }
    //        return true;
    //    }

    public boolean doLoad(Context c, ClsRequest req)
    {
        if (!ClsNetWork.isConnectNetWork(c))
        {
            if (null != req.getCallBack())
            {
                ClsLog.toast(c, "is not network, callback");
                req.getCallBack()
                        .onDownloadCallback(
                                req,
                                ClsNetWorkConstants.ResultCode.RESULT_FAILE,
                                String.valueOf(ClsNetWorkConstants.ErrorCode.ERROR_DOWNLOAD));
            }
            return false;
        }
        //        mCallBack = req.getCallBack();
        DownLoadWorker worker = new DownLoadWorker(c, req);
        setCurrentWorker(worker);
        IsThreadRun = true;
        if (req.isUseThread())
        {
            worker.start();
        }
        else
        {
            worker.run();
        }
        return true;
    }

    //    public boolean doLoad1(Context c, int requestCode, Object parameter,
    //            ICallBack call)
    //    {
    //        if (!ClsNetWork.isConnectNetWork(c))
    //        {
    //            if (call != null)
    //            {
    //                ClsLog.I(TAG, "callback");
    //                call.onDownloadCallback(
    //                        requestCode,
    //                        RESULT_FAILE,
    //                        String.valueOf(SystemServiceConstants.ErrorCode.ERROR_DOWNLOAD));
    //            }
    //            return false;
    //        }
    //        mCallBack = call;
    //        onSetDownloadWork(IsThreadRun, mContext, requestCode, parameter, call);
    //        IsThreadRun = true;
    //        return true;
    //    }

    public class DownLoadWorker extends Thread
    {
        //        private int mRequestCode;
        //        private Object mParameter;
        //        private Context mContext;
        //        private ICallBack mCall;

        private Context mContext;
        private ClsRequest mReq;

        //        public DownLoadWorker(Context c, int requestCode, Object parameter,
        //                ICallBack call)
        //        {
        //            mRequestCode = requestCode;
        //            mParameter = parameter;
        //            mContext = c;
        //            mCall = call;
        //
        //        }

        public DownLoadWorker(Context c, ClsRequest req)
        {
            mContext = c;
            mReq = req;
        }

        @Override
        public void run()
        {
            //Looper.prepare(); 
            onSetDownloadWork(IsThreadRun, mContext, mReq);
            //Looper.loop(); 
        }

    }

    public void onSetDownloadWork(boolean isthreadrun, Context c, ClsRequest req)
    {

    }
}
