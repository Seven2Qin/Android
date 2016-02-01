/**
 * 项目名：     Travel
 * 文件名：     MapLogic.java
 * 文件描述： 
 * 作者：         Administrator
 * 创建时间：  2013年7月22日
 * 版权声明 ： Copyright (C) 2008-2010 RichPeak
 *
 */
package com.mendong.travel.control.map;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import android.util.Log;

import com.ab.global.AbAppException;
import com.mendong.travel.Bean.map.MapBean;
import com.mendong.travel.Bean.map.MapReqBean;
import com.mendong.travel.Bean.map.MapRspBean;
import com.mendong.travel.control.ClsLogic;
import com.mendong.travel.utils.InputStreamUtils;

/**
 * 类名称：     MapLogic
 * 作者：         Administrator
 * 创建时间：  2013年7月22日 上午11:19:25
 * 类描述：    
 * 版权声明 ： Copyright (C) 2008-2010 RichPeak
 * 修改时间：  2013年7月22日 上午11:19:25
 *
 */
public final class MapLogic extends ClsLogic
{

    private static final String TAG = "MapLogic";

    private final List<MapBean> mMapList = new ArrayList<MapBean>();

    private final List<MapBean> mNewMapList = new ArrayList<MapBean>();

    private MapXml xmlParser = new MapXml();

    private float pointX;

    private float pointY;

    private MapLogic()
    {
    }

    public static MapLogic getInstance()
    {
        return SingletonHolder.INSTANCE;
    }

    /**
     * 方法名称：  getPointX
     * 作者：          Administrator
     * 方法描述：  
     * 字段类型：  float
     * 返回字段：  pointX
     * 备注：
     */
    public float getPointX()
    {
        return pointX;
    }

    /**
     * 方法名称： setPointX
     * 作者：         Administrator
     * 方法描述：  
     * 字段类型：  float
     * 设置字段：  设置 pointX 给 pointX
     * 备注：
     */
    public void setPointX(float pointX)
    {
        this.pointX = pointX;
    }

    /**
     * 方法名称：  getPointY
     * 作者：          Administrator
     * 方法描述：  
     * 字段类型：  float
     * 返回字段：  pointY
     * 备注：
     */
    public float getPointY()
    {
        return pointY;
    }

    /**
     * 方法名称： setPointY
     * 作者：         Administrator
     * 方法描述：  
     * 字段类型：  float
     * 设置字段：  设置 pointY 给 pointY
     * 备注：
     */
    public void setPointY(float pointY)
    {
        this.pointY = pointY;
    }
    
    /**
     * 方法名称：  getPoiList
     * 作者：         Administrator
     * 方法描述：  
     * 输入参数：  @return
     * 返回类型：  List<MapBean>
     */
    public List<MapBean> getPoiList()
    {
        return mMapList;
    }

    public void init()
    {
        // TODO Auto-generated method stub

    }

    public void requestData() throws AbAppException
    {
        try
        {
            MapReqBean req = new MapReqBean();
            req.setOpType("showShopArea");
            req.setPointX(String.valueOf(pointX));
            req.setPointY(String.valueOf(pointY));

            String param = xmlParser.create(req);

            byte[] data = param.getBytes();
            InputStream inputStream = request(data, URL);
            if (null != inputStream)
            {
                data = new InputStreamUtils().InputStreamTOByte(inputStream, 0);
                if (null != data && data.length > 0)
                {
                    MapRspBean rsp = xmlParser.parse(data);
                    if (null != rsp)
                    {
                        List<MapBean> pois = rsp.getPoi();
                        if (null != pois && !pois.isEmpty())
                        {
                            mNewMapList.addAll(pois);
                            Log.i("sysout","pois.get(0)->Id:"
                                +pois.get(0).getId()+",Name:"
                                +pois.get(0).getName()+",Area:"
                                +pois.get(0).getArea());
                            pois.clear();
                            rsp.setPoi(null);
                        }
                    }
                }
            }
        }
        catch (Exception e)
        {
            if (null != mNewMapList && !mNewMapList.isEmpty())
            {
                mNewMapList.clear();
            }

            AbAppException mAbAppException = new AbAppException(e);
            throw mAbAppException;
        }

    }

    public boolean updateData(boolean clearAllOld)
    {
        if (clearAllOld)
        {
            mMapList.clear();
        }
        if (null != mNewMapList && !mNewMapList.isEmpty())
        {
            mMapList.addAll(mNewMapList);
            mNewMapList.clear();
            return true;
        }
        return false;
    }

    public void release()
    {
        if (null != mMapList)
        {
            mMapList.clear();
        }
        if (null != mNewMapList)
        {
            mNewMapList.clear();
        }

    }

    private static class SingletonHolder
    {
        private static final MapLogic INSTANCE = new MapLogic();
    }

}
