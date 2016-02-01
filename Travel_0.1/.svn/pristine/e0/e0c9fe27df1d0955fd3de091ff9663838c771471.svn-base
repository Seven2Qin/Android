/**
 * 项目名：     Travel
 * 文件名：     CheckVersionLogic.java
 * 文件描述： 
 * 作者：         Administrator
 * 创建时间：  2013年7月22日
 * 版权声明 ： Copyright (C) 2008-2010 RichPeak
 *
 */
package com.mendong.travel.control.checkversion;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.ab.global.AbAppException;
import com.mendong.travel.TravelApp;
import com.mendong.travel.Bean.checkversion.CheckVersionBean;
import com.mendong.travel.Bean.checkversion.CheckVersionReqBean;
import com.mendong.travel.Bean.checkversion.CheckVersionRspBean;
import com.mendong.travel.control.ClsLogic;
import com.mendong.travel.control.bzbinfo.BZBInfoLogic;
import com.mendong.travel.utils.InputStreamUtils;

/**
 * 类名称：     CheckVersionLogic
 * 作者：         Administrator
 * 创建时间：  2013年7月22日 下午10:34:22
 * 类描述：    
 * 版权声明 ： Copyright (C) 2008-2010 RichPeak
 * 修改时间：  2013年7月22日 下午10:34:22
 *
 */
public final class CheckVersionLogic extends ClsLogic
{

    private static final String TAG = "CheckVersionLogic";

    private final List<CheckVersionBean> mList = new ArrayList<CheckVersionBean>();

    private final List<CheckVersionBean> mNewList = new ArrayList<CheckVersionBean>();

    private CheckVersionXml xmlParser = new CheckVersionXml();
    
    

    private CheckVersionLogic()
    {
    }

    public static CheckVersionLogic getInstance()
    {
        return SingletonHolder.INSTANCE;
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

            CheckVersionReqBean req = new CheckVersionReqBean();
            req.setOpType("checkVer");
            req.setCtype("1");
            req.setVid("v1.0.0");
            req.setLat("0");
            req.setLng("0");
            String param = xmlParser.create(req);

            byte[] data = param.getBytes();
            InputStream inputStream = request(data, URL);
            if (null != inputStream)
            {
                data = new InputStreamUtils().InputStreamTOByte(inputStream, 0);
                if (null != data && data.length > 0)
                {
                    CheckVersionRspBean rsp = xmlParser.parse(data);
                    if (null != rsp)
                    {
                        CheckVersionBean checkVerBean = rsp
                                .getmCheckVersionBean();
                        if (null != checkVerBean)
                        {
                            mNewList.add(checkVerBean);
                            checkVerBean = null;
                            rsp.setmCheckVersionBean(checkVerBean);
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
            if(null != mList)
            {
                mList.clear();
            }
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
        if (null != mList)
        {
            mList.clear();
        }
        if (null != mNewList)
        {
            mNewList.clear();
        }

    }

    public boolean isEnter()
    {
        if (null == mList || mList.isEmpty())
        {
            return false;
        }
        CheckVersionBean bean = mList.get(0);
        if (null == bean)
        {
            return false;
        }
        String rang = bean.getRang();
        if ("1".equals(rang))
        {
            return true;
        }
        return false;
    }
    

    
    public String getUpFlag()
    {
        if (null == mList || mList.isEmpty())
        {
            return null;
        }
        CheckVersionBean bean = mList.get(0);
        if (null == bean)
        {
            return null;
        }
        String flag = bean.getUpflag();

        return flag;
    }
    
    public double getLat()
    {
        if (null == mList || mList.isEmpty())
        {
            return 0.0D;
        }
        CheckVersionBean bean = mList.get(0);
        if (null == bean)
        {
            return 0.0D;
        }
        
        double lat = bean.getLatitude();
        
        return lat;
    }
    
    public double getLng()
    {
        if (null == mList || mList.isEmpty())
        {
            return 0.0D;
        }
        CheckVersionBean bean = mList.get(0);
        if (null == bean)
        {
            return 0.0D;
        }
        
        double lng = bean.getLongitude();
        
        return lng;
    }

    private static class SingletonHolder
    {
        private static final CheckVersionLogic INSTANCE = new CheckVersionLogic();
    }

}
