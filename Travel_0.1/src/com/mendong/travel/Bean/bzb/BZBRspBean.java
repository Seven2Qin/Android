/**
 * 项目名：     Travel
 * 文件名：     BZBRsp.java
 * 文件描述： 
 * 作者：         Administrator
 * 创建时间：  2013年7月21日
 * 版权声明 ： Copyright (C) 2008-2010 RichPeak
 *
 */
package com.mendong.travel.Bean.bzb;

import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 类名称：     BZBRsp
 * 作者：         Administrator
 * 创建时间：  2013年7月21日 上午6:53:34
 * 类描述：      商家列表请求返回数据
 * 版权声明 ： Copyright (C) 2008-2010 RichPeak
 * 修改时间：  2013年7月21日 上午6:53:34
 *
 */
public class BZBRspBean implements Parcelable
{
    /**
     * 返回码
     */
    private String rstCode;
    
    /**
     * 请求的第一页返回当前类型ID的总记录数
     */
    private int total;
    
    /**
     * 商家列表
     */
    private List<BZBBean> shops;

    /**
     * 方法名称：  getRstCode
     * 作者：          Administrator
     * 方法描述：  
     * 字段类型：  String
     * 返回字段：  rstCode
     * 备注：
     */
    public String getRstCode()
    {
        return rstCode;
    }

    /**
     * 方法名称： setRstCode
     * 作者：         Administrator
     * 方法描述：  
     * 字段类型：  String
     * 设置字段：  设置 rstCode 给 rstCode
     * 备注：
     */
    public void setRstCode(String rstCode)
    {
        this.rstCode = rstCode;
    }

    /**
     * 方法名称：  getTotal
     * 作者：          Administrator
     * 方法描述：  
     * 字段类型：  int
     * 返回字段：  total
     * 备注：
     */
    public int getTotal()
    {
        return total;
    }

    /**
     * 方法名称： setTotal
     * 作者：         Administrator
     * 方法描述：  
     * 字段类型：  int
     * 设置字段：  设置 total 给 total
     * 备注：
     */
    public void setTotal(int total)
    {
        this.total = total;
    }

    /**
     * 方法名称：  getShops
     * 作者：          Administrator
     * 方法描述：  
     * 字段类型：  List<BZBBean>
     * 返回字段：  shops
     * 备注：
     */
    public List<BZBBean> getShops()
    {
        return shops;
    }

    /**
     * 方法名称： setShops
     * 作者：         Administrator
     * 方法描述：  
     * 字段类型：  List<BZBBean>
     * 设置字段：  设置 shops 给 shops
     * 备注：
     */
    public void setShops(List<BZBBean> shops)
    {
        this.shops = shops;
    }

    @Override
    public int describeContents()
    {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        // TODO Auto-generated method stub
        
    }
    
    
}
