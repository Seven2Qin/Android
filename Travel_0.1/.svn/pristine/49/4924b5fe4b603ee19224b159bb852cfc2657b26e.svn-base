/**
 * 项目名：     Travel
 * 文件名：     BZBLogic.java
 * 文件描述： 
 * 作者：         Administrator
 * 创建时间：  2013年7月20日
 * 版权声明 ： Copyright (C) 2008-2010 RichPeak
 *
 */
package com.mendong.travel.control.bzb;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.ab.global.AbAppException;
import com.mendong.travel.Bean.bzb.BZBBean;
import com.mendong.travel.Bean.bzb.BZBReqBean;
import com.mendong.travel.Bean.bzb.BZBRspBean;
import com.mendong.travel.control.ClsLogic;
import com.mendong.travel.utils.InputStreamUtils;

/**
 * 类名称：     BZBLogic
 * 作者：         Administrator
 * 创建时间：  2013年7月20日 下午2:08:13
 * 类描述：    
 * 版权声明 ： Copyright (C) 2008-2010 RichPeak
 * 修改时间：  2013年7月20日 下午2:08:13
 *
 */
public final class BZBLogic extends ClsLogic
{

    private static final int PER_PAGE_SIZE = 8;

    private final List<BZBBean> mBZBList = new ArrayList<BZBBean>();

    private final List<BZBBean> mNewBZBList = new ArrayList<BZBBean>();

    private boolean refreshFlag = false;

    private int totalNum;

    private int mId = 0;

    private int mPage = 0;

    private BZBXml xmlParser = new BZBXml();

    public void BZBLogic()
    {
    }

    public static BZBLogic getInstance()
    {
        return SingletonHolder.INSTANCE;
    }

    public List<BZBBean> getBZBList()
    {
        return mBZBList;
    }
    
    public int getDataSize()
    {
        if(null == mBZBList)
        {
            return 0;
        }
        
        return mBZBList.size();
    }

    public boolean hasNext()
    {
        return mBZBList.size() < totalNum;
    }

    /**
     * 方法名称：  getmId
     * 作者：          Administrator
     * 方法描述：  
     * 字段类型：  int
     * 返回字段：  mId
     * 备注：
     */
    public int getmId()
    {
        return mId;
    }

    /**
     * 方法名称： setmId
     * 作者：         Administrator
     * 方法描述：  
     * 字段类型：  int
     * 设置字段：  设置 mId 给 mId
     * 备注：
     */
    public void setmId(int mId)
    {
        this.mId = mId;
    }

    /**
     * 方法名称：  getmPage
     * 作者：          Administrator
     * 方法描述：  
     * 字段类型：  int
     * 返回字段：  mPage
     * 备注：
     */
    public int getmPage()
    {
        return mPage;
    }

    /**
     * 方法名称： setmPage
     * 作者：         Administrator
     * 方法描述：  
     * 字段类型：  int
     * 设置字段：  设置 mPage 给 mPage
     * 备注：
     */
    public void setmPage(int mPage)
    {
        this.mPage = mPage;
    }

    public boolean refreshFlag()
    {
        return refreshFlag;
    }

    @Override
    public void init()
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void requestData() throws AbAppException
    {
        try
        {
            if (mId <= 0)
            {
                AbAppException mAbAppException = new AbAppException("mId <= 0");
                throw mAbAppException;
            }

            ++mPage;
            BZBReqBean req = new BZBReqBean();
            req.setOpType("showShopList");
            req.setId(String.valueOf(mId));
            req.setPage(mPage);
            req.setSize(PER_PAGE_SIZE);
            String param = xmlParser.create(req);
            byte[] data = param.getBytes();
            InputStream inputStream = request(data, URL);
            if (null != inputStream)
            {
                data = new InputStreamUtils().InputStreamTOByte(inputStream, 0);
                if (null != data && data.length > 0)
                {

                    BZBRspBean rsp = xmlParser.parse(data);
                    if (null != rsp)
                    {
                        int total = rsp.getTotal();
                        if (total > 0)
                        {
                            totalNum = total;
                        }
                        List<BZBBean> shops = rsp.getShops();
                        if (null != shops && !shops.isEmpty())
                        {
                            mNewBZBList.addAll(shops);
                            shops.clear();
                            rsp.setShops(null);
                        }
                    }
                }
            }
        }
        catch (Exception e)
        {
            if (null != mNewBZBList && !mNewBZBList.isEmpty())
            {
                mNewBZBList.clear();
            }
            --mPage;
            AbAppException mAbAppException = new AbAppException(e);
            throw mAbAppException;
        }

    }

    @Override
    public boolean updateData(boolean clearAllOld)
    {
        if (clearAllOld)
        {
            mBZBList.clear();
        }
        if (null != mNewBZBList && !mNewBZBList.isEmpty())
        {
            mBZBList.addAll(mNewBZBList);
            mNewBZBList.clear();
            return true;
        }
        return false;
    }

    @Override
    public void release()
    {
        mPage = 0;
        mId = 0;
        totalNum = 0;
        refreshFlag = false;
        if (null != mNewBZBList)
        {
            mNewBZBList.clear();
        }
        if (null != mBZBList)
        {
            mBZBList.clear();
        }
    }

    private static class SingletonHolder
    {
        private static final BZBLogic INSTANCE = new BZBLogic();
    }

}
