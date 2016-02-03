/**
 * 项目名：     Travel
 * 文件名：     BZBInfoLogic.java
 * 文件描述： 
 * 作者：         Administrator
 * 创建时间：  2013年7月22日
 * 版权声明 ： Copyright (C) 2008-2010 RichPeak
 *
 */
package com.mendong.travel.control.bzbinfo;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.ab.global.AbAppException;
import com.mendong.travel.Bean.bzbinfo.BZBInfoBean;
import com.mendong.travel.Bean.bzbinfo.BZBInfoReqBean;
import com.mendong.travel.Bean.bzbinfo.BZBInfoRspBean;
import com.mendong.travel.Bean.map.MapBean;
import com.mendong.travel.Bean.map.MapReqBean;
import com.mendong.travel.Bean.map.MapRspBean;
import com.mendong.travel.control.ClsLogic;
import com.mendong.travel.control.map.MapLogic;
import com.mendong.travel.control.map.MapXml;
import com.mendong.travel.utils.InputStreamUtils;

/**
 * 类名称：     BZBInfoLogic
 * 作者：         Administrator
 * 创建时间：  2013年7月22日 下午2:09:07
 * 类描述：    
 * 版权声明 ： Copyright (C) 2008-2010 RichPeak
 * 修改时间：  2013年7月22日 下午2:09:07
 *
 */
public final class BZBInfoLogic extends ClsLogic
{
    
    private static final String TAG = "BZBInfoLogic";
    
    private final List<BZBInfoBean> mList = new ArrayList<BZBInfoBean>();

    private final List<BZBInfoBean> mNewList = new ArrayList<BZBInfoBean>();

    private BZBInfoXml xmlParser = new BZBInfoXml();
    
    /**
     * 商家ID
     */
    private int id = -1;
    
    private BZBInfoLogic(){}
    
    public static BZBInfoLogic getInstance()
    {
        return SingletonHolder.INSTANCE;
    }
    
    @Override
    public void init()
    {
        // TODO Auto-generated method stub
        
    }

    /**
     * 方法名称：  getId
     * 作者：          Administrator
     * 方法描述：  
     * 字段类型：  int
     * 返回字段：  id
     * 备注：
     */
    public int getId()
    {
        return id;
    }

    /**
     * 方法名称： setId
     * 作者：         Administrator
     * 方法描述：  
     * 字段类型：  int
     * 设置字段：  设置 id 给 id
     * 备注：
     */
    public void setId(int id)
    {
        this.id = id;
    }
    
    public BZBInfoBean getBZBInfoBean()
    {
        if(null == mList || mList.isEmpty())
        {
            return null;
        }
        return mList.get(0);
    }

    @Override
    public void requestData() throws AbAppException
    {
        try
        {
            
            BZBInfoReqBean req = new BZBInfoReqBean();
            req.setOpType("showShop");
            req.setId(String.valueOf(id));

            String param = xmlParser.create(req);

            byte[] data = param.getBytes();
            InputStream inputStream = request(data, URL);
            if (null != inputStream)
            {
                data = new InputStreamUtils().InputStreamTOByte(inputStream, 0);
                if (null != data && data.length > 0)
                {
                    BZBInfoRspBean rsp = xmlParser.parse(data);
                    if (null != rsp)
                    {
                        BZBInfoBean bzbInfo = rsp.getmBzbInfo();
                        if (null != bzbInfo)
                        {
                            mNewList.add(bzbInfo);
                            bzbInfo = null;
                            rsp.setmBzbInfo(null);
                        }
                    }
                }
            }
        }
        catch (Exception e)
        {
            if (null != mNewList && !mNewList.isEmpty())
            {
                mNewList.clear();
            }

            AbAppException mAbAppException = new AbAppException(e);
            throw mAbAppException;
        }
        
    }

    @Override
    public boolean updateData(boolean clearAllOld)
    {
        if (clearAllOld)
        {
            mList.clear();
        }
        if (null != mNewList && !mNewList.isEmpty())
        {
            mList.addAll(mNewList);
            mNewList.clear();
            return true;
        }
        return false;
    }

    @Override
    public void release()
    {
        id = -1;
        if (null != mList)
        {
            mList.clear();
        }
        if (null != mNewList)
        {
            mNewList.clear();
        }
        
    }
    
    private static class SingletonHolder
    {
        private static final BZBInfoLogic INSTANCE = new BZBInfoLogic();
    }

}
